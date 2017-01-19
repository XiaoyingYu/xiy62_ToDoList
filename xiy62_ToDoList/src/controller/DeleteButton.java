package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButton  implements ActionListener{
	private Controller controller ;
	
	public DeleteButton(Controller con){
		controller=con;
	}
	public void actionPerformed(ActionEvent event) {
		String output = controller.getView().getSelected();
		controller.getModel().deleteListItem(output);
		controller.getView().refreshlist2();
		
		
	}
	
}
