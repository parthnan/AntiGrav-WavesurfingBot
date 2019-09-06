package group08;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;

import robocode.BulletHitEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.MessageEvent;
import robocode.RobotDeathEvent;//add
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;
import robocode.util.Utils;

public class Main extends TeamRobot {
	private String targetName = "none";//add
	private int enemyNum = 3;//add
	private double teamMateX;
	private double teamMateY;
	private double changehead;
	private double prevhead;
	private double prevtime;

	/* run:  Leader's default behavior*/
	public void run() {
		// 初期設定：しゅうへい　釣谷　担当分
		setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);
		//色の設定
		setColors(new Color(255,145,0),new Color(0,0,0),new Color(0,0,0),new Color(255,115,0),new Color(255,115,0));
		turnRadarRightRadians(Double.POSITIVE_INFINITY);

		//バトル上、常に働くもの
		 do {
			antiGravity();
			execute();//移動のために必要な関数
        } while (true);
	}

	/*onScannedRobot:  What to do when you see another robot
	 しゅうへい　釣谷　担当分*/
	public void onScannedRobot(ScannedRobotEvent e) { //edit
		double power;

		if(!isTeammate(e.getName()) && e.getName().indexOf("Walls") == -1 && enemyNum !=3){
			targetName = e.getName();
			try {
				//メッセージ送信の例ーSend enemy position to teammates
				broadcastMessage(e.getName());
			} catch (IOException ex) {
				out.println("Unable to send order: ");//デバッグの例
				ex.printStackTrace(out);
			}
		}

		else if(!isTeammate(e.getName()) && enemyNum == 0){
			targetName = e.getName();
			try {
				//メッセージ送信の例ーSend enemy position to teammates
				broadcastMessage(e.getName());
			} catch (IOException ex) {
				out.println("Unable to send order: ");//デバッグの例
				ex.printStackTrace(out);
			}
			//out.println("targetName is " + e.getName());
		}else{
			targetName = e.getName();
		}
			// Don't fire on teammates, 反重力のための味方の位置の情報収集（消した）
		if (isTeammate(e.getName())) {
		}

		else if(e.getName().indexOf("Walls") != -1 && enemyNum !=0){
		}

		else if(e.getName().equals(targetName)){
			//out.println(e.getName() + "targetName is " + targetName);
			//サンプルチームのコードは以下の通りCalculate enemy bearing
			double enemyBearing = this.getHeading() + e.getBearing();
			double enemyBearingRadians = this.getHeadingRadians()+e.getBearingRadians();
			// 自機の向き、敵までの距離より、敵の座標の推測			
			power=150/e.getDistance();
			power*=(power*4+2*(power-1))/(power*4);
			if(power>3){power=3;}
			//放射の準備
			double enemyAngPosn = e.getVelocity()*Math.sin(e.getHeadingRadians()-getHeadingRadians()-e.getBearingRadians())/Rules.getBulletSpeed((power+6)/3);
			changehead = (e.getHeadingRadians() - prevhead) / (getTime() - prevtime);
			setTurnRight(e.getBearing());
			setAhead(e.getDistance());
			setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
			doGun(e,power);
			setTurnGunRightRadians(Utils.normalRelativeAngle((enemyBearingRadians+Math.asin(enemyAngPosn))-getGunHeadingRadians()));
			fire(power);	//放射
			setTurnRightRadians(e.getBearingRadians() + Math.PI/2);
			setAhead(50);//放射直後の移動
			prevhead = e.getHeadingRadians();
			prevtime = getTime();
		}
		antiGravity();//戦車発見後も反重力を続ける
	}

	public void onHitByBullet(HitByBulletEvent e) {
		if (isTeammate(e.getName())) {
			out.println("turn2");
			setTurnRight(100);
		}
	}

	//見方からの攻撃の後の動き
	public void onBulletHit(BulletHitEvent e){
		if(isTeammate(e.getName())){
			setTurnRight(Math.atan2(getBattleFieldHeight(), getBattleFieldWidth())-Math.atan2(getY(),getX()));
			setAhead(300);
		}
  }

	public void onHitByRobot(HitRobotEvent e){

		if (isTeammate(e.getName())) {
			out.println("turn");
			setTurnRight(50);
		}
	}

	public void onHitWall(HitWallEvent e) {
	}

	//パルト担当分：　移動の1：見方からの反重力
	public void antiGravity(){
		double xForce = 0,yForce = 0,force,power;
		final double antikabe = 7500;
		xForce += antikabe/Math.pow(getBattleFieldWidth()-getX(),3);
		xForce -= antikabe/Math.pow(getX(),3);
		yForce += antikabe/Math.pow(getBattleFieldHeight()-getY(),3);
		yForce -= antikabe/Math.pow(getY(),3);

		double angle = getHeadingRadians()+Math.atan2(yForce, xForce)-Math.PI/2;
			if(angle > Math.PI/2){angle -= Math.PI; }
			else if(angle<-Math.PI/2){angle += Math.PI; }
			double distance=3000*(Math.sqrt(xForce*xForce+yForce*yForce));
			setTurnRightRadians(angle);
			setAhead(-1*distance);
	}

	public void onRobotDeath(RobotDeathEvent e){
		//out.println("least robot "+enemyNum);
		if(!isTeammate(e.getName()) && e.getName().indexOf("Walls") == -1){
			enemyNum--;
			//out.println("least robot "+enemyNum);
			if(e.getName().equals(targetName)){
				targetName = "none";
				out.println("target " + e.getName() +" is dead");
			}
		}
		antiGravity();
	}

	public void onMessageReceived(MessageEvent e){

		if (e.getMessage() instanceof Point) {

			String name = e.getSender();
			Point p = (Point) e.getMessage();
			//out.println(e.getSender()+" "+p.getX()+" "+p.getY());
			teamMateX = p.getX();
			teamMateY = p.getY();
		}else if(e.getMessage() instanceof String && targetName.equals("none")){
			//out.println("target is " + e.getMessage());
			targetName = (String)e.getMessage();
		}

		antiGravity();
	}
	
  public Point2D.Double guessPosition(long when, ScannedRobotEvent e) { //着弾時の敵の存在位置(予測)の座標を計算
    /**time is when our scan data was produced.  when is the time 
    that we think the bullet will reach the target.  diff is the 
    difference between the two **/
    double diff = when - getTime();
		double enemyBearing = getHeading() + e.getBearing();
    double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
	  double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
    double newX, newY;
    /**if there is a significant change in heading, use circular 
    path prediction**/
    if (Math.abs(changehead) > 0.00001) {  //角速度が大きければ円形予測
        double radius = e.getVelocity()/changehead;
        double tothead = diff * changehead;
        newY = enemyY + (Math.sin(e.getHeadingRadians() + tothead) * radius) - 
                      (Math.sin(e.getHeadingRadians()) * radius);
        newX = enemyX + (Math.cos(e.getHeadingRadians()) * radius) - 
                      (Math.cos(e.getHeadingRadians() + tothead) * radius);
    }
    /**if the change in heading is insignificant, use linear 
    path prediction**/
    else {  //角速度が小さければ線形予測
        newY = enemyY + Math.cos(e.getHeadingRadians()) * e.getVelocity() * diff;
        newX = enemyX + Math.sin(e.getHeadingRadians()) * e.getVelocity() * diff;
    }
    return new Point2D.Double(newX, newY);
    //y...敵の現在のy座標
    //x...敵の現在のx座標
    //newX...着弾時の敵の存在位置のx座標(予測)
    //newY...着弾時の敵の存在位置のy座標(予測)
    //radius...敵の円運動の半径
    //speed...敵の移動速度
    //changehead...敵の角速度
    //tothead...現在と着弾時の敵の向いてる方向の差(ラジアン)
    //heading...敵が現在向いている向き(ラジアン)
    //when...着弾時の時刻
    //time...現在の時刻
  }

  /**This function predicts the time of the intersection between the 
  bullet and the target based on a simple iteration.  It then moves 
  the gun to the correct angle to fire on the target.**/
  void doGun(ScannedRobotEvent e, double power) {  //砲台を予測した向きに向ける
    long time;
    long nextTime;
		double enemyBearing = getHeading() + e.getBearing();
    double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
	  double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
    Point2D.Double p;
    p = new Point2D.Double(enemyX, enemyY);
    for (int i = 0; i < 10; i++){
        nextTime = 
    (Math.round((getRange(getX(),getY(),p.x,p.y)/(20-(3*power)))));
        time = getTime() + nextTime;
        p = guessPosition(time,e);
    }
    /**Turn the gun to the correct angle**/
    double gunOffset = getGunHeadingRadians() - 
                  (Math.PI/2 - Math.atan2(p.y - getY(), p.x - getX()));
    setTurnGunLeftRadians(normaliseBearing(gunOffset));
  }
 
  double normaliseBearing(double ang) { //角度angを正規化する
    if (ang > Math.PI)
        ang -= 2*Math.PI;
    if (ang < -Math.PI)
        ang += 2*Math.PI;
    return ang;
  }
 
  public double getRange(double x1,double y1, double x2,double y2) {  //座標(x1,y1)と(x2,y2)の距離を求める
    double x = x2-x1;
    double y = y2-y1;
    double h = Math.sqrt( x*x + y*y );
    return h;   
  }

}

