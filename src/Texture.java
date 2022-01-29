import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	public static Render floor = loadBitmap("res/textures/StoneDing.bmp", false);
	
	public static Render loadBitmap(String fileName, boolean inherit) {
		try {
			BufferedImage image = ImageIO.read(new File(fileName));
			if(inherit == true) {
				image = ImageIO.read(Texture.class.getResource(fileName));
			}
			int width = image.getWidth();
			int height = image.getHeight();
			Render result = new Render(width, height);
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
		}catch(IOException e) {
			if(inherit = true) {
				System.out.println("Image failed to load " + "'" + fileName + "'!");
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			else if(inherit == false) {
				System.out.println("Couldn't load image! Loading inherited...");
				loadBitmap(fileName, true);
			}
			
		}finally {
			System.out.println("Image loaded " + "'" + fileName + "'!");
		}
		return null;
	}
}