/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import util.conexao;

/**
 *
 * @author moises
 */
public class AlterarTabela extends javax.swing.JFrame {
        String codigo, cat;
        float preco,impostoFederal,impostoEstadual,ipi,icms,frete,ganhoLivre,custosGerais,precoVenda;
        float impostoFederalPorcentagem,impostoEstadualPorcentagem,ipiPorcentagem,icmsPorcentagem,fretePorcentagem,ganhoLivrePorcentagem;
        boolean camposVazios, camposInvalidos;
    boolean campoVazio[] = new boolean[8];
    boolean campoInvalido[] = new boolean[8];
        /**
     * Creates new form AlterarTabela
     */
    
    public AlterarTabela(String Descricao, String Preco, String ImpostoFederal, String ImpostoEstadual, String IPI, String ICMS, String Frete, String GanhoLivre, String CustosGerais, String PrecoVenda, String codigo, String cat) {
        this.cat = cat;
        preco = Float.parseFloat(Preco);
        impostoFederal = Float.parseFloat(ImpostoFederal);
        impostoEstadual = Float.parseFloat(ImpostoEstadual);
        ipi = Float.parseFloat(IPI);
        icms = Float.parseFloat(ICMS);
        frete = Float.parseFloat(Frete);
        ganhoLivre = Float.parseFloat(GanhoLivre);
        custosGerais = Float.parseFloat(CustosGerais);
        precoVenda = Float.parseFloat(PrecoVenda);
        
        impostoFederalPorcentagem = (impostoFederal * 100) / preco;
        impostoEstadualPorcentagem = (impostoEstadual * 100) / preco;
        ipiPorcentagem = (ipi*100) / preco;
        icmsPorcentagem = (icms*100) / preco;
        fretePorcentagem = (frete*100) / preco;
        ganhoLivrePorcentagem = (ganhoLivre*100) / preco;
        //float custosGeraisPorcentagem = (Float.parseFloat(CustosGerais)*100) / Float.parseFloat(Preco);
        initComponents();
        botaoAlterar.addKeyListener(new KeyAdapter(){
          public void keyPressed(KeyEvent e) {
              if(e.getKeyCode()==KeyEvent.VK_ENTER){
                  botaoAlterar.doClick();
              }
          }
      });
        this.codigo = codigo;
        this.campoDescricao.setText(Descricao);
        this.campoPrecoUnitario.setText(Preco);
        this.campoImpostoFederal.setText(String.valueOf(impostoFederalPorcentagem));
        this.campoImpostoEstadual.setText(String.valueOf(impostoEstadualPorcentagem));
        this.campoIPI.setText(String.valueOf(ipiPorcentagem));
        this.campoICMS.setText(String.valueOf(icmsPorcentagem));
        this.campoFrete.setText(String.valueOf(fretePorcentagem));
        this.campoGanhoLivre.setText(String.valueOf(ganhoLivrePorcentagem));
        this.campoCustosGerais.setText((CustosGerais));
        this.campoPrecoVenda.setText(PrecoVenda);
        populaCB();
        this.categoria.setSelectedItem(cat);
        campoDescricao.grabFocus();
        campoDescricao.selectAll();
    }

