package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GEConstant;
public class GEFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private GEMenuBar menuBar;
	private GEPanel drawingPanel;
	private GEToolBar toolBar;
	
	// associations
	
	// working variables
	
	public GEFrame() {
		super(); // �ý����� ����Ϳ��� super�� �ѹ� �ҷ����
		// attributes initialization
		this.setSize(GEConstant.FRAME_W, GEConstant.FRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// components lifecycle management
		this.menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);		
		
		this.drawingPanel = new GEPanel();
		this.add(drawingPanel, BorderLayout.CENTER);		//jframe�� jcomponenet vector�� add ��Ŵ		
		this.toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
	}
	
	public void init() {
		// associations
		this.menuBar.init(drawingPanel);	//association
		this.toolBar.init(drawingPanel);
		this.drawingPanel.init();
		
		this.setVisible(true);
	}
}
