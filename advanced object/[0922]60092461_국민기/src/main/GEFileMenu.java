package main;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEFileMenu extends JMenu {
	private Vector<JMenuItem> menuItems;
	public GEFileMenu(String menuString){
		super(menuString);
		
		for(EFileMenuItem eFileMenuItem : EFileMenuItem.values()){
			JMenuItem item = new JMenuItem(eFileMenuItem.getName());
			this.add(item);
		}
		
		menuItems = new Vector<JMenuItem>();
		
		for(int i = 1; i < menuItems.size(); ++i){
			this.add(menuItems.get(i));
		}
		
	}
	public static enum EFileMenuItem{
		newFile("new"),
		open("open"),
		save("Save");
		private String name;
		private JFrame frame;
		private EFileMenuItem(String name){
			this.name = name;

		}
		public String getName(){return name;};

	}
}
