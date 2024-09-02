/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import classes.Aluno;
import classes.Disciplina;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.DisciplinaDAO;
import model.dao.GradeDAO;
import verificacao.MapeiaHorarios;

/**
 *
 * @author pedro
 */
public class telaGrade extends javax.swing.JFrame {

    /**
     * Creates new form telaGrade
     */
    ArrayList <String> disciplinasAdicionadasGrade = new ArrayList<>();
    ArrayList <ArrayList<String>> horarios = new ArrayList<>();
    int horasGrade;
    Aluno  alunoTela;
    ArrayList <String> listaHorariosDisciplinas = new ArrayList<>();
    GradeDAO carregaGrade = new GradeDAO();
    DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
    public telaGrade() {
        initComponents();
    }
    public telaGrade(Aluno aluno){
        alunoTela=aluno;
        listaHorariosDisciplinas = carregaGrade.acharHorariosAdicionados(aluno.getId());
        horarios= carregaGrade.achaGrade(aluno.getId());
        disciplinasAdicionadasGrade = carregaGrade.acharDisciplinasAdicionadas(aluno.getId());
        horasGrade = carregaGrade.readHoras(aluno.getId());
        initComponents();
        btnSalvarGrade.setEnabled(true);
        btnPerfilDisciplina.setEnabled(false);
        btnRemover.setEnabled(false);
        carregarTabelaGrade();
    }
            
    public void carregarTabelaGrade (){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"-","Segunda","Terça","Quarta","Quinta","Sexta", "Sábado"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (int i = 0; i <horarios.size(); i++){
            Object [] linha = new Object [] {horarios.get(i).get(0),
                                             horarios.get(i).get(1),
                                             horarios.get(i).get(2),
                                             horarios.get(i).get(3),
                                             horarios.get(i).get(4),
                                             horarios.get(i).get(5),
                                             horarios.get(i).get(6)};
            modelo.addRow(linha);
        }
        tblGrade.setModel(modelo);
    }
    
