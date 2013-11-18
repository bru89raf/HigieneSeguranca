/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulocontrresultados;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class JanelaPrincipal extends javax.swing.JFrame {

    String url = "jdbc:derby://localhost:1527/controloResultados";
    Connection con;
    DefaultTableModel model;
    String sql;
    String NomeTabelaContrResultado;
    
    String comboTipoOP = ""; // guardar a opçao da ComboBox TIPO
    String comboSeccaoOP = ""; // guardar a opção da  comboBoc Opção (Controlo resultados)
    
    /* VARIAVEIS PARA O IDCONTROLORESULTADO, PARA INSERIR OS DADOS NA TABELA DAS NAO CONFORMIDADES */
    int idControloResultadosID = 0;
    
    /* ID DO FUNCIONARIO QUE ESTAMOS A SELECCIONAR PARA VER A NAO CONFORMIDADE  */
    int idFuncionarioNaoConformidade = 0;
    
    /*  VARIAVEIS PARA ADICIONAR UMA NOVA NAO CONFORMIDADE A UM CONTROLO DE RESULTADOS  */
    int addNewNaoConformidadeIdFuncionario = 0;
    int addNewNaoConformidadeIDContrResultado = 0;
    
    
    /* JANELA MEDIDAS CORRECTIVIAS */
    int idNaoConformidadeUpdate = 0;
    
    /* SABER QUANTOS REGISTO TEMOS POR NAO CONFORMIDADE, PARA SABER SE É O ULTIMO E 
     * ACTUALIZAR A TABELA DE CONTROLO DE RESULTADOS */
    int contaNaoConformidade_MedidasCorrectivas = 0;
    int idContResultadosUltimo = 0; //CHAVE ESTRANGUEIRA(CONTROLO RESULTADOS) QUE ESTA NA TABELA NAO CONFORMIDADES
    
    
    
    /************************************************/
    
    public JanelaPrincipal() {
        initComponents();
                
        
        /*CARREGAMENTO DOS CAMPOS PARA A COMBO BOX */
    
    
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogNovoControloResultados = new javax.swing.JDialog();
        jPanelNovoControloResultdos = new javax.swing.JPanel();
        jComboBoxTipo = new javax.swing.JComboBox();
        jComboBoxOpcao = new javax.swing.JComboBox();
        jLabelTipo = new javax.swing.JLabel();
        jLabelOpcao = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelResultado = new javax.swing.JLabel();
        jLabelFichaTecnica = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jComboBoxResultado = new javax.swing.JComboBox();
        jScrollPaneDescricao = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonInserir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelOutraOpcao = new javax.swing.JPanel();
        jLabelNomeOutraOpcao = new javax.swing.JLabel();
        jTextFieldOutraOpcao = new javax.swing.JTextField();
        jDateChooserControloResultados = new com.toedter.calendar.JDateChooser();
        jLabelFuncionario = new javax.swing.JLabel();
        jComboBoxFuncionario = new javax.swing.JComboBox();
        jDialogNaoConformidades = new javax.swing.JDialog();
        jPanelNaoConformidades = new javax.swing.JPanel();
        jLabelDataNaoConformidade = new javax.swing.JLabel();
        jLabelOcorrecniaNaoConformidade = new javax.swing.JLabel();
        jLabelMedidaCorrectivaNaoConformidade = new javax.swing.JLabel();
        jScrollPaneOcorrencia = new javax.swing.JScrollPane();
        jTextAreaOcorrenciaNaoConformidade = new javax.swing.JTextArea();
        jScrollPanemedidaCorrectiva = new javax.swing.JScrollPane();
        jTextAreaMedidaCorrctivaNaoConformidade = new javax.swing.JTextArea();
        jButtonGravarNaoConformidade = new javax.swing.JButton();
        jButtonCancelarNaoConformidade = new javax.swing.JButton();
        jDateChooserNaoConformidade = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxNaoConformidadeFuncionarioResponsav = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jDialogMedidasCorrectiva = new javax.swing.JDialog();
        jLabelMedidasCorrectivas = new javax.swing.JLabel();
        jPanelMedidasCorrectivas = new javax.swing.JPanel();
        jLabelDataMedidasCorrectivas = new javax.swing.JLabel();
        jLabelObservacaoMedidasCorrectivas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacaoMedidaCorrectiva = new javax.swing.JTextArea();
        jDateChooserMedidaCorrectiva = new com.toedter.calendar.JDateChooser();
        jButtonGravarMedidaCorrectiva = new javax.swing.JButton();
        jButtonCancelarMedidaCorrectiva = new javax.swing.JButton();
        jDialogConsultarControlos = new javax.swing.JDialog();
        jPanelConsultaControlos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPaneConsultarControlos = new javax.swing.JScrollPane();
        jTableConsultarControlos = new javax.swing.JTable();
        jButtonFecharConsultaControlosResultados = new javax.swing.JButton();
        jButtonCriaNovoControloResultados = new javax.swing.JButton();
        jButtonVerNaoConformidade = new javax.swing.JButton();
        jComboBoxSeccao = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jDialogConsultaNaoConformidades = new javax.swing.JDialog();
        jPanelConsultaNaoConformidades = new javax.swing.JPanel();
        jLabelNaoConformidades = new javax.swing.JLabel();
        jScrollPaneNaoConformidades = new javax.swing.JScrollPane();
        jTableNaoConformidades = new javax.swing.JTable();
        jButtonVoltar = new javax.swing.JButton();
        jButtonAddNovaNaoConformidade = new javax.swing.JButton();
        jButtonAddMedidaCorrectiva = new javax.swing.JButton();
        jDialogConsultarMedidasCorrectivas = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabelConsultaMedidasCorrectivas = new javax.swing.JLabel();
        jScrollPaneConsultaMedidasCorrectivas = new javax.swing.JScrollPane();
        jTableConsultaMedidasCorrectivas = new javax.swing.JTable();
        jButtonVoltarMedidasCorrectivas = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonNovoControlo = new javax.swing.JButton();
        jButtonControlos = new javax.swing.JButton();
        jButtonNaoConformidades = new javax.swing.JButton();
        jButtonConsultarMedidasCorrectivas = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jDialogNovoControloResultados.setTitle("NOVO CONTROLO DE RESULTADOS");
        jDialogNovoControloResultados.setMinimumSize(new java.awt.Dimension(548, 740));

        jPanelNovoControloResultdos.setBorder(javax.swing.BorderFactory.createTitledBorder("Controlo de Resultados"));

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- TIPO--", "INSECTOCACADORES", "EQUIPAMENTO", "ENTRADA", "Outro" }));
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });

        jComboBoxOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOpcaoActionPerformed(evt);
            }
        });

        jLabelTipo.setText("Tipo");

        jLabelOpcao.setText("Opção");

        jLabelData.setText("Data");

        jLabelResultado.setText("Resultado");

        jLabelFichaTecnica.setText("Ficha Tecnica");

        jLabelDescricao.setText("Descrição");

        jComboBoxResultado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "C", "NC" }));
        jComboBoxResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxResultadoActionPerformed(evt);
            }
        });

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPaneDescricao.setViewportView(jTextAreaDescricao);

        jLabel2.setText("C - Conforme | NC - Não Conforme");

        jLabel3.setText("UPLOAD");

        jButtonInserir.setText("Inserir");
        jButtonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabelNomeOutraOpcao.setText("Nome");

        javax.swing.GroupLayout jPanelOutraOpcaoLayout = new javax.swing.GroupLayout(jPanelOutraOpcao);
        jPanelOutraOpcao.setLayout(jPanelOutraOpcaoLayout);
        jPanelOutraOpcaoLayout.setHorizontalGroup(
            jPanelOutraOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOutraOpcaoLayout.createSequentialGroup()
                .addComponent(jLabelNomeOutraOpcao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldOutraOpcao, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
        );
        jPanelOutraOpcaoLayout.setVerticalGroup(
            jPanelOutraOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOutraOpcaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelOutraOpcaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeOutraOpcao)
                    .addComponent(jTextFieldOutraOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDateChooserControloResultados.setDateFormatString("yyyy-MM-dd");

        jLabelFuncionario.setText("Funcionario");

        jComboBoxFuncionario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelNovoControloResultdosLayout = new javax.swing.GroupLayout(jPanelNovoControloResultdos);
        jPanelNovoControloResultdos.setLayout(jPanelNovoControloResultdosLayout);
        jPanelNovoControloResultdosLayout.setHorizontalGroup(
            jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelResultado)
                            .addComponent(jLabelFichaTecnica)
                            .addComponent(jLabelData)
                            .addComponent(jLabelDescricao))
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3))
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                        .addComponent(jButtonInserir)
                                        .addGap(60, 60, 60)
                                        .addComponent(jButtonCancelar))
                                    .addComponent(jLabel2)
                                    .addComponent(jDateChooserControloResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPaneDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTipo)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelOutraOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelOpcao)
                            .addComponent(jComboBoxOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelFuncionario)
                    .addComponent(jComboBoxFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanelNovoControloResultdosLayout.setVerticalGroup(
            jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipo)
                    .addComponent(jLabelOpcao))
                .addGap(4, 4, 4)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelOutraOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelData)
                    .addComponent(jDateChooserControloResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResultado)
                    .addComponent(jComboBoxResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFichaTecnica)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescricao)
                    .addComponent(jScrollPaneDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInserir)
                    .addComponent(jButtonCancelar))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jDialogNovoControloResultadosLayout = new javax.swing.GroupLayout(jDialogNovoControloResultados.getContentPane());
        jDialogNovoControloResultados.getContentPane().setLayout(jDialogNovoControloResultadosLayout);
        jDialogNovoControloResultadosLayout.setHorizontalGroup(
            jDialogNovoControloResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoControloResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovoControloResultdos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jDialogNovoControloResultadosLayout.setVerticalGroup(
            jDialogNovoControloResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoControloResultadosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanelNovoControloResultdos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jDialogNaoConformidades.setTitle("NOVA NÃO CONFORMIDADE");
        jDialogNaoConformidades.setMinimumSize(new java.awt.Dimension(435, 670));

        jPanelNaoConformidades.setMinimumSize(new java.awt.Dimension(200, 150));

        jLabelDataNaoConformidade.setText("Data");

        jLabelOcorrecniaNaoConformidade.setText("Ocorrencia");

        jLabelMedidaCorrectivaNaoConformidade.setText("Medida Correctiva");

        jTextAreaOcorrenciaNaoConformidade.setColumns(20);
        jTextAreaOcorrenciaNaoConformidade.setRows(5);
        jTextAreaOcorrenciaNaoConformidade.setPreferredSize(new java.awt.Dimension(164, 100));
        jScrollPaneOcorrencia.setViewportView(jTextAreaOcorrenciaNaoConformidade);

        jTextAreaMedidaCorrctivaNaoConformidade.setColumns(20);
        jTextAreaMedidaCorrctivaNaoConformidade.setRows(5);
        jScrollPanemedidaCorrectiva.setViewportView(jTextAreaMedidaCorrctivaNaoConformidade);

        jButtonGravarNaoConformidade.setText("Guardar");
        jButtonGravarNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarNaoConformidadeActionPerformed(evt);
            }
        });

        jButtonCancelarNaoConformidade.setText("Cancelar");
        jButtonCancelarNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarNaoConformidadeActionPerformed(evt);
            }
        });

        jDateChooserNaoConformidade.setDateFormatString("yyyy-MM-dd");

        jLabel6.setText("Funcionario Responsavel");

        jComboBoxNaoConformidadeFuncionarioResponsav.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelNaoConformidadesLayout = new javax.swing.GroupLayout(jPanelNaoConformidades);
        jPanelNaoConformidades.setLayout(jPanelNaoConformidadesLayout);
        jPanelNaoConformidadesLayout.setHorizontalGroup(
            jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPanemedidaCorrectiva, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelMedidaCorrectivaNaoConformidade))
                            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jScrollPaneOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                                        .addComponent(jLabelDataNaoConformidade)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateChooserNaoConformidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabelOcorrecniaNaoConformidade)))))
                    .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                                .addComponent(jButtonGravarNaoConformidade)
                                .addGap(28, 28, 28)
                                .addComponent(jButtonCancelarNaoConformidade))
                            .addComponent(jComboBoxNaoConformidadeFuncionarioResponsav, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelNaoConformidadesLayout.setVerticalGroup(
            jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDataNaoConformidade)
                    .addComponent(jDateChooserNaoConformidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jLabelOcorrecniaNaoConformidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelMedidaCorrectivaNaoConformidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanemedidaCorrectiva, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxNaoConformidadeFuncionarioResponsav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGravarNaoConformidade)
                    .addComponent(jButtonCancelarNaoConformidade))
                .addGap(67, 67, 67))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("NÃO CONFORMIDADE");

        javax.swing.GroupLayout jDialogNaoConformidadesLayout = new javax.swing.GroupLayout(jDialogNaoConformidades.getContentPane());
        jDialogNaoConformidades.getContentPane().setLayout(jDialogNaoConformidadesLayout);
        jDialogNaoConformidadesLayout.setHorizontalGroup(
            jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                .addGroup(jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel4))
                    .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanelNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jDialogNaoConformidadesLayout.setVerticalGroup(
            jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jDialogMedidasCorrectiva.setTitle("NOVA MEDIDA CORRECTIVA");
        jDialogMedidasCorrectiva.setMinimumSize(new java.awt.Dimension(500, 600));

        jLabelMedidasCorrectivas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelMedidasCorrectivas.setText("Medidas Correctivas");

        jLabelDataMedidasCorrectivas.setText("Data");

        jLabelObservacaoMedidasCorrectivas.setText("Observação");

        jScrollPane1.setMinimumSize(new java.awt.Dimension(200, 150));

        jTextAreaObservacaoMedidaCorrectiva.setColumns(20);
        jTextAreaObservacaoMedidaCorrectiva.setRows(5);
        jScrollPane1.setViewportView(jTextAreaObservacaoMedidaCorrectiva);

        jButtonGravarMedidaCorrectiva.setText("Gravar");
        jButtonGravarMedidaCorrectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarMedidaCorrectivaActionPerformed(evt);
            }
        });

        jButtonCancelarMedidaCorrectiva.setText("Cancelar");
        jButtonCancelarMedidaCorrectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarMedidaCorrectivaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMedidasCorrectivasLayout = new javax.swing.GroupLayout(jPanelMedidasCorrectivas);
        jPanelMedidasCorrectivas.setLayout(jPanelMedidasCorrectivasLayout);
        jPanelMedidasCorrectivasLayout.setHorizontalGroup(
            jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                .addGroup(jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButtonGravarMedidaCorrectiva)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonCancelarMedidaCorrectiva))
                    .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabelDataMedidasCorrectivas)
                        .addGap(31, 31, 31)
                        .addComponent(jDateChooserMedidaCorrectiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMedidasCorrectivasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelObservacaoMedidasCorrectivas))
                .addGap(59, 59, 59))
        );
        jPanelMedidasCorrectivasLayout.setVerticalGroup(
            jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDataMedidasCorrectivas)
                    .addComponent(jDateChooserMedidaCorrectiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jLabelObservacaoMedidasCorrectivas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGravarMedidaCorrectiva)
                    .addComponent(jButtonCancelarMedidaCorrectiva))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogMedidasCorrectivaLayout = new javax.swing.GroupLayout(jDialogMedidasCorrectiva.getContentPane());
        jDialogMedidasCorrectiva.getContentPane().setLayout(jDialogMedidasCorrectivaLayout);
        jDialogMedidasCorrectivaLayout.setHorizontalGroup(
            jDialogMedidasCorrectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMedidasCorrectivaLayout.createSequentialGroup()
                .addGroup(jDialogMedidasCorrectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogMedidasCorrectivaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanelMedidasCorrectivas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogMedidasCorrectivaLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabelMedidasCorrectivas)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jDialogMedidasCorrectivaLayout.setVerticalGroup(
            jDialogMedidasCorrectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMedidasCorrectivaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelMedidasCorrectivas)
                .addGap(34, 34, 34)
                .addComponent(jPanelMedidasCorrectivas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jDialogConsultarControlos.setTitle("CONSULTAR CONTROLOS DE RESULTADOS");
        jDialogConsultarControlos.setMinimumSize(new java.awt.Dimension(1040, 492));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Consultar Controlos");

        jTableConsultarControlos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionario", "Secção", "Data", "Resultado", "Ficha Tecnica", "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultarControlos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultarControlosMouseClicked(evt);
            }
        });
        jScrollPaneConsultarControlos.setViewportView(jTableConsultarControlos);

        jButtonFecharConsultaControlosResultados.setText("Cancelar");
        jButtonFecharConsultaControlosResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharConsultaControlosResultadosActionPerformed(evt);
            }
        });

        jButtonCriaNovoControloResultados.setText("Criar Novo");
        jButtonCriaNovoControloResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriaNovoControloResultadosActionPerformed(evt);
            }
        });

        jButtonVerNaoConformidade.setText("Ver Não Conformidade");
        jButtonVerNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerNaoConformidadeActionPerformed(evt);
            }
        });

        jComboBoxSeccao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Sessão--", "ANALISE", "EQUIPAMENTO", "INSECTOCACADORES", "OUTROS" }));
        jComboBoxSeccao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSeccaoActionPerformed(evt);
            }
        });

        jLabel7.setText("Secção");

        javax.swing.GroupLayout jPanelConsultaControlosLayout = new javax.swing.GroupLayout(jPanelConsultaControlos);
        jPanelConsultaControlos.setLayout(jPanelConsultaControlosLayout);
        jPanelConsultaControlosLayout.setHorizontalGroup(
            jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneConsultarControlos)
                    .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                        .addComponent(jButtonCriaNovoControloResultados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonFecharConsultaControlosResultados)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxSeccao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVerNaoConformidade)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaControlosLayout.createSequentialGroup()
                .addContainerGap(415, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(397, 397, 397))
        );
        jPanelConsultaControlosLayout.setVerticalGroup(
            jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonVerNaoConformidade, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxSeccao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneConsultarControlos, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFecharConsultaControlosResultados)
                    .addComponent(jButtonCriaNovoControloResultados))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultarControlosLayout = new javax.swing.GroupLayout(jDialogConsultarControlos.getContentPane());
        jDialogConsultarControlos.getContentPane().setLayout(jDialogConsultarControlosLayout);
        jDialogConsultarControlosLayout.setHorizontalGroup(
            jDialogConsultarControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultarControlosLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanelConsultaControlos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogConsultarControlosLayout.setVerticalGroup(
            jDialogConsultarControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultarControlosLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanelConsultaControlos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogConsultaNaoConformidades.setTitle("CONSULTAR NÃO CONFORMIDADES");
        jDialogConsultaNaoConformidades.setMinimumSize(new java.awt.Dimension(840, 375));

        jLabelNaoConformidades.setText("CONSULTA NAO CONFORMIDADES");

        jTableNaoConformidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NC", "Funcionario", "Data(Ñ Confor)", "Ocorrencia", "Medida Correct.", "Func. Responsav."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneNaoConformidades.setViewportView(jTableNaoConformidades);

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jButtonAddNovaNaoConformidade.setText("Nova Não Conformidade");
        jButtonAddNovaNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNovaNaoConformidadeActionPerformed(evt);
            }
        });

        jButtonAddMedidaCorrectiva.setText("Medida Correctiva");
        jButtonAddMedidaCorrectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMedidaCorrectivaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultaNaoConformidadesLayout = new javax.swing.GroupLayout(jPanelConsultaNaoConformidades);
        jPanelConsultaNaoConformidades.setLayout(jPanelConsultaNaoConformidadesLayout);
        jPanelConsultaNaoConformidadesLayout.setHorizontalGroup(
            jPanelConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaNaoConformidadesLayout.createSequentialGroup()
                .addGroup(jPanelConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaNaoConformidadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPaneNaoConformidades, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
                    .addGroup(jPanelConsultaNaoConformidadesLayout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jLabelNaoConformidades)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanelConsultaNaoConformidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jButtonAddNovaNaoConformidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAddMedidaCorrectiva, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConsultaNaoConformidadesLayout.setVerticalGroup(
            jPanelConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaNaoConformidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNaoConformidades)
                .addGap(39, 39, 39)
                .addComponent(jScrollPaneNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVoltar)
                    .addComponent(jButtonAddNovaNaoConformidade)
                    .addComponent(jButtonAddMedidaCorrectiva))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultaNaoConformidadesLayout = new javax.swing.GroupLayout(jDialogConsultaNaoConformidades.getContentPane());
        jDialogConsultaNaoConformidades.getContentPane().setLayout(jDialogConsultaNaoConformidadesLayout);
        jDialogConsultaNaoConformidadesLayout.setHorizontalGroup(
            jDialogConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaNaoConformidadesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanelConsultaNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jDialogConsultaNaoConformidadesLayout.setVerticalGroup(
            jDialogConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaNaoConformidadesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanelConsultaNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogConsultarMedidasCorrectivas.setTitle("CONSULTAR MEDIDAS CORRECCTIVAS");
        jDialogConsultarMedidasCorrectivas.setMinimumSize(new java.awt.Dimension(740, 380));

        jLabelConsultaMedidasCorrectivas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelConsultaMedidasCorrectivas.setText("Consulta Medidas Correctivas");

        jTableConsultaMedidasCorrectivas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionario", "Data", "Correctiva", "Observações"
            }
        ));
        jScrollPaneConsultaMedidasCorrectivas.setViewportView(jTableConsultaMedidasCorrectivas);

        jButtonVoltarMedidasCorrectivas.setText("Voltar");
        jButtonVoltarMedidasCorrectivas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarMedidasCorrectivasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabelConsultaMedidasCorrectivas))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVoltarMedidasCorrectivas)
                            .addComponent(jScrollPaneConsultaMedidasCorrectivas, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConsultaMedidasCorrectivas)
                .addGap(31, 31, 31)
                .addComponent(jScrollPaneConsultaMedidasCorrectivas, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonVoltarMedidasCorrectivas)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultarMedidasCorrectivasLayout = new javax.swing.GroupLayout(jDialogConsultarMedidasCorrectivas.getContentPane());
        jDialogConsultarMedidasCorrectivas.getContentPane().setLayout(jDialogConsultarMedidasCorrectivasLayout);
        jDialogConsultarMedidasCorrectivasLayout.setHorizontalGroup(
            jDialogConsultarMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultarMedidasCorrectivasLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogConsultarMedidasCorrectivasLayout.setVerticalGroup(
            jDialogConsultarMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultarMedidasCorrectivasLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modulo Controlo Resultadosmo");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Controlo de Resultados");

        jButtonNovoControlo.setText("Novo Controlo");
        jButtonNovoControlo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoControloActionPerformed(evt);
            }
        });

        jButtonControlos.setText("Consultar Controlos");
        jButtonControlos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonControlosActionPerformed(evt);
            }
        });

        jButtonNaoConformidades.setText("Consultar Não Conformidades");
        jButtonNaoConformidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNaoConformidadesActionPerformed(evt);
            }
        });

        jButtonConsultarMedidasCorrectivas.setText("Consultar Medidas Correctivas");
        jButtonConsultarMedidasCorrectivas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarMedidasCorrectivasActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButtonNovoControlo))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonConsultarMedidasCorrectivas)
                            .addComponent(jButtonNaoConformidades)
                            .addComponent(jButtonControlos)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoControlo)
                    .addComponent(jButtonControlos))
                .addGap(18, 18, 18)
                .addComponent(jButtonNaoConformidades)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButtonConsultarMedidasCorrectivas)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(15, 15, 15)
                        .addComponent(jButton2))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoControloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoControloActionPerformed
        
        //ABRIR JANELA ESCOLHE
        jDialogNovoControloResultados.setLocationRelativeTo(this);
        jDialogNovoControloResultados.setVisible(true);
        
        //ESCONDER PANEL "OUTRA OPÇAO NOME"
        jTextFieldOutraOpcao.setEnabled(false);
        
        //COMBOBOX FUNCIONARIO CARREGAR DADOS
        LerBDFuncionario();
      
        
    }//GEN-LAST:event_jButtonNovoControloActionPerformed

    private void jComboBoxResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxResultadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxResultadoActionPerformed

    private void jButtonGravarNaoConformidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGravarNaoConformidadeActionPerformed
        // BOTAO GUARDAR E ABRIR JANELA DE MEDIDAS CORRECTIVAS
        //jDialogMedidasCorrectiva.setVisible(true);
       //GravarNaoConformidade(); 
       
       
       int resultNaoConformidade = JOptionPane.showConfirmDialog(jDialogNaoConformidades, "Deseja Abrir a Janela de Medidas Correctivas?", null, JOptionPane.YES_NO_OPTION);
        
        if (resultNaoConformidade == JOptionPane.YES_OPTION){
            GravarNaoConformidade();
            jDialogMedidasCorrectiva.setLocationRelativeTo(jDialogNaoConformidades);
            jDialogNaoConformidades.setVisible(false);
            jDialogMedidasCorrectiva.setVisible(true);
        } else if (resultNaoConformidade == JOptionPane.NO_OPTION){
            int resultNaoConformidadeGuardar = JOptionPane.showConfirmDialog(jDialogNaoConformidades, "Deseja Guardar os Dados e Voltar ao Menu Principal?", null, JOptionPane.YES_NO_OPTION);
            
            if (resultNaoConformidadeGuardar == JOptionPane.YES_OPTION){
                GravarNaoConformidade();
                jDialogNaoConformidades.setVisible(false);
            }
            
        }
       
       
    }//GEN-LAST:event_jButtonGravarNaoConformidadeActionPerformed

    private void jButtonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirActionPerformed
       //BOTAO INSERIR NOVA CONTROLO DE RESULTADOS
       InserirNovoControloResultados();     

        
    }//GEN-LAST:event_jButtonInserirActionPerformed

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        //VERIFICAR QUAL A OPÇAO QUE ESTAMOS A SELECCIONAR
        comboTipoOP = jComboBoxTipo.getSelectedItem().toString();
             
        if (comboTipoOP.equals("Outro")){ 
            System.out.println("TIPO : " + comboTipoOP);
            //BLOQUEAR COMBOBOX OPÇAO
            jComboBoxOpcao.setEnabled(false);
            //MOSTRAR TEXTFIEL
            jTextFieldOutraOpcao.setEnabled(true);
        }else if (comboTipoOP.equals("INSECTOCACADORES")){
            //Select a tabela dos insectadores
            LerBDInsectadores();
            
        }else if (comboTipoOP.equals("EQUIPAMENTO")){
            //SELECT A TABELA DE EQUIPAMENTOS
            LerBDEquipamento();
        }else if (comboTipoOP.equals("ENTRADA")){
            //SELECT A TABELA DE ENTRADAS
            
        }
       
        


    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void jButtonControlosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonControlosActionPerformed
        // ABRIR JANELA DE CONSULTAS DE RESULTADOS DE CONTROLO
        LimpaTabelaConsultarControlos();
        
        jComboBoxOpcao.setEnabled(true);
        
        jTextFieldOutraOpcao.setText("");
        jTextFieldOutraOpcao.setEnabled(true);
       
        jTextAreaDescricao.setText("");
        
        jDialogConsultarControlos.setLocationRelativeTo(this);
        jDialogConsultarControlos.setVisible(true);
        
    }//GEN-LAST:event_jButtonControlosActionPerformed

    private void jButtonFecharConsultaControlosResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharConsultaControlosResultadosActionPerformed
        // FECHAR JANELA CONSULTAR RESULTADOS
        jDialogConsultarControlos.setVisible(false);
    }//GEN-LAST:event_jButtonFecharConsultaControlosResultadosActionPerformed

    private void jTableConsultarControlosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultarControlosMouseClicked
        // QUANDO CARREGAMOS NA TABELA
        LimpaTabelaConsultaNaoConformidades();
