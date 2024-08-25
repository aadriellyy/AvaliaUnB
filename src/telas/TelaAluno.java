/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import classes.Aluno;
import classes.Avaliacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.AlunoDAO;

/**
 *
 * @author limaa
 */
public class TelaAluno extends javax.swing.JFrame{

    private Aluno aluno;

    /**
     * Creates new form TelaAluno
     */
    //static ArrayList<Avaliacao> listaAvaliacao;
    
    public TelaAluno() {
        //int indiceProfessor;
        //boolean chamaProfessor = false;
        //inicializa os componentes da tela, limpa os campos e habilita ou desabilita campos de texto e botoes
        initComponents();
        this.limpar();
        this.habilitarBtn(true, true, false, true, false, false, false);
        this.habilitarTexto(false, false, false, false, false, false);
        //listaAvaliacao = new ArrayList();
        this.carregarTabelaAvaliacao();
       
        
    }
    
    public TelaAluno(Aluno aluno){
        //int indiceProfessor;
        //boolean chamaProfessor = false;
        //inicializa os componentes da tela, limpa os campos e habilita ou desabilita campos de texto e botoes
        initComponents();
        this.limpar();
        this.habilitarBtn(true, true, false, true, false, true, false);
        this.habilitarTexto(false, false, false, false, false, false);
        this.aluno = aluno;
        //listaAvaliacao = new ArrayList();
        this.carregarTabelaAvaliacao();
       
         
    }

    public Aluno getAluno() {
        return this.aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    private void carregarTabelaAvaliacao(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[] {"ID", "Professor (a)", "Feedback", "Nota"},0);
        AlunoDAO dao = new AlunoDAO();
        
        List <Avaliacao> listaAvaliacao = dao.agrupAvaliacao(this.aluno).getAvaliacoes();
                
        modelo.setNumRows(0);
        for(Avaliacao avalia: listaAvaliacao){
            modelo.addRow(new Object[]{
                avalia.getId(),
                avalia.getProfessor().getNome(),
                avalia.getFeedback(), 
                avalia.getLike()
            });
        }
        
        tblAvaliacoes.setModel(modelo);
        
        tblAvaliacoes.getColumnModel().getColumn(0).setMinWidth(0); // ID escondido
        tblAvaliacoes.getColumnModel().getColumn(0).setMaxWidth(0); // ID escondido
        tblAvaliacoes.getColumnModel().getColumn(0).setPreferredWidth(0); // ID escondido
        tblAvaliacoes.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblAvaliacoes.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblAvaliacoes.getColumnModel().getColumn(3).setPreferredWidth(10);   
    } 
    
    private void habilitarTexto(boolean nome, boolean departamento,boolean email,boolean matricula, boolean curso, boolean senha){ //metodo para habilitar campos de texto
        this.txtNome.setEnabled(nome);
        this.txtDepartament.setEnabled(departamento);
        this.txtEmail.setEnabled(email);
        this.txtMatricula.setEnabled(matricula);
        this.txtCurso.setEnabled(curso);
        this.txtSenha.setEnabled(senha);
    }
    
    private void habilitarBtn(boolean voltar, boolean novo, boolean cancelar, boolean pesquisar, boolean salvar, boolean excluir, boolean ok){ //metodo para habilitar botoes
        this.btnNovaAvaliacao.setEnabled(voltar);
        this.btnNovo.setEnabled(novo);
        //this.btnEditar.setEnabled(editar);
        this.btnCancelar.setEnabled(cancelar);
        this.btnPesquisar.setEnabled(pesquisar);
        this.btnSalvar.setEnabled(salvar);
        this.btnExcluir.setEnabled(excluir);
        this.btnOk.setEnabled(ok);
    }
    
