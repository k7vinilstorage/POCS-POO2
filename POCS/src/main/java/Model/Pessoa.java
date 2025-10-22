package Model;

public abstract class Pessoa {
    
    String cpf;
    String nome;
    String celular;
    String email;
    int idade;
    
    //Construtor
    public Pessoa() {
        cpf = "";
        nome = "";
        celular = "";
        email = "";
        idade = 0;
    }

    //Construtor - Sobrecarga
    public Pessoa(String cpf, String nome, String celular, String email, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
