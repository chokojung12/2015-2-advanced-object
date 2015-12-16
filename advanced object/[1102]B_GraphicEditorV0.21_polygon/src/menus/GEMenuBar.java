package menus;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstant;


public class GEMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;

	public GEMenuBar() {
		for(GEConstant.EMenus eMenu: GEConstant.EMenus.values()) {
			JMenu menu = eMenu.getMenu();
			menu.setText(eMenu.getMenuName());
			this.add(menu);
		}
	}
}
