
package Testes;
import Controller.AlunoCtrl;
import Controller.ProfessorCtrl;
import Controller.AulaCtrl;

import java.util.Date;

public class Teste_CRUD {
    
    public static void main(String[] args){
        AlunoCtrl aluno = AlunoCtrl.AlunoCtrlCreate();
        ProfessorCtrl professor = ProfessorCtrl.ProfessorCtrl();
        AulaCtrl aula = AulaCtrl.AulaCtrlCriar();
        
        //aluno.criarTabela(); //funciona
        //aluno.inserirTabela(); //funciona
        //aluno.selectTabela(); //funciona
        //aluno.inserirTabela("'Lusia'","'Extraordinário'","'lusia@gmail.com'");
        //aluno.selectTabela();
        //aluno.selectTabela("Nome","'Gabriel'"); // funciona (tem que colocar texto entre '' dentro das "")
        //aluno.deleteTabela("Nome");
        
        //professor.criarTabela(); //funciona
        //professor.inserirTabela(); //funciona
        //professor.selectTabela(); //funciona
        //professor.inserirTabela("'Ana Cristina'", "'Ciências'", "'5T2'", "'AnaCris@gmai.com'"); //funciona
        //professor.selectTabela();
        //professor.deleteTabela("'Gabriel'"); //funciona
        //professor.selectTabela("'Codigo'", "'2'");
        
        aula.criarTabela(); //funciona
        aula.inserirTabela(); //funciona
        aula.selectTabela();
        
        aula.inserirTabela(4,"'Rhuan'","'Gabriel'","'3M5'","'2025-12-11'","'Acesso aos Registradores'"); //funciona
        aula.deleteTupla(2); //funciona
        aula.selectTabela("CodigoAula", 3); //funciona
        
    }
}
