/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Aluno;
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
                String [] listaCodigosDisciplinas= rs.getString("listaDisciplinas").split(",");
                DisciplinaDAO procuraDisciplinas = new DisciplinaDAO();
                ArrayList <Disciplina> listaDisciplinasGeral = (ArrayList <Disciplina>) procuraDisciplinas.read();
                Professor professor = new Professor(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email"));
                for (Disciplina disciplina : listaDisciplinasGeral){
                    for (String codigo : listaCodigosDisciplinas){
                        if (disciplina.getCodigo().equals(codigo)){
                            professor.addDisciplina(disciplina);
                            break;
                        }
                    }
                }

                String [] paresChaveValor = rs.getString("listaHorarios").split(";");
                for (String par : paresChaveValor){
                    String [] chaveValorSeparados = par.split(":");
                    Disciplina disciplinaChave = null;
                    for (Disciplina disciplina : listaDisciplinasGeral){
                        if (disciplina.getCodigo().equals(chaveValorSeparados[0])){
                            disciplinaChave = disciplina;
                            break;
                        }
                    }
                    try {
                        professor.setHorariosDisciplinas(chaveValorSeparados[1],disciplinaChave);
                    }
                    catch (NullPointerException e){             
                    }
                }
                professores.add(professor);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return professores;
    }
    
    
    public Professor achaProfessor(String nome){
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
           DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
           List<Disciplina> listaDisciplinas = procuraDisciplina.read();
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
    public void criaListaHorarios (Professor prof){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        String [] listaHorarios = null;
         try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getString("id").equals(prof.getId())){
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
}
