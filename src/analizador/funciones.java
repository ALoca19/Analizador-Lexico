/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anita
 */
public class funciones {
   lectorCaracteres lector;//= new lectorCaracteres("alfanumericos.txt");
   String cadena; //Cadena donde se guardara el token para comparar
   
   validacionOperacion operacion; 
   List<validacionOperacion> nombreOperaciones = new ArrayList<validacionOperacion>();
   String operacionAuxiliar="";
   int operacionEntero=0;
   
   
    palabrasToken cad = new palabrasToken();
    int contador=0;//Contador pa palabras
   
   List<String> nombreFunciones = new ArrayList<String>(); //Nombre de las Funciones
   List<variables> nombreVariable = new ArrayList<variables>(); //Nombre de las Funciones
   //List<String> palabras = palabrasT.getPalabras();
   
   public void inicioLeer() throws IOException
   {
        cad.obtenerAllWords();
        PROGRAMA();
   }
    


   //<PROGRAMA> ::= Func /<NumEntero> IDENT <CAJA> <aux0> 
   //FIRST(PROGRAMA)::=Func 
    //<PROGRAMA> ::= Func /<NumEntero> IDENT <CAJA> <aux0> 
   //FIRST(PROGRAMA)::=Func 
    public void PROGRAMA() throws IOException
    {
        
        //lector= new lectorCaracteres("alfanumericos.txt");
        //cadena=lector.token();
       
        cadena=cad.obtenerPalabra(contador);
        contador++;
        if(cadena.equals("Func"))
        {
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
            if(cadena.equals("<NumEntero>"))
            {
                //cadena=lector.token();
                cadena=cad.obtenerPalabra(contador);
                contador++;
       
       
               
                if(cad.isIdentificador(cadena))
                {
                    
                    nombreFunciones.add(cadena);
                    CAJA();
                    //<aux0> ::=  ε  
                    //<aux0> ::= <programa> 
                    //FOLLOW(aux0)::=<Programa> 
                    AUX0();
                }
                else
                {
                    System.out.println("Error 01");//ident
                }
            }
            else
                {
                    System.out.println("Error 02");//NumEnero
                }
        }
        else
                {
                    System.out.println("Cadena: "+cadena);
                    System.out.println("Error 03");//func
                }
    }
    
    
    //<aux0> ::=  ε  
    //<aux0> ::= <programa> 
    //FOLLOW(aux0)::=<Programa> 
    private void AUX0() throws IOException 
    {
        //cadena=lector.token();
         cadena=cad.obtenerPalabra(contador);
          contador++;
        
          
//        if(!cadena.equals("Func"))
//        {
//            PROGRAMA();
//            //System.out.println("Terminado");
//        }
//        else
//        {
//            contador--;
//            cadena=cad.obtenerPalabra(contador);
//            
//        }

        if(cadena.equals("Func"))
        {
            contador--;
            PROGRAMA();
            //System.out.println("Terminado");
        }
        else
        {
            System.out.println("Terminado");
            
        }
        
    }
    
    //<CAJA> ::= $  <BLOQUE>  $$ 
    //FIRST(CAJA)::=$   
    public void CAJA() throws IOException
    {
        //cadena=lector.token();
         
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
         
        if(cadena.equals("$"))
        {
            
            BLOQUE();
          
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
            
            if(!cadena.equals("$$"))
            {
               System.out.println("Error 04");//no se cerro 
            }
        }
        else
        {
            System.out.println("Error 05");
        }
        
    }

    //<BLOQUE> ::= <VARIABLE><ITERACIONES><OPERACIONES> Adios ; 
    
    //FIRST(CAJA)::= FIRST(VARIABLE) = {“/<Entero>/”+”/<Cadena>/  ”+” /<Decimal>/  ”+” /<Buliado>/  ”+” /<NumEntero>/  ”} 
    private void BLOQUE() throws IOException {
        VARIABLES();
        INTERACIONES();
        OPERACIONES();
        
        
        
        //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
       
        if(cadena.equals("Adios"))
        {
             
            //cadena=lector.token();
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
            if(!cadena.equals(";"))
            {
                System.out.println("Error 06");
            }
        }
        else
        {
            System.out.println("Error 07");
        }
        
    }

