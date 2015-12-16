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

	public static Vector<GEShape> shapes = new Vector<GEShape>(); 	// static은 new를 하지 않아도 메모리에 올라간다. 공유자원이다.  class variable이다. 여러번 new를 해도 1번만 만들어진다.
	
	
	static public Vector<GEShape> getShapes() {return shapes;}
	static public void setShapes(Vector<GEShape> shapes) {GEModelShape.shapes = shapes;}
	
	@SuppressWarnings("unchecked")
	static public void read(String fileName){
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);   // 파일 오픈 같은것
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); // 파일이 너무 클 경우를 대비해 버퍼를 만들어준다
			ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream); // 오픈한 파일을 오브젝트로 만들어준다.
			GEModelShape.shapes = (Vector<GEShape>) objectInputStream.readObject();
			objectInputStream.close();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static public void save(String fileName){
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(fileName);		// 파일을 분해한다.
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream); // 분해한 파일을 버퍼에 저장한다.
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
			objectOutputStream.writeObject(GEModelShape.shapes);
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
}
