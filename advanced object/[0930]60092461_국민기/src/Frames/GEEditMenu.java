package Frames;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EEditMenuItems;
import Constant.GEConstant.EFileMenuItems;

public class GEEditMenu extends JMenu{
	public GEEditMenu(){
		for(EEditMenuItems EditMenuItem: EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(EditMenuItem.getEditMenuName());
			this.add(menuItem);
		}
	}
}
