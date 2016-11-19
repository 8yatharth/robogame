package g1;

import java.awt.Image;
import java.awt.Rectangle;


public class Boy extends Enemy {
	public Boy(int x, int y) {
	
		super.positionX=x;
		super.positionY=y;
	}

	public int getX() {
		return super.positionX;
	}

	public int getY() {
		return super.positionY;
	}

	public void hit(Rectangle bRect) {
if(bRect.intersects(hBody)){
	this.speedX=0;
	this.speedY=0;
}
else if(bRect.intersects(eBody)){
	this.speedX=0;
	this.speedY=0;
}
	}
}
