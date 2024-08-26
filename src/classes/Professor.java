package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import verificacao.Horario;

public class Professor extends Pessoa{
    Map <Disciplina,String> horarios = new HashMap<>();     //um objeto do tipo Map com as chaves sendo as disciplinas que o professor oferta e os valores sendo os horários que o professor oferta aquela disciplina
    private ArrayList <Disciplina> listaDisciplinas;                    //lista de disciplinas do professor    
    //lista de disciplinas do professor    
    private int id;
    //lista de avaliações feitas pelo professor
    private ArrayList <Avaliacao> listaAvaliacoes;                      
    

    public Professor(String nome, String departamento, String email){
        super(nome, departamento, email);
        this.listaAvaliacoes = new ArrayList<>();
        this.listaDisciplinas = new ArrayList<>();
        id ++;
        //Professor.todosProfessores.add(this);
    }   //construtor que inicializa os atributos
    
    public Professor getId (int id, ArrayList <Disciplina> listaDisciplinas, String departamento ) { //retorna o id do professor procurando esses dados no banco de dados
        return this;
    }
    
    /*
    public void setId(int id) {
        this.id= id;
    }
    */
    public Map <Disciplina, String> getListaHorarios () { 
        return this.horarios;
    }
    
    public ArrayList <String> getHorarios () { //retorna um ArrayList com todos os horários de aula do professor, sem especificar a disciplina
        ArrayList <String> listaHorarios = new ArrayList <>();
        for (String horario : this.horarios.values()){
                listaHorarios.add(horario);
        }
        Collections.sort(listaHorarios);
        if (!listaHorarios.isEmpty()){ //verifica se há algum horario cadastrado
            return listaHorarios;
        }
        else {
            throw new NullPointerException("Nenhum horário cadastrado");
        }
    }
    
    public String getHorario (Disciplina disciplina) { //retorna o horário de uma determinada disciplina passada como argumento
        if (this.listaDisciplinas.contains(disciplina)){ //verifica se a disciplina passada como argumento é ministrada pelo professor
            return this.horarios.get(disciplina);
        }
        else{
            throw new Illegal­Argument­Exception ("O professor não ministra esta disciplina");
        }
    }
    
    public void setHorariosDisciplinas (String horario, Disciplina disciplina) {
        
        try {
            Horario.verificaHorario(horario);
            if (this.listaDisciplinas.contains(disciplina)){ //verifica se a disciplina passada como argumento é ministrada pelo professor
                this.horarios.put(disciplina, horario);        
            }

            else{
                throw new Illegal­Argument­Exception ("O professor não ministra esta disciplina");
            }
        }
        catch (IllegalArgumentException e){
            
        }
    }
    
    
    public ArrayList<Disciplina> getDisciplinas(){
        if (this.listaDisciplinas == null){
            throw new NullPointerException ("Não há nenhuma disciplina cadastrada");
        } 
        else {
            return this.listaDisciplinas;
        }
    }
    
    public void addDisciplina (Disciplina disciplina) {
        if (!this.listaDisciplinas.contains(disciplina)){
            listaDisciplinas.add(disciplina);
            if (!disciplina.getListaProfessores().contains(this)){
                disciplina.addListaProfessores(this);
            }
        }
    }
    
    public void setListaDisciplinas (ArrayList <Disciplina> listaDisciplinas){
        if (listaDisciplinas == null){
            throw new NullPointerException ("Lista vazia");
        }
        else{
            this.listaDisciplinas= listaDisciplinas;
        }
    }
    
    public void removeAvaliacao(Avaliacao avaliacao) {
        this.listaAvaliacoes.remove(avaliacao);
    }
    
    public void recebeAvaliacao (Avaliacao avaliacao) {
        this.listaAvaliacoes.add(avaliacao);
    }
    
    public double mediaAvaliacao () {  //retorna a media de avaliacao geral do professor 
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
    
    public double mediaAvaliacao(Disciplina disciplina){ //retorna a media de avaliação do professor para uma determinada disciplina
        if (this.listaAvaliacoes.isEmpty()) {
            return -1;
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
                return -1;
            }
            else{
                return notas/numAvaliacoes;
            }
        }
    }
    
    public ArrayList<Avaliacao> rankearAvaliacao (ArrayList<Avaliacao> listaAvaliacoes){ //ordena as avaliacoes do professor de acordo com a quantidade de likes
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
    
    public ArrayList <Avaliacao> getListaAvaliacoes () {
        return this.listaAvaliacoes;
}
    
    public int getId(){
        return this.id;
    }
    
}
    
