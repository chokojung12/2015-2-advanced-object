package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	
	private void open() {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String fileName = chooser.getSelectedFile().getName();
			GEModelShape.read(fileName);
			this.drawingPanel.repaint();	
	    }
	}
	
	private void save() {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String fileName = chooser.getSelectedFile().getName();
			GEModelShape.save(fileName);
	    }
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				open();
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {
				save();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				System.exit(1);
			}
		}
	}	
}
