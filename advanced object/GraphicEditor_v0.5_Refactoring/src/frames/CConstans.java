package frames;

import java.util.EnumMap;
import java.util.Vector;

public class CConstans {
	//Frame attributes
	public static final int FRAME_X = 100;
	public static final int FRAME_Y = 100;
	public static final int FRAME_W = 400;
	public static final int FRAME_H = 600;
	
	//Toolbar Attributes
	public static final int TOOLBAR_W = FRAME_W;
	public static final int TOOLBAR_H = 100;
	
	//Menu attributes
	public static final String[] MENUNAMES = {"File","Edit","Color","Help"};	
	public static final String[][] MENUITEMNAMES = { 
													{"new","open","save","save-as","close","exit"},
													{"cut","copy","paste","delete","do","undo","redo","group","ungroup"},
													{"fill color","line color", "font color"},
													{}
													};
	public static enum EMENU {
		File("File",""),//EFILEMENUITEM.values()),
		Edit("Edit",""),//EEDITMENUITEM.values()),
		Color("Color",""),//ECOLORMENUITEM.values()),
		Help("Help","");
		
		private String menuItem;
		private String name;
		private EMENU(String name, String menuItem){
			this.name=name;
		}
		public String getName(){return name;}
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
		Rectangle("img/rectangle.png", "img/rectangle_push.png", "Rectangle"), 
		Ellipse("img/ellipse.png", "img/ellipse_push.png", "Ellipse"), 
		Line("img/line.png", "img/line_push.png", "Line"), 
		Polygon("img/polygon.png", "img/polygon_push.png", "Polygon");
		
		private String fileName;
		private String fileName2;
		private String actionCommand;
		private EBUTTON(String fileName, String fileName2, String actionCommand){
			this.fileName=fileName;
			this.fileName2=fileName2;
			this.actionCommand=actionCommand;
		}
		public String getActionCommand(){return actionCommand;}
		public String getfileName(){return fileName;}
		public String getfileName2(){return fileName2;}
	};
	
	
	
	//file attribute 
	public static final String DEFAULTFILENAME = "test";
}
