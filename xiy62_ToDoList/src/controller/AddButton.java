package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.sql.ResultSet;

import model.Model;
import view.View;




public class AddButton implements ActionListener{
	private Controller controller;

	public AddButton(Controller con){
		controller=con;	    

	}
	/**
	 * 	get input message from View.
	 *  implement addListItem method to add this message into list and also into database.
	 *  generate a new list displayed in JList.
	 */
	public void actionPerformed(ActionEvent event) {
		String input =" "+ controller.getView().getValue()+" ";
		int size = controller.getModel().getTodolist().size();
		int id = 0;
		if (size ==0)
			id = 1;	
		else 
			id = controller.getModel().getTodolist().get(size-1).getId()+1;
		java.sql.Timestamp ts = controller.getView().getTimestamp();
		controller.getModel().addListItem(id,input,ts);
		controller.getView().refreshlist(controller.getModel());
		controller.getView().getInput().setText("");
		controller.getView().getTimeStamp().setText("");

	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}




}
