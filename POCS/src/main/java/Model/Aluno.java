package Model;

public class Aluno extends Pessoa{
    String escola;
    String desenvolvimento;
    String aCod;

    //Construtor
    public Aluno() {
        super();
        escola = "";
        desenvolvimento = "";
        aCod = "";
    }
    
    //Construtor - Sobrecarga
    public Aluno(String cpf, String nome, String celular, String email, int idade, String escola, String desenvolvimento, String aCod) {
        super(cpf, nome, celular, email, idade);
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

    public String getaCod() {
        return aCod;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public void setDesenvolvimento(String desenvolvimento) {
        this.desenvolvimento = desenvolvimento;
    }

    public void setaCod(String aCod) {
        this.aCod = aCod;
    }
}
