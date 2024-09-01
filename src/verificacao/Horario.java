/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacao;

import classes.Disciplina;
import classes.Professor;
import static java.lang.Character.isDigit;
import java.util.*;
import javax.swing.JOptionPane;
import model.dao.DisciplinaDAO;
import model.dao.ProfessorDAO;

/**
 *
 * @author pedro
 */
public class Horario implements Verificadores {
    ProfessorDAO procuraProfessor = new ProfessorDAO();
    
    
    @Override
    public boolean verifica (String horario){
        if (horario.equals("")){
            throw new IllegalArgumentException ("Horário vazio");
        }
        String dia, horas, turno;
        dia = "";
        horas="";
        turno = "";
        int count = 0;
        Character carac = horario.charAt(count);
        ArrayList <Character> listaDias = new ArrayList<>();
        listaDias.add('2');
        listaDias.add('3');
        listaDias.add('4');
        listaDias.add('5');
        listaDias.add('6');
        listaDias.add('7');

        while (count < horario.length() && isDigit(carac)){
            if (!listaDias.contains(carac)){
                throw new IllegalArgumentException ("Este dia não existe");
            }
            if (!dia.contains(String.valueOf(carac))){
            dia= dia+carac;
            count+=1;
            carac= horario.charAt(count);   
            }   
            else {
                throw new IllegalArgumentException ("Dia repetido no código");
            }
        }
        if (dia.length()>6 || dia.length()<1){
            throw new IllegalArgumentException ("Quantidade de dias inválida");
        }

        ArrayList <Character> listaTurnos = new ArrayList<>();
        listaTurnos.add('m');
        listaTurnos.add('M');
        listaTurnos.add('t');
        listaTurnos.add('T');
        listaTurnos.add('n');
        listaTurnos.add('N');

        while (count < horario.length() && Character.isAlphabetic(carac)){
            if (!listaTurnos.contains(carac)){
                throw new IllegalArgumentException ("Turno inválido");   
            }
            else{
            turno= turno+carac;
            count+=1;
            carac= horario.charAt(count);
            }
        }
        if (turno.length()!=1){
            throw new IllegalArgumentException ("Foi informado mais de um turno");   
        }
        ArrayList <Character> listaHoras= new ArrayList<>();
        listaHoras.add('1');
        listaHoras.add('2');
        listaHoras.add('3');
        listaHoras.add('4');
        listaHoras.add('5');
        ArrayList <Character> listaHorasNoite = new ArrayList<>();
        listaHorasNoite.add('1');
        listaHorasNoite.add('2');
        listaHorasNoite.add('3');
        listaHorasNoite.add('4');
        while (count < horario.length()-1 && isDigit(carac)){
            if (turno.equals('n')|| turno.equals('N')){
                if (!listaHorasNoite.contains(carac)){
                    throw new IllegalArgumentException ("Informado horário inválido");   

                }
                else{
                    horas= horas+carac;
                    count+=1;
                    carac= horario.charAt(count);
                }
            }
            else{
                if (!listaHoras.contains(carac)){
                    throw new IllegalArgumentException ("Informado horário inválido");   
                }
                else{
                    horas= horas+carac;
                    count+=1;
                    carac= horario.charAt(count);
                }
            }
        }
        if (turno.equals('n')|| turno.equals('N')){
            if (isDigit(horario.charAt(horario.length()-1)) && listaHorasNoite.contains(horario.charAt(horario.length()-1))){
                horas = horas + horario.charAt(horario.length()-1);
            }
            else {
                throw new IllegalArgumentException ("Informado horário inválido");   
            }
        }
        else {
            if (isDigit(horario.charAt(horario.length()-1)) && listaHoras.contains(horario.charAt(horario.length()-1))){
                horas = horas + horario.charAt(horario.length()-1);
            }
            else {
                throw new IllegalArgumentException ("Informado horário inválido");   
            }
        }

        if (turno.equals('n')|| turno.equals('N')){
            if (horas.length()<1 || horas.length()>4){
                        throw new IllegalArgumentException ("Informado horário inválido");   
            }
        }
        else {
            if (horas.length()<1 || horas.length()>5){
                        throw new IllegalArgumentException ("Informado horário inválido"); 
            }  
        }
        return true;
    }

}
