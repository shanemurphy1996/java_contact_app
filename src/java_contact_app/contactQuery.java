
package java_contact_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author shane
 */
public class contactQuery {
    
    public void insertContact (contact cont){
        
        
        
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO `mycontacts`(`fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic`, `userid`) VALUES (?,?,?,?,?,?,?,?)");
      
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8, cont.getUid());
            
                if (ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Your new contact has been added!");
                   
                }
                
            else{
                JOptionPane.showMessageDialog(null, "Something Went Wrong");
               
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    // ################END OF INSERT INTO CONTACTS#############
    
    
    
    
    public void updateContact (contact cont, boolean withImage){
        
        
        
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        String updateQuery = "";
        
        if(withImage == true){ //IF THE USER WANTS TO UPDATE CONTACTS PROFILE PIC
            updateQuery = "UPDATE `mycontacts` SET `fname`= ?,`lname`= ?,`groupc`= ?,`phone`= ?,`email`= ?,`address`= ?,`pic`= ? WHERE `id` = ?";
    
             try {
            ps = con.prepareStatement(updateQuery);
      
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
            ps.setBytes(7, cont.getPic());
            ps.setInt(8,cont.getCid());
            
                if (ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "You have successfully edited the contacts information!");
                   
                }
            else{
                JOptionPane.showMessageDialog(null, "Something Went Wrong");
               
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
        
        else{ //THE USER WANTS TO KEEP THE SAME IMAGE (OR DELETE THE IMAGE FROM UPDATE)
            
        updateQuery = "UPDATE `mycontacts` SET `fname`=?,`lname`=?,`groupc`=?,`phone`=?,`email`=?,`address`=? WHERE `id` = ?";
            
        try {
            ps = con.prepareStatement(updateQuery);
      
            ps.setString(1, cont.getFname());
            ps.setString(2, cont.getLname());
            ps.setString(3, cont.getGroupc());
            ps.setString(4, cont.getPhone());
            ps.setString(5, cont.getEmail());
            ps.setString(6, cont.getAddress());
          
            ps.setInt(7,cont.getCid());
            
                if (ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "You have successfully edited the contacts information!");
                   
                }
            else{
                JOptionPane.showMessageDialog(null, "Something Went Wrong");
               
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    
    }
       
    //#########################END OF INSERT EDIT CONTACTS#############
    
    public void deleteContact (int cid){
        
        
        
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("DELETE FROM `mycontacts` WHERE `id` = ?");
            ps.setInt(1, cid);
      
            
            
                if (ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "Contact Deleted!");
                   
                }
                
            else{
                JOptionPane.showMessageDialog(null, "Something Went Wrong");
               
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
    
    
    
    //THIS IS WHERE THE LIST CREATION IS DONE - CONTACT LIST 
    public ArrayList<contact> contactList( int userId){
        
        ArrayList<contact> clist = new ArrayList<contact>();
        
        Connection con = myConnection.getConnection();
        Statement st;
        ResultSet rs;
        
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT `id`, `fname`, `lname`, `groupc`, `phone`, `email`, `address`, `pic` FROM `mycontacts` WHERE userid="+userId);
        
            contact ct;
            
            while(rs.next()){
                ct = new contact(rs.getInt("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("groupc"),
                rs.getString("phone"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getBytes("pic"),
                userId);
                        clist.add(ct);                    
            }
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(contactQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        return clist;
    }
    
    

}
