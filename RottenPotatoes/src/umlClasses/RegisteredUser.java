/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umlClasses;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

/**
 *
 * @author mounica
 */
public class RegisteredUser extends User {
    
    private int id;
    private String username;
    private String email,password;
    private List<Comments> comment_list = new ArrayList<Comments>();
    private List<UserGenre> Genre = new ArrayList<UserGenre>();
    private boolean has_access;
    private String firstName;
    private String lastName;
    private Connection conn;
    
    public RegisteredUser(Connection con, int userId) throws Exception {
    	this.conn = con;
    	this.id = userId;
    	PreparedStatement getUser = con.prepareStatement("select * from RegisteredUser ru where ru.id = ?");
    	Utils.printDatabaseWarning(getUser.getWarnings());
    	try {
    		getUser.setInt(1, userId);
    		ResultSet rsUser = getUser.executeQuery();
    		Utils.printQueryWarning(getUser.getWarnings());
    		if (rsUser.next()) {
    			this.username = rsUser.getString(2);
    			this.password = rsUser.getString(3);
    			this.email = rsUser.getString(4);
    			this.firstName = rsUser.getString(5);
    			this.lastName = rsUser.getString(6);
    			this.has_access = rsUser.getBoolean(7);
    		} else {
    			throw new Exception("User not found!");
    		}
    	} finally {
    		getUser.close();
    	}
    }
    
    public RegisteredUser(int id, String username, String email, String password, List<Comments> comment_list,
			List<UserGenre> genre, boolean has_access, String firstName, String lastName, Connection conn) {
		super(id);
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.comment_list = comment_list;
		Genre = genre;
		this.has_access = has_access;
		this.firstName = firstName;
		this.lastName = lastName;
		this.conn = conn;
	}
    
    
    public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public int getid(){
        return id;
    }
    
    public void setid(int userid){
        this.id = userid;
    }
    
    public String getusername(){
        return username;
    }
    public void setusername(String u){
        this.username = u;
    }
    
    public String getemail(){
        return email;
    }
    public void setemail(String e){
        this.email = e;
    }
    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    public Boolean get_access(){
        return has_access;
    }
    
   

	public void set_Access(Boolean a){
        this.has_access = a;
    }
    
     public List<UserGenre> getBenefit ()
        {
            return Genre;
        }
    
    public List<Comments> getcomment ()
        {
            return comment_list;
        }
        
    public String getfname(){
        return firstName;
    }
    
    public void setfname(String fname){
        this.firstName = fname;
    }
    
    public String getlname(){
        return lastName;
    }
    
    public void setlname(String lname){
        this.lastName = lname;
    }
  
    
    public RegisteredUser(int id){
        super(id);
    }
    
    public int getUserAuthentication(Connection con, String username, String password) throws Exception{
    	int result = 0;
    	PreparedStatement getUser = con.prepareStatement("select id from RegisteredUser where username = ? and password = ?");
    	Utils.printDatabaseWarning(getUser.getWarnings());
    	try {
    		getUser.setString(1, username);
    		getUser.setString(2, password);
    		ResultSet rsAu = getUser.executeQuery();
    		Utils.printQueryWarning(getUser.getWarnings());
    		if (rsAu.next()) {
    			result = rsAu.getInt(1);
    		} else {
    			result = 0;
    		}
    	} finally {
    		getUser.close();
    	}
    	return result;
    }
 
    
    public RegisteredUser createUser(Connection con, String username,String password, 
            String email, String firstname, String lastname)throws SQLException{
    	RegisteredUser r = null;
        this.conn = con;
        PreparedStatement checkUser = conn.prepareStatement
        ("select username, email from RegisteredUser where username =? or email = ?");
        PreparedStatement createUser = conn.prepareStatement
        ("insert into RegisteredUser(id,username,password,email,firstName,lastName,hasAccess) values(?,?,?,?,?,?)");
        SQLWarning warning = checkUser.getWarnings();
        while(warning != null){
            System.out.println("Database warning: "+warning);
        }
        SQLWarning warning1 = createUser.getWarnings();
        while(warning1 != null){
            System.out.println("Database warning: "+warning1);
        }
        try{
            ResultSet rs = checkUser.executeQuery();
            if(rs.next()){
                throw new Exception("User exists with the same username or email");
            }else{
             r = new RegisteredUser();  
             createUser.setString(2,username);
             createUser.setString(3, password);
             createUser.setString(4,email);
             createUser.setString(5,firstname);
             createUser.setString(6,lastname);
             createUser.setBoolean(7, true);
             createUser.executeUpdate();
             int userid = getNewUserId(con);
             r.setid(userid);
             r.setusername(username);
             r.setPassword(password);
             r.setemail(email);
             r.setfname(firstname);
             r.setlname(lastname);
             r.set_Access(true);
             
            }
            
        }catch(Exception e){
            
        }finally{
        	createUser.close();
        }
        return r;
    }
    
