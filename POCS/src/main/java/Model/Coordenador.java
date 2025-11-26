package Model;

public class Coordenador extends Pessoa{
    private String cCod;

    public Coordenador() {
        cCod = "";
    }
    
    public Coordenador(String cCod) {
        this.cCod = cCod;
    }

    public String getcCod() {
        return cCod;
    }
// teste netBeans
    public void setcCod(String cCod) {
        this.cCod = cCod;
    }
}
