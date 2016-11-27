import java.sql.Connection;
import java.util.Date;

public class Comments {
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
	
	public Date getDate(Date date){
		return date;
	}
}