    public int getNewUserId(Connection con) throws Exception{
    	this.conn = con;
		int newId = 0;
		PreparedStatement getMaxId = conn.prepareStatement("select if(max(id)+1 is null, 1, max(id) + 1) from RegisteredUser");
		Utils.printDatabaseWarning(getMaxId.getWarnings());
		try {
			ResultSet rs = getMaxId.executeQuery();
			Utils.printQueryWarning(getMaxId.getWarnings());
			if (rs.next()) {
				newId = rs.getInt(1);
			} else {
				throw new Exception("Invalid id!");
			}
		} finally {
			getMaxId.close();
		}
		return newId;
	}
    
    public RegisteredUser(){
    	super();
    }
    
    public RegisteredUser getUser(Connection connection, String username, String password) throws SQLException{
    	RegisteredUser r=null;
        int userid;
        this.conn = connection;
        PreparedStatement getUser = conn.prepareStatement
        ("select id,username,password,email,hasAccess,firstName,lastName "
                + "from  RegisteredUser where username = ? and password = ?");
        PreparedStatement getGenres = conn.prepareStatement
                ("select u.genre from UserGenre u, RegisteredUser r where u.user = r.id and r.username = ?"
                        + "and r.password = ?");
        PreparedStatement getcomments = conn.prepareStatement
        ("select c.commentText, c.commentTime from Comment c, UserComment u where c.id = u.comment and u.commentedOnBy = ?");
        SQLWarning warning = getUser.getWarnings();
        while(warning != null){
            System.out.println("Database warning: " + warning);
        }
        try{
            
            getUser.setString(1,username);
            getUser.setString(2,password);
            getGenres.setString(1,username);
            getGenres.setString(2,password);
            ResultSet rs = getUser.executeQuery();
            SQLWarning querywarning = getUser.getWarnings();         
            while(querywarning != null){
                System.out.println("Query warning: " + querywarning);
            }
            
            while(rs.next()){
            	r = new RegisteredUser();
                userid = rs.getInt(1);
                r.id = userid;
                r.username = rs.getString(2);
                r.setPassword(rs.getString(3));
                r.email = rs.getString(4);
                r.has_access = rs.getBoolean(5);
                r.firstName = rs.getString(6);
                r.lastName = rs.getString(7);
                ResultSet g = getGenres.executeQuery();
                SQLWarning querywarning1 = getGenres.getWarnings();
                while(querywarning1 != null){
                System.out.println("Query warning: " + querywarning);
            }
                while(g.next()){
                    UserGenre ug = new UserGenre();
                    UserGenre ug1 = ug.getug(GenreType.valueOf(g.getString(1)));
                    Genre.add(ug1);
                    r.Genre = Genre;
                }
                getcomments.setInt(1, userid);
                ResultSet c = getcomments.executeQuery();
                while(c.next()){
                    String text = rs.getString(1);
                    Date d = rs.getDate(2);
                    Comments co = new Comments();
                    Comments comm = co.getComment(text,d);
                    comment_list.add(comm);
                    r.comment_list = comment_list;
                }
                
            }
            
            
        }catch(Exception e){
            System.out.println("Invalid entries "+ e);
        }finally{
            getUser.close();
        }
        
       return r;

    }

