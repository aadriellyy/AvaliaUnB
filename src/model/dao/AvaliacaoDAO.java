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
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        nome = nome.trim().toLowerCase();
        Professor prof = null;
        List<Professor> professores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Professor professor = new Professor(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email")); 
                professor.setId(rs.getInt("id"));
                professores.add(professor);
            }   
            for(Professor profe: professores){
                String nomeProf = profe.getNome().trim().toLowerCase();
                if(nomeProf.equals(nome)){
                    prof = profe;
                }
            }    
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
      
        return prof;
        
    }
    
    public List<Avaliacao> read(){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        AlunoDAO procuraAluno = new AlunoDAO();
        ProfessorDAO procuraProfessor = new ProfessorDAO();
        
        List<Avaliacao> avaliacoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM avaliacao");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluno alunoAvaliacao = null;
                Professor profAvaliacao = null;
                for (Aluno aluno : procuraAluno.read()){
                    if (Integer.parseInt(rs.getString("alunoID"))==aluno.getId()){
                        alunoAvaliacao = aluno;
                        break;
                    }
                }
                for (Professor prof : procuraProfessor.read()){
                    if (prof.getId()==Integer.parseInt(rs.getString("professorID"))){
                        profAvaliacao= prof;
                        break;
                    }
                }
                Avaliacao avaliacao;
                avaliacao = new Avaliacao(rs.getString("feedback"), rs.getFloat("nota"), alunoAvaliacao, profAvaliacao);
                avaliacao.setId (rs.getInt("id"));
                avaliacoes.add(avaliacao);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return avaliacoes;
    }
<<<<<<< HEAD
    public void create(Avaliacao avaliacao){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
=======
    
    public void create(String feedback, float nota, int like, Aluno aluno, Professor professor){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
>>>>>>> fa74c4d0b8d22a5f455192f38366c5c5a13af816
        PreparedStatement stmt = null;  //preparando a sql para execucao
        Aluno alu = avaliacao.getAluno();
        Professor prof = avaliacao.getProfessor();
        
        try {
            stmt = con.prepareStatement("INSERT INTO avaliacao (feedback, nota, professorID, alunoID) VALUES(?, ?, ?, ?)");
            stmt.setString(1, avaliacao.getFeedback()); 
            stmt.setFloat(2, avaliacao.getNota());
            stmt.setInt(3, avaliacao.getProfessor().getId());
            stmt.setInt(4, avaliacao.getAluno().getId());
            //executando a sql
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Avaliação salva com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar avaliação!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void updateLike (Avaliacao avalia){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
         try {
            stmt = con.prepareStatement("UPDATE avaliacao SET likes=? WHERE id=?");
            try{
            stmt.setInt(1, avalia.getLike()); 
            stmt.setInt(2, avalia.getId());
            //executando a sql
<<<<<<< HEAD
            stmt.executeUpdate();}
            catch (NullPointerException e){       
=======
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Avaliação salva com sucesso!");    
            }
            catch (NullPointerException e){
                
>>>>>>> fa74c4d0b8d22a5f455192f38366c5c5a13af816
            }

        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar avaliação!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void update(Avaliacao avaliacao){
        
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("UPDATE avaliacao SET feedback = ?, nota = ? WHERE id = ?");
            stmt.setString(1, avaliacao.getFeedback());
            stmt.setFloat(2, avaliacao.getNota());
            stmt.setInt(3, avaliacao.getId());
            //executando a sql
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizada com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }       
    }
    
    public Avaliacao achaAvaliacao(Avaliacao avalia){
        List<Avaliacao> listaAvaliacoes = this.read();
        for (Avaliacao avaliacao: listaAvaliacoes){
            if (avaliacao.getId()==avalia.getId()){
                return avaliacao;
            }
        }
        return null;
    }
}
