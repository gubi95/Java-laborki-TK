package lab09;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import net.myprojects.xsd.testscheme.AddressXML;
import net.myprojects.xsd.testscheme.CardXML;

public class MainClass {
	public static void main(String[] args) {
//		AddressXML objAddressXMLModel = new AddressXML();
//		objAddressXMLModel.setLine1("line 1");
//		objAddressXMLModel.setLine2("line 2");
//		objAddressXMLModel.setFirstname("first name");
//		objAddressXMLModel.setLastname("lastname");
//		XMLManager.serialize(System.getProperty("user.dir") + "\\xml_addresses\\1.xml", objAddressXMLModel);
		new MainWindow();
	}
}
