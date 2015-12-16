package frames;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import menus.CClipBoard;
import shapes.CGroupManager;
import shapes.CShapeManager;
import transformer.CTransformer;
import constants.CConstans.EDrawingState;
import constants.CConstans.EDrawingType;
import constants.CConstans.ETransformationState;

public class CDrawingPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// association variable
	private CShapeManager currentTool;
	private CClipBoard clipBoard;
	// components
	private Vector<CShapeManager> shapes;
	//Utilities
	private MouseHandler mouseHandler;
	// working variable
	private EDrawingState eDrawingState;
	private CTransformer transformer;
	private CShapeManager currentShape;
	private boolean bDiirty;
	//Setters & Getters	
	public void setCurrentTool(CShapeManager tool) {this.currentTool = tool;}
	public CTransformer getTransformer() {	return transformer;	}
	public CShapeManager getCurrentShape() {	return currentShape;	}
	public void setCurrentShape(CShapeManager currentShape) {	this.currentShape=currentShape;	}
	public void setShapes(Vector<CShapeManager> shapes) {	this.shapes=shapes;	repaint();	}
	public void addShape(CShapeManager shape){ this.shapes.add(shape); }
	public Vector<CShapeManager> getShapes() {	return shapes;	}
	public CClipBoard getClipBoard(){	return clipBoard;	}
	public boolean isbDiirty() { return bDiirty; }
	public void setbDiirty(boolean bDiirty) {this.bDiirty = bDiirty;}
	
	//Cunstructor
	public CDrawingPanel() {
		//Create Instances
		shapes = new Vector<CShapeManager>();
		clipBoard = new CClipBoard();
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		//Variables Initializing
		currentTool=null;
		eDrawingState = EDrawingState.idle;
		transformer=null;
		currentShape=null;
		bDiirty=false;
	}
	
	public void init() {		
	}
	public void initPanel() {
		shapes.clear();
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		// Extends JPanel's paint
		super.paint(g);
		@SuppressWarnings("unused")
		Graphics2D graphics2D = (Graphics2D) g;
		for (CShapeManager shape: shapes) {
			if(shape.getClass().equals(CGroupManager.class)){
				Vector<CShapeManager> group = ((CGroupManager)shape).getGroup();
				for(CShapeManager member: group){
					member.draw(g);
				}
			}else shape.draw(g);
		}
	}
	public void resetSelections(CShapeManager selectedShape){
		for(CShapeManager shape: shapes){
			shape.setSelected(false);
		}
		if(selectedShape!=null){
			selectedShape.setSelected(true);
		}
		repaint();
	}
	public void repaintPanel(){
		repaint();
	}
	public CShapeManager onShape(int x, int y){
		for(CShapeManager shape : shapes){			
			if(shape.contains(x, y)){
				//currentShape = shape;
				return shape;
			}
		}
		return null;
	}
	public Graphics2D getGraphic(){
		Graphics2D g = (Graphics2D) getGraphics();
		return g;
	}
	public void initTransforming(int x, int y){
		currentShape = onShape(x, y);
		if(currentShape == null){
			resetSelections(null);
			currentShape = currentTool.clone();
	//		mouseHandler.setBClicked(true);
		} else {
			resetSelections(currentShape);
			repaint();
		}
		transformer = currentShape.geteTransformationType().newTransformer();
		transformer.setShapeManager(currentShape);
		transformer.setPanel(this);
		transformer.initTransforming(x, y);
		bDiirty=true;
	}
	public void keepTransforming(int x, int y){
		if(eDrawingState != EDrawingState.NPDrawing) {
			Graphics2D g = (Graphics2D) getGraphics();
			g.setXORMode(getBackground());
			transformer.keepTransforming(g, x, y);
			if(!currentShape.geteTransformationType().equals(ETransformationState.draw)) repaint();
		} else { currentShape.addPoint(x, y);	}
	}
	public void finishTransforming(int x, int y){
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		transformer.finishTransforming(g, x, y);
	}
	public void eraseShape(){
		Graphics2D g = (Graphics2D) getGraphics();
		g.setXORMode(getBackground());
		currentShape.draw(g);
	}
	public void setCursors(int x, int y) {
		CShapeManager shape = onShape(x, y);
		if(shape != null){
			setCursor(shape.getCursor());
		}else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public class MouseHandler implements MouseInputListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==1){
				mouse1Clicked(e);
			}else if(e.getClickCount()==2){
				mouse2Clicked(e);
			}//state Diagram
		}
		public void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				initTransforming(e.getX(), e.getY());
				if(currentTool.getEDrawingType().equals(EDrawingType.NPoint)){
					eDrawingState = EDrawingState.NPDrawing;
				}else {
					eDrawingState = EDrawingState.transforming;
				}
			} else if (eDrawingState == EDrawingState.NPDrawing) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		public void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.NPDrawing) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EDrawingState.idle){
				setCursors(e.getX(), e.getY());
			}else if(eDrawingState == EDrawingState.NPDrawing){		
				keepTransforming(e.getX(),e.getY());
			}
		}
		//private boolean bClicked;
	//	public void setBClicked(boolean bClicked){	this.bClicked = bClicked;	}
		@Override
		public void mousePressed(MouseEvent e) {	//initTransforming 하나만 부르게 refactoring 하기
			if (eDrawingState == EDrawingState.idle) {         //Origin Code for TPDrawing
				initTransforming(e.getX(), e.getY());
				if(currentTool.getEDrawingType().equals(EDrawingType.Twopoint)){
					eDrawingState = EDrawingState.TPDrawing;
				}else {
					eDrawingState = EDrawingState.transforming;
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
		//	bClicked = false;
			keepTransforming(e.getX(),e.getY());
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			finishTransforming(e.getX(), e.getY());
			eDrawingState = EDrawingState.idle;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
