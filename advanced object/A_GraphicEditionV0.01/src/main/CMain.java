package main;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;



public class CMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new GEFrame();	
		frame.setSize(400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		/*
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("파일");
		fileMenu.addSeparator(); //구분선 추가
		fileMenu.add(new JMenuItem("새로만들기"));
		fileMenu.add(new JMenuItem("저장하기"));
		fileMenu.add(new JMenuItem("불러오기"));
		fileMenu.add(new JMenuItem("닫기"));
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);*/
	}
}
