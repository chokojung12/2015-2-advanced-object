package transformer;


public class CDrawer extends CTransformer{
	
	@Override
	public void initTransforming(int x, int y) {
		getShapeManager().setPP(x, y);
		pp.setLocation(x,y);
	}

	@Override
	public void keepTransforming(int x, int y) {
		getShapeManager().movePoint(x, y);
	}

	@Override
	public void finishTransforming(int x, int y) {
		
	}

}
