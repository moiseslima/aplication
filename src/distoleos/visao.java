/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;


import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author moises
 */
public class visao extends javax.swing.JFrame {

    double Custos, Soma, PrecoUnitario, ImpostoFederal, ImpostoEstadual, IPI, ICMS, GanhoLivre, CustosGerais, Frete, PrecoVenda;
    String Material;

    /**
     * Creates new form visao
     */
    public visao() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        TextFieldMaterial.grabFocus();
        //focar ao iniciar o programa
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
    }
//Classe Adicionar

    public void carregarTabela() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTable1.getModel();
        int rowCount = dtm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }
        //fazer a conexao com o mysql
        java.sql.Connection con;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/armazenamento", "root", "Moises@125");
        } catch (Exception e) {
            e.printStackTrace();
            throw new java.lang.RuntimeException("erro ao conectar");

        }

        Vector<Vector> dados = new Vector<Vector>();

        String comando = "select * from producao";

        try {
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(comando);

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

                dados.add(registroAtual);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (java.sql.SQLException e) {
            throw new java.lang.RuntimeException(e.getMessage());
        }

        //Fechando a Conexão:
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new java.lang.RuntimeException("erro fechar");
        }

        
        for (int i = 0; i < dados.size(); i++) {
            dtm.addRow(dados.get(i));
        }
        


        jTable1.revalidate();
    }
    
    public void LogicaSomas(){
        
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
        /* //os campos da esquerda não mais ficam "0.0", se deletar as seguintes linhas:
        TextFieldMaterial.setText(String.valueOf(Material));
        TextFieldPrecoUnitario.setText(String.valueOf(PrecoUnitario));
        TextFieldImpostoEstadual.setText(String.valueOf(ImpostoEstadual));
        TextFieldImpostoFederal.setText(String.valueOf(ImpostoFederal));
        TextFieldIPI.setText(String.valueOf(IPI));
        TextFieldICMS.setText(String.valueOf(ICMS));
        TextFieldGanhoLivre.setText(String.valueOf(GanhoLivre));
        TextFieldFrete.setText(String.valueOf(Frete));
        */
        
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
        // We have to parse the text to a type Double.

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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAlterar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        botaoCriarCopia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(getMaximumSize());

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
        jLabel4.setText("Digite o percentual do ICMS :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 0));
        jLabel5.setText("Digite o percentual do Frete:");

        TextFieldPrecoUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldPrecoUnitarioActionPerformed(evt);
            }
        });

        TextFieldImpostoFederal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldImpostoFederalActionPerformed(evt);
            }
        });

        TextFieldImpostoEstadual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldImpostoEstadualActionPerformed(evt);
            }
        });

        TextFieldIPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldIPIActionPerformed(evt);
            }
        });

        TextFieldFrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldFreteActionPerformed(evt);
            }
        });

        jLabel11.setText("R$:");

        jLabel6.setText("%");

        jLabel7.setText("%");

        jLabel8.setText("%");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setText("Digite o percentual do Ganho Livre:");

        TextFieldGanhoLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldGanhoLivreActionPerformed(evt);
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
        jLabel3ImpostoEstadual2.setText("O Imposto ICMS Custará:");

        TextFieldResultadoIF.setEditable(false);
        TextFieldResultadoIF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoIF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoIF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoIE.setEditable(false);
        TextFieldResultadoIE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoIE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoIE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoIPI.setEditable(false);
        TextFieldResultadoIPI.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoIPI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoIPI.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoFrete.setEditable(false);
        TextFieldResultadoFrete.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoFrete.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoFrete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoCustosGerais.setEditable(false);
        TextFieldResultadoCustosGerais.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoCustosGerais.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoCustosGerais.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoGanhoLivre.setEditable(false);
        TextFieldResultadoGanhoLivre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoGanhoLivre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoGanhoLivre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoICMS.setEditable(false);
        TextFieldResultadoICMS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoICMS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoICMS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        TextFieldResultadoPrecoVenda.setEditable(false);
        TextFieldResultadoPrecoVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
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
        TextFieldResultadoPrecoProduto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###0.00"))));
        TextFieldResultadoPrecoProduto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TextFieldResultadoPrecoProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1PrecoUnitario1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1PrecoUnitario1.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1PrecoUnitario1.setText("Digite aqui a descrição do Produto/Material:");

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

        TextFieldMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldMaterialActionPerformed(evt);
            }
        });

        TextFieldICMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldICMSActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel9))
                                                .addGap(54, 54, 54)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(TextFieldGanhoLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel14))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(TextFieldFrete, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel13))))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(54, 54, 54)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(TextFieldICMS)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel12))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(TextFieldImpostoEstadual)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel7))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(TextFieldIPI, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel8))))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1PrecoUnitario1)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel1PrecoUnitario)
                                                        .addGap(31, 31, 31)
                                                        .addComponent(jLabel11))
                                                    .addComponent(jLabel1))))
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(TextFieldPrecoUnitario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(TextFieldImpostoFederal, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TextFieldMaterial))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 67, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButtonLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79)
                                        .addComponent(jButtonGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)))))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TextFieldResultadoMaterial, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7PrecodeVenda1)
                                            .addComponent(jLabel5CustosGerais))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TextFieldResultadoCustosGerais, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(TextFieldResultadoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 86, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TextFieldResultadoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
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
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5CustosGerais, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldResultadoCustosGerais, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7PrecodeVenda1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextFieldResultadoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
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
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonLimparCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator1)))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "Descricao", "Preco", "ImpostoFederal", "IPI", "ImpostoEstadual", "ICMS", "Frete", "GanhoLivre", "CustosGerais", "PrecoVenda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(1265, Short.MAX_VALUE)
                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1369, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAlterar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

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
                .addContainerGap(1129, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoCriarCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(591, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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

        TextFieldMaterial.grabFocus();
        TextFieldMaterial.selectAll();//foca o campo preco unitario

    }//GEN-LAST:event_jButtonLimparCamposActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void TextFieldPrecoUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldPrecoUnitarioActionPerformed

        this.LogicaSomas();
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldImpostoFederal.requestFocus();
        TextFieldImpostoFederal.selectAll();

    }//GEN-LAST:event_TextFieldPrecoUnitarioActionPerformed

    // num1 = PrecoUnitario, num2 = ImpostoEstadual, num3 = IPI, num4 = Frete, num5= GanhoLivre, num6 = ImpostoFederal;
    private void TextFieldImpostoEstadualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldImpostoEstadualActionPerformed
        this.LogicaSomas();       
        TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldIPI.requestFocus();
         TextFieldIPI.selectAll();
    }//GEN-LAST:event_TextFieldImpostoEstadualActionPerformed

    private void TextFieldIPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldIPIActionPerformed
         this.LogicaSomas();  
         TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldICMS.requestFocus();
        TextFieldICMS.selectAll();

    }//GEN-LAST:event_TextFieldIPIActionPerformed

    private void TextFieldFreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldFreteActionPerformed
         this.LogicaSomas();   
         TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldGanhoLivre.requestFocus();
        TextFieldGanhoLivre.selectAll();
    }//GEN-LAST:event_TextFieldFreteActionPerformed

    private void jButtonGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGravarActionPerformed
        //fazer a conexao com o mysql
        java.sql.Connection con;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            con = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/armazenamento", "root", "Moises@125");
        } catch (Exception e) {
            e.printStackTrace();
            throw new java.lang.RuntimeException("erro ao conectar");
        }

        String Descricao = this.TextFieldMaterial.getText();
        String Preco = this.TextFieldPrecoUnitario.getText();
        String IPI = this.TextFieldResultadoIPI.getText();
        String ICMS = this.TextFieldResultadoICMS.getText();
        String Frete = this.TextFieldResultadoFrete.getText();
        String GanhoLivre = this.TextFieldResultadoGanhoLivre.getText();
        String CustosGerais = this.TextFieldResultadoCustosGerais.getText();
        String PrecoVenda = this.TextFieldResultadoPrecoVenda.getText();
        String ImpostoFederal = this.TextFieldResultadoIF.getText();
        String ImpostoEstadual = this.TextFieldResultadoIE.getText();
        String comando = "insert into producao ( Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda) "
                + "values " + "('" + Descricao + "','" + Preco + "','" + ImpostoFederal + "','" + ImpostoEstadual + "', '" + IPI + "', '" + ICMS + "', '" + Frete + "', '" + GanhoLivre + "', '" + CustosGerais + "', '" + PrecoVenda + "')";

        try {
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(comando);
            stmt.close();
            con.close();
        } catch (java.sql.SQLException e) {
            throw new java.lang.RuntimeException(e.getMessage());
        }

        this.carregarTabela();

