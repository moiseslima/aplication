/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distoleos;

/**
 *
 * @author moises
 */
public class DistOleos {

    private static visao _app;
    public static visao getSharedApplication() 
    {
        if (_app == null)
            _app = new visao();
        return _app;
    }
    public static void main(String[] args) {
        // TODO code application logic here
         //new visao().setVisible(true);
         getSharedApplication().setVisible(true);
    }

      
}
