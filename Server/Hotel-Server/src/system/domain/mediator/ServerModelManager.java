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

	public void reserveRoom(int id)
	{
		getRoom(id).setAvailable(false);
		super.setChanged();
		super.notifyObservers("Room with id " + id + " was reserved.");
	}
	
	public int getBookingSize()
	{
		return bookings.size();
	}

	public ArrayList<Booking> ListBookings()
	{
		return bookings;
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

	public void closeClient()
	{

	}

}
