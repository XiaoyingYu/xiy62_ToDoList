package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.ResultSet;

import model.ListItem;
import model.Model;

/**
 * 
 * Create Swing Component
 *
 */
public class View {
	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JButton addButton;
	private JButton deleteButton;
	private JTextField input;
	private JTextField timeStamp;
	private JList<ListItem> toDoL;
	private DefaultListModel<ListItem> toDoList;

	/**
	 * View Constructor
	 * Create GUI swing component
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
		timeStamp = new JTextField("");
		timeStamp.setBounds(20,60,310,25);

		toDoList = new DefaultListModel<ListItem>();
		toDoL = new JList<ListItem>(toDoList);



		toDoL.setBounds(20, 20, 310, 360);


		frame.add(panel1);
		frame.add(panel2);
		panel1.add(input);
		panel1.add(timeStamp);
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
		toDoList.removeAllElements();
		for(int i=0;i<model.getTodolist().size();i++){
			ListItem list = new ListItem(model.getTodolist().get(i).getId(),model.getTodolist().get(i).getDescription(),model.getTodolist().get(i).getTimestamp());
			toDoList.addElement(list);
		}
	}







	/**
	 * 	get a input text in a JTextField.
	 */

	public String getValue(){
		return input.getText();

	}
	public java.sql.Timestamp getTimestamp(){
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
		String tsStr = timeStamp.getText();  
		try {  
			ts = Timestamp.valueOf(tsStr);  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  

		return ts;
	}

	/**
	 * 
	 * get selected list in List component.
	 */
	public String getSelected(){
		return toDoL.getSelectedValue().getId()+toDoL.getSelectedValue().getDescription()+toDoL.getSelectedValue().getTimestamp();

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
	public JList<ListItem> getToDoL() {
		return toDoL;
	}

	public DefaultListModel<ListItem> getToDoList() {
		return toDoList;
	}
	public JTextField getTimeStamp() {
		return timeStamp;





	}
}


