package frames;

import java.io.FileNotFoundException;

import constants.GEConstant;
import entity.GEModel;


public class GEMain {
	
	private static GEFrame frame;
	
	public static void main(String[] args) {
		try {
			String configContents = GEModel.configRead();
			if(configContents != null){
				GEConstant.SWORKSPACE = configContents;
			}
			else{
				; // default setting
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame = new GEFrame();
		frame.init();
		frame.setVisible(true);
	}
}
