package Menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Constant.GEConstant.EFileMenuItems;
							// ���, Ȯ��, Ưȭ ���� �� �������̴�. <-> �Ϲ�ȭ
public class GEFileMenu extends JMenu{
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> vectorMenuItems; // ���߿� ����ϱ����ؼ� ���͹̸� ����
	
	public GEFileMenu() {
		super(); // �̷� ����ΰ�� �׻� super�� �ҷ��ش�.
		
		this.vectorMenuItems = new Vector<JMenuItem>();  // �̷������� this�� �Ἥ �и��ϰ� �ϴ� ����
		
		for(EFileMenuItems fileMenuItem: EFileMenuItems.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(fileMenuItem.getFileMenuName());
			this.add(menuItem);

			this.vectorMenuItems.add(menuItem);
		}
	}
	
}
