package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ListItem;

public class DeleteButton  implements ActionListener{
	private Controller controller ;
	
	public DeleteButton(Controller con){
		controller=con;
	}
	/**
	 * 	get selected item in todolist in View.
	 *  implement deleteListItem method to remove this message from list and also from database.
	 *  generate a new list displayed in JList on View.
	 */
	public void actionPerformed(ActionEvent event) {
		int id = controller.getView().getToDoL().getSelectedValue().getId();
		controller.getModel().deleteListItem(id);
		controller.getView().refreshlist(controller.getModel());
		
		
	}
	
}
