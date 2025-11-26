/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.CoordenadorCtrl;
import Controller.ProfessorCtrl;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author arthu
 */
public class ViewProfTable extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ViewProfTable.class.getName());
    
    private static ViewProfTable viewProfTableUnic;
    
    int rowH = 0;
    
    private ViewProfTable(){
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public static ViewProfTable geraViewProfTable(){
        if(viewProfTableUnic == null){
            viewProfTableUnic = new ViewProfTable();
        }
        return viewProfTableUnic;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        alunoLb = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        profTab = new javax.swing.JTable();
        voltarBt = new javax.swing.JButton();
        novoProf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ver professores");
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
        alunoLb.setText("Professores");

        profTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "CPF", "Celular", "Idade", "Disciplina", "Formação", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        profTab.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                profTabFocusGained(evt);
            }
        });
        profTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profTabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(profTab);

        voltarBt.setText("Voltar");
        voltarBt.addActionListener(this::voltarBtActionPerformed);

        novoProf.setText("Novo Professor");
        novoProf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                novoProfFocusGained(evt);
            }
        });
        novoProf.addActionListener(this::novoProfActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(alunoLb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(novoProf))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(voltarBt)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alunoLb, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(novoProf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(voltarBt)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void profTabFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_profTabFocusGained

    }//GEN-LAST:event_profTabFocusGained

    private void voltarBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarBtActionPerformed
        dispose();
    }//GEN-LAST:event_voltarBtActionPerformed

    private void profTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profTabMouseClicked
        DefaultTableModel modelo = (DefaultTableModel) profTab.getModel();
        rowH = profTab.getSelectedRow();
        
        String pCod = modelo.getValueAt(rowH, 0).toString();
        
        if(DialogsView.createDialogs().infoOpDialog("Deseja exlcuir o preofessor?", "Exlcuir") == 0) {
            if(ProfessorCtrl.ProfessorCtrlCreate().deleteTabela(pCod)) {
                DialogsView.createDialogs().infoDialog("Professor excluido com sucesso", "Excluir");
            }
            else {
                DialogsView.createDialogs().errorDialog("Erro ao Excluir Professor \n O professor possui horarios cadastrados na agenda", "Erro");
            }
        }
    }//GEN-LAST:event_profTabMouseClicked

    private void novoProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoProfActionPerformed
        CadProfView.geraCadProfessor().setVisible(true);
    }//GEN-LAST:event_novoProfActionPerformed

    private void novoProfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_novoProfFocusGained
        
    }//GEN-LAST:event_novoProfFocusGained

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        
    }//GEN-LAST:event_formFocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        DefaultTableModel modelo = (DefaultTableModel) profTab.getModel();
        modelo.setRowCount(0);
        
        CoordenadorCtrl.CoordenadorCtrlCreate().selectFillProf();
    }//GEN-LAST:event_formWindowGainedFocus

    public void preencherTabela(Object[] linha){
        DefaultTableModel modelo = (DefaultTableModel) profTab.getModel();
        modelo.addRow(linha);
    }
    
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
        java.awt.EventQueue.invokeLater(() -> new ViewProfTable().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alunoLb;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton novoProf;
    private javax.swing.JTable profTab;
    private javax.swing.JButton voltarBt;
    // End of variables declaration//GEN-END:variables
}
