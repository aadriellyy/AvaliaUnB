/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Aluno;
import classes.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import classes.Disciplina;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class DisciplinaDAO {
    public List<Disciplina> read(){
        
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        
        List<Disciplina> disciplinas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM disciplinas");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                                
                Disciplina disciplina = new Disciplina(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("codigo"), Integer.parseInt(rs.getString("horas")));
                disciplinas.add(disciplina);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return disciplinas;
    }
    
        public void update(Disciplina disciplina){
        
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("UPDATE disciplinas SET  listaProfessores = ?  WHERE codigo=?");
            ArrayList<String> listaProfessores = new ArrayList<>();
            for (Professor professor : disciplina.getListaProfessores()){
                listaProfessores.add(professor.getNome());
            }
            String professoresFormat =String.join(",", listaProfessores);
            stmt.setString(1, professoresFormat);
            stmt.setString(2, disciplina.getCodigo());

            //executando a sql
            stmt.executeUpdate();

            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
   

   public void criaListaDisciplina (Disciplina disciplina){ 
       Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        String [] nomeProfessores = null;
         try {
            stmt = con.prepareStatement("SELECT * FROM disciplinas");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getString("codigo").equals(disciplina.getCodigo())){
                    nomeProfessores = rs.getString("listaProfessores").split(",");
                    break;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
       try{
            ProfessorDAO procuraProfessores = new ProfessorDAO();
            DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
            for (String nomeProf : nomeProfessores){
                boolean jaFeito=false;
                for (Professor profDisciplina : disciplina.getListaProfessores()){
                    if (profDisciplina.getNome().equals(nomeProf)){
                      jaFeito = true;  
                    }
                }
                if (!jaFeito){
                    for (Professor prof : procuraProfessores.read()){
                        if (prof.getNome().equals(nomeProf)){
                            disciplina.addListaProfessores(prof);
                        }
                    }
                }
            }
        }
       
       catch (NullPointerException e){
           JOptionPane.showMessageDialog(null,"Não há nenhum professor cadastrado nessa disciplina");
       }
   }
   
    }



