import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
	private List<IEntity> entities;
	private int initialX, initialY;
	private double moveSpeed = 10;
	private MyVector lightVector = MyVector.normalize(new MyVector(1, 1, 1));
	private Mouse mouse;
	private Keyboard keyboard;
	private Camera camera;
	
	public EntityManager() {
		this.entities = new ArrayList<IEntity>();
		this.camera = new Camera(0, 0, 0);
	}
	
	public void init(UserInput userInput) {
		this.mouse = userInput.mouse;
		this.keyboard = userInput.keyboard;
		
		this.entities.add(ComplexEntityBuilder.createRubiksCube(100, 0, 0, 0));
		this.setLighting();
	}
	
	public void update() {
		int x = this.mouse.getX();
		int y = this.mouse.getY();
		if(this.mouse.getButton() == ClickType.LeftClick) {
			int xDif = x - initialX;
			int yDif = y - initialY;
			
			this.rotate(true, 0, -yDif, -xDif);
		}
		else if(this.mouse.getButton() == ClickType.RightClick) {
			int xDif = x - initialX;
			
			this.rotate(true, -xDif, 0, 0);
		}
		
		if(this.mouse.isScrollingUp()) {
			PointConverter.zoomIn();
		}
		else if(this.mouse.isScrollingDown()) {
			PointConverter.zoomOut();
		}
		
		if(this.keyboard.getLeft()) {
			this.camera.translate(0, -moveSpeed, 0);
		}
		
		this.mouse.resetScroll();
		this.keyboard.update();
		
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
			entity.rotate(CW, xDegrees, yDegrees, zDegrees, this.lightVector);
		}
	}
	
	private void setLighting() {
		for(IEntity entity : this.entities) {
			entity.setLighting(this.lightVector);
		}
	}
}