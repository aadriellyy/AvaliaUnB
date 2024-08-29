/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Professor;
import static java.lang.Character.isDigit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import verificacao.MapeiaHorarios;

/**
 *
 * @author pedro
 */
public class GradeDAO {
    public ArrayList<ArrayList<String>> achaGrade(int idAluno){ //método para achar professor através do nome
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        ArrayList<ArrayList<String>> horariosGrade = new ArrayList<>();
        for (int i=0; i <14; i++){
        ArrayList<String> adicionarHorario= new ArrayList ();
        adicionarHorario.add(MapeiaHorarios.mapearHorarios.get(i));
        adicionarHorario.add("");
        adicionarHorario.add("");
        adicionarHorario.add("");
        adicionarHorario.add("");
        adicionarHorario.add("");
        adicionarHorario.add("");
        horariosGrade.add(adicionarHorario);}
        try {
            stmt = con.prepareStatement("SELECT * FROM grade");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getInt("alunoID")==idAluno){
                String [] horarios = rs.getString("").split(",");
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
                
            }
    
     public void ajustarHorario(String horariosPassados, String disciplina, ArrayList<ArrayList<String>> horarios){
        String [] listaHorarios = horariosPassados.split(" ");
        boolean adicionou = false;
        for (String horario : listaHorarios){
            boolean jaAdicionado = false;
            adicionou = false;
            String dia, horas, turno;
            dia = "";
            horas="";
            turno = "";
            int count = 0;
            Character carac = horario.charAt(count);
            while (count < horario.length() && isDigit(carac)){
                    dia= dia+carac;
                    count+=1;
                    carac= horario.charAt(count);
                }
            while (count < horario.length() && Character.isAlphabetic(carac)){
                turno= turno+carac;
                count+=1;
                carac= horario.charAt(count);
            }

            while (count < horario.length()-1 && isDigit(carac)){
                    horas= horas+carac;
                    count+=1;
                    carac= horario.charAt(count);
                }
            horas = horas+ horario.charAt(horario.length()-1);


            for (int i = 0; i<horas.length(); i++){
                //jaAdicionado = false;
                Integer linha = null;
                 if (turno.equals("m") || turno.equals("M")){
                    linha =Integer.parseInt(horas.substring(i,i+1))-1;
                 }
                 else if (turno.equals("t") || turno.equals("T")){
                    linha =Integer.parseInt(horas.substring(i,i+1))+4;
                 }
                else if (turno.equals("n") || turno.equals("N")){
                     linha =Integer.parseInt(horas.substring(i,i+1))+9;
                }
                try {
                    for (int x = 0; x<dia.length(); x++){
                        if (!horarios.get(linha).get(Integer.parseInt(dia.substring(x, x+1))-1).equals("") && !jaAdicionado){
                            JOptionPane.showMessageDialog(null, "Conflito de horários, escolha outro horário");
                            jaAdicionado = true;
                            break;

                        }
                        else if (horarios.get(linha).get(Integer.parseInt(dia.substring(x, x+1))-1).equals("")){
                                horarios.get(linha).set(Integer.parseInt(dia.substring(x, x+1))-1, disciplina);
                                adicionou = true;
                        }
                    }
                    jaAdicionado= true;


            }

                catch (NullPointerException e ) {
                    JOptionPane.showMessageDialog(null, "Não foi possível adicionar a disciplina à grade");
                }
            }

        }  
        
    
    }

     public ArrayList<String> acharDisciplinasAdicionadas(int idAluno){
        ArrayList<String> disciplinasAdicionadas = new ArrayList<>();
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        String [] horariosDisciplinas = null;
              try {
            stmt = con.prepareStatement("SELECT * FROM grade");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getInt("alunoID")==idAluno){
                horariosDisciplinas = rs.getString("horarios").split(",");
                break;
                }
                try {
                    for (String parHorariosDisciplina: horariosDisciplinas){
                        String [] horario = parHorariosDisciplina.split(":");
                        disciplinasAdicionadas.add(horario[0]);
                    }
                }
                catch(NullPointerException e){

                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return disciplinasAdicionadas;
     }
     
    public List<Professor> read(){
        Connection con = ConnectionFactory.getConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        List<Professor> professores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM grade");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                if (rs.getString("listaDisciplinas")!=null){
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
}
