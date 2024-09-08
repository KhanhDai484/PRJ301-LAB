package dao;

import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.util;

public class userDao {
       
    public User checkLogin(String userId, String password) throws SQLException {
        
        Connection conn = null;
        PreparedStatement p = null;
        ResultSet rs= null;
        User user = null;
        String userID;
        String fullName;
        String pass;
        String roleID;
        
        try{
            
            conn = util.createConnection();
            p = conn.prepareStatement("SELECT * FROM tblUsers WHERE UserId =? AND password =?");

            p.setString(1, userId);
            p.setString(2, password);
            rs= p.executeQuery();
            if (rs.next()) {
                    
                userID = rs.getString("UserID");
                fullName=rs.getString("fullName");
                pass=rs.getString("password");
                roleID=rs.getString("roleID");
                
                user = new User(userID, fullName, pass, roleID);
                
            }
            
        }catch(SQLException e){
            System.out.println("Error access database");
        }
        finally {
            //close connection
            if (rs != null){
                rs.close();
            }
            if (p != null) {
                p.close();
            }
            if (conn != null){
                conn.close();
            }
        }
        
        return user;
    }
}
