package classes;

public class Pessoa {

    /*VARIAVEIS: nome, departamento e email*/
    private String name;
    private String departamento;
    private String email;
    static int id;

    /*Construtor da classe*/
    public Pessoa(String name, String departamento, String email){
        this.name = name;
        this.departamento = departamento;
        this.email = email;
        id++;
    }

    public Pessoa(){}

    public String getNome(){
        return this.name;
    }

    public String getDepartamento(){
        return this.departamento;
    }

    public String getEmail(){
        return this.email;
    }
    
}