    public RegisteredUser getUserById(Connection connection, int id) throws SQLException{
    	RegisteredUser r=null;
        this.conn = connection;
        PreparedStatement getUser = conn.prepareStatement
        		("select username,password,email,hasAccess,firstName,lastName "
                + "from  RegisteredUser where id=?");
        PreparedStatement getGenres = conn.prepareStatement
                ("select u.genreType from UserGenre u where u.user = ?");
        PreparedStatement getcomments = conn.prepareStatement
        ("select c.commentText, c.commentTime from Comment c, UserComment u where c.id = u.comment and u.commentedOnBy = ?");
        SQLWarning warning = getUser.getWarnings();
        while(warning != null){
            System.out.println("Database warning: " + warning);
        }
        try{
            
            getUser.setInt(1,id);
            getGenres.setInt(1,id);
            getcomments.setInt(1, id);
            ResultSet rs = getUser.executeQuery();
            Utils.printQueryWarning(rs.getWarnings());
            
            while(rs.next()){
            	r = new RegisteredUser();
                r.setid(id);
                r.setusername(rs.getString(1));
                r.setPassword(rs.getString(2));
                r.setemail(rs.getString(3));
                r.set_Access(rs.getBoolean(4));
                r.setfname(rs.getString(5));
                r.setlname(rs.getString(6));
                ResultSet g = getGenres.executeQuery();
                Utils.printQueryWarning(g.getWarnings());
                while(g.next()){
                    UserGenre ug = new UserGenre();
                    UserGenre ug1 = ug.getug(GenreType.valueOf(g.getString(1)));
                    Genre.add(ug1);
                    r.Genre = Genre;
                }
                ResultSet c = getcomments.executeQuery();
                Utils.printQueryWarning(c.getWarnings());
                while(c.next()){
                    String text = rs.getString(1);
                    Date d = rs.getDate(2);
                    Comments co = new Comments();
                    Comments comm = co.getComment(text,d);
                    comment_list.add(comm);
                    r.comment_list = comment_list;
                }
                
            }
            
            
        }catch(Exception e){
            System.out.println("Invalid entries "+ e);
        }finally{
            getUser.close();
        }
        
       return r;

    }

    public RegisteredUser updateUser(Connection con, int id, String username,String password, 
            String email, String firstname, String lastname) throws Exception{
    	RegisteredUser r = null;
		try {
			this.conn = con;
			PreparedStatement updateuser = conn.prepareStatement("Update RegisteredUser set username = ? "
					+" password = ? , email= ? , firstName=? , lastName = ?  "
					+"  where id = ? ");
			
			
			SQLWarning warnings;
			warnings = updateuser.getWarnings();
			while(warnings!=null){
				System.err.println("Database Warnings! "+warnings);
			}
			updateuser.setString(1, username);
			updateuser.setString(2, password);
			updateuser.setString(3, email);
			updateuser.setString(4, firstname);
			updateuser.setString(5, lastname);
			updateuser.setInt(6, id);
			int updateCount = updateuser.executeUpdate();
			r = new RegisteredUser();
			SQLWarning updateWar;
			updateWar = updateuser.getWarnings();
			while(updateWar!=null){
				System.err.println("Database Warnings! "+updateWar);
			}
			
			if(updateCount!=1){
				throw new Exception("No records to be updated");
			}
			r.setusername(username);
			r.setPassword(password);
			r.setemail(email);
			r.setfname(firstName);
			r.setlname(lastname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
    
    public ArrayList<RegisteredUser> getExistingUsers(Connection con) throws Exception {
		ArrayList<RegisteredUser> userList = new ArrayList<RegisteredUser>();
		PreparedStatement getRegisteredUsers = con.prepareStatement("select username,password,email,hasAccess,firstName,lastName "
				+ "from RegisteredUser");
		Utils.printDatabaseWarning(getRegisteredUsers.getWarnings());
		try {
			ResultSet rsUser = getRegisteredUsers.executeQuery();
			Utils.printQueryWarning(getRegisteredUsers.getWarnings());
			if (!rsUser.next()) {
				throw new Exception("No events exist");
			} else {
				rsUser.beforeFirst();
				while(rsUser.next()) {
					RegisteredUser rs = new RegisteredUser();
//					rs.setId(rsUser.getInt(1));
					rs.setusername(rsUser.getString(1));
					rs.setemail(rsUser.getString(2));
					rs.setPassword(rsUser.getString(3));
					rs.set_Access(rsUser.getBoolean(6));
					userList.add(rs);
				}
			}
		} finally {
			getRegisteredUsers.close();
		}
		return userList;
	}
    
    public String getUserNameById(Connection con, int userId) throws Exception {
    	String name = "";
    	PreparedStatement getName = con.prepareStatement("select username from RegisteredUser where id = ?");
    	Utils.printDatabaseWarning(getName.getWarnings());
    	try {
    		getName.setInt(1, userId);
    		ResultSet rsName = getName.executeQuery();
    		Utils.printQueryWarning(getName.getWarnings());
    		if (rsName.next()) {
    			name = rsName.getString(1);
    		} else {
    			throw new Exception("User not found!");
    		}
    	} finally {
    		getName.close();
    	}
    	return name;
    }
    
}
