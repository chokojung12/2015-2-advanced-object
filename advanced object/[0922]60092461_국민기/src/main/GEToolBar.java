package main;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GEToolBar extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<JRadioButton> buttons;
	public GEToolBar(){	
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttons = new Vector<JRadioButton>();
		this.setSize(400, 100);
		JRadioButton button = new JRadioButton();
		JRadioButton button2 = new JRadioButton();
		JRadioButton button3 = new JRadioButton();
		
		button.setIcon(new ImageIcon ("rsc/line.jpg"));
		button.setSelectedIcon(new ImageIcon("rsc/lineSLT.jpg"));
		this.add(button);
		buttonGroup.add(button);
		
		button2.setIcon(new ImageIcon ("rsc/rectangle.jpg"));
		button2.setSelectedIcon(new ImageIcon("rsc/rectangleSLT.jpg"));
		this.add(button2);
		buttonGroup.add(button2);
		
		button3.setIcon(new ImageIcon ("rsc/pentagon.jpg"));
		button3.setSelectedIcon(new ImageIcon("rsc/pentagonSLT.jpg"));
		this.add(button3);
		buttonGroup.add(button3);


	}
}
