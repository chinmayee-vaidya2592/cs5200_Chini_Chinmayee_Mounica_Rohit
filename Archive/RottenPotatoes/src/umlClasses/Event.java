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
	private double calculatedRating;
	private ArrayList<Comments> commentList = new ArrayList<Comments>();
	private ArrayList<Reviews> reviewList = new ArrayList<Reviews>();
	private Date startDate;
	private Date endDate;
	private String showTime;
	private EventType type;
	private int availableTickets;
	private int ticketPrice;
	private GenreType genreType;
	private Connection connection;
	
	public Event() {
		super();
	}
	
	public Event(Connection connection, int eventId) throws Exception {
		this.connection = connection;
		PreparedStatement getEvent = connection.prepareStatement("select * from Event e "
				+ "where e.id = ?");
		Utils.printDatabaseWarning(getEvent.getWarnings());
		PreparedStatement getComment = connection.prepareStatement("select uc.comment, uc.commentedOnBy from UserComment uc "
				+ " where uc.commentOn = ?");
		Utils.printDatabaseWarning(getComment.getWarnings());
		PreparedStatement getReview = connection.prepareStatement("select ur.reviewId, ur.reviewedBy from UserReview ur"
				+ " where ur.reviews = ?");
		Utils.printDatabaseWarning(getReview.getWarnings());
		try {
			getEvent.setInt(1, eventId);
			ResultSet rsEvent = getEvent.executeQuery();
			Utils.printQueryWarning(getEvent.getWarnings());
			if (!rsEvent.next()) {
				throw new Exception("Event does not exist.");
			} else {
				rsEvent.beforeFirst();
				while (rsEvent.next()) {
					this.id = rsEvent.getInt(1);
					this.name = rsEvent.getString(2);
					this.description = rsEvent.getString(3);
					this.calculatedRating = rsEvent.getDouble(4);
					this.startDate = rsEvent.getDate(5);
					this.endDate = rsEvent.getDate(6);
					this.showTime = rsEvent.getString(7);
					this.type = EventType.valueOf(rsEvent.getString(8));
					this.availableTickets = rsEvent.getInt(9);
					this.ticketPrice = rsEvent.getInt(10);
					this.genreType = GenreType.valueOf(rsEvent.getString(11));
				}
			}
			getComment.setInt(1, eventId);
			ResultSet rsComment = getComment.executeQuery();
			Utils.printQueryWarning(getComment.getWarnings());
			while (rsComment.next()) {
				Comments c = new Comments(connection, rsComment.getInt(1), rsComment.getInt(2));
				this.commentList.add(c);
			}
			getReview.setInt(1, eventId);
			ResultSet rsReview = getReview.executeQuery();
			Utils.printQueryWarning(getReview.getWarnings());
			while (rsReview.next()) {
				Reviews r = new Reviews(connection, rsReview.getInt(1), rsReview.getInt(2));
				this.reviewList.add(r);
			}
		} finally {
			getEvent.close();
			getComment.close();
			getReview.close();
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

	public double getCalculatedRating() {
		return calculatedRating;
	}

	public void setCalculatedRating(double calculatedRating) {
		this.calculatedRating = calculatedRating;
	}

	public ArrayList<Comments> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<Comments> commentList) {
		this.commentList = commentList;
	}
	
	public ArrayList<Reviews> getReviewList() {
		return reviewList;
	}

	public void setReviewList(ArrayList<Reviews> reviewList) {
		this.reviewList = reviewList;
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
	
	public GenreType getGenreType() {
		return genreType;
	}

	public void setGenreType(GenreType genreType) {
		this.genreType = genreType;
	}
	 
	
	public int createEvent(String name, String description, double calculatedRating, 
			Date startDate, Date endDate, String showTime, EventType type, 
			int availableTickets, int ticketPrice, GenreType genreType) throws Exception{
		int eventId = getNewEventId();
		PreparedStatement ps = getConnection().prepareStatement("insert into Event values (?,?,?,?,?,?,?,?,?,?,?)");
		Utils.printDatabaseWarning(ps.getWarnings());
		try {
			ps.setInt(1, eventId);
			ps.setString(2, name);
			ps.setString(3, description);
			ps.setDouble(4, calculatedRating);
			ps.setDate(5, (java.sql.Date) startDate);
			ps.setDate(6, (java.sql.Date) startDate);
			ps.setString(7, showTime);
			ps.setString(8, type.toString());
			ps.setInt(9, availableTickets);
			ps.setInt(10, ticketPrice);
			ps.setString(11, genreType.toString());
			int insertCount = ps.executeUpdate();
			Utils.printInsertWarning(ps.getWarnings());
			if (insertCount != 1) {
				throw new Exception("Error in inserting");
			}
		} finally {
			ps.close();
		}
		return eventId;
	}
	
	public void updateEvent(String name, String description, double calculatedRating, 
			Date startDate, Date endDate, String showTime, EventType type, 
			int availableTickets, int ticketPrice, GenreType genreType, int eventId) throws Exception{
		PreparedStatement ps = getConnection().prepareStatement("UPDATE Event SET name = ?, "
				+ "description = ?, calculatedRating = ?, startDate = ?, endDate = ?, "
				+ "showTime = ?, type = ?, availableTickets = ?, ticketPrice = ?, genreType = ? "
				+ "WHERE id = ?");
		Utils.printDatabaseWarning(ps.getWarnings());
		try {
			java.sql.Date start =  new java.sql.Date(startDate.getTime());
			java.sql.Date end =  new java.sql.Date(endDate.getTime());
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setDouble(3, calculatedRating);
			ps.setDate(4, start);
			ps.setDate(5, end);
			ps.setString(6, showTime);
			ps.setString(7, type.toString());
			ps.setInt(8, availableTickets);
			ps.setInt(9, ticketPrice);
			ps.setString(10, genreType.toString());
			ps.setInt(11, eventId);
			int updateCount = ps.executeUpdate();
			Utils.printUpdateWarning(ps.getWarnings());
			if (updateCount != 1) {
				throw new Exception("Error in updating");
			}
		} finally {
			ps.close();
		}
	}
	
	public int getNewEventId() throws Exception{
		int newId = 0;
		PreparedStatement getMaxId = getConnection().prepareStatement("select if(max(id)+1 is null, 1, max(id) + 1) from Event");
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
	
	// Get the list according to user genre selection
	public ArrayList<Event> getEventsForUser(Connection con, int userId) throws Exception {
		ArrayList<Event> eventListUser = new ArrayList<Event>();
		PreparedStatement getEvent = con.prepareStatement("select e.id, e.name, e.description, e.calculatedRating, e.type, e.genreType "
				+ "from Event e where e.genreType in "
				+ "(select ug.genreType from UserGenre ug where ug.user = ?)");
		Utils.printDatabaseWarning(getEvent.getWarnings());
		try {
			getEvent.setInt(1, userId);
			ResultSet rs = getEvent.executeQuery();
			Utils.printQueryWarning(getEvent.getWarnings());
			if (!rs.next()) {
				throw new Exception("No events exist");
			} else {
				rs.beforeFirst();
				while(rs.next()) {
					Event eve = new Event();
					eve.setId(rs.getInt(1));
					eve.setName(rs.getString(2));
					eve.setDescription(rs.getString(3));
					eve.setCalculatedRating(rs.getDouble(4));
					eve.setType(EventType.valueOf(rs.getString(5)));
					eve.setGenreType(GenreType.valueOf(rs.getString(6)));
					eventListUser.add(eve);
				}
			}
		} finally {
			getEvent.close();
		}
		return eventListUser;
	}
	
	//Gets the list of all existing events in the database
	public ArrayList<Event> getExistingEvents(Connection con) throws Exception {
		ArrayList<Event> eventList = new ArrayList<Event>();
		PreparedStatement getEvents = con.prepareStatement("select e.id, e.name, e.description, e.type, e.genreType "
				+ "from Event e order by genreType");
		Utils.printDatabaseWarning(getEvents.getWarnings());
		try {
			ResultSet rsEvent = getEvents.executeQuery();
			Utils.printQueryWarning(getEvents.getWarnings());
			if (!rsEvent.next()) {
				throw new Exception("No events exist");
			} else {
				rsEvent.beforeFirst();
				while(rsEvent.next()) {
					Event e = new Event();
					e.setId(rsEvent.getInt(1));
					e.setName(rsEvent.getString(2));
					e.setDescription(rsEvent.getString(3));
					e.setType(EventType.valueOf(rsEvent.getString(4)));
					e.setGenreType(GenreType.valueOf(rsEvent.getString(5)));
					eventList.add(e);
				}
			}
		} finally {
			getEvents.close();
		}
		return eventList;
	}
	
	// Updates the ticket count by reducing the number of tickets requested by user
	public int updateAvailableTickets(int ticketCount) throws Exception {
		int leftTickets = 0;
		if (this.getAvailableTickets() >= ticketCount) {
			leftTickets = this.getAvailableTickets() - ticketCount;
			PreparedStatement updateTicket = getConnection().prepareStatement("update Event set availableTickets = ? "
					+ "where id = ?");
			Utils.printDatabaseWarning(updateTicket.getWarnings());
			try {
			
				updateTicket.setInt(1, leftTickets);
				updateTicket.setInt(2, this.id);
				int updateCount = updateTicket.executeUpdate();
				Utils.printUpdateWarning(updateTicket.getWarnings());
				if (updateCount != 1) {
					throw new Exception("Error inserting records!");
				}			
			} finally {
				updateTicket.close();
			}
		}
		System.out.println("Tickets left: " + leftTickets);
		return leftTickets;
	}
	
	// Get the list of events on the basis of ratings
	public ArrayList<Event> getEventListByRating(Connection con) throws Exception {
		ArrayList<Event> eventList = new ArrayList<Event>();
		PreparedStatement getEvents = con.prepareStatement("select e.id, e.name, e.description, e.calculatedRating, e.type, "
				+ "e.genreType from Event e where e.calculatedRating > 3 order by e.genreType");
		Utils.printDatabaseWarning(getEvents.getWarnings());
		try {
			ResultSet rsEvent = getEvents.executeQuery();
			Utils.printQueryWarning(getEvents.getWarnings());
				while(rsEvent.next()) {
					Event e = new Event();
					e.setId(rsEvent.getInt(1));
					e.setName(rsEvent.getString(2));
					e.setDescription(rsEvent.getString(3));
					e.setCalculatedRating(rsEvent.getDouble(4));
					e.setType(EventType.valueOf(rsEvent.getString(5)));
					e.setGenreType(GenreType.valueOf(rsEvent.getString(6)));
					eventList.add(e);
			}
		} finally {
			getEvents.close();
		}
		return eventList;
	}
	
	
}
