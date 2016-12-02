package umlClasses;

import utils.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;

public abstract class User {
	private int id;
	
	public User() {
	
	}
	
	public User(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void deleteUserComment(Connection conn, int commentId, int eventId) throws Exception {
		PreparedStatement deleteComment = conn.prepareStatement("delete from UserComment where "
				+ "commentOn = ? and commentedOnBy = ? and comment = ?");
		Utils.printDatabaseWarning(deleteComment.getWarnings());
		PreparedStatement deleteCommentText = conn.prepareStatement("delete from Comment where "
				+ "id = ?");
		Utils.printDatabaseWarning(deleteCommentText.getWarnings());
		try {
			deleteComment.setInt(1, eventId);
			deleteComment.setInt(2, this.getId());
			deleteComment.setInt(3, commentId);
			int deleteCount1 = deleteComment.executeUpdate();
			Utils.printDeleteWarning(deleteComment.getWarnings());
			if (deleteCount1 != 1) {
				throw new Exception("No records to be deleted!");
			}
			deleteCommentText.setInt(1, commentId);
			int deleteCount2 = deleteComment.executeUpdate();
			Utils.printDeleteWarning(deleteComment.getWarnings());
			if (deleteCount2 != 1) {
				throw new Exception("No records to be deleted!");
			}
		} finally {
			deleteComment.close();
			deleteCommentText.close();
		}	
	}
	
	public void insertUserComment(Connection conn, Comments comments, int eventId) throws Exception {
		int newCommentId = comments.getNewCommentId();
		PreparedStatement insertCommentText = conn.prepareStatement("insert into Comment "
				+ "values (?, ?, ?)");
		Utils.printDatabaseWarning(insertCommentText.getWarnings());
		PreparedStatement insertComment = conn.prepareStatement("insert into UserComment"
				+ " values (?, ?, ?)");
		Utils.printDatabaseWarning(insertComment.getWarnings());
		try {
			insertCommentText.setInt(1, newCommentId);
			insertCommentText.setString(2, comments.getCommentText());
			insertCommentText.setDate(3, (Date) comments.getDate());
			int insertCount1 = insertCommentText.executeUpdate();
			if (insertCount1 != 1) {
				throw new Exception("Error inserting records!");
			}
			insertComment.setInt(1, eventId);
			insertComment.setInt(2, this.getId());
			insertComment.setInt(3, newCommentId);
			int insertCount2 = insertComment.executeUpdate();
			if (insertCount2 != 1) {
				throw new Exception("Error inserting records!");
			}
		} finally {
			insertCommentText.close();
			insertComment.close();
		}
	}
	
	public void updateUserComment(Connection conn, int commentId, String newComment)throws Exception {
		PreparedStatement updateComment = conn.prepareStatement("update Comment set commentText = ? where id = ?");
		Utils.printDatabaseWarning(updateComment.getWarnings());
		try{
			updateComment.setString(1, newComment);
			updateComment.setInt(2, commentId);
			int updateCount = updateComment.executeUpdate();
			Utils.printDatabaseWarning(updateComment.getWarnings());
			if(updateCount!=1){
				throw new Exception("No records to be updated");
			}
		} finally  {
			updateComment.close();
		}
	}
	
	
	public void deleteUserReview(Connection conn, int eventId, int reviewId) throws Exception {
		PreparedStatement deleteComment = conn.prepareStatement("delete from UserReview where "
				+ "reviews = ? and reviewedBy = ? and reviewId = ?");
		Utils.printDatabaseWarning(deleteComment.getWarnings());
		PreparedStatement deleteCommentText = conn.prepareStatement("delete from Review where "
				+ "id = ?");
		Utils.printDatabaseWarning(deleteCommentText.getWarnings());
		try {
			deleteComment.setInt(1, eventId);
			deleteComment.setInt(2, this.getId());
			deleteComment.setInt(3, reviewId);
			int deleteCount1 = deleteComment.executeUpdate();
			Utils.printDeleteWarning(deleteComment.getWarnings());
			if (deleteCount1 != 1) {
				throw new Exception("No records to be deleted!");
			}
			deleteCommentText.setInt(1, reviewId);
			int deleteCount2 = deleteComment.executeUpdate();
			Utils.printDeleteWarning(deleteComment.getWarnings());
			if (deleteCount2 != 1) {
				throw new Exception("No records to be deleted!");
			}
		} finally {
			deleteComment.close();
			deleteCommentText.close();
		}
	}
	
	public void insertUserReview(Connection conn, Reviews reviews, int eventId) throws Exception {
		int newReviewId = reviews.getNewReviewId();
		PreparedStatement insertReviewText = conn.prepareStatement("insert into Review "
				+ "values (?, ?, ?)");
		Utils.printDatabaseWarning(insertReviewText.getWarnings());
		PreparedStatement insertReview = conn.prepareStatement("insert into UserReview"
				+ " values (?, ?, ?)");
		Utils.printDatabaseWarning(insertReview.getWarnings());
		try {
			insertReviewText.setInt(1, newReviewId);
			insertReviewText.setString(2, reviews.getDescription());
			insertReviewText.setInt(3, reviews.getRating());
			int insertCount1 = insertReviewText.executeUpdate();
			if (insertCount1 != 1) {
				throw new Exception("Error inserting records!");
			}
			insertReview.setInt(1, eventId);
			insertReview.setInt(2, this.getId());
			insertReview.setInt(3, newReviewId);
			int insertCount2 = insertReview.executeUpdate();
			if (insertCount2 != 1) {
				throw new Exception("Error inserting records!");
			}
		} finally {
			insertReviewText.close();
			insertReview.close();
		}
	}
	
	/**
	 * 
	 * @param rev_id: id of the review whose description has to be changed
	 * @param conn: Connection to the database
	 * @throws Exception 
	 */
	public void updateUserReview(int rev_id, Connection conn, String desc) throws Exception {
		try {

			PreparedStatement updateReview = conn.prepareStatement("Update Review set description = ? "
					+"  where rev_id = ? ");
			
			
			SQLWarning warnings;
			warnings = updateReview.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			updateReview.setString(1, desc);
			updateReview.setInt(2, rev_id);
	
			int updateCount = updateReview.executeUpdate();
			SQLWarning updateRev;
			updateRev= updateReview.getWarnings();
			while(updateRev!=null){
				System.err.println("Database Warnings! "+updateRev);
			}
			
			if(updateCount!=1){
				throw new Exception("No review with the id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
