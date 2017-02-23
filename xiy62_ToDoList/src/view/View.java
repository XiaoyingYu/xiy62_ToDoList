package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.DefaultTreeModel;

import model.ListItem;
import model.Model;
import model.Users;

/**
 * 
 * Create Swing Component
 *
 */
public class View {
	private JFrame frame;
	private JPanel panel1;
	private JPanel panel3;
	private JLabel task;
	private JLabel userID;
	private JButton addButton;
	private JButton deleteButton;
	private JTextField input;
	//private JList<ListItem> toDoL;
	//private DefaultListModel<ListItem> toDoList;
	private JComboBox user;
	private DefaultMutableTreeNode top;
	private DefaultMutableTreeNode list;
	private Model model;
	private ListItem item;
	private JScrollPane jspMain;
	private JTree todoTree;
	private int nodeId;
	private DefaultMutableTreeNode selectedNode;
	public Map<Integer, DefaultMutableTreeNode> treeNodeMap;
	public int parentId;
	private DefaultTreeModel m;

	/**
	 * View Constructor Create GUI swing component
	 */

	public View(Model model) {
		this.model = model;


		frame = new JFrame("To Do List");
		frame.setBounds(100, 100, 800, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel1 = new JPanel();
		panel1.setBounds(20, 10, 350, 400);
		panel1.setLayout(null);
		panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("A New Event"));
		panel3 = new JPanel();
		panel3.setBounds(390, 10, 350, 400);
		panel3.setLayout(null);
		panel3.setBorder(javax.swing.BorderFactory.createTitledBorder("To Do Tree"));
		addButton = new JButton("Add to List");
		addButton.setBounds(70, 430, 200, 25);
		deleteButton = new JButton("Delete Selected Event");
		deleteButton.setBounds(470, 430, 200, 25);
		task = new JLabel("Input new tasks AFTER select a task node");
		task.setBounds(20, 20, 350, 25);
		input = new JTextField("");
		input.setBounds(20, 60, 310, 30);
		userID = new JLabel("userID");
		userID.setBounds(20, 140, 100, 25);
		String label[] = { "1", "2", "3" };
		user = new JComboBox(label);
		user.setBounds(20, 170, 100, 30);

		/**
		 * call the createTreeNode that created in model class.
		 * And display the tree into Jtree.
		 */

		treeNodeMap = model.createTreeNode(model.getTodolist());

		Timestamp ts = new Timestamp(System.currentTimeMillis());
		ListItem topList=new ListItem(0," Todo List Tree",ts,-1);
		
		top = new DefaultMutableTreeNode(topList);
		for (Integer id : treeNodeMap.keySet()) {
			if (treeNodeMap.get(id).getParent() == null) {
				top.add(treeNodeMap.get(id));
			}
		}

		list=new DefaultMutableTreeNode("Tree");
		list.add(top);

		
		todoTree = new JTree(list);
		todoTree.setEditable(true);
		todoTree.setBounds(20, 20, 310, 360);

		/**
		 * add addTreeSelectionListene to get selected task node.
		 */
		todoTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				selectedNode = (DefaultMutableTreeNode)todoTree.getLastSelectedPathComponent(); 

				ListItem item=(ListItem)(selectedNode.getUserObject());
				parentId = item.getId();	
			}
		});
		m=(DefaultTreeModel) todoTree.getModel();
		todoTree.setRootVisible(false);	
		frame.add(panel1);
		frame.add(panel3);
		panel1.add(input);
		panel1.add(task);
		panel1.add(user);
		panel1.add(userID);
		panel3.add(todoTree);
		frame.add(addButton);
		frame.add(deleteButton);
		frame.setVisible(true);
	}

	



	/**
	 * find a user according to user id.
	 */
	public Users finduser(Model model) {
		String temp = user.getSelectedItem().toString();
		int userid = Integer.parseInt(temp);
		Users u = new Users(1, "", "");
		for (int i = 0; i < model.getUserslist().size(); i++) {
			if (model.getUserslist().get(i).getId() == userid) {
				u = new Users(model.getUserslist().get(i).getId(), model.getUserslist().get(i).getFirstname(),
						model.getUserslist().get(i).getLastname());

			}
		}
		return u;

	}

	/**
	 * Add a new task into Jtree.
	 * 
	 */
	public  void addNewNode(int id){	
		String description=input.getText();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		if(selectedNode!=null&&selectedNode.getParent()!=null){
			ListItem l =new ListItem(id,description,ts,parentId);
			DefaultMutableTreeNode newList = new DefaultMutableTreeNode(l);
			System.out.println(selectedNode.getParent());
			selectedNode.add(newList);
			m.reload(selectedNode);
			selectedNode=null;
		
		}else{
			System.out.println("Please select a task node to insert a new task.");
		}


	}
	/**
	 * delete a selected task from Jtree.
	 */
	public void deleteNode(){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode.getParent();
		if (parentId==0) {	   
			System.out.println("a main menu cannot be deleted");
		}else{
			try{
				selectedNode.removeFromParent();
				m.reload(node);
				selectedNode= null;
			}catch(Exception e){
			}
		}
	}




	/**
	 * get a input text in a JTextField.
	 */

	public String getValue() {
		return input.getText();
	}

	public java.sql.Timestamp getTimestamp() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return ts;
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





	public JComboBox getUser() {
		return user;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


}


