package Model;

public class Horario {
    char diaSemana;
    char diaHorario;

    //Construtor
    public Horario() {
        diaSemana = ' ';
        diaHorario = ' ';
    }
    
    //Construtor - Sobrecarga
    public Horario(char diaSemana, char diaHorario) {
        this.diaSemana = diaSemana;
        this.diaHorario = diaHorario;
    }

    //getter
    public char getDiaSemana() {
        return diaSemana;
    }
    
    public char getDiaHorario() {
        return diaHorario;
    }
    
    //setter
    public void setDiaSemana(char diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setDiaHorario(char diaHorario) {
        this.diaHorario = diaHorario;
    }
    
    //métodos
    
}
