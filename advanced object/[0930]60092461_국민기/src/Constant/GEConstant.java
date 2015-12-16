package Constant;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Frames.GEColorMenu;
import Frames.GEEditMenu;
import Frames.GEFileMenu;



public class GEConstant {
	public static final int FRAME_WIDTH = 400;
	public static final int FRAME_HEIGHT = 600;

	
	
	public static enum EMenus{
		file("File", new GEFileMenu()),
		edit("Edit", new GEEditMenu()),
		color("Color", new GEColorMenu());
		
		private String menuName;
		private JMenu menu;
		
		private EMenus(String menuName, JMenu menu){
			this.menuName = menuName;
			this.menu = menu;
		}	
		public JMenu getMenu() {return this.menu;}
		public String getMenuName() {return this.menuName;}
	}
	
	public static enum EFileMenuItems{
		newFile("New"),
		openFile("Open"),
		saveFile("Save"),
		saveAsFile("Save_As"),
		closeFile("Close"),
		exitProgram("Exit");
	
		private String name;
		
		private EFileMenuItems(String name) {
			this.name = name;
		}
		
		public String getFileMenuName() {return this.name;}
	}

	
	public static enum EEditMenuItems{
		Do("Do"),
		Undo("Undo"),
		Cut("Cut"),
		Copy("Copy"),
		Paste("Paste"),
		Delete("Delete"),
		Select_all("select_all"),
		Deselect_all("Deselect_all");
		
		private String name;
		private EEditMenuItems(String name) {this.name = name;}
		public String getEditMenuName() {return this.name;}
	}
	public static enum EColorMenuItems {
		LineColor("LineColor"),
		FillColor("FillColor");
		
		private String name;
		private EColorMenuItems(String name) {this.name = name;}
		public String getColorMenuName() {return name;}
	}
	//GEToolbar
	public static enum EToolbarIcons {Line,Text,Circle,Rectangle,Heart, Ellipse, 
							Polygon};
	public static final String TOOLBAR_ICON_URL = "rsc/";
	public static final String TOOLBAR_EXTENDER = ".jpg";
	public static final String TOOLBAR_SLT_EXTENDER = "SLT.jpg";
	
	public static enum EButtons {
		rectangle("rectangle.gif", "rectangle.gif"),//0  각각의 오브젝트가 아래의 속성들을 갖는다
		ellipse("rectangle.gif", "rectangle.gif"), //1
		line("rectangle.gif", "rectangle.gif"), //2
		heart("rectangle.gif", "rectangle.gif"), //3
		polygon("rectangle.gif", "rectangle.gif"), //4
		text("rectangle.gif", "rectangle.gif"); //5   이것들 하나하나를 object를 만들 수 있다.
		
		private String buttonImage;
		private String selectedButtonImage;  // 각 위에 오브젝트의 속성, 이 오브젝트는 이러한 속성을 갖고있을것이다.
		
		private EButtons(String buttonImage, String selectedButtonImage){
			this.buttonImage = buttonImage;
			this.selectedButtonImage = selectedButtonImage;
		}
		public String getButtonImage(){return buttonImage;};
		public String getselectedButtonImage(){return buttonImage;};
	}
}
