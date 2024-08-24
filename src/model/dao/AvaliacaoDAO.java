/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Aluno;
import classes.Avaliacao;
import classes.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author limaa
 */
public class AvaliacaoDAO {
    
    
    public Professor buscarProfessor(String nome){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        Professor prof = null;
        List<Professor> professores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM professor");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Professor professor = new Professor(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email"));        
                professores.add(professor);
            }   
            for(Professor profe: professores){
                if(profe.getNome().equals(nome)){
                    prof = profe;
                }
            }    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
      
        return prof;
        
    }
    
    public void create(String feedback, int like, Aluno aluno, Professor professor){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        Aluno alu = aluno;
        Professor prof = professor;
        System.out.println(feedback + " " + like + " " + alu.getMatricula() + " " + prof.getId());
        Avaliacao avaliacao = new Avaliacao(feedback, like, alu, prof);
        
        try {
            stmt = con.prepareStatement("INSERT INTO avaliacao (feedback, nota, professorID, alunoID) VALUES(?, ?, ?, ?)");
            stmt.setString(1, avaliacao.getFeedback()); 
            stmt.setInt(2, avaliacao.getLike());
            stmt.setInt(3, avaliacao.getProfessor().getId());
            stmt.setInt(4, avaliacao.getAluno().getId());
            //executando a sql
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Avaliação salva com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar avaliação!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
