package menus;
import java.awt.Component;

import javax.swing.JMenuBar;

import constants.CConstans.EMENU;
import frames.CDrawingPanel;
import frames.CFrame;



public class CMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	//1st phase Initialization
	public CMenuBar() {		
		for(EMENU eMenu:EMENU.values()){
			CMenu menu = eMenu.newMenu();
			menu.setText(eMenu.getName());
			this.add(menu);			
		}
	}
	//2nd phase Initialization
	public void init(CDrawingPanel drawingPanel, CFrame frame){
		for(Component component: this.getComponents()){
			CMenu menu = (CMenu) component;
			menu.init(drawingPanel, frame);
		}
	}	
}
