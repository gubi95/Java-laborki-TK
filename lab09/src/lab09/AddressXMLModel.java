package lab09;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AddressXMLModel {
	private String _strFirstName;
	private String _strLastName;
	private String _strAddressLine1;
	private String _strAddressLine2;
	
	@XmlElement
	public void setFirstName(String strFirstName) {
		this._strFirstName = strFirstName;
	}
	
	@XmlElement
	public void setLastName(String strLastName) {
		this._strLastName = strLastName;
	}
	
	@XmlElement
	public void setAddressLine1(String strAddressLine1) {
		this._strAddressLine1 = strAddressLine1;
	}
	
	@XmlElement
	public void setAddressLine2(String strAddressLine2) {
		this._strAddressLine2 = strAddressLine2;
	}
	
	public String getFirstName() {
		return this._strFirstName;
	}
	
	public String getLastName() {
		return this._strLastName;
	}
	
	public String getAddressLine1() {
		return this._strAddressLine1;
	}
	
	public String getAddressLine2() {
		return this._strAddressLine2;
	}
}
