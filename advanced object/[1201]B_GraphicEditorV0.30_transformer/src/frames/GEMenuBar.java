package frames;
import javax.swing.JMenuBar;
import menus.GEMenu;
import constants.GEConstant;


public class GEMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;

	// menu 붙이기
	public GEMenuBar() {
		for(GEConstant.EMenus eMenu: GEConstant.EMenus.values()) {
			GEMenu menu = eMenu.getMenu();
			menu.setText(eMenu.getMenuName());
			this.add(menu);
		}
	}

	// menu initialize함
	public void init(GEPanel drawingPanel) {
		for (int i=0; i<this.getMenuCount(); i++) {
			GEMenu menu = (GEMenu) this.getMenu(i);
			menu.init(drawingPanel);
		}
		
	}
}
