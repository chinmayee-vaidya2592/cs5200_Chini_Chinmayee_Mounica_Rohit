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
		PreparedStatement getEvent = connection.prepareStatement("select * from Event "
				+ "where id = ?");
		Utils.printDatabaseWarning(getEvent.getWarnings());
		PreparedStatement getComment = connection.prepareStatement("select comment, commentedOnBy from UserComment"
				+ " where commentOn = ?");
		Utils.printDatabaseWarning(getComment.getWarnings());
		PreparedStatement getReview = connection.prepareStatement("select reviewId, reviewedBy from UserReview"
				+ " where reviews = ?");
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
					this.calculatedRating = rsEvent.getString(4);
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
			if (!rsComment.next()) {
				throw new Exception("No comments found!");
			} else {
				rsComment.beforeFirst();
				while (rsComment.next()) {
					Comments c = new Comments(connection, rsComment.getInt(1), rsComment.getInt(2));
					this.commentList.add(c);
				}
			}
			getReview.setInt(1, eventId);
			ResultSet rsReview = getReview.executeQuery();
			Utils.printQueryWarning(getReview.getWarnings());
			if (!rsReview.next()) {
				throw new Exception("No reviews found!");
			} else {
				rsReview.beforeFirst();
				while (rsReview.next()) {
					Reviews r = new Reviews(connection, rsReview.getInt(1), rsReview.getInt(2));
					this.reviewList.add(r);
				}
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
	
	public ArrayList<Event> getExistingEvents(Connection con) throws Exception {
		ArrayList<Event> eventList = new ArrayList<Event>();
		PreparedStatement getEvents = con.prepareStatement("select id, name, description, type, genreType "
				+ "from Event order by genreType");
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
}
