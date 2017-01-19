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
		int position = -1;
		for(int i=0;i<todolist.size();i++){
			if (todolist.get(i).getDescription().equals(description)){
				position = i;
			}
		}
		if (position != -1) {
			todolist.remove(position);
		}

	}
	/**
	 * get a todolist.
	 * 
	 */
	public Vector<ListItem> getTodolist() {
		return todolist;
	}


}
