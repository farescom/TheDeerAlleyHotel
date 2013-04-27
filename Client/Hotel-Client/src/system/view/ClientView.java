package system.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import system.controller.ClientController;

public class ClientView implements Observer
{
   private ClientController controller;

   public ClientView()
   {
   }
   
   public void start(ClientController controller)
   {
      this.controller = controller;
      controller.execute("Server?");
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
	  //String inforamtion = (String)arg1;
	  //System.out.println(inforamtion);
	  controller.execute(arg1);
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