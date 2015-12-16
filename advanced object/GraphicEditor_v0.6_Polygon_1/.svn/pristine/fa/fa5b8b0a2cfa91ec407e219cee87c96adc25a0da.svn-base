import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;


public class CToolbar extends JToolBar{
	//assosiation
	private ActionHandler actionHandler;
	private CDrawingPanel drawingPanel;
	
	public CDrawingPanel getDrawingPanel() {
		return drawingPanel;
	}
	public void setDrawingPanel(CDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	
	//components
	private Vector<JButton> buttonItems; //CToolbar에 달기 위해 Array 생성
	private Vector<ImageIcon> iconItems; //ImageIcon용 Array 생성
	private Vector<ImageIcon> iconItems_s; //눌렀을때 Array	
	private int selected=1;
	
		
	public CToolbar(){
		super();
		//this.getParent().getSize().width
		this.setSize(400, 40);
		actionHandler = new ActionHandler();
		
		iconItems = new Vector<ImageIcon>();// Array 초기화
		for(String itemIcon: CConstans.iconNames){
			ImageIcon buttonIcon = new ImageIcon(itemIcon);
			iconItems.add(buttonIcon);
		}
		
		iconItems_s = new Vector<ImageIcon>();// Array 초기화
		for(String itemIcon_s: CConstans.selectedIconNames){
			ImageIcon buttonIcon_s = new ImageIcon(itemIcon_s);
			iconItems_s.add(buttonIcon_s);
		}
		
		buttonItems = new Vector<JButton>();// Array 초기화
		for(int i = 0; i < iconItems.size(); i++){
			JButton buttonItem = new JButton();
			buttonItem.setIcon(iconItems.get(i));
			buttonItem.addActionListener(actionHandler);
			buttonItem.setActionCommand(CConstans.buttonNames[i]);
			buttonItems.add(buttonItem); // Array에 추가
			this.add(buttonItem);
		}
		
	}
	
	public class ActionHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getActionCommand().equals(CConstans.buttonNames[0])){
				selected=0;
				drawingPanel.setCurrentShape(CConstans.buttonNames[0]);
				changeIcon(buttonItems.get(0), selected);
				
			}
			if(e.getActionCommand().equals(CConstans.buttonNames[1])){
				selected=1;
				drawingPanel.setCurrentShape(CConstans.buttonNames[1]);
				changeIcon(buttonItems.get(1), selected);
			}
			if(e.getActionCommand().equals(CConstans.buttonNames[2])){
				selected=2;
				drawingPanel.setCurrentShape(CConstans.buttonNames[2]);
				changeIcon(buttonItems.get(2), selected);
			}
			
		}
	}
	public void changeIcon(JButton b, int i){
		
		for(int j = 0; j < buttonItems.size();j++){
			buttonItems.get(j).setIcon(iconItems.get(j));
		}
		b.setIcon(iconItems_s.get(i));
		
	}
	
}
