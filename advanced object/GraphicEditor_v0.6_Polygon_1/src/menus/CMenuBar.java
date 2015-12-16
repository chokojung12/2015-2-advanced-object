package menus;
import java.util.EnumMap;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Libs.CConstans;
import Libs.CConstans.ECOLORMENUITEM;
import Libs.CConstans.EEDITMENUITEM;
import Libs.CConstans.EFILEMENUITEM;
import Libs.CConstans.EMENU;
import frames.CDrawingPanel;



public class CMenuBar extends JMenuBar {
	
	//association
	private Vector<CMenu> menus;
	private CDrawingPanel drawingPanel;

	//1st phase Initialization
	public CMenuBar() {
		//add menus
		menus = new Vector<CMenu>();
	//	EnumMap<Enum<K>, V>
		//for(EMENU eMenu : EMENU.values()){
			//CMenu menu = new CMenu(eMenu.getName(),eMenu.getItem());
			JMenu menu = new JMenu(EMENU.File.getName());
			for(EFILEMENUITEM eMenuItem : EFILEMENUITEM.values()){
				menu.add(new JMenuItem(eMenuItem.getName()));
			}
			this.add(menu);
			
			 menu = new JMenu(EMENU.Edit.getName());
			 for(EEDITMENUITEM eMenuItem : EEDITMENUITEM.values()){
					menu.add(new JMenuItem(eMenuItem.getName()));
			 }
			 this.add(menu);
			 
			 menu = new JMenu(EMENU.Color.getName());
			 for(ECOLORMENUITEM eMenuItem : ECOLORMENUITEM.values()){
					menu.add(new JMenuItem(eMenuItem.getName()));
			 }
			 this.add(menu);
			//menus.add(menu);
		//}
	}
	//2nd phase Initialization
	public void init(CDrawingPanel drawingPanel){
		this.drawingPanel=drawingPanel;
		for(CMenu menu : this.menus){
			menu.init(this.drawingPanel);
		}
	}
	
}
