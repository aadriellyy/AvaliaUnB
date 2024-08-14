package classes;
import java.util.ArrayList;
import java.util.Collections;

public class Professor extends Pessoa{
    
    private ArrayList <String> horarios;
    private ArrayList <Disciplina> listaDisciplinas;
    static int id;
    private ArrayList <Avaliacao> listaAvaliacoes;
    
    
    public Professor(){}
    
    public Professor(String nome, String departamento, String email){
        super(nome, departamento, email);
    }
    
    public Professor getId (int id, ArrayList <Disciplina> listaDisciplinas, String departamento ) { //retorna o id do professor procurando esses dados no banco de dados
        return this;
    }
    public void setId(int id) {
        this.id= id;
    }
    public ArrayList <String> getHorarios () {
        return this.horarios;
    }
    public void setHorarios (String horario) {
        this.horarios.add(horario);
    }
    
    public ArrayList<Disciplina> getDisciplinas(){
        return this.listaDisciplinas;
    }
    
    public void setDisciplinas (Disciplina disciplina) {
        listaDisciplinas.add(disciplina);
    }
    
    public void removeAvaliacao(Avaliacao avaliacao) {
        this.listaAvaliacoes.remove(avaliacao);
    }
    
    public void recebeAvaliacao (Avaliacao avaliacao) {
        this.listaAvaliacoes.add(avaliacao);
    }
    
    public double mediaAvaliacao () {
        if (this.listaAvaliacoes.isEmpty()) {
            return 0;
        }
        else {
            double notas= 0;
            for (Avaliacao avaliacao : this.listaAvaliacoes) {
                notas+=avaliacao.getLike();
            } 
            return notas/this.listaAvaliacoes.size();
        }
    }
    
    public double mediaAvaliacao(Disciplina disciplina){
        if (this.listaAvaliacoes.isEmpty()) {
            return 0;
        }
        else {
            double notas=0;
            int numAvaliacoes=0;
            for (Avaliacao avaliacao : this.listaAvaliacoes) {
                if (avaliacao.getDisciplina().equals(disciplina)){
                    notas+=avaliacao.getLike();
                    numAvaliacoes++;
                }
            } 
            if (numAvaliacoes==0){
                return 0;
            }
            else{
                return notas/numAvaliacoes;
            }
        }
    }
    
    public ArrayList<Avaliacao> rankearAvaliacao (ArrayList<Avaliacao> listaAvaliacoes){
        ArrayList<Avaliacao> listaRankeada = new ArrayList<>();
        ArrayList <Integer> listaLikes = new ArrayList<>();
        for (Avaliacao avaliacao: listaAvaliacoes){
            listaLikes.add(avaliacao.getLike());
        }
        Collections.sort(listaLikes);
        for (int like : listaLikes){
            for (Avaliacao avaliacao: listaAvaliacoes){
                if (avaliacao.getLike()==like){
                    listaRankeada.add(avaliacao);
                    break;
                }
            }
        }
        return listaRankeada;
    }
    
    public ArrayList <Professor> mostraTodos () { //retorna todos os professores presentes no banco de dados
        return null;
    }
}
