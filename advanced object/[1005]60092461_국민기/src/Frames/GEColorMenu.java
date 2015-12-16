package Frames;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EColorMenuItems;
import Constant.GEConstant.EEditMenuItems;

public class GEColorMenu extends JMenu{
	
	private Vector<JMenuItem> vectorMenuItems; // ���߿� ����ϱ����ؼ� ���͹̸� ����
	
	public GEColorMenu() {
		// TODO Auto-generated constructor stub
		this.vectorMenuItems = new Vector<JMenuItem>();
		
		for(EColorMenuItems EditMenuItem: EColorMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(EditMenuItem.getColorMenuName());
			this.add(menuItem);
			
			this.vectorMenuItems.add(menuItem);
		}
	}

}
