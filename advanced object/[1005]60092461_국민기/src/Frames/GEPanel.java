package Frames;

import java.awt.Color;
import java.awt.Graphics; // Graphics는 그림 도구( 물감, 붓, 도형, 등등, OS에서 기본적인것들은 갖고옴)
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel; // JPanel을 호출하면서 이미 default setting이 됨, 도화지 
import javax.swing.event.MouseInputAdapter;

import Constant.GEConstant.EToolBarIcons;
import Draw.GECircle;
import Draw.GELine;
import Draw.GERectangle;


public class GEPanel extends JPanel {
	private EToolBarIcons selectShape;
	private GERectangle rectangle;
	private GELine line;
	private GECircle ellipse;
	private MouseDrawingHandler drawingHandler;
	
	private MouseListener mouseListener;
	
	public GEPanel(){
		super();
		drawingHandler = new MouseDrawingHandler();
		this.addMouseListener(drawingHandler);
		this.addMouseMotionListener(drawingHandler);
	}
	

	private void initDraw(Point startP){
		if(selectShape == EToolBarIcons.Rectangle){     
			rectangle = new GERectangle();            
			rectangle.initDraw(startP);              
		}else if(selectShape == EToolBarIcons.Circle){   
			ellipse = new GECircle();              
			ellipse.initDraw(startP);             
		}else if(selectShape == EToolBarIcons.Line){      
			line = new GELine();                
			line.initDraw(startP);    
		}
	}
	private void animateDraw(Point currentP){
		Graphics2D g2D = (Graphics2D)getGraphics();      
		g2D.setXORMode(g2D.getBackground());
		
		if(selectShape == EToolBarIcons.Rectangle){      
			g2D.draw(rectangle.getRectangle());          
			rectangle.setCoordinate(currentP);           
			g2D.draw(rectangle.getRectangle());          
		}else if(selectShape == EToolBarIcons.Circle){    
			g2D.draw(ellipse.getEllipse());            
			ellipse.setCoordinate(currentP);           
			g2D.draw(ellipse.getEllipse());            
		}else if(selectShape == EToolBarIcons.Line){       
			g2D.draw(line.getLine());              
			line.setCoordinate(currentP);            
			g2D.draw(line.getLine());              
		}
	}

	public void setSelectShape(EToolBarIcons selectShape){
		this.selectShape = selectShape;
	}
	
	private class MouseDrawingHandler extends MouseInputAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			animateDraw(e.getPoint());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			initDraw(e.getPoint());
		}
		
	}
	
}
