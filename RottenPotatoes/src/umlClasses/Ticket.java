package umlClasses;

import java.util.Date;

public class Ticket {
	
	private int ticketId;
	private int eventId;
	private Date showDate;
	
	public Ticket(int ticketId, int price, int eventId, Date showDate) {
		this.ticketId = ticketId;
		this.eventId = eventId;
		this.showDate = showDate;
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

}
