import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;

public class Texture {
	public static Render floor = loadBitmap("res/textures/StoneDing.bmp");
	
	public static Render loadBitmap(String fileName) {
		try {
			BufferedImage image = ImageIO.read(new File(fileName));
			int width = image.getWidth();
			int height = image.getHeight();
			Render result = new Render(width, height);
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
		}catch(Exception e) {
			System.out.println("Image failed to load " + "'" + fileName + "'!");
			throw new RuntimeException(e);
		}finally {
			System.out.println("Image loaded " + "'" + fileName + "'!");
		}
	}
}