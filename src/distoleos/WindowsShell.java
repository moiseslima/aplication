/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;

import java.io.IOException;  
import java.util.ArrayList;  
import java.util.Arrays;
import javax.swing.JOptionPane;
   
public class WindowsShell {  
   
    //CLASSE PARA FAZER BACKUP DO MYSQL NO WINDOWS
    public void executeCommand(final String ... inputs){  
           
        final ArrayList<String> commands = new ArrayList<>();  
        commands.add("c:/windows/system32/cmd.exe");   
        commands.add("/c");  
        commands.addAll(Arrays.asList(inputs));        
        try {                         
            final ProcessBuilder p = new ProcessBuilder(commands);  
            p.start();
            
        } catch (IOException ex) {  
            JOptionPane.showMessageDialog(null, "FALHA AO REALIZAR BACKUP. Contate (83)-9936-4007 e informe o"
                                            + "\nErro:" + ex.getMessage());
        }
    }   
}  