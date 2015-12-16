package frames;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;




public class CMenu extends JMenu {
	private Vector<JMenuItem> menuItems; //CMenu�� �ޱ� ���� Array ����
	private ActionHandler actionHandler;
	private CDrawingPanel drawingPanel;
	//private Hashtable<String, JMenuItem> menuItems1;
	
	
	
	public CMenu(String name, String[] menuItemNames) {
		super(name);
		this.actionHandler = new ActionHandler();
		
		menuItems = new Vector<JMenuItem>();// Array �ʱ�ȭ
		for(String menuItemName: menuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName);
			menuItem.setActionCommand(menuItemName);
			menuItem.addActionListener(actionHandler);
			menuItems.add(menuItem); // Array�� �߰�
			//menuItems1.put(menuItemName, menuItem);
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
			if(e.getActionCommand()=="open"){
				drawingPanel.open();
			} else if(e.getActionCommand()=="save"){
				drawingPanel.save();
			}
		}
		
	}
	
	
}
