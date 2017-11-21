/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.guest.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.guest.view.home_guest;

/**
 *
 * @author lluis
 */
public class Model_Guest {
    
        //funcion para buscar todos los articulos y mostrarlos en la tabla
    public static void searchProducts(){
        try {
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM articulos");
            //SELECT a.*,c.nombre FROM articulos a,categorias c,prodcategoria p WHERE a.codigo = p.articulo AND c.categoria_id = p.categoria
            
            while(rs.next()){
                int stock = rs.getInt("stock");
                String stock2;
                if(stock>0){
                    stock2 = "Esta en Stock";
                }else{
                    stock2 = "No esta en Stock";
                }
                singleton.dtm.addRow(getArrayDeObjectos(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2));     
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    //funcion para montar la tabla
    public static Object[] getArrayDeObjectos(int codigo,String nombre,String fabricante,float precio, String stock) {
        Object[] v = new Object[6];
        try {
            Statement stmt = singleton.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT c.nombre FROM articulos a,categorias c,prodcategoria p "
                    + "WHERE a.codigo = p.articulo AND c.categoria_id = p.categoria AND a.codigo = "+codigo);

            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            
            if(rs.first()){
                String categories = rs.getString("nombre");
                while(rs.next()){
                    categories = categories + ", "+rs.getString("nombre");
                }

                v[5] = categories;
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return v;
    }
    
    public static boolean getProduct(){
        boolean error = false;
        try{
            int row = home_guest.table.getSelectedRow();
            singleton.selectedRow = Integer.parseInt(singleton.dtm.getValueAt(row, 0).toString());
        }catch(Exception ex){
            error = true;
            System.err.println("Error: "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"No has seleccionado ningun producto de la tabla! por favor selecciona uno para poder ver sus opiniones", "Atención", JOptionPane.WARNING_MESSAGE);
        }
        return error;
    }
    
    public static void seeOpinions(){
        try {
            Statement stmt = singleton.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM opiniones WHERE articulo = "+singleton.selectedRow);
            
            while(rs.next()){
                String recomend = "";
                if(rs.getByte("recomienda")==0){
                    recomend = "No";
                }else{
                    recomend = "Si";
                }
               
                singleton.dtm.addRow(getArrayDeObjectosOpiniones(rs.getString("pseudonimo"),rs.getString("descripcion"),rs.getDate("fecha"),rs.getString("ventajas"),rs.getString("inconvenientes"),recomend,rs.getInt("valoracion"),rs.getInt("calidadprecio")));     
            }
            
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
    //funcion para montar la tabla de opiniones
    public static Object[] getArrayDeObjectosOpiniones(String pseudonimo,String descripcion,Date fecha,String ventajas, String inconvenientes,String recomendado,int valoracion,int calidad_precio) {
        Object[] v = new Object[8];
        
            v[0] = pseudonimo;
            v[1] = descripcion;
            v[2] = fecha;
            v[3] = ventajas;
            v[4] = inconvenientes;
            v[5] = recomendado;
            v[6] = valoracion+"/5";
            v[7] = calidad_precio+"/5";

        return v;
    }
    
}
