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
	
	public Display() {
		this.frame = new JFrame();
		
		Dimension size = new Dimension(WIDTH, HEIGHT);
		this.setPreferredSize(size);
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
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				this.frame.setTitle(title + " | " + frames + " fps");
				frames = 0;
			}
		}
		
		stop();
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
		
		MyPolygon poly = new MyPolygon(new MyPoint(0, 100, 0), new MyPoint(0, 0, 0), new MyPoint(0, 50, 50));
		poly.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void update() {
		
	}
}