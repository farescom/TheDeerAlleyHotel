package system.domain.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/** represents users Booking 
 * */
public class Booking implements Serializable
{
	/** reservation number */
	int id;				
	/** guest personal data: name, address, phone, date of birth and nationality. */
	Guest guest;						
	/** which rooms are booked at which prices. */
	ArrayList <Room> booked_rooms;
	Date arrival;
	Date departure;
	/** (Optional) the number of extra beds */
	int extra_bed = 0; 				 	
	/** (Optional) could be null */
	int number_of_guests;				
	/** the status of Booking, 1- booked, 0- notConfirmed (happens after 18:00)*/
	int status = 1;					
	
	public Booking(int id, Guest guest, ArrayList<Room> booked_rooms,
			Date arrival, Date departure, int extra_bed, int number_of_guests)
	{
		this.id = id;
		this.guest = guest;
		this.booked_rooms = booked_rooms;
		this.arrival = arrival;
		this.departure = departure;
		this.extra_bed = extra_bed;
		this.number_of_guests = number_of_guests;
		this.status = 1;
	}
	
	public Booking(int id, Guest guest, ArrayList<Room> booked_rooms, Date arrival, Date departure, int extra_bed)
	{
		this.id = id;
		this.guest = guest;
		this.booked_rooms = booked_rooms;
		this.arrival = arrival;
		this.departure = departure;
		this.extra_bed = extra_bed;
		this.status = 1;
	}
	
	public Booking(int id, Guest guest, ArrayList<Room> booked_rooms, Date arrival, Date departure)
	{
		this.id = id;
		this.guest = guest;
		this.booked_rooms = booked_rooms;
		this.arrival = arrival;
		this.departure = departure;
		this.extra_bed = 0;
		this.status = 1;
	}
	
	public Booking()
	{
		this.id = 0;
		this.guest = new Guest();
		this.booked_rooms = null;
		//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		this.arrival = cal.getTime();
		this.departure = cal.getTime();
		this.extra_bed = 0;
		this.status = 1;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public ArrayList<Room> getBooked_rooms() {
		return booked_rooms;
	}

	public void setBooked_rooms(ArrayList<Room> booked_rooms) {
		this.booked_rooms = booked_rooms;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public int getExtra_bed() {
		return extra_bed;
	}

	public void setExtra_bed(int extra_bed) {
		this.extra_bed = extra_bed;
	}

	public int getNumber_of_guests() {
		return number_of_guests;
	}

	public void setNumber_of_guests(int number_of_guests) {
		this.number_of_guests = number_of_guests;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Booking [id=" + id + ", guest=" + guest + ", booked_rooms="
				+ booked_rooms + ", arrival=" + arrival + ", departure="
				+ departure + ", extra_bed=" + extra_bed
				+ ", number_of_guests=" + number_of_guests + "]";
	}
}