    public void populaCB(){
        categoria.removeAllItems();
        conexao.connection().conecta();
        String comando = "select * from categoria";
        try {
            java.sql.ResultSet rs = conexao.connection().executeSQLQuery(comando);   
            while (rs != null && rs.next()) {
               String CadastroCategoria = rs.getString("CadastroCategoria");
               categoria.addItem(CadastroCategoria);
            }
        } catch (java.sql.SQLException e) {
            throw new java.lang.RuntimeException(e.getMessage());
        }
        conexao.connection().desconecta();
    }
    public void recalcularValores(){
        float ResultadoIPI = (Float.parseFloat(campoIPI.getText()) / 100) * Float.parseFloat(campoPrecoUnitario.getText());
        campoIPI.setText(String.valueOf(ResultadoIPI));

        float ResultadoFrete = (Float.parseFloat(campoFrete.getText()) / 100) * Float.parseFloat(campoPrecoUnitario.getText());
        campoFrete.setText(String.valueOf(ResultadoFrete));

        float ResultadoGanhoLivre = (Float.parseFloat(campoGanhoLivre.getText()) / 100) * Float.parseFloat(campoPrecoUnitario.getText());
        campoGanhoLivre.setText(String.valueOf(ResultadoGanhoLivre));

        float ResultadoIF = (Float.parseFloat(campoImpostoFederal.getText()) / 100) * Float.parseFloat(campoPrecoUnitario.getText());
        campoImpostoFederal.setText(String.valueOf(ResultadoIF));

        float ResultadoICMS = (Float.parseFloat(campoICMS.getText()) / 100) * Float.parseFloat(campoPrecoUnitario.getText());
        campoICMS.setText(String.valueOf(ResultadoICMS));

        float ResultadoIE = (Float.parseFloat(campoImpostoEstadual.getText()) / 100) * Float.parseFloat(campoPrecoUnitario.getText());
        campoImpostoEstadual.setText(String.valueOf(ResultadoIE));

        float Custos = Float.parseFloat(campoPrecoUnitario.getText()) + ResultadoICMS + ResultadoIF + ResultadoIPI + ResultadoIE + ResultadoFrete;
        float PrecoVenda = Custos + ResultadoGanhoLivre;
        campoCustosGerais.setText(String.valueOf(Custos));
        campoPrecoVenda.setText(String.valueOf(PrecoVenda));
    }
    public boolean validadorSoNumeros(){
        if(!campoPrecoUnitario.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[1]=true; camposInvalidos=true;campoPrecoUnitario.setForeground(Color.RED);}else campoPrecoUnitario.setForeground(Color.BLACK);
        if(!campoImpostoFederal.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[2]=true; camposInvalidos=true;campoImpostoFederal.setForeground(Color.RED);}else campoImpostoFederal.setForeground(Color.BLACK);
        if(!campoImpostoEstadual.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[3]=true; camposInvalidos=true;campoImpostoEstadual.setForeground(Color.RED);}else campoImpostoEstadual.setForeground(Color.BLACK);
        if(!campoIPI.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[4]=true; camposInvalidos=true;campoIPI.setForeground(Color.RED);}else campoIPI.setForeground(Color.BLACK);
        if(!campoICMS.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[5]=true; camposInvalidos=true;campoICMS.setForeground(Color.RED);}else campoICMS.setForeground(Color.BLACK);
        if(!campoFrete.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[6]=true; camposInvalidos=true;campoFrete.setForeground(Color.RED);}else campoFrete.setForeground(Color.BLACK);
        if(!campoGanhoLivre.getText().replace(".", "").replace(",", "").matches("\\d+")){campoInvalido[7]=true; camposInvalidos=true;campoGanhoLivre.setForeground(Color.RED);}else campoGanhoLivre.setForeground(Color.BLACK);
        
        if(camposInvalidos){
            JOptionPane.showMessageDialog(null,"Por favor, insira apenas números nos campos de porcentagem ou reais");
            if(campoInvalido[1]){
                    
                    campoPrecoUnitario.grabFocus();campoPrecoUnitario.selectAll();
                }else{
                    if(campoInvalido[2]){
                        campoImpostoFederal.grabFocus();campoImpostoFederal.selectAll();
                        
                    }else{
                        if(campoInvalido[3]){
                            
                            campoImpostoEstadual.grabFocus();campoImpostoEstadual.selectAll();
                        }else{
                            if(campoInvalido[4]){
                                
                                campoIPI.grabFocus();campoIPI.selectAll();
                            }else{
                                if(campoInvalido[5]){
                                    
                                    campoICMS.grabFocus();campoICMS.selectAll();
                                }else{
                                    if(campoInvalido[6]){
                                        
                                        campoFrete.grabFocus();campoFrete.selectAll();
                                    }else{
                                        if(campoInvalido[7]){
                                            
                                            campoGanhoLivre.grabFocus();campoGanhoLivre.selectAll();
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
    public boolean existeCampoVazio(){
        if(campoPrecoUnitario.getText().isEmpty()){campoVazio[1]=true; camposVazios=true;}
        if(campoImpostoFederal.getText().isEmpty()){campoVazio[2]=true; camposVazios=true;}
        if(campoImpostoEstadual.getText().isEmpty()){campoVazio[3]=true; camposVazios=true;}
        if(campoIPI.getText().isEmpty()){campoVazio[4]=true; camposVazios=true;}
        if(campoICMS.getText().isEmpty()){campoVazio[5]=true; camposVazios=true;}
        if(campoFrete.getText().isEmpty()){campoVazio[6]=true; camposVazios=true;}
        if(campoGanhoLivre.getText().isEmpty()){campoVazio[7]=true; camposVazios=true;}
        
        if(campoDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Opa! Faltou o Nome do Produto/Material\nPor favor, informe um nome para este produto");
            campoDescricao.grabFocus();
            return true;
        }
        if(camposVazios){
            if(JOptionPane.showConfirmDialog(this, "Opa! Encontrei campos vazios.\nDeseja preencher automaticamente os campos vazios com 0%?\nPara corrigir manualmente, pressione NÃO","Campos vazios encontrados",JOptionPane.YES_NO_OPTION) == 0){
                if (campoPrecoUnitario.getText().isEmpty()) {
                    campoPrecoUnitario.setText("0");
                }
                if (campoImpostoFederal.getText().isEmpty()) {
                    campoImpostoFederal.setText("0");
                }
                if (campoImpostoEstadual.getText().isEmpty()) {
                    campoImpostoEstadual.setText("0");
                }
                if (campoIPI.getText().isEmpty()) {
                    campoIPI.setText("0");
                }
                if (campoICMS.getText().isEmpty()) {
                    campoICMS.setText("0");
                }

                if (campoFrete.getText().isEmpty()) {
                    campoFrete.setText("0");
                }
                if (campoGanhoLivre.getText().isEmpty()) {
                    campoGanhoLivre.setText("0");
                }
                
            }else return true;
            for(int i=1;i<=7;i++){
                campoVazio[i] = false;
            }
            camposVazios=false;
        }
        return false;
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1PrecoUnitario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1PrecoUnitario1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        botaoAlterar = new javax.swing.JButton();
        campoDescricao = new javax.swing.JTextField();
        campoPrecoUnitario = new javax.swing.JTextField();
        campoImpostoFederal = new javax.swing.JTextField();
        campoImpostoEstadual = new javax.swing.JTextField();
        campoIPI = new javax.swing.JTextField();
        campoICMS = new javax.swing.JTextField();
        campoFrete = new javax.swing.JTextField();
        campoGanhoLivre = new javax.swing.JTextField();
        campoCustosGerais = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        campoPrecoVenda = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Precificação JBvox Óleos - Alterar Tabela");
        setResizable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setText("Digite o percentual do Ganho Livre:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 0));
        jLabel10.setText("Custos Gerais:");

        jLabel12.setText("%");

        jLabel13.setText("%");

        jLabel14.setText("%");

        jLabel15.setText("R$");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1.setText("Digite o percentual do Imposto federal:");

        jLabel1PrecoUnitario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1PrecoUnitario.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1PrecoUnitario.setText("Digite aqui o Preço Unitário do Produto:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 0));
        jLabel3.setText("Digite o percentual do IPI:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("Digite o percentual do Imposto Estadual:");

        jLabel1PrecoUnitario1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1PrecoUnitario1.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1PrecoUnitario1.setText("Digite aqui a descrição do Produto:");

        jLabel6.setText("%");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 0));
        jLabel5.setText("Digite o percentual do Frete:");

        jLabel8.setText("%");

        jLabel7.setText("%");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 0));
        jLabel4.setText("Digite o percentual dos Outros Custos");

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(51, 51, 0));
        jTextField2.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(102, 255, 255));
        jTextField2.setText("                                        Alterar Tabela");

        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        campoDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoDescricaoActionPerformed(evt);
            }
        });

        campoPrecoUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoPrecoUnitarioActionPerformed(evt);
            }
        });

        campoImpostoFederal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoImpostoFederalActionPerformed(evt);
            }
        });

        campoImpostoEstadual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoImpostoEstadualActionPerformed(evt);
            }
        });

        campoIPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIPIActionPerformed(evt);
            }
        });

        campoICMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoICMSActionPerformed(evt);
            }
        });

        campoFrete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFreteActionPerformed(evt);
            }
        });

        campoGanhoLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoGanhoLivreActionPerformed(evt);
            }
        });

        campoCustosGerais.setEditable(false);
        campoCustosGerais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCustosGeraisActionPerformed(evt);
            }
        });

        jLabel16.setText("R$");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 204, 0));
        jLabel17.setText("Preço de Venda:");

        campoPrecoVenda.setEditable(false);

        jLabel18.setText("R$:");

        categoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        categoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                categoriaKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 204, 0));
        jLabel19.setText("Categoria:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1PrecoUnitario)
                            .addComponent(jLabel17)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1PrecoUnitario1)
                                .addGap(1, 1, 1))
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(campoICMS, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                    .addComponent(campoImpostoEstadual, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(campoPrecoUnitario, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(campoImpostoFederal, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(campoIPI, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(campoFrete)
                                                    .addComponent(campoGanhoLivre)
                                                    .addComponent(campoCustosGerais))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(categoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(17, 17, 17)))))
                .addGap(17, 17, 17))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1PrecoUnitario1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1PrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addComponent(campoPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoImpostoFederal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoImpostoEstadual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoIPI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoICMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoFrete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoGanhoLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(campoCustosGerais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(campoPrecoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campoDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoDescricaoActionPerformed
        campoPrecoUnitario.grabFocus();
        campoPrecoUnitario.selectAll();
    }//GEN-LAST:event_campoDescricaoActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
      
        
        if(existeCampoVazio())return;
        if(!validadorSoNumeros())return;
        
        recalcularValores();
        conexao.connection().conecta();
        
     // Descricao, Preco, ImpostoFederal, ImpostoEstadual, IPI, ICMS, Frete, GanhoLivre, CustosGerais, PrecoVenda) 
        
        String comando = "UPDATE producao SET " 
          + "Descricao = '" + this.campoDescricao.getText() + "'," 
          + "Preco = '" + this.campoPrecoUnitario.getText() + "'," 
          +      "ImpostoFederal = '" + this.campoImpostoFederal.getText() + "'," 
          +      "ImpostoEstadual = '" + this.campoImpostoEstadual.getText() + "'," 
          +      "IPI = '" + this.campoIPI.getText() + "'," 
          +      "ICMS = '" + this.campoICMS.getText() + "'," 
          +      "Frete = '" + this.campoFrete.getText() + "'," 
          +      "GanhoLivre = '" + this.campoGanhoLivre.getText() + "'," 
          +       "CustosGerais = '" + this.campoCustosGerais.getText() + "'," 
          +       "PrecoVenda = '" + this.campoPrecoVenda.getText() + "',"
          +       "Categoria = '" + categoria.getSelectedItem().toString() + "'" 
          +      "WHERE(codigo) = '" +  this.codigo + "'";
        
        
        
        conexao.connection().executeSQLUpdate(comando);
        
        conexao.connection().desconecta();

        DistOleos.getSharedApplication().carregarTabela();
        dispose();
        JOptionPane.showMessageDialog(this, "Dados atualizados com SUCESSO", campoDescricao.getText(), JOptionPane.INFORMATION_MESSAGE, null);
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void campoPrecoUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoPrecoUnitarioActionPerformed
        campoImpostoFederal.grabFocus();
        campoImpostoFederal.selectAll();// TODO add your handling code here:
    }//GEN-LAST:event_campoPrecoUnitarioActionPerformed

    private void campoImpostoFederalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoImpostoFederalActionPerformed
        campoImpostoEstadual.grabFocus();
        campoImpostoEstadual.selectAll();// TODO add your handling code here:
    }//GEN-LAST:event_campoImpostoFederalActionPerformed

    private void campoImpostoEstadualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoImpostoEstadualActionPerformed
        campoIPI.grabFocus();
        campoIPI.selectAll();// TODO add your handling code here:
    }//GEN-LAST:event_campoImpostoEstadualActionPerformed

    private void campoIPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIPIActionPerformed
        campoICMS.grabFocus();
        campoICMS.selectAll();// TODO add your handling code here:
    }//GEN-LAST:event_campoIPIActionPerformed

    private void campoICMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoICMSActionPerformed
        campoFrete.grabFocus();
        campoFrete.selectAll();// TODO add your handling code here:
    }//GEN-LAST:event_campoICMSActionPerformed

    private void campoFreteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFreteActionPerformed
        campoGanhoLivre.grabFocus();
        campoGanhoLivre.selectAll();// TODO add your handling code here:
    }//GEN-LAST:event_campoFreteActionPerformed

    private void campoGanhoLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoGanhoLivreActionPerformed
        categoria.grabFocus();// TODO add your handling code here:
    }//GEN-LAST:event_campoGanhoLivreActionPerformed

    private void categoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoriaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            botaoAlterar.grabFocus();
        }
    }//GEN-LAST:event_categoriaKeyPressed

    private void campoCustosGeraisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCustosGeraisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCustosGeraisActionPerformed

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
            java.util.logging.Logger.getLogger(AlterarTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlterarTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlterarTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlterarTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JTextField campoCustosGerais;
    private javax.swing.JTextField campoDescricao;
    private javax.swing.JTextField campoFrete;
    private javax.swing.JTextField campoGanhoLivre;
    private javax.swing.JTextField campoICMS;
    private javax.swing.JTextField campoIPI;
    private javax.swing.JTextField campoImpostoEstadual;
    private javax.swing.JTextField campoImpostoFederal;
    private javax.swing.JTextField campoPrecoUnitario;
    private javax.swing.JTextField campoPrecoVenda;
    private javax.swing.JComboBox categoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
