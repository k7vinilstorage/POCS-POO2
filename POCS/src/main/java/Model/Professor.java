package Model;

public class Professor extends Pessoa{
    private String formacao;
    private String disciplina;
    private String pCod;

    //Construtor
    public Professor() {
        super();
        formacao = "";
        disciplina = "";
        pCod = "";
    }
    
    //Construtor - Sobrecarga
    public Professor(String cpf, String nome, String celular, String email, String senha, int idade, String formacao, String disciplina, String pCod) {
        super(cpf, nome, celular, email, senha, idade);
        this.formacao = formacao;
        this.disciplina = disciplina;
        this.pCod = pCod;
    }

    //Getters
    public String getFormacao() {
        return formacao;
    }

    public String getDisciplina() {
        return disciplina;
    }
    
    public String getpCod() {
        return pCod;
    }

    //Setters
    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setpCod(String pCod) {
        this.pCod = pCod;
    }
}
