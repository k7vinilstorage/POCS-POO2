package Utils;

import View.*;
import Model.*;
import Controller.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author joao
 */
public class Utils {
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;
    
    private static Utils singleUtils;
    private DialogsView aviso = DialogsView.createDialogs();
    private AlunoCtrl alunoCtrl = AlunoCtrl.AlunoCtrlCreate();
    private ProfessorCtrl professorCtrl = ProfessorCtrl.ProfessorCtrlCreate();
    private CoordenadorCtrl coordenadorCtrl = CoordenadorCtrl.CoordenadorCtrlCreate();
    
    public static Utils createUtils() {
        if(singleUtils == null) {
            singleUtils = new Utils();
        }
        return singleUtils;
    }
    
    public boolean validarCpf(String cpf) {
        char cpf_char [] = cpf.toCharArray();
        int cpf_int[] = new int[11];
        int count = 0;
        int verify = 0;
        for(int i = 0; i < cpf_char.length; i++) {
            if(cpf_char[i] == '.' || cpf_char[i] == '-');
            else {
                cpf_int[count] = Character.getNumericValue(cpf_char[i]);
                count++;
            } 
        }
        boolean all_equal = true;
        for (int i = 1; i < 11; i++) {
            if (cpf_int[i] != cpf_int[0]) {
                all_equal = false;
                break;
            }
        }
        if(all_equal) {
            return false;
        }
        for(int i = 0; i < cpf_int.length - 2; i++) {
            verify += cpf_int[i] * (10 - i);
            
        }
        verify = verify * 10;
        verify = verify % 11;
        if(verify == cpf_int[9]) {
            verify = 0;
            for(int i = 0; i < cpf_int.length - 1; i++) {
                verify += cpf_int[i] * (11 - i);
            }   
            verify = verify * 10;
            verify = verify % 11;
            if(verify == cpf_int[10]) {
                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }
    
    public boolean verificarEmail(String email) {
        return email.contains(String.valueOf("@"));  
    }
    
    public boolean verificarCpfDuplicado(String cpf) {
        boolean result = false;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String findCpf = "SELECT * FROM Professor WHERE Cpf = ?";
            PreparedStatement ps = con.prepareStatement(findCpf);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            result = (!rs.next());
            rs.close();
            ps.close();
            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        if(!result) {
            return result;
        }
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String findCpf = "SELECT * FROM Aluno WHERE Cpf = ?";
            PreparedStatement ps = con.prepareStatement(findCpf);
            ps.setString(1, cpf);
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
    
    public int tipoUsuario(String id){
        switch (id.charAt(0)) {
            case 'a':
                return 1;
            case 'p':
                return 2;
            case 'c':
                return 3;
            default:
                aviso.errorDialog("Id não é válido", "ERRO DE USUÁRIO");
                return 0;
        }
    }
    
    public int verificaBd(int tipo, String id){
        Aluno a1 = null;
        Professor p1 = null;
        Coordenador c1 = null;
        
        switch(tipo){
            case 1: 
                a1 = alunoCtrl.selectTabela("Codigo", id);
                if(a1 == null){
                    return 0;
                }
                break;
            case 2:
                p1 = professorCtrl.selectTabela("Codigo", id);
                if(p1 == null){
                    return 0;
                }
                break;
            case 3:
                //c1 = coordenadorCtrl.selectTabela("Codigo", id);
                if(c1 == null){
                    return 0;
                }
        }
        
        
        return 1;
    }
    
    public int verificaSenha(int tipo, String id, String senha){
        Aluno a1 = null;
        Professor p1 = null;
        Coordenador c1 = null;
        
        switch(tipo){
            case 1: 
                a1 = alunoCtrl.selectTabela("Codigo", id);
                if(a1.getSenha().equals(senha)){
                    return 1;
                }
                break;
            case 2:
                p1 = professorCtrl.selectTabela("Codigo", id);
                if(p1.getSenha().equals(senha)){
                    return 1;
                }
                break;
            case 3: 
                //c1 = coordenadorCtrl.selectTabela("Codigo", id);
                if(c1.getSenha().equals(senha)){
                    return 1;
                }
            default:
                return 0;
        }
                
        return 0;
    }
    
    public String converteDia(int dia){
        switch(dia){
            case 0:
                return "Segunda";
            case 1:
                return "Terça";
            case 2:
                return "Quarta";
            case 3:
                return "Quinta";
            case 4:
                return "Sexta";
            default:
                return null;
        }
    }
    
    public String converteHorario(int horario){
        switch(horario){
            case 0:
                return "06:00-07:00";
            case 1:
                return "07:00-08:00";
            case 2:
                return "08:00-09:00";
            case 3:
                return "09:00-10:00";
            case 4:
                return "10:00-11:00";
            case 5:
                return "11:00-12:00";
            case 6:
                return "12:00-13:00";
            case 7:
                return "13:00-14:00";
            case 8:
                return "14:00-15:00";
            case 9:
                return "15:00-16:00";
            case 10:
                return "16:00-17:00";
            case 11:
                return "17:00-18:00";
            default:
                return null;
        }
    }
    
    public int desConverteDia(String dia){
        switch(dia){
            case "Segunda":
                return 0;
            case "Terça":
                return 1;
            case "Quarta":
                return 2;
            case "Quinta":
                return 3;
            case "Sexta":
                return 4;
            default:
                return -1;
        }
    }
    
    public int desConverteHorario(String horario){
        switch(horario){
            case "06:00-07:00":
                return 0;
            case "07:00-08:00":
                return 1;
            case "08:00-09:00":
                return 2;
            case "09:00-10:00":
                return 3;
            case "10:00-11:00":
                return 4;
            case "11:00-12:00":
                return 5;
            case "12:00-13:00":
                return 6;
            case "13:00-14:00":
                return 7;
            case "14:00-15:00":
                return 8;
            case "15:00-16:00":
                return 9;
            case "16:00-17:00":
                return 10;
            case "17:00-18:00":
                return 11;
            default:
                return -1;
        }
    }
}
