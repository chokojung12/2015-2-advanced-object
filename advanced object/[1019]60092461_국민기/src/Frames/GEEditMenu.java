package Frames;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EEditMenuItems;
import Constant.GEConstant.EFileMenuItems;

public class GEEditMenu extends JMenu{

	private Vector<JMenuItem> vectorMenuItems; // ���߿� ����ϱ����ؼ� ���͹̸� ����
	
	public GEEditMenu(){
		
		this.vectorMenuItems = new Vector<JMenuItem>();
		
		for(EEditMenuItems EditMenuItem: EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(EditMenuItem.getEditMenuName());
			this.add(menuItem);
			this.vectorMenuItems.add(menuItem);
		}

	}
}
