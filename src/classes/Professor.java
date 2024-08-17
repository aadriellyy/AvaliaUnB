package classes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Professor extends Pessoa{
    
    Map <Disciplina,String> horarios = new HashMap<>();     //um objeto do tipo Map com as chaves sendo as disciplinas que o professor oferta e os valores sendo os horários que o professor oferta aquela disciplina
    private ArrayList <Disciplina> listaDisciplinas;                    //lista de disciplinas do professor    
//lista de disciplinas do professor    
    static int id;
    //lista de avaliações feitas pelo professor
    private ArrayList <Avaliacao> listaAvaliacoes;                      
    private static ArrayList <Professor> todosProfessores= new ArrayList<>();
    

    public Professor(String nome, String departamento, String email){
        super(nome, departamento, email);
        this.listaAvaliacoes = new ArrayList<>();
        this.listaDisciplinas = new ArrayList<>();
        Professor.todosProfessores.add(this);
        
    }   //construtor que inicializa os atributos
    
    public Professor getId (int id, ArrayList <Disciplina> listaDisciplinas, String departamento ) { //retorna o id do professor procurando esses dados no banco de dados
        return this;
    }
    
    public void setId(int id) {
        this.id= id;
    }
    
    public Map <Disciplina, String> getListaHorarios () {
        return this.horarios;
    }
    
    public ArrayList <String> getHorarios () {
        ArrayList <String> listaHorarios = new ArrayList <>();
        for (String horario : this.horarios.values()){
                listaHorarios.add(horario);
        }
        Collections.sort(listaHorarios);
        return listaHorarios;
    }
    
    public String getHorarios (Disciplina disciplina) {
        if (this.listaDisciplinas.contains(disciplina)){
            return this.horarios.get(disciplina);
        }
        else{
            throw new Illegal­Argument­Exception ("O professor não ministra esta disciplina");
        }
    }
    
    public void setHorariosDisciplinas (String horario, Disciplina disciplina) {
        if (this.listaDisciplinas.contains(disciplina)){
            this.horarios.put(disciplina, horario);        
        }
        else{
            throw new Illegal­Argument­Exception ("O professor não ministra esta disciplina");
        }
    }
    
    
    public ArrayList<Disciplina> getDisciplinas(){
        return this.listaDisciplinas;
    }
    
    public void setDisciplinas (Disciplina disciplina) {
        if (!this.listaDisciplinas.contains(disciplina)){
            listaDisciplinas.add(disciplina);
            if (!disciplina.getListaProfessores().contains(this)){
                disciplina.setListaProfessores(this);
            }
        }
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
    
    public ArrayList <Avaliacao> getListaAvaliacoes () {
        return this.listaAvaliacoes;
}
    
    public Professor achaProfessor (String nome){
        for (Professor prof : Professor.todosProfessores) {
            if (prof.getNome().equals(nome)){
                return prof;
            }
        }
        throw new IllegalArgumentException ("Professor não encontrado");
    }
    
    public static void insereBanco(Professor prof){
        try (Connection conn = MySQLConnection.getConnection()) { //testando conexao com o banco de dados
                String sql = "INSERT INTO professores (nome, email, departamento, listaDisciplinas, listaAvaliacoes, listaHorarios) VALUES (?, ?, ?, ?, ?, ?)"; //comando para inserir os dados na tabela alunos
                PreparedStatement stmt = conn.prepareStatement(sql); //variavel que fara a inserção dos dados na tabela
                ArrayList <String> listaDisciplinasLocal = new ArrayList<>();
                ArrayList <String> listaIdLocal = new ArrayList<>();
                for (Disciplina disc : prof.getDisciplinas()){
                    System.out.println(disc.getNome());
                    listaDisciplinasLocal.add(disc.getCodigo());
                }
                for (Avaliacao avalia : prof.getListaAvaliacoes()){
                    listaIdLocal.add(String.valueOf(avalia.getId()));
                }
                ArrayList <String> horariosDisciplinaLocal = new ArrayList<>();
                for (Disciplina disciplina : prof.getListaHorarios().keySet()){
                    String disciplinaLocal = disciplina.getCodigo();
                    String horariosDaDisciplina = prof.getListaHorarios().get(disciplina);
                    String disciplinaHorarioLocal = String.join(":", disciplinaLocal, horariosDaDisciplina);
                    horariosDisciplinaLocal.add(disciplinaHorarioLocal);
                }
                String horariosDisciplinaBanco = String.join(",", horariosDisciplinaLocal);
                String listaDisciplinasBanco = String.join(",", listaDisciplinasLocal);
                String listaIdBanco = String.join(",", listaIdLocal);
                stmt.setString(1,prof.getNome() );
                stmt.setString(2, prof.getEmail());
                stmt.setString(3, prof.getDepartamento());
                stmt.setString(4, listaDisciplinasBanco);
                stmt.setString(5, listaIdBanco);
                stmt.setString(6, horariosDisciplinaBanco);
                stmt.executeUpdate();
            } catch (SQLException e) { //caso a conexão com o banco de dados falhe
                e.printStackTrace();
            }

    }
    
}
