package entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import shapes.GEShape;

public class GEModelShape {
	
	static private Vector<GEShape> shapes = new Vector<GEShape>();
	static public Vector<GEShape> getShapes() {return GEModelShape.shapes;}
	static public void setShapes(Vector<GEShape> shapes) {GEModelShape.shapes = shapes;}
	
	@SuppressWarnings("unchecked")
	static public void read(String fileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
			GEModelShape.shapes = (Vector<GEShape>) objectInputStream.readObject();
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void save(String fileName) {
		try {
			FileOutputStream FileOutputStream = new FileOutputStream(fileName);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(FileOutputStream);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
			objectOutputStream.writeObject(GEModelShape.shapes);
			objectOutputStream.close();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
