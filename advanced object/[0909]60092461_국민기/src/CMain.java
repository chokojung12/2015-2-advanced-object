import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;





public class CMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("�׸���");	
		frame.setSize(400, 600);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("file");
		JMenu editMenu = new JMenu("edit");
		JMenu colorMenu = new JMenu("color");
		fileMenu.addSeparator(); //���м� �߰�
		fileMenu.add(new JMenuItem("���θ����"));
		fileMenu.add(new JMenuItem("�����ϱ�"));
		fileMenu.add(new JMenuItem("�ҷ�����"));
		fileMenu.add(new JMenuItem("�ݱ�"));
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(colorMenu);
		
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
}
