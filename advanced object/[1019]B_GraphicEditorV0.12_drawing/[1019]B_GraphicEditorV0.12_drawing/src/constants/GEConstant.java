package constants;
import javax.swing.JMenu;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;

public class GEConstant {
	
	public final static int FRAME_W = 400;
	public final static int FRAME_H = 600;
	
	
	public static enum EDrawingState {idle, drawing};

	public static enum EMenus {
		file("File", new GEFileMenu()),
		edit("Edit", new GEEditMenu()),
		color("Color", new GEColorMenu());
		
		private String menuName;
		private JMenu menu;
		private EMenus(String menuName, JMenu menu) {
			this.menuName = menuName;
			this.menu = menu;
		}
		public String getMenuName() {return menuName;}
		public JMenu getMenu() {return menu;}
	}
	
	public enum EFileMenuItem {
		newFile("New"),
		open("Open"),
		save("Save");		
		private String name;		
		private EFileMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}
	
	public enum EEditMenuItem {
		newFile("New"),
		open("Open"),
		save("Save");		
		private String name;		
		private EEditMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}
	
	public enum EColorMenuItem {
		newFile("New"),
		open("Open"),
		save("Save");
		
		private String name;		
		private EColorMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}

	public static enum EButtons {
		rectangle("rsc/rectangle.gif", "rsc/rectangleSLT.gif"), 
		ellipse("rsc/ellipse.gif", "rsc/ellipseSLT.gif"),
		line("rsc/line.gif", "rsc/lineSLT.gif"),
		heart("rsc/heart.gif", "rsc/heartSLT.gif"),
		polygon("rsc/polygon.gif", "rsc/polygonSLT.gif"),
		text("rsc/text.gif", "rsc/textSLT.gif");
		
		private String icon;
		private String iconSLT;
		private EButtons(String icon, String iconSLT) {
			this.icon = icon;
			this.iconSLT = iconSLT;
		}
		public String getIcon() {return icon;}
		public String getIconSLT() {return iconSLT;}
	}	
}
