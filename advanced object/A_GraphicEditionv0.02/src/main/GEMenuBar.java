package main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.GEConstant.EMenus;

public class GEMenuBar extends JMenuBar {
	
	public GEMenuBar() {
		for(EMenus eMenu : EMenus.values()){
			JMenu menu = eMenu.getMenu();
			menu.setText(eMenu.getMenuName());
			this.add(menu);	
		}
		/*JMenu a = new JMenu("hihihi");
		this.add(a);
		a.add(new JMenuItem("d"));*/
	}
	
}
