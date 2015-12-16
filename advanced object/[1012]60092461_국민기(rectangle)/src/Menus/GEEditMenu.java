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
	private Vector<JMenuItem> vectorMenuItems; // 나중에 사용하기위해서 벡터미리 정의
	
	public GEEditMenu(){
		super(); // 이런 상속인경우 항상 super를 불러준다.
		
		this.vectorMenuItems = new Vector<JMenuItem>();
		
		for(EEditMenuItems EditMenuItem: EEditMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(EditMenuItem.getEditMenuName());
			this.add(menuItem);
			this.vectorMenuItems.add(menuItem);
		}

	}
}
