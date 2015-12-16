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
	
	// 부모가 자식을 관리하기위해서는 갖고있는게 좋다.
	//private Vector <JComponent> components;
	//private JMenubar menubar JFrame이 component가 없다면 이렇게 하라 + get, set 정의
	
	public GEFrame(){
		//this.components = new Vector <JComponent>(); 이런식으로 해서 vector로 넣음
		//but JFrame에서 이미 다 미리 정의돼있으므로 set, get으로 갖다쓰면됨
		//GE
		JMenuBar menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);			// GEFrame객체, JFrame 상속, 
		
		JToolBar toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		
		JPanel panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
	}
}
