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
import model.dao.AlunoDAO;
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
ArrayList<Integer> listaCurtidas = new ArrayList<>();
AlunoDAO procuraAluno = new AlunoDAO();

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
        if (professorTela.mediaAvaliacao()==-1.0){
            lblMedia.setText("Não há avaliações cadastradas");
        }
        else{
            lblMedia.setText(String.valueOf(professorTela.mediaAvaliacao()));
        }
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

    }


    public telaProfessor() {              
            
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
    
    public telaProfessor(Professor prof,Aluno aluno) {
        professorTela=prof;
        alunoTela= aluno;
        initComponents();
        try{
            this.listaCurtidas= procuraAluno.avaliacoesCurtidas(alunoTela.getId());
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
            btnIrAvaliar.setEnabled (true);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        pnlPesquisa = new javax.swing.JPanel();
        lblImagemPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        rdbPesquisaProfessor = new javax.swing.JRadioButton();
        rdbDisciplina = new javax.swing.JRadioButton();
        cmbPesquisa = new javax.swing.JComboBox<>();
        btnPesquisar = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTitleNome = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblTituloDepartamento = new javax.swing.JLabel();
        lblDepartamento = new javax.swing.JLabel();
        lblTituloDepartamento1 = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTitleNumAvaliacoes = new javax.swing.JLabel();
        lblTitleMedia = new javax.swing.JLabel();
        lblNumAvaliacoes = new javax.swing.JLabel();
        lblMedia = new javax.swing.JLabel();
        btnIrAvaliar = new javax.swing.JButton();
        lblListaDisciplinas = new javax.swing.JLabel();
        scrlpnlDisciplinas = new javax.swing.JScrollPane();
        tblDisciplinas = new javax.swing.JTable();
        btnPerfilDisciplina = new javax.swing.JButton();
        lblTitleListaAvaliacoes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAvaliacoes = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        btnLike = new javax.swing.JButton();

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Perfil do professor");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/teacher icon.png")).getImage());
        setResizable(false);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(707, 800));

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

        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pessoa.png"))); // NOI18N

        lblTitleNome.setBackground(new java.awt.Color(0, 255, 0));
        lblTitleNome.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNome.setText("Nome");

        lblNome.setBackground(new java.awt.Color(0, 255, 0));
        lblNome.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N

        lblTituloDepartamento.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTituloDepartamento.setText("Departamento");

        lblDepartamento.setBackground(new java.awt.Color(0, 255, 0));
        lblDepartamento.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N

        lblTituloDepartamento1.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTituloDepartamento1.setText("Email");

        lblEmail.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(51, 102, 255));
        lblEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblTitleNumAvaliacoes.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNumAvaliacoes.setText("Nº de avaliações");

        lblTitleMedia.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleMedia.setText("Média de avaliações");

        btnIrAvaliar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/nova-avaliacao.png"))); // NOI18N
        btnIrAvaliar.setText("Avaliar");
        btnIrAvaliar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrAvaliarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTituloDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTituloDepartamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMedia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitleMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnIrAvaliar))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitleNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNumAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTitleNumAvaliacoes)
                                .addGap(63, 63, 63))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleNumAvaliacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumAvaliacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloDepartamento)
                    .addComponent(lblTitleMedia))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMedia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIrAvaliar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTituloDepartamento1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
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

        lblTitleListaAvaliacoes.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleListaAvaliacoes.setText("Lista de avaliações");

        tblAvaliacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Feedback", "Nota", "Like"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
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

        btnLike.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/curtir icone.png"))); // NOI18N
        btnLike.setText("Curtir avaliação");
        btnLike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLikeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(btnVoltar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLike))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFoto)
                            .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPerfilDisciplina)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblListaDisciplinas)
                        .addGap(214, 214, 214))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(scrlpnlDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitleListaAvaliacoes)
                        .addGap(243, 243, 243))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblListaDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(scrlpnlDisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPerfilDisciplina)
                .addGap(35, 35, 35)
                .addComponent(lblTitleListaAvaliacoes)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLike, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        else if (!pesquisaProfessor && !pesquisaDisciplina){
            JOptionPane.showMessageDialog(null, "Selecione uma opção de pesquisa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (pesquisaProfessor){
                String nomeProfessor = txtPesquisa.getText().toLowerCase();
                boolean achou = false;
                for (Professor professor : listaProfessores){
                    if (professor.getNome().toLowerCase().equals(nomeProfessor)){
                        new telaProfessor(professor,alunoTela).setVisible(true);
                        this.setVisible(false);                                                
                        achou=true;
                        dispose();
                        break;
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
            if (listaCurtidas.contains(avaliacaoSelecionada.getId())){
                JOptionPane.showMessageDialog(null, "Você já curtiu essa avaliação");
            }
            else{
                int novoLike = avaliacaoSelecionada.getLike()+1;
                avaliacaoSelecionada.setLike(novoLike);
                procuraAvaliacao.updateLike(avaliacaoSelecionada);
                procuraAluno.atualizarAvaliacoesCurtidas(alunoTela.getId(), avaliacaoSelecionada.getId());
                listaCurtidas= procuraAluno.avaliacoesCurtidas(alunoTela.getId());
                professorTela.limpaAvaliacoes();
                professorPesquisa.criaListaAvaliacoes(professorTela);
                lblNumAvaliacoes.setText(String.valueOf(professorTela.getListaAvaliacoes().size()));
                carregarTabelaAvaliacoes();
            }
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

    private void tblDisciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisciplinasMouseClicked
        int linha = tblDisciplinas.getSelectedRow();
        if (linha>=0 && linha <tblDisciplinas.getRowCount()){
            String celula = (String) tblDisciplinas.getValueAt(linha, 0);
            if (!celula.equals("")){
                btnPerfilDisciplina.setEnabled(true);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_tblDisciplinasMouseClicked

    private void btnIrAvaliarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrAvaliarActionPerformed
        this.setVisible(false);
        new TelaAvaliacao (alunoTela.getMatricula(), alunoTela, professorTela).setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnIrAvaliarActionPerformed

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JPanel pnlPesquisa;
    private javax.swing.JRadioButton rdbDisciplina;
    private javax.swing.JRadioButton rdbPesquisaProfessor;
    private javax.swing.JScrollPane scrlpnlDisciplinas;
    private javax.swing.JTable tblAvaliacoes;
    private javax.swing.JTable tblDisciplinas;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
