package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Vector;
import java.sql.ResultSet;

import model.ListItem;
import model.Model;
import model.Users;
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
		java.sql.Timestamp ts = controller.getView().getTimestamp();
		int fk=controller.getView().getParentId();
		int taskid =controller.getModel().addListItemDB(input,ts,fk);
		controller.getView().addNewNode(taskid);
		controller.getModel().addItem(taskid, input, ts, fk);
		Users u =controller.getView().finduser(controller.getModel());
		controller.getModel().addConnectDb(u, taskid);
		for(int i=0;i<controller.getModel().getTodolist().size();i++){
			if(controller.getModel().getTodolist().get(i).getId()==taskid){
				ListItem list = new ListItem(controller.getModel().getTodolist().get(i).getId(),controller.getModel().getTodolist().get(i).getDescription(),controller.getModel().getTodolist().get(i).getTimestamp(),controller.getModel().getTodolist().get(i).getFk());
				controller.getModel().addConnect(u, list);
			}
		}	
	
		
		controller.getView().getInput().setText("");
	
		

	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}




}
