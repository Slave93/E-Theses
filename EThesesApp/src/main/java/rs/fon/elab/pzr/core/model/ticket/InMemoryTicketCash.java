package rs.fon.elab.pzr.core.model.ticket;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTicketCash {
	private static Map<String, SimpleTicket> ticketCash = new HashMap<String, SimpleTicket>();

	public void addTicket(SimpleTicket ticket) {
		ticketCash.put(ticket.getTicket(), ticket);
	}

	public SimpleTicket getTicket(String ticketId) {
		return ticketCash.get(ticketId);
	}

	public void remove(String ticket) {
		ticketCash.remove(ticket);
	}

	public void removeExpired() {
		for (Map.Entry<String, SimpleTicket> entry : ticketCash.entrySet()) {
			if(entry.getValue().isExpired()){				
				ticketCash.remove(entry.getKey());
			}
		}
	}
}
