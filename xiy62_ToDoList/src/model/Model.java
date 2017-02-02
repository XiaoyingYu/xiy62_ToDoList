package model;

import java.util.Vector;
import java.sql.*;

/**
 * Model Class
 * Xiaoying Yu
 * 1/28/2017
 */
public class Model {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://sis-teach-01.sis.pitt.edu:3306/xiy62is1017?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";
	static final String USER = "xiy62is1017";
	static final String PASS = "xiy62@pitt.edu"; 
	private Vector<ListItem> todolist;

	/**
	 * Model Constructor
	 * Initialize vector<ListItem> todolist.
	 * Connect java to database, read record from database and add it into todolist.
	 */
	public Model(){	
		todolist = new Vector<ListItem>();

		Connection conn=null;
		Statement stmt=null;		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Succeeded connecting to the Database!");

			stmt= conn.createStatement();
			String sql;
			sql = "SELECT * FROM xiy62is1017.ToDoList ORDER by id;";
			ResultSet rs = stmt.executeQuery(sql);


			while(rs.next()) {

				int id = rs.getInt("id");
				String description = rs.getString("description");
				java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");
				ListItem list = new ListItem(id," "+description+" ",timestamp);
				todolist.add(list);
			}


			rs.close();
			stmt.close();
			conn.close();
		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null) stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null) conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}


	/**
	 * addListItem method
	 * add a new element in todolist and also upload into databse.
	 */
	public void addListItem(int id,String description,java.sql.Timestamp timestamp){	
		ListItem list = new ListItem(id,description,timestamp);
		todolist.add(list);
		Connection conn=null;
		Statement stmt=null;		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Succeeded connecting to the Database!");

			stmt= conn.createStatement();
			String sql;
			sql="INSERT INTO `ToDoList` VALUES('"+id+"','"+description+"','"+timestamp+"');";
			stmt.executeUpdate(sql);

			stmt.close();
			conn.close();
		}catch(SQLException se){

			se.printStackTrace();
		}catch(Exception e){

			e.printStackTrace();
		}finally{

			try{
				if(stmt!=null) stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null) conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}


	/**
	 * deleteListItem method
	 * delete a element from todolist and also from database.
	 */
	public void deleteListItem(int id1){
		Connection conn=null;
		Statement stmt=null;	
		int position = -1;
		for(int i=0;i<todolist.size();i++){
			if (todolist.get(i).getId()==id1){
				position = i;
			}
		}
		if (position != -1) {
			todolist.remove(position);
			try {

				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Succeeded connecting to the Database!");

				stmt= conn.createStatement();
				String sql;
				sql="DELETE FROM `ToDoList` WHERE id = '"+id1+"';";
				stmt.executeUpdate(sql);

				stmt.close();
				conn.close();
			}catch(SQLException se){

				se.printStackTrace();
			}catch(Exception e){

				e.printStackTrace();
			}finally{

				try{
					if(stmt!=null) stmt.close();
				}catch(SQLException se2){
				}
				try{
					if(conn!=null) conn.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
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
