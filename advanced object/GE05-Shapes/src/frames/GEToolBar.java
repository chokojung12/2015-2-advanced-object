package frames;

import javax.swing.JToolBar;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import constants.GEConstants;
import constants.GEConstants.EToolBarButtons;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GEToolBar extends JToolBar {
	
	private ButtonGroup buttonGroup;
	private GEToolBarHandler shapeToolBarHandler;
	private GEDrawingPanel drawingPanel;
	
	public GEToolBar(String label){
		super(label);
		
		shapeToolBarHandler = new GEToolBarHandler();
		
		buttonGroup = new ButtonGroup();
		JRadioButton rButton;
		for(EToolBarButtons btn : EToolBarButtons.values()){
			rButton = new JRadioButton();
			rButton.setIcon(new ImageIcon(GEConstants.IMG_URL + btn.toString() 
					+ GEConstants.TOOLBAR_BTN));
			rButton.setSelectedIcon(new ImageIcon(GEConstants.IMG_URL + 
					btn.toString() + GEConstants.TOOLBAR_BTN_SLT));
			rButton.addActionListener(shapeToolBarHandler);
			rButton.setActionCommand(btn.toString());
			buttonGroup.add(rButton);
			this.add(rButton);
		}		
	}
	
	public void init(GEDrawingPanel dp){
		this.drawingPanel = dp;
		clickDefaultButton();
	}
	
	private void clickDefaultButton(){
		JRadioButton rButton = (JRadioButton)this.getComponent(
					EToolBarButtons.Rectangle.ordinal());
		rButton.doClick();
	}
	
	private class GEToolBarHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JRadioButton button = (JRadioButton)e.getSource();
			if(button.getActionCommand().equals(              
	                EToolBarButtons.Rectangle.name())){         
	        drawingPanel.setSelectShape(EToolBarButtons.Rectangle);   
	      }else if(button.getActionCommand().equals(            
	                EToolBarButtons.Ellipse.name())){              
	        drawingPanel.setSelectShape(EToolBarButtons.Ellipse);   
	      }else if(button.getActionCommand().equals(            
	                EToolBarButtons.Line.name())){                    
	        drawingPanel.setSelectShape(EToolBarButtons.Line);      
	      }                               
		}
	}
}



