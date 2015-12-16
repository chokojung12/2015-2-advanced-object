package Frames;

import javax.swing.JFrame;

import Constant.GEConstant;



public class CMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		JFrame frame = new GEFrame();	
		frame.setSize(GEConstant.FRAME_WIDTH, GEConstant.FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
