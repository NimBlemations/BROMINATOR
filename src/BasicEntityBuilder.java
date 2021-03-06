import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

public class BasicEntityBuilder {
	public static IEntity createCube(double size, double centerX, double centerY, double centerZ) {
		MyPoint p1 =  new MyPoint(centerX + size/2, centerY + -size/2, centerZ + -size/2);
		MyPoint p2 =  new MyPoint(centerX + size/2, centerY + size/2, centerZ + -size/2);
		MyPoint p3 =  new MyPoint(centerX + size/2, centerY + size/2, centerZ + size/2);
		MyPoint p4 =  new MyPoint(centerX + size/2, centerY + -size/2, centerZ + size/2);
		MyPoint p5 =  new MyPoint(centerX + -size/2, centerY + -size/2, centerZ + -size/2);
		MyPoint p6 =  new MyPoint(centerX + -size/2, centerY + size/2, centerZ + -size/2);
		MyPoint p7 =  new MyPoint(centerX + -size/2, centerY + size/2, centerZ + size/2);
		MyPoint p8 =  new MyPoint(centerX + -size/2, centerY + -size/2, centerZ + size/2);
		
		Tetrahedron tetra = new Tetrahedron(
			new MyPolygon(Color.RED, p1, p2, p3, p4),
			new MyPolygon(Color.BLUE, p5, p6, p7, p8),
			new MyPolygon(Color.WHITE, p1, p2, p6, p5),
			new MyPolygon(Color.YELLOW, p1, p5, p8, p4),
			new MyPolygon(Color.GREEN, p2, p6, p7, p3),
			new MyPolygon(Color.ORANGE, p4, p3, p7, p8));
		
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		tetras.add(tetra);
		
		return new Entity(tetras);
	}
	
	public static IEntity createDiamond(Color color, double size, double centerX, double centerY, double centerZ) {
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		
		int edges = 20;
		double inFactor = 0.8;
		MyPoint bottom = new MyPoint(centerX, centerY, centerZ - size / 2);
		MyPoint[] outerPoints = new MyPoint[edges];
		MyPoint[] innerPoints = new MyPoint[edges];
		for(int i = 0; i < edges; i++) {
			double theta = 2 * Math.PI / edges * i;
			double xPos = -Math.sin(theta) * size / 2;
			double yPos = -Math.cos(theta) * size / 2;
			double zPos = size / 2;
			outerPoints[i] = new MyPoint(centerX + xPos, centerY + yPos, centerZ + zPos * inFactor);
			innerPoints[i] = new MyPoint(centerX + xPos * inFactor, centerY + yPos * inFactor, centerZ + zPos);
		}
		
		MyPolygon polygons[] = new MyPolygon[2 * edges + 1];
		for(int i = 0; i < edges; i++) {
			polygons[i] = new MyPolygon(outerPoints[i], bottom, outerPoints[(i + 1) % edges]);
		}
		
		for(int i = 0; i < edges; i++) {
			polygons[i + edges] = new MyPolygon(outerPoints[i], outerPoints[(i + 1) % edges], innerPoints[(i + 1) % edges], innerPoints[i]);
		}
		
		polygons[edges * 2] = new MyPolygon(innerPoints);
		
		Tetrahedron tetra = new Tetrahedron(color, true, polygons);
		tetras.add(tetra);
		
		return new Entity(tetras);
	}
}