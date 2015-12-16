import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class CMenu extends JMenu {
	private Vector<JMenuItem> menuItems; //CMenu�� �ޱ� ���� Array ����
	
	
	
	public CMenu(String name, String[] menuItemNames) {
		super(name);
		menuItems = new Vector<JMenuItem>();// Array �ʱ�ȭ
		for(String menuItemName: menuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName);
			menuItems.add(menuItem); // Array�� �߰�
			this.add(menuItem);
		}
		
	}
	
}