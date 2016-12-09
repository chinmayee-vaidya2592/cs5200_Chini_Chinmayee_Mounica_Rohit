package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import umlClasses.Comments;
import umlClasses.Event;
import umlClasses.EventType;
import umlClasses.GenreType;
import umlClasses.RegisteredUser;
import umlClasses.Reviews;
import umlClasses.User;

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

	public void deleteUserComment(Connection conn, Scanner scanner) throws Exception {
		scanner.nextLine();
		System.out.println("Delete a user comment: ");
		System.out.println("Enter User Id to view the comments: ");
		int userId = scanner.nextInt();
		User user = new RegisteredUser(conn, userId);
		Map<Comments, Integer> commentList = user.getCommentsForUser(conn, userId);
		System.out.println("Comments for this user with Event ID: ");
		for (Entry<Comments, Integer> entry : commentList.entrySet()) {
			System.out.println("Comment ID:" + entry.getKey().getId());
			System.out.println("Comment Text:" + entry.getKey().getCommentText());
			System.out.println("Event ID : " + entry.getValue());
		}
		System.out.println("Enter the comment ID you want to delete: ");
		int commentId = scanner.nextInt();
		System.out.println("Enter the event ID associated with the comment: ");
		int eventId = scanner.nextInt();
		user.deleteUserComment(conn, commentId, eventId);
		System.out.println("User Comment deleted");
	}
	
	public void deleteUserReview(Connection conn, Scanner scanner) throws Exception {
		scanner.nextLine();
		System.out.println("Delete a user Review: ");
		System.out.println("Enter User Id to view the Reviews: ");
		int userId = scanner.nextInt();
		User user = new RegisteredUser(conn, userId);
		Map<Reviews, Integer> reviewList = user.getReviewsForUser(conn, userId);
		System.out.println("Comments for this user with Event ID: ");
		for (Entry<Reviews, Integer> entry : reviewList.entrySet()) {
			System.out.println("Review ID:" + entry.getKey().getId());
			System.out.println("Review Text:" + entry.getKey().getDescription());
			System.out.println("Review Rating:" + entry.getKey().getRating());
			System.out.println("Event ID : " + entry.getValue());
		}
		System.out.println("Enter the review ID you want to delete: ");
		int reviewId = scanner.nextInt();
		System.out.println("Enter the event ID associated with the review: ");
		int eventId = scanner.nextInt();
		user.deleteUserReview(conn, eventId, reviewId);
		System.out.println("User Review deleted");
	}
	
	public void modifyUserComment(Connection conn, Scanner scanner) throws Exception {
		scanner.nextLine();
		System.out.println("Modify a user comment: ");
		System.out.println("Enter User Id to view the comments: ");
		int userId = scanner.nextInt();
		User user = new RegisteredUser(conn, userId);
		Map<Comments, Integer> commentList = user.getCommentsForUser(conn, userId);
		System.out.println("Comments for this user with Event ID: ");
		for (Entry<Comments, Integer> entry : commentList.entrySet()) {
			System.out.println("Comment ID:" + entry.getKey().getId());
			System.out.println("Comment Text:" + entry.getKey().getCommentText());
			System.out.println("Event ID : " + entry.getValue());
		}
		System.out.println("Enter the Comment ID you want to update: ");
		int commentId = scanner.nextInt();
		System.out.println("Enter the new changed comment: ");
		scanner.nextLine();
		String commentText = scanner.nextLine();
		user.updateUserComment(conn, commentId, commentText);
		System.out.println("The comment has been updated! ");
	}
	
	public void modifyUserReview(Connection conn, Scanner scanner) throws Exception {
		scanner.nextLine();
		System.out.println("Modify a user Review: ");
		System.out.println("Enter User Id to view the Reviews: ");
		int userId = scanner.nextInt();
		User user = new RegisteredUser(conn, userId);
		Map<Reviews, Integer> reviewList = user.getReviewsForUser(conn, userId);
		System.out.println("Reviews for this user with Event ID: ");
		for (Entry<Reviews, Integer> entry : reviewList.entrySet()) {
			System.out.println("Review ID:" + entry.getKey().getId());
			System.out.println("Review Text:" + entry.getKey().getDescription());
			System.out.println("Review Rating:" + entry.getKey().getRating());
			System.out.println("Event ID : " + entry.getValue());
		}
		System.out.println("Enter the review ID you want to update: ");
		int reviewId = scanner.nextInt();
		System.out.println("Enter the new changed review: ");
		scanner.nextLine();
		String reviewText = scanner.nextLine();
		user.updateUserReview(reviewId, conn, reviewText);
		System.out.println("The review description has been updated! ");
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
		System.out.println("4. Delete a user Comment");
		System.out.println("5. Modify a user Comment");
		System.out.println("6. Delete a user Review");
		System.out.println("7. Modify a user Review");
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
			case 4:
				demo.deleteUserComment(connection, scanner);
				break;
			case 5:
				demo.modifyUserComment(connection, scanner);
				break;
			case 6:
				demo.deleteUserReview(connection, scanner);
				break;
			case 7:
				demo.modifyUserReview(connection, scanner);
				break;
		}
		scanner.close();
		connection.close();
	}

}
