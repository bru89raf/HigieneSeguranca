/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modulosfinal;

import java.awt.Component;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Rafael
 */
public class Login extends javax.swing.JFrame {

    
     /* VARIAVEIS PARA A LIGAÇÃO A BASE DE DADOS */
    String url = "jdbc:derby://localhost:1527/moduloFinal";
    Connection con;
    String sql;

    DefaultTableModel model;
    
    
    
    /* **************************************** CONTROLO DE RESULTADOS  ****************************************** */
    
    /*  VARIAVEL QUE GUARDA O ID NO INSECTOCAÇADOR SELECCIONADO  */
    int idInsectocacadorControloResultado = 0;
    int idEquipamentoControloResultado = 0;
    int idEntradaControloResultado = 0;
    
    //parte das entradas
    int idEntradaSeleccionada = 0;
    String loteEntradaSeleccionada = "";
    String secçãoControloResultadoSeleccionada = "";
    String dataControloResultadoSeleccionada = "";
    int idControloResultadosParaPesquisarNConformidade = 0;
    
    
    
    /****************************************************************************************************/
    
    /*  ******************************* VARIAVEIS GLOBAIS INSECTOCAÇADORES  *********************************************** */
   
    /*  VARIAVEL PARA CONTAR AS LINHAS DAS LIMPEZAS*/
    int CONTA_LINHAS_LIMPEZAS = 0;
    int CONTA_LINHAS_CONTROLORESULTADOS = 0;
    int CONTA_LINHAS_NAOCONFORMIDADES = 0;
    int CONTA_LINHAS_MEDIDASCORRECTIVAS = 0;
    

    /*  VARIVAVEL QUE GUARDA O ID DO FUNCINONARIO SELECCIONADO NUM CONTROLO DE RESULTADOS  */
    int idFuncionarioSelecionadoNaoConformidade = 0;
    int idControloResuladoSeleccionado = 0;
    
    
    /****************************************************************************************************/
    
    
    /*  **************************    VARIAVEIS GLOBAIS EQUIPAMENTOS    ****************************** */
  
    
    /* VARIVAEL PARA GUARDAR O ID DO FUNCIONARIO REFERENTE A NAO CONFORMIDADE*/
    int idControloResultadosID = 0; 
    
    /*VARIVAEL PARA CONTAR OS REGISTO DE UMA TABELA*/
     int CONTA_LINAS_NAOCONFORMIDADES = 0;
     int CONTA_LINHAS_MANUTENCAO = 0;
    /****************************************************************************************************/
    
    /* **************************** VARIAVEIS GLOBAIS CONTROLO DE RESULTADOS ********************** */
    
    String comboTipoOP = ""; // guardar a opçao da ComboBox TIPO
    String comboSeccaoOP = ""; // guardar a opção da  comboBoc Opção (Controlo resultados)
    
     
    /* ID DO FUNCIONARIO QUE ESTAMOS A SELECCIONAR PARA VER A NAO CONFORMIDADE  */
    int idFuncionarioNaoConformidade = 0;
    
    /*  VARIAVEIS PARA ADICIONAR UMA NOVA NAO CONFORMIDADE A UM CONTROLO DE RESULTADOS  */
    int addNewNaoConformidadeIdFuncionario = 0;
    int addNewNaoConformidadeIDContrResultado = 0;
    
    
    /* JANELA MEDIDAS CORRECTIVIAS */
    int idMedidaCorrectivaUpdate = 0;
    
    /* SABER QUANTOS REGISTO TEMOS POR NAO CONFORMIDADE, PARA SABER SE É O ULTIMO E 
     * ACTUALIZAR A TABELA DE CONTROLO DE RESULTADOS */
   
    int idContResultadosUltimo = 0; //CHAVE ESTRANGUEIRA(CONTROLO RESULTADOS) QUE ESTA NA TABELA NAO CONFORMIDADES
    
    String observacaoDoControloResultadoParaANC = "";
    
    
    /****************************************************************************************************/
    
    
   /*  ******************************* VARIAVEIS GLOBAIS INSECTOCAÇADORES  *********************************************** */
    
    /*  ID DA ENTRADA SELECCIONADA -> JANELA CONSULTA ENTRADAS */

  
    
    /*  ID'S PARA FAZER DEVOLUÇÃO -> JANELA CONSULTA ENTRDAS - JANELA DEVOLUÇÃO */
    int idFornecedorDevolucao = 0;
    int idMateriaPrimaDevolucao = 0;
    float quantidadeEntradaTotalSelecionada;
    
    
    /*  ABRIR OU NAO CONTROLO DE RESULTADOS */
    boolean abrirControloResultados = false;
      
    /*  VARIAVEL PARA SABERMOS SE EXISTE OU NAO DEVOLUÇÃO   */
    boolean MateriaPrimaADevolver =  false;
    
    float quantidadeADevolverSelecionada = 0.0f;
    float quantidadeADevolverSelecionadaFixa = 0.0f;
    
    float mostraQuantidadeDevolvidaAteAoMomento = 0.0f;
    
    
    /*  VARIAVEL PARA GUARDAR O LOTE    */
    String geraLote = "";
    
    /*  VARIVAL PARA GUARDAR O LOTE PESQUISADO  */
    String LoteOriginalSeleccionado = "";
   
    /*  MOSTRAR FICHA DETALHADA DE UMA DEVOLUÇÃO    */
    int idDevolucaoSeleccionado = 0;
        //pesquisa
    int idForncedorPesquisaDevolucao = 0;
    int idMateriaPrimaPesquisaDevolucao = 0;
    int devolucaoNumero = 0;
    
    
    /*  VARIVEL PARA CONTAR QUANTOS DADOS TEMOS INSERIDOS NAS DEVOLUÇOES (PESQUISA) */
    
    int CONTA_NUMERO_DEVOLUCOES = 0;
    
     /****************************************************************************************************/
    
    
    
    /*  VARIAVEIS PARA OS DADOS QUE VAMOS PASSAR ENTRE AS JANELAS -> CR -> NC -> MC */
    String seccaoCRparaNC = "";
    String problemaCRparaNC = "";
    
    
    String EstadoFornecedor = "";
    int idFornecedorPesquisar ;
    boolean estadoFornecedor ;
    
    boolean estadoMateriaPrima;
    int idMateriaPrimaPesquisar;
    
    boolean estadoInsectocacadores ;
    int idInsectocacadorPesquisar;
    
    boolean estadoEquipamento;
    int idEquipamentoPesquisar;
    
    //DADOS DE LOGIN
    String NomeFuncionarioLogin;
    int idFuncionarioLogin;
    
    boolean mediaCorrectivaUltima = false;
    
    /*  VARIAVEIS PARA O HISTORICO */
    int idFuncionarioHistorico;
    int idControloResultadoHistorico;
    
    boolean gravarNaBDNovoControloResultado = false;
    
    /*  VARIAVEL PARA VERIFICAR SE JA EXISTE NA BASE DE DADOS, GUARDARMOS O NOME DO OBJECTO */
    String nomeAVerficar = "";
    
    
    //VARIAVEIS PARA CARREGAR OS FICHEIROS
    public File input = null;
    private String out = "D:\\";
    JFileChooser jfc = new JFileChooser(".");
    private String nomeFile = "";
    
    //ficheiro
    
    /**
     * Creates new form Login
     */
    public Login() {
         
        this.setLocationRelativeTo(this);
         
        initComponents();
        //jMenuBar1.setEnabled(false);
        LerBDFuncionario(jComboBoxNomeLogin);
       
        for (Component cp : jMenuBar1.getComponents())
        cp.setEnabled(false);
        
//         for (Component cp : jPanelPrincipal.getComponents())
//        cp.setVisible(false);
        
        //ESCONDER PANEL PRINCIPAL DA JANELA PRINCIPAL
        jPanelPrincipal.setVisible(true);
        //ESCONDER OS PANEL DOS BOTOES
        
        jPanelInserirNovo.setVisible(false);
        jPanelConsultar.setVisible(false);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogJanelaPrincipal = new javax.swing.JDialog();
        jPanelPrincipal = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButtonInserirNovo = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();
        jPanelMenus = new javax.swing.JPanel();
        jPanelInserirNovo = new javax.swing.JPanel();
        jButtonNvEquipamento = new javax.swing.JButton();
        jButtonNvEntrada = new javax.swing.JButton();
        jButtonNvMateriaPrima = new javax.swing.JButton();
        jButtonNvFornecedor = new javax.swing.JButton();
        jButtonNvContrPragas = new javax.swing.JButton();
        jButtonNvContrResultados = new javax.swing.JButton();
        jPanelConsultar = new javax.swing.JPanel();
        jButtonConsEquipamento = new javax.swing.JButton();
        jButtonConsEntrada = new javax.swing.JButton();
        jButtonConsDevoluções = new javax.swing.JButton();
        jButtonConsuMateriaPrima = new javax.swing.JButton();
        jButtonNvFornecedor1 = new javax.swing.JButton();
        jButtonNvContrPragas1 = new javax.swing.JButton();
        jButtonNvContrResultados1 = new javax.swing.JButton();
        jLabelBoasVindas = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuMenus = new javax.swing.JMenu();
        jMenuItemEquipamento = new javax.swing.JMenuItem();
        jMenuItemEntradas = new javax.swing.JMenuItem();
        jMenuItemInsectocacadores = new javax.swing.JMenuItem();
        jMenuItemControloResultados = new javax.swing.JMenuItem();
        jDialogMenuControloResultados = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButtonNovoControlo1 = new javax.swing.JButton();
        jButtonControlos1 = new javax.swing.JButton();
        jLabelUserLogin = new javax.swing.JLabel();
        jDialogMenuInsectocacadores = new javax.swing.JDialog();
        jPanelMenuInsectocadores = new javax.swing.JPanel();
        jButtonNovoInsecto = new javax.swing.JButton();
        jButtonConsultaInsecto = new javax.swing.JButton();
        jLabelUserLogin2 = new javax.swing.JLabel();
        jDialogMenuEquipamentos = new javax.swing.JDialog();
        jPanelMenuEquipamentos = new javax.swing.JPanel();
        jButtonAddNovoEquipa = new javax.swing.JButton();
        jButtonConsultaEquipamentos = new javax.swing.JButton();
        jLabelUserLogin3 = new javax.swing.JLabel();
        jDialogMenuEntradas = new javax.swing.JDialog();
        jPanelMenuEntradas = new javax.swing.JPanel();
        jButtonNovaEntrada = new javax.swing.JButton();
        jButtonConsultaEntradas = new javax.swing.JButton();
        jButtonNovaMateriaPrima = new javax.swing.JButton();
        jButtonNovoForncedor = new javax.swing.JButton();
        jButtonConsultaDevolucoes = new javax.swing.JButton();
        jButtonConsultarFornecedores = new javax.swing.JButton();
        jButtonConsultaMateriasPrimas = new javax.swing.JButton();
        jLabelUserLogin4 = new javax.swing.JLabel();
        jDialogNovaEntrada = new javax.swing.JDialog();
        jPanelNovaEntrada = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxFornecedorEntrada = new javax.swing.JComboBox();
        jTextFieldLoteEntrada = new javax.swing.JTextField();
        jTextFieldQuantidadeEntrada = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBoxMateriaPrimaEntrada = new javax.swing.JComboBox();
        jLabelFuncionarioLogin = new javax.swing.JLabel();
        jTextFieldNovaEntradaUnidadeQuantidade = new javax.swing.JTextField();
        jPanelBotoesNovaEntrada = new javax.swing.JPanel();
        jButtonGuardarNovaEntrada = new javax.swing.JButton();
        jButtonSairNovaEntrada = new javax.swing.JButton();
        jButtonDesvloquearPanel = new javax.swing.JButton();
        jPanelADevolverNovaEntrada = new javax.swing.JPanel();
        jTextFieldQuantidadeADevolver = new javax.swing.JTextField();
        jLabelQuantidadeADevolver = new javax.swing.JLabel();
        jTextFieldUnidadeQuantidadeADevolver = new javax.swing.JTextField();
        jPanelConformidadesNovaEntrada = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxTemperaturaEntrada = new javax.swing.JComboBox();
        jComboBoxDataValidadeEntrada = new javax.swing.JComboBox();
        jComboBoxCaraOrgonolepticasEntradas = new javax.swing.JComboBox();
        jComboBoxEmbalagemEntradas = new javax.swing.JComboBox();
        jLabel35 = new javax.swing.JLabel();
        jDialogNovaMateriaPrima = new javax.swing.JDialog();
        jPanelNovaMateriaPrima = new javax.swing.JPanel();
        jLabelNomeMateriaPrima = new javax.swing.JLabel();
        jLabelDescricaoMateriaPrima = new javax.swing.JLabel();
        jLabelUnidadeMateriaPrima = new javax.swing.JLabel();
        jTextFieldNomeMateriaPrima = new javax.swing.JTextField();
        jScrollPaneDescricaoMateriaPrima = new javax.swing.JScrollPane();
        jTextAreaDescricaoMateriaPrima = new javax.swing.JTextArea();
        jTextFieldUnidadeMateriaPrima = new javax.swing.JTextField();
        jButtonGuardarMateriaPrima = new javax.swing.JButton();
        jButtonSairMateriaPrima = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jDialogNovoFornecedor = new javax.swing.JDialog();
        jPanelNovoFornecedor = new javax.swing.JPanel();
        jLabelNomeFornecedor = new javax.swing.JLabel();
        jLabelMoradaFornecedor = new javax.swing.JLabel();
        jLabelCodPostalFornecedor = new javax.swing.JLabel();
        jLabelLocalidadeForncedor = new javax.swing.JLabel();
        jLabelContactoForncedor = new javax.swing.JLabel();
        jLabelEmailFornecedor = new javax.swing.JLabel();
        jLabelNIFFornecedor = new javax.swing.JLabel();
        jLabelTipoProduto = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldNomeFornecedor = new javax.swing.JTextField();
        jTextFieldMoradaFornecedor = new javax.swing.JTextField();
        jTextFieldCodPostalFornecedor = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter cpf= new javax.swing.text.MaskFormatter("####-###");
            jTextFieldCodPostalFornecedor = new javax.swing.JFormattedTextField(cpf);
        }catch (Exception e){}
        jTextFieldLocalidadeFornecedor = new javax.swing.JTextField();
        jTextFieldContactoFornecedor = new javax.swing.JTextField();
        jTextFieldEmailFornecedor = new javax.swing.JTextField();
        jTextFieldNIFFornecedor = new javax.swing.JTextField();
        jTextFieldTipoProdutoFornecedor = new javax.swing.JTextField();
        jButtonGuardarNovoFornecedor = new javax.swing.JButton();
        jButtonSairNovoFornecedor = new javax.swing.JButton();
        jLabelNovoFornecedor = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBoxEstadoFornecedor = new javax.swing.JComboBox();
        jButtonUpdateFornecedor = new javax.swing.JButton();
        jDialogNovoInsectocacador = new javax.swing.JDialog();
        jPanelNovoInsectocacador = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jLabelReferencia = new javax.swing.JLabel();
        jLabelLocal = new javax.swing.JLabel();
        jTextFieldReferenciaInsecto = new javax.swing.JTextField();
        jTextFieldNomeInsecto = new javax.swing.JTextField();
        jTextFieldLocalInsecto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonGuardarInsecto = new javax.swing.JButton();
        jButtonSairInsecto = new javax.swing.JButton();
        jButtonActualizarInsecto = new javax.swing.JButton();
        jButtonAlterarReferencia = new javax.swing.JButton();
        jDialogNovoControloResultados = new javax.swing.JDialog();
        jPanelNovoControloResultdos = new javax.swing.JPanel();
        jComboBoxTipo = new javax.swing.JComboBox();
        jComboBoxOpcao = new javax.swing.JComboBox();
        jLabelTipo = new javax.swing.JLabel();
        jLabelOpcao = new javax.swing.JLabel();
        jLabelResultado = new javax.swing.JLabel();
        jLabelFichaTecnica = new javax.swing.JLabel();
        jLabelDescricao = new javax.swing.JLabel();
        jComboBoxResultado = new javax.swing.JComboBox();
        jScrollPaneDescricao = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonInserir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jPanelOutraOpcao = new javax.swing.JPanel();
        jLabelNomeOutraOpcao = new javax.swing.JLabel();
        jTextFieldOutraOpcao = new javax.swing.JTextField();
        jLabelFuncionario = new javax.swing.JLabel();
        jLabelFuncionarioLogin2 = new javax.swing.JLabel();
        jDialogNaoConformidades = new javax.swing.JDialog();
        jPanelNaoConformidades = new javax.swing.JPanel();
        jLabelOcorrecniaNaoConformidade = new javax.swing.JLabel();
        jLabelMedidaCorrectivaNaoConformidade = new javax.swing.JLabel();
        jScrollPaneOcorrencia = new javax.swing.JScrollPane();
        jTextAreaOcorrenciaNaoConformidade = new javax.swing.JTextArea();
        jScrollPanemedidaCorrectiva = new javax.swing.JScrollPane();
        jTextAreaMedidaCorrctivaNaoConformidade = new javax.swing.JTextArea();
        jButtonGravarNaoConformidade = new javax.swing.JButton();
        jButtonCancelarNaoConformidade = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxNaoConformidadeFuncionarioResponsav = new javax.swing.JComboBox();
        jLabel = new javax.swing.JLabel();
        jLabelFuncionarioLogin4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelVerMensagemProblema = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaProblemadoControlo = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldVerSeccaoDoCR = new javax.swing.JTextField();
        jDialogMedidasCorrectiva = new javax.swing.JDialog();
        jLabelMedidasCorrectivas = new javax.swing.JLabel();
        jPanelMedidasCorrectivas = new javax.swing.JPanel();
        jLabelObservacaoMedidasCorrectivas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaObservacaoMedidaCorrectiva = new javax.swing.JTextArea();
        jButtonGravarMedidaCorrectiva = new javax.swing.JButton();
        jButtonCancelarMedidaCorrectiva = new javax.swing.JButton();
        jPanelInformacaoAdicional = new javax.swing.JPanel();
        jPanelInformacaoNaoConformidade = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaDetalhesMC = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaMedidasCorrectivasMC = new javax.swing.JTextArea();
        jPanelInformacaoControloResultado = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextFieldSeccaoMC = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaProblemaMC = new javax.swing.JTextArea();
        jDialogNovaManutencaoEquipamento = new javax.swing.JDialog();
        jPanelManutencaoEquipamento = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPaneObservacaManuetencaoEquipametno = new javax.swing.JScrollPane();
        jTextAreaObservacaoManutencaoEquipameto = new javax.swing.JTextArea();
        jButtonGravarManutencaoEquipametno = new javax.swing.JButton();
        jButtonSairManutencaoEquipamento = new javax.swing.JButton();
        jLabelFuncionarioResponsavel1 = new javax.swing.JLabel();
        jLabelFuncionariologin3 = new javax.swing.JLabel();
        jPanelEquipamentoSeleccionado = new javax.swing.JPanel();
        jTextFieldNomeEquipamentoSeleccionado = new javax.swing.JTextField();
        jDialogNovaLimpeza = new javax.swing.JDialog();
        jPanelNovaLimpeza = new javax.swing.JPanel();
        jLabelFuncionarioResponsavel = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jButtonGuardarLimpeza = new javax.swing.JButton();
        jButtonSairLimpeza = new javax.swing.JButton();
        jLabelFuncionarioLogin5 = new javax.swing.JLabel();
        jLabelDataNovaLimpeza = new javax.swing.JLabel();
        jDialogNovoEquipamento = new javax.swing.JDialog();
        jPanelNovoEquipamento = new javax.swing.JPanel();
        jLabelNovoEquipametno = new javax.swing.JLabel();
        jLabelNome1 = new javax.swing.JLabel();
        jLabelDescrição = new javax.swing.JLabel();
        jTextFieldNomeEquipamento = new javax.swing.JTextField();
        jScrollPaneNovoEquipmanentoObservacao = new javax.swing.JScrollPane();
        jTextAreaObservacaoNovoEquipamento = new javax.swing.JTextArea();
        jButtonGuardarEquipametno = new javax.swing.JButton();
        jButtonSairNovoEquipamento = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jDialogDevolucoes = new javax.swing.JDialog();
        jPanelDevolucoes = new javax.swing.JPanel();
        jLabelFuncionarioResponsavel2 = new javax.swing.JLabel();
        jLabelDataDevolucao = new javax.swing.JLabel();
        jLabelQuantidadeDevolvida = new javax.swing.JLabel();
        jLabelObservação = new javax.swing.JLabel();
        jDateChooserDataDevolucao = new com.toedter.calendar.JDateChooser();
        jTextFieldQuantidadeDevolucao = new javax.swing.JTextField();
        jScrollPaneObservacoesDevolucao = new javax.swing.JScrollPane();
        jTextAreaObservacoesDevolucao = new javax.swing.JTextArea();
        jButtonGuardaDevolucao = new javax.swing.JButton();
        jButtonSairDevolucao = new javax.swing.JButton();
        jLabelQuantidadeADevolverDevolucao = new javax.swing.JLabel();
        jTextFieldQuantidadeADevolverDevolucao = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabelFuncionarioLogin6 = new javax.swing.JLabel();
        jDialogConsultaInsectocacadores = new javax.swing.JDialog();
        jPanelConsultaInsecto = new javax.swing.JPanel();
        jScrollPaneConsultInsecto = new javax.swing.JScrollPane();
        jTableConsultaInsecto = new javax.swing.JTable();
        jButtonAddNovoInsecto = new javax.swing.JButton();
        jButtonSairConsultaInsecto = new javax.swing.JButton();
        jButtonNovaLimpeza = new javax.swing.JButton();
        jButtonConsultarLimpezas = new javax.swing.JButton();
        jButtonEditarInsectoca = new javax.swing.JButton();
        jButtonControloResultadosInsectoca = new javax.swing.JButton();
        jTextFieldPesquisaInsectocacador = new javax.swing.JTextField();
        jLabelPesquisarInsectocacador = new javax.swing.JLabel();
        jButtonVerInsectocacadoresInativos = new javax.swing.JButton();
        jButtonAlterarEstadoInsectocacadores = new javax.swing.JButton();
        jLabelConsultaControloPragas = new javax.swing.JLabel();
        jDialogConsultaLimpezas = new javax.swing.JDialog();
        jPanelConsultaLimpezas = new javax.swing.JPanel();
        jScrollPaneConsultaLimpezas = new javax.swing.JScrollPane();
        jTableConsultaLimpezas = new javax.swing.JTable();
        jButtonNovaLimpezaConsultaLimpezas = new javax.swing.JButton();
        jButtonSairConsultaLimpezas = new javax.swing.JButton();
        jDialogConsultaEquipamentos = new javax.swing.JDialog();
        jPanelConsultaEquipamentos = new javax.swing.JPanel();
        jScrollPaneConsultaEquipamentos = new javax.swing.JScrollPane();
        jTableConsultaEquipamentos = new javax.swing.JTable();
        jButtonNovoEquipamento = new javax.swing.JButton();
        jButtonSairConsultaEquipamentos = new javax.swing.JButton();
        jButtonManutencaoConsultaEquipamentos = new javax.swing.JButton();
        jButtonControloResultados = new javax.swing.JButton();
        jButtonEditarEquipamento = new javax.swing.JButton();
        jButtonConsultaManutencao = new javax.swing.JButton();
        jTextFieldPesquisaEquipamento = new javax.swing.JTextField();
        jLabelPesquisarEquipamento = new javax.swing.JLabel();
        jButtonAlterarEstadoEquipamento = new javax.swing.JButton();
        jButtonVerInativasEquipamentos = new javax.swing.JButton();
        jLabelConsultarEquipamentos = new javax.swing.JLabel();
        jDialogConsultaManutencaoEquipamentos = new javax.swing.JDialog();
        jPanelConsultaManutencaoEquipamentos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPaneConsultaManutencaoEquipamentos = new javax.swing.JScrollPane();
        jTableConsultaManutencaoEquipamentos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jDialogConsultarControlos = new javax.swing.JDialog();
        jPanelConsultaControlos = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPaneConsultarControlos = new javax.swing.JScrollPane();
        jTableConsultarControlos = new javax.swing.JTable();
        jButtonFecharConsultaControlosResultados = new javax.swing.JButton();
        jButtonCriaNovoControloResultados = new javax.swing.JButton();
        jButtonVerNaoConformidade = new javax.swing.JButton();
        jComboBoxSeccao = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jButtonVerNaoConformidadeEntrada = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButtonVerHistoricoControlo = new javax.swing.JButton();
        jDialogConsultaNaoConformidades = new javax.swing.JDialog();
        jPanelConsultaNaoConformidades = new javax.swing.JPanel();
        jLabelNaoConformidades = new javax.swing.JLabel();
        jScrollPaneNaoConformidades = new javax.swing.JScrollPane();
        jTableNaoConformidades = new javax.swing.JTable();
        jButtonVoltar = new javax.swing.JButton();
        jButtonAddNovaNaoConformidade = new javax.swing.JButton();
        jButtonAddMedidaCorrectiva = new javax.swing.JButton();
        jPanelMostraDadosControloResultado = new javax.swing.JPanel();
        jLabelSeccaoNC = new javax.swing.JLabel();
        jLabelProblemaNC = new javax.swing.JLabel();
        jTextFieldVerSeccaoNC = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaVerProblemaNC = new javax.swing.JTextArea();
        jDialogConsultarMedidasCorrectivas = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabelConsultaMedidasCorrectivas = new javax.swing.JLabel();
        jScrollPaneConsultaMedidasCorrectivas = new javax.swing.JScrollPane();
        jTableConsultaMedidasCorrectivas = new javax.swing.JTable();
        jButtonVoltarMedidasCorrectivas = new javax.swing.JButton();
        jDialogConsultaFornecedores = new javax.swing.JDialog();
        jPanelConsultaFornecedores = new javax.swing.JPanel();
        jLabelConsultaFornecedores = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableConsultaFornecedor = new javax.swing.JTable();
        jButtonFecharConsultaFornecedores = new javax.swing.JButton();
        jButtonFornecedoresInativos = new javax.swing.JButton();
        jButtonEditarFornecedor = new javax.swing.JButton();
        jButtonAlterarEstadoFornecedor = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldPesquisarFornecedor = new javax.swing.JTextField();
        jDialogConsultaEntradas = new javax.swing.JDialog();
        jPanelConsultaEntradas = new javax.swing.JPanel();
        jButtonConsultaEntradasSair = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsultaEntradas = new javax.swing.JTable();
        jButtonVerDevolucoes = new javax.swing.JButton();
        jButtonDevolucao = new javax.swing.JButton();
        jLabelPesquisar = new javax.swing.JLabel();
        jTextFieldPesquisarEntrada = new javax.swing.JTextField();
        jButtonVerControloResultados = new javax.swing.JButton();
        jButtonVerLoteTotal = new javax.swing.JButton();
        jLabelTituloConsultaEntradas = new javax.swing.JLabel();
        jDialogConsultaDevolucoes = new javax.swing.JDialog();
        jPanelConsultaDevolucoes = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableConsultaDevolucoes = new javax.swing.JTable();
        jButtonConsultaDevolucoesSair = new javax.swing.JButton();
        jButtonVerFichaDetalhadaConsultaDevolucoes = new javax.swing.JButton();
        jComboBoxFornecedorDevolucoes = new javax.swing.JComboBox();
        jComboBoxMateriaPrimaDevolucoes = new javax.swing.JComboBox();
        jLabelFornecedorDevolucoes = new javax.swing.JLabel();
        jLabelMateriaPrimaDevolucoes = new javax.swing.JLabel();
        jButtonVerTodasAsDevolucoes = new javax.swing.JButton();
        jDialogConsultaMateriasPrimas = new javax.swing.JDialog();
        jPanelConsutaMateriasPrimas = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableConsultaMateriasPrimas = new javax.swing.JTable();
        jButtonSairConsultaMateriaPrima = new javax.swing.JButton();
        jLabelPesquisarMateriaPrima = new javax.swing.JLabel();
        jTextFieldPesquisarMateriaPrima = new javax.swing.JTextField();
        jButtonAlterarEstadoMP = new javax.swing.JButton();
        jButtonVerInativosMP = new javax.swing.JButton();
        jButtonEditarMP = new javax.swing.JButton();
        jLabelConsultarMateriasPrimas = new javax.swing.JLabel();
        jDialogVerHistoricoControlo = new javax.swing.JDialog();
        jPanelConsultaHistorico = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableVerHistorico = new javax.swing.JTable();
        jButtonVerDatas = new javax.swing.JButton();
        jButtonSairHistorico = new javax.swing.JButton();
        jDialogAlterarReferencia = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jButtonGuardarNovaRef = new javax.swing.JButton();
        jTextFieldNovaReferencia = new javax.swing.JTextField();
        jLabelAntigaReferencia = new javax.swing.JLabel();
        jLabelNovaReferencia = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        jButtonSeleccionarNPF = new javax.swing.JButton();
        jTextFieldUploudNUC = new javax.swing.JTextField();
        JT01 = new javax.swing.JTextField();
        JT02 = new javax.swing.JTextField();
        JTRES = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanelJanela = new javax.swing.JPanel();
        jPanelRealizaLogin = new javax.swing.JPanel();
        jLabelNomeLogin = new javax.swing.JLabel();
        jLabelPasswordLogin = new javax.swing.JLabel();
        jPasswordFieldLogin = new javax.swing.JPasswordField();
        jButtonEntrar = new javax.swing.JButton();
        jComboBoxNomeLogin = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        jDialogJanelaPrincipal.setTitle("Janela Principal");
        jDialogJanelaPrincipal.setMinimumSize(new java.awt.Dimension(700, 500));

        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder("Menus Rapidos"));

        jLabel15.setText("Atalhos Rapidos");

        jButtonInserirNovo.setText("Inserir Novo");
        jButtonInserirNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirNovoActionPerformed(evt);
            }
        });

        jButtonConsultar.setText("Consultar");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jPanelInserirNovo.setBorder(javax.swing.BorderFactory.createTitledBorder("Inserir Novo"));

        jButtonNvEquipamento.setText("Equipamento");

        jButtonNvEntrada.setText("Entrada");

        jButtonNvMateriaPrima.setText("Materia-Prima");

        jButtonNvFornecedor.setText("Fornecedor");

        jButtonNvContrPragas.setText("Contr. Pragas");

        jButtonNvContrResultados.setText("Contr.Resultados");

        javax.swing.GroupLayout jPanelInserirNovoLayout = new javax.swing.GroupLayout(jPanelInserirNovo);
        jPanelInserirNovo.setLayout(jPanelInserirNovoLayout);
        jPanelInserirNovoLayout.setHorizontalGroup(
            jPanelInserirNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInserirNovoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInserirNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonNvFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNvEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInserirNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNvEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNvContrPragas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(jPanelInserirNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonNvContrResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNvMateriaPrima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanelInserirNovoLayout.setVerticalGroup(
            jPanelInserirNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInserirNovoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInserirNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNvEquipamento)
                    .addComponent(jButtonNvEntrada)
                    .addComponent(jButtonNvMateriaPrima))
                .addGap(18, 18, 18)
                .addGroup(jPanelInserirNovoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNvFornecedor)
                    .addComponent(jButtonNvContrPragas)
                    .addComponent(jButtonNvContrResultados))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanelConsultar.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar"));

        jButtonConsEquipamento.setText("Equipamento");
        jButtonConsEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsEquipamentoActionPerformed(evt);
            }
        });

        jButtonConsEntrada.setText("Entrada");

        jButtonConsDevoluções.setText("Devoluções");
        jButtonConsDevoluções.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsDevoluçõesActionPerformed(evt);
            }
        });

        jButtonConsuMateriaPrima.setText("Materia-Prima");

        jButtonNvFornecedor1.setText("Fornecedor");

        jButtonNvContrPragas1.setText("Contr. Pragas");

        jButtonNvContrResultados1.setText("Contr.Resultados");

        javax.swing.GroupLayout jPanelConsultarLayout = new javax.swing.GroupLayout(jPanelConsultar);
        jPanelConsultar.setLayout(jPanelConsultarLayout);
        jPanelConsultarLayout.setHorizontalGroup(
            jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultarLayout.createSequentialGroup()
                        .addComponent(jButtonNvContrResultados1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelConsultarLayout.createSequentialGroup()
                        .addGroup(jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonConsuMateriaPrima, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(jButtonConsEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonConsEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNvFornecedor1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonConsDevoluções, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNvContrPragas1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                        .addGap(51, 51, 51))))
        );
        jPanelConsultarLayout.setVerticalGroup(
            jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsEquipamento)
                    .addComponent(jButtonConsEntrada)
                    .addComponent(jButtonConsDevoluções))
                .addGap(18, 18, 18)
                .addGroup(jPanelConsultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsuMateriaPrima)
                    .addComponent(jButtonNvFornecedor1)
                    .addComponent(jButtonNvContrPragas1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonNvContrResultados1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMenusLayout = new javax.swing.GroupLayout(jPanelMenus);
        jPanelMenus.setLayout(jPanelMenusLayout);
        jPanelMenusLayout.setHorizontalGroup(
            jPanelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInserirNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMenusLayout.setVerticalGroup(
            jPanelMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelInserirNovo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel15))
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonInserirNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanelMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jButtonInserirNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConsultar))
                    .addComponent(jPanelMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelBoasVindas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelBoasVindas.setText("Olá, ");

        jMenuFile.setText("Programa");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItem1);

        jMenuBar1.add(jMenuFile);

        jMenuMenus.setText("Menus");

        jMenuItemEquipamento.setText("Equipamentos");
        jMenuItemEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEquipamentoActionPerformed(evt);
            }
        });
        jMenuMenus.add(jMenuItemEquipamento);

        jMenuItemEntradas.setText("Entradas");
        jMenuItemEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEntradasActionPerformed(evt);
            }
        });
        jMenuMenus.add(jMenuItemEntradas);

        jMenuItemInsectocacadores.setText("Contr. Pragas");
        jMenuItemInsectocacadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInsectocacadoresActionPerformed(evt);
            }
        });
        jMenuMenus.add(jMenuItemInsectocacadores);

        jMenuItemControloResultados.setText("Controlo Resultados");
        jMenuItemControloResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemControloResultadosActionPerformed(evt);
            }
        });
        jMenuMenus.add(jMenuItemControloResultados);

        jMenuBar1.add(jMenuMenus);

        jDialogJanelaPrincipal.setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout jDialogJanelaPrincipalLayout = new javax.swing.GroupLayout(jDialogJanelaPrincipal.getContentPane());
        jDialogJanelaPrincipal.getContentPane().setLayout(jDialogJanelaPrincipalLayout);
        jDialogJanelaPrincipalLayout.setHorizontalGroup(
            jDialogJanelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogJanelaPrincipalLayout.createSequentialGroup()
                .addGroup(jDialogJanelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogJanelaPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogJanelaPrincipalLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelBoasVindas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogJanelaPrincipalLayout.setVerticalGroup(
            jDialogJanelaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogJanelaPrincipalLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelBoasVindas)
                .addGap(18, 18, 18)
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jDialogMenuControloResultados.setTitle("MENU CONTROLO DE RESULTADOS");
        jDialogMenuControloResultados.setMinimumSize(new java.awt.Dimension(410, 150));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Controlo de Resultados");

        jButtonNovoControlo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonNovoControlo1.setText("Novo Controlo");
        jButtonNovoControlo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoControlo1ActionPerformed(evt);
            }
        });

        jButtonControlos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonControlos1.setText("Consultar Controlos");
        jButtonControlos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonControlos1ActionPerformed(evt);
            }
        });

        jLabelUserLogin.setText("jLabel36");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelUserLogin))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonNovoControlo1)
                        .addGap(40, 40, 40)
                        .addComponent(jButtonControlos1)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabelUserLogin))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoControlo1)
                    .addComponent(jButtonControlos1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogMenuControloResultadosLayout = new javax.swing.GroupLayout(jDialogMenuControloResultados.getContentPane());
        jDialogMenuControloResultados.getContentPane().setLayout(jDialogMenuControloResultadosLayout);
        jDialogMenuControloResultadosLayout.setHorizontalGroup(
            jDialogMenuControloResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMenuControloResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jDialogMenuControloResultadosLayout.setVerticalGroup(
            jDialogMenuControloResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMenuControloResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogMenuInsectocacadores.setTitle("MENU CONTROLO DE PRAGAS");
        jDialogMenuInsectocacadores.setMinimumSize(new java.awt.Dimension(480, 150));

        jButtonNovoInsecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonNovoInsecto.setText("Novo Controlo de Praga");
        jButtonNovoInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoInsectoActionPerformed(evt);
            }
        });

        jButtonConsultaInsecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonConsultaInsecto.setText("Consulta Controlos de Praga");
        jButtonConsultaInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaInsectoActionPerformed(evt);
            }
        });

        jLabelUserLogin2.setText("jLabel36");

        javax.swing.GroupLayout jPanelMenuInsectocadoresLayout = new javax.swing.GroupLayout(jPanelMenuInsectocadores);
        jPanelMenuInsectocadores.setLayout(jPanelMenuInsectocadoresLayout);
        jPanelMenuInsectocadoresLayout.setHorizontalGroup(
            jPanelMenuInsectocadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuInsectocadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuInsectocadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelUserLogin2)
                    .addGroup(jPanelMenuInsectocadoresLayout.createSequentialGroup()
                        .addComponent(jButtonNovoInsecto)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConsultaInsecto)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMenuInsectocadoresLayout.setVerticalGroup(
            jPanelMenuInsectocadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuInsectocadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUserLogin2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanelMenuInsectocadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoInsecto)
                    .addComponent(jButtonConsultaInsecto))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jDialogMenuInsectocacadoresLayout = new javax.swing.GroupLayout(jDialogMenuInsectocacadores.getContentPane());
        jDialogMenuInsectocacadores.getContentPane().setLayout(jDialogMenuInsectocacadoresLayout);
        jDialogMenuInsectocacadoresLayout.setHorizontalGroup(
            jDialogMenuInsectocacadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMenuInsectocacadoresLayout.createSequentialGroup()
                .addComponent(jPanelMenuInsectocadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );
        jDialogMenuInsectocacadoresLayout.setVerticalGroup(
            jDialogMenuInsectocacadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMenuInsectocadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jDialogMenuEquipamentos.setTitle("MENU EQUIPAMENTOS");
        jDialogMenuEquipamentos.setMinimumSize(new java.awt.Dimension(410, 150));

        jButtonAddNovoEquipa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonAddNovoEquipa.setText("Novo Equipamento");
        jButtonAddNovoEquipa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNovoEquipaActionPerformed(evt);
            }
        });

        jButtonConsultaEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonConsultaEquipamentos.setText("Consultar Equipamento");
        jButtonConsultaEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaEquipamentosActionPerformed(evt);
            }
        });

        jLabelUserLogin3.setText("jLabel36");

        javax.swing.GroupLayout jPanelMenuEquipamentosLayout = new javax.swing.GroupLayout(jPanelMenuEquipamentos);
        jPanelMenuEquipamentos.setLayout(jPanelMenuEquipamentosLayout);
        jPanelMenuEquipamentosLayout.setHorizontalGroup(
            jPanelMenuEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelUserLogin3)
                    .addGroup(jPanelMenuEquipamentosLayout.createSequentialGroup()
                        .addComponent(jButtonAddNovoEquipa)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConsultaEquipamentos)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanelMenuEquipamentosLayout.setVerticalGroup(
            jPanelMenuEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMenuEquipamentosLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabelUserLogin3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMenuEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddNovoEquipa)
                    .addComponent(jButtonConsultaEquipamentos))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogMenuEquipamentosLayout = new javax.swing.GroupLayout(jDialogMenuEquipamentos.getContentPane());
        jDialogMenuEquipamentos.getContentPane().setLayout(jDialogMenuEquipamentosLayout);
        jDialogMenuEquipamentosLayout.setHorizontalGroup(
            jDialogMenuEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMenuEquipamentosLayout.createSequentialGroup()
                .addComponent(jPanelMenuEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        jDialogMenuEquipamentosLayout.setVerticalGroup(
            jDialogMenuEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMenuEquipamentosLayout.createSequentialGroup()
                .addComponent(jPanelMenuEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jDialogMenuEntradas.setTitle("MENU ENTRADAS");
        jDialogMenuEntradas.setFocusCycleRoot(false);
        jDialogMenuEntradas.setMinimumSize(new java.awt.Dimension(450, 370));

        jButtonNovaEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonNovaEntrada.setText("Nova Entrada");
        jButtonNovaEntrada.setAlignmentY(0.0F);
        jButtonNovaEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonNovaEntrada.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonNovaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaEntradaActionPerformed(evt);
            }
        });

        jButtonConsultaEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonConsultaEntradas.setText("Consultar Entradas");
        jButtonConsultaEntradas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonConsultaEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaEntradasActionPerformed(evt);
            }
        });

        jButtonNovaMateriaPrima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonNovaMateriaPrima.setText("Nova Matéria-Prima");
        jButtonNovaMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaMateriaPrimaActionPerformed(evt);
            }
        });

        jButtonNovoForncedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fornecedor.png"))); // NOI18N
        jButtonNovoForncedor.setText("Novo Forncedor");
        jButtonNovoForncedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonNovoForncedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoForncedorActionPerformed(evt);
            }
        });

        jButtonConsultaDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonConsultaDevolucoes.setText("Consultar Devoluçõoes");
        jButtonConsultaDevolucoes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonConsultaDevolucoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaDevolucoesActionPerformed(evt);
            }
        });

        jButtonConsultarFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonConsultarFornecedores.setText("Consultar Fornecedores");
        jButtonConsultarFornecedores.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonConsultarFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarFornecedoresActionPerformed(evt);
            }
        });

        jButtonConsultaMateriasPrimas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonConsultaMateriasPrimas.setText("Consulta Materias-Primas");
        jButtonConsultaMateriasPrimas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaMateriasPrimasActionPerformed(evt);
            }
        });

        jLabelUserLogin4.setText("jLabel36");

        javax.swing.GroupLayout jPanelMenuEntradasLayout = new javax.swing.GroupLayout(jPanelMenuEntradas);
        jPanelMenuEntradas.setLayout(jPanelMenuEntradasLayout);
        jPanelMenuEntradasLayout.setHorizontalGroup(
            jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuEntradasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelUserLogin4)
                    .addGroup(jPanelMenuEntradasLayout.createSequentialGroup()
                        .addGroup(jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonNovoForncedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNovaMateriaPrima, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNovaEntrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonConsultarFornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonConsultaDevolucoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonConsultaEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonConsultaMateriasPrimas))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanelMenuEntradasLayout.setVerticalGroup(
            jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuEntradasLayout.createSequentialGroup()
                .addGroup(jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMenuEntradasLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelUserLogin4)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonConsultaEntradas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConsultaDevolucoes))
                    .addGroup(jPanelMenuEntradasLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButtonNovaEntrada)))
                .addGap(18, 18, 18)
                .addGroup(jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsultaMateriasPrimas)
                    .addComponent(jButtonNovaMateriaPrima))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsultarFornecedores)
                    .addComponent(jButtonNovoForncedor, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogMenuEntradasLayout = new javax.swing.GroupLayout(jDialogMenuEntradas.getContentPane());
        jDialogMenuEntradas.getContentPane().setLayout(jDialogMenuEntradasLayout);
        jDialogMenuEntradasLayout.setHorizontalGroup(
            jDialogMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMenuEntradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenuEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jDialogMenuEntradasLayout.setVerticalGroup(
            jDialogMenuEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMenuEntradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMenuEntradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialogNovaEntrada.setTitle("NOVA ENTRADA");
        jDialogNovaEntrada.setMinimumSize(new java.awt.Dimension(620, 510));

        jLabel13.setText("Fornecedor");

        jLabel14.setText("Funcionario Responsavel");

        jLabel16.setText("Lote Origem");

        jLabel17.setText("Quantidade");

        jComboBoxFornecedorEntrada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldLoteEntrada.setEditable(false);

        jLabel18.setText("Materia Prima");

        jComboBoxMateriaPrimaEntrada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMateriaPrimaEntrada.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxMateriaPrimaEntradaPopupMenuWillBecomeInvisible(evt);
            }
        });

        jLabelFuncionarioLogin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFuncionarioLogin.setText("jLabel37");

        javax.swing.GroupLayout jPanelNovaEntradaLayout = new javax.swing.GroupLayout(jPanelNovaEntrada);
        jPanelNovaEntrada.setLayout(jPanelNovaEntradaLayout);
        jPanelNovaEntradaLayout.setHorizontalGroup(
            jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovaEntradaLayout.createSequentialGroup()
                .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovaEntradaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelNovaEntradaLayout.createSequentialGroup()
                                    .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17))
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelNovaEntradaLayout.createSequentialGroup()
                                            .addComponent(jTextFieldQuantidadeEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldNovaEntradaUnidadeQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                                        .addComponent(jTextFieldLoteEntrada)))
                                .addGroup(jPanelNovaEntradaLayout.createSequentialGroup()
                                    .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel18))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxFornecedorEntrada, 0, 161, Short.MAX_VALUE)
                                        .addComponent(jComboBoxMateriaPrimaEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(jPanelNovaEntradaLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabelFuncionarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelNovaEntradaLayout.setVerticalGroup(
            jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovaEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelFuncionarioLogin)
                .addGap(27, 27, 27)
                .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxFornecedorEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jComboBoxMateriaPrimaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextFieldLoteEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldQuantidadeEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNovaEntradaUnidadeQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jButtonGuardarNovaEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGuardarNovaEntrada.setText("Guardar");
        jButtonGuardarNovaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarNovaEntradaActionPerformed(evt);
            }
        });

        jButtonSairNovaEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairNovaEntrada.setText("Fechar");
        jButtonSairNovaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairNovaEntradaActionPerformed(evt);
            }
        });

        jButtonDesvloquearPanel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/desbloquear.png"))); // NOI18N
        jButtonDesvloquearPanel.setText("Desbloquear");
        jButtonDesvloquearPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesvloquearPanelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBotoesNovaEntradaLayout = new javax.swing.GroupLayout(jPanelBotoesNovaEntrada);
        jPanelBotoesNovaEntrada.setLayout(jPanelBotoesNovaEntradaLayout);
        jPanelBotoesNovaEntradaLayout.setHorizontalGroup(
            jPanelBotoesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesNovaEntradaLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jButtonGuardarNovaEntrada)
                .addGap(18, 18, 18)
                .addComponent(jButtonSairNovaEntrada)
                .addGap(18, 18, 18)
                .addComponent(jButtonDesvloquearPanel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBotoesNovaEntradaLayout.setVerticalGroup(
            jPanelBotoesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotoesNovaEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBotoesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarNovaEntrada)
                    .addComponent(jButtonSairNovaEntrada)
                    .addComponent(jButtonDesvloquearPanel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelADevolverNovaEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder("A Devolver"));

        jLabelQuantidadeADevolver.setText("Quantidade a Devolver ?");

        javax.swing.GroupLayout jPanelADevolverNovaEntradaLayout = new javax.swing.GroupLayout(jPanelADevolverNovaEntrada);
        jPanelADevolverNovaEntrada.setLayout(jPanelADevolverNovaEntradaLayout);
        jPanelADevolverNovaEntradaLayout.setHorizontalGroup(
            jPanelADevolverNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelADevolverNovaEntradaLayout.createSequentialGroup()
                .addGroup(jPanelADevolverNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelADevolverNovaEntradaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldQuantidadeADevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUnidadeQuantidadeADevolver, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelADevolverNovaEntradaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelQuantidadeADevolver)))
                .addContainerGap())
        );
        jPanelADevolverNovaEntradaLayout.setVerticalGroup(
            jPanelADevolverNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelADevolverNovaEntradaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabelQuantidadeADevolver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelADevolverNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQuantidadeADevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUnidadeQuantidadeADevolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanelConformidadesNovaEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder("Conformidades"));

        jLabel19.setText("Temperatura");

        jLabel20.setText("Data Validade");

        jLabel21.setText("Caract. Orgonolepticas");

        jLabel22.setText("Embalagem/Transporte");

        jComboBoxTemperaturaEntrada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Opção--", "C", "NC" }));

        jComboBoxDataValidadeEntrada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Opção--", "C", "NC" }));

        jComboBoxCaraOrgonolepticasEntradas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Opção--", "C", "NC" }));

        jComboBoxEmbalagemEntradas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Opção--", "C", "NC" }));

        javax.swing.GroupLayout jPanelConformidadesNovaEntradaLayout = new javax.swing.GroupLayout(jPanelConformidadesNovaEntrada);
        jPanelConformidadesNovaEntrada.setLayout(jPanelConformidadesNovaEntradaLayout);
        jPanelConformidadesNovaEntradaLayout.setHorizontalGroup(
            jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConformidadesNovaEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addGap(26, 26, 26)
                .addGroup(jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxTemperaturaEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxDataValidadeEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxCaraOrgonolepticasEntradas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxEmbalagemEntradas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConformidadesNovaEntradaLayout.setVerticalGroup(
            jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConformidadesNovaEntradaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jComboBoxTemperaturaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jComboBoxDataValidadeEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jComboBoxCaraOrgonolepticasEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelConformidadesNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBoxEmbalagemEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setText("Nova Entrada");

        javax.swing.GroupLayout jDialogNovaEntradaLayout = new javax.swing.GroupLayout(jDialogNovaEntrada.getContentPane());
        jDialogNovaEntrada.getContentPane().setLayout(jDialogNovaEntradaLayout);
        jDialogNovaEntradaLayout.setHorizontalGroup(
            jDialogNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogNovaEntradaLayout.createSequentialGroup()
                .addGroup(jDialogNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialogNovaEntradaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanelNovaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jDialogNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelConformidadesNovaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelADevolverNovaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
                    .addGroup(jDialogNovaEntradaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelBotoesNovaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72))
            .addGroup(jDialogNovaEntradaLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jLabel35)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogNovaEntradaLayout.setVerticalGroup(
            jDialogNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovaEntradaLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addGroup(jDialogNovaEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogNovaEntradaLayout.createSequentialGroup()
                        .addComponent(jPanelConformidadesNovaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelADevolverNovaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelNovaEntrada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotoesNovaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jDialogNovaMateriaPrima.setTitle("NOVA MAT. PRIMA");
        jDialogNovaMateriaPrima.setMinimumSize(new java.awt.Dimension(332, 421));

        jLabelNomeMateriaPrima.setText("Nome");

        jLabelDescricaoMateriaPrima.setText("Descricao");

        jLabelUnidadeMateriaPrima.setText("Unidade");

        jTextAreaDescricaoMateriaPrima.setColumns(20);
        jTextAreaDescricaoMateriaPrima.setLineWrap(true);
        jTextAreaDescricaoMateriaPrima.setRows(4);
        jTextAreaDescricaoMateriaPrima.setMinimumSize(new java.awt.Dimension(4, 15));
        jTextAreaDescricaoMateriaPrima.setPreferredSize(new java.awt.Dimension(150, 94));
        jScrollPaneDescricaoMateriaPrima.setViewportView(jTextAreaDescricaoMateriaPrima);

        jButtonGuardarMateriaPrima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGuardarMateriaPrima.setText("Guardar");
        jButtonGuardarMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarMateriaPrimaActionPerformed(evt);
            }
        });

        jButtonSairMateriaPrima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairMateriaPrima.setText("Fechar");
        jButtonSairMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairMateriaPrimaActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Nova Materia Prima");

        javax.swing.GroupLayout jPanelNovaMateriaPrimaLayout = new javax.swing.GroupLayout(jPanelNovaMateriaPrima);
        jPanelNovaMateriaPrima.setLayout(jPanelNovaMateriaPrimaLayout);
        jPanelNovaMateriaPrimaLayout.setHorizontalGroup(
            jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovaMateriaPrimaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescricaoMateriaPrima)
                    .addGroup(jPanelNovaMateriaPrimaLayout.createSequentialGroup()
                        .addComponent(jLabelUnidadeMateriaPrima)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldUnidadeMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelNovaMateriaPrimaLayout.createSequentialGroup()
                        .addComponent(jLabelNomeMateriaPrima)
                        .addGap(29, 29, 29)
                        .addGroup(jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNovaMateriaPrimaLayout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(56, 56, 56))))
                    .addGroup(jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelNovaMateriaPrimaLayout.createSequentialGroup()
                            .addComponent(jButtonGuardarMateriaPrima)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSairMateriaPrima))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelNovaMateriaPrimaLayout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(jScrollPaneDescricaoMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanelNovaMateriaPrimaLayout.setVerticalGroup(
            jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovaMateriaPrimaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(13, 13, 13)
                .addGroup(jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeMateriaPrima)
                    .addComponent(jTextFieldNomeMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabelDescricaoMateriaPrima)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPaneDescricaoMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUnidadeMateriaPrima)
                    .addComponent(jTextFieldUnidadeMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarMateriaPrima)
                    .addComponent(jButtonSairMateriaPrima))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jDialogNovaMateriaPrimaLayout = new javax.swing.GroupLayout(jDialogNovaMateriaPrima.getContentPane());
        jDialogNovaMateriaPrima.getContentPane().setLayout(jDialogNovaMateriaPrimaLayout);
        jDialogNovaMateriaPrimaLayout.setHorizontalGroup(
            jDialogNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovaMateriaPrimaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovaMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jDialogNovaMateriaPrimaLayout.setVerticalGroup(
            jDialogNovaMateriaPrimaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovaMateriaPrimaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovaMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jDialogNovoFornecedor.setTitle("Novo Forncedor");
        jDialogNovoFornecedor.setMinimumSize(new java.awt.Dimension(330, 630));

        jLabelNomeFornecedor.setText("Nome");

        jLabelMoradaFornecedor.setText("Morada");

        jLabelCodPostalFornecedor.setText("Codigo Postal");

        jLabelLocalidadeForncedor.setText("Localidade");

        jLabelContactoForncedor.setText("Contacto");

        jLabelEmailFornecedor.setText("Email");

        jLabelNIFFornecedor.setText("NIF");

        jLabelTipoProduto.setText("Tipo Produto");

        jLabel24.setText("Estado");

        jTextFieldCodPostalFornecedor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldCodPostalFornecedorCaretUpdate(evt);
            }
        });
        jTextFieldCodPostalFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodPostalFornecedorActionPerformed(evt);
            }
        });

        jTextFieldTipoProdutoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTipoProdutoFornecedorActionPerformed(evt);
            }
        });

        jButtonGuardarNovoFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGuardarNovoFornecedor.setText("Guardar");
        jButtonGuardarNovoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarNovoFornecedorActionPerformed(evt);
            }
        });

        jButtonSairNovoFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairNovoFornecedor.setText("Voltar");
        jButtonSairNovoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairNovoFornecedorActionPerformed(evt);
            }
        });

        jLabelNovoFornecedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNovoFornecedor.setText("Novo Fornecedor");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 0, 51));
        jLabel25.setText("*");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 51));
        jLabel26.setText("*");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 51));
        jLabel27.setText("*");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 51));
        jLabel28.setText("*");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setText("*");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 51));
        jLabel30.setText("*");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 51));
        jLabel31.setText("*");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 0, 51));
        jLabel32.setText("* - Campos Obrigatorios");

        jComboBoxEstadoFornecedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ativo", "Inativo" }));

        jButtonUpdateFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/atualizar.png"))); // NOI18N
        jButtonUpdateFornecedor.setText("Atualizar");
        jButtonUpdateFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateFornecedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNovoFornecedorLayout = new javax.swing.GroupLayout(jPanelNovoFornecedor);
        jPanelNovoFornecedor.setLayout(jPanelNovoFornecedorLayout);
        jPanelNovoFornecedorLayout.setHorizontalGroup(
            jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabelNovoFornecedor))
                    .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                                        .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelLocalidadeForncedor)
                                            .addComponent(jLabelCodPostalFornecedor)
                                            .addComponent(jLabelContactoForncedor)
                                            .addComponent(jLabelEmailFornecedor)
                                            .addComponent(jLabelNIFFornecedor)
                                            .addComponent(jLabelTipoProduto)
                                            .addComponent(jLabel24))
                                        .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextFieldLocalidadeFornecedor)
                                                    .addComponent(jTextFieldContactoFornecedor)
                                                    .addComponent(jTextFieldEmailFornecedor)
                                                    .addComponent(jTextFieldNIFFornecedor)
                                                    .addComponent(jTextFieldTipoProdutoFornecedor)
                                                    .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                                                        .addComponent(jComboBoxEstadoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, Short.MAX_VALUE))))
                                            .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(jTextFieldCodPostalFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelNomeFornecedor)
                                            .addComponent(jLabelMoradaFornecedor))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldMoradaFornecedor)
                                            .addComponent(jTextFieldNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                                .addComponent(jButtonGuardarNovoFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonUpdateFornecedor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSairNovoFornecedor)
                                .addGap(10, 10, 10))))
                    .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelNovoFornecedorLayout.setVerticalGroup(
            jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoFornecedorLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelNovoFornecedor)
                .addGap(29, 29, 29)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeFornecedor)
                    .addComponent(jTextFieldNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMoradaFornecedor)
                    .addComponent(jTextFieldMoradaFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodPostalFornecedor)
                    .addComponent(jTextFieldCodPostalFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLocalidadeForncedor)
                    .addComponent(jTextFieldLocalidadeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContactoForncedor)
                    .addComponent(jTextFieldContactoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmailFornecedor)
                    .addComponent(jTextFieldEmailFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNIFFornecedor)
                    .addComponent(jTextFieldNIFFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipoProduto)
                    .addComponent(jTextFieldTipoProdutoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jComboBoxEstadoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarNovoFornecedor)
                    .addComponent(jButtonSairNovoFornecedor)
                    .addComponent(jButtonUpdateFornecedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogNovoFornecedorLayout = new javax.swing.GroupLayout(jDialogNovoFornecedor.getContentPane());
        jDialogNovoFornecedor.getContentPane().setLayout(jDialogNovoFornecedorLayout);
        jDialogNovoFornecedorLayout.setHorizontalGroup(
            jDialogNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoFornecedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogNovoFornecedorLayout.setVerticalGroup(
            jDialogNovoFornecedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoFornecedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovoFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jDialogNovoInsectocacador.setTitle("NOVO INSECTOCAÇADOR");
        jDialogNovoInsectocacador.setMinimumSize(new java.awt.Dimension(352, 306));

        jLabelNome.setText("Nome");

        jLabelReferencia.setText("Referencia");

        jLabelLocal.setText("Local");

        jTextFieldReferenciaInsecto.setEditable(false);

        jTextFieldNomeInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeInsectoActionPerformed(evt);
            }
        });

        jLabel1.setText("Novo Insectocaçador");

        jButtonGuardarInsecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGuardarInsecto.setText("Guardar");
        jButtonGuardarInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarInsectoActionPerformed(evt);
            }
        });

        jButtonSairInsecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairInsecto.setText("Sair");
        jButtonSairInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairInsectoActionPerformed(evt);
            }
        });

        jButtonActualizarInsecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/atualizar.png"))); // NOI18N
        jButtonActualizarInsecto.setText("Actualizar (Campos)");
        jButtonActualizarInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarInsectoActionPerformed(evt);
            }
        });

        jButtonAlterarReferencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/atualizar.png"))); // NOI18N
        jButtonAlterarReferencia.setText("Alterar Referência");
        jButtonAlterarReferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarReferenciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNovoInsectocacadorLayout = new javax.swing.GroupLayout(jPanelNovoInsectocacador);
        jPanelNovoInsectocacador.setLayout(jPanelNovoInsectocacadorLayout);
        jPanelNovoInsectocacadorLayout.setHorizontalGroup(
            jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                        .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelReferencia)
                                    .addComponent(jLabelNome)
                                    .addComponent(jLabelLocal))
                                .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jTextFieldLocalInsecto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNovoInsectocacadorLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldReferenciaInsecto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldNomeInsecto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                                .addComponent(jButtonGuardarInsecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonActualizarInsecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSairInsecto))
                            .addComponent(jButtonAlterarReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelNovoInsectocacadorLayout.setVerticalGroup(
            jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNovoInsectocacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelReferencia)
                    .addComponent(jTextFieldReferenciaInsecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovoInsectocacadorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabelNome)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelNovoInsectocacadorLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeInsecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLocal)
                    .addComponent(jTextFieldLocalInsecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanelNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarInsecto)
                    .addComponent(jButtonSairInsecto)
                    .addComponent(jButtonActualizarInsecto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterarReferencia))
        );

        javax.swing.GroupLayout jDialogNovoInsectocacadorLayout = new javax.swing.GroupLayout(jDialogNovoInsectocacador.getContentPane());
        jDialogNovoInsectocacador.getContentPane().setLayout(jDialogNovoInsectocacadorLayout);
        jDialogNovoInsectocacadorLayout.setHorizontalGroup(
            jDialogNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoInsectocacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovoInsectocacador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogNovoInsectocacadorLayout.setVerticalGroup(
            jDialogNovoInsectocacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoInsectocacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovoInsectocacador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogNovoControloResultados.setTitle("NOVO CONTROLO DE RESULTADOS");
        jDialogNovoControloResultados.setMinimumSize(new java.awt.Dimension(490, 680));

        jPanelNovoControloResultdos.setBorder(javax.swing.BorderFactory.createTitledBorder("Controlo de Resultados"));

        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- TIPO--", "CONT. PRAGAS", "EQUIPAMENTO", "ENTRADA", "Outro" }));
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });

        jComboBoxOpcao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Opção--" }));
        jComboBoxOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOpcaoActionPerformed(evt);
            }
        });

        jLabelTipo.setText("Tipo");

        jLabelOpcao.setText("Opção");

        jLabelResultado.setText("Resultado");

        jLabelFichaTecnica.setText("Ficha Tecnica");

        jLabelDescricao.setText("Problema");

        jComboBoxResultado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "C", "NC" }));
        jComboBoxResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxResultadoActionPerformed(evt);
            }
        });

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setLineWrap(true);
        jTextAreaDescricao.setRows(5);
        jScrollPaneDescricao.setViewportView(jTextAreaDescricao);

        jLabel2.setText("C - Conforme | NC - Não Conforme");

        jLabel6.setText("UPLOAD");

        jButtonInserir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonInserir.setText("Guardar");
        jButtonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInserirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonCancelar.setText("Fechar");
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
                .addComponent(jTextFieldOutraOpcao, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
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

        jLabelFuncionario.setText("Funcionario:");

        jLabelFuncionarioLogin2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFuncionarioLogin2.setText("jLabel37");

        javax.swing.GroupLayout jPanelNovoControloResultdosLayout = new javax.swing.GroupLayout(jPanelNovoControloResultdos);
        jPanelNovoControloResultdos.setLayout(jPanelNovoControloResultdosLayout);
        jPanelNovoControloResultdosLayout.setHorizontalGroup(
            jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addComponent(jLabelFuncionario)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelFuncionarioLogin2))
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jButtonInserir)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCancelar)))
                        .addContainerGap(71, Short.MAX_VALUE))
                    .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTipo)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelOpcao)
                            .addComponent(jPanelOutraOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addComponent(jComboBoxOpcao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                        .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addComponent(jLabelFichaTecnica)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel6))
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabelResultado)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabelDescricao)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPaneDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelNovoControloResultdosLayout.setVerticalGroup(
            jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoControloResultdosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFuncionario)
                    .addComponent(jLabelFuncionarioLogin2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipo)
                    .addComponent(jLabelOpcao))
                .addGap(4, 4, 4)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelOutraOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResultado)
                    .addComponent(jComboBoxResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFichaTecnica)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescricao)
                    .addComponent(jScrollPaneDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanelNovoControloResultdosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInserir)
                    .addComponent(jButtonCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogNovoControloResultadosLayout = new javax.swing.GroupLayout(jDialogNovoControloResultados.getContentPane());
        jDialogNovoControloResultados.getContentPane().setLayout(jDialogNovoControloResultadosLayout);
        jDialogNovoControloResultadosLayout.setHorizontalGroup(
            jDialogNovoControloResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoControloResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovoControloResultdos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jDialogNovoControloResultadosLayout.setVerticalGroup(
            jDialogNovoControloResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoControloResultadosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanelNovoControloResultdos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jDialogNaoConformidades.setTitle("NOVA NÃO CONFORMIDADE");
        jDialogNaoConformidades.setMinimumSize(new java.awt.Dimension(730, 710));

        jPanelNaoConformidades.setBorder(javax.swing.BorderFactory.createTitledBorder("Não Conformidade - a preencher"));
        jPanelNaoConformidades.setMinimumSize(new java.awt.Dimension(200, 150));

        jLabelOcorrecniaNaoConformidade.setText("Detalhes");

        jLabelMedidaCorrectivaNaoConformidade.setText("Medida Correctiva");

        jTextAreaOcorrenciaNaoConformidade.setColumns(20);
        jTextAreaOcorrenciaNaoConformidade.setLineWrap(true);
        jTextAreaOcorrenciaNaoConformidade.setRows(5);
        jTextAreaOcorrenciaNaoConformidade.setPreferredSize(new java.awt.Dimension(164, 100));
        jScrollPaneOcorrencia.setViewportView(jTextAreaOcorrenciaNaoConformidade);

        jTextAreaMedidaCorrctivaNaoConformidade.setColumns(20);
        jTextAreaMedidaCorrctivaNaoConformidade.setLineWrap(true);
        jTextAreaMedidaCorrctivaNaoConformidade.setRows(5);
        jScrollPanemedidaCorrectiva.setViewportView(jTextAreaMedidaCorrctivaNaoConformidade);

        jButtonGravarNaoConformidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGravarNaoConformidade.setText("Guardar");
        jButtonGravarNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarNaoConformidadeActionPerformed(evt);
            }
        });

        jButtonCancelarNaoConformidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonCancelarNaoConformidade.setText("Fechar");
        jButtonCancelarNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarNaoConformidadeActionPerformed(evt);
            }
        });

        jLabel7.setText("Funcionario Responsavel");

        jComboBoxNaoConformidadeFuncionarioResponsav.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Funcionario--" }));

        jLabel.setText("Funcionario");

        jLabelFuncionarioLogin4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFuncionarioLogin4.setText("jLabel37");

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
                                .addComponent(jLabelOcorrecniaNaoConformidade))
                            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelFuncionarioLogin4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jComboBoxNaoConformidadeFuncionarioResponsav, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButtonGravarNaoConformidade)
                        .addGap(82, 82, 82)
                        .addComponent(jButtonCancelarNaoConformidade)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanelNaoConformidadesLayout.setVerticalGroup(
            jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNaoConformidadesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel)
                    .addComponent(jLabelFuncionarioLogin4))
                .addGap(56, 56, 56)
                .addComponent(jLabelOcorrecniaNaoConformidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneOcorrencia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelMedidaCorrectivaNaoConformidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPanemedidaCorrectiva, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxNaoConformidadeFuncionarioResponsav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanelNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGravarNaoConformidade)
                    .addComponent(jButtonCancelarNaoConformidade))
                .addGap(67, 67, 67))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("NÃO CONFORMIDADE");

        jPanelVerMensagemProblema.setBorder(javax.swing.BorderFactory.createTitledBorder("Problema do Controlo de Resultado"));

        jTextAreaProblemadoControlo.setEditable(false);
        jTextAreaProblemadoControlo.setColumns(20);
        jTextAreaProblemadoControlo.setLineWrap(true);
        jTextAreaProblemadoControlo.setRows(5);
        jScrollPane7.setViewportView(jTextAreaProblemadoControlo);

        javax.swing.GroupLayout jPanelVerMensagemProblemaLayout = new javax.swing.GroupLayout(jPanelVerMensagemProblema);
        jPanelVerMensagemProblema.setLayout(jPanelVerMensagemProblemaLayout);
        jPanelVerMensagemProblemaLayout.setHorizontalGroup(
            jPanelVerMensagemProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVerMensagemProblemaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelVerMensagemProblemaLayout.setVerticalGroup(
            jPanelVerMensagemProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVerMensagemProblemaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Secção"));

        jTextFieldVerSeccaoDoCR.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldVerSeccaoDoCR)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldVerSeccaoDoCR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogNaoConformidadesLayout = new javax.swing.GroupLayout(jDialogNaoConformidades.getContentPane());
        jDialogNaoConformidades.getContentPane().setLayout(jDialogNaoConformidadesLayout);
        jDialogNaoConformidadesLayout.setHorizontalGroup(
            jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                .addGroup(jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel8))
                    .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelVerMensagemProblema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(jPanelNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jDialogNaoConformidadesLayout.setVerticalGroup(
            jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGroup(jDialogNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogNaoConformidadesLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelVerMensagemProblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogMedidasCorrectiva.setTitle("NOVA MEDIDA CORRECTIVA");
        jDialogMedidasCorrectiva.setMinimumSize(new java.awt.Dimension(900, 550));

        jLabelMedidasCorrectivas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelMedidasCorrectivas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/medida_correctiva.png"))); // NOI18N
        jLabelMedidasCorrectivas.setText("Medida Correctiva");

        jPanelMedidasCorrectivas.setBorder(javax.swing.BorderFactory.createTitledBorder("Media Correctica da Não Conformidade"));

        jLabelObservacaoMedidasCorrectivas.setText("Observação");

        jScrollPane2.setMinimumSize(new java.awt.Dimension(200, 150));

        jTextAreaObservacaoMedidaCorrectiva.setColumns(20);
        jTextAreaObservacaoMedidaCorrectiva.setLineWrap(true);
        jTextAreaObservacaoMedidaCorrectiva.setRows(5);
        jScrollPane2.setViewportView(jTextAreaObservacaoMedidaCorrectiva);

        jButtonGravarMedidaCorrectiva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGravarMedidaCorrectiva.setText("Resolvida");
        jButtonGravarMedidaCorrectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarMedidaCorrectivaActionPerformed(evt);
            }
        });

        jButtonCancelarMedidaCorrectiva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonCancelarMedidaCorrectiva.setText("Voltar");
        jButtonCancelarMedidaCorrectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarMedidaCorrectivaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMedidasCorrectivasLayout = new javax.swing.GroupLayout(jPanelMedidasCorrectivas);
        jPanelMedidasCorrectivas.setLayout(jPanelMedidasCorrectivasLayout);
        jPanelMedidasCorrectivasLayout.setHorizontalGroup(
            jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMedidasCorrectivasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelObservacaoMedidasCorrectivas)
                    .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                                .addComponent(jButtonGravarMedidaCorrectiva)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonCancelarMedidaCorrectiva))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43))
        );
        jPanelMedidasCorrectivasLayout.setVerticalGroup(
            jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMedidasCorrectivasLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelObservacaoMedidasCorrectivas)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(jPanelMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGravarMedidaCorrectiva, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelarMedidaCorrectiva))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jPanelInformacaoAdicional.setBorder(javax.swing.BorderFactory.createTitledBorder("Infor. Adicional"));

        jPanelInformacaoNaoConformidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Não Conformidade"));

        jLabel40.setText("Detalhes :");

        jLabel41.setText("Medida Correctiva :");

        jTextAreaDetalhesMC.setEditable(false);
        jTextAreaDetalhesMC.setColumns(20);
        jTextAreaDetalhesMC.setLineWrap(true);
        jTextAreaDetalhesMC.setRows(5);
        jScrollPane10.setViewportView(jTextAreaDetalhesMC);

        jTextAreaMedidasCorrectivasMC.setEditable(false);
        jTextAreaMedidasCorrectivasMC.setColumns(20);
        jTextAreaMedidasCorrectivasMC.setLineWrap(true);
        jTextAreaMedidasCorrectivasMC.setRows(5);
        jScrollPane11.setViewportView(jTextAreaMedidasCorrectivasMC);

        javax.swing.GroupLayout jPanelInformacaoNaoConformidadeLayout = new javax.swing.GroupLayout(jPanelInformacaoNaoConformidade);
        jPanelInformacaoNaoConformidade.setLayout(jPanelInformacaoNaoConformidadeLayout);
        jPanelInformacaoNaoConformidadeLayout.setHorizontalGroup(
            jPanelInformacaoNaoConformidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacaoNaoConformidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformacaoNaoConformidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanelInformacaoNaoConformidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanelInformacaoNaoConformidadeLayout.setVerticalGroup(
            jPanelInformacaoNaoConformidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacaoNaoConformidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformacaoNaoConformidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelInformacaoNaoConformidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelInformacaoControloResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Do Controlo de Resultado"));

        jLabel38.setText("Secção :");

        jLabel39.setText("Problema :");

        jTextFieldSeccaoMC.setEditable(false);

        jTextAreaProblemaMC.setEditable(false);
        jTextAreaProblemaMC.setColumns(20);
        jTextAreaProblemaMC.setLineWrap(true);
        jTextAreaProblemaMC.setRows(5);
        jScrollPane9.setViewportView(jTextAreaProblemaMC);

        javax.swing.GroupLayout jPanelInformacaoControloResultadoLayout = new javax.swing.GroupLayout(jPanelInformacaoControloResultado);
        jPanelInformacaoControloResultado.setLayout(jPanelInformacaoControloResultadoLayout);
        jPanelInformacaoControloResultadoLayout.setHorizontalGroup(
            jPanelInformacaoControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacaoControloResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformacaoControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSeccaoMC, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(jPanelInformacaoControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanelInformacaoControloResultadoLayout.setVerticalGroup(
            jPanelInformacaoControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInformacaoControloResultadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelInformacaoControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInformacaoControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelInformacaoControloResultadoLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jTextFieldSeccaoMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInformacaoControloResultadoLayout.createSequentialGroup()
                            .addComponent(jLabel38)
                            .addGap(31, 31, 31)))
                    .addGroup(jPanelInformacaoControloResultadoLayout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelInformacaoAdicionalLayout = new javax.swing.GroupLayout(jPanelInformacaoAdicional);
        jPanelInformacaoAdicional.setLayout(jPanelInformacaoAdicionalLayout);
        jPanelInformacaoAdicionalLayout.setHorizontalGroup(
            jPanelInformacaoAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacaoAdicionalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformacaoAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInformacaoControloResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelInformacaoNaoConformidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanelInformacaoAdicionalLayout.setVerticalGroup(
            jPanelInformacaoAdicionalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInformacaoAdicionalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelInformacaoControloResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelInformacaoNaoConformidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogMedidasCorrectivaLayout = new javax.swing.GroupLayout(jDialogMedidasCorrectiva.getContentPane());
        jDialogMedidasCorrectiva.getContentPane().setLayout(jDialogMedidasCorrectivaLayout);
        jDialogMedidasCorrectivaLayout.setHorizontalGroup(
            jDialogMedidasCorrectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMedidasCorrectivaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelInformacaoAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelMedidasCorrectivas, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogMedidasCorrectivaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelMedidasCorrectivas)
                .addGap(321, 321, 321))
        );
        jDialogMedidasCorrectivaLayout.setVerticalGroup(
            jDialogMedidasCorrectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogMedidasCorrectivaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelMedidasCorrectivas)
                .addGap(18, 18, 18)
                .addGroup(jDialogMedidasCorrectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelInformacaoAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelMedidasCorrectivas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jDialogNovaManutencaoEquipamento.setTitle("NOVA MANUTENCAO DE EQUIPAMENTO");
        jDialogNovaManutencaoEquipamento.setMinimumSize(new java.awt.Dimension(360, 510));

        jPanelManutencaoEquipamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Nova Mnutenção de Equipamento"));

        jLabel3.setText("Observações");

        jLabel4.setText("Ficha Tecnica");

        jTextAreaObservacaoManutencaoEquipameto.setColumns(20);
        jTextAreaObservacaoManutencaoEquipameto.setLineWrap(true);
        jTextAreaObservacaoManutencaoEquipameto.setRows(5);
        jScrollPaneObservacaManuetencaoEquipametno.setViewportView(jTextAreaObservacaoManutencaoEquipameto);

        jButtonGravarManutencaoEquipametno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGravarManutencaoEquipametno.setText("Guardar");
        jButtonGravarManutencaoEquipametno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarManutencaoEquipametnoActionPerformed(evt);
            }
        });

        jButtonSairManutencaoEquipamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairManutencaoEquipamento.setText("Fechar");
        jButtonSairManutencaoEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairManutencaoEquipamentoActionPerformed(evt);
            }
        });

        jLabelFuncionarioResponsavel1.setText("Funcionario:");

        jLabelFuncionariologin3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFuncionariologin3.setText("jLabel37");

        jPanelEquipamentoSeleccionado.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipamento Seleccionado"));

        jTextFieldNomeEquipamentoSeleccionado.setEditable(false);
        jTextFieldNomeEquipamentoSeleccionado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanelEquipamentoSeleccionadoLayout = new javax.swing.GroupLayout(jPanelEquipamentoSeleccionado);
        jPanelEquipamentoSeleccionado.setLayout(jPanelEquipamentoSeleccionadoLayout);
        jPanelEquipamentoSeleccionadoLayout.setHorizontalGroup(
            jPanelEquipamentoSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEquipamentoSeleccionadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldNomeEquipamentoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelEquipamentoSeleccionadoLayout.setVerticalGroup(
            jPanelEquipamentoSeleccionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEquipamentoSeleccionadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldNomeEquipamentoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelManutencaoEquipamentoLayout = new javax.swing.GroupLayout(jPanelManutencaoEquipamento);
        jPanelManutencaoEquipamento.setLayout(jPanelManutencaoEquipamentoLayout);
        jPanelManutencaoEquipamentoLayout.setHorizontalGroup(
            jPanelManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManutencaoEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelEquipamentoSeleccionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelManutencaoEquipamentoLayout.createSequentialGroup()
                        .addComponent(jButtonGravarManutencaoEquipametno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSairManutencaoEquipamento))
                    .addGroup(jPanelManutencaoEquipamentoLayout.createSequentialGroup()
                        .addGroup(jPanelManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanelManutencaoEquipamentoLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPaneObservacaManuetencaoEquipametno, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelManutencaoEquipamentoLayout.createSequentialGroup()
                                .addComponent(jLabelFuncionarioResponsavel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelFuncionariologin3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelManutencaoEquipamentoLayout.setVerticalGroup(
            jPanelManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelManutencaoEquipamentoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelEquipamentoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFuncionarioResponsavel1)
                    .addComponent(jLabelFuncionariologin3))
                .addGap(18, 18, 18)
                .addGroup(jPanelManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanelManutencaoEquipamentoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPaneObservacaManuetencaoEquipametno, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addGap(40, 40, 40)
                .addGroup(jPanelManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGravarManutencaoEquipametno)
                    .addComponent(jButtonSairManutencaoEquipamento))
                .addGap(351, 351, 351))
        );

        javax.swing.GroupLayout jDialogNovaManutencaoEquipamentoLayout = new javax.swing.GroupLayout(jDialogNovaManutencaoEquipamento.getContentPane());
        jDialogNovaManutencaoEquipamento.getContentPane().setLayout(jDialogNovaManutencaoEquipamentoLayout);
        jDialogNovaManutencaoEquipamentoLayout.setHorizontalGroup(
            jDialogNovaManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovaManutencaoEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelManutencaoEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jDialogNovaManutencaoEquipamentoLayout.setVerticalGroup(
            jDialogNovaManutencaoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovaManutencaoEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelManutencaoEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        jDialogNovaLimpeza.setTitle("NOVA LIMPEZA");
        jDialogNovaLimpeza.setMinimumSize(new java.awt.Dimension(390, 240));

        jLabelFuncionarioResponsavel.setText("Funcionario Responsavel");

        jLabelData.setText("Data");

        jButtonGuardarLimpeza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGuardarLimpeza.setText("Guardar");
        jButtonGuardarLimpeza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarLimpezaActionPerformed(evt);
            }
        });

        jButtonSairLimpeza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairLimpeza.setText("Fechar");
        jButtonSairLimpeza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairLimpezaActionPerformed(evt);
            }
        });

        jLabelFuncionarioLogin5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelFuncionarioLogin5.setText("jLabel34");

        jLabelDataNovaLimpeza.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelDataNovaLimpeza.setText("jLabel15");

        javax.swing.GroupLayout jPanelNovaLimpezaLayout = new javax.swing.GroupLayout(jPanelNovaLimpeza);
        jPanelNovaLimpeza.setLayout(jPanelNovaLimpezaLayout);
        jPanelNovaLimpezaLayout.setHorizontalGroup(
            jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovaLimpezaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovaLimpezaLayout.createSequentialGroup()
                        .addGroup(jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFuncionarioResponsavel)
                            .addComponent(jLabelData, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFuncionarioLogin5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDataNovaLimpeza))
                        .addContainerGap(95, Short.MAX_VALUE))
                    .addGroup(jPanelNovaLimpezaLayout.createSequentialGroup()
                        .addComponent(jButtonGuardarLimpeza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSairLimpeza)
                        .addGap(36, 36, 36))))
        );
        jPanelNovaLimpezaLayout.setVerticalGroup(
            jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovaLimpezaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFuncionarioResponsavel)
                    .addComponent(jLabelFuncionarioLogin5))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelData)
                    .addComponent(jLabelDataNovaLimpeza))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarLimpeza)
                    .addComponent(jButtonSairLimpeza))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogNovaLimpezaLayout = new javax.swing.GroupLayout(jDialogNovaLimpeza.getContentPane());
        jDialogNovaLimpeza.getContentPane().setLayout(jDialogNovaLimpezaLayout);
        jDialogNovaLimpezaLayout.setHorizontalGroup(
            jDialogNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovaLimpezaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovaLimpeza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jDialogNovaLimpezaLayout.setVerticalGroup(
            jDialogNovaLimpezaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovaLimpezaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelNovaLimpeza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jDialogNovoEquipamento.setTitle("Novo Equipamento");
        jDialogNovoEquipamento.setMinimumSize(new java.awt.Dimension(460, 339));

        jLabelNovoEquipametno.setText("Novo Equipamento");

        jLabelNome1.setText("Nome");

        jLabelDescrição.setText("Descrição");

        jTextAreaObservacaoNovoEquipamento.setColumns(20);
        jTextAreaObservacaoNovoEquipamento.setLineWrap(true);
        jTextAreaObservacaoNovoEquipamento.setRows(5);
        jScrollPaneNovoEquipmanentoObservacao.setViewportView(jTextAreaObservacaoNovoEquipamento);

        jButtonGuardarEquipametno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGuardarEquipametno.setText("Guardar");
        jButtonGuardarEquipametno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarEquipametnoActionPerformed(evt);
            }
        });

        jButtonSairNovoEquipamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairNovoEquipamento.setText("Fechar");
        jButtonSairNovoEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairNovoEquipamentoActionPerformed(evt);
            }
        });

        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/atualizar.png"))); // NOI18N
        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelNovoEquipamentoLayout = new javax.swing.GroupLayout(jPanelNovoEquipamento);
        jPanelNovoEquipamento.setLayout(jPanelNovoEquipamentoLayout);
        jPanelNovoEquipamentoLayout.setHorizontalGroup(
            jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoEquipamentoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNovoEquipametno)
                    .addGroup(jPanelNovoEquipamentoLayout.createSequentialGroup()
                        .addGroup(jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNome1)
                            .addComponent(jLabelDescrição))
                        .addGap(21, 21, 21)
                        .addGroup(jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelNovoEquipamentoLayout.createSequentialGroup()
                                .addComponent(jButtonGuardarEquipametno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSairNovoEquipamento))
                            .addComponent(jTextFieldNomeEquipamento)
                            .addComponent(jScrollPaneNovoEquipmanentoObservacao))))
                .addContainerGap())
        );
        jPanelNovoEquipamentoLayout.setVerticalGroup(
            jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNovoEquipamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNovoEquipametno)
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome1)
                    .addComponent(jTextFieldNomeEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescrição)
                    .addComponent(jScrollPaneNovoEquipmanentoObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonGuardarEquipametno)
                        .addComponent(jButtonActualizar))
                    .addComponent(jButtonSairNovoEquipamento))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogNovoEquipamentoLayout = new javax.swing.GroupLayout(jDialogNovoEquipamento.getContentPane());
        jDialogNovoEquipamento.getContentPane().setLayout(jDialogNovoEquipamentoLayout);
        jDialogNovoEquipamentoLayout.setHorizontalGroup(
            jDialogNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoEquipamentoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanelNovoEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jDialogNovoEquipamentoLayout.setVerticalGroup(
            jDialogNovoEquipamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogNovoEquipamentoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanelNovoEquipamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        jDialogDevolucoes.setTitle("NOVA DEVOLUÇÃO");
        jDialogDevolucoes.setMinimumSize(new java.awt.Dimension(400, 505));

        jPanelDevolucoes.setToolTipText("DEVOLUÇÃO");

        jLabelFuncionarioResponsavel2.setText("Funcionario ");

        jLabelDataDevolucao.setText("Data de Devolução");

        jLabelQuantidadeDevolvida.setText("Quantidade Devolvida");

        jLabelObservação.setText("Observação");

        jDateChooserDataDevolucao.setDateFormatString("yyyy-MM-dd");

        jTextAreaObservacoesDevolucao.setColumns(20);
        jTextAreaObservacoesDevolucao.setLineWrap(true);
        jTextAreaObservacoesDevolucao.setRows(5);
        jScrollPaneObservacoesDevolucao.setViewportView(jTextAreaObservacoesDevolucao);

        jButtonGuardaDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonGuardaDevolucao.setText("Guardar");
        jButtonGuardaDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardaDevolucaoActionPerformed(evt);
            }
        });

        jButtonSairDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairDevolucao.setText("Fechar");
        jButtonSairDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairDevolucaoActionPerformed(evt);
            }
        });

        jLabelQuantidadeADevolverDevolucao.setText("Quantidade que Falta");

        jTextFieldQuantidadeADevolverDevolucao.setEditable(false);
        jTextFieldQuantidadeADevolverDevolucao.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextFieldQuantidadeADevolverDevolucao.setForeground(new java.awt.Color(255, 0, 0));

        jLabel33.setText("Devolver");

        jLabelFuncionarioLogin6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelFuncionarioLogin6.setText("jLabel37");

        javax.swing.GroupLayout jPanelDevolucoesLayout = new javax.swing.GroupLayout(jPanelDevolucoes);
        jPanelDevolucoes.setLayout(jPanelDevolucoesLayout);
        jPanelDevolucoesLayout.setHorizontalGroup(
            jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                        .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelObservação)
                            .addComponent(jLabelDataDevolucao)
                            .addComponent(jLabelQuantidadeADevolverDevolucao)
                            .addComponent(jLabel33)
                            .addComponent(jLabelQuantidadeDevolvida)
                            .addComponent(jLabelFuncionarioResponsavel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                                .addComponent(jDateChooserDataDevolucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(233, 233, 233))
                            .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                                .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldQuantidadeDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldQuantidadeADevolverDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelFuncionarioLogin6))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                                .addComponent(jButtonGuardaDevolucao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSairDevolucao))
                            .addComponent(jScrollPaneObservacoesDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelDevolucoesLayout.setVerticalGroup(
            jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFuncionarioResponsavel2)
                    .addComponent(jLabelFuncionarioLogin6))
                .addGap(18, 18, 18)
                .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelDataDevolucao)
                    .addComponent(jDateChooserDataDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabelQuantidadeADevolverDevolucao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33))
                    .addGroup(jPanelDevolucoesLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jTextFieldQuantidadeADevolverDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuantidadeDevolvida)
                    .addComponent(jTextFieldQuantidadeDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabelObservação)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneObservacoesDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardaDevolucao)
                    .addComponent(jButtonSairDevolucao))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogDevolucoesLayout = new javax.swing.GroupLayout(jDialogDevolucoes.getContentPane());
        jDialogDevolucoes.getContentPane().setLayout(jDialogDevolucoesLayout);
        jDialogDevolucoesLayout.setHorizontalGroup(
            jDialogDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogDevolucoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jDialogDevolucoesLayout.setVerticalGroup(
            jDialogDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogDevolucoesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanelDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jDialogConsultaInsectocacadores.setTitle("CONSULTA INSECTOCADORES");
        jDialogConsultaInsectocacadores.setMinimumSize(new java.awt.Dimension(850, 480));

        jTableConsultaInsecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Referencia", "Nome", "Local"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneConsultInsecto.setViewportView(jTableConsultaInsecto);

        jButtonAddNovoInsecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonAddNovoInsecto.setText("Novo Insecto.");
        jButtonAddNovoInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNovoInsectoActionPerformed(evt);
            }
        });

        jButtonSairConsultaInsecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairConsultaInsecto.setText("Fechar");
        jButtonSairConsultaInsecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairConsultaInsectoActionPerformed(evt);
            }
        });

        jButtonNovaLimpeza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Limpeza.png"))); // NOI18N
        jButtonNovaLimpeza.setText("Nova Limpeza");
        jButtonNovaLimpeza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaLimpezaActionPerformed(evt);
            }
        });

        jButtonConsultarLimpezas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consulta.png"))); // NOI18N
        jButtonConsultarLimpezas.setText("Consultar Limpezas");
        jButtonConsultarLimpezas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarLimpezasActionPerformed(evt);
            }
        });

        jButtonEditarInsectoca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jButtonEditarInsectoca.setText("Editar");
        jButtonEditarInsectoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarInsectocaActionPerformed(evt);
            }
        });

        jButtonControloResultadosInsectoca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Nao Conformidades.png"))); // NOI18N
        jButtonControloResultadosInsectoca.setText("Controlo Resultados");
        jButtonControloResultadosInsectoca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonControloResultadosInsectocaActionPerformed(evt);
            }
        });

        jTextFieldPesquisaInsectocacador.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldPesquisaInsectocacadorCaretUpdate(evt);
            }
        });

        jLabelPesquisarInsectocacador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabelPesquisarInsectocacador.setText("Pesquisar");

        jButtonVerInsectocacadoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png"))); // NOI18N
        jButtonVerInsectocacadoresInativos.setText("Ver Inativas");
        jButtonVerInsectocacadoresInativos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerInsectocacadoresInativosActionPerformed(evt);
            }
        });

        jButtonAlterarEstadoInsectocacadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/trocar.png"))); // NOI18N
        jButtonAlterarEstadoInsectocacadores.setText("Alterar Estado");
        jButtonAlterarEstadoInsectocacadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarEstadoInsectocacadoresActionPerformed(evt);
            }
        });

        jLabelConsultaControloPragas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelConsultaControloPragas.setText("Consulta Controlo de Pragas - Ativos");

        javax.swing.GroupLayout jPanelConsultaInsectoLayout = new javax.swing.GroupLayout(jPanelConsultaInsecto);
        jPanelConsultaInsecto.setLayout(jPanelConsultaInsectoLayout);
        jPanelConsultaInsectoLayout.setHorizontalGroup(
            jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaInsectoLayout.createSequentialGroup()
                .addGroup(jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaInsectoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPaneConsultInsecto)
                            .addGroup(jPanelConsultaInsectoLayout.createSequentialGroup()
                                .addComponent(jButtonVerInsectocacadoresInativos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAddNovoInsecto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonNovaLimpeza)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSairConsultaInsecto))
                            .addGroup(jPanelConsultaInsectoLayout.createSequentialGroup()
                                .addGroup(jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPesquisaInsectocacador, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelPesquisarInsectocacador))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonConsultarLimpezas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonEditarInsectoca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAlterarEstadoInsectocacadores)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonControloResultadosInsectoca))))
                    .addGroup(jPanelConsultaInsectoLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(jLabelConsultaControloPragas)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelConsultaInsectoLayout.setVerticalGroup(
            jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaInsectoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConsultaControloPragas)
                .addGap(16, 16, 16)
                .addGroup(jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonEditarInsectoca)
                        .addComponent(jButtonControloResultadosInsectoca)
                        .addComponent(jButtonConsultarLimpezas)
                        .addComponent(jButtonAlterarEstadoInsectocacadores))
                    .addGroup(jPanelConsultaInsectoLayout.createSequentialGroup()
                        .addComponent(jLabelPesquisarInsectocacador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPesquisaInsectocacador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneConsultInsecto, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelConsultaInsectoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddNovoInsecto)
                    .addComponent(jButtonNovaLimpeza)
                    .addComponent(jButtonVerInsectocacadoresInativos)
                    .addComponent(jButtonSairConsultaInsecto))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultaInsectocacadoresLayout = new javax.swing.GroupLayout(jDialogConsultaInsectocacadores.getContentPane());
        jDialogConsultaInsectocacadores.getContentPane().setLayout(jDialogConsultaInsectocacadoresLayout);
        jDialogConsultaInsectocacadoresLayout.setHorizontalGroup(
            jDialogConsultaInsectocacadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaInsectocacadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaInsecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jDialogConsultaInsectocacadoresLayout.setVerticalGroup(
            jDialogConsultaInsectocacadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaInsectocacadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaInsecto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jDialogConsultaLimpezas.setTitle("CONSULTA LIMPEZAS");
        jDialogConsultaLimpezas.setMinimumSize(new java.awt.Dimension(621, 324));

        jTableConsultaLimpezas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Controlo de Praga", "Funcionario", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneConsultaLimpezas.setViewportView(jTableConsultaLimpezas);

        jButtonNovaLimpezaConsultaLimpezas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonNovaLimpezaConsultaLimpezas.setText("Nova Limpeza");
        jButtonNovaLimpezaConsultaLimpezas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaLimpezaConsultaLimpezasActionPerformed(evt);
            }
        });

        jButtonSairConsultaLimpezas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairConsultaLimpezas.setText("Fechar");
        jButtonSairConsultaLimpezas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairConsultaLimpezasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultaLimpezasLayout = new javax.swing.GroupLayout(jPanelConsultaLimpezas);
        jPanelConsultaLimpezas.setLayout(jPanelConsultaLimpezasLayout);
        jPanelConsultaLimpezasLayout.setHorizontalGroup(
            jPanelConsultaLimpezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaLimpezasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaLimpezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneConsultaLimpezas, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGroup(jPanelConsultaLimpezasLayout.createSequentialGroup()
                        .addComponent(jButtonNovaLimpezaConsultaLimpezas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSairConsultaLimpezas)))
                .addContainerGap())
        );
        jPanelConsultaLimpezasLayout.setVerticalGroup(
            jPanelConsultaLimpezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaLimpezasLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jScrollPaneConsultaLimpezas, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConsultaLimpezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovaLimpezaConsultaLimpezas)
                    .addComponent(jButtonSairConsultaLimpezas))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jDialogConsultaLimpezasLayout = new javax.swing.GroupLayout(jDialogConsultaLimpezas.getContentPane());
        jDialogConsultaLimpezas.getContentPane().setLayout(jDialogConsultaLimpezasLayout);
        jDialogConsultaLimpezasLayout.setHorizontalGroup(
            jDialogConsultaLimpezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaLimpezasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaLimpezas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogConsultaLimpezasLayout.setVerticalGroup(
            jDialogConsultaLimpezasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaLimpezasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaLimpezas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jDialogConsultaEquipamentos.setTitle("CONSULTA EQUIPAMENTOS");
        jDialogConsultaEquipamentos.setMinimumSize(new java.awt.Dimension(880, 400));

        jTableConsultaEquipamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Observação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneConsultaEquipamentos.setViewportView(jTableConsultaEquipamentos);

        jButtonNovoEquipamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonNovoEquipamento.setText("Novo Equipamento");
        jButtonNovoEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoEquipamentoActionPerformed(evt);
            }
        });

        jButtonSairConsultaEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairConsultaEquipamentos.setText("Sair");
        jButtonSairConsultaEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairConsultaEquipamentosActionPerformed(evt);
            }
        });

        jButtonManutencaoConsultaEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/manutencao2.png"))); // NOI18N
        jButtonManutencaoConsultaEquipamentos.setText("Nova Manutenção");
        jButtonManutencaoConsultaEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonManutencaoConsultaEquipamentosActionPerformed(evt);
            }
        });

        jButtonControloResultados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Nao Conformidades.png"))); // NOI18N
        jButtonControloResultados.setText("Controlo de Resultados");
        jButtonControloResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonControloResultadosActionPerformed(evt);
            }
        });

        jButtonEditarEquipamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jButtonEditarEquipamento.setText("Editar Equipamento");
        jButtonEditarEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarEquipamentoActionPerformed(evt);
            }
        });

        jButtonConsultaManutencao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consulta.png"))); // NOI18N
        jButtonConsultaManutencao.setText("Consulta Manutenção");
        jButtonConsultaManutencao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaManutencaoActionPerformed(evt);
            }
        });

        jTextFieldPesquisaEquipamento.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldPesquisaEquipamentoCaretUpdate(evt);
            }
        });

        jLabelPesquisarEquipamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabelPesquisarEquipamento.setText("Pesquisar");

        jButtonAlterarEstadoEquipamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/trocar.png"))); // NOI18N
        jButtonAlterarEstadoEquipamento.setText("Alterar Estado");
        jButtonAlterarEstadoEquipamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarEstadoEquipamentoActionPerformed(evt);
            }
        });

        jButtonVerInativasEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png"))); // NOI18N
        jButtonVerInativasEquipamentos.setText("Ver Inativas");
        jButtonVerInativasEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerInativasEquipamentosActionPerformed(evt);
            }
        });

        jLabelConsultarEquipamentos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelConsultarEquipamentos.setText("Consultar Equipamentos - Ativos");

        javax.swing.GroupLayout jPanelConsultaEquipamentosLayout = new javax.swing.GroupLayout(jPanelConsultaEquipamentos);
        jPanelConsultaEquipamentos.setLayout(jPanelConsultaEquipamentosLayout);
        jPanelConsultaEquipamentosLayout.setHorizontalGroup(
            jPanelConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaEquipamentosLayout.createSequentialGroup()
                        .addComponent(jLabelPesquisarEquipamento)
                        .addGap(149, 149, 149)
                        .addComponent(jLabelConsultarEquipamentos))
                    .addGroup(jPanelConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPaneConsultaEquipamentos)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConsultaEquipamentosLayout.createSequentialGroup()
                            .addComponent(jTextFieldPesquisaEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonConsultaManutencao)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonAlterarEstadoEquipamento)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonControloResultados))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelConsultaEquipamentosLayout.createSequentialGroup()
                            .addComponent(jButtonVerInativasEquipamentos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonNovoEquipamento)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonManutencaoConsultaEquipamentos)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonEditarEquipamento)
                            .addGap(56, 56, 56)
                            .addComponent(jButtonSairConsultaEquipamentos))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanelConsultaEquipamentosLayout.setVerticalGroup(
            jPanelConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelConsultarEquipamentos)
                    .addComponent(jLabelPesquisarEquipamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonControloResultados)
                    .addComponent(jButtonConsultaManutencao)
                    .addComponent(jTextFieldPesquisaEquipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlterarEstadoEquipamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneConsultaEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovoEquipamento)
                    .addComponent(jButtonSairConsultaEquipamentos)
                    .addComponent(jButtonManutencaoConsultaEquipamentos)
                    .addComponent(jButtonEditarEquipamento)
                    .addComponent(jButtonVerInativasEquipamentos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultaEquipamentosLayout = new javax.swing.GroupLayout(jDialogConsultaEquipamentos.getContentPane());
        jDialogConsultaEquipamentos.getContentPane().setLayout(jDialogConsultaEquipamentosLayout);
        jDialogConsultaEquipamentosLayout.setHorizontalGroup(
            jDialogConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaEquipamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jDialogConsultaEquipamentosLayout.setVerticalGroup(
            jDialogConsultaEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jDialogConsultaManutencaoEquipamentos.setTitle("CONSULTA MANUTENÇÃO DE EQUIPAMENTOS");
        jDialogConsultaManutencaoEquipamentos.setMinimumSize(new java.awt.Dimension(673, 351));

        jPanelConsultaManutencaoEquipamentos.setMinimumSize(new java.awt.Dimension(642, 302));

        jLabel5.setText("Consulta Manutenção de Equipamentos");

        jTableConsultaManutencaoEquipamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Equipamento", "Data", "Observações", "Ficha Tecnica"
            }
        ));
        jScrollPaneConsultaManutencaoEquipamentos.setViewportView(jTableConsultaManutencaoEquipamentos);

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultaManutencaoEquipamentosLayout = new javax.swing.GroupLayout(jPanelConsultaManutencaoEquipamentos);
        jPanelConsultaManutencaoEquipamentos.setLayout(jPanelConsultaManutencaoEquipamentosLayout);
        jPanelConsultaManutencaoEquipamentosLayout.setHorizontalGroup(
            jPanelConsultaManutencaoEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaManutencaoEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaManutencaoEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPaneConsultaManutencaoEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConsultaManutencaoEquipamentosLayout.setVerticalGroup(
            jPanelConsultaManutencaoEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaManutencaoEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(42, 42, 42)
                .addComponent(jScrollPaneConsultaManutencaoEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultaManutencaoEquipamentosLayout = new javax.swing.GroupLayout(jDialogConsultaManutencaoEquipamentos.getContentPane());
        jDialogConsultaManutencaoEquipamentos.getContentPane().setLayout(jDialogConsultaManutencaoEquipamentosLayout);
        jDialogConsultaManutencaoEquipamentosLayout.setHorizontalGroup(
            jDialogConsultaManutencaoEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaManutencaoEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaManutencaoEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jDialogConsultaManutencaoEquipamentosLayout.setVerticalGroup(
            jDialogConsultaManutencaoEquipamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaManutencaoEquipamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaManutencaoEquipamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jDialogConsultarControlos.setTitle("CONSULTAR CONTROLOS DE RESULTADOS");
        jDialogConsultarControlos.setMinimumSize(new java.awt.Dimension(1040, 492));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("Consultar Controlos");

        jTableConsultarControlos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionario", "Secção", "Data", "Estado", "Problema"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        if (jTableConsultarControlos.getColumnModel().getColumnCount() > 0) {
            jTableConsultarControlos.getColumnModel().getColumn(3).setMinWidth(60);
            jTableConsultarControlos.getColumnModel().getColumn(3).setMaxWidth(80);
        }

        jButtonFecharConsultaControlosResultados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonFecharConsultaControlosResultados.setText("Fechar");
        jButtonFecharConsultaControlosResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharConsultaControlosResultadosActionPerformed(evt);
            }
        });

        jButtonCriaNovoControloResultados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novo.png"))); // NOI18N
        jButtonCriaNovoControloResultados.setText("Criar Novo");
        jButtonCriaNovoControloResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriaNovoControloResultadosActionPerformed(evt);
            }
        });

        jButtonVerNaoConformidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Nao Conformidades.png"))); // NOI18N
        jButtonVerNaoConformidade.setText("Ver Não Conformidade");
        jButtonVerNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerNaoConformidadeActionPerformed(evt);
            }
        });

        jComboBoxSeccao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Secção--", "ENTRADA", "EQUIPAMENTO", "CONT. PRAGAS", "OUTROS" }));
        jComboBoxSeccao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSeccaoActionPerformed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabel10.setText("Secção");

        jButtonVerNaoConformidadeEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonVerNaoConformidadeEntrada.setText("Ver Nao Conformidade (Entrada)");
        jButtonVerNaoConformidadeEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerNaoConformidadeEntradaActionPerformed(evt);
            }
        });

        jLabel12.setText("C - Conforme");

        jLabel36.setText("NC - Não Conforme");

        jButtonVerHistoricoControlo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/historico.png"))); // NOI18N
        jButtonVerHistoricoControlo.setText("Ver Historico");
        jButtonVerHistoricoControlo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerHistoricoControloActionPerformed(evt);
            }
        });

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
                        .addGap(18, 18, 18)
                        .addComponent(jButtonVerHistoricoControlo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFecharConsultaControlosResultados))
                    .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxSeccao, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel36))
                        .addGap(4, 4, Short.MAX_VALUE)
                        .addComponent(jButtonVerNaoConformidadeEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonVerNaoConformidade)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaControlosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(397, 397, 397))
        );
        jPanelConsultaControlosLayout.setVerticalGroup(
            jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel9)
                .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVerNaoConformidade, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxSeccao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jButtonVerNaoConformidadeEntrada)
                                .addComponent(jLabel36)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPaneConsultarControlos, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelConsultaControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonFecharConsultaControlosResultados)
                            .addComponent(jButtonCriaNovoControloResultados)
                            .addComponent(jButtonVerHistoricoControlo)))
                    .addGroup(jPanelConsultaControlosLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultarControlosLayout = new javax.swing.GroupLayout(jDialogConsultarControlos.getContentPane());
        jDialogConsultarControlos.getContentPane().setLayout(jDialogConsultarControlosLayout);
        jDialogConsultarControlosLayout.setHorizontalGroup(
            jDialogConsultarControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultarControlosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaControlos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );
        jDialogConsultarControlosLayout.setVerticalGroup(
            jDialogConsultarControlosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultarControlosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaControlos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jDialogConsultaNaoConformidades.setTitle("CONSULTAR NÃO CONFORMIDADES");
        jDialogConsultaNaoConformidades.setMinimumSize(new java.awt.Dimension(800, 500));

        jPanelConsultaNaoConformidades.setLayout(null);

        jLabelNaoConformidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Nao Conformidades.png"))); // NOI18N
        jLabelNaoConformidades.setText("CONSULTA NAO CONFORMIDADES");
        jPanelConsultaNaoConformidades.add(jLabelNaoConformidades);
        jLabelNaoConformidades.setBounds(10, 11, 205, 32);

        jTableNaoConformidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionario", "Data(Ñ Confor)", "Detalhes", "Medida Correct.", "Func. Responsav."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneNaoConformidades.setViewportView(jTableNaoConformidades);

        jPanelConsultaNaoConformidades.add(jScrollPaneNaoConformidades);
        jScrollPaneNaoConformidades.setBounds(10, 220, 735, 178);

        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonVoltar.setText("Fechar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });
        jPanelConsultaNaoConformidades.add(jButtonVoltar);
        jButtonVoltar.setBounds(610, 410, 131, 41);

        jButtonAddNovaNaoConformidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Nao Conformidades.png"))); // NOI18N
        jButtonAddNovaNaoConformidade.setText("Nova Não Conformidade");
        jButtonAddNovaNaoConformidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddNovaNaoConformidadeActionPerformed(evt);
            }
        });
        jPanelConsultaNaoConformidades.add(jButtonAddNovaNaoConformidade);
        jButtonAddNovaNaoConformidade.setBounds(10, 410, 200, 41);

        jButtonAddMedidaCorrectiva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/medida_correctiva.png"))); // NOI18N
        jButtonAddMedidaCorrectiva.setText("Aplicar Medida Correctiva");
        jButtonAddMedidaCorrectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddMedidaCorrectivaActionPerformed(evt);
            }
        });
        jPanelConsultaNaoConformidades.add(jButtonAddMedidaCorrectiva);
        jButtonAddMedidaCorrectiva.setBounds(560, 170, 191, 41);

        jPanelMostraDadosControloResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Controlo Resultado"));

        jLabelSeccaoNC.setText("Secção : ");

        jLabelProblemaNC.setText("Problema :");

        jTextFieldVerSeccaoNC.setEditable(false);

        jTextAreaVerProblemaNC.setEditable(false);
        jTextAreaVerProblemaNC.setColumns(10);
        jTextAreaVerProblemaNC.setLineWrap(true);
        jTextAreaVerProblemaNC.setRows(3);
        jTextAreaVerProblemaNC.setTabSize(4);
        jScrollPane8.setViewportView(jTextAreaVerProblemaNC);

        javax.swing.GroupLayout jPanelMostraDadosControloResultadoLayout = new javax.swing.GroupLayout(jPanelMostraDadosControloResultado);
        jPanelMostraDadosControloResultado.setLayout(jPanelMostraDadosControloResultadoLayout);
        jPanelMostraDadosControloResultadoLayout.setHorizontalGroup(
            jPanelMostraDadosControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMostraDadosControloResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMostraDadosControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelSeccaoNC)
                    .addComponent(jTextFieldVerSeccaoNC, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelMostraDadosControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelProblemaNC))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanelMostraDadosControloResultadoLayout.setVerticalGroup(
            jPanelMostraDadosControloResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMostraDadosControloResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelSeccaoNC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldVerSeccaoNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelMostraDadosControloResultadoLayout.createSequentialGroup()
                .addComponent(jLabelProblemaNC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanelConsultaNaoConformidades.add(jPanelMostraDadosControloResultado);
        jPanelMostraDadosControloResultado.setBounds(10, 61, 540, 140);

        javax.swing.GroupLayout jDialogConsultaNaoConformidadesLayout = new javax.swing.GroupLayout(jDialogConsultaNaoConformidades.getContentPane());
        jDialogConsultaNaoConformidades.getContentPane().setLayout(jDialogConsultaNaoConformidadesLayout);
        jDialogConsultaNaoConformidadesLayout.setHorizontalGroup(
            jDialogConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaNaoConformidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jDialogConsultaNaoConformidadesLayout.setVerticalGroup(
            jDialogConsultaNaoConformidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaNaoConformidadesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaNaoConformidades, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addContainerGap(204, Short.MAX_VALUE))
        );
        jDialogConsultarMedidasCorrectivasLayout.setVerticalGroup(
            jDialogConsultarMedidasCorrectivasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultarMedidasCorrectivasLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jDialogConsultaFornecedores.setMinimumSize(new java.awt.Dimension(800, 450));

        jLabelConsultaFornecedores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelConsultaFornecedores.setText("Consulta Fornecedores");

        jTableConsultaFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Nome", "NIF", "Tipo Produto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableConsultaFornecedor);
        if (jTableConsultaFornecedor.getColumnModel().getColumnCount() > 0) {
            jTableConsultaFornecedor.getColumnModel().getColumn(0).setMinWidth(20);
            jTableConsultaFornecedor.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jButtonFecharConsultaFornecedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonFecharConsultaFornecedores.setText("Fechar");
        jButtonFecharConsultaFornecedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharConsultaFornecedoresActionPerformed(evt);
            }
        });

        jButtonFornecedoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png"))); // NOI18N
        jButtonFornecedoresInativos.setText("Ver Inativos");
        jButtonFornecedoresInativos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFornecedoresInativosActionPerformed(evt);
            }
        });

        jButtonEditarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jButtonEditarFornecedor.setText("Editar");
        jButtonEditarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarFornecedorActionPerformed(evt);
            }
        });

        jButtonAlterarEstadoFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/trocar.png"))); // NOI18N
        jButtonAlterarEstadoFornecedor.setText("Alterar Estado");
        jButtonAlterarEstadoFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarEstadoFornecedorActionPerformed(evt);
            }
        });

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabel34.setText("Pesquisar");

        jTextFieldPesquisarFornecedor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldPesquisarFornecedorCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultaFornecedoresLayout = new javax.swing.GroupLayout(jPanelConsultaFornecedores);
        jPanelConsultaFornecedores.setLayout(jPanelConsultaFornecedoresLayout);
        jPanelConsultaFornecedoresLayout.setHorizontalGroup(
            jPanelConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaFornecedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaFornecedoresLayout.createSequentialGroup()
                        .addComponent(jButtonFornecedoresInativos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEditarFornecedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonFecharConsultaFornecedores))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaFornecedoresLayout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPesquisarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAlterarEstadoFornecedor)))
                .addContainerGap())
            .addGroup(jPanelConsultaFornecedoresLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabelConsultaFornecedores)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConsultaFornecedoresLayout.setVerticalGroup(
            jPanelConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaFornecedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConsultaFornecedores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterarEstadoFornecedor)
                    .addComponent(jLabel34)
                    .addComponent(jTextFieldPesquisarFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonFecharConsultaFornecedores)
                        .addComponent(jButtonEditarFornecedor))
                    .addComponent(jButtonFornecedoresInativos))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jDialogConsultaFornecedoresLayout = new javax.swing.GroupLayout(jDialogConsultaFornecedores.getContentPane());
        jDialogConsultaFornecedores.getContentPane().setLayout(jDialogConsultaFornecedoresLayout);
        jDialogConsultaFornecedoresLayout.setHorizontalGroup(
            jDialogConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaFornecedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogConsultaFornecedoresLayout.setVerticalGroup(
            jDialogConsultaFornecedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaFornecedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaFornecedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogConsultaEntradas.setTitle("CONSULTA ENTRADAS");
        jDialogConsultaEntradas.setMinimumSize(new java.awt.Dimension(1014, 430));

        jButtonConsultaEntradasSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonConsultaEntradasSair.setText("Fechar");
        jButtonConsultaEntradasSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaEntradasSairActionPerformed(evt);
            }
        });

        jTableConsultaEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Funcionário", "Forncedor", "Matéria Prima", "Data Entrega", "Lote Origem", "Quant. Disponível", "Quant. a Devolver"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableConsultaEntradas);
        if (jTableConsultaEntradas.getColumnModel().getColumnCount() > 0) {
            jTableConsultaEntradas.getColumnModel().getColumn(0).setMinWidth(20);
            jTableConsultaEntradas.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jButtonVerDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/trocar.png"))); // NOI18N
        jButtonVerDevolucoes.setText("Fazer Devolução");
        jButtonVerDevolucoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerDevolucoesActionPerformed(evt);
            }
        });

        jButtonDevolucao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/devolução.png"))); // NOI18N
        jButtonDevolucao.setText("Devolução");
        jButtonDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDevolucaoActionPerformed(evt);
            }
        });

        jLabelPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabelPesquisar.setText("Pesquisar Por Lote");

        jTextFieldPesquisarEntrada.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldPesquisarEntradaCaretUpdate(evt);
            }
        });

        jButtonVerControloResultados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Nao Conformidades.png"))); // NOI18N
        jButtonVerControloResultados.setText("Ver Controlo Resultados");
        jButtonVerControloResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerControloResultadosActionPerformed(evt);
            }
        });

        jButtonVerLoteTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consulta.png"))); // NOI18N
        jButtonVerLoteTotal.setText("Ver Lote Total(S/Devoluções)");
        jButtonVerLoteTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerLoteTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultaEntradasLayout = new javax.swing.GroupLayout(jPanelConsultaEntradas);
        jPanelConsultaEntradas.setLayout(jPanelConsultaEntradasLayout);
        jPanelConsultaEntradasLayout.setHorizontalGroup(
            jPanelConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaEntradasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaEntradasLayout.createSequentialGroup()
                        .addComponent(jButtonVerDevolucoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVerLoteTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonConsultaEntradasSair))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaEntradasLayout.createSequentialGroup()
                        .addComponent(jLabelPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPesquisarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonVerControloResultados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDevolucao)))
                .addContainerGap())
        );
        jPanelConsultaEntradasLayout.setVerticalGroup(
            jPanelConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaEntradasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDevolucao)
                    .addComponent(jLabelPesquisar)
                    .addComponent(jTextFieldPesquisarEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerControloResultados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsultaEntradasSair)
                    .addComponent(jButtonVerDevolucoes)
                    .addComponent(jButtonVerLoteTotal))
                .addGap(138, 138, 138))
        );

        jLabelTituloConsultaEntradas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelTituloConsultaEntradas.setText("jLabel25");

        javax.swing.GroupLayout jDialogConsultaEntradasLayout = new javax.swing.GroupLayout(jDialogConsultaEntradas.getContentPane());
        jDialogConsultaEntradas.getContentPane().setLayout(jDialogConsultaEntradasLayout);
        jDialogConsultaEntradasLayout.setHorizontalGroup(
            jDialogConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaEntradasLayout.createSequentialGroup()
                .addGroup(jDialogConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogConsultaEntradasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelConsultaEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogConsultaEntradasLayout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(jLabelTituloConsultaEntradas)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jDialogConsultaEntradasLayout.setVerticalGroup(
            jDialogConsultaEntradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaEntradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTituloConsultaEntradas)
                .addGap(2, 2, 2)
                .addComponent(jPanelConsultaEntradas, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jDialogConsultaDevolucoes.setTitle("CONSULTA DEVOLUÇÕES");
        jDialogConsultaDevolucoes.setMinimumSize(new java.awt.Dimension(1000, 425));

        jTableConsultaDevolucoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Fornecedor", "Mater. Prima", "Lote Original", "Devolução Nº", "Data Devolução", "Quantidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTableConsultaDevolucoes);
        if (jTableConsultaDevolucoes.getColumnModel().getColumnCount() > 0) {
            jTableConsultaDevolucoes.getColumnModel().getColumn(0).setMinWidth(10);
            jTableConsultaDevolucoes.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jButtonConsultaDevolucoesSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonConsultaDevolucoesSair.setText("Fechar");
        jButtonConsultaDevolucoesSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaDevolucoesSairActionPerformed(evt);
            }
        });

        jButtonVerFichaDetalhadaConsultaDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consulta.png"))); // NOI18N
        jButtonVerFichaDetalhadaConsultaDevolucoes.setText("Ver Ficha Detalhada");
        jButtonVerFichaDetalhadaConsultaDevolucoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerFichaDetalhadaConsultaDevolucoesActionPerformed(evt);
            }
        });

        jComboBoxFornecedorDevolucoes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxFornecedorDevolucoes.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxFornecedorDevolucoesPopupMenuWillBecomeInvisible(evt);
            }
        });

        jComboBoxMateriaPrimaDevolucoes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxMateriaPrimaDevolucoes.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxMateriaPrimaDevolucoesPopupMenuWillBecomeInvisible(evt);
            }
        });

        jLabelFornecedorDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabelFornecedorDevolucoes.setText("Fornecedor");

        jLabelMateriaPrimaDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabelMateriaPrimaDevolucoes.setText("Materia-Prima");

        jButtonVerTodasAsDevolucoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Consltas_Menu.png"))); // NOI18N
        jButtonVerTodasAsDevolucoes.setText("Ver Todas as Devoluções");
        jButtonVerTodasAsDevolucoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerTodasAsDevolucoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultaDevolucoesLayout = new javax.swing.GroupLayout(jPanelConsultaDevolucoes);
        jPanelConsultaDevolucoes.setLayout(jPanelConsultaDevolucoesLayout);
        jPanelConsultaDevolucoesLayout.setHorizontalGroup(
            jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaDevolucoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanelConsultaDevolucoesLayout.createSequentialGroup()
                        .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelConsultaDevolucoesLayout.createSequentialGroup()
                                .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelFornecedorDevolucoes)
                                    .addComponent(jComboBoxFornecedorDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelMateriaPrimaDevolucoes)
                                    .addComponent(jComboBoxMateriaPrimaDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButtonVerFichaDetalhadaConsultaDevolucoes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 387, Short.MAX_VALUE)
                        .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonVerTodasAsDevolucoes, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonConsultaDevolucoesSair, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanelConsultaDevolucoesLayout.setVerticalGroup(
            jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelConsultaDevolucoesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFornecedorDevolucoes)
                    .addComponent(jLabelMateriaPrimaDevolucoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFornecedorDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMateriaPrimaDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVerTodasAsDevolucoes))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonConsultaDevolucoesSair)
                    .addComponent(jButtonVerFichaDetalhadaConsultaDevolucoes))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jDialogConsultaDevolucoesLayout = new javax.swing.GroupLayout(jDialogConsultaDevolucoes.getContentPane());
        jDialogConsultaDevolucoes.getContentPane().setLayout(jDialogConsultaDevolucoesLayout);
        jDialogConsultaDevolucoesLayout.setHorizontalGroup(
            jDialogConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaDevolucoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jDialogConsultaDevolucoesLayout.setVerticalGroup(
            jDialogConsultaDevolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaDevolucoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaDevolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jDialogConsultaMateriasPrimas.setTitle("CONSULTAR MÁTERIAS-PRIMAS");
        jDialogConsultaMateriasPrimas.setMinimumSize(new java.awt.Dimension(680, 400));

        jTableConsultaMateriasPrimas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Nome", "Descrição", "Unidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableConsultaMateriasPrimas);
        if (jTableConsultaMateriasPrimas.getColumnModel().getColumnCount() > 0) {
            jTableConsultaMateriasPrimas.getColumnModel().getColumn(0).setMinWidth(10);
            jTableConsultaMateriasPrimas.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jButtonSairConsultaMateriaPrima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairConsultaMateriaPrima.setText("Fechar");
        jButtonSairConsultaMateriaPrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairConsultaMateriaPrimaActionPerformed(evt);
            }
        });

        jLabelPesquisarMateriaPrima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/pesquisar.png"))); // NOI18N
        jLabelPesquisarMateriaPrima.setText("Pesquisar");

        jTextFieldPesquisarMateriaPrima.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldPesquisarMateriaPrimaCaretUpdate(evt);
            }
        });

        jButtonAlterarEstadoMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/trocar.png"))); // NOI18N
        jButtonAlterarEstadoMP.setText("Alterar Estado");
        jButtonAlterarEstadoMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarEstadoMPActionPerformed(evt);
            }
        });

        jButtonVerInativosMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png"))); // NOI18N
        jButtonVerInativosMP.setText("Ver Inativas");
        jButtonVerInativosMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerInativosMPActionPerformed(evt);
            }
        });

        jButtonEditarMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/editar.png"))); // NOI18N
        jButtonEditarMP.setText("Editar");
        jButtonEditarMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarMPActionPerformed(evt);
            }
        });

        jLabelConsultarMateriasPrimas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelConsultarMateriasPrimas.setText("Consultar Matérias-Primas - Ativas");

        javax.swing.GroupLayout jPanelConsutaMateriasPrimasLayout = new javax.swing.GroupLayout(jPanelConsutaMateriasPrimas);
        jPanelConsutaMateriasPrimas.setLayout(jPanelConsutaMateriasPrimasLayout);
        jPanelConsutaMateriasPrimasLayout.setHorizontalGroup(
            jPanelConsutaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsutaMateriasPrimasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelConsutaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsutaMateriasPrimasLayout.createSequentialGroup()
                        .addComponent(jButtonVerInativosMP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSairConsultaMateriaPrima))
                    .addGroup(jPanelConsutaMateriasPrimasLayout.createSequentialGroup()
                        .addComponent(jLabelPesquisarMateriaPrima)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisarMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEditarMP)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAlterarEstadoMP))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanelConsutaMateriasPrimasLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabelConsultarMateriasPrimas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelConsutaMateriasPrimasLayout.setVerticalGroup(
            jPanelConsutaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsutaMateriasPrimasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConsultarMateriasPrimas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConsutaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelPesquisarMateriaPrima)
                    .addGroup(jPanelConsutaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPesquisarMateriaPrima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAlterarEstadoMP)
                        .addComponent(jButtonEditarMP)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelConsutaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVerInativosMP)
                    .addComponent(jButtonSairConsultaMateriaPrima))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogConsultaMateriasPrimasLayout = new javax.swing.GroupLayout(jDialogConsultaMateriasPrimas.getContentPane());
        jDialogConsultaMateriasPrimas.getContentPane().setLayout(jDialogConsultaMateriasPrimasLayout);
        jDialogConsultaMateriasPrimasLayout.setHorizontalGroup(
            jDialogConsultaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaMateriasPrimasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsutaMateriasPrimas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jDialogConsultaMateriasPrimasLayout.setVerticalGroup(
            jDialogConsultaMateriasPrimasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogConsultaMateriasPrimasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsutaMateriasPrimas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogVerHistoricoControlo.setTitle("Historico do Controlo de Resultado");
        jDialogVerHistoricoControlo.setMinimumSize(new java.awt.Dimension(890, 520));

        jLabel37.setText("Consulta Historico de um Controlo de Resultado");

        jTableVerHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Funcionario (Registou)", "Data Ocorrencia", "Ocorrencia", "Medida Correctica (MC)", "Observação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTableVerHistorico);
        if (jTableVerHistorico.getColumnModel().getColumnCount() > 0) {
            jTableVerHistorico.getColumnModel().getColumn(0).setMinWidth(20);
            jTableVerHistorico.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jButtonVerDatas.setText("Ver Medida Correctiva");
        jButtonVerDatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerDatasActionPerformed(evt);
            }
        });

        jButtonSairHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/fechar.png"))); // NOI18N
        jButtonSairHistorico.setText("Fechar");
        jButtonSairHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairHistoricoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConsultaHistoricoLayout = new javax.swing.GroupLayout(jPanelConsultaHistorico);
        jPanelConsultaHistorico.setLayout(jPanelConsultaHistoricoLayout);
        jPanelConsultaHistoricoLayout.setHorizontalGroup(
            jPanelConsultaHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaHistoricoLayout.createSequentialGroup()
                .addGroup(jPanelConsultaHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConsultaHistoricoLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel37))
                    .addGroup(jPanelConsultaHistoricoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelConsultaHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSairHistorico)
                            .addComponent(jButtonVerDatas)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanelConsultaHistoricoLayout.setVerticalGroup(
            jPanelConsultaHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConsultaHistoricoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(41, 41, 41)
                .addComponent(jButtonVerDatas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSairHistorico)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogVerHistoricoControloLayout = new javax.swing.GroupLayout(jDialogVerHistoricoControlo.getContentPane());
        jDialogVerHistoricoControlo.getContentPane().setLayout(jDialogVerHistoricoControloLayout);
        jDialogVerHistoricoControloLayout.setHorizontalGroup(
            jDialogVerHistoricoControloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogVerHistoricoControloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogVerHistoricoControloLayout.setVerticalGroup(
            jDialogVerHistoricoControloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogVerHistoricoControloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConsultaHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jDialogAlterarReferencia.setTitle("ALTERAÇÃO CÓDIGO REFERÊNCIA");
        jDialogAlterarReferencia.setMinimumSize(new java.awt.Dimension(300, 200));

        jButtonGuardarNovaRef.setText("Atualizar Referência");
        jButtonGuardarNovaRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarNovaRefActionPerformed(evt);
            }
        });

        jLabelAntigaReferencia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelAntigaReferencia.setText("[referencia antiga]");

        jLabelNovaReferencia.setText("Nova Referência:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonGuardarNovaRef)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabelNovaReferencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNovaReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelAntigaReferencia)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 152, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jLabelAntigaReferencia)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNovaReferencia)
                        .addComponent(jTextFieldNovaReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jButtonGuardarNovaRef)
                    .addContainerGap(34, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jDialogAlterarReferenciaLayout = new javax.swing.GroupLayout(jDialogAlterarReferencia.getContentPane());
        jDialogAlterarReferencia.getContentPane().setLayout(jDialogAlterarReferenciaLayout);
        jDialogAlterarReferenciaLayout.setHorizontalGroup(
            jDialogAlterarReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAlterarReferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jDialogAlterarReferenciaLayout.setVerticalGroup(
            jDialogAlterarReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogAlterarReferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jButtonSeleccionarNPF.setText("Seleccione");
        jButtonSeleccionarNPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarNPFActionPerformed(evt);
            }
        });

        jButton3.setText("Diferença");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Gravar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUploudNUC, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jButtonSeleccionarNPF))))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton4))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(JT01, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addComponent(JT02, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(JTRES, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(278, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSeleccionarNPF)
                    .addComponent(jTextFieldUploudNUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jButton4)
                .addGap(59, 59, 59)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JT01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JT02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTRES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(518, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Higiene e Segurança - Principio Base");
        setMinimumSize(new java.awt.Dimension(250, 200));

        jPanelRealizaLogin.setBorder(javax.swing.BorderFactory.createTitledBorder("Login"));

        jLabelNomeLogin.setText("Nome:");

        jLabelPasswordLogin.setText("Password");

        jButtonEntrar.setText("Entrar");
        jButtonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntrarActionPerformed(evt);
            }
        });

        jComboBoxNomeLogin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanelRealizaLoginLayout = new javax.swing.GroupLayout(jPanelRealizaLogin);
        jPanelRealizaLogin.setLayout(jPanelRealizaLoginLayout);
        jPanelRealizaLoginLayout.setHorizontalGroup(
            jPanelRealizaLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRealizaLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRealizaLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPasswordLogin)
                    .addComponent(jLabelNomeLogin))
                .addGap(18, 18, 18)
                .addGroup(jPanelRealizaLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEntrar)
                    .addGroup(jPanelRealizaLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPasswordFieldLogin)
                        .addComponent(jComboBoxNomeLogin, 0, 125, Short.MAX_VALUE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanelRealizaLoginLayout.setVerticalGroup(
            jPanelRealizaLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRealizaLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRealizaLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeLogin)
                    .addComponent(jComboBoxNomeLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanelRealizaLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPasswordLogin)
                    .addComponent(jPasswordFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonEntrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelJanelaLayout = new javax.swing.GroupLayout(jPanelJanela);
        jPanelJanela.setLayout(jPanelJanelaLayout);
        jPanelJanelaLayout.setHorizontalGroup(
            jPanelJanelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJanelaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanelJanelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jPanelRealizaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanelJanelaLayout.setVerticalGroup(
            jPanelJanelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJanelaLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanelRealizaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelJanela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelJanela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(416, 339));
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoInsectoActionPerformed
        // BOTAO NOVO INSECTOCAÇADOR -> JANELA MENU
        jDialogNovoInsectocacador.setLocationRelativeTo(this);
        jDialogNovoInsectocacador.setVisible(true);

        jButtonActualizarInsecto.setVisible(false);
        jButtonGuardarInsecto.setVisible(true);
    }//GEN-LAST:event_jButtonNovoInsectoActionPerformed

    private void jButtonConsultaInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaInsectoActionPerformed
        // BOTAO CONSULTA INSECTOCACADORES -> JANELA MENU
        jDialogConsultaInsectocacadores.setLocationRelativeTo(this);
        jDialogConsultaInsectocacadores.setVisible(true);
        //cCARREGAR A TABELA
        jLabelConsultaControloPragas.setText("Consulta Controlo de Pragas - Ativos");
        estadoInsectocacadores = true;
        LimpaTabelaConsultaInsectocacadores();
        ConsultaInsectocacadores();
    }//GEN-LAST:event_jButtonConsultaInsectoActionPerformed

    private void jTextFieldNomeInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeInsectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeInsectoActionPerformed

    private void jButtonGuardarInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarInsectoActionPerformed
        // BOTAO GUARDAR -> JANELAS NOVO INSECTOCAÇADOR

        InserirNovoInsecto();
        LimpaCamposNovoInsectocador();
        ConsultaInsectocacadores();
    }//GEN-LAST:event_jButtonGuardarInsectoActionPerformed

    private void jButtonSairInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairInsectoActionPerformed
        // BOTAO SAIR -> JANELA NOVO INSECTOCAÇADOR
        jDialogNovoInsectocacador.setVisible(false);
        LimpaCamposNovoInsectocador();
    }//GEN-LAST:event_jButtonSairInsectoActionPerformed

    private void jButtonActualizarInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarInsectoActionPerformed
        // BOTAO ACTUALIZAR DADOS -> JANELA NOVO INSECTOCAÇADOR
        UpdateDadosInsectocacador();
        ConsultaInsectocacadores();

    }//GEN-LAST:event_jButtonActualizarInsectoActionPerformed

    private void jButtonAddNovoInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNovoInsectoActionPerformed
        // BOTAO NOVO INSECTOCAÇADOR -> JANELAS CONSULTA INSECTOCAÇADORES
        jDialogNovoInsectocacador.setLocationRelativeTo(this);
        jDialogNovoInsectocacador.setTitle("Novo Contro. de Praga");
        jDialogNovoInsectocacador.setBounds(300, 300, 380, 300);
            jDialogNovoInsectocacador.repaint();  
        jDialogNovoInsectocacador.setVisible(true);
        //ESCOLHER O BOTAO QUE VAI APARECER
        jButtonActualizarInsecto.setVisible(false);
        jButtonGuardarInsecto.setVisible(true);
        jButtonAlterarReferencia.setVisible(false);
    }//GEN-LAST:event_jButtonAddNovoInsectoActionPerformed

    private void jButtonSairConsultaInsectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairConsultaInsectoActionPerformed
        // BOTAO SAIR -> JANELA CONSULTA INSECTOCADORES
        jDialogConsultaInsectocacadores.setVisible(false);
    }//GEN-LAST:event_jButtonSairConsultaInsectoActionPerformed

    private void jButtonNovaLimpezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaLimpezaActionPerformed
        // BOTAO NOVA LIMPEZA -> JANELA CONSULTA IINSECTOCACADORES

        int linha = jTableConsultaInsecto.getSelectedRow();

        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Seleccione a Linha de um Insectocaçador!");
        }else{
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeEquipamento = (String) jTableConsultaInsecto.getValueAt(linha, 1);

            idInsectocacadorControloResultado = selectId("INSECTOCACADORES", "NOME", nomeEquipamento, "IDINSECTOCACADORES");
            
            //ESCREVER A DATA DIRECTAMENTE NA PROXIMA JANELA
            Date dataAtual = new Date();    
            DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");  
            String Data = formato.format(dataAtual);
            jLabelDataNovaLimpeza.setText(Data);
            
            
            jDialogNovaLimpeza.setLocationRelativeTo(this);
            jDialogNovaLimpeza.setVisible(true);
            jLabelFuncionarioLogin5.setText("" + NomeFuncionarioLogin);

            System.out.println("\n***BOTAO NOVA LIMPEZA -> JANELA CONSULTA INSECTOCAÇADORES");
            System.out.println("NOME INSECTOC. SELEC.: " + nomeEquipamento );
            System.out.println("ID INSECTO SELEC.: " + idInsectocacadorControloResultado);

        }

    }//GEN-LAST:event_jButtonNovaLimpezaActionPerformed

    private void jButtonConsultarLimpezasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarLimpezasActionPerformed
        //BOTAO CONSULAR LIMPEZAS -> JANELAS CONSULTA INSECTOCAÇADORES
        int linha = jTableConsultaInsecto.getSelectedRow();

        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Seleccione a Linha de um Insectocaçador!");
        }else{
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeEquipamento = (String) jTableConsultaInsecto.getValueAt(linha, 1);

            idInsectocacadorControloResultado = selectId("INSECTOCACADORES", "NOME", nomeEquipamento, "IDINSECTOCACADORES");

            LimpaTabelaConsultaLimpezas();
            ConsultaLimpezas();

            if (CONTA_LINHAS_LIMPEZAS == 0) {
                JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Insectocaçador Ainda não contem nenhuma LIMPEZA  ! ");
            } else {
                jDialogConsultaLimpezas.setLocationRelativeTo(this);
                jDialogConsultaLimpezas.setVisible(true);
            }

            System.out.println("\n***BOTAO CONSULTA LIMPEZAS -> JANELA CONSULTA INSECTOCAÇADORES");
            System.out.println("NOME INSECTOC. SELEC.: " + nomeEquipamento );
            System.out.println("ID INSECTO SELEC.: " + idInsectocacadorControloResultado);

        }
    }//GEN-LAST:event_jButtonConsultarLimpezasActionPerformed

    private void jButtonEditarInsectocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarInsectocaActionPerformed
        // BOTAO EDITAR INSECTOCA -> JANELA CONSULTA INSETECTOCAÇADORES
        int linha = jTableConsultaInsecto.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Seleccione a Linha de um Equipamento!");
        } else {
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeInsecto = (String) jTableConsultaInsecto.getValueAt(linha, 1);

            idInsectocacadorControloResultado = selectId("INSECTOCACADORES", "NOME", nomeInsecto, "IDINSECTOCACADORES");

            
            //PERGUNTAR O QUE QUEREMOS FAZER,, EDITAR NOME OU O RESTO           
            
            jDialogNovoInsectocacador.setLocationRelativeTo(this);
            jDialogNovoInsectocacador.setTitle("Editar Contro. de Praga");
            jDialogNovoInsectocacador.setBounds(300, 300, 380, 370);
            jDialogNovoInsectocacador.repaint();            
            jDialogNovoInsectocacador.setVisible(true);
            LimpaCamposNovoInsectocador();
            ConsulInsectocaAlterar();

            //ESCOLHER BOTAO
            jButtonActualizarInsecto.setVisible(true);
            jButtonGuardarInsecto.setVisible(false);
             jButtonAlterarReferencia.setVisible(true);

            System.out.println("\n***BOTAO EDITAR INSECTOCAÇADOR -> JANELA CONSULTA INSECTOCAÇADOR");
            System.out.println("NOME EQUIPAMENTO SELEC.: " + nomeInsecto);
            System.out.println("ID EQUIPAMENTO SELEC.: " + idInsectocacadorControloResultado);

        }

    }//GEN-LAST:event_jButtonEditarInsectocaActionPerformed

    private void jButtonControloResultadosInsectocaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonControloResultadosInsectocaActionPerformed
        // BOTAO CONTROLO DE RESULTADOS -> JANELA  CONSULTA INSECTADORES
        
        //QUANDO CARREGAMOS NESTE BOTAO VAMOS DIZER QUE TEMOS DE PROCURAR NA COLUNA DOS INSECTOCAÇADORES
          int linha = jTableConsultaInsecto.getSelectedRow();
          
          if (linha == -1){
          JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Seleccione uma Linha da Tabela !");
        }else{
          //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeInsecto = (String) jTableConsultaInsecto.getValueAt(linha, 1);
            idInsectocacadorControloResultado = selectId("INSECTOCACADORES", "NOME", nomeInsecto, "IDINSECTOCACADORES");
            idEquipamentoControloResultado = 0;
            idEntradaControloResultado = 0;
            
            LimpaTabelaConsultaControloResultados();
            ConsultaControloResultado(idEntradaControloResultado, idEquipamentoControloResultado, idInsectocacadorControloResultado);
            
            //FALTA FAZER VALIDAÇÃO PARA DAR ERRO QUANDO NAO CONTEM NENGUMA LINHA INSERIDA
            
            
                if (CONTA_LINHAS_CONTROLORESULTADOS == 0) {
                    JOptionPane.showMessageDialog(jDialogConsultarControlos, "Linha Seleccionada não contem nenhuma Controlo de Resultado  ! ");
                } else {
                    jDialogConsultarControlos.setLocationRelativeTo(this);
                    jDialogConsultarControlos.setVisible(true);
                }
            
          }
    }//GEN-LAST:event_jButtonControloResultadosInsectocaActionPerformed

    private void jTextFieldPesquisaInsectocacadorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaInsectocacadorCaretUpdate
        // TEXTFIEL PESQUISA INSECTOCAÇADOR
        LimpaTabelaConsultaInsectocacadores();
        PesquisaInsectocacador();

    }//GEN-LAST:event_jTextFieldPesquisaInsectocacadorCaretUpdate

    private void jButtonGuardarLimpezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarLimpezaActionPerformed
        // BOTAO GUARDAR -> JANELA NOVA LIMPEZA

        //VERIFICAR SE ESTA ALGUM FUNCIONARIO SELECCIONADO
//        String comboFuncio = jComboBoxFuncionarioResponsvelLimpeza.getSelectedItem().toString();
//        if (comboFuncio.equals("--Funcionario--")) {
//            JOptionPane.showMessageDialog(jDialogNovaLimpeza, "Seleccione um Funcionario !");
//        } else {

            InserirNovaLimpeza();
            LimpaTabelaConsultaLimpezas();
            ConsultaLimpezas();
       // }
    }//GEN-LAST:event_jButtonGuardarLimpezaActionPerformed

    private void jButtonSairLimpezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairLimpezaActionPerformed
        // BOTAO SAIR -> JANELA NOVA LIMPEZA
        jDialogNovaLimpeza.setVisible(false);
    }//GEN-LAST:event_jButtonSairLimpezaActionPerformed

    private void jButtonNovaLimpezaConsultaLimpezasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaLimpezaConsultaLimpezasActionPerformed
        // BOTAO NOVA LIMPEZA -> JANELA CONSULTA LIMPEZAS
        jDialogNovaLimpeza.setLocationRelativeTo(this);
        jDialogNovaLimpeza.setVisible(true);
        //CARREGAR COMBOBOX
        jLabelFuncionarioLogin5.setText("" + NomeFuncionarioLogin);

    }//GEN-LAST:event_jButtonNovaLimpezaConsultaLimpezasActionPerformed

    private void jButtonSairConsultaLimpezasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairConsultaLimpezasActionPerformed
        // BOTAO SAIR -> JANELAS CONSULTA LIMPEZAS
        jDialogConsultaLimpezas.setVisible(false);
    }//GEN-LAST:event_jButtonSairConsultaLimpezasActionPerformed

    private void jButtonAddNovoEquipaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNovoEquipaActionPerformed
        // ADICIONAR NOVO EQUIPAMENTO
        jButtonActualizar.setVisible(false);
        jButtonGuardarEquipametno.setVisible(true);
        jDialogNovoEquipamento.setLocationRelativeTo(this);
        jDialogNovoEquipamento.setVisible(true);
    }//GEN-LAST:event_jButtonAddNovoEquipaActionPerformed

    private void jButtonConsultaEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaEquipamentosActionPerformed
        // CONSULTAR EQUIPAMENTOS
        estadoEquipamento = true;
        LimpaTabelaConsultaEquipamentos();
        ConsultaEquipamentos();
        jDialogConsultaEquipamentos.setLocationRelativeTo(this);
        jDialogConsultaEquipamentos.setVisible(true);
        

    }//GEN-LAST:event_jButtonConsultaEquipamentosActionPerformed

    private void jButtonGuardarEquipametnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarEquipametnoActionPerformed
        // BOTAO GUARDA NOVO EQUIPAMENTO
        String nome = jTextFieldNomeEquipamento.getText();

        if (nome.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaManutencaoEquipamento, "Introduza o Nome do Equipamento!");
        } else {
            InserirNovoEquipamento();
            LimpaCamposNovoEquipamento();
            jDialogNovoEquipamento.setVisible(false);

            LimpaTabelaConsultaEquipamentos();
            ConsultaEquipamentos();
            jDialogConsultaEquipamentos.setLocationRelativeTo(this);
            jDialogConsultaEquipamentos.setVisible(true);

        }
    }//GEN-LAST:event_jButtonGuardarEquipametnoActionPerformed

    private void jButtonSairNovoEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairNovoEquipamentoActionPerformed
        //BOTAO SAIR NOVO EQUIPAMENTO
        //LIMPAR OS CAMPOS
        LimpaCamposNovoEquipamento();
        jDialogNovoEquipamento.setVisible(false);
    }//GEN-LAST:event_jButtonSairNovoEquipamentoActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        // BOTAO ACTUALIZAR EQIPAMENTO -> JANELA NOVO EQUIPAMETNO
        String nome = jTextFieldNomeEquipamento.getText();

        if (nome.equals("")){
            JOptionPane.showMessageDialog(null, "Insira o Nome do Equipamento!");
        } else{
            UpdateDadosEquipamento();
            jDialogNovoEquipamento.setVisible(false);
            LimpaCamposNovoEquipamento();
            LimpaTabelaConsultaEquipamentos();
            ConsultaEquipamentos();
        }

    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonNovoEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoEquipamentoActionPerformed
        // NOVO EQUIPAMENTO -> JANELA DE CONSULTA EQUIPAMENTO
        LimpaTabelaConsultaEquipamentos();
        jDialogConsultaEquipamentos.setVisible(false);
        jDialogNovoEquipamento.setLocationRelativeTo(this);
        jDialogNovoEquipamento.setVisible(true);
        //BUTOES
        jButtonActualizar.setVisible(false);
        jButtonGuardarEquipametno.setVisible(true);
    }//GEN-LAST:event_jButtonNovoEquipamentoActionPerformed

    private void jButtonSairConsultaEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairConsultaEquipamentosActionPerformed
        //BOTAO SAIR DE CONSULTA DE EQUIPAMENTOS
        jDialogConsultaEquipamentos.setVisible(false);
        LimpaTabelaConsultaEquipamentos();
    }//GEN-LAST:event_jButtonSairConsultaEquipamentosActionPerformed

    private void jButtonManutencaoConsultaEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonManutencaoConsultaEquipamentosActionPerformed
        // BOTAO DE MANUTENÇÃO -> JANELA CONSULTA EQUIPAMENTOS
        int linha = jTableConsultaEquipamentos.getSelectedRow();

        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Seleccione a Linha de um Equipamento!");
        }else{
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeEquipamento = (String) jTableConsultaEquipamentos.getValueAt(linha, 0);

            idEquipamentoControloResultado = selectId("EQUIPAMENTO", "NOME", nomeEquipamento, "IDEQUIPAMENTO");
            jLabelFuncionariologin3.setText(NomeFuncionarioLogin);
            jDialogNovaManutencaoEquipamento.setLocationRelativeTo(this);
            jDialogNovaManutencaoEquipamento.setVisible(true);

            //ESCREVER O NOME DO EQUIPAMENTO NA JANELA DA NOVA MANUENTENÇÃO
            jTextFieldNomeEquipamentoSeleccionado.setText(nomeEquipamento);            
            
            
            System.out.println("\n***BOTAO DE MANUTENÇÃO -> JANELA CONSULTA DADOS");
            System.out.println("NOME EQUIPAMENTO SELEC.: " + nomeEquipamento );
            System.out.println("ID EQUIPAMENTO SELEC.: " + idEquipamentoControloResultado);

        }

    }//GEN-LAST:event_jButtonManutencaoConsultaEquipamentosActionPerformed

    private void jButtonControloResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonControloResultadosActionPerformed
        // CONSULTAR CONTROLO DE RESULTADOS -> JANELA CONSULTA EQUIPAMENTOS
        int linha = jTableConsultaEquipamentos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Seleccione a Linha de um Equipamento!");
        } else {
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeEquipamento = (String) jTableConsultaEquipamentos.getValueAt(linha, 0);

            idEquipamentoControloResultado = selectId("EQUIPAMENTO", "NOME", nomeEquipamento, "IDEQUIPAMENTO");
            idInsectocacadorControloResultado = 0;
            idEntradaControloResultado = 0;
            
            LimpaTabelaConsultaControloResultados();
            ConsultaControloResultado(idEntradaControloResultado, idEquipamentoControloResultado, idInsectocacadorControloResultado);
            
            //FALTA FAZER VALIDAÇÃO PARA DAR ERRO QUANDO NAO CONTEM NENGUMA LINHA INSERIDA
            
            
                if (CONTA_LINHAS_CONTROLORESULTADOS == 0) {
                    JOptionPane.showMessageDialog(jDialogConsultarControlos, "Linha Seleccionada não contem nenhuma Controlo de Resultado  ! ");
                } else {
                    jDialogConsultarControlos.setLocationRelativeTo(this);
                    jDialogConsultarControlos.setVisible(true);
                }
            
            
            System.out.println("\n***BOTAO DE CONTROLO RESULTADOS -> JANELA CONSULTA DADOS");
            System.out.println("NOME EQUIPAMENTO SELEC.: " + nomeEquipamento);
            System.out.println("ID EQUIPAMENTO SELEC.: " + idEquipamentoControloResultado);

        }

    }//GEN-LAST:event_jButtonControloResultadosActionPerformed

    private void jButtonEditarEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarEquipamentoActionPerformed
        // BOTAO EDITAR EQUIPAMENTO -> JANELA CONSULTA EQUIPAMENTOS
        int linha = jTableConsultaEquipamentos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Seleccione a Linha de um Equipamento!");
        } else {
            //DEVOLVE O NOME DO EQUIPAMENTO
            nomeAVerficar = (String) jTableConsultaEquipamentos.getValueAt(linha, 0);

            idEquipamentoControloResultado = selectId("EQUIPAMENTO", "NOME", nomeAVerficar, "IDEQUIPAMENTO");

            
            //VAMOS PERGUNTAR O QUE QUERMOS FAZER, SE ALTERAR O NOME OU O RESTO DA INFORMAÇÃO
//           
//             int resultOQueVaiEditar = JOptionPane.showConfirmDialog(jDialogNaoConformidades, "O que Deseja Editar, Só o Nome ou os outros campos?\nYES -> Só o Nome\nNO -> Restantes campos", null, JOptionPane.YES_NO_OPTION);
//
//            if (resultOQueVaiEditar == JOptionPane.YES_OPTION){
//                //VAMOS APENAS EDITAR O NOME
//                
//            }
//            
            
            
            
            jDialogNovoEquipamento.setLocationRelativeTo(this);
            jDialogNovoEquipamento.setVisible(true);
            ConsultaDadosEquipamentoAlterar();

            // ESCONDER O BOTAO DE GUARDA
            jButtonGuardarEquipametno.setVisible(false);
            jButtonActualizar.setVisible(true);

            System.out.println("\n***BOTAO DE CONTROLO RESULTADOS -> JANELA CONSULTA DADOS");
            System.out.println("NOME EQUIPAMENTO SELEC.: " + nomeAVerficar);
            System.out.println("ID EQUIPAMENTO SELEC.: " + idEquipamentoControloResultado);
        }
    }//GEN-LAST:event_jButtonEditarEquipamentoActionPerformed

    private void jButtonConsultaManutencaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaManutencaoActionPerformed
        // BOTAO CONSULTA MANUTENÇÃO -> JANALE CONSULTA EQUIPAMENTOS
        int linha = jTableConsultaEquipamentos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Seleccione a Linha de um Equipamento!");
        } else {
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeEquipamento = (String) jTableConsultaEquipamentos.getValueAt(linha, 0);

            idEquipamentoControloResultado = selectId("EQUIPAMENTO", "NOME", nomeEquipamento, "IDEQUIPAMENTO");

            LimpaTabelaConsultaManutencaoEquipamentos();
            ConsultaManutencaoEquipamentos();

            if (CONTA_LINHAS_MANUTENCAO == 0){
                JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Equipamento Não têm Nenhuma Manutenção ! ");
            } else{
                jDialogConsultaManutencaoEquipamentos.setLocationRelativeTo(this);
                jDialogConsultaManutencaoEquipamentos.setVisible(true);
            }

            System.out.println("\n***BOTAO CONSULTA MANUTENÇÕES -> JANELA CONSULTA EQUIPAMENTOS");
            System.out.println("NOME EQUIPAMENTO SELEC.: " + nomeEquipamento);
            System.out.println("ID EQUIPAMENTO SELEC.: " + idEquipamentoControloResultado);
        }
    }//GEN-LAST:event_jButtonConsultaManutencaoActionPerformed

    private void jTextFieldPesquisaEquipamentoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaEquipamentoCaretUpdate
        // TEXTFIEL PESQUISAR EQUIPAMENTO
        LimpaTabelaConsultaEquipamentos();
        PesquisarEquipamento();

    }//GEN-LAST:event_jTextFieldPesquisaEquipamentoCaretUpdate

    private void jButtonGravarManutencaoEquipametnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGravarManutencaoEquipametnoActionPerformed
        // BOTAO GRAVAR MANUTENCAO DE UM EQUIPAMETNO
        String observacao =  jTextAreaObservacaoManutencaoEquipameto.getText();

        if (observacao.equals("")){
            JOptionPane.showMessageDialog(jDialogNovaManutencaoEquipamento, "Introduza a Descrição da Manutenção!");
        } else{

            InserirManuencaoEquipamento();
            jDialogNovaManutencaoEquipamento.setVisible(false);

            LimpaTabelaConsultaManutencaoEquipamentos();
            ConsultaManutencaoEquipamentos();

            jDialogConsultaManutencaoEquipamentos.setLocationRelativeTo(this);
            jDialogConsultaManutencaoEquipamentos.setVisible(true);

        }
    }//GEN-LAST:event_jButtonGravarManutencaoEquipametnoActionPerformed

    private void jButtonSairManutencaoEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairManutencaoEquipamentoActionPerformed
        // BOTAO SAIR NOVA MANUTENÇÃO DE EQUIPAMENTOS
        jDialogNovaManutencaoEquipamento.setVisible(false);
    }//GEN-LAST:event_jButtonSairManutencaoEquipamentoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BOTAO VOLTAR -> JANELA CONSULTA MANUTENCAO EQUIPAMENTOS
        jDialogConsultaManutencaoEquipamentos.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        //VERIFICAR QUAL A OPÇAO QUE ESTAMOS A SELECCIONAR
        comboTipoOP = jComboBoxTipo.getSelectedItem().toString();

        if (comboTipoOP.equals("Outro")){
            System.out.println("TIPO : " + comboTipoOP);
            //BLOQUEAR COMBOBOX OPÇAO
            jComboBoxOpcao.setEnabled(false);
            //MOSTRAR TEXTFIEL
            jTextFieldOutraOpcao.setEnabled(true);
        }else if (comboTipoOP.equals("CONT. PRAGAS")){
            //Select a tabela dos insectadores
            LerBDInsectadores();
            jLabelOpcao.setText("Cont. Praga");
        }else if (comboTipoOP.equals("EQUIPAMENTO")){
            //SELECT A TABELA DE EQUIPAMENTOS
            LerBDEquipamento();
            jLabelOpcao.setText("Equipamento");
        }else if (comboTipoOP.equals("ENTRADA")){
            //SELECT A TABELA DE ENTRADAS
            LerBDEntradas();
            jLabelOpcao.setText("Lote de Entrada");
        }

    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void jComboBoxOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOpcaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxOpcaoActionPerformed

    private void jComboBoxResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxResultadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxResultadoActionPerformed

    private void jButtonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirActionPerformed
        //BOTAO INSERIR NOVA CONTROLO DE RESULTADOS
        InserirNovoControloResultados();

    }//GEN-LAST:event_jButtonInserirActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        //BOTAO FECHAR A JANELA CONTROLO DE RESULTADOS
        jComboBoxOpcao.setEnabled(true);

        jTextFieldOutraOpcao.setText("");
        jTextFieldOutraOpcao.setEnabled(true);

        jDialogNovoControloResultados.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

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
           // int resultNaoConformidadeGuardar = JOptionPane.showConfirmDialog(jDialogNaoConformidades, "Deseja Guardar os Dados e Voltar ao Menu Principal?", null, JOptionPane.YES_NO_OPTION);

            //if (resultNaoConformidadeGuardar == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(jDialogNaoConformidades, "Não Conformidade Adicionada Com Sucesso !");
                GravarNaoConformidade();
                jDialogNaoConformidades.setVisible(false);
            //}

        }

    }//GEN-LAST:event_jButtonGravarNaoConformidadeActionPerformed

    private void jButtonCancelarNaoConformidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarNaoConformidadeActionPerformed
        // BOTAO FECHAR JANELA NAO CONFORMIDADES
        jDialogNaoConformidades.setVisible(false);
        LimpaTabelaNaoConformidades();
    }//GEN-LAST:event_jButtonCancelarNaoConformidadeActionPerformed

    private void jButtonGravarMedidaCorrectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGravarMedidaCorrectivaActionPerformed
        // BOTAO GUARDAR MEDIDA CORRETIVAS

        //SE FOR = 1 QUER DIZER QUE ESTA É A ULTIMA NAO CONFORMIDADE
        if (CONTA_LINHAS_NAOCONFORMIDADES == 0){

        } else{//SE FOR DIFENTE DE 1 QUER DIZER QUE TEMOS MAIS NAO CONFORMIDADES
            ActualizaMedidaCorrectiva();
            //ATUALIZAR A TABELA NAO CONFORMIDADES
            LimpaTabelaNaoConformidades();
            ConsultaBDNaoConformidadeSelect();

            jTextAreaObservacaoMedidaCorrectiva.setText("");
            jDialogMedidasCorrectiva.setVisible(false);
        }

    }//GEN-LAST:event_jButtonGravarMedidaCorrectivaActionPerformed

    private void jButtonCancelarMedidaCorrectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarMedidaCorrectivaActionPerformed
        //BOTAO FEXHAR JANELA MEDIDAS CORRECTIVAS
        jDialogMedidasCorrectiva.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarMedidaCorrectivaActionPerformed

    private void jTableConsultarControlosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultarControlosMouseClicked
        // QUANDO CARREGAMOS NA TABELA
        LimpaTabelaNaoConformidades();
        //        LerBDConsultaNaoConformidades();

    }//GEN-LAST:event_jTableConsultarControlosMouseClicked

    private void jButtonFecharConsultaControlosResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharConsultaControlosResultadosActionPerformed
        // FECHAR JANELA CONSULTAR RESULTADOS
        jDialogConsultarControlos.setVisible(false);
    }//GEN-LAST:event_jButtonFecharConsultaControlosResultadosActionPerformed

    private void jButtonCriaNovoControloResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriaNovoControloResultadosActionPerformed
        // CRIAR NOVA CONTROLO DE RESULTADOS

        //COMBOBOX FUNCIONARIO CARREGAR DADOS
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
            idFuncionarioNaoConformidade = 0;
            String nomeFunc = (String) jTableConsultarControlos.getValueAt(linha, 0);
            secçãoControloResultadoSeleccionada = (String) jTableConsultarControlos.getValueAt(linha,1);
            String resultado = (String) jTableConsultarControlos.getValueAt(linha, 3);
            dataControloResultadoSeleccionada = (String) jTableConsultarControlos.getValueAt(linha, 2);
            
            
            //DEVOLVE O ID DO FUNCIONARIO
            idFuncionarioNaoConformidade = selectId("FUNCIONARIO", "Nome", nomeFunc, "IDFUNCIONARIO");
            
            //VERIFICAR SE A LINHA QUE ESTAMOS A SELECCINAR, O RESILTADO É
            // C OU NC
            if (resultado.equals("NC")) {                                
                //LER OS DADOS PARA JANELA SEGUINTE - QUANDO ESTA JA TEM NC INSERIDAS
                seccaoCRparaNC = (String) jTableConsultarControlos.getValueAt(linha, 1);
                problemaCRparaNC = (String) jTableConsultarControlos.getValueAt(linha, 4);                
                System.out.println("SECÇÃO DO CR : " + seccaoCRparaNC);
                System.out.println("PROBLEMA DO CR : " + problemaCRparaNC);
                
                LimpaTabelaNaoConformidades();
                ConsultaBDNaoConformidadeSelect();                
                
                jTextFieldVerSeccaoNC.setText(seccaoCRparaNC);
                jTextAreaVerProblemaNC.setText(problemaCRparaNC);
                
                
                        

            } else {
                JOptionPane.showMessageDialog(null, "Linha Selecciona esta CONFORME ! ");
            }
        
        
        }
    }//GEN-LAST:event_jButtonVerNaoConformidadeActionPerformed

    private void jComboBoxSeccaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSeccaoActionPerformed
        //QUAL A SESSAO QUE VAMOS CARREGAAR PARA  A TABELA
        String comboPesquisa = jComboBoxSeccao.getSelectedItem().toString();
        String nomeColuna = "";

        if (comboPesquisa.equals("ENTRADA")){
            LimpaTabelaControloResultados();
            nomeColuna = "IDENTRADA";
            LerBDControloResultados(nomeColuna);
            jButtonVerNaoConformidadeEntrada.setVisible(true);

        }else if (comboPesquisa.equals("EQUIPAMENTO")){
            LimpaTabelaControloResultados();
            nomeColuna = "IDEQUIPAMENTO";
            LerBDControloResultados(nomeColuna);
            jButtonVerNaoConformidadeEntrada.setVisible(false);

        }else if (comboPesquisa.equals("CONT. PRAGAS")){
            LimpaTabelaControloResultados();
            nomeColuna = "IDINSECTOCACADORES";
            LerBDControloResultados(nomeColuna);
            jButtonVerNaoConformidadeEntrada.setVisible(false);

        }else if (comboPesquisa.equals("OUTROS")){
            LimpaTabelaControloResultados();
            nomeColuna = "OUTROS";
            LerBDControloResultados(nomeColuna);
            jButtonVerNaoConformidadeEntrada.setVisible(false);

        }

        System.out.println("--OPÇÃO MOSTRAR NA TABELA CONSULTA CONTROLO DE RESULADOS");
        System.out.println("OP : " + comboPesquisa);
        System.out.println("NOME COLUNA : " + nomeColuna);



    }//GEN-LAST:event_jComboBoxSeccaoActionPerformed

    private void jButtonVoltarMedidasCorrectivasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarMedidasCorrectivasActionPerformed
        // BOTOA VOLTAR
        jDialogConsultarMedidasCorrectivas.setVisible(false);
        LimpaTabelaConsultaMedidasCorrectivas();
    }//GEN-LAST:event_jButtonVoltarMedidasCorrectivasActionPerformed

    private void jButtonNovoControlo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoControlo1ActionPerformed
        // TODO add your handling code here:
        //ABRIR JANELA ESCOLHE
        jDialogNovoControloResultados.setLocationRelativeTo(this);
        jDialogNovoControloResultados.setVisible(true);

        LimpaCamposNovoControloResultado();
        
        

    }//GEN-LAST:event_jButtonNovoControlo1ActionPerformed

    private void jButtonControlos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonControlos1ActionPerformed
        // TODO add your handling code here:
        //LimpaTabelaConsultarControlos();
        
        jComboBoxOpcao.setEnabled(true);
        
        jTextFieldOutraOpcao.setText("");
        jTextFieldOutraOpcao.setEnabled(true);
       
        jTextAreaDescricao.setText("");
        
        jDialogConsultarControlos.setLocationRelativeTo(this);
        jDialogConsultarControlos.setVisible(true);
        
        jButtonVerNaoConformidadeEntrada.setVisible(false);
        
        
    }//GEN-LAST:event_jButtonControlos1ActionPerformed

    private void jButtonNovaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaEntradaActionPerformed
        //BOTAO NOVA ENTRADA -> JANELA MENU ENTRADA
        jDialogNovaEntrada.setLocationRelativeTo(this);
        jDialogNovaEntrada.setVisible(true);
       jLabelFuncionarioLogin.setText("" + NomeFuncionarioLogin);
        LerBDFornecedorComboBox(jComboBoxFornecedorEntrada);
        LerBDMateriaPrimaComboBox(jComboBoxMateriaPrimaEntrada, "true");

        //PAINEIS VISIBEIS
        //panel entradas
        for (Component cp : jPanelNovaEntrada.getComponents())
        cp.setEnabled(true);
        //panel conformidades
        for (Component cp : jPanelConformidadesNovaEntrada.getComponents())
        cp.setEnabled(true);
        //botoes
        for (Component cp : jPanelBotoesNovaEntrada.getComponents())
        cp.setEnabled(true);

        //BLOQUEAR O PANEL E OS COMPONENTES
        for (Component cp : jPanelADevolverNovaEntrada.getComponents())
        cp.setEnabled(false);

        jButtonDesvloquearPanel.setVisible(false);

        //TEXTO DO BOTAO
        jButtonGuardarNovaEntrada.setText("Verificar Devolução");
        //TEXTO DE QUANTIDADES
        jTextFieldQuantidadeEntrada.setText("0.0");
        jTextFieldQuantidadeADevolver.setText("0.0");

        //COMBO BOX CONFOMIDADES COLOCAR VALOR A ZERO
        jComboBoxTemperaturaEntrada.setSelectedIndex(0);
        jComboBoxDataValidadeEntrada.setSelectedIndex(0);
        jComboBoxCaraOrgonolepticasEntradas.setSelectedIndex(0);
        jComboBoxEmbalagemEntradas.setSelectedIndex(0);

        //GERAR LOTE
        GeraLote();
        jTextFieldLoteEntrada.setText(geraLote);

    }//GEN-LAST:event_jButtonNovaEntradaActionPerformed

    private void jButtonConsultaEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaEntradasActionPerformed
        // BOTAO CONSULTA ENTRADAS -> JANELA MENU
        jDialogConsultaEntradas.setLocationRelativeTo(this);
        jDialogConsultaEntradas.setVisible(true);

        jButtonDevolucao.setVisible(false);

        jLabelTituloConsultaEntradas.setText("Consulta Todas as Entradas");

        LimpaTabelaEntradas();
        ConsultaEntradas();
    }//GEN-LAST:event_jButtonConsultaEntradasActionPerformed

    private void jButtonNovaMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaMateriaPrimaActionPerformed
        // NOVA MATERIA PRIMA -> JANELA MENU
        jDialogNovaMateriaPrima.setLocationRelativeTo(this);
        jDialogNovaMateriaPrima.setVisible(true);
        LimpaNovaMateriaPrima();
    }//GEN-LAST:event_jButtonNovaMateriaPrimaActionPerformed

    private void jButtonNovoForncedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoForncedorActionPerformed
        // BOTAO NOVO FORNECEDOR -> JANELA MENU
        
        //ESCONDER BOTAO ATUALIZAR
        jButtonUpdateFornecedor.setVisible(false);
        
        jDialogNovoFornecedor.setLocationRelativeTo(this);
        jDialogNovoFornecedor.setVisible(true);
    }//GEN-LAST:event_jButtonNovoForncedorActionPerformed

    private void jButtonConsultaDevolucoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaDevolucoesActionPerformed
        //BOTAO CONSULTA DEVOLUÇOES -> JANELA MENUS
        jDialogConsultaDevolucoes.setLocationRelativeTo(this);
        jDialogConsultaDevolucoes.setVisible(true);
        LimpaTabelaConsultaDevolucoes();
        ConsultaDevolucoesTabela();

        LerBDFornecedorComboBox(jComboBoxFornecedorDevolucoes);
        LerBDMateriaPrimaComboBox(jComboBoxMateriaPrimaDevolucoes,"devolucao");
    }//GEN-LAST:event_jButtonConsultaDevolucoesActionPerformed

    private void jButtonGuardarNovaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarNovaEntradaActionPerformed
        // BOTAO GUARDAR -> JANELA ENTRADAS

        //FAZER AS VALIDAÇÕES, SE OS CAMPOS ESTAO TODOS PREENCHIDOS
        
        String nomeFornecedorEntrada = jComboBoxFornecedorEntrada.getSelectedItem().toString();
        String nomeMateriaPrimaEntrada = jComboBoxMateriaPrimaEntrada.getSelectedItem().toString();
        float qantidadeMatPrimaEntrada = Float.parseFloat(jTextFieldQuantidadeEntrada.getText().toString());

        String conformidadeTemperatura = jComboBoxTemperaturaEntrada.getSelectedItem().toString();
        String conformidadeDtaValidade = jComboBoxDataValidadeEntrada.getSelectedItem().toString();
        String conformidadeCaratOrgono = jComboBoxCaraOrgonolepticasEntradas.getSelectedItem().toString();
        String conformidadeEmbalagem = jComboBoxEmbalagemEntradas.getSelectedItem().toString();

        if (nomeFornecedorEntrada.equals("--Fornecedor--")){
            JOptionPane.showMessageDialog(jDialogNovaEntrada, "Por Favor !\nSelecciona um Fornecedor!");
        }else if (nomeMateriaPrimaEntrada.equals("--Materia Prima--")){
            JOptionPane.showMessageDialog(jDialogNovaEntrada, "Por Favor !\nSelecciona uma Matéria-Prima!");
        }else if(qantidadeMatPrimaEntrada == 0.0){
            JOptionPane.showMessageDialog(jDialogNovaEntrada, "Por Favor !\nQuantidade não pode ser Zero!");
        }else if(conformidadeTemperatura.equals("--Opção--")){
            JOptionPane.showMessageDialog(jDialogNovaEntrada, "Por Favor !\nSelecciona a Conformidade da Temperatura!");
        }else if (conformidadeDtaValidade.equals("--Opção--")){
            JOptionPane.showMessageDialog(jDialogNovaEntrada, "Por Favor !\nSelecciona a Conformidade da Data de Validade!");
        }else if (conformidadeCaratOrgono.equals("--Opção--")){
            JOptionPane.showMessageDialog(jDialogNovaEntrada, "Por Favor !\nSelecciona a Conformidade das Caract. Orgonolepticas!");
        }else if (conformidadeEmbalagem.equals("--Opção--")){
            JOptionPane.showMessageDialog(jDialogNovaEntrada, "Por Favor !\nSelecciona a Conformidade da Embalagem/Transporte!");
        }else{

            InserirNovaEntrada();
        }
    }//GEN-LAST:event_jButtonGuardarNovaEntradaActionPerformed

    private void jButtonSairNovaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairNovaEntradaActionPerformed
        // BOTAO SAIR NOVA ENTRADA
        jDialogNovaEntrada.setVisible(false);
    }//GEN-LAST:event_jButtonSairNovaEntradaActionPerformed

    private void jButtonDesvloquearPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesvloquearPanelActionPerformed
        // DESBLOQUEAR PANEL'S DAS ENTRADAS
        for (Component cp : jPanelADevolverNovaEntrada.getComponents())
        cp.setEnabled(true);

        for (Component cp : jPanelConformidadesNovaEntrada.getComponents())
        cp.setEnabled(true);

        for (Component cp : jPanelNovaEntrada.getComponents())
        cp.setEnabled(true);

        jButtonDesvloquearPanel.setVisible(false);

    }//GEN-LAST:event_jButtonDesvloquearPanelActionPerformed

    private void jButtonGuardarMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarMateriaPrimaActionPerformed
        // BOTAO GUARDAR NOVA MATERIAS PRIMA -> JANELA NOVA MATERIA PRIMA
        String nomeMateria = jTextFieldNomeMateriaPrima.getText();
        String descricaoMateria = jTextAreaDescricaoMateriaPrima.getText();
        String unidadeMateria = jTextFieldUnidadeMateriaPrima.getText();

        if (nomeMateria.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Nome da Materia Prima !");
        } else if (unidadeMateria.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira a Unidade da Materia Prima !");
        } else {
            jDialogNovaMateriaPrima.setVisible(false);
            InserirNovaMateriaPrima();
            LimpaNovaMateriaPrima();
        }
    }//GEN-LAST:event_jButtonGuardarMateriaPrimaActionPerformed

    private void jButtonSairMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairMateriaPrimaActionPerformed
        // BOTAO SAIR -> JANELA MATERIA PRIMA
        jDialogNovaMateriaPrima.setVisible(false);
    }//GEN-LAST:event_jButtonSairMateriaPrimaActionPerformed

    private void jTextFieldCodPostalFornecedorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldCodPostalFornecedorCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodPostalFornecedorCaretUpdate

    private void jTextFieldCodPostalFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodPostalFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodPostalFornecedorActionPerformed

    private void jTextFieldTipoProdutoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTipoProdutoFornecedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTipoProdutoFornecedorActionPerformed

    private void jButtonGuardarNovoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarNovoFornecedorActionPerformed
        // BOTAO GUARDAR NOVO FORNECEDOR -> JANELA NOVO FORNECEDOR
        String nome = jTextFieldNomeFornecedor.getText();
        String morada = jTextFieldMoradaFornecedor.getText();
        String codPostal = jTextFieldCodPostalFornecedor.getText();
        String localidade = jTextFieldLocalidadeFornecedor.getText();
        String contacto = jTextFieldContactoFornecedor.getText();
        String email = jTextFieldEmailFornecedor.getText();
        String nif = jTextFieldNIFFornecedor.getText();
        String tipoProduto = jTextFieldTipoProdutoFornecedor.getText();

        if (nome.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Nome !");
        } else if (morada.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira a Morada !");
        } else if (codPostal.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Codigo Postal !");
        } else if (localidade.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira a Localidade !");
        } else if (contacto.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Contacto !");
        } else if (nif.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o NIF !");
        }else if (tipoProduto.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Tipo de Produto !");
        }else {

            InserirNovoFornecedor();
            LimpaNovoFornecedor();
            jDialogNovoFornecedor.setVisible(false);
        }

    }//GEN-LAST:event_jButtonGuardarNovoFornecedorActionPerformed

    private void jButtonSairNovoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairNovoFornecedorActionPerformed
        // BOTAO SAIR -> JANELA NOVO FORNECEDOR
        jDialogNovoFornecedor.setVisible(false);
        LimpaNovoFornecedor();
    }//GEN-LAST:event_jButtonSairNovoFornecedorActionPerformed

    private void jButtonGuardaDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardaDevolucaoActionPerformed
        // BOTAO GUARDAR -> JANELA DEVOLUÇOES
        
        InserirDevolucao();

    }//GEN-LAST:event_jButtonGuardaDevolucaoActionPerformed

    private void jButtonSairDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairDevolucaoActionPerformed
        // BOTAO SAIR -> JANELA DEVOLUÇÕES
        jDialogDevolucoes.setVisible(false);
    }//GEN-LAST:event_jButtonSairDevolucaoActionPerformed

    private void jButtonConsultaEntradasSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaEntradasSairActionPerformed
        // BOTAO SAIR -> JANELA CONSULTA ENTRADAS
        jDialogConsultaEntradas.setVisible(false);
    }//GEN-LAST:event_jButtonConsultaEntradasSairActionPerformed

    private void jButtonVerDevolucoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerDevolucoesActionPerformed
        // BOTAO VER DEVOLUÇOES -> JANELA CONSULTA ENTRADAS
        String nomebotao = jButtonVerDevolucoes.getText();

        //int linha = jTableConsultaEntradas.getSelectedRow();

        //VERIFICAR SE A LINHA SELECCIONADA TEM DEVOLUÇÃO

        if (nomebotao.equals("Fazer Devolução")) {
            jButtonVerDevolucoes.setText("Ver Entradas");
            LimpaTabelaEntradas();
            ConsultaDevolucoesEntradas();

            jLabelTituloConsultaEntradas.setText("Consulta as Possiveis Devoluções");

            jButtonDevolucao.setVisible(true);
            jButtonVerControloResultados.setVisible(false);

        }else{
            jButtonVerDevolucoes.setText("Fazer Devolução");
            LimpaTabelaEntradas();
            ConsultaEntradas();

            jLabelTituloConsultaEntradas.setText("Consulta Todas as Entradas");

            jButtonDevolucao.setVisible(false);
            jButtonVerControloResultados.setVisible(true);

        }

    }//GEN-LAST:event_jButtonVerDevolucoesActionPerformed

    private void jButtonDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDevolucaoActionPerformed
        // BOTAO DEVOLUÇÃO -> JANELA CONSULTA ENTRADAS
        //VERIFICAR SE TEMOS ALGUMA LINHA SELECIONADA
        int linha = jTableConsultaEntradas.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione a Linha de uma Entrada ! ");
        } else {

            VerEntradaSeleccionadaConsultaDevolucao();
            jDialogDevolucoes.setLocationRelativeTo(this);
            jDialogDevolucoes.setVisible(true);
            jLabelFuncionarioLogin6.setText("" + NomeFuncionarioLogin);

            CalculaQuantoFaltaDevolver();

            LimpaNovaDevolucao();
        }
    }//GEN-LAST:event_jButtonDevolucaoActionPerformed

    private void jTextFieldPesquisarEntradaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarEntradaCaretUpdate
        // PESQUISAR ENTRADA
        LimpaTabelaEntradas();
        PesquisaEntrada();

    }//GEN-LAST:event_jTextFieldPesquisarEntradaCaretUpdate

    private void jButtonVerControloResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerControloResultadosActionPerformed
        // VER CONTROLO DE RESULTADOS -> JANELA CONSULTA ENTRADAS
        VerEntradaSeleccionadaConsultaControloResultados();

        LimpaTabelaControloResultados();
        ConsultaControloResultadosEntradas();

        if(abrirControloResultados == true){
            jDialogConsultarControlos.setLocationRelativeTo(this);            
            jDialogConsultarControlos.setVisible(true);
        }

    }//GEN-LAST:event_jButtonVerControloResultadosActionPerformed

    private void jButtonVerLoteTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerLoteTotalActionPerformed
        // BOTAO VER TOTAL DE MATERIA PRIMA SEM DEVOLUÇOES -> JANELA CONSULTA ENTRADAS

        int linha = jTableConsultaEntradas.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaEntradas, "Seleccione a Linha de uma Entrada!");
        } else {

            String valor = (String) jTableConsultaEntradas.getValueAt(linha, 7);
            
            if (valor.equals("0.0")) {
                String valorEntrada = (String) jTableConsultaEntradas.getValueAt(linha, 6);
                JOptionPane.showMessageDialog(jDialogConsultaEntradas, "Linha Seleccionada não contem devoluções !\n\n"
                        + "Valor total: "+ valorEntrada);
            } else {

                LoteOriginalSeleccionado = (String) jTableConsultaEntradas.getValueAt(linha, 4);
                System.out.println("LOTE ORIGINAL : " + LoteOriginalSeleccionado);
                CalculaQuatidadeLoteTotalEntrada();
            }
        }
    }//GEN-LAST:event_jButtonVerLoteTotalActionPerformed

    private void jButtonConsultaDevolucoesSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaDevolucoesSairActionPerformed
        //BOTAO SAIR CONSULTA DEVOLUCOES -> JANELA CONCULTA DEVOLUÇOES
        jDialogConsultaDevolucoes.setVisible(false);
    }//GEN-LAST:event_jButtonConsultaDevolucoesSairActionPerformed

    private void jButtonVerFichaDetalhadaConsultaDevolucoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerFichaDetalhadaConsultaDevolucoesActionPerformed
        // VER FICHA DETALHADA -> JANELA CONSULTA DEVLUÇOES

        int linha = jTableConsultaDevolucoes.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione a Linha de uma Devolução!");
        } else {
            //VERIFCAR QUAL É A dEVOLUÇÃO

            String nomeForncedor = (String) jTableConsultaDevolucoes.getValueAt(linha, 1);
            int idFornecedor = selectId("FORNECEDOR", "NOME", nomeForncedor, "IDFORNECEDOR");

            String materiaPrima = (String) jTableConsultaDevolucoes.getValueAt(linha, 2);
            int idMateriaPrima = selectId("MATERIA_PRIMA", "NOME", materiaPrima, "IDMATERIAPRIMA");

            String dataDevolucao = (String) jTableConsultaDevolucoes.getValueAt(linha, 5);

            String quant = (String) jTableConsultaDevolucoes.getValueAt(linha, 6);
            float quantidade = Float.parseFloat(quant);

            try { 
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }

            //SABER QUAL O ID DA DEVOLUÇÃO, PARA FAZER A PESQUISA
            try {

                con = DriverManager.getConnection(url);
                String nomeTabela = "DEVOLUCOES";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE IDFORNECEDOR=" + idFornecedor + " AND IDMATERIAPRIMA=" + idMateriaPrima + " AND DATADEVOLUCAO='" + dataDevolucao + "' AND QUANTIDADEDEVOLVIDA=" + quantidade;
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    idDevolucaoSeleccionado = rs.getInt("IDDEVOLUCAO");
                }

                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }

            ConsultaDevolucaoMostraFichaCompleta();

            System.out.println("NOME FORNECEDOR " + nomeForncedor);
            System.out.println("ID FORNECEDOR " + idFornecedor);
            System.out.println("NOME MATER. PRIMA " + materiaPrima);
            System.out.println("ID MATERIA PRIMA " + idMateriaPrima);
            System.out.println("QUANTIDADE " + quantidade);
            System.out.println("\nID DEVOLUÇÃO : " + idDevolucaoSeleccionado);

        }

    }//GEN-LAST:event_jButtonVerFichaDetalhadaConsultaDevolucoesActionPerformed

    private void jComboBoxFornecedorDevolucoesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxFornecedorDevolucoesPopupMenuWillBecomeInvisible
        //COMBO BOX MOSTRA FUNCIONARIO DEVOLUÇÕES -> PESQUISAR DEVOLUÇOES

        String nomeFornecedorPesquisaDevolucao = jComboBoxFornecedorDevolucoes.getSelectedItem().toString();
        idForncedorPesquisaDevolucao = selectId("FORNECEDOR", "NOME", nomeFornecedorPesquisaDevolucao, "IDFORNECEDOR");

        //VERIFICAR O CAMPO DA COMBO BOS MATERIA PRIMA
        String nomeMateriaPrimaPesquisaDevolucao = jComboBoxMateriaPrimaDevolucoes.getSelectedItem().toString();

        //PARA APARECEREM TODOS OS DADOS INSERIDOS
        if(nomeFornecedorPesquisaDevolucao.equals("--Fornecedor--")){
            ConsultaDevolucoesTabela();
        }

        if (nomeMateriaPrimaPesquisaDevolucao.equals("--Materia Prima--")) {
            idMateriaPrimaPesquisaDevolucao = 0;
        }

        LimpaTabelaConsultaDevolucoes();
        PesquisaDevolucoesComDadosDeEntrada();

        
        
        System.out.println("--- FORNECEDOR");
        System.out.println("FORNECEDOR PESQUISA : " + nomeFornecedorPesquisaDevolucao);
        System.out.println("ID FORNECEDOR : " + idForncedorPesquisaDevolucao);
        System.out.println("MATERIA PRIMA : " + idMateriaPrimaPesquisaDevolucao);
        //VER QUAL O NOME QUE ESTAMOS A SELECCIONAR

    }//GEN-LAST:event_jComboBoxFornecedorDevolucoesPopupMenuWillBecomeInvisible

    private void jComboBoxMateriaPrimaDevolucoesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxMateriaPrimaDevolucoesPopupMenuWillBecomeInvisible
        // COMBO BOX MOSTRA MATERIA PRIMA -> PESQUISAR DEVOLUÇOES

        String nomeMateriaPrimaPesquisaDevolucao = jComboBoxMateriaPrimaDevolucoes.getSelectedItem().toString();
        idMateriaPrimaPesquisaDevolucao = selectId("MATERIA_PRIMA", "NOME", nomeMateriaPrimaPesquisaDevolucao, "IDMATERIAPRIMA");

        //VERIFCAR QUAL A OPÇÃO ESCOLHIDA NO FORNECEDOR
        String nomeFornecedorPesquisaDevolucao = jComboBoxFornecedorDevolucoes.getSelectedItem().toString();

        if(nomeFornecedorPesquisaDevolucao.equals("--Fornecedor--")){
            idForncedorPesquisaDevolucao = 0;
        }

        LimpaTabelaConsultaDevolucoes();
        PesquisaDevolucoesComDadosDeEntrada();

        System.out.println("--- MATERIA PRIMA");
        System.out.println("MATERIA PESQUISA : " + nomeMateriaPrimaPesquisaDevolucao);
        System.out.println("ID MATERIA PRIMA : " + idMateriaPrimaPesquisaDevolucao);
        System.out.println("FORNECEDOR : " + nomeFornecedorPesquisaDevolucao);

    }//GEN-LAST:event_jComboBoxMateriaPrimaDevolucoesPopupMenuWillBecomeInvisible

    private void jButtonVerTodasAsDevolucoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerTodasAsDevolucoesActionPerformed
        // BOTAO VER TODAS AS DEVOLUÇOES

        LimpaTabelaConsultaDevolucoes();
        ConsultaDevolucoesTabela();

    }//GEN-LAST:event_jButtonVerTodasAsDevolucoesActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // BOTAO VOLTAR
        jDialogConsultaNaoConformidades.setVisible(false);
        LimpaTabelaNaoConformidades();
        //LimpaTabelaConsultaNaoConformidades();

    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonAddNovaNaoConformidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddNovaNaoConformidadeActionPerformed
        // ADICIONAR NOVA NAO CONFORMIDADE AKELE CONTROLO RESULTADOS
        int linha = jTableNaoConformidades.getSelectedRow();

        //if (linha == -1) {
         //   JOptionPane.showMessageDialog(jDialogConsultaNaoConformidades, "Seleccione Uma Linha da Tabela de Não Conformidade");
        //} else {
            
            

            //PARA ADICIONAR UMA NOVA NAO CONFORMIDADE NECESSITAMOS DE PASSAR OS CAMPOS "IDCONTRESULT" e "IDFUNCIONARIO"
            int funcionario = addNewNaoConformidadeIdFuncionario;
            int controResult = idControloResultadosID;
            
            //ESCREVER NA TEXT AREA O PROBLEMA LIDO ANTERIORMENTE
            jTextAreaProblemadoControlo.setText(observacaoDoControloResultadoParaANC);
            
            
            
            //CARREGAR COMBOBOX FUNCIONARIO
            LerBDFuncionario(jComboBoxNaoConformidadeFuncionarioResponsav);
            jLabelFuncionarioLogin4.setText("" + NomeFuncionarioLogin);

            //MOSTRAR JANELA NAO CONFORMIDADES
            jDialogNaoConformidades.setLocationRelativeTo(this);
            jDialogNaoConformidades.setVisible(true);
            
            //ESCREVER
            System.out.println("CONTROLO RESULTADOS");
            System.out.println("FUNCIONARIO: " + funcionario);
            System.out.println("CONTRO RESULT: " + controResult);
            System.out.println("\n\n");
        //}
    }//GEN-LAST:event_jButtonAddNovaNaoConformidadeActionPerformed

    private void jButtonAddMedidaCorrectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddMedidaCorrectivaActionPerformed
        // ADICIONAR MEDIDA CORRECTIVA

        int linha = jTableNaoConformidades.getSelectedRow();
        idMedidaCorrectivaUpdate = 0;

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaNaoConformidades, "Seleccione Uma Linha da Tabela de Não Conformidade");
        }else{
            //VER O ID DA NAO CONFORMIDADE
                        
            ConsultaBDMedidasCorrectivas();
            
            //PREENCHER OS CAMPOS DE INFORMAÇÃO DA MEDIDA CORRECTIVA
            jTextFieldSeccaoMC.setText(jTextFieldVerSeccaoNC.getText());
            jTextAreaProblemaMC.setText(jTextAreaVerProblemaNC.getText());
            
            //LER DA TABELA
            String detalhes = (String) jTableNaoConformidades.getValueAt(linha, 2);
            String medidasCorrectivas = (String) jTableNaoConformidades.getValueAt(linha, 3);
            jTextAreaDetalhesMC.setText(detalhes);
            jTextAreaMedidasCorrectivasMC.setText(medidasCorrectivas);
            

        }
    }//GEN-LAST:event_jButtonAddMedidaCorrectivaActionPerformed

    private void jMenuItemEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEntradasActionPerformed
        // MENU BAR - ENTRADAS
        jDialogMenuEntradas.setLocationRelativeTo(this);
        jDialogMenuEntradas.setVisible(true);
        jLabelUserLogin4.setText("* " + NomeFuncionarioLogin);
    }//GEN-LAST:event_jMenuItemEntradasActionPerformed

    private void jMenuItemInsectocacadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInsectocacadoresActionPerformed
        // MENU BAR INSECTOCAÇADORES
        jDialogMenuInsectocacadores.setLocationRelativeTo(this);
        jDialogMenuInsectocacadores.setVisible(true);
        jLabelUserLogin2.setText("* " + NomeFuncionarioLogin);
    }//GEN-LAST:event_jMenuItemInsectocacadoresActionPerformed

    private void jMenuItemEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEquipamentoActionPerformed
        // MENU BAR - EQUIPAMENTOS
        jDialogMenuEquipamentos.setLocationRelativeTo(this);
        jDialogMenuEquipamentos.setVisible(true);
        jLabelUserLogin3.setText("* " + NomeFuncionarioLogin);
    }//GEN-LAST:event_jMenuItemEquipamentoActionPerformed

    private void jMenuItemControloResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemControloResultadosActionPerformed
        // MENU BAR - CONTROLO RESULTADOS
        jDialogMenuControloResultados.setLocationRelativeTo(this);
        jDialogMenuControloResultados.setVisible(true);
        jLabelUserLogin.setText("* " + NomeFuncionarioLogin);
    }//GEN-LAST:event_jMenuItemControloResultadosActionPerformed

    private void jButtonConsultarFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarFornecedoresActionPerformed
        // BOTAO CONSULTA FORNECEDORES 
        jDialogConsultaFornecedores.setLocationRelativeTo(this);
        jDialogConsultaFornecedores.setVisible(true);
        EstadoFornecedor = "true";
        jLabelConsultaFornecedores.setText("CONSULTA FORNECEDORES - ATIVOS");
        LimpaTabelaFornecedores();
        ConsultaFornecedores();
    }//GEN-LAST:event_jButtonConsultarFornecedoresActionPerformed

    private void jButtonFornecedoresInativosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFornecedoresInativosActionPerformed
        // VER FORNECEDORES INATIVOS
        String nomeBotao = jButtonFornecedoresInativos.getText();
      
        //VERFICAR SE TEMOS A LINHA SELECCIONADA
       

            //VER NOME DO FORNECEDOR (so existe um fornecedor com o mesmo nome)
            
            
            if (nomeBotao.equals("Ver Inativos")) {
                jButtonFornecedoresInativos.setText("Ver Ativos");
                jButtonFornecedoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ativos.png")));
                jLabelConsultaFornecedores.setText("CONSULTA FORNECEDORES - INATIVOS");
                //mostrar Inativos
                EstadoFornecedor = "false";
                LimpaTabelaFornecedores();
                ConsultaFornecedores();
                
            } else if (nomeBotao.equals("Ver Ativos")) {
                jButtonFornecedoresInativos.setText("Ver Inativos");
                jButtonFornecedoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png")));
                jLabelConsultaFornecedores.setText("CONSULTA FORNECEDORES - ATIVOS");
                //mostrar ativos
                EstadoFornecedor = "true";
                LimpaTabelaFornecedores();
                ConsultaFornecedores();
            }
            
            
            
        
    }//GEN-LAST:event_jButtonFornecedoresInativosActionPerformed

    private void jButtonEditarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarFornecedorActionPerformed
        // BOTAO EDITAR FORNECEDOR
         int linha = jTableConsultaFornecedor.getSelectedRow();

        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaFornecedores, "Seleccione a Linha de um Fornecedor !");
        } else {
            //BUSCAR NOME DO FORNECEDOR
            String nomeForncedor = (String) jTableConsultaFornecedor.getValueAt(linha, 0);
            String nif = (String) jTableConsultaFornecedor.getValueAt(linha, 1);
            
            //buscar o id
            idFornecedorPesquisar = selectId("FORNECEDOR", "NOME", nomeForncedor, "IDFORNECEDOR");
            
            System.out.println("ID FORNECEDOR -> " + idFornecedorPesquisar);
            ConsultaDadosForncedorAlterar();
            
            
            //esconder botao guardar
            jButtonGuardarNovoFornecedor.setVisible(false);
            jButtonUpdateFornecedor.setVisible(true);
            
            
            jDialogNovoFornecedor.setLocationRelativeTo(this);
            jDialogNovoFornecedor.setVisible(true);
            
        }
        
        
        
    }//GEN-LAST:event_jButtonEditarFornecedorActionPerformed

    private void jButtonAlterarEstadoFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarEstadoFornecedorActionPerformed
        // BOTAO ALTERAR ESTADO DO FORNECEDOR
        int linha = jTableConsultaFornecedor.getSelectedRow();

        
        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaFornecedores, "Seleccione a Linha de um Fornecedor Inativo !");
        } else {
            //VER QUAL O FORNECEDOR SELECCIONADO
            String nomeForncedor = (String) jTableConsultaFornecedor.getValueAt(linha, 0);
                        
            //ID FORNECEDOR
            idFornecedorPesquisar = selectId("FORNECEDOR", "NOME", nomeForncedor, "IDFORNECEDOR");
            
            
            
            //VER QUAL O ESTADO 
            String nomeBotao = jButtonFornecedoresInativos.getText();
            
            if (nomeBotao.equals("Ver Inativos")){
                //colocar fornecedor Inactivo
                estadoFornecedor = false;
                ActualizaEstadoFornecedor();
                LimpaTabelaFornecedores();
                ConsultaFornecedores();
                JOptionPane.showMessageDialog(jDialogConsultaFornecedores, "Fornecedor Ficou Inativo !");               
            }else{
                //colcoar fornecedor Ativo
                estadoFornecedor = true;
                ActualizaEstadoFornecedor();
                LimpaTabelaFornecedores();
                ConsultaFornecedores();
                JOptionPane.showMessageDialog(jDialogConsultaFornecedores, "Fornecedor Ficou Ativo !");
            }               
            
            System.out.println("ID FORNECEDOR -> " + idFornecedorPesquisar);
        
        }
    }//GEN-LAST:event_jButtonAlterarEstadoFornecedorActionPerformed

    private void jButtonUpdateFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateFornecedorActionPerformed
        // UPDATE FORNECEDOR -> DEPOIS DE EDITAR
        String nome = jTextFieldNomeFornecedor.getText();
        String morada = jTextFieldMoradaFornecedor.getText();
        String codPostal = jTextFieldCodPostalFornecedor.getText();
        String localidade = jTextFieldLocalidadeFornecedor.getText();
        String contacto = jTextFieldContactoFornecedor.getText();
        String email = jTextFieldEmailFornecedor.getText();
        String nif = jTextFieldNIFFornecedor.getText();
        String tipoProduto = jTextFieldTipoProdutoFornecedor.getText();

        if (nome.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Nome !");
        } else if (morada.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira a Morada !");
        } else if (codPostal.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Codigo Postal !");
        } else if (localidade.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira a Localidade !");
        } else if (contacto.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Contacto !");
        } else if (nif.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o NIF !");
        }else if (tipoProduto.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Insira o Tipo de Produto !");
        }else {
            UpdateDadosFornecedor();
        }
    }//GEN-LAST:event_jButtonUpdateFornecedorActionPerformed

    private void jButtonFecharConsultaFornecedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharConsultaFornecedoresActionPerformed
        //BOTAO FECHAR CONSULTA FORNECEDORES
        jDialogConsultaFornecedores.setVisible(false);
    }//GEN-LAST:event_jButtonFecharConsultaFornecedoresActionPerformed

    private void jButtonSairConsultaMateriaPrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairConsultaMateriaPrimaActionPerformed
        // FECHAR JAELA CONSULTA MATERIAS-PRIMAS
        jDialogConsultaMateriasPrimas.setVisible(false);
    }//GEN-LAST:event_jButtonSairConsultaMateriaPrimaActionPerformed

    private void jButtonConsultaMateriasPrimasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaMateriasPrimasActionPerformed
        // ABRIR CONSULTA DE MATERIAS PRIMAS
        jDialogConsultaMateriasPrimas.setLocationRelativeTo(this);
        jDialogConsultaMateriasPrimas.setVisible(true);
        estadoMateriaPrima = true;
        jLabelConsultarMateriasPrimas.setText("Consultar Matérias-Primas - Ativas");
        ConsultaMateriasPrimas();
        
        
    }//GEN-LAST:event_jButtonConsultaMateriasPrimasActionPerformed

    private void jButtonVerInativosMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerInativosMPActionPerformed
        // TBOTAO VER INATIVOS - MATERIA PRIMA
        
        String nomeBotao = jButtonVerInativosMP.getText();
        
        if (nomeBotao.equals("Ver Inativas")){
            jButtonVerInativosMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ativos.png")));
            jButtonVerInativosMP.setText("Ver Ativas");
            jLabelConsultarMateriasPrimas.setText("Consultar Matérias-Primas - Inativas");
            estadoMateriaPrima = false;
            LimpaTabelaMateriaPrimas();
            ConsultaMateriasPrimas();
            
            
                
        } else if (nomeBotao.equals("Ver Ativas")){
            jButtonVerInativosMP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png")));
            jButtonVerInativosMP.setText("Ver Inativas");
            jLabelConsultarMateriasPrimas.setText("Consultar Matérias-Primas - Ativas");
            estadoMateriaPrima = true;
            LimpaTabelaMateriaPrimas();
            ConsultaMateriasPrimas();
           
             
        } 
        
        
        
    }//GEN-LAST:event_jButtonVerInativosMPActionPerformed

    private void jButtonAlterarEstadoMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarEstadoMPActionPerformed
        // ALTERAR ESTADO DA MATERIA PRIMA
        int linha = jTableConsultaMateriasPrimas.getSelectedRow();
        
        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaMateriasPrimas, "Seleccione a Linha de uma Matéria-Prima !");
        } else {
            //VER QUAL O FORNECEDOR SELECCIONADO
            String nomeMateriaPrima = (String) jTableConsultaMateriasPrimas.getValueAt(linha, 1);
//            System.out.println("NOME MATERI PRIMA -> " + nomeMateriaPrima);
            //ID MATERIA-PRIMA 
            idMateriaPrimaPesquisar = selectId("MATERIA_PRIMA", "NOME", nomeMateriaPrima, "IDMATERIAPRIMA");
//            System.out.println("ID MATERI PRIMA -> " + idMateriaPrimaPesquisar);
            
            //VER QUAL O ESTADO 
            String nomeBotao = jButtonVerInativosMP.getText();
            
            if (nomeBotao.equals("Ver Inativas")){
                //colocar materia prima Inactivo
                estadoMateriaPrima = false;
                ActualizaEstadoMateriaPrima();
                LimpaTabelaMateriaPrimas();
                ConsultaMateriasPrimas();
                jLabelConsultarMateriasPrimas.setText("Consultar Matérias-Primas - Inativas");
                
                JOptionPane.showMessageDialog(jDialogConsultaFornecedores, "Matéria-Prima Ficou Inativa !");               
            }else{
                //colcoar fornecedor Ativo
                estadoMateriaPrima = true;
                ActualizaEstadoMateriaPrima();
                LimpaTabelaMateriaPrimas();
                ConsultaMateriasPrimas();
                JOptionPane.showMessageDialog(jDialogConsultaFornecedores, "Matéria-Prima Ficou Ativa !");
                jLabelConsultarMateriasPrimas.setText("Consultar Matérias-Primas - Ativas");
            }          
            
            System.out.println("ID MATERIA PRIMA -> " + idMateriaPrimaPesquisar);
        }
    }//GEN-LAST:event_jButtonAlterarEstadoMPActionPerformed

    private void jTextFieldPesquisarMateriaPrimaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarMateriaPrimaCaretUpdate
        //TEXT FIEL PESQUISAR MATERIA PRIMA
       LimpaTabelaMateriaPrimas();
       PesquisaMateriaPrima();
    }//GEN-LAST:event_jTextFieldPesquisarMateriaPrimaCaretUpdate

    private void jButtonEditarMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarMPActionPerformed
        // EDITAR MATERIA PRIMA 
        int linha = jTableConsultaMateriasPrimas.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaMateriasPrimas, "Seleccione a Linha de uma Mat+eria-Prima!");
        } else {
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeMateriaPrima = (String) jTableConsultaMateriasPrimas.getValueAt(linha, 1);

             idMateriaPrimaPesquisar = selectId("MATERIA_PRIMA", "NOME", nomeMateriaPrima, "IDMATERIAPRIMA");

            jDialogNovaMateriaPrima.setLocationRelativeTo(this);
            jDialogNovaMateriaPrima.setVisible(true);
            ConsultaDadosMateriaPrimaAlterar();

            // ESCONDER O BOTAO DE GUARDA
            jButtonGuardarEquipametno.setVisible(false);
            jButtonActualizar.setVisible(true);

            System.out.println("\n***BOTAO DE CONTROLO RESULTADOS -> JANELA CONSULTA DADOS");
            System.out.println("NOME EQUIPAMENTO SELEC.: " + nomeMateriaPrima);
            System.out.println("ID EQUIPAMENTO SELEC.: " + idEquipamentoControloResultado);
        }
        
    }//GEN-LAST:event_jButtonEditarMPActionPerformed

    private void jButtonVerNaoConformidadeEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerNaoConformidadeEntradaActionPerformed
        // BOTAO VER NAO CONFORMIDADE DE UMA ENTRADA - JANELA CONSULTA CONTROLO DE RESULTADOS
        int linha = jTableConsultarControlos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultarControlos, "Seleccione a Linha de um Controlo!");
        } else {
            String loteConsultaControloNaoConformidades = (String) jTableConsultarControlos.getValueAt(linha, 1);
            
            verNaoConformidadeDeUmaEntrada(loteConsultaControloNaoConformidades);
        }
        
    }//GEN-LAST:event_jButtonVerNaoConformidadeEntradaActionPerformed

    private void jButtonVerInsectocacadoresInativosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerInsectocacadoresInativosActionPerformed
        // VER ESTADO DO CONTROLO DE PRAGAS - ATIVO / INATIVO
        
        String nomeBotao = jButtonVerInsectocacadoresInativos.getText();
        
        
        if (nomeBotao.equals("Ver Inativas")){
            jButtonVerInsectocacadoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ativos.png")));
            jButtonVerInsectocacadoresInativos.setText("Ver Ativas");
            jLabelConsultaControloPragas.setText("Consulta Controlo de Pragas - Inativos");
            estadoInsectocacadores = false;
            LimpaTabelaConsultaInsectocacadores();
            ConsultaInsectocacadores();
            
            
                
        } else if (nomeBotao.equals("Ver Ativas")){
            jButtonVerInsectocacadoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png")));
            jButtonVerInsectocacadoresInativos.setText("Ver Inativas");
            jLabelConsultaControloPragas.setText("Consulta Controlo de Pragas - Ativos");
            estadoInsectocacadores = true;
            LimpaTabelaConsultaInsectocacadores();
            ConsultaInsectocacadores();
           
             
        } 
        
        
        
    }//GEN-LAST:event_jButtonVerInsectocacadoresInativosActionPerformed

    private void jButtonAlterarEstadoInsectocacadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarEstadoInsectocacadoresActionPerformed
        // ALTERAR ESTADO DE UM CONTROLO D PRAGAS - 
        
        int linha = jTableConsultaInsecto.getSelectedRow();

        
        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Seleccione a Linha de um Controlo de Pragas !");
        } else {
            //ver qual o controlo de resultados seleccionado
            String refControlo = (String) jTableConsultaInsecto.getValueAt(linha, 0);
                        
            //ID FORNECEDOR
            idInsectocacadorPesquisar = selectId("INSECTOCACADORES", "REFERENCIA", refControlo, "IDINSECTOCACADORES");
            
            
            String nomeBotao = jButtonVerInsectocacadoresInativos.getText();
        
            if (nomeBotao.equals("Ver Inativas")){
                //colocar fornecedor Inactivo
                estadoInsectocacadores = false;
                AtualizarEstadoInsectocacador();
                LimpaTabelaConsultaInsectocacadores();
                ConsultaInsectocacadores();
                JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Controlo de Pragas Ficou Inativo !");     
                jButtonVerInsectocacadoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ativos.png")));
                jButtonVerInsectocacadoresInativos.setText("Ver Ativas");
                 jLabelConsultaControloPragas.setText("Consulta Controlo de Pragas - Inativos");
                
                
                
            }else if (nomeBotao.equals("Ver Ativas")){
                //colcoar fornecedor Ativo
                estadoInsectocacadores = true;
                AtualizarEstadoInsectocacador();
                LimpaTabelaConsultaInsectocacadores();
                ConsultaInsectocacadores();
                JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Controlo de Pragas Ficou Ativo !");
                jButtonVerInsectocacadoresInativos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png")));
                jButtonVerInsectocacadoresInativos.setText("Ver Inativas");
                jLabelConsultaControloPragas.setText("Consulta Controlo de Pragas - Ativos");
                
            }               
        }
        
    }//GEN-LAST:event_jButtonAlterarEstadoInsectocacadoresActionPerformed

    private void jButtonVerInativasEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerInativasEquipamentosActionPerformed
        //botao ver inativas equipamento
     
        String nomeBotao = jButtonVerInativasEquipamentos.getText();

        if (nomeBotao.equals("Ver Inativas")) {
            jButtonVerInativasEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ativos.png")));
            jButtonVerInativasEquipamentos.setText("Ver Ativas");
            jLabelConsultarEquipamentos.setText("Consulta Equipamentos - Inativos");
            estadoEquipamento = false;
            LimpaTabelaConsultaEquipamentos();
            ConsultaEquipamentos();

        } else if (nomeBotao.equals("Ver Ativas")) {
            jButtonVerInativasEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png")));
            jButtonVerInativasEquipamentos.setText("Ver Inativas");
            jLabelConsultarEquipamentos.setText("Consulta Equipamentos - Ativos");
            estadoEquipamento = true;
            LimpaTabelaConsultaEquipamentos();
            ConsultaEquipamentos();

        }

    }//GEN-LAST:event_jButtonVerInativasEquipamentosActionPerformed

    private void jButtonAlterarEstadoEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarEstadoEquipamentoActionPerformed
        //ALTERAR ESTADO EQUIPAMENTO
         int linha = jTableConsultaEquipamentos.getSelectedRow();

        
        if (linha == -1){
            JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Seleccione a Linha de um Equipamento !");
        } else {
            //ver qual o controlo de resultados seleccionado
            String nomeEquipa = (String) jTableConsultaEquipamentos.getValueAt(linha, 0);
                        
            //ID FORNECEDOR
            idEquipamentoPesquisar = selectId("EQUIPAMENTO", "NOME", nomeEquipa, "IDEQUIPAMENTO");
            System.out.println("ID Equuipamento -> " + idEquipamentoPesquisar);
            
            
            String nomeBotao = jButtonVerInativasEquipamentos.getText();
        
            if (nomeBotao.equals("Ver Inativas")){
                //colocar fornecedor Inactivo
                estadoEquipamento = false;
                AtualizaEstadoEquipametos();
                LimpaTabelaConsultaEquipamentos();
                ConsultaEquipamentos();
                JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Equipamento Ficou Inativo !");     
                jButtonVerInativasEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ativos.png")));
                jButtonVerInativasEquipamentos.setText("Ver Ativas");
                jLabelConsultarEquipamentos.setText("Consulta Equipamentos - Inativos");
                
                
                
            }else if (nomeBotao.equals("Ver Ativas")){
                //colcoar fornecedor Ativo
                estadoEquipamento = true;
                AtualizaEstadoEquipametos();
                LimpaTabelaConsultaEquipamentos();
                ConsultaEquipamentos();
                JOptionPane.showMessageDialog(jDialogConsultaEquipamentos, "Equipamento Ficou Ativo !");
                jButtonVerInativasEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inativos.png")));
                jButtonVerInativasEquipamentos.setText("Ver Inativas");
                jLabelConsultarEquipamentos.setText("Consulta Controlo de Pragas - Ativos");
                
            }               
        }
        
        
    }//GEN-LAST:event_jButtonAlterarEstadoEquipamentoActionPerformed

    private void jButtonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntrarActionPerformed
        // BOTAO LOGIN
        NomeFuncionarioLogin = jComboBoxNomeLogin.getSelectedItem().toString();
        String pwdInserida = jPasswordFieldLogin.getText();
        idFuncionarioLogin = selectId("FUNCIONARIO", "NOME", NomeFuncionarioLogin, "IDFUNCIONARIO");
        
           //LIMPAR JLABEL DE BOAS VINDAS
        jLabelBoasVindas.setText("Olá, " + NomeFuncionarioLogin);
        //jDialogJanelaPrincipal.repaint();
        
        VerificaLogin(pwdInserida);
             
        
                
        System.out.println("nomeFuncionario -> " + NomeFuncionarioLogin);
        System.out.println("idfuncionario -> " + idFuncionarioLogin);
        
        
    }//GEN-LAST:event_jButtonEntrarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //LOGOUT
        
        //FUNÇÃO PARA FECHAR TODAS AS JANELAS
        for (Window window : Login.getWindows()) {
            if (window instanceof JDialog){
                window.dispose();
            }
        }
      
        
        //LIMPAR CAMPOS DE LOGIN
        for (Component cp : jMenuBar1.getComponents())
        cp.setEnabled(false);
        
        //COLOCAR JANELA DE LOGIN ESCONDIDA
        for (Component cp : jPanelRealizaLogin.getComponents())
        cp.setEnabled(true);
        
        jComboBoxNomeLogin.setSelectedIndex(0);
        jPasswordFieldLogin.setText("");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextFieldPesquisarFornecedorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarFornecedorCaretUpdate
        // PESUISAR FORNECEDOR
        LimpaTabelaConsultaFornecedores();
        PesquisarFornecedor();
    }//GEN-LAST:event_jTextFieldPesquisarFornecedorCaretUpdate

    private void jButtonVerHistoricoControloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerHistoricoControloActionPerformed
        // VER HISTORICO DE UM CONTROLO DE RESULTADO
        
        int linha = jTableConsultarControlos.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione a Linha de um Controlo de Resultado!");
        } else {
            //DADOS DE PESQUISA
            String estado = (String) jTableConsultarControlos.getValueAt(linha, 3);
            
            //O HISTORICO SO ESTA PRESENTA PARA NC CONFORMES
            if (estado.equals("NC")){
                JOptionPane.showMessageDialog(jDialogConsultarControlos, "Linha Seleccionada não é CONFORME !");
            }else{
                //VAMOS VERIFICAR SE JA TEM HISTORICO
                LimpaTabelaConsultaHistorico();
                ConsultaHistoricoControlo();
               
                
            }
          
            
            
        }
        
    }//GEN-LAST:event_jButtonVerHistoricoControloActionPerformed

    private void jButtonSairHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairHistoricoActionPerformed
        // BOTAO SAIR DA JANELA HISTORICO
        jDialogVerHistoricoControlo.setVisible(false);
        
    }//GEN-LAST:event_jButtonSairHistoricoActionPerformed

    private void jButtonVerDatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerDatasActionPerformed
        // VER MEDIDAS CORRECTIVAS HISTORICO
        
        int linha = jTableVerHistorico.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogVerHistoricoControlo, "Seleccione Uma Linha !");
        } else {
        
            ConsultaHistoricoMedidaCorrectiva();
            
        }
        
    }//GEN-LAST:event_jButtonVerDatasActionPerformed

    private void jButtonSeleccionarNPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarNPFActionPerformed
        selectFile();
        jTextFieldUploudNUC.setText(jfc.getSelectedFile().getName());   
        
    }//GEN-LAST:event_jButtonSeleccionarNPFActionPerformed

    private void jComboBoxMateriaPrimaEntradaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxMateriaPrimaEntradaPopupMenuWillBecomeInvisible
        //Dependendo da materia prima seleccionada vamos mostrar a sua Unidade
        //MATERIA PRIMA SELECCIONADA
        String materiaPrimaSeleccionada = jComboBoxMateriaPrimaEntrada.getSelectedItem().toString();
        System.out.println("materiaPrimaSeleccionada : " + materiaPrimaSeleccionada);

        String unidadeMateriaPrima = "";

        //Ir a base de dados procurar o nome
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "MATERIA_PRIMA";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE NOME='" + materiaPrimaSeleccionada + "'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                unidadeMateriaPrima = rs.getString("UNIDADE");
            }
            st.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        if (!unidadeMateriaPrima.equals("")) {
            jTextFieldNovaEntradaUnidadeQuantidade.setText(unidadeMateriaPrima);
        } else {
            jTextFieldNovaEntradaUnidadeQuantidade.setText("ERROR");
        }

    }//GEN-LAST:event_jComboBoxMateriaPrimaEntradaPopupMenuWillBecomeInvisible

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        float resultado ;
        
        float valor1 = Float.parseFloat(JT01.getText());
        float valor2 = Float.parseFloat(JT02.getText());
        
        resultado = valor1 - valor2;
        System.out.println("resultado "+resultado +" = valor1"+valor1+" - valor2" + valor2);
//        DecimalFormat df = new DecimalFormat("0.0");
//        String fin = df.format(resultado);
//        System.out.println("FIN -> " + fin);
        
        //arredondar valor directamente
        float round = Round(resultado,2);
        
        
        JTRES.setText(""+round);
        System.out.println("RESULTADO FINAL -> " + round);
        
    }//GEN-LAST:event_jButton3ActionPerformed

 
    
    
    
    
    
    
    
    
    
    
    private void jButtonConsEquipamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsEquipamentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonConsEquipamentoActionPerformed

    private void jButtonConsDevoluçõesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsDevoluçõesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonConsDevoluçõesActionPerformed

    private void jButtonInserirNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInserirNovoActionPerformed
        //BOTAO PARA VER OS ATALHOS RAPIDOS DO INSERIR NOVO
        
        jPanelInserirNovo.setVisible(true);
        jPanelConsultar.setVisible(false);
    }//GEN-LAST:event_jButtonInserirNovoActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        // BOTAO PARA VER OS ATALHOS RAPIDOS DE CONSULTAS
        jPanelInserirNovo.setVisible(false);
        jPanelConsultar.setVisible(true);
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String cliente = "BrunoRafael";
        String prod = "Queijo Duro";
        gerarNomeFile(cliente, prod, 2);
        copyFile();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButtonAlterarReferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarReferenciaActionPerformed
        jDialogAlterarReferencia.setVisible(true);
        jDialogAlterarReferencia.setLocationRelativeTo(this);
    }//GEN-LAST:event_jButtonAlterarReferenciaActionPerformed

    private void jButtonGuardarNovaRefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarNovaRefActionPerformed
        //BOTAO PARA FAZER UPDATE A REFERENCIA QUANDO ESTA É EDITADA
        UpdateReferenciaInsectocacador();
        
    }//GEN-LAST:event_jButtonGuardarNovaRefActionPerformed

/*  ******************************    FUNCÇOES ************************************************************************************   */
    
    
    
    private void selectFile() {
        jfc.setDialogTitle("Selecione o Ficheiro");
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.setAcceptAllFileFilterUsed(false);
        int returnVal = jfc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            input = jfc.getSelectedFile();
        }
    }
    
    private void copyFile() {
        if (input == null) {
            JOptionPane.showMessageDialog(null, "Por Favor selecione um Ficheiro");
            return;
        }
        try {
            File file = new File(out, nomeFile);
            System.out.println("file = " + file.getPath());
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(input)));
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file)));
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line, 0, line.length());
                bw.newLine();
            }
            br.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("Exception occured:" + e.getMessage());
        }
    }
    
    private void gerarNomeFile(String n, String n2, int i) {
        /*
         * Algoritmo para geral o nome
         * nome_id_data.extinsao
         * ex:queijoSerra_2_20-10-2013.pdf
         */
        String nome = n;
        String nome2 = n2;
        String nomeFinal = "";
        String nomeFinal2 = "";
        int id = i;
        String data = "";

        //Eliminar possiveis espaços no nome
        String[] semEspacos = nome.split(" ");
        for (int j = 0; j < semEspacos.length; j++) {
            nomeFinal += semEspacos[j];
        }
        //Eliminar possiveis espaços no nome2
        String[] semEspacos2 = nome2.split(" ");
        for (int j = 0; j < semEspacos2.length; j++) {
            nomeFinal2 += semEspacos2[j];
        }
        //ler a data do sistema
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dat = new java.util.Date();
        data = df.format(dat);
        //Guardar extinção do ficheiro
        String s = input.getName();
        int ext = s.lastIndexOf(".");
        String extincao = s.substring(ext);
        //criar nome final
        nomeFile = nomeFinal + "_" + "_" + id + "_" + data + extincao;
    }
    
    
    private void VerificaLogin(String pwdInserida){
      
        boolean login = false;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "FUNCIONARIO";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE IDFUNCIONARIO="+idFuncionarioLogin+ " AND PASSWORD='"+pwdInserida+"'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                login = true;
            }
            st.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        if (login == true){
            System.out.println("LOGIN ACEITE");
            //COLOCAR MENU VISIBEL
            for (Component cp : jMenuBar1.getComponents())
            cp.setEnabled(true);
            
            //COLOCAR JANELA DE LOGIN ESCONDIDA
            for (Component cp : jPanelRealizaLogin.getComponents())
            cp.setEnabled(false);
            
            //MOSTRAR A JANELA PRINCIPALCOM OS MENUS
            jDialogJanelaPrincipal.setLocationRelativeTo(this);
            jDialogJanelaPrincipal.setVisible(true);
            
            //ESCREVER O NOME DO FUNCIONARIO
            String nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionarioLogin, "NOME");
            //jLabelBoasVindas.setText("Olá, " + nomeFuncionario);
            
            //MOSTRAR PANEL DA JANELA PRINCIPAL
            jPanelPrincipal.setVisible(true);
            
          
            
            
            
        }else{
            System.out.println("LOGIN ERRADO");
            JOptionPane.showMessageDialog(this, "Password Errada ! ");
            jPasswordFieldLogin.setText("");
        }

    }
    
    
    
    /*  COMUM   */
      private void ConsultaControloResultado(int entrada, int equipamento, int insectoca) {
        //DADOS QUE VAMOS LER DA BD

        int idControResultado = 0;
        int idFuncionario = 0;
        int idEntrada = 0;
        
        int idEquipamento = 0;
        String nomeEquipamento = "";
        
        int idInsectoca = 0;
        String data = "";
        String resultado = "NC";
        String fichaTecnica = "";
        String descricao = "";
        
        
        String nomeFuncionario = "";
        String nomeInsectador = "";    
        
        String escreveNaTabelaNome = "" ; // vai escrever na tabela a opcao ke selecionamos

        model = (DefaultTableModel) jTableConsultarControlos.getModel();
       
        CONTA_LINHAS_CONTROLORESULTADOS = 0;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "CONTROLORESULTADOS";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE IDENTRADA=" + entrada + " AND IDEQUIPAMENTO="+equipamento+" AND IDINSECTOCACADORES="+insectoca;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");
                
                idEntrada = rs.getInt("IDENTRADA");
                
                idEquipamento = rs.getInt("IDEQUIPAMENTO");
                nomeEquipamento = selectString("EQUIPAMENTO", "IDEQUIPAMENTO", idEquipamento, "NOME");
                
                idInsectoca = rs.getInt("IDINSECTOCACADORES");
                nomeInsectador = selectString("INSECTOCACADORES", "IDINSECTOCACADORES", idInsectoca, "NOME");

                data = rs.getString("DATA");
                resultado = rs.getString("RESULTADO");
                fichaTecnica = rs.getString("FICHATECNICA");
                descricao = rs.getString("DESCRICAO");

                //VER QUAL É QUE VAMOS ESCREVER NA TABELA
                if(entrada !=0){
                    //escreveNaTabelaNome = idEntrada;
                }else if (equipamento != 0){
                    escreveNaTabelaNome = nomeEquipamento;
                }else if (insectoca != 0){
                    escreveNaTabelaNome = nomeInsectador;
                }
                
                
                model.addRow(new Object[]{nomeFuncionario, escreveNaTabelaNome, data, resultado, fichaTecnica, descricao});
                CONTA_LINHAS_CONTROLORESULTADOS++;
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

    }
    
    
      /*   LER       */
     private void LerBDFuncionario(JComboBox nomeComboBox){
    //LIMPAR A COMBO
        nomeComboBox.removeAllItems();
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
            String sql = "SELECT * FROM " +nomeTabela; 
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
        
        nomeComboBox.addItem("--Funcionario--");
        Iterator<String> it = opcao.iterator();
        while(it.hasNext()){
            nomeComboBox.addItem(it.next());
        }
    
    } 
     
     
          
     /* LER CONTROLO DE RESULTADOS*/ 
       
     private void LerBDControloResultados(String nomeColuna){
         int idFuncionario = 0;
         String nomeFuncionario = "";

         String data = "";
         String resultado = "";
         String fichaTecnica = "";
         String descricao = "";
         String outro = "";

         String nomeSeccao = ""; // ir a tabela buscar o nome da tabela em questao!
         int colunaID = 0;
       

         int numeroRegistos = 0;

         model = (DefaultTableModel) jTableConsultarControlos.getModel();

         //Ligação BD
         try {
             Class.forName("org.apache.derby.jdbc.ClientDriver");
         } catch (ClassNotFoundException e) {
             System.err.print("ClassNotFoundException: ");
             System.err.println(e.getMessage());
             System.out.println("O driver expecificado nao foi encontrado.");
         }

         try {

             con = DriverManager.getConnection(url);
             String nomeTabela = "controloresultados";
             String sql = "select * from " + nomeTabela;
             PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
             ResultSet rs = st.executeQuery();

             while (rs.next()) {
                 idFuncionario = rs.getInt("IDFUNCIONARIO");
                 nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");

                 if (!nomeColuna.equals("OUTROS")) {
                     colunaID = rs.getInt(nomeColuna);
                 }

                 //VERIFICAR QUAL A COLUNA  E IR A TABELA CERTA BUSCAR O NOME
                 if (nomeColuna.equals("IDENTRADA")) {
                     nomeSeccao = selectString("ENTRADA", "IDENTRADA", colunaID, "LOTEORIGEM");

                 } else if (nomeColuna.equals("IDEQUIPAMENTO")) {
                     nomeSeccao = selectString("EQUIPAMENTO", "IDEQUIPAMENTO", colunaID, "NOME");

                 } else if (nomeColuna.equals("IDINSECTOCACADORES")) {
                     nomeSeccao = selectString("INSECTOCACADORES", "IDINSECTOCACADORES", colunaID, "NOME");

                 }

                 outro = rs.getString(nomeColuna);
                 System.out.println("OUTRO: " + outro);

                 data = rs.getString("DATA");
                 resultado = rs.getString("RESULTADO");
                 fichaTecnica = rs.getString("FICHATECNICA");
                 descricao = rs.getString("DESCRICAO");

                 if (!nomeColuna.equals("OUTROS")) {
                     if (colunaID != 0) {
                         model.addRow(new Object[]{nomeFuncionario, nomeSeccao, data, resultado,  descricao});
                         numeroRegistos++;
                     }
                 } else {
                     if (!outro.equals("")) {
                         model.addRow(new Object[]{nomeFuncionario, outro, data, resultado, descricao});
                         numeroRegistos++;
                     }
                 }

             }

             //QUANDO NAO EXISTE NENHUM REGISTO INSERIDO
             if (numeroRegistos == 0) {
                 JOptionPane.showMessageDialog(jDialogConsultarControlos, "Não Existe Nenhum Registo Nesta Secção ! ");
             }
             System.out.println("NOME DA SECÇÃ -> " + nomeSeccao);
             st.close();
             con.close();
         } catch (SQLException ex) {
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
            String sql = "select * from " +nomeTabela+ " WHERE ESTADO ='true'"; 
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
            String sql = "select * from " +nomeTabela+" WHERE ESTADO ='true'"; 
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
    
    private void LerBDEntradas(){
        jComboBoxOpcao.removeAllItems();
        //CARREGAR DADOS
        Set<String> opcao = new HashSet<String>();
        
        //CAMPOS KE TÊM DE SER NC
        String temperatura = "NC";
        String dataValidade = "NC";
        String caratorgonolepticas = "NC";
        String embalagem = "NC";
        
        
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
            String nomeTabela = "ENTRADA";
            String sql = "select * from " +nomeTabela + " WHERE TEMPERATURA='"+temperatura+"' or DATAVALIDADE='"+dataValidade+"' or CARATORGONOLEPTICAS='"+caratorgonolepticas+"' or EMBALAGEM='"+embalagem+"'" ; 
          
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                loteEntradaSeleccionada = rs.getString("LOTEORIGEM");
                opcao.add(loteEntradaSeleccionada);
                System.out.println(" " + loteEntradaSeleccionada);
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
    
      /*   LER   ENTRADAS    */
    private void LerBDFornecedorComboBox(JComboBox ComboBoxFornecedor) {
        //LIMPAR A COMBO
        ComboBoxFornecedor.removeAllItems();
        //CARREGAR DADOS
        Set<String> opcao = new HashSet<String>();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "FORNECEDOR";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE ESTADO=true";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                opcao.add(rs.getString("NOME"));
            }
            st.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        ComboBoxFornecedor.addItem("--Fornecedor--");
        Iterator<String> it = opcao.iterator();
        while (it.hasNext()) {
            ComboBoxFornecedor.addItem(it.next());
        }

    }

    private void LerBDMateriaPrimaComboBox(JComboBox ComboBoxMateriaPrima, String VerEstadoAMostrar) {
       
        ComboBoxMateriaPrima.removeAllItems();
        //CARREGAR DADOS
        Set<String> opcao = new HashSet<String>();

        
        //VERIFICAR QUAL O ESTADO QUE VAMOS MOSTRAR
        boolean estado = false ;
        boolean verDevolucao = false;
        
        int oQueFazer = 0;
        
        
        if(VerEstadoAMostrar.equals("true")){
            estado = true;
            oQueFazer = 2;
        }else if(VerEstadoAMostrar.equals("false")){
            estado = false;
            oQueFazer = 1;
        }else if (VerEstadoAMostrar.equals("devolucao")){
            //verDevolucao = true;
            oQueFazer = 3;
        }
        
        System.out.println("VER DEVOLUCAO: " + verDevolucao+ " || VER ESTADO : " + estado);
        
        String sql = "";
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "MATERIA_PRIMA";
                        
            //TENTAR NOVA FORMA
            if (oQueFazer == 1){
                sql = "SELECT * FROM " + nomeTabela + " WHERE ESTADO=" + estado;
            }else if (oQueFazer == 2){
                sql = "SELECT * FROM " + nomeTabela + " WHERE ESTADO=" + estado;
            }else{
                sql = "SELECT * FROM " + nomeTabela;
            }
                      
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                opcao.add(rs.getString("NOME"));
            }
            st.close();
            con.close();

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        ComboBoxMateriaPrima.addItem("--Materia Prima--");
        Iterator<String> it = opcao.iterator();
        while (it.hasNext()) {
            ComboBoxMateriaPrima.addItem(it.next());
        }
        
    }
       
    
    
    
    /*  VER ENTRADAS */
    
    private void VerEntradaSeleccionadaConsultaDevolucao() {

        int linha = jTableConsultaEntradas.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaEntradas, "Seleccione a Linha de uma Entrada!");
        } else {
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeFuncionario = (String) jTableConsultaEntradas.getValueAt(linha, 1);
            int idFuncionario = selectId("FUNCIONARIO", "NOME", nomeFuncionario, "IDFUNCIONARIO");

            String nomeFornecedor = (String) jTableConsultaEntradas.getValueAt(linha, 2);
            idFornecedorDevolucao = selectId("FORNECEDOR", "NOME", nomeFornecedor, "IDFORNECEDOR");

            String matPrima = (String) jTableConsultaEntradas.getValueAt(linha, 3);
            idMateriaPrimaDevolucao = selectId("MATERIA_PRIMA", "NOME", matPrima, "IDMATERIAPRIMA");

            String dataEntrega = (String) jTableConsultaEntradas.getValueAt(linha, 4);
            String lote = (String) jTableConsultaEntradas.getValueAt(linha, 5);
            int idEntrada = selectId("ENTRADA", "LOTEORIGEM", lote, "IDENTRADA");
            
            String peso = (String) jTableConsultaEntradas.getValueAt(linha, 6);
            String devolucao = (String) jTableConsultaEntradas.getValueAt(linha, 7);
            String devolucaoActualizar = "S";
            
            
         

            //DEVOLVER O ID DA ENTRADA COMPARANDO TODOS OS CAMPOS

           // if (devolucao.equals("Sim")) {

                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) {
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }

                try {

                    con = DriverManager.getConnection(url);
                    String nomeTabela = "ENTRADA";
                   // String sql = "SELECT * FROM " + nomeTabela + " WHERE DEVOLUCAO='" + devolucaoActualizar + "' and IDFORNECEDOR=" + idFornecedorDevolucao + " and IDFUNCIONARIO=" + idFuncionario + " and IDMATERIAPRIMA=" + idMateriaPrimaDevolucao + " and LOTEORIGEM='" + lote + "'";
                    String sql = "SELECT * FROM " + nomeTabela + " WHERE IDENTRADA = " + idEntrada+ " AND DEVOLUCAO='" + devolucaoActualizar+"'";
                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();

                    while (rs.next()) {

                        idEntradaSeleccionada = rs.getInt("IDENTRADA");
                        quantidadeEntradaTotalSelecionada = rs.getFloat("QUANTIDADE");//VALOR FIXO
                        quantidadeADevolverSelecionada = rs.getFloat("ADEVOLVER");//VALOR VARIVEL
                        quantidadeADevolverSelecionadaFixa = rs.getFloat("QUANTIDADETOTALADEVOLVER");//VALOR FIXO
                    }

                    st.close();
                    con.close();
                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }



            //} else {
           //     JOptionPane.showMessageDialog(jDialogConsultaEntradas, "Linha Seleccionada Não Apresenta Devoluções!");
           // }

            System.out.println("BOTAO DEVOLOÇÃO -> JANELA CONSULTA ENTRADAS");
            System.out.println("NOME FUNCIO: " + nomeFuncionario);
            System.out.println("ID FUNCIO: " + idFuncionario);
            System.out.println("NOME FORNECEDOR: " + nomeFornecedor);
            System.out.println("ID FORNECEDOR: " + idFornecedorDevolucao);
            System.out.println("NOME MATERIA PRIM: " + matPrima);
            System.out.println("ID MATERIA PRIMA: " + idMateriaPrimaDevolucao);
            System.out.println("DATA ENTREGA: " + dataEntrega);
            System.out.println("LOTE: " + lote);
            System.out.println("ID ENTRADA: " + idEntrada);
            System.out.println("QUANTIDADE: " + peso);
            System.out.println("ID ENTRADA SELECIONADO : " + idEntradaSeleccionada);
            System.out.println("QUANTIDADE ENTRADA : " + quantidadeEntradaTotalSelecionada);
            System.out.println("QUANTIDADE A DEVOLVER SELECCIONADA : " + quantidadeADevolverSelecionada);
            System.out.println("QUNATIDADE A DEVOLVER FIXA : " + quantidadeADevolverSelecionadaFixa);


        }
    }

    private void VerEntradaSeleccionadaConsultaControloResultados() {

        int linha = jTableConsultaEntradas.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(jDialogConsultaEntradas, "Seleccione a Linha de uma Entrada!");
        } else {
            //DEVOLVE O NOME DO EQUIPAMENTO
            String nomeFuncionario = (String) jTableConsultaEntradas.getValueAt(linha, 0);
            int idFuncionario = selectId("FUNCIONARIO", "NOME", nomeFuncionario, "IDFUNCIONARIO");

            String nomeFornecedor = (String) jTableConsultaEntradas.getValueAt(linha, 1);
            idFornecedorDevolucao = selectId("FORNECEDOR", "NOME", nomeFornecedor, "IDFORNECEDOR");

            String matPrima = (String) jTableConsultaEntradas.getValueAt(linha, 2);
            idMateriaPrimaDevolucao = selectId("MATERIA_PRIMA", "NOME", matPrima, "IDMATERIAPRIMA");

            String dataEntrega = (String) jTableConsultaEntradas.getValueAt(linha, 3);
            String lote = (String) jTableConsultaEntradas.getValueAt(linha, 4);
            String peso = (String) jTableConsultaEntradas.getValueAt(linha, 5);
            String devolucao = (String) jTableConsultaEntradas.getValueAt(linha, 6);


            //DEVOLVER O ID DA ENTRADA COMPARANDO TODOS OS CAMPOS

            if (devolucao.equals("Sim")) {
                devolucao = "S";
            } else if (devolucao.equals("Não")) {
                devolucao = "N";
            }


            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }

            try {

                con = DriverManager.getConnection(url);
                String nomeTabela = "ENTRADA";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE DEVOLUCAO='" + devolucao + "' and IDFORNECEDOR=" + idFornecedorDevolucao + " and IDFUNCIONARIO=" + idFuncionario + " and IDMATERIAPRIMA=" + idMateriaPrimaDevolucao + " and LOTEORIGEM='" + lote + "'";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {

                    idEntradaSeleccionada = rs.getInt("IDENTRADA");
                    quantidadeEntradaTotalSelecionada = rs.getFloat("QUANTIDADE");
                }

                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }





            System.out.println("NOME FUNCIO: " + nomeFuncionario);
            System.out.println("ID FUNCIO: " + idFuncionario);
            System.out.println("NOME FORNECEDOR: " + nomeFornecedor);
            System.out.println("ID FORNECEDOR: " + idFornecedorDevolucao);
            System.out.println("NOME MATERIA PRIM: " + matPrima);
            System.out.println("ID MATERIA PRIMA: " + idMateriaPrimaDevolucao);
            System.out.println("DATA ENTREGA: " + dataEntrega);
            System.out.println("LOTE: " + lote);
            System.out.println("QUANTIDADE: " + peso);
            System.out.println("ID ENTRADA SELECIONADO : " + idEntradaSeleccionada);
            System.out.println("QUANTIDADE ENTRADA : " + quantidadeEntradaTotalSelecionada);


        }
    }
    
    private void verNaoConformidadeDeUmaEntrada(String loteSeleccionado){
        //VARIAVEIS
        String dataValidade = "";
        String temperatura = "";
        String caratOrgonolepticas = "";
        String embalagem = "";
       
        
        //LIGACAO
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }

            try {

                con = DriverManager.getConnection(url);
                String nomeTabela = "ENTRADA";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE LOTEORIGEM='"+loteSeleccionado+"'" ;
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    dataValidade = rs.getString("DATAVALIDADE");
                    temperatura =  rs.getString("TEMPERATURA");
                    caratOrgonolepticas = rs.getString("CARATORGONOLEPTICAS");
                    embalagem = rs.getString("EMBALAGEM");  
                }

                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
    
            
            //MOSTRAR MENSAGEM COM AS NAO CONFORMIDADES
            JOptionPane.showMessageDialog(jDialogConsultarControlos, "Não Conformidades Registadas na Entrada\n\n"
                                                +"Data de Validade  :   " + dataValidade +"\n\n"
                                                +"Temperatura       :   " + temperatura + "\n\n"
                                                +"Caract. Orgonolep.:   " + caratOrgonolepticas+"\n\n"
                                                +"Embal./Transporp. :   " + embalagem);
    }
    
    
   /*  INSERIR INSECTOCAÇADOR*/
    
    private void InserirNovoInsecto(){
        String referencia = jTextFieldReferenciaInsecto.getText();
        String nome = jTextFieldNomeInsecto.getText();
        String local = jTextFieldLocalInsecto.getText();
        boolean estado = true;

        boolean existeInsectocacador = false;
        
        if (referencia.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovoInsectocacador, "Insira a REFERENCIA do Insecto.!");
        } else if (nome.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovoInsectocacador, "Insira o NOME do Insecto.!");
        } else if (local.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovoInsectocacador, "Insira o LOCAL do Insecto.!");
        } else {

            
            //VERIFICAR SE O NOME DO CONTROLO DE PRAGAS JA EXISTE 
            
            //VERIFICAR SE NOME JA EXISTE
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }
            try {

                con = DriverManager.getConnection(url);
                String nomeTabela = "INSECTOCACADORES";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE REFERENCIA='"+referencia+"'";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while(rs.next()){               
                   existeInsectocacador = true;
                }
                st.close();
                con.close();

            } catch (SQLException ex) {

                existeInsectocacador = false;
                System.out.println("ERROR : " + ex);
            }


            System.out.println("EXISTE CONTROLO DE PRAGAS? -> " + existeInsectocacador);

            if (existeInsectocacador == true) {
                JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Controlador de Pragas já Existe !");
            } else {
                //INSERIRR NA BASE DE DADOS 
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) { //driver não encontrado
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }
                try {
                    con = DriverManager.getConnection(url);
                    String nomeTabela = "INSECTOCACADORES";
                    sql = "INSERT INTO " + nomeTabela + " (REFERENCIA,NOME,LOCAL, ESTADO)" + " VALUES(" + "'" + referencia + "'," + "'" + nome + "'," + "'" + local + "',"+ estado + ")";

                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    st.executeUpdate();
                    st.close();
                    con.close();

                    jDialogNovoInsectocacador.setVisible(false);
                    JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "DADOS INSERIDOS COM SUCESSO !");

                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }
            }
        }
        System.out.println("\n*** INSERIR NOVO INSECTOCAÇADOR -> NOVO");
        System.out.println("REFERENCIA INSECTO.: " + referencia);
        System.out.println("NOME: " + nome);
        System.out.println("LOCAL: " + local);

    }
    

    
    
    private void InserirNovaLimpeza(){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        String data = (String) sdf.format(jDateChooserLimpeza.getDate());
       
        //LER DATA DO SISTEMA
        Date dataAtual = new Date();    
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");  
        String data = formato.format(dataAtual);
        
        
        int idInsectocacador = idInsectocacadorControloResultado;
       // int idFuncionarioResponsavel = 0;
        //INSERIR OS DADOS NA TABELA
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }
            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "LIMPEZAINSECTOCACADORES";
                sql = "INSERT INTO " + nomeTabela + "(IDINSECTOCACADORES,IDFUNCIONARIO,DATA)" + " values("+ idInsectocacador + "," +idFuncionarioLogin + "," + "'" + data + "'"  + ")";

                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        
            //FECHAR JANELA DE NOVA LIMPEZA
            jDialogNovaLimpeza.setVisible(false);
            
            System.out.println("\n*** INSERIR NOVA LIMPEZA -> JANELA NOVA LIMPEZA, VINDO DA CONSULTA DE INSECTOCAÇADORES");
            System.out.println("ID EQUIPAMENTO SELECIONADO: " + idInsectocacador);
            System.out.println("FUNCIOANRIO RESPONSAVEL: " + idFuncionarioLogin);
            System.out.println("DATA: " + data);    
        
    }

    /* INSERT EQUIIUPAMENTOS  */ 
    private void InserirNovoEquipamento(){
        //DAODS A GRAVAR 
        String nomeEquipamento = jTextFieldNomeEquipamento.getText();
        String observacaoEquipamento = jTextAreaObservacaoNovoEquipamento.getText();
        boolean estado = true;
        boolean existeEquipamento = false;

        // VERIFICAR SE NOME JA EXISTE
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "EQUIPAMENTO";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE NOME='" + nomeEquipamento + "'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("\n\nNOME DO EQUIPAMENTO-> JA EXISTE");
                existeEquipamento = true;
            }

            st.close();
            con.close();

        } catch (SQLException ex) {
            existeEquipamento = false;
            System.out.println("ERROR : " + ex);

        }//FECHA CATCH
        
        
        //VER QUAL A ACÇÃO QUE VAI SER REALIZADA
        if (existeEquipamento == true) {
            JOptionPane.showMessageDialog(jDialogNovoEquipamento, "EQUIPAMENTO JA EXISTE !");
        } else {

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }
            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "EQUIPAMENTO";
                sql = "INSERT INTO " + nomeTabela + "(NOME,DESCRICAO,ESTADO)" + " values(" + "'" + nomeEquipamento + "','" + observacaoEquipamento + "'," + estado + ")";

                JOptionPane.showMessageDialog(jDialogNovoEquipamento, "Equipamento Gravado Com Sucesso !");

                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();
                st.close();
                con.close();
            } catch (SQLException ex2) {
                System.err.println("SQLException: " + ex2.getMessage());
            }
        }
        System.out.println("\n*** INSERIR NOVO EQUIPAMENTO");
        System.out.println("ESTADO -> " + existeEquipamento);
        System.out.println("NOME: " + nomeEquipamento);
        System.out.println("OBSERVAÇÃO: " + observacaoEquipamento);

    }
    
    private void InserirManuencaoEquipamento(){
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        //String data = (String) sdf.format(jDateChooserDataManutencaoEquipamento.getDate());
         
        //LER DATA DO SISTEMA
        Date dataAtual = new Date();    
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");  
        String data = formato.format(dataAtual);


        //DADOS A INSERIR 
        int idEquip = idEquipamentoControloResultado;       
        String observacoes = jTextAreaObservacaoManutencaoEquipameto.getText();
        String fichaTecnica = "";
        
        
        //INSERIR OS DADOS NA TABELA
        
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }
            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "MANUTENCAOEQUIPAMENTOS";
                sql = "INSERT INTO " + nomeTabela + "(IDEQUIPAMENTO,IDFUNCIONARIO,DATA,OBSERVACOES,FICHATECNICA)" + " values("+ idEquip + "," +idFuncionarioLogin + "," + "'" + data + "'" + "," + "'" + observacoes + "'"+"," + "'" + fichaTecnica+ "'"  + ")";

                
              
                
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        
            System.out.println("\n*** INSERIR NOVA MANUTENÇÃO -> VINDA DA CONSULTA DOS EQUIPAMETNO");
            System.out.println("ID EQUIPAMENTO SELECIONADO: " + idEquip);
            System.out.println("FUNCIOANRIO RESPONSAVEL: " + idFuncionarioLogin);
            System.out.println("DATA: " + data);
            System.out.println("OBSERVAÇÕES: " + observacoes);
        
    }
    
    
   /*  INSERIR CONTROLO DE RESULTADOS    */
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
            GravarControloResultados();
            if (!gravarNaBDNovoControloResultado == false) {

                int resultNCNaoConformidades = JOptionPane.showConfirmDialog(jDialogNovoControloResultados, "Deseja Abrir a Janela de Não Conformidades ?", null, JOptionPane.YES_NO_OPTION);

                if (resultNCNaoConformidades == JOptionPane.YES_OPTION) {
                   
                    jDialogNaoConformidades.setLocationRelativeTo(this);
                    jDialogNaoConformidades.setVisible(true);
                    jDialogNovoControloResultados.setVisible(false);
                    LerBDFuncionarioResponsavel();
                    /* DADOS A PASSAR PARA A PROXIMA JANELA */
                    //ESCREVEMOS A SECCAO NA JTEXTFIEL
                    jTextFieldVerSeccaoDoCR.setText(jComboBoxTipo.getSelectedItem().toString());
                    //ESCREVER OS DADOS NA JTEXTAREA DO PROBLEMA
                    jTextAreaProblemadoControlo.setText(jTextAreaDescricao.getText());
                    //ESCREVER O NOME DO FUNCIONARIO NA LABEL
                    jLabelFuncionarioLogin4.setText(NomeFuncionarioLogin);
                    
                }else{
                    jDialogNovoControloResultados.setVisible(false);
                    LimpaCamposNovoControloResultado();
                }
            }
        }
        
    }
    
    private void GravarControloResultados(){
    
        //CAMPOS DA BASE DE DADOS
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
//        String data = (String) sdf.format(jDateChooserControloResultados.getDate());
        
        //LER DATA DO SISTEMA
        Date dataAtual = new Date();    
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        String data = formato.format(dataAtual);
        
        
        //4  id's
        int idOpcaoEscolhida = 0;
        //int idFuncionario = 0;
        int idEntrada = 0;
        int idEquipamento = 0;
        int idInsectocacadores = 0;
        
        String fichaTecnica = "";
        
        
        /* RSULTADO É C OU NC */
        String resultado = jComboBoxResultado.getSelectedItem().toString();
        String outro = jTextFieldOutraOpcao.getText();
        String descricao = jTextAreaDescricao.getText();
        
        //VERIFICAR EM QUAL COLUNA VAMOS INSERIR A OPÇAO
        
        /* FAZER A DIFERENÇA ENTRE ENTRADA  E (EQUIPAMENTO / INSECTOCAÇADOR) */
        
        String comboOpcao = jComboBoxOpcao.getSelectedItem().toString();
        if(comboTipoOP.equals("CONT. PRAGAS") ||comboTipoOP.equals("EQUIPAMENTO") ){
        
            //COMBO BOX OPÇÃO -> aki vamos buscar o ID a tabela Insectocaçadore e Equipamento
            if(comboTipoOP.equals("CONT. PRAGAS"))  
                comboTipoOP = "INSECTOCACADORES";
                
            if (!comboOpcao.equals("--Opção--")) {
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) { //driver não encontrado
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }
                try {
                    con = DriverManager.getConnection(url);
                    String nomeTabela = comboTipoOP; // nome da tabela
                    String sql = "select * from " + nomeTabela + " where NOME='" + comboOpcao + "'";
                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();

                    while (rs.next()) {
                        idOpcaoEscolhida = rs.getInt("ID" + comboTipoOP); // ID + COMBOTIPO (nome da tabelas = ID + nome)
                    }
                    st.close();
                    con.close();
                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }
            }
            

            if (comboTipoOP.equals("INSECTOCACADORES")) {
                idInsectocacadores = idOpcaoEscolhida;
                idEquipamento = 0;
                idEntrada = 0;
            } else if (comboTipoOP.equals("EQUIPAMENTO")) {
                idInsectocacadores = 0;
                idEquipamento = idOpcaoEscolhida;
                idEntrada = 0;
            }
            
            
        }else if (comboTipoOP.equals("ENTRADA")){
            //VAMOS PROCURAR NA TABELA ENTRADAS
           
            if (!comboOpcao.equals("--Opção--")) {
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) { //driver não encontrado
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }
                try {
                    con = DriverManager.getConnection(url);
                    String nomeTabela = comboTipoOP; // nome da tabela
                    String sql = "select * from " + nomeTabela + " where LOTEORIGEM='" + comboOpcao + "'";
                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();

                    while (rs.next()) {
                        idOpcaoEscolhida = rs.getInt("ID" + comboTipoOP); // ID + COMBOTIPO (nome da tabelas = ID + nome)
                    }
                    st.close();
                    con.close();
                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }
                
                
                idInsectocacadores = 0;
                idEquipamento = 0;
                idEntrada = idOpcaoEscolhida;
            }
  
        }else{
            //ker dizer ke tenho a opçao OUTRA
            idInsectocacadores = 0;
            idEquipamento = 0;
            idEntrada = 0;
            //outro = "sim";
        }

     
        //VERIFICAR SE JA EXISTE O CONTROLO COOM ALGUMA NC 
        gravarNaBDNovoControloResultado = true;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "CONTROLORESULTADOS"; // nome da tabela
            String sql = "select * from " + nomeTabela + " WHERE IDENTRADA=" + idEntrada + " AND IDEQUIPAMENTO="+idEquipamento+" AND IDINSECTOCACADORES="+idInsectocacadores+" AND RESULTADO='NC'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                gravarNaBDNovoControloResultado = false;
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        
        
        
        
        if (gravarNaBDNovoControloResultado == false){
            //MENSAGEM DE ERRO A DIZER KE JA EXISTE
            JOptionPane.showMessageDialog(jDialogNovoControloResultados, "Ja existe um controlo de resultado em esta Não Conforme !");
        } else {
            //GRAVAR NA BD CONTROLO RESULTADO
            gravarNaBDNovoControloResultado= true;
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
                sql = "insert into " + nomeTabela + "(IDFUNCIONARIO,IDEQUIPAMENTO,IDINSECTOCACADORES, IDENTRADA, DATA,RESULTADO,FICHATECNICA,DESCRICAO,OUTROS)"
                        + " values (" + idFuncionarioLogin + "," + idEquipamento + "," + idInsectocacadores + "," + idEntrada + "," + "'" + data + "'" + "," + "'" + resultado + "'" + "," + "'" + fichaTecnica + "'" + "," + "'" + descricao + "'" + "," + "'" + outro + "'" + ")";

                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();
                System.out.println("chegou");
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
        }
         // ESCREVER CAMPOS NA CONSOLA
            System.out.println("\n\n***** INSERÇÃO DE DADOS NA TABELA CONTROLO DE RESULTADOS");
            System.out.println("ID OPCAO ESCOLHIDA: " + idOpcaoEscolhida);
            System.out.println("FUNCIONARIO: " + idFuncionarioLogin); 
           // System.out.println("LOTE: " + comboLote);
            System.out.println("ENTRADA: " + idEntrada);
            System.out.println("EQUIPAMENTO: " + idEquipamento); 
            System.out.println("INSECTICADO: " + idInsectocacadores);
            System.out.println("DATA: " + data);
            System.out.println("RESULTADO: " + resultado);
            System.out.println("FICHA TEC: " + fichaTecnica); 
            System.out.println("DESCRICAO: " + descricao);
            System.out.println("OUTRO: " + outro ); 
            System.out.println("\n\n combo opcao : " +comboOpcao);
          
    }
       
    private void GravarNaoConformidade(){
        //GRAVAR NOVO NAO CONFORMIDADE
        
        //DADOS JANELA NAO CONFORMIDADE
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
//        String data = (String) sdf.format(jDateChooserNaoConformidade.getDate());

        Date dataAtual = new Date();    
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        String data = formato.format(dataAtual);
        
        String ocorrecia = jTextAreaOcorrenciaNaoConformidade.getText();
        String medidaCorrectiva = jTextAreaMedidaCorrctivaNaoConformidade.getText();
        String funcionarioRegistaNome =  NomeFuncionarioLogin;
        int funcionarioRegistaID = 0;
        String funcionarioResponsavelNome = jComboBoxNaoConformidadeFuncionarioResponsav.getSelectedItem().toString();
        int funcionarioResponssavelID = 0;
        String correctiva = "N";
   
            funcionarioRegistaID = idFuncionarioLogin;
        
        if (!funcionarioResponsavelNome.equals("--Funcionario--"))
            funcionarioResponssavelID = selectId("FUNCIONARIO", "NOME", funcionarioResponsavelNome, "IDFUNCIONARIO");
        
        
        //GRAVAR BD NAO CONFORMIDADE
        
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
            sql = "INSERT INTO " + nomeTabela + "(IDCONTRESULTADOS,IDFUNCIONARIO, DATANAOCONFORMIDADE, OCORRENCIA, MEDIDACORRECTIVA, IDFUNCIONARIOMEDIDACORRECTIVA, CORRECTIVA)"
                    + " values ("+idControloResultadosParaPesquisarNConformidade + "," +funcionarioRegistaID+ "," + "'" +data+ "'" + "," + "'"+ocorrecia+ "'" + "," + "'" + medidaCorrectiva+"'" + "," +funcionarioResponssavelID + "," + "'" + correctiva + "'"  +")";

            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();
          
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        LimpaTabelaNaoConformidades();
        ConsultaBDNaoConformidadeSelect();
        LimpaCamposNovaNaoConformidade();
        
        
        System.out.println("*** INSERIR NOVO CONTROLO");
        System.out.println("FUNCIONARIO QUE REGISTA : " + funcionarioRegistaNome);
        System.out.println("ID : " +funcionarioRegistaID);
        System.out.println("FUNCIONARIO RESPONSAVEL : " + funcionarioResponsavelNome);
        System.out.println("ID: " + funcionarioResponssavelID);
        System.out.println("ID CONT. RESULTADO  SELECCIONADO : " + idControloResultadosParaPesquisarNConformidade);
        
       
    }
    
    
    /* INSERIR  ENTRADAS*/
    private void InserirNovaMateriaPrima() {

        //DAODS A GRAVAR 
        String nomeMateria = jTextFieldNomeMateriaPrima.getText();
        String descricaoMateria = jTextAreaDescricaoMateriaPrima.getText();
        String unidadeMateria = jTextFieldUnidadeMateriaPrima.getText();
        
        boolean existeMateriaPrima = false;
        
        //VERIFICAR SE NOME JA EXISTE
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "MATERIA_PRIMA";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE NOME='"+nomeMateria+"'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){               
               existeMateriaPrima = true;
            }
            st.close();
            con.close();

        } catch (SQLException ex) {
            
            existeMateriaPrima = false;
            System.out.println("ERROR : " + ex);
        }

        
        System.out.println("EXISTE MATERIA-PRIMA -> " +existeMateriaPrima);
        
        if(existeMateriaPrima == true){
             JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Matéria-Prima ja Existe ! !");
        } else if (existeMateriaPrima == false){

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }
            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "MATERIA_PRIMA";
                sql = "INSERT INTO " + nomeTabela + "(NOME,DESCRICAO,UNIDADE)" + " values(" + "'" + nomeMateria + "'" + "," + "'" + descricaoMateria + "'" + "," + "'" + unidadeMateria + "'" + ")";

                JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Nova Materia Gravada Com Sucesso !");

                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();
                st.close();
                con.close();
            } catch (SQLException ex2) {
                System.err.println("SQLException: " + ex2.getMessage());
            }

        }
        System.out.println("\n*** INSERIR NOVA MATERIA PRIMA");        
        System.out.println("NOME: " + nomeMateria);
        System.out.println("OBSERVAÇÃO: " + descricaoMateria);
        System.out.println("UNIDADE: " + unidadeMateria);


    }

    private void InserirNovoFornecedor() {
        String nome = jTextFieldNomeFornecedor.getText();
        String morada = jTextFieldMoradaFornecedor.getText();
        String codPostal = jTextFieldCodPostalFornecedor.getText();
        String localidade = jTextFieldLocalidadeFornecedor.getText();
        String contacto = jTextFieldContactoFornecedor.getText();
        String email = jTextFieldEmailFornecedor.getText();
        String nif = jTextFieldNIFFornecedor.getText();
        String tipoProduto = jTextFieldTipoProdutoFornecedor.getText();     
        
        boolean existeFornecedor =  false;
        
        //VERIFICAR SE NOME JA EXISTE
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }
        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "FORNECEDOR";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE NOME='" + nome + "' OR NIF=" + nif;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                existeFornecedor = true;
                
            }
            st.close();
            con.close();

        } catch (SQLException ex) {
            existeFornecedor = false;
            System.out.println("\n\nNOME DO FORNECEDOR -> NAO EXISTE");

        }//FEECHAR CATCH

        
        System.out.println("EXISTE FORNECEDOR -> " + existeFornecedor);
        
        
        if (existeFornecedor == true) {
            JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Fornecedor já Existe!");
        } else {

            //VALIDAR NIF
            if (!validarNif(nif)) {
                System.out.println("Invalido");
                JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "NIF INVALIDO!");
            } else {

                System.out.println("Valido");

                //VERIFICAR O ESTADO DO FORNECEDOR
                String estadoFornecedor = jComboBoxEstadoFornecedor.getSelectedItem().toString();
                boolean estado;

                if (estadoFornecedor.equals("Ativo")) {
                    estado = true;
                } else {
                    estado = false;
                }

                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) { //driver não encontrado
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }

                try {
                    con = DriverManager.getConnection(url);
                    String nomeTabela = "FORNECEDOR";
                    sql = "INSERT INTO " + nomeTabela + "(NOME, MORADA, COD_POSTAL, LOCALIDADE, CONTATO, EMAIL, NIF, TIPOPRODUTO, ESTADO)"
                            + " values(" + "'" + nome + "','" + morada + "','" + codPostal + "','" + localidade + "'," + contacto + ",'" + email + "'," + nif + ",'" + tipoProduto + "'," + estado + ")";

                    JOptionPane.showMessageDialog(jDialogNovaMateriaPrima, "Nova Fornecedor Adicionado com Sucesso !");

                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    st.executeUpdate();
                    st.close();
                    con.close();
                } catch (SQLException ex2) {
                    System.err.println("SQLException: " + ex2.getMessage());
                }

                jDialogNovoFornecedor.setVisible(false);
                LimpaNovoFornecedor();

                System.out.println("\n*** INSERIR NOVO FORNECEDOR");
                System.out.println("NOME: " + nome);
                System.out.println("MORADA: " + morada);
                System.out.println("CODPOSTAL: " + codPostal);
                System.out.println("LOCALIDADE: " + localidade);
                System.out.println("CONTACTO: " + contacto);
                System.out.println("EMAIL: " + email);
                System.out.println("NIF: " + nif);
                System.out.println("TIPOPRODUTO: " + tipoProduto);
                System.out.println("ESTADO: " + estado);

            }
        }//fecha if
    }

    private void InserirNovaEntrada() {

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
//        String dataEntrega = (String) sdf.format(jDateChooserEntrada.getDate());
        
        //LER DATA DO SISTEMA
        Date dataAtual = new Date();    
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        String dataEntrega = formato.format(dataAtual);
        
        
        String nomeFornecedor = jComboBoxFornecedorEntrada.getSelectedItem().toString();
        int idForncedor = selectId("FORNECEDOR", "NOME", nomeFornecedor, "IDFORNECEDOR");

        String nomeMateriaPrima = jComboBoxMateriaPrimaEntrada.getSelectedItem().toString();
        int idMateriaPrima = selectId("MATERIA_PRIMA", "NOME", nomeMateriaPrima, "IDMATERIAPRIMA");

       

        String lote = jTextFieldLoteEntrada.getText();
        float quantidade = Float.parseFloat(jTextFieldQuantidadeEntrada.getText().toString());
        String temperatura = jComboBoxTemperaturaEntrada.getSelectedItem().toString();
        String datavalidade = jComboBoxDataValidadeEntrada.getSelectedItem().toString();
        String caratOrgonolepricas = jComboBoxCaraOrgonolepticasEntradas.getSelectedItem().toString();
        String embalagem = jComboBoxEmbalagemEntradas.getSelectedItem().toString();
        String devolucao = "";
        float adevolver = 0.0f;
        
        devolucaoNumero = 0; //-- POR ESTE CAMINHO, ESTA VAI SER SEMPRE A PRIMEIRA DEVOLUÇÃO
       
        boolean gravarEntrada = false;
        
        boolean mostraJanela = false;
        
            
        //VRIFICAR O ESTADO DA DEVOLUÇÃO -> SIM OU NAO 
        if (temperatura.equals("NC") || datavalidade.equals("NC") || caratOrgonolepricas.equals("NC") || embalagem.equals("NC")) {
            devolucao = "S";
            jButtonGuardarNovaEntrada.setText("Guardar");
        } else {
            devolucao = "N";
            jButtonGuardarNovaEntrada.setText("Guardar");
        }


        if (devolucao.equals("N")) {
            //NAO VAI HAVER DEVOLUCAO
            
            int resultNaoConformidade = JOptionPane.showConfirmDialog(jDialogNaoConformidades, "Não Existem Não Conformidades !\n Deseja Guardar a Entrada?", null, JOptionPane.YES_NO_OPTION);
            if (resultNaoConformidade == JOptionPane.YES_OPTION) {
                jDialogNovaEntrada.setVisible(false);
                adevolver = 0.0f;
                gravarEntrada = true;
            }
 
        } else {
            
            //MOSTRAR AVISO A DIZER PARA PREENCHER A QUANTIDADE KE TEM DE SER DEVOLVIDA!
          
            for (Component cp : jPanelADevolverNovaEntrada.getComponents()) 
            cp.setEnabled(true);
            
            //QUANDO DESBLOQUEIO O PAINEL COLOCAR A UNIDADE IGUAL A UNIDADE DE ENTRADA
            jTextFieldUnidadeQuantidadeADevolver.setText( jTextFieldNovaEntradaUnidadeQuantidade.getText());
            
            
            for (Component cp : jPanelConformidadesNovaEntrada.getComponents()) 
            cp.setEnabled(false);
            
            for (Component cp : jPanelNovaEntrada.getComponents()) 
            cp.setEnabled(false);
            
            jButtonDesvloquearPanel.setVisible(true);
            
             adevolver = Float.parseFloat(jTextFieldQuantidadeADevolver.getText().toString());
             System.out.println("A DEVOLVER AGORA -> " + adevolver);
             
            if (adevolver > quantidade || adevolver == 0.0f) {

                if (mostraJanela == true) {
                    JOptionPane.showMessageDialog(jDialogNovaEntrada, "Quantidade A Devolver Maior que a Quantidade\n OU \nQuantidade a Devolver Igual a Zero !");
                }
                
                mostraJanela = true;
            } else {
                gravarEntrada = true;
                
                //ACTUALIZAMOS O VALOR DA QUANTIDADE PARA A QUANTIDADE REAL
                float quantidadeDesactualizada = quantidade;
                quantidade = quantidade - adevolver;
                JOptionPane.showMessageDialog(jDialogNovaEntrada, "Quantidade disponibel de Materia Prima!\n\n"
                                                        + "QUANTIDADE : " + quantidadeDesactualizada + "\n"
                                                        + "A DEVOLVER : " + adevolver + "\n"
                                                        + "DISPONIVEL : " + quantidade);
            }

        }
        
       
        
        if (gravarEntrada == true) {

            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }

            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "ENTRADA";
                sql = "INSERT INTO " + nomeTabela + "(IDFORNECEDOR, IDFUNCIONARIO, IDMATERIAPRIMA, DATAENTREGA, LOTEORIGEM, QUANTIDADE, TEMPERATURA, DATAVALIDADE, CARATORGONOLEPTICAS, EMBALAGEM, DEVOLUCAO, ADEVOLVER, QUANTIDADETOTALDISPONIVEL, QUANTIDADETOTALADEVOLVER) "
                        + " values(" + idForncedor + "," + idFuncionarioLogin + "," + idMateriaPrima + ",'" + dataEntrega + "','" + lote + "'," + quantidade + ",'" + temperatura + "','" + datavalidade + "','" + caratOrgonolepricas + "','" + embalagem + "','" + devolucao + "'," + adevolver+ ","+ quantidade + ","+ adevolver + " )";

      
                System.out.println("\n\n** DADOS DA NOVA ENTRADA INSERIDOS COM SUCESSO !");

                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
            
          
            LimpaNovaEntrada();
            
              //COLOCAR BOTAO A PERGUNTAR SE QUEREMOS REALIZAR A DEVOLUÇÃO OU NAO
                
            if (devolucao.equals("S")) {
                
             int resultNaoConformidade = JOptionPane.showConfirmDialog(jDialogNovaEntrada, "Deseja Fazer a Devolução ?", null, JOptionPane.YES_NO_OPTION);
             if (resultNaoConformidade == JOptionPane.YES_OPTION){
           // FAZER PESQUISA PARA SABERMOS QUAL O IDENTRADA DA ENTRADA QUE 
           //ACABAMOS DE INSERIR VERIFCAMOS SE ESTAMOS A INSERIR NA BD
                 
                 jDialogNovaEntrada.setVisible(false);
                 
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) {
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }

                try {
                    idFornecedorDevolucao = idForncedor;
                    idMateriaPrimaDevolucao = idMateriaPrima;
                    quantidadeADevolverSelecionada = adevolver;
                    
                    con = DriverManager.getConnection(url);
                    String nomeTabela = "ENTRADA";
                    String sql = "SELECT * FROM " + nomeTabela + " WHERE IDFUNCIONARIO=" + idFuncionarioLogin
                            + " AND IDFORNECEDOR=" + idFornecedorDevolucao
                            + " AND IDMATERIAPRIMA=" + idMateriaPrimaDevolucao
                            + " AND LOTEORIGEM='" + lote + "'"
                            + " AND QUANTIDADE=" + quantidade
                            + " AND DEVOLUCAO='" + devolucao + "'";
                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    ResultSet rs = st.executeQuery();

                    while (rs.next()) {

                        idEntradaSeleccionada = rs.getInt("IDENTRADA");

                    }
                    //DEVOLUCAO NUMERO VAI SER SEMPRE 0, POR ESTE PERCURSO, POIS É A PRIMEIRA VEZ
                    //QUE ESTAMOS A FAZER A DEVOLUÇÃO
                    
                    devolucaoNumero = 0;
                    
                    
                    System.out.println("DADAOS PARA DEVOLUÇAO -> JANELA NOVA ENTRADAS");
                    System.out.println("ID ENTRADA -> " + idEntradaSeleccionada);
                    System.out.println("ID FORNECEDOR -> " + idFornecedorDevolucao);
                    System.out.println("ID MATERIA PRIMA -> " + idMateriaPrimaDevolucao);
                    System.out.println("QUANTIDADE A DEVOLVER -> " + quantidadeADevolverSelecionada);
                    System.out.println("DEVOLUÇAO NUMERO -> " + devolucaoNumero);
                    
                    //ABRIR JANELA DE DEVOLUÇOES
                    LimpaNovaDevolucao();
                    jDialogDevolucoes.setLocationRelativeTo(this);
                    jDialogDevolucoes.setVisible(true);
                    jLabelFuncionarioLogin6.setText("" + NomeFuncionarioLogin);
                    CalculaQuantoFaltaDevolver();                   
                    //jTextFieldQuantidadeADevolverDevolucao.setText(""+adevolver);
                    st.close();
                    con.close();
                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }
                
              
                
                }else {
                   jDialogNovaEntrada.setVisible(false);
                }
             
              }
             
            }//FECHA O IF GRAVA ENTRADA
              

        System.out.println("\n\n**BOTAO GUARDAR NOVA ENTRADA**");

        System.out.println("NOME FUNCIONARIO: " + NomeFuncionarioLogin);
        System.out.println("ID FUNCIONARIO : " + idFuncionarioLogin);

        System.out.println("NOME FORNECEDOR : " + nomeFornecedor);
        System.out.println("ID FORNECEDOR : " + idForncedor);

        System.out.println("MATERIA PRIMA : " + nomeMateriaPrima);
        System.out.println("ID PRIMA : " + idMateriaPrima);

        System.out.println("DTA ENTREGA : " + dataEntrega);
        System.out.println("LOTE : " + lote);
        System.out.println("QUANTIDADE : " + quantidade);
        System.out.println("TEMPERATURA : " + temperatura);
        System.out.println("DATA VALIDADE : " + datavalidade);
        System.out.println("CARAT. ORGONOLEPRICAS : " + caratOrgonolepricas);
        System.out.println("EMBALAGEM/TRANSPORTE : " + embalagem);
        System.out.println("VAI HAVER DEVOLUÇÃO : " + devolucao);
        System.out.println("QUANTIDADE A DEVOLVER : " + adevolver);
        

    }

    private void InserirDevolucao() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                

        String dataDevolucao = (String) sdf.format(jDateChooserDataDevolucao.getDate());
        float quantidadeQueEstamosAdevolver = Float.parseFloat(jTextFieldQuantidadeDevolucao.getText().toString());
        String observacao = jTextAreaObservacoesDevolucao.getText();
       // int idFuncionarioRresponsavel = 0;
        float quantidadeActualizar = 0.0f;
        String devolucao = "N";

        float quantidadeDevolvidadaAteAgora = 0.0f; //para ir buscar o valor a coluna e somar a de baixo
        float quantidadeDevolvidadaAteAgora_TOTAL = 0.0f; //somatorio das quantidades das devoluçoes ja efectuadas
        float quantidadeDevolvidadaAteAgora_FINAL = 0.0f; // esta e a soma das 2 -> _total + quantidadeQueEstamosAdevolver
        float quantidadeDevolvidadaAteAgora_Arrendondar = 0.0f;
        
        //COMBOBOX
        //FUNCIONARIO RESPONSAVEL PELA DEVOLUCAO

         if (quantidadeQueEstamosAdevolver == 0.0) {

            //PERCORRER A BD E VER A QUANTIDADE QUE JA FOI DEVOLVIDA
            JOptionPane.showMessageDialog(jDialogDevolucoes, "Quantidade a Devolver não pode ser ZERO !");

        } else {
            
            //BD DE DEVOLUÇÕES
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }

            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "DEVOLUCOES";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE IDENTRADA=" + idEntradaSeleccionada;
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    quantidadeDevolvidadaAteAgora = rs.getFloat("QUANTIDADEDEVOLVIDA");
                    quantidadeDevolvidadaAteAgora_TOTAL = quantidadeDevolvidadaAteAgora_TOTAL + quantidadeDevolvidadaAteAgora;

                    System.out.println("\nquantidadeDevolvidaAteAgora : " + quantidadeDevolvidadaAteAgora);

                }
                System.out.println("\nquantidadeDevolvidaAteAgora_TOTAL : " + quantidadeDevolvidadaAteAgora_TOTAL);
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }

            //APAGAR
            mostraQuantidadeDevolvidaAteAoMomento = quantidadeDevolvidadaAteAgora_TOTAL;

            //VERIFICAR SE A QUANTIDADE JA DEVOLVIDA + A QUE VAMOS DEVOLVER ESTAO DENTRO DOS LIMITES
            //quantidadeDevolvidadaAteAgora_FINAL = quantidadeDevolvidadaAteAgora_TOTAL + quantidadeQueEstamosAdevolver;
            
            quantidadeDevolvidadaAteAgora_Arrendondar = quantidadeADevolverSelecionada - quantidadeQueEstamosAdevolver;
            quantidadeDevolvidadaAteAgora_FINAL = Round(quantidadeDevolvidadaAteAgora_Arrendondar, 2);
            
            System.out.println("CONTAS PARA VERIFICAR SE ESTAMOS OU NAO A INSERIR A MAIS\n-> " + quantidadeADevolverSelecionada + " + " + quantidadeQueEstamosAdevolver + " = " + quantidadeDevolvidadaAteAgora_FINAL);

            
            
            
            
            //QUANTIDADE MAIOR
            if (quantidadeDevolvidadaAteAgora_FINAL > quantidadeADevolverSelecionada || quantidadeDevolvidadaAteAgora_FINAL < 0) {

                JOptionPane.showMessageDialog(jDialogDevolucoes, "Quantidade a devolvel nao pode ser tanta\n\n"
                        + "FALTA Devolver Apenas : " + (quantidadeADevolverSelecionada ));

            } else if (quantidadeDevolvidadaAteAgora_FINAL == 0) {
                //QANTIDADE A DEVELVER = A DEVOLVIDA
                
                //inserir na mesma o registo mas actualizar o estado na tabela entradas
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) { //driver não encontrado
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }

                try {
                    con = DriverManager.getConnection(url);
                    String nomeTabela = "DEVOLUCOES";
                    sql = "INSERT INTO " + nomeTabela + "(IDFUNCIONARIO, IDFORNECEDOR, IDENTRADA, IDMATERIAPRIMA, DATADEVOLUCAO, QUANTIDADEDEVOLVIDA, OBSERVACAO)"
                            + " values(" + idFuncionarioLogin + "," + idFornecedorDevolucao + "," + idEntradaSeleccionada + "," + idMateriaPrimaDevolucao + ",'" + dataDevolucao + "'," + quantidadeQueEstamosAdevolver + ",'" + observacao + "'" + ")";

                    System.out.println("\n\n** DADOS DA NOVA DEVOLUÇÃO INSERIDOS COM SUCESSO !");

                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    st.executeUpdate();
                    st.close();

                    //VAMOS TER DE ACTUALIZAR O ESTADO DA DEVOLUÇÃO DE "SIM" PARA "NAO"
                    //VAMOS TER DE ACTUALUZAR O VALOR DE ADEVOLVER PARA 0
                    double aDevolter = 0.0;
                  
                    
                    String nomeTabela2 = "ENTRADA";
                    String sql2 = "UPDATE " + nomeTabela2 + " SET DEVOLUCAO='" + devolucao + "', ADEVOLVER="+ aDevolter +" WHERE IDENTRADA=" + idEntradaSeleccionada;
                    PreparedStatement st2 = (PreparedStatement) con.prepareStatement(sql2);
                    st2.executeUpdate();
                    st2.close();

                    JOptionPane.showMessageDialog(jDialogDevolucoes, "Estado da Devolução Actualizado com Sucesso.\nAgora : Devolvido");

                    jDialogDevolucoes.setVisible(false);
                    LimpaNovaDevolucao();
                    LimpaTabelaEntradas();
                    ConsultaEntradas();
                    //ALTERAR TEXTO
                    jButtonVerDevolucoes.setText("Fazer Devolição");
                    jLabelTituloConsultaEntradas.setText("Consulta Todas as Entradas");

                    con.close();
                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }

            } else {

                //VER QUAL O NUMERO DA DEVOLUÇÃO QUE VAI TER -> DEVOLUCAONUMERO
                devolucaoNumero = devolucaoNumero + 1;

                //INSERIR O NOVO VALOR NA TABELA DEVOLUÇÕES
                try {
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                } catch (ClassNotFoundException e) { //driver não encontrado
                    System.err.print("ClassNotFoundException: ");
                    System.err.println(e.getMessage());
                    System.out.println("O driver expecificado nao foi encontrado.");
                }
                
                try {
                    con = DriverManager.getConnection(url);
                    String nomeTabela = "DEVOLUCOES";
                    sql = "INSERT INTO " + nomeTabela + "(IDFUNCIONARIO, IDFORNECEDOR, IDENTRADA, IDMATERIAPRIMA, DATADEVOLUCAO, QUANTIDADEDEVOLVIDA, OBSERVACAO, DEVOLUCAONUMERO)"
                            + " values(" + idFuncionarioLogin + "," + idFornecedorDevolucao + "," + idEntradaSeleccionada + "," + idMateriaPrimaDevolucao + ",'" + dataDevolucao + "'," + quantidadeQueEstamosAdevolver + ",'" + observacao + "'," + devolucaoNumero + ")";

                    System.out.println("\n\n** DADOS DA NOVA DEVOLUÇÃO INSERIDOS COM SUCESSO !");

                        //ACTUALIZAR A TABELA ENTRADAS VALOR ADEVOLVER
                        //FAZER A CONTA PARA ACTUALIZAR
                       float quantidadeActualizar_arredonfar  = quantidadeADevolverSelecionada - quantidadeQueEstamosAdevolver;
                       quantidadeActualizar = Round(quantidadeActualizar_arredonfar, 2);
                       
                        System.out.println("\n\n\nQuantidadeAtualiza = quantidadeADevolver("+quantidadeADevolverSelecionada+") - quantidadeQueEstamosAdevolver("+quantidadeQueEstamosAdevolver+")");
                        
                        String nomeTabela2 = "ENTRADA";
                        String sql2 = "UPDATE " + nomeTabela2 + " SET ADEVOLVER="+ quantidadeActualizar +" WHERE IDENTRADA=" + idEntradaSeleccionada;
                        PreparedStatement st2 = (PreparedStatement) con.prepareStatement(sql2);
                        st2.executeUpdate();
                        st2.close();       
                        
                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    st.executeUpdate();
                    st.close();

                    jDialogDevolucoes.setVisible(false);
                    LimpaNovaDevolucao();
                    LimpaTabelaEntradas();
                    ConsultaEntradas();
                    //ALTERAR NOVME DO BOTAO E DA LABEL
                    jButtonVerDevolucoes.setText("Fazer Devolução");
                    jLabelTituloConsultaEntradas.setText("Consulta Todas as Entradas");

                    con.close();
                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }             
               
               
            }
        }

        System.out.println("\n\n** BOTAO GUARDAR DEVOLUCAO");
        System.out.println("ID FUNCIONARIO : " + idFuncionarioLogin);
        System.out.println("ID FORNECEDOR: " + idFornecedorDevolucao);
        System.out.println("ID MMAT. PRIMA: " + idMateriaPrimaDevolucao);
        System.out.println("ID ENTRADA: " + idEntradaSeleccionada);
        System.out.println("QUANT. DEVOLVIDA: " + quantidadeQueEstamosAdevolver);
        System.out.println("DATA DEVOL: " + dataDevolucao);
        System.out.println("DESCRICAO: " + observacao);
        System.out.println("DEVOLUÇÃO NUMERO: " + devolucaoNumero);
        
        System.out.println("\n\nQUANTIDADE ADEVOLVER ACTUAÇIZADA : " + quantidadeActualizar);

    }
    
    
    
    
    
    
        /*  CONSULTAS INSECTOCAÇADOR  */
    
    private void ConsultaInsectocacadores(){
        String referencia = "";
        String nome = "";
        String local = "";
       
        
        LimpaTabelaConsultaInsectocacadores();
        
        model = (DefaultTableModel) jTableConsultaInsecto.getModel();
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }   
        
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "INSECTOCACADORES";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE ESTADO="+estadoInsectocacadores;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                referencia =  rs.getString("REFERENCIA");
                nome =  rs.getString("NOME");
                local =  rs.getString("LOCAL");
                
                model.addRow(new Object[]{referencia, nome, local });
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }    
        
    }
    
    private void ConsulInsectocaAlterar(){
        String referencia = "";
        String nome = "";
        String local = "";
                   
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "INSECTOCACADORES";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDINSECTOCACADORES="+idInsectocacadorControloResultado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                referencia =  rs.getString("REFERENCIA");
                nome =  rs.getString("NOME");
                local =  rs.getString("LOCAL");
                
                jTextFieldReferenciaInsecto.setText(referencia);
                jLabelAntigaReferencia.setText(referencia);
                jTextFieldNomeInsecto.setText(nome);
                jTextFieldLocalInsecto.setText(local);
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    
    

    
    private void ConsultaLimpezas(){
        int idInsectocacador = 0;
        String nomeInsectocacador = "";
        int idFuncionario = 0;
        String nomeFuncionario = "";
        String data = "";
        
        model  = (DefaultTableModel) jTableConsultaLimpezas.getModel();
        CONTA_LINHAS_LIMPEZAS = 0;
        
         try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "LIMPEZAINSECTOCACADORES";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDINSECTOCACADORES="+idInsectocacadorControloResultado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                 idInsectocacador = rs.getInt("IDINSECTOCACADORES");
                 nomeInsectocacador = selectString("INSECTOCACADORES", "IDINSECTOCACADORES", idInsectocacador, "NOME");
                
                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");
                
                data =  rs.getString("DATA");
               
                model.addRow(new Object[]{nomeInsectocacador, nomeFuncionario, data,});
                CONTA_LINHAS_LIMPEZAS++;
            }
            
          
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        
        
        
    }
    
    private void AtualizarEstadoInsectocacador(){
    
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "INSECTOCACADORES";
           
            String sql = "UPDATE " + nomeTabela + " SET ESTADO='" + estadoInsectocacadores  +"' WHERE IDINSECTOCACADORES=" + idInsectocacadorPesquisar;

            System.out.println("ESTADO MATERIA-PRIMA ALTERADO COM SUCESSO : " + estadoInsectocacadores);
            System.out.println("id materiaa -> " + idInsectocacadorPesquisar);
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
       
    
    
    /*  CONSULTAR  EQUIPAMENTOS */
    private void ConsultaEquipamentos(){
        String nomeEquipamento = "";
        String descricaoEquipamento = "";
        
        model = (DefaultTableModel) jTableConsultaEquipamentos.getModel();
                
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "EQUIPAMENTO";
            String sql = "SELECT * FROM "+nomeTabela+ " WHERE ESTADO = '" + estadoEquipamento+"'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                nomeEquipamento =  rs.getString("NOME");
                descricaoEquipamento =  rs.getString("DESCRICAO");
                
                model.addRow(new Object[]{nomeEquipamento, descricaoEquipamento});
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        
        
    }
    
    private void ConsultaManutencaoEquipamentos(){
        int idEquip = 0;
        String nomeEquip = "";
        String data = "";
        String observacoes = "";
        String fichaTecnica = "";
        
        model = (DefaultTableModel) jTableConsultaManutencaoEquipamentos.getModel();
        CONTA_LINHAS_MANUTENCAO = 0;      
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "MANUTENCAOEQUIPAMENTOS";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDEQUIPAMENTO="+idEquipamentoControloResultado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                idEquip = rs.getInt("IDEQUIPAMENTO");
                nomeEquip = selectString("EQUIPAMENTO", "IDEQUIPAMENTO", idEquip, "NOME");
                data =  rs.getString("DATA");
                observacoes =  rs.getString("OBSERVACOES");
                fichaTecnica = rs.getString("FICHATECNICA");
                
                model.addRow(new Object[]{nomeEquip, data, observacoes, fichaTecnica});
                CONTA_LINHAS_MANUTENCAO++;
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
     
    private void ConsultaDadosEquipamentoAlterar(){
        String nomeEquipamento = "";
        String descricaoEquipamento = "";
                   
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "EQUIPAMENTO";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDEQUIPAMENTO="+idEquipamentoControloResultado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                nomeEquipamento =  rs.getString("NOME");
                descricaoEquipamento =  rs.getString("DESCRICAO");
                
                jTextFieldNomeEquipamento.setText(nomeEquipamento);
                jTextAreaObservacaoNovoEquipamento.setText(descricaoEquipamento);
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    private void AtualizaEstadoEquipametos(){
     try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "EQUIPAMENTO";
           
            String sql = "UPDATE " + nomeTabela + " SET ESTADO='" + estadoEquipamento  +"' WHERE IDEQUIPAMENTO=" + idEquipamentoPesquisar;

            System.out.println("ESTADO MATERIA-PRIMA ALTERADO COM SUCESSO : " + estadoEquipamento);
            System.out.println("id materiaa -> " + idEquipamentoPesquisar);
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    
    }
    
    
     /* CONSULTAS CONTROLO DE RESULTADOS */    
    
    //PREENCHER TABELAS COM DADOS SELECCIONADOS
    private void ConsultaBDNaoConformidadeSelect( ){
                
        int idFuncionario = 0;
        String dataNaoConform = "";
        String ocorrencia = "";
        String medidaCorrecti = "";
        
        String resultado = "NC";
        String nomefuncionario = "";
        
        String correctiva = "N"; //PARA SABERMOS QUAL SAO AS QUE AINDA ANO TEM MEDIDAS CORRECTIVAS
        
        int idFuncionarioResponsavel = 0;
        String nomeFuncionarioResponsavel = "";
        
        CONTA_LINHAS_NAOCONFORMIDADES = 0;              
        model = (DefaultTableModel) jTableNaoConformidades.getModel();
        
        
        //VERIFICAR QUAL O ID DA SECÇÃO SELECCIONADA
       String comboPesquisa = jComboBoxSeccao.getSelectedItem().toString();
       String nomeColuna = ""; 
       
       String nomeTabela = ""; //NOME DA TABELA ONDE VAMOS PESQUISAR O ID
        
        if (comboPesquisa.equals("ENTRADA")){
             nomeTabela = "ENTRADA";
             nomeColuna = "LOTEORIGEM";
        }else if (comboPesquisa.equals("EQUIPAMENTO")){
            nomeTabela = "EQUIPAMENTO";
            nomeColuna = "NOME";    
        }else if (comboPesquisa.equals("CONT. PRAGAS")){
            nomeTabela = "INSECTOCACADORES";
            nomeColuna = "NOME";            
        }else if (comboPesquisa.equals("OUTROS")){
            nomeTabela = "CONTROLORESULTADOS";
            nomeColuna = "OUTROS";            
        }
        
        System.out.println("\n\nDADOS NAO CONFORMIDADE");
        System.out.println("Nome tabela : " +nomeTabela );
        System.out.println("Nome coluna : " + nomeColuna);
        System.out.println("Seleccionada: " + secçãoControloResultadoSeleccionada);
        
        //LIGAÇÃO PARA NOS DAR OS ID DO QUE ESTAMOS A SELECIONAR
         try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }      
        
         try{         
            con = DriverManager.getConnection(url);
                          
            String sql = "select * from "+nomeTabela+" WHERE "+nomeColuna+"= '"+ secçãoControloResultadoSeleccionada +"'";
            System.out.println("select * from "+nomeTabela+" WHERE "+nomeColuna+"= '"+ secçãoControloResultadoSeleccionada +"'");
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
                   
            while(rs.next()){
                
                //VERIFICAR QUAL A COLUNA 
                
                if (!nomeColuna.equals("OUTROS")) {               
                    idControloResultadosID = rs.getInt("ID"+nomeTabela); 
                    System.out.println("ID CONTROLO : " + idControloResultadosID);
                } else{
                    idControloResultadosID = rs.getInt("IDCONTRESULTADOS");
                    System.out.println("ID CONTROLO : " + idControloResultadosID);
                }
                
            }            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        } 
        
         
         
         /* PESQUISAR CONTROLO RESULTADO COM O DEVIDO CAMPOS, MSMO QUE UM FUNCIONARIO TENHA MAIS DO QUE UM CONTROLO*
         * ESTE CONTROLO SO DIZ RESPEITO AO SELECCIONADO*/
         
         //ACTUALIZAR O NOME DA COLUNA ONDE VAMOS PESQUISAR
         if (nomeTabela.equals("ENTRADA")) {
            nomeColuna = "IDENTRADA";
        } else if (nomeTabela.equals("EQUIPAMENTO")) {
            nomeColuna = "IDEQUIPAMENTO";
        } else if (nomeTabela.equals("CONT. PRAGAS")) {
            nomeColuna = "IDINSECTOCACADORES";
        }else if (nomeTabela.equals("CONTROLORESULTADOS")){
            nomeColuna = "OUTROS";
        }
         
         
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela2 = "CONTROLORESULTADOS";
            
            String sql = "SELECT * FROM " + nomeTabela2 + " WHERE IDFUNCIONARIO=" + idFuncionarioNaoConformidade + " AND "+ nomeColuna+"="+idControloResultadosID +" "
                    + "AND DATA='"+dataControloResultadoSeleccionada + "' AND RESULTADO='" + resultado + "'";
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                //GUARDAR O ID DO CONTROLO RESULTADO, PARA PESQUISAR NAS NAO CONFORMIDADES
                idControloResultadosParaPesquisarNConformidade = rs.getInt("IDCONTRESULTADOS");
            
                //VAMSO TER  COLOCAR NA VARIVELL GLOBAL O DESCRIÇÃO DO PROBLEMA
                observacaoDoControloResultadoParaANC = rs.getString("DESCRICAO");
                
                
                //FAZER PESQUISA NA TABELA DE NAO CONFORMIDADES
                Connection con2 = DriverManager.getConnection(url);
                String nomeTabela3 = "NAOCONFORMIDADES";
                String sql2 = "select * from "+nomeTabela3+" where IDCONTRESULTADOS="+idControloResultadosParaPesquisarNConformidade+" and CORRECTIVA='"  + correctiva + "'";
                PreparedStatement st2 = (PreparedStatement) con2.prepareStatement(sql2);
                ResultSet rs2 = st2.executeQuery();
                
                 while(rs2.next()){
                     
//                    idNaoConformidade = rs2.getInt("IDNAOCONF");
//                    idContResultado = rs2.getInt("IDCONTRESULTADOS");
                    
                    idFuncionario = rs2.getInt("IDFUNCIONARIO");
                    nomefuncionario = selectString("FUNCIONARIO","IDFUNCIONARIO",idFuncionario,"NOME"); 
                    
                    dataNaoConform = rs2.getString("DATANAOCONFORMIDADE");
                    ocorrencia = rs2.getString("OCORRENCIA");
                    medidaCorrecti = rs2.getString("MEDIDACORRECTIVA");
                    
                    idFuncionarioResponsavel = rs2.getInt("IDFUNCIONARIOMEDIDACORRECTIVA");
                    nomeFuncionarioResponsavel = selectString("FUNCIONARIO","IDFUNCIONARIO",idFuncionarioResponsavel,"NOME"); 
                    
                    model.addRow(new Object[]{nomefuncionario, dataNaoConform, ocorrencia, medidaCorrecti, nomeFuncionarioResponsavel});
                    
                    CONTA_LINHAS_NAOCONFORMIDADES++;
                    //idContResultadosUltimo = idContResultado;
                }
                st2.close();   
                con2.close();
                     
            }
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

        
        //VERIFICAR SE LINHA SELECCIONA CONTEM ALGUMA NAO CONFORMIDADE
        if (CONTA_LINHAS_NAOCONFORMIDADES == 0) {
            
            
            //VERIFICAR SE A MEDIDA CORRECTIVA
            
            if (mediaCorrectivaUltima == false) {

                //PERGUNTAR SE DESEJA ADICIONAR UMA NOVO NC OU SE DESEJA ALTERAR O RESULTA DO CONTROLO PARA CONFORME
                int resultNaoConformidade = JOptionPane.showConfirmDialog(jDialogConsultarControlos, "Linha Selecciona não contem Nehuma Não Conformidade Registada!\n Deseja Atualizar o Resultado do Controlo?", null, JOptionPane.YES_NO_OPTION);

                if (resultNaoConformidade == JOptionPane.YES_OPTION) {
                    //ATUALIZAR RESULTADO DO CONTROLO
                    jDialogMedidasCorrectiva.setVisible(false);
                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver");
                    } catch (ClassNotFoundException e) {
                        System.err.print("ClassNotFoundException: ");
                        System.err.println(e.getMessage());
                        System.out.println("O driver expecificado nao foi encontrado.");
                    }

                    try {
                        con = DriverManager.getConnection(url);
                        String nomeTabela2 = "CONTROLORESULTADOS";
                        String sql = "UPDATE " + nomeTabela2 + " SET RESULTADO='C' WHERE IDCONTRESULTADOS=" + idControloResultadosParaPesquisarNConformidade;

                        PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                        st.executeUpdate();

                        //MENSAGEM A CONFIRMAR UPDATE
                        JOptionPane.showMessageDialog(null, "RESULTADO ACTUALIZADO COM SUCESSO ! ");
                        LimpaTabelaConsultaControloResultados();

                        st.close();
                        con.close();
                    } catch (SQLException ex) {
                        System.err.println("SQLException: " + ex.getMessage());
                    }

                } else {
                    //PERGUNTAR SE QUER ADICIONAR UM NOVO CONTROLO
                    int resultNovaNaoConformide = JOptionPane.showConfirmDialog(jDialogConsultarControlos, "Deseja Adicionar uma Não Conformide ?", null, JOptionPane.YES_NO_OPTION);

                    if (resultNovaNaoConformide == JOptionPane.YES_OPTION) {

                        /*passar o IDCONTROLORESULTADO  e IDFUNCIONARIO pra a nova Não Conformidade
                         *que sao as variaveis globais
                         * idControloResultadosID
                         * idFuncionarioNaoConformidade*/
                        LimpaCamposNovaNaoConformidade();
                        //escrever funcionario na label 
                        jLabelFuncionarioLogin4.setText("" + NomeFuncionarioLogin);
                        
                        //PASSAR OS DADOS PARA A PARTE JA JANELA QUE NOS INFORMA DE QUAL É O CONTROLO QUE TEMOS SELECCIONADO                        
                        jTextFieldVerSeccaoDoCR.setText(seccaoCRparaNC);
                        jTextAreaProblemadoControlo.setText(problemaCRparaNC);


                        //MOSTRAR A JANELA
                        jDialogNaoConformidades.setLocationRelativeTo(this);
                        jDialogNaoConformidades.setVisible(true);

                        //ler funcionarios para a combo box funcionario responsavel
                        LerBDFuncionario(jComboBoxNaoConformidadeFuncionarioResponsav);
                    }

                }

            }//fechar o boolean
        
        } else {
            //MOSTRAR JANELAS DE NÃO CONFORMIDADES
            jDialogConsultaNaoConformidades.setLocationRelativeTo(this);
            jDialogConsultaNaoConformidades.setVisible(true);
            
            //PASSAR OS DADOS PARA A PARTE JA JANELA QUE NOS INFORMA DE QUAL É O CONTROLO QUE TEMOS SELECCIONADO                        
            jTextFieldVerSeccaoDoCR.setText(seccaoCRparaNC);
            jTextAreaProblemadoControlo.setText(problemaCRparaNC);

        }
        
       // jDialogNaoConformidades.setVisible(false);
   
        
         
         System.out.println("\n\n******* CONTROLO RESULTADO SELECIONAR LINHA");
         System.out.println("NOME TABELA PESQUISA : " + nomeTabela);
         System.out.println("NOME COLUNA PESQUISA : " + nomeColuna);
         System.out.println("VALOR PESQUISA : " + secçãoControloResultadoSeleccionada);
         System.out.println("ID -> " + idControloResultadosID);
         System.out.println("*********************************************************");
         System.out.println("---- SELECT PESQUISA CONTROLO RESULTADO");
         System.out.println("FUNCIONARIO : " + idFuncionarioNaoConformidade);
         System.out.println("ID PESQUISA : " + idControloResultadosID);
         System.out.println("DATA : " + dataControloResultadoSeleccionada);
         System.out.println("RESULTADO : " + resultado);
         System.out.println("*********************************************************");
         System.out.println("--> DADOS PARA PESQUISAR NAO CONFORMIDADE");
         System.out.println("ID CONT. RESULTADO : " + idControloResultadosParaPesquisarNConformidade);
         System.out.println("*********************************************************");
         System.out.println("--> NUMERO DE NAO CONFORMIDADES");
         System.out.println("Nº de LINHAS : " + CONTA_LINHAS_NAOCONFORMIDADES );
         System.out.println("ID A ENCONTRAR : " + idControloResultadosParaPesquisarNConformidade);
         System.out.println("CORRECTIVA : " + correctiva);
    }
    
    private void ConsultaBDMedidasCorrectivas(){
            //ENCONTRAR O ID DA NAO CONFORMIDADE
        int linha = jTableNaoConformidades.getSelectedRow();
        String funcionario = (String) jTableNaoConformidades.getValueAt(linha, 0);
        int funcionarioID = 0;
        String data = (String) jTableNaoConformidades.getValueAt(linha, 1);
        String funcionarioResponsavel = (String) jTableNaoConformidades.getValueAt(linha, 4);
        int funcionarioResponsavelID = 0;

               
        funcionarioID = selectId("FUNCIONARIO", "NOME", funcionario, "IDFUNCIONARIO");
        funcionarioResponsavelID = selectId("FUNCIONARIO", "NOME", funcionarioResponsavel, "IDFUNCIONARIO");

        //IR A BD FAZER PESQUISA
        
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
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDFUNCIONARIO="+funcionarioID+ " AND IDFUNCIONARIOMEDIDACORRECTIVA="+funcionarioResponsavelID+ " AND DATANAOCONFORMIDADE='"+data+"'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                idMedidaCorrectivaUpdate =  rs.getInt("IDNAOCONF");  
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        //ABRIR JANELA DAS MEDIDAS CORRECTIVAS
        jDialogMedidasCorrectiva.setLocationRelativeTo(this);
        jDialogMedidasCorrectiva.setVisible(true);        
        
        System.out.println("\n****** QUAL A NAO CONFORMIDADE ");
        System.out.println("ID FUNCIONARIO " + funcionarioID);
        System.out.println("ID FUNC. RESPONSAVEL : " + funcionarioResponsavelID);
        System.out.println("DATA : " + data);
        System.out.println("ID DA NAO CONFORMIDADE : " + idMedidaCorrectivaUpdate);

        
    
    }
    
    private void ConsultaHistoricoControlo() {
        int linha = jTableConsultarControlos.getSelectedRow();
        int idFuncionario;
        int contaLinhas = 0;
        //DADOS DE PESQUISA
        String nomeFunc = (String) jTableConsultarControlos.getValueAt(linha, 0);
        idFuncionario = selectId("FUNCIONARIO", "NOME", nomeFunc, "IDFUNCIONARIO");
        String seccao = (String) jTableConsultarControlos.getValueAt(linha, 1);
        String data = (String) jTableConsultarControlos.getValueAt(linha, 2);
        String resultado = "C";
        String nomeTabela = "CONTROLORESULTADOS";
        String nomeTabela2 = "NAOCONFORMIDADES";
        
        int idControloResultado = 0; 
        int idPesquisa = 0;
       
        
        String ocorrencia = "";
        String medidaCorrectiva = "";
        int idfuncionarioRegistou=0;
        String funcionarioRegistou = "";
        String Observacao = "";
        String dataOcorrencia ="";
        //SABER O ID DO CONTROLO
        
        //SABER A SECÇÃO QUE ESCOLHEMOS
        String comboPesquisa = jComboBoxSeccao.getSelectedItem().toString();
        String nomeColuna = "";

        if (comboPesquisa.equals("ENTRADA")){
            nomeColuna = "IDENTRADA";
            idPesquisa = selectId("ENTRADA", "LOTEORIGEM", seccao, "IDENTRADA");
           
        }else if (comboPesquisa.equals("EQUIPAMENTO")){
            nomeColuna = "IDEQUIPAMENTO";
            idPesquisa = selectId("EQUIPAMENTO", "NOME", seccao, "IDEQUIPAMENTO");

        }else if (comboPesquisa.equals("INSECTOCACADORES")){
            nomeColuna = "IDINSECTOCACADORES";
            idPesquisa = selectId("INSECTOCACADORES", "NOME", seccao, "IDINSECTOCACADORES");

        }else if (comboPesquisa.equals("OUTROS")){
            nomeColuna = "OUTROS";

        }

        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        try{
                     
            con = DriverManager.getConnection(url);
            
            //String sql = "SELECT * FROM "+nomeTabela + " WHERE IDFUNCIONARIO='"+idFuncionario+ "' AND "+nomeColuna+"='"+seccao+"' AND DATA='"+data+"' AND RESULTADO='"+resultado+"'";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDFUNCIONARIO="+idFuncionario+" AND "+nomeColuna+"="+idPesquisa+" AND RESULTADO='"+resultado+"'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
               //idControloResultado =  rs.getInt("IDCONTRESULTADOS");  
                idControloResultadoHistorico = rs.getInt("IDCONTRESULTADOS"); 
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        
        //PROCURAR NA TABELA NAO CONFORMIDADES
         model = (DefaultTableModel) jTableVerHistorico.getModel();
        try{
                     
            con = DriverManager.getConnection(url);

            String sql = "SELECT * FROM "+nomeTabela2 + " WHERE IDCONTRESULTADOS="+idControloResultadoHistorico+" AND CORRECTIVA='S'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                //CAMPOS A LER
                idfuncionarioRegistou = rs.getInt("IDFUNCIONARIOMEDIDACORRECTIVA");
                funcionarioRegistou = selectString("FUNCIONARIO", "IDFUNCIONARIO", idfuncionarioRegistou, "NOME");
                dataOcorrencia = rs.getString("DATANAOCONFORMIDADE");
                ocorrencia = rs.getString("OCORRENCIA");
                medidaCorrectiva = rs.getString("MEDIDACORRECTIVA");
                Observacao = rs.getString("OBSERVACAOMEDIDACORRECTIVA");
                
                contaLinhas++;
                model.addRow(new Object[]{contaLinhas, funcionarioRegistou, dataOcorrencia, ocorrencia, medidaCorrectiva, Observacao});
                //ESCREVER NA TABELA
                System.out.println("ID CONTROLO -> " + idControloResultadoHistorico );
                System.out.println("\n" + contaLinhas);
                System.out.println(" " + ocorrencia);
                System.out.println(" " + medidaCorrectiva);
                System.out.println(" " + idfuncionarioRegistou);
                System.out.println(" " + funcionarioRegistou);
                System.out.println(" " + Observacao);
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        if(contaLinhas == 0){
            System.out.println("ZERO ZERO");
            JOptionPane.showMessageDialog(jDialogVerHistoricoControlo, "Controlo Não Contem Historico!");
        }else{
             jDialogVerHistoricoControlo.setLocationRelativeTo(this);
             jDialogVerHistoricoControlo.setVisible(true);
        }
        
        
        
        
        //ESCREVER 
//        System.out.println("SELECT * FROM "+nomeTabela + " WHERE IDFUNCIONARIO="+idFuncionario+" AND "+nomeColuna+"="+idPesquisa);
//        System.out.println("ID CONTROLO : " + idControloResultado);
//        System.out.println("ID FUNCIONARIO: " + idFuncionario);
    }
    
    private void ConsultaHistoricoMedidaCorrectiva(){
        int linha = jTableVerHistorico.getSelectedRow();
        //CAMPOS PARA PESQUISA            
        String funcionario = (String) jTableVerHistorico.getValueAt(linha, 1);
        int idFuncionario = selectId("FUNCIONARIO", "NOME", funcionario, "IDFUNCIONARIO");
        String dataOcorrencia = (String) jTableVerHistorico.getValueAt(linha, 2);
        String ocorrencia = (String) jTableVerHistorico.getValueAt(linha, 3);
        String medidaCorrectiva = (String) jTableVerHistorico.getValueAt(linha, 4);
        
        String funcionarioResponsavel = "";
        int idFuncionarioResponsavel = 0;
        String dataMC ="";
        String obervacoes = "";
        
        
        //PROCURAR NA BASE DE DADOS
        
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
            //String sql = "SELECT * FROM "+nomeTabela + " WHERE IDFUNCIONARIO='"+idFuncionario+ "' AND "+nomeColuna+"='"+seccao+"' AND DATA='"+data+"' AND RESULTADO='"+resultado+"'";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDFUNCIONARIO="+idFuncionario+" AND DATANAOCONFORMIDADE='"+dataOcorrencia+"' AND OCORRENCIA='"+ocorrencia+"' AND MEDIDACORRECTIVA='"+medidaCorrectiva+"' AND CORRECTIVA='S' AND IDCONTRESULTADOS="+idControloResultadoHistorico;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                //DADOS DA MEDIDA CORRECTIVA 
                idFuncionarioResponsavel = rs.getInt("IDFUNCIONARIOMEDIDACORRECTIVA");
                funcionarioResponsavel = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionarioResponsavel, "NOME");
                
                dataMC = rs.getString("DATAMEDIDACORRECTIVA");
                obervacoes = rs.getString("OBSERVACAOMEDIDACORRECTIVA");
                
                if (obervacoes.equals("")){
                    obervacoes = "Sem Observação.";
                }
                
                
                JOptionPane.showMessageDialog(jDialogVerHistoricoControlo, "MEDIDAS CORRECTIVAS \n\n"
                        + "Funcionario (Responsavel): " + funcionarioResponsavel+ "\n"
                        + "Data Medica Correctiva   : " + dataMC+"\n"
                        + "Observações              : " + obervacoes+"\n");
                
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        
        
    }
    
       /* CONSULTAS  ENTRADAS */
    private void ConsultaEntradas() {

        int idFuncionario = 0;
        String nomeFuncionario = "";

        int idFornecedor = 0;
        String nomeFornecedor = "";

        int idMateriaPrima = 0;
        String nomeMateriaPrima = "";

        String dataEntrega = "";
        String loteOrigem = "";
        String peso = "";
        String temperatura = "";
        String dataValidade = "";
        String caraOrgonolept = "";
        String embalagem = "";
        String devolucao = "";
        String aDevolver = "";
        
        int contaLinha=0;
        model = (DefaultTableModel) jTableConsultaEntradas.getModel();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "ENTRADA";
            String sql = "SELECT * FROM " + nomeTabela+" ORDER by LOTEORIGEM DESC";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFornecedor = rs.getInt("IDFORNECEDOR");
                nomeFornecedor = selectString("FORNECEDOR", "IDFORNECEDOR", idFornecedor, "NOME");

                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");

                idMateriaPrima = rs.getInt("IDMATERIAPRIMA");
                nomeMateriaPrima = selectString("MATERIA_PRIMA", "IDMATERIAPRIMA", idMateriaPrima, "NOME");

                dataEntrega = rs.getString("DATAENTREGA");
                loteOrigem = rs.getString("LOTEORIGEM");
                peso = rs.getString("QUANTIDADE");
                temperatura = rs.getString("TEMPERATURA");
                dataValidade = rs.getString("DATAVALIDADE");
                caraOrgonolept = rs.getString("CARATORGONOLEPTICAS");
                embalagem = rs.getString("EMBALAGEM");
                devolucao = rs.getString("DEVOLUCAO");
                aDevolver = rs.getString("ADEVOLVER");
                

                if (devolucao.equals("S")) {
                    devolucao = "Sim";
                } else {
                    devolucao = "Não";
                }
                contaLinha++;
                model.addRow(new Object[]{contaLinha,nomeFuncionario, nomeFornecedor, nomeMateriaPrima, dataEntrega, loteOrigem, peso, aDevolver});
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

    }

    private void ConsultaDevolucoesEntradas() {
        int idFornecedor = 0;
        String nomeFornecedor = "";

        int idFuncionario = 0;
        String nomeFuncionario = "";

        int idMateriaPrima = 0;
        String nomeMateriaPrima = "";

        String dataEntrega = "";
        String loteOrigem = "";
        String peso = "";
        String temperatura = "";
        String dataValidade = "";
        String caraOrgonolept = "";
        String embalagem = "";
        String devolucao = "";
        String aDevolver = "";
        int contaLinha = 0;
        String where = "S";
        model = (DefaultTableModel) jTableConsultaEntradas.getModel();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "ENTRADA";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE DEVOLUCAO='" + where + "' ORDER BY LOTEORIGEM DESC";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFornecedor = rs.getInt("IDFORNECEDOR");
                nomeFornecedor = selectString("FORNECEDOR", "IDFORNECEDOR", idFornecedor, "NOME");

                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");

                idMateriaPrima = rs.getInt("IDMATERIAPRIMA");
                nomeMateriaPrima = selectString("MATERIA_PRIMA", "IDMATERIAPRIMA", idMateriaPrima, "NOME");

                dataEntrega = rs.getString("DATAENTREGA");
                loteOrigem = rs.getString("LOTEORIGEM");
                peso = rs.getString("QUANTIDADE");
                temperatura = rs.getString("TEMPERATURA");
                dataValidade = rs.getString("DATAVALIDADE");
                caraOrgonolept = rs.getString("CARATORGONOLEPTICAS");
                embalagem = rs.getString("EMBALAGEM");
                devolucao = rs.getString("DEVOLUCAO");
                aDevolver = rs.getString("ADEVOLVER");

                if (devolucao.equals("S")) {
                    devolucao = "Sim";
                } else {
                    devolucao = "Não";
                }
                contaLinha++;
                model.addRow(new Object[]{contaLinha,nomeFuncionario, nomeFornecedor, nomeMateriaPrima, dataEntrega, loteOrigem, peso, aDevolver});
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    private void ConsultaControloResultadosEntradas() {

        //CAMPOS DA TABELA
        int idFuncionario = 0;
        String nomeFuncionario = "";
        int idEntrada = 0;

        String data = "";
        String resultado = "";
        String fichaTecnica = "";
        String descricao = "";



        model = (DefaultTableModel) jTableConsultarControlos.getModel();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "CONTROLORESULTADOS";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE IDENTRADA=" + idEntradaSeleccionada;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");

                idEntrada = rs.getInt("IDENTRADA");


                data = rs.getString("DATA");
                resultado = rs.getString("RESULTADO");
                fichaTecnica = rs.getString("FICHATECNICA");
                descricao = rs.getString("DESCRICAO");

                model.addRow(new Object[]{nomeFuncionario, data, resultado});
                CONTA_LINHAS_CONTROLORESULTADOS++;
                abrirControloResultados = true;
            }



            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }



    }

    private void ConsultaDevolucoesTabela() {
        int idFornecedor = 0;
        String nomeFornecedor = "";
        
        int idMateriaPrima = 0;
        String nomeMatPrima = "";

        int idEntrada = 0;
        String loteOriginal = "";
        
        String dataDevolucao = "";
        String quantiDevolvida = "";
        String observacao = "";
        int devolucaoNum = 0;
        
        int numeroLinha = 0;
        
       model = (DefaultTableModel) jTableConsultaDevolucoes.getModel();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }



        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "DEVOLUCOES";
            String sql = "SELECT * FROM " + nomeTabela;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFornecedor = rs.getInt("IDFORNECEDOR");
                nomeFornecedor = selectString("FORNECEDOR", "IDFORNECEDOR", idFornecedor, "NOME");

                idMateriaPrima = rs.getInt("IDMATERIAPRIMA");
                nomeMatPrima = selectString("MATERIA_PRIMA", "IDMATERIAPRIMA", idMateriaPrima, "NOME");
                
                dataDevolucao = rs.getString("DATADEVOLUCAO");
                quantiDevolvida = rs.getString("QUANTIDADEDEVOLVIDA");

                observacao = rs.getString("OBSERVACAO");
                
                devolucaoNum = rs.getInt("DEVOLUCAONUMERO");
                
                
                //IR BUSCAR O LOTE DA MATERIA PRIMA ATRAVES DO ID ENTRADA
                idEntrada = rs.getInt("IDENTRADA");
                    
                    Connection con2 = DriverManager.getConnection(url);
                    String nomeTabela2= "ENTRADA";
                    String sql2 = "SELECT * FROM " + nomeTabela2 + " WHERE IDENTRADA=" + idEntrada;
                    PreparedStatement st2 = (PreparedStatement) con2.prepareStatement(sql2);
                    ResultSet rs2 = st2.executeQuery();
                    while (rs2.next()){
                        loteOriginal = rs2.getString("LOTEORIGEM");
                        numeroLinha++;
                    }
                    st2.close();
                    con2.close();
                                 
                model.addRow(new Object[]{numeroLinha,nomeFornecedor, nomeMatPrima,loteOriginal, devolucaoNum, dataDevolucao, quantiDevolvida});
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
      

    }
    
    private void ConsultaDevolucaoMostraFichaCompleta(){
    
        int idFornecedor = 0;
        String nomeFornecedor = "";
        
        int idFuncionario = 0;
        String nomeFuncionario = "";
        
        int idMatPrima = 0;
        String nomeMatPrima = "";

        int idEntrada = 0;
        
        String dataDevolucao = "";
        
        String quantiDevolvida = "";
        
        String observacao = "";
        String loteOriginal = "";

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "DEVOLUCOES";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE IDDEVOLUCAO="+idDevolucaoSeleccionado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFornecedor = rs.getInt("IDFORNECEDOR");
                nomeFornecedor = selectString("FORNECEDOR", "IDFORNECEDOR", idFornecedor, "NOME");

                idFuncionario =  rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");

                idEntrada = rs.getInt("IDENTRADA");
                
                idMatPrima = rs.getInt("IDMATERIAPRIMA");
                nomeMatPrima = selectString("MATERIA_PRIMA", "IDMATERIAPRIMA", idMatPrima, "NOME");

                dataDevolucao = rs.getString("DATADEVOLUCAO");
                quantiDevolvida = rs.getString("QUANTIDADEDEVOLVIDA");

                observacao = rs.getString("OBSERVACAO");

                //IR BUSCAR O LOTE ATRAVES DO ID ENTRADA
                
                Connection con2 = DriverManager.getConnection(url);
                    String nomeTabela2= "ENTRADA";
                    String sql2 = "SELECT * FROM " + nomeTabela2 + " WHERE IDENTRADA=" + idEntrada;
                    PreparedStatement st2 = (PreparedStatement) con2.prepareStatement(sql2);
                    ResultSet rs2 = st2.executeQuery();
                    while (rs2.next()){
                        loteOriginal = rs2.getString("LOTEORIGEM");
                    }
                    st2.close();
                    con2.close();            
                
                JOptionPane.showMessageDialog(jDialogConsultaDevolucoes, "INFORMAÇÃO!\n\n "
                                                                       + "NOME FUNCIONARIO : " + nomeFuncionario+"\n\n"
                                                                       + "NOME FORNECEDOR : " + nomeFornecedor+"\n\n"
                                                                       + "LOTE : " + loteOriginal + "\n\n"
                                                                       + "MATER. PRIMA : " + nomeMatPrima + "\n\n"
                                                                       + "DATA DEVOLUÇÃO : " + dataDevolucao + "\n\n"
                                                                       + "QUANTID. DEVOLV. : " +quantiDevolvida + "\n\n"
                                                                       + "OBSERVAÇÃO : " + observacao + "\n\n\n");
                
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    private void ConsultaControloResultadosEntradaFichaCompleta(){
    
        //CAMPOS DA TABELA
        int idFuncionario = 0;
        String nomeFuncionario = "";
        int idEntrada = 0;
        String data = "";
        String fichaTecnica = "";
        String descricao = "";
        
        
        model = (DefaultTableModel) jTableConsultarControlos.getModel();
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        
        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "CONTROLORESULTADOS";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE IDENTRADA="+idEntradaSeleccionada;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");

                idEntrada = rs.getInt("IDENTRADA");
                
                data = rs.getString("DATA");
                fichaTecnica = rs.getString("FICHATECNICA");
                descricao = rs.getString("DESCRICAO");

                JOptionPane.showMessageDialog(jDialogConsultaDevolucoes, "FICHA CONTROLO RESULTADOS!\n "
                                                                       + "NOME FUNCIONARIO: " + nomeFuncionario+"\n"
                                                                       + "Nº ENTREGA : " + idEntrada+"\n"
                                                                       + "DATA: " + data + "\n"
                                                                       + "FICHA TECNICA: " + fichaTecnica + "\n"
                                                                       + "DESCRIÇÃO: " + descricao );
                
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }


        
        
    }
    
    private void PesquisaDevolucoesComDadosDeEntrada(){
    
        int idFornecedor = 0;
        String nomeFornecedor = "";
        int idMateriaPrima = 0;
        String nomeMatPrima = "";
        String dataDevolucao = "";
        String quantiDevolvida = "";
        int devolucaoNum = 0;
        
        String opcaoSelect = "";
        
        int idEntrada = 0;
        String loteOriginal = "";

        model = (DefaultTableModel) jTableConsultaDevolucoes.getModel();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }



        try {
          
            CONTA_NUMERO_DEVOLUCOES = 0;
            con = DriverManager.getConnection(url);
            String nomeTabela = "DEVOLUCOES";
          
            //VERIFICAR QUAL O SELECT QUE VAMOS EFECTUAR
            
            if(idForncedorPesquisaDevolucao != 0 && idMateriaPrimaPesquisaDevolucao == 0){
                //VAMOS PESQUISAR SO PELO FORNECEDOR
                 sql = "SELECT * FROM " + nomeTabela + " WHERE IDFORNECEDOR=" + idForncedorPesquisaDevolucao ;
                System.out.println("SELECT FORNCEDOR");
            } else if (idForncedorPesquisaDevolucao == 0 && idMateriaPrimaPesquisaDevolucao != 0){
                //VAMOS PESQUISAR PELA MATERIA PRIMA
                 sql = "SELECT * FROM " + nomeTabela + " WHERE IDMATERIAPRIMA=" + idMateriaPrimaPesquisaDevolucao ;
                System.out.println("SELECT MATERIA PRIMA");
            }else{
                //VAMOS PESQUISAR PELO FORNECEDOR E PELA MATERIA-PRIMA
                 sql = "SELECT * FROM " + nomeTabela + " WHERE IDFORNECEDOR=" + idForncedorPesquisaDevolucao +" AND IDMATERIAPRIMA="+idMateriaPrimaPesquisaDevolucao;
                 System.out.println("SELECT FORNCEDOR e MATERIA PRIMA");
            }
  
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                idFornecedor = rs.getInt("IDFORNECEDOR");
                nomeFornecedor = selectString("FORNECEDOR", "IDFORNECEDOR", idFornecedor, "NOME");

                idMateriaPrima = rs.getInt("IDMATERIAPRIMA");
                nomeMatPrima = selectString("MATERIA_PRIMA", "IDMATERIAPRIMA", idMateriaPrima, "NOME");

                dataDevolucao = rs.getString("DATADEVOLUCAO");
                quantiDevolvida = rs.getString("QUANTIDADEDEVOLVIDA");

                devolucaoNum = rs.getInt("DEVOLUCAONUMERO");
             
                
                 //IR BUSCAR O LOTE DA MATERIA PRIMA ATRAVES DO ID ENTRADA
                idEntrada = rs.getInt("IDENTRADA");
                    
                    Connection con2 = DriverManager.getConnection(url);
                    String nomeTabela2= "ENTRADA";
                    String sql2 = "SELECT * FROM " + nomeTabela2 + " WHERE IDENTRADA=" + idEntrada;
                    PreparedStatement st2 = (PreparedStatement) con2.prepareStatement(sql2);
                    ResultSet rs2 = st2.executeQuery();
                    while (rs2.next()){
                        loteOriginal = rs2.getString("LOTEORIGEM");
                               
                    }
                    st2.close();
                    con2.close();
                
                    CONTA_NUMERO_DEVOLUCOES++;
                model.addRow(new Object[]{CONTA_NUMERO_DEVOLUCOES, nomeFornecedor, nomeMatPrima, loteOriginal,devolucaoNum, dataDevolucao, quantiDevolvida});
               
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        } 
        
        System.out.println("\nCONTA_NUMERO_DEVOLUÇOES --> " + CONTA_NUMERO_DEVOLUCOES );
        
//         if (CONTA_NUMERO_DEVOLUCOES == 0){
//           
//         }
      
        
    
    }
    
    private void ConsultaFornecedores(){
        String nome = "";
        String nif = "";
        String tipoProduto = "";
        int contaLinhas  = 0;
        model = (DefaultTableModel) jTableConsultaFornecedor.getModel();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "FORNECEDOR";
            String sql = "SELECT * FROM " + nomeTabela+ " WHERE ESTADO='"+EstadoFornecedor+"'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                nome = rs.getString("NOME");
                nif = rs.getString("NIF");
                tipoProduto = rs.getString("TIPOPRODUTO");
                contaLinhas++;
                model.addRow(new Object[]{contaLinhas,nome, nif, tipoProduto});
            }

            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    
    }
    
    private void ConsultaDadosMateriaPrimaAlterar(){
        String nomeMp = "";
        String descricao = "";
        String unidade = "";
                   
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "MATERIA_PRIMA";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDMATERIAPRIMA="+idMateriaPrimaPesquisar ;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                nomeMp =  rs.getString("NOME");
                descricao =  rs.getString("DESCRICAO");
                unidade = rs.getString("UNIDADE");
                
                jTextFieldNomeMateriaPrima.setText(nomeMp);
                jTextAreaDescricaoMateriaPrima.setText(descricao);
                jTextFieldUnidadeMateriaPrima.setText(unidade);
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    
    
    /*  CONSULTA ENTRADAS   -  eidtar entrada*/
    private void ConsultaDadosForncedorAlterar(){
        int contacto = 0;
        boolean estado ;
        
        model = (DefaultTableModel) jTableConsultaEquipamentos.getModel();
                
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }    
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "FORNECEDOR";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE IDFORNECEDOR="+ idFornecedorPesquisar;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                contacto = rs.getInt("CONTATO");
                estado = rs.getBoolean("ESTADO");
                        
                jTextFieldNomeFornecedor.setText(rs.getString("NOME") );
                jTextFieldMoradaFornecedor.setText(rs.getString("MORADA"));
                jTextFieldCodPostalFornecedor.setText(rs.getString("COD_POSTAL"));
                jTextFieldLocalidadeFornecedor.setText(rs.getString("LOCALIDADE"));
                jTextFieldContactoFornecedor.setText(""+contacto);
                jTextFieldEmailFornecedor.setText(rs.getString("EMAIL"));
                jTextFieldNIFFornecedor.setText(rs.getString("NIF"));
                jTextFieldTipoProdutoFornecedor.setText(rs.getString("TIPOPRODUTO"));
                
                if(estado == true){
                    jComboBoxEstadoFornecedor.setSelectedItem(0);
                }else{
                    jComboBoxEstadoFornecedor.setSelectedItem(1);
                }
                
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    private void ConsultaMateriasPrimas(){
    
        String nome = "";
        String descricao = "";
        String unidade = "";
        int NumerLinha = 0;
        
        LimpaTabelaMateriaPrimas();
        
        model = (DefaultTableModel) jTableConsultaMateriasPrimas.getModel();
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");  
        }catch(ClassNotFoundException e){
           System.err.print("ClassNotFoundException: ");
           System.err.println(e.getMessage());
           System.out.println("O driver expecificado nao foi encontrado."); 
        }   
        
        
        try{
                     
            con = DriverManager.getConnection(url);
            String nomeTabela = "MATERIA_PRIMA";
            String sql = "SELECT * FROM "+nomeTabela + " WHERE ESTADO=" + estadoMateriaPrima;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next()){
                nome =  rs.getString("NOME");
                descricao =  rs.getString("DESCRICAO");
                unidade =  rs.getString("UNIDADE");
                NumerLinha++;
                
                model.addRow(new Object[]{NumerLinha, nome, descricao, unidade });
            }
            
            st.close();
            con.close();
        }catch (SQLException ex){
            System.err.println("SQLException: " + ex.getMessage());
        }
    
    }
    
   
    /*  PESQUISAR INSECTOCAÇADOR   */
    private void PesquisaInsectocacador(){
        // DADOS A EPSQUISAR
        String referencia = "";
        String nome = "";
        String local = "";
        boolean estado = false ;
        String pesquisa = jTextFieldPesquisaInsectocacador.getText();
        
        //verificar o estado de pesquisa
        String textoBotao = jButtonVerInsectocacadoresInativos.getText();
        
        if(textoBotao.equals("Ver Inativas"))
            estado = true;
        else
            estado = false;
            
        System.out.println("estado : " + estado);
        
        
        model = (DefaultTableModel) jTableConsultaInsecto.getModel();
        
          try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado."); 
        }
          
          
        try{
            con = DriverManager.getConnection(url);
            String nomeTabela = "INSECTOCACADORES";
            sql = "select * from "+ nomeTabela+" where (REFERENCIA LIKE '"+pesquisa+ "%' or NOME like '" + pesquisa+"%' or LOCAL like '" +pesquisa + "%') AND ESTADO="+estado;
           // sql = "select * from "+ nomeTabela+" where REFERENCIA like '"+pesquisa+ "%' or NOME like '" + pesquisa+"%' or LOCAL like '" +pesquisa + "%'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                referencia = rs.getString("REFERENCIA");
                nome = rs.getString("NOME");
                local = rs.getString("LOCAL");
               
                //guardar dados num arraylist e adicionalos a tabela
                model.addRow(new Object[]{referencia, nome, local});
            }
            st.close();
            con.close();
        }catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        
        
    }
    
   /*  PESQUISAR EQUIPAMENTOS  */
    private void PesquisarEquipamento(){
         // DADOS A EPSQUISAR
        
        String nome = "";
        String descricao = "";        
        String pesquisa = jTextFieldPesquisaEquipamento.getText();
        boolean estado;
        
        model = (DefaultTableModel) jTableConsultaEquipamentos.getModel();
        
        //VER ESTADO EM QUE VAMOS PROCURAR
        String textoBotao = jButtonVerInativasEquipamentos.getText();
        if(textoBotao.equals("Ver Inativas"))
            estado = true;
        else
            estado = false;
        
        System.out.println("ESTADO PESQUISA EQUIPAMENTO -> " + estado);
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado."); 
        }
          
          
        try{
            con = DriverManager.getConnection(url);
            String nomeTabela = "EQUIPAMENTO";
            sql = "select * from "+ nomeTabela+" where (NOME like '%"+pesquisa+ "%') AND ESTADO="+estado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){                
                nome = rs.getString("NOME");
                descricao = rs.getString("DESCRICAO");
               
                //guardar dados num arraylist e adicionalos a tabela
                model.addRow(new Object[]{nome, descricao});
            }
            st.close();
            con.close();
        }catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
    }
    
    /*  PESQUISAR ENTRADA  */
    private void PesquisaEntrada() {
        String pesquisaEntrada = jTextFieldPesquisarEntrada.getText();


        int idFuncionario = 0;
        String nomeFuncionario = "";

        int idFornecedor = 0;
        String nomeFornecedor = "";

        int idMateriaPrima = 0;
        String nomeMateriaPrima = "";

        String dataEntrega = "";
        String loteOrigem = "";
        String peso = "";
        String temperatura = "";
        String dataValidade = "";
        String caraOrgonolept = "";
        String embalagem = "";
        String devolucao = "";

        model = (DefaultTableModel) jTableConsultaEntradas.getModel();

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }


        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "ENTRADA";
            sql = "select * from " + nomeTabela + " where LOTEORIGEM like '%" + pesquisaEntrada + "%'";
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                idFornecedor = rs.getInt("IDFORNECEDOR");
                nomeFornecedor = selectString("FORNECEDOR", "IDFORNECEDOR", idFornecedor, "NOME");

                idFuncionario = rs.getInt("IDFUNCIONARIO");
                nomeFuncionario = selectString("FUNCIONARIO", "IDFUNCIONARIO", idFuncionario, "NOME");

                idMateriaPrima = rs.getInt("IDMATERIAPRIMA");
                nomeMateriaPrima = selectString("MATERIA_PRIMA", "IDMATERIAPRIMA", idMateriaPrima, "NOME");

                dataEntrega = rs.getString("DATAENTREGA");
                loteOrigem = rs.getString("LOTEORIGEM");
                peso = rs.getString("QUANTIDADE");
                temperatura = rs.getString("TEMPERATURA");
                dataValidade = rs.getString("DATAVALIDADE");
                caraOrgonolept = rs.getString("CARATORGONOLEPTICAS");
                embalagem = rs.getString("EMBALAGEM");
                devolucao = rs.getString("DEVOLUCAO");


                if (devolucao.equals("S")) {
                    devolucao = "Sim";
                } else {
                    devolucao = "Não";
                }

                model.addRow(new Object[]{nomeFuncionario, nomeFornecedor, nomeMateriaPrima, dataEntrega, loteOrigem, peso, devolucao});
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }



    }
    
    private void PesquisaMateriaPrima(){
        String nome = "";
        String descricao = "";
        String unidade = "";
        int contaNumero = 0;
        String pesquisa = jTextFieldPesquisarMateriaPrima.getText();
        boolean estado = false;
        model = (DefaultTableModel) jTableConsultaMateriasPrimas.getModel();
        
        
        //VER QUAL O ESTADO EM QUE VAMOS PESQUISAR
        String nomeBotao = jButtonVerInativosMP.getText();
        
        
        if (nomeBotao.equals("Ver Inativas"))
            estado = true;
        else
            estado = false;
        
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado."); 
        }
          
          
        try{
            con = DriverManager.getConnection(url);
            String nomeTabela = "MATERIA_PRIMA";
            sql = "select * from "+ nomeTabela+" where (NOME like '%"+pesquisa+ "%') AND ESTADO="+ estado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){                
                nome = rs.getString("NOME");
                descricao = rs.getString("DESCRICAO");
                unidade = rs.getString("UNIDADE");
                contaNumero++;
                //guardar dados num arraylist e adicionalos a tabela
                model.addRow(new Object[]{contaNumero, nome, descricao, unidade});
            }
            st.close();
            con.close();
        }catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    
    
    }
    
    private void PesquisarFornecedor(){

        String nome = "";
        String nif = "";
        String tipoProduto = "";
        int contaNumero = 0;
        
        String pesquisa = jTextFieldPesquisarFornecedor.getText();
       
        boolean estado = false;
        model = (DefaultTableModel) jTableConsultaFornecedor.getModel();
        
        
        //VER QUAL O ESTADO EM QUE VAMOS PESQUISAR
        String nomeBotao = jButtonFornecedoresInativos.getText();
        
        
        if (nomeBotao.equals("Ver Inativos"))
            estado = true;
        else
            estado = false;
        
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        }catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado."); 
        }
          
          
        try{
            con = DriverManager.getConnection(url);
            String nomeTabela = "FORNECEDOR";
            sql = "SELECT * FROM "+ nomeTabela+" WHERE (NOME like '%"+pesquisa+ "%' OR NIF like '"+pesquisa+"%' OR TIPOPRODUTO like '%"+pesquisa+"%') AND ESTADO="+ estado;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()){                
                nome = rs.getString("NOME");
                nif = rs.getString("NIF");
                tipoProduto =  rs.getString("TIPOPRODUTO");
                contaNumero++;
                //guardar dados num arraylist e adicionalos a tabela
                model.addRow(new Object[]{contaNumero, nome, nif, tipoProduto});
            }
            st.close();
            con.close();
        }catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
        
    }
    
    
    
    /*  UPDATE INSECTOCAÇADOR */
    private void UpdateDadosInsectocacador(){
        
        
        String nome = jTextFieldNomeInsecto.getText();
        String local = jTextFieldLocalInsecto.getText();
        

        if (nome.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovoInsectocacador, "Insira o NOME do Insecto.!");
        } else if (local.equals("")) {
            JOptionPane.showMessageDialog(jDialogNovoInsectocacador, "Insira o LOCAL do Insecto.!");
        } else {

            //ATUALIZAR OS DADOS
            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "INSECTOCACADORES";
                String sql = "UPDATE " + nomeTabela + " SET NOME='" + nome + "'" + "," + " LOCAL='" + local + "' WHERE IDINSECTOCACADORES=" + idInsectocacadorControloResultado;
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                st.executeUpdate();

                st.close();
                con.close();

                jDialogNovoInsectocacador.setVisible(false);
                JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "Campos Atualizados com Sucesso !");

            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }

        }

        System.out.println("\n** ACTUALIZA CAMPOS INTECOÇADOR SELECIONADO");
        System.out.println("NOME: " + nome);
        System.out.println("LOCAL: " + local);

    }
    
    private void UpdateReferenciaInsectocacador() {
        //VAMOS ATUALIZAR  SO A REFERENCIA E VER SE EXISTE NA BD
        String refereciaUpdate = jTextFieldNovaReferencia.getText();
        boolean jaExiste = false;

        if (refereciaUpdate.equals("")) {
            //JOptionPane.showMessageDialog(jDialogAlterarReferencia, "Preencher o campo da Referencia!");
            JOptionPane.showMessageDialog(null, "DEVE preencher o campo referência.", "CAMPO OBRIGATÓRIO", JOptionPane.WARNING_MESSAGE);
        } else {
            // CORRER BD E VERIFICAR SE REFERECNIA JA EXISTE
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) {
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }
            try {

                con = DriverManager.getConnection(url);
                String nomeTabela = "INSECTOCACADORES";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE REFERENCIA='" + refereciaUpdate + "'";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    jaExiste = true;
                }
                st.close();
                con.close();
            } catch (SQLException ex) {

                jaExiste = false;
                System.out.println("ERROR : " + ex);
            }

            if (jaExiste == true) {
                //MENSAGEM DE ERRO A DIZER KE JA EXISTE
                JOptionPane.showMessageDialog(null, "Referência já Existe.", "CAMPO REPETIDO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                //VAMOS FAZER O UPDATE A REFERENCIA

                try {
                    con = DriverManager.getConnection(url);
                    String nomeTabela = "INSECTOCACADORES";
                    String sql = "UPDATE " + nomeTabela + " SET REFERENCIA='" + refereciaUpdate + "' WHERE IDINSECTOCACADORES=" + idInsectocacadorControloResultado;
                    PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                    st.executeUpdate();

                    st.close();
                    con.close();
                    
                    //ATUALIZAR TABELA
                    LimpaTabelaConsultaInsectocacadores();
                    ConsultaInsectocacadores();
                    
                    jDialogNovoInsectocacador.setVisible(false);
                    JOptionPane.showMessageDialog(jDialogConsultaInsectocacadores, "REFERÊNCIA ATUALIZADOS COM SUCESSO !");

                } catch (SQLException ex) {
                    System.err.println("SQLException: " + ex.getMessage());
                }

            }

        }//else do campo vazio

    }
    
    
    
    
    /*  UPDATE  EQUIPAMENTOS*/
     private void UpdateDadosEquipamento(){
         String nome =  jTextFieldNomeEquipamento.getText();
         String descricao =  jTextAreaObservacaoNovoEquipamento.getText();         
         int contaLinhas = 0;
                  
         boolean verificarBD = false ;
         boolean existenaBD =  false;
         
         //VERIFICAMOS SE NOMEPARACOMPARAR == NOME
          if (nome.equals(nomeAVerficar)){
            //AQUI IGNONAMOS A  VERIFICAÇÃO DO NOME, NAO VAMOS A BD VER SE JA EXISTE
              verificarBD = false;
              
          }else if (!nome.equals(nomeAVerficar)){
              //VERIFICAMOS NA BD SE O NOME JA EXISTE
              verificarBD = true;
          }
         
         System.out.println("\n** ACTUALIZA EQUIPAMENTO SELECIONADO");
         System.out.println("NOME: " + nome);
         System.out.println("DESCRICAO: " + descricao);
         System.out.println("\nVERIFICAR BD: " + verificarBD);
        
         
         if (verificarBD == false) {
             //System.out.println("EXISTEnaBD -> " + existenaBD);
             //ESTAMOS A ATUALIZAR O CAMPO NA BD
             try {
                 Class.forName("org.apache.derby.jdbc.ClientDriver");
             } catch (ClassNotFoundException e) { //driver não encontrado
                 System.err.print("ClassNotFoundException: ");
                 System.err.println(e.getMessage());
                 System.out.println("O driver expecificado nao foi encontrado.");
             }

             try {
                 con = DriverManager.getConnection(url);
                 String nomeTabela = "EQUIPAMENTO";
                 String sql = "UPDATE " + nomeTabela + " SET NOME='" + nome + "'" + "," + " DESCRICAO='" + descricao + "' WHERE IDEQUIPAMENTO=" + idEquipamentoControloResultado;
                 PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                 st.executeUpdate();

                 st.close();
                 con.close();
             } catch (SQLException ex) {
                 System.err.println("SQLException: " + ex.getMessage());
             }

         }else if (verificarBD == true){
             //System.out.println("EXISTEnaBD -> " + existenaBD);
             //VAMOS A BD VERIFICAR SE O NOME JA EXISTE
               
             try {
                 con = DriverManager.getConnection(url);
                 String nomeTabela = "EQUIPAMENTO";
                 String sql = "SELECT * FROM " + nomeTabela + " WHERE NOME='" + nome + "'"; //TEM DE SER O NOME A VERIFICAR PORQUE É O NOVO QUE INSERIMOS
                 PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                 ResultSet rs = st.executeQuery();
                 while (rs.next()) {
                   contaLinhas++;                                         
                 }
                 
                 //VERIFICAR SE TEMOS TEMOS
                 System.out.println("SELECT * FROM " + nomeTabela + " WHERE NOME='" + nome + "'");
                 System.out.println("CONTA LINHAS : " + contaLinhas );
                 
                 if(contaLinhas != 0){
                     existenaBD = true;
                     System.out.println("EXISTE NA BD");
                 } else{
                     System.out.println("NÃO EXISTE NA BD");
                     existenaBD = false;
                 }
                 
                 st.close();
                 con.close();
             } catch (SQLException ex) {                             
                 System.err.println("SQLException: " + ex.getMessage());
             }
             
             System.out.println("EXISTEnaBD -> " + existenaBD);
             
             //PROCEDIMENTO KE VAMOS REALIZAR
             if (existenaBD == true){
                 //MENSAGEM A DIZER QUE JA EXISTE NA BD
                 JOptionPane.showMessageDialog(jDialogNovoEquipamento, "Equipamento ja existe na BD !");
                 System.out.println("MENSAGEM A DIZER K JA EXISTE");
             }else if (existenaBD == false){
                 //PERGUNTAR SE QUEREMOS ADICIONAR COMO NOVO
                 int resultExisteNaBD = JOptionPane.showConfirmDialog(jDialogNovoEquipamento, "Deseja Adicionar este Como um Novo Eqwuipamento?\n"
                         + "YES -> Adicionar \nNO -> Fechar Janela", null, JOptionPane.YES_NO_OPTION);

                 if (resultExisteNaBD == JOptionPane.YES_OPTION) {
                     //VAMOS ADICIONAR NOVO EQUIPAMENTO
                     InserirNovoEquipamento();
                     LimpaCamposNovoEquipamento();
                 }else if (resultExisteNaBD == JOptionPane.NO_OPTION){
                     //FECHAR JANELA E LIMPAR CAMPOS
                     jDialogNovoEquipamento.setVisible(false);
                     LimpaCamposNovoEquipamento();                 
                 }
                 System.out.println("MENSAGEM A PERGUNTAR SE O QUEREMOS ADICIONAR");

             }//fecha IF da existenaBD = false
             
         }//fecha IF da verificarBD = FALSE

         
         
         
         
     }
    
     
      /*  UPDATE  CONTROLO DE RESULTADOS*/
     private void ActualizaMedidaCorrectiva(){
               
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        String data = (String) sdf.format(jDateChooserMedidaCorrectiva.getDate());
        
        Date dataAtual = new Date();    
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");  
        String data = formato.format(dataAtual);
                
       //LER DADOS DA JANELA         
        String correctiva = "S";
        String observacaoMedidaCorrectiva = jTextAreaObservacaoMedidaCorrectiva.getText();
        CONTA_LINHAS_MEDIDASCORRECTIVAS = 0;

        //GUARDAMOS MEDIDA CORRECTIVA NA BD     
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
            String sql = "UPDATE "+ nomeTabela + " SET DATAMEDIDACORRECTIVA='" + data +"'"+","+" CORRECTIVA='"+correctiva+"'," + " OBSERVACAOMEDIDACORRECTIVA='"+observacaoMedidaCorrectiva+"' WHERE IDNAOCONF= " + idMedidaCorrectivaUpdate;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();

            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    
        
        //VERIFICAR SE AINDA EXISTEM NAO CONFORMIDADES PENDENTES        
        
        if(CONTA_LINHAS_NAOCONFORMIDADES == 1){
             
            //ALTERAR ESTADO PARA NAO APARECER MENSGEM A DIZER KE ENTRADA NAO TEM NENHUMA NAO CONFORMIDADE
            mediaCorrectivaUltima = true;
            
             int resultAlterarEstado = JOptionPane.showConfirmDialog(jDialogMedidasCorrectiva, "Ultima Medida Correctica atualizada com Sucesso.\n"
                     + "Deseja Actualizar o Resulta do Controlo de Resultado ?", null, JOptionPane.YES_NO_OPTION);

             if (resultAlterarEstado == JOptionPane.YES_OPTION) {
                 //AQUI ALTERAMOS O ESTADO DO CONTROLO RESULTADO
                 String resultado = "C";
                 jDialogMedidasCorrectiva.setVisible(false); 
                 
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
                     String sql = "UPDATE " + nomeTabela + " SET RESULTADO='" + resultado +"' WHERE IDCONTRESULTADOS= " + idControloResultadosParaPesquisarNConformidade;
                     PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                     st.executeUpdate();

                     st.close();
                     con.close();
                 } catch (SQLException ex) {
                     System.err.println("SQLException: " + ex.getMessage());
                 }
            
                 //FECHAR JANELA DE CONSULTA NC
                 jDialogConsultaNaoConformidades.setVisible(false);
                 //ATUALIZAR TABELA -  ver qual o valor seleccionado na combo box
                 String comboPesquisa = jComboBoxSeccao.getSelectedItem().toString();
                 String nomeColuna = "";

                 if (comboPesquisa.equals("ENTRADA")) {
                     LimpaTabelaControloResultados();
                     nomeColuna = "IDENTRADA";
                     LerBDControloResultados(nomeColuna);
                     //jButtonVerNaoConformidadeEntrada.setVisible(true);

                 } else if (comboPesquisa.equals("EQUIPAMENTO")) {
                     LimpaTabelaControloResultados();
                     nomeColuna = "IDEQUIPAMENTO";
                     LerBDControloResultados(nomeColuna);
                     //jButtonVerNaoConformidadeEntrada.setVisible(false);

                 } else if (comboPesquisa.equals("INSECTOCACADORES")) {
                     LimpaTabelaControloResultados();
                     nomeColuna = "IDINSECTOCACADORES";
                     LerBDControloResultados(nomeColuna);
                     //jButtonVerNaoConformidadeEntrada.setVisible(false);

                 } else if (comboPesquisa.equals("OUTROS")) {
                     LimpaTabelaControloResultados();
                     nomeColuna = "OUTROS";
                     LerBDControloResultados(nomeColuna);
                     //jButtonVerNaoConformidadeEntrada.setVisible(false);

                 }
                 
                 
                System.out.println("\n\n**** MEDIDA CORRECTIVA -> ATUALIZAR RESULTADO");
                System.out.println("RESULTADO : " + resultado);
                System.out.println("ID CONTROLO : " + idControloResultadosParaPesquisarNConformidade);
            }

         }
        
        
        
         /*  DADOS NA CONSOLA -> GRAVAR MEDIDA CORRECTIVA   */
        System.out.println("\n*** MEDIDAS CORRECTIVAS -> UPDATE ");
        System.out.println("ID NAO CONFORMIDADE: " + idMedidaCorrectivaUpdate);
        System.out.println("DATA MEDIDA CORRECTIVA: " + data);
        System.out.println("CORRECTIVA: " + correctiva);
        System.out.println("OBSERVAÇÃO MED. CORREC: " + observacaoMedidaCorrectiva);
        System.out.println("ID CONT. RESULTADO : " + idControloResultadosParaPesquisarNConformidade);
        
         
        
        
    }
    
       
    /*  ATUALIZAR FORNECEDOR  */
    private void UpdateDadosFornecedor(){
         
        String nome = jTextFieldNomeFornecedor.getText();
        String morada = jTextFieldMoradaFornecedor.getText();
        String codPostal = jTextFieldCodPostalFornecedor.getText();
        String localidade = jTextFieldLocalidadeFornecedor.getText();
        String contacto = jTextFieldContactoFornecedor.getText();
        String email = jTextFieldEmailFornecedor.getText();
        String nif = jTextFieldNIFFornecedor.getText();
        String tipoProduto = jTextFieldTipoProdutoFornecedor.getText();
        String estadoFornecedor = jComboBoxEstadoFornecedor.getSelectedItem().toString();
        boolean estado ;
        
        if(estadoFornecedor.equals("Ativo")){
            estado = true;
        }else{
            estado = false;
        }
        
        
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "FORNECEDOR";
            String sql = "UPDATE " + nomeTabela + " SET NOME='" + nome + "', MORADA='" + morada + "', COD_POSTAL='"+codPostal+"',"+
                                                        " LOCALIDADE='"+localidade+"', CONTATO="+contacto+", EMAIL='"+ email +"',"+
                                                        " NIF="+nif+ ", TIPOPRODUTO='"+tipoProduto+"', ESTADO='"+ estado + "'"  + 
                                                        " WHERE IDFORNECEDOR=" + idFornecedorPesquisar;

            JOptionPane.showMessageDialog(jDialogNovoFornecedor, "Fornecedor Atualizado Com Sucesso !");
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        System.out.println("\n** UPDATE FORNECEDOR -> DEPOIS DE EDITAR DADOS");
        System.out.println("FORNECEDOR UPDATE: " + idFornecedorPesquisar);
        System.out.println("NOME: " + nome);
        System.out.println("MORADA: " + morada);
        System.out.println("COD_POSTAL: " + codPostal );
        System.out.println("LOCALIDADE: " + localidade );
        System.out.println("CONTATO: " + contacto);
        System.out.println("EMAIL: " + email);
        System.out.println("NIF: " + nif);
        System.out.println("TIPOPRODUTO: " +tipoProduto );
        System.out.println("ESTADO: " + estado);                
        
    }
    
    private void ActualizaEstadoFornecedor(){
    
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "FORNECEDOR";
            String sql = "UPDATE " + nomeTabela + " SET ESTADO='" + estadoFornecedor  +"' WHERE IDFORNECEDOR=" + idFornecedorPesquisar;

            System.out.println("ESTADO FORNECEDOR ALTERADO COM SUCESSO : " + EstadoFornecedor);
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        
        
    }
    
    
        /*  ATUAIZAR MATERIA PRIMA */
    private void ActualizaEstadoMateriaPrima(){
    
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) { //driver não encontrado
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        try {
            con = DriverManager.getConnection(url);
            String nomeTabela = "MATERIA_PRIMA";
           
            String sql = "UPDATE " + nomeTabela + " SET ESTADO='" + estadoMateriaPrima  +"' WHERE IDMATERIAPRIMA=" + idMateriaPrimaPesquisar;

            System.out.println("ESTADO MATERIA-PRIMA ALTERADO COM SUCESSO : " + estadoMateriaPrima);
            System.out.println("id materiaa -> " + idMateriaPrimaPesquisar);
            
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            st.executeUpdate();            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    
    }
    
    
       /*  CALCULOS ENTRADAS    */
    
   private void CalculaQuantoFaltaDevolver(){
        //VAMOS A TABELA DEVOLUÇOES E VEMOS O VALOR QUE FALTA
        
         float quantidadeDevolvidadaAteAgora  = 0.0f; //para ir buscar o valor a coluna e somar a de baixo
         float quantidadeDevolvidadaAteAgora_TOTAL = 0.0f; //somatorio das quantidades das devoluçoes ja efectuadas
         float quantidadeDevolvidadaAteAgora_FINAL = 0.0f; // esta e a soma das 2 -> _total + quantidadeQueEstamosAdevolver
        
         devolucaoNumero = 0; //inciar
         
        try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }

            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "DEVOLUCOES";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE IDENTRADA=" + idEntradaSeleccionada ;
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    //SOMAR VALOR DAS DEVOLUCOES JA EFECTUADAS
                    quantidadeDevolvidadaAteAgora = rs.getFloat("QUANTIDADEDEVOLVIDA");
                    
                    quantidadeDevolvidadaAteAgora_TOTAL = quantidadeDevolvidadaAteAgora_TOTAL + quantidadeDevolvidadaAteAgora;
                    
                    devolucaoNumero++;
                    
                    //PERCURRER TODOS OS CAMPOS DA BD
                    System.out.println("quantidadeDevolvidaAteAgora : " + quantidadeDevolvidadaAteAgora);
                    System.out.println("devolucoesNumero : " + devolucaoNumero);
                    
                    
                }
                System.out.println("\nquantidadeDevolvidaAteAgora_TOTAL : " + quantidadeDevolvidadaAteAgora_TOTAL );
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
    
  
         jTextFieldQuantidadeADevolverDevolucao.setText(String.valueOf(quantidadeADevolverSelecionadaFixa -quantidadeDevolvidadaAteAgora_TOTAL));
         
         System.out.println("ECRÃ ->  " + quantidadeADevolverSelecionadaFixa + "-" + quantidadeDevolvidadaAteAgora_TOTAL );
    }
    
   private void CalculaQuatidadeLoteTotalEntrada(){
        
       int idEntrada = 0;
       int quantidade = 0;
       int adevolver = 0;
       float quantidadeTotal = 0;
       String loteOrigem = "";
       
       float quantidadeDevolvida = 0;
       float quantidadeDevolvidaTotal = 0;
       
       // TABLA ENTRADAS BUSCAS A QUANTIDADE DE MT E A QUANTIDADE A DEVOLVER(actualizada)
       try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException e) { //driver não encontrado
                System.err.print("ClassNotFoundException: ");
                System.err.println(e.getMessage());
                System.out.println("O driver expecificado nao foi encontrado.");
            }

            try {
                con = DriverManager.getConnection(url);
                String nomeTabela = "ENTRADA";
                String sql = "SELECT * FROM " + nomeTabela + " WHERE LOTEORIGEM= '" + LoteOriginalSeleccionado + "'";
                PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    idEntrada = rs.getInt("IDENTRADA");
                    quantidade = rs.getInt("QUANTIDADETOTALDISPONIVEL");
                    adevolver = rs.getInt("ADEVOLVER");
                  //  loteOrigem = rs.getString("LOTEORIGEM");
                }                
            
                        //PERCORERR A TABELA DEVOLUÇÕES PARA SABERMOS A QUANTIDADE DE MT JA DEVOLVIDA
                        Connection con2 = DriverManager.getConnection(url);
                        String nomeTabela2 = "DEVOLUCOES";
                        String sql2 = "SELECT * FROM " + nomeTabela2 + " WHERE IDENTRADA= " + idEntrada ;
                        PreparedStatement st2 = (PreparedStatement) con2.prepareStatement(sql2);
                        ResultSet rs2 = st2.executeQuery();
                        while(rs2.next()){
                            quantidadeDevolvida = rs2.getFloat("QUANTIDADEDEVOLVIDA");
                            System.out.println("QUANTIDADE DEVOLVIDA : " + quantidadeDevolvida);
                            quantidadeDevolvidaTotal = quantidadeDevolvidaTotal + quantidadeDevolvida;
                            
                        }
                        System.out.println("QUANTIDADE DEVOLVIDA TOTAL: " + quantidadeDevolvidaTotal);
                        st2.close(); 
                        con2.close();
                
                
                st.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
            }
            
            quantidadeTotal = quantidade + adevolver + quantidadeDevolvidaTotal;
           
//            
            JOptionPane.showMessageDialog(jDialogConsultaEntradas, "CALCULO :\n"
                    + "Qtd Disponivel : " + quantidade +"\n"
                    + "Qtd a Devolver : " + adevolver + "\n"
                    + "Qtd já Devolvida : " + quantidadeDevolvidaTotal + "\n\n"
                    + "Quantidade que deu Entrada (S/Devoluções) :\n"
                    + "--> " + quantidadeTotal);
//            
            
            System.out.println("IDENTRADA : " + idEntrada);
            System.out.println("QUANTIDADE : " +quantidade);
            System.out.println("ADEVOLVER : " + adevolver);
            System.out.println("QUANTIDADE TOTAL : " + quantidadeTotal);
           // System.out.println("LOTE ORIG : " + loteOrigem);
             System.out.println("QUANTIDADE TOTAL DE MATERIA PRIMA :  " + quantidadeTotal);
   }
    
    
    
     /* A PARTE  */
    public static boolean validarEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
                System.out.println("\nEmail Valido");
            } else {
                System.out.println("\nEmail Invalido");
            }
        }
        return isEmailIdValid;
    }

    public static boolean validarNif(String number) {
        // TAMANHO OBRIGATORIO TEM DE SER 9
        if (number.length() != 9) {
            return false;

        } //else if (number.equals("123456789")) {
            //return false;
        //}
       
        
        // TEM DE COMERÇAR COM 1, 2, 5, 6, 7, 8 ou 9
        if (!"1256789".contains(number.charAt(0) + "")) {
            return false;
        }

        // CICLO PARA CONSTRUIR O ARRAY DE INTEIROS
        int[] numbers = new int[9];
        for (int i = 0; i < 9; i++) {
            numbers[i] = Integer.parseInt(number.charAt(i) + "");
        }

        //FAZER AS CONTAS
        float resultado = 0.0f;
        for (int i = 0, j = 9; i < 9; i++, j--) {
            resultado += (j * numbers[i]);
            System.out.println(resultado + " = " + j + " x " + numbers[i]);
        }

        if ((resultado % 11) != 0.0f) {
            return false;
        } else {
            return true;
        }

        /*
         *  http://www.dgaiec.min-financas.pt/pt/informacao_aduaneira/eori/
         * 
         * http://pt.wikipedia.org/wiki/N%C3%BAmero_de_identifica%C3%A7%C3%A3o_fiscal
         * http://raul252.blogspot.pt/2013/05/que-lo-sepas-validar-nif-nif-cif.html
         * http://amatellanes.wordpress.com/2013/07/30/java-validar-dni-nie-o-cif-en-java/
         * 
         */


    }

    private void GeraLote(){
        Date dataAtual = new Date();    
        DateFormat formato = new SimpleDateFormat("yyyyMMddHHmmss");  
        String formatodData = formato.format(dataAtual);
        System.out.println("\n " + formatodData );
        
        String LoteComExtencao = "LT"+formatodData;
        
        geraLote = LoteComExtencao;
        
        //IR A BASE DE DADOS VERIFICAR SE JA EXISTE ALGUM LOTE COM ESTA COMBINAÇÃO
        
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
            System.out.println("O driver expecificado nao foi encontrado.");
        }

        
        try {

            con = DriverManager.getConnection(url);
            String nomeTabela = "ENTRADA";
            String sql = "SELECT * FROM " + nomeTabela + " WHERE LOTE="+geraLote;
            PreparedStatement st = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            System.out.println("LOTE JA EXISTE");
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            
            System.out.println("LOTE NAO EXISTE");
        }

        
        
        
    }
   
    //FUNCAO PARA ARREDONDAR PARA DIAS CASAS DECIMAIS 
    public static float Round(float Rval, int Rpl) {
        float p = (float) Math.pow(10, Rpl);
        Rval = Rval * p;
        float tmp = Math.round(Rval);
        return (float) tmp / p;
    }
    
    
    
    /*  LIMPAR INSECTOCAÇADOR */
    
    private void LimpaCamposNovoInsectocador(){
        jTextFieldLocalInsecto.setText("");
        jTextFieldNomeInsecto.setText("");
        jTextFieldReferenciaInsecto.setText("");
    }
    
    private void LimpaTabelaConsultaInsectocacadores(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultaInsecto.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaConsultaLimpezas(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultaLimpezas.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaConsultaControloResultados(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultarControlos.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaNaoConformidades(){
        DefaultTableModel model = (DefaultTableModel) jTableNaoConformidades.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaCamposNovaNaoConformidade() {
        jTextAreaOcorrenciaNaoConformidade.setText("");
        jTextAreaMedidaCorrctivaNaoConformidade.setText("");
        jComboBoxNaoConformidadeFuncionarioResponsav.setSelectedIndex(0);
    }
    
    /* LIMPAR  EQUIPAMENTOS */
    private void LimpaCamposNovoEquipamento(){
        jTextFieldNomeEquipamento.setText("");
        jTextAreaObservacaoNovoEquipamento.setText("");
    }
    
    private void LimpaTabelaConsultaEquipamentos(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultaEquipamentos.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaConsultaManutencaoEquipamentos(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultaManutencaoEquipamentos.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    /* LIMPAR  CONTROLO DE RESULTADOS */
   
    private void LimpaCamposNovoControloResultado(){
        //ESCONDER PANEL "OUTRA OPÇAO NOME"
        jTextFieldOutraOpcao.setEnabled(false);

        jLabelFuncionarioLogin2.setText("" + NomeFuncionarioLogin);

        jComboBoxOpcao.setVisible(true);
        jComboBoxOpcao.setSelectedIndex(0);

        jComboBoxTipo.setSelectedIndex(0);

        jComboBoxResultado.setSelectedIndex(0);

    }
    
    private void LimpaTabelaConsultaMedidasCorrectivas(){
      DefaultTableModel model = (DefaultTableModel) jTableConsultaMedidasCorrectivas.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaControloResultados(){
         DefaultTableModel model = (DefaultTableModel) jTableConsultarControlos.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaConsultaHistorico(){
        DefaultTableModel model = (DefaultTableModel) jTableVerHistorico.getModel();
        int linhas = model.getRowCount();
        
        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
   
    
     /* LIMPAR */
    private void LimpaNovaMateriaPrima() {
        jTextFieldNomeMateriaPrima.setText("");
        jTextAreaDescricaoMateriaPrima.setText("");
        jTextFieldUnidadeMateriaPrima.setText("");
    }

    private void LimpaNovoFornecedor() {
        jTextFieldNomeFornecedor.setText("");
        jTextFieldMoradaFornecedor.setText("");
        jTextFieldCodPostalFornecedor.setText("");
        jTextFieldLocalidadeFornecedor.setText("");
        jTextFieldContactoFornecedor.setText("");
        jTextFieldEmailFornecedor.setText("");
        jTextFieldNIFFornecedor.setText("");
        jTextFieldTipoProdutoFornecedor.setText("");
    }

    private void LimpaNovaDevolucao() {
        jTextFieldQuantidadeDevolucao.setText("0.0");
        jTextAreaObservacoesDevolucao.setText("");
    }
    
    private void LimpaNovaEntrada(){
        jTextFieldLoteEntrada.setText("");
        jTextFieldQuantidadeEntrada.setText("");
        jTextFieldQuantidadeADevolver.setText("");
    }

    private void LimpaTabelaEntradas() {
        DefaultTableModel model = (DefaultTableModel) jTableConsultaEntradas.getModel();
        int linhas = model.getRowCount();

        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }

    private void LimpaTabelaConsultaDevolucoes() {
        DefaultTableModel model = (DefaultTableModel) jTableConsultaDevolucoes.getModel();
        int linhas = model.getRowCount();

        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaFornecedores(){
    DefaultTableModel model = (DefaultTableModel) jTableConsultaFornecedor.getModel();
        int linhas = model.getRowCount();

        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaMateriaPrimas(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultaMateriasPrimas.getModel();
        int linhas = model.getRowCount();

        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
    
    private void LimpaTabelaConsultaFornecedores(){
        DefaultTableModel model = (DefaultTableModel) jTableConsultaFornecedor.getModel();
        int linhas = model.getRowCount();

        for (int i = 0; i < linhas; i++) {
            model.removeRow(0);
        }
    }
     
    /*   FUNÇAO PARA LER OS CAMPO DE UMA TABELAS */
    private int selectId(String tab, String col, String cam, String colId){
        int resultId = 0;
        String tabela = tab;
        String coluna = col;
        String campo = cam;
        String colunaId = colId;
        
        try {
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JT01;
    private javax.swing.JTextField JT02;
    private javax.swing.JTextField JTRES;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonActualizarInsecto;
    private javax.swing.JButton jButtonAddMedidaCorrectiva;
    private javax.swing.JButton jButtonAddNovaNaoConformidade;
    private javax.swing.JButton jButtonAddNovoEquipa;
    private javax.swing.JButton jButtonAddNovoInsecto;
    private javax.swing.JButton jButtonAlterarEstadoEquipamento;
    private javax.swing.JButton jButtonAlterarEstadoFornecedor;
    private javax.swing.JButton jButtonAlterarEstadoInsectocacadores;
    private javax.swing.JButton jButtonAlterarEstadoMP;
    private javax.swing.JButton jButtonAlterarReferencia;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCancelarMedidaCorrectiva;
    private javax.swing.JButton jButtonCancelarNaoConformidade;
    private javax.swing.JButton jButtonConsDevoluções;
    private javax.swing.JButton jButtonConsEntrada;
    private javax.swing.JButton jButtonConsEquipamento;
    private javax.swing.JButton jButtonConsuMateriaPrima;
    private javax.swing.JButton jButtonConsultaDevolucoes;
    private javax.swing.JButton jButtonConsultaDevolucoesSair;
    private javax.swing.JButton jButtonConsultaEntradas;
    private javax.swing.JButton jButtonConsultaEntradasSair;
    private javax.swing.JButton jButtonConsultaEquipamentos;
    private javax.swing.JButton jButtonConsultaInsecto;
    private javax.swing.JButton jButtonConsultaManutencao;
    private javax.swing.JButton jButtonConsultaMateriasPrimas;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonConsultarFornecedores;
    private javax.swing.JButton jButtonConsultarLimpezas;
    private javax.swing.JButton jButtonControloResultados;
    private javax.swing.JButton jButtonControloResultadosInsectoca;
    private javax.swing.JButton jButtonControlos1;
    private javax.swing.JButton jButtonCriaNovoControloResultados;
    private javax.swing.JButton jButtonDesvloquearPanel;
    private javax.swing.JButton jButtonDevolucao;
    private javax.swing.JButton jButtonEditarEquipamento;
    private javax.swing.JButton jButtonEditarFornecedor;
    private javax.swing.JButton jButtonEditarInsectoca;
    private javax.swing.JButton jButtonEditarMP;
    private javax.swing.JButton jButtonEntrar;
    private javax.swing.JButton jButtonFecharConsultaControlosResultados;
    private javax.swing.JButton jButtonFecharConsultaFornecedores;
    private javax.swing.JButton jButtonFornecedoresInativos;
    private javax.swing.JButton jButtonGravarManutencaoEquipametno;
    private javax.swing.JButton jButtonGravarMedidaCorrectiva;
    private javax.swing.JButton jButtonGravarNaoConformidade;
    private javax.swing.JButton jButtonGuardaDevolucao;
    private javax.swing.JButton jButtonGuardarEquipametno;
    private javax.swing.JButton jButtonGuardarInsecto;
    private javax.swing.JButton jButtonGuardarLimpeza;
    private javax.swing.JButton jButtonGuardarMateriaPrima;
    private javax.swing.JButton jButtonGuardarNovaEntrada;
    private javax.swing.JButton jButtonGuardarNovaRef;
    private javax.swing.JButton jButtonGuardarNovoFornecedor;
    private javax.swing.JButton jButtonInserir;
    private javax.swing.JButton jButtonInserirNovo;
    private javax.swing.JButton jButtonManutencaoConsultaEquipamentos;
    private javax.swing.JButton jButtonNovaEntrada;
    private javax.swing.JButton jButtonNovaLimpeza;
    private javax.swing.JButton jButtonNovaLimpezaConsultaLimpezas;
    private javax.swing.JButton jButtonNovaMateriaPrima;
    private javax.swing.JButton jButtonNovoControlo1;
    private javax.swing.JButton jButtonNovoEquipamento;
    private javax.swing.JButton jButtonNovoForncedor;
    private javax.swing.JButton jButtonNovoInsecto;
    private javax.swing.JButton jButtonNvContrPragas;
    private javax.swing.JButton jButtonNvContrPragas1;
    private javax.swing.JButton jButtonNvContrResultados;
    private javax.swing.JButton jButtonNvContrResultados1;
    private javax.swing.JButton jButtonNvEntrada;
    private javax.swing.JButton jButtonNvEquipamento;
    private javax.swing.JButton jButtonNvFornecedor;
    private javax.swing.JButton jButtonNvFornecedor1;
    private javax.swing.JButton jButtonNvMateriaPrima;
    private javax.swing.JButton jButtonSairConsultaEquipamentos;
    private javax.swing.JButton jButtonSairConsultaInsecto;
    private javax.swing.JButton jButtonSairConsultaLimpezas;
    private javax.swing.JButton jButtonSairConsultaMateriaPrima;
    private javax.swing.JButton jButtonSairDevolucao;
    private javax.swing.JButton jButtonSairHistorico;
    private javax.swing.JButton jButtonSairInsecto;
    private javax.swing.JButton jButtonSairLimpeza;
    private javax.swing.JButton jButtonSairManutencaoEquipamento;
    private javax.swing.JButton jButtonSairMateriaPrima;
    private javax.swing.JButton jButtonSairNovaEntrada;
    private javax.swing.JButton jButtonSairNovoEquipamento;
    private javax.swing.JButton jButtonSairNovoFornecedor;
    private javax.swing.JButton jButtonSeleccionarNPF;
    private javax.swing.JButton jButtonUpdateFornecedor;
    private javax.swing.JButton jButtonVerControloResultados;
    private javax.swing.JButton jButtonVerDatas;
    private javax.swing.JButton jButtonVerDevolucoes;
    private javax.swing.JButton jButtonVerFichaDetalhadaConsultaDevolucoes;
    private javax.swing.JButton jButtonVerHistoricoControlo;
    private javax.swing.JButton jButtonVerInativasEquipamentos;
    private javax.swing.JButton jButtonVerInativosMP;
    private javax.swing.JButton jButtonVerInsectocacadoresInativos;
    private javax.swing.JButton jButtonVerLoteTotal;
    private javax.swing.JButton jButtonVerNaoConformidade;
    private javax.swing.JButton jButtonVerNaoConformidadeEntrada;
    private javax.swing.JButton jButtonVerTodasAsDevolucoes;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JButton jButtonVoltarMedidasCorrectivas;
    private javax.swing.JComboBox jComboBoxCaraOrgonolepticasEntradas;
    private javax.swing.JComboBox jComboBoxDataValidadeEntrada;
    private javax.swing.JComboBox jComboBoxEmbalagemEntradas;
    private javax.swing.JComboBox jComboBoxEstadoFornecedor;
    private javax.swing.JComboBox jComboBoxFornecedorDevolucoes;
    private javax.swing.JComboBox jComboBoxFornecedorEntrada;
    private javax.swing.JComboBox jComboBoxMateriaPrimaDevolucoes;
    private javax.swing.JComboBox jComboBoxMateriaPrimaEntrada;
    private javax.swing.JComboBox jComboBoxNaoConformidadeFuncionarioResponsav;
    private javax.swing.JComboBox jComboBoxNomeLogin;
    private javax.swing.JComboBox jComboBoxOpcao;
    private javax.swing.JComboBox jComboBoxResultado;
    private javax.swing.JComboBox jComboBoxSeccao;
    private javax.swing.JComboBox jComboBoxTemperaturaEntrada;
    private javax.swing.JComboBox jComboBoxTipo;
    private com.toedter.calendar.JDateChooser jDateChooserDataDevolucao;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialogAlterarReferencia;
    private javax.swing.JDialog jDialogConsultaDevolucoes;
    private javax.swing.JDialog jDialogConsultaEntradas;
    private javax.swing.JDialog jDialogConsultaEquipamentos;
    private javax.swing.JDialog jDialogConsultaFornecedores;
    private javax.swing.JDialog jDialogConsultaInsectocacadores;
    private javax.swing.JDialog jDialogConsultaLimpezas;
    private javax.swing.JDialog jDialogConsultaManutencaoEquipamentos;
    private javax.swing.JDialog jDialogConsultaMateriasPrimas;
    private javax.swing.JDialog jDialogConsultaNaoConformidades;
    private javax.swing.JDialog jDialogConsultarControlos;
    private javax.swing.JDialog jDialogConsultarMedidasCorrectivas;
    private javax.swing.JDialog jDialogDevolucoes;
    private javax.swing.JDialog jDialogJanelaPrincipal;
    private javax.swing.JDialog jDialogMedidasCorrectiva;
    private javax.swing.JDialog jDialogMenuControloResultados;
    private javax.swing.JDialog jDialogMenuEntradas;
    private javax.swing.JDialog jDialogMenuEquipamentos;
    private javax.swing.JDialog jDialogMenuInsectocacadores;
    private javax.swing.JDialog jDialogNaoConformidades;
    private javax.swing.JDialog jDialogNovaEntrada;
    private javax.swing.JDialog jDialogNovaLimpeza;
    private javax.swing.JDialog jDialogNovaManutencaoEquipamento;
    private javax.swing.JDialog jDialogNovaMateriaPrima;
    private javax.swing.JDialog jDialogNovoControloResultados;
    private javax.swing.JDialog jDialogNovoEquipamento;
    private javax.swing.JDialog jDialogNovoFornecedor;
    private javax.swing.JDialog jDialogNovoInsectocacador;
    private javax.swing.JDialog jDialogVerHistoricoControlo;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAntigaReferencia;
    private javax.swing.JLabel jLabelBoasVindas;
    private javax.swing.JLabel jLabelCodPostalFornecedor;
    private javax.swing.JLabel jLabelConsultaControloPragas;
    private javax.swing.JLabel jLabelConsultaFornecedores;
    private javax.swing.JLabel jLabelConsultaMedidasCorrectivas;
    private javax.swing.JLabel jLabelConsultarEquipamentos;
    private javax.swing.JLabel jLabelConsultarMateriasPrimas;
    private javax.swing.JLabel jLabelContactoForncedor;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelDataDevolucao;
    private javax.swing.JLabel jLabelDataNovaLimpeza;
    private javax.swing.JLabel jLabelDescricao;
    private javax.swing.JLabel jLabelDescricaoMateriaPrima;
    private javax.swing.JLabel jLabelDescrição;
    private javax.swing.JLabel jLabelEmailFornecedor;
    private javax.swing.JLabel jLabelFichaTecnica;
    private javax.swing.JLabel jLabelFornecedorDevolucoes;
    private javax.swing.JLabel jLabelFuncionario;
    private javax.swing.JLabel jLabelFuncionarioLogin;
    private javax.swing.JLabel jLabelFuncionarioLogin2;
    private javax.swing.JLabel jLabelFuncionarioLogin4;
    private javax.swing.JLabel jLabelFuncionarioLogin5;
    private javax.swing.JLabel jLabelFuncionarioLogin6;
    private javax.swing.JLabel jLabelFuncionarioResponsavel;
    private javax.swing.JLabel jLabelFuncionarioResponsavel1;
    private javax.swing.JLabel jLabelFuncionarioResponsavel2;
    private javax.swing.JLabel jLabelFuncionariologin3;
    private javax.swing.JLabel jLabelLocal;
    private javax.swing.JLabel jLabelLocalidadeForncedor;
    private javax.swing.JLabel jLabelMateriaPrimaDevolucoes;
    private javax.swing.JLabel jLabelMedidaCorrectivaNaoConformidade;
    private javax.swing.JLabel jLabelMedidasCorrectivas;
    private javax.swing.JLabel jLabelMoradaFornecedor;
    private javax.swing.JLabel jLabelNIFFornecedor;
    private javax.swing.JLabel jLabelNaoConformidades;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNome1;
    private javax.swing.JLabel jLabelNomeFornecedor;
    private javax.swing.JLabel jLabelNomeLogin;
    private javax.swing.JLabel jLabelNomeMateriaPrima;
    private javax.swing.JLabel jLabelNomeOutraOpcao;
    private javax.swing.JLabel jLabelNovaReferencia;
    private javax.swing.JLabel jLabelNovoEquipametno;
    private javax.swing.JLabel jLabelNovoFornecedor;
    private javax.swing.JLabel jLabelObservacaoMedidasCorrectivas;
    private javax.swing.JLabel jLabelObservação;
    private javax.swing.JLabel jLabelOcorrecniaNaoConformidade;
    private javax.swing.JLabel jLabelOpcao;
    private javax.swing.JLabel jLabelPasswordLogin;
    private javax.swing.JLabel jLabelPesquisar;
    private javax.swing.JLabel jLabelPesquisarEquipamento;
    private javax.swing.JLabel jLabelPesquisarInsectocacador;
    private javax.swing.JLabel jLabelPesquisarMateriaPrima;
    private javax.swing.JLabel jLabelProblemaNC;
    private javax.swing.JLabel jLabelQuantidadeADevolver;
    private javax.swing.JLabel jLabelQuantidadeADevolverDevolucao;
    private javax.swing.JLabel jLabelQuantidadeDevolvida;
    private javax.swing.JLabel jLabelReferencia;
    private javax.swing.JLabel jLabelResultado;
    private javax.swing.JLabel jLabelSeccaoNC;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JLabel jLabelTipoProduto;
    private javax.swing.JLabel jLabelTituloConsultaEntradas;
    private javax.swing.JLabel jLabelUnidadeMateriaPrima;
    private javax.swing.JLabel jLabelUserLogin;
    private javax.swing.JLabel jLabelUserLogin2;
    private javax.swing.JLabel jLabelUserLogin3;
    private javax.swing.JLabel jLabelUserLogin4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemControloResultados;
    private javax.swing.JMenuItem jMenuItemEntradas;
    private javax.swing.JMenuItem jMenuItemEquipamento;
    private javax.swing.JMenuItem jMenuItemInsectocacadores;
    private javax.swing.JMenu jMenuMenus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanelADevolverNovaEntrada;
    private javax.swing.JPanel jPanelBotoesNovaEntrada;
    private javax.swing.JPanel jPanelConformidadesNovaEntrada;
    private javax.swing.JPanel jPanelConsultaControlos;
    private javax.swing.JPanel jPanelConsultaDevolucoes;
    private javax.swing.JPanel jPanelConsultaEntradas;
    private javax.swing.JPanel jPanelConsultaEquipamentos;
    private javax.swing.JPanel jPanelConsultaFornecedores;
    private javax.swing.JPanel jPanelConsultaHistorico;
    private javax.swing.JPanel jPanelConsultaInsecto;
    private javax.swing.JPanel jPanelConsultaLimpezas;
    private javax.swing.JPanel jPanelConsultaManutencaoEquipamentos;
    private javax.swing.JPanel jPanelConsultaNaoConformidades;
    private javax.swing.JPanel jPanelConsultar;
    private javax.swing.JPanel jPanelConsutaMateriasPrimas;
    private javax.swing.JPanel jPanelDevolucoes;
    private javax.swing.JPanel jPanelEquipamentoSeleccionado;
    private javax.swing.JPanel jPanelInformacaoAdicional;
    private javax.swing.JPanel jPanelInformacaoControloResultado;
    private javax.swing.JPanel jPanelInformacaoNaoConformidade;
    private javax.swing.JPanel jPanelInserirNovo;
    private javax.swing.JPanel jPanelJanela;
    private javax.swing.JPanel jPanelManutencaoEquipamento;
    private javax.swing.JPanel jPanelMedidasCorrectivas;
    private javax.swing.JPanel jPanelMenuEntradas;
    private javax.swing.JPanel jPanelMenuEquipamentos;
    private javax.swing.JPanel jPanelMenuInsectocadores;
    private javax.swing.JPanel jPanelMenus;
    private javax.swing.JPanel jPanelMostraDadosControloResultado;
    private javax.swing.JPanel jPanelNaoConformidades;
    private javax.swing.JPanel jPanelNovaEntrada;
    private javax.swing.JPanel jPanelNovaLimpeza;
    private javax.swing.JPanel jPanelNovaMateriaPrima;
    private javax.swing.JPanel jPanelNovoControloResultdos;
    private javax.swing.JPanel jPanelNovoEquipamento;
    private javax.swing.JPanel jPanelNovoFornecedor;
    private javax.swing.JPanel jPanelNovoInsectocacador;
    private javax.swing.JPanel jPanelOutraOpcao;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelRealizaLogin;
    private javax.swing.JPanel jPanelVerMensagemProblema;
    private javax.swing.JPasswordField jPasswordFieldLogin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPaneConsultInsecto;
    private javax.swing.JScrollPane jScrollPaneConsultaEquipamentos;
    private javax.swing.JScrollPane jScrollPaneConsultaLimpezas;
    private javax.swing.JScrollPane jScrollPaneConsultaManutencaoEquipamentos;
    private javax.swing.JScrollPane jScrollPaneConsultaMedidasCorrectivas;
    private javax.swing.JScrollPane jScrollPaneConsultarControlos;
    private javax.swing.JScrollPane jScrollPaneDescricao;
    private javax.swing.JScrollPane jScrollPaneDescricaoMateriaPrima;
    private javax.swing.JScrollPane jScrollPaneNaoConformidades;
    private javax.swing.JScrollPane jScrollPaneNovoEquipmanentoObservacao;
    private javax.swing.JScrollPane jScrollPaneObservacaManuetencaoEquipametno;
    private javax.swing.JScrollPane jScrollPaneObservacoesDevolucao;
    private javax.swing.JScrollPane jScrollPaneOcorrencia;
    private javax.swing.JScrollPane jScrollPanemedidaCorrectiva;
    private javax.swing.JTable jTableConsultaDevolucoes;
    private javax.swing.JTable jTableConsultaEntradas;
    private javax.swing.JTable jTableConsultaEquipamentos;
    private javax.swing.JTable jTableConsultaFornecedor;
    private javax.swing.JTable jTableConsultaInsecto;
    private javax.swing.JTable jTableConsultaLimpezas;
    private javax.swing.JTable jTableConsultaManutencaoEquipamentos;
    private javax.swing.JTable jTableConsultaMateriasPrimas;
    private javax.swing.JTable jTableConsultaMedidasCorrectivas;
    private javax.swing.JTable jTableConsultarControlos;
    private javax.swing.JTable jTableNaoConformidades;
    private javax.swing.JTable jTableVerHistorico;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextArea jTextAreaDescricaoMateriaPrima;
    private javax.swing.JTextArea jTextAreaDetalhesMC;
    private javax.swing.JTextArea jTextAreaMedidaCorrctivaNaoConformidade;
    private javax.swing.JTextArea jTextAreaMedidasCorrectivasMC;
    private javax.swing.JTextArea jTextAreaObservacaoManutencaoEquipameto;
    private javax.swing.JTextArea jTextAreaObservacaoMedidaCorrectiva;
    private javax.swing.JTextArea jTextAreaObservacaoNovoEquipamento;
    private javax.swing.JTextArea jTextAreaObservacoesDevolucao;
    private javax.swing.JTextArea jTextAreaOcorrenciaNaoConformidade;
    private javax.swing.JTextArea jTextAreaProblemaMC;
    private javax.swing.JTextArea jTextAreaProblemadoControlo;
    private javax.swing.JTextArea jTextAreaVerProblemaNC;
    private javax.swing.JTextField jTextFieldCodPostalFornecedor;
    private javax.swing.JTextField jTextFieldContactoFornecedor;
    private javax.swing.JTextField jTextFieldEmailFornecedor;
    private javax.swing.JTextField jTextFieldLocalInsecto;
    private javax.swing.JTextField jTextFieldLocalidadeFornecedor;
    private javax.swing.JTextField jTextFieldLoteEntrada;
    private javax.swing.JTextField jTextFieldMoradaFornecedor;
    private javax.swing.JTextField jTextFieldNIFFornecedor;
    private javax.swing.JTextField jTextFieldNomeEquipamento;
    private javax.swing.JTextField jTextFieldNomeEquipamentoSeleccionado;
    private javax.swing.JTextField jTextFieldNomeFornecedor;
    private javax.swing.JTextField jTextFieldNomeInsecto;
    private javax.swing.JTextField jTextFieldNomeMateriaPrima;
    private javax.swing.JTextField jTextFieldNovaEntradaUnidadeQuantidade;
    private javax.swing.JTextField jTextFieldNovaReferencia;
    private javax.swing.JTextField jTextFieldOutraOpcao;
    private javax.swing.JTextField jTextFieldPesquisaEquipamento;
    private javax.swing.JTextField jTextFieldPesquisaInsectocacador;
    private javax.swing.JTextField jTextFieldPesquisarEntrada;
    private javax.swing.JTextField jTextFieldPesquisarFornecedor;
    private javax.swing.JTextField jTextFieldPesquisarMateriaPrima;
    private javax.swing.JTextField jTextFieldQuantidadeADevolver;
    private javax.swing.JTextField jTextFieldQuantidadeADevolverDevolucao;
    private javax.swing.JTextField jTextFieldQuantidadeDevolucao;
    private javax.swing.JTextField jTextFieldQuantidadeEntrada;
    private javax.swing.JTextField jTextFieldReferenciaInsecto;
    private javax.swing.JTextField jTextFieldSeccaoMC;
    private javax.swing.JTextField jTextFieldTipoProdutoFornecedor;
    private javax.swing.JTextField jTextFieldUnidadeMateriaPrima;
    private javax.swing.JTextField jTextFieldUnidadeQuantidadeADevolver;
    private javax.swing.JTextField jTextFieldUploudNUC;
    private javax.swing.JTextField jTextFieldVerSeccaoDoCR;
    private javax.swing.JTextField jTextFieldVerSeccaoNC;
    // End of variables declaration//GEN-END:variables
}
