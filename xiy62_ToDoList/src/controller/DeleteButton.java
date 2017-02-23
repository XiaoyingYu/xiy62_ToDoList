package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ListItem;
import model.Users;

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
		int id=controller.getView().parentId;
		Users u =controller.getView().finduser(controller.getModel());
		controller.getModel().deleteListItem(id);
		controller.getModel().dropConnectDB(id);
		controller.getView().deleteNode();
		
		
		
		
	}
	
}
