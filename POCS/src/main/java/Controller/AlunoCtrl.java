package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class AlunoCtrl {
    private static AlunoCtrl alunoCtrlUnic;
    String escola;
    String desenvolvimento;
    int aCod;
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;

    //Singleton
    public static AlunoCtrl AlunoCtrlCreate(){
        if(alunoCtrlUnic == null){
            alunoCtrlUnic = new AlunoCtrl();
        }
        
        return alunoCtrlUnic;
    }
    
    //Construtor
    private AlunoCtrl(){
        escola = "";
        desenvolvimento = "";
        aCod = 0;
    }
    
    //Construtor Sobrecarga
    public AlunoCtrl(String escola, String desenvolvimento, int aCod) {
        this.escola = escola;
        this.desenvolvimento = desenvolvimento;
        this.aCod = aCod;
    }
    
    
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Aluno("
                + "Codigo int primary key not null,"
                + "Nome text,"
                + "Desenvolvimento text,"
                + "Escola text,"
                + "Email text)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Criando a Tabela...");
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            System.out.println("Tabela criada com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha no carregamento...");
            System.out.println(e);
        }
    }
    
    public void inserirTabela(){
        String aluno1 = "INSERT INTO aluno VALUES(1,'Arthur Jardim','Bom','artJard@gmail.com')";
        String aluno2 = "INSERT INTO aluno VALUES(2,'Joao Alberto','Ótimo','joaoTore@gmail.com')";
        String aluno3 = "INSERT INTO aluno VALUES(3,'Gabriel','Excelente','gabrielpinheiroferrari@gmail.com')";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela...");
            
            st = con.createStatement();
            st.executeUpdate(aluno1);
            st.executeUpdate(aluno2);
            st.executeUpdate(aluno3);
            System.out.println("Alunos cadastrado com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de alunos...");
            System.out.println(e);
        }
    }
    
    //Busca (select)
    public void selectTabela(){
        String busca= "SELECT * FROM aluno";
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("Codigo: " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("NOME: " + rs.getString(2));
                System.out.println("DESEMPENHO: " + rs.getString(3));
                System.out.println("EMAIL: " + rs.getString(4));
                    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de alunos...");
            System.out.println(e);
        }
    }
    
    //delete
    public void deleteTabela(String condicao){
        String delete = "DELETE FROM aluno WHERE Codigo = 1";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Excluindo um aluno...");
            
            st = con.createStatement();
            st.executeUpdate(delete);
            System.out.println("Aluno excluido com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha ao excluir aluno...");
            System.out.println(e);
        }
    }
    
    //Sobrecargas
    //Sobrecarga Inserir
    public void inserirTabela(String nome, String desempenho, String email){
        String aluno = "INSERT INTO aluno VALUES(4," + nome + "," + desempenho + "," + email + ")";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela...");
            
            st = con.createStatement();
            st.executeUpdate(aluno);
            System.out.println("Aluno cadastrado com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserçãode aluno...");
            System.out.println(e);
        }
    }
    
    //Sobrecarga de busca
    public void selectTabela(String coluna, int condicao){
        String busca= "SELECT * FROM aluno WHERE" + coluna + "=" + condicao+ ";"; // ex: id = 1
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("NOME: " + rs.getString(2));
                System.out.println("DESEMPENHO: " + rs.getString(3));
                System.out.println("EMAIL: " + rs.getString(4));
                    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de aluno...");
            System.out.println(e);
        }
    }
    
    public void selectTabela(String coluna, String condicao){
        String busca = "SELECT * FROM aluno WHERE " + coluna + " = " + condicao; // ex: Codigo = 1
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RESULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("NOME: " + rs.getString(2));
                System.out.println("DESEMPENHO: " + rs.getString(3));
                System.out.println("EMAIL: " + rs.getString(4));
                    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de alunos...");
            System.out.println(e);
        }
    }
    
    //Sobrecarga de busca
    public void selectTabela(String escolha, String coluna, String condicao){
        String busca= "SELECT" + escolha + "FROM aluno WHERE" + coluna + "=" + condicao+ ";"; // ex: nome; id = 1
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println(escolha + rs.getString(escolha));
                    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserçãode alunos...");
            System.out.println(e);
        }
    }
    
}
