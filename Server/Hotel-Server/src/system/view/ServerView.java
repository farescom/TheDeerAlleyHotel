package system.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import system.controller.ServerController;

public class ServerView implements Observer
{
   private ServerController controller;

   public ServerView()
   {
   }
   
   public void start(ServerController controller)
   {
      this.controller = controller;
      
      try
      {
    	  BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
    	  String command;
    	  while (true)
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

   @Override
   public void update(Observable arg0, Object arg1)
   {
	  String inforamtion = (String)arg1;
	  System.out.println(inforamtion);
   }
   
   public void Println(String text)
   {
	   System.out.println(text);
   }
   
   public void Print(String text)
   {
	   System.out.print(text);
   }
}