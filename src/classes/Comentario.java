package classes;

import java.util.*;

public class Comentario {
    static int id;
    private String texto;
    private Date dateCreated;
    private Avaliacao avaliacao;
    //Construtores
    public Comentario(){}

    public Comentario(String texto, Date dateCreated, Avaliacao avaliacao) {
        id++;
        this.texto = texto;
        this.dateCreated = dateCreated;
        this.avaliacao = avaliacao;
    }

    public Comentario(String texto){
        id++;
        this.texto = texto;

    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Avaliacao getAvaliacao(){
        return avaliacao;
    }

    
}
