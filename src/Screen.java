package src;

import java.util.Random;

public class Screen extends Render {
	private Render test;
	
	public Screen(int width, int height) {
		super(width, height);
		Random random = new Random();
		test = new Render(256, 256);
		for(int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt() * (random.nextInt(5) / 4);
		}
	}
	
	public void render() {
		for(int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}
		int anim = (int) (Math.sin(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 100);
		draw(test, anim, 0);
	}
}