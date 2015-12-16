package menus;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JOptionPane;

import shapes.CGroupManager;
import shapes.CSelectionManager;
import shapes.CShapeManager;
import constants.CConstans.EMenuItem;

public class CEditMenu extends CMenu {
	private static final long serialVersionUID = 1L;
	public CEditMenu(EMenuItem[] eMenuItems){
		super(eMenuItems);
	}
	
	private void moveClip(CShapeManager clip){
		Graphics2D g2D = drawingPanel.getGraphic();
		AffineTransform affineTransform = new AffineTransform();
		AffineTransform saveAT = g2D.getTransform();
		affineTransform.setToTranslation(10, 10);
		if(clip.getClass().equals(CGroupManager.class)){
			for(CShapeManager member: ((CGroupManager)clip).getGroup()){
				member.setShape(affineTransform.createTransformedShape(member.getShape()));
				//member.draw(g2D);
			}
		} else {
			clip.setShape(affineTransform.createTransformedShape(clip.getShape()));
			//clip.draw(g2D);
			}
		g2D.setTransform(saveAT);
	}
	public void cut(){
		Vector<CShapeManager> buffer = drawingPanel.getShapes();
		drawingPanel.getClipBoard().addClip(buffer);
		drawingPanel.getClipBoard().setBuffer(drawingPanel.getCurrentShape());
		buffer.remove(drawingPanel.getCurrentShape());
		drawingPanel.setShapes(buffer);
		drawingPanel.setbDiirty(true);
		drawingPanel.repaint();
	}
	public void copy(){
		drawingPanel.getClipBoard().setBuffer(drawingPanel.getCurrentShape());
	}
	public void paste(){
		Vector<CShapeManager> buffer = drawingPanel.getShapes();
		drawingPanel.getClipBoard().addClip(buffer);
		CShapeManager clip = drawingPanel.getClipBoard().getBuffer();
		moveClip(clip);
		buffer.add(clip);
		drawingPanel.setShapes(buffer);
		drawingPanel.setCurrentShape(drawingPanel.getShapes().lastElement());
		drawingPanel.resetSelections(drawingPanel.getCurrentShape());
		drawingPanel.setbDiirty(true);
		drawingPanel.repaint();
	}
	public void delete(){
		Vector<CShapeManager> buffer = drawingPanel.getShapes();
		drawingPanel.getClipBoard().addClip(buffer);
		buffer.remove(drawingPanel.getCurrentShape());
		drawingPanel.setShapes(buffer);
		drawingPanel.repaint();
	}
	public void unDo(){
		CClipBoard clipBoard = drawingPanel.getClipBoard();
		int que = clipBoard.getQue();
		if(que >= 0){
			Vector<CShapeManager> buffer = clipBoard.getClip(que-1);
			clipBoard.setQue(que-1);
			drawingPanel.setShapes(buffer);
			drawingPanel.setbDiirty(true);
			drawingPanel.repaint();
		}else {
			String message="You can Undo 10 times only";
			String title="Cannot Undo!";
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		}
	}
	public void reDo(){
		CClipBoard clipBoard = drawingPanel.getClipBoard();
		int que = clipBoard.getQue();
		if(que<clipBoard.getSize()){
			Vector<CShapeManager> buffer = clipBoard.getClip(que+1);
			clipBoard.setQue(que+1);
			drawingPanel.setShapes(buffer);
			drawingPanel.setbDiirty(true);
			drawingPanel.repaint();
		} else {
			String message="We reach an status what before change";
			String title="Cannot Redo!";
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
			
		}
	}
	public void group(){
		CGroupManager group = new CGroupManager();
		if(drawingPanel.getCurrentShape().getClass().equals(CSelectionManager.class)){
			Vector<CShapeManager> selected = ((CSelectionManager) drawingPanel.getCurrentShape()).getShapes();
			if(selected!=null&&selected.size()>1){	group.setGroup(drawingPanel, selected);	drawingPanel.setbDiirty(true); drawingPanel.setCurrentShape(group);	}
			else {
				String message="Please select 2 shapes at least to set Group";
				String title="Deficient Select";
				JOptionPane.showConfirmDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
			}
		} else {
			String message="Please select 2 shapes at least to set Group";
			String title="Deficient Select";
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		}
	}
	public void unGroup(){
		CGroupManager group = new CGroupManager();
		CShapeManager currentShape = drawingPanel.getCurrentShape();
		if(currentShape.getClass().equals(CGroupManager.class)){
			group.setUnGroup(drawingPanel, ((CGroupManager)currentShape));
			drawingPanel.setbDiirty(true);
		} else {
			String message="Please select Group to set Ungroup";
			String title="Please select Group";
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
		}
	}
}