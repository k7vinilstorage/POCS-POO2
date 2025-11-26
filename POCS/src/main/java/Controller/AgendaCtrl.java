/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utils.Utils;
import View.PesqAulasView;
import Model.Horario;
import Model.Professor;
import View.AgendaView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

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
                + "DiaS int,"
                + "DiaH int,"
                + "Status text)";
        
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
    
    public boolean inserirTabela(Horario h, Professor p){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela..."); 
            
            String insertAln = "INSERT INTO Agenda (Pcod, DiaS, DiaH, Status) VALUES (?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(insertAln);
            
            ps.setString(1, p.getpCod());
            ps.setInt(2, h.getDiaSemana());
            ps.setInt(3, h.getDiaHorario());
            ps.setBoolean(4, h.getStatus());

            ps.executeUpdate();
            System.out.println("Agenda cadastrado com sucesso...");
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de alunos...");
            System.out.println(e);
            return false;
        }
        
        return true;
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
                  AgendaView.createAgendaView().preencherTabela(rs.getInt(4), rs.getInt(3));
            }
            
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean deleteTabela(String codigo, int diaS, int diaH){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);

            String sql = "DELETE FROM Agenda WHERE Pcod = ? AND DiaS = ? AND DiaH = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, codigo);
            ps.setInt(2, diaS);
            ps.setInt(3, diaH);

            ps.executeUpdate(); // executa DELETE

            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println("Erro ao deletar");
            return false;
        }
        return true;
    }

    
    public void selectPesqProfTb(String p){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String joinProf = "SELECT Professor.Nome,"
                    + " Professor.Disciplina,"
                    + " Agenda.DiaS, "
                    + "Agenda.DiaH FROM Professor INNER JOIN Agenda ON Professor.Codigo = Agenda.Pcod WHERE Professor.Nome LIKE ?";
            
            PreparedStatement ps = con.prepareStatement(joinProf);
            ps.setString(1, p);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Object[] linha = {
                    rs.getString("Nome"),
                    rs.getString("Disciplina"),
                    Utils.createUtils().converteDia(rs.getInt("DiaS")),
                    Utils.createUtils().converteHorario(rs.getInt("DiaH"))
                };
                PesqAulasView.geraPesqAulasView().preencherTabela(linha);
            }
            
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
        public void selectPesqMatTb(String m){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String joinProf = "SELECT Professor.Nome,"
                    + " Professor.Disciplina,"
                    + " Agenda.DiaS, "
                    + "Agenda.DiaH FROM Professor INNER JOIN Agenda ON Professor.Codigo = Agenda.Pcod WHERE Professor.Disciplina LIKE ?";
            
            PreparedStatement ps = con.prepareStatement(joinProf);
            ps.setString(1, m);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Object[] linha = {
                    rs.getString("Nome"),
                    rs.getString("Disciplina"),
                    Utils.createUtils().converteDia(rs.getInt("DiaS")),
                    Utils.createUtils().converteHorario(rs.getInt("DiaH"))
                };
                PesqAulasView.geraPesqAulasView().preencherTabela(linha);
            }
            
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean verificaHorarioExistente(Professor p, int rowH, int colD) {
        boolean result = false;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String findCpf = "SELECT * FROM Agenda WHERE Pcod = ? AND DiaS = ? AND DiaH = ?";
            PreparedStatement ps = con.prepareStatement(findCpf);
            ps.setString(1, p.getpCod());
            ps.setInt(2, colD);
            ps.setInt(3, rowH);
            ResultSet rs = ps.executeQuery();
            
            result = (!rs.next());
            
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return result;
    }    

    

    
}
