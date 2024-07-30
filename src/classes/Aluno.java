import java.util.*;

public class Aluno {
    private int id;
    private String matricula;
    private String nome;
    private String curso;
    private String senha;
    private List<Avaliacao> avaliacoes;

    public Aluno(String matricula, String nome, String curso, String senha) {
        this.matricula = matricula;
        this.nome = nome;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
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

    public void removeAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.remove(avaliacao);
    }
}
