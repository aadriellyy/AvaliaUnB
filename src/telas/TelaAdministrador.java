/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;
import Connection.ConnectionFactory;
import classes.Aluno;
import classes.Avaliacao;
import classes.Disciplina;
import classes.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.dao.AdmDAO;
import model.dao.AvaliacaoDAO;
import model.dao.DisciplinaDAO;
import model.dao.ProfessorDAO;
import verificacao.Horario;
import verificacao.VerificaCodigo;
import verificacao.VerificaEmail;
import verificacao.VerificaNome;
import verificacao.VerificaNumero;
/**
 *
 * @author pedro
 */

public class TelaAdministrador extends javax.swing.JFrame {
    Professor professorTela;
    Disciplina disciplinaTela;
    ProfessorDAO procuraProfessor = new ProfessorDAO();
    DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
    AdmDAO adm = new AdmDAO();
    boolean novoProfessor= false;
    boolean editarProfessor = false;
    boolean novaDisciplina = false;
    /**
     * Creates new form TelaAdministrador
     */
    public TelaAdministrador() {
        initComponents();
        txtNomeProfessor.setEnabled(false);
        txtEmail.setEnabled(false);
        txtDepartamentoProfessor.setEnabled(false);
        txtDisciplinaHorario.setEnabled(false);
        btnCancelarProfessor.setEnabled(false);
        btnEditarHorarioProfessor.setEnabled(false);
        btnExcluirProfessor.setEnabled(false);
        btnNovoProfessor.setEnabled(true);
        btnOkEditarHorario.setEnabled(false);
        btnOkProfessor.setEnabled(false);
        btnPesquisarProfessor.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnExcluirDisciplinaProfessor.setEnabled(false);
        listaDisciplinasHorarios.setEnabled(false);
        txtEditarHorario.setEnabled(false);
        btnEditarProfessor.setEnabled(false);
        limparDisciplina();
    }
    
    public void limparProfessor (){
        professorTela = null;
        txtNomeProfessor.setText("");
        txtEmail.setText("");
        txtDepartamentoProfessor.setText("");
        DefaultListModel modelo = new DefaultListModel();
        listaDisciplinasHorarios.setModel(modelo);
        listaDisciplinasHorarios.setEnabled(false);
        modelo.removeAllElements();
        txtDisciplinaHorario.setText("");
        txtNomeProfessor.setEnabled(false);
        txtEmail.setEnabled(false);
        txtDepartamentoProfessor.setEnabled(false); 
        txtDisciplinaHorario.setEnabled(false);
        btnCancelarProfessor.setEnabled(false);
        btnEditarHorarioProfessor.setEnabled(false);
        btnExcluirProfessor.setEnabled(false);
        btnNovoProfessor.setEnabled(true);
        btnOkEditarHorario.setEnabled(false);
        btnOkProfessor.setEnabled(false);
        btnPesquisarProfessor.setEnabled(true);
        btnSalvar.setEnabled(false);
        btnExcluirDisciplinaProfessor.setEnabled(false);
        listaDisciplinasHorarios.setEnabled(false);
        txtEditarHorario.setEnabled(false);
        btnEditarProfessor.setEnabled(false);
    }
    
    public void limparDisciplina(){
        disciplinaTela= null; 
        txtNomeDisciplina.setEnabled(false);
        txtNomeDisciplina.setText("");
        DefaultListModel limpaLista = new DefaultListModel();
        listaProfessores.setModel(limpaLista);
        listaProfessores.setEnabled(false);
        txtCargaHoraria.setEnabled(false);
        txtCargaHoraria.setText("");
        txtDepartamentoDisciplina.setEnabled(false);
        txtDepartamentoDisciplina.setText("");
        txtCodigoDisciplina.setEnabled(false);
        txtCodigoDisciplina.setText("");
        txtAdicionarProfessores.setEnabled(false);
        txtAdicionarProfessores.setText("");
        btnNovaDisciplina.setEnabled(true);
        btnPesquisarDisciplina.setEnabled(true);
        btnCancelarDisciplina.setEnabled(false);
        btnSalvarDisciplina.setEnabled(false);
        btnExcluirDisciplina.setEnabled(false);
        btnRemoveProfessor.setEnabled(false);
        btnOkPesquisaDisciplina.setEnabled(false);
    }
    
    public void carregarListaDisciplinas () {
        DefaultListModel data = new DefaultListModel();
        for (Disciplina disciplina : professorTela.getDisciplinas()){
            data.addElement(disciplina.getCodigo()+ ":"+professorTela.getHorario(disciplina));
        }
        listaDisciplinasHorarios.setModel(data);
    }
    
