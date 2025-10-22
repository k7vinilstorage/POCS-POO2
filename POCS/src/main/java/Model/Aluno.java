package Model;

public class Aluno extends Pessoa{
    String escola;
    String desenvolvimento;
    int aCod;

    //Construtor
    public Aluno() {
        escola = "";
        desenvolvimento = "";
        aCod = 0;
    }
    
    //Construtor - Sobrecarga
    public Aluno(String escola, String desenvolvimento, int aCod) {
        this.escola = escola;
        this.desenvolvimento = desenvolvimento;
        this.aCod = aCod;
    }

    public String getEscola() {
        return escola;
    }

    public String getDesenvolvimento() {
        return desenvolvimento;
    }

    public int getaCod() {
        return aCod;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public void setDesenvolvimento(String desenvolvimento) {
        this.desenvolvimento = desenvolvimento;
    }

    public void setaCod(int aCod) {
        this.aCod = aCod;
    }
}
