package frames;
import javax.swing.ButtonGroup;
import javax.swing.JToolBar;

import constants.CConstans;
import constants.CConstans.EBUTTON;


public class CToolbar extends JToolBar{
	private static final long serialVersionUID = 1L;
	//association
//	private CDrawingPanel drawingPanel;
	private CRadioButton radiobutton;
	private ButtonGroup group;
	
	
	//1st phase initialization	
	public CToolbar(){
		// attributes initialization
		this.setSize(CConstans.TOOLBAR_W, CConstans.TOOLBAR_H);
		this.setFloatable(true);
		this.setFocusable(true);
		
		//components initialization
		group = new ButtonGroup();
		for(EBUTTON eButton: EBUTTON.values()){
			radiobutton = new CRadioButton(eButton.name(), eButton.geticonname(), eButton.getselectedIconName());
			this.add(radiobutton);
			group.add(radiobutton);
		}		
		
	}
	
	//2nd phase initialization
	public void init(CDrawingPanel drawingPanel){
	//	this.drawingPanel = drawingPanel;
		for(int i=0; i< this.group.getButtonCount();i++){
			CRadioButton button = (CRadioButton) this.getComponentAtIndex(i);
			button.init(drawingPanel);
		}
		//set association attribute
		CRadioButton button = (CRadioButton) this.getComponentAtIndex(EBUTTON.Rectangle.ordinal());
		button.doClick();
	}
	
}
