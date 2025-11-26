/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Aluno;
import Utils.Utils;
import Utils.BdInfo;
import View.PesqAulasView;
import Model.Horario;
import Model.Professor;
import View.AgendaView;
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
    
    private static Connection con = null;
    private static String url = BdInfo.createBdInfo().getUrl();
    private static String senha = BdInfo.createBdInfo().getSenha();
    private static String user = BdInfo.createBdInfo().getUser();
    private static String driver = BdInfo.createBdInfo().getDriver();
    private static Statement st = null;
    private static ResultSet rs = null;
    
    private static AgendaCtrl singleAgendaCtrl;
    
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
                + "Reserved bool)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            
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
            con = DriverManager.getConnection(url,user,senha);; 
            
            String insertAln = "INSERT INTO Agenda (Pcod, DiaS, DiaH, Reserved) VALUES (?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(insertAln);
            
            ps.setString(1, p.getpCod());
            ps.setInt(2, h.getDiaSemana());
            ps.setInt(3, h.getDiaHorario());
            ps.setBoolean(4, h.getStatus());

            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de alunos...");
            System.out.println(e);
            return false;
        }
        
        return true;
    }
    
    public void selectTabelaProf(Professor p) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String findCpf = "SELECT * FROM Agenda WHERE Pcod = ?";
            PreparedStatement ps = con.prepareStatement(findCpf);
            ps.setString(1, p.getpCod());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                  AgendaView.createAgendaView().preencherTabela(rs.getInt(4), rs.getInt(3), rs.getBoolean(5));
            }
            
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void selectTabelaAlu(Aluno a) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String findCpf = "SELECT * FROM Aula WHERE aCod = ?";
            PreparedStatement ps = con.prepareStatement(findCpf);
            ps.setString(1, a.getaCod());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                  AgendaView.createAgendaView().preencherTabela(rs.getInt("DiaH"), rs.getInt("DiaS"), true);
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
                    + "Agenda.DiaH FROM Professor INNER JOIN Agenda ON Professor.Codigo = Agenda.Pcod WHERE Professor.Nome LIKE ? AND Agenda.Reserved = 0";
            
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
                    + "Agenda.DiaH FROM Professor INNER JOIN Agenda ON Professor.Codigo = Agenda.Pcod WHERE Professor.Disciplina LIKE ? AND Agenda.Reserved = 0";
            
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
        
    public boolean changeReserved(Horario h, boolean reserved, String pCod) {
    try {
        Class.forName(driver);
        con = DriverManager.getConnection(url, user, senha);

        String change = "UPDATE Agenda SET Reserved = ? "
                      + "WHERE Pcod = ? AND DiaS = ? AND DiaH = ?";

        PreparedStatement ps = con.prepareStatement(change);

        ps.setBoolean(1, reserved);
        ps.setString(2, pCod);
        ps.setInt(3, h.getDiaSemana());
        ps.setInt(4, h.getDiaHorario());

        int rowsUpdated = ps.executeUpdate();

        ps.close();
        con.close();

        return rowsUpdated > 0;
    }
    catch (Exception e) {
        System.out.println(e);
        return false;
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
