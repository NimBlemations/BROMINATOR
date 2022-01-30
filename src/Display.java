//Big thanks to MeanRollerCoding's "[Java] 3D Rendering" tutorial!

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Display extends Canvas implements Runnable {
	private Thread thread;
	private JFrame frame;
	private static String title = "BROMINATOR";
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static boolean running = false;
	
	private Tetrahedron tetra;
	
	private Mouse mouse;
	
	public Display() {
		this.frame = new JFrame();
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
		
		this.mouse = new Mouse();
		
		this.addMouseListener(this.mouse);
		this.addMouseMotionListener(this.mouse);
		this.addMouseWheelListener(this.mouse);
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		display.frame.setTitle(title);
		display.frame.add(display);
		display.frame.pack();
		display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.frame.setLocationRelativeTo(null);
		display.frame.setResizable(false);
		display.frame.setVisible(true);
		
		display.start();
	}
	
	public synchronized void start() {
		running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			this.thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		int frames = 0;
		
		init();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
				render();
				frames++;
			}
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.frame.setTitle(title + " | " + frames + " fps");
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void init() {
		int s = 100;
		MyPoint p1 =  new MyPoint(s/2, -s/2, -s/2);
		MyPoint p2 =  new MyPoint(s/2, s/2, -s/2);
		MyPoint p3 =  new MyPoint(s/2, s/2, s/2);
		MyPoint p4 =  new MyPoint(s/2, -s/2, s/2);
		MyPoint p5 =  new MyPoint(-s/2, -s/2, -s/2);
		MyPoint p6 =  new MyPoint(-s/2, s/2, -s/2);
		MyPoint p7 =  new MyPoint(-s/2, s/2, s/2);
		MyPoint p8 =  new MyPoint(-s/2, -s/2, s/2);
		this.tetra = new Tetrahedron(
			new MyPolygon(Color.RED, p1, p2, p3, p4),
			new MyPolygon(Color.BLUE, p5, p6, p7, p8),
			new MyPolygon(Color.WHITE, p1, p2, p6, p5),
			new MyPolygon(Color.YELLOW, p1, p5, p8, p4),
			new MyPolygon(Color.GREEN, p2, p6, p7, p3),
			new MyPolygon(Color.ORANGE, p4, p3, p7, p8));
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		tetra.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void update() {
		this.tetra.rotate(true, 0, 1, 1);
		//System.out.println(this.mouse.getX() + ", " + this.mouse.getY());
		System.out.println(this.mouse.getButton());
		
		this.mouse.resetButton();
	}
}