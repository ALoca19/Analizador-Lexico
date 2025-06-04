/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author anita
 */
public class lectorCaracteres {

    String archivo;
    FileReader fileA, fileB; 
    char x3;
    String cadena="";
    boolean identificador=false, palabra=false;
 
    public lectorCaracteres(String archivo) throws FileNotFoundException, IOException{
        
        this.archivo=archivo;
        this.fileA=new FileReader(archivo);
        this.fileB=new FileReader(archivo);
        x3=leer();
        
    }
    
    public char leer() throws FileNotFoundException, IOException
    {
        int caracter=fileA.read();
        return (char)caracter;
    }
    
    public char leerAux() throws FileNotFoundException, IOException
    {
        int caracter=fileB.read();
        return (char)caracter;
    }
    
    public String IDENT() throws IOException
    {
         cadena="";
        if((int)x3>=65 && (int)x3<=90) //Identificador, La primera letra siempre MAYUSCULA
        {
            
            cadena+=x3;
            x3=leer(); //u
            while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
            {
               cadena+=x3; //func
               x3=leer(); // 
            }
            nintendoSwitch();
        }
        
        return cadena;
    }
    
    
    public String token() throws FileNotFoundException, IOException
    { 
        
        cadena="";
        //boolean punto=false;
        //System.out.println("X: "+x3);
        
        

        
        if((int)x3==68 || (int)x3==72){//LETRA MAYUSCULA D -68, H-72
           
            cadena+=x3;
            x3=leer(); // D o H
            while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                }
                
                if((int)x3==62)//Dadasdasdasdad>
                {
                    cadena+=x3;
                     x3=leer();
                     if((int)x3==62)//Dadasdasdasdad>>
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                }
                else if((int)x3==60)//Dadasdasdasdad<
                {
                    cadena+=x3;
                     x3=leer();
                     if((int)x3==60)//Dadasdasdasdad<<
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                }
                
                nintendoSwitch();
        
        }
        else if((int)x3>=65 && (int)x3<=90) //Identificador, La primera letra siempre MAYUSCULA
        {
            
            cadena+=x3;
            x3=leer(); //u
            while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
            {
               cadena+=x3; //func
               x3=leer(); // 
            }
            nintendoSwitch();
        }
//        else if(){
//        }
        else if((int)x3==61  || (int)x3==42 || (int)x3==43 || (int)x3==45 || (int)x3==47 ||  (int)x3==62 || (int)x3==38 || (int)x3==124) //Identificador, signo de operacion
        {
           //=, +, -, *, /, >, &, |  --- 61, 43, 45, 42, 47, 62, 38, 124
            
            signos();
        }
        else if((int)x3==60) //analizar si es variable, primer caracter <
        {
            cadena+=x3; //<
            x3=leer(); 
            if((int)x3>=65 && (int)x3<=90){// <MAYUSCULA
            variable();
            }
            else{
            signos();
            }
            
        }
        else if((int)x3>=0 && (int)x3<=9) //NUMEROS
        {
            //([0..9]+(/.[0..9]+)?)
            cadena+=x3;
            x3=leer();
            
            while((int)x3>=0 && (int)x3<=9)
            {
               cadena+=x3;
                x3=leer(); 
            }
            
            if((int)x3==46)
            {
                cadena+=x3;
                x3=leer();
                
                while((int)x3>=0 && (int)x3<=9)
                {
                cadena+=x3;
                x3=leer(); 
                }
            }
            
        } 
        
      
        /*else if ((int)x3>=60 && (int)x3<=62 || (int)x3==33 || (int)x3<=124 || (int)x3<=38)
        {
            caracter();
        }*/
        else if((int)x3==65535)
        {
            return "finalizado";
        }
        else if((int)x3==32 || (int)x3==10 || (int)x3==13 )
        {
            x3=leer();
            token();
        }

