package view;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ListItem;
import model.Model;


public class View {
	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JButton addButton;
	private JButton deleteButton;
	private JTextField input;
	private List toDoL;

	/**
	 * View Constructor
	 * Create GUI component
	 */

	public View(){

		frame = new JFrame("To Do List");
		frame.setBounds(100,100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel1 = new JPanel();
		panel1.setBounds(20,10,350, 100);
		panel1.setLayout(null);
		panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("A New Event"));
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(390,10,350,400);
		panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("To Do List"));
		addButton = new JButton("Add to List");
		addButton.setBounds(70,270,200,25);
		deleteButton = new JButton("Delete Selected Event");
		deleteButton.setBounds(470,430,200,25);
		input= new JTextField("");
		input.setBounds(20,20,310,25);

		toDoL = new List(10,true);
		toDoL.setBounds(20, 20, 310, 360);


		frame.add(panel1);
		frame.add(panel2);
		panel1.add(input);
		panel2.add(toDoL);
		frame.add(addButton);
		frame.add(deleteButton);
		frame.setVisible(true);	
	}
	/**
	 * a refresh method to get a new todolist.
	 * 
	 */
	public void refreshlist(Model model){
		toDoL.removeAll();
		for(int i=0;i<model.getTodolist().size();i++){
			toDoL.add(model.getTodolist().get(i).getDescription());
		}
	}

	public void refreshlist2(){
		toDoL.remove(getSelected());

	}

	/**
	 * 	get a input text in a JTextField.
	 */

	public String getValue(){
		return input.getText();

	}
	/**
	 * 
	 * get selected list in List component.
	 */
	public String getSelected(){
		return toDoL.getSelectedItem();

	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}


	public JTextField getInput() {
		return input;
	}

	public List getToDoL() {
		return toDoL;
	}


}