    /*
    

    FOLLOW(VARIABLES)=FIRST(ITERACIONES);
    */
    private void VARIABLES() throws IOException {
        //cadena=lector.token();
          
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
        String tipo="", ident="", valor="";
        variables var= new variables(tipo, ident, valor);
         //<VARIABLE> ::= /<Entero>/ IDENT =! NUM; <VARIABLE>
        
        if(cadena.equals("<Entero>"))
        {
            var.setTipo("<Entero>");
            //cadena=lector.token();
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
                if(cad.isIdentificador(cadena) && freeIdent(cadena))
                {
                    
                    var.setNombre(cadena);
                    //cadena=lector.token();
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                    
                    if(cadena.equals("=!"))
                    {
                        //cadena=lector.token(); //DEBE DE HABER UN ENTERO
                        
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        
                        if(cad.isNumerico(cadena)) //valida que es numero
                        {
                            var.setValor(cadena);
                            nombreVariable.add(var);
                            cadena=cad.obtenerPalabra(contador);
                            contador++;
                            if(cadena.equals(";"))
                            {
                                
                                VARIABLES();
                            }  
                        }
                        else
                        {
                            System.out.println("Error 08");
                        }
                        
                    }
                }
                else
                {
                    System.out.println("Error 09");
                }
        }
        //<VARIABLE> ::= /<Cadena>/  IDENT  =!  “ palabra  ”  ; <VARIABLE>
        else if(cadena.equals("<Cadena>"))  
        {
           
            var.setTipo("<Cadena>");
            //cadena=lector.token();
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
                if(cad.isIdentificador(cadena) && freeIdent(cadena))
                {
                    
                    var.setNombre(cadena);
                    //cadena=lector.token();
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                    
                    if(cadena.equals("=!"))
                    {
                        
                        //cadena=lector.token(); //DEBE DE IDENTIFICAR LA PALABRA
                       
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                    
                        if(isCadena(cadena, var))
                        {
                            
                            nombreVariable.add(var);
                            cadena=cad.obtenerPalabra(contador);
                            contador++;
                            
                            
                            if(cadena.equals(";"))
                            {
                                VARIABLES();
                            }
                            
                            
                        }
                        
                    }
                }
                else
                {
                    System.out.println("Error");
                }
            
        }
        //<VARIABLE> ::= /<Decimal>/  IDENT  =!  NUM ; <VARIABLE>
        else if(cadena.equals("<Decimal>")) 
        {
            var.setTipo("<Decimal>");
            //cadena=lector.token();
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
                if(cad.isIdentificador(cadena) && freeIdent(cadena))
                {
                    var.setNombre(cadena);
                    //cadena=lector.token();
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                    
                    if(cadena.equals("=!"))
                    {
                        //cadena=lector.token(); //DEBE DE HABER UN ENTERO
                        
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        
                        boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
                        
                        if(cad.isFlotante(cadena))
                        {
                            var.setValor(cadena);
                            nombreVariable.add(var);
                      
                            //cadena=lector.token();
                            cadena=cad.obtenerPalabra(contador);
                            contador++;
                            
                            if(cadena.equals(";"))
                            {
                                VARIABLES();
                            }
                        }
                        else
                        {
                            System.out.println("Error");
                        }
                    }
                }
                else
                {
                    System.out.println("Error");
                }
            
        }
        //<VARIABLE> ::= /<Buliado>/  IDENT  =!  0|1  ; <VARIABLE>
        else if(cadena.equals("<Buliado>"))
        { 
            var.setTipo("<Buliado>");
            //cadena=lector.token();
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
                if(cad.isIdentificador(cadena) && freeIdent(cadena))
                {
                    var.setNombre(cadena);
                    //cadena=lector.token();
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                    
                    if(cadena.equals("=!"))
                    {
                        
                        //cadena=lector.token(); //0 o 1
                          
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        
                        nombreVariable.add(var);
                        if(cad.areYouBulleado(cadena))
                        {
                            var.setValor(cadena);
                            nombreVariable.add(var);
                            //cadena=lector.token();
                            
                            cadena=cad.obtenerPalabra(contador);
                            contador++;
                            
                            if(cadena.equals(";"))
                            {
                                VARIABLES();
                            }
                        }
                        else
                        {
                            System.out.println("Error");
                        }

                    }
                }
                else
                {
                    System.out.println("Error");
                }
            
            
        }
        //<VARIABLE> ::= /<NumEntero>/  IDENT  =!  NUM  ; <VARIABLE>
        else if(cadena.equals("<NumEntero>"))
        { 
            var.setTipo("<NumEntero>");
            //cadena=lector.token();
            
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
                if(cad.isIdentificador(cadena) && freeIdent(cadena))
                {
                    var.setNombre(cadena);
                    //cadena=lector.token();
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
            
                    if(cadena.equals("=!"))
                    {
                        //cadena=lector.token(); //DEBE DE HABER UN ENTERO
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        
                        if(cad.isNumerico(cadena))
                        {
                            var.setValor(cadena);
                            nombreVariable.add(var);
                            //cadena=lector.token();
                            cadena=cad.obtenerPalabra(contador);
                            contador++;
                            if(cadena.equals(";"))
                            {
                                VARIABLES();
                            }
                        }
                        else
                        {
                            System.out.println("Error");
                        }
                    }
                }
                else
                {
                    System.out.println("Error");
                }
            
        }
        //FOLLOW(VARIABLES)=FIRST(ITERACIONES);
        else
        {
            contador--;
            INTERACIONES();
        }
        
 
    }
    /**/





/**/

//    <INTERACCIONES> ::= ε 
//    FIRST(INTERACCIONES) ::= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
//    FOLLOWS(INTERACCIONES)=FIRST(INTERACCIONES)= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
    /**/
    private void INTERACIONES() throws IOException {
        
        //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
       
        
//    <INTERACCIONES> ::= Cataficcia  (  IDENT  )  $  < SELECCION>  $$         
        if(cadena.equals("Cataficcia"))
        {
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
            
            if(cadena.equals("("))
            {
                //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
                
                if(isValidado())
                {
                    claseCataficcia cata = new claseCataficcia("", "", "0", "", -1, false);
                    cata.setNombre(cadena);
                    cata.setValor(nombreVariable.get(buscarElemento(cadena)).getValor());
                   
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                    
                    if(cadena.equals(")"))
                    {
                        //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
                        if(cadena.equals("$"))
                        {
                            cadena=cad.obtenerPalabra(contador);
                            contador++;
                            SELECCION(cata);
                            
                            if(cadena.equals("$$"))
                            {
                                INTERACIONES();
                            }
                        }
                        
                    }
                }
                else
                {
                    System.out.println("Error");
                }
            }
            else
            {
                System.out.println("Error");
            }
            
                
        }
        //    <INTERACCIONES> ::= Mufasa  ( <INICIADOR>  <CONDICION>  IDENT  <INCREODECRE>  )   <CAJA>
        else if(cadena.equals("Mufasa"))
        { 
           //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
            
            if(cadena.equals("("))
            {
                
                operacionFor variableFor = new operacionFor("", "", "","","",-1);
                
                INICIADOR(variableFor);
                CONDICION(variableFor);
                
                    //cadena=lector.token();
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                   

                    if(cadena.equals(variableFor.getNombre()))
                    {
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        INCREOGRECRE(variableFor);


                        if(cadena.equals(")"))
                        {
                            int auxContador=contador; //para regresar el contador
//                            System.out.println("For: ");
//                            System.out.println(variableFor.toString());
//                            System.out.println("Entero de for: "+Integer.parseInt(variableFor.getValor()));
//                            System.out.println("Entero de variable: "+Integer.parseInt(nombreVariable.get(variableFor.getVariableComparable()).getValor()));

                            if(variableFor.getMasomenos().equals("++") && variableFor.getOperacionFor().equals(">=!") && (Integer.parseInt(variableFor.getValor()) < Integer.parseInt(nombreVariable.get(variableFor.getVariableComparable()).getValor())))
                            {
                                    for(int i=Integer.parseInt(variableFor.getValor()); i<Integer.parseInt(nombreVariable.get(variableFor.getVariableComparable()).getValor()); i++)
                                    {
                                    contador=auxContador;
                                    BLOQUE();  
                                    }
                                    
                            }
                            else if(variableFor.getMasomenos().equals("--") && variableFor.getOperacionFor().equals("<=!") && (Integer.parseInt(variableFor.getValor()) > Integer.parseInt(nombreVariable.get(variableFor.getVariableComparable()).getValor())))
                            
                            {
                                for(int i=Integer.parseInt(variableFor.getValor()); i>Integer.parseInt(nombreVariable.get(variableFor.getVariableComparable()).getValor()); i--)
                                    {
                                    contador=auxContador;
                                    BLOQUE();  
                                    }
                            }
                            else if(variableFor.getOperacionFor().equals("=!=!"))
                            {
                                if(variableFor.getMasomenos().equals("--") && variableFor.getOperacionFor().equals("<=!"))
                                {
                                    for(int i=Integer.parseInt(variableFor.getValor()); i==Integer.parseInt(nombreVariable.get(variableFor.getVariableComparable()).getValor()); i--)
                                    {
                                    contador=auxContador;
                                    BLOQUE();  
                                    }
                                }
                                else if(variableFor.getMasomenos().equals("++") && variableFor.getOperacionFor().equals(">=!"))
                                {
                                    for(int i=Integer.parseInt(variableFor.getValor()); i==Integer.parseInt(nombreVariable.get(variableFor.getVariableComparable()).getValor()); i++)
                                    {
                                    contador=auxContador;
                                    BLOQUE();  
                                    }
                                }
                                else
                                {
                                    System.out.println("Error en asignar condicion");
                                }
                            }
                            else
                            {
                                System.out.println("Error al declarar el for");
                            }
                            
//                            for(int i=0; i<5; i++)
//                            {
//                                contador=auxContador;
//                                BLOQUE();  
//                            }

                            //CAJA();
                            //INTERACIONES();
                        }
                    }
                    else
                    {
                        System.out.println("Error");
                    }
                    
               
                
            }
            else
            {
                System.out.println("Error");
            } 
        }
        //    <INTERACCIONES> ::= Ontas <FUNCION>; (LLAMA A LA FUNCION YA CREADA)
        else if(cadena.equals("Ontas"))
        { 
            FUNCION();
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
            if(cadena.equals(";"))
            {
                INTERACIONES();
            }
            
        }
        //    <INTERACCIONES> ::= Hayteva>> INDENT <MOSTRAR>; (ACCION MUESTRA A LA PANTALLA) 
        else if(cadena.equals("Hayteva>>"))
        { 
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
              
                if(isValidado())
                {
                    MOSTRAR(cadena);
                    
                    //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
                    
                    if(cadena.equals(";"))
                    {
                        INTERACIONES();
                    }
                }
                else
                {
                    System.out.println("Error");
                }
        }
        //    <INTERACCIONES> ::= Dime<< <LEER DATO> <ASIGNAR> IDENT; (LEE DATO QUE SE ASIGNA)
        else if(cadena.equals("Dime<<"))
        { 
           
            String dato = LEERDATOS();
            
            ASIGNAR(dato);
            
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
                    if(cadena.equals(";"))
                    {
                        INTERACIONES();
                    }
            
        }
        
        else
        {
            contador--;
            OPERACIONES();
        }
        
        //    <INTERACCIONES> ::= ε 
        //    FIRST(INTERACCIONES) ::= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
        //    FOLLOWS(INTERACCIONES)=FIRST(INTERACCIONES)= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
        //MISMA DUDA QUE EL DE VARIABLES, SI DEJAMOS ESTE IF O ESTA BIEN SOLO CON EL ELSE DE ARRIB
        //perdone las mayusculas
//        if(cadena!="Cataficcia" && cadena!="Mufasa" && cadena!="Ontas" && cadena!="Hayteva>>" && cadena!="Dime<<" )
//        {
//            INTERACIONES();
//        }
//        else
//        {
//            contador--;
//            cadena=cad.obtenerPalabra(contador);
//        }
    
    }
    
//    <SELECCION> ::= Escoge  <CONDICION> :  $  <BLOQUE> ADIOS  ;  $$ <AUX4>
//    <AUX4>::= <SELECCION>
//    <AUX4>= ε
//    FIRST(SELECCION) ::= Escoge
//    FOLLOW(aux4)::=FIRS(SELECCION)
    
