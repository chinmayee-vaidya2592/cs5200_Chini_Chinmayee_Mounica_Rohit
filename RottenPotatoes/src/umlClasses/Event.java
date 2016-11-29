package umlClasses;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

public class Event {
	
	private String name;
	private String description;
	private String calculatedRating;
	private ArrayList<Comments> commentList = new ArrayList<Comments>();
	private ArrayList<Date> showTimes = new ArrayList<Date>();
	private EventType type;
	private int availableTickets;
	private Connection connection;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCalculatedRating() {
		return calculatedRating;
	}

	public void setCalculatedRating(String calculatedRating) {
		this.calculatedRating = calculatedRating;
	}

	public ArrayList<Comments> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<Comments> commentList) {
		this.commentList = commentList;
	}

	public ArrayList<Date> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(ArrayList<Date> showTimes) {
		this.showTimes = showTimes;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getAvailableTickets() {
		return availableTickets;
	}

	public void setAvailableTickets(int availableTickets) {
		this.availableTickets = availableTickets;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
