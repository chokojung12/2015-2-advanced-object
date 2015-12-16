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
	
	//���̾� �α�â ����
	private void open() {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps"); //gps�� Ȯ����, graphic editor�� description
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String fileName = chooser.getSelectedFile().getName();
			GEModelShape.read(chooser.getCurrentDirectory()+"\\"+fileName);
	    }
	}
	
	private void save() {
		Object question = "���� �����Ͻðڽ��ϱ�?";
		String title = "Save";
		int answer;
		answer = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(answer == JOptionPane.CLOSED_OPTION  ||answer == JOptionPane.CANCEL_OPTION ){
			;
		}
		else if(answer == JOptionPane.YES_OPTION ){
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showSaveDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	String fileName = chooser.getSelectedFile().getName();
				GEModelShape.save(chooser.getCurrentDirectory()+"\\"+fileName);		//���õ� ��ο� ���� ����
		    }
		}
		else if(answer == JOptionPane.NO_OPTION ){
			;
		}
	}
	private void newFile(){
		/*if(state.equals(GEModelShape.shapes)){
			String defualt = ".//default.gps";
			GEModelShape.read(defualt);
			this.drawingPanel.repaint();
		}
		else{
			Object question = "����� ������ �����Ͻðڽ��ϱ�?";
			String title = "New";
			int answer;
			answer = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				
			if(answer == JOptionPane.CLOSED_OPTION  ||answer == JOptionPane.CANCEL_OPTION ){
				;
			}
			else if(answer == JOptionPane.YES_OPTION ){
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showSaveDialog(null);
				
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				    String fileName = chooser.getSelectedFile().getName();
					GEModelShape.save(chooser.getCurrentDirectory()+"\\"+fileName);		//���õ� ��ο� ���� ����
					this.drawingPanel.repaint();
				}
			}
			else if(answer == JOptionPane.NO_OPTION ){
					this.drawingPanel.repaint();
			}
		}*/
	}
	private void saveAs(){
		Object question = "���� �����Ͻðڽ��ϱ�?";
		String title = "Save";
		int answer;
		answer = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(answer == JOptionPane.CLOSED_OPTION  ||answer == JOptionPane.CANCEL_OPTION ){
			;
		}
		else if(answer == JOptionPane.YES_OPTION ){
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showSaveDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	String fileName = chooser.getSelectedFile().getName();
				GEModelShape.save(chooser.getCurrentDirectory()+"\\"+fileName);		//���õ� ��ο� ���� ����
		    }
		}
	}
	private void close(){
		
		Object question = "����� ������ �����Ͻðڽ��ϱ�?";
		String title = "Close";
		int answer;
		answer = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(answer == JOptionPane.CLOSED_OPTION  ||answer == JOptionPane.CANCEL_OPTION ){
			;
		}
		else if(answer == JOptionPane.YES_OPTION ){
			save();
			newFile();
		}
		else if(answer == JOptionPane.NO_OPTION ){
			newFile();
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
			System.out.println("������ ���� " + pe.getMessage() );
		}
	}
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.newFile.getName())) {
				newFile();
			}
			else if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				open();
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {
				save();
			} else if(e.getActionCommand().equals(EFileMenuItem.saveAs.getName())) {
				saveAs();
			} else if(e.getActionCommand().equals(EFileMenuItem.close.getName())) {
				close();
			}  else if(e.getActionCommand().equals(EFileMenuItem.print.getName())) {
				print();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				System.exit(1);
			}
		}
	}	
}
