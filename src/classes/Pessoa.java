package classes;

public abstract class Pessoa {

    /*VARIAVEIS: nome, departamento e email*/
    private String name;
    private String departamento;
    private String email;

    /*Construtor da classe*/
    public Pessoa(String name, String departamento, String email){
        this.name = name;
        this.departamento = departamento;
        this.email = email;
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
