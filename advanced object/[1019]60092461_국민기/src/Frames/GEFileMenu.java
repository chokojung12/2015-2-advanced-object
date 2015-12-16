package Frames;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EFileMenuItems;

public class GEFileMenu extends JMenu{
	
	private Vector<JMenuItem> vectorMenuItems; // ���߿� ����ϱ����ؼ� ���͹̸� ����
	
	public GEFileMenu() {
		this.vectorMenuItems = new Vector<JMenuItem>();  // �̷������� this�� �Ἥ �и��ϰ� �ϴ� ����
		
		for(EFileMenuItems fileMenuItem: EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(fileMenuItem.getFileMenuName());
			this.add(menuItem);

			this.vectorMenuItems.add(menuItem);
		}
	}
	
}
