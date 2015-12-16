package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

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
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.init(panel);
		
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
		GEConstant.PANEL = panel;
		
		toolbar = new GEToolbar();
		toolbar.init(panel);
		this.add(toolbar, BorderLayout.NORTH);
		toolbar.init(panel);
	}
}
