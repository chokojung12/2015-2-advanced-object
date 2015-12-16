package main;

import java.util.Vector;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEFileMenu extends JMenu{
	private Vector<JMenuItem> menuItems; //Menu�� �ޱ� ���� Array ����
	
	public GEFileMenu(String fileMenuName, String[] fileMenuItemNames) {
		super(fileMenuName);
		menuItems = new Vector<JMenuItem>();// Array �ʱ�ȭ
		for(String menuItemName: fileMenuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName);
			menuItems.add(menuItem); // Array�� �߰�
			this.add(menuItem);
		}
	}
}
