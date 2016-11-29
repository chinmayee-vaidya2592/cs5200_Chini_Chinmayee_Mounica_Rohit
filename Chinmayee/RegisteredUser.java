package project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

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
}
