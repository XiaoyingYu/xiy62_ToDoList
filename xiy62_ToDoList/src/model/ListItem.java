package model;

import java.sql.ResultSet;

public class ListItem {
	
	private String description;
	private int id;
	private java.sql.Timestamp timestamp;
	
	/**
	 * ListItem Constructor
	 * Initialize id, description, timestamp of List.
	 */

	public ListItem(int id,String description,java.sql.Timestamp timestamp){
		this.description=description;
		this.id=id;
		this.timestamp=timestamp;
}

	public String getDescription() {
	return description;
	}

	public void setDescription(String description) {
	this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString(){
		return id+description+timestamp;		
		}

}
