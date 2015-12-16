package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.CConstans.EMenuItem;
import frames.CDrawingPanel;
import frames.CFrame;





public class CMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	protected ActionHandler actionHandler;
	protected CDrawingPanel drawingPanel;
	protected CFrame frame;
	
	
	
	public CMenu(EMenuItem[] menuItemNames){
		this.actionHandler = new ActionHandler();
		
		for(EMenuItem menuItemName: menuItemNames){
			JMenuItem menuItem = new JMenuItem(menuItemName.getName());
			menuItem.setActionCommand(menuItemName.getNames());
			menuItem.addActionListener(actionHandler);
			this.add(menuItem);
		}
	}
	
	public void init(CDrawingPanel drawingPanel, CFrame frame){
		this.drawingPanel = drawingPanel;
		this.frame = frame;
	}
	
	private void invokedMethod(String name){
		try {
			this.getClass().getMethod(name).invoke(this);
		} catch (Exception e) {e.printStackTrace();	}
	}	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			invokedMethod(e.getActionCommand());
		}
	
	}	
}
