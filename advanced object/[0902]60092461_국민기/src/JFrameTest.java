import javax.swing.JFrame;



public class JFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("�׸���");	
		frame.setBounds(250, 250, 400, 400);// a, b, c, d  a�� b â�� ��ġ, c,d â�� ũ��
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
