package main;


public class GEConstants {
	public static final String[] menuNames = {"File","Edit","Color"};	
	public static final String[][] menuItemNames = { 
													{"new","open","save","save-as","close","exit"},
													{"cut","copy","paste","delete","do","undo","redo","group","ungroup"},
													{"line_color","fill_color"},
													};	
	
	
	//GEToolbar
	public static enum EToolbarIcons {Line,Text,Circle,Rectangle,Heart, Ellipse, 
							Polygon};
	public static final String TOOLBAR_ICON_URL = "rsc/";
	public static final String TOOLBAR_EXTENDER = ".jpg";
	public static final String TOOLBAR_SLT_EXTENDER = "SLT.jpg";
}
