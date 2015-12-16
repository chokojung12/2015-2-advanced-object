package main;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;

import main.GEConstants.EFileMenus;
import main.GEConstants.EToolbarIcons;

public class GEMenuBar extends JMenuBar {

	private Vector<GEFileMenu> menus;

	public GEMenuBar(){
		super();
		/*
		menus = new Vector<GEFileMenu>();
		for(int i = 0; i < GEConstants.menuNames.length; i++){
			GEFileMenu menu = new GEFileMenu(GEConstants.menuNames[i],GEConstants.menuItemNames[i]);
			menus.add(menu);
			this.add(menu);
		}*/
		
		for(EFileMenus menus : EFileMenus.values()){
			JMenu menu = new JMenu(menus.toString());
			this.add(menu);
		}
		//JMenu fileMenu = new JMenu("File");
		
	}
}
