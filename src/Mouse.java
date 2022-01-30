import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	private int mouseX = -1;
	private int mouseY = -1;
	private int mouseB = -1;
	private int scroll = 0;
	
	public int getX() {
		return this.mouseX;
	}
	
	public int getY() {
		return this.mouseY;
	}
	
	public ClickType getButton() {
		switch(this.mouseB) {
			case 1:
				return ClickType.LeftClick;
			case 2:
				return ClickType.ScrollClick;
			case 3:
				return ClickType.RightClick;
			case 4:
				return ClickType.ForwardPage;
			case 5:
				return ClickType.BackPage;
			default:
				return ClickType.Unknown;
		}
	}
	
	public void resetButton() {
		this.mouseB = -1;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		//Move with click
		this.mouseX = event.getX();
		this.mouseY = event.getY();
	}
	
	@Override
	public void mouseMoved(MouseEvent event) {
		//Move without click
		this.mouseX = event.getX();
		this.mouseY = event.getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//Press and release mouse
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		this.mouseB = event.getButton();
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}