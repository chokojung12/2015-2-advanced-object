package main;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import main.GEConstants.EToolbarIcons;

public class GEToolBar extends JToolBar {

	private ButtonGroup buttonGroup;
	public GEToolBar(){
		super();
		buttonGroup = new ButtonGroup();
		JRadioButton Button;
		for(EToolbarIcons Icon : EToolbarIcons.values()){
			Button = new JRadioButton();
			Button.setIcon(new ImageIcon(GEConstants.TOOLBAR_ICON_URL + Icon.toString() 
					+ GEConstants.TOOLBAR_EXTENDER));
			Button.setSelectedIcon(new ImageIcon(
									GEConstants.TOOLBAR_ICON_URL + Icon.toString()
									+ GEConstants.TOOLBAR_SLT_EXTENDER));
			buttonGroup.add(Button);
			this.add(Button);
		}
	}
}
