package umlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import utils.Utils;

public class Event {
	
	private int id;
	private String name;
	private String description;
	private String calculatedRating;
	private ArrayList<Comments> commentList = new ArrayList<Comments>();
	private Date startDate;
	private Date endDate;
	private String showTime;
	private EventType type;
	private int availableTickets;
	private int ticketPrice;
	private Connection connection;
	
	public Event() {
		name = "";
		description = "";
		calculatedRating = "";
	}
	
	public Event(Connection connection, int eventId) throws Exception {
		this.connection = connection;
		PreparedStatement getEvent = connection.prepareStatement("select * from Event "
				+ "where id = ?");
		Utils.printDatabaseWarning(getEvent.getWarnings());
		PreparedStatement getComment = connection.prepareStatement("select ");
		PreparedStatement getReview = connection.prepareStatement("select ");
		try {
			getEvent.setInt(1, eventId);
			ResultSet rs = getEvent.executeQuery();
			Utils.printQueryWarning(getEvent.getWarnings());
			if (!rs.next()) {
				throw new Exception("Event does not exist.");
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					this.id = eventId;
					this.name = rs.getString(2);
					this.description = rs.getString(3);
					this.calculatedRating = rs.getString(4);
					this.startDate = rs.getDate(5);
					this.endDate = rs.getDate(6);
					this.showTime = rs.getString(7);
					this.type = EventType.valueOf(rs.getString(8));
					this.availableTickets = rs.getInt(9);
					this.ticketPrice = rs.getInt(10);
				}
			}
		} finally {
			getEvent.close();
		}
	}
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
