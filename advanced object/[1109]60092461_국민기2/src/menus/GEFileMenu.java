package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstant;
import constants.GEConstant.EFileMenuItem;
import entity.GEModel;
import frames.GEMenu;
import frames.GEPanel;

public class GEFileMenu extends GEMenu {
	
	private static final long serialVersionUID = 1L;
	private String panelDefualt = "default.gps";
	
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
	private void saveComponent(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps"); //configuration default path가 어디인지
	    // 여기서 documents는 윈도우에서 잡은 default path 임
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String fileName = chooser.getSelectedFile().getName();
			GEModel.save(chooser.getCurrentDirectory()+"//"+fileName);
			
	    }
	}
	private void readComponent(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String fileName = chooser.getSelectedFile().getName();
	    	GEModel.read(chooser.getCurrentDirectory()+"//"+fileName);
			this.drawingPanel.repaint();	
	    }
	}
	
	public void init(GEPanel drawingPanel){
		super.init(drawingPanel);
		currentDirectory = (String)GEModel.read();
	}
	
	//open
	@SuppressWarnings("static-access")
	private void open() {
		if(GEModelShape.state == false){
			readComponent();
		}
		else{
			JOptionPane jOptionPane = new JOptionPane();
			Object message = "변경된 사항을 저장하시겠습니까??";
			String title = "Open";
			int answer;
			answer = jOptionPane.showConfirmDialog(null, message, title, jOptionPane.YES_NO_CANCEL_OPTION, jOptionPane.QUESTION_MESSAGE);
			
			if(answer == JOptionPane.OK_OPTION){
				//save
				saveComponent();
			    //read
				readComponent();
				this.drawingPanel.readShape();
				
			}
			else if(answer == JOptionPane.NO_OPTION){
				readComponent();
				
			}
			else{
				;
			}
		}	
	}
	
	//save
	@SuppressWarnings("static-access")
	private void save() {
		JOptionPane jOptionPane = new JOptionPane();
		Object message = "정말 저장하시겠습니까?";
		String title = "Save";
		int answer;
		answer = jOptionPane.showConfirmDialog(null, message, title, jOptionPane.YES_NO_CANCEL_OPTION, jOptionPane.QUESTION_MESSAGE);
		if(answer == JOptionPane.OK_OPTION){
			saveComponent();
			GEModel.state = false;
			this.drawingPanel.saveShapes(fileName);
		}
		else if(answer == JOptionPane.NO_OPTION){
			;
		}
		else{
			;
		}
	}
	
	//saveas
	private void saveAs() {
		Object message = "정말 저장하시겠습니까?";
		String title = "Save";
		int answer;
		answer = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(answer == JOptionPane.OK_OPTION){
			saveComponent();
			GEModelShape.state = false;
		}
		else if(answer == JOptionPane.NO_OPTION){
			;
		}
		else{
			;
		}
	}
	
	//newfile
	@SuppressWarnings("deprecation")
	private void newFile(){
		if(GEModel.state == false){
			GEModel.read(panelDefualt);
			this.drawingPanel.repaint();
		}
		else{
			Object message = "변경된 사항을 저장하시겠습니까??";
			String title = "New";
			int answer;
			answer = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					
			if(answer == JOptionPane.OK_OPTION){
				saveComponent();
			    GEModel.read(panelDefualt);
				this.drawingPanel.repaint();
				
			}
			else if(answer == JOptionPane.NO_OPTION){
				this.drawingPanel.removeAll(); 
				this.drawingPanel.updateUI();
				GEModel.read(panelDefualt);
				this.drawingPanel.repaint();
				
			}
			else{
				
			}
		}
		
	}
	
	private void close(){
		if(GEModelShape.state == false){
			System.exit(0);
		}
		else{
			Object message = "변경된 사항을 저장하시겠습니까??";
			String title = "Close";
			int answer;
			answer = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(answer == JOptionPane.OK_OPTION){
				//save
				saveComponent();
				System.exit(0);
			}
			else if(answer == JOptionPane.NO_OPTION){
				System.exit(0);
			}
			else{
				;
			}
		}
	}
	private void exit(){
		if(GEModelShape.state == false){
			System.exit(0);
		}
		else{
			Object message = "변경된 사항을 저장하시겠습니까??";
			String title = "Close";
			int answer;
			answer = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(answer == JOptionPane.OK_OPTION){
				//save
				saveComponent();
				System.exit(0);
			}
			else if(answer == JOptionPane.NO_OPTION){
				System.exit(0);
			}
			else{
				;
			}
		}
	}	
	
	private void print(){
		PrinterJob prnJob = PrinterJob.getPrinterJob();
		prnJob.setPrintable(this.drawingPanel);
		if (!prnJob.printDialog())
			return;
		try {
			prnJob.print();
		} 
		catch (PrinterException pe) {
			System.out.println("프린터 에러 " + pe.getMessage() );
		}
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				open();
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {
				save();
			} else if(e.getActionCommand().equals(EFileMenuItem.newFile.getName())) {
				newFile();
			} else if(e.getActionCommand().equals(EFileMenuItem.saveAs.getName())) {
				saveAs();
			} else if(e.getActionCommand().equals(EFileMenuItem.close.getName())) {
				close();
			} else if(e.getActionCommand().equals(EFileMenuItem.print.getName())) {
				print();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				exit();
			}
		}
	}	
}
