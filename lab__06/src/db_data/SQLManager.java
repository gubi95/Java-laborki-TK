package db_data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class SQLManager {
	private static final String _strConnectionString = "jdbc:sqlserver://DESKTOP-71GEC34\\SQLEXPRESSKONIU;databaseName=JDBC_lab06;user=sa;password=abc098123@@!";	
	private static Connection _objConnection = null;
	
	public static Connection getConnection() {			
		if(_objConnection == null) {		
			try {
				DriverManager.registerDriver(new SQLServerDriver());
				_objConnection = DriverManager.getConnection(_strConnectionString);				
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}		
		}
		
		return _objConnection;
	}
	
	public static void closeConnection() {
		try {
			if(_objConnection != null && !_objConnection.isClosed()) {
				_objConnection.close();
				_objConnection = null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
