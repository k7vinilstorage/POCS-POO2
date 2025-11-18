/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import javax.swing.JOptionPane;

/**
 *
 * @author joao
 */
public class DialogsView {
    
        private static DialogsView dialogs;
    
    public static DialogsView createDialogs() {
        if(dialogs == null) {
            dialogs = new DialogsView();
        }
        return dialogs;
    }
        
    public void infoDialog(String info, String title) {
        JOptionPane.showMessageDialog(
                        null,
                        info,
                        title,
                        JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public void errorDialog(String info, String title) {
        JOptionPane.showMessageDialog(
                        null,
                        info,
                        title,
                        JOptionPane.ERROR_MESSAGE
        );
    }  
    
    public int errorOpDialog(String info, String title) {
        int op = JOptionPane.showConfirmDialog(
                        null,
                        info,
                        title,
                        JOptionPane.ERROR_MESSAGE
        );
        return op;
    }  
}
