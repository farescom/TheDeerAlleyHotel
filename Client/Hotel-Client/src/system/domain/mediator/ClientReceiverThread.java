package system.domain.mediator;

import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import system.domain.model.SingleRoom;

/* A Thread that receives responses from the Server */
public class ClientReceiverThread extends Thread
{
	ClientModelManager clientModelManager;
	private ObjectInputStream in;
	
	public ClientReceiverThread(ObjectInputStream inFromServer, ClientModelManager clientModelManager) throws ParserConfigurationException, TransformerException
	{
		in = inFromServer;
		this.clientModelManager = clientModelManager;
	}
	
	public void run()
	{
		// create input stream attached to the socket.
		try
		{
			while (true)
			{
			    // receive
				Object data = in.readObject();
				SingleRoom singleRoom = (SingleRoom)data;
				clientModelManager.update(singleRoom);
			}
		}
		catch (IOException | ClassNotFoundException e)
		{
			//e.printStackTrace();
			clientModelManager.update("ServerOff");
		}
	}
}