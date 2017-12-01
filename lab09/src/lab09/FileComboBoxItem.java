package lab09;

import java.io.File;

public class FileComboBoxItem {
	private boolean _bIsCustom;
	private String _strOverrideName;
	private String _strFilePath;
	
	public FileComboBoxItem(String strFilePath) {
		this._strFilePath = strFilePath;
		this._bIsCustom = false;
	}
	
	public FileComboBoxItem(boolean bIsCustom, String strOverrideName) {
		this._bIsCustom = bIsCustom;
		this._strOverrideName = strOverrideName;
	}
	
	public String getFilePath() {
		return this._strFilePath;
	}
	
	public boolean getIsCustom() {
		return this._bIsCustom;
	}
	
	@Override
	public String toString() {
		if(this._bIsCustom) {
			return _strOverrideName;
		}
		return new File(this._strFilePath).getName();
	}
}