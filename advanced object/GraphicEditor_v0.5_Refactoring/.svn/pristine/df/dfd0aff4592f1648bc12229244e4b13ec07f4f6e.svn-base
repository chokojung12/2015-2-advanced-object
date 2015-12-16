import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class CFileMenu extends JMenu {
	private JMenuItem save;
	private JMenuItem open;
	private CDrawingPanel drawingPanel;
	private ActionHandler actionHandler;
	
	public CFileMenu(String name){
		super(name);
		this.actionHandler = new ActionHandler();
		
		open = new JMenuItem("open");
		open.setActionCommand("open");
		open.addActionListener(actionHandler);
		this.add(open);
		
		save = new JMenuItem("save");
		save.setActionCommand("save");
		save.addActionListener(actionHandler);
		this.add(save);
		
	}
	
	public void init(CDrawingPanel drawingPanel){
		this.drawingPanel = drawingPanel;
	}
	
	public class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand()=="open"){
				drawingPanel.open();
			} else if(e.getActionCommand()=="save"){
				drawingPanel.save();
			}
		}
		
	}
}
