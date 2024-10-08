package telas;

import classes.Aluno;
import classes.Avaliacao;
import classes.Comentario;
import classes.Professor;
import java.util.*;
import model.dao.ComentarioDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.AvaliacaoDAO;
import model.dao.ProfessorDAO;

/**
 *
 * @author lucas
 */

public class TelaComentario extends javax.swing.JFrame {

    String botao;
    ArrayList <Comentario> listaComentarios;
    Calendar c = Calendar.getInstance();
    int id=0;
    Avaliacao avalia;
    ComentarioDAO procuraComentario = new ComentarioDAO();
    Professor prof;
    Aluno alunoTela;
    
    /**
     * Creates new form Comentario
     */
    public TelaComentario(Avaliacao avaliacao, Professor professor, Aluno aluno) {
         int id = avaliacao.getId();
         this.prof = professor;
         avalia= avaliacao;
         alunoTela=aluno;
        //habilitar os botões
        initComponents();
        lblInfoProfessor.setText(prof.getNome());
        lblInfoDepartamento.setText(prof.getDepartamento());
        lblInfoEmail.setText(prof.getEmail());
        lblInfoNota.setText(String.valueOf(avalia.getNota()));
        txtFeedback.setText(avalia.getFeedback());
        txtFeedback.setEnabled(false);
        listaComentarios = procuraComentario.agruparComentario(avalia);
        btnNovoComentario.setEnabled(true);
        btnEditarComentario.setEnabled(false);
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
        AvaliacaoDAO procuraAvaliacao = new AvaliacaoDAO(); 
        ProfessorDAO procuraProfessor = new ProfessorDAO();
        prof = procuraProfessor.read().get(0);
        List<Avaliacao> listaAvaliacoes = procuraAvaliacao.read();
        this.id = listaAvaliacoes.get(0).getId();
        avalia=listaAvaliacoes.get(0);
        initComponents();
        lblInfoProfessor.setText(prof.getNome());
        lblInfoDepartamento.setText(prof.getDepartamento());
        lblInfoEmail.setText(prof.getEmail());
        lblInfoNota.setText(String.valueOf(avalia.getNota()));
        txtFeedback.setText(avalia.getFeedback());
        txtFeedback.setEnabled(false);
        listaComentarios = procuraComentario.agruparComentario(avalia);
        btnNovoComentario.setEnabled(true);
        btnEditarComentario.setEnabled(false);
        btnCancelarComentario.setEnabled(false);
        btnOkComentario.setEnabled(false);
        btnExcluirComentario.setEnabled(false);
        carregarTabelaComentarios();
        txtComentario.setEnabled(false);
    }

