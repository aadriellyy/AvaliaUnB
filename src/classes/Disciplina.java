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
    private ArrayList <Professor> listaProfessores;
    public ArrayList <Professor> melhoresProfessores() {
        ArrayList <Integer> avaliacoes = new ArrayList<Integer>();
        ArrayList <Professor> rankingProfessores = new ArrayList <Professor>();
        for (Professor i : listaProfessores) {
            avaliacoes.add(i.mediaAvaliacao());
        }
        Collections.sort(avaliacoes);
        for (Professor i : listaProfessores) {
            int indice= avaliacoes.get(i.mediaAvaliacao());
            rankingProfessores.add(listaProfessores.get(indice));
        }
        return rankingProfessores;
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

}
