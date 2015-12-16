package Menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EColorMenuItems;

public class GEColorMenu extends JMenu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems; // ���߿� ����ϱ����ؼ� ���͹̸� ����
	
	public GEColorMenu() {
		super(); // �̷� ����ΰ�� �׻� super�� �ҷ��ش�.
		
		this.vectorMenuItems = new Vector<JMenuItem>();
		
		for(EColorMenuItems EditMenuItem: EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(EditMenuItem.getColorMenuName());
			this.add(menuItem);
			
			this.vectorMenuItems.add(menuItem);
		}
	}

}
