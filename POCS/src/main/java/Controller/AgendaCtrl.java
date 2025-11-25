/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Model.Horario;
import Model.Professor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author joao
 */
public class AgendaCtrl {
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;
    
    public static AgendaCtrl singleAgendaCtrl;
    
    private AgendaCtrl(){
        
    }
    
    public static AgendaCtrl createAgendaCtrl() {
        if(singleAgendaCtrl == null) {
            singleAgendaCtrl = new AgendaCtrl();
        }
        return singleAgendaCtrl;
    }
    
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Agenda("
                + "Id INT auto_increment primary key not null,"
                + "Pcod text,"
                + "DiaS text,"
                + "DiaH text)";
        
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
    
    public void inserirTabela(Horario h, Professor p){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela..."); 
            
            String insertAln = "INSERT INTO Agenda VALUES(?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(insertAln);
            
            ps.setString(2, p.getpCod());
            ps.setString(3, String.valueOf(h.getDiaSemana()));
            ps.setString(4, String.valueOf(h.getDiaHorario()));

            ps.executeUpdate();
            System.out.println("Agenda cadastrado com sucesso...");
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de alunos...");
            System.out.println(e);
        }
    }
    
    public void selectTabela(Professor p) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String findCpf = "SELECT * FROM Agenda WHERE Pcod = ?";
            PreparedStatement ps = con.prepareStatement(findCpf);
            ps.setString(1, p.getpCod());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("Id: " + rs.getString(1));
                System.out.println("Pcod: " + rs.getString(2));
                System.out.println("DiaS: " + rs.getString(3));
                System.out.println("DiaHorario: " + rs.getString(4));
            }
            
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
