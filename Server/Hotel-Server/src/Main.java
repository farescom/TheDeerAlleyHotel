import system.controller.ServerController;
import system.domain.mediator.ServerModelManager;
import system.view.ServerView;

public class Main
{
   public static void main(String args[])
   {
      try
      {
	  //Question: Is it enough to have server and recetion in the same app, or
	  //		  we have to create server app, and normal application??
    	  
      // creating serverS
    	  System.out.println("DUPA");
         ServerModelManager model = new ServerModelManager();
         ServerView view = new ServerView();
         ServerController controller = new ServerController(model, view);
         view.start(controller);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}