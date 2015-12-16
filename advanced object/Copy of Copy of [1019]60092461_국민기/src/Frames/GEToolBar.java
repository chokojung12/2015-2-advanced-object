package Frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import Constant.GEConstant;
import Constant.GEConstant.EToolBarIcons;
import Shapes.GEEllipse;
import Shapes.GELine;
import Shapes.GEPolygon;
import Shapes.GERectangle;


public class GEToolBar extends JToolBar {

	private ButtonGroup buttonGroup;
	private GEToolBarHandler shapeToolBarHandler;
	private GEPanel drawingPanel;
	
	public GEToolBar(){
		super();
		
		shapeToolBarHandler = new GEToolBarHandler();
		
		buttonGroup = new ButtonGroup();
		JRadioButton Button;
		for(EToolBarIcons Icon : EToolBarIcons.values()){
			Button = new JRadioButton();
			Button.setIcon(new ImageIcon(GEConstant.TOOLBAR_ICON_URL + Icon.toString() 
					+ GEConstant.TOOLBAR_EXTENDER));
			Button.setSelectedIcon(new ImageIcon(
									GEConstant.TOOLBAR_ICON_URL + Icon.toString()
									+ GEConstant.TOOLBAR_SLT_EXTENDER));
			Button.addActionListener(shapeToolBarHandler);
			Button.setActionCommand(Icon.toString());
			buttonGroup.add(Button);
			this.add(Button);
		}
	}
	
	public void init(GEPanel dp){
		this.drawingPanel = dp;
		clickDefaultButton();
	}
	
	private void clickDefaultButton(){
		JRadioButton rButton = (JRadioButton)this.getComponent(
					EToolBarIcons.Rectangle.ordinal());
		rButton.doClick();
	}
	
	private class GEToolBarHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JRadioButton button = (JRadioButton)e.getSource();
			if(button.getActionCommand().equals(              
					EToolBarIcons.Rectangle.name())){         
	        drawingPanel.setCurrentShape(new GERectangle());
	      }else if(button.getActionCommand().equals(            
	    		  EToolBarIcons.Ellipse.name())){              
	        drawingPanel.setCurrentShape(new GEEllipse());
	      }else if(button.getActionCommand().equals(            
	    		  EToolBarIcons.Line.name())){                    
	        drawingPanel.setCurrentShape(new GELine());     
	      }else if(button.getActionCommand().equals(            
	    		  EToolBarIcons.Polygon.name())){                    
	        drawingPanel.setCurrentShape(new GEPolygon());     
	      }                                
		}
	}
}
