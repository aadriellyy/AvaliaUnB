/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacao;

import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class VerificaNumero implements Verificadores {
    public boolean verifica(String texto){
        try {
            Integer.parseInt(texto);
            return true;
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um número inteiro válido");
            return false;
        }
    }
}
