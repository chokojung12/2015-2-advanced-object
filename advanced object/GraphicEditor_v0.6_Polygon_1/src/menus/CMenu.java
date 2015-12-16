package menus;

import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frames.CDrawingPanel;
import Libs.CConstans.EFILEMENUITEM;





public class CMenu extends JMenu {
	private Vector<JMenuItem> menuItems; //CMenu에 달기 위해 Array 생성
	private ActionHandler actionHandler;
	private CDrawingPanel drawingPanel;
	
	
	
	public CMenu(String name, Vector<String> menuItemNames) {
		super(name);
		this.actionHandler = new ActionHandler();
		
		menuItems = new Vector<JMenuItem>();// Array 초기화
		for(String menuItemName: menuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName);
			menuItem.setActionCommand(menuItemName);
			menuItem.addActionListener(actionHandler);
			menuItems.add(menuItem); // Array에 추가
			this.add(menuItem);
		}
	}
	
	public void init(CDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals(EFILEMENUITEM.Open.getName())){
				drawingPanel.open();
			} else if(e.getActionCommand().equals(EFILEMENUITEM.Save.getName())){
				drawingPanel.save();
			}
		}
		
	}
	
	
}
