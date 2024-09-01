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
    public List<Disciplina> read(){ //método para criar uma lista com todos os objetos do tipo Disciplina através dos dados do banco
        
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null; 
        
        List<Disciplina> disciplinas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM disciplinas");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Disciplina disciplina = new Disciplina(rs.getString("nome"), rs.getString("departamento"),
                         rs.getString("codigo"), (rs.getInt("horas"))); //instancia o objeto da disciplina
                disciplinas.add(disciplina); //adiciona alista de disciplinas
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return disciplinas;
    }
    
    public void update(Disciplina disciplina){ //metodo para atualizar os dados da disciplina no banco de dados       
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
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
   

<<<<<<< HEAD
   public void criaListaDisciplina (Disciplina disciplina){ //metodo que procura a lista de professores do objeto de Disciplina passada como parâmetro  
       Connection con = ConnectionFactory.getConnection(); //abrindo conexao
=======
   public void criaListaDisciplina (Disciplina disciplina){ 
       Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
>>>>>>> 4201ee5e9b280d50be3b51321792d2ecfb842632
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
            for (String nomeProf : nomeProfessores){
                ArrayList <String> listaNomeProfessores = new ArrayList<>();
                for (Professor prof : disciplina.getListaProfessores()){
                    listaNomeProfessores.add(prof.getNome());
                }      
                if (!listaNomeProfessores.contains(nomeProf)){
                    disciplina.addListaProfessores(procuraProfessores.achaProfessor(nomeProf));

                }
            }
       }
       
       catch (NullPointerException e){
       }
   }

    public Disciplina achaDisciplina (String codigo){ //metodo para achar uma disciplina atraves de um codigo passado
        Disciplina disc = null;
        List<Disciplina> disciplinas = this.read();
        for (Disciplina disciplina : disciplinas){
            if (disciplina.getCodigo().equals(codigo)){ 
                disc= disciplina;
                return disc;
            }
        }      
        return disc;       
    }
}




