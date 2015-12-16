package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;

import shapes.CEllipse;
import shapes.CRectangle;
import frames.CConstans.EBUTTON;


public class CToolbar extends JToolBar{
	//assosiation
	private CDrawingPanel drawingPanel;
	
	
	//components
	private ActionHandler actionHandler;
	private Vector<JButton> buttonItems; //CToolbar�� �ޱ� ���� Array ����
	private Vector<ImageIcon> iconItems; //ImageIcon�� Array ����
	private Vector<ImageIcon> iconItems_s; //�������� Array	
	
	//1st phase initialization	
	public CToolbar(){
		// attributes initialization
		super();
		this.setSize(CConstans.TOOLBAR_W, CConstans.TOOLBAR_H);
		
		//components initialization
		this.actionHandler = new ActionHandler();
		this.iconItems = new Vector<ImageIcon>();// Array �ʱ�ȭ
		this.iconItems_s = new Vector<ImageIcon>();// Array �ʱ�ȭ
		this.buttonItems = new Vector<JButton>();// Array �ʱ�ȭ
		for(EBUTTON eButton: CConstans.EBUTTON.values()){
			JButton buttonItem = new JButton();
			ImageIcon buttonIcon = new ImageIcon(eButton.getfileName());
			this.iconItems.add(buttonIcon);
			buttonItem.setIcon(buttonIcon);
			ImageIcon buttonIcon_s = new ImageIcon(eButton.getfileName2());
			this.iconItems_s.add(buttonIcon_s);
			buttonItem.addActionListener(actionHandler);
			buttonItem.setActionCommand(eButton.getActionCommand());
			this.buttonItems.add(buttonItem); // Array�� �߰�
			this.add(buttonItem);
		}		
		
	}
	
	//2nd phase initialization
	public void init(CDrawingPanel drwawingPanel){
		this.drawingPanel = drwawingPanel;
	}
	
	public void changeIcon(JButton b, String name){
		
		for(int j = 0; j < buttonItems.size();j++){
			buttonItems.get(j).setIcon(iconItems.get(j));
		}
		b.setIcon(iconItems_s.get(EBUTTON.valueOf(name).ordinal()));
		
	}
	
	public class ActionHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Rectangle")){
				drawingPanel.setCurrentShape(new CRectangle());
			}else if(e.getActionCommand().equals("Rectangle")){
				drawingPanel.setCurrentShape(new CEllipse());
			}else if(e.getActionCommand().equals("Rectangle")){
				drawingPanel.setCurrentShape(new CLine());
			} 
				
			
			changeIcon((JButton) e.getSource(),e.getActionCommand());
			
		}
	}
	
}
