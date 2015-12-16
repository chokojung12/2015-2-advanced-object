package frames;

import java.util.Vector;

import constants.GEConstant;
import shapes.GEShape;

public class GEUndoStack {
	private final int MAXSIZE = 10;
	private Vector<Vector<GEShape>> stack;
	private Vector<GEShape> initialStack = new Vector<GEShape>();
	
	public void setInitialStack(Vector<GEShape> initialStack) {
		this.initialStack = initialStack;
	}

	public Vector<Vector<GEShape>> getStack() {return stack;}

	public GEUndoStack () {
		this.stack = new Vector<Vector<GEShape>>();
	}
	@SuppressWarnings("unchecked")
	public void push(Vector<GEShape> shapes) {
		this.stack.add((Vector<GEShape>)shapes.clone());
		if (this.stack.size() > MAXSIZE){
			this.stack.remove(0);
		}
	}
	
	public Vector<GEShape> pop() {
		if (this.stack.size() > 0 && GEConstant.VARIATION > 0)
			return this.stack.remove(this.stack.size()-1);
		else{
			return null;
		}
	}

}
