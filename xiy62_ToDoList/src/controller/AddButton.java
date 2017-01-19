package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import model.Model;
import view.View;




public class AddButton implements ActionListener{
	private Controller controller;
	
	public AddButton(Controller con){
		controller=con;	    
		
	}
	
	public void actionPerformed(ActionEvent event) {
		String input = controller.getView().getValue();
		controller.getModel().addListItem(input);
		controller.getView().refreshlist(controller.getModel());
		controller.getView().getInput().setText("");
	
	}
	public Controller getController() {
		return controller;
	}
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	
	
	
}
