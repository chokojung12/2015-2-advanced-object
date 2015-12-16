package constants;

import java.awt.Cursor;
import java.util.EnumMap;
import java.util.Vector;






import shapes.*;

public class CConstans {
	//Frame attributes
	public static final int FRAME_X = 100;
	public static final int FRAME_Y = 100;
	public static final int FRAME_W = 400;
	public static final int FRAME_H = 600;
	
	public static enum EJFrame{
		Toolbar,
		DrawingPanel;
	};
	
	//Toolbar Attributes
	public static final int TOOLBAR_W = FRAME_W;
	public static final int TOOLBAR_H = 100;
	
	//Menu attributes
	public static enum EMENU {
		File("File", EFILEMENUITEM.values()),
		Edit("Edit", EEDITMENUITEM.values()),
		Color("Color", ECOLORMENUITEM.values()),
		Help("Help", EHELPMENUITEM.values());
		
		
		private String name;
		private Enum[] items;
		private EMENU(String name, Enum[] items){
			this.name=name;
			this.items=items;
		}
		public String getName(){	return name;	}
		public Enum[] getItems(){	return items;	}
	};
	public static enum EFILEMENUITEM {
		New("New"),
		Open("Open"),
		Save("Save"),
		Saveas("Save-as"),
		Close("Close"),
		Exit("Exit");
		
		private String name;
		private EFILEMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
	};
	
	public static enum EEDITMENUITEM {
		Cut("Cut"),
		Copy("Copy"),
		Paste("Paste"),
		Delete("Delete"),
		Do("Do"),
		Undo("Undo"),
		Redo("Redo"),
		Group("Group"),
		Ungroup("Ungroup");
		
		private String name;
		private EEDITMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
	};
	
	public static enum ECOLORMENUITEM {
		Fill("Fill color"),
		Line("Line color"),
		Font("Font color");
		
		private String name;
		private ECOLORMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
	}
	public static enum EHELPMENUITEM {
		Help("Help"),
		Version("Version");
		
		private String name;
		private EHELPMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
	}
	
	//Button Attribute
	public static enum EBUTTON { 
		Rectangle("img/rectangle.png", "img/rectangle_push.png", new CRectangle()),
		Ellipse("img/ellipse.png", "img/ellipse_push.png", new CEllipse()), 
		Line("img/line.png", "img/line_push.png", new CLine()), 
		Polygon("img/polygon.png", "img/polygon_push.png", new CPolygon());
		
		private String iconname;
		private String selectedIconName;
		private CShape tool;
		private EDrawingState eDrawingType;
		private EBUTTON(String iconname, String selectedIconName, CShape tool){
			this.iconname=iconname;
			this.selectedIconName=selectedIconName;
			this.tool = tool;
		}
		public String geticonname(){return iconname;}
		public String getselectedIconName(){return selectedIconName;}
		public CShape newTool(){	return tool;	}
	};
	
	
	public static enum EDrawingState {
		idle,
		TPDrawing,
		NPDrawing,
		moving,
		resizing,
		rotating;
		
	};
	
	public static enum ECURSOR {
		DEFAULT(0,Cursor.DEFAULT_CURSOR),
		NW_RESIZE(1,Cursor.NW_RESIZE_CURSOR),
		N_RESIZE(2,Cursor.N_RESIZE_CURSOR),
		NE_RESIZE(3,Cursor.NE_RESIZE_CURSOR),
		E_RESIZE(4,Cursor.E_RESIZE_CURSOR),
		SE_RESIZE(5,Cursor.SE_RESIZE_CURSOR),
		S_RESIZE(6,Cursor.S_RESIZE_CURSOR),
		SW_RESIZE(7,Cursor.SW_RESIZE_CURSOR),
		W_RESIZE(8,Cursor.W_RESIZE_CURSOR);
		
		private int cursor;
		private int index;
		private ECURSOR(int index, int cursor){
			this.index = index;
			this.cursor = cursor;
		}
		public int getCursor(){ return cursor;	}
		public static ECURSOR valueOf( int value ){
			ECURSOR result = null;
	        final ECURSOR [] cursors = ECURSOR.values();
	        for( ECURSOR cursor : cursors ){
	            if( cursor.index == value ){
	                result = cursor;
	                break;
	            }
	        }	        
	        return result;
	    }
	}
	
	//file attribute 
	public static final String DEFAULTFILENAME = "test";
}
