package Controller;
import Model.Horario;
import Model.Professor;
import Utils.BdInfo;
import View.DialogsView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProfessorCtrl {
    // Código SQL para conexão com o banco
    private static ProfessorCtrl professorUnic = null;
    String formacao;
    String disciplina;
    int pCod;
    
    static Connection con = null;
    static String url = BdInfo.createBdInfo().getUrl();
    static String senha = BdInfo.createBdInfo().getSenha();
    static String user = BdInfo.createBdInfo().getUser();
    static String driver = BdInfo.createBdInfo().getDriver();
    static Statement st = null;
    static ResultSet rs = null;

    //Singleton
    public static ProfessorCtrl ProfessorCtrlCreate(){ 
        if(professorUnic == null){
            professorUnic = new ProfessorCtrl();
        }
        return professorUnic;
    }
    
    //Construtor
    private ProfessorCtrl() {
        formacao = "";
        disciplina = "";
        pCod = 0;
    }
    
    //Sobrecarga Construtor
    public ProfessorCtrl(String formacao, String disciplina, Horario horario[], int pCod) {
        this.formacao = formacao;
        this.disciplina = disciplina;
        this.pCod = pCod;
    }
    
    public void criarTabela(){
        String tabela = "CREATE TABLE IF NOT EXISTS Professor("
                + "Codigo VARCHAR(20) primary key not null,"
                + "Cpf text,"
                + "Senha text,"
                + "Nome text,"
                + "Celular text,"
                + "Idade int,"
                + "Formacao text,"
                + "Disciplina text,"
                + "Email text)";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha ao criar tabela PROFESSOR ...");
            System.out.println(e);
        }
    }
    
    public String geraCodigo(){ 
        String busca = "SELECT Codigo FROM Professor ORDER BY Codigo DESC LIMIT 1"; 

        try { 
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            st = con.createStatement();
            rs = st.executeQuery(busca);

            // 1) Verifica se há algum resultado
            if (!rs.next()) {
                return "p0001"; // Nenhum aluno existe
            }

            // 2) Recupera o código existente
            String codProf = rs.getString("Codigo");

            // 3) Gera próximo código
            String numero = codProf.substring(1);  // remove 'a'
            int n = Integer.parseInt(numero) + 1;

            return String.format("p%04d", n);

        } catch (Exception e) { 
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch(Exception e){}
            try { if (st != null) st.close(); } catch(Exception e){}
            try { if (con != null) con.close(); } catch(Exception e){}
        }

        return null;
    }
    
    public void inserirTabela(Professor prof){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            
            String insertProf = "INSERT INTO Professor VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement(insertProf);
            
            String cod = geraCodigo();
            
            ps.setString(1, cod);//codigo
            ps.setString(2, prof.getCpf());//cpf
            ps.setString(3, prof.getSenha());//senha
            ps.setString(4, prof.getNome());//nome
            ps.setString(5, prof.getCelular());//celular
            ps.setInt(6, prof.getIdade());//idade
            ps.setString(7, prof.getFormacao());//formacao
            ps.setString(8, prof.getDisciplina());//disciplina
            ps.setString(9,prof.getEmail());
            
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de professor...");
            System.out.println(e);
        }
    }
    
    public Professor selectTabela(String coluna, String condicao){
        Professor p1 = new Professor();
        Professor profReturn = null;
        try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, senha);
                String findCpf = "SELECT * FROM Professor WHERE " + coluna + " = ?";
                PreparedStatement ps = con.prepareStatement(findCpf);
                ps.setString(1, condicao);
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    profReturn = p1;
                
                    p1.setpCod(rs.getString(1));
                    p1.setCpf(rs.getString(2));
                    p1.setSenha(rs.getString(3));
                    p1.setNome(rs.getString(4));
                    p1.setCelular(rs.getString(5));
                    p1.setIdade(rs.getInt(6));
                    p1.setFormacao(rs.getString(7));
                    p1.setDisciplina(rs.getString(8));
                    p1.setEmail(rs.getString(9));
                }
                
                
                rs.close();
                ps.close();
                con.close();
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        
        return profReturn;
    }
    
    public boolean deleteTabela(String pCod){
        boolean result = false;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, senha);
            String findCpf = "SELECT * FROM Agenda WHERE pCod = ?";
            PreparedStatement ps = con.prepareStatement(findCpf);
            ps.setString(1, pCod);
            ResultSet rs = ps.executeQuery();
            result = (!rs.next());
            rs.close();
            ps.close();
            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        if(result) {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, senha);

                String sql = "DELETE FROM Professor WHERE Codigo = ?";
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, pCod);

                ps.executeUpdate();


                ps.close();
                con.close();

            }
            catch (Exception e) {
                System.out.println("Erro ao deletar professor");
                return false;
            }
        }
        else {
            return false;
        }
        
        return true;
    }
    
    //Sobrecargas
    // SOBRECARGA Inserir na Tabela
    public void inserirTabela(String nome, String disciplina, String horario, String email){
        String aluno = "INSERT INTO Professor VALUES(4," + nome + "," + disciplina + "," + email + ")";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            
            st = con.createStatement();
            st.executeUpdate(aluno);
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserçãode de PROFESSOR...");
            System.out.println(e);
        }
    }
    
    public boolean atualizaEmailProfessor(String email, String id){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            
            String mudanca = "UPDATE Professor SET Email = ?"
                    + "WHERE Codigo = ?";
            
            PreparedStatement ps = con.prepareStatement(mudanca);
            
            ps.setString(1,email);
            ps.setString(2, id);
            
            ps.executeUpdate();
            
            ps.close();
            con.close();
            
            Professor profTeste = selectTabela("Codigo",id);
            LoginCtrl.createLoginCtrl().setProfessorAtual(profTeste);
            DialogsView.createDialogs().infoDialog("Atualizacao realizada com sucesso", "Atualização");
            return true;
        }catch(Exception e){
            DialogsView.createDialogs().errorDialog("Não foi possível atualizar professor", "Erro");
            return false;
        }
    }
    
    public boolean atualizaCelularProfessor(String celular, String id){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            
            String mudanca = "UPDATE Professor SET Celular = ?"
                    + "WHERE Codigo = ?";
            
            PreparedStatement ps = con.prepareStatement(mudanca);
            
            ps.setString(1,celular);
            ps.setString(2, id);
            
            ps.executeUpdate();
            
            ps.close();
            con.close();
            Professor profTeste = selectTabela("Codigo",id);
            LoginCtrl.createLoginCtrl().setProfessorAtual(profTeste);
            DialogsView.createDialogs().infoDialog("Atualizacao realizada com sucesso", "Atualização");
            return true;
        }catch(Exception e){
            DialogsView.createDialogs().errorDialog("Não foi possível atualizar aluno", "Erro");
            return false;
        }
    }
}
