/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbproject;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

/**
 *
 * @author mounica
 */
public class RegisteredUser {
    private String username;
    private String email,password;
    private int comment_counter;
    private List<Comment> comment_list = new ArrayList<Comment>();
    private List<UserGenre> Genre = new ArrayList<UserGenre>();
    private boolean has_access;
    private String firstName;
    private String lastName;
    private Connection conn;
    
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
    
    public String getpassword(){
        return username;
    }
    public void setpassword(String p){
        this.password = p;
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
     
    public List<Comment> getcomments ()
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
    public RegisteredUser(){
        
    }
    
    public void createUser(Connection con, String username,String password, 
            String email, String firstname, String lastname)throws SQLException{
        this.conn = con;
        PreparedStatement checkUser = conn.prepareStatement
        ("select username, email from RegisteredUser where username =? or email = ?");
        PreparedStatement createUser = conn.prepareStatement
        ("insert into RegisteredUser(id,username,password,email,firstName,lastName) values(?,?,?,?,?)");
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
             createUser.setString(3,email);
             createUser.setString(4,firstname);
             createUser.setString(5,lastname);
             createUser.executeUpdate();
             
            }
            
        }catch(Exception e){
            
        }
    }
    
   
    public void getUser(Connection connection, String username, String password) throws SQLException{
        this.conn = connection;
        PreparedStatement getUser = conn.prepareStatement
        ("select username,password,email,hasAccess,firstName,lastName "
                + "from  RegisteredUser where username = ? and password = ?");
        PreparedStatement getGenres = conn.prepareStatement
                ("select u.genreType from UserGenre u, RegisteredUser r where u.id = r.id and r.username = ?"
                        + "and r.password = ?");
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
                this.username = rs.getString(1);
                this.password = rs.getString(2);
                this.email = rs.getString(3);
                this.has_access = rs.getBoolean(4);
                this.firstName = rs.getString(5);
                this.lastName = rs.getString(6);
                ResultSet g = getGenres.executeQuery();
                SQLWarning querywarning1 = getGenres.getWarnings();
                while(querywarning1 != null){
                System.out.println("Query warning: " + querywarning);
            }
                while(g.next()){
                    UserGenre ug = new UserGenre();
                    UserGenre ug1 = ug.getug(GenreType.valueOf(g.getString(1)));
                    Genre.add(ug1);
                }
                
            }
            
        }catch(Exception e){
            System.out.println("Invalid entries "+ e);
        }finally{
            getUser.close();
        }

    }
    
}
