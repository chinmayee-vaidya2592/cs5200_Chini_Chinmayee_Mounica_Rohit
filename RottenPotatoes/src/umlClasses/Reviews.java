package umlClasses;

import java.util.Date;

import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Reviews {
	
	private int id;
	private int userId;
	private String description;
	private double rating;
	private Date date;
	
	private Connection connection;
	
	public Reviews() {
		super();
	}
	
	public Reviews(Connection conn, int id, int userId) throws Exception {
		this.connection = conn;
		this.userId = userId;
        PreparedStatement getReview = conn.prepareStatement("select * from Review where id = ?");
        Utils.printDatabaseWarning(getReview.getWarnings());
        try {
        	getReview.setInt(1, id);
        	ResultSet rsReview = getReview.executeQuery();
        	Utils.printQueryWarning(getReview.getWarnings());
        	if (rsReview.next()) {
        		this.id = rsReview.getInt(1);
        		this.description = rsReview.getString(2);
        		this.rating = rsReview.getDouble(3);
        		this.date = rsReview.getDate(4);
        	} else {
        		throw new Exception("No reviews returned");
        	}
        } finally {
        	getReview.close();
        }
    }
	
	public Reviews(String desc, Date dte, Integer rate){
		description = desc;
		date = dte;
		rating = rate;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setRating(double rating){
		this.rating = rating;
	}
	
	public double getRating(){
		return rating;
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
	
	public int getNewReviewId() throws Exception{
		int newId = 0;
		PreparedStatement getMaxId = getConnection().prepareStatement("select if(max(id)+1 is null, 1, max(id) + 1) from Review");
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
	
	public double updateCalulatedRating(int eventId) throws Exception {
		double rating = this.getRating();
		PreparedStatement getNewRating = getConnection().prepareStatement("select avg(rating) from review r "
				+ "where exists (select * from UserReview ur "
				+ "where ur.reviews = ? and ur.reviewId = r.id)");
		Utils.printDatabaseWarning(getNewRating.getWarnings());
		PreparedStatement updateEvent = getConnection().prepareStatement("update Event "
				+ "set calculatedRating = ? where id = ?");
		Utils.printDatabaseWarning(updateEvent.getWarnings());
		try {
			getNewRating.setInt(1, eventId);
			ResultSet rsNewRating = getNewRating.executeQuery();
			Utils.printQueryWarning(getNewRating.getWarnings());
			if (rsNewRating.next()) {
				rating = rsNewRating.getDouble(1);
			}
			updateEvent.setDouble(1, rating);
			updateEvent.setInt(2, eventId);
			int updateCount = updateEvent.executeUpdate();
			if (updateCount != 1) {
				throw new Exception("Error updating records!");
			}
		} finally {
			getNewRating.close();
			updateEvent.close();
		}
		return rating;
	}
}
