package menus;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import shapes.CShapeManager;
import constants.CConstants;
import constants.CConstants.EMenuItem;

public class CFileMenu extends CMenu {

	private static final long serialVersionUID = 1L;
	
	private File file;
	
	public CFileMenu(EMenuItem[] eMenuItems) {
		super(eMenuItems);
		file = null;
	}

	@SuppressWarnings("unchecked")
	private void inputStream(File file) {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			this.drawingPanel.setShapeManagers((Vector<CShapeManager>) inputStream.readObject());
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	private void outputStream(File file) {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(this.drawingPanel.getShapeManagers());
			outputStream.close();
			this.drawingPanel.setDirty(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
    private int showOpenDialog() {
        JFileChooser fileDialog = new JFileChooser(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CGraphicEditor Files", "gps");
        fileDialog.setFileFilter(filter);
        int reply = fileDialog.showOpenDialog(null);
        file = fileDialog.getSelectedFile();
        
        return reply;
    }
    private int showSaveDialog() {
		JFileChooser fileDialog = new JFileChooser(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CGraphicEditor Files", "gps");
        fileDialog.setFileFilter(filter);
        int reply = fileDialog.showSaveDialog(null);        
        file = fileDialog.getSelectedFile();
        
        return reply;
    }    
	private int askSave() {
		int reply = JOptionPane.NO_OPTION;
    	// if changed, try to save
    	if (drawingPanel.isDirty()) {
    		// ask whether to save or not
   			reply = JOptionPane.showConfirmDialog(null, 
   					CConstants.sMSG_CONFIRMSAVE, CConstants.sLABEL_CONFIRMSAVE, 
   					JOptionPane.INFORMATION_MESSAGE);
    	}
    	return reply;
	}    
	public void newFile() {
		int reply = askSave();
		if (reply == JOptionPane.CANCEL_OPTION)
			return;
		if (reply == JOptionPane.OK_OPTION)
			this.saveFile();
		this.drawingPanel.clearPanel();
	}
	public void openFile() {
		int reply = askSave();
		if (reply == JOptionPane.CANCEL_OPTION)
			return;
		if (reply == JOptionPane.OK_OPTION)
			this.saveFile();
		
		reply = this.showOpenDialog();
		if (reply==JFileChooser.CANCEL_OPTION)
			return;
		if (reply==JFileChooser.APPROVE_OPTION)
			inputStream(file);
	}
	public void saveFile() {
		int reply=JFileChooser.APPROVE_OPTION;
		if (file==null) {
			reply = this.showSaveDialog();
		} 
		if (reply==JFileChooser.APPROVE_OPTION) {
			outputStream(file);
		}
	}
	public void saveAsFile() {
		int reply = this.showSaveDialog();
		if (reply==JFileChooser.APPROVE_OPTION)
			outputStream(file);
	}
	public void closeFile() {
		this.newFile();
		file = null;
	}
	public void exitProgram() {
		System.exit(0);
	}	
}
