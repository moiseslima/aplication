/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import javax.swing.JOptionPane;

/**
 *
 * @author Assis Andrade
 */
public class PenDrive {
     public String procuraUnidadePenDrive(){
        String unidade="Z";
        boolean encontrou = false;
        for (FileStore store : FileSystems.getDefault().getFileStores()) {
            if(store.type().contains("FAT32")){
                unidade = store.toString().substring(store.toString().indexOf("(")+1, store.toString().indexOf(")"));
                encontrou = true;
                }else{
                    if(store.type().contains("FAT")){
                    unidade = store.toString().substring(store.toString().indexOf("(")+1, store.toString().indexOf(")"));
                    encontrou = true;
                }
            }
        }
        if(!encontrou){
            JOptionPane.showMessageDialog(null, "PEN DRIVE NÃO CONECTADO. BACKUP SALVO SOMENTE EM C:", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"Backup realizado com SUCESSO!");
        }
        return unidade;
    }
}

