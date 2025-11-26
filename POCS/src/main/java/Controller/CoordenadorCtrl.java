package Controller;

import Model.*;
import Utils.BdInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import Utils.Utils;
import View.ViewAlunoTable;
import View.ViewAulaTable;
import View.ViewProfTable;

public class CoordenadorCtrl {
    
    private static CoordenadorCtrl coordenadorCtrlUnic;
    
    private static Connection con = null;
    private static String url = BdInfo.createBdInfo().getUrl();
    private static String senha = BdInfo.createBdInfo().getSenha();
    private static String user = BdInfo.createBdInfo().getUser();
    private static String driver = BdInfo.createBdInfo().getDriver();
    private static Statement st = null;
    private static ResultSet rs = null;
    
    //singleton
    public static CoordenadorCtrl CoordenadorCtrlCreate(){
        if(coordenadorCtrlUnic == null){
            coordenadorCtrlUnic = new CoordenadorCtrl();
        }
        return coordenadorCtrlUnic;
    }
    
    private CoordenadorCtrl(){

    }
    
    public Coordenador selectTabela(String coluna, String condicao){
        Coordenador c1 = new Coordenador();
        Coordenador coordReturn = null;
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            String findCoord = "SELECT * FROM Coordenador WHERE " + coluna + " = ? ";
		PreparedStatement ps = con.prepareStatement(findCoord);
		ps.setString(1, condicao);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){
			coordReturn = c1;
			
			c1.setcCod(rs.getString(1));
			c1.setNome(rs.getString(2));
			c1.setCpf(rs.getString(3));
			c1.setSenha(rs.getString(4));
			c1.setCelular(rs.getString(5));
			c1.setIdade(rs.getInt(6));
			c1.setEmail(rs.getString(7));

		}
		
		rs.close();
		ps.close();
		con.close();
	}catch(Exception e){
		System.out.println("Falha na consulta de coord");
		e.printStackTrace();
	}

	return coordReturn;
    }
    
    public void selectFillProf(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String fillProf = "SELECT Codigo, Nome, Cpf, Celular, Idade, Disciplina, Formacao, Email FROM Professor";
            
            st = con.createStatement();
            rs = st.executeQuery(fillProf);
            
            while(rs.next()){
                Object[] linha ={
                    rs.getString("Codigo"),
                    rs.getString("Nome"),
                    rs.getString("Cpf"),
                    rs.getString("Celular"),
                    rs.getInt("Idade"),
                    rs.getString("Disciplina"),
                    rs.getString("Formacao"),
                    rs.getString("Email")
                };
                ViewProfTable.geraViewProfTable().preencherTabela(linha);
            }
            rs.close();
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Erro na busca");
            e.printStackTrace();
        }
    }
    
    public void selectFillAluno(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String fillAluno = "SELECT Codigo, Nome, Cpf, Celular, Idade, Desenvolvimento, Escola, Email FROM Aluno";
            
            st = con.createStatement();
            rs = st.executeQuery(fillAluno);
            
            while(rs.next()){
                Object[] linha ={
                    rs.getString("Codigo"),
                    rs.getString("Nome"),
                    rs.getString("Cpf"),
                    rs.getString("Celular"),
                    rs.getInt("Idade"),
                    rs.getString("Desenvolvimento"),
                    rs.getString("Escola"),
                    rs.getString("Email")
                };
                ViewAlunoTable.geraViewAlunoTable().preencherTabela(linha);
            }
            rs.close();
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Erro na busca");
            e.printStackTrace();
        }
    }
    
    public void selectFillAula(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String fillAluno = "SELECT pCod, aCod, DiaS, DiaH FROM Aula";
            
            st = con.createStatement();
            rs = st.executeQuery(fillAluno);
            
            while(rs.next()){
                Object[] linha ={
                    rs.getString("pCod"),
                    rs.getString("aCod"),
                    Utils.createUtils().converteDia(rs.getInt("DiaS")),
                    Utils.createUtils().converteHorario(rs.getInt("DiaH"))
                };
                ViewAulaTable.geraViewAulaTable().preencherTabela(linha);
            }
            rs.close();
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Erro na busca");
            e.printStackTrace();
        }
    }
    
}
