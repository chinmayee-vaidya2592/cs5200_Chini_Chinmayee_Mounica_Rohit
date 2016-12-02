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
    
    public void createUser(Connection con, String username,String password, 
            String email, String firstname, String lastname)throws SQLException{
        this.conn = con;
        PreparedStatement checkUser = conn.prepareStatement
        ("select username, email from RegisteredUser where username =? or email = ?");
        PreparedStatement createUser = conn.prepareStatement
        ("insert into RegisteredUser(id,username,password,email,firstName,lastName,hasAccess) values(?,?,?,?,?,?)");
        SQLWarning warning = checkUser.getWarnings();
        while(warning != null){
            System.out.println("Database warning: "+warning);
        }
        try{
            ResultSet rs = checkUser.executeQuery();
            if(rs.next()){
                throw new Exception("User exists with the same username or email");
            }else{
               
             createUser.setString(2,username);
             createUser.setString(3, password);
             createUser.setString(4,email);
             createUser.setString(5,firstname);
             createUser.setString(6,lastname);
             createUser.setBoolean(7, true);
             createUser.executeUpdate();
             
            }
            
        }catch(Exception e){
            
        }
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
                ("select u.genreType from UserGenre u, RegisteredUser r where u.id = r.id and r.username = ?"
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
    
}
