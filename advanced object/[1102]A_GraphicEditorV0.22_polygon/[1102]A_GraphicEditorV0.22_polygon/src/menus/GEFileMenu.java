package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import shapes.GEShape;
import constants.GEConstant;
import constants.GEConstant.EFileMenuItem;
import frames.GEPanel;

public class GEFileMenu extends JMenu {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	private GEPanel panel;
	private Vector<GEShape> shapes;

	public GEFileMenu() {	
		actionListener = new ActionHandler();
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EFileMenuItem eMenuItem : GEConstant.EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItems.add(menuItem);
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.add(menuItem);
		}
	}
	
	private void open() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("test")));
		Object obj = in.readObject(); //파일에 쓰여진 오브젝트들을 obj에 넣어줌
		GEConstant.PANEL.setShapes((Vector<GEShape>) obj);
		GEConstant.PANEL.repaint();
		
	}	
	private void save() throws FileNotFoundException, IOException {
		ObjectOutputStream outputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("test")));
		outputStream.writeObject(GEConstant.PANEL.getShapes()); //드로잉패널의 shape배열을 파일에 씀
		outputStream.close();
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				try {
					open();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {
				try {
					save();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				System.exit(1);
			}
		}
	}	
}
