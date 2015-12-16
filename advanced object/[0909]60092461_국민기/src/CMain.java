import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;





public class CMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("그림판");	
		frame.setSize(400, 600);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("file");
		JMenu editMenu = new JMenu("edit");
		JMenu colorMenu = new JMenu("color");
		fileMenu.addSeparator(); //구분선 추가
		fileMenu.add(new JMenuItem("새로만들기"));
		fileMenu.add(new JMenuItem("저장하기"));
		fileMenu.add(new JMenuItem("불러오기"));
		fileMenu.add(new JMenuItem("닫기"));
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(colorMenu);
		
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
}
