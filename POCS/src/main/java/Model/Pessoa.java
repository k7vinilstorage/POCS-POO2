package Model;

public abstract class Pessoa {
    
    String cpf;
    String nome;
    String celular;
    String email;
    String senha;
    int idade;
    
    //Construtor
    public Pessoa() {
        cpf = "";
        nome = "";
        celular = "";
        email = "";
        senha = "";
        idade = 0;
    }

    //Construtor - Sobrecarga
    public Pessoa(String cpf, String nome, String celular, String email, String senha, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
        this.senha = senha;
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
    
    public String getSenha() {
        return senha;
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
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