    public void inicializaHorario (){
        if (horarios.isEmpty()){
            for (int i=0; i <14; i++){
            ArrayList<String> adicionarHorario= new ArrayList ();
            adicionarHorario.add(MapeiaHorarios.mapearHorarios.get(i));
            adicionarHorario.add("");
            adicionarHorario.add("");
            adicionarHorario.add("");
            adicionarHorario.add("");
            adicionarHorario.add("");
            adicionarHorario.add("");
            horarios.add(adicionarHorario);}
        }

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
        jPanel2 = new javax.swing.JPanel();
        btnSalvarGrade = new javax.swing.JButton();
        btnPerfilDisciplina = new javax.swing.JButton();
        btnTelaInicial = new javax.swing.JButton();
        lblTitleDiscEscolhe = new javax.swing.JLabel();
        lblTitleTotalHoras = new javax.swing.JLabel();
        lblDisciplinaEscolhida = new javax.swing.JLabel();
        lblHoras = new javax.swing.JLabel();
        btnRemover = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrade = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minha grade");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone grade.png")).getImage());
        setResizable(false);

        btnSalvarGrade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone salvar grade.png"))); // NOI18N
        btnSalvarGrade.setText("Salvar grade");
        btnSalvarGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarGradeActionPerformed(evt);
            }
        });

        btnPerfilDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone salvar grade.png"))); // NOI18N
        btnPerfilDisciplina.setText("Perfil da disciplina");
        btnPerfilDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilDisciplinaActionPerformed(evt);
            }
        });

        btnTelaInicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/de-volta.png"))); // NOI18N
        btnTelaInicial.setText("Tela Inicial");
        btnTelaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTelaInicialActionPerformed(evt);
            }
        });

        lblTitleDiscEscolhe.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblTitleDiscEscolhe.setText("Disciplina escolhida:");

        lblTitleTotalHoras.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblTitleTotalHoras.setText("Total de horas:");

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone remover.png"))); // NOI18N
        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnRemover)
                .addGap(18, 18, 18)
                .addComponent(btnSalvarGrade)
                .addGap(18, 18, 18)
                .addComponent(btnPerfilDisciplina)
                .addGap(18, 18, 18)
                .addComponent(btnTelaInicial)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitleDiscEscolhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDisciplinaEscolhida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitleTotalHoras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblHoras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTitleDiscEscolhe)
                            .addComponent(lblTitleTotalHoras))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDisciplinaEscolhida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHoras, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPerfilDisciplina)
                            .addComponent(btnTelaInicial)
                            .addComponent(btnSalvarGrade)
                            .addComponent(btnRemover))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblGrade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGradeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGrade);

        jLabel3.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Minha grade");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(369, 369, 369)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTelaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTelaInicialActionPerformed
       this.setVisible(false);
        new TelaAluno(alunoTela).setVisible(true);           // TODO add your handling code here:
    }//GEN-LAST:event_btnTelaInicialActionPerformed

    private void btnSalvarGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarGradeActionPerformed
        GradeDAO salvarGrade = new GradeDAO();
        String atualizarGrade= String.join(",", listaHorariosDisciplinas);
        salvarGrade.update(atualizarGrade, this.horasGrade, alunoTela.getId());
        JOptionPane.showMessageDialog(null, "Grade salva com sucesso");         // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarGradeActionPerformed

    private void btnPerfilDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilDisciplinaActionPerformed
       int indice= tblGrade.getSelectedRow();
        int coluna = tblGrade.getSelectedColumn();
        if (coluna>0){
            String celula = (String) tblGrade.getValueAt(indice, coluna);
            if (!celula.equals("")){
                Disciplina disc = procuraDisciplina.achaDisciplina(celula); 
                new telaDisciplina(disc,alunoTela).setVisible(true);
                dispose();                          
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnPerfilDisciplinaActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
       int indice = tblGrade.getSelectedRow();
        if(indice>=0 && indice<horarios.size()){
            int coluna = tblGrade.getSelectedColumn();
            if (coluna>0 && coluna <tblGrade.getColumnCount()){
                String valor = (String) tblGrade.getValueAt(indice, coluna);  
                if (!valor.equals("") && null!=valor){
                    btnRemover.setEnabled(true);
                    btnPerfilDisciplina.setEnabled(true);
                }
                else {
                    btnRemover.setEnabled(false);
                    btnPerfilDisciplina.setEnabled(false);
                }
                lblDisciplinaEscolhida.setText(valor);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
       int indice= tblGrade.getSelectedRow();
        int coluna = tblGrade.getSelectedColumn();
        if (coluna>0){
            String celula = (String) tblGrade.getValueAt(indice, coluna);
            if (!celula.equals("")){
                for (ArrayList<String> linha : horarios){
                    for (String valor: linha){
                        if (valor.equals(celula)){
                            int index = linha.indexOf(valor);
                            linha.set(index,"");
                        }
                    }
                }
                String disciplinaRemover = null;
                for (String horario : listaHorariosDisciplinas){
                    if (horario.contains(celula)){
                        disciplinaRemover = horario;
                    }
                }
                listaHorariosDisciplinas.remove(disciplinaRemover);
                disciplinasAdicionadasGrade.remove(celula);
                horasGrade-= procuraDisciplina.achaDisciplina(celula).getHoras();
            }
            lblHoras.setText(String.valueOf(horasGrade));
            lblDisciplinaEscolhida.setText("");
            carregarTabelaGrade();
        }          // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void tblGradeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGradeMouseClicked
       int indice = tblGrade.getSelectedRow();
        if(indice>=0 && indice<horarios.size()){
            int coluna = tblGrade.getSelectedColumn();
            if (coluna>0 && coluna <tblGrade.getColumnCount()){
                String valor = (String) tblGrade.getValueAt(indice, coluna);  
                if (!valor.equals("") && null!=valor){
                    btnRemover.setEnabled(true);
                    btnPerfilDisciplina.setEnabled(true);
                }
                else {
                    btnRemover.setEnabled(false);
                    btnPerfilDisciplina.setEnabled(false);
                }
                lblDisciplinaEscolhida.setText(valor);
            }
        }         // TODO add your handling code here:
    }//GEN-LAST:event_tblGradeMouseClicked

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
            java.util.logging.Logger.getLogger(telaGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaGrade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaGrade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPerfilDisciplina;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvarGrade;
    private javax.swing.JButton btnTelaInicial;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDisciplinaEscolhida;
    private javax.swing.JLabel lblHoras;
    private javax.swing.JLabel lblTitleDiscEscolhe;
    private javax.swing.JLabel lblTitleTotalHoras;
    private javax.swing.JTable tblGrade;
    // End of variables declaration//GEN-END:variables
}
