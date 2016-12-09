package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import umlClasses.Event;
import umlClasses.EventType;
import umlClasses.GenreType;
import umlClasses.RegisteredUser;

public class Demo {
	
	public void addEvent(Connection con, Scanner scanner) throws Exception {
		scanner.nextLine();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		// Adding an Event:
		System.out.println("Add an Event:");
		System.out.println("Enter Name of the Event: ");
		String eventName = scanner.nextLine();
		System.out.println("Enter Event description: ");
		String description = scanner.nextLine();
		System.out.println("Enter Start Date: ");
		String startDate = scanner.nextLine();
		Date start = sdf1.parse(startDate);
		java.sql.Date startdate =  new java.sql.Date(start.getTime());
		System.out.println("Enter End Date: ");
		String endDate = scanner.nextLine();
		Date end = sdf1.parse(endDate);
		java.sql.Date enddate =  new java.sql.Date(end.getTime());
		System.out.println("Enter Show Time: ");
		String showTime = scanner.next();
		System.out.println("Enter Event Type: (Play, Musical)");
		EventType type = EventType.valueOf(scanner.next());
		System.out.println("Enter available tickets: ");
		int availTickets = scanner.nextInt();
		System.out.println("Enter Event ticket price: ");
		int price = scanner.nextInt();
		System.out.println("Enter genre type: (Horror, Thriller, History, Drama, Comedy)");
		GenreType genre = GenreType.valueOf(scanner.next());
		Event e = new Event();
		e.setConnection(con);
		e.createEvent(eventName, description, 0, startdate, enddate, showTime, type, availTickets, price, genre);
		System.out.println("New Event created!");
	}
	
	public void addRegisteredUser(Connection con, Scanner scanner) throws Exception {
		scanner.nextLine();
		System.out.println("Add a User:");
		System.out.println("Enter UserName: ");
		String userName = scanner.nextLine();
		System.out.println("Enter Password: ");
		String password = scanner.nextLine();
		System.out.println("Enter email: ");
		String email = scanner.nextLine();
		System.out.println("Enter First Name: ");
		String fname = scanner.nextLine();
		System.out.println("Enter Last Name: ");
		String lname = scanner.nextLine();
		RegisteredUser rs = new RegisteredUser();
		RegisteredUser rs1 = rs.createUser(con, userName, password, email, fname, lname);
		System.out.println("User created with user name: " + rs1.getusername());
	}
	
	public void modifyRegisteredUser(Connection con, Scanner scanner) throws Exception {
		scanner.nextLine();
		System.out.println("Modify a User:");
		System.out.println("Enter User Id to be Modified: ");
		int userId = scanner.nextInt();
		RegisteredUser existingUser = new RegisteredUser(con, userId);
		System.out.println("Current user details: ");
		System.out.println("Username: " + existingUser.getusername());
		System.out.println("Password: " + existingUser.getPassword());
		System.out.println("Email: " + existingUser.getemail());
		System.out.println("First Name: " + existingUser.getfname());
		System.out.println("Last Name: " + existingUser.getlname());
		System.out.println("Enter UserName to be modified: ");
		String userName = scanner.next();
		System.out.println("Enter Password: ");
		String password = scanner.next();
		System.out.println("Enter email: ");
		String email = scanner.next();
		System.out.println("Enter First Name: ");
		String fname = scanner.next();
		System.out.println("Enter Last Name: ");
		String lname = scanner.next();
		
		RegisteredUser updatedUser = existingUser.updateUser(con, userId, userName, password, email, fname, lname);
		System.out.println("User modified: " + updatedUser.getusername());
	}

	public static void main(String[] args) throws Exception {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		Scanner scanner = new Scanner(System.in);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbmsProject",
		"root", "chini");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Demo demo = new Demo();
		
		System.out.println("Choose the action you want to perform: ");
		System.out.println("1. Add an Event");
		System.out.println("2. Add a user");
		System.out.println("3. Modify a user");
		System.out.println("Enter choice here:");
		int choice = scanner.nextInt();
		switch (choice) {
			case 1:
				demo.addEvent(connection, scanner);
				break;
			case 2:
				demo.addRegisteredUser(connection, scanner);
				break;
			case 3:
				demo.modifyRegisteredUser(connection, scanner);
				break;
		}
		scanner.close();
		connection.close();
	}

}
