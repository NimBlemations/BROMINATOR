import java.awt.Color;
import java.awt.Graphics;

public class Tetrahedron {
	private MyPolygon[] polygons;
	private Color color;
	
	public Tetrahedron(Color color, MyPolygon... polygons) {
		this.color = color;
		this.polygons = polygons;
		this.setPolygonsColor();
	}
	
	public Tetrahedron(MyPolygon... polygons) {
		this.color = Color.WHITE;
		this.polygons = polygons;
	}
	
	public void render(Graphics g) {
		for(MyPolygon poly : this.polygons) {
			poly.render(g);
		}
	}
	
	private void sortPolygons() {
		
	}
	
	private void setPolygonsColor() {
		for(MyPolygon poly : this.polygons) {
			poly.setColor(this.color);
		}
	}
}