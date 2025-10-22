package mavenbd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MavenBD{
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    
    public static void main(String[]args){
        //Create Table
        String sql1 = "CREATE TABLE IF NOT EXISTS aluno("
                + "id int primary key not null,"
                + "nome text,"
                + "email text)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Criando a Tabela...");
            
            st = con.createStatement();
            st.executeUpdate(sql1);
            System.out.println("Tabela criada com sucesso...");
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha no carregamento...");
        }
        
        //INSERIR DADOS
        
        String sql2 = "INSERT INTO aluno VALUES(1,'Gabriel','gabrielpinheiroferrari@gmail.com')";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela...");
            
            st = con.createStatement();
            st.executeUpdate(sql2);
            System.out.println("Aluno cadastrado com sucesso...");
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção...");
        }
        
        /*
        try{
            System.out.println("Conectando Driver");
            Class.forName(driver);
            System.out.println("Driver carregado com sucesso!!");
        }catch(Exception e){
            System.out.println("Falha na conexão");
            System.out.println(e);
        }
        
        try{
            System.out.println("Abrindo Conexão...");
            con = DriverManager.getConnection(url, user,senha);
            System.out.println("Conexão realizada com sucesso!!");
        }catch(Exception e){
            System.out.println("Falha na conexão");
            System.out.println(e);
            
        }*/
    }
    
}
