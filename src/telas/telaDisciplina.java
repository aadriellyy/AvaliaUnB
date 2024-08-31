/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package telas;

import classes.Aluno;
import classes.Disciplina;
import classes.Professor;
import static java.lang.Character.isDigit;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.DisciplinaDAO;
import model.dao.GradeDAO;
import model.dao.ProfessorDAO;
import verificacao.MapeiaHorarios;

/**
 *
 * @author pedro
 */
public class telaDisciplina extends javax.swing.JFrame {
    boolean pesquisaProfessor = false;
    boolean pesquisaDisciplina = false;
    Professor professorSelecionado;
    static ArrayList <String> disciplinasAdicionadasGrade = new ArrayList<>();
    static ArrayList <ArrayList<String>> horarios = new ArrayList<>();
    int horasGrade=0;
    Aluno  alunoTela;
    Disciplina disciplinaTela = null;    
    DisciplinaDAO procuraDisciplina = new DisciplinaDAO();
    List <Disciplina> listaDisciplinas = procuraDisciplina.read();
    ProfessorDAO procuraProfessor = new ProfessorDAO();
    static ArrayList <String> listaHorariosDisciplinas = new ArrayList<>();

    /**
     * Creates new form telaDisciplina
     */
    public telaDisciplina() {
        MapeiaHorarios.inicializa();
        inicializaHorario();
        initComponents();
        carregaInformacoes();
    }

