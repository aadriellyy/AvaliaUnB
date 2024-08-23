/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver"; //interface do JDBC
    private static final String URL = "jdbc:mysql://localhost:3306/avaliaunb";
    private static final String USER = "root";
    private static final String PASS = "292620yda";
    
    public static Connection getConnection(){
        //construtor da conexao com o banco de dados, herdado do driver jdbc
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro no conexão com o banco de dados");
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