        else{
            cadena+=x3;
            x3=leer();
        }
       // System.out.println("");
//        System.out.println("x3= "+(int)x3);
        return cadena;
        
    }
    
    public void variable() throws IOException
    {
        cadena+=x3; //<
        char aux=x3;// E, C, D, B, N, F, O, H, A,<   - 69,67,68,66,78,60
        x3=leer(); 
        
        
        switch (aux)
        {
            case 'E': //Validar entero
                cadena+=x3; //<E
                
                 x3=leer();
                while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                }
                
                if((int)x3==62)
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                
                nintendoSwitch();
                
            break;
            
            case 'C'://Validar cadena
                cadena+=x3; //<C
                
                 x3=leer();
                while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                }
                
                if((int)x3==62)
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                
                nintendoSwitch();
            break;
            case 'D'://Validar decimal
                cadena+=x3; //<D
                
                 x3=leer();
                while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                }
                
                if((int)x3==62)
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                
                nintendoSwitch();
            break;
            case 'B'://Validar  boleano
                cadena+=x3; //<B
                
                 x3=leer();
                while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                }
                
                if((int)x3==62)
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                
                nintendoSwitch();
            break;
            case 'N'://Validar numero entero
                cadena+=x3; //<N
                
                 x3=leer();
                while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                }
                
                if(x3=='E')
                {
                    cadena+=x3;
                    x3=leer(); 
                }
                
                while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                } 
                
                if((int)x3==62)
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                
                nintendoSwitch();
            break;
            case '='://Validar decimal
                cadena+=x3; //<D
                
                 x3=leer();
                while((int)x3>=97 && (int)x3<=122) //MINUSCULAS
                {
                   cadena+=x3;
                    x3=leer(); 
                }
                
                if((int)x3==62)
                {
                    cadena+=x3;
                     x3=leer(); 
                }
                
                nintendoSwitch();
            break;
            
            default:
                
            break;
            
        }
        
        
               
    }
    
    
    public void signos() throws IOException
 {
        
        /* 61 33 60 62 43 45 47 42 38 124 36
        = -> DIFERENTE - 61
        =! -> IGUAL - 61 33
        =!=! -> COMPARACION - 61 33 61 33
        <=! -> < - 60 61 33 -mayor q
        >=! -> < - 62 61 33 -menor que
        + - 43
        - - 45
        / - 47
        * - 42
        && 38 38 -or
        || 124 124--and
        espacio 32
        $ 36
        */
         
        if((int)x3==61) //inicia con un =
        {
            cadena+=x3;
            x3=leer();
            
            if((int)x3==33)//=!
            {
                cadena+=x3;
                x3=leer();
                
                if((int)x3==61)//=!=
                {
                    cadena+=x3;
                    x3=leer();
                    
                    if((int)x3==33)//=!=!
                    {
                        cadena+=x3; 
                        x3=leer();
                        
                    }
                    
                }
                
            }
            
        }
      // <=! -> < - 60 61 33 -mayor q
        else if((int)x3==60) //inicia con un <
        {
            cadena+=x3;
            x3=leer();
            
            if((int)x3==61)//<=
            {
                cadena+=x3;
                x3=leer();
                
                if((int)x3==33)//<=!
                {
                    cadena+=x3;
                    x3=leer();
                }
                
            }
            
        }
        //>=! -> < - 62 61 33 -menor que
        else if((int)x3==62) //inicia con un >
        {
            cadena+=x3;
            x3=leer();
            
            if((int)x3==61)//>=
            {
                cadena+=x3;
                x3=leer();
                
                if((int)x3==33)//>=!
                {
                    cadena+=x3;
                    x3=leer();
                }
            }
        }
        else if((int)x3==43 || (int)x3==45 || (int)x3==47 || (int)x3==42){ //inicia con un + o - o * o /
            cadena+=x3;
            x3=leer();
        }
        
        else if((int)x3==38) //inicia con un &
        {
            cadena+=x3;
            x3=leer();
            
            if((int)x3==38)//&&
            {
                cadena+=x3;
                x3=leer();
            }
        }
        else if((int)x3==124) //inicia con un |
        {
            cadena+=x3;
            x3=leer();
            
            if((int)x3==124)//||
            {
                cadena+=x3;
                x3=leer();
            }
        }
        else if((int)x3==36) //inicia con un &
        {
            cadena+=x3;
            x3=leer();
            
            if((int)x3==36)//$$
            {
                cadena+=x3;
                x3=leer();
            }
        }
        nintendoSwitch();
}
    
    public void nintendoSwitch()
    {
        System.out.println("");
        identificador=false;
        
        switch (cadena)
        {
            case "<Entero>"://numeros enteros
                System.out.println("Es palabra reservada, ENTERO");
            break;
            case "<Cadena>"://cadenas
                System.out.println("Es palabra reservada, CADENA");
            break;
            case "<Decimal>"://decimales
                System.out.println("Es palabra reservada, DECIMAL");
            break;
             case "<Buliado>"://boleanes
                System.out.println("Es palabra reservada, BOLEANO");
            break;
            case "<NumEntero>"://constante
                System.out.println("Es palabra reservada, NUMERO ENTERO, CONSTANTE");
            break;
            case "Func"://funcion
                System.out.println("Es palabra reservada, FUNCION");
            break;
            case "Ontas"://Llama a la funcion
                System.out.println("Es palabra reservada, LLAMAR A LA FUNCION");
            break;
            case "Hayteva>>"://Para mostrar en la pantalla
                System.out.println("Es palabra reservada, mostrar en la pantalla");
            break;
            case "Dime<<"://Para proporcionar datos a las bariables
                System.out.println("Es palabra reservada, proporcionar datos a las bariables");
            break;
            case "Adios"://Valor de retorno
                System.out.println("Es palabra reservada, Valor de retorno");
            break;
            case "Cataficcia"://switch
                System.out.println("Es palabra reservada, switch");
            break;
            case "Escoge"://casos del switch
                System.out.println("Es palabra reservada, casos del switch");
            break;
            case "Mufasa"://For
                System.out.println("Es palabra reservada, For");
            break;
            case "Int"://parlabra reservada
                System.out.println("Es palabra reservada");
            break;
            case "Main"://Palabra reservada
                System.out.println("Es palabra reservada");
            break;
            case "="://diferente
                System.out.println("Es palabra reservada, diferente");
            break;
            case "=!"://igual
                System.out.println("Es palabra reservada, igual");
            break;
            case "=!=!"://igual igual
                System.out.println("Es palabra reservada, igual igual");
            break;
             case "<=!"://mayor igual
                System.out.println("Es palabra reservada, mayor igual");
            break;
            case ">=!"://menor igual
                System.out.println("Es palabra reservada, menor igual");
            break;
            case "+"://suma
                System.out.println("Es palabra reservada, suma");
            break;
            case "-"://resta
                System.out.println("Es palabra reservada, resta");
            break;
            case "/"://divicion
                System.out.println("Es palabra reservada, divicion");
            break;
            case "*"://multiplicacion
                System.out.println("Es palabra reservada, multiplicacion");
            break;
            case "&&"://uno o otro, o , or
                System.out.println("Es palabra reservada, or");
            break;
            case "||"://uno y otro, y, and
                System.out.println("Es palabra reservada, and");
            break;
            case "$$"://final de funcion
                System.out.println("Es palabra reservada, final de funcion");
            break;
            case "$"://inicio de funcion
                System.out.println("Es palabra reservada, inicio de funcion");
            break;
               
            default:
            System.out.println("palabra no valida");
            identificador=true;
            break;
            
            
        }
        
    }

    public boolean isIdentificador() {
        return identificador;
    }

    public void setIdentificador(boolean identificador) {
        this.identificador = identificador;
    }
    
    public String palabra() throws IOException
    {
        
        cadena="";
         
        while((int)x3!=34)
        { 
            if((int)x3!=10 || (int)x3!=13 || (int)x3!=59)
            {
                cadena="Error";
                break;
            }
            else
            {
                cadena+=x3;
                x3=leer(); //u
            }
            
        }
            
      
        return cadena;
    }
    
    
    
    public void muestraContenido() throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        
        BufferedReader b = new BufferedReader(f);
        
        /*while((cadena = b.readLine())!=null) {
            System.out.println(cadena+"\n");
        }*/
        
        int caracter=f.read();
        while(caracter!=-1) {
            //System.out.println((char)caracter+" = "+caracter);
            muestraContenido(caracter);
            //System.out.println((char)caracter);
            
            caracter=f.read();
        }
        
        b.close();
    }
    
    public void muestraContenido(int letraASCII)
    {
        
        if(letraASCII!=10)
        {
            System.out.print((char)letraASCII);
        }
        else
        {
            System.out.println("");
            
        }
    }
    
    public void validarExprecion() throws FileNotFoundException, IOException
    {
        FileReader file = new FileReader(archivo);
        int c;
        c=file.read();
        System.out.println("Primer digito "+(char)c);
        //Validar Identificador o digito
        while(c!=-1)
        {
           
        if(c>=65 && c<=90) //si es Identificador, verifica que sea mayuscula
        {
            c=file.read();
             System.out.println("Digito a Analisar "+(char)c);
           while(c!=10)
           {
               if(c>=97 && c<=122) //debe de ser minuscula
               {
                   c=file.read();
                    System.out.println("Siguiente digito a analizar "+(char)c);
               }
               else if(c==13)
               {
                    
                   System.out.println("Cadena Valida");
                   c=file.read();
                   System.out.println("C deberia ser 10: "+c);
                   break;
               }
               else
               {
                    
                   System.out.println("Cadena Invalida");
                   break;
               }
               
               
           }
           
        }
        /*
        else if(c>0 && c<=9) //si es un digito mayor a 0.0
        {//([1|2|3|…|9].[0|1|2|…|9]*|0).([/.0|/.1|/.2|…|/.9].[0|1|2|3|…|9]*)?
            boolean punto=false;
            c=file.read();
           while(c!=10)
           {
               if((c>=0 && c<=9))
               {
                   c=file.read();
               }
               else if(c==250)
               {
                   if(punto==false)
                   {
                       punto=true;
                       c=file.read();
                   }
                   else
                   {
                      System.out.println("Cadena Invalida");
                      break; 
                   }
                   
               }
               else
               {
                   System.out.println("Cadena Invalida");
                   break;
               }
               
               if(c==13)
               {
                   System.out.println("Cadena Valida");
                   break;
               }
           }
        }
        else if(c==0)
        {
             c=file.read();
             
             if(c==250)
             {
               c=file.read();
               
               while(c!=10)
               {
                    if((c>=0 && c<=9))
                    {
                        c=file.read();
                    }
                    else
                    {
                        System.out.println("Cadena Invalida");
                        break;
                    }

                    if(c==13)
                    {
                        System.out.println("Cadena Valida");
                        break;
                    }
               }
             }
             else
             {
                System.out.println("Cadena Invalida");
             }
            
        }
        else
        {
            System.out.println("Cadena Invalida");
        }*/
        
        if(c!=10)
        {
            while(c!=10)
            {
                c=file.read();
                 System.out.print((char)c);
            }
            System.out.println("");
        }
        else
        {
           c=file.read(); 
            System.out.print("Ultimo digito: "+(char)c);
        }
        
     }
    }
    
}
