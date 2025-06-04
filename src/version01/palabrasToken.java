/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anita
 */
public class palabrasToken {
    
     String cadena;
      List<String> palabras = new ArrayList<String>();
      
   
        
        public void obtenerAllWords() throws IOException
        {
           lectorCaracteres lector= new lectorCaracteres("alfanumericos.txt");
            cadena=lector.token();
           while(cadena!="finalizado")
           {
               palabras.add(cadena);
             //  System.out.println("Cadena: "+ cadena); 
               cadena=lector.token();
           }
           
           palabras.add("finalizado");
       
      
       
        }

   public String obtenerPalabra(int index) {
       String c = palabras.get(index);
        return c;
    }

    public List<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(List<String> palabras) {
        this.palabras = palabras;
    }
        
        
       
       
    
}
