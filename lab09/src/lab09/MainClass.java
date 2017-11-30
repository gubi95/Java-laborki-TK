package lab09;

public class MainClass {
	public static void main(String[] args) {
		AddressXMLModel objAddressXMLModel = new AddressXMLModel();
		objAddressXMLModel.setAddressLine1("line 1");
		objAddressXMLModel.setAddressLine2("line 2");
		objAddressXMLModel.setFirstName("first name");
		objAddressXMLModel.setLastName("lastname");
		
		XMLManager.serialize(System.getProperty("user.dir") + "\\1.xml", objAddressXMLModel);
	}
}
