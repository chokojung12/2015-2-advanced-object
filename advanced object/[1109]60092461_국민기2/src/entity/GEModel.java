package entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import shapes.GEShape;

public class GEModel {
		
	// exception을 여기서 던지면 애를 호출하는애가 exception처리를 해야한다.
	//여기서 try catch로 애러를 처리하게 되면 뭐가 문제인지 호출하는애는 뭐가 문제인지 모른다.
	static public Object read(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(fileName);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		return object;
		
			// TODO Auto-generated catch block
	}
	static public void save(String fileName, Object object) throws IOException {
		FileOutputStream FileOutputStream = new FileOutputStream(fileName);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(FileOutputStream);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();			
	}

}
