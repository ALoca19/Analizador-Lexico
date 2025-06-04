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
public class claseCataficcia extends variables{
    
    String operacion;
    boolean seRealizo=false;
    int elementoSecundario;
    
    public claseCataficcia(String tipo, String identificador, String valor, String operacion, int elementoSecundario, boolean seRealizo) {
        super(tipo, identificador, valor);
        this.operacion = operacion;
        this.elementoSecundario = elementoSecundario;
        this.seRealizo = seRealizo;
    }

    public boolean isSeRealizo() {
        return seRealizo;
    }

    public void setSeRealizo(boolean seRealizo) {
        this.seRealizo = seRealizo;
    }

    
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getElementoSecundario() {
        return elementoSecundario;
    }

    public void setElementoSecundario(int elementoSecundario) {
        this.elementoSecundario = elementoSecundario;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getValor() {
        return valor;
    }

    @Override
    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "claseCataficcia{" + "operacion=" + operacion + ", seRealizo=" + seRealizo + ", elementoSecundario=" + elementoSecundario + '}';
    }
    
    
    
    
    
    
}
