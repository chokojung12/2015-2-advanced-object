package frames;

import java.util.Vector;

import javax.swing.JMenu;

import entity.GEModelShape;
import shapes.GEShape;

public class GEMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	protected GEPanel drawingPanel;
	protected Vector<GEShape> state = new Vector<GEShape>();
	public GEMenu() {
		super();
		this.drawingPanel = null;
		
	}
	public void init(GEPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		
	}
}
