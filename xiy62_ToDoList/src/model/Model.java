package model;

import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
	private Vector<ListItem>l1;
	private Vector<ListItem>l2;
	private Vector<ListItem>l3;
	private Vector<Users> userlist;
	private HashMap<Users, Vector<ListItem>> idConnect;
	private Statement stmt;
	private int taskid;


	/**
	 * Model Constructor
	 * Initialize vector<ListItem> todolist.
	 * Connect java to database, read record from database and add it into todolist.
	 */
	public Model(){	
		todolist = new Vector<ListItem>();
		userlist = new Vector<Users>();
		l1 =new Vector<ListItem>();
		l2 =new Vector<ListItem>();
		l3 =new Vector<ListItem>();
		idConnect = new HashMap<Users, Vector<ListItem>>();




		Connection conn=null;
		Statement stmt=null;		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Succeeded connecting to the Database!");

			stmt= conn.createStatement();
			String sql;
			sql = "SELECT * FROM xiy62is1017.ToDoList ORDER by taskID;";
			ResultSet rs = stmt.executeQuery(sql);



			while(rs.next()) {

				int id = rs.getInt("taskID");
				String description = rs.getString("description");
				java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");
				int fk = rs.getInt("belongs");
				ListItem list = new ListItem(id," "+description+" ",timestamp,fk);
				todolist.add(list);
				System.out.println(todolist);
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

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Succeeded connecting to the Database!");

			stmt= conn.createStatement();
			String sql2;
			sql2 =  "SELECT * FROM xiy62is1017.users ORDER by userID;";
			ResultSet rs2 = stmt.executeQuery(sql2);

			while(rs2.next()) {

				int id = rs2.getInt("userID");
				String firstname = rs2.getString("user_firstname");
				String lastname = rs2.getString("user_lastname");
				Users user = new Users(id," "+firstname+" ",lastname);
				System.out.println(firstname);
				userlist.add(user);

			}

			rs2.close();
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
	public int addListItemDB(String description,java.sql.Timestamp timestamp,int fk){	
		
		int taskid=0;
		Connection conn=null;
		PreparedStatement stmt=null;	
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Succeeded connecting to the Database!");
			
			String sql="INSERT INTO `ToDoList`(`description`,`timestamp`,`belongs`) VALUES('"+description+"','"+timestamp+"','"+fk+"');";
			PreparedStatement prest;
			prest = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        //prest.setString(1,list.getDescription());
	        //prest.setTimestamp(2, list.getTimestamp());
	       // prest.setInt(3, fk);
	        prest.executeUpdate();
	        ResultSet rs = prest.getGeneratedKeys();
	        if(rs.next()){
                taskid = rs.getInt(1);
            }

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
		return taskid;
	}
	public void addItem(int taskid, String description,java.sql.Timestamp timestamp, int fk){
	ListItem list = new ListItem(taskid,description,timestamp,fk);
	todolist.add(list);
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
				sql="DELETE FROM `ToDoList` WHERE taskID = '"+id1+"';";
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
	public void addConnect(Users users,ListItem list){
		ListItem ltemp =list;
		if(users.getId()==1){
			l1.add(ltemp);
			idConnect.remove(users);
			idConnect.put(users,l1);
		}
		if(users.getId()==2){
			l2.add(list);
			idConnect.remove(users);
			idConnect.put(users, l2);
		}
		if(users.getId()==3){
			l3.add(list);
			idConnect.remove(users);
			idConnect.put(users, l3);
		}
		
	}
	/**
	 * add a connect of task id and list id into database.
	 * 
	 */
	public void addConnectDb(Users users,int id){
		Connection conn=null;
		Statement stmt = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Succeeded connecting to the Database!");
			
			stmt= conn.createStatement();
			String sql;
			sql="INSERT INTO `todo_user` VALUES('"+users.getId()+"','"+id+"');";
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
	 * drop connection from hashmap.
	 */

	public void dropConnect(ListItem list){
		if(l1.contains(list)){
			l1.remove(list);
			System.out.println(l1.size());
			Users u =new Users(1,"","");
			for(int i=0;i<userlist.size();i++){
				if(userlist.get(i).getId()==1){
					u =new Users(userlist.get(i).getId(),userlist.get(i).getFirstname(),userlist.get(i).getLastname());
				}
				idConnect.remove(u);
				idConnect.put(u,l1);
			}
		}
		if(l2.contains(list)){
			l2.remove(list);
			System.out.println(l2.size());
			Users u =new Users(1,"","");
			for(int i=0;i<userlist.size();i++){
				if(userlist.get(i).getId()==2){
					u =new Users(userlist.get(i).getId(),userlist.get(i).getFirstname(),userlist.get(i).getLastname());
				}
				idConnect.remove(u);
				idConnect.put(u,l2);
			}
		}
		if(l3.contains(list)){
			l3.remove(list);
			System.out.println(l3.size());
			Users u =new Users(1,"","");
			for(int i=0;i<userlist.size();i++){
				if(userlist.get(i).getId()==3){
					u =new Users(userlist.get(i).getId(),userlist.get(i).getFirstname(),userlist.get(i).getLastname());
				}
				idConnect.remove(u);
				idConnect.put(u,l3);
			}
		}
	}
   /**
    * drop connection from database.
    */

	public void dropConnectDB(int taskId){
		Connection conn=null;
		Statement stmt=null;
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Succeeded connecting to the Database!");

			stmt= conn.createStatement();
			String sql;
			sql="DELETE FROM `todo_user` WHERE TaskID = '"+taskId+"';";
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
	 * 
	 * Transfer Vector<ListItem> that read from database into DefaultMutableTreeNode.
	 */
	public Map<Integer, DefaultMutableTreeNode> createTreeNode(Vector<ListItem> todoList) {
        Map<Integer, ListItem> mapIdItem = new HashMap<Integer, ListItem>();
        Map<Integer, DefaultMutableTreeNode> mapIdTreeNode = new HashMap<Integer, DefaultMutableTreeNode>();
        for (ListItem li : todoList) {
                        mapIdItem.put(li.getId(), li);
                        mapIdTreeNode.put(li.getId(), new DefaultMutableTreeNode(li));
        }
       
        for (Integer id: mapIdItem.keySet()) {
                        DefaultMutableTreeNode treeNode = mapIdTreeNode.get(id);
                        int parentID = mapIdItem.get(id).getFk();
                        if (parentID == 0) {
                                        treeNode.setParent(null);
                        }
                        else {
                                        DefaultMutableTreeNode parentNode = mapIdTreeNode.get(parentID);
                                        parentNode.add(treeNode);
                                        treeNode.setParent(parentNode);
                        }
        }
       
        return mapIdTreeNode;   
      
	}
	
	

	public int getId(){
		int size = todolist.size();
		int id = 0;
		if (size ==0){
			id = 1;	
		}else{ 
			id = todolist.get(size-1).getId()+1;
		}
		return id;
	}





	public Vector<ListItem> getTodolist() {
		return todolist;
	}

	public Vector<Users>getUserslist(){
		return userlist;
	}

	public HashMap getIdConnect() {
		return idConnect;
	}


	public Vector<ListItem> getL1() {
		return l1;
	}


	public void setL1(Vector<ListItem> l1) {
		this.l1 = l1;
	}


	public Vector<ListItem> getL2() {
		return l2;
	}


	public void setL2(Vector<ListItem> l2) {
		this.l2 = l2;
	}


	public Vector<ListItem> getL3() {
		return l3;
	}


	public void setL3(Vector<ListItem> l3) {
		this.l3 = l3;
	}











}
