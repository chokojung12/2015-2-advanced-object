package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import Libs.CConstans.EBUTTON;

public class CRadioButton extends JRadioButton {
	//components
	private ActionHandler actionHandler;
	private CDrawingPanel drawingPanel;
	
	//Constructor
	public CRadioButton(String name, String icon, String selectedIcon){
		this.actionHandler = new ActionHandler(); //자식(components)가 공유
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
