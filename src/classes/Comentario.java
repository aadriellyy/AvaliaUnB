package classes;

import java.util.*;

public class Comentario {
    private int id;
    private String comentario;
    private Date dateCreated;

    //Construtores
    public Comentario(){}
    public Comentario(int id, String comentario, Date dateCreated) {
        this.id = id;
        this.comentario = comentario;
        this.dateCreated = dateCreated;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    //metodo
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
