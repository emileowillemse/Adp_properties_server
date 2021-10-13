/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.Adp_properties_server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import za.ac.cput.Adp_properties_server.dbconnection.DBConnection;
import za.ac.cput.Adp_properties_server.classes.User;

/**
 *
 * @author emile
 */
public class ServerDAO {
        private final Connection con;
//    private final Statement statement;
    
        public ServerDAO() throws SQLException{
        this.con = DBConnection.derbyConnection();
//        this.statement = this.con.createStatement();
    }
    
    //Create
    public ServerDAO save(ServerDAO Login) throws SQLException{
     
    User login = new User("","");   
    String insertSQL = "INSERT INTO Login (username, password)"
            + "VALUES (?, ?)";
    
    PreparedStatement ps = this.con.prepareStatement(insertSQL);
    ps.setString(1, login.getUsername());
    ps.setString(2, login.getPassword());
    
    ps.executeUpdate();
    ps.close();
    return Login;
    
//    insertSQL = String.format(insertSQL, student.getStudentNumber(), student.getFirstName(), 
//                                         student.getMiddleName(), student.getLastName());
//    System.out.println(insertSQL);
//    this.statement.executeUpdate(insertSQL);
    
//    Connection con = DBConnection.derbyConnection();
//    Statement statement = con.createStatement();

    }
    
    //Read
    public List<User> getAll() throws SQLException{
        
//        ResultSet rs = this.statement.executeQuery(getAll_SQL);
//        PreparedStatement ps = this.con.prepareStatement(getAll_SQL);
//        ResultSet rs = ps.executeQuery();

        String getAll_SQL = "SELECT * From Users";      
        List<User> User = new ArrayList<>();
        try (PreparedStatement ps = this.con.prepareStatement(getAll_SQL);ResultSet rs = ps.executeQuery())
        {
        while (rs.next()){
          String username = rs.getString("username");
          String password = rs.getString("password");
          User milo = new User(username, password);
          User.add(milo);
        }
        rs.close();
//        ps.close();
        return User;
    }}
    
    public void closeResources() throws SQLException{
//        this.statement.close();
        this.con.close();
    }


}
