package Utils;

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
            System.out.println("Erro ao consultar professor...");
            System.out.println(e);
        }
        return result;
    } 
}
