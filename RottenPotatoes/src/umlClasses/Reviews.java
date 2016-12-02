package umlClasses;

import java.util.Date;

import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Reviews {
	
	private int id;
	private String description;
	private Date date;
	private Integer rating;
	
	private Connection connection;
	
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
	
	public void setRating(Integer rating){
		this.rating = rating;
	}
	
	public Integer getRating(){
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

}
