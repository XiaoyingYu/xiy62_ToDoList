package controller;

import view.View;

import java.awt.event.ActionListener;

import model.Model;
//import actionListener.ActionListener;

public class Controller {

	private View view;
	private Model model;
	private AddButton addButton;
	private DeleteButton deleteButton;
	/**
	 * 	Controller Constructor
	 *  Initial view, model, addButton, and deleteButton.
	 * 
	 */
	public Controller(View view, Model model) {
		this.view=view;
		this.model=model;
		addButton = new AddButton(this);
		deleteButton = new DeleteButton(this);
		view.getAddButton().addActionListener(addButton);
		view.getDeleteButton().addActionListener(deleteButton);


	}


	public View getView() {
		return view;
	}

	public Model getModel() {
		return model;
	}

	public AddButton getAddButton() {
		return addButton;
	}

	public DeleteButton getDeleteButton() {
		return deleteButton;
	}



}
