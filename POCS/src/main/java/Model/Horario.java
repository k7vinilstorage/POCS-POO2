package Model;

public class Horario {
    int diaSemana;
    int diaHorario;
    boolean status;

    //Construtor
    public Horario() {
        diaSemana = ' ';
        diaHorario = ' ';
        status = false;
    }
    
    //Construtor - Sobrecarga
    public Horario(int diaSemana, int diaHorario, boolean status) {
        this.diaSemana = diaSemana;
        this.diaHorario = diaHorario;
        this.status = status;
    }

    //getter
    public int getDiaSemana() {
        return diaSemana;
    }
    
    public int getDiaHorario() {
        return diaHorario;
    }
    
    public boolean getStatus() {
        return status;
    }
    
    //setter
    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setDiaHorario(int diaHorario) {
        this.diaHorario = diaHorario;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    //métodos
    
}
