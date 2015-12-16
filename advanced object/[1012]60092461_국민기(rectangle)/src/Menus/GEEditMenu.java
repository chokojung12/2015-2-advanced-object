package Menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EEditMenuItems;

public class GEEditMenu extends JMenu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems; // ���߿� ����ϱ����ؼ� ���͹̸� ����
	
	public GEEditMenu(){
		super(); // �̷� ����ΰ�� �׻� super�� �ҷ��ش�.
		
		this.vectorMenuItems = new Vector<JMenuItem>();
		
		for(EEditMenuItems EditMenuItem: EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(EditMenuItem.getEditMenuName());
			this.add(menuItem);
			this.vectorMenuItems.add(menuItem);
		}

	}
}
