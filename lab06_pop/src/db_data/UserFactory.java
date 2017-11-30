package db_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserFactory {	
	public User getByID(int nUserID) {		
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
			ResultSet objResultSet = objStatement.executeQuery("SELECT * FROM Users WHERE UserID = " + nUserID);			
			
			while(objResultSet.next()) {
				User objUser = new User();				
				objUser.setID(objResultSet.getInt("UserID"));
				objUser.setFirstName(objResultSet.getString("FirstName"));
				objUser.setLastName(objResultSet.getString("LastName"));
				objUser.setPESEL(objResultSet.getString("PESEL"));
				objUser.setIsPatient(objResultSet.getBoolean("IsPatient"));
				SQLManager.closeConnection();
				return objUser;
			}
			SQLManager.closeConnection();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<User> getAll() {		
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
			ResultSet objResultSet = objStatement.executeQuery("SELECT * FROM Users");
			
			ArrayList<User> listUser = new ArrayList<>();
			
			while(objResultSet.next()) {
				User objUser = new User();				
				objUser.setID(objResultSet.getInt("UserID"));
				objUser.setFirstName(objResultSet.getString("FirstName"));
				objUser.setLastName(objResultSet.getString("LastName"));
				objUser.setPESEL(objResultSet.getString("PESEL"));
				objUser.setIsPatient(objResultSet.getBoolean("IsPatient"));
				listUser.add(objUser);
			}
			SQLManager.closeConnection();
			return listUser;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}	
	
	public User create(User objUser) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
		 			"INSERT INTO Users " + 
		 			"(FirstName, LastName, PESEL, IsPatient) " + 
					"VALUES ('" + objUser.getFirstName() + "', '" + objUser.getLastName() + "', '" + objUser.getPESEL() + "', " + (objUser.getIsPatient() ? "1" : "0") + ")"
							, Statement.RETURN_GENERATED_KEYS);
		 	
		 	ResultSet objResultSet = objStatement.getGeneratedKeys();
		 	objResultSet.next();
	        objUser.setID(objResultSet.getInt(1));		 	
		 	
		 	SQLManager.closeConnection();
		 	return objUser;
			
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void update(User objUser) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
							"UPDATE Users " + 
							"SET FirstName='" + objUser.getFirstName() + "', LastName='" + objUser.getLastName() + "', PESEL='" + objUser.getPESEL() + "', IsPatient=" + (objUser.getIsPatient() ? "1" : "0") + 
							" WHERE UserID = " + objUser.getID());
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}		
	}
	
	public void delete(int nUserID) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate("DELETE FROM Users WHERE UserID = " + nUserID);
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}	
	}
}
