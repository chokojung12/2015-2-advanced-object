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
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);		
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);		
		toolbar = new GEToolbar();
		this.add(toolbar, BorderLayout.NORTH);				
	}
	
	public void init() {
		menuBar.init(panel);
		toolbar.init(panel);
		panel.init();
	}
}
