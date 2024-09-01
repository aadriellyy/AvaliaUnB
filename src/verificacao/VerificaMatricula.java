/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacao;

/**
 *
 * @author pedro
 */
public class VerificaMatricula implements Verificadores{   
    @Override
    public boolean verifica(String matricula){
        for (int i=0; i<matricula.length();i++){
            Character carac = matricula.charAt(i);
            if (!Character.isDigit(carac)){
                return false;
            }
        }
        return true;
    }
}
