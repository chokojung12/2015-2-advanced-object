package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import constants.CConstans.EBUTTON;

public class CRadioButton extends JRadioButton {
	private static final long serialVersionUID = 1L;
	//components
	private ActionHandler actionHandler;
	private CDrawingPanel drawingPanel;
	
	//Constructor
	public CRadioButton(String name, String icon, String selectedIcon){
		//set Association
		this.actionHandler = new ActionHandler(); //자식(components)가 공유
		
		//set Attribute
		this.setIcon(new ImageIcon(icon));
		this.setSelectedIcon(new ImageIcon(selectedIcon));
		this.addActionListener(actionHandler);
		this.setActionCommand(name);
	}
	
	public void init(CDrawingPanel drawingPanel){
		//set Association
		this.drawingPanel = drawingPanel;
		
	}
	
	public class ActionHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			drawingPanel.setCurrentTool(EBUTTON.valueOf(e.getActionCommand()).newTool());
			
		}
	}
}
