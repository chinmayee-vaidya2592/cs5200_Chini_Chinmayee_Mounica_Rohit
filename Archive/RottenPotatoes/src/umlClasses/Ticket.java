package umlClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.Utils;

public class Ticket {
	
	private int ticketId;
	private int eventId;
	private int userId;
	private Date showDate;
	
	public Ticket() {
		super();
	}
	
	public int getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getNewTicketId(Connection con) throws Exception {
		int newId = 0;
		PreparedStatement getMaxId = con.prepareStatement("select if(max(id)+1 is null, 1, max(id) + 1)"
				+ " from Ticket");
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
	
	
	public Ticket createTicket(Connection conn, int eventId, int userId, String showDate) throws Exception {
		int newTicketId = getNewTicketId(conn);
		Ticket ticket = new Ticket();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		Date show = new Date();
		PreparedStatement insertTicket = conn.prepareStatement("insert into Ticket values (?, ?, ?, ?)");
		Utils.printDatabaseWarning(insertTicket.getWarnings());
		try {
			show = df.parse(showDate);
			insertTicket.setInt(1, newTicketId);
			insertTicket.setInt(2, eventId);
			insertTicket.setInt(3, userId);
			insertTicket.setDate(4, new java.sql.Date(show.getTime()));
			int insertCount = insertTicket.executeUpdate();
			if (insertCount != 1) {
				throw new Exception("Error inserting records!");
			}
			ticket.setTicketId(newTicketId);
			ticket.setEventId(eventId);
			ticket.setUserId(userId);
			ticket.setShowDate(show);
		} finally {
			insertTicket.close();
		}
		return ticket;
	}	
}
