package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.JButton;

import constants.CConstans;
import constants.CConstans.EBUTTON;
import shapes.CPolygon;
import shapes.CRectangle;


public class CToolbar extends JToolBar{
	//association
	private CDrawingPanel drawingPanel;
	private CRadioButton radiobutton;
	private ButtonGroup group;
	
	
	//1st phase initialization	
	public CToolbar(){
		// attributes initialization
		this.setSize(CConstans.TOOLBAR_W, CConstans.TOOLBAR_H);
		
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
		this.drawingPanel = drawingPanel;
		for(int i=0; i< this.group.getButtonCount();i++){
			CRadioButton button = (CRadioButton) this.getComponentAtIndex(i);
			button.init(drawingPanel);
		}
		//set association attribute
		CRadioButton button = (CRadioButton) this.getComponentAtIndex(EBUTTON.Rectangle.ordinal());
		button.doClick();
	}
	
}
