package Controller;

import java.sql.*;

public class AulaCtrl {
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;
    
    public static AulaCtrl singleAulaCtrl;
    
    private AulaCtrl(){
    
    }
    
    public static AulaCtrl createAulaCtrl(){
        if(singleAulaCtrl == null){
            singleAulaCtrl = new AulaCtrl();
        }
        return singleAulaCtrl;
    
    }
    
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Aula("
                + "Id INT auto_increment primary key not null"
                + "pCod VARCHAR(20)"
                + "aCod VARCHAR(20)"
                + "DiaS int"
                + "DiaH int"
                + "Status text)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Criando tabela");
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            System.out.println("Tabela criada com sucesso");
            
            st.close();
            con.close();
        
        }catch(Exception e){
            System.out.println("Falha na criação da tabela...");
            e.printStackTrace();
        }
    }    
        
    public void inserirTabela(){




    }
    
    
}
