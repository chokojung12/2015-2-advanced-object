package main;
import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class GEFrame extends JFrame {
	public GEFrame(){
		JMenuBar menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
	}
}