    public telaDisciplina (Disciplina disc,Aluno aluno){
        alunoTela= aluno;
        disciplinaTela= disc;
        try {
            MapeiaHorarios.inicializa();
            inicializaHorario();
            initComponents();
            carregaInformacoes();
            carregarTabelaGrade();}
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Não foi possível exibir o perfil da disciplina", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void carregarTabelaProfessores (){
         DefaultTableModel modeloProfessores = new DefaultTableModel(new Object[]{"Professor","Horário", "Avaliação"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
                }

        };
         ArrayList<Professor> professoresRankeados = disciplinaTela.melhoresProfessores();
         for (int i = 0; i <professoresRankeados.size(); i++){
             double avaliacaoProfessor = professoresRankeados.get(i).mediaAvaliacao();
             String avaliacaoFormatada= "";
             if (avaliacaoProfessor==-1){
                 avaliacaoFormatada = "Não há avaliação cadastrada";
             }
             else{
                 avaliacaoFormatada= String.valueOf(avaliacaoProfessor);
             }
            Object [] linha = new Object [] {professoresRankeados.get(i).getNome(),
                                             professoresRankeados.get(i).getHorario(disciplinaTela),
                                             avaliacaoFormatada};
            modeloProfessores.addRow(linha);
        }
        tblRanking.setModel(modeloProfessores);
        btnAdicionarGrade.setEnabled(false);
        btnAvaliarProfessor.setEnabled(false);
        btnPerfilProfessor.setEnabled(false);
        btnRemover.setEnabled(false);
        btnTelaInicial.setEnabled(true);
        
    }
    public void carregaInformacoes(){        
        procuraDisciplina.criaListaDisciplina(disciplinaTela);
        for (Professor prof : disciplinaTela.getListaProfessores()){
            procuraProfessor.addHorario(disciplinaTela, prof);
            procuraProfessor.criaListaAvaliacoes(prof);
        }
        lbNomeDisciplina.setText(disciplinaTela.getNome());
        lblCargaHoraria.setText(String.valueOf(disciplinaTela.getCodigo()));
        lblCodigo.setText(disciplinaTela.getCodigo());
        lblDepartamentoDisciplina.setText(disciplinaTela.getDepartamento());
        lblHoras.setText(String.valueOf(horasGrade));
        btnAvaliarProfessor.setEnabled(false);
        btnAdicionarGrade.setEnabled(false);
        btnPerfilProfessor.setEnabled(false);
        btnPesquisar.setEnabled(false);
        btnPesquisar.setEnabled(true);
        btnRemover.setEnabled(false);
        cmbPesquisa.setEnabled(false);
        rdbDisciplina.setEnabled(true);
        rdbPesquisaProfessor.setEnabled(true);
        btnPerfilDisciplina.setEnabled(false);
        carregarTabelaProfessores();  
        
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
    
    public void ajustarHorario(String horariosPassados, String disciplina){
        String [] listaHorarios = horariosPassados.split(" ");
        boolean adicionou = false;
        if (!disciplinasAdicionadasGrade.contains(disciplina)){    
            for (String horario : listaHorarios){
                boolean jaAdicionado = false;
                adicionou = false;
                String dia, horas, turno;
                dia = "";
                horas="";
                turno = "";
                int count = 0;
                Character carac = horario.charAt(count);
                while (count < horario.length() && isDigit(carac)){
                        dia= dia+carac;
                        count+=1;
                        carac= horario.charAt(count);
                    }
                while (count < horario.length() && Character.isAlphabetic(carac)){
                    turno= turno+carac;
                    count+=1;
                    carac= horario.charAt(count);
                }

                while (count < horario.length()-1 && isDigit(carac)){
                        horas= horas+carac;
                        count+=1;
                        carac= horario.charAt(count);
                    }
                horas = horas+ horario.charAt(horario.length()-1);


                for (int i = 0; i<horas.length(); i++){
                    //jaAdicionado = false;
                    Integer linha = null;
                     if (turno.equals("m") || turno.equals("M")){
                        linha =Integer.parseInt(horas.substring(i,i+1))-1;
                     }
                     else if (turno.equals("t") || turno.equals("T")){
                        linha =Integer.parseInt(horas.substring(i,i+1))+4;
                     }
                    else if (turno.equals("n") || turno.equals("N")){
                         linha =Integer.parseInt(horas.substring(i,i+1))+9;
                    }
                    try {
                        for (int x = 0; x<dia.length(); x++){
                            if (!horarios.get(linha).get(Integer.parseInt(dia.substring(x, x+1))-1).equals("") && !jaAdicionado){
                                JOptionPane.showMessageDialog(null, "Conflito de horários, escolha outro horário");
                                jaAdicionado = true;
                                break;

                            }
                            else if (horarios.get(linha).get(Integer.parseInt(dia.substring(x, x+1))-1).equals("")){
                                    horarios.get(linha).set(Integer.parseInt(dia.substring(x, x+1))-1, disciplina);
                                    adicionou = true;
                                    //disciplinasAdicionadasGrade.add(disciplina);
                            }
                        }
                        jaAdicionado= true;

                        carregarTabelaGrade();

                }

                    catch (NullPointerException e ) {
                        JOptionPane.showMessageDialog(null, "Não foi possível adicionar a disciplina à grade");
                    }
                }

            }  
        }
    else if (disciplinasAdicionadasGrade.contains(disciplina)){
            JOptionPane.showMessageDialog(null, "Disciplina já adicionada a grade");
            }
    if (adicionou && !disciplinasAdicionadasGrade.contains(disciplina)){
            String novoHorario = disciplinaTela.getCodigo()+":"+professorSelecionado.getHorario(disciplinaTela);
            listaHorariosDisciplinas.add(novoHorario);
            disciplinasAdicionadasGrade.add(disciplina);
            horasGrade+=procuraDisciplina.achaDisciplina(disciplina).getHoras();
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

        btngrpPesquisa = new javax.swing.ButtonGroup();
        pnlDadosDisciplina = new javax.swing.JPanel();
        lblTitleNomeDisciplina = new javax.swing.JLabel();
        lbNomeDisciplina = new javax.swing.JLabel();
        lblTitleDepartamento = new javax.swing.JLabel();
        lblDepartamentoDisciplina = new javax.swing.JLabel();
        lblTitleCodigo = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        lblTitleCarga = new javax.swing.JLabel();
        lblCargaHoraria = new javax.swing.JLabel();
        pnlRankingProfessores = new javax.swing.JPanel();
        lblTitleRanking = new javax.swing.JLabel();
        pnlBtnsRanking = new javax.swing.JPanel();
        btnPerfilProfessor = new javax.swing.JButton();
        btnAvaliarProfessor = new javax.swing.JButton();
        btnAdicionarGrade = new javax.swing.JButton();
        scrllpnlRanking = new javax.swing.JScrollPane();
        tblRanking = new javax.swing.JTable();
        pnlPesquisa = new javax.swing.JPanel();
        lblImagemPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        rdbPesquisaProfessor = new javax.swing.JRadioButton();
        rdbDisciplina = new javax.swing.JRadioButton();
        btnPesquisar = new javax.swing.JButton();
        cmbPesquisa = new javax.swing.JComboBox<>();
        pnlGradeHorária = new javax.swing.JPanel();
        lblGrade = new javax.swing.JLabel();
        scrlpnlGrade = new javax.swing.JScrollPane();
        tblGrade = new javax.swing.JTable();
        pnlBotoesGrade = new javax.swing.JPanel();
        btnTelaInicial = new javax.swing.JButton();
        btnPerfilDisciplina = new javax.swing.JButton();
        lblTitleDisciplinaEscolhida = new javax.swing.JLabel();
        lblDisciplinaEscolhida = new javax.swing.JLabel();
        lblTitleHoras = new javax.swing.JLabel();
        lblHoras = new javax.swing.JLabel();
        btnSalvarGrade = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Perfil da disciplina");

        lblTitleNomeDisciplina.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleNomeDisciplina.setText("Nome");

        lbNomeDisciplina.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        lbNomeDisciplina.setText("Técnicas de Programação 1");

        lblTitleDepartamento.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleDepartamento.setText("Departamento");

        lblDepartamentoDisciplina.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        lblDepartamentoDisciplina.setText("Ciência da computação");

        lblTitleCodigo.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleCodigo.setText("Código");

        lblCodigo.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        lblCodigo.setText("CIC001");

        lblTitleCarga.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleCarga.setText("Carga Horária");

        lblCargaHoraria.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        lblCargaHoraria.setText("60 horas");

        lblTitleRanking.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblTitleRanking.setText("Ranking de professores");

        btnPerfilProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone professor.png"))); // NOI18N
        btnPerfilProfessor.setText("Perfil do professor");
        btnPerfilProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilProfessorActionPerformed(evt);
            }
        });

