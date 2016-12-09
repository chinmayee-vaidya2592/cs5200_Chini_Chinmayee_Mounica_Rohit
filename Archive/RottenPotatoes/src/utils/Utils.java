package utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

// This class is a utility class which prints the SQL warnings. Since these methods
// have been called multiple times. 
public final class Utils {
	
	public static void printDatabaseWarning(SQLWarning warning) {
		while (warning != null) {
		    System.err.println("Database Warning: " + warning);
		}
	}
	
	public static void printQueryWarning(SQLWarning warning) {
		while (warning != null) {
		    System.err.println("Query Warning: " + warning);
		}
	}
	
	public static void printUpdateWarning(SQLWarning warning) {
		while (warning != null) {
		    System.err.println("Update Warning: " + warning);
		}
	}
	
	public static void printDeleteWarning(SQLWarning warning) {
		while (warning != null) {
		    System.err.println("Delete Warning: " + warning);
		}
	}
	
	public static void printInsertWarning(SQLWarning warning) {
		while (warning != null) {
		    System.err.println("Insert Warning: " + warning);
		}
	}
	
	public static ArrayList<String> getDateRange(Date startDate, Date endDate) {
		ArrayList<String> dates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		while (cal.getTime().before(endDate)) {
		    cal.add(Calendar.DATE, 1);
		    dates.add(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
		}
		return dates;
	}
	
	public static String getUserNameById(Connection con, int userId) throws Exception {
    	String name = "";
    	PreparedStatement getName = con.prepareStatement("select username from RegisteredUser where id = ?");
    	Utils.printDatabaseWarning(getName.getWarnings());
    	try {
    		getName.setInt(1, userId);
    		ResultSet rsName = getName.executeQuery();
    		Utils.printQueryWarning(getName.getWarnings());
    		if (rsName.next()) {
    			name = rsName.getString(1);
    		} else {
    			throw new Exception("User not found!");
    		}
    	} finally {
    		getName.close();
    	}
    	return name;
    }

}