//        LerBDConsultaNaoConformidades();
                
        
    }//GEN-LAST:event_jTableConsultarControlosMouseClicked

    private void jButtonNaoConformidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNaoConformidadesActionPerformed
        // JANELA CONSULTAR NAO CONFORMIDADES
        LimpaTabelaConsultaNaoConformidades();
        LerBDNaoConformidades();
        jDialogConsultaNaoConformidades.setLocationRelativeTo(this);
        jDialogConsultaNaoConformidades.setVisible(true);
        //ESCONDER BOTAO ADICIONAR NOVO
        jButtonAddNovaNaoConformidade.setVisible(false);
        
        
    }//GEN-LAST:event_jButtonNaoConformidadesActionPerformed

    private void jButtonCriaNovoControloResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriaNovoControloResultadosActionPerformed
        // CRIAR NOVA CONTROLO DE RESULTADOS
        
        //COMBOBOX FUNCIONARIO CARREGAR DADOS
        LerBDFuncionario();
        jDialogConsultarControlos.setVisible(false);
        jDialogNovoControloResultados.setLocationRelativeTo(this);
        jDialogNovoControloResultados.setVisible(true);
        
    }//GEN-LAST:event_jButtonCriaNovoControloResultadosActionPerformed

    private void jButtonVerNaoConformidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerNaoConformidadeActionPerformed
        // BOTAO PARA ABRIR O SELECCIONADA DA TABELA ( NAO CONFORMIDADES)
        int linha = jTableConsultarControlos.getSelectedRow();
        

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione a Linha do Controlo de Resulto ");
        } else {

            //DEVOLVE O NOME DO FUNCIONARIO
            String nomeFunc = (String) jTableConsultarControlos.getValueAt(linha, 0);
            //System.out.println("Nome: " + nomeFunc);

            String resultado = (String) jTableConsultarControlos.getValueAt(linha, 3);
           
            idFuncionarioNaoConformidade = 0;
            
            
            //VERIFICAR SE A LINHA QUE ESTAMOS A SELECCINAR, O RESILTADO É
            // C OU NC
            if (resultado.equals("NC")) {

                jDialogNovoControloResultados.setVisible(false);
                jDialogConsultaNaoConformidades.setVisible(true);
                jDialogConsultaNaoConformidades.setLocationRelativeTo(this);

                //DEVOLVE O ID DO FUNCIONARIO
                idFuncionarioNaoConformidade = selectId("FUNCIONARIO", "Nome", nomeFunc, "IDFUNCIONARIO");
                //System.out.println("IDFUNCIONARIO TABELA -> " + idFuncionario);
               

                //DEVOLVE O IDCONTRESULTADO ONDE O FUNCIONARIO É = AO QUE VEM DE CIMA
                LimpaTabelaConsultaNaoConformidades();
                ConsultaBDNaoConformidadeSelect(idFuncionarioNaoConformidade);
            } else {
                JOptionPane.showMessageDialog(null, "Linha Selecciona não contem Nehuma Não Conformidade ! ");
            }

        }
    }//GEN-LAST:event_jButtonVerNaoConformidadeActionPerformed

    private void jComboBoxOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOpcaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxOpcaoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        //BOTAO FECHAR A JANELA CONTROLO DE RESULTADOS
         jComboBoxOpcao.setEnabled(true);
        
        jTextFieldOutraOpcao.setText("");
        jTextFieldOutraOpcao.setEnabled(true);
        
        jDialogNovoControloResultados.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonGravarMedidaCorrectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGravarMedidaCorrectivaActionPerformed
        // BOTAO GUARDAR MEDIDA CORRETIVAS
      
        //SE FOR = 1 QUER DIZER QUE ESTA É A ULTIMA NAO CONFORMIDADE
        if (contaNaoConformidade_MedidasCorrectivas == 0){
           
            
        } else{//SE FOR DIFENTE DE 1 QUER DIZER QUE TEMOS MAIS NAO CONFORMIDADES
            ActualizaMedidaCorrectiva();
            //ATUALIZAR A TABELA NAO CONFORMIDADES
            LimpaTabelaConsultaNaoConformidades();
            ConsultaBDNaoConformidadeSelect(idFuncionarioNaoConformidade);
            
            jTextAreaObservacaoMedidaCorrectiva.setText("");
            jDialogMedidasCorrectiva.setVisible(false);
        }
        
       
        
        
        
        
    }//GEN-LAST:event_jButtonGravarMedidaCorrectivaActionPerformed

    private void jButtonConsultarMedidasCorrectivasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarMedidasCorrectivasActionPerformed
        // CONSULTAR MEDIDAS CORRECTIVAS
        LimpaTabelaConsultaMedidasCorrectivas();
       // LerBDConsultarMedidasCorrectivas();
        jDialogConsultarMedidasCorrectivas.setLocationRelativeTo(this);
        jDialogConsultarMedidasCorrectivas.setVisible(true);
    }//GEN-LAST:event_jButtonConsultarMedidasCorrectivasActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // BOTAO VOLTAR
        jDialogConsultaNaoConformidades.setVisible(false);
        LimpaTabelaConsultaNaoConformidades();
        
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonVoltarMedidasCorrectivasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarMedidasCorrectivasActionPerformed
        // BOTOA VOLTAR
        jDialogConsultarMedidasCorrectivas.setVisible(false);
        LimpaTabelaConsultaMedidasCorrectivas();
    }//GEN-LAST:event_jButtonVoltarMedidasCorrectivasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jDialogNaoConformidades.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jDialogMedidasCorrectiva.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxSeccaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSeccaoActionPerformed
        //QUAL A SESSAO QUE VAMOS CARREGAAR PARA  A TABELA
        String comboPesquisa = jComboBoxSeccao.getSelectedItem().toString();
        String nomeColuna = "";
        
        if (comboPesquisa.equals("ENTRADA")){
            LimpaTabelaConsultarControlos();
            nomeColuna = "IDENTRADA";
            LerBDControloResultados(nomeColuna);
            
        }else if (comboPesquisa.equals("EQUIPAMENTO")){
            LimpaTabelaConsultarControlos();
            nomeColuna = "IDEQUIPAMENTO";
            LerBDControloResultados(nomeColuna);
            
        }else if (comboPesquisa.equals("INSECTOCACADORES")){
            LimpaTabelaConsultarControlos();
            nomeColuna = "IDINSECTOCACADORES";
            LerBDControloResultados(nomeColuna);
            
            
        }else if (comboPesquisa.equals("OUTROS")){
            LimpaTabelaConsultarControlos();
            nomeColuna = "OUTROS";
            LerBDControloResultados(nomeColuna);
           
        }
        
        System.out.println("--OPÇÃO MOSTRAR NA TABELA CONSULTA CONTROLO DE RESULADOS");
        System.out.println("OP : " + comboPesquisa);
        
