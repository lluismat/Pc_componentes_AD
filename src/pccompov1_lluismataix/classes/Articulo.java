/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.classes;

/**
 *
 * @author lluis
 */
public class Articulo {
    
    private int cod;
    private String nombre;
    private float precio;

    public Articulo(int cod, String nombre, float precio) {
        this.cod = cod;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCod() {
        return cod;
    }
    
    public String getNombre(){
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }
        
    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "articulo{" + "cod=" + cod + ", precio=" + precio + '}';
    }
    
    
}
