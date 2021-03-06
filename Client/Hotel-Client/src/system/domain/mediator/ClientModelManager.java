package system.domain.mediator;

import java.io.IOException;
import java.util.Observable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import system.domain.model.SingleRoom;

/** a facade for the Model */
public class ClientModelManager extends Observable
{
	private int status = 1;		// 0- server not working   1- server working
    private Proxy proxy;
   
   /** ClientModelManager contructor */
   public ClientModelManager(int client_computer_id)
   {
	   
	   try
	   {
		   proxy = new Proxy(this, client_computer_id);
	   }
	   catch (IOException | ParserConfigurationException | TransformerException e)
	   {
		   //e.printStackTrace();
		   //System.out.println("The server is off. Program is closing.");
		   //update("serverOff");
		   status = 0;
	   }
   }
   
   public void update(Object object)
   {
	   // method update(�) notifies the observers (in this case the view).
	   // The method will be called from ClientReceiverThread every time there is a new message received from the server.
	      super.setChanged();
	      super.notifyObservers(object);
   }
   
   public void send()
   {
	   try
	   {
		   proxy.send();
	   }
	   catch (IOException | ParserConfigurationException | TransformerException e)
	   {
		   e.printStackTrace();
	   }
   }
   
   public int getStatus()
   {
	   return status;
   }
}