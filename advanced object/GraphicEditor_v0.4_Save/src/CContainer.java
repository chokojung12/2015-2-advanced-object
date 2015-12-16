import java.util.Vector;
import java.awt.Graphics;


public abstract class CContainer extends CComponent{
	private Vector<CComponent> containers;
	
	public CContainer(){
		containers = new Vector<CComponent>();
	}
	
	public void add(CContainer container){
		this.containers.add(container);
	}
	
	
	
}