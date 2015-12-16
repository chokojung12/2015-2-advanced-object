package frames;

import javax.swing.JToolBar;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;

public class GEToolBar extends JToolBar {
	private ButtonGroup buttonGroup;
	public GEToolBar(String label){
		super(label);
		buttonGroup = new ButtonGroup();
		JRadioButton rButton;
		for(EToolBarButtons btn : EToolBarButtons.values()){
			rButton = new JRadioButton();
			rButton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() 
					+ GEConstants.TOOLBAR_BTN));//"images/select.gif"
			rButton.setSelectedIcon(new ImageIcon(
									GEConstants.IMG_URL + btn.toString()
									+ GEConstants.TOOLBAR_BTN_SLT));
			buttonGroup.add(rButton);
			this.add(rButton);
		}
	}

}



