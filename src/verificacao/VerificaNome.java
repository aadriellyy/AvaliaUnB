/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacao;

/**
 *
 * @author pedro
 */
public class VerificaNome {
    public boolean verifica (String texto){
        String [] listaCaracteres = texto.split(" ");
        for (String palavra : listaCaracteres){
            for (int i=0; i<palavra.length();i++){
                char letra = palavra.charAt(i);
                if(!Character.isLetter(letra)){
                    return false;
                }
            }
        }
        return true;
    }
}
