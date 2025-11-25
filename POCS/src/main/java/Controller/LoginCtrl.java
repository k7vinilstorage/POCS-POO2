/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.*;
import Model.*;

/**
 *
 * @author gp51f
 */
public abstract class LoginCtrl {
    private static AlunoCtrl controleAluno = AlunoCtrl.AlunoCtrlCreate();
    private static ProfessorCtrl controleProf = ProfessorCtrl.ProfessorCtrlCreate();
    private static Aluno alunoAtual = null;
    private static Professor professorAtual = null;
    
    public static boolean login(String id, char[] senha){
            if(id.charAt(0) == 'a'){
                alunoAtual = controleAluno.selectTabela("Codigo", id);
                return true;
            }else{
                if(id.charAt(0) == 'p'){
                    professorAtual = controleProf.selectTabela("Codigo", id);
                    return true;
                }
                return false;
            }
    }

    public static void setAlunoAtual(Aluno alunoAtual) {
        LoginCtrl.alunoAtual = alunoAtual;
    }

    public static void setProfessorAtual(Professor professorAtual) {
        LoginCtrl.professorAtual = professorAtual;
    }

    public static Aluno getAlunoAtual() {
        return alunoAtual;
    }

    public static Professor getProfessorAtual() {
        return professorAtual;
    }
    
    
}
