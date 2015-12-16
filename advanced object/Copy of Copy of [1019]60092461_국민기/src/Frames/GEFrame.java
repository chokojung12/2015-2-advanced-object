package Frames;
import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GEFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// �θ� �ڽ��� �����ϱ����ؼ��� �����ִ°� ����.
	//private Vector <JComponent> components;
	//private JMenubar menubar JFrame�� component�� ���ٸ� �̷��� �϶� + get, set ����
	private GEMenuBar menuBar;
	private GEToolBar toolBar;
	private GEPanel panel;
	
	public GEFrame(){
		//this.components = new Vector <JComponent>(); �̷������� �ؼ� vector�� ����
		//but JFrame���� �̹� �� �̸� ���ǵ������Ƿ� set, get���� ���پ����
		//GE
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);			// GEFrame��ü, JFrame ���, 
		
		toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
		
		toolBar.init(panel);
	}
}
