package frames;

import shapes.CPolygon;
import shapes.CRectangle;
import shapes.CShape;

public class CConstants {
	// frame attributes
	public final static int FRAME_X = 100;
	public final static int FRAME_Y = 100;
	public final static int FRAME_W = 400;
	public final static int FRAME_H = 600;
	
	public static enum EJFrame {
		ToolBar,
		DrawingPanel;
	}
	
	// menu attributes
	public static enum EMenu {
		File("ÆÄÀÏ"),
		Edit("Edit"),
		Color("Color");
		
		private String name;
		private EMenu(String name) {
			this.name = name;
		}
		public String getName() {return name;}
	}
	
	public static enum EFileMenuItem {
		New("New"),
		Open("Open"),
		Save("Save"),
		SaveAs("SaveAs"),
		Close("Close"),
		Exit("Exit");
		
		private String name;
		private EFileMenuItem(String name) {this.name = name;}
		public String getName() {return name;}
	}
	public static enum EEditMenuItem {
		Do("Do"),
		Undo("Undo"),
		Cut("Cut"),
		Paste("Paste"),
		Copy("Copy"),
		Delete("Delete"),
		Group("Group"),
		Ungroup("Ungroup");
		
		private String name;
		private EEditMenuItem(String name) {this.name = name;}
		public String getName() {return name;}
	}
	public static enum EColorMenuItem {
		FillColor("FillColor"),
		LineColor("LineColor");
		
		private String name;
		private EColorMenuItem(String name) {this.name = name;}
		public String getName() {return name;}
	}
	
	// toolbar attributes
	public final static int TOOLBAR_W = FRAME_W;
	public final static int TOOLBAR_H = 40;
	
	public static enum EButton { 
		Rectangle("rectangle.gif", "rectangleSLT.gif", new CRectangle()), 
		Ellipse("ellipse.gif", "ellipseSLT.gif", new CRectangle()), 
		Line("line.gif", "lineSLT.gif", new CRectangle()), 
		Polygon("polygon.gif", "polygonSLT.gif", new CPolygon());
		
		private String iconName;
		private String iconSLTName;
		private CShape tool;
		
		private EButton(String iconName, String iconSLTName, CShape tool) {
			this.iconName = iconName;
			this.iconSLTName = iconSLTName;
			this.tool = tool;
		}
		public String getIconName() {return iconName;}
		public String getIconSLTName() {return iconSLTName;}
		public CShape newTool() {return tool;}
	};
	
	public static enum EDrawingState {
		idle,
		NPDrawing,
		TPDrawing,
		moving,
		resizing,
		rotating;
	}
	
	// drawingPanel attributes
	public static final String DEFAULTFILENAME = "test";
}
