package main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GEMenuBar extends JMenuBar {
	public GEMenuBar(){
		JMenu fileMenu = new JMenu("file");
		this.add(fileMenu);
	}
}
