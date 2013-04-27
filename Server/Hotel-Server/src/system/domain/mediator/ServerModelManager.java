package system.domain.mediator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import system.domain.model.Booking;
import system.domain.model.CheckIn;
import system.domain.model.CheckOut;

public class ServerModelManager extends Observable implements ModelInterface
{
	private ArrayList<Booking> bookings;
	private ArrayList<CheckIn> checkins;
	private ArrayList<CheckOut> checkouts;

   public ServerModelManager() throws IOException, ParserConfigurationException, TransformerException
   {
	   bookings = new ArrayList<Booking>();   
	   checkins = new ArrayList<CheckIn>();   
	   checkouts = new ArrayList<CheckOut>();   
	   
	   ServerConnectionThread server = new ServerConnectionThread(this);
	   server.start();
   }

   // Here we will implement or the functionality, which will be called from controller in execute function.
   // example
   public void Book(Booking booking)
   {
	   bookings.add(booking);
	   super.setChanged();
	   super.notifyObservers("Booking with id "+booking.getId() + " was added.");
   }

   public ArrayList<Booking> ListBookings()
   {
	   return bookings;
   }
   
}