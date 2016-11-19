package g1;

import java.applet.Applet;

import framework.Animation;
import g1.Boy;
import g1.Robot;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import g1.Bullets;
//step1-make a thread in applet starter method
//Step2-initialise graphics
//step3-initailise keyevents
//step4-initialize characters
//Images are painted in the order they appear. So if you want the character to be above the background,
//you need to put these two lines above the line that paints the character!
//getBullets and animations

public class Starter extends Applet implements Runnable, KeyListener {
	private Boy heli1, heli2, heli3;
	private static BgClass bg1;
	private static BgClass bg2;
	private Robot rob;
	private Graphics gps;
	private static boolean isAlive = true;
	public static Image Enemy;
	private Image pic, robfig, robfigD, robfigUp, fig, bgIm1, helib, helib2,
			helib3, helib4, wTile, gTile, topSurface, downSurface,
			rightSurface, leftSurface, currentSurface;
	private URL url;
	private boolean speed = false, inAir = false, right = false, shot = false,
			bow = false, nowScroll = true;
	public Animation hanim;
	public ArrayList<Tile> wTiles = new ArrayList<Tile>();
	public ArrayList<Tile> gTiles = new ArrayList<Tile>();

	ArrayList<Tile> lTiles = new ArrayList<Tile>();
	private Tile tw, tw2, tw3, tg, tg2;
	private int wT = 1310, height;

	public void init() {
		setSize(1350, 500);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame farma = (Frame) this.getParent().getParent();
		farma.setTitle("RObogame");
		try {
			url = getDocumentBase();
		} catch (Exception e) {
			System.out.println(e);
		}
		bgIm1 = getImage(url, "data/background.png");
		robfig = getImage(url, "data/rob1.png");
		robfigD = getImage(url, "data/rob2.png");
		robfigUp = getImage(url, "data/rob3.png");
		helib = getImage(url, "data/heli1.png");
		helib2 = getImage(url, "data/heli2.png");
		helib3 = getImage(url, "data/heli3.png");
		helib4 = getImage(url, "data/heli4.png");
		wTile = getImage(url, "data/water.png");
		gTile = getImage(url, "data/dirt.png");
		topSurface = getImage(url, "data/topSur.png");
		downSurface = getImage(url, "data/downSur.png");
		rightSurface = getImage(url, "data/rightSur.png");
		leftSurface = getImage(url, "data/leftSur.png");

		hanim = new Animation();
		hanim.addFrame(helib, 100);
		hanim.addFrame(helib2, 100);
		hanim.addFrame(helib3, 100);
		hanim.addFrame(helib4, 100);
		fig = robfig;

		int j = 440, b = 0;
		for (int i = 0; i < 34; i++) {
			b = i * 40;
			tw = new Tile(b, j);
			wTiles.add(tw);

		}
		b = 0;
		for (int i = 0; i < 34; i++) {
			b = i * 40;
			tg = new Tile(b, j + 40);
			gTiles.add(tg);
		}
	}

	public void paint(Graphics g) {
		if (isAlive) {
			g.drawImage(bgIm1, bg1.getX(), bg1.getY(), this);
			g.drawImage(bgIm1, bg2.getX(), bg2.getY(), this);
			for (int i = 0; i < wTiles.size(); i++) {
				tw2 = wTiles.get(i);
				int r = tw2.checkCollision(rob.body, rob.head, rob.lHand,
						rob.rHand);
				rob.setFig(r);
				g.drawImage(wTile, tw2.getPositionx(), tw2.getPositioY(), this);
			}
			for (int i = 0; i < gTiles.size(); i++) {
				tg2 = gTiles.get(i);
				int r = tg2.checkCollision(rob.body, rob.head, rob.lHand,
						rob.rHand);
				rob.setFig(r);
				g.drawImage(gTile, tg2.getPositionx(), tg2.getPositioY(), this);
			}

			for (int i = 0; i < lTiles.size(); i++) {
				Tile pT = lTiles.get(i);
				int t = pT.type;
				if (t == 2) {
					currentSurface = downSurface;
					pT.initializeRect();
					int r = pT.checkCollision(rob.body, rob.head, rob.lHand,
							rob.rHand);
					rob.setFig(r);
					g.drawImage(currentSurface, pT.getPositionx(),
							pT.getPositioY(), this);
				
				} else if (t == 5) {
					currentSurface = topSurface;
					pT.initializeRect();
					int r = pT.checkCollision(rob.body, rob.head, rob.lHand,
							rob.rHand);
					rob.setFig(r);
					g.drawImage(currentSurface, pT.getPositionx(),
							pT.getPositioY(), this);
					
				} else if (t == 8) {
					currentSurface = topSurface;
					pT.initializeRect();
					int r = pT.checkCollision(rob.body, rob.head, rob.lHand,
							rob.rHand);
					rob.setFig(r);
					g.drawImage(currentSurface, pT.getPositionx(),
							pT.getPositioY(), this);
				

				} else if (t == 4) {
					currentSurface = leftSurface;
					pT.initializeRect();
					int r = pT.checkCollision(rob.body, rob.head, rob.lHand,
							rob.rHand);
					rob.setFig(r);
					g.drawImage(currentSurface, pT.getPositionx(),
							pT.getPositioY(), this);
					
				} else if (t == 6) {
					currentSurface = rightSurface;
					pT.initializeRect();
					int r = pT.checkCollision(rob.body, rob.head, rob.lHand,
							rob.rHand);
					rob.setFig(r);
					g.drawImage(currentSurface, pT.getPositionx(),
							pT.getPositioY(), this);
					
				}

			}

			ArrayList bullet = rob.getBullets();
			if (shot && !bow) {
				for (int p = 1; p < bullet.size(); p++) {
					Bullets b = (Bullets) bullet.get(p);
					g.setColor(Color.yellow);
					// b.checkCollision();
					g.fillRect(b.getX(), b.getY(), 10, 5);
					
				}
			}

			g.drawImage(fig, rob.getPositionX(), rob.getPositionY(), this);
			g.drawImage(hanim.getImage(), heli1.getX(), heli1.getY(), this);
			g.drawImage(hanim.getImage(), heli3.getX(), heli3.getY(), this);
			g.drawImage(hanim.getImage(), heli2.getX(), heli2.getY(), this);

		} else {
			g.setColor(Color.WHITE);
			g.drawString("Dead", 600, 250);
		}

	}

