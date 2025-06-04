/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anita
 */
public class palabrasToken {
    
     String cadena;
      public List<String> palabras = new ArrayList<String>();
      
   
        
        public void obtenerAllWords() throws IOException
        {
           lectorCaracteres lector= new lectorCaracteres("Programita.txt");
            //lectorCaracteres lector= new lectorCaracteres("Programita (2).txt");
            
            cadena=lector.token();
           while(cadena!="finalizado")
           {
               palabras.add(cadena);
              //System.out.println("Cadena: "+ cadena); 
               cadena=lector.token();
           }
           
           palabras.add("finalizado");
       
        }
        
        public void ShowAllWords() throws IOException
        {
           for(int i=0; i<palabras.size(); i++)
           {
               System.out.println(palabras.get(i));
           }
       
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

    public boolean isIdentificador(String cadena) {
    
        String cade=cadena;
        
        if((int)cade.charAt(0)>=65 && (int)cade.charAt(0)>=90)
        {
            for(int i=1; i<cade.length(); i++)
            {
                if((int)cade.charAt(i)>=97 && (int)cade.charAt(i)>=122)
                {
                    
                }
                else
                {
                    return false;
                }
            }
        }
        
        
        return true;
    }

    public boolean isCorrectValue(String valor, String tipo) {
    
        if(tipo.equals("<Entero>"))
        {
            boolean isNumeric =  valor.matches("[+-]?\\d*(\\.\\d+)?");
                        
                        if(isNumeric)
                        {
                            System.out.println("ES NUMERO");
                           
                        }
                        else
                        {
                            System.out.println("no es numero");
                        }
        }
        
        return true;
    }

    boolean isNumerico(String cadena) {
        
        //boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
        boolean isNumeric =  cadena.matches("[+-]?\\d*(\\d+)?");
        
        if(isNumeric)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    

    
    boolean isFlotante(String cadena) {
        
        boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
        
        if(isNumeric)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    boolean areYouBulleado(String cadena) {
        
        String ca=cadena;
        
        if(ca.equals("0") || ca.equals("1"))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
        
        
       
       
    
}
