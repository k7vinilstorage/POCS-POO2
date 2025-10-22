package Model;

public class Coordenador extends Pessoa{
    int cCod;

    public Coordenador() {
        cCod = 0;
    }
    
    public Coordenador(int cCod) {
        this.cCod = cCod;
    }

    public int getcCod() {
        return cCod;
    }

    public void setcCod(int cCod) {
        this.cCod = cCod;
    }
}
