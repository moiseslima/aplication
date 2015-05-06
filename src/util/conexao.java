package util;
import java.sql.*;
import java.util.ResourceBundle;
import javax.swing.*;

public class conexao
{
    
    private ResourceBundle bundle = ResourceBundle.getBundle("bancoDistOleos");

        final private String driver = bundle.getString("driver");
        
        final private String url = bundle.getString("url");
        final private String usuario = bundle.getString("user");
        final private String senha = bundle.getString("password");
        private Connection conexao;
        public Statement statement;
        public ResultSet resultset;

        public static conexao conec;     
        public static conexao connection() 
        {
            if (conec == null)
                conec = new conexao();
            return conec;
        }
        
       public void conecta(){
            try{
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, usuario, senha);
            }
            catch(ClassNotFoundException Driver){
               JOptionPane.showMessageDialog(null,"Driver não localizado: "+Driver);
            }
            catch(SQLException Fonte){
                JOptionPane.showMessageDialog(null,"Deu erro na conexão "+
                        "com a fonte de dados: "+Fonte);
            }
            
       }
       
       public void desconecta(){
            try{
                conexao.close();
            }catch(SQLException fecha){
                JOptionPane.showMessageDialog(null,"Não foi possivel "+
                        "fechar o banco de dados: "+fecha);
            }
       }
       
       public ResultSet executeSQL(String sql){
            try{
                statement = conexao.createStatement();
                resultset = statement.executeQuery(sql);  
            }catch(SQLException sqlex){
               JOptionPane.showMessageDialog(null,"Não foi possível "+
                       "executar o comando sql,"+sqlex+", o sql passado foi "+sql);
            }return resultset;
       }      
}