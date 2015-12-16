package frames;

import java.util.Vector;

import shapes.GEShape;

public class GEUndoStack {
	private final int MAXSIZE = 10;
	private Vector<Vector<GEShape>> stack;
	public Vector<Vector<GEShape>> getStack() {return stack;}

	public GEUndoStack () {
		this.stack = new Vector<Vector<GEShape>>();
	}
	@SuppressWarnings("unchecked")
	// 넣는거
	public void push(Vector<GEShape> shapes) {
		this.stack.add((Vector<GEShape>)shapes.clone());
		if (this.stack.size() > MAXSIZE){
			this.stack.remove(0);
		}
	}
	// 빼는거
	public Vector<GEShape> pop() {
		if (this.stack.size() > 0){
			return this.stack.remove(this.stack.size()-1);
		}
		return null;
	}
}
