/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //interface do JDBC
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/avaliaunb";
    private static final String USER = "root";
    private static final String PASS = "292620yda";
    
    
    
    public static Connection getInitialConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexão inicial com o MySQL", ex);
        }
    }

    // Método para se conectar ao banco de dados após ele ter sido criado
    public static Connection getDatabaseConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
    }
    
    public static void closeConnection(Connection con){
        try{
            if(con != null){     
                con.close();
            } 
        }catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        closeConnection(con);     
        try{
            if(stmt != null){     
                stmt.close();
            } 
        }catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(con, stmt);
        
        try{
            if(rs != null){     
                rs.close();
            } 
        }catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
