/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;

import java.io.IOException;
import javax.swing.JOptionPane;
import util.PenDrive;
/**
 *
 * @author z
 */
public class BackupRestauracao {
    private String os;

    public void fazerBackup(){
        os = (new distoleos.ChecaSistemaOperacional().checaSistema());
        String com[] = {"/bin/sh", "-c","mysqldump -u root -pMoises@125 armazenamento > backupSistema.sql"};
        
        //plataforma linux (joao):
        if(os.startsWith("Linux")){
            try {
                Runtime.getRuntime().exec(com);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "FALHA AO REALIZAR BACKUP. Contate (83)-9988-1960 ou pelo email moisaport@hotmail.com e informe o"
                                            + "\nErro:" + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Backup realizado com SUCESSO");
        }
        
        //plataforma não linux (windows/moises): 
        else{
            new WindowsShell().executeCommand("C:\\mysqldump.exe", "--user=root", "--password=Moises@125", "armazenamento", "--result-file=C:\\backupSistema.sql");
            new WindowsShell().executeCommand("C:\\mysqldump.exe", "--user=root", "--password=Moises@125", "armazenamento", "--result-file="+
                                                                                                                                                     new PenDrive().procuraUnidadePenDrive()+ "\\backupSistema.sql");
        }
    }
 

}