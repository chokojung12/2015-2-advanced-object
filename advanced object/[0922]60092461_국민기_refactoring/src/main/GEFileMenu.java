package main;

import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEFileMenu extends JMenu{
	private Vector<JMenuItem> menuItems; //Menu에 달기 위해 Array 생성
	
	public GEFileMenu(String fileMenuName, String[] fileMenuItemNames) {
		super(fileMenuName);
		menuItems = new Vector<JMenuItem>();// Array 초기화
		for(String menuItemName: fileMenuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName);
			menuItems.add(menuItem); // Array에 추가
			this.add(menuItem);
		}
	}
}
