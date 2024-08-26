package classes;

import java.util.List;

public class Avaliacao {

    private int id;
    private String feedback;
    private int like;
    private Aluno aluno;
    private List<Comentario> comentarios;
    private Professor professor;
    private final Disciplina disciplina = null;

    //construtor
    public Avaliacao (String feedback, int like, Aluno aluno, Professor prof){
        this.feedback = feedback;
        this.like = like;
        this.aluno = aluno;
        id ++;
        this.professor = prof;
    }

    public Avaliacao() {
    }

    public void removeAvaliacao(Professor professor, Aluno aluno) {
        professor.removeAvaliacao(this);
        aluno.removeAvaliacao(this);
    }
    
    public void setId(int id){
        this.id = id;
    }

    /**
    public void atualizaAvaliacao(String newFeedback, int newLike) {
        this.feedback = newFeedback;
        this.like = newLike;
    }
**/

    public int getId(){
        return this.id;
    }

    public void setFeedback(String feedback){
        this.feedback = feedback;
    }

    public String getFeedback(){
        return this.feedback;
    }

    public void setLike(int like){
        this.like = like;
    }

    public int getLike(){
        return this.like;
    }

    public Aluno getAluno(){
        return this.aluno;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    
    public Disciplina getDisciplina (){
        return this.disciplina;
    }
   
    
    public Professor getProfessor(){
        return this.professor;
    }

}