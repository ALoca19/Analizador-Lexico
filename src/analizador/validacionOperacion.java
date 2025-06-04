/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizador;

/**
 *
 * @author anita
 */
public class validacionOperacion {
    
    String operacion;
    float resultadoNumerico;
    String resultadoCadena, variablePrincipal;
    String tipoVariable;

    public validacionOperacion(String variablePrincipal, String tipoVariable, int resultadoNumerico, String resultadoCadena, String operacion) {
        this.operacion = operacion;
        this.resultadoNumerico = resultadoNumerico;
        this.resultadoCadena = resultadoCadena;
        this.variablePrincipal = variablePrincipal;
        this.tipoVariable = tipoVariable;
    }

    public validacionOperacion() {
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public float getResultadoNumerico() {
        return resultadoNumerico;
    }

    public void setResultadoNumerico(float resultadoNumerico) {
        this.resultadoNumerico = resultadoNumerico;
    }

    public String getResultadoCadena() {
        return resultadoCadena;
    }

    public void setResultadoCadena(String resultadoCadena) {
        this.resultadoCadena = resultadoCadena;
    }

    public String getVariablePrincipal() {
        return variablePrincipal;
    }

    public void setVariablePrincipal(String variablePrincipal) {
        this.variablePrincipal = variablePrincipal;
    }

    public String getTipoVariable() {
        return tipoVariable;
    }

    public void setTipoVariable(String tipoVariable) {
        this.tipoVariable = tipoVariable;
    }
    
    

    void realizarOperacion(float numero) {
       
        
        if(operacion.equals("+")){
            resultadoNumerico+=numero;
              
        }
        else if(operacion.equals("-")){
             resultadoNumerico-=numero;
        }
        else if(operacion.equals("*")){
            resultadoNumerico*=numero;
        }
        else if(operacion.equals("/")){
               resultadoNumerico/=numero;
        }
        else 
        {
            resultadoNumerico=numero;
        }
        setResultadoNumerico(resultadoNumerico);
       
    }

    void realizarOperacionString(String valor) {
        
        if(operacion.equals("+")){
            resultadoCadena+=valor;
            
        }
        else 
        {
            resultadoCadena=valor;
        }
        setResultadoCadena(resultadoCadena);
    }
    
    

    
    
    
          
}
