package classes;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author pedro
 */
public class Disciplina {
    private String nome;
    private String departamento;
    private String codigo;
    private int horas;
    private ArrayList <Professor> listaProfessores = new ArrayList <Professor>();

   public Disciplina (String nome, String departamento, String codigo, int horas) {
       this.nome= nome;
       this.departamento= departamento;
       this.codigo = codigo;
       this.horas= horas;
   }
      public Disciplina (String nome, String departamento, String codigo, int horas, ArrayList <Professor> listaProfessores) {
       this.nome= nome;
       this.departamento= departamento;
       this.codigo = codigo;
       this.horas= horas;
       this.listaProfessores= listaProfessores;
   }
    
    public ArrayList <Professor> melhoresProfessores() {
        ArrayList <Double> avaliacoes = new ArrayList<Double>();
        ArrayList <Professor> rankingProfessores = new ArrayList <Professor>();
        for (Professor i : listaProfessores) {
            avaliacoes.add(i.mediaAvaliacao(this));
        }
        Collections.sort(avaliacoes);
        Collections.reverse(avaliacoes);
        for (Double avaliacao: avaliacoes) {
            for  (Professor professor : listaProfessores){
                if (professor.mediaAvaliacao(this) == avaliacao){
                    rankingProfessores.add(professor);
                    break;
                }
            }         
        }
        return rankingProfessores;
    }
    
    public ArrayList<String> mostrarMelhoresProfessores (){
        ArrayList <String> listaMelhoresProfessores = new ArrayList<>();
        ArrayList <Professor> rankingProfessor = this.melhoresProfessores();
        for (Professor professor : rankingProfessor){
            listaMelhoresProfessores.add(professor.getNome());
        }
        return listaMelhoresProfessores;
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
        this.horas= horas;
    }
    
    public void setListaProfessores (Professor professor){
        this.listaProfessores.add(professor);
    }
    
    public ArrayList <Professor> getListaProfessores (){
        return this.listaProfessores;
    }

}