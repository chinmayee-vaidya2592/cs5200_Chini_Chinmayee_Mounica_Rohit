package umlClasses;

import java.sql.Connection;
import java.util.Date;

public class Comments {
	
	private int id;
	private String commentText;
	private Date date;
	
	private Connection connection;
	
	public Comments(String cmntxt, Date dte) {
		commentText = cmntxt;
		date = dte;
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
}
