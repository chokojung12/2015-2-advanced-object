package menus;
import java.util.EnumMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import constants.CConstans;
import constants.CConstans.ECOLORMENUITEM;
import constants.CConstans.EEDITMENUITEM;
import constants.CConstans.EFILEMENUITEM;
import constants.CConstans.EMENU;
import frames.CDrawingPanel;



public class CMenuBar extends JMenuBar {
	
	//association
	private Vector<CMenu> menus;
	private CDrawingPanel drawingPanel;

	//1st phase Initialization
	public CMenuBar() {
		//add menus
		menus = new Vector<CMenu>();
		EnumMap<EMENU,Enum[]> menuItems = putitem();
		for(Map.Entry<EMENU,Enum[]> items : menuItems.entrySet()){
			CMenu menu = new CMenu(items.getKey().getName(), items.getValue());
			menus.add(menu);
			this.add(menu);
		}
	}
	//2nd phase Initialization
	public void init(CDrawingPanel drawingPanel){
		this.drawingPanel=drawingPanel;
		for(CMenu menu : this.menus){
			menu.init(this.drawingPanel);
		}
	}
	private EnumMap<EMENU,Enum[]> putitem(){
		EnumMap<EMENU,Enum[]> menuItems = new EnumMap<>(EMENU.class);
		for(EMENU eMenu : EMENU.values()){
			menuItems.put(eMenu, eMenu.getItems());
		}		
		return menuItems;
	}
	
}
