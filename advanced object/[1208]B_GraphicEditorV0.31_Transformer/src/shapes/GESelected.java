package shapes;

public class GESelected extends GERectangle {
	public boolean contains(GEShape shape) {
		if (this.getShape().contains(shape.getShape().getBounds())) {
			return true;
		}
		return false;
	}

}
