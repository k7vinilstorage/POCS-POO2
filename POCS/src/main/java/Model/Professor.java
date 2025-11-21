package Model;

public class Professor extends Pessoa{
    String formacao;
    String disciplina;
    Horario horario[];
    String pCod;

    //Construtor
    public Professor() {
        super();
        formacao = "";
        disciplina = "";
        horario = new Horario[85];
        pCod = "";
    }
    
    //Construtor - Sobrecarga
    public Professor(String cpf, String nome, String celular, String email, int idade, String formacao, String disciplina, Horario[] horario, String pCod) {
        super(cpf, nome, celular, email, idade);
        this.formacao = formacao;
        this.disciplina = disciplina;
        this.horario = horario;
        this.pCod = pCod;
    }

    //Getters
    public String getFormacao() {
        return formacao;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public Horario[] getHorario() {
        return horario;
    }
    
    public String getpCod() {
        return pCod;
    }

    //Setters
    public void setHorario(Horario[] horario) {
        this.horario = horario;
    }

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
