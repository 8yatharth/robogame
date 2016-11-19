package g1;

import java.awt.Rectangle;

public class Tile {
	private int positionx, positionY, speedX;
	public int type;
	private boolean inialised = false;
	public Rectangle tRect = new Rectangle(positionx, positionY, 40, 40);

	public Tile(int x, int y) {
		this.positionx = x;
		this.positionY = y;
		speedX = -2;
	}

	public int getPositionx() {
		return positionx;
	}

	public int getPositioY() {
		return positionY;
	}

	public void update(boolean right) {
		if (right) {
			positionx = positionx + speedX;
		}
		if (inialised) {
			tRect.setBounds(positionx, positionY, 40, 40);
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void initializeRect() {
		tRect = new Rectangle(positionx, positionY, 40, 40);
		inialised = true;
	}

	public int checkCollision(Rectangle body, Rectangle head, Rectangle lHand,
			Rectangle rHand) {
		if (body.intersects(tRect)) {
			return 1;
		} else if (head.intersects(tRect)) {
			return 2;
		} else if (lHand.intersects(tRect)) {
			return 3;
		} else if (rHand.intersects(tRect)) {
			return 4;
		} else
			return 0;
	}
}
