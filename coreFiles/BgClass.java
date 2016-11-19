package g1;

public class BgClass {
	private int x, y;
	private int speed = -10;

	public BgClass(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void update(boolean yes,boolean right) {
		if (yes&&right) {
			x = x + speed;
		}
		if(x<-2160){
			x=2150;
		}
	}

	public void moveLeftBg() {
		speed = -10;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}