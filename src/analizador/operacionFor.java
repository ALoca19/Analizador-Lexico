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
public class operacionFor extends variables{
    String tipo, nombre, valor, operacionFor, masomenos;
    int variableComparable=-1;

    public operacionFor(String tipo, String identificador, String valor, String operacionFor, String masomenos, int variableComparable) {
        super( tipo,  identificador,  valor);
        this.tipo = tipo;
        this.nombre = identificador;
        this.valor = valor;
        this.operacionFor = operacionFor;
        this.masomenos = masomenos;
        this.variableComparable = variableComparable;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getOperacionFor() {
        return operacionFor;
    }

    public void setOperacionFor(String operacionFor) {
        this.operacionFor = operacionFor;
    }

    public String getMasomenos() {
        return masomenos;
    }

    public void setMasomenos(String masomenos) {
        this.masomenos = masomenos;
    }

    public int getVariableComparable() {
        return variableComparable;
    }

    public void setVariableComparable(int variableComparable) {
        this.variableComparable = variableComparable;
    }

    @Override
    public String toString() {
        return "operacionFor{" + "tipo=" + tipo + ", nombre=" + nombre + ", valor=" + valor + ", operacionFor=" + operacionFor + ", masomenos=" + masomenos + ", variableComparable=" + variableComparable + '}';
    }

    

    
    
    
}
