package constants;

import java.awt.Color;

public class GEConstants {
	//GEMainFrame
	public static final int WIDTH_MAINFRAME = 400;
	public static final int HEIGHT_MAINFRAME = 600;
	public static final String TITLE_MAINFRAME = "그래픽 에디터-05";	
	
	//GEMenu
	public static final String TITLE_FILEMENU = "파일";	
	public static final String TITLE_EDITMENU = "편집";	
	public static final String TITLE_COLORMENU = "컬러";	
	
	//GEMenuItems
	public static enum EFileMenuItems {새로만들기, 열기, 저장, 다른이름으로저장, 종료};
	public static enum EEditMenuItems {Undo, Redo, 삭제, 잘라내기, 복사, 붙이기, Group, Ungroup};
	public static enum EColorMenuItems {SetLineColor, ClearLineColor, SetFillColor, ClearFillColor};
	
	//GEToolbarShape
	public static final String TITLE_SHAPETOOLBAR = "Shape Tools";
	public static enum EToolBarButtons {Line,Text,Circle,Rectangle,Heart, Ellipse, 
		Polygon};
	public static final String IMG_URL = "images/";
	public static final String TOOLBAR_BTN = ".gif";
	public static final String TOOLBAR_BTN_SLT = "SLT.gif";
	
	//GEDrawingPanel
	public static final Color FOREGROUND_COLOR = Color.BLACK;
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	
}









