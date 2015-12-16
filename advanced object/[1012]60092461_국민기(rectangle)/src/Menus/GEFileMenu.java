package Menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EFileMenuItems;
							// 상속, 확장, 특화 전부 다 같은뜻이다. <-> 일반화
public class GEFileMenu extends JMenu{
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems; // 나중에 사용하기위해서 벡터미리 정의
	
	public GEFileMenu() {
		super(); // 이런 상속인경우 항상 super를 불러준다.
		
		this.vectorMenuItems = new Vector<JMenuItem>();  // 이런식으로 this를 써서 분명하게 하는 습관
		
		for(EFileMenuItems fileMenuItem: EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(fileMenuItem.getFileMenuName());
			this.add(menuItem);

			this.vectorMenuItems.add(menuItem);
		}
	}
	
}
