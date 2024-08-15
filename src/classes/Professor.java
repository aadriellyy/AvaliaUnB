package classes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Professor extends Pessoa{
    
    Map <Disciplina, ArrayList<String>> horarios = new HashMap<>();     //um objeto do tipo Map com as chaves sendo as disciplinas que o professor oferta e os valores sendo os horários que o professor oferta aquela disciplina
    private ArrayList <Disciplina> listaDisciplinas;                    //lista de disciplinas do professor    
    static int id;
    private ArrayList <Avaliacao> listaAvaliacoes;                      //lista de avaliações feitas pelo professor
    
    
    public Professor(){} //construtor vazio
    
    public Professor(String nome, String departamento, String email){
        super(nome, departamento, email);
    }   //construtor que inicializa os atributos
    
    public Professor getId (int id, ArrayList <Disciplina> listaDisciplinas, String departamento ) { //retorna o id do professor procurando esses dados no banco de dados
        return this;
    }
    
    public void setId(int id) {
        this.id= id;
    }
    
    public ArrayList <String> getHorarios () {
        ArrayList <String> listaHorarios = new ArrayList <>();
        for (ArrayList<String> horarios : this.horarios.values()){
            for (String horario : horarios){
                listaHorarios.add(horario);
            }
        }
        Collections.sort(listaHorarios);
        return listaHorarios;
    }
    
    public ArrayList <String> getHorarios (Disciplina disciplina) {
        if (this.listaDisciplinas.contains(disciplina)){
            return this.horarios.get(disciplina);
        }
        else{
            throw new Illegal­Argument­Exception ("O professor não ministra esta disciplina");
        }
    }
    
    
    public void setHorariosDisciplinas (String horario, Disciplina disciplina) {
        if (this.listaDisciplinas.contains(disciplina)){
            this.horarios.get(disciplina).add(horario);
        }
        else{
            throw new Illegal­Argument­Exception ("O professor não ministra esta disciplina");
        }
    }
    
    public void ordenarHorarios(){
        for (Disciplina disciplina : this.horarios.keySet()){
            Collections.sort(this.horarios.get(disciplina));
        }
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
        Collections.reverse(listaLikes);
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