    public void carregarListaProfessores (){
        DefaultListModel modelo = new DefaultListModel();
        for (Professor professor : disciplinaTela.getListaProfessores()){
            if (professor!=null){
                modelo.addElement(professor.getNome());
            }
        }
        listaProfessores.setModel(modelo);
    }
    
    public void carregarInformacoesProfessor(){
        procuraProfessor.criaListaDisciplinas(professorTela);
        for (Disciplina disciplina : professorTela.getDisciplinas()){
            procuraProfessor.addHorario(disciplina, professorTela);
        }
        procuraProfessor.criaListaAvaliacoes(professorTela);
        txtNomeProfessor.setText(professorTela.getNome());
        txtEmail.setText(professorTela.getEmail());
        txtDepartamentoProfessor.setText(professorTela.getDepartamento());
        carregarListaDisciplinas();     
        
    }
    
    public void carregarInformacoesDisciplina(){
        procuraDisciplina.criaListaDisciplina(disciplinaTela);
        txtNomeDisciplina.setText(disciplinaTela.getNome());
        txtCodigoDisciplina.setText(disciplinaTela.getCodigo());
        txtDepartamentoDisciplina.setText(disciplinaTela.getDepartamento());
        txtCargaHoraria.setText(String.valueOf(disciplinaTela.getHoras()));
        carregarListaProfessores();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblNomeDisciplina = new javax.swing.JLabel();
        txtNomeDisciplina = new javax.swing.JTextField();
        lblDepartamentoDisciplina = new javax.swing.JLabel();
        txtDepartamentoDisciplina = new javax.swing.JTextField();
        lblCargaHoraria = new javax.swing.JLabel();
        txtCargaHoraria = new javax.swing.JTextField();
        lblCodigoDisciplina = new javax.swing.JLabel();
        txtCodigoDisciplina = new javax.swing.JTextField();
        lblListaProfessores = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaProfessores = new javax.swing.JList<>();
        lblAdicionarListaProfessor = new javax.swing.JLabel();
        txtAdicionarProfessores = new javax.swing.JTextField();
        btnRemoveProfessor = new javax.swing.JButton();
        lblDisciplinas = new javax.swing.JLabel();
        btnNovaDisciplina = new javax.swing.JButton();
        btnSalvarDisciplina = new javax.swing.JButton();
        btnPesquisarDisciplina = new javax.swing.JButton();
        btnExcluirDisciplina = new javax.swing.JButton();
        btnCancelarDisciplina = new javax.swing.JButton();
        btnOkPesquisaDisciplina = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblProfessores = new javax.swing.JLabel();
        lblNomeProfessor = new javax.swing.JLabel();
        txtNomeProfessor = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnOkProfessor = new javax.swing.JButton();
        lblDepartamento = new javax.swing.JLabel();
        txtDepartamentoProfessor = new javax.swing.JTextField();
        lblDisciplinasHorarios = new javax.swing.JLabel();
        scrlpnlListaDisciplinasHorarios = new javax.swing.JScrollPane();
        listaDisciplinasHorarios = new javax.swing.JList<>();
        btnExcluirDisciplinaProfessor = new javax.swing.JButton();
        btnEditarHorarioProfessor = new javax.swing.JButton();
        txtEditarHorario = new javax.swing.JTextField();
        btnOkEditarHorario = new javax.swing.JButton();
        lblDisciplinaHorario = new javax.swing.JLabel();
        txtDisciplinaHorario = new javax.swing.JTextField();
        pnlBtnsProfessor = new javax.swing.JPanel();
        btnPesquisarProfessor = new javax.swing.JButton();
        btnExcluirProfessor = new javax.swing.JButton();
        btnCancelarProfessor = new javax.swing.JButton();
        btnNovoProfessor = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditarProfessor = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrador");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone adm.png")).getImage());
        setPreferredSize(new java.awt.Dimension(780, 800));
        setResizable(false);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(700, 952));

        jPanel1.setPreferredSize(new java.awt.Dimension(603, 950));

        lblNomeDisciplina.setText("Nome:");

        lblDepartamentoDisciplina.setText("Departamento:");

        lblCargaHoraria.setText("Carga Horária:");

        txtCargaHoraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargaHorariaActionPerformed(evt);
            }
        });

        lblCodigoDisciplina.setText("Código:");

        lblListaProfessores.setText("Lista de professores:");

        listaProfessores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaProfessoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaProfessores);

        lblAdicionarListaProfessor.setText("Adicionar professores/horarios:");

        btnRemoveProfessor.setText("Remover professor");
        btnRemoveProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveProfessorActionPerformed(evt);
            }
        });

        lblDisciplinas.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        lblDisciplinas.setText("Disciplinas");

        btnNovaDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone adicionar.png"))); // NOI18N
        btnNovaDisciplina.setText("Novo");
        btnNovaDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaDisciplinaActionPerformed(evt);
            }
        });

        btnSalvarDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/check icon.png"))); // NOI18N
        btnSalvarDisciplina.setText("Salvar");
        btnSalvarDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarDisciplinaActionPerformed(evt);
            }
        });

        btnPesquisarDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone pesquisa.png"))); // NOI18N
        btnPesquisarDisciplina.setText("Pesquisar");
        btnPesquisarDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarDisciplinaActionPerformed(evt);
            }
        });

        btnExcluirDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete .png"))); // NOI18N
        btnExcluirDisciplina.setText("Excluir");
        btnExcluirDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirDisciplinaActionPerformed(evt);
            }
        });

        btnCancelarDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/circulo-cruzado.png"))); // NOI18N
        btnCancelarDisciplina.setText("Cancelar");
        btnCancelarDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarDisciplinaActionPerformed(evt);
            }
        });

        btnOkPesquisaDisciplina.setText("OK");
        btnOkPesquisaDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkPesquisaDisciplinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblCodigoDisciplina)
                        .addGap(18, 18, 18)
                        .addComponent(txtCodigoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOkPesquisaDisciplina))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblDepartamentoDisciplina))
                            .addComponent(lblCargaHoraria, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartamentoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(txtNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnNovaDisciplina)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarDisciplina)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisarDisciplina)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirDisciplina)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarDisciplina))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(lblAdicionarListaProfessor)
                            .addGap(18, 18, 18)
                            .addComponent(txtAdicionarProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(lblListaProfessores)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnRemoveProfessor)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(372, 372, 372)
                        .addComponent(lblDisciplinas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDisciplinas)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNomeDisciplina))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoDisciplina)
                    .addComponent(txtCodigoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOkPesquisaDisciplina))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamentoDisciplina)
                    .addComponent(txtDepartamentoDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCargaHoraria)
                    .addComponent(txtCargaHoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnRemoveProfessor))
                            .addComponent(lblListaProfessores))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAdicionarListaProfessor)
                            .addComponent(txtAdicionarProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisarDisciplina)
                    .addComponent(btnExcluirDisciplina)
                    .addComponent(btnCancelarDisciplina)
                    .addComponent(btnNovaDisciplina)
                    .addComponent(btnSalvarDisciplina))
                .addContainerGap())
        );

        lblProfessores.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        lblProfessores.setText("Professores");

        lblNomeProfessor.setText("Nome:");

        lblEmail.setText("E-mail:");

        btnOkProfessor.setText("OK");
        btnOkProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkProfessorActionPerformed(evt);
            }
        });

        lblDepartamento.setText("Departamento:");

        lblDisciplinasHorarios.setText("Lista de disciplinas/horários:");

        listaDisciplinasHorarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaDisciplinasHorariosMouseClicked(evt);
            }
        });
        scrlpnlListaDisciplinasHorarios.setViewportView(listaDisciplinasHorarios);

        btnExcluirDisciplinaProfessor.setText("Remover disciplina");
        btnExcluirDisciplinaProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirDisciplinaProfessorActionPerformed(evt);
            }
        });

        btnEditarHorarioProfessor.setText("Editar horário");
        btnEditarHorarioProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarHorarioProfessorActionPerformed(evt);
            }
        });

        txtEditarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEditarHorarioActionPerformed(evt);
            }
        });

        btnOkEditarHorario.setText("OK");
        btnOkEditarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkEditarHorarioActionPerformed(evt);
            }
        });

        lblDisciplinaHorario.setText("Definir disciplinas/horários:");

        txtDisciplinaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDisciplinaHorarioActionPerformed(evt);
            }
        });

        btnPesquisarProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icone pesquisa.png"))); // NOI18N
        btnPesquisarProfessor.setText("Pesquisar");
        btnPesquisarProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarProfessorActionPerformed(evt);
            }
        });

        btnExcluirProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete .png"))); // NOI18N
        btnExcluirProfessor.setText("Excluir");
        btnExcluirProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProfessorActionPerformed(evt);
            }
        });

        btnCancelarProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/circulo-cruzado.png"))); // NOI18N
        btnCancelarProfessor.setText("Cancelar");
        btnCancelarProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarProfessorActionPerformed(evt);
            }
        });

        btnNovoProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone adicionar.png"))); // NOI18N
        btnNovoProfessor.setText("Novo");
        btnNovoProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoProfessorActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/check icon.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnEditarProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/edit icon png.png"))); // NOI18N
        btnEditarProfessor.setText("Editar");
        btnEditarProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarProfessorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBtnsProfessorLayout = new javax.swing.GroupLayout(pnlBtnsProfessor);
        pnlBtnsProfessor.setLayout(pnlBtnsProfessorLayout);
        pnlBtnsProfessorLayout.setHorizontalGroup(
            pnlBtnsProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBtnsProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovoProfessor)
                .addGap(18, 18, 18)
                .addComponent(btnEditarProfessor)
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addGap(18, 18, 18)
                .addComponent(btnPesquisarProfessor)
                .addGap(18, 18, 18)
                .addComponent(btnExcluirProfessor)
                .addGap(18, 18, 18)
                .addComponent(btnCancelarProfessor)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        pnlBtnsProfessorLayout.setVerticalGroup(
            pnlBtnsProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnsProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBtnsProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBtnsProfessorLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlBtnsProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovoProfessor)
                            .addComponent(btnEditarProfessor)
                            .addComponent(btnPesquisarProfessor)
                            .addComponent(btnExcluirProfessor)
                            .addComponent(btnCancelarProfessor)))
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNomeProfessor)
                .addGap(18, 18, 18)
                .addComponent(btnOkProfessor)
                .addGap(129, 129, 129))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(pnlBtnsProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail)
                        .addGap(219, 219, 219))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblDepartamento)
                        .addGap(18, 18, 18)
                        .addComponent(txtDepartamentoProfessor)
                        .addGap(221, 221, 221))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDisciplinasHorarios)
                            .addComponent(lblDisciplinaHorario))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrlpnlListaDisciplinasHorarios)
                            .addComponent(txtDisciplinaHorario))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnExcluirDisciplinaProfessor)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnEditarHorarioProfessor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnOkEditarHorario)
                                    .addComponent(txtEditarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(lblProfessores)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblProfessores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeProfessor)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNomeProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOkProfessor)))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDepartamento)
                    .addComponent(txtDepartamentoProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDisciplinasHorarios)
                            .addComponent(btnExcluirDisciplinaProfessor))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditarHorarioProfessor)
                            .addComponent(txtEditarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnOkEditarHorario))
                    .addComponent(scrlpnlListaDisciplinasHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDisciplinaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDisciplinaHorario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBtnsProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 852, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkProfessorActionPerformed
        String nomeProfessor = txtNomeProfessor.getText();
        VerificaNome verificadorNome= new VerificaNome();
        if (nomeProfessor.equals("") || !verificadorNome.verifica(nomeProfessor)){
            JOptionPane.showMessageDialog(null, "Digite uma entrada válida para a pesquisa");
        }
        else {
            Professor prof =null;
            List <Professor> listaProfessoresLocal = procuraProfessor.read();
            for (Professor professor : listaProfessoresLocal){
                if (professor.getNome().toLowerCase().equals(nomeProfessor.toLowerCase())){
                    prof = professor;
                }
                
            }
            if (prof == null){
                JOptionPane.showMessageDialog(null, "Professor não encontrado");
            }
            else {
                professorTela= prof;
                carregarInformacoesProfessor();
                btnEditarProfessor.setEnabled(true);
                txtNomeProfessor.setEnabled(false);
                btnExcluirProfessor.setEnabled(true);
                btnNovoProfessor.setEnabled(true);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnOkProfessorActionPerformed

    private void listaDisciplinasHorariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaDisciplinasHorariosMouseClicked
        int indice = listaDisciplinasHorarios.getSelectedIndex();
        if (indice >=0 && indice <=listaDisciplinasHorarios.getMaxSelectionIndex()){
            btnExcluirDisciplinaProfessor.setEnabled(true);
            btnEditarHorarioProfessor.setEnabled(true);
            btnOkEditarHorario.setEnabled(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_listaDisciplinasHorariosMouseClicked

    private void txtDisciplinaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisciplinaHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisciplinaHorarioActionPerformed

    private void btnExcluirDisciplinaProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirDisciplinaProfessorActionPerformed
        int indice = listaDisciplinasHorarios.getSelectedIndex();
        if (indice >=0 && indice <=listaDisciplinasHorarios.getMaxSelectionIndex() && listaDisciplinasHorarios.getModel().getSize()-1>0){
            Disciplina disciplinaExcluir = professorTela.getDisciplinas().get(indice);
            adm.tiraDisciplinaLista(professorTela, disciplinaExcluir.getCodigo());
            adm.tiraProfessorLista(disciplinaExcluir.getCodigo(), professorTela.getNome());
            professorTela.limpaHorarios();
            professorTela.limpaDisciplinas();
            carregarInformacoesProfessor();
            listaDisciplinasHorarios.clearSelection();
            btnExcluirDisciplinaProfessor.setEnabled(false);
            btnOkEditarHorario.setEnabled(false);
        }
        else if (listaDisciplinasHorarios.getModel().getSize()-1==0){
            JOptionPane.showMessageDialog(null, "O professor deve ter ao menos uma disciplina cadastrada", "Erro ao excluir",JOptionPane.INFORMATION_MESSAGE);
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirDisciplinaProfessorActionPerformed

    private void txtEditarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEditarHorarioActionPerformed

              // TODO add your handling code here:
    }//GEN-LAST:event_txtEditarHorarioActionPerformed

    private void btnEditarHorarioProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarHorarioProfessorActionPerformed
        int indice = listaDisciplinasHorarios.getSelectedIndex();
        if (indice >=0 && indice <=listaDisciplinasHorarios.getMaxSelectionIndex()){
            txtEditarHorario.setEnabled(true);
            btnOkEditarHorario.setEnabled(true);
            txtEditarHorario.requestFocus();
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnEditarHorarioProfessorActionPerformed

    private void btnOkEditarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkEditarHorarioActionPerformed
        int indice = listaDisciplinasHorarios.getSelectedIndex();
        if (indice >=0 && indice <=listaDisciplinasHorarios.getMaxSelectionIndex()){
            String novoHorario = txtEditarHorario.getText();
            String[] disciplinaHorario = listaDisciplinasHorarios.getSelectedValue().split(":");

            String disciplina = disciplinaHorario[0];
            Horario verificadorHorario = new Horario();
            try{
                if (verificadorHorario.verifica(novoHorario)){
                    adm.editarHorarioProfessor(professorTela, disciplina, novoHorario);
                    professorTela.limpaHorarios();
                    professorTela.limpaDisciplinas();
                    carregarInformacoesProfessor();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Insira um horario válido");
                }
            }
            catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Insira um horario válido");
            }
        }
    // TODO add your handling code here:
    }//GEN-LAST:event_btnOkEditarHorarioActionPerformed

    private void btnNovaDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaDisciplinaActionPerformed
        limparDisciplina();
        btnNovaDisciplina.setEnabled(false);
        btnPesquisarDisciplina.setEnabled(false);
        btnCancelarDisciplina.setEnabled(true);
        btnSalvarDisciplina.setEnabled(true);
        txtNomeDisciplina.setEnabled(true);
        txtCodigoDisciplina.setEnabled(true);
        txtDepartamentoDisciplina.setEnabled(true);
        txtCargaHoraria.setEnabled(true);
        txtAdicionarProfessores.setEnabled(true);
        novaDisciplina= true;
                // TODO add your handling code here:
    }//GEN-LAST:event_btnNovaDisciplinaActionPerformed

    private void txtCargaHorariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargaHorariaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargaHorariaActionPerformed

    private void btnCancelarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarDisciplinaActionPerformed
        limparDisciplina();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarDisciplinaActionPerformed

    private void btnPesquisarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarDisciplinaActionPerformed
        limparDisciplina();
        btnNovaDisciplina.setEnabled(false);
        btnOkPesquisaDisciplina.setEnabled(true);
        txtCodigoDisciplina.setEnabled(true); 
        btnCancelarDisciplina.setEnabled(true);
        txtCodigoDisciplina.requestFocus();// TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarDisciplinaActionPerformed

    private void btnExcluirDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirDisciplinaActionPerformed
        for (Professor prof : disciplinaTela.getListaProfessores()){
            if (prof!=null){
                adm.tiraDisciplinaLista(prof, disciplinaTela.getCodigo());
            }
        }
        adm.deleteDisciplina(disciplinaTela);
        limparDisciplina();// TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirDisciplinaActionPerformed

    private void btnRemoveProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProfessorActionPerformed
        int indice = listaProfessores.getSelectedIndex();
        if (indice >=0 && indice <=listaProfessores.getMaxSelectionIndex()){
            Professor professorExcluir = disciplinaTela.getListaProfessores().get(indice);
            adm.tiraProfessorLista(disciplinaTela.getCodigo(), professorExcluir.getNome());
            adm.tiraDisciplinaLista(professorExcluir, disciplinaTela.getCodigo());
            disciplinaTela.limpaProfessores();
            carregarInformacoesDisciplina();
            listaProfessores.clearSelection();
            btnRemoveProfessor.setEnabled(false);         
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoveProfessorActionPerformed

    private void btnOkPesquisaDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkPesquisaDisciplinaActionPerformed
        String codigo = txtCodigoDisciplina.getText();
        VerificaCodigo verificadorCodigo = new VerificaCodigo();
        if (codigo.equals("")|| !verificadorCodigo.verifica(codigo)){
            JOptionPane.showMessageDialog(null, "Digite uma entrada válida para a pesquisa");         
        }  
        else {
            Disciplina disc= null;
            List <Disciplina> listaDisciplinas = procuraDisciplina.read();
            for (Disciplina disciplina : listaDisciplinas){
                if (disciplina.getCodigo().toLowerCase().equals(codigo.toLowerCase())){
                    disc = disciplina;
                    break;
                }              
            }
            if (disc==null){
                JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
            }
            else{
                disciplinaTela = disc;
                carregarInformacoesDisciplina();
                txtNomeDisciplina.setEnabled(false);
                btnExcluirDisciplina.setEnabled(true);
                btnNovaDisciplina.setEnabled(true);
                listaProfessores.setEnabled(true);
            }
        }
    // TODO add your handling code here:
    }//GEN-LAST:event_btnOkPesquisaDisciplinaActionPerformed

    private void listaProfessoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaProfessoresMouseClicked
        int indice = listaProfessores.getSelectedIndex();
        if (indice>=0 && indice<=listaProfessores.getMaxSelectionIndex()){
            btnRemoveProfessor.setEnabled(true);
        }
    // TODO add your handling code here:
    }//GEN-LAST:event_listaProfessoresMouseClicked

    private void btnSalvarDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarDisciplinaActionPerformed
        VerificaCodigo verificadorCodigo= new VerificaCodigo();
        VerificaNumero verificadorNumero = new VerificaNumero();
        Horario verificaHorario = new Horario();
        String nome = txtNomeDisciplina.getText();
        String codigo = txtCodigoDisciplina.getText();
        String departamento = txtDepartamentoDisciplina.getText();
        String carga= txtCargaHoraria.getText();
        String [] adicionarProfessores = txtAdicionarProfessores.getText().split(",");
        ArrayList<String> listaNomesProfessores= new ArrayList<>();
        ArrayList<String> novaListaProfessores = new ArrayList<>();
        ArrayList<String> novaListaHorarios = new ArrayList<>();
        List<Professor> listaProfessoresGeral = procuraProfessor.read();
        for (Professor prof: listaProfessoresGeral){
            listaNomesProfessores.add(prof.getNome());
        }
        boolean validoProfessor = false;
        boolean validoHorario = false;
        if (!txtAdicionarProfessores.getText().equals("")){
            for (String professorHorario : adicionarProfessores){
                String[] separaProfessorHorario = professorHorario.split(":");
                validoProfessor = listaNomesProfessores.contains(separaProfessorHorario[0]);
                validoHorario = verificaHorario.verifica(separaProfessorHorario[1]);
                novaListaProfessores.add(separaProfessorHorario[0]);
                novaListaHorarios.add(separaProfessorHorario[1]);
            }
        }
        if (nome.equals("")|| codigo.equals("")|| departamento.equals("")|| carga.equals("") || !validoProfessor || !verificadorCodigo.verifica(codigo) || !verificadorNumero.verifica(carga) || !validoHorario){
            JOptionPane.showMessageDialog(null, "Digite entradas válidas");
        }
        else if (Integer.parseInt(carga)<=0){
            JOptionPane.showMessageDialog(null, "Digite entradas válidas");
        }
        else{
            if (novaDisciplina){
                Disciplina novaDisc = new Disciplina (nome,departamento,codigo,Integer.parseInt(carga));
                String novosProfessores = String.join(",", novaListaProfessores);
                adm.createDisciplina(novaDisc, novosProfessores);
                procuraDisciplina.criaListaDisciplina(novaDisc);
                for (int i=0; i<novaDisc.getListaProfessores().size();i++){
                    adm.addDisciplina(codigo, novaListaHorarios.get(i), novaDisc.getListaProfessores().get(i).getId());
                }
                limparDisciplina();
                novaDisciplina = false;
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarDisciplinaActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Horario verificadorHorario = new Horario();
        VerificaCodigo verificadorCodigo = new VerificaCodigo();
        String nome = txtNomeProfessor.getText();
        String email = txtEmail.getText();
        String departamento = txtDepartamentoProfessor.getText();
        String horarios = txtDisciplinaHorario.getText();
        String [] horariosSeparados = horarios.split(";");
        ArrayList<String> nomeDisciplinas = new ArrayList<>();
        boolean existeDisciplina =false;
        boolean horarioValido = false;
        boolean codigoValido = false;
        if (!horarios.equals("")){
            for (String horariosLoop : horariosSeparados){
                String [] disciplinaHorario = horariosLoop.split(":");
                for (Disciplina disc : procuraDisciplina.read()){
                    if (disc.getCodigo().equals(disciplinaHorario[0])){
                        existeDisciplina=true;
                        nomeDisciplinas.add(disc.getCodigo());
                        break;
                    }
                }
                codigoValido = verificadorCodigo.verifica(disciplinaHorario[0]);
                horarioValido = verificadorHorario.verifica(disciplinaHorario[1]);
            }
        }
        String listaDisciplinas = String.join(",", nomeDisciplinas);
        VerificaEmail verificadorEmail = new VerificaEmail();
        VerificaNome verificadorNome = new VerificaNome();

        boolean valido = verificadorEmail.verifica(email) && verificadorNome.verifica(nome) && horarioValido && codigoValido;
        if (nome.equals("") || email.equals("") || departamento.equals("") || horarios.equals("") || !valido || !existeDisciplina){
            JOptionPane.showMessageDialog(null, "Digite entradas válidas");
        }
        else {
            if (novoProfessor){
                Professor novoProf = new Professor (nome, departamento, email);
                adm.createProfessor(novoProf, listaDisciplinas, horarios);
                Professor acharId = procuraProfessor.achaProfessor(novoProf.getNome());
                int id = acharId.getId();
                novoProf.setId(id);
                procuraProfessor.criaListaDisciplinas(novoProf);
                for (Disciplina disc : novoProf.getDisciplinas()){
                    adm.addProfessor(novoProf.getNome(), disc);
                }
                limparProfessor();
                novoProfessor = false;

            }
            else if (editarProfessor){

                Professor editProfessor = new Professor (nome,departamento,email);
                DisciplinaDAO achaDisciplina = new DisciplinaDAO();
                ArrayList<String> listaDisciplinasTela = new ArrayList<>();
                ArrayList<String> novosHorarios = new ArrayList<>();
                for (Disciplina disc : professorTela.getDisciplinas()){
                    listaDisciplinasTela.add(disc.getNome());
                    editProfessor.addDisciplina(achaDisciplina.achaDisciplina(disc.getCodigo()));
                    novosHorarios.add(disc.getCodigo()+":"+professorTela.getHorario(disc));
                }
                boolean jaAdicionado = false;
                for (String horariosLoop : horariosSeparados){
                    String [] disciplinaHorario = horariosLoop.split(":");
                    if (listaDisciplinasTela.contains(disciplinaHorario[0])){
                        jaAdicionado = true;
                    }
                    else{
                        editProfessor.addDisciplina(procuraDisciplina.achaDisciplina(disciplinaHorario[0]));
                        novosHorarios.add(disciplinaHorario[0]+":"+disciplinaHorario[1]);
                    }
                }
                String atualizaHorario = String.join(";", novosHorarios);
                editProfessor.setId(professorTela.getId());
                if (jaAdicionado){
                    JOptionPane.showMessageDialog(null, "Digite disciplinas/horários válidos");
                }
                else{
                    adm.editarProfessor(editProfessor,atualizaHorario);
                    limparProfessor();
                }

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoProfessorActionPerformed
        limparProfessor();
        btnNovoProfessor.setEnabled(false);
        btnCancelarProfessor.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnPesquisarProfessor.setEnabled(false);
        novoProfessor = true;
        editarProfessor = false;
        txtNomeProfessor.setEnabled(true);
        txtEmail.setEnabled(true);
        txtDepartamentoProfessor.setEnabled(true);
        txtDisciplinaHorario.setEnabled(true);
        listaDisciplinasHorarios.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_btnNovoProfessorActionPerformed

    private void btnCancelarProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarProfessorActionPerformed
        limparProfessor();   // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarProfessorActionPerformed

    private void btnExcluirProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProfessorActionPerformed
        AvaliacaoDAO procuraAvaliacao= new AvaliacaoDAO();
        List<Avaliacao> listaAvaliacoes = procuraAvaliacao.read();
        for (Avaliacao avalia : listaAvaliacoes){
            if (avalia.getProfessor().getId() == professorTela.getId()){
                adm.deleteAvaliacao(avalia);
            }
        }
        for (Disciplina disc : professorTela.getDisciplinas()){
            adm.tiraProfessorLista(disc.getCodigo(), professorTela.getNome());
        }
        adm.deleteProfessor(professorTela);
        limparProfessor();        
    // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirProfessorActionPerformed

    private void btnPesquisarProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarProfessorActionPerformed
        limparProfessor();
        btnNovoProfessor.setEnabled(false);
        btnOkProfessor.setEnabled(true);
        txtNomeProfessor.setEnabled(true);
        btnCancelarProfessor.setEnabled(true);
        txtNomeProfessor.requestFocus();// TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarProfessorActionPerformed

    private void btnEditarProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarProfessorActionPerformed
        txtNomeProfessor.setEnabled(true);
        txtDepartamentoProfessor.setEnabled(true);
        txtEmail.setEnabled(true);
        listaDisciplinasHorarios.setEnabled(true);
        btnSalvar.setEnabled(true);
        btnNovoProfessor.setEnabled(false);
        btnCancelarProfessor.setEnabled(true);
        btnPesquisarProfessor.setEnabled(false);
        editarProfessor = true;
        novoProfessor = false;
        txtDisciplinaHorario.setEnabled(true);
// TODO add your handling code here:
    }//GEN-LAST:event_btnEditarProfessorActionPerformed

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
            java.util.logging.Logger.getLogger(TelaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarDisciplina;
    private javax.swing.JButton btnCancelarProfessor;
    private javax.swing.JButton btnEditarHorarioProfessor;
    private javax.swing.JButton btnEditarProfessor;
    private javax.swing.JButton btnExcluirDisciplina;
    private javax.swing.JButton btnExcluirDisciplinaProfessor;
    private javax.swing.JButton btnExcluirProfessor;
    private javax.swing.JButton btnNovaDisciplina;
    private javax.swing.JButton btnNovoProfessor;
    private javax.swing.JButton btnOkEditarHorario;
    private javax.swing.JButton btnOkPesquisaDisciplina;
    private javax.swing.JButton btnOkProfessor;
    private javax.swing.JButton btnPesquisarDisciplina;
    private javax.swing.JButton btnPesquisarProfessor;
    private javax.swing.JButton btnRemoveProfessor;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvarDisciplina;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAdicionarListaProfessor;
    private javax.swing.JLabel lblCargaHoraria;
    private javax.swing.JLabel lblCodigoDisciplina;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblDepartamentoDisciplina;
    private javax.swing.JLabel lblDisciplinaHorario;
    private javax.swing.JLabel lblDisciplinas;
    private javax.swing.JLabel lblDisciplinasHorarios;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblListaProfessores;
    private javax.swing.JLabel lblNomeDisciplina;
    private javax.swing.JLabel lblNomeProfessor;
    private javax.swing.JLabel lblProfessores;
    private javax.swing.JList<String> listaDisciplinasHorarios;
    private javax.swing.JList<String> listaProfessores;
    private javax.swing.JPanel pnlBtnsProfessor;
    private javax.swing.JScrollPane scrlpnlListaDisciplinasHorarios;
    private javax.swing.JTextField txtAdicionarProfessores;
    private javax.swing.JTextField txtCargaHoraria;
    private javax.swing.JTextField txtCodigoDisciplina;
    private javax.swing.JTextField txtDepartamentoDisciplina;
    private javax.swing.JTextField txtDepartamentoProfessor;
    private javax.swing.JTextField txtDisciplinaHorario;
    private javax.swing.JTextField txtEditarHorario;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNomeDisciplina;
    private javax.swing.JTextField txtNomeProfessor;
    // End of variables declaration//GEN-END:variables
}
