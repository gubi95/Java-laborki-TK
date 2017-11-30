package db_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomFactory {
	public Room getByID(int nRoomID) {		
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
			ResultSet objResultSet = objStatement.executeQuery("SELECT * FROM Rooms WHERE RoomID = " + nRoomID);			
			
			while(objResultSet.next()) {
				Room objRoom = new Room();				
				objRoom.setID(objResultSet.getInt("RoomID"));
				objRoom.setName(objResultSet.getString("Name"));
				SQLManager.closeConnection();
				return objRoom;
			}
			SQLManager.closeConnection();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Room> getAll() {		
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
			ResultSet objResultSet = objStatement.executeQuery("SELECT * FROM Rooms");
			
			ArrayList<Room> listRoom = new ArrayList<>();
			
			while(objResultSet.next()) {
				Room objRoom = new Room();				
				objRoom.setID(objResultSet.getInt("RoomID"));
				objRoom.setName(objResultSet.getString("Name"));				
				listRoom.add(objRoom);
			}
			SQLManager.closeConnection();
			return listRoom;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}	
	
	public Room create(Room objRoom) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
		 			"INSERT INTO Rooms " + 
		 			"(Name) " + 
					"VALUES ('" + objRoom.getName() + "')"
							, Statement.RETURN_GENERATED_KEYS);
		 	
		 	ResultSet objResultSet = objStatement.getGeneratedKeys();
		 	objResultSet.next();
	        objRoom.setID(objResultSet.getInt(1));		 	
		 	
		 	SQLManager.closeConnection();
		 	return objRoom;
			
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void update(Room objRoom) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
							"UPDATE Rooms " + 
							"SET Name='" + objRoom.getName() + "'" +  
							" WHERE RoomID = " + objRoom.getID());
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}		
	}
	
	public void delete(int nRoomID) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate("DELETE FROM Rooms WHERE RoomID = " + nRoomID);
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}	
	}
}
