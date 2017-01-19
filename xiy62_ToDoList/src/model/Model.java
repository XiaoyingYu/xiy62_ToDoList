package model;

import java.util.Vector;


/**
 * Model Class
 * Xiaoying Yu
 * 1/18/2017
 */
public class Model {
	private Vector<ListItem> todolist;


	public Model(){		
		todolist = new Vector<ListItem>();
	}
	/**
	 * addListItem method
	 * add a new element in todolist.
	 */
	public ListItem addListItem(String description){	
		ListItem list = new ListItem(description);
		todolist.add(list);
		return list;
	}

	/**
	 * deleteListItem method
	 * delete a element from todolist.
	 */
	public void deleteListItem(String description){
		ListItem list = new ListItem(description);
		todolist.remove(list);

	}
	/**
	 * get a todolist.
	 * 
	 */
	public Vector<ListItem> getTodolist() {
		return todolist;
	}


}
