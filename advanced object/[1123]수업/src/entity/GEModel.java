package entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import constants.GEConstant;

public class GEModel {
	
	static public Object read(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(fileName);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		return object;
	}
	static public void save(String fileName, Object object) throws IOException {
		FileOutputStream FileOutputStream = new FileOutputStream(fileName);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(FileOutputStream);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.close();			
	}
	static public void configSave(String configDirectory) throws IOException{
		if(GEModel.configRead() == null){
			FileWriter writer = new FileWriter(GEConstant.SFILECONFIG);
			writer.write(configDirectory);
			writer.close();
		}
		else{
			FileWriter writer = new FileWriter(GEConstant.SFILECONFIG);
			writer.flush();
			writer.write(configDirectory);
			writer.close();
		}
	}
	static public String configRead() throws FileNotFoundException{
		String configContents = null;
		Scanner scanner = new Scanner(new File(GEConstant.SFILECONFIG));
		while(scanner.hasNext()){
			configContents = scanner.next();
		}
		scanner.close();
		return configContents;
	}
}
