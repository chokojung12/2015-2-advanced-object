package Frames;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EColorMenuItems;
import Constant.GEConstant.EEditMenuItems;

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
