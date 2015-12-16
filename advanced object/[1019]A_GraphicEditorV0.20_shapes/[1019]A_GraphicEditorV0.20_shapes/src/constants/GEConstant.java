package constants;
import javax.swing.JMenu;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;


public class GEConstant {
	
	public static final int FRAME_W = 400;
	public static final int FRAME_H = 600;
	
	public static enum EDrawingState {idle, drawing};
	
	public static enum EButtons {
		
		rectangle("rectangle.gif", "rectangleSLT.gif"),
		ellipse("ellipse.gif", "ellipseSLT.gif"),
		line("line.gif", "lineSLT.gif"),
		heart("heart.gif", "heartSLT.gif"),
		polygon("polygon.gif", "polygonSLT.gif"),
		text("text.gif", "textSLT.gif");
		
		private String buttonImage;
		private String selectedButtonImage;
		
		private EButtons(String buttonImage, String selectedButtonImage){
			this.buttonImage = buttonImage;
			this.selectedButtonImage = selectedButtonImage;
		}
		public String getButtonImage() {return buttonImage;}
		public String getSelectedButtonImage() {return selectedButtonImage;}
	}
	
	public static enum EMenu {
		file("File", new GEFileMenu()),
		edit("Edit", new GEEditMenu()),
		color("Color", new GEColorMenu());
		
		private String name;
		private JMenu menu;
		
		private EMenu(String name, JMenu menu) {
			this.name = name;
			this.menu = menu;
		}

		public String getName() {return name;}
		public JMenu newMenu() {return menu;}
			
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

}
