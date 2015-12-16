package frames;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstant;

public class GEMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;

	public GEMenuBar() {
		super();
		
		JMenu menu;
		for (GEConstant.EMenu eMenu: GEConstant.EMenu.values()) {
			menu = eMenu.newMenu();
			menu.setText(eMenu.getName());
			this.add(menu);
		}
	}
}
