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
                try{
                    String [] listaProfessores = rs.getString("listaProfessores").split(",");
                    ProfessorDAO procuraProfessores = new ProfessorDAO();
                    /*for (String nomeProfessor : listaProfessores){
                        for (Professor professor : procuraProfessores.read()){
                            if (nomeProfessor.equals(professor.getNome())){
                                disciplina.addListaProfessores(professor);
                                break;
                            }
                        }
                    }*/
                    
                    
                }
                catch (NullPointerException e){
                    
                }
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
            //JOptionPane.showMessageDialog(null, professoresFormat);
            stmt.setString(1, professoresFormat);
            stmt.setString(2, disciplina.getCodigo());
            //JOptionPane.showMessageDialog(null, stmt);
            //JOptionPane.showMessageDialog(null,disciplina.getListaProfessores().size());

            //executando a sql
            stmt.executeUpdate();

            //JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void teste(){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
         try{
            stmt = con.prepareStatement("SELECT * FROM disciplinas");
            rs= stmt.executeQuery();
            while (rs.next()){
                JOptionPane.showMessageDialog(null, rs.getString("listaProfessores"));}
             }
         catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt,rs);
        }
    }

}


