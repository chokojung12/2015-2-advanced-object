package menus;

import javax.swing.JMenu;

import frames.GEPanel;

public class GEMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	protected GEPanel drawingPanel;
	
	public GEMenu() {
		super();
		this.drawingPanel = null;
	}
	public void init(GEPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
}
