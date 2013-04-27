package system.controller;

import system.domain.mediator.ServerModelManager;
import system.domain.model.Booking;
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

   public void execute(String what)
   {
	  what = what.toLowerCase();
      switch (what)			// here we define all the functionality of our Hoter system for server :)	
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
      		view.Println("The program is closed..");
      		System.exit(0);
            break;
      	case "book":
      		// here we have to ask the user for more information, then create booking and 
      		// send it to the model manager
      		model.Book(new Booking());
      		break;
      	case "bookings":
      		view.Println(model.ListBookings().toString());
      		break;
      	default:
      		view.Println("Unknown command!");
      }
   }
}