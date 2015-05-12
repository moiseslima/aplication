/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;


import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import util.conexao;

/**
 * @author moises
 */
public class visao extends javax.swing.JFrame {

    double Custos, Soma, PrecoUnitario, ImpostoFederal, ImpostoEstadual, IPI, ICMS, GanhoLivre, CustosGerais, Frete, PrecoVenda;
    String Material;
    String codigo;
    int linhaSelecionada;
    boolean precificacaoPendente,camposVazios, camposInvalidos;
    boolean campoVazio[] = new boolean[8];
    boolean campoInvalido[] = new boolean[8];
    /**
     * Creates new form visao
     */
    public visao() {
        initComponents();
         
        this.codigo = codigo;
        jButtonGravar.addKeyListener(new KeyAdapter(){
          public void keyPressed(KeyEvent e) {
              if(e.getKeyCode()==KeyEvent.VK_ENTER){
                  jButtonGravar.doClick();
              }
          }
      });
        jTable1.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent me) {
        JTable table =(JTable) me.getSource();
        Point p = me.getPoint();
        int row = table.rowAtPoint(p);
        if (me.getClickCount() == 2) {
            linhaSelecionada = row;
            jButtonAlterar.doClick();
        }
    }
});
        setExtendedState(MAXIMIZED_BOTH);
        TextFieldMaterial.grabFocus();
        if (TextFieldMaterial.getText().isEmpty()) {
            TextFieldMaterial.setText("0");
        }
        if (TextFieldPrecoUnitario.getText().isEmpty()) {
            TextFieldPrecoUnitario.setText("0");
        }
        if (TextFieldImpostoFederal.getText().isEmpty()) {
            TextFieldImpostoFederal.setText("0");
        }
        if (TextFieldImpostoEstadual.getText().isEmpty()) {
            TextFieldImpostoEstadual.setText("0");
        }
        if (TextFieldIPI.getText().isEmpty()) {
            TextFieldIPI.setText("0");
        }
        if (TextFieldICMS.getText().isEmpty()) {
            TextFieldICMS.setText("0");
        }

        if (TextFieldFrete.getText().isEmpty()) {
            TextFieldFrete.setText("0");
        }
        if (TextFieldGanhoLivre.getText().isEmpty()) {
            TextFieldGanhoLivre.setText("0");
        }
        if (TextFieldResultadoPrecoVenda.getText().isEmpty()) {
            TextFieldResultadoPrecoVenda.setText("0");
        }
        
        TextFieldMaterial.selectAll();
        this.carregarTabela();
        this.carregarTabela2();
        experiencia();
        CBCategoria.grabFocus();
    }
