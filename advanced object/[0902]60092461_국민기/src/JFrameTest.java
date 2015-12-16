import javax.swing.JFrame;



public class JFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("그림판");	
		frame.setBounds(250, 250, 400, 400);// a, b, c, d  a와 b 창의 위치, c,d 창의 크기
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
