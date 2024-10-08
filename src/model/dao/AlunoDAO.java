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
    
    
    //metodo usado para criar um novo aluno
    public void create(Aluno aluno){
        
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("INSERT INTO aluno (nome, email, senha, curso, departamento, matricula) VALUES(?, ?, ?, ?, ?, ?)");
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
    
    //metodo usado para ler todos os alunos cadastrados no banco de dados
    public List<Aluno> read(){
        
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno");
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
    
    //metodo usado para atualizar o aluno
    public void update(Aluno aluno){
        
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("UPDATE aluno SET nome = ?, email = ?, senha = ?, curso = ?, departamento = ?, matricula = ? WHERE id = ?");
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
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }       
    }
    
    //metodo usado para buscar um professor acessando o seu id
    public Professor buscarProfessor(int id){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        Professor prof = null;
        ProfessorDAO procuraProfessor = new ProfessorDAO();
        List<Professor> professores = new ArrayList<>();
       
        professores= procuraProfessor.read();
        prof = procuraProfessor.achaProfessor(id);
      
        return prof;
        
    }
    
    //metodo usado para adicionar todas as avaliacoes feitas por um aluno ao objeto aluno
    public Aluno agrupAvaliacao(Aluno aluno){
        Connection con = ConnectionFactory.getDatabaseConnection();
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
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }       
        
        return null;
        
    }
    
    //metodo feito para deletar uma avaliacao
    public void delete(Avaliacao avalia){
        Connection con = ConnectionFactory.getDatabaseConnection();
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
    
    //metodo feito para retornar a lista de avaliações que o aluno curtiu, para evitar que ele curta a mesma avaliação varias vezes
    public ArrayList<Integer>  avaliacoesCurtidas (int id){//procura os ids das avaliações que o aluno ja curtiu
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        ArrayList<Integer> listaCurtidas = new ArrayList<>();
        String [] idsAvaliacoes = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno where id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();            
            while(rs.next()){
                if (rs.getString("avaliacoesCurtidas")!=null){
                    idsAvaliacoes = rs.getString("avaliacoesCurtidas").split(",");
                }
            }
            if (idsAvaliacoes!=null){
                for (String idLoop : idsAvaliacoes){
                    listaCurtidas.add(Integer.valueOf(idLoop));
                }
            }
            else {
                return listaCurtidas;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }      
        return listaCurtidas;
    }
    
    
    //atualiza o número de curtidas de uma avaliacao
    public void atualizarAvaliacoesCurtidas(int idAluno, int idAvaliacao){//atualizando os ids das avaliações que o aluno já curtiu
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        ResultSet rs=null;
        ArrayList<Integer> listaCurtidas = this.avaliacoesCurtidas(idAluno);
        listaCurtidas.add(idAvaliacao);
        ArrayList<String> novoArrayCurtidas= new ArrayList<>();
        for (Integer curtida : listaCurtidas){
            novoArrayCurtidas.add(String.valueOf(curtida));
        }
        String novaListaAvaliacoes = String.join(",", novoArrayCurtidas);
        JOptionPane.showMessageDialog(null, novaListaAvaliacoes);
        try {
            stmt = con.prepareStatement("UPDATE aluno SET avaliacoesCurtidas=? where id =?");
            stmt.setString(1, novaListaAvaliacoes);
            stmt.setInt(2, idAluno);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }      
    }
    
}

