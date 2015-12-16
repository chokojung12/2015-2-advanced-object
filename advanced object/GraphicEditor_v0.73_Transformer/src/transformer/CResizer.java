package transformer;


public class CResizer extends CTransformer{
	
	@Override
	public void initTransforming(int x, int y) {
		getShapeManager().setPP(x, y);
		pp.setLocation(x,y);
	}

	@Override
	public void keepTransforming(int x, int y) {
		double scaleX=(getShapeManager().getShape().getBounds().getWidth()+(x-pp.x))/getShapeManager().getShape().getBounds().getWidth();
		double scaleY=(getShapeManager().getShape().getBounds().getHeight()+(y-pp.y))/getShapeManager().getShape().getBounds().getHeight();
		affineTransform.setToScale(scaleX, scaleY);
		getShapeManager().setShape(affineTransform.createTransformedShape(getShapeManager().getShape()));
		

		affineTransform.setToScale(1, 1);
		for(int i = 0 ; i < getShapeManager().getAnchors().size(); i++){
			getShapeManager().getAnchors().set(i,affineTransform.createTransformedShape(getShapeManager().getAnchors().get(i)));
		}
		pp.setLocation(x,y);
	}

	@Override
	public void finishTransforming(int x, int y) {
		
	}

}
