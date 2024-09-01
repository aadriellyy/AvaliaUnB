/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacao;

/**
 *
 * @author pedro
 */
public class VerificaEmail implements Verificadores {
    @Override
    public boolean verifica (String texto){
        String [] listaCaracteres = texto.split(" ");
        if (listaCaracteres.length>1){
            return false;
        }
        int quantArrobas=0;
            for (int i=0; i<texto.length();i++){
                Character carac = texto.charAt(i);
                if(carac.equals('@')){
                    quantArrobas+=1;
                    if (i==texto.length()-1 || i==0){
                        return false;
                    }
                }
            }
 
        return (quantArrobas==1);
    }
}
