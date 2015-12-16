package frames;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import frames.CConstans.EMENU;


public class CMenuBar extends JMenuBar {
	private Vector<CMenu> menus;
	private CDrawingPanel drawingPanel;


	public CMenuBar() {
		
		menus = new Vector<CMenu>();
		for(EMENU eMenu : EMENU.values()){
			JMenu menu = new JMenu(eMenu.getName());
			//menus.add(menu);
			this.add(menu);
		}
		/*for(int i = 0; i < CConstans.MENUNAMES.length; i++){
			CMenu menu = new CMenu(CConstans.MENUNAMES[i],CConstans.MENUITEMNAMES[i]);
			menus.add(menu);
			this.add(menu);
			
		}*/

	}
	
	public void init(CDrawingPanel drawingPanel){
		this.drawingPanel=drawingPanel;
		for(int i =0;i<menus.size();i++){
			menus.get(i).init(drawingPanel);
		}
		
	}
	
}
