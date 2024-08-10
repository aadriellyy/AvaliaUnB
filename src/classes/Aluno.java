package classes;

import java.util.*;

public class Aluno extends Pessoa{
    static int id;
    private String matricula;
    private String curso;
    private String senha;
    private List<Avaliacao> avaliacoes;

    public Aluno(String nome, String departamento, String email, String matricula, String curso, String senha) {
        super(nome, departamento, email);
        this.matricula = matricula;
        this.curso = curso;
        this.senha = senha;
        this.avaliacoes = new ArrayList<>();
        id ++;
        
    }

    public int getId() {
        return id;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public void adicionaAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getSenha(){
        return senha;
    }
    

    public void removeAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.remove(avaliacao);
    }
}
