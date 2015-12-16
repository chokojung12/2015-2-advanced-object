package Frames;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EEditMenuItems;
import Constant.GEConstant.EFileMenuItems;

public class GEEditMenu extends JMenu{

	private Vector<JMenuItem> vectorMenuItems; // 나중에 사용하기위해서 벡터미리 정의
	
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
