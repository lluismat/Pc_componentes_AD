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
public class CarritoClass {
  private String nombre;
  private int id ;
  
  public CarritoClass(int id,String nombre) {
    this.nombre=nombre;
    this.id=id;
  }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
