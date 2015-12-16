package Constant;

import java.awt.Color;

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
	public static enum EToolBarIcons {Line, Text, Ellipse, Rectangle, Heart,
							Polygon};
	
	public static final String TOOLBAR_ICON_URL = "rsc/";
	public static final String TOOLBAR_EXTENDER = ".jpg";
	public static final String TOOLBAR_SLT_EXTENDER = "SLT.jpg";	

}
