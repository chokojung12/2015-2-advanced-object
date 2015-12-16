package main;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.GEConstants.EFileMenus;

public class GEFileMenu extends JMenu{
	private Vector<JMenuItem> menuItems; //CMenu�� �ޱ� ���� Array ����
	
	public GEFileMenu(String menuName) {
		
		menuItems = new Vector<JMenuItem>();// Array �ʱ�ȭ
		for(String menuItemName: menuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName);
			menuItems.add(menuItem); // Array�� �߰�
			this.add(menuItem);
		}
		
	}
}
