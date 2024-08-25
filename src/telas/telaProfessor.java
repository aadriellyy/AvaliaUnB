/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;


import classes.Disciplina;
import java.sql.*;
import classes.Professor;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import model.dao.DisciplinaDAO;
import model.dao.ProfessorDAO;

/**
 *
 * @author pedro
 */
public class telaProfessor extends javax.swing.JFrame {
Disciplina disciplinaPesquisada = null;
Professor professorTela = null;
boolean pesquisaProfessor = false ;
boolean pesquisaDisciplina = false;
    /**
     * Creates new form telaProfessor
     */
    public telaProfessor() {
        initComponents();
        Font font = lblEmail.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        lblEmail.setFont(font.deriveFont(attributes));
        StringSelection stringSelection = new StringSelection(lblEmail.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null); 
        btnIrAvaliar.setEnabled (false);
        btnPesquisar.setEnabled(true);
        btnVoltar.setEnabled(true);
        cmbPesquisa.setEnabled(false);
        
            
        /*Professor professorLocal = Professor.resgataProfessorBanco();
        lblNome.setText(professorLocal.getNome());*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpPesquisa = new javax.swing.ButtonGroup();
        pnlFoto = new javax.swing.JPanel();
        pnlInformacoesProfessor = new javax.swing.JPanel();
        lblTituloDepartamento = new javax.swing.JLabel();
        lblTitleNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTitleNota = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblTituloDepartamento1 = new javax.swing.JLabel();
        lblNota = new javax.swing.JLabel();
        lblTitleNumAvaliacoes = new javax.swing.JLabel();
        lblNumAvaliacoes = new javax.swing.JLabel();
        lblTitleMedia = new javax.swing.JLabel();
        lblMedia = new javax.swing.JLabel();
        pblAvaliacoes = new javax.swing.JPanel();
        lblTitleAvaliacoes = new javax.swing.JLabel();
        scrlpnlavaliações = new javax.swing.JScrollPane();
        tblAvaliacoes = new javax.swing.JTable();
        pnlBtnsAvaliacoes = new javax.swing.JPanel();
        btnVoltar = new javax.swing.JButton();
        btnPerfilDisciplina = new javax.swing.JButton();
        btnIrAvaliar = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        pnlPesquisa = new javax.swing.JPanel();
        lblImagemPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        rdbPesquisaProfessor = new javax.swing.JRadioButton();
        rdbDisciplina = new javax.swing.JRadioButton();
        cmbPesquisa = new javax.swing.JComboBox<>();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perfil do professor");

        lblTituloDepartamento.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTituloDepartamento.setText("Departamento");

        lblTitleNome.setBackground(new java.awt.Color(0, 255, 0));
        lblTitleNome.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNome.setText("Nome");

        lblEmail.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(51, 102, 255));
        lblEmail.setText("pedrolucaspn13@gmail.com");
        lblEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblTitleNota.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNota.setText("Nota");

        lblDepartamento.setBackground(new java.awt.Color(0, 255, 0));
        lblDepartamento.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        lblDepartamento.setText("Ciência da Computação");

        lblNome.setBackground(new java.awt.Color(0, 255, 0));
        lblNome.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        lblNome.setText("Pedro Lucas Pereira Neris");

        lblTituloDepartamento1.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTituloDepartamento1.setText("Email");

        lblNota.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        lblNota.setText("☆☆☆☆☆");

        lblTitleNumAvaliacoes.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNumAvaliacoes.setText("Nº de avaliações");

        lblNumAvaliacoes.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        lblNumAvaliacoes.setText("0");

        lblTitleMedia.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleMedia.setText("Média de avaliações");

        lblMedia.setText("0");

        javax.swing.GroupLayout pnlInformacoesProfessorLayout = new javax.swing.GroupLayout(pnlInformacoesProfessor);
        pnlInformacoesProfessor.setLayout(pnlInformacoesProfessorLayout);
        pnlInformacoesProfessorLayout.setHorizontalGroup(
            pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTituloDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNome))
                                .addGap(0, 36, Short.MAX_VALUE))
                            .addComponent(lblTitleNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNota)
                                    .addComponent(lblTitleNumAvaliacoes)))
                            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTitleNota)))
                        .addGap(33, 33, 33))
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addComponent(lblTituloDepartamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTitleMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addComponent(lblDepartamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNumAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))))
        );
        pnlInformacoesProfessorLayout.setVerticalGroup(
            pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacoesProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNota))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloDepartamento)
                    .addComponent(lblTitleNumAvaliacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumAvaliacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloDepartamento1)
                    .addComponent(lblTitleMedia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(lblMedia))
                .addGap(48, 48, 48))
        );

        lblTitleAvaliacoes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitleAvaliacoes.setText("Avaliações");

        tblAvaliacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Disciplina", "Horário", "Nota ", "Feedback", "Likes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAvaliacoes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scrlpnlavaliações.setViewportView(tblAvaliacoes);

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/de-volta.png"))); // NOI18N
        btnVoltar.setText("Voltar");

        btnPerfilDisciplina.setText("Perfil da disciplina");

        javax.swing.GroupLayout pnlBtnsAvaliacoesLayout = new javax.swing.GroupLayout(pnlBtnsAvaliacoes);
        pnlBtnsAvaliacoes.setLayout(pnlBtnsAvaliacoesLayout);
        pnlBtnsAvaliacoesLayout.setHorizontalGroup(
            pnlBtnsAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnsAvaliacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPerfilDisciplina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoltar)
                .addContainerGap(109, Short.MAX_VALUE))
        );
        pnlBtnsAvaliacoesLayout.setVerticalGroup(
            pnlBtnsAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnsAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnVoltar)
                .addComponent(btnPerfilDisciplina))
        );

        btnIrAvaliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/nova-avaliacao.png"))); // NOI18N
        btnIrAvaliar.setText("Avaliar");

        javax.swing.GroupLayout pblAvaliacoesLayout = new javax.swing.GroupLayout(pblAvaliacoes);
        pblAvaliacoes.setLayout(pblAvaliacoesLayout);
        pblAvaliacoesLayout.setHorizontalGroup(
            pblAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblAvaliacoesLayout.createSequentialGroup()
                .addGroup(pblAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pblAvaliacoesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrlpnlavaliações))
                    .addGroup(pblAvaliacoesLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnIrAvaliar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBtnsAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pblAvaliacoesLayout.createSequentialGroup()
                .addGap(273, 273, 273)
                .addComponent(lblTitleAvaliacoes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pblAvaliacoesLayout.setVerticalGroup(
            pblAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblAvaliacoesLayout.createSequentialGroup()
                .addComponent(lblTitleAvaliacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrlpnlavaliações, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(pblAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIrAvaliar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlBtnsAvaliacoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pessoa.png"))); // NOI18N

        javax.swing.GroupLayout pnlFotoLayout = new javax.swing.GroupLayout(pnlFoto);
        pnlFoto.setLayout(pnlFotoLayout);
        pnlFotoLayout.setHorizontalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotoLayout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFotoLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblFoto)
                        .addGap(46, 46, 46)
                        .addComponent(pnlInformacoesProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pblAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlFotoLayout.setVerticalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInformacoesProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(pblAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        lblImagemPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/pesquisa.png"))); // NOI18N
        lblImagemPesquisa.setText("jLabel3");

        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });

        grpPesquisa.add(rdbPesquisaProfessor);
        rdbPesquisaProfessor.setText("Professor");
        rdbPesquisaProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbPesquisaProfessorActionPerformed(evt);
            }
        });

        grpPesquisa.add(rdbDisciplina);
        rdbDisciplina.setText("Disciplina");
        rdbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDisciplinaActionPerformed(evt);
            }
        });

        cmbPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome" }));

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPesquisaLayout = new javax.swing.GroupLayout(pnlPesquisa);
        pnlPesquisa.setLayout(pnlPesquisaLayout);
        pnlPesquisaLayout.setHorizontalGroup(
            pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlPesquisaLayout.createSequentialGroup()
                        .addComponent(lblImagemPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdbPesquisaProfessor)
                        .addGap(18, 18, 18)
                        .addComponent(rdbDisciplina))
                    .addComponent(cmbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnPesquisar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPesquisaLayout.setVerticalGroup(
            pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPesquisaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblImagemPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbPesquisaProfessor)
                    .addComponent(rdbDisciplina)
                    .addComponent(btnPesquisar))
                .addGap(0, 0, 0)
                .addComponent(cmbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbPesquisaProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbPesquisaProfessorActionPerformed
        cmbPesquisa.setEnabled(false);
        pesquisaProfessor = true;   
        pesquisaDisciplina = false;// TODO add your handling code here:
    }//GEN-LAST:event_rdbPesquisaProfessorActionPerformed

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        if (txtPesquisa.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nada foi digitado para a pesquisa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (pesquisaProfessor){
                ProfessorDAO procuraProfessor = new ProfessorDAO();
                ArrayList<Professor> listaProfessores = (ArrayList<Professor>) procuraProfessor.read();
                String nomeProfessor = txtPesquisa.getText();
                for (Professor professor : listaProfessores){
                    if (professor.getNome().equals(nomeProfessor)){
                        professorTela = professor;
                        break;
                    }
                }
            }
            else if (pesquisaDisciplina){
            DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
            ArrayList <Disciplina> listaDisciplinas = (ArrayList<Disciplina>) procuraDisciplina.read();
            if (cmbPesquisa.getSelectedIndex() ==0){
                String codigoDisciplina = txtPesquisa.getText();
                for (Disciplina disciplina : listaDisciplinas){
                    if (disciplina.getCodigo().equals(codigoDisciplina)){
                        disciplinaPesquisada = disciplina;
                        break;
                    }
                }
            }
        }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void rdbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDisciplinaActionPerformed
        cmbPesquisa.setEnabled(true);
        pesquisaDisciplina= true;
        pesquisaProfessor = false;// TODO add your handling code here:
    }//GEN-LAST:event_rdbDisciplinaActionPerformed

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
            java.util.logging.Logger.getLogger(telaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaProfessor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaProfessor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIrAvaliar;
    private javax.swing.JButton btnPerfilDisciplina;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cmbPesquisa;
    private javax.swing.ButtonGroup grpPesquisa;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblImagemPesquisa;
    private javax.swing.JLabel lblMedia;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblNumAvaliacoes;
    private javax.swing.JLabel lblTitleAvaliacoes;
    private javax.swing.JLabel lblTitleMedia;
    private javax.swing.JLabel lblTitleNome;
    private javax.swing.JLabel lblTitleNota;
    private javax.swing.JLabel lblTitleNumAvaliacoes;
    private javax.swing.JLabel lblTituloDepartamento;
    private javax.swing.JLabel lblTituloDepartamento1;
    private javax.swing.JPanel pblAvaliacoes;
    private javax.swing.JPanel pnlBtnsAvaliacoes;
    private javax.swing.JPanel pnlFoto;
    private javax.swing.JPanel pnlInformacoesProfessor;
    private javax.swing.JPanel pnlPesquisa;
    private javax.swing.JRadioButton rdbDisciplina;
    private javax.swing.JRadioButton rdbPesquisaProfessor;
    private javax.swing.JScrollPane scrlpnlavaliações;
    private javax.swing.JTable tblAvaliacoes;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