    public void carregarTabelaComentarios (){
        ComentarioDAO procuraComentario = new ComentarioDAO();
           DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Comentário","Data"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; 
        for (Comentario comentario : procuraComentario.agruparComentario(avalia)){
            Object linha [] = new Object[] {comentario.getTexto(),
                                            comentario.getDateCreated()                                            
                                            };
            modelo.addRow(linha);        
        } 
        tblComentarios.setModel(modelo);
    }
        
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlInformacoesProfessor = new javax.swing.JPanel();
        lblDepartamentoComentario = new javax.swing.JLabel();
        lblInfoDepartamento = new javax.swing.JLabel();
        lblProfessor = new javax.swing.JLabel();
        lblInfoProfessor = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblInfoEmail = new javax.swing.JLabel();
        scrComentarios = new javax.swing.JScrollPane();
        tblComentarios = new javax.swing.JTable();
        pnlAvaliaçãoDisciplina = new javax.swing.JPanel();
        lblNota = new javax.swing.JLabel();
        lblFeedback = new javax.swing.JLabel();
        lblInfoNota = new javax.swing.JLabel();
        txtFeedback = new javax.swing.JTextField();
        sprSeparadorComentario = new javax.swing.JSeparator();
        pnlComentario = new javax.swing.JPanel();
        btnOkComentario = new javax.swing.JButton();
        txtComentario = new javax.swing.JTextField();
        btnNovoComentario = new javax.swing.JButton();
        btnEditarComentario = new javax.swing.JButton();
        btnCancelarComentario = new javax.swing.JButton();
        btnExcluirComentario = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comentários");
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
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addComponent(lblDepartamentoComentario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInfoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInfoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addComponent(lblProfessor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInfoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInformacoesProfessorLayout.setVerticalGroup(
            pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblProfessor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoProfessor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDepartamentoComentario)
                    .addComponent(lblInfoDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 21, Short.MAX_VALUE)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        scrComentarios.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Comentários", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        tblComentarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Texto", "Data"
            }
        ));
        tblComentarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComentariosMouseClicked(evt);
            }
        });
        scrComentarios.setViewportView(tblComentarios);

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
                .addComponent(lblInfoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(lblFeedback)
                .addGap(18, 18, 18)
                .addComponent(txtFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAvaliaçãoDisciplinaLayout.setVerticalGroup(
            pnlAvaliaçãoDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAvaliaçãoDisciplinaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlAvaliaçãoDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAvaliaçãoDisciplinaLayout.createSequentialGroup()
                        .addGroup(pnlAvaliaçãoDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInfoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNota)
                            .addComponent(lblFeedback))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlAvaliaçãoDisciplinaLayout.createSequentialGroup()
                        .addComponent(txtFeedback, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pnlComentario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faça seu comentário aqui!", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        btnOkComentario.setText("OK");
        btnOkComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkComentarioActionPerformed(evt);
            }
        });

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
                .addGap(163, 163, 163)
                .addComponent(btnOkComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );
        pnlComentarioLayout.setVerticalGroup(
            pnlComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComentarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtComentario, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(btnOkComentario)
                .addContainerGap())
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
        btnExcluirComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirComentarioActionPerformed(evt);
            }
        });

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/de-volta.png"))); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(scrComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExcluirComentario)
                                .addGap(192, 192, 192)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoComentario)
                    .addComponent(btnEditarComentario)
                    .addComponent(btnCancelarComentario)
                    .addComponent(btnExcluirComentario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pnlComentario.getAccessibleContext().setAccessibleName("Faça seu comentário aqui!");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComentarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtComentarioActionPerformed

    private void btnNovoComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoComentarioActionPerformed
        // TODO add your handling code here:
        botao = "novo";
        
        btnNovoComentario.setEnabled(false);
        btnEditarComentario.setEnabled(false);
        btnCancelarComentario.setEnabled(true);
        btnOkComentario.setEnabled(true);
        btnExcluirComentario.setEnabled(false);
        tblComentarios.setEnabled(false);
        
       txtComentario.setEnabled(true);
    }//GEN-LAST:event_btnNovoComentarioActionPerformed

    private void btnEditarComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarComentarioActionPerformed
        // TODO add your handling code here:
        botao = "editar";
        btnNovoComentario.setEnabled(false);
        btnEditarComentario.setEnabled(false);
        btnCancelarComentario.setEnabled(true);
        btnOkComentario.setEnabled(true);
        btnExcluirComentario.setEnabled(false);
        tblComentarios.setEnabled(false);
        txtComentario.setEnabled(true);
    }//GEN-LAST:event_btnEditarComentarioActionPerformed

    private void btnCancelarComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarComentarioActionPerformed
        // TODO add your handling code here:
        txtComentario.setText("");
        btnNovoComentario.setEnabled(true);
        btnEditarComentario.setEnabled(false);
        btnCancelarComentario.setEnabled(false);
        btnOkComentario.setEnabled(false);
        btnExcluirComentario.setEnabled(false);
        tblComentarios.setEnabled(true);
        txtComentario.setEnabled(false);
    }//GEN-LAST:event_btnCancelarComentarioActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.setVisible(false);
        new TelaAluno(alunoTela).setVisible(true);        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void btnOkComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkComentarioActionPerformed
        // TODO add your handling code here:
        if (txtComentario.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Digite algo para comentar!");
        }
        else{
            if(botao.equals("novo")){
               ComentarioDAO a = new ComentarioDAO();
               Comentario comentario = new Comentario(txtComentario.getText());
               a.comentar(comentario, avalia);
               btnNovoComentario.setEnabled(true);
               btnEditarComentario.setEnabled(false);
               btnCancelarComentario.setEnabled(false);
               tblComentarios.setEnabled(true);
               txtComentario.setText("");
               carregarTabelaComentarios();
            }
            else{
                Comentario editComentario = listaComentarios.get(tblComentarios.getSelectedRow());
                editComentario.setTexto(txtComentario.getText());
                procuraComentario.updateComentario(editComentario);
                btnNovoComentario.setEnabled(true);
                btnEditarComentario.setEnabled(false);
                btnCancelarComentario.setEnabled(false);
                tblComentarios.setEnabled(true);
                txtComentario.setText("");
                carregarTabelaComentarios();
            }
        }
    }//GEN-LAST:event_btnOkComentarioActionPerformed

    private void tblComentariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComentariosMouseClicked
        int indice = tblComentarios.getSelectedRow();
        if (indice>=0 && indice <tblComentarios.getRowCount()){
            this.btnExcluirComentario.setEnabled(true);
            this.btnEditarComentario.setEnabled(true);
        }
            // TODO add your handling code here:
    }//GEN-LAST:event_tblComentariosMouseClicked

    private void btnExcluirComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirComentarioActionPerformed
        int indice = tblComentarios.getSelectedRow();
        ComentarioDAO procuraComentario = new ComentarioDAO();
        ArrayList<Comentario> listaLocalComentarios = procuraComentario.agruparComentario(avalia);
        Comentario comentarioExcluir = listaLocalComentarios.get(indice);
        procuraComentario.excluirComentario(comentarioExcluir);
        carregarTabelaComentarios();// TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirComentarioActionPerformed

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
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDepartamentoComentario;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFeedback;
    private javax.swing.JLabel lblInfoDepartamento;
    private javax.swing.JLabel lblInfoEmail;
    private javax.swing.JLabel lblInfoNota;
    private javax.swing.JLabel lblInfoProfessor;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblProfessor;
    private javax.swing.JPanel pnlAvaliaçãoDisciplina;
    private javax.swing.JPanel pnlComentario;
    private javax.swing.JPanel pnlInformacoesProfessor;
    private javax.swing.JScrollPane scrComentarios;
    private javax.swing.JSeparator sprSeparadorComentario;
    private javax.swing.JTable tblComentarios;
    private javax.swing.JTextField txtComentario;
    private javax.swing.JTextField txtFeedback;
    // End of variables declaration//GEN-END:variables
}
