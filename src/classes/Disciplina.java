package classes;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import model.dao.DisciplinaDAO;

/**
 *
 * @author pedro
 */
public class Disciplina {
    private String nome;            //nome da disciplina
    private String departamento;    //departamento que oferta a disciplina
    private String codigo;          //código da disciplina
    private int horas;              //carga horária da disciplina
    private ArrayList <Professor> listaProfessores;   //lista dos professores que ofertam a disciplina

    public Disciplina (String nome, String departamento, String codigo, int horas) { //usar enum para departamento?
        this.nome= nome;
        this.departamento= departamento;
        this.codigo = codigo;
        if (horas >0){ //verificação de valor para o argumento passado para horas
            this.horas= horas;
        }
        else {
            throw new IllegalArgumentException("A carga horária da disciplina não pode ser um valor negativo");
        }
        listaProfessores = new ArrayList <>();
       }      //construtor que inicializa a lista de professores vazia e exige os demais atributos
   
    public Disciplina (String nome, String departamento, String codigo, int horas, ArrayList <Professor> listaProfessores) {
        this.nome= nome;
        this.departamento= departamento;
        this.codigo = codigo;
        this.horas= horas;
        this.listaProfessores= listaProfessores;
        for (Professor prof : listaProfessores) {
            if (!listaProfessores.isEmpty()){ //verifica se a lista de professores passada como argumento é vazia
            prof.addDisciplina(this);
            }
        }
      }    //construtor que passa uma lista com professores como argumento

    public Disciplina() { //construtor vazio
         // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getNome () {
        return this.nome;
    }
    
    public void setNome (String nome) {
        this.nome= nome;
    }
        
    public String getDepartamento() {
        return this.departamento;
    }
    
    public void setDepartamento (String departamento) {
        this.departamento= departamento;
    }
    
     public String getCodigo () {
        return this.codigo;
    }
     
    public void setCodigo (String codigo) {
        this.codigo=codigo;
    }
    
    public int getHoras () {
        return this.horas;
    }
    
    public void setHoras (int horas) {
        if (horas>0) { //verificação de valor
            this.horas= horas;
        }
        else {
            throw new IllegalArgumentException ("A carga horária deve ser um valor válido");
        }
    }
        
    public ArrayList <Professor> getListaProfessores (){
        return this.listaProfessores;
    }
    
    public void setListaProfessores(ArrayList<Professor> listaProfessores){
        this.listaProfessores= listaProfessores;
    }
    
    public void addListaProfessores (Professor professor){ //adiciona um professor a lista de professores
        if (!this.listaProfessores.contains(professor)){
            this.listaProfessores.add(professor);
            if (!professor.getDisciplinas().contains(this)){
                professor.addDisciplina(this);
            }
        }
    }
    
    public ArrayList <Professor> melhoresProfessores() {    //retorna uma lista com um ranking dos melhores professores de acordo com a média de avaliação de cada professor naquela disciplina
        ArrayList <Double> avaliacoes = new ArrayList<Double>();
        ArrayList <Professor> rankingProfessores = new ArrayList <Professor>();
        for (Professor i : this.listaProfessores) {
            avaliacoes.add(i.mediaAvaliacao());
        }
        Collections.sort(avaliacoes);
        Collections.reverse(avaliacoes);
        for (Double avaliacao: avaliacoes) {
            for  (Professor professor : this.listaProfessores){
                if (professor.mediaAvaliacao() == avaliacao && !rankingProfessores.contains(professor)){
                    rankingProfessores.add(professor);
                }
            }         
        }

        return rankingProfessores;
    }
    
    public ArrayList<String> mostrarMelhoresProfessores (){     //retorna uma lista com os nomes dos professores em ordem de melhor avaliação, usando o método melhoresProfessores()
        ArrayList <String> listaMelhoresProfessores = new ArrayList<>();
        ArrayList <Professor> rankingProfessor = this.melhoresProfessores();
        for (Professor professor : rankingProfessor){
            listaMelhoresProfessores.add(professor.getNome());
        }
        return listaMelhoresProfessores;
    }
    
    public void limpaProfessores(){  //método para limpar a lista de professores da disciplina
        this.listaProfessores.clear();
    }
        
}