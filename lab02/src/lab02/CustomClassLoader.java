package lab02;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CustomClassLoader extends ClassLoader {	
	private HashMap<String, Class> _listLoadedClassess = null;
	
	public CustomClassLoader(ClassLoader parent) {
		super(parent);
		_listLoadedClassess = new HashMap();
	}
	
	public boolean fileExists(String strPath) {
		File objFile = new File(strPath);
		return objFile.exists() && !objFile.isDirectory();
	}
	
	public static List<File> getAllClassesToLoad() {
		File objFile = new File(System.getProperty("user.dir") + "/classes_to_load");
		return Arrays.asList(objFile.listFiles((d, name) -> name.endsWith(".class")));
	}
	
	public boolean isClassLoaded(String strClassName) {		
		return _listLoadedClassess.get(strClassName) != null;
	}
	
	public void unloadClass(String strClassName) {
		if(this.isClassLoaded(strClassName)) {
			this._listLoadedClassess.remove(strClassName);
		}
	}
	
	public Class getClass(String strClassName) {
		if(this.isClassLoaded(strClassName)) {
			return this._listLoadedClassess.get(strClassName);
		}
		return null;
	}
	
	public Class loadClass(String strName) throws ClassNotFoundException {		
		try {			
			String strDirectory = System.getProperty("user.dir");
			
			if(!fileExists(strDirectory + "/classes_to_load/" + strName + ".class")) {				
				return super.loadClass(strName);
			}
			
			String strURL = "file:/" + strDirectory + "/classes_to_load/" + strName + ".class";			
		    URL objURL = new URL(strURL);	        
		        
		    URLConnection objURLConnection = objURL.openConnection();	        
		    InputStream objInputStream = objURLConnection.getInputStream();
		    ByteArrayOutputStream objByteArrayOutputStream = new ByteArrayOutputStream();
		    int nData = objInputStream.read();
	
		    while(nData != -1) {
		    	objByteArrayOutputStream.write(nData);
		        nData = objInputStream.read();
		    }
	
		    objInputStream.close();
	
		    byte[] arrClassData = objByteArrayOutputStream.toByteArray();		    
		        
		    Class objClass = defineClass(arrClassData, 0, arrClassData.length);
		    
		    this.resolveClass(objClass);
		    
		    System.out.println(strName + " loaded!");
		    
		    return objClass;
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
}


	

