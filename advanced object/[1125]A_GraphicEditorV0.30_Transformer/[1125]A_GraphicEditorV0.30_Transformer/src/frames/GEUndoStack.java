package frames;

import java.util.Vector;

import shapes.GEShape;

public class GEUndoStack {
	private Vector<Vector<GEShape>> stack;
	private final int MAXSIZE = 10;
	
	public GEUndoStack(){
		this.stack = new Vector<Vector<GEShape>>();
	}
	@SuppressWarnings("unchecked")
	public void push(Vector<GEShape> shapes){
		this.stack.add((Vector<GEShape>)shapes.clone());  // 포인터기 때문에 copy해서 저장해야
		if(this.stack.size() > MAXSIZE)
			this.stack.remove(0);
	}
	public Vector<GEShape> pop(){
		if(this.stack.size() > 0){
			return this.stack.remove(this.stack.size()-1);
		}
		return null;
	}
}
