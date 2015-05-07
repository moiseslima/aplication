/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;


/**
 *
 * @author z
 */
public class ChecaSistemaOperacional {
    public String sistema;
    
    public String checaSistema() {
        sistema = System.getProperty("os.name");
        return sistema;
    }
}
