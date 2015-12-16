package constants;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;

import menus.CColorMenu;
import menus.CEditMenu;
import menus.CFileMenu;
import menus.CMenu;
import shapes.CEllipseManager;
import shapes.CLineManager;
import shapes.CRectangleManager;
import shapes.CSelectionManager;
import shapes.CShapeManager;
import transformer.CDrawer;
import transformer.CMover;
import transformer.CResizer;
import transformer.CRotator;
import transformer.CTransformer;

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
	public static final int TOOLBAR_H = 50;
	
	//Menu attributes
	public interface EMenuItem {
		public String getName();
		public String getNames();
	}
	public static enum EMENU {
		File("파일", new CFileMenu(EFILEMENUITEM.values())),//"File", EFILEMENUITEM.values())),
		Edit("편집", new CEditMenu(EEDITMENUITEM.values())),//"Edit",EEDITMENUITEM.values())),
		Color("색", new CColorMenu(ECOLORMENUITEM.values()));//"Color",ECOLORMENUITEM.values()));
		//Help("Help", EHELPMENUITEM.values(),new CHelpMenu());
		
		
		private String name;
		private CMenu menu;
		private EMENU(String name, CMenu menu){
			this.name=name;
			this.menu=menu;
		}
		public String getName(){	return name;	}
		public CMenu newMenu(){ return menu;	}
	};
	public static enum EFILEMENUITEM implements EMenuItem {
		newFile("새 파일"),
		open("열기"),
		save("저장"),
		saveAs("다른 이름으로 저장"),
		close("닫기"),
		exit("종료");
		
		private String name;
		private EFILEMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
		@Override
		public String getNames() {
			return this.name();
		}
	};
	
	public static enum EEDITMENUITEM implements EMenuItem {
		cut("잘라내기"),
		copy("복사"),
		paste("붙여넣기"),
		delete("지우기"),
		unDo("되돌리기"),
		reDo("다시실행"),
		group("그룹화"),
		unGroup("그룹 해제");
		
		private String name;
		private EEDITMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
		@Override
		public String getNames() {
			return this.name();
		}
	};
	
	public static enum ECOLORMENUITEM implements EMenuItem {
		fill("면 색"),
		line("선 색"),
		font("글꼴 색");
		
		private String name;
		private ECOLORMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
		@Override
		public String getNames() {
			return this.name();
		}
	}
	public static enum EHELPMENUITEM implements EMenuItem{
		Help("Help"),
		Version("Version");
		
		private String name;
		private EHELPMENUITEM(String name){
			this.name=name;
		}
		public String getName(){return name;}
		@Override
		public String getNames() {
			return this.name();
		}
	}
	
	//Button Attribute
	public static enum EBUTTON { 
		Rectangle("img/rectangle.png", "img/rectangle_push.png", new CRectangleManager()),
		Ellipse("img/ellipse.png", "img/ellipse_push.png", new CEllipseManager()), 
		Line("img/line.png", "img/line_push.png", new CLineManager()), 
		Polygon("img/polygon.png", "img/polygon_push.png", new CRectangleManager()),
		Selection("img/rectangle.png", "img/rectangle_push.png", new CSelectionManager());
		
		private String iconname;
		private String selectedIconName;
		private CShapeManager tool;
		private EBUTTON(String iconname, String selectedIconName, CShapeManager tool){
			this.iconname=iconname;
			this.selectedIconName=selectedIconName;
			this.tool = tool;
		}
		public String geticonname(){return iconname;}
		public String getselectedIconName(){return selectedIconName;}
		public CShapeManager newTool(){	return tool;	}
	};
	
	
	public static enum EDrawingState {
		idle,
		TPDrawing,
		NPDrawing,
		transforming;
		
	};
	
	public static enum EDrawingType {
		Twopoint,
		NPoint;
	};
	
	public static enum ETransformationState {
		draw(new CDrawer()),
		move(new CMover()),
		rotate(new CRotator()),
		resize(new CResizer());
		
		private CTransformer transformer;
		private ETransformationState(CTransformer transformer){
			this.transformer=transformer;
		}
		public CTransformer newTransformer(){	return transformer;	}
	}
	public static enum EAnchorType{	
		NN(0), NE(1), EE(2), SE(3), SS(4), SW(5), WW(6), NW(7), RR(8);
		
		private int no;
		
		private EAnchorType(int no) {
			this.no = no;
		}
		public static EAnchorType getValue(int i){
			for(EAnchorType position : EAnchorType.values()) {
	            if (position.no == i) {
	                return position;
	            }
	        }
	        return null;
		}
	}
	
	public static final int ANCHOR_W = 8;
	public static final int ANCHOR_H = 8;
	
	public static Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	//cursor attribute
	public static enum ECURSOR {
		N_RESIZE(0,new Cursor(Cursor.N_RESIZE_CURSOR)),
		NE_RESIZE(1,new Cursor(Cursor.NE_RESIZE_CURSOR)),
		E_RESIZE(2,new Cursor(Cursor.E_RESIZE_CURSOR)),
		SE_RESIZE(3,new Cursor(Cursor.SE_RESIZE_CURSOR)),
		S_RESIZE(4,new Cursor(Cursor.S_RESIZE_CURSOR)),
		SW_RESIZE(5,new Cursor(Cursor.SW_RESIZE_CURSOR)),
		W_RESIZE(6,new Cursor(Cursor.W_RESIZE_CURSOR)),
		NW_RESIZE(7,new Cursor(Cursor.NW_RESIZE_CURSOR)),
		ROTATE(8,toolkit.createCustomCursor(toolkit.getImage("img/RotateCursor.gif"), new Point(12,12), "rotate")),
		MOVE(9,new Cursor(Cursor.MOVE_CURSOR)),
		DEFAULT(10,new Cursor(Cursor.DEFAULT_CURSOR));
		
		private Cursor cursor;
		private int index;
		private ECURSOR(int index, Cursor cursor){
			this.index = index;
			this.cursor = cursor;
		}
		public Cursor getCursor(){ return cursor;	}
		public static ECURSOR valueOf( int value ){
		//	ECURSOR result = null;
	        final ECURSOR [] cursors = ECURSOR.values();
	        for( ECURSOR cursor : cursors ){
	            if( cursor.index == value ){
	                return cursor;
	            }
	        }
	        return null;
	    }
	}
	
	//file attribute 
	public static final String DEFAULTFILENAME = "test";
}
