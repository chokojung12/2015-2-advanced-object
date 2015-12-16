package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
public class GEFileMenu extends JMenu {
	
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
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="exit") {
				System.exit(1);
			}
			else if(e.getActionCommand()=="print") {
				if (! constants.GEConstant.prnJob.printDialog())
					return;
				try {
					constants.GEConstant.prnJob.print();
				} 
				catch (PrinterException pe) {
					System.out.println("프린터 에러 " + pe.getMessage() );
				}
			}
		}
	}
	
}
