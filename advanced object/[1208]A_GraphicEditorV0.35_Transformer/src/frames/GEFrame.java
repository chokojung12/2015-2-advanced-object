package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GEConstant;

public class GEFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private GEMenuBar menuBar;
	private GEPanel panel;
	private GEToolbar toolbar;
	
	// associations
	
	// working variables
	
	public GEFrame() {
		super();
		// attributes initialization
		this.setSize(GEConstant.FRAME_W, GEConstant.FRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// components lifecycle management
		this.menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		this.toolbar = new GEToolbar();
		this.add(toolbar, BorderLayout.NORTH);				
		this.panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);		
	}
	
	public void init() {
		this.menuBar.init(panel);
		this.toolbar.init(panel);
		this.panel.init();
		
		this.setVisible(true);	
	}
}
