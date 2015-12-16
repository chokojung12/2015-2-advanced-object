package Frames;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EFileMenuItems;

public class GEFileMenu extends JMenu{
	
	private Vector<JMenuItem> vectorMenuItems; // 나중에 사용하기위해서 벡터미리 정의
	
	public GEFileMenu() {
		this.vectorMenuItems = new Vector<JMenuItem>();  // 이런식으로 this를 써서 분명하게 하는 습관
		
		for(EFileMenuItems fileMenuItem: EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(fileMenuItem.getFileMenuName());
			this.add(menuItem);

			this.vectorMenuItems.add(menuItem);
		}
	}
	
}
