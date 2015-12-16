package Frames;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Constant.GEConstant.EMenus;

public class GEMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;

	public GEMenuBar() {
		super();
		
		for(EMenus eMenu : EMenus.values()){
			JMenu menu = eMenu.getMenu();
			menu.setText(eMenu.getMenuName());
			this.add(menu);	
		}
	}
	
}
