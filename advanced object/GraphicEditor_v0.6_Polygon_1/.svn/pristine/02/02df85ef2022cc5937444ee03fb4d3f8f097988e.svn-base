import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class CMenuBar extends JMenuBar {
	private Vector<CMenu> menus;
//	private CFileMenu fileMenu;
	//private CMenu cMenu;
	//private CMenu colorMenu;

	public CMenuBar() {
		
		menus = new Vector<CMenu>();
		
		//fileMenu = new CFileMenu("File");
		//menus.add(fileMenu);
		//this.add(fileMenu);
		for(int i = 0; i < CConstans.menuNames.length; i++){
			CMenu menu = new CMenu(CConstans.menuNames[i],CConstans.menuItemNames[i]);
			menus.add(menu);
			this.add(menu);
			
		}

	}
	
	public void init(CDrawingPanel drawingPanel){
		//fileMenu.init(drawingPanel);
		for(int i =0;i<menus.size();i++){
			menus.get(i).init(drawingPanel);
		}
		
	}
	
}
