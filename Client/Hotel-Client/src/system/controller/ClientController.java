package system.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.domain.mediator.ClientModelManager;
import system.view.ClientView;

public class ClientController
{
   private ClientModelManager model;
   private ClientView view;

   public ClientController(ClientModelManager model, ClientView view)
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
	      		view.Println("availability: shows the availability of all rooms.");
	      		view.Println("av: shows the availability of all rooms.");
	      		break;
	      	case "close":
	      	case "quit":
	      		view.Println("The program is closed..");
	      		System.exit(0);
	            break;
	      	case "availability":
	      	case "av":
	      		model.send();
	      		break;
	      	default:
	      		view.Println("Unknown command!");
	      }
   }
}