//Classe Adicionar

    
    
    public void atualizaResultados(){
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldMaterial.grabFocus();
    }
    
    public void experiencia(){

           CBCategoria.removeAllItems();

        conexao.connection().conecta();

        

        String comando = "select * from categoria";

        try {
            java.sql.ResultSet rs = conexao.connection().executeSQLQuery(comando);

            // Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda)    
            while (rs != null && rs.next()) {
               String CadastroCategoria = rs.getString("CadastroCategoria");
               CBCategoria.addItem(CadastroCategoria);
            }
        } catch (java.sql.SQLException e) {
            throw new java.lang.RuntimeException(e.getMessage());
        }
        
        
        conexao.connection().desconecta();
        
    }
    
    public void carregarTabela2() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTable2.getModel();
        int rowCount = dtm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
        conexao.connection().conecta();

        Vector<Vector> dados = new Vector<Vector>();

        String comando = "select * from categoria";

        try {
            java.sql.ResultSet rs = conexao.connection().executeSQLQuery(comando);

            // Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda)    
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String CadastroCategoria = rs.getString("CadastroCategoria");
                
                Vector registroAtual = new Vector();
                 registroAtual.add(codigo);
                registroAtual.add(CadastroCategoria);
                

                dados.add(registroAtual);
            }
        } catch (java.sql.SQLException e) {
            throw new java.lang.RuntimeException(e.getMessage());
        }

        conexao.connection().desconecta();

        
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
        }
        
    }
    public void carregarTabela() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTable1.getModel();
        int rowCount = dtm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
        conexao.connection().conecta();

        Vector<Vector> dados = new Vector<Vector>();

        String comando = "select * from producao";

        try {
            java.sql.ResultSet rs = conexao.connection().executeSQLQuery(comando);

            // Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda)    
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String Descricao = rs.getString("Descricao");
                String Preco = rs.getString("Preco");
                String ImpostoFederal = rs.getString("ImpostoFederal");
                String ImpostoEstadual = rs.getString("ImpostoEstadual");
                String IPI = rs.getString("IPI");
                String ICMS = rs.getString("ICMS");
                String Frete = rs.getString("Frete");
                String GanhoLivre = rs.getString("GanhoLivre");
                String CustosGerais = rs.getString("CustosGerais");
                String PrecoVenda = rs.getString("PrecoVenda");
                String Categoria = rs.getString("Categoria");

                Vector registroAtual = new Vector();
                registroAtual.add(codigo);
                registroAtual.add(Descricao);
                registroAtual.add(Preco);
                registroAtual.add(ImpostoFederal);
                registroAtual.add(ImpostoEstadual);
                registroAtual.add(IPI);
                registroAtual.add(ICMS);
                registroAtual.add(Frete);
                registroAtual.add(GanhoLivre);
                registroAtual.add(CustosGerais);
                registroAtual.add(PrecoVenda);
                registroAtual.add(Categoria);

                dados.add(registroAtual);
            }
        } catch (java.sql.SQLException e) {
            throw new java.lang.RuntimeException(e.getMessage());
        }

        conexao.connection().desconecta();

        
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
        }
        


        jTable1.revalidate();
    }
    
    public void LogicaSomas(){
        if(!validadorSoNumeros()){
            return;
        }
        
         if (TextFieldMaterial.getText().isEmpty()) {
            TextFieldMaterial.setText("0");
        }
        if (TextFieldPrecoUnitario.getText().isEmpty()) {
            TextFieldPrecoUnitario.setText("0");
        }
        if (TextFieldImpostoFederal.getText().isEmpty()) {
            TextFieldImpostoFederal.setText("0");
        }
        if (TextFieldImpostoEstadual.getText().isEmpty()) {
            TextFieldImpostoEstadual.setText("0");
        }
        if (TextFieldIPI.getText().isEmpty()) {
            TextFieldIPI.setText("0");
        }
        if (TextFieldICMS.getText().isEmpty()) {
            TextFieldICMS.setText("0");
        }

        if (TextFieldFrete.getText().isEmpty()) {
            TextFieldFrete.setText("0");
        }
        if (TextFieldGanhoLivre.getText().isEmpty()) {
            TextFieldGanhoLivre.setText("0");
        }
        if (TextFieldResultadoPrecoVenda.getText().isEmpty()) {
            TextFieldResultadoPrecoVenda.setText("0");
        }
        Material = (TextFieldMaterial.getText().replace(",", "."));
        PrecoUnitario = Double.parseDouble(TextFieldPrecoUnitario.getText().replace(",", "."));
        ImpostoEstadual = Double.parseDouble(TextFieldImpostoEstadual.getText().replace(",", "."));
        ImpostoFederal = Double.parseDouble(TextFieldImpostoFederal.getText().replace(",", "."));
        IPI = Double.parseDouble(TextFieldIPI.getText().replace(",", "."));
        ICMS = Double.parseDouble(TextFieldICMS.getText().replace(",", "."));
        GanhoLivre = Double.parseDouble(TextFieldGanhoLivre.getText().replace(",", "."));
        Frete = Double.parseDouble(TextFieldFrete.getText().replace(",", "."));
       
        
        // Variáveis Necessárias para fazer a soma dos campos e mostrar o total
        double ResultadoPrecoUnitario = Double.parseDouble(TextFieldPrecoUnitario.getText().replace(",", "."));
        //TextFieldPrecoUnitario.setText(String.valueOf(ResultadoPrecoUnitario));
        
        String ResultadoMaterial = (TextFieldMaterial.getText());
       // TextFieldMaterial.setText(String.valueOf(ResultadoMaterial));

        double ResultadoIPI = (IPI / 100) * ResultadoPrecoUnitario;
        TextFieldResultadoIPI.setText(String.valueOf(ResultadoIPI).replace(".", ","));

        double ResultadoFrete = (Frete / 100) * ResultadoPrecoUnitario;
        TextFieldResultadoFrete.setText(String.valueOf(ResultadoFrete).replace(".", ","));

        double ResultadoGanhoLivre = (GanhoLivre / 100) * ResultadoPrecoUnitario;
        TextFieldResultadoGanhoLivre.setText(String.valueOf(ResultadoGanhoLivre).replace(".", ","));

        double ResultadoIF = (ImpostoFederal / 100) * ResultadoPrecoUnitario;
        TextFieldResultadoIF.setText(String.valueOf(ResultadoIF).replace(".", ","));

        double ResultadoICMS = (ICMS / 100) * ResultadoPrecoUnitario;
        TextFieldResultadoICMS.setText(String.valueOf(ResultadoICMS).replace(".", ","));

        double ResultadoIE = (ImpostoEstadual / 100) * ResultadoPrecoUnitario;
        TextFieldResultadoIE.setText(String.valueOf(ResultadoIE).replace(".", ","));

        Custos = ResultadoPrecoUnitario + ResultadoICMS + ResultadoIF + ResultadoIPI + ResultadoIE + ResultadoFrete;
        PrecoVenda = Custos + ResultadoGanhoLivre;

       /* TextFieldResultadoPrecoVenda.setText("");
        TextFieldResultadoCustosGerais.setText("");
        TextFieldResultadoPrecoProduto.setText("");
        TextFieldResultadoMaterial.setText("");
        */
        TextFieldResultadoPrecoProduto.setText(String.valueOf(ResultadoPrecoUnitario).replace(".", ","));
        TextFieldResultadoMaterial.setText(String.valueOf(ResultadoMaterial).replace(".", ","));
        
        TextFieldResultadoCustosGerais.setText(String.valueOf(Custos).replace(".", ","));
        TextFieldResultadoPrecoVenda.setText(String.valueOf(PrecoVenda).replace(".", ","));
        

    }

    //Fim da classe adicionar
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1PrecoUnitario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextFieldPrecoUnitario = new javax.swing.JFormattedTextField();
        TextFieldImpostoFederal = new javax.swing.JFormattedTextField();
        TextFieldImpostoEstadual = new javax.swing.JFormattedTextField();
        TextFieldIPI = new javax.swing.JFormattedTextField();
        TextFieldFrete = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TextFieldGanhoLivre = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButtonLimparCampos = new javax.swing.JButton();
        jLabel2ImpostoFederal = new javax.swing.JLabel();
        jLabel4Frete = new javax.swing.JLabel();
        jLabel5CustosGerais = new javax.swing.JLabel();
        jLabel3ImpostoEstadual = new javax.swing.JLabel();
        jLabel6GanhoLivre = new javax.swing.JLabel();
        jLabel3ImpostoEstadual1 = new javax.swing.JLabel();
        jLabel3ImpostoEstadual2 = new javax.swing.JLabel();
        TextFieldResultadoIF = new javax.swing.JFormattedTextField();
        TextFieldResultadoIE = new javax.swing.JFormattedTextField();
        TextFieldResultadoIPI = new javax.swing.JFormattedTextField();
        TextFieldResultadoFrete = new javax.swing.JFormattedTextField();
        TextFieldResultadoCustosGerais = new javax.swing.JFormattedTextField();
        TextFieldResultadoGanhoLivre = new javax.swing.JFormattedTextField();
        TextFieldResultadoICMS = new javax.swing.JFormattedTextField();
        TextFieldResultadoPrecoVenda = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel3ImpostoEstadual3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        TextFieldResultadoPrecoProduto = new javax.swing.JFormattedTextField();
        jLabel1PrecoUnitario1 = new javax.swing.JLabel();
        jLabel7PrecodeVenda1 = new javax.swing.JLabel();
        jButtonGravar = new javax.swing.JButton();
        TextFieldMaterial = new javax.swing.JFormattedTextField();
        TextFieldICMS = new javax.swing.JFormattedTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        TextFieldResultadoMaterial = new javax.swing.JTextField();
        CBCategoria = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAlterar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        botaoPesquisar = new javax.swing.JButton();
        campoPesquisar = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        botaoCriarCopia = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        campoCadCategoria = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        botaoCadCategoria = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        menuPrecificacao = new javax.swing.JMenuItem();
        menuPesquisa = new javax.swing.JMenuItem();
        menuBackup = new javax.swing.JMenuItem();
        menuCadCategoria = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        menuSair = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Precificação JBvox Óleos");

        jPanel2.setMaximumSize(new java.awt.Dimension(1369, 743));
        jPanel2.setPreferredSize(new java.awt.Dimension(1369, 743));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 153));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 51, 153));
        jTextField1.setText("                                                                               Programa de Processo de Precificação");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1PrecoUnitario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1PrecoUnitario.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1PrecoUnitario.setText("Digite aqui o Preço Unitário do Produto:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1.setText("Digite o percentual do Imposto Federal:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("Digite o percentual do Imposto Estadual:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 0));
        jLabel3.setText("Digite o percentual do IPI:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 0));
        jLabel4.setText("Digite o percentual de Outros Custos :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 0));
        jLabel5.setText("Digite o percentual do Frete:");

        TextFieldPrecoUnitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldPrecoUnitarioKeyPressed(evt);
            }
        });

        TextFieldImpostoFederal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldImpostoFederalKeyPressed(evt);
            }
        });

        TextFieldImpostoEstadual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldImpostoEstadualKeyPressed(evt);
            }
        });

        TextFieldIPI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldIPIKeyPressed(evt);
            }
        });

        TextFieldFrete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldFreteKeyPressed(evt);
            }
        });

        jLabel11.setText("R$:");

        jLabel6.setText("%");

        jLabel7.setText("%");

        jLabel8.setText("%");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setText("Digite o percentual do Ganho Livre:");

        TextFieldGanhoLivre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldGanhoLivreKeyPressed(evt);
            }
        });

        jLabel12.setText("%");

        jLabel13.setText("%");

        jLabel14.setText("%");

        jButtonLimparCampos.setText("Limpar Campos");
        jButtonLimparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparCamposActionPerformed(evt);
            }
        });

        jLabel2ImpostoFederal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2ImpostoFederal.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2ImpostoFederal.setText("O Imposto Federal Custará:");

        jLabel4Frete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4Frete.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4Frete.setText("O preço do Frete Custará:");

        jLabel5CustosGerais.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5CustosGerais.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5CustosGerais.setText("Os Custos Gerais são de:");

        jLabel3ImpostoEstadual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3ImpostoEstadual.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3ImpostoEstadual.setText("O Imposto Estadual Custará:");

        jLabel6GanhoLivre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6GanhoLivre.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6GanhoLivre.setText("O Ganho Livre será de:");

        jLabel3ImpostoEstadual1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3ImpostoEstadual1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3ImpostoEstadual1.setText("O Imposto IPI Custará:");

        jLabel3ImpostoEstadual2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3ImpostoEstadual2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3ImpostoEstadual2.setText("Os Outros Custos são de:");

        TextFieldResultadoIF.setEditable(false);
        TextFieldResultadoIF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoIF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoIF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoIE.setEditable(false);
        TextFieldResultadoIE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoIE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoIE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoIPI.setEditable(false);
        TextFieldResultadoIPI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoIPI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoIPI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoFrete.setEditable(false);
        TextFieldResultadoFrete.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoFrete.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoFrete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoCustosGerais.setEditable(false);
        TextFieldResultadoCustosGerais.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoCustosGerais.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoCustosGerais.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoGanhoLivre.setEditable(false);
        TextFieldResultadoGanhoLivre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoGanhoLivre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoGanhoLivre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoICMS.setEditable(false);
        TextFieldResultadoICMS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoICMS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoICMS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoPrecoVenda.setEditable(false);
        TextFieldResultadoPrecoVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoPrecoVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoPrecoVenda.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("R$:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("R$:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("R$:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("R$:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("R$:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("R$:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("R$:");

        jLabel3ImpostoEstadual3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3ImpostoEstadual3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3ImpostoEstadual3.setText("O Preço unitário do Produto Será de:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("R$:");

        TextFieldResultadoPrecoProduto.setEditable(false);
        TextFieldResultadoPrecoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#.00"))));
        TextFieldResultadoPrecoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoPrecoProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1PrecoUnitario1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1PrecoUnitario1.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1PrecoUnitario1.setText("Digite aqui o nome do Produto/Material:");

        jLabel7PrecodeVenda1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7PrecodeVenda1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel7PrecodeVenda1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7PrecodeVenda1.setText("O Preço de Venda será de:");

        jButtonGravar.setText("Gravar");
        jButtonGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGravarActionPerformed(evt);
            }
        });

        TextFieldMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldMaterialKeyPressed(evt);
            }
        });

        TextFieldICMS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldICMSKeyPressed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(51, 51, 0));
        jTextField3.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(102, 255, 255));
        jTextField3.setText("                                    RESULTADOS");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(51, 51, 0));
        jTextField4.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(102, 255, 255));
        jTextField4.setText("                               ENTRADA DE DADOS");
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        TextFieldResultadoMaterial.setEditable(false);
        TextFieldResultadoMaterial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TextFieldResultadoMaterial.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        CBCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CBCategoriaKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 0));
        jLabel10.setText("Escolha a categoria do Produto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 44, Short.MAX_VALUE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(jButtonLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(76, 76, 76)
                                        .addComponent(jButtonGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(TextFieldImpostoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1PrecoUnitario)
                                                        .addComponent(jLabel10)
                                                        .addComponent(jLabel1PrecoUnitario1)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel9))
                                                    .addGap(28, 28, 28)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(CBCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                            .addComponent(jLabel11)
                                                            .addGap(9, 9, 9)
                                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(TextFieldMaterial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                                .addComponent(TextFieldPrecoUnitario)
                                                                .addComponent(TextFieldImpostoFederal))))))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(TextFieldICMS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TextFieldIPI, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TextFieldFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TextFieldGanhoLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(TextFieldResultadoMaterial, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel3ImpostoEstadual1)
                                                        .addComponent(jLabel3ImpostoEstadual3)
                                                        .addComponent(jLabel2ImpostoFederal)
                                                        .addComponent(jLabel3ImpostoEstadual)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel4Frete)
                                                            .addComponent(jLabel3ImpostoEstadual2)
                                                            .addComponent(jLabel6GanhoLivre, javax.swing.GroupLayout.Alignment.TRAILING)))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(TextFieldResultadoFrete, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TextFieldResultadoICMS, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TextFieldResultadoIE, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TextFieldResultadoPrecoProduto, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TextFieldResultadoIF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(TextFieldResultadoIPI, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(TextFieldResultadoGanhoLivre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                    .addComponent(jLabel5CustosGerais)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(TextFieldResultadoCustosGerais, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(169, 169, 169)
                                        .addComponent(jLabel7PrecodeVenda1)))
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextFieldResultadoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CBCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1PrecoUnitario1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1PrecoUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldImpostoFederal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldImpostoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldIPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12)
                            .addComponent(TextFieldICMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldGanhoLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TextFieldResultadoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3ImpostoEstadual3)
                                    .addComponent(jLabel23)
                                    .addComponent(TextFieldResultadoPrecoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2ImpostoFederal)
                                    .addComponent(jLabel16)
                                    .addComponent(TextFieldResultadoIF, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(TextFieldResultadoIE, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3ImpostoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel3ImpostoEstadual1)
                                    .addComponent(TextFieldResultadoIPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3ImpostoEstadual2)
                                    .addComponent(jLabel22)
                                    .addComponent(TextFieldResultadoICMS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                    .addComponent(jLabel4Frete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20)
                                    .addComponent(TextFieldResultadoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6GanhoLivre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel21)
                                    .addComponent(TextFieldResultadoGanhoLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5CustosGerais, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TextFieldResultadoCustosGerais, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7PrecodeVenda1)
                                .addGap(18, 18, 18)
                                .addComponent(TextFieldResultadoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Precificação", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descricao", "Preco", "ImpostoFederal", "IPI", "ImpostoEstadual", "OutrosCustos", "Frete", "GanhoLivre", "CustosGerais", "PrecoVenda", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(55);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(25);
        }

        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButton1.setText("Remover");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        campoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1260, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButton1)
                    .addComponent(campoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoPesquisar))
                .addContainerGap(230, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pesquisar", jPanel3);

        botaoCriarCopia.setForeground(new java.awt.Color(66, 107, 215));
        botaoCriarCopia.setText("CRIAR CÓPIA DE SEGURANÇA");
        botaoCriarCopia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarCopiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoCriarCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1020, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoCriarCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(638, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fazer Backup", jPanel4);

        jLabel15.setBackground(new java.awt.Color(255, 204, 204));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 204, 51));
        jLabel15.setText("Cadastro de Categorias:");

        campoCadCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoCadCategoriaKeyPressed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "codigo", "CadastroCategoria"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setText("Digite o nome da categoria que deseja Cadastrar:");

        botaoCadCategoria.setText("Cadastrar");
        botaoCadCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCadCategoriaActionPerformed(evt);
            }
        });

        jButton3.setText("REMOVER CATEGORIA SELECIONADA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel24))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoCadCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(botaoCadCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel24)
                        .addGap(57, 57, 57)
                        .addComponent(campoCadCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(botaoCadCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro de Categoria", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1265, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 737, Short.MAX_VALUE)
        );

        jMenu4.setText("Arquivos");

        menuPrecificacao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        menuPrecificacao.setText("Precificação");
        menuPrecificacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPrecificacaoActionPerformed(evt);
            }
        });
        jMenu4.add(menuPrecificacao);

        menuPesquisa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        menuPesquisa.setText("Pesquisa/  tabela de cadastros");
        menuPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPesquisaActionPerformed(evt);
            }
        });
        jMenu4.add(menuPesquisa);

        menuBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        menuBackup.setText("Backup de Tabela");
        menuBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBackupActionPerformed(evt);
            }
        });
        jMenu4.add(menuBackup);

        menuCadCategoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        menuCadCategoria.setText("Cadastro de Categorias");
        menuCadCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCadCategoriaActionPerformed(evt);
            }
        });
        jMenu4.add(menuCadCategoria);
        jMenu4.add(jSeparator4);

        menuSair.setText("Sair");
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        jMenu4.add(menuSair);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Relatórios");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLimparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparCamposActionPerformed
        TextFieldMaterial.setText("");
        TextFieldPrecoUnitario.setText("");
        TextFieldImpostoFederal.setText("");
        TextFieldImpostoEstadual.setText("");
        TextFieldIPI.setText("");
        TextFieldICMS.setText("");
        TextFieldFrete.setText("");
        TextFieldGanhoLivre.setText("");

        TextFieldResultadoMaterial.setText("");
        TextFieldResultadoIF.setText("");
        TextFieldResultadoIE.setText("");
        TextFieldResultadoIPI.setText("");
        TextFieldResultadoICMS.setText("");
        TextFieldResultadoFrete.setText("");
        TextFieldResultadoCustosGerais.setText("");
        TextFieldResultadoGanhoLivre.setText("");
        TextFieldResultadoPrecoVenda.setText("");
        TextFieldResultadoPrecoProduto.setText("");

        CBCategoria.grabFocus();
        TextFieldMaterial.selectAll();//foca o campo preco unitario

    }//GEN-LAST:event_jButtonLimparCamposActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    public boolean validadorSoNumeros(){
        if(!TextFieldPrecoUnitario.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[1]=true; camposInvalidos=true;TextFieldPrecoUnitario.setForeground(Color.RED);}else TextFieldPrecoUnitario.setForeground(Color.BLACK);
        if(!TextFieldImpostoFederal.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[2]=true; camposInvalidos=true;TextFieldImpostoFederal.setForeground(Color.RED);}else TextFieldImpostoFederal.setForeground(Color.BLACK);
        if(!TextFieldImpostoEstadual.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[3]=true; camposInvalidos=true;TextFieldImpostoEstadual.setForeground(Color.RED);}else TextFieldImpostoEstadual.setForeground(Color.BLACK);
        if(!TextFieldIPI.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[4]=true; camposInvalidos=true;TextFieldIPI.setForeground(Color.RED);}else TextFieldIPI.setForeground(Color.BLACK);
        if(!TextFieldICMS.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[5]=true; camposInvalidos=true;TextFieldICMS.setForeground(Color.RED);}else TextFieldICMS.setForeground(Color.BLACK);
        if(!TextFieldFrete.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[6]=true; camposInvalidos=true;TextFieldFrete.setForeground(Color.RED);}else TextFieldFrete.setForeground(Color.BLACK);
        if(!TextFieldGanhoLivre.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[7]=true; camposInvalidos=true;TextFieldGanhoLivre.setForeground(Color.RED);}else TextFieldGanhoLivre.setForeground(Color.BLACK);
        
        if(camposInvalidos){
            JOptionPane.showMessageDialog(null,"Por favor, insira apenas números nos campos de porcentagem ou reais");
            if(campoInvalido[1]){
                    
                    TextFieldPrecoUnitario.grabFocus();TextFieldPrecoUnitario.selectAll();
                }else{
                    if(campoInvalido[2]){
                        TextFieldImpostoFederal.grabFocus();TextFieldImpostoFederal.selectAll();
                        
                    }else{
                        if(campoInvalido[3]){
                            
                            TextFieldImpostoEstadual.grabFocus();TextFieldImpostoEstadual.selectAll();
                        }else{
                            if(campoInvalido[4]){
                                
                                TextFieldIPI.grabFocus();TextFieldIPI.selectAll();
                            }else{
                                if(campoInvalido[5]){
                                    
                                    TextFieldICMS.grabFocus();TextFieldICMS.selectAll();
                                }else{
                                    if(campoInvalido[6]){
                                        
                                        TextFieldFrete.grabFocus();TextFieldFrete.selectAll();
                                    }else{
                                        if(campoInvalido[7]){
                                            
                                            TextFieldGanhoLivre.grabFocus();TextFieldGanhoLivre.selectAll();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            
            
            for(int i=1;i<=7;i++){
                    campoInvalido[i] = false;
                }
            camposInvalidos=false;
            return false;
         
        }
        return true;
    }
    private void jButtonGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGravarActionPerformed
        
        
        if(CBCategoria.getItemCount()==0){
            JOptionPane.showMessageDialog(null,"Categoria não informada. Por favor, cadastre uma nova categoria", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            jTabbedPane1.setSelectedIndex(3);
            campoCadCategoria.grabFocus();
            precificacaoPendente=true;
            return;
        }
        
        if(TextFieldPrecoUnitario.getText().isEmpty()){campoVazio[1]=true; camposVazios=true;}
        if(TextFieldImpostoFederal.getText().isEmpty()){campoVazio[2]=true; camposVazios=true;}
        if(TextFieldImpostoEstadual.getText().isEmpty()){campoVazio[3]=true; camposVazios=true;}
        if(TextFieldIPI.getText().isEmpty()){campoVazio[4]=true; camposVazios=true;}
        if(TextFieldICMS.getText().isEmpty()){campoVazio[5]=true; camposVazios=true;}
        if(TextFieldFrete.getText().isEmpty()){campoVazio[6]=true; camposVazios=true;}
        if(TextFieldGanhoLivre.getText().isEmpty()){campoVazio[7]=true; camposVazios=true;}
        
        if(TextFieldMaterial.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Opa! Faltou o Nome do Produto/Material\nPor favor, informe um nome para este produto");
            TextFieldMaterial.grabFocus();
            
            return;
        }

        if(camposVazios){
            if(JOptionPane.showConfirmDialog(this, "Opa! Encontrei campos vazios.\nDeseja preencher automaticamente os campos vazios com 0%?\nPara corrigir manualmente, pressione NÃO","Campos vazios encontrados",JOptionPane.YES_NO_OPTION) == 0){
                if(TextFieldMaterial.getText().isEmpty()){TextFieldMaterial.setText("0");campoVazio[1]=false; camposVazios=false;}
                if(TextFieldImpostoFederal.getText().isEmpty()){TextFieldImpostoFederal.setText("0");campoVazio[2]=false; camposVazios=false;}
                if(TextFieldImpostoEstadual.getText().isEmpty()){TextFieldImpostoEstadual.setText("0");campoVazio[3]=false; camposVazios=false;}
                if(TextFieldIPI.getText().isEmpty()){TextFieldIPI.setText("0");campoVazio[4]=false; camposVazios=false;}
                if(TextFieldICMS.getText().isEmpty()){TextFieldICMS.setText("0");campoVazio[5]=false; camposVazios=false;}
                if(TextFieldFrete.getText().isEmpty()){TextFieldFrete.setText("0");campoVazio[6]=false; camposVazios=false;}
                if(TextFieldGanhoLivre.getText().isEmpty()){TextFieldGanhoLivre.setText("0");campoVazio[7]=false; camposVazios=false;}
                LogicaSomas();
                atualizaResultados();
                return;
            }else{
                if(campoVazio[1]){
                    TextFieldPrecoUnitario.grabFocus();
                }else{
                    if(campoVazio[2]){
                        TextFieldImpostoFederal.grabFocus();
                    }else{
                        if(campoVazio[3]){
                            TextFieldImpostoEstadual.grabFocus();
                        }else{
                            if(campoVazio[4]){
                                TextFieldIPI.grabFocus();
                            }else{
                                if(campoVazio[5]){
                                    TextFieldICMS.grabFocus();
                                }else{
                                    if(campoVazio[6]){
                                        TextFieldFrete.grabFocus();
                                    }else{
                                        if(campoVazio[7]){
                                            TextFieldGanhoLivre.grabFocus();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for(int i=1;i<=7;i++){
                    campoVazio[i] = false;
                }
                camposVazios=false;
                return;
            }
        }

        if(!validadorSoNumeros()){
            return;
        }
        
        LogicaSomas();
        atualizaResultados();
        conexao.connection().conecta();
        
                       
        //TextFieldCB.setText(CBCategoria.getSelectedItem().toString());
        String Descricao = this.TextFieldMaterial.getText().replace(",", ".");
        String Preco = this.TextFieldPrecoUnitario.getText().replace(",", ".");
        String IPI = this.TextFieldResultadoIPI.getText().replace(",", ".");
        String ICMS = this.TextFieldResultadoICMS.getText().replace(",", ".");
        String Frete = this.TextFieldResultadoFrete.getText().replace(",", ".");
        String GanhoLivre = this.TextFieldResultadoGanhoLivre.getText().replace(",", ".");
        String CustosGerais = this.TextFieldResultadoCustosGerais.getText().replace(",", ".");
        String PrecoVenda = this.TextFieldResultadoPrecoVenda.getText().replace(",", ".");
        String ImpostoFederal = this.TextFieldResultadoIF.getText().replace(",", ".");
        String ImpostoEstadual = this.TextFieldResultadoIE.getText().replace(",", ".");
        String Categoria = this.CBCategoria.getSelectedItem().toString();
        String comando = "insert into producao ( Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda,Categoria) "
                + "values " + "('" + Descricao + "','" + Preco + "','" + ImpostoFederal + "','" + ImpostoEstadual + "', '" + IPI + "', '" + ICMS + "', '" + Frete + "', '" + GanhoLivre + "', '" + CustosGerais + "', '" + PrecoVenda + "', '" + Categoria + "')";

        conexao.connection().executeSQLUpdate(comando);
        

        this.carregarTabela();
        JOptionPane.showMessageDialog(null, "Dados inseridos com SUCESSO");
        TextFieldMaterial.setText("");
        TextFieldPrecoUnitario.setText("");
        TextFieldImpostoFederal.setText("");
        TextFieldImpostoEstadual.setText("");
        TextFieldIPI.setText("");
        TextFieldICMS.setText("");
        TextFieldFrete.setText("");
        TextFieldGanhoLivre.setText("");

        TextFieldResultadoMaterial.setText("");
        TextFieldResultadoIF.setText("");
        TextFieldResultadoIE.setText("");
        TextFieldResultadoIPI.setText("");
        TextFieldResultadoICMS.setText("");
        TextFieldResultadoFrete.setText("");
        TextFieldResultadoCustosGerais.setText("");
        TextFieldResultadoGanhoLivre.setText("");
        TextFieldResultadoPrecoVenda.setText("");
        TextFieldResultadoPrecoProduto.setText("");

        CBCategoria.grabFocus();
        TextFieldMaterial.selectAll();//foca o campo preco unitario
        
        conexao.connection().desconecta();
        

    }//GEN-LAST:event_jButtonGravarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        linhaSelecionada = this.jTable1.getSelectedRow();
        // Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda)    

        String Descricao = this.jTable1.getValueAt(linhaSelecionada, 1).toString();
        String Preco = this.jTable1.getValueAt(linhaSelecionada, 2).toString();
        String ImpostoFederal = this.jTable1.getValueAt(linhaSelecionada, 3).toString();
        String ImpostoEstadual = this.jTable1.getValueAt(linhaSelecionada, 4).toString();
        String IPI = this.jTable1.getValueAt(linhaSelecionada, 5).toString();
        String ICMS = this.jTable1.getValueAt(linhaSelecionada, 6).toString();
        String Frete = this.jTable1.getValueAt(linhaSelecionada, 7).toString();
        String GanhoLivre = this.jTable1.getValueAt(linhaSelecionada, 8).toString();
        String CustosGerais = this.jTable1.getValueAt(linhaSelecionada, 9).toString();
        String PrecoVenda = this.jTable1.getValueAt(linhaSelecionada, 10).toString();
        String codigo = this.jTable1.getValueAt(linhaSelecionada, 0).toString();
         String Categoria = this.jTable1.getValueAt(linhaSelecionada, 11).toString();
        
        new AlterarTabela(Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda, codigo).setVisible(true);


    }//GEN-LAST:event_jButtonAlterarActionPerformed

        private void botaoCriarCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarCopiaActionPerformed
            new BackupRestauracao().fazerBackup();
    }//GEN-LAST:event_botaoCriarCopiaActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int linhaSelecionada = this.jTable1.getSelectedRow();
       String codigo = this.jTable1.getValueAt(linhaSelecionada, 0).toString();
        new JanelaRemover(codigo).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed
        new JanelaPesquisar(campoPesquisar.getText()).setVisible(true);
    }//GEN-LAST:event_botaoPesquisarActionPerformed

    private void campoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPesquisarActionPerformed
        botaoPesquisarActionPerformed(evt);
    }//GEN-LAST:event_campoPesquisarActionPerformed

    private void botaoCadCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCadCategoriaActionPerformed
       conexao.connection().conecta();
        
                       
        //TextFieldCB.setText(CBCategoria.getSelectedItem().toString());
       
        
        String Categoria = this.campoCadCategoria.getText().replace(",", ".");
        String comando = "insert into categoria ( CadastroCategoria) "
                + "values " + "('" + Categoria + "')";

        conexao.connection().executeSQLUpdate(comando);
        

        this.carregarTabela2();
        experiencia();
        JOptionPane.showMessageDialog(null, "Dados inseridos com SUCESSO");
        

        conexao.connection().desconecta();
        
        campoCadCategoria.setText("");
        campoCadCategoria.grabFocus();


        if(precificacaoPendente){
            jTabbedPane1.setSelectedIndex(0);
            jButtonGravar.grabFocus();
            precificacaoPendente=false;
        }
    }//GEN-LAST:event_botaoCadCategoriaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int linhaSelecionada = this.jTable2.getSelectedRow();
       String codigo = this.jTable2.getValueAt(linhaSelecionada, 0).toString();
        new JanelaRemover2(codigo).setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void menuPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPesquisaActionPerformed
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_menuPesquisaActionPerformed

    private void menuBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBackupActionPerformed
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_menuBackupActionPerformed

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSairActionPerformed

    private void menuPrecificacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPrecificacaoActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_menuPrecificacaoActionPerformed

    private void menuCadCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCadCategoriaActionPerformed
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_menuCadCategoriaActionPerformed

    private void CBCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CBCategoriaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TextFieldMaterial.requestFocus();
            TextFieldMaterial.selectAll();
        }
    }//GEN-LAST:event_CBCategoriaKeyPressed

    private void TextFieldImpostoFederalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldImpostoFederalKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                 this.LogicaSomas();
            atualizaResultados();
            TextFieldImpostoEstadual.requestFocus();
            TextFieldImpostoEstadual.selectAll();}      // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldImpostoFederalKeyPressed

    private void TextFieldImpostoEstadualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldImpostoEstadualKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        this.LogicaSomas();       
        atualizaResultados();
        TextFieldIPI.requestFocus();
        TextFieldIPI.selectAll();
    }        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldImpostoEstadualKeyPressed

    private void TextFieldIPIKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldIPIKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        this.LogicaSomas();  
        atualizaResultados();
        TextFieldICMS.requestFocus();
        TextFieldICMS.selectAll();
    }         // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldIPIKeyPressed

    private void TextFieldICMSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldICMSKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        this.LogicaSomas();   
        atualizaResultados();
        TextFieldFrete.requestFocus();
        TextFieldFrete.selectAll();
    }         // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldICMSKeyPressed

    private void TextFieldFreteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldFreteKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        this.LogicaSomas();   
        atualizaResultados();
        TextFieldGanhoLivre.requestFocus();
        TextFieldGanhoLivre.selectAll();
    }           // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldFreteKeyPressed

    private void TextFieldGanhoLivreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldGanhoLivreKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        this.LogicaSomas();   
        atualizaResultados();
        jButtonGravar.grabFocus();
        jButtonGravar.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), evt);
        jButtonGravar.getActionMap().put("DoClick", null);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldGanhoLivreKeyPressed

    private void campoCadCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCadCategoriaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            botaoCadCategoria.doClick();
        }
    }//GEN-LAST:event_campoCadCategoriaKeyPressed

    private void TextFieldPrecoUnitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldPrecoUnitarioKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.LogicaSomas();
            atualizaResultados();
            TextFieldImpostoFederal.requestFocus();
            TextFieldImpostoFederal.selectAll();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldPrecoUnitarioKeyPressed

    private void TextFieldMaterialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldMaterialKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            this.LogicaSomas();
            TextFieldPrecoUnitario.requestFocus();
            TextFieldPrecoUnitario.selectAll();
        }
    }//GEN-LAST:event_TextFieldMaterialKeyPressed
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
            java.util.logging.Logger.getLogger(visao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(visao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(visao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(visao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new visao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CBCategoria;
    private javax.swing.JFormattedTextField TextFieldFrete;
    private javax.swing.JFormattedTextField TextFieldGanhoLivre;
    private javax.swing.JFormattedTextField TextFieldICMS;
    private javax.swing.JFormattedTextField TextFieldIPI;
    private javax.swing.JFormattedTextField TextFieldImpostoEstadual;
    private javax.swing.JFormattedTextField TextFieldImpostoFederal;
    private javax.swing.JFormattedTextField TextFieldMaterial;
    private javax.swing.JFormattedTextField TextFieldPrecoUnitario;
    private javax.swing.JFormattedTextField TextFieldResultadoCustosGerais;
    private javax.swing.JFormattedTextField TextFieldResultadoFrete;
    private javax.swing.JFormattedTextField TextFieldResultadoGanhoLivre;
    private javax.swing.JFormattedTextField TextFieldResultadoICMS;
    private javax.swing.JFormattedTextField TextFieldResultadoIE;
    private javax.swing.JFormattedTextField TextFieldResultadoIF;
    private javax.swing.JFormattedTextField TextFieldResultadoIPI;
    private javax.swing.JTextField TextFieldResultadoMaterial;
    private javax.swing.JFormattedTextField TextFieldResultadoPrecoProduto;
    private javax.swing.JFormattedTextField TextFieldResultadoPrecoVenda;
    private javax.swing.JButton botaoCadCategoria;
    private javax.swing.JButton botaoCriarCopia;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JTextField campoCadCategoria;
    private javax.swing.JTextField campoPesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonLimparCampos;
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
    private javax.swing.JLabel jLabel1PrecoUnitario;
    private javax.swing.JLabel jLabel1PrecoUnitario1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel2ImpostoFederal;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel3ImpostoEstadual;
    private javax.swing.JLabel jLabel3ImpostoEstadual1;
    private javax.swing.JLabel jLabel3ImpostoEstadual2;
    private javax.swing.JLabel jLabel3ImpostoEstadual3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel4Frete;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel5CustosGerais;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel6GanhoLivre;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel7PrecodeVenda1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JMenuItem menuBackup;
    private javax.swing.JMenuItem menuCadCategoria;
    private javax.swing.JMenuItem menuPesquisa;
    private javax.swing.JMenuItem menuPrecificacao;
    private javax.swing.JMenuItem menuSair;
    // End of variables declaration//GEN-END:variables
}
