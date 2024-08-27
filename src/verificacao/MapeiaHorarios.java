/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificacao;

import java.util.HashMap;

/**
 *
 * @author pedro
 */
public class MapeiaHorarios {
        public static HashMap <Integer, String> mapearHorarios = new HashMap<>();
        public static void inicializa(){
        mapearHorarios.put(0, "08:00 - 08:55");
        mapearHorarios.put(1, "08:55 - 09:50");
        mapearHorarios.put(2, "10:00 - 10:55");
        mapearHorarios.put(3, "10:55 - 11:50");
        mapearHorarios.put(4, "12:00 - 12:55");
        mapearHorarios.put(5, "12:55 - 13:50");
        mapearHorarios.put(6, "14:00 - 14:55");
        mapearHorarios.put(7, "14:55 - 15:50");
        mapearHorarios.put(8, "16:00 - 16:55");
        mapearHorarios.put(9, "16:55 - 17:50");
        mapearHorarios.put(10, "19:00 - 19:50");
        mapearHorarios.put(11, "19:50 - 20:40");
        mapearHorarios.put(12, "20:50 - 21:40");
        mapearHorarios.put(13, "21:40 - 22:30");
        }
}
