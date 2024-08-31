/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacao;

/**
 *
 * @author pedro
 */
public class VerificaCodigo implements Verificadores {

    @Override
    public boolean verifica(String codigo) {
        if (codigo.length()!=7){
            return false;
        }
        for (int i = 0; i<7; i++){
            Character carac = codigo.charAt(i);
            if (i<3 && !Character.isLetter(carac)){
                return false;
            }
            else if (i>=3 && i<7 && !Character.isDigit(carac)){
                return false;
            }
        }
        return true;
    }
    
}
