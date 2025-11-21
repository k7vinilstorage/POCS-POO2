package Controller;
import Model.Horario;
import Model.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProfessorCtrl {
    // Código SQL para conexão com o banco
    static ProfessorCtrl professorUnic = null;
    String formacao;
    String disciplina;
    Horario horario[];
    int pCod;
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;

    //Singleton
    public static ProfessorCtrl ProfessorCtrlCreate(){ 
        if(professorUnic == null){
            professorUnic = new ProfessorCtrl();
        }
        return professorUnic;
    }
    
    //Construtor
    private ProfessorCtrl() {
        formacao = "";
        disciplina = "";
        horario = null;
        pCod = 0;
    }
    
    //Sobrecarga Construtor
    public ProfessorCtrl(String formacao, String disciplina, Horario horario[], int pCod) {
        this.formacao = formacao;
        this.disciplina = disciplina;
        this.horario = horario;
        this.pCod = pCod;
    }
    
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Professor("
                + "Codigo int primary key not null,"
                + "Nome text,"
                + "Disciplina text,"
                + "Horario text,"
                + "Email text)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Criando a Tabela Professor...");
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            System.out.println("Tabela PROFESSOR criada com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha ao criar tabela PROFESSOR ...");
            System.out.println(e);
        }
    }
    
    public void inserirTabela(Professor prof){
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo PROFESSOR na Tabela...");
            
            st = con.createStatement();
            System.out.println("Professor cadastrado com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de professor...");
            System.out.println(e);
        }
    }
    
    //Buscar na Tabela
    public void selectTabela(){
        String busca= "SELECT * FROM Professor";
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("NOME: " + rs.getString(2));
                System.out.println("DISCIPLINA: " + rs.getString(3));
                System.out.println("HORARIO: " + rs.getString(4));
                System.out.println("EMAIL: " + rs.getString(5));
                    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de PROFESSOR...");
            System.out.println(e);
        }
    }
    
    //Deletar da Tabela
    public void deleteTabela(String condicao){
        String delete = "DELETE FROM Professor WHERE Codigo = 1";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Excluindo um PROFESSOR...");
            
            st = con.createStatement();
            st.executeUpdate(delete);
            System.out.println("PROFESSOR excluido com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha ao excluir PROFESSOR...");
            System.out.println(e);
        }
    }
    
    //Sobrecargas
    // SOBRECARGA Inserir na Tabela
    public void inserirTabela(String nome, String disciplina, String horario, String email){
        String aluno = "INSERT INTO Professor VALUES(4," + nome + "," + disciplina + "," + horario + "," + email + ")";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela PROFESSOR...");
            
            st = con.createStatement();
            st.executeUpdate(aluno);
            System.out.println("PROFESSOR cadastrado com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserçãode de PROFESSOR...");
            System.out.println(e);
        }
    }
    
    // SOBRECARGA Consulta
    public void selectTabela(String coluna, String condicao){
        String busca= "SELECT * FROM Professor WHERE" + coluna + "=" + condicao; // ex: id = 1
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("NOME: " + rs.getString(2));
                System.out.println("DISCIPLINA: " + rs.getString(3));
                System.out.println("HORAIRO: " + rs.getString(4));
                System.out.println("EMAIL: " + rs.getString(5));    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de aluno...");
            System.out.println(e);
        }
    }
}
