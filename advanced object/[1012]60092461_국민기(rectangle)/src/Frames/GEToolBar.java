package Frames;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import Constant.GEConstant;
import Constant.GEConstant.EToolbarIcons;


public class GEToolBar extends JToolBar {
	//attribute
	// components
	// association
	
	
	private static final long serialVersionUID = 1L;
	private ButtonGroup buttonGroup;
	public GEToolBar(){
		super();
		buttonGroup = new ButtonGroup();
		JRadioButton Button;
		for(EToolbarIcons Icon : EToolbarIcons.values()){
			Button = new JRadioButton();
			Button.setIcon(new ImageIcon(GEConstant.TOOLBAR_ICON_URL + Icon.toString() 
					+ GEConstant.TOOLBAR_EXTENDER));
			Button.setSelectedIcon(new ImageIcon(
									GEConstant.TOOLBAR_ICON_URL + Icon.toString()
									+ GEConstant.TOOLBAR_SLT_EXTENDER));
			buttonGroup.add(Button);
			this.add(Button);
		}
	}
}
