import java.awt.Component;

import javax.swing.JFrame;


public class CFrame extends JFrame {
	private CMenuBar menuBar;
	private CToolbar toolbar;
	private CDrawingPanel drawingPanel;
	
	public CFrame(){
		menuBar = new CMenuBar();
		this.setJMenuBar(menuBar);
		
		toolbar = new CToolbar();
		this.add(toolbar);
		
		drawingPanel = new CDrawingPanel();
		this.add(drawingPanel);
		
		toolbar.setDrawingPanel(drawingPanel);
	}
	
	public void init(){
		menuBar.init(drawingPanel);
	}

}