    private void limpar(){ //metodo para limpar campos de texto
        this.txtNome.setText("");
        this.txtDepartament.setText("");
        this.txtEmail.setText("");
        this.txtMatricula.setText("");
        this.txtCurso.setText("");
        this.txtSenha.setText("");
    
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblCurso = new javax.swing.JLabel();
        txtCurso = new javax.swing.JTextField();
        lblMatricula = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnNovaAvaliacao = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        txtDepartamento = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        txtDepartament = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAvaliacoes = new javax.swing.JTable();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Perfil Aluno", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N
        jPanel1.setToolTipText("Perfil do Aluno");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/user-aluno.png"))); // NOI18N

        lblNome.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblNome.setText("Nome");

        lblCurso.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblCurso.setText("Curso");

        lblMatricula.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblMatricula.setText("Matrícula");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatriculaActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/user-cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/user-pesquisar.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/user-salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/user-excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnNovaAvaliacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/nova-avaliacao.png"))); // NOI18N
        btnNovaAvaliacao.setText("Avaliar");
        btnNovaAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaAvaliacaoActionPerformed(evt);
            }
        });

        btnOk.setBackground(new java.awt.Color(0, 204, 51));
        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        txtDepartamento.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        txtDepartamento.setText("Departamento");

        lblEmail.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblEmail.setText("Email");

        lblSenha.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblSenha.setText("Senha:");

        txtSenha.setText("jPasswordField2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(88, Short.MAX_VALUE)
                        .addComponent(btnNovaAvaliacao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNovo)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDepartament, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartamento)
                            .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCurso)
                            .addComponent(lblNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail)
                            .addComponent(lblSenha)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMatricula))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExcluir)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNome)
                            .addComponent(lblMatricula))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtDepartamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDepartament, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar)
                    .addComponent(btnSalvar)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelar)
                    .addComponent(btnNovo)
                    .addComponent(btnNovaAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Minhas Avaliações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 18))); // NOI18N

        tblAvaliacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Professor (a)", "Feedback", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAvaliacoes.setToolTipText("");
        tblAvaliacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAvaliacoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAvaliacoes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 19, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        //esse metodo realiza a busca do perfil do aluno pela matricula inserida. sao buscados os dados referidos a matricula no banco de dados, caso a matricula nao exista a consulta retorna uma mensagem de erro
        String matricula = txtMatricula.getText();  //pega a matricula digitada no campo de texto
        
        if(matricula.equals("")){ //caso tenham clicado no botao sem digitar nenhuma matricula
            JOptionPane.showMessageDialog(null, "A matrícula deve ser informada!", "erro", JOptionPane.INFORMATION_MESSAGE );
            return;
        }
        AlunoDAO dao = new AlunoDAO();
        boolean validar = false;
        for(Aluno aluno : dao.read()){
            if(aluno.getMatricula().equals(matricula)){
                validar = true;
                this.txtNome.setText(aluno.getNome());
                this.txtCurso.setText(aluno.getCurso());
                this.txtDepartament.setText(aluno.getDepartamento());
                this.txtEmail.setText(aluno.getEmail());
            }
        }
        if(!validar){
            JOptionPane.showMessageDialog(null, "A matrícula informada não existe no sitema!", "erro", JOptionPane.INFORMATION_MESSAGE);
        }
      
        this.txtMatricula.selectAll();
        this.txtMatricula.requestFocus();
        
        //carregarTabelaAvaliacao();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnNovaAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaAvaliacaoActionPerformed
        this.limpar();
        this.setVisible(false);
        new TelaAvaliacao(aluno.getMatricula()).setVisible(true);
        //carregarTabelaAvaliacao();
    }//GEN-LAST:event_btnNovaAvaliacaoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        
        if(tblAvaliacoes.getSelectedRow() != -1){
            
            Avaliacao avalia = new Avaliacao();
            AlunoDAO dao = new AlunoDAO();
            
            avalia.setId((int) tblAvaliacoes.getValueAt(tblAvaliacoes.getSelectedRow(), 0));
            
            dao.delete(avalia);
            
            // Remover a linha do modelo da tabela
            DefaultTableModel modelo = (DefaultTableModel) tblAvaliacoes.getModel();
            modelo.removeRow(tblAvaliacoes.getSelectedRow());
        }
        else{
            JOptionPane.showMessageDialog(null, "Selecione uma avaliação para excluir.");
        }
        
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        this.habilitarBtn(false, false, true, false, true, false, false);
        //pega os dados inseridos na caixa de texto
        String nome = txtNome.getText();
        String matricula = txtMatricula.getText();
        String curso = txtCurso.getText();
        String departamento = txtDepartament.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();
        
        //verifica se todos os campos estao preenchidos
        if(nome.equals("") || matricula.equals("") || curso.equals("") || departamento.equals("") || email.equals("") || senha.equals("")){
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser inseridos!", "erro", JOptionPane.INFORMATION_MESSAGE );
        }
        else{
            Aluno aluno = new Aluno(nome, departamento, email, matricula, curso, senha); //instancia um novo objeto aluno
            AlunoDAO dao = new AlunoDAO();
            dao.create(aluno);            
            this.limpar();
        }
        
        this.habilitarBtn(true, true, false, true, false, false, false);
        this.habilitarTexto(false, false, false, false, false, false);
        //carregarTabelaAvaliacao();
        
        
    }//GEN-LAST:event_btnSalvarActionPerformed
 
    
    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        //apenas habilita botoes e campos de texto para que a consulta seja realizada
        this.limpar();
        this.habilitarTexto(false, false, false, true, false, false);
        this.habilitarBtn(false, false, true, true, false, false, true);
        this.txtMatricula.requestFocus();   
        //carregarTabelaAvaliacao();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //cancela a acao atual e volta para estado inicial da pagina, com todos os campos limpos
        this.limpar();
        this.habilitarBtn(true, true, false, true, false, false, false);
        this.habilitarTexto(false, false, false, false, false, false);
        //carregarTabelaAvaliacao();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        //apenas habilita botoes e campos de texto para que os dados sejam inseridos
        this.limpar();
        this.habilitarTexto(true, true, true, true, true, true);
        this.habilitarBtn(false, false, true, false, true, false, false);
        this.txtNome.requestFocus();
        //carregarTabelaAvaliacao();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void txtMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatriculaActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void tblAvaliacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAvaliacoesMouseClicked
        int i = tblAvaliacoes.getSelectedRow();
        if(i>=0 && i<aluno.getAvaliacoes().size()){
            Avaliacao avalia = aluno.getAvaliacoes().get(i);
        }
        this.habilitarBtn(true, false, true, true, false, true, false);
        
    }//GEN-LAST:event_tblAvaliacoesMouseClicked

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
            java.util.logging.Logger.getLogger(TelaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAluno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAluno().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovaAvaliacao;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JTable tblAvaliacoes;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextField txtDepartament;
    private javax.swing.JLabel txtDepartamento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
