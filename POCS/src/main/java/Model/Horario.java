package Model;

public class Horario {
    char diaSemana;
    char diaHorario;
    boolean status;

    //Construtor
    public Horario() {
        diaSemana = ' ';
        diaHorario = ' ';
        status = false;
    }
    
    //Construtor - Sobrecarga
    public Horario(char diaSemana, char diaHorario, boolean status) {
        this.diaSemana = diaSemana;
        this.diaHorario = diaHorario;
        this.status = status;
    }

    //getter
    public char getDiaSemana() {
        return diaSemana;
    }
    
    public char getDiaHorario() {
        return diaHorario;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    //setter
    public void setDiaSemana(char diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setDiaHorario(char diaHorario) {
        this.diaHorario = diaHorario;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    //métodos
    
}
