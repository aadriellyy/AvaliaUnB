/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Connection.ConnectionFactory;
import classes.Aluno;
import static java.lang.Character.isDigit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import verificacao.MapeiaHorarios;
import verificacao.*;

/**
 *
 * @author pedro
 */
public class GradeDAO  {
    Horario verificarHorario = new Horario();
    
    public ArrayList<Integer> readIDs(){ //método para ler todos os ids de alunos que possuem grade salva 
        ArrayList<Integer> listaIDs = new ArrayList<>();
        PreparedStatement stmt = null;  
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM grade");
            rs = stmt.executeQuery();          
            while(rs.next()){
                listaIDs.add(rs.getInt("alunoID"));                    
            }
            return listaIDs;
        }   
        catch (SQLException ex) {
            Logger.getLogger(GradeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);           
        }
        return listaIDs;
    }
    
    public ArrayList<ArrayList<String>> achaGrade(int idAluno){ //método para achar professor através do nome
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        ArrayList<ArrayList<String>> horariosGrade = null;
        boolean encontradoGrade = false;
        boolean encontradoAluno = false;
        AlunoDAO procuraAluno = new AlunoDAO();
        ArrayList<Integer> listaIDs = new ArrayList<>();
        List <Aluno> listaAlunos=procuraAluno.read();
        String [] horariosDisciplinas = null;
        for (Aluno aluno : listaAlunos){
            listaIDs.add(aluno.getId());
        }
        try {
            stmt = con.prepareStatement("SELECT * FROM grade");
            rs = stmt.executeQuery();
            if (listaIDs.contains(idAluno)){
                while(rs.next()){
                    if (rs.getInt("alunoID")==idAluno){
                        encontradoAluno=true;
                        horariosDisciplinas = rs.getString("horarios").split(",");
                        }
                }
                if (horariosDisciplinas==null || horariosDisciplinas.equals("")){
                    return this.inicializaHorario(horariosGrade);
                }
                else{
                    for (String parDisciplinaHorario : horariosDisciplinas){
                        if (!parDisciplinaHorario.equals("")){
                            String [] horario = parDisciplinaHorario.split(":");
                            String codigoDisciplina = horario[0];
                            String horarioDisciplina = horario[1];
                            horariosGrade = this.ajustarHorario(horarioDisciplina, codigoDisciplina, horariosGrade);
                            encontradoGrade= true;
                        }
                    }
                }
            }
            else{
                throw new IllegalArgumentException ("O aluno não está cadastrado");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        if (!encontradoAluno){
            horariosGrade= inicializaHorario(horariosGrade);
        }
        if (!encontradoGrade){
            horariosGrade= inicializaHorario(horariosGrade);
        }
        return horariosGrade;  
        
    }
    
    public ArrayList<ArrayList<String>> inicializaHorario (ArrayList<ArrayList<String>> horarios){
        try {
            MapeiaHorarios.inicializa();
            if (horarios.isEmpty()){
                for (int i=0; i <14; i++){
                    ArrayList<String> adicionarHorario= new ArrayList ();
                    adicionarHorario.add(MapeiaHorarios.mapearHorarios.get(i));
                    adicionarHorario.add("");
                    adicionarHorario.add("");
                    adicionarHorario.add("");
                    adicionarHorario.add("");
                    adicionarHorario.add("");
                    adicionarHorario.add("");
                    horarios.add(adicionarHorario);
                }
                return horarios;
            }
        }
        catch (NullPointerException e){
            horarios = new ArrayList<ArrayList<String>> ();
            horarios = inicializaHorario (horarios);
            return horarios;
        }
        return horarios;
                            
    }
  
    public int readHoras(int alunoID){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        int horas=0;
        try {
            stmt = con.prepareStatement("SELECT * FROM grade");
            rs = stmt.executeQuery();          
            while(rs.next()){
                if (rs.getInt("alunoID")==alunoID){
                    horas = rs.getInt("horas");
                    return horas;
                }
            }
        }   
        catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);           
        }
        return horas;
    }
          
    public ArrayList<ArrayList<String>> ajustarHorario(String horariosPassados, String disciplina, ArrayList<ArrayList<String>> horarios){
        horarios= inicializaHorario(horarios);
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

        return horarios;
    }

    public ArrayList<String> acharDisciplinasAdicionadas(int idAluno){
        ArrayList<String> disciplinasAdicionadas = new ArrayList<>();
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
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
            }
            try {
                for (String parHorariosDisciplina: horariosDisciplinas){
                    String [] horario = parHorariosDisciplina.split(":");
                    disciplinasAdicionadas.add(horario[0]);
                }
            }
            catch(NullPointerException e){
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return disciplinasAdicionadas;
     }
   
    public void update (String novosHorarios, int horas, int idAluno){
        Connection con = ConnectionFactory.getDatabaseConnection(); //abrindo conexao
        PreparedStatement stmt = null;  //preparando a sql para execucao
        ResultSet rs = null;
        ArrayList<Integer> listaIDs = this.readIDs();
        try {
            if (listaIDs.contains(idAluno)){
                stmt = con.prepareStatement("UPDATE grade SET horarios = ?,horas=? where alunoID=?");
                stmt.setString(1, novosHorarios);
                stmt.setInt(2, horas);
                stmt.setInt(3, idAluno);
                stmt.executeUpdate(); 
            }
            else {
                stmt = con.prepareStatement("INSERT INTO grade (horarios, horas, alunoID) values (?,?,?)");
                stmt.setString(1,novosHorarios);
                stmt.setInt(2, horas);
                stmt.setInt(3, idAluno);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public ArrayList<String> acharHorariosAdicionados (int idAluno){
        ArrayList<String> horariosAdicionados = new ArrayList<>();
        Connection con = ConnectionFactory.getDatabaseConnection(); 
        PreparedStatement stmt = null;  
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
            }
            try{
                for (String horario : horariosDisciplinas){
                    horariosAdicionados.add(horario);
                }
            }         
            catch (NullPointerException e){
                return horariosAdicionados;
            }         
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horariosAdicionados;
    }
}
    
    
            
    

