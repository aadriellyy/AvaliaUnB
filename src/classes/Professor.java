package classes;
import java.util.ArrayList;

public class Professor extends Pessoa{
    
    private ArrayList <String> horarios;
    private ArrayList <Disciplina> listaDisciplinas;
    private int id;
    private ArrayList <Avaliacao> listaAvaliacoes;
    
    public int getId (String nome, ArrayList <Disciplina> listaDisciplinas, String departamento ) { //retorna o id do professor procurando esses dados no banco de dados
        return this.id;
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
    
    public int mediaAvaliacao () {
        if (this.listaAvaliacoes.isEmpty()) {
            return 0;
        }
        else {
            int notas= 0;
            for (Avaliacao avaliacao : this.listaAvaliacoes) {
                notas+=avaliacao.getNota();
            } 
            return notas/this.listaAvaliacoes.size();
        }
    }
    
    public ArrayList <Professor> mostraTodos () { //retorna todos os professores presentes no banco de dados
        return null;
    }
}
