/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
/**
 *
 * @author z
 */
public class BackupRestauracao {


  // Constantes da classe
 

  private static String SEPARATOR = File.separator;
  private String os;
 

  private static String MYSQL_PATH =

    "C:" + SEPARATOR +

    "Arquivos de programas" + SEPARATOR +

    "MySQL" + SEPARATOR +

    "MySQL Server 5.0" + SEPARATOR +

    "bin" + SEPARATOR;

 


 

  // Lista dos bancos de dados a serem "backupeados"; se desejar adicionar mais,

  // basta colocar o nome separado por espaços dos outros nomes

  private static String DATABASES =

   // "agenda cultos webcheckadmin projectmanager calendario webfinance mysql";
   "armazenamento";
 

  private List<String> dbList = new ArrayList<String>();

 

  public void fazerBackup(){
    os = (new distoleos.ChecaSistemaOperacional().checaSistema());
    String com[] = {"/bin/sh", "-c","mysqldump -u root -pMoises@125 armazenamento > /home/z/1/backupSistema.sql"};

    
    
    //plataforma linux (joao):
    if(os.startsWith("Linux")){
        try {
            Runtime.getRuntime().exec(com);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "FALHA AO REALIZAR BACKUP. Contate (83)-9936-4007 e informe o"
                                        + "\nErro:" + ex.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Backup realizado com SUCESSO");
    }
    //plataforma não linux (windows/moises): 
    else{
        String command = MYSQL_PATH + "mysqldump.exe";
        String[] databases = DATABASES.split(" ");
        for (int i = 0; i < databases.length; i++)
          dbList.add(databases[i]);

        // Contador
        int i = 1;
        // Tempo
        long time1, time2, time;
        // Início
        time1 = System.currentTimeMillis();

        for (String dbName : dbList) {

          ProcessBuilder pb = new ProcessBuilder(
            command,
            "--user=root",
            "--password=Moises@125",
            dbName,
            "--result-file=" + "." + SEPARATOR + "Backup" + SEPARATOR + dbName + ".sql");


          try {
            System.out.println(
              "Backup do banco de dados (" + i + "): " + dbName + " ...");
            pb.start();
          }
          catch (Exception e) {
            e.printStackTrace();
          }
          i++;
        }
        // Fim
        time2 = System.currentTimeMillis();
        // Tempo total da operação
        time = time2 - time1;
        // Avisa do sucesso
        System.out.println("\nBackups realizados com sucesso.\n\n");

        System.out.println("Tempo total de processamento: " + time + " ms\n");

        System.out.println("Finalizando...");

    }
        try {
          Thread.sleep(2000);
        }
        catch (Exception e) {}
    
  }

    public void fazerRestauracao(){
      
    }
     
}
 

