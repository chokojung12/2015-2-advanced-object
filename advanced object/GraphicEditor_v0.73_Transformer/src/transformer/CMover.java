package transformer;

import java.awt.Rectangle;

import shapes.CAnchors;
import shapes.CRectangleManager;
import shapes.CShapeManager;

public class CMover extends CTransformer{
	
	@Override
	public void initTransforming(int x, int y) {
		getShapeManager().setPP(x, y);
		pp.setLocation(x,y);
	}

	@Override
	public void keepTransforming(int x, int y) {
		//this.getShapeManager().moveShape(x, y);
		affineTransform.setToTranslation(x-pp.x, y-pp.y);
		getShapeManager().setShape(affineTransform.createTransformedShape(getShapeManager().getShape()));
		
		for(int i = 0 ; i < getShapeManager().getAnchors().size(); i++){
			getShapeManager().getAnchors().set(i,affineTransform.createTransformedShape(getShapeManager().getAnchors().get(i)));
		}
		pp.setLocation(x,y);
	}

	@Override
	public void finishTransforming(int x, int y) {
		
	}

}