     private void SELECCION(claseCataficcia cata) throws IOException 
    {
        //cadena=lector.token();
        
        if(cadena.equals("Escoge"))
        {
            //CONDICION(cadena);
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
            CONDICION(cata);
            
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
            //System.out.println("Cata: "+cata.getValor());
            //System.out.println("Variable Secundaria: "+nombreVariable.get(cata.elementoSecundario).getValor());
           
            //AQUI DEBE DE HACER LA VALIDACION DEL ESCOGE
            if(cata.getOperacion().equals("=!=!"))
            {
                if(!cata.isSeRealizo()) //Si es falso
                {
                    if(Double.parseDouble(cata.getValor()) == Double.parseDouble(nombreVariable.get(cata.elementoSecundario).getValor()))
                    {
                        //System.out.println("Tienen el mismo dato");
                        cata.setSeRealizo(true);
                        
                        
                        
                                    if(cadena.equals(":"))
                                    {

                                        //cadena=lector.token();
                                cadena=cad.obtenerPalabra(contador);
                                contador++;

                                        if(cadena.equals("$"))
                                        {

                                            BLOQUE();

                                cadena=cad.obtenerPalabra(contador);
                                contador++;
                                                if(cadena.equals("$$"))
                                                    {
                                                        cadena=cad.obtenerPalabra(contador);
                                                        contador++;
                                                        AUX4(cata);
                                                    }
                                                else
                                                {
                                                    System.out.println("Error al cerrar operacion Cata");
                                                }
                                        }
                                        else
                                        {
                                            System.out.println("Error203");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Error204");
                                    }
                        
                                    
                        
                        
                    }
                    else
                    {
                        //System.out.println("No deberia entrar al igual");
                        while(!cadena.equals("$$"))
                        {
                            
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                         
                        }
                        //System.out.println("ANTES Cadena: "+cadena);
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        //System.out.println("Cadena: "+cadena);
                        AUX4(cata);
                    }
                }
                else
                {
                    //System.out.println("Ya se acompleto una operacion");
                    while(!cadena.equals("$$"))
                        {
                            
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                         
                        }
                        //System.out.println("ANTES Cadena: "+cadena);
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        //System.out.println("Cadena: "+cadena);
                        AUX4(cata);
                }
                
            }
            else if(cata.getOperacion().equals("<=!"))
            {
                if(!cata.isSeRealizo())
                {
                    if(Double.parseDouble(cata.getValor()) > Double.parseDouble(nombreVariable.get(cata.elementoSecundario).getValor()))
                    {
                        //System.out.println("Mayor que");
                        cata.setSeRealizo(true);
                         if(cadena.equals(":"))
                                    {

                                        //cadena=lector.token();
                                cadena=cad.obtenerPalabra(contador);
                                contador++;

                                        if(cadena.equals("$"))
                                        {

                                            BLOQUE();

                                cadena=cad.obtenerPalabra(contador);
                                contador++;
                                                if(cadena.equals("$$"))
                                                    {
                                                        cadena=cad.obtenerPalabra(contador);
                                                        contador++;
                                                        AUX4(cata);
                                                    }
                                                else
                                                {
                                                    System.out.println("Error al cerrar operacion Cata");
                                                }
                                        }
                                        else
                                        {
                                            System.out.println("Error203");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Error204");
                                    }
                    }
                    else
                    {
                        //System.out.println("No deberia entrar al mayor que");
                        while(!cadena.equals("$$"))
                        {
                            
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                         
                        }
                        //System.out.println("ANTES Cadena: "+cadena);
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        //System.out.println("Cadena: "+cadena);
                        AUX4(cata);
                    }
                }
                else
                {
                    //System.out.println("Ya se acompleto una operacion");
                    while(!cadena.equals("$$"))
                        {
                            
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                         
                        }
                     //   System.out.println("ANTES Cadena: "+cadena);
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                     //   System.out.println("Cadena: "+cadena);
                        AUX4(cata);
                }
                
            }
            else if(cata.getOperacion().equals(">=!"))
            {
                if(!cata.isSeRealizo())
                {
                   if(Double.parseDouble(cata.getValor()) < Double.parseDouble(nombreVariable.get(cata.elementoSecundario).getValor()))
                    {
                        //System.out.println("Menor que");
                        if(cadena.equals(":"))
                                    {

                                        //cadena=lector.token();
                                cadena=cad.obtenerPalabra(contador);
                                contador++;

                                        if(cadena.equals("$"))
                                        {

                                            BLOQUE();

                                cadena=cad.obtenerPalabra(contador);
                                contador++;
                                                if(cadena.equals("$$"))
                                                    {
                                                        cadena=cad.obtenerPalabra(contador);
                                                        contador++;
                                                        AUX4(cata);
                                                    }
                                                else
                                                {
                                                    System.out.println("Error al cerrar operacion Cata");
                                                }
                                        }
                                        else
                                        {
                                            System.out.println("Error203");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Error204");
                                    }
                    }
                    else
                    {
                        //System.out.println("No deberia entrar al menor que");
                        
                        while(!cadena.equals("$$"))
                        {
                            
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                         
                        }
                        //System.out.println("ANTES Cadena: "+cadena);
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                       // System.out.println("Cadena: "+cadena);
                        AUX4(cata);
                    } 
                }
                else
                {
                    //System.out.println("Ya se acompleto una operacion");
                    while(!cadena.equals("$$"))
                        {
                            
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                         
                        }
                        //System.out.println("ANTES Cadena: "+cadena);
                        cadena=cad.obtenerPalabra(contador);
                        contador++;
                        //System.out.println("Cadena: "+cadena);
                        AUX4(cata);
                }
                    
                
            }
            
            //DE AQUI FUNCIONA HASTA ....
//            if(cadena.equals(":"))
//            {
//               
//                //cadena=lector.token();
//        cadena=cad.obtenerPalabra(contador);
//        contador++;
//      
//                if(cadena.equals("$"))
//                {
//                    
//                    BLOQUE();
//                    
//                    //cadena=lector.token();
//        cadena=cad.obtenerPalabra(contador);
//        contador++;
////                    System.out.println("Antes del adios: "+cadena);
////                     if(cadena.equals("Adios"))
////                    {
////                   //cadena=lector.token();
////        cadena=cad.obtenerPalabra(contador);
////        contador++;
////                        if(cadena.equals(";"))
////                        {
////                        //cadena=lector.token();
////        cadena=cad.obtenerPalabra(contador);
////        contador++;
//
//                        if(cadena.equals("$$"))
//                            {
//                                cadena=cad.obtenerPalabra(contador);
//                                contador++;
//                                AUX4(cata);
//                            }
//                        else
//                        {
//                            System.out.println("Error al cerrar operacion Cata");
//                        }
////                        }
////                        else
////                        {
////                            System.out.println("Error201");
////                        }
////                    }
////                     else
////                     {
////                      System.out.println("Error202");
////                     }
//                }
//                else
//                {
//                    System.out.println("Error203");
//                }
//            }
//            else
//            {
//                System.out.println("Error204");
//            }
            ///HASTA AQUI FUNCIONA
        }
        else
        {
            System.out.println("Error205");
        }
    }
    
    //    <SELECCION> ::= Escoge  <CONDICION> :  $  <BLOQUE> ADIOS  ;  $$ <AUX4>
//    <AUX4>::= <SELECCION>
//    <AUX4>= ε
//    FIRST(SELECCION) ::= Escoge
//    FOLLOW(aux4)::=FIRS(SELECCION)
    private void AUX4(claseCataficcia cata) throws IOException
    {
        //cadena=lector.token();
        

        if(!cadena.equals("Escoge"))
        {   
            contador--;
            cadena=cad.obtenerPalabra(contador);
        }
        else
        {
            SELECCION(cata);
            
        }
        
    }
    
    //<INICIADOR> ::= /<Entero>/  IDENT  =!  NUM  ;
    private void INICIADOR(variables variableFor) throws IOException {
        
        //cadena=lector.token();
        String dato=null;
        cadena=cad.obtenerPalabra(contador);
        contador++;
            
        if(cadena.equals("<Entero>"))
        {
           variableFor.setTipo(cadena);
          //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
            
            if(cad.isIdentificador(cadena))
                {
                    dato=cadena;
                    variableFor.setNombre(dato);
                    //cadena=lector.token();
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                    
                    if(cadena.equals("=!"))
                    {
                        //cadena=lector.token();//DEBE DE HABER UN ENTERO
                        cadena=cad.obtenerPalabra(contador);
                        contador++; 
                                
                        if(cad.isNumerico(cadena))
                        {
                            variableFor.setValor(cadena);
                            cadena=cad.obtenerPalabra(contador);
                            contador++;
                            
                                   if(!cadena.equals(";"))
                                   {
                                       System.out.println("Error Nada ta chido");
                                       
                                   }
                        }
                         else
                        {
                           System.out.println("Error en mufasa no es numerico");
                           
                         }

                    }
                    else
                    {
                           System.out.println("Error falta igual en mufasa");
                           
                    }
                }
            else
            {
                System.out.println("Error no es cadena para mufasa");
                
            }
                
        }
        else
        {
            System.out.println("Error al declarar mufasa");
            
        }
        
       
        
   }

    //<CONDICION> ::=  IDENT  <IGUALDADES>  IDENT2 <AUX6> ;
    //FIRST(CONDICION) ::= Ident
    private void CONDICION(operacionFor variableFor) throws IOException 
    {
    
        //cadena=lector.token();
       cadena=cad.obtenerPalabra(contador);
       contador++;
        
        if(variableFor.getNombre().equals(cadena))
        {
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        
        IGUALDADES(variableFor);
            
            
        cadena=cad.obtenerPalabra(contador);
        contador++;
                
                if(cadena.equals( nombreVariable.get(buscarElemento(cadena)).getNombre() )  && (nombreVariable.get(buscarElemento(cadena)).getTipo().equals("<Entero>") ||  nombreVariable.get(buscarElemento(cadena)).getTipo().equals("<NumEntero>")))
                {
                    variableFor.setVariableComparable(buscarElemento(cadena));
                   
                    AUX6();
                    
                    cadena=cad.obtenerPalabra(contador);
                    contador++;
                    
                    if(!cadena.equals(";"))
                    {
                        System.out.println("Error");
                    }
                    
                }
                else
                {
                    System.out.println("error en variable introducida");
                }
            
            
        }
        else if(isValidado())
        {
            System.out.println("Valido en condicion");
        }
        else
        {
            System.out.println("Identificador no definido");
        }
        
    }
    
        private void CONDICION(claseCataficcia cata) throws IOException 
    {
    
        //cadena=lector.token();
       
        if(cata.getNombre().equals(cadena))
        {
            
            //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
        if(cadena.equals("=!=!") || cadena.equals("<=!") || cadena.equals(">=!"))
        {
            cata.setOperacion(cadena);
            
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
            if(isValidado())
            {
                cata.setElementoSecundario(buscarElemento(cadena));
                cadena=cad.obtenerPalabra(contador);
                contador++;
                   
                if(!cadena.equals(";"))
                    {
                        System.out.println("Error");
                    }
            }
                    
        } 
        }
        else
        {
            System.out.println("Identificador no definido");
        }
        
    }
    
    public boolean ValidarPalabra()
    {
        return false;
    }
    
    /**/
//    <IGUALDADES> ::=  =!=!
//	<IGUALDADES> ::= <=!
//	<IGUALDADES> ::= >=!

    public void IGUALDADES(operacionFor variableFor)
    {
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
        if(cadena.equals("=!=!") || cadena.equals("<=!") || cadena.equals(">=!"))
            {
                variableFor.setOperacionFor(cadena);
            }
        else
        {
            System.out.println("Error");
        }
    
    }
    
//    <AUX6> ::= ε
//    <AUX6> ::= && <CONDICION> 
//    <AUX6> ::= || <CONDICION>
//    FOLLOWS(AUX6)= ;

    public void AUX6() throws IOException
    {
        String operacion="";
       // cadena=lector.token();
       cadena=cad.obtenerPalabra(contador);
       contador++;
       
        if(!cadena.equals(";"))
        {
            //cadena=lector.token();
            
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
            if(cadena.equals("&&"))
            {
                operacion=cadena;
            }
            else if(cadena.equals("||"))
            {
                operacion=cadena;
            }
            cadena=cad.obtenerPalabra(contador);
            //contador++;
            //CONDICION(cadena);
            
        }
        else
        {
            contador--;
            cadena=cad.obtenerPalabra(contador);
        }
    }
    
    
//<INCREODECRE> ::= ++
//<INCREODECRE> ::= --

    private void INCREOGRECRE(operacionFor variableFor) throws IOException {
          
      
        String inde="";     
      
        if(cadena.equals("+"))
        {
            inde="++";
            //operacion+=operacion;
        }
        else if(cadena.equals("-"))
        {
            inde="--";
            //operacion-=operacion;
        }
        else
        {
            System.out.println("Error");
        }
        
        cadena=cad.obtenerPalabra(contador);
        contador++;

        if(inde.equals("++"))
        {
            if(cadena.equals("+"))
            {
                inde="++";
            }
            else
            {
                System.out.println("Error en incremento y decremento de suma");
            }
        }
        else if(inde.equals("-7"))
        {
            if(cadena.equals("-"))
            {
                inde="--";
            }
            else
            {
                System.out.println("Error en incremento y decremento de resta");
            }
        }
        else
        {
            System.out.println("Error en incremento y decremento");
        }
        
        variableFor.setMasomenos(inde);
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
    }
    
    private void MOSTRAR(String variable) {
        
        System.out.println(nombreVariable.get(buscarElemento(variable)).getNombre()+" -> "+nombreVariable.get(buscarElemento(variable)).getValor());
       
         
    }

    private String LEERDATOS() throws IOException {
    
        Scanner sc = new Scanner(System.in);
        String datoEntrada="";
        System.out.print("Introduce dato: ");
        datoEntrada = sc.nextLine();
        
        return datoEntrada;
    }

    private void ASIGNAR(String dato) throws IOException {
        //cadena=lector.token();
        
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
        
        if(isValidado())
        {
            
            if(nombreVariable.get(buscarElemento(cadena)).getTipo().equals("<Entero>") || nombreVariable.get(buscarElemento(cadena)).getTipo().equals("<Decimal>") )
            {
               if(cad.isFlotante(dato))
               {
                   nombreVariable.get(buscarElemento(cadena)).setValor(dato);
               }
               else
               {
                   System.out.println("Error al asignar valor");
               }
            }
            else if(nombreVariable.get(buscarElemento(cadena)).getTipo().equals("<Cadena>"))
            {
                  nombreVariable.get(buscarElemento(cadena)).setValor(dato);      
                
            }
            else if(nombreVariable.get(buscarElemento(cadena)).getTipo().equals("<Buliado>"))
            {
                if(cad.areYouBulleado(dato))
               {
                   nombreVariable.get(buscarElemento(cadena)).setValor(dato);
               }
               else
               {
                   System.out.println("Error al asignar valor");
               }
            }
            
            
        }
        else
        {
            System.out.println("Error");
        }
        
     
        
    }
    
    

    
    
    
private void OPERACIONES() throws IOException //corecto
    {
     
       
//        <OPERACIONES> ::= IDENT =! <AUX2> <AUX3> ;
//        <OPERACIONES> ::= ε
//        FIRST(OPERACIONES) ::= {“IDENT”+FIRST(OPERACIONES)}
       
        //cadena=lector.token();
        operacion = new validacionOperacion("","", 0, "", "nada");
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
        
       if(!cadena.equals("Adios"))
       {
           if(isValidado())
            {

                operacion.setVariablePrincipal(cadena);
                operacion.setTipoVariable(nombreVariable.get(buscarElemento(cadena)).getTipo());

                //cadena=lector.token();
                cadena=cad.obtenerPalabra(contador);
                contador++;

                if(cadena.equals("=!"))
                {
                   AUX2();
                   AUX3(); 
                   SustitucionValor();
                   cadena=cad.obtenerPalabra(contador);
                   contador++;

                   if(cadena.equals(";"))
                    {
                        OPERACIONES();
                    }
                   else
                   {
                       System.out.println("Error en operaciones");
                   }
                }
                else
                {
                    System.out.println("Error");
                }
            }
       }   
        else
        {
            contador--;
           // cadena=cad.obtenerPalabra(contador);
        }
    }
   
private void AUX2() throws IOException //corecto
     {
//         <AUX2> ::= NUM
//         <AUX2> ::= IDENT
        //cadena=lector.token();
         
        cadena=cad.obtenerPalabra(contador);
        contador++;
       
        //Si existe como identificador
        if(isValidado() /*&& tipoValidado(posicionVariable)*/)
        {
            
            verificarValidesVariables(cadena);
            
            
        }
        else if(cad.isNumerico(cadena))//debe ser numerico el dato
        {
            verificarValidesNumero(cadena);     
            
        }
        else
        {
            System.out.println("Error con dato guardado a trabajar");
        }
    }
    
    private void AUX3() throws IOException //corecto
    {
//        <AUX3>::= ε
//        <AUX3>::= <SIGNO> <AUX2> <AUX3>
//        FOLLOWS(AUX3)=  ;
        
          //cadena=lector.token();
          cadena=cad.obtenerPalabra(contador);
          contador++;
          
            
         if(!cadena.equals(";"))
         {
             SIGNOS();
             AUX2();
             AUX3();
         }
         else
         {
            
             contador--;
             //OPERACIONES();
            
         }
           
       
    }
   
    private void SIGNOS() throws IOException //corecto
    {
        //cadena=lector.token();
        
       String operacionHere="";
        
        if(cadena.equals("+")){
              operacionHere="+";
              operacionAuxiliar+=operacionHere;
              this.operacion.setOperacion(operacionHere);
              
        }
        else if(cadena.equals("-")){
             operacionHere="-";
             operacionAuxiliar+=operacionHere;
              this.operacion.setOperacion(operacionHere);
        }
        else if(cadena.equals("*")){
            operacionHere="*";
            operacionAuxiliar+=operacionHere;
             this.operacion.setOperacion(operacionHere);
        }
        else if(cadena.equals("/")){
              operacionHere="/";
              operacionAuxiliar+=operacionHere;
               this.operacion.setOperacion(operacionHere);
        }
        else {//no es un signo de operacionHere
            System.out.println("Error");
        }
        
    }

    private void FUNCION() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean freeIdent(String ca)
    {
        for(int i=0; i<nombreVariable.size(); i++)
        {
            if(ca.equals(nombreVariable.get(i).getNombre()))
            {
                return false;
            }
        }
        return true;
    }

    private boolean isCadena(String cadena, variables var) {
    
        String cade = cadena;
        String variable = "";
      
        
        if((int)cade.charAt(0)==34)
        {
            cade=cad.obtenerPalabra(contador);
            contador++;
            while((int)cade.charAt(0)!=34 || cade.equals(";"))
            {
                variable+=cade;
                cade=cad.obtenerPalabra(contador);
                contador++;
              
            }
            
            if(cade.equals(";"))
            {
                return false;
            }
            else
            {
                var.setValor(variable);
                return true;
            }
            
        }
        else
        {
            return false;
        }
    
    }
    
   //Verifica que exista el identificador
    private boolean isValidado() {
    
        boolean validado=false;
        
        for(int a = 0; a<nombreVariable.size(); a++)
        {
            
            if(nombreVariable.get(a).getNombre().equals(cadena))
            {
                validado=true;
               
            }
        }
        
        return validado;
    }

    private boolean tipoValidado(int posicionVariable) {
        
        if(operacion.getTipoVariable() == nombreVariable.get(posicionVariable).getTipo())
        {
            return true;
        }
        else
        {
            System.out.println("Error, incompatible tipos de variable");
            return false;
        }
    }
    
    private int buscarElemento(String cadena) {
        
        int posicionVariable=-1;
        for(int a = 0; a<nombreVariable.size(); a++)
        {
            
            
            if(nombreVariable.get(a).getNombre().equals(cadena))
            {
                
                posicionVariable=a;
            }
        }
        
        return posicionVariable;
    }

    private void SustitucionValor() {
    
        String variablePrincipal = operacion.getVariablePrincipal();
        
        for(int a = 0; a<nombreVariable.size(); a++)
        {
            
            
            if(variablePrincipal.equals(nombreVariable.get(a).getNombre()))
            {
                if(nombreVariable.get(a).getTipo().equals("<Entero>"))
                {
                   nombreVariable.get(a).setValor(String.valueOf( (int)operacion.getResultadoNumerico())); 
                }
                else if(nombreVariable.get(a).getTipo().equals("<Cadena>"))
                {
                    nombreVariable.get(a).setValor(operacion.getResultadoCadena()); 
                }
                else
                {
                    nombreVariable.get(a).setValor(String.valueOf( operacion.getResultadoNumerico()));
                }
                
                
            }
        }
        
    }

    private void verificarValidesVariables(String cad) {
        String tipoVar = operacion.getTipoVariable(), cade=cad;
        String tipovarCade = nombreVariable.get(buscarElemento(cad)).getTipo();
        
        if(tipoVar.equals("<Entero>") || tipoVar.equals("<Decimal>"))
        {
            
            if(tipovarCade.equals("<Entero>") || tipovarCade.equals("<Decimal>") || tipovarCade.equals("<NumEntero>"))
            {
                operacion.realizarOperacion( Float.parseFloat(nombreVariable.get( buscarElemento(cadena)).valor) );
            }
            else
            {
                System.out.println("Error de incompatibilidad");
            }
                
        }
        else if(tipoVar.equals("<Cadena>"))
        {
            if(tipovarCade.equals("<Cadena>"))
            {
                operacion.realizarOperacionString( nombreVariable.get( buscarElemento(cadena)).valor );
            }
            else
            {
                System.out.println("Error de incompatibilidad");
            }
        }
        else
        {
            System.out.println("Error de incompatibilidad");
        }
    }

    private void verificarValidesNumero(String cadena) {
        
        
        if(cad.isFlotante(cadena))
        {
            String tipoVarPrin= operacion.getTipoVariable();
            String aux=cadena;
            
                    if(tipoVarPrin.equals("<Entero>") || tipoVarPrin.equals("<Decimal>"))
                {
                        operacion.realizarOperacion( Float.parseFloat(aux) );
                }
                else if(tipoVarPrin.equals("<Buliado>"))
                {
                    if(aux.equals("0") || aux.equals("1"))
                    {
                        operacion.setResultadoCadena(aux);
                    }
                }
            
            
        }
        else
        {
            System.out.println("Dato insertado no validado");
        }
        
   
    }




}
