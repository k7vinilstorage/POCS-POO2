
package Model;
import java.util.Date;

public class Aula {
    Professor prof;
    Aluno aluno;
    String horario;
    Date dia;
    String tema;
    
    public Aula() {
        prof = new Professor();
        aluno = new Aluno();
        horario = "";
        dia = new Date();
        tema = "";
    }

    public Aula(Professor prof, Aluno aluno, String horario, Date dia, String tema) {
        this.prof = prof;
        this.aluno = aluno;
        this.horario = horario;
        this.dia = dia;
        this.tema = tema;
    }

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
