package umlClasses;

import java.util.Date;
import java.sql.Connection;

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

}
