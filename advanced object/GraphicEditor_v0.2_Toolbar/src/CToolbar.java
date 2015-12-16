import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JButton;


public class CToolbar extends JToolBar {
		
	private JButton rectangleButton;
	private JButton ellipseButton;
	private JButton lineButton;
	
	public CToolbar(){
		super();
		//this.getParent().getSize().width
		this.setSize(400, 40);
		
		rectangleButton = new JButton();
		rectangleButton.setIcon(new ImageIcon("img/rectangle.png"));
		//rectangleButton.imageUpdate("rectangle_push.png", infoflags, x, y, w, h)
		this.add(rectangleButton);
		ellipseButton = new JButton("Ellipse");
		this.add(ellipseButton);
		lineButton = new JButton("Line");
		this.add(lineButton);
	}
	
}