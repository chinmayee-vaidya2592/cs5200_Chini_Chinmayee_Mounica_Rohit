package utils;
import java.sql.SQLWarning;

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
}
