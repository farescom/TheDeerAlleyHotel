package system.domain.model;

import java.io.Serializable;
import java.util.Date;

public class Query implements Serializable
{
	int client_id;
	Date from;
	Date to;

	public Query(int client_id, Date from, Date to)
	{
		this.client_id = client_id;
		this.from = from;
		this.to = to;
	}
	
	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
	
	@Override
	public String toString() {
		return "Query [client_id=" + client_id + ", from=" + from + ", to="
				+ to + "]";
	}
}