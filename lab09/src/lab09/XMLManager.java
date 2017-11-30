package lab09;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLManager {
	public static <T> void serialize(String strFilePath, T object) {
		try {
			File objFile = new File(strFilePath);
			JAXBContext objJAXBContext = JAXBContext.newInstance(object.getClass());
			Marshaller objMarshaller = objJAXBContext.createMarshaller();
			objMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			objMarshaller.marshal(object, objFile);
			objMarshaller.marshal(object, System.out);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String strFilePath, Class<T> objTClass) {
		try {
			File objFile = new File(strFilePath);			
			JAXBContext objJAXBContext = JAXBContext.newInstance(objTClass);
			Unmarshaller objUnmarshaller = objJAXBContext.createUnmarshaller();
			return (T) objUnmarshaller.unmarshal(objFile);			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}