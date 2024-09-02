/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Avaliacao;
import classes.Disciplina;
import classes.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.ResultSet;


/**
 *
 * @author pedro
 */
public class AdmDAO {
    
    public void tiraProfessorLista(String codigoDisciplina, String nomeProfessor){ //método para excluir um professor da lista de professores de uma disciplina
        DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
        Disciplina disc= null;
        for (Disciplina disciplina : procuraDisciplina.read()){//procura a disciplina no banco de dados pelo código
            procuraDisciplina.criaListaDisciplina(disciplina);
            if (disciplina.getCodigo().equals(codigoDisciplina)){
                disc = disciplina;
            }
        }
        if (disc!=null){ //verifica se a disciplina existe
            ArrayList<Professor> listaProfessores = disc.getListaProfessores();
            ArrayList<String> nomeProfessores = new ArrayList<>();
            Professor professorExcluir = new Professor();
            for (Professor prof : listaProfessores){
                if (!prof.getNome().equals(nomeProfessor)){
                    nomeProfessores.add(prof.getNome());
                }
                else {
                    professorExcluir = prof;
                }
            }
            listaProfessores.remove(professorExcluir);
            disc.setListaProfessores(listaProfessores);
            String novosProfessores = String.join(",", nomeProfessores);
            Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
            PreparedStatement stmt = null;  //preparando a sql para execucao
            try {
                stmt = con.prepareStatement("UPDATE disciplinas SET listaProfessores = ?  WHERE codigo = ?");
                stmt.setString(1, novosProfessores);
                stmt.setString(2, disc.getCodigo()); 
                stmt.executeUpdate();                       //executando a sql 
            } catch (SQLException ex) {
                Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao excluir avaliação!" + ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }
        }
        else {
            throw new IllegalArgumentException ("Não foi possível encontrar a disciplina");
        }
                
    }
    
    public void tiraDisciplinaLista(Professor prof, String codigoDisciplina){ //método para tirar a disciplina passada como argumennto da lista de um professor
        ArrayList<Disciplina> listaDisciplinas = prof.getDisciplinas();
        ArrayList<String> novaListaHorarios = new ArrayList<>();
        ArrayList<String> novaListaDisciplinas = new ArrayList<>();
        Map <Disciplina,String> listaHorarios = prof.getListaHorarios();
        Disciplina disciplinaExcluir = new Disciplina();
        for (Disciplina disciplina : listaHorarios.keySet()){
            if (disciplina.getCodigo().equals(codigoDisciplina)){
                disciplinaExcluir = disciplina;
            }
            else{
                novaListaHorarios.add(disciplina.getCodigo()+":"+listaHorarios.get(disciplina));
                novaListaDisciplinas.add(disciplina.getCodigo());
            }
        }
        listaHorarios.remove(disciplinaExcluir);
        listaDisciplinas.remove(disciplinaExcluir);
        prof.setListaDisciplinas(listaDisciplinas);
        prof.setHorariosDisciplinas(listaHorarios);
        String novosHorarios = String.join(",", novaListaHorarios);
        String novasDisciplinas = String.join(",", novaListaDisciplinas);
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("UPDATE professores SET listaDisciplinas = ?,listaHorarios=? WHERE id = ?");
            stmt.setString(1, novasDisciplinas);
            stmt.setString(2, novosHorarios);
            stmt.setInt(3, prof.getId());
            //executando a sql
            stmt.executeUpdate();    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao remover disciplina!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }


    public void addDisciplina(String codigoDisciplina, String horario, int idProfessor){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        ResultSet rs = null; //preparando a sql para execucao
        try {
            String disciplinas = null;
            String horarios = null;
            stmt = con.prepareStatement("SELECT * from professores");
            rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getInt("id")==idProfessor){
                    disciplinas = rs.getString("listaDisciplinas");
                    horarios = rs.getString("listaHorarios");
                    break;
                }

            }
            if (disciplinas!=null && disciplinas.equals("")){
                disciplinas= codigoDisciplina;
            }
            else if (disciplinas==null){
                disciplinas=codigoDisciplina;
            }
            else{
                disciplinas= disciplinas+","+codigoDisciplina;
            }
            if (horarios!=null && horarios.equals("")){
                horarios = codigoDisciplina + ":"+horario;
            }
            else if(horarios==null){
                horarios = codigoDisciplina + ":"+horario;
            }
            else{
                horarios = horarios + ";" + codigoDisciplina + ":"+horario;
            }
            try {
                stmt = con.prepareStatement("UPDATE professores SET listaDisciplinas = ?, listaHorarios = ? where id=?");
                stmt.setString(1, disciplinas);
                stmt.setString(2, horarios);
                stmt.setInt(3, idProfessor);
                stmt.executeUpdate();               
            }catch (SQLException ex) {
                Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao adicionar disciplina!" + ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt);
            }  
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao adicionar disciplina!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }         
    }
    
    public void editarDisciplina (Disciplina disc, String codigo){ //edita os dados de uma disciplina no banco de dados de acordo com os atributos do objeto de Disciplina passado como parâmetro
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        ArrayList<String> listaProfessores = new ArrayList<>();
        for (Professor prof : disc.getListaProfessores()){
            listaProfessores.add(prof.getNome());
        }
        String novaListaProfessores = String.join(",", listaProfessores);
         try {
                stmt = con.prepareStatement("UPDATE disciplinas SET nome= ?, codigo=?, departamento=?, horas=?, listaProfessores=?  where codigo=?");
                stmt.setString(1, disc.getNome());
                stmt.setString(2, disc.getCodigo());
                stmt.setString(3, disc.getDepartamento());
                stmt.setInt(4, disc.getHoras());
                stmt.setString(5, novaListaProfessores);
                stmt.setString(6, codigo);
                stmt.executeUpdate();               
            }catch (SQLException ex) {
                Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao editar professor!" + ex);
            }finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }

    public void addProfessor(String nomeProfessor, Disciplina disc){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        String codigoDisc = disc.getCodigo();
        ResultSet rs = null;
        try{
            String professores = null;
            stmt = con.prepareStatement("SELECT * from disciplinas");
            rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getString("codigo").equals (codigoDisc)){
                    professores = rs.getString("listaProfessores");
                    break;
                }
            }
            professores = professores+","+nomeProfessor;
            try {
                stmt = con.prepareStatement("UPDATE disciplinas SET listaProfessores = ? where codigo=?");
                stmt.setString(1, professores);
                stmt.setString(2, codigoDisc);
                stmt.executeUpdate();               
            }catch (SQLException ex) {
                Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao atualizar!" + ex);
            }finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualzar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
    
    public void editarProfessor (Professor prof, String listaHorarios){ //edita os dados de um professor no banco de dados de acordo com os atributos do objeto de Professor passsado como parâmetro
        int idProfessor = prof.getId();
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        ArrayList<String> listaDisciplinas = new ArrayList<>();
        for (Disciplina disc : prof.getDisciplinas()){
            listaDisciplinas.add(disc.getCodigo());
        }
        String novaListaDisciplinas = String.join(",", listaDisciplinas);
         try {
                stmt = con.prepareStatement("UPDATE professores SET nome= ?, email=?, departamento=?, listaDisciplinas=?, listaHorarios=?  where id=?");
                stmt.setString(1, prof.getNome());
                stmt.setString(2, prof.getEmail());
                stmt.setString(3, prof.getDepartamento());
                stmt.setString(4, novaListaDisciplinas);
                stmt.setString(5, listaHorarios);
                stmt.setInt(6, idProfessor);
                stmt.executeUpdate();               
            }catch (SQLException ex) {
                Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao editar professor!" + ex);
            }finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
    

    public void editarHorarioProfessor (Professor prof, String codigoDisciplina, String novoHorario){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        ResultSet rs = null; //preparando a sql para execucao
        try {
            String [] horariosDisciplinas = null;
            String horarios = null;
            stmt = con.prepareStatement("SELECT * from professores");
            rs = stmt.executeQuery();
            while (rs.next()){
                if (rs.getInt("id")==prof.getId()){
                    horariosDisciplinas = rs.getString("listaHorarios").split(";");
                    break;
                }
            }
            if (horariosDisciplinas!=null){
                String atualizaHorario=null;
                ArrayList<String> novosHorarios = new ArrayList<>();
                for (String horario : horariosDisciplinas){
                    String [] separaHorarioDisciplina = horario.split(":");
                     if (separaHorarioDisciplina[0].equals(codigoDisciplina)){
                         novosHorarios.add(separaHorarioDisciplina[0]+":"+novoHorario);
                     }
                }
                atualizaHorario = String.join(";", novosHorarios);
                try {
                    stmt = con.prepareStatement("UPDATE professores SET listaHorarios = ? where id=?");
                    stmt.setString(1, atualizaHorario);
                    stmt.setInt(2, prof.getId());
                    stmt.executeUpdate();      
                    JOptionPane.showMessageDialog(null, "Horário alterado com sucesso!");
                }catch (SQLException ex) {
                    Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Erro ao editar horário da disciplina!" + ex);
                }finally{
                ConnectionFactory.closeConnection(con, stmt);
                }   
            }             
        }catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao editar horário da disciplina!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
    


    public void deleteDisciplina(Disciplina disc){ 
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("DELETE from disciplinas WHERE codigo = ?");
            stmt.setString(1, disc.getCodigo());
            //executando a sql
            stmt.executeUpdate();    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir disciplina!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }

    public void deleteAvaliacao (Avaliacao avalia){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("DELETE from avaliacao WHERE id = ?");
            stmt.setInt(1, avalia.getId());
            //executando a sql
            stmt.executeUpdate();    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir avaliação!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }

    public void createProfessor (Professor prof, String listaDisciplinas, String listaHorarios){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO professores (nome,email, departamento, listaDisciplinas, listaHorarios) VALUES (?,?,?,?,?)");
            stmt.setString (1, prof.getNome());
            stmt.setString(2, prof.getEmail());
            stmt.setString(3, prof.getDepartamento());
            stmt.setString(4, listaDisciplinas);
            stmt.setString (5, listaHorarios);
            //executando a sql
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Professor criado com sucesso!");
        }
        catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao criar professor!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }

    
    public void createDisciplina (Disciplina disc, String listaProfessores){ //insere uma nova disciplina no banco de dados
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO disciplinas (nome,codigo,departamento,horas,listaProfessores) VALUES (?,?,?,?,?)");
            stmt.setString (1, disc.getNome());
            stmt.setString(2,  disc.getCodigo());
            stmt.setString(3, disc.getDepartamento());
            stmt.setInt (4, disc.getHoras());
            stmt.setString (5, listaProfessores);
            //executando a sql
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Disciplina criada com sucesso!");
        }
        catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao criar disciplina!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
    

    public void deleteProfessor(Professor prof){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("DELETE from professores WHERE id = ?");
            stmt.setInt(1, prof.getId());
            //executando a sql
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Professor excluído com sucesso!");
    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir professor!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }  
    }
}
