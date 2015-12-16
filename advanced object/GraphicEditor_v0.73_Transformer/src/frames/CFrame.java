package frames;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import constants.CConstans;
import menus.CMenuBar;



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
		
		this.getContentPane().setLayout(new BorderLayout());
		this.toolbar = new CToolbar();
		this.add(this.toolbar, BorderLayout.NORTH);
		this.drawingPanel = new CDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);	

		
	}
	//2nd phase initialization
	public void init(){
		//components association initialization
		this.menuBar = (CMenuBar) this.getJMenuBar();
		this.menuBar.init(drawingPanel); //this.menuBar.init(drawingPanel);
		this.toolbar.init(drawingPanel);
		this.drawingPanel.init();
				
		//associated attributes initialization
		this.setVisible(true);
	}

}