package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstant;


public class GEToolbar extends JToolBar {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private ButtonGroup buttonGroup;
	private ActionListener actionListener;
	// associations
	private GEPanel drawingPanel;
	
	public GEToolbar() {
		super();
		
		buttonGroup = new ButtonGroup();
		actionListener = new ActionHandler();
		
		for(GEConstant.EButtons eButton: GEConstant.EButtons.values()){
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon("rsc/"+eButton.getButtonImage()));
			button.setSelectedIcon(new ImageIcon("rsc/"+eButton.getSelectedButtonImage()));
			buttonGroup.add(button);
			this.add(button);
			button.addActionListener(actionListener);
			button.setActionCommand(eButton.name());
		}
	}
	
	public void init(GEPanel panel) {
		this.drawingPanel = panel;
		((JRadioButton)this.getComponent(GEConstant.EButtons.rectangle.ordinal())).doClick();;
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setCurrentTool(GEConstant.EButtons.valueOf(e.getActionCommand()).getShape());
			//System.out.println(GEConstant.EButtons.valueOf(e.getActionCommand()).getShape());
		}	
	}
}
