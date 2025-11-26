/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author joao
 */
public class BdInfo {
    private String url;
    private String senha;
    private String user;
    private String driver;
    
    private static BdInfo singleBdInfo;

    private BdInfo() {
        url = "jdbc:mysql://localhost:3306/teste2";
        senha = "Gabriel123/";
        user = "root";
        driver = "com.mysql.cj.jdbc.Driver";
    }
    
    public static BdInfo createBdInfo() {
        if(singleBdInfo == null) {
            singleBdInfo = new BdInfo();
        }
        return singleBdInfo;
    }
    
    public String getUrl() {
        return url;
    }

    public String getSenha() {
        return senha;
    }

    public String getUser() {
        return user;
    }

    public String getDriver() {
        return driver;
    }
    
    
}
