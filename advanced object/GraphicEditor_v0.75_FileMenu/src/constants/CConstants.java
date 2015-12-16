package constants;

import menus.CFileMenu;
import menus.CMenu;
import shapes.CPolygonManager;
import shapes.CRectangleManager;
import shapes.CShapeManager;
import transformers.CDrawer;
import transformers.CMover;
import transformers.CResizer;
import transformers.CRotater;
import transformers.CTransformer;

public class CConstants {
	// frame attributes
	public final static int FRAME_X = 100;
	public final static int FRAME_Y = 100;
	public final static int FRAME_W = 400;
	public final static int FRAME_H = 600;
	
	public final static int DASH_OFFSET = 4;
	public final static int DASHEDLINE_WIDTH = 1;
	
	public static enum EJFrame {
		ToolBar,
		DrawingPanel;
	}
	
	public interface EMenuItem {
		public String getName();
	}
	// menu attributes
	public static enum EMenus {
		File("파일", new CFileMenu(EFileMenuItems.values())),
		Edit("Edit", new CFileMenu(EFileMenuItems.values())),
		Color("Color", new CFileMenu(EFileMenuItems.values()));
		
		private String name;
		private CMenu menu;
		private EMenus(String name, CMenu menu) {
			this.name = name;
			this.menu = menu;
		}
		public String getName() {return name;}
		public CMenu newMenu() {return menu;}
	}
	public static enum EFileMenuItems implements EMenuItem {
		newFile("New"),
		openFile("Open"),
		saveFile("Save"),
		saveAsFile("SaveAs"),
		closeFile("Close"),
		exitProgram("Exit");
		
		private String name;
		private EFileMenuItems(String name) {
			this.name = name;
		}
		public String getName() {return name;}
	}
	public final static String sLABEL_CONFIRMSAVE 		= "저장 확인";
	public final static String sMSG_CONFIRMSAVE 		= "변경된 내용을 저장 하시겠습니다까?";
	
	public static enum EEditMenuItems {
		Do("Do"),
		Undo("Undo"),
		Cut("Cut"),
		Paste("Paste"),
		Copy("Copy"),
		Delete("Delete"),
		Group("Group"),
		Ungroup("Ungroup");
		
		private String name;
		private EEditMenuItems(String name) {this.name = name;}
		public String getName() {return name;}
	}
	public static enum EColorMenuItems {
		FillColor("FillColor"),
		LineColor("LineColor");
		
		private String name;
		private EColorMenuItems(String name) {this.name = name;}
		public String getName() {return name;}
	}
	
	// toolbar attributes
	public final static int TOOLBAR_W = FRAME_W;
	public final static int TOOLBAR_H = 40;
	
	public static enum EShapeManager { 
		Rectangle("rectangle.gif", "rectangleSLT.gif", new CRectangleManager()), 
		Ellipse("ellipse.gif", "ellipseSLT.gif", new CRectangleManager()), 
		Line("line.gif", "lineSLT.gif", new CRectangleManager()), 
		Line1("line.gif", "lineSLT.gif", new CRectangleManager()), 
		Polygon("polygon.gif", "polygonSLT.gif", new CPolygonManager());
		
		private String iconName;
		private String iconSLTName;
		private CShapeManager tool;
		
		private EShapeManager(String iconName, String iconSLTName, CShapeManager tool) {
			this.iconName = iconName;
			this.iconSLTName = iconSLTName;
			this.tool = tool;
		}
		public String getIconName() {return iconName;}
		public String getIconSLTName() {return iconSLTName;}
		public CShapeManager newTool() {return tool;}
	}
	
	public static enum EDrawingType {TwoPoint, NPoint;}
	public static enum EDrawingState {
		idle,
		TPDrawing,
		NPDrawing,
		transforming;
	}
	public static enum ETransformationState {
		draw(new CDrawer()),
		resize(new CResizer()),
		move(new CMover()),
		rotate(new CRotater());
		
		private CTransformer transformer;
		private ETransformationState(CTransformer transformer) {
			this.transformer = transformer;			
		}
		public CTransformer newTransformer() {
			return transformer;
		}
	}	
	public static enum EAnchorType {
		NN, NE, EE, SE, SS, SW, WW, NW, RR;
	}
	public static final int ANCHOR_W = 6;
	public static final int ANCHOR_H = 6;
	
	// drawingPanel attributes
	public static final String DEFAULTFILENAME = "test";
}








