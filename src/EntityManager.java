import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
	private List<IEntity> entities;
	private int initialX, initialY;
	
	public EntityManager() {
		this.entities = new ArrayList<IEntity>();
	}
	
	public void init() {
		this.entities.add(BasicEntityBuilder.createDiamond(new Color(200, 40, 150), 100, 0, 0, 0));
	}
	
	public void update(Mouse mouse) {
		int x = mouse.getX();
		int y = mouse.getY();
		if(mouse.getButton() == ClickType.LeftClick) {
			int xDif = x - initialX;
			int yDif = y - initialY;
			
			this.rotate(true, 0, -yDif, -xDif);
		}
		else if(mouse.getButton() == ClickType.RightClick) {
			int xDif = x - initialX;
			
			this.rotate(true, -xDif, 0, 0);
		}
		
		if(mouse.isScrollingUp()) {
			PointConverter.zoomIn();
		}
		else if(mouse.isScrollingDown()) {
			PointConverter.zoomOut();
		}
		
		mouse.resetScroll();
		
		initialX = x;
		initialY = y;
	}
	
	public void render(Graphics g) {
		for(IEntity entity : this.entities) {
			entity.render(g);
		}
	}
	
	private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
		for(IEntity entity : this.entities) {
			entity.rotate(CW, xDegrees, yDegrees, zDegrees);
		}
	}
}