        btnAvaliarProfessor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-avaliacao/nova-avaliacao.png"))); // NOI18N
        btnAvaliarProfessor.setText("Avaliar");
        btnAvaliarProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvaliarProfessorActionPerformed(evt);
            }
        });

        btnAdicionarGrade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone adicionar.png"))); // NOI18N
        btnAdicionarGrade.setText("Adicionar a grade");
        btnAdicionarGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarGradeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBtnsRankingLayout = new javax.swing.GroupLayout(pnlBtnsRanking);
        pnlBtnsRanking.setLayout(pnlBtnsRankingLayout);
        pnlBtnsRankingLayout.setHorizontalGroup(
            pnlBtnsRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnsRankingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPerfilProfessor)
                .addGap(18, 18, 18)
                .addComponent(btnAvaliarProfessor)
                .addGap(18, 18, 18)
                .addComponent(btnAdicionarGrade)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBtnsRankingLayout.setVerticalGroup(
            pnlBtnsRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBtnsRankingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBtnsRankingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPerfilProfessor)
                    .addComponent(btnAvaliarProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdicionarGrade))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblRanking.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Professor", "Horário", "Avaliação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRanking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRankingMouseClicked(evt);
            }
        });
        scrllpnlRanking.setViewportView(tblRanking);
        if (tblRanking.getColumnModel().getColumnCount() > 0) {
            tblRanking.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblRanking.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblRanking.getColumnModel().getColumn(1).setMaxWidth(70);
        }

        javax.swing.GroupLayout pnlRankingProfessoresLayout = new javax.swing.GroupLayout(pnlRankingProfessores);
        pnlRankingProfessores.setLayout(pnlRankingProfessoresLayout);
        pnlRankingProfessoresLayout.setHorizontalGroup(
            pnlRankingProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRankingProfessoresLayout.createSequentialGroup()
                .addComponent(scrllpnlRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRankingProfessoresLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlRankingProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRankingProfessoresLayout.createSequentialGroup()
                        .addComponent(lblTitleRanking)
                        .addGap(166, 166, 166))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRankingProfessoresLayout.createSequentialGroup()
                        .addComponent(pnlBtnsRanking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        pnlRankingProfessoresLayout.setVerticalGroup(
            pnlRankingProfessoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRankingProfessoresLayout.createSequentialGroup()
                .addComponent(lblTitleRanking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrllpnlRanking, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBtnsRanking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDadosDisciplinaLayout = new javax.swing.GroupLayout(pnlDadosDisciplina);
        pnlDadosDisciplina.setLayout(pnlDadosDisciplinaLayout);
        pnlDadosDisciplinaLayout.setHorizontalGroup(
            pnlDadosDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosDisciplinaLayout.createSequentialGroup()
                .addGroup(pnlDadosDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleNomeDisciplina)
                    .addComponent(lbNomeDisciplina)
                    .addComponent(lblTitleDepartamento)
                    .addComponent(lblDepartamentoDisciplina)
                    .addComponent(lblTitleCodigo)
                    .addComponent(lblCodigo)
                    .addComponent(lblTitleCarga)
                    .addComponent(lblCargaHoraria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(pnlRankingProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        pnlDadosDisciplinaLayout.setVerticalGroup(
            pnlDadosDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosDisciplinaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDadosDisciplinaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRankingProfessores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDadosDisciplinaLayout.createSequentialGroup()
                        .addComponent(lblTitleNomeDisciplina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNomeDisciplina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTitleDepartamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDepartamentoDisciplina)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTitleCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTitleCarga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCargaHoraria)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblImagemPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/pesquisa.png"))); // NOI18N
        lblImagemPesquisa.setText("jLabel3");

        txtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisaActionPerformed(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyPressed(evt);
            }
        });

        btngrpPesquisa.add(rdbPesquisaProfessor);
        rdbPesquisaProfessor.setText("Professor");
        rdbPesquisaProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbPesquisaProfessorActionPerformed(evt);
            }
        });

        btngrpPesquisa.add(rdbDisciplina);
        rdbDisciplina.setText("Disciplina");
        rdbDisciplina.setToolTipText("Pesquise usando o código da disciplina");
        rdbDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDisciplinaActionPerformed(evt);
            }
        });

        btnPesquisar.setMnemonic('P');
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        cmbPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome" }));

        javax.swing.GroupLayout pnlPesquisaLayout = new javax.swing.GroupLayout(pnlPesquisa);
        pnlPesquisa.setLayout(pnlPesquisaLayout);
        pnlPesquisaLayout.setHorizontalGroup(
            pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPesquisaLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(lblImagemPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rdbPesquisaProfessor)
                .addGap(18, 18, 18)
                .addGroup(pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbDisciplina))
                .addGap(18, 18, 18)
                .addComponent(btnPesquisar)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlPesquisaLayout.setVerticalGroup(
            pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblImagemPesquisa)
                    .addComponent(rdbPesquisaProfessor)
                    .addComponent(rdbDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPesquisar))
                .addGap(0, 0, 0)
                .addComponent(cmbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblGrade.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        lblGrade.setText("Grade horária");

        tblGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"08:00 - 08:45", "", null, null, null, null},
                {"08:55 - 09:50", null, null, null, null, null},
                {"10:00 - 10:55", null, null, null, null, null},
                {"10:55 - 11:50", null, null, null, null, null},
                {"12:00 - 12:55", null, null, null, null, null},
                {"12:55 - 13:50", null, null, null, null, null},
                {"14:00 - 14:55", null, null, null, null, null},
                {"14:55 - 15:50", null, null, null, null, null},
                {"16:00 - 16:55", null, null, null, null, null},
                {"16:55  - 17:50", null, null, null, null, null},
                {"19: 00 - 19:50", null, null, null, null, null},
                {"19:50 - 20:40", null, null, null, null, null},
                {"20:50 - 21:40", null, null, null, null, null},
                {"21:40 - 22:30", null, null, null, null, null}
            },
            new String [] {
                "-", "Segunda", "Terça", "Quarta", "Quinta", "Sexta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGrade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGradeMouseClicked(evt);
            }
        });
        scrlpnlGrade.setViewportView(tblGrade);
        if (tblGrade.getColumnModel().getColumnCount() > 0) {
            tblGrade.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        btnTelaInicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-aluno/de-volta.png"))); // NOI18N
        btnTelaInicial.setText("Tela Inicial");
        btnTelaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTelaInicialActionPerformed(evt);
            }
        });

        btnPerfilDisciplina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/perfil disciplina icon.png"))); // NOI18N
        btnPerfilDisciplina.setText("Perfil da disciplina");
        btnPerfilDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilDisciplinaActionPerformed(evt);
            }
        });

        lblTitleDisciplinaEscolhida.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblTitleDisciplinaEscolhida.setText("Disciplina escolhida:");

        lblDisciplinaEscolhida.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N

        lblTitleHoras.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        lblTitleHoras.setText("Total de horas:");

        lblHoras.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N

        btnSalvarGrade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone salvar grade.png"))); // NOI18N
        btnSalvarGrade.setText("Salvar grade");
        btnSalvarGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarGradeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotoesGradeLayout = new javax.swing.GroupLayout(pnlBotoesGrade);
        pnlBotoesGrade.setLayout(pnlBotoesGradeLayout);
        pnlBotoesGradeLayout.setHorizontalGroup(
            pnlBotoesGradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotoesGradeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTelaInicial)
                .addGap(18, 18, 18)
                .addComponent(btnSalvarGrade)
                .addGap(18, 18, 18)
                .addComponent(btnPerfilDisciplina)
                .addGap(18, 18, 18)
                .addGroup(pnlBotoesGradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBotoesGradeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblDisciplinaEscolhida, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBotoesGradeLayout.createSequentialGroup()
                        .addComponent(lblTitleDisciplinaEscolhida)
                        .addGap(18, 18, 18)
                        .addComponent(lblTitleHoras)
                        .addContainerGap(38, Short.MAX_VALUE))))
        );
        pnlBotoesGradeLayout.setVerticalGroup(
            pnlBotoesGradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlBotoesGradeLayout.createSequentialGroup()
                .addGroup(pnlBotoesGradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleHoras)
                    .addComponent(lblTitleDisciplinaEscolhida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBotoesGradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHoras, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addComponent(lblDisciplinaEscolhida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBotoesGradeLayout.createSequentialGroup()
                .addGroup(pnlBotoesGradeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPerfilDisciplina)
                    .addComponent(btnTelaInicial)
                    .addComponent(btnSalvarGrade))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/disciplina/icone remover.png"))); // NOI18N
        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGradeHoráriaLayout = new javax.swing.GroupLayout(pnlGradeHorária);
        pnlGradeHorária.setLayout(pnlGradeHoráriaLayout);
        pnlGradeHoráriaLayout.setHorizontalGroup(
            pnlGradeHoráriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGradeHoráriaLayout.createSequentialGroup()
                .addGroup(pnlGradeHoráriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGradeHoráriaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlBotoesGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGradeHoráriaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(scrlpnlGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGradeHoráriaLayout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(lblGrade)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGradeHoráriaLayout.setVerticalGroup(
            pnlGradeHoráriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGradeHoráriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGrade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrlpnlGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGradeHoráriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBotoesGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlGradeHorária, javax.swing.GroupLayout.PREFERRED_SIZE, 880, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDadosDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDadosDisciplina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlGradeHorária, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdbPesquisaProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbPesquisaProfessorActionPerformed
        pesquisaProfessor = true;   
        pesquisaDisciplina= false;
        cmbPesquisa.setEnabled(false);// TODO add your handling code here:
    }//GEN-LAST:event_rdbPesquisaProfessorActionPerformed

    private void txtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisaActionPerformed
                // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaActionPerformed

    private void btnAvaliarProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvaliarProfessorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvaliarProfessorActionPerformed

    private void btnAdicionarGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarGradeActionPerformed
        if (horasGrade+disciplinaTela.getHoras()<=450){ 
            ajustarHorario(professorSelecionado.getHorario(disciplinaTela), disciplinaTela.getCodigo());
            tblGrade.clearSelection();
            lblHoras.setText(String.valueOf(horasGrade));
        }
        else{
            JOptionPane.showMessageDialog(null, "Máximo de horas atingido");
        }
                // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicionarGradeActionPerformed

    private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyPressed

    }//GEN-LAST:event_txtPesquisaKeyPressed

    private void rdbDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDisciplinaActionPerformed
        pesquisaDisciplina= true;  
        pesquisaProfessor= false;
        cmbPesquisa.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_rdbDisciplinaActionPerformed

    private void tblRankingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRankingMouseClicked
        int linha = tblRanking.getSelectedRow();
        if (linha>=0 && linha <tblRanking.getRowCount()){
            professorSelecionado = disciplinaTela.getListaProfessores().get(linha);
           
            btnAdicionarGrade.setEnabled(true);
            btnAvaliarProfessor.setEnabled(true);   
            btnPerfilProfessor.setEnabled(true);
        }// TODO add your handling code here:
    }//GEN-LAST:event_tblRankingMouseClicked

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        if (txtPesquisa.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Nada foi digitado para a pesquisa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else if (!pesquisaProfessor && !pesquisaDisciplina){
            JOptionPane.showMessageDialog(null, "Selecione uma opção de pesquisa", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if (pesquisaDisciplina){
                String disciplinaProcurada = txtPesquisa.getText().toLowerCase();
                boolean achou = false;
                if (cmbPesquisa.getSelectedIndex()==0){
                    for (Disciplina disc : listaDisciplinas){
                        if (disc.getCodigo().toLowerCase().equals(disciplinaProcurada)){

                            new telaDisciplina(disc,alunoTela).setVisible(true);
                            
                            //procuraDisciplina.criaListaDisciplina(disciplinaTela);
                            //carregaInformacoes();
                            achou=true;
                            break;
                        } 
                    }
                }
                else if (cmbPesquisa.getSelectedIndex()==1){
                   for (Disciplina disc : listaDisciplinas){
                        if (disc.getNome().toLowerCase().equals(disciplinaProcurada)){
                            //disciplinaTela=disc;
                            //procuraDisciplina.criaListaDisciplina(disciplinaTela);
                            new telaDisciplina(disc,alunoTela).setVisible(true);
                            dispose();
                            achou=true;
                            break;
                        } 
                    }  
                }
                if (achou){
                    /*ProfessorDAO procurarProfessor = new ProfessorDAO();
                    for (Professor prof : disciplinaTela.getListaProfessores()){
                        procurarProfessor.addHorario(disciplinaTela, prof);
                        }

                    carregaInformacoes();
                    carregarTabelaProfessores();*/
                }
                else {
                    JOptionPane.showMessageDialog(null, "Não há nenhuma disciplina com esse código", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }
                txtPesquisa.setText("");
            }
            else if (pesquisaProfessor){
                boolean achou=false;
                ProfessorDAO procuraProfessor = new ProfessorDAO();
                List <Professor> listaProfessores = procuraProfessor.read();
                String nomeProfessor= txtPesquisa.getText();
                for (Professor prof : listaProfessores){
                    if (prof.getNome().equals(nomeProfessor)){
                        Professor professorPesquisado=prof;
                        achou = true;
                        new telaProfessor(professorPesquisado,alunoTela).setVisible(true);
                        this.setVisible(false);
                        dispose();
                    }
                }
                if (!achou){
                    JOptionPane.showMessageDialog(null, "Não há nenhum professor com esse nome","Professor não encontrado", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnPerfilProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilProfessorActionPerformed

        int linha = tblRanking.getSelectedRow();
        if (linha>=0 && linha <tblRanking.getRowCount()){
            Professor professorPesquisado = disciplinaTela.getListaProfessores().get(linha);
            new telaProfessor(professorPesquisado,alunoTela).setVisible(true);
            this.setVisible(false);
            dispose();
        }
    }//GEN-LAST:event_btnPerfilProfessorActionPerformed

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
        JOptionPane.showMessageDialog(null, listaHorariosDisciplinas);
        disciplinasAdicionadasGrade.remove(celula);
        horasGrade-= procuraDisciplina.achaDisciplina(celula).getHoras();
        }
        lblHoras.setText(String.valueOf(horasGrade));
        lblDisciplinaEscolhida.setText("");
         carregarTabelaGrade();
        }        // TODO add your handling code here:
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
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblGradeMouseClicked

    private void btnPerfilDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilDisciplinaActionPerformed
       int indice= tblGrade.getSelectedRow();
        int coluna = tblGrade.getSelectedColumn();
        if (coluna>0){
            String celula = (String) tblGrade.getValueAt(indice, coluna);
            if (!celula.equals("")){
                Disciplina disc = procuraDisciplina.achaDisciplina(celula); 
                new telaDisciplina(disc,alunoTela).setVisible(true);
                dispose();                          
                disciplinaTela = disc;
                procuraDisciplina.criaListaDisciplina(disciplinaTela);
                carregaInformacoes();
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnPerfilDisciplinaActionPerformed

    private void btnTelaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTelaInicialActionPerformed
        this.setVisible(false);
        new TelaAluno(alunoTela).setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnTelaInicialActionPerformed

    private void btnSalvarGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarGradeActionPerformed
        GradeDAO salvarGrade = new GradeDAO();
        String atualizarGrade= String.join(",", listaHorariosDisciplinas);
        salvarGrade.update(atualizarGrade, this.horasGrade, 8);
        JOptionPane.showMessageDialog(null, "Grade salva com sucesso");        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarGradeActionPerformed

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
            java.util.logging.Logger.getLogger(telaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaDisciplina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarGrade;
    private javax.swing.JButton btnAvaliarProfessor;
    private javax.swing.JButton btnPerfilDisciplina;
    private javax.swing.JButton btnPerfilProfessor;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvarGrade;
    private javax.swing.JButton btnTelaInicial;
    private javax.swing.ButtonGroup btngrpPesquisa;
    private javax.swing.JComboBox<String> cmbPesquisa;
    private javax.swing.JLabel lbNomeDisciplina;
    private javax.swing.JLabel lblCargaHoraria;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDepartamentoDisciplina;
    private javax.swing.JLabel lblDisciplinaEscolhida;
    private javax.swing.JLabel lblGrade;
    private javax.swing.JLabel lblHoras;
    private javax.swing.JLabel lblImagemPesquisa;
    private javax.swing.JLabel lblTitleCarga;
    private javax.swing.JLabel lblTitleCodigo;
    private javax.swing.JLabel lblTitleDepartamento;
    private javax.swing.JLabel lblTitleDisciplinaEscolhida;
    private javax.swing.JLabel lblTitleHoras;
    private javax.swing.JLabel lblTitleNomeDisciplina;
    private javax.swing.JLabel lblTitleRanking;
    private javax.swing.JPanel pnlBotoesGrade;
    private javax.swing.JPanel pnlBtnsRanking;
    private javax.swing.JPanel pnlDadosDisciplina;
    private javax.swing.JPanel pnlGradeHorária;
    private javax.swing.JPanel pnlPesquisa;
    private javax.swing.JPanel pnlRankingProfessores;
    private javax.swing.JRadioButton rdbDisciplina;
    private javax.swing.JRadioButton rdbPesquisaProfessor;
    private javax.swing.JScrollPane scrllpnlRanking;
    private javax.swing.JScrollPane scrlpnlGrade;
    private javax.swing.JTable tblGrade;
    private javax.swing.JTable tblRanking;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
