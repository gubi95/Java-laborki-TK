package db_data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VisitingHourFactory {	
	public VisitingHour getByID(int nVisitingHourID) {		
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
			ResultSet objResultSet = objStatement.executeQuery("SELECT * FROM VisitingHours WHERE VisitingHourID = " + nVisitingHourID);			
			
			while(objResultSet.next()) {
				VisitingHour objVisitingHour = new VisitingHour();				
				objVisitingHour.setID(objResultSet.getInt("VisitingHourID"));
				
				Calendar objCalendarFrom = Calendar.getInstance();
				objResultSet.getDate("FromDateTime", objCalendarFrom);				
				objVisitingHour.setFromDateTime(objCalendarFrom);
				
				Calendar objCalendarTo = Calendar.getInstance();
				objResultSet.getDate("ToDateTime", objCalendarTo);				
				objVisitingHour.setToDateTime(objCalendarTo);				
				
				SQLManager.closeConnection();
				return objVisitingHour;
			}
			SQLManager.closeConnection();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<VisitingHour> getAll() {		
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
			ResultSet objResultSet = objStatement.executeQuery("SELECT * FROM VisitingHours");
			
			ArrayList<VisitingHour> listVisitingHour = new ArrayList<>();
			
			while(objResultSet.next()) {
				VisitingHour objVisitingHour = new VisitingHour();				
				objVisitingHour.setID(objResultSet.getInt("VisitingHourID"));
				
				Calendar objCalendarFrom = Calendar.getInstance();				
				Timestamp objTimestampFrom = objResultSet.getTimestamp("FromDateTime");
				objCalendarFrom.setTime(new Date(objTimestampFrom.getTime()));
				objVisitingHour.setFromDateTime(objCalendarFrom);				
				
				Calendar objCalendarTo = Calendar.getInstance();
				Timestamp objTimestampTo = objResultSet.getTimestamp("ToDateTime");
				objCalendarTo.setTime(new Date(objTimestampTo.getTime()));								
				objVisitingHour.setToDateTime(objCalendarTo);				
				
				listVisitingHour.add(objVisitingHour);
			}
			SQLManager.closeConnection();
			return listVisitingHour;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}	
	
	public VisitingHour create(VisitingHour objVisitingHour) {
		try {			
			String strFromDateTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objVisitingHour.getFromDateTime().getTime());
			String strToDateTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objVisitingHour.getToDateTime().getTime());			
			
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
		 			"INSERT INTO VisitingHours " + 
		 			"(FromDateTime, ToDateTime) " + 
					"VALUES ('" + strFromDateTimeFormatted + "', '" + strToDateTimeFormatted + "')"
							, Statement.RETURN_GENERATED_KEYS);
		 	
		 	ResultSet objResultSet = objStatement.getGeneratedKeys();
		 	objResultSet.next();
		 	objVisitingHour.setID(objResultSet.getInt(1));		 	
		 	
		 	SQLManager.closeConnection();
		 	return objVisitingHour;
			
		}
		catch (Exception e) { 
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void update(VisitingHour objVisitingHour) {
		try {
			String strFromDateTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objVisitingHour.getFromDateTime().getTime());
			String strToDateTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(objVisitingHour.getToDateTime().getTime());	
			
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate(
							"UPDATE VisitingHours " + 
							"SET FromDateTime='" + strFromDateTimeFormatted + "', ToDateTime='" + strToDateTimeFormatted + "'" +  
							" WHERE VisitingHourID = " + objVisitingHour.getID());
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}		
	}
	
	public void delete(int nVisitingHourID) {
		try {
			Statement objStatement = SQLManager.getConnection().createStatement();
		 	objStatement.executeUpdate("DELETE FROM VisitingHours WHERE VisitingHourID = " + nVisitingHourID);
		 	SQLManager.closeConnection();
		}
		catch (Exception e) { 
			e.printStackTrace();
		}	
	}
}
