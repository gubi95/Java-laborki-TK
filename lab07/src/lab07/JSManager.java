package lab07;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class JSManager {

	public static String callStringReturnFunction(String strJSFilePath, String strFunctionName, String strText) {
		try {
			ScriptEngine objScriptEngine = new ScriptEngineManager().getEngineByName("nashorn");			
			objScriptEngine.eval(new FileReader(strJSFilePath));			
			Invocable invocable = (Invocable) objScriptEngine;
			return (String) invocable.invokeFunction(strFunctionName, strText);			
		}
		catch (Exception e) { }
		return "";
	}
	
}
