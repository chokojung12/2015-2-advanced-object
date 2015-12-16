package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class GEFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GEFrame(){
		JMenuBar menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);			// GEFrame°´Ã¼, JFrame »ó¼Ó
		JToolBar toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		JPanel panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
	}
}
