
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class HorarioCtrl {
    private static HorarioCtrl horarioUnic;
    int diaSemana;
    char diaPeriodo;
    char diaHorario;
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;

    //SINGLETON
    public static HorarioCtrl HorarioCtrlGera(){
        if(horarioUnic == null){
            horarioUnic = new HorarioCtrl();
        }
        return horarioUnic;
    }
    
    //CONSTRUTOR
    private HorarioCtrl(){
        diaSemana = 0;
        diaPeriodo = ' ';
        diaHorario = ' ';
    }
    
    //SOBRECARGA CONSTRUTOR
    public HorarioCtrl(int diaSemana, char diaPeriodo, char diaHorario) {
        this.diaSemana = diaSemana;
        this.diaPeriodo = diaPeriodo;
        this.diaHorario = diaHorario;
    }
    
    //CREATE
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Horario("
                + "CodigoHorario int primary key not null,"
                + "Dia int,"
                + "Periodo char,"
                + "Horario char)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Criando tabela HORARIO");
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            System.out.println("Tabela HORARIO criada com sucesso...");
            
        }catch(Exception e){
            System.out.println("Erro ao criar Tabela HORARIO");
            System.out.println(e);
        }
    }
    
    //INSERT
    public void inserirTupla(){
        String horario1 = "INSERT INTO Horario VALUES(1, 2, 'M', '5')";
        String horario2 = "INSERT INTO Horario VALUES(2, 3, 'T', '3')";
        String horario3 = "INSERT INTO Horario VALUES(3, 4, 'N', '2')";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na tabela HORARIO...");
            
            st = con.createStatement();
            st.executeUpdate(horario1);
            st.executeUpdate(horario2);
            st.executeUpdate(horario3);
            
            System.out.println("Horarios Cadastrados com sucesso...");
            
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println("Erro ao inserir na tabela HORARIO");
            System.out.println(e);
        }
    }
}
