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
	private Vector<String> buttonImageIcon = {};
	private Vector<String> selectButtonImageIcon = {};
	
	public GEToolBar(){	
		this.setSize(400, 100);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttons = new Vector<JRadioButton>();
		
		
		JRadioButton button = new JRadioButton();
		JRadioButton button2 = new JRadioButton();
		JRadioButton button3 = new JRadioButton();
		
		for(){
		button.setIcon(new ImageIcon ("rsc/line.jpg"));
		button.setSelectedIcon(new ImageIcon("rsc/lineSLT.jpg"));
		this.add(button);
		buttonGroup.add(button);
		}
		
	}
}
