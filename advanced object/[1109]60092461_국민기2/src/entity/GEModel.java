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
		
	// exception�� ���⼭ ������ �ָ� ȣ���ϴ¾ְ� exceptionó���� �ؾ��Ѵ�.
	//���⼭ try catch�� �ַ��� ó���ϰ� �Ǹ� ���� �������� ȣ���ϴ¾ִ� ���� �������� �𸥴�.
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
