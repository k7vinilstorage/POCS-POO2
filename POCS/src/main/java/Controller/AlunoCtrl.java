package Controller;

import Model.Aluno;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import Utils.Utils;
import View.DialogsView;

public class AlunoCtrl {
    private static AlunoCtrl alunoCtrlUnic;
    String escola;
    String desenvolvimento;
    int aCod;
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static Statement st = null;
    static ResultSet rs = null;

    //Singleton
    public static AlunoCtrl AlunoCtrlCreate(){
        if(alunoCtrlUnic == null){
            alunoCtrlUnic = new AlunoCtrl();
        }
        
        return alunoCtrlUnic;
    }
    
    //Construtor
    private AlunoCtrl(){
        escola = "";
        desenvolvimento = "";
        aCod = 0;
    }
    
    //Construtor Sobrecarga
    public AlunoCtrl(String escola, String desenvolvimento, int aCod) {
        this.escola = escola;
        this.desenvolvimento = desenvolvimento;
        this.aCod = aCod;
    }
    
    
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Aluno("
                + "Codigo VARCHAR(20) primary key not null,"
                + "Cpf text,"
                + "Senha text,"
                + "Nome text,"
                + "Celular text,"
                + "Idade int,"
                + "Desenvolvimento text,"
                + "Escola text,"
                + "Email text)";
        
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
    
    public String geraCodigo(){ 
        String busca = "SELECT Codigo FROM Aluno ORDER BY Codigo DESC LIMIT 1"; 

        try { 
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            st = con.createStatement();
            rs = st.executeQuery(busca);

            // 1) Verifica se há algum resultado
            if (!rs.next()) {
                return "a0001"; // Nenhum aluno existe
            }

            // 2) Recupera o código existente
            String codAluno = rs.getString("Codigo");

            // 3) Gera próximo código
            String numero = codAluno.substring(1);  // remove 'a'
            int n = Integer.parseInt(numero) + 1;

            return String.format("a%04d", n);

        } catch (Exception e) { 
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch(Exception e){}
            try { if (st != null) st.close(); } catch(Exception e){}
            try { if (con != null) con.close(); } catch(Exception e){}
        }

        return null;
    }
    //alterado 21/11/25
    public void inserirTabela(Aluno aln){
        
        criarTabela();
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela..."); //Vai continuar esses?
            
            String insertAln = "INSERT INTO Aluno VALUES(?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = con.prepareStatement(insertAln);
            
            String cod  = geraCodigo();
            
            ps.setString(1, cod);//codigo
            ps.setString(2, aln.getCpf());//cpf
            ps.setString(3, aln.getSenha());//senha
            ps.setString(4, aln.getNome());//nome
            ps.setString(5, aln.getCelular());//celular
            ps.setInt(6, aln.getIdade());//idade
            ps.setString(7, aln.getDesenvolvimento());//desenvolvimento
            ps.setString(8, aln.getEscola());//escola
            ps.setString(9, aln.getEmail());//email
            
            ps.executeUpdate();
            System.out.println("Alunos cadastrado com sucesso...");
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de alunos...");
            System.out.println(e);
        }
    }
    
    //Busca (select)
    public void selectTabela(){
        String busca= "SELECT * FROM aluno";
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("Codigo: " + rs.getString(1)); // 1 = primeira coluna
                System.out.println("NOME: " + rs.getString(2));
                System.out.println("DESEMPENHO: " + rs.getString(3));
                System.out.println("EMAIL: " + rs.getString(4));
                    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de alunos...");
            System.out.println(e);
        }
    }
    
    public Aluno selectTabela(String coluna, String condicao){
        Aluno a1 = new Aluno();
        Aluno alunoReturn = null;
        try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, senha);
                String findCpf = "SELECT * FROM Aluno WHERE " + coluna + " = ?";
                PreparedStatement ps = con.prepareStatement(findCpf);
                ps.setString(1, condicao);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    alunoReturn = a1;
                
                    a1.setaCod(rs.getString(1));// 1 = primeira coluna
                    a1.setCpf(rs.getString(2));
                    a1.setSenha(rs.getString(3));
                    a1.setNome(rs.getString(4));
                    a1.setCelular(rs.getString(5));
                    a1.setIdade(rs.getInt(6));
                    a1.setDesenvolvimento(rs.getString(7));
                    a1.setEscola(rs.getString(8));
                    a1.setEmail(rs.getString(9));
                }
                
                
                rs.close();
                ps.close();
                con.close();
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        
        return alunoReturn;
    }
    
    public boolean atualizaAluno(){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            
            String mudanca = "UPDATE Aluno SET Email = ?"
                    + "WHERE Codigo = ?";
            
            PreparedStatement ps = con.prepareStatement(mudanca);
            return true;
        }catch(Exception e){
            DialogsView.createDialogs().errorDialog("Não foi possível atualizar aluno", "Erro");
            return false;
        }
    }
