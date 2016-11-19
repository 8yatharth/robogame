package g1;

import java.awt.Rectangle;
import java.util.Random;

public class Enemy {
 protected int health,positionX,positionY,speedX=1,speedY=10,exp,n;
 public Rectangle eBody=new Rectangle(positionX+20,positionY+40,50,49);
 public Rectangle hBody = new Rectangle(positionX+33,positionY+10,30,240);
 private Random ran =new Random();
 public void update(boolean speed){
	 eBody.setBounds(positionX+20, positionY+40, 50, 48);
	 hBody.setBounds(positionX+33, positionY+10, 30, 40);
	 if(!speed){
		 positionX=positionX-speedX;
	 }
	 exp=ran.nextInt(10000)/1000;
	 n=(int) Math.pow(exp, -1);
	 if(positionY>400||positionY<100){
		 if(positionY>380){
			 positionY=380;
		 }
		 if(positionY<100){
			 positionY=100;
		 }
	 }
	 else{
		 positionY=positionY+(n*speedY);
	 }
 }
}
