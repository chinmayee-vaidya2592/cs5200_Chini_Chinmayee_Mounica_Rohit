package umlClasses;

import utils.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public abstract class User {
	private int id;
	
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
	
	public void updateUserComment() {
		
	}
	
	public void createUserComment() {
		
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
	
	public void updateUserReview() {
		
	}
	
	public void createUserReview() {
		
	}
	
}
