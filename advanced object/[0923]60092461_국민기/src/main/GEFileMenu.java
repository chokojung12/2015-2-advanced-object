package main;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEFileMenu extends JMenu{
	private Vector<JMenuItem> menuItems; //CMenu에 달기 위해 Array 생성
	
	public GEFileMenu(String name, String[] menuItemNames) {
		super(name);
		menuItems = new Vector<JMenuItem>();// Array 초기화
		for(String menuItemName: menuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName);
			menuItems.add(menuItem); // Array에 추가
			this.add(menuItem);
		}
		
	}
}
