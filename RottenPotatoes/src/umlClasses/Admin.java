package umlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;

import utils.Utils;


/**
 * 
 * @author Chinmayee Vaidya
 *
 */
public class Admin extends RegisteredUser{

	public Admin(int id) {
		super(id);
	}

	/**
	 * @param user creates a new user according to the registered user object passed
	 * @throws Exception 
	 */
	public void createUser(RegisteredUser user) throws Exception{
		
		try {
			Connection conn = user.getConn();
			PreparedStatement userInsert = conn.prepareStatement("insert into RegisteredUser (username, password,email, firstName, lastName, hasAccess )"
					+" values (?,?,?,?,?,?) ");
			SQLWarning warnings;
			warnings = userInsert.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			userInsert.setString(1, user.getusername());
			userInsert.setString(2, user.getPassword());
			userInsert.setString(3, user.getemail());
			userInsert.setString(4, "FirstName Attribute Name");
			userInsert.setString(5, "LastName Attribute Name");
			userInsert.setBoolean(6, true);
			int updateCount = userInsert.executeUpdate();
			SQLWarning updateWar;
			updateWar = userInsert.getWarnings();
			while(updateWar!=null){
				System.err.println("Database Warnings! "+updateWar);
			}
			
			if(updateCount!=1){
				throw new Exception("No apartment has that id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param user: updates values of a particular user
	 * @throws Exception 
	 */
	public void updateUser(RegisteredUser user) throws Exception{
		try {
			Connection conn = user.getConn();
			PreparedStatement updateuser = conn.prepareStatement("Update RegisteredUser set username = ? "
					+" password = ? , email= ? , firstName=? , lastName = ?  "
					+"  where id = ? ");
			
			
			SQLWarning warnings;
			warnings = updateuser.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			updateuser.setString(1, user.getusername());
			updateuser.setString(2, user.getPassword());
			updateuser.setString(3, user.getemail());
			updateuser.setString(4, "FirstName");
			updateuser.setString(5, "LastName");
			updateuser.setInt(6, 1);//get id from user class
			int updateCount = updateuser.executeUpdate();
			SQLWarning updateWar;
			updateWar = updateuser.getWarnings();
			while(updateWar!=null){
				System.err.println("Database Warnings! "+updateWar);
			}
			
			if(updateCount!=1){
				throw new Exception("No apartment has that id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param id Deletes a user for the particular id
	 * @throws Exception 
	 */
	public void deleteUser(int id, Connection conn) throws Exception{
		PreparedStatement deleteuser;
		try {
			deleteuser = conn.prepareStatement("delete from RegisteredUser where id = ? ");
			
			SQLWarning warnings;
			warnings = deleteuser.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			deleteuser.setInt(1, id);
			int deleteCount = deleteuser.executeUpdate();
			SQLWarning deleteWar;
			deleteWar = deleteuser.getWarnings();
			while(deleteWar!=null){
				System.err.println("Database Warnings! "+deleteWar);
			}
			
			if(deleteCount!=1){
				throw new Exception("No user with that id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param id: deletes one comment according to the comment id passed
	 * @throws Exception 
	 */
	public void deleteOneComment(int comment_id, Connection conn) throws Exception{
		PreparedStatement deleteComment;
		try {
			deleteComment = conn.prepareStatement("delete from Comments where id = ?");
			SQLWarning warnings;
			warnings = deleteComment.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			deleteComment.setInt(1, comment_id);
			int deleteCount = deleteComment.executeUpdate();
			SQLWarning delWar;
			delWar = deleteComment.getWarnings();
			while(delWar!=null){
				System.err.println("Database Warnings! "+delWar);
			}
			
			if(deleteCount!=1){
				throw new Exception("No user with that id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param id: deletes all comments for a particular user id
	 * @throws Exception 
	 */
	public void deleteAllComments(int user_id, Connection conn) throws Exception{
		PreparedStatement deleteAllCommentsForUser;
		try {
			deleteAllCommentsForUser = conn.prepareStatement("select comment from UserComment where commentedOnBy = ?");
			SQLWarning warnings;
			warnings = deleteAllCommentsForUser.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);	
		} 
			deleteAllCommentsForUser.setInt(1, user_id);
			ResultSet rs = deleteAllCommentsForUser.executeQuery();
			
			while(rs.next()){
				int comm_id = rs.getInt(1);
				deleteOneComment(comm_id,conn);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//Reviews
	
	public void deleteOneReview(int rev_id, Connection conn) throws Exception{
		PreparedStatement deleteReview;
		try {
			deleteReview = conn.prepareStatement("delete from review where id = ?");
			SQLWarning warnings;
			warnings = deleteReview.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			deleteReview.setInt(1, rev_id);
			int deleteCount = deleteReview.executeUpdate();
			SQLWarning delWar;
			delWar = deleteReview.getWarnings();
			while(delWar!=null){
				System.err.println("Database Warnings! "+delWar);
			}
			
			if(deleteCount!=1){
				throw new Exception("No review with that id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAllReviews(int user_id, Connection conn) throws Exception{
		PreparedStatement deleteAllReviewsForUser;
		try {
			deleteAllReviewsForUser = conn.prepareStatement("select reviewId from UserReview where reviewedBy = ?");
			SQLWarning warnings;
			warnings =deleteAllReviewsForUser.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);	
		} 
			deleteAllReviewsForUser.setInt(1, user_id);
			ResultSet rs = deleteAllReviewsForUser.executeQuery();
			
			while(rs.next()){
				int comm_id = rs.getInt(1);
				deleteOneReview(comm_id,conn);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<RegisteredUser> getAllUsers(Connection conn) throws SQLException{
		ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();
		PreparedStatement ps = conn.prepareStatement("select * from RegisteredUser");
		SQLWarning warnings;
		warnings =ps.getWarnings();
		while(warnings!=null){
			System.err.println("Database Warnings! "+warnings);	
	} 
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			RegisteredUser ru = new RegisteredUser();
			ru.setId(rs.getInt(1));
			ru.setusername(rs.getString(2));
			ru.setPassword(rs.getString(3));
			ru.setemail(rs.getString(4));
			ru.setfname(rs.getString(5));
			ru.setlname(rs.getString(6));
			ru.set_Access(rs.getBoolean(7));
			ru.setConn(conn);
			users.add(ru);
		}
		return users;
	}
	
	public ArrayList<Event> getAllEvents(Connection conn) throws Exception{
		ArrayList<Event> events = new ArrayList<Event>();
		PreparedStatement ps = conn.prepareStatement("select * from Event");
		Utils.printDatabaseWarning(ps.getWarnings());
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Event e = new Event();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setDescription(rs.getString(3));
				events.add(e);
			}
		} finally {
			ps.close();
		}
		return events;
	}
	
	public void deleteOneEvent(int event_id, Connection conn) throws Exception{
		PreparedStatement deleteEvent;
		try {
			deleteEvent = conn.prepareStatement("delete from Event where id = ?");
			SQLWarning warnings;
			warnings = deleteEvent.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			deleteEvent.setInt(1, event_id);
			int deleteCount = deleteEvent.executeUpdate();
			SQLWarning delWar;
			delWar = deleteEvent.getWarnings();
			while(delWar!=null){
				System.err.println("Database Warnings! "+delWar);
			}
			
			if(deleteCount!=1){
				throw new Exception("No event with that id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
