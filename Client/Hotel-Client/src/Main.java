import system.controller.ClientController;
import system.domain.mediator.ClientModelManager;
import system.view.ClientView;

public class Main
{
   public static void main(String args[])
   {
      try
      {
      // creating client
    	 int client_computer_id = 1;
         ClientModelManager model2 = new ClientModelManager(client_computer_id);
         ClientView view2 = new ClientView();
         ClientController controller2 = new ClientController(model2, view2);
         view2.start(controller2);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}