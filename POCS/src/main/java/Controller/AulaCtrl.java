package Controller;
// conexoes com sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

//classes relacionadas e tipo de dado "Date"
import Model.Aluno;
import Model.Professor;
import java.util.Date;

public class AulaCtrl {
    private static AulaCtrl aulaCtrlUnic;
    Professor prof;
    Aluno aluno;
    String horario;
    Date dia;
    String tema;
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;
    
    //Singleton
    public static AulaCtrl AulaCtrlCriar(){
        if(aulaCtrlUnic == null){
            aulaCtrlUnic = new AulaCtrl();
        }
        return aulaCtrlUnic;
    }
    
    //Construtor
    private AulaCtrl(){
        prof = null;
        aluno = null;
        horario = null;
        dia = null;
        tema = null;
    }
    
    //Construtor Sobrecarga
    public AulaCtrl(Professor prof, Aluno aluno, String horario, Date dia, String tema) {
        this.prof = prof;
        this.aluno = aluno;
        this.horario = horario;
        this.dia = dia;
        this.tema = tema;
    }
    
    //CREATE
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Aula("
                + "CodigoAula int primary key not null,"
                + "Professor text,"
                + "Aluno text,"
                + "Horario text,"
                + "Dia Date,"
                + "Tema text)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Criando Tabela de Aula...");
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            System.out.println("Tabela Aula criada com sucesso...");
            
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("ERRO NO CARREGAMENTO: ");
            System.out.println(e);
        }   
    }
    
    //INSERT
    public void inserirTabela(){
        String aula1 = "INSERT INTO Aula VALUES(1,'Hodgson','Gabriel','2T3','2025-11-15','Transmissao Wireless')";
        String aula2 = "INSERT INTO Aula VALUES(2,'Weslley','Arthur','4M5','2025-11-25','Leitura de Sinais Analógicos')";
        String aula3 = "INSERT INTO Aula VALUES(3,'Gisele','João Alberto','5N1','2025-12-10','Conexoes MySQL')";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela de Aulas...");
            
            st = con.createStatement();
            st.executeUpdate(aula1);
            st.executeUpdate(aula2);
            st.executeUpdate(aula3);
            System.out.println("Aulas cadastradas com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de AULAS...");
            System.out.println(e);
        }
    }
    
    //SELECT (busca)
    public void selectTabela(){
        String busca= "SELECT * FROM Aula";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("RESULTADO DA CONSULTA EM AULAS...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("PROFESSOR: " + rs.getString(2));
                System.out.println("ALUNO: " + rs.getString(3));
                System.out.println("HORARIO: " + rs.getString(4));
                System.out.println("DIA: " + rs.getDate(5));
                System.out.println("TEMA: " + rs.getString(6));
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de AULAS...");
            System.out.println(e);
        }
    }
    
    //DELETE
    public void deleteTupla(){
        String delete = "DELETE FROM Aula WHERE CodigoAula = 1";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Excluindo uma AULA...");
            
            st = con.createStatement();
            st.executeUpdate(delete);
            System.out.println("AULA excluida com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha ao excluir AULA...");
            System.out.println(e);
        }
    }
    
    //SOBRECARGAS
    
    //INSERT COM VALORES ESPECIFICADOS
    public void inserirTabela(int codigo, String professor,String aluno, String horario, String dia, String tema){
        String aula = "INSERT INTO Aula VALUES(" + codigo + "," + professor + "," + aluno + "," + horario + "," + dia + "," + tema + ")";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela de Aulas...");
            
            st = con.createStatement();
            st.executeUpdate(aula);
            System.out.println("Aula cadastrada com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de AULA...");
            System.out.println(e);
        }
    }
    
    //SELECT COM VALORES ESPECIFICADOS PARA STRINGS
    public void selectTabela(String coluna, String condicao){
        String busca= "SELECT * FROM Aula WHERE " + coluna + " = " + condicao;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("RESULTADO DA CONSULTA EM AULAS...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("PROFESSOR: " + rs.getString(2));
                System.out.println("ALUNO: " + rs.getString(3));
                System.out.println("HORARIO: " + rs.getString(4));
                System.out.println("DIA: " + rs.getDate(5));
                System.out.println("TEMA: " + rs.getString(6));
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de AULAS...");
            System.out.println(e);
        }
    }
    
    //SELECT COM VALORES ESPECIFICADOS PARA INTEIROS
    public void selectTabela(String coluna, int condicao){
        String busca= "SELECT * FROM Aula WHERE " + coluna + " = " + condicao;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("RESULTADO DA CONSULTA EM AULAS...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("PROFESSOR: " + rs.getString(2));
                System.out.println("ALUNO: " + rs.getString(3));
                System.out.println("HORARIO: " + rs.getString(4));
                System.out.println("DIA: " + rs.getDate(5));
                System.out.println("TEMA: " + rs.getString(6));
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de AULAS...");
            System.out.println(e);
        }
    }
    
    //DELETE COM VALORES ESPECIFICADOS
    public void deleteTupla(int codigo){
        String delete = "DELETE FROM Aula WHERE CodigoAula = " + codigo;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Excluindo uma AULA...");
            
            st = con.createStatement();
            st.executeUpdate(delete);
            System.out.println("AULA excluida com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha ao excluir AULA...");
            System.out.println(e);
        }
    }
}
