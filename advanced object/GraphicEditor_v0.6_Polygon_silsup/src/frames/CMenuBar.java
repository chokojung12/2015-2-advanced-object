package frames;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import frames.CConstants.EEditMenuItem;
import frames.CConstants.EFileMenuItem;
import frames.CConstants.EMenu;

public class CMenuBar extends JMenuBar {
	// associations
	CDrawingPanel drawingPanel;
	
	public CMenuBar() {
		// add menus
		JMenu menu;
		menu = new JMenu(EMenu.File.getName());
		for(EFileMenuItem eMenuItem: EFileMenuItem.values()) {
			menu.add(new JMenuItem(eMenuItem.getName()));
		}
		this.add(menu);
		
		menu = new JMenu(EMenu.Edit.getName());
		for(EEditMenuItem eMenuItem: EEditMenuItem.values()) {
			menu.add(new JMenuItem(eMenuItem.getName()));
		}
		this.add(menu);
	}
	
	public void init(CDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
}
