package classes;

import java.util.List;

public class Avaliacao {

    private int id;
    private String feedback;
    private int like;
    private final Aluno aluno;
    private List<Comentario> comentarios;
    private Professor professor;
    private Disciplina disciplina;

    //construtor
    public Avaliacao (String feedback, int like, Aluno aluno, Professor professor, Disciplina disciplina){
        this.feedback = feedback;
        this.like = like;
        this.aluno = aluno;
        id ++;
        this.professor= professor;
        this.disciplina = disciplina;
        this.professor.recebeAvaliacao(this);
    }

    public void removeAvaliacao(Professor professor, Aluno aluno) {
        professor.removeAvaliacao(this);
        aluno.removeAvaliacao(this);
    }

    public void atualizaAvaliacao(String newFeedback, int newLike) {
        this.feedback = newFeedback;
        this.like = newLike;
    }

    public void inserirAvaliacao(Aluno aluno, Professor professor, String feedback, int like) {
        Avaliacao novaAvaliacao = new Avaliacao(feedback, like, aluno, professor, disciplina);
        professor.recebeAvaliacao(novaAvaliacao);
        aluno.adicionaAvaliacao(novaAvaliacao);
    }

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

}