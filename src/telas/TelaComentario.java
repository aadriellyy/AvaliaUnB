package telas;

import classes.Avaliacao;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucas
 */

public class TelaComentario extends javax.swing.JFrame {

    /**
     * Creates new form Comentario
     */
    public TelaComentario(Avaliacao avaliacao) {
         int id = avaliacao.getId();

        //habilitar os botões
        initComponents();
        btnNovoComentario.setEnabled(true);
        btnEditarComentario.setEnabled(true);
        btnCancelarComentario.setEnabled(false);
        btnOkComentario.setEnabled(false);
        btnExcluirComentario.setEnabled(false);
       //habilitar o campo de texto
        txtComentario.setEnabled(false);
        
        //setar dia e hora
        Calendar c = Calendar.getInstance();
        //System.out.println("data e hora atual " + c.getTime());
    }
    
    public TelaComentario(){
        initComponents();
        btnNovoComentario.setEnabled(true);
        btnEditarComentario.setEnabled(true);
        btnCancelarComentario.setEnabled(false);
        btnOkComentario.setEnabled(false);
        btnExcluirComentario.setEnabled(false);
        
        txtComentario.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInformacoesProfessor = new javax.swing.JPanel();
        lblDepartamentoComentario = new javax.swing.JLabel();
        lblInfoDepartamento = new javax.swing.JLabel();
        lblProfessor = new javax.swing.JLabel();
        lblInfoProfessor = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblInfoEmail = new javax.swing.JLabel();
        scrComentarios = new javax.swing.JScrollPane();
        lstListaComentarios = new javax.swing.JList<>();
        pnlAvaliaçãoDisciplina = new javax.swing.JPanel();
        lblNota = new javax.swing.JLabel();
        lblFeedback = new javax.swing.JLabel();
        lblInfoFeedback = new javax.swing.JLabel();
        lblInfoNota = new javax.swing.JLabel();
        sprSeparadorComentario = new javax.swing.JSeparator();
        pnlComentario = new javax.swing.JPanel();
        btnOkComentario = new javax.swing.JButton();
        txtComentario = new javax.swing.JTextField();
        btnNovoComentario = new javax.swing.JButton();
        btnEditarComentario = new javax.swing.JButton();
        btnCancelarComentario = new javax.swing.JButton();
        btnExcluirComentario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comentarios");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Imagens/iconComentario.png")).getImage()
        );

        pnlInformacoesProfessor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Informações do Professor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        lblDepartamentoComentario.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        lblDepartamentoComentario.setText("Departamento:");

        lblProfessor.setText("Professor:");

        lblEmail.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        lblEmail.setText("Email:");

        javax.swing.GroupLayout pnlInformacoesProfessorLayout = new javax.swing.GroupLayout(pnlInformacoesProfessor);
        pnlInformacoesProfessor.setLayout(pnlInformacoesProfessorLayout);
        pnlInformacoesProfessorLayout.setHorizontalGroup(
            pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDepartamentoComentario)
                    .addComponent(lblProfessor)
                    .addComponent(lblEmail))
                .addGap(18, 18, 18)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInformacoesProfessorLayout.setVerticalGroup(
            pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblProfessor)
                    .addComponent(lblInfoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDepartamentoComentario)
                    .addComponent(lblInfoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(lblEmail))
                    .addComponent(lblInfoEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        scrComentarios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        lstListaComentarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstListaComentarios.setToolTipText("");
        scrComentarios.setViewportView(lstListaComentarios);

        pnlAvaliaçãoDisciplina.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avaliação ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        lblNota.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        lblNota.setText("Nota: ");

        lblFeedback.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        lblFeedback.setText("Feedback:");

        javax.swing.GroupLayout pnlAvaliaçãoDisciplinaLayout = new javax.swing.GroupLayout(pnlAvaliaçãoDisciplina);
        pnlAvaliaçãoDisciplina.setLayout(pnlAvaliaçãoDisciplinaLayout);
        pnlAvaliaçãoDisciplinaLayout.setHorizontalGroup(
            pnlAvaliaçãoDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvaliaçãoDisciplinaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInfoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lblFeedback)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblInfoFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAvaliaçãoDisciplinaLayout.setVerticalGroup(
            pnlAvaliaçãoDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvaliaçãoDisciplinaLayout.createSequentialGroup()
                .addGroup(pnlAvaliaçãoDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNota)
                    .addComponent(lblFeedback))
                .addGap(0, 37, Short.MAX_VALUE))
            .addGroup(pnlAvaliaçãoDisciplinaLayout.createSequentialGroup()
                .addGroup(pnlAvaliaçãoDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfoFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAvaliaçãoDisciplinaLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblInfoNota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlComentario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faça seu comentario aqui!", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        btnOkComentario.setText("OK");

        txtComentario.setToolTipText("deixe seu feedback");
        txtComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComentarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlComentarioLayout = new javax.swing.GroupLayout(pnlComentario);
        pnlComentario.setLayout(pnlComentarioLayout);
        pnlComentarioLayout.setHorizontalGroup(
            pnlComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComentarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtComentario)
                .addContainerGap())
            .addGroup(pnlComentarioLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(btnOkComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );
        pnlComentarioLayout.setVerticalGroup(
            pnlComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComentarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtComentario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOkComentario)
                .addGap(5, 5, 5))
        );

        btnNovoComentario.setFont(new java.awt.Font("Malgun Gothic", 0, 11)); // NOI18N
        btnNovoComentario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/comentario.png"))); // NOI18N
        btnNovoComentario.setText("Novo Comentario");
        btnNovoComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoComentarioActionPerformed(evt);
            }
        });

        btnEditarComentario.setFont(new java.awt.Font("Malgun Gothic", 0, 11)); // NOI18N
        btnEditarComentario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editarComentario.png"))); // NOI18N
        btnEditarComentario.setText("Editar");
        btnEditarComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarComentarioActionPerformed(evt);
            }
        });

        btnCancelarComentario.setFont(new java.awt.Font("Malgun Gothic", 0, 11)); // NOI18N
        btnCancelarComentario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Cancelar.png"))); // NOI18N
        btnCancelarComentario.setText("Cancelar");
        btnCancelarComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarComentarioActionPerformed(evt);
            }
        });

        btnExcluirComentario.setFont(new java.awt.Font("Malgun Gothic", 0, 11)); // NOI18N
        btnExcluirComentario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/excluirComentario.png"))); // NOI18N
        btnExcluirComentario.setText("Excluir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInformacoesProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sprSeparadorComentario)
                    .addComponent(pnlAvaliaçãoDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(btnNovoComentario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditarComentario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelarComentario))
                            .addComponent(pnlComentario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrComentarios, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnExcluirComentario)
                                .addGap(72, 72, 72)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnlInformacoesProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sprSeparadorComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAvaliaçãoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(pnlComentario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNovoComentario)
                        .addComponent(btnEditarComentario)
                        .addComponent(btnCancelarComentario))
                    .addComponent(btnExcluirComentario))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComentarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComentarioActionPerformed

    private void btnNovoComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoComentarioActionPerformed
        // TODO add your handling code here:
        btnNovoComentario.setEnabled(false);
        btnEditarComentario.setEnabled(false);
        btnCancelarComentario.setEnabled(true);
        btnOkComentario.setEnabled(true);
        btnExcluirComentario.setEnabled(false);
        
       txtComentario.setEnabled(true);
    }//GEN-LAST:event_btnNovoComentarioActionPerformed

    private void btnEditarComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarComentarioActionPerformed
        // TODO add your handling code here:
        btnNovoComentario.setEnabled(false);
        btnEditarComentario.setEnabled(false);
        btnCancelarComentario.setEnabled(true);
        btnOkComentario.setEnabled(true);
        btnExcluirComentario.setEnabled(false);
        
        txtComentario.setEnabled(true);
    }//GEN-LAST:event_btnEditarComentarioActionPerformed

    private void btnCancelarComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarComentarioActionPerformed
        // TODO add your handling code here:
        btnNovoComentario.setEnabled(true);
        btnEditarComentario.setEnabled(true);
        btnCancelarComentario.setEnabled(false);
        btnOkComentario.setEnabled(false);
        btnExcluirComentario.setEnabled(false);
        
        txtComentario.setEnabled(false);
    }//GEN-LAST:event_btnCancelarComentarioActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaComentario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaComentario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarComentario;
    private javax.swing.JButton btnEditarComentario;
    private javax.swing.JButton btnExcluirComentario;
    private javax.swing.JButton btnNovoComentario;
    private javax.swing.JButton btnOkComentario;
    private javax.swing.JLabel lblDepartamentoComentario;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFeedback;
    private javax.swing.JLabel lblInfoDepartamento;
    private javax.swing.JLabel lblInfoEmail;
    private javax.swing.JLabel lblInfoFeedback;
    private javax.swing.JLabel lblInfoNota;
    private javax.swing.JLabel lblInfoProfessor;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblProfessor;
    private javax.swing.JList<String> lstListaComentarios;
    private javax.swing.JPanel pnlAvaliaçãoDisciplina;
    private javax.swing.JPanel pnlComentario;
    private javax.swing.JPanel pnlInformacoesProfessor;
    private javax.swing.JScrollPane scrComentarios;
    private javax.swing.JSeparator sprSeparadorComentario;
    private javax.swing.JTextField txtComentario;
    // End of variables declaration//GEN-END:variables
}
