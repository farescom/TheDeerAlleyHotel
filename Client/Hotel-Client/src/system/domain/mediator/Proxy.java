package system.domain.mediator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import system.domain.model.Query;
import system.domain.model.SingleRoom;

/* A class that makes a connection with the server */
public class Proxy
{
	private static int PORT = 2012;
	private static String HOST = "localhost";
	private Socket clientSocket;
	private ObjectInputStream inFromServer;
	private ObjectOutputStream outToServer;
	private int client_computer_id;
	private int counter = 0;
	ClientModelManager clientModelManager;
    
	/* Proxy constructor */
	public Proxy(ClientModelManager clientModelManager, int client_computer_id) throws UnknownHostException, IOException, ParserConfigurationException, TransformerException
	{
		this.clientModelManager = clientModelManager;
		this.client_computer_id = client_computer_id;
		clientSocket = new Socket(HOST, PORT);
		outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
		inFromServer = new ObjectInputStream(clientSocket.getInputStream());
	}
	
	/* A method responsible for connecting with the server and delegting the task of waiting for responses to ClientReceiverThread*/
	public void send() throws IOException, ParserConfigurationException, TransformerException
	{
		Query query = new Query(client_computer_id, Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
		outToServer.writeObject(query);
		
	    if (counter == 0) // we create a Thread when we first time call the server
	    {
	    	 ClientReceiverThread clientrt = new ClientReceiverThread(inFromServer, clientModelManager);
			 clientrt.start();
			 counter++;
	    }
	}
}
