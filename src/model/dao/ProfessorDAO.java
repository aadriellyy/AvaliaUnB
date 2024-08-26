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
        DisciplinaDAO atualizaDisciplina = new DisciplinaDAO();
        List<Professor> professores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM professores");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                String [] listaCodigosDisciplinas= rs.getString("listaDisciplinas").split(",");
                //JOptionPane.showMessageDialog(null,listaCodigosDisciplinas);
                DisciplinaDAO procuraDisciplinas = new DisciplinaDAO();
                ArrayList <Disciplina> listaDisciplinasGeral = (ArrayList <Disciplina>) procuraDisciplinas.read();
                Professor professor = new Professor(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("email"));
                for (Disciplina disciplina : listaDisciplinasGeral){
                    for (String codigo : listaCodigosDisciplinas){
                        if (disciplina.getCodigo().equals(codigo)){
                            //JOptionPane.showMessageDialog(null, disciplina.getCodigo()+" "+ codigo);
                            professor.addDisciplina(disciplina);
                            //JOptionPane.showMessageDialog(null, disciplina.getNome());
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

}
