package frames;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import menus.GEMenu;
import constants.GEConstant;

public class GEMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;

	public GEMenuBar() {
		super();
		for (GEConstant.EMenu eMenu: GEConstant.EMenu.values()) {
			JMenu menu = eMenu.newMenu();
			menu.setText(eMenu.getName());
			this.add(menu);
		}
	}
	
	public void init(GEPanel drawingPanel) {
		for (int i=0; i<this.getMenuCount(); i++) {
			GEMenu menu = (GEMenu) this.getMenu(i);
			menu.init(drawingPanel);
		}			
	}
}
