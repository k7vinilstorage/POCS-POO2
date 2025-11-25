package Controller;
import Model.Horario;
import Model.Professor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ProfessorCtrl {
    // Código SQL para conexão com o banco
    static ProfessorCtrl professorUnic = null;
    String formacao;
    String disciplina;
    int pCod;
    
    static Connection con = null;
    static String url = "jdbc:mysql://localhost:3306/teste2";
    static String senha = "Gabriel123/";
    static String user = "root";
    static String driver = "com.mysql.cj.jdbc.Driver";
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
            System.out.println("Criando a Tabela Professor...");
            
            st = con.createStatement();
            st.executeUpdate(tabela);
            System.out.println("Tabela PROFESSOR criada com sucesso...");
            
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
        
        criarTabela();
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo PROFESSOR na Tabela...");
            
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
            System.out.println("Professor cadastrado com sucesso...");
            
            ps.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserção de professor...");
            System.out.println(e);
        }
    }
    
    //Buscar na Tabela
    public void selectTabela(){
        String busca= "SELECT * FROM Professor";
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
            while(rs.next()){
                System.out.println("CODIGO " + rs.getInt(1)); // 1 = primeira coluna
                System.out.println("NOME: " + rs.getString(2));
                System.out.println("DISCIPLINA: " + rs.getString(3));
                System.out.println("EMAIL: " + rs.getString(5));
                    
            }
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de PROFESSOR...");
            System.out.println(e);
        }
    }
    
    public Professor selectTabela(String coluna, String condicao){
        String busca= "SELECT * FROM Professor WHERE " + coluna + " = " + condicao;
        Professor p1 = null;
        
        try{
            Class.forName(driver); //driver
            con = DriverManager.getConnection(url,user,senha); //abro conexão
            System.out.println("RSULTADO DA CONSULTA...");
            st = con.createStatement();
            rs = st.executeQuery(busca);
            
           
            p1.setpCod(rs.getString(1));// 1 = primeira coluna
            p1.setNome(rs.getString(2));
            p1.setDisciplina(rs.getString(3));
            p1.setEmail(rs.getString(5));
              
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na consulta de alunos...");
            System.out.println(e);
        }
        
        return p1;
    }
    
    //Deletar da Tabela
    public void deleteTabela(String condicao){
        String delete = "DELETE FROM Professor WHERE Codigo = 1";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Excluindo um PROFESSOR...");
            
            st = con.createStatement();
            st.executeUpdate(delete);
            System.out.println("PROFESSOR excluido com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha ao excluir PROFESSOR...");
            System.out.println(e);
        }
    }
    
    //Sobrecargas
    // SOBRECARGA Inserir na Tabela
    public void inserirTabela(String nome, String disciplina, String horario, String email){
        String aluno = "INSERT INTO Professor VALUES(4," + nome + "," + disciplina + "," + email + ")";
        
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,senha);
            System.out.println("Inserindo na Tabela PROFESSOR...");
            
            st = con.createStatement();
            st.executeUpdate(aluno);
            System.out.println("PROFESSOR cadastrado com sucesso...");
            
            st.close();
            con.close();
            
        }catch(Exception e){
            System.out.println("Falha na inserçãode de PROFESSOR...");
            System.out.println(e);
        }
    }
    
}
