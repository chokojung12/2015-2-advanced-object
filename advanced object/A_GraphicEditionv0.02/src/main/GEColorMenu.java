package main;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.GEConstant.EColorMenuItems;
import main.GEConstant.EEditMenuItems;

public class GEColorMenu extends JMenu{

	public GEColorMenu() {
		// TODO Auto-generated constructor stub
		for(EColorMenuItems EditMenuItem: EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(EditMenuItem.getColorMenuName());
			this.add(menuItem);
		}
	}

}
