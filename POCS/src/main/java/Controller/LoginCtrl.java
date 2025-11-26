/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.*;

/**
 *
 * @author gp51f
 */
public class LoginCtrl {
    private static AlunoCtrl controleAluno;
    private static ProfessorCtrl controleProf;
    private static Aluno alunoAtual;
    private static Professor professorAtual;
    private static Coordenador coordenadorAtual;
    
    private static LoginCtrl singleLoginCtrl;
    
    private LoginCtrl() {
        controleAluno = AlunoCtrl.AlunoCtrlCreate();
        controleProf = ProfessorCtrl.ProfessorCtrlCreate();
        alunoAtual = null;
        professorAtual = null;
        coordenadorAtual = null;
    }
    
    public static LoginCtrl createLoginCtrl() {
        if(singleLoginCtrl == null) {
            singleLoginCtrl = new LoginCtrl();
        }
        
        return singleLoginCtrl;
    }
    
    public static Coordenador getCoordenadorAtual() {
        return coordenadorAtual;
    }

    public static void setCoordenadorAtual(Coordenador coordenadorAtual) {
        LoginCtrl.coordenadorAtual = coordenadorAtual;
    }
    
    public void setAlunoAtual(Aluno alunoAtual) {
        LoginCtrl.alunoAtual = alunoAtual;
    }

    public void setProfessorAtual(Professor professorAtual) {
        LoginCtrl.professorAtual = professorAtual;
    }

    public Aluno getAlunoAtual() {
        return alunoAtual;
    }

    public Professor getProfessorAtual() {
        return professorAtual;
    }
    
    
}
