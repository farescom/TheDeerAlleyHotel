package system.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import system.controller.ServerController;
import system.domain.model.Booking;
import system.domain.model.Guest;
import system.domain.model.Room;
import system.domain.model.SimpleDate;

/** A class that represents the console */
public class ServerView implements Observer
{
	private ServerController controller;
	private BufferedReader bufferRead;
	private String command;

	public ServerView()
	{
		bufferRead = new BufferedReader(new InputStreamReader(System.in));
	}

	/** A method that gets a controller and set it in View class
	 * @param controller Controller class */
	public void start(ServerController controller)
	{
		this.controller = controller;
		try
		{
			while(true)
			{
				command = bufferRead.readLine();
				controller.execute(command);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/** A method called by Observavles, to update the view */
	@Override
	public void update(Observable arg0, Object arg1)
	{
		String inforamtion = (String) arg1;
		System.out.println(inforamtion);
	}
	
	/** A method that reads a line from console */
	public String readLine() throws IOException
	{
		return bufferRead.readLine();
	}
	
	/** A method that prints line with a new line char*/
	public void Println(String text)
	{
		System.out.println(text);
	}

	/** A method that prints line */
	public void Print(String text)
	{
		System.out.print(text);
	}

}