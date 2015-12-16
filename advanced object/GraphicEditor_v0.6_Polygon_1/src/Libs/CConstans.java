package Libs;

import java.util.EnumMap;
import java.util.Vector;


import shapes.CPolygon;
import shapes.CRectangle;
import shapes.CShape;

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
		File("File",EFILEMENUITEM.values()),
		Edit("Edit",EEDITMENUITEM.values()),
		Color("Color",ECOLORMENUITEM.values());
		//Help("Help",EHELPMENUITEM.values());
		
		private Vector<String> menuItem;
		private String name;
		private EMENU(String name, Object obj){
			this.name=name;
			if(obj.getClass().equals(EFILEMENUITEM.values().getClass())){
				menuItem = new Vector<String>();
				for(EFILEMENUITEM items : (EFILEMENUITEM[])obj){
					menuItem.add(items.getName());
				}
			}else if(obj.getClass().equals(EEDITMENUITEM.values().getClass())){
				menuItem = new Vector<String>();
				for(EEDITMENUITEM items : (EEDITMENUITEM[])obj){
					menuItem.add(items.getName());
				}
			}else if(obj.getClass().equals(ECOLORMENUITEM.values().getClass())){
				menuItem = new Vector<String>();
				for(ECOLORMENUITEM items : (ECOLORMENUITEM[])obj){
					menuItem.add(items.getName());
				}
			}
		}
		public String getName(){	return name;	}
		public Vector<String> getItem(){	return menuItem;	}
	};
	public static enum EFILEMENUITEM {
		New("new"),
		Open("open"),
		Save("save"),
		Saveas("save-as"),
		Close("close"),
		Exit("exit");
		
		private String name;
		private EFILEMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
	};
	
	public static enum EEDITMENUITEM {
		Cut("cut"),
		Copy("copy"),
		Paste("paste"),
		Delete("delete"),
		Do("do"),
		Undo("undo"),
		Redo("redo"),
		Group("group"),
		Ungroup("ungroup");
		
		private String name;
		private EEDITMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
	};
	
	public static enum ECOLORMENUITEM {
		Fill("fill color"),
		Line("line color"),
		Font("font color");
		
		private String name;
		private ECOLORMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
	};
	
	//Button Attribute
	public static enum EBUTTON { 
		Rectangle("img/rectangle.png", "img/rectangle_push.png", new CRectangle()),
		Ellipse("img/ellipse.png", "img/ellipse_push.png", new CRectangle()), 
		Line("img/line.png", "img/line_push.png", new CRectangle()), 
		Polygon("img/polygon.png", "img/polygon_push.png", new CPolygon());
		
		private String iconname;
		private String selectedIconName;
		private CShape tool;
		private int[] points;
		private EBUTTON(String iconname, String selectedIconName, CShape tool){
			this.iconname=iconname;
			this.selectedIconName=selectedIconName;
			this.tool = tool;
			this.points=null;
		}
		public String geticonname(){return iconname;}
		public String getselectedIconName(){return selectedIconName;}
		public CShape newTool(){	return tool;	}
		public int[] getPoints(){ return points;	}
	};
	
	
	public static enum EDrawingState {
		idle,
		TPdrawing,
		NPdrawing,
		moving,
		resizing,
		rotating;
		
	}
	
	
	
	//file attribute 
	public static final String DEFAULTFILENAME = "test";
}
