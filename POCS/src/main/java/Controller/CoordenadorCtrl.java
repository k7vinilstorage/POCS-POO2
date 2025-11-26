package Controller;

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
}
