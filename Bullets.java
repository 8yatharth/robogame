package g1;

import java.awt.Rectangle;

public class Bullets {
	private int X, Y, speedB;
	public Rectangle bRect;

	public Bullets(int x, int y) {
		speedB = 10;
		this.X = x + 100;
		this.Y = y + 40;
		bRect = new Rectangle(X, Y, 10, 5);
	}

	public void update() {
		// if(shot){
		X = X + speedB;
		bRect.setBounds(X, Y, 10, 5);
		// }
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public void setX(int x) {
		X = x;
	}

	public void setY(int y) {
		Y = y;
	}


	}


