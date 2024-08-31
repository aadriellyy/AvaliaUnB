/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Aluno;
import classes.Avaliacao;
import classes.Disciplina;
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
 * @author pedro
 */
public class ProfessorDAO {
    public List<Professor> read(){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        List<Professor> professores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getString("listaDisciplinas")!=null){ //verifica se o professor possui alguma disciplina cadastrada
                Professor professor = new Professor(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email"));
                professor.setId(rs.getInt("id"));

                
                professores.add(professor);
            }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return professores;
    }


    public void update (Professor professor, String idAvaliacao){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        String [] listaAvaliacoes = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getString("nome").equals(professor.getNome())){
                    if (rs.getString("listaAvaliacoes")==null){
                        listaAvaliacoes = new String [1];
                    }
                    else {
                    listaAvaliacoes = rs.getString("listaAvaliacoes").split(",");
                    }
                    break;
                }
            }
        try {
            String [] adicionaListaAvaliacoes = new String[listaAvaliacoes.length];
            adicionaListaAvaliacoes [listaAvaliacoes.length-1]= idAvaliacao;
            String novaListaAvaliacoes = String.join(",", adicionaListaAvaliacoes);
            con = ConnectionFactory.getConnection(); //abrindo conexao
            stmt = null;
            stmt = con.prepareStatement("UPDATE professores SET listaAvaliacoes = ? WHERE nome = ?");
            stmt.setString(1, novaListaAvaliacoes);
            stmt.setString(2, professor.getNome());
            //executando a sql
            stmt.executeUpdate();
        }
        catch (NullPointerException e){
            
        }
         }
         catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public Professor achaProfessor(String nome){ //método para achar professor através do nome
        ProfessorDAO procuraProfessor = new ProfessorDAO();
                for (Professor prof : procuraProfessor.read()){
                    if (prof.getNome().equals(nome)){
                    return prof;
                }
            }
        return null;
    }   
    
    public void addHorario(Disciplina disc, Professor prof){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        String [] listaHorarios = null;
         try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getString("nome").equals(prof.getNome())){
                    listaHorarios = rs.getString("listaHorarios").split(";");
                    break;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
         try{
           for (String par : listaHorarios){
               String [] chaveValor= par.split(":");
               if (disc.getCodigo().equals(chaveValor[0])){
                    prof.setHorariosDisciplinas(chaveValor[1], disc);
                    break;       
               }
           }               
        }
        catch (NullPointerException e){
            
        }
         
    }
    
    public void criaListaAvaliacoes(Professor prof){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ArrayList<Integer> listaIdsAvaliacoes= new ArrayList<>();
        for (Avaliacao avalia : prof.getListaAvaliacoes()){
            listaIdsAvaliacoes.add(avalia.getId());
        }
        int id = prof.getId();
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM avaliaunb.avaliacao");
            rs = stmt.executeQuery();          
                while(rs.next()){
                    if(rs.getString("professorID")!=null && rs.getInt("professorID") == id  ){
                        int idAvaliacao = rs.getInt("id");
                        if (!listaIdsAvaliacoes.contains(idAvaliacao)){
                            int alunoId = rs.getInt("alunoID");
                            Aluno aluno = this.buscarAluno(alunoId);
                            Avaliacao avalia = new Avaliacao(rs.getString("feedback"), rs.getFloat("nota"), aluno, prof);
                            avalia.setId(rs.getInt("id"));
                            avalia.setLike(rs.getInt("likes"));
                            prof.recebeAvaliacao(avalia);
                        }
                    }
                
                }
            }   
        catch (SQLException ex){
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }       
                
    }
        
    
    
    public Aluno buscarAluno(int id){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        Aluno aluno = null;
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM alunos");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluno alunoLocal = new Aluno(rs.getInt("id"),rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email"), rs.getString("matricula"), rs.getString("curso"),rs.getString("senha"));        
                alunos.add(alunoLocal);
            }   
            for(Aluno alun: alunos){
                if(alun.getId() == id){
                    aluno = alun;
                    break;
                }
            }    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }     
        return aluno;        
    }
    
    public void criaListaHorarios (Professor prof){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        String [] listaHorarios = null;
         try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            while(rs.next()){
                if (rs.getInt("id")==(prof.getId())){
                    listaHorarios = rs.getString("listaHorarios").split(";");
                    JOptionPane.showMessageDialog(null, "listaHorarios");
                    break;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        try{
           DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
           List<Disciplina> listaDisciplinas = procuraDisciplina.read();
           for (String par : listaHorarios){
               String [] chaveValor= par.split(":");
                for (Disciplina disc : listaDisciplinas){
                        if (disc.getCodigo().equals(chaveValor[0])){
                            prof.setHorariosDisciplinas(chaveValor[1], disc);
                            break;
                        }
                    }

           }
               
        }
        catch (NullPointerException e){
            
        }

    }
    public void criaListaDisciplinas(Professor prof){ //método que cria a lista de disciplinas de um professor
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        String [] listaDisciplinas = null;
         try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            while(rs.next()){
                if (rs.getInt("id")==(prof.getId())){
                    listaDisciplinas = rs.getString("listaDisciplinas").split(",");
                    break;
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        try{
           DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
           List<Disciplina> listaDisciplinasGeral = procuraDisciplina.read();
           for (String disciplina : listaDisciplinas){
                for (Disciplina disc : listaDisciplinasGeral){
                        if (disc.getCodigo().equals(disciplina)){
                            prof.addDisciplina(disc);
                            break;
                        }
                    }

           }

        }
        catch (NullPointerException e){

        }
    }
    
    public Professor achaProfessor (int id) {
        ProfessorDAO procuraProfessor = new ProfessorDAO();
                for (Professor prof : procuraProfessor.read()){
                    if (prof.getId() == id){
                    return prof;
                }
            }
        return null;
    }   

}

