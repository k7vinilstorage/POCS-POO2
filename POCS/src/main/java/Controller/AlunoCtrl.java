package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class AlunoCtrl {
    // Código SQL para conexão com o banco
    String escola;
    String desenvolvimento;
    int aCod;
    
    static Statement st = null;
    static Connection con = null;
    static String url = "";
    static String senha = "";
    static String driver = "";
    static String user = "";
    
    public void criarTabela(){
        
    }
    
}