//        LimpaTabelaConsultarControlos();
//        LerBDControloResultados();

        
    }//GEN-LAST:event_jComboBoxSeccaoActionPerformed

    private void jButtonAddNovaNaoConformidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNovaNaoConformidadeActionPerformed
        // ADICIONAR NOVA NAO CONFORMIDADE AKELE CONTROLO RESULTADOS
            
        jDialogNaoConformidades.setVisible(true);
        
        //PARA ADICIONAR UMA NOVA NAO CONFORMIDADE NECESSITAMOS DE PASSAR OS CAMPOS "IDCONTRESULT" e "IDFUNCIONARIO"
        
        int funcionario = addNewNaoConformidadeIdFuncionario ;
        int controResult = idControloResultadosID;
        
        
        
        //ESCREVER
        System.out.println("CONTROLO RESULTADOS");
        System.out.println("FUNCIONARIO: " + funcionario);
        System.out.println("CONTRO RESULT: " + controResult);
        System.out.println("\n\n");
        
   
        
    }//GEN-LAST:event_jButtonAddNovaNaoConformidadeActionPerformed

    private void jButtonCancelarNaoConformidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarNaoConformidadeActionPerformed
        // BOTAO FECHAR JANELA NAO CONFORMIDADES
        jDialogNaoConformidades.setVisible(false);
        LimpaNaoConformidade();
    }//GEN-LAST:event_jButtonCancelarNaoConformidadeActionPerformed

    private void jButtonCancelarMedidaCorrectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarMedidaCorrectivaActionPerformed
        //BOTAO FEXHAR JANELA MEDIDAS CORRECTIVAS
        jDialogMedidasCorrectiva.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarMedidaCorrectivaActionPerformed

    private void jButtonAddMedidaCorrectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMedidaCorrectivaActionPerformed
        // ADICIONAR MEDIDA CORRECTIVA
      
        int linha = jTableNaoConformidades.getSelectedRow();
        idNaoConformidadeUpdate = 0;
          
        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione Uma Linha da Tabela de Não Conformidade");
        }else{
            //VER O ID DA NAO CONFORMIDADE
            idNaoConformidadeUpdate = (int) jTableNaoConformidades.getValueAt(linha, 0);
                                    
            System.out.println("\n****** NAO CONFORMIDADE -> MEDIDA CORRECTIVA");
            System.out.println("ID NAO CONFOMRIDADE UPDATE: " + idNaoConformidadeUpdate);
            jDialogMedidasCorrectiva.setLocationRelativeTo(this);
            jDialogMedidasCorrectiva.setVisible(true);
            
        }
    }//GEN-LAST:event_jButtonAddMedidaCorrectivaActionPerformed
    
    
    /* LER */ 
       
    private void LerBDControloResultados(String nomeColuna){
     
        int idFuncionario = 0; 
        String nomeFuncionario ="";

        String data = "";
        String resultado = "";
        String fichaTecnica = "";
        String descricao = "";
        String outro = "";
        
        String nomeSeccao = ""; // ir a tabela buscar o nome da tabela em questao!
        int colunaID = 0;
        String coluna =  nomeColuna;
        
        int numeroRegistos = 0;
        
        model = (DefaultTableModel) jTableConsultarControlos.getModel();
        
        //Ligação BD
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }      
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "controloresultados";
            String sql = "select * from "+nomeTabela;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO","IDFUNCIONARIO",idFuncionario,"NOME"); 
                
                if (!coluna.equals("OUTROS")){
                    colunaID = rs.getInt(coluna);
                }
                
                
                //VERIFICAR QUAL A COLUNA  E IR A TABELA CERTA BUSCAR O NOME
                if (coluna.equals("IDANALISE")){
                    nomeSeccao = selectString("ANALISES","IDANALISE",colunaID,"NOME");
                
                }else if (coluna.equals("IDEQUIPAMENTO")){
                     nomeSeccao = selectString("EQUIPAMENTO","IDEQUIPAMENTO",colunaID,"NOME");
                     
                }else if (coluna.equals("IDINSECTOCACADORES")){
                    nomeSeccao = selectString("INSECTOCACADORES","IDINSECTOCACADORES",colunaID,"NOME");
                
                }
                
                outro = rs.getString(coluna);
                System.out.println("OUTRO: " + outro);
                
                data = rs.getString("DATA");
                resultado = rs.getString("RESULTADO");
                fichaTecnica = rs.getString("FICHATECNICA");
                descricao = rs.getString("DESCRICAO");
              
                
                if (!coluna.equals("OUTROS")){
                    if (colunaID != 0){
                        model.addRow(new Object[]{nomeFuncionario,nomeSeccao , data, resultado, fichaTecnica, descricao});
                        numeroRegistos++;
                    }
                }else{
                    if (outro != null ){
                         model.addRow(new Object[]{nomeFuncionario,outro , data, resultado, fichaTecnica, descricao});
                         numeroRegistos++;
                    }
                }
                                  
            }
            
            //QUANDO NAO EXISTE NENHUM REGISTO INSERIDO
            if ( numeroRegistos == 0){
                   JOptionPane.showMessageDialog(jDialogConsultarControlos, "Não Existe Nenhum Registo Nesta Secção ! ");
               } 
            
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
    
    }
    
    private void LerBDNaoConformidades(){
        int idNaoConf = 0;
        int idContResultados = 0;
        int idFuncionario = 0;
        String dataNaoConform = "";
        String ocorrencia = "";
        String medidaCorrecti = "";
        int idFuncionarioResponsavelMedidaCorrectiva = 0;
        String NomeIdFuncionarioResponsavelMedidaCorrectiva = "";
        
        model = (DefaultTableModel) jTableNaoConformidades.getModel();
         
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }
        
         try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "NAOCONFORMIDADES";
            String sql = "select * from "+nomeTabela ;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                idNaoConf = rs.getInt("IDNAOCONF");
                idContResultados = rs.getInt("IDCONTRESULTADOS");
                idFuncionario = rs.getInt("IDFUNCIONARIO");
                dataNaoConform = rs.getString("DATANAOCONFORMIDADE");
                ocorrencia = rs.getString("OCORRENCIA");
                medidaCorrecti = rs.getString("MEDIDACORRECTIVA");
                idFuncionarioResponsavelMedidaCorrectiva = rs.getInt("IDFUNCIONARIOMEDIDACORRECTIVA");
                NomeIdFuncionarioResponsavelMedidaCorrectiva = selectString("FUNCIONARIO","IDFUNCIONARIO",idFuncionarioResponsavelMedidaCorrectiva,"NOME"); 
                
                model.addRow(new Object[]{idNaoConf, idContResultados, idFuncionario, dataNaoConform, ocorrencia, medidaCorrecti, NomeIdFuncionarioResponsavelMedidaCorrectiva});
                                
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
        
    }
    
    private void LerBDEquipamento(){
     //LIMPAR A COMBO
        jComboBoxOpcao.removeAllItems();
        //CARREGAR DADOS
        Set<String> opcao = new HashSet<String>();
        
        try{
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            Class.forName("com.mysql.jcbd.Driver");        
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        }catch(ClassNotFoundException e){
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        
        try{
            con = DriverManager.getConnection(url);
            //            stmt = con.createStatement();
            String nomeTabela = "EQUIPAMENTO";
            String sql = "select * from " +nomeTabela; 
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                opcao.add(rs.getString("NOME"));
            }
            st.close();
            con.close();
            
        }catch(SQLException ex){
            System.err.println("SQLException: " + ex.getMessage()); 
        }
        
        jComboBoxOpcao.addItem("--Opção--");
        Iterator<String> it = opcao.iterator();
        while(it.hasNext()){
            jComboBoxOpcao.addItem(it.next());
        }
        
        
    }
    
    private void LerBDInsectadores(){
    //LIMPAR A COMBO
        jComboBoxOpcao.removeAllItems();
        //CARREGAR DADOS
        Set<String> opcao = new HashSet<String>();
        
        try{   
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        }catch(ClassNotFoundException e){
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        
        try{
            con = DriverManager.getConnection(url);
            //            stmt = con.createStatement();
            String nomeTabela = "INSECTOCACADORES";
            String sql = "select * from " +nomeTabela; 
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                opcao.add(rs.getString("NOME"));
            }
            st.close();
            con.close();
            
        }catch(SQLException ex){
            System.err.println("SQLException: " + ex.getMessage()); 
        }
        
        jComboBoxOpcao.addItem("--Opção--");
        Iterator<String> it = opcao.iterator();
        while(it.hasNext()){
            jComboBoxOpcao.addItem(it.next());
        }
    }
    
    private void LerBDFuncionario(){
    //LIMPAR A COMBO
        jComboBoxFuncionario.removeAllItems();
        //CARREGAR DADOS
        Set<String> opcao = new HashSet<String>();
        
        try{   
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        }catch(ClassNotFoundException e){
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        
        try{
            con = DriverManager.getConnection(url);
            //            stmt = con.createStatement();
            String nomeTabela = "FUNCIONARIO";
            String sql = "select * from " +nomeTabela; 
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                opcao.add(rs.getString("NOME"));
            }
            st.close();
            con.close();
            
        }catch(SQLException ex){
            System.err.println("SQLException: " + ex.getMessage()); 
        }
        
        jComboBoxFuncionario.addItem("--Funcionario--");
        Iterator<String> it = opcao.iterator();
        while(it.hasNext()){
            jComboBoxFuncionario.addItem(it.next());
        }
    
    }
     
    private void LerBDFuncionarioResponsavel(){
       //LIMPAR A COMBO
        jComboBoxNaoConformidadeFuncionarioResponsav.removeAllItems();
        //CARREGAR DADOS
        Set<String> opcao = new HashSet<String>();
        
        try{   
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        }catch(ClassNotFoundException e){
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        
        try{
            con = DriverManager.getConnection(url);
            //            stmt = con.createStatement();
            String nomeTabela = "FUNCIONARIO";
            String sql = "select * from " +nomeTabela; 
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                opcao.add(rs.getString("NOME"));
            }
            st.close();
            con.close();
            
        }catch(SQLException ex){
            System.err.println("SQLException: " + ex.getMessage()); 
        }
        
        jComboBoxNaoConformidadeFuncionarioResponsav.addItem("--Funcionario--");
        Iterator<String> it = opcao.iterator();
        while(it.hasNext()){
            jComboBoxNaoConformidadeFuncionarioResponsav.addItem(it.next());
        }
     
    }
      
    
    /* CONSULTAS */    
    
    //PREENCHER TABELAS COM DADOS SELECCIONADOS
    private void ConsultaBDNaoConformidadeSelect(int idFuncionarioSelecc ){
        int funcionario = idFuncionarioSelecc;
        
        int idNaoConformidade = 0;
        int idContResultado = 0;
        int idFuncionario = 0;
        String dataNaoConform = "";
        String ocorrencia = "";
        String medidaCorrecti = "";
        String resultado = "NC";
        String nomefuncionario = "";
        String correctiva = "N";
        int idFuncionarioResponsavel = 0;
        String nomeFuncionarioResponsavel = "";
   
        model = (DefaultTableModel) jTableNaoConformidades.getModel();
        contaNaoConformidade_MedidasCorrectivas = 0; 
        //Ligação BD
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }      
        
        try{         
            con = DriverManager.getConnection(url);
            String nomeTabela = "CONTROLORESULTADOS";
            String sql = "select * from "+nomeTabela+" where IDFUNCIONARIO="+funcionario +"and RESULTADO="+"'"+ resultado +"'";
            //String sql = "select * from "+nomeTabela+" where IDFUNCIONARIO="+funcionario;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
                   
            while(rs.next()){
                idControloResultadosID = rs.getInt("IDCONTRESULTADOS");
                System.out.println("IDCONTROLORESULTADO Pesquisar -> " + idControloResultadosID); 
                 //VARIVAEL GLOBAL
                addNewNaoConformidadeIdFuncionario = idFuncionario;              
                addNewNaoConformidadeIdFuncionario = funcionario;
                //ESCREVER NA TABELA A PESQUISA
                String nomeTabela2 = "NAOCONFORMIDADES";
                String sql2 = "select * from "+nomeTabela2+" where IDCONTRESULTADOS="+idControloResultadosID+" and CORRECTIVA='"  + correctiva + "'";
                PreparedStatement st2 = (PreparedStatement) con.prepareStatement(sql2);
                ResultSet rs2 = st2.executeQuery();
                
                while(rs2.next()){
                    
                    idNaoConformidade = rs2.getInt("IDNAOCONF");
                    idContResultado = rs2.getInt("IDCONTRESULTADOS");
                    
                    idFuncionario = rs2.getInt("IDFUNCIONARIO");
                    nomefuncionario = selectString("FUNCIONARIO","IDFUNCIONARIO",idFuncionario,"NOME"); 
                    
                    dataNaoConform = rs2.getString("DATANAOCONFORMIDADE");
                    ocorrencia = rs2.getString("OCORRENCIA");
                    medidaCorrecti = rs2.getString("MEDIDACORRECTIVA");
                    
                    idFuncionarioResponsavel = rs2.getInt("IDFUNCIONARIOMEDIDACORRECTIVA");
                    nomeFuncionarioResponsavel = selectString("FUNCIONARIO","IDFUNCIONARIO",idFuncionarioResponsavel,"NOME"); 
                    
                    model.addRow(new Object[]{idNaoConformidade,nomefuncionario, dataNaoConform, ocorrencia, medidaCorrecti, nomeFuncionarioResponsavel});
                    
                    contaNaoConformidade_MedidasCorrectivas++;
                    idContResultadosUltimo = idContResultado;
                }
                st2.close();               
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        } 
        
        /* VERIFICAR SE É O ULTIMO OU NAO NA LISTA *
         * IR A TABELA CONTROLO RESULTADOS ALTERAR O VALOR DO RESULTADO*/
        if (contaNaoConformidade_MedidasCorrectivas == 0){
            //ACTUALIZAR OS DADOS NA TABELA NAO CONFORMIDADES
            ActualizaMedidaCorrectiva();
            //ACTUALIZAR A TABELA DE CONTROLO DE RESULTADOS
            JOptionPane.showMessageDialog(jDialogMedidasCorrectiva, "IRÁ SER ALERADO O RESULTADO DO CONTROLO DE RESULTADO!");
            ActualizaControloResultado();
            jTextAreaObservacaoMedidaCorrectiva.setText("");
            jDialogMedidasCorrectiva.setVisible(false);
            jDialogConsultaNaoConformidades.setVisible(false);
        }
        
        
        System.out.println("\n*** NUMERO DE REGISTO DE NAO CONFORMIDADES");
        System.out.println("REGISTOS: " + contaNaoConformidade_MedidasCorrectivas);
        System.out.println("ID ULTIMO: " +idContResultadosUltimo);
    }
        
    
    /* INSERIR */
    
    //COM A VERIFICAÇÃO DO C OU NC
    private void InserirNovoControloResultados(){
     
        
        String comboResultado = jComboBoxResultado.getSelectedItem().toString();
        //VERIFICAR QUAL O RESULTADO (NC) OU (C)
        if (comboResultado.equals("C")){
            //perguntar se quer mesmo gravar
            int resultGravar = JOptionPane.showConfirmDialog(jDialogNovoControloResultados, "Deseja Mesmo Gravar os dados ?", null, JOptionPane.YES_NO_OPTION);
            
            if(resultGravar == JOptionPane.YES_OPTION){
                //VAMOS GRAVAR OS DADOS
                GravarControloResultados();  
                jDialogNovoControloResultados.setVisible(false);
            }
        }else if (comboResultado.equals("NC")){
            int resultNCNaoConformidades = JOptionPane.showConfirmDialog(jDialogNovoControloResultados, "Deseja Abrir a Janela de Não Conformidades ?", null, JOptionPane.YES_NO_OPTION);
            
            if (resultNCNaoConformidades == JOptionPane.YES_OPTION){
                GravarControloResultados();
                jDialogNaoConformidades.setLocationRelativeTo(this);
                jDialogNaoConformidades.setVisible(true);
                jDialogNovoControloResultados.setVisible(false);
                LerBDFuncionarioResponsavel();
            }else if (resultNCNaoConformidades == JOptionPane.NO_OPTION){
                //perguntar se  quer gravar e voltar a pagi principal
                 int resultNCGravarVoltar = JOptionPane.showConfirmDialog(jDialogNovoControloResultados, "Deseja Gravar e Volta a Pagina Principal ?", null, JOptionPane.YES_NO_OPTION); 
                 
                 if (resultNCGravarVoltar == JOptionPane.YES_OPTION){
                     GravarControloResultados();
                     jDialogNovoControloResultados.setVisible(false);
                 }
                 
            }
                    
        }    
        
    }
    
    private void GravarControloResultados(){
    // INSERIR DADOS NA BASE 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        
        int idOpcaoEscolhida = 0;
        int idFuncionario = 0;
        int idEntrada = 0;
        int idEquipamento = 0;
        int idInsectocacadores = 0;
        String fichaTecnica = "" ;
        
        /*VERIFICAR SE O RESULTADO É C OU NC*/          
        String comboResultado = jComboBoxResultado.getSelectedItem().toString();
          
        
        //LER OS CAMPOS DA JANELAS
        String outro = jTextFieldOutraOpcao.getText();
        String data = (String) sdf.format(jDateChooserControloResultados.getDate());
        String resultado = comboResultado;
        String descricao = jTextAreaDescricao.getText();
    
        System.out.println("DATA: " + data);
      
        //VERIFICAR QUAL A OPÇAO DA COMBOBOX OPÇAO
        String comboOpcao = jComboBoxOpcao.getSelectedItem().toString();
        if (!comboOpcao.equals("--Opção--")){
          try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }          
          try{
              con = DriverManager.getConnection(url);
              String nomeTabela = comboTipoOP; // nome da tabela
              String sql = "select * from " +nomeTabela+" where NOME='"+comboOpcao+"'";
              PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
              ResultSet rs = st.executeQuery();
              
              while (rs.next()){
                  idOpcaoEscolhida = rs.getInt("ID"+comboTipoOP); // ID + COMBOTIPO (nome da tabelas = ID + nome)
              }
              st.close();
              con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());        
          
            }   
        }  
        
        //VERIFICAR EM QUAL COLUNA VAMOS INSERIR A OPÇAO
        if (comboTipoOP.equals("INSECTOCACADORES")){
            idInsectocacadores = idOpcaoEscolhida;
            idEquipamento = 0;
            idEntrada = 0;
        }else if (comboTipoOP.equals("EQUIPAMENTO")){
            idInsectocacadores = 0;
            idEquipamento = idOpcaoEscolhida;
            idEntrada = 0;
        }else if (comboTipoOP.equals("ENTRADAS")){
            idInsectocacadores = 0;
            idEquipamento = 0;
            idEntrada = idOpcaoEscolhida;
        }else{
            //ker dizer ke tenho a opçao OUTRA
            idInsectocacadores = 0;
            idEquipamento = 0;
            idEntrada = 0;
            }
    
        
        //VERIFICAR QUAL O FUNCIONARIO
        String comboFuncio = jComboBoxFuncionario.getSelectedItem().toString();
        if (!comboFuncio.equals("--Funcionario--")){
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }        
            
            try{
                con = DriverManager.getConnection(url);
                String nomeTabela = "FUNCIONARIO";
                String sql = "select * from "+nomeTabela+" where NOME='"+comboFuncio+"'";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                
                while(rs.next()){
                    idFuncionario = rs.getInt("IDFUNCIONARIO");
                }
                st.close();
                con.close();
            }catch (SQLException ex){
                 System.err.println("SQLException: " + ex.getMessage());
            }
        
        }
        
        
        
        //LIGAÇAO PARA GRAVAR NA BASE DE DADOS
         try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }
            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "CONTROLORESULTADOS";
                sql = "insert into " + nomeTabela+ "(IDFUNCIONARIO,IDEQUIPAMENTO,IDINSECTOCACADORES, IDENTRADA, DATA,RESULTADO,FICHATECNICA,DESCRICAO,OUTROS)"
                + " values (" + idFuncionario + "," +idEquipamento+ "," + idInsectocacadores + ","+ idEntrada + "," + "'" + data + "'" + "," + "'" + resultado + "'" + "," + "'"+ fichaTecnica +"'"+ "," + "'" + descricao + "'" + "," + "'"+ outro + "'"  + ")";

                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();
                 System.out.println("chegou");
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
          
  
          // ESCREVER CAMPOS NA CONSOLA
            System.out.println("\n\n***** INSERÇÃO DE DADOS NA TABELA CONTROLO DE RESULTADOS");
            System.out.println("FUNCIONARIO: " + idFuncionario); 
            System.out.println("ENTRADA: " + idEntrada);
            System.out.println("EQUIPAMENTO: " + idEquipamento); 
            System.out.println("INSECTICADO: " + idInsectocacadores);
            System.out.println("DATA: " + data);
            System.out.println("RESULTADO: " + resultado);
            System.out.println("FICHA TEC: " + fichaTecnica); 
            System.out.println("DESCRICAO: " + descricao);
            System.out.println("OUTRO: " + outro );  
            

        
    }
       
    private void GravarNaoConformidade(){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        
        int idConsResultados = 1;
        int idFuncionario = 0;
        String data = (String) sdf.format(jDateChooserNaoConformidade.getDate());
        String ocorrecia = jTextAreaOcorrenciaNaoConformidade.getText();
        String medidaCorrectiva = jTextAreaMedidaCorrctivaNaoConformidade.getText();
        int idFuncResponsavMedidCorrectiva = 0;
        String correctiva = "";
       

        
        //DADOS FICITCIOS
        idConsResultados = addNewNaoConformidadeIDContrResultado;
        idFuncionario = addNewNaoConformidadeIdFuncionario;
        
        
       
        
        //VERIFICAR QUAL O FUNCIONARIO RESPOSAVEL PELA MEDIDA CORRECTIVA
        String comboFuncio = jComboBoxNaoConformidadeFuncionarioResponsav.getSelectedItem().toString();
        if (!comboFuncio.equals("--Funcionario--")){
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }        
            
            try{
                con = DriverManager.getConnection(url);
                String nomeTabela = "FUNCIONARIO";
                String sql = "select * from "+nomeTabela+" where NOME='"+comboFuncio+"'";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                
                while(rs.next()){
                    idFuncResponsavMedidCorrectiva = rs.getInt("IDFUNCIONARIO");
                }
                st.close();
                con.close();
            }catch (SQLException ex){
                 System.err.println("SQLException: " + ex.getMessage());
            }
        
        }
        
        // DADOS FICTICIOS
        idFuncResponsavMedidCorrectiva = 1; 
        idConsResultados = 1;
        correctiva = "N";
        
        //LIGAÇAO PARA GRAVAR NA BASE DE DADOS
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "NAOCONFORMIDADES";
            sql = "insert into " + nomeTabela + "(IDCONTRESULTADOS,IDFUNCIONARIO, DATANAOCONFORMIDADE, OCORRENCIA, MEDIDACORRECTIVA, IDFUNCIONARIOMEDIDACORRECTIVA, CORRECTIVA)"
                    + " values ("+idConsResultados + "," +idFuncionario+ "," + "'" +data+ "'" + "," + "'"+ocorrecia+ "'" + "," + "'" + medidaCorrectiva+"'" + "," +idFuncResponsavMedidCorrectiva + "," + "'" + correctiva + "'"  +")";

            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();
          
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        
        System.out.println("\n\n**DADOS DA NAO CONFORMIDADE");
        System.out.println("IdContrResultados " + idConsResultados);
        System.out.println("IdFuncionario: " + idFuncionario);
        System.out.println("Data : " + data);
        System.out.println("OCOORENCIA : " + ocorrecia);
        System.out.println("MEDIDA : " + medidaCorrectiva);
        System.out.println("Funci Responsa: " + idFuncResponsavMedidCorrectiva);
       
    }
    
     /* UPDATE */
    /* vai actualizar a tabela nao conformidades, onde estao
     * os campos das MEDIDAS CORRECTIVAS  */
    private void ActualizaMedidaCorrectiva(){
               
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        
       //LER DADOS DA JANELA 
        String data = (String) sdf.format(jDateChooserMedidaCorrectiva.getDate());
        String correctiva = "S";
        String observacaoMedidaCorrectiva = jTextAreaObservacaoMedidaCorrectiva.getText();
        
               
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "NAOCONFORMIDADES";
            String sql = "UPDATE "+ nomeTabela + " SET DATAMEDIDACORRECTIVA='" + data +"'"+","+" CORRECTIVA='"+correctiva+"'," + " OBSERVACAOMEDIDACORRECTIVA='"+observacaoMedidaCorrectiva+"' WHERE IDNAOCONF= " + idNaoConformidadeUpdate;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();

            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    
        
        
        
         /*  DADOS NA CONSOLA -> GRAVAR MEDIDA CORRECTIVA   */
        System.out.println("\n*** MEDIDAS CORRECTIVAS -> UPDATE ");
        System.out.println("ID NAO CONFORMIDADE: " + idNaoConformidadeUpdate);
        System.out.println("DATA MEDIDA CORRECTIVA: " + data);
        System.out.println("CORRECTIVA: " + correctiva);
        System.out.println("OBSERVAÇÃO MED. CORREC: " + observacaoMedidaCorrectiva);
        
        
        
    }
    
    //ACTUALIZAR TABELA CONTROLO RESULTADOS, C OU NC
    private void ActualizaControloResultado(){
        
        String resultado =  "C";
  
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        
         try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "CONTROLORESULTADOS";
            String sql = "UPDATE "+ nomeTabela + " SET RESULTADO='"+resultado+"' WHERE IDCONTRESULTADOS=" + idContResultadosUltimo;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();

            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    
        
    }
        
    
    /*LIMPAR*/
    
    private void LimpaTabelaConsultarControlos(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultarControlos.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaConsultaNaoConformidades(){
        DefaultTableModel model = (DefaultTableModel) jTableNaoConformidades.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaConsultaMedidasCorrectivas(){
      DefaultTableModel model = (DefaultTableModel) jTableConsultaMedidasCorrectivas.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaNaoConformidade (){
        
        jTextAreaOcorrenciaNaoConformidade.setText("");
        jTextAreaMedidaCorrctivaNaoConformidade.setText("");
        
    }
       
    
    /*FUNÇAO PARA LER OS CAMPO DE UMA TABELAS*/
    
    private int selectId(String tab, String col, String cam, String colId){
        int resultId = 0;
        String tabela = tab;
        String coluna = col;
        String campo = cam;
        String colunaId = colId;
        
        try {
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            Class.forName("com.mysql.jcbd.Driver");
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }                
            try {
                con = DriverManager.getConnection(url);
                sql = "select * from "+ tabela+ " where "+coluna+"='"+campo+"'";
                
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    resultId = rs.getInt(colunaId);
                }
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        return resultId;
    }
     
    private String selectString(String tab, String col, int cam, String colNome){
        String resultString = "";
        String tabela = tab;
        String coluna = col;
        int campo = cam;
        String colunaNome = colNome;
        
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }                
            try {
                con = DriverManager.getConnection(url);
                sql = "select * from "+ tabela+ " where "+coluna+"="+campo+" ";
                
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    resultString = rs.getString(colunaNome);
                }
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
            
        return resultString;
    }
    
    
   
   
    
    
    
    
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
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAddMedidaCorrectiva;
    private javax.swing.JButton jButtonAddNovaNaoConformidade;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCancelarMedidaCorrectiva;
    private javax.swing.JButton jButtonCancelarNaoConformidade;
    private javax.swing.JButton jButtonConsultarMedidasCorrectivas;
    private javax.swing.JButton jButtonControlos;
    private javax.swing.JButton jButtonCriaNovoControloResultados;
    private javax.swing.JButton jButtonFecharConsultaControlosResultados;
    private javax.swing.JButton jButtonGravarMedidaCorrectiva;
    private javax.swing.JButton jButtonGravarNaoConformidade;
    private javax.swing.JButton jButtonInserir;
    private javax.swing.JButton jButtonNaoConformidades;
    private javax.swing.JButton jButtonNovoControlo;
    private javax.swing.JButton jButtonVerNaoConformidade;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JButton jButtonVoltarMedidasCorrectivas;
    private javax.swing.JComboBox jComboBoxFuncionario;
    private javax.swing.JComboBox jComboBoxNaoConformidadeFuncionarioResponsav;
    private javax.swing.JComboBox jComboBoxOpcao;
    private javax.swing.JComboBox jComboBoxResultado;
    private javax.swing.JComboBox jComboBoxSeccao;
    private javax.swing.JComboBox jComboBoxTipo;
    private com.toedter.calendar.JDateChooser jDateChooserControloResultados;
    private com.toedter.calendar.JDateChooser jDateChooserMedidaCorrectiva;
    private com.toedter.calendar.JDateChooser jDateChooserNaoConformidade;
    private javax.swing.JDialog jDialogConsultaNaoConformidades;
    private javax.swing.JDialog jDialogConsultarControlos;
    private javax.swing.JDialog jDialogConsultarMedidasCorrectivas;
    private javax.swing.JDialog jDialogMedidasCorrectiva;
    private javax.swing.JDialog jDialogNaoConformidades;
    private javax.swing.JDialog jDialogNovoControloResultados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelConsultaMedidasCorrectivas;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelDataMedidasCorrectivas;
    private javax.swing.JLabel jLabelDataNaoConformidade;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelFichaTecnica;
    private javax.swing.JLabel jLabelFuncionario;
    private javax.swing.JLabel jLabelMedidaCorrectivaNaoConformidade;
    private javax.swing.JLabel jLabelMedidasCorrectivas;
    private javax.swing.JLabel jLabelNaoConformidades;
    private javax.swing.JLabel jLabelNomeOutraOpcao;
    private javax.swing.JLabel jLabelObservacaoMedidasCorrectivas;
    private javax.swing.JLabel jLabelOcorrecniaNaoConformidade;
    private javax.swing.JLabel jLabelOpcao;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelConsultaControlos;
    private javax.swing.JPanel jPanelConsultaNaoConformidades;
    private javax.swing.JPanel jPanelMedidasCorrectivas;
    private javax.swing.JPanel jPanelNaoConformidades;
    private javax.swing.JPanel jPanelNovoControloResultdos;
    private javax.swing.JPanel jPanelOutraOpcao;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneConsultaMedidasCorrectivas;
    private javax.swing.JScrollPane jScrollPaneConsultarControlos;
    private javax.swing.JScrollPane jScrollPaneDescricao;
    private javax.swing.JScrollPane jScrollPaneNaoConformidades;
    private javax.swing.JScrollPane jScrollPaneOcorrencia;
    private javax.swing.JScrollPane jScrollPanemedidaCorrectiva;
    private javax.swing.JTable jTableConsultaMedidasCorrectivas;
    private javax.swing.JTable jTableConsultarControlos;
    private javax.swing.JTable jTableNaoConformidades;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextArea jTextAreaMedidaCorrctivaNaoConformidade;
    private javax.swing.JTextArea jTextAreaObservacaoMedidaCorrectiva;
    private javax.swing.JTextArea jTextAreaOcorrenciaNaoConformidade;
    private javax.swing.JTextField jTextFieldOutraOpcao;
    // End of variables declaration//GEN-END:variables
}
