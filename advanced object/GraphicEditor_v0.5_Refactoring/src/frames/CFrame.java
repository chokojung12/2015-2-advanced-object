package frames;
import java.awt.Component;

import javax.swing.JFrame;


public class CFrame extends JFrame {
	//attributes
	
	//components
	private CMenuBar menuBar;
	private CToolbar toolbar;
	private CDrawingPanel drawingPanel;
	
	//1st phase initialization
	public CFrame(){
		// attributes initialization
		this.setSize(CConstans.FRAME_W,CConstans.FRAME_H);
		this.setLocation(CConstans.FRAME_X, CConstans.FRAME_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		//components initialization
		this.menuBar = new CMenuBar();
		this.setJMenuBar(menuBar);
		this.toolbar = new CToolbar();
		this.add(toolbar);
		this.drawingPanel = new CDrawingPanel();
		this.add(drawingPanel);
		//this.toolbar.setDrawingPanel(drawingPanel);		
	}
	//2nd phase initialization
	public void init(){
		//components association initialization
		this.menuBar.init(drawingPanel);
		this.toolbar.init(drawingPanel);
		this.drawingPanel.init();
				
		//associated attributes initialization
		this.setVisible(true);
	}

}