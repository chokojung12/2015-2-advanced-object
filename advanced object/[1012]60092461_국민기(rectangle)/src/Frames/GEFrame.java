package Frames;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Constant.GEConstant;


public class GEFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 부모가 자식을 관리하기위해서는 갖고있는게 좋다.
	//private Vector <JComponent> components;
	//private JMenubar menubar JFrame이 component가 없다면 이렇게 하라 + get, set 정의
	
	//attributes
	
	// components
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JPanel panel;
	
	//associations
	
	
	//working variables(ex, i 같은)
	
	public GEFrame(){
		//this.components = new Vector <JComponent>(); 이런식으로 해서 vector로 넣음
		//but JFrame에서 이미 다 미리 정의돼있으므로 set, get으로 갖다쓰면됨
		//GE
		
		//attributes initialization
		this.setSize(GEConstant.FRAME_WIDTH, GEConstant.FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// components lifecycle management
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);			// GEFrame객체, JFrame 상속, 
		
		toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
	}
}
