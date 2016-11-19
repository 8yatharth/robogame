package g1;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import g1.Starter;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class Robot implements Runnable, KeyListener {
	private BgClass bg;
	private int bWidth = 52, bLenght = 92, hWidth = 26, hHeight = 18,
			hhead = 25;
	private int positionX = 100;
	private int positionY = 312;
	private int speedX = 0;
	private int speedY = 0;
	private boolean jump = false;
	public Rectangle body = new Rectangle(positionX + 50, positionY, bWidth,
			bLenght);
	public Rectangle head = new Rectangle(positionX + 20, positionY, hhead,
			hhead);
	public Rectangle rHand = new Rectangle(positionX + 50, positionY, hWidth,
			hHeight);
	public Rectangle lHand = new Rectangle(positionX + 50, positionY, hWidth,
			hHeight);
	public ArrayList<Bullets> bullet = new ArrayList<Bullets>();
	private boolean yCollision, xCollision;

	public void moveRight() {
		speedX = 10;
	}

	public void moveLeft() {
		speedX = -10;
	}

	public void jump(boolean jump) {
		speedY = -5;
	}

	public void onGround(int x) {
		speedY = x;
	}

	public void halt() {
		speedX = 0;
	}

	public void update() {
		body.setBounds(positionX + 35, positionY + 33, bWidth, bLenght);
		head.setBounds(positionX + 48, positionY, hhead, hhead);
		lHand.setBounds(positionX + 1, positionY + 33, hWidth, hHeight);
		rHand.setBounds(positionX + 95, positionY + 33, hWidth, hHeight);
		if (positionX > 0 && positionX < 1200) {
			positionX = positionX + speedX;
		}
		if (positionX <= 0 || positionX >= 600) {
			if (positionX <= 0) {
				positionX = 10;

			}
			if (positionX >= 600) {
				positionX = 590;

			}
		}
		if (positionY > 312) {
			positionY = 312;
			speedY = 0;
		} else if (positionY < 100) {
			positionY = 100;
		} else {
			positionY = positionY + speedY;
		}

	}

	public void shoot() {
		Bullets b = new Bullets(getPositionX(), getPositionY());
		bullet.add(b);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public ArrayList getBullets() {
		return bullet;
	}

	public void setFig(int r) {
		switch (r) {
		case 1: {
			this.positionY=this.positionY-5;
		}
		case 2: {
			this.positionY=this.positionY+5;
		}
		case 3: {
			this.positionX=this.positionX+10;
		}
		case 4: {
			this.positionX=this.positionX-10;
		}
		}
	}

	public void enemyCollision(Rectangle eBody, Rectangle hBody) {
		if(this.lHand.intersects(hBody)){
			Starter.setAlive(false);
		}
		if(this.rHand.intersects(hBody)){
			Starter.setAlive(false);
		}
		if(this.head.intersects(hBody)){
			Starter.setAlive(false);
		}
		if(this.body.intersects(hBody)){
			Starter.setAlive(false);
		}
		if(this.lHand.intersects(eBody)){
			Starter.setAlive(false);
		}
		if(this.rHand.intersects(eBody)){
			Starter.setAlive(false);
		}
		if(this.head.intersects(eBody)){
			Starter.setAlive(false);
		}
		if(this.body.intersects(eBody)){
			Starter.setAlive(false);
		}
		
}
	}


