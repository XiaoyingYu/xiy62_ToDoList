package model;


import java.util.Vector;



public class Model {
	private Vector<ListItem> todolist;
	
	
	public Model(){		
		todolist = new Vector<ListItem>();
	}
	public ListItem addListItem(String description){	
		ListItem list = new ListItem(description);
		todolist.add(list);
		return list;
		}
		
	
	public void deleteListItem(String description){
		ListItem list = new ListItem(description);
		todolist.remove(list);
		
	}
	public Vector<ListItem> getTodolist() {
		return todolist;
	}
	
	
	}
