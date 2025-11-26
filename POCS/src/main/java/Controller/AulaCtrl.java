package Controller;

import Model.Aluno;
import Model.Horario;
import Model.Professor;
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
                + "Id INT auto_increment primary key not null,"
                + "pCod VARCHAR(20),"
                + "aCod VARCHAR(20),"
                + "DiaS int,"
                + "DiaH int)";
        
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
        }
    }    
        
    public boolean inserirTabela(Horario h, Professor p){
        
        Aluno a = LoginCtrl.createLoginCtrl().getAlunoAtual();
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela..."); 
            
            String insertAln = "INSERT INTO Aula (pCod, aCod, DiaS, DiaH) VALUES (?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(insertAln);
            
            ps.setString(1, p.getpCod());
            ps.setString(2, a.getaCod());
            ps.setInt(3, h.getDiaSemana());
            ps.setInt(4, h.getDiaHorario());

            ps.executeUpdate();
            System.out.println("Aula cadastrado com sucesso...");
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de aula");
            System.out.println(e);
            return false;
        }
        
        AgendaCtrl.createAgendaCtrl().changeReserved(h, true, p.getpCod());
        
        return true;
    }
    
    public boolean deleteTabela(String codigo, int diaS, int diaH){
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);

            String sql = "DELETE FROM Aula WHERE aCod = ? AND DiaS = ? AND DiaH = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, codigo);
            ps.setInt(2, diaS);
            ps.setInt(3, diaH);

            ps.executeUpdate();

            ps.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println("Erro ao deletar");
            return false;
        }
        return true;
    }
    
}
