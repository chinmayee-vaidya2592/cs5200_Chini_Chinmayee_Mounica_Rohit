package umlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import utils.Utils;

public class Comments {
	
	private int id;
	private int userId;
	private String commentText;
	private Date date;
	
	private Connection connection;
	
	public Comments() {
		super();
	}
	
	public Comments(int id, int userId, String commentText, Date date) {
		this.id = id;
		this.userId = userId;
		this.commentText = commentText;
		this.date = date;
	}
	
	public Comments(Connection conn, int id, int userId) throws Exception {
		this.connection = conn;
		this.userId = userId;
        PreparedStatement getComment = conn.prepareStatement("select * from Comment where id = ?");
        Utils.printDatabaseWarning(getComment.getWarnings());
        try {
        	getComment.setInt(1, id);
        	ResultSet rsComment = getComment.executeQuery();
        	Utils.printQueryWarning(getComment.getWarnings());
        	if (rsComment.next()) {
        		this.id = rsComment.getInt(1);
        		this.commentText = rsComment.getString(2);
        		this.date = rsComment.getDate(3);
        	} else {
        		throw new Exception("No comments returned");
        	}
        } finally {
        	getComment.close();
        }
    }
	
	public Comments getComment(String cmntxt, Date dte) {
        Comments c = new Comments();
	    c.setCommentText(cmntxt);
		c.setDate(dte);
        return c;
}
	
	public void setCommentText(String commentText){
		this.commentText = commentText;
	}
	
	public String getCommentText(){
		return commentText;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getNewCommentId() throws Exception{
		int newId = 0;
		PreparedStatement getMaxId = getConnection().prepareStatement("select if(max(id)+1 is null, 1, max(id) + 1) from Comment");
		Utils.printDatabaseWarning(getMaxId.getWarnings());
		try {
			ResultSet rs = getMaxId.executeQuery();
			Utils.printQueryWarning(getMaxId.getWarnings());
			if (rs.next()) {
				newId = rs.getInt(1);
			} else {
				throw new Exception("Invalid id!");
			}
		} finally {
			getMaxId.close();
		}
		return newId;
	}
	
	public String getUserNameById(Connection con, int userId) throws Exception {
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
