package lab09;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GreetingsXMLModel {
	private String _strGreetingsText;
	
	@XmlElement
	public void setGreetingsText(String strGreetingsText) {
		this._strGreetingsText = strGreetingsText;
	}
	
	public String getGreetingsText() {
		return this._strGreetingsText;
	}
}
