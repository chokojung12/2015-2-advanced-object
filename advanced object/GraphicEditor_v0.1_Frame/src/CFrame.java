import javax.swing.JFrame;


public class CFrame extends JFrame {
	private CMenuBar menuBar;
	
	public CFrame(){
		menuBar = new CMenuBar();
		this.setJMenuBar(menuBar);
	}
}