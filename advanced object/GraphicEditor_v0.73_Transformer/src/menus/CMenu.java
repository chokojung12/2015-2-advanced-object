package menus;

import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.CConstans.EFILEMENUITEM;
import frames.CDrawingPanel;





public class CMenu extends JMenu {
	private Vector<JMenuItem> menuItems; //CMenu�� �ޱ� ���� Array ����
	private ActionHandler actionHandler;
	private CDrawingPanel drawingPanel;
	
	
	
	public CMenu(String name, Enum[] menuItemNames) {
		super(name);
		this.actionHandler = new ActionHandler();
		
		menuItems = new Vector<JMenuItem>();// Array �ʱ�ȭ
		for(Enum menuItemName: menuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName.name());
			menuItem.setActionCommand(menuItemName.name());
			menuItem.addActionListener(actionHandler);
			menuItems.add(menuItem); // Array�� �߰�
			this.add(menuItem);
		}
	}
	
	public void init(CDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFILEMENUITEM.Open.getName())){
				drawingPanel.open();
			} else if(e.getActionCommand().equals(EFILEMENUITEM.Save.getName())){
				drawingPanel.save();
			}
		}		
	}	
}
