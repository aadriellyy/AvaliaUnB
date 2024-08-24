/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import classes.Aluno;
import classes.Professor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.dao.AlunoDAO;
import model.dao.AvaliacaoDAO;

/**
 *
 * @author limaa
 */
public class TelaAvaliacao extends javax.swing.JFrame {

    /**
     * Creates new form TelaAvaliacao
     */
    private String matriculaAluno;
    public TelaAvaliacao(){
        initComponents();
        this.limpar();
        this.inicial();
    }
    
    public TelaAvaliacao(String matricula) {
        initComponents();
        this.limpar();
        this.inicial();
        this.matriculaAluno = matricula;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNome2 = new javax.swing.JLabel();
        lblNome3 = new javax.swing.JLabel();
        lblNome4 = new javax.swing.JLabel();
        txtNomeProfessor = new javax.swing.JTextField();
        txtDepartamento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsMaterias = new javax.swing.JList<>();
        btnAvaliar = new javax.swing.JButton();
        btnCancelarPesquisa = new javax.swing.JButton();
        btnExibir = new javax.swing.JButton();
        lblBusca = new javax.swing.JPanel();
        txtNota = new javax.swing.JTextField();
        txtNomePesquisa = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblFiltrar = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnOk = new javax.swing.JButton();
        lblNome = new javax.swing.JLabel();
        lblNome1 = new javax.swing.JLabel();
        lblAvaliacao = new javax.swing.JPanel();
        txtFeedback = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelarAvaliacao = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        lblNome5 = new javax.swing.JLabel();
        lblNome6 = new javax.swing.JLabel();
        txtNotaAvaliacao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/icon-avaliacao.png"))); // NOI18N

        lblNome2.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome2.setText("Nome:");

        lblNome3.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome3.setText("Departamento:");

        lblNome4.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome4.setText("Matérias:");

        txtNomeProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeProfessorActionPerformed(evt);
            }
        });

        txtDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartamentoActionPerformed(evt);
            }
        });

        lsMaterias.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lsMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsMateriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lsMaterias);

        btnAvaliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/nova-avaliacao.png"))); // NOI18N
        btnAvaliar.setText("Avaliar");
        btnAvaliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvaliarActionPerformed(evt);
            }
        });

        btnCancelarPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/cancelar-avaliacao.png"))); // NOI18N
        btnCancelarPesquisa.setText("Cancelar");
        btnCancelarPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarPesquisaActionPerformed(evt);
            }
        });

        btnExibir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/pesquisar-avaliacao.png"))); // NOI18N
        btnExibir.setText("Exibir avaliações");
        btnExibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExibirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNome2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNome3)
                                .addGap(18, 18, 18)
                                .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblNome4)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAvaliar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelarPesquisa)
                        .addGap(63, 63, 63)
                        .addComponent(btnExibir)
                        .addGap(52, 52, 52))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome2)
                            .addComponent(txtNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome3)
                            .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAvaliar)
                    .addComponent(btnCancelarPesquisa)
                    .addComponent(btnExibir))
                .addContainerGap())
        );

        lblBusca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar Professor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N
        lblBusca.setToolTipText("Buscar por professor");

        txtNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNotaActionPerformed(evt);
            }
        });

        txtNomePesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomePesquisaActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblFiltrar.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblFiltrar.setText("Filtrar por:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Discplina" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Departamento" }));

        btnOk.setBackground(new java.awt.Color(0, 204, 51));
        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        lblNome.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome.setText("Nome:");

        lblNome1.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome1.setText("Nota:");

        javax.swing.GroupLayout lblBuscaLayout = new javax.swing.GroupLayout(lblBusca);
        lblBusca.setLayout(lblBuscaLayout);
        lblBuscaLayout.setHorizontalGroup(
            lblBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblBuscaLayout.createSequentialGroup()
                .addGroup(lblBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblBuscaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFiltrar)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lblBuscaLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)
                        .addGap(52, 52, 52)
                        .addComponent(lblNome1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        lblBuscaLayout.setVerticalGroup(
            lblBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblBuscaLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(lblBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFiltrar)
                    .addGroup(lblBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOk)))
                .addGap(34, 34, 34)
                .addGroup(lblBuscaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNomePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(lblNome1)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAvaliacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avaliar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        txtFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFeedbackActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/salvar-avaliacao.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelarAvaliacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/cancelar-avaliacao.png"))); // NOI18N
        btnCancelarAvaliacao.setText("Cancelar");
        btnCancelarAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAvaliacaoActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/excluir-avaliacao.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        lblNome5.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome5.setText("Feedback:");

        lblNome6.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome6.setText("Nota");

        txtNotaAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNotaAvaliacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lblAvaliacaoLayout = new javax.swing.GroupLayout(lblAvaliacao);
        lblAvaliacao.setLayout(lblAvaliacaoLayout);
        lblAvaliacaoLayout.setHorizontalGroup(
            lblAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblAvaliacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblAvaliacaoLayout.createSequentialGroup()
                        .addComponent(lblNome5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNome6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNotaAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblAvaliacaoLayout.createSequentialGroup()
                        .addComponent(txtFeedback)
                        .addContainerGap())
                    .addGroup(lblAvaliacaoLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir)
                        .addGap(151, 151, 151)
                        .addComponent(btnCancelarAvaliacao)
                        .addGap(58, 58, 58))))
        );
        lblAvaliacaoLayout.setVerticalGroup(
            lblAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblAvaliacaoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(lblAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome5)
                    .addComponent(lblNome6)
                    .addComponent(txtNotaAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(txtFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lblAvaliacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelarAvaliacao))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAvaliacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBusca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeProfessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeProfessorActionPerformed

    private void inicial(){
        this.limpar();
        this.habilitarBtn(true, true, false, false, false, false, false, false);
        this.habilitarTxt(true, false, false, false, false, false);
    }
    
    private void habilitarBtn(boolean ok, boolean buscar, boolean avaliar, boolean cancelarPesquisa, 
            boolean exibirAvaliacao, boolean salvar, boolean excluir, boolean cancelarAvaliacao){
     
        this.btnOk.setEnabled(ok);
        this.btnBuscar.setEnabled(buscar);
        this.btnAvaliar.setEnabled(avaliar);
        this.btnCancelarPesquisa.setEnabled(cancelarPesquisa);
        this.btnExibir.setEnabled(exibirAvaliacao);
        this.btnSalvar.setEnabled(salvar);
        this.btnExcluir.setEnabled(excluir);
        this.btnCancelarAvaliacao.setEnabled(cancelarAvaliacao);       
    }
    
    private void habilitarTxt(boolean nomePesquisa, boolean nota, boolean nomeProfessor, boolean departamento, 
            boolean feedback, boolean notaAvaliacao){

        this.txtNomePesquisa.setEnabled(nomePesquisa);
        this.txtNota.setEnabled(nota);
        this.txtNomeProfessor.setEnabled(nomeProfessor);
        this.txtDepartamento.setEnabled(departamento);
        this.txtFeedback.setEnabled(feedback);
        this.txtNotaAvaliacao.setEnabled(notaAvaliacao);
  
    }
    
    private void limpar(){
        this.txtNomePesquisa.setText(" ");
        this.txtNota.setText(" ");
        this.txtNomeProfessor.setText(" ");
        this.txtDepartamento.setText(" ");
        this.txtFeedback.setText(" ");
        this.txtNotaAvaliacao.setText(" ");
    }
    
    private void txtDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartamentoActionPerformed

    private void lsMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsMateriasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lsMateriasMouseClicked

    private void btnAvaliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvaliarActionPerformed
        this.habilitarTxt(false, false, false, false, true, true);
        this.habilitarBtn(false, false, false, false, false, true, false, true);
    }//GEN-LAST:event_btnAvaliarActionPerformed

    private void btnCancelarPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarPesquisaActionPerformed
        this.inicial();
    }//GEN-LAST:event_btnCancelarPesquisaActionPerformed

    private void btnExibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExibirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExibirActionPerformed

    private void txtNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotaActionPerformed

    private void txtNomePesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomePesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomePesquisaActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        String nomeProfessor = this.txtNomePesquisa.getText();
        AvaliacaoDAO dao = new AvaliacaoDAO();
        Professor prof = dao.buscarProfessor(nomeProfessor);
        if(prof != null){
            //quando existir a media de nota da classe professor
            //this.txtNota.setText(prof.getNota());
            this.txtNomeProfessor.setText(prof.getNome());
            this.txtDepartamento.setText(prof.getDepartamento());
            this.habilitarBtn(true, false, true, true, true, false, false, false);
        }
        else{
            JOptionPane.showMessageDialog(null, "Professor(a) não encontrado(a)");
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOkActionPerformed

    private void txtFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFeedbackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFeedbackActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
        AvaliacaoDAO dao = new AvaliacaoDAO();
        Professor prof = dao.buscarProfessor(this.txtNomeProfessor.getText());
        System.out.println(prof.getNome() + " " + prof.getId());
        AlunoDAO daoAluno = new AlunoDAO();
        List<Aluno> alunos = new ArrayList<>();
        
        Aluno aluno = new Aluno();
        alunos = daoAluno.read();
        String feedback = this.txtFeedback.getText();
        int nota = Integer.parseInt(this.txtNotaAvaliacao.getText());
        
        for(Aluno alu: alunos){
            if (alu.getMatricula().equals(this.getMatricula())){
                aluno= alu;
            }
        }
        
        if(feedback.equals(" ") || this.txtNotaAvaliacao.getText().equals(" ") || aluno.getId() == 0){
            JOptionPane.showMessageDialog(null, "A nota e feedback devem ser preenchidos!");
        }
        else{
            dao.create(feedback, nota, aluno, prof);
            JOptionPane.showMessageDialog(null, "Avaliação criada com sucesso!");
        }
        this.inicial();
        
        
        
        
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAvaliacaoActionPerformed
        this.inicial();
    }//GEN-LAST:event_btnCancelarAvaliacaoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtNotaAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNotaAvaliacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNotaAvaliacaoActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public String getMatricula(){
        return this.matriculaAluno;
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAvaliacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                new TelaAvaliacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvaliar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelarAvaliacao;
    private javax.swing.JButton btnCancelarPesquisa;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExibir;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lblAvaliacao;
    private javax.swing.JPanel lblBusca;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblNome2;
    private javax.swing.JLabel lblNome3;
    private javax.swing.JLabel lblNome4;
    private javax.swing.JLabel lblNome5;
    private javax.swing.JLabel lblNome6;
    private javax.swing.JList<String> lsMaterias;
    private javax.swing.JTextField txtDepartamento;
    private javax.swing.JTextField txtFeedback;
    private javax.swing.JTextField txtNomePesquisa;
    private javax.swing.JTextField txtNomeProfessor;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtNotaAvaliacao;
    // End of variables declaration//GEN-END:variables
}
