package menus;

import java.awt.FileDialog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JOptionPane;

import shapes.CShapeManager;
import constants.CConstans;
import constants.CConstans.EMenuItem;

public class CFileMenu extends CMenu {
	private static final long serialVersionUID = 1L;
	private File currentFile;
	
	public CFileMenu(EMenuItem[] eMenuItems){
		super(eMenuItems);
		currentFile = null;
	}
	public void newFile() {
		if(drawingPanel.isbDiirty()){
			String message="File has changed.\n Do you want create new file without save?";
			String title="Create new file without save?";
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
			switch(reply){
			case JOptionPane.YES_OPTION:
				drawingPanel.initPanel();
				break;
			case JOptionPane.NO_OPTION:
				saveAs();
				drawingPanel.initPanel();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
		else drawingPanel.initPanel();
	}
	public void open() {
		File file = setFile("Open", FileDialog.LOAD);
		inputStream(file);
	}
	public void save() {
		if(drawingPanel.isbDiirty()){
			File file = currentFile;
			if(currentFile==null){	file = setFile("Save", FileDialog.SAVE);	}
			outputStream(file);
			drawingPanel.setbDiirty(false);
		}
		else saveAs();
	}
	public void saveAs() {
		File file = setFile("Save-as", FileDialog.SAVE);	
		outputStream(file);
		drawingPanel.setbDiirty(false);
	}
	public void close() {
		if(drawingPanel.isbDiirty()){
			String message="File has changed.\n Do you want close without save?";
			String title="Close without save?";
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
			switch(reply){
			case JOptionPane.YES_OPTION:
				drawingPanel.setbDiirty(false);
				newFile();
				break;
			case JOptionPane.NO_OPTION:
				saveAs();
				drawingPanel.setbDiirty(false);
				newFile();
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
	}
	public void exit() {
		if(drawingPanel.isbDiirty()){
			String message="File has changed.\n Do you want exit without save?";
			String title="Exit without save?";
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
			switch(reply){
			case JOptionPane.YES_OPTION:
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				saveAs();
				System.exit(0);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
			}
		}
	}
/*	private int showOpenDialog(){
		JFileChooser fileChooser = new JFileChooser(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GraphicsEditor", "gps");
		fileChooser.setFileFilter(filter);
		int reply = fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		return reply;
	}
	private int showSaveDialog(){
		JFileChooser fileChooser = new JFileChooser(new File("."));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("GraphicsEditor", "gps");
		fileChooser.setFileFilter(filter);
		int reply = fileChooser.showSaveDialog(null);
		File file = fileChooser.getSelectedFile();
		return reply;
	}*/
	@SuppressWarnings("unused")
	private File setFile(String title, int mode){
		//JFileChooser fileChooser = new JFileChooser();
		//int reply = fileChooser.showOpenDialog(null);
		//File file = fileChooser.getSelectedFile();
		
		FileDialog dialog = new FileDialog(frame, title, mode);
		dialog.setFile("*.*");
		dialog.setFilenameFilter(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				if(name.contains(".grp")){
					return true;
				}
				return false;
			}
		});
		dialog.setVisible(true);
		String filename=dialog.getDirectory()+dialog.getFile();
		if(filename==null){
			filename=CConstans.DEFAULTFILENAME;
		}
		return new File(filename);
	}
	private void outputStream(File file){
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(file)));
			outputStream.writeObject(drawingPanel.getShapes());
			outputStream.close();
			currentFile = file;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	private void inputStream(File file){
		try {
			ObjectInputStream inputStream = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(file)));
			drawingPanel.setShapes((Vector<CShapeManager>) inputStream.readObject());
			inputStream.close();
			currentFile = file;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}