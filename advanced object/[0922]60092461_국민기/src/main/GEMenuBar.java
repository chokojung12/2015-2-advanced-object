package main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GEMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GEMenuBar(){
		JMenu fileMenu = new JMenu("File");		
		this.add(fileMenu);					// GEMenuBar°´Ã¼, JMenubar »ó¼Ó
		fileMenu.add(new JMenuItem("new"));
		fileMenu.add(new JMenuItem("open"));
		fileMenu.add(new JMenuItem("save"));
		fileMenu.add(new JMenuItem("save_as"));
		fileMenu.add(new JMenuItem("exit"));
		
		JMenu editMenu = new JMenu("Edit");
		this.add(editMenu);
		editMenu.add(new JMenuItem("undo"));
		editMenu.add(new JMenuItem("redo"));
		editMenu.add(new JMenuItem("cut"));
		editMenu.add(new JMenuItem("copy"));
		editMenu.add(new JMenuItem("paste"));
		editMenu.add(new JMenuItem("delete"));
		editMenu.add(new JMenuItem("select_all"));
		
		JMenu colorMenu = new JMenu("Color");
		this.add(colorMenu);
	}
}
