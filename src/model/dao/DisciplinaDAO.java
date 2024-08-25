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
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                                
                Disciplina disciplina = new Disciplina(rs.getString("nome"), rs.getString("email"),
                         rs.getString("departamento"), Integer.parseInt(rs.getString("horas")));
                
                disciplinas.add(disciplina);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return disciplinas;
    }
    
        /*public void update(Disciplina disciplina){
        
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        try {
            stmt = con.prepareStatement("UPDATE alunos SET nome = ?, codigo = ?, departamento = ?, horas = ?, listaProfessores = ?  WHERE codigo = ?");
            stmt.setString(1, disciplina.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());
            stmt.setString(4, aluno.getCurso());
            //executando a sql
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao autalizar!" + ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }*/

}


