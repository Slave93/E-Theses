package rs.fon.elab.pzr.rest.model.response.ticket;

public class TicketResponse {
	private String ticket;

	public TicketResponse() {

	}

	public TicketResponse(String ticket) {
		this.ticket = ticket;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
