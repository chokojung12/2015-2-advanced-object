package constants;
import java.util.Vector;

import javax.swing.JMenu;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShape;


public class GEConstant {
	
	public static final int FRAME_W = 400;
	public static final int FRAME_H = 600;
	
	public static enum EDrawingState {idle, drawingTP, drawingNP, moving};
	
	public static enum EButtons {
		// ���� ������
		rectangle("rectangle.gif", "rectangleSLT.gif", new GERectangle()),
		ellipse("ellipse.gif", "ellipseSLT.gif", new GEEllipse()),
		line("line.gif", "lineSLT.gif", new GELine()),
		heart("heart.gif", "heartSLT.gif", new GERectangle()),
		polygon("polygon.gif", "polygonSLT.gif", new GEPolygon()),
		text("text.gif", "textSLT.gif", new GERectangle());
		
		private String buttonImage;
		private String selectedButtonImage;
		private GEShape newShape;
		
		private EButtons(String buttonImage, String selectedButtonImage, GEShape newShape){
			this.buttonImage = buttonImage;
			this.selectedButtonImage = selectedButtonImage;
			this.newShape = newShape;
		}
		public String getButtonImage() {return buttonImage;}
		public String getSelectedButtonImage() {return selectedButtonImage;}
		public GEShape getShape() {return newShape;}
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
		newFile("new"),
		open("open"),
		save("save"),		
		saveAs("saveAs"),		
		close("close"),		
		print("print"),		
		exit("exit");		
		private String name;		
		private EFileMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}
	
	public static String SFILECONFIG = "config\\file.config";
	public static String SWORKSPACE = "data\\";
	public static String SSAVEORNOT = "���� ������ �����Ͻðڽ��ϱ�?";
	public static String SFILEKIND ="Graphics Editor";
	public static String SFILEEXTENSION ="gps"	;
	
	public enum EEditMenuItem {
		copy("copy"),
		cut("cut"),
		paste("paste"),		
		delete("delete"),		
		redo("redo"),
		undo("undo"),		
		group("group"),		
		ungroup("ungroup"),		
		selectAll("select All"), ;		
		private String name;		
		private EEditMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}
	public enum EColorMenuItem {
		lineColor("lineColor"),
		fillColor("fillColor"),
		textColor("textColor");		
		private String name;		
		private EColorMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}
}
