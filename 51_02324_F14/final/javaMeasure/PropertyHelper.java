package javaMeasure;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.Properties;
import javaMeasure.control.CConnector;

public class PropertyHelper {
	
	
	public PropertyHelper(){
	 
	}
	
	public static void writeToProperty(String key, String value){
		writeToProperty("config", key, value);
	}

	public static void writeToProperty(String filename, String key, String value){
		
		//**********************PATH FIX**************************************************
		CodeSource codeSource = CConnector.class.getProtectionDomain().getCodeSource();
		String configPath = null;
		File jarFile = null;
			try {
				jarFile = new File(codeSource.getLocation().toURI().getPath());
				configPath = jarFile.getParentFile().getParentFile().getPath() + "/";
			} catch (URISyntaxException e) {
				System.err.println("failed to get path for properties");
			}
		//*********************************************************************************
		
		InputStream input = null;
		Properties prop = new Properties();	
		OutputStream output = null;
		
		try{
		input = new FileInputStream(filename + ".properties");
		prop.load(input);
		input.close();
		} catch(Exception npe){
			System.err.println("File not found. File will be created.");
		}
		try {
			output = new FileOutputStream(configPath + filename + ".properties", false);	
		} catch (FileNotFoundException f){
			System.err.println("PropertyHelper writeToProperty() - file not found exception");
		} 
		// save properties to project root folder
		prop.put(key, value);
		try {
			if (output != null) {
				prop.store(output, null);
				output.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static String readFromProperty(String key){
		return readFromProperty("config", key);
	}
	public static String readFromProperty(String fileName, String key){
		
		//**********************PATH FIX**************************************************
		CodeSource codeSource = CConnector.class.getProtectionDomain().getCodeSource();
		String configPath = null;
		File jarFile = null;
			try {
				jarFile = new File(codeSource.getLocation().toURI().getPath());
				configPath = jarFile.getParentFile().getParentFile().getPath() + "/";
			} catch (URISyntaxException e) {
				System.err.println("failed to get path for properties");
			}
		//*********************************************************************************
		
		String value = null;
		Properties prop = new Properties();
		InputStream in = null;
		try {
			System.out.println(configPath + fileName + ".properties");
			
			in = new FileInputStream(configPath + fileName + ".properties");
			prop.load(in);
			value = prop.getProperty(key);
			
		} catch (UnsupportedEncodingException e) {
			System.err.println("Unsupported encoding in property");
		} catch (FileNotFoundException e) {
			System.err.println("file is not found: " + key);
//			System.err.println("path: " + configPath);
		} catch (IOException e) {
		} finally{
			try {
				
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				System.err.println("failed to load inputStreamReader in property");
			}
		}
		
		return value;
	}

	// testing if overwriting is done correctly and if read and write itself is working properly, even if does not exist 
	public static void main(String[] args){
//		PropertyHelper p = new PropertyHelper();
//		p.writeToProperty("test", "key1", "value1");
//		System.out.println("init: " + p.readFromProperty("test", "key1"));
//		p.writeToProperty("test", "key2", "value2");
//		System.out.println("1: " + p.readFromProperty("test", "key1"));
//		System.out.println("2: " + p.readFromProperty("test", "key2"));
//		p.writeToProperty("test", "key1", "value6");
//		System.out.println("3: " + p.readFromProperty("test", "key1"));
	}
}