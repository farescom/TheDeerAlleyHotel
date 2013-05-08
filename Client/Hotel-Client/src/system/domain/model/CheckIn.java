package system.domain.model;

import java.io.Serializable;
import java.util.Date;

/** represents users CheckIn */
public class CheckIn implements Serializable
{
	Guest guest;
	Room room;				// or room_id
	Date arrival_date;
	Date expected_departure;
	
	public CheckIn(Guest guest, Room room, Date arrival_date, Date expected_departure)
	{
		super();
		this.guest = guest;
		this.room = room;
		this.arrival_date = arrival_date;
		this.expected_departure = expected_departure;
	}
	
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public Date getExpected_departure() {
		return expected_departure;
	}

	public void setExpected_departure(Date expected_departure) {
		this.expected_departure = expected_departure;
	}

	@Override
	public String toString() {
		return "CheckIn [guest=" + guest + ", room=" + room + ", arrival_date="
				+ arrival_date + ", expected_departure=" + expected_departure
				+ "]";
	}
}