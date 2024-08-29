/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;


import classes.Aluno;
import classes.Avaliacao;
import classes.Disciplina;
import java.sql.*;
import classes.Professor;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Font;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.font.TextAttribute;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.AvaliacaoDAO;
import model.dao.DisciplinaDAO;
import model.dao.ProfessorDAO;

/**
 *
 * @author pedro
 */
public class telaProfessor extends javax.swing.JFrame {
Professor professorTela = null;
boolean pesquisaProfessor = false ;
boolean pesquisaDisciplina = false;
ProfessorDAO professorPesquisa = new ProfessorDAO();
List<Professor> listaProfessores = professorPesquisa.read();
ArrayList<String> disciplinasAdicionadas = new ArrayList<>();
Aluno alunoTela= null;

    /**
     * Creates new form telaProfessor
     */
    public void carregaInformacoes(){
        professorPesquisa.criaListaDisciplinas(professorTela);
        for (Disciplina disc : professorTela.getDisciplinas()){
            professorPesquisa.addHorario(disc, professorTela);
        }
        professorPesquisa.criaListaAvaliacoes(professorTela);
        lblNome.setText(professorTela.getNome());
        lblEmail.setText(professorTela.getEmail());
        lblMedia.setText(String.valueOf(professorTela.mediaAvaliacao()));
        lblNumAvaliacoes.setText(String.valueOf(professorTela.getListaAvaliacoes().size()));
        lblDepartamento.setText(professorTela.getDepartamento());
        btnIrAvaliar.setEnabled(false);
        btnPerfilDisciplina.setEnabled(false);
        btnPesquisar.setEnabled(true);
        btnVoltar.setEnabled(true);
        btnLike.setEnabled(false);
        cmbPesquisa.setEnabled(false);      
    }
    
    public void carregarTabelaDisciplinas (){
        DefaultTableModel modelo = new DefaultTableModel (new Object[] {"Disciplina","Código","Horário"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Disciplina disciplina : professorTela.getDisciplinas()){
            if (!disciplinasAdicionadas.contains(disciplina.getNome())){           
            Object linha [] = new Object[] {disciplina.getNome(),
                                            disciplina.getCodigo(),
                                            professorTela.getHorario(disciplina),
                                            };
            modelo.addRow(linha);
            disciplinasAdicionadas.add(disciplina.getNome());
            }
        }
        tblDisciplinas.setModel(modelo);
    }
    
     public void carregarTabelaAvaliacoes (){        
        DefaultTableModel modelo = new DefaultTableModel (new Object[] {"Feedback","Nota","Like"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
        return false;
                }

        };
        for (Avaliacao avalia : professorTela.rankearAvaliacao()){
           
            Object linha [] = new Object[] {avalia.getFeedback(),
                                            avalia.getNota(),
                                            avalia.getLike()
                                            };
            modelo.addRow(linha);        
        }

        tblAvaliacoes.setModel(modelo);
        tblAvaliacoes.getColumnModel().getColumn(0).setPreferredWidth(35);
        tblAvaliacoes.getColumnModel().getColumn(1).setPreferredWidth(5);
        tblAvaliacoes.getColumnModel().getColumn(2).setPreferredWidth(5);


    }


    public telaProfessor() {              
        professorTela= listaProfessores.get(5);
        initComponents();
        try{
        carregaInformacoes();
        carregarTabelaDisciplinas();
        carregarTabelaAvaliacoes ();
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
        rdbDisciplina.setEnabled(true);
        rdbPesquisaProfessor.setEnabled(true);}
        
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o perfil do professor");
        }    
    }
    
