package src;

public class Render3D extends Render {
	public Render3D(int width, int height) {
		super(width, height);
	}
	
	public void floor(Game game) {
		
		double floorPosition = 8.0;
		double ceilingPosition = 8.0;
		double forward = game.time / 5.0;
		double right = game.time / 5.0;
		
		double rotation = game.time / 100.0;
		double cosine = Math.cos(rotation);
		double sine = Math.sin(rotation);
		
		for(int y = 0; y < height; y++) {
			double ceiling = (y + -height / 2.0) / height;
			
			double z = floorPosition / ceiling;
			
			if(ceiling < 0) {
				z = ceilingPosition / -ceiling;
			}
			
			for(int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) / height;
				depth *= z;
				double xx = depth * cosine + z * sine + right; //Right
				double yy = z * cosine - depth * sine + forward; //Forward
				int xPix = (int) (xx);
				int yPix = (int) (yy);
				
				pixels[x + y * width] = ((xPix & 15) * 16) | ((yPix & 15) * 16) << 8;
			}
		}
	}
}