package model;

import java.sql.ResultSet;

public class ListItem {
	
	private String description;
	private int id;
	private java.sql.Timestamp timestamp;
	private int fk;
	
	/**
	 * ListItem Constructor
	 * Initialize id, description, timestamp of List.
	 */

	public ListItem(int id,String description,java.sql.Timestamp timestamp, int fk){
		this.description=description;
		this.id=id;
		this.timestamp=timestamp;
		this.fk=fk;
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
	
	
	public int getFk() {
		return fk;
	}

	public void setFk(int fk) {
		this.fk = fk;
	}

	public String toString(){
		return id+" "+description;		
		}

}