    public telaProfessor(Professor prof,Aluno aluno) {
        professorTela=prof;
        alunoTela= aluno;
        initComponents();
        try{
            carregaInformacoes();
            carregarTabelaDisciplinas();
            carregarTabelaAvaliacoes ();
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
            rdbDisciplina.setEnabled(true);
            rdbPesquisaProfessor.setEnabled(true);
        }
        
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Não foi possível acessar o perfil do professor");
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

        grpPesquisa = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        pnlFoto = new javax.swing.JPanel();
        pnlInformacoesProfessor = new javax.swing.JPanel();
        lblTituloDepartamento = new javax.swing.JLabel();
        lblTitleNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblTituloDepartamento1 = new javax.swing.JLabel();
        lblTitleNumAvaliacoes = new javax.swing.JLabel();
        lblTitleMedia = new javax.swing.JLabel();
        lblMedia = new javax.swing.JLabel();
        btnIrAvaliar = new javax.swing.JButton();
        lblNumAvaliacoes = new javax.swing.JLabel();
        pblAvaliacoes = new javax.swing.JPanel();
        lblListaDisciplinas = new javax.swing.JLabel();
        scrlpnlDisciplinas = new javax.swing.JScrollPane();
        tblDisciplinas = new javax.swing.JTable();
        pnlBtnsAvaliacoes = new javax.swing.JPanel();
        btnPerfilDisciplina = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        pnlPesquisa = new javax.swing.JPanel();
        lblImagemPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        rdbPesquisaProfessor = new javax.swing.JRadioButton();
        rdbDisciplina = new javax.swing.JRadioButton();
        cmbPesquisa = new javax.swing.JComboBox<>();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAvaliacoes = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        lblTitleListaAvaliacoes = new javax.swing.JLabel();
        btnLike = new javax.swing.JButton();

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perfil do professor");

        lblTituloDepartamento.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTituloDepartamento.setText("Departamento");

        lblTitleNome.setBackground(new java.awt.Color(0, 255, 0));
        lblTitleNome.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNome.setText("Nome");

        lblEmail.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(51, 102, 255));
        lblEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblDepartamento.setBackground(new java.awt.Color(0, 255, 0));
        lblDepartamento.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N

        lblNome.setBackground(new java.awt.Color(0, 255, 0));
        lblNome.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N

        lblTituloDepartamento1.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTituloDepartamento1.setText("Email");

        lblTitleNumAvaliacoes.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNumAvaliacoes.setText("Nº de avaliações");

        lblTitleMedia.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleMedia.setText("Média de avaliações");

        btnIrAvaliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/nova-avaliacao.png"))); // NOI18N
        btnIrAvaliar.setText("Avaliar");

