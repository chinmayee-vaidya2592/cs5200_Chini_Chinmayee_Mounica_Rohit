/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umlClasses;

/**
 *
 * @author mounica
 */
public class UserGenre {
    
    private GenreType type;
    
    public GenreType gettype(){
        return type;
    }
    
    public void settype(GenreType t){
        this.type = t;
    }
    public UserGenre(){
        
    }
    
    public UserGenre getug(GenreType t){
        UserGenre ug = new UserGenre();
        ug.settype(t);
        return ug;
    }
}