//        
//    //delete (falta testar)
//    public void deleteTabela(String condicao){
//        String delete = "DELETE FROM aluno WHERE Codigo = 1";
//        
//        try{
//            Class.forName(driver);
//            con = DriverManager.getConnection(url,user,senha);
//            System.out.println("Excluindo um aluno...");
//            
//            st = con.createStatement();
//            st.executeUpdate(delete);
//            System.out.println("Aluno excluido com sucesso...");
//            
//            st.close();
//            con.close();
//            
//        }catch(Exception e){
//            System.out.println("Falha ao excluir aluno...");
//            System.out.println(e);
//        }
//    }
    
    //Sobrecargas
    //Sobrecarga Inserir
//    public void inserirTabela(String nome, String desempenho, String email){
//        String aluno = "INSERT INTO aluno VALUES(4," + nome + "," + desempenho + "," + email + ")";
//        
//        try{
//            Class.forName(driver);
//            con = DriverManager.getConnection(url,user,senha);
//            System.out.println("Inserindo na Tabela...");
//            
//            st = con.createStatement();
//            st.executeUpdate(aluno);
//            System.out.println("Aluno cadastrado com sucesso...");
//            
//            st.close();
//            con.close();
//            
//        }catch(Exception e){
//            System.out.println("Falha na inserçãode aluno...");
//            System.out.println(e);
//        }
//    }
    
    //Sobrecarga de busca
//    public void selectTabela(String coluna, int condicao){
//        String busca= "SELECT * FROM aluno WHERE" + coluna + "=" + condicao+ ";"; // ex: id = 1
//        
//        try{
//            Class.forName(driver); //driver
//            con = DriverManager.getConnection(url,user,senha); //abro conexão
//            System.out.println("RSULTADO DA CONSULTA...");
//            st = con.createStatement();
//            rs = st.executeQuery(busca);
//            
//            while(rs.next()){
//                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
//                System.out.println("NOME: " + rs.getString(2));
//                System.out.println("DESEMPENHO: " + rs.getString(3));
//                System.out.println("EMAIL: " + rs.getString(4));
//                    
//            }
//            st.close();
//            con.close();
//            
//        }catch(Exception e){
//            System.out.println("Falha na consulta de aluno...");
//            System.out.println(e);
//        }
//    }
    
//    public void selectTabela(String coluna, String condicao){
//        String busca = "SELECT * FROM aluno WHERE " + coluna + " = " + condicao; // ex: Codigo = 1
//        
//        try{
//            Class.forName(driver); //driver
//            con = DriverManager.getConnection(url,user,senha); //abro conexão
//            System.out.println("RESULTADO DA CONSULTA...");
//            st = con.createStatement();
//            rs = st.executeQuery(busca);
//            
//            while(rs.next()){
//                System.out.println("CODIGO: " + rs.getInt(1)); // 1 = primeira coluna
//                System.out.println("NOME: " + rs.getString(2));
//                System.out.println("DESEMPENHO: " + rs.getString(3));
//                System.out.println("EMAIL: " + rs.getString(4));
//                    
//            }
//            st.close();
//            con.close();
//            
//        }catch(Exception e){
//            System.out.println("Falha na consulta de alunos...");
//            System.out.println(e);
//        }
//    }
    
    //Sobrecarga de busca
//    public void selectTabela(String escolha, String coluna, String condicao){
//        String busca= "SELECT" + escolha + "FROM aluno WHERE" + coluna + "=" + condicao+ ";"; // ex: nome; id = 1
//        
//        try{
//            Class.forName(driver); //driver
//            con = DriverManager.getConnection(url,user,senha); //abro conexão
//            System.out.println("RSULTADO DA CONSULTA...");
//            st = con.createStatement();
//            rs = st.executeQuery(busca);
//            
//            while(rs.next()){
//                System.out.println(escolha + rs.getString(escolha));
//                    
//            }
//            st.close();
//            con.close();
//            
//        }catch(Exception e){
//            System.out.println("Falha na inserçãode alunos...");
//            System.out.println(e);
//        }
//    }
    
}
