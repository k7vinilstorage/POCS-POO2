package Controller;

import Model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import Utils.Utils;

public class CoordenadorCtrl {
    
    private static CoordenadorCtrl coordenadorCtrlUnic;
    int cCod;
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;
    
    //singleton
    public static CoordenadorCtrl CoordenadorCtrlCreate(){
        if(coordenadorCtrlUnic == null){
            coordenadorCtrlUnic = new CoordenadorCtrl();
        }
        return coordenadorCtrlUnic;
    }
    
    private CoordenadorCtrl(){
        cCod = 0;
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
}
