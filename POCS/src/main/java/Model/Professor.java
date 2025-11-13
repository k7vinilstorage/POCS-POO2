package Model;

public class Professor extends Pessoa{
    String formacao;
    String disciplina;
    Horario horario[];
    int pCod;

    //Construtor
    public Professor() {
        formacao = "";
        disciplina = "";
        horario = new Horario[85];
        pCod = 0;
    }
    
    //Construtor - Sobrecarga
    public Professor(String formacao, String disciplina, Horario[] horario, int tamanho, int pCod) {
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
    
    public int getpCod() {
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

    public void setpCod(int pCod) {
        this.pCod = pCod;
    }
}