	@Override
	public void update(Graphics g) {
		if (pic == null) {
			pic = createImage(this.getWidth(), this.getHeight());
			gps = pic.getGraphics();
		}

		gps.setColor(getBackground());
		gps.fillRect(0, 0, getWidth(), getHeight());
		gps.setColor(getForeground());
		paint(gps);

		g.drawImage(pic, 0, 0, this);

	}

	public void stop() {

	}

	public void destroy() {

	}

	public void start() {
		bg1 = new BgClass(0, 0);
		bg2 = new BgClass(2160, 0);
		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {

		}

		rob = new Robot();
		heli1 = new Boy(1000, 300);
		heli2 = new Boy(1200, 221);
		heli3 = new Boy(1555, 111);
		Thread t = new Thread(this);
		t.start();

	}

	private void loadMap(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		height = -40;
		while (isAlive()) {
			String line = br.readLine();
			if (line == null) {
				br.close();
				break;
			} else {
				getMap(line);
			}
		}

	}

	private void getMap(String line) {
		height = height + 40;
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			int t = Character.getNumericValue(c);
			Tile lT = new Tile(40 * i, height);
			lT.type = t;
			lTiles.add(lT);
		}
	}

	@Override
	public void run() {
		while (isAlive()) {
			repaint();
			bg1.update(speed, right);
			bg2.update(speed, right);
			rob.update();
			heli1.update(speed);
			heli2.update(speed);
			heli3.update(speed);
			rob.enemyCollision(heli1.eBody, heli1.hBody);
			rob.enemyCollision(heli2.eBody, heli2.hBody);
			rob.enemyCollision(heli3.eBody, heli3.hBody);
			ArrayList bullet = rob.getBullets();
			for (int i = 0; i < bullet.size(); i++) {
				Bullets b = (Bullets) bullet.get(i);
				if (shot && !bow) {
					heli1.hit(b.bRect);
					heli2.hit(b.bRect);
					heli3.hit(b.bRect);
					b.update();
				} else {
					bullet.remove(i);
				}
			}
			animate();
			if (tw.getPositionx() < 1310) {
				wT = wT + 36;
				tw3 = new Tile(wT, 440);
				wTiles.add(tw3);
			}
			for (int i = 0; i < wTiles.size(); i++) {
				Tile t3 = wTiles.get(i);
				t3.update(!right);

			}
			for (int i = 0; i < lTiles.size(); i++) {
				Tile lt = lTiles.get(i);
				if (rob.getPositionX() > 580) {
					lt.setSpeedX(-3);
					lt.update(right);
				}

			}

			repaint();

			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void animate() {
		hanim.update(50);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP: {
			if (!inAir) {
				fig = robfigUp;
				inAir = true;
				rob.jump(inAir);
			}
			break;
		}
		case KeyEvent.VK_DOWN: {
			fig = robfigD;
			rob.onGround(5);
			bow = true;
			break;
		}
		case KeyEvent.VK_RIGHT: {
			if (!speed) {
				rob.moveRight();
				speed = true;
				right = true;
			}
			break;
		}
		case KeyEvent.VK_LEFT: {
			if (!speed) {
				rob.moveLeft();
				speed = true;
				right = false;

			}
			break;
		}
		case KeyEvent.VK_SPACE: {
			shot = true;
			rob.shoot();
		}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP: {
			if (inAir) {
				inAir = false;
				fig = robfig;
				rob.onGround(5);
			}
			break;
		}
		case KeyEvent.VK_DOWN: {
			fig = robfig;
			bow = false;
			break;
		}
		case KeyEvent.VK_RIGHT: {
			if (speed) {
				speed = false;
				right = false;
				rob.halt();
				break;
			}
			break;
		}
		case KeyEvent.VK_LEFT: {
			if (speed) {
				speed = false;
				rob.halt();
				break;
			}
			break;
		}
		case KeyEvent.VK_SPACE: {
			// speed = false;
		}
		}
	}

	public void done() {

	}

	public void resume() {
		setAlive(true);
	}

	public static BgClass getBg1() {
		return bg1;
	}

	public static BgClass getBg2() {
		return bg2;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public static void setAlive(boolean see) {
		isAlive = see;
	}
}
