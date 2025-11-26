/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.AlunoCtrl;
import Controller.CoordenadorCtrl;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arthu
 */
public class ViewAlunoTable extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewAlunoTable.class.getName());
    
     int rowH = 0;
    
    private static ViewAlunoTable viewAlunoTableUnic;
    
    private ViewAlunoTable(){
        initComponents();
    }
    
    public static ViewAlunoTable geraViewAlunoTable(){
        if(viewAlunoTableUnic == null){
            viewAlunoTableUnic = new ViewAlunoTable();
        }
        return viewAlunoTableUnic;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        alunoLb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alunoTab = new javax.swing.JTable();
        voltarBt = new javax.swing.JButton();
        novoAlu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        alunoLb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        alunoLb.setText("Alunos");

        alunoTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nome", "CPF", "Celular", "Idade", "Desenvolvimento", "Escola", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        alunoTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                alunoTabFocusGained(evt);
            }
        });
        alunoTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                alunoTabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(alunoTab);

        voltarBt.setText("Voltar");
        voltarBt.addActionListener(this::voltarBtActionPerformed);

        novoAlu.setText("Novo Aluno");
        novoAlu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                novoAluFocusGained(evt);
            }
        });
        novoAlu.addActionListener(this::novoAluActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(alunoLb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(novoAlu))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(voltarBt)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alunoLb)
                    .addComponent(novoAlu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(voltarBt)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarBtActionPerformed
        dispose();
    }//GEN-LAST:event_voltarBtActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        
    }//GEN-LAST:event_formFocusGained

    private void alunoTabFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_alunoTabFocusGained
        
    }//GEN-LAST:event_alunoTabFocusGained

    private void alunoTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alunoTabMouseClicked
        DefaultTableModel modelo = (DefaultTableModel) alunoTab.getModel();
        rowH = alunoTab.getSelectedRow();

        String aCod = modelo.getValueAt(rowH, 0).toString();

        if(DialogsView.createDialogs().infoOpDialog("Deseja exlcuir o aluno?", "Exlcuir") == 0) {
            if(AlunoCtrl.AlunoCtrlCreate().deleteTabela(aCod)) {
                DialogsView.createDialogs().infoDialog("Aluno excluido com sucesso", "Excluir");
            }
            else {
                DialogsView.createDialogs().errorDialog("Erro ao Excluir Aluno \n O Aluno possui aulas cadastrados na agenda", "Erro");
            }
        }        
    }//GEN-LAST:event_alunoTabMouseClicked

    private void novoAluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoAluActionPerformed
        CadAluView.geraCadAluno().setVisible(true);
    }//GEN-LAST:event_novoAluActionPerformed

    private void novoAluFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_novoAluFocusGained
        
    }//GEN-LAST:event_novoAluFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        DefaultTableModel modelo = (DefaultTableModel) alunoTab.getModel();
        modelo.setRowCount(0);
        
        CoordenadorCtrl.CoordenadorCtrlCreate().selectFillAluno();
    }//GEN-LAST:event_formWindowGainedFocus
    
    public void preencherTabela(Object[] linha){
        DefaultTableModel modelo = (DefaultTableModel) alunoTab.getModel();
        modelo.addRow(linha);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ViewAlunoTable().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alunoLb;
    private javax.swing.JTable alunoTab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton novoAlu;
    private javax.swing.JButton voltarBt;
    // End of variables declaration//GEN-END:variables
}
