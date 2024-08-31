package classes;

import java.util.*;

public class Comentario {
    private int id;
    private String texto;
    private Date dateCreated;
    private Avaliacao avaliacao;
    //Construtores
    public Comentario(){}
    
    
    
    public Comentario(int id, String texto, Date dateCreated, Avaliacao avaliacao) {
        this.id = id;
        this.texto = texto;
        this.dateCreated = dateCreated;
        this.avaliacao = avaliacao;
    }
    
    public Comentario(int id, String texto, Date dateCreated){
        this.id = id;
        this.texto = texto;
        this.dateCreated = dateCreated;
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
    
    //metodo pro bd
    public void comentar(int avaliacaoId){
        
    }
    public void excluirComentario(int comentarioId){
        
    }
    
    public void updateComentario(int comentarioId){
        
    }
    
    public String mostraComentario (int avaliacaoId, int comentarioId){
        return "";
    }
}
