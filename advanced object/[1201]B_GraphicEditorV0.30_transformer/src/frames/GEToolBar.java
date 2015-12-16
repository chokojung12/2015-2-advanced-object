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
		super();
		// attributes initialization
		
		// component initialization
		actionListener = new ActionHandler();
		ButtonGroup buttonGroup = new ButtonGroup();
		
		for(EButtons eButton: EButtons.values()) {
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon("rsc/"+eButton.getIcon()));
			button.setSelectedIcon(new ImageIcon("rsc/"+eButton.getIconSLT()));
			
			button.addActionListener(actionListener);
			button.setActionCommand(eButton.name());
			
			this.add(button);
			buttonGroup.add(button);
		}
	}
	
	public void init(GEPanel drawingPanel) {
		// association initialization
		this.drawingPanel = drawingPanel; // ���⿡�� panel�� toolbar association
		
		// association attributes initialization
		((JRadioButton)this.getComponent(EButtons.rectangle.ordinal())).doClick();;	// ó���� �簢���� ���õǵ���
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setCurrentTool(EButtons.valueOf(e.getActionCommand()).getTool());
		}	
	}
}