        javax.swing.GroupLayout pnlInformacoesProfessorLayout = new javax.swing.GroupLayout(pnlInformacoesProfessor);
        pnlInformacoesProfessor.setLayout(pnlInformacoesProfessorLayout);
        pnlInformacoesProfessorLayout.setHorizontalGroup(
            pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addComponent(lblTituloDepartamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnIrAvaliar)
                        .addGap(193, 193, 193))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTituloDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                                .addComponent(lblTitleNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3))
                            .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                                .addComponent(lblDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitleNumAvaliacoes)
                            .addComponent(lblTitleMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(133, 133, 133))))
        );
        pnlInformacoesProfessorLayout.setVerticalGroup(
            pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInformacoesProfessorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNumAvaliacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumAvaliacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloDepartamento)
                    .addComponent(lblTitleMedia))
                .addGap(0, 4, Short.MAX_VALUE)
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlInformacoesProfessorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTituloDepartamento1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlInformacoesProfessorLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnIrAvaliar))))
        );

        lblListaDisciplinas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblListaDisciplinas.setText("Lista de Disciplinas");

        tblDisciplinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Disciplina", "Código", "Horário"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDisciplinas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblDisciplinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDisciplinasMouseClicked(evt);
            }
        });
        scrlpnlDisciplinas.setViewportView(tblDisciplinas);

        btnPerfilDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/perfil disciplina icon.png"))); // NOI18N
        btnPerfilDisciplina.setText("Perfil da disciplina");
        btnPerfilDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilDisciplinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBtnsAvaliacoesLayout = new javax.swing.GroupLayout(pnlBtnsAvaliacoes);
        pnlBtnsAvaliacoes.setLayout(pnlBtnsAvaliacoesLayout);
        pnlBtnsAvaliacoesLayout.setHorizontalGroup(
            pnlBtnsAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnsAvaliacoesLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(btnPerfilDisciplina)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        pnlBtnsAvaliacoesLayout.setVerticalGroup(
            pnlBtnsAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnPerfilDisciplina, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout pblAvaliacoesLayout = new javax.swing.GroupLayout(pblAvaliacoes);
        pblAvaliacoes.setLayout(pblAvaliacoesLayout);
        pblAvaliacoesLayout.setHorizontalGroup(
            pblAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblAvaliacoesLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(pnlBtnsAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblAvaliacoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pblAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblAvaliacoesLayout.createSequentialGroup()
                        .addComponent(lblListaDisciplinas)
                        .addGap(224, 224, 224))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblAvaliacoesLayout.createSequentialGroup()
                        .addComponent(scrlpnlDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        pblAvaliacoesLayout.setVerticalGroup(
            pblAvaliacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblAvaliacoesLayout.createSequentialGroup()
                .addComponent(lblListaDisciplinas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrlpnlDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(pnlBtnsAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pessoa.png"))); // NOI18N

        javax.swing.GroupLayout pnlFotoLayout = new javax.swing.GroupLayout(pnlFoto);
        pnlFoto.setLayout(pnlFotoLayout);
        pnlFotoLayout.setHorizontalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFotoLayout.createSequentialGroup()
                        .addComponent(pblAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlFotoLayout.createSequentialGroup()
                        .addComponent(lblFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlInformacoesProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlFotoLayout.setVerticalGroup(
            pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInformacoesProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(pblAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        btnPesquisar.setMnemonic('P');
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnPesquisar))
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

        tblAvaliacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Feedback", "Nota", "Aluno", "Like"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblAvaliacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAvaliacoesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAvaliacoes);

        btnVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/de-volta.png"))); // NOI18N
        btnVoltar.setText("Tela Inicial");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblTitleListaAvaliacoes.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleListaAvaliacoes.setText("Lista de avaliações");

        btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/curtir icone.png"))); // NOI18N
        btnLike.setText("Curtir avaliação");
        btnLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLikeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitleListaAvaliacoes)
                .addGap(260, 260, 260))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(btnVoltar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLike)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitleListaAvaliacoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLike, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
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
        else if (!pesquisaProfessor && !pesquisaDisciplina){
            JOptionPane.showMessageDialog(null, "Selecione uma opção de pesquisa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (pesquisaProfessor){
                String nomeProfessor = txtPesquisa.getText().toLowerCase();
                boolean achou = false;
                for (Professor professor : listaProfessores){
                    if (professor.getNome().toLowerCase().equals(nomeProfessor)){
                        achou=true;
                        new telaProfessor(professor,alunoTela).setVisible(true);
                        this.setVisible(false);
                        dispose();
                    } 
                }
                if (!achou){
                    JOptionPane.showMessageDialog(null, "Nenhum professor encontrado", "", JOptionPane.INFORMATION_MESSAGE);

                }

            }
            else if (pesquisaDisciplina){
                DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
                ArrayList <Disciplina> listaDisciplinas = (ArrayList<Disciplina>) procuraDisciplina.read();
                boolean achou = false;
                if (cmbPesquisa.getSelectedIndex() ==0){
                    String codigoDisciplina = txtPesquisa.getText().toLowerCase();
                    for (Disciplina disciplina : listaDisciplinas){
                        if (disciplina.getCodigo().toLowerCase().equals(codigoDisciplina)){
                            new telaDisciplina(disciplina,alunoTela).setVisible(true);
                            this.setVisible(false);
                            dispose();
                            achou= true;
                            break;
                        }
                    }
                }
                else if (cmbPesquisa.getSelectedIndex()==1){
                    String nomeDisciplina = txtPesquisa.getText().toLowerCase();
                    for (Disciplina disciplina : listaDisciplinas){
                        if (disciplina.getNome().toLowerCase().equals(nomeDisciplina)){
                            new telaDisciplina(disciplina,alunoTela).setVisible(true);
                            this.setVisible(false);
                            dispose();
                            achou=true;
                            break;
                        }
                    }
                }
                if (!achou){
                    JOptionPane.showMessageDialog(null, "Nenhuma disciplina encontrada","", JOptionPane.INFORMATION_MESSAGE);
                    txtPesquisa.setText("");
                }
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void rdbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDisciplinaActionPerformed
        cmbPesquisa.setEnabled(true);
        pesquisaDisciplina= true;
        pesquisaProfessor = false;
        cmbPesquisa.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_rdbDisciplinaActionPerformed

    private void tblDisciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisciplinasMouseClicked
     int linha = tblDisciplinas.getSelectedRow();
        if (linha>=0 && linha <tblDisciplinas.getRowCount()){
            String celula = (String) tblDisciplinas.getValueAt(linha, 0);
            if (!celula.equals("")){
                btnPerfilDisciplina.setEnabled(true);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_tblDisciplinasMouseClicked

    private void btnPerfilDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilDisciplinaActionPerformed
        String celula = (String) tblDisciplinas.getValueAt(tblDisciplinas.getSelectedRow(), 1);
        DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
        try {
            Disciplina disc = procuraDisciplina.achaDisciplina(celula);
            new telaDisciplina(disc,alunoTela).setVisible(true);
            this.setVisible(false);
            dispose();
        }
        catch(NullPointerException e){
                JOptionPane.showMessageDialog(null, "Não foi possível abrir o perfil da disciplina");
        }
        
        
      // TODO add your handling code here:
    }//GEN-LAST:event_btnPerfilDisciplinaActionPerformed

    private void btnLikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLikeActionPerformed
        int linha= tblAvaliacoes.getSelectedRow();   
        if (linha>=0 && linha <tblAvaliacoes.getRowCount()){
            Avaliacao avaliacaoSelecionada = professorTela.getListaAvaliacoes().get(linha);
            AvaliacaoDAO procuraAvaliacao = new AvaliacaoDAO();
            int novoLike = avaliacaoSelecionada.getLike()+1;
            avaliacaoSelecionada.setLike(novoLike);
            procuraAvaliacao.updateLike(avaliacaoSelecionada);
            professorTela.limpaAvaliacoes();
            professorPesquisa.criaListaAvaliacoes(professorTela);
            lblNumAvaliacoes.setText(String.valueOf(professorTela.getListaAvaliacoes().size()));
            carregarTabelaAvaliacoes();
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnLikeActionPerformed

    private void tblAvaliacoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAvaliacoesMouseClicked
        int linha = tblAvaliacoes.getSelectedRow();
        if (linha>=0 && linha <tblAvaliacoes.getRowCount()){
            btnLike.setEnabled(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblAvaliacoesMouseClicked

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.setVisible(false);
        new TelaAluno(alunoTela).setVisible(true);
        dispose();// TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarActionPerformed

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
            @Override
            public void run() {
                new telaProfessor().setVisible(true);}

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIrAvaliar;
    private javax.swing.JButton btnLike;
    private javax.swing.JButton btnPerfilDisciplina;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cmbPesquisa;
    private javax.swing.ButtonGroup grpPesquisa;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDepartamento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblImagemPesquisa;
    private javax.swing.JLabel lblListaDisciplinas;
    private javax.swing.JLabel lblMedia;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNumAvaliacoes;
    private javax.swing.JLabel lblTitleListaAvaliacoes;
    private javax.swing.JLabel lblTitleMedia;
    private javax.swing.JLabel lblTitleNome;
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
    private javax.swing.JScrollPane scrlpnlDisciplinas;
    private javax.swing.JTable tblAvaliacoes;
    private javax.swing.JTable tblDisciplinas;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
