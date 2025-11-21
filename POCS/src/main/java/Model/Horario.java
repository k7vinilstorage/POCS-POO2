package Model;

public class Horario {
    char diaSemana;
    char diaPeriodo;
    char diaHorario;

    //Construtor
    public Horario() {
        diaSemana = ' ';
        diaPeriodo = ' ';
        diaHorario = ' ';
    }
    
    //Construtor - Sobrecarga
    public Horario(char diaSemana, char diaPeriodo, char diaHorario) {
        this.diaSemana = diaSemana;
        this.diaPeriodo = diaPeriodo;
        this.diaHorario = diaHorario;
    }

    //getter
    public char getDiaSemana() {
        return diaSemana;
    }

    public char getDiaPeriodo() {
        return diaPeriodo;
    }
    
    public char getDiaHorario() {
        return diaHorario;
    }
    
    //setter
    public void setDiaSemana(char diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setDiaPeriodo(char diaPeriodo) {
        this.diaPeriodo = diaPeriodo;
    }

    public void setDiaHorario(char diaHorario) {
        this.diaHorario = diaHorario;
    }
    
    //métodos
    
}
