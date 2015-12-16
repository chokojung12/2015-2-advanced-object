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
	
	// �θ� �ڽ��� �����ϱ����ؼ��� �����ִ°� ����.
	//private Vector <JComponent> components;
	//private JMenubar menubar JFrame�� component�� ���ٸ� �̷��� �϶� + get, set ����
	
	//attributes
	
	// components
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JPanel panel;
	
	//associations
	
	
	//working variables(ex, i ����)
	
	public GEFrame(){
		//this.components = new Vector <JComponent>(); �̷������� �ؼ� vector�� ����
		//but JFrame���� �̹� �� �̸� ���ǵ������Ƿ� set, get���� ���پ����
		//GE
		
		//attributes initialization
		this.setSize(GEConstant.FRAME_WIDTH, GEConstant.FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// components lifecycle management
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);			// GEFrame��ü, JFrame ���, 
		
		toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
	}
}
