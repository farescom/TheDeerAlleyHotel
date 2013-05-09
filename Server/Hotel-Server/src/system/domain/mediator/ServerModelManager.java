package system.domain.mediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.domain.model.Booking;
import system.domain.model.CheckIn;
import system.domain.model.CheckOut;
import system.domain.model.Room;
import utility.persistence.dataProvider;

/** a facade for the Model */
public class ServerModelManager extends Observable implements ModelInterface
{
	private ArrayList<Booking> bookings;
	private ArrayList<CheckIn> checkins;
	private ArrayList<CheckOut> checkouts;
	private ArrayList<Room> rooms;

	public ServerModelManager() throws IOException, ParserConfigurationException, TransformerException
	{
		bookings = new ArrayList<Booking>();
		checkins = new ArrayList<CheckIn>();
		checkouts = new ArrayList<CheckOut>();
		rooms = dataProvider.getRoomList();

		ServerConnectionThread server = new ServerConnectionThread(this);
		server.start();
	}

	// Here we will implement or the functionality, which will be called from
	// controller in execute function.
	// example
	public void Book(Booking booking)
	{
		bookings.add(booking);
		super.setChanged();
		super.notifyObservers("Booking with id " + booking.getId() + " was added.");
	}

	public Boolean isRoomAvailable(int id)
	{
		return rooms.get(id).getAvailable();
	}

	public Room getRoom(int id)
	{
		return rooms.get(id);
	}

	/**
	 * @param id of the room
	 */
	public void reserveRoom(int id)
	{
		getRoom(id).setAvailable(false);
		super.setChanged();
		super.notifyObservers("Room with id " + id + " was reserved.");
	}
	
	/**
	 * @return booking list size
	 */
	public int getBookingSize()
	{
		return bookings.size();
	}

	/**
	 * @return ArrayList<Booking>
	 */
	public ArrayList<Booking> ListBookings()
	{
		return bookings;
	}
	
	/**
	 * Return a ArrayList of Booking reserved by a particular guest
	 * @param name of the guest
	 * @return ArrayList<Booking> 
	 */
	public ArrayList<Booking> getBookingsByName(String name)
	{
		ArrayList<Booking> tmpArrayList = new ArrayList<Booking>();
		for(Booking booking : bookings)
		{
			if(booking.getGuest().getName().equals(name))
			{
				tmpArrayList.add(booking);
			}
		}
		return tmpArrayList;
	}
	
	/**
	 * Remove booking by specific id
	 * @param id by which the booking is going to be removed
	 */
	public void removeBooking(int id)
	{
		for(Booking booking : bookings)
		{
			if(booking.getId() == id)
			{
				ArrayList <Room> booked_rooms = booking.getBooked_rooms();
				releaseRooms(booked_rooms);
				bookings.remove(booking);
				super.setChanged();
				super.notifyObservers("Booking with id " + id + " was released.");
				break;
			}
		}
	}
	
	/**
	 * Release the rooms
	 * @param List of the rooms that are going to be released
	 */
	public void releaseRooms(ArrayList <Room> rooms)
	{
		for(Room room : rooms)
		{
			room.setAvailable(true);
			super.setChanged();
			super.notifyObservers("Room with id " + room.get_id() + " was released.");
		}
	}
	
	public ArrayList<CheckIn> ListCheckins()
	{
		return checkins;
	}
	
	public ArrayList<CheckOut> ListCheckouts()
	{
		return checkouts;
	}
	
	public ArrayList<CheckIn> ListCheckinsByDay(Date date)
	{
		ArrayList<CheckIn> newArrayList = new ArrayList<CheckIn>();
		for(CheckIn checkin : checkins)
		{
			if(checkin.getArrival_date() == date)
			{
				newArrayList.add(checkin);
			}
		}
		
		return newArrayList;
	}
	
	public ArrayList<CheckOut> ListCheckoutsByDay(Date date)
	{
		ArrayList<CheckOut> newArrayList = new ArrayList<CheckOut>();
		for(CheckOut checkout : checkouts)
		{
			if(checkout.getDeparture_data() == date)
			{
				newArrayList.add(checkout);
			}
		}
		
		return newArrayList;
	}
	
	public ArrayList<Room> ListRooms()
	{
		return rooms;
	}
	
	/**
	 * Return a ArrayList of rooms available in given period of time,
	 * limited by range of prices (or optionally by type of room)
	 * 
	 * @param ArrayList<Date> dates
	 * @param ArrayList<Integer> prices
	 * @param Integer roomType
	 * @return ArrayList<Room>
	 */
	public ArrayList<Room> ListAvailableRoomsByPeriod(ArrayList<Date> dates, ArrayList<Integer> prices, Integer roomType)
	{
		ArrayList<Room> freeRooms = new ArrayList<Room>();
		ArrayList<Room> bookedRooms = new ArrayList<Room>();
		for(Booking book : bookings)
		{
			if((book.getArrival().compareTo(dates.get(0)) >= 0 && book.getArrival().compareTo(dates.get(1)) <= 0) ||
					(book.getDeparture().compareTo(dates.get(0)) >= 0 && book.getDeparture().compareTo(dates.get(1)) <= 0))
			{
				bookedRooms.addAll(book.getBooked_rooms());
			}
			
		}
		
		for(Room room : rooms)
		{
			if(!bookedRooms.contains(room) &&
					(room.get_price() >= prices.get(0) && room.get_price() <= prices.get(1)))
			{
				if(roomType != null)
				{
					if(room.get_number_of_bedrooms() == roomType)
						freeRooms.add(room);
				}
				else freeRooms.add(room);
			}
		}
		
		return freeRooms;
	}

	public void closeClient()
	{

	}
}
