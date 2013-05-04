package system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import system.domain.mediator.ServerModelManager;
import system.domain.model.Booking;
import system.domain.model.Guest;
import system.domain.model.Room;
import system.domain.model.SimpleDate;
import system.view.ServerView;

public class ServerController
{
	private ServerModelManager model;
	private ServerView view;

	public ServerController(ServerModelManager model, ServerView view)
	{
		this.model = model;
		this.view = view;
		this.model.addObserver(view);
	}

	public void execute(String what) throws IOException
	{
		what = what.toLowerCase();
		switch(what)
		// here we define all the functionality of our Hoter system for server
		// :)
		{
		case "":
			break;
		case "command":
		case "commands":
			view.Println("Available commands:");
			view.Println("close: closes the program.");
			view.Println("quit: closes the program.");
			view.Println("command: shows available commands.");
			view.Println("commands: shows available commands.");
			view.Println("book: creates new booking (reservation).");
			view.Println("bookings: shows all the bookings (reservations).");
			break;
		case "close":
		case "quit":
			model.closeClient();
			view.Println("The program is closed..");
			System.exit(0);
			break;
		case "book":
			model.Book(getBookingInfo());
			break;
		case "bookings":
			view.Println(model.ListBookings().toString());
			break;
		default:
			view.Println("Unknown command!");
		}
	}

	/**
	 * Ask user to fill Booking information
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public Booking getBookingInfo() throws NumberFormatException, IOException
	{
		Booking booking = new Booking();
		booking.setId(model.getBookingSize());

		booking.setGuest(getGustInfo());

		view.Print("Give the arrival date: ");
		booking.setArrival(getDateInfo());

		view.Print("Give the departure date: ");
		booking.setDeparture(getDateInfo());

		ArrayList<Room> booked_rooms = new ArrayList<Room>();
		do
		{
			// TODO implement showAvailableRooms(by telling the price range,
			// time period, and/or room type). - just a tip for employees
			// e.g.
			// showAvailableRooms(price,booking.getArrival(),booking.getDeparture()...
			// There's already a user story for this

			// for a temporary solution:
			view.Println(model.ListRooms().toString());

			view.Print("Give the room id which you would like to reserve: ");
			int id = Integer.parseInt(view.readLine());
			String tmp = "one more";
			if(model.isRoomAvailable(id))
			{
				booked_rooms.add(model.getRoom(id));
				model.reserveRoom(id);
				view.Print("Do you want to use a special price offer (y/n): ");
				if(view.readLine().equals("y"))
				{
					int price;
					do
					{
						view.Print("Give the special price: ");
						price = Integer.parseInt(view.readLine());
						if(price < model.getRoom(id).get_price())
						{
							model.getRoom(id).set_price(price);
						}
						else
						{
							view.Println("Special price has to be lower than normal price");
						}

					}
					while(price > model.getRoom(id).get_price());
				}

			}
			else
			{
				view.Println("The room is occupied ");
				tmp = "other";
			}

			view.Print("Do you want to book " + tmp + " room (y/n): ");
		}
		while(view.readLine().equals("y"));
		booking.setBooked_rooms(booked_rooms);

		view.Print("Does the guest want extra beds (y/n): ");
		if(view.readLine().equals("y"))
		{
			view.Print("How many extra beds does the guest want");
			booking.setExtra_bed(Integer.parseInt(view.readLine()));
		}

		view.Print("Does the guest want additional friends (y/n): ");
		if(view.readLine().equals("y"))
		{
			view.Print("How many additional guests");
			booking.setNumber_of_guests(Integer.parseInt(view.readLine()));
		}

		return booking;
	}

	/**
	 * Ask user to fill Guest information
	 * 
	 * @return Guest
	 * @throws IOException
	 */
	private Guest getGustInfo() throws IOException
	{
		Guest guest = new Guest();
		view.Print("Give the name of the guest: ");
		guest.setName(view.readLine());
		view.Print("Give the address: ");
		guest.setAddress(view.readLine());
		view.Print("Give the phone number: ");
		guest.setPhone(view.readLine());
		view.Print("Give the date of birth: ");
		guest.setDate_of_birth(view.readLine());
		view.Print("Give the nationality: ");
		guest.setNationality(view.readLine());

		return guest;
	}

	/**
	 * Ask user for date in a string format (dd/MM/yyyy HH:mm:ss)
	 * 
	 * @return Date
	 * @throws IOException
	 */
	private Date getDateInfo() throws IOException
	{
		view.Print("Date format dd/MM/yyyy HH");
		return SimpleDate.getTime(view.readLine()).getTime();
	}
}