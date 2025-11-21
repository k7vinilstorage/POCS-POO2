package Model;
import java.util.Date;

public class Aula {
    Professor prof;
    Aluno aluno;
    String horario;
    Date dia;
    String tema;
    
    //Construtor
    public Aula() {
        prof = new Professor();
        aluno = new Aluno();
        horario = "";
        dia = new Date();
        tema = "";
    }

    //Construtor - Sobrecarga
    public Aula(Professor prof, Aluno aluno, String horario, Date dia, String tema) {
        this.prof = prof;
        this.aluno = aluno;
        this.horario = horario;
        this.dia = dia;
        this.tema = tema;
    }

    //Getters
    public Professor getProf() {
        return prof;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getHorario() {
        return horario;
    }

    public Date getDia() {
        return dia;
    }

    public String getTema() {
        return tema;
    }

    //Setters
    public void setProf(Professor prof) {
        this.prof = prof;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
