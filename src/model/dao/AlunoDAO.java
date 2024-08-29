/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Aluno;
import classes.Avaliacao;
import classes.Professor;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AlunoDAO {
    
    //CRUD DE ALUNO
    private Aluno aluno;
    
    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }
    
    public Aluno getAluno(){
        return this.aluno;
    }
    
    public void create(Aluno aluno){
        
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("INSERT INTO alunos (nome, email, senha, curso, departamento, matricula) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());
            stmt.setString(4, aluno.getCurso());
            stmt.setString(5, aluno.getDepartamento());
            stmt.setString(6, aluno.getMatricula());
            //executando a sql
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public List<Aluno> read(){
        
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM alunos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluno aluno = new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email"), rs.getString("matricula"), rs.getString("curso"), rs.getString("senha"));
                
                alunos.add(aluno);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return alunos;
    }
    
    public void update(Aluno aluno){
        
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("UPDATE alunos SET nome = ?, email = ?, senha = ?, curso = ?, departamento = ?, matricula = ? WHERE id = ?");
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());
            stmt.setString(4, aluno.getCurso());
            stmt.setString(5, aluno.getDepartamento());
            stmt.setString(6, aluno.getMatricula());
            stmt.setInt(7, aluno.getId());
            //executando a sql
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }       
    }
    
    public Professor buscarProfessor(int id){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        Professor prof = null;
        ProfessorDAO procuraProfessor = new ProfessorDAO();
        List<Professor> professores = new ArrayList<>();
        
        /*try {
            /*stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Professor professor = new Professor(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email")); 
                professor.setId(rs.getInt(id));
                professores.add(professor);
            }   */
            professores= procuraProfessor.read();
            prof = procuraProfessor.achaProfessor(id);
            /*for(Professor profe: professores){
                if(profe.getId() == id){
                    prof = profe;
                }
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }*/
      
        return prof;
        
    }
    
    public Aluno agrupAvaliacao(Aluno aluno){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int id = aluno.getId();
        
        ResultSet rs = null;
        List<Avaliacao> avaliacao = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM avaliacao");
            rs = stmt.executeQuery();          
          
            while(rs.next()){
                if(rs.getInt("alunoID") == id){
                    int profId = rs.getInt("professorID");
                    Professor prof = this.buscarProfessor(profId);
                    Avaliacao avalia = new Avaliacao(rs.getString("feedback"), rs.getFloat("nota"), aluno, prof);
                    avalia.setId(rs.getInt("id"));
                    aluno.adicionaAvaliacao(avalia);
                }
            }   
            
            return aluno;
        }
        
        catch (SQLException ex){
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }       
        
        return null;
        
    }
    
    public void delete(Avaliacao avalia){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM avaliacao WHERE id = ?");
            stmt.setInt(1, avalia.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao deletar" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
        
    }
    
}
