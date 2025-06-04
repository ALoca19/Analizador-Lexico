/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version01;

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
   
   
    palabrasToken cad = new palabrasToken();
    int contador=0;//Contador pa palabras
   
   List<String> nombreFunciones = new ArrayList<String>(); //Nombre de las Funciones
   List<variables> nombreVariable = new ArrayList<variables>(); //Nombre de las Funciones
   //List<String> palabras = palabrasT.getPalabras();
   


   //<PROGRAMA> ::= Func /<NumEntero> IDENT <CAJA> <aux0> 
   //FIRST(PROGRAMA)::=Func 
    public void PROGRAMA() throws IOException
    {
        lector= new lectorCaracteres("alfanumericos.txt");
       
        //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        contador++;
        
        if(cadena.equals("Func"))
        {
            cadena=lector.token();
            if(cadena.equals("<NumEntero>"))
            {
                cadena=lector.token();
                if(lector.isIdentificador())
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
                    contador--;
                    cadena=cad.obtenerPalabra(contador);
          
                }
                
            }
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
          
        if(!cadena.equals("Func"))
        {
            PROGRAMA();
            //System.out.println("Terminado");
        }
        else
        {
            contador--;
            cadena=cad.obtenerPalabra(contador);
        }
        
    }
    
    //<CAJA> ::= $  <BLOQUE>  $$ 
    //FIRST(CAJA)::=$   
    public void CAJA() throws IOException
    {
        cadena=lector.token();
        if(cadena.equals("$"))
        {
            BLOQUE();
          
            cadena=lector.token();
            
            if(!cadena.equals("$$"))
            {
                System.out.println("Error");
            }
        }
        
    }

    //<BLOQUE> ::= <VARIABLE><ITERACIONES><OPERACIONES> Adios ; 
    
    //FIRST(CAJA)::= FIRST(VARIABLE) = {“/<Entero>/”+”/<Cadena>/  ”+” /<Decimal>/  ”+” /<Buliado>/  ”+” /<NumEntero>/  ”} 
    private void BLOQUE() throws IOException {
        VARIABLES();
        INTERACIONES();
        OPERACIONES();
        
        cadena=lector.token();
        if(cadena.equals("Adios"))
        {
            cadena=lector.token();
            if(!cadena.equals(";"))
            {
                System.out.println("Error");
            }
        }
        
    }

    /*
    
    
   





    FOLLOW(VARIABLES)=FIRST(ITERACIONES);
    */
    private void VARIABLES() throws IOException {
        cadena=lector.token();
        
        
        String tipo="", ident="", valor="";
        variables var= new variables(tipo, ident, valor);
         //<VARIABLE> ::= /<Entero>/ IDENT =! NUM; <VARIABLE>
        if(cadena.equals("<Entero>"))
        {
            var.setTipo("<Entero>");
            cadena=lector.token();
            
                if(lector.isIdentificador())
                {
                    var.setNombre(ident);
                    cadena=lector.token();
                    
                    if(cadena.equals("=!"))
                    {
                        cadena=lector.token(); //DEBE DE HABER UN ENTERO
                        boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
                        
                        if(isNumeric)
                        {
                            var.setValor(valor);
                            nombreVariable.add(var);
                            
                            cadena=lector.token();
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
        //<VARIABLE> ::= /<Cadena>/  IDENT  =!  “ palabra  ”  ; <VARIABLE>
        else if(cadena.equals("<Cadena>"))  
        {
            var.setTipo("<Cadena>");
            cadena=lector.token();
            
                if(lector.isIdentificador())
                {
                    var.setNombre(ident);
                    cadena=lector.token();
                    
                    if(cadena.equals("=!"))
                    {
                        cadena=lector.token(); //DEBE DE IDENTIFICAR LA PALABRA
                        
                        if(cadena.equals('"'))
                        {
                            cadena=lector.palabra();
                            
                            if(!cadena.equalsIgnoreCase("Error"))
                            {
                                 var.setValor(valor);
                                 cadena=lector.token();
                                 nombreVariable.add(var);
                                    if(cadena.equals(";"))
                                    {
                                        VARIABLES();
                                    }
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
            cadena=lector.token();
            
                if(lector.isIdentificador())
                {
                    var.setNombre(ident);
                    cadena=lector.token();
                    
                    if(cadena.equals("=!"))
                    {
                        cadena=lector.token(); //DEBE DE HABER UN ENTERO
                        boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
                        
                        if(isNumeric)
                        {
                            var.setValor(valor);
                            nombreVariable.add(var);
                            cadena=lector.token();
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
            cadena=lector.token();
            
                if(lector.isIdentificador())
                {
                    var.setNombre(ident);
                    cadena=lector.token();
                    
                    if(cadena.equals("=!"))
                    {
                        
                        cadena=lector.token(); //0 o 1
                        nombreVariable.add(var);
                        if(cadena.equals("0") || cadena.equals("1"))
                        {
                            var.setValor(valor);
                            nombreVariable.add(var);
                            cadena=lector.token();
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
            cadena=lector.token();
            
                if(lector.isIdentificador())
                {
                    var.setNombre(ident);
                    cadena=lector.token();
                    
                    if(cadena.equals("=!"))
                    {
                        cadena=lector.token(); //DEBE DE HABER UN ENTERO
                        boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
                        
                        if(isNumeric)
                        {
                            var.setValor(valor);
                            nombreVariable.add(var);
                            cadena=lector.token();
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
        /*FOLLOW(VARIABLES)=FIRST(ITERACIONES);
        else
        {
            INTERACIONES();
        }
        */
        //duda aqui maestra, tenemos que poner aqui el if o esta bien con dejar el ELSE de arriba
        //FOLLOW(VARIABLES)=FIRST(ITERACIONES);
        if(cadena!="<Entero>" &&  cadena!="<Cadena>" && cadena!="<Decimal>" && cadena!="<Buliado>" && cadena!="<NumEntero>")
        {
            VARIABLES();
        }
        else
        {
            contador--;
            INTERACIONES();
        }
    }
    /**/





//    <INTERACCIONES> ::= ε 
//    FIRST(INTERACCIONES) ::= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
//    FOLLOWS(INTERACCIONES)=FIRST(INTERACCIONES)= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
    /**/

    private void INTERACIONES() throws IOException {
        
        cadena=lector.token();
        
//    <INTERACCIONES> ::= Cataficcia  (  IDENT  )  $  < SELECCION>  $$         
        if(cadena.equals("Cataficcia"))
        {
            cadena=lector.token();
            
            if(cadena.equals("("))
            {
                cadena=lector.token();
                
                if(lector.isIdentificador())
                {
                    cadena=lector.token();
                    
                    if(cadena.equals(")"))
                    {
                        cadena=lector.token();
                        if(cadena.equals("$"))
                        {
                            SELECCION();
                            
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
           cadena=lector.token();
            
            if(cadena.equals("("))
            {
                INICIADOR();
                CONDICION();
                
                cadena=lector.token();
                
                if(lector.isIdentificador())
                {
                    INCREOGRECRE(cadena);
                    
                    cadena=lector.token();
                    
                    if(cadena.equals(")"))
                    {
                        CAJA();
                        INTERACIONES();
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
            cadena=lector.token();
            if(cadena.equals(";"))
            {
                INTERACIONES();
            }
            
        }
        //    <INTERACCIONES> ::= Hayteva>> INDENT <MOSTRAR>; (ACCION MUESTRA A LA PANTALLA) 
        else if(cadena.equals("Hayteva>>"))
        { 
            cadena=lector.token();
                
                if(lector.isIdentificador())
                {
                    MOSTRAR(cadena);
                    
                    cadena=lector.token();
                    
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
            LEERDATOS();
            ASIGNAR();
            cadena=lector.token();
                
                if(lector.isIdentificador())
                {
                    cadena=lector.token();
                    
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
        /*
        else
        {
            OPERACIONES();
        }
        */
        //    <INTERACCIONES> ::= ε 
        //    FIRST(INTERACCIONES) ::= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
        //    FOLLOWS(INTERACCIONES)=FIRST(INTERACCIONES)= {“ Cataficcia ” + “Mufasa  ”+ “Ontas”+” Hayteva”+” Dime”+ FIRST(INTERACCIONES) }
        //MISMA DUDA QUE EL DE VARIABLES, SI DEJAMOS ESTE IF O ESTA BIEN SOLO CON EL ELSE DE ARRIB
        //perdone las mayusculas
        if(cadena!="Cataficcia" && cadena!="Mufasa" && cadena!="Ontas" && cadena!="Hayteva>>" && cadena!="Dime<<" )
        {
            INTERACIONES();
        }
        else
        {
            contador--;
            cadena=cad.obtenerPalabra(contador);
        }
    
    }
    
//    <SELECCION> ::= Escoge  <CONDICION> :  $  <BLOQUE> ADIOS  ;  $$ <AUX4>
//    <AUX4>::= <SELECCION>
//    <AUX4>= ε
//    FIRST(SELECCION) ::= Escoge
//    FOLLOW(aux4)::=FIRS(SELECCION)
    
    private void SELECCION() throws IOException 
    {
        cadena=lector.token();
        if(cadena.equals("Escoge"))
        {
            CONDICION();
            cadena=lector.token();
            
            if(cadena.equals(":"))
            {
                cadena=lector.token();
                
                if(cadena.equals("$"))
                {
                    BLOQUE();
                    cadena=lector.token();
                    
                     if(cadena.equals("ADIOS"))
                    {
                    
                    cadena=lector.token();
                    
                        if(cadena.equals(";"))
                        {

                        cadena=lector.token();
                        
                        if(cadena.equals("$$"))
                            {
                                AUX4();
                                

                            }

                        }
                    
                    }
                    
                }
            }
            
        }
    
    }
    //    <SELECCION> ::= Escoge  <CONDICION> :  $  <BLOQUE> ADIOS  ;  $$ <AUX4>
//    <AUX4>::= <SELECCION>
//    <AUX4>= ε
//    FIRST(SELECCION) ::= Escoge
//    FOLLOW(aux4)::=FIRS(SELECCION)
    private void AUX4() throws IOException
    {
        //cadena=lector.token();
        cadena=cad.obtenerPalabra(contador);
        if(!cadena.equals("Escoge"))
        {
            SELECCION();
        }
        else
        {
            contador--;
            cadena=cad.obtenerPalabra(contador);
            
        }
        
    }
    
    //<INICIADOR> ::= /<Entero>/  IDENT  =!  NUM  ;
    private void INICIADOR() throws IOException {
        
        cadena=lector.token();
            
        if(cadena.equals("<Entero>"))
        {
           cadena=lector.token();
            
            if(lector.isIdentificador())
                {
                    cadena=lector.token();
                   if(cadena.equals("=!"))
                   {
                       cadena=lector.token(); //DEBE DE HABER UN ENTERO
                        boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
                        
                        if(isNumeric)
                        {
                           cadena=lector.token(); //DEBE DE HABER UN ENTERO
                           
                           if(!cadena.equals(";"))
                           {
                               System.out.println("Error");
                           }
                        }
                       
                   }
                }
            else
            {
                System.out.println("Error");
            }
                
        }
        
   }

    //<CONDICION> ::=  IDENT  <IGUALDADES>  IDENT2 <AUX6> ;
    //FIRST(CONDICION) ::= Ident
    private void CONDICION() throws IOException 
    {
    
        cadena=lector.token();
        boolean validado=false;
        int posicionVariable=0;
        
   
        for(int a = 0; a<nombreVariable.size(); a++)
        {
           
            if(nombreVariable.get(a).getNombre().equals(cadena))
            {
                validado=true;
                posicionVariable=a;
            }
        }
        
        if(validado)
        {
            cadena=lector.token();
            IGUALDADES();
            
            validado=false;
                //cadena=lector.token();
                cadena=cad.obtenerPalabra(contador);
                contador++;
                
                for(int a = 0; a<nombreVariable.size(); a++)
                {

                    if(nombreVariable.get(a).getNombre().equals(cadena))
                    {
                        validado=true;
                        posicionVariable=a;
                    }
                }
                
                if(validado)
                {
                    AUX6();
                }
                else
                {
                    System.out.println("error");
                }
            
            
        }
        
    }
    
    /**/
//    <IGUALDADES> ::=  =!=!
//	<IGUALDADES> ::= <=!
//	<IGUALDADES> ::= >=!

    public void IGUALDADES()
    {
        String condicion="";
        
        if(cadena.equals("=!=!") || cadena.equals("<=!") || cadena.equals(">=!"))
            {
                condicion=cadena;
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
        //contador++;
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
            CONDICION();
        }
        else
        {
            //contador--;
            cadena=cad.obtenerPalabra(contador);
        }
    }
    
    
//<INCREODECRE> ::= ++
//<INCREODECRE> ::= --

    private void INCREOGRECRE(String cad) throws IOException {
        
        cadena=lector.token();
        int operacion=Integer.parseInt(cad);
                
        if(cadena.equals("++"))
        {
            operacion+=operacion;
        }
        else if(cadena.equals("--"))
        {
            operacion-=operacion;
        }
        
    }
    private void MOSTRAR(String variable) {
        
        System.out.println(variable);
        
    }

    private void LEERDATOS() {
    
        Scanner sc = new Scanner(System.in);
        String datoEntrada="";
        System.out.print("Introduce dato: ");
        datoEntrada = sc.nextLine();
    }

    private void ASIGNAR() throws IOException {
        cadena=lector.token();
        boolean validado=false;
        int posicionVariable=0;
        String datoEntrada = null;
   
        for(int a = 0; a<nombreVariable.size(); a++)
        {
           
            if(nombreVariable.get(a).getNombre().equals(cadena))
            {
                nombreVariable.get(a).setNombre(datoEntrada);
            }
        }
    }
    
    

    
    
    
private void OPERACIONES() throws IOException //corecto
    {
       
//        <OPERACIONES> ::= IDENT =! <AUX2> <AUX3> ;
//        <OPERACIONES> ::= ε
//        FIRST(OPERACIONES) ::= {“IDENT”+FIRST(OPERACIONES)}
       
        cadena=lector.token();
        boolean validado=false;
        int posicionVariable=0;
   
        for(int a = 0; a<nombreVariable.size(); a++)
        {
           
            if(nombreVariable.get(a).getNombre().equals(cadena))
            {
                validado=true;
                posicionVariable=a;
            }
        }
        
        if(validado)
        {
            //cadena=lector.token();
            cadena=cad.obtenerPalabra(contador);
            contador++;
            
            if(cadena.equals("=!"))
            {
               AUX2();
               AUX3(); 
               
                
//               if(!cadena.equals(";"))
//                {
//                    System.out.println("Error");
//                }

            }
        }
        else
        {
            contador--;
            cadena=cad.obtenerPalabra(contador);
        }
        
       
    }
   
     private void AUX2() throws IOException //corecto
     {
//         <AUX2> ::= NUM
//         <AUX2> ::= IDENT
        cadena=lector.token();
        boolean validado=false;
        int posicionVariable=0;
        String variableAcumuladora="";
   
        for(int a = 0; a<nombreVariable.size(); a++)
        {
           
            if(nombreVariable.get(a).getNombre().equals(cadena))
            {
                validado=true;
                posicionVariable=a;
            }
        }
        
        if(validado)
        {
            variableAcumuladora=cadena;
        }
        else
        {
           boolean isNumeric =  cadena.matches("[+-]?\\d*(\\.\\d+)?");
                        
            if(isNumeric)
               {
                   variableAcumuladora=cadena;
                } 
        }
        
       
        
    }
    
    private void AUX3() throws IOException //corecto
    {
//        <AUX3>::= ε
//        <AUX3>::= <SIGNO> <AUX2> <AUX3>
//        FOLLOWS(AUX3)=  ;
        
          //cadena=lector.token();
          cadena=cad.obtenerPalabra(contador);
          //contador++;
            
         if(!cadena.equals(";"))
         {
             SIGNOS();
             AUX2();
             AUX3();
         }
         else
         {
             contador--;
             cadena=cad.obtenerPalabra(contador);
         }
           
       
    }
   
    private void SIGNOS() throws IOException //corecto
    {
        cadena=lector.token();
       String operacion="";

        if(cadena.equals("+")){
              operacion="+";
        }
        else if(cadena.equals("-")){
             operacion="-";
        }
        else if(cadena.equals("*")){
            operacion="*";
        }
        else if(cadena.equals("/")){
              operacion="/";
        }
        else {//no es un signo de operacion
            System.out.println("Error");
        }
    }

    private void FUNCION() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
