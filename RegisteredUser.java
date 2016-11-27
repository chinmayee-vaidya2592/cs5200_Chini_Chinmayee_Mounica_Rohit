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
    List<GenreType> Genre = new ArrayList<GenreType>();
    private Comment comment_list;
    private boolean has_access;
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
    
    public List<GenreType> getBenefit ()
        {
            return Genre;
        }
    
    public Comment get_list(){
        return comment_list;
    }
    
    public void set_list(Comment list){
        this.comment_list = list;
    }
    
    public Boolean get_access(){
        return has_access;
    }
    
    public void set_Access(Boolean a){
        this.has_access = a;
    }
    public RegisteredUser(){
        
    }
    
    public void createUser(Connection con, String username,String password, 
            String verifypassword, String email, String firstname, String lastname)throws SQLException{
        this.conn = con;
        PreparedStatement createUser = conn.prepareStatement("");
        
    }
    
   
    public void getUser(Connection connection, String username, String password) throws SQLException{
        this.conn = connection;
        PreparedStatement getUser = conn.prepareStatement("select firstName from  RegisteredUser where username = ? and password = ?");
        SQLWarning warning = getUser.getWarnings();
        while(warning != null){
            System.out.println("Database warning: " + warning);
        }
        try{
            getUser.setString(1,username);
            getUser.setString(2,password);
            ResultSet rs = getUser.executeQuery();
            SQLWarning querywarning = getUser.getWarnings();
            while(querywarning != null){
                System.out.println("Query warning: " + querywarning);
            }
            while(rs.next()){
                rs.getString(1);
            }
            
        }catch(Exception e){
            System.out.println("Invalid entries "+ e);
        }finally{
            getUser.close();
        }

    }
    
}
