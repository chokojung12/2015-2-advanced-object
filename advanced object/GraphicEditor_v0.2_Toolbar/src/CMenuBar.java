import java.util.Vector;

import javax.swing.JMenuBar;


public class CMenuBar extends JMenuBar {
	private Vector<CMenu> menus;

	public CMenuBar() {
		
		menus = new Vector<CMenu>();
		for(int i = 0; i < CConstans.menuNames.length; i++){
			CMenu menu = new CMenu(CConstans.menuNames[i],CConstans.menuItemNames[i]);
			menus.add(menu);
			this.add(menu);
			
		}

	}
	
	
}
