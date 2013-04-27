package system.domain.mediator;

import java.io.IOException;
import java.util.Observable;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import system.domain.model.SingleRoom;

public class ClientModelManager extends Observable
{
   private Proxy proxy;
   
   public ClientModelManager(int client_computer_id) throws IOException, ParserConfigurationException, TransformerException
   {
	   proxy = new Proxy(this, client_computer_id);
   }
   
   public void update(SingleRoom singleRoom)
   {
	   // method update(�) notifies the observers (in this case the view).
	   // The method will be called from ClientReceiverThread every time there is a new message received from the server.
	      super.setChanged();
	      super.notifyObservers(singleRoom.toString());
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
}