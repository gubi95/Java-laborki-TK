package db_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VisitFactory {	
	public ArrayList<Visit> getAll() {		
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
			ResultSet objResultSet = objStatement.executeQuery("SELECT * FROM Visits");
			
			ArrayList<Visit> listVisit = new ArrayList<>();
			
			while(objResultSet.next()) {
				Visit objVisit = new Visit();		
				
				int nRoomID = objResultSet.getInt("RoomID");
				int nVisitingHourID = objResultSet.getInt("VisitingHourID");
				int nPatientID = objResultSet.getInt("PatientID");
				int nDoctorID = objResultSet.getInt("DoctorID");
				
				Room objRoom = new RoomFactory().getByID(nRoomID);
				VisitingHour objVisitingHour = new VisitingHourFactory().getByID(nVisitingHourID);
				User objUserPatient = new UserFactory().getByID(nPatientID);
				User objUserDoctor = new UserFactory().getByID(nDoctorID);
				
				objVisit.setID(objResultSet.getInt("VisitID"));
				objVisit.setRoom(objRoom);
				objVisit.setVisitingHour(objVisitingHour);
				objVisit.setPatient(objUserPatient);
				objVisit.setDoctor(objUserDoctor);
				
				listVisit.add(objVisit);
			}
			SQLManager.closeConnection();
			return listVisit;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}	
	
	public Visit create(Visit objVisit) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
		 			"INSERT INTO Visits " + 
		 			"(RoomID,VisitingHourID,PatientID,DoctorID) " + 
					"VALUES (" + objVisit.getRoom().getID() + "," + objVisit.getVisitingHour().getID() + "," + objVisit.getPatient().getID() + "," + objVisit.getDoctor().getID() + ")"
							, Statement.RETURN_GENERATED_KEYS);
		 	
		 	ResultSet objResultSet = objStatement.getGeneratedKeys();
		 	objResultSet.next();
		 	objVisit.setID(objResultSet.getInt(1));		 	
		 	
		 	SQLManager.closeConnection();
		 	return objVisit;
			
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void update(Visit objVisit) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
							"UPDATE Visits " + 
							"SET " + 
									"RoomID=" + objVisit.getRoom().getID() + 
									",VisitingHourID=" + objVisit.getVisitingHour().getID() + 
									",PatientID=" + objVisit.getPatient().getID() + 
									",DoctorID=" + objVisit.getDoctor().getID() +  
							" WHERE VisitID = " + objVisit.getID());
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}		
	}
	
	public void delete(int nVisitID) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate("DELETE FROM Visits WHERE VisitID = " + nVisitID);
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}	
	}
}
