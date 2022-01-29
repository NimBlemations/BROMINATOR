import java.awt.Color;
import java.awt.Graphics;

public class Tetrahedron {
	private MyPolygon[] polygons;
	private Color color;
	
	private Tetrahedron(Color color, MyPolygon... polygons) {
		this.color = color;
		this.polygons = polygons;
	}
	
	private void render(Graphics g) {
		for(MyPolygons poly : this.polygons) {
			poly.render(g);
		}
	}
	
	private void sortPolygons() {
		
	}
	
	private void setPolygonsColor() {
		for(MyPolygons poly : this.polygons) {
			poly.setColor(this.color);
		}
	}
}