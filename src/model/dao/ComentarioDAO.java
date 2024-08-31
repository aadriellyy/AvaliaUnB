/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author lucas
 */

import Connection.ConnectionFactory;
import classes.Comentario;
import classes.Aluno;
import classes.Avaliacao;
import classes.Professor;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ComentarioDAO {
    private Comentario comentario;
    
    public void setComentario(Comentario comentario){
        this.comentario = comentario;
    }

    public Comentario getComentario() {
        return comentario;
    }
    
    Avaliacao avaliacao;
    
    public void comentar(Comentario comentario){
        
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("INSERT INTO comentario (id, texto, dateCreated, avaliacaoid) VALUES(?, ?, ?, ?)");
            stmt.setInt(1, comentario.getId());
            stmt.setString(2, comentario.getTexto());
            stmt.setDate(3, (java.sql.Date) Calendar.getInstance().getTime());
            stmt.setInt(4, avaliacao.getId());
            //executando a sql
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateComentario(Comentario comentario){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        
        try {
            stmt = con.prepareStatement("UPDATE comentario SET texto = ?, dateCreated = ? WHERE id = ?");
            stmt.setString(1, comentario.getTexto());
            stmt.setDate(2, (java.sql.Date) Calendar.getInstance().getTime());
            stmt.setInt(3, comentario.getId());
            //executando a sql
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }       
    }
    
    public void excluirComentario(Comentario comentario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM comentario WHERE id = ?");
            stmt.setInt(1, comentario.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao deletar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public String mostrarComentario(Avaliacao avaliacao, Comentario comentario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String text = "";
        //int id = avaliacao.getId()
        int id = comentario.getAvaliacao().getId();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM comentario");
            rs = stmt.executeQuery();          
          
            while(rs.next()){
                if(rs.getInt("avaliacaoid") == id){
                    text = rs.getString("texto");
                    break;
                }
            }   
            
        }
        
        catch (SQLException ex){
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }       
        
        return text;
    
    }


}
