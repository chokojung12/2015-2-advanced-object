package Frames;
import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class GEFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// �θ� �ڽ��� �����ϱ����ؼ��� �����ִ°� ����.
	//private Vector <JComponent> components;
	//private JMenubar menubar JFrame�� component�� ���ٸ� �̷��� �϶� + get, set ����
	
	public GEFrame(){
		//this.components = new Vector <JComponent>(); �̷������� �ؼ� vector�� ����
		//but JFrame���� �̹� �� �̸� ���ǵ������Ƿ� set, get���� ���پ����
		//GE
		JMenuBar menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);			// GEFrame��ü, JFrame ���, 
		
		JToolBar toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		JPanel panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
	}
}