//Fechando a Conexão:
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new java.lang.RuntimeException("erro fechar");
        }


    }//GEN-LAST:event_jButtonGravarActionPerformed

    private void TextFieldGanhoLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldGanhoLivreActionPerformed
         this.LogicaSomas();   
         TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldGanhoLivre.transferFocus();
        TextFieldGanhoLivre.selectAll();//apertando enter, transfere foco p/proximo campo
    }//GEN-LAST:event_TextFieldGanhoLivreActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        int linhaSelecionada = this.jTable1.getSelectedRow();
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
        new AlterarTabela(Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda, codigo).setVisible(true);


    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void TextFieldMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldMaterialActionPerformed
         this.LogicaSomas();
         TextFieldPrecoUnitario.requestFocus();
         TextFieldPrecoUnitario.selectAll();
    }//GEN-LAST:event_TextFieldMaterialActionPerformed

    private void TextFieldImpostoFederalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldImpostoFederalActionPerformed
        this.LogicaSomas();
        TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldImpostoEstadual.requestFocus();
        TextFieldImpostoEstadual.selectAll();//apertando enter, transfere foco p/proximo campo
    }//GEN-LAST:event_TextFieldImpostoFederalActionPerformed

        private void botaoCriarCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarCopiaActionPerformed
            new BackupRestauracao().fazerBackup();
    }//GEN-LAST:event_botaoCriarCopiaActionPerformed

    private void TextFieldICMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldICMSActionPerformed
         this.LogicaSomas();   
         TextFieldResultadoCustosGerais.grabFocus();
	TextFieldResultadoPrecoVenda.grabFocus();
        TextFieldResultadoPrecoProduto.grabFocus();
	TextFieldResultadoIF.grabFocus();
	TextFieldResultadoIE.grabFocus();
	TextFieldResultadoIPI.grabFocus();
	TextFieldResultadoICMS.grabFocus();
	TextFieldResultadoFrete.grabFocus();
	TextFieldResultadoGanhoLivre.grabFocus();
        TextFieldFrete.requestFocus();
        TextFieldFrete.selectAll();//apertando enter, transfere foco p/proximo campo


    }//GEN-LAST:event_TextFieldICMSActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

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
    private javax.swing.JButton botaoCriarCopia;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonGravar;
    private javax.swing.JButton jButtonLimparCampos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
