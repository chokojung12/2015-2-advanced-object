package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstant.EButtons;


public class GEToolBar extends JToolBar {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	private ActionListener actionListener;
	// associations
	private GEPanel drawingPanel;

	public GEToolBar() {
		// attributes initialization
		
		// component initialization
		actionListener = new ActionHandler();
		ButtonGroup buttonGroup = new ButtonGroup();
		for(EButtons eButton: EButtons.values()) {
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon(eButton.getIcon()));
			button.setSelectedIcon(new ImageIcon(eButton.getIconSLT()));
			this.add(button);
			button.addActionListener(actionListener);
			button.setActionCommand(eButton.name());
			buttonGroup.add(button);
		}
	}
	
	public void init(GEPanel drawingPanel) {
		// association initialization
		this.drawingPanel = drawingPanel;
		// working variables initialization
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}	
	}
}
