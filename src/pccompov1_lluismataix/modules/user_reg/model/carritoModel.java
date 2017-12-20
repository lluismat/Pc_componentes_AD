/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.user_reg.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.user_reg.views.carritoView;

/**
 *
 * @author lluis
 */
public class carritoModel {
    
    public static boolean comprobaIfCarritoExist(){
        boolean carritoExist=false;
        
        try {
            singleton.dtm = new DefaultTableModel(); 
            String[] columnas = {"Id","Articulo","Cantidad","Precio"};
            singleton.dtm.setColumnIdentifiers(columnas);
            carritoView.carritoTable.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cestas WHERE usuario_id = "+singleton.user.getId());
            
            if(rs.first()){
                createComboCarrito();
                carritoView.panelCarrito.setVisible(false);
                Statement stmt2 = singleton.conn.createStatement();
                ResultSet rs1 = stmt2.executeQuery("SELECT * FROM lincesta,articulos WHERE articulo = codigo AND cesta = "+rs.getInt("cesta_id"));
               
                while(rs1.next()){
                    singleton.dtm.addRow(getArrayDeObjectosCarrito(rs1.getInt("articulo"),rs1.getString("nombre"),rs1.getInt("cantidad"),rs1.getFloat("precio")));
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "No tienes creado ningun carrito, crea un carrito");
                carritoView.panelCarrito.setVisible(true);
            }

        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
        
        return carritoExist;
    }
    
    //funcion para montar la tabla RAM
    public static Object[] getArrayDeObjectosCarrito(int codArt, String articulo,int cantidad,float precio) {
        Object[] v = new Object[4];
        
            v[0] = codArt;
            v[1] = articulo;
            v[2] = cantidad;
            v[3] = precio;

        return v;
    }
    
    public static void createComboCarrito(){
        try {
            ArrayList<String> carritos = new ArrayList();
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cestas WHERE usuario_id = "+singleton.user.getId());
            
                while(rs.next()){
                    carritos.add(rs.getString("nombre"));
                }
                //lleno el combobox con los nombres del carrito
                DefaultComboBoxModel model = new DefaultComboBoxModel(carritos.toArray());
                carritoView.comboCarritos.setModel(model);
                

        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    public static void createCarrito(){
        try {
            if(carritoView.panelCarrito.isVisible()){
                
                if(!carritoView.nameCarritoField.getText().equals("")){
                    Statement stmt = singleton.conn.createStatement();
            
                    Date date = Calendar.getInstance().getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    stmt.executeUpdate("INSERT INTO cestas (nombre,fecha,tipo,usuario_id) VALUES ('"+carritoView.nameCarritoField.getText()+"','"+String.valueOf(sdf.format(date))+
                            "','"+carritoView.grupo.getSelection().getActionCommand()+"','"+singleton.user.getId()+"')");
                    
                    createComboCarrito();
                    carritoView.panelCarrito.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Debes rellenar el nombre del nuevo carrito");
                }
            
            }else{
                carritoView.panelCarrito.setVisible(true);
            }
            
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    public static void deleteCarrito(){
        try {
            Statement stmt = singleton.conn.createStatement();

            createComboCarrito();
                
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
}
