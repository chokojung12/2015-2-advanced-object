package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstant;
import constants.GEConstant.EFileMenuItem;
import entity.GEModel;
import frames.GEPanel;

public class GEFileMenu extends GEMenu {
	
	private static final long serialVersionUID = 1L;
	
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	private String currentDirectory;
	private String fileName = null;

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
	public void init(GEPanel drawingPanel) {
		super.init(drawingPanel);
		openDirectory();
	}
	
	private void openDirectory() {
		try {
			this.currentDirectory = (String) GEModel.read(GEConstant.SFILECONFIG);
		} catch (ClassNotFoundException | IOException e) {
			this.currentDirectory = GEConstant.SWORKSPACE;
		}
	}
	
	private int SaveOrNot() {
		int reply = JOptionPane.NO_OPTION;
		if(drawingPanel.isUpdated()==true) {
			reply = JOptionPane.showConfirmDialog(null, GEConstant.SSAVEORNOT);
		}
		if(reply == JOptionPane.OK_OPTION) {
			save();
		}
		return reply;
	}
	
	private void newFile() {
		int reply = SaveOrNot();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			this.drawingPanel.newShapes();
		}
	}
	
	private JFileChooser createChooser() {
	    JFileChooser chooser = new JFileChooser(this.currentDirectory);
	    FileNameExtensionFilter filter = 
	    		new FileNameExtensionFilter(GEConstant.SFILEKIND, GEConstant.SFILEEXTENSION);
	    chooser.setFileFilter(filter);
		return chooser;
	}
	private void open() {
		int reply = SaveOrNot();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
			JFileChooser chooser = createChooser();
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	this.currentDirectory = chooser.getSelectedFile().getParent();
		    	fileName = this.currentDirectory + "\\" + chooser.getSelectedFile().getName();
		    	this.drawingPanel.readShapes(fileName);
		    }
		}
	}	
	private void save() {
		if (drawingPanel.isUpdated() == true) {
			if (fileName == null) {
				saveAs();
			} else {
				this.drawingPanel.saveShapes(fileName);
			}
		}
	}	
	private void saveAs() {
		openDirectory();
		JFileChooser chooser = createChooser();
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.currentDirectory = chooser.getSelectedFile().getParent();
			fileName = this.currentDirectory + "\\"
					+ chooser.getSelectedFile().getName();
			if (!fileName.endsWith(GEConstant.SFILEEXTENSION)) {
				fileName = fileName + "." + GEConstant.SFILEEXTENSION;
			}
			this.drawingPanel.saveShapes(fileName);
			try {
				GEModel.save(GEConstant.SFILECONFIG, currentDirectory);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void printFile() {
	}

	private void exitSystem() {
		int reply = SaveOrNot();
		System.exit(1);		
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.newFile.getName())) {
				newFile();
			} else if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				open();
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {
				save();
			} else if(e.getActionCommand().equals(EFileMenuItem.saveAs.getName())) {
				saveAs();
			} else if(e.getActionCommand().equals(EFileMenuItem.print.getName())) {
				printFile();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				exitSystem();
			}
		}
	}	
}
