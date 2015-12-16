package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import shapes.GEShape;
import constants.GEConstant;
import constants.GEConstant.EFileMenuItem;
import entity.GEModelShape;
import frames.GEMenu;
import frames.GEPanel;

public class GEFileMenu extends GEMenu {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;

	
	public GEFileMenu() {	
		actionListener = new ActionHandler();
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EFileMenuItem eMenuItem : GEConstant.EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItems.add(menuItem);
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.add(menuItem);
		}
	}
	
	private void open(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("","");
		chooser.setFileFilter(filter);
		JFileChooser jFileChooser = new JFileChooser();
		GEModelShape.read("test");
		this.drawingPanel.repaint();
	}	
	private void save(){
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("","");
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String fileName = chooser.getSelectedFile().getName();
			GEModelShape.save(fileName);
		}
		GEModelShape.save("test");
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				open();
			} 
			else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {	
				save();	
			} 
			else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				System.exit(1);
			}
		}
	}	
}
