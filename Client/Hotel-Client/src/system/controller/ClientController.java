package system.controller;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.domain.mediator.ClientModelManager;
import system.view.ClientView;

/** Controller class */
public class ClientController
{
   private ClientModelManager model;
   private ClientView view;

   /** Controller contructor */
   public ClientController(ClientModelManager model, ClientView view)
   {
      this.model = model;
      this.view = view;
      this.model.addObserver(view);
   }
   
   /** A method that receives commands entered by users from View  */
   public void execute(Object what)
   {
	   if (what instanceof String)	// then its just a command
	   {
		  String command = ((String)what).toLowerCase();
	      switch (command)			// here we define all the functionality of our Hoter system for server :)	
	      {
	        case "":
	    	  break;
	        case "server?":		// we have to check the status of server
	          if (model.getStatus() == 0)
	          {
	        	  view.Println("The server is not working.");
	        	  execute("close");
	          }
	          else
	          {
	        	  System.out.println("Hello! Type command to see what you can do.");
	          }
	          break;
	        case "serveroff":		// we have to check the status of server
	        	view.Println("Sorry! The server stopped working!");
		        execute("close"); 
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
	   else
	   {
		   view.Println("An object reveived!");
	   }
   }
}