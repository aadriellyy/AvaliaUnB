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
import java.time.LocalDate;
import java.time.Month;
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
    
    
    public void comentar(Comentario comentario, Avaliacao avalia){
        
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("INSERT INTO comentario (id, texto, dateCreated, avaliacaoid) VALUES(?, ?, ?, ?)");
            Calendar cal = Calendar.getInstance();
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH);
            int ano = cal.get(Calendar.YEAR);
            stmt.setInt(1, comentario.getId());
            stmt.setString(2, comentario.getTexto());
            LocalDate data = LocalDate.of(ano, mes, dia);
            stmt.setDate(3, (java.sql.Date.valueOf(data)));
            stmt.setInt(4, avalia.getId());
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
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        
        try {
            stmt = con.prepareStatement("UPDATE comentario SET texto = ?, dateCreated = ? WHERE id = ?");
            Calendar cal = Calendar.getInstance();
            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH);
            int ano = cal.get(Calendar.YEAR);
            LocalDate data = LocalDate.of(ano, mes, dia);
            stmt.setString(1, comentario.getTexto());
            stmt.setDate(2, (java.sql.Date.valueOf(data)));
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
        Connection con = ConnectionFactory.getDatabaseConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM comentario WHERE id = ?");
            stmt.setInt(1, comentario.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluí­do com sucesso!");
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao deletar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Comentario> agruparComentario (Avaliacao avalia){
        ArrayList<Comentario> listaComentarios = new ArrayList<>();
        Connection con = ConnectionFactory.getDatabaseConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String text = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM comentario");
            rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getInt("avaliacaoid") == avalia.getId()){
                    Comentario comentario = new Comentario (rs.getString("texto"),rs.getDate("dateCreated"),avalia);
                    comentario.setId(rs.getInt("id"));
                    listaComentarios.add(comentario);
                    }
            }   

        }
        catch (SQLException ex){
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return listaComentarios;
        
    }
    public String mostrarComentario(Avaliacao avaliacao, Comentario comentario){
        Connection con = ConnectionFactory.getDatabaseConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String text = null;
        //int id = avaliacao.getId()
        int id = comentario.getAvaliacao().getId();

        try {
            stmt = con.prepareStatement("SELECT * FROM comentario");
            rs = stmt.executeQuery();
          try{
            while(rs.next()){
                if(rs.getInt("avaliacaoid") == id){
                    text = rs.getString("texto");
                    return text;
                    }
                }
            }

          catch(NullPointerException a){
                    JOptionPane.showMessageDialog(null, "deu erro" );
                    }
        }
        catch (SQLException ex){
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return null;
        }
}