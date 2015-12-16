package main;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.GEConstant.EFileMenuItems;

public class GEFileMenu extends JMenu{
	
	
	public GEFileMenu() {
		for(EFileMenuItems fileMenuItem: EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(fileMenuItem.getFileMenuName());
			this.add(menuItem);
		}
	}
}
