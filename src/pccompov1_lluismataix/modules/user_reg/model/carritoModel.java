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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.Articulo;
import pccompov1_lluismataix.classes.CarritoClass;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.user_reg.views.carritoView;
import pccompov1_lluismataix.modules.user_reg.views.home_RegisterUser;
import pccompov1_lluismataix.modules.user_reg.views.newPedido;
import pccompov1_lluismataix.modules.user_reg.views.pedidosView;

/**
 *
 * @author lluis
 */
public class carritoModel {
    
    public static boolean comprobaIfCarritoExist(){
        boolean carritoExist=false;
        
        try {
            singleton.dtm = new DefaultTableModel(); 
            String[] columnas = {"Linea","Id","Articulo","Cantidad","Precio","Importe"};
            singleton.dtm.setColumnIdentifiers(columnas);
            carritoView.carritoTable.setModel(singleton.dtm);
            carritoView.panelCantidad.setVisible(false);
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cestas WHERE usuario_id = "+singleton.user.getId());
            
            if(rs.first()){
                
                if(singleton.carritoActivo!=null){
                    rs.beforeFirst();
                    while(rs.next()){
                        if(rs.getInt("cesta_id") == singleton.carritoActivo.getId()){
                            createComboCarrito();
                            carritoView.panelCarrito.setVisible(false);
                            Statement stmt2 = singleton.conn.createStatement();
                            ResultSet rs1 = stmt2.executeQuery("SELECT * FROM lincesta,articulos WHERE articulo = codigo AND cesta = "+rs.getInt("cesta_id"));

                            while(rs1.next()){
                                singleton.dtm.addRow(getArrayDeObjectosCarrito(rs1.getInt("linea"),rs1.getInt("articulo"),rs1.getString("nombre"),rs1.getInt("cantidad"),rs1.getFloat("precio"),rs1.getFloat("importe")));
                            }
                        }
                    }
                }else{
                    createComboCarrito();
                    carritoView.panelCarrito.setVisible(false);
                    Statement stmt2 = singleton.conn.createStatement();
                    ResultSet rs1 = stmt2.executeQuery("SELECT * FROM lincesta,articulos WHERE articulo = codigo AND cesta = "+rs.getInt("cesta_id"));

                    while(rs1.next()){
                        singleton.dtm.addRow(getArrayDeObjectosCarrito(rs1.getInt("linea"),rs1.getInt("articulo"),rs1.getString("nombre"),rs1.getInt("cantidad"),rs1.getFloat("precio"),rs1.getFloat("importe")));
                    }
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
    
    public static void seeCarts(CarritoClass carrito){
        singleton.dtm = new DefaultTableModel(); 
        String[] columnas = {"Linea","Id","Articulo","Cantidad","Precio","Importe"};
        singleton.dtm.setColumnIdentifiers(columnas);
        carritoView.carritoTable.setModel(singleton.dtm);
            
        try {
            Statement stmt2 = singleton.conn.createStatement();
            ResultSet rs1 = stmt2.executeQuery("SELECT * FROM lincesta,articulos WHERE articulo = codigo AND cesta = "+carrito.getId());

            while(rs1.next()){
                singleton.dtm.addRow(getArrayDeObjectosCarrito(rs1.getInt("linea"),rs1.getInt("articulo"),rs1.getString("nombre"),rs1.getInt("cantidad"),rs1.getFloat("precio"),rs1.getFloat("importe")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(carritoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Object[] getArrayDeObjectosCarrito(int linea,int codArt, String articulo,int cantidad,float precio,float importe) {
        Object[] v = new Object[6];
        
            v[0] = linea;
            v[1] = codArt;
            v[2] = articulo;
            v[3] = cantidad;
            v[4] = precio;
            v[5] = importe;

        return v;
    }
    
    public static void createComboCarrito(){
        DefaultComboBoxModel model;
        try {
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cestas WHERE usuario_id = "+singleton.user.getId());
            
            //lleno el combobox con los nombres del carrito
            model = new DefaultComboBoxModel();
            carritoView.comboCarritos.setModel(model);
            int count=0;
            while(rs.next()){
                model.addElement(new CarritoClass(rs.getInt("cesta_id"),rs.getString("nombre")));
                if(singleton.carritoActivo!=null){
                    if(singleton.carritoActivo.getId()==rs.getInt("cesta_id")){
                        carritoView.comboCarritos.setSelectedIndex(count);
                    }
                }
                count++;  
            }
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
    
    public static void changeCarrito(){
            try {
                if(carritoView.panelCarrito.isVisible()){
                    if(!carritoView.nameCarritoField.getText().equals("")){
                        ResultSet rs = null;
                        singleton.dtm = new DefaultTableModel();
                        Statement stmt = singleton.conn.createStatement();

                        stmt.executeUpdate("UPDATE cestas SET nombre = '"+carritoView.nameCarritoField.getText()+"', tipo = '"+carritoView.grupo.getSelection().getActionCommand()+"' "
                                + "WHERE cesta_id = "+singleton.carritoActivo.getId());

                        createComboCarrito();
                        carritoView.panelCarrito.setVisible(false);
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

            stmt.executeUpdate("DELETE FROM cestas WHERE cesta_id = "+singleton.carritoActivo.getId());
            
            createComboCarrito();
                
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    public static void addArtToCart(){

        if(singleton.carritoActivo != null){   
            try {
                Statement stmt = singleton.conn.createStatement();
                Statement stmt2 = singleton.conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM lincesta,articulos WHERE articulo = codigo AND cesta = "+singleton.carritoActivo.getId());
                if(rs.first()){

                    rs.beforeFirst();
                    int updateifexist=0;
                    while(rs.next()){
                        if(rs.getInt("articulo")==singleton.articuloSelected.getCod()){
                            stmt2.executeUpdate("UPDATE lincesta SET cantidad = "
                          +(rs.getInt("cantidad")+1)+", importe = "+singleton.articuloSelected.getPrecio()*(rs.getInt("cantidad")+1)
                          +"WHERE linea = "+rs.getInt("linea"));
                            updateifexist = 1;
                        }
                    }
                    if(updateifexist == 0){
                        rs.last();
                        stmt2.executeUpdate("INSERT INTO lincesta (linea,cesta,articulo,cantidad,precio,importe) "
                        + "VALUES ('"+(rs.getRow()+1)+"','"+singleton.carritoActivo.getId()+
                        "','"+singleton.articuloSelected.getCod()+"',"+1+","+singleton.articuloSelected.getPrecio()+","+singleton.articuloSelected.getPrecio()*1+")");
                    }

                }else{
                    stmt2.executeUpdate("INSERT INTO lincesta (linea,cesta,articulo,cantidad,precio,importe) "
                    + "VALUES (1,'"+singleton.carritoActivo.getId()+
                    "','"+singleton.articuloSelected.getCod()+"',"+1+","
                    + ""+singleton.articuloSelected.getPrecio()+","+singleton.articuloSelected.getPrecio()*1+")");
                }
                JOptionPane.showMessageDialog(null, "El articulo se ha añadido a tu carrito activo "+singleton.carritoActivo.getNombre());
            } catch (SQLException ex) {
                System.err.println("Error: "+ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "No tienes ninguna cesta activa, por favor selecciona la cesta activa entrando en el boton del carrito y seleccionando el carrito que desee.");
        }
    }
    
    public static boolean selectArticulo(){
        boolean error = false;
        try{
            int row = home_RegisterUser.table.getSelectedRow();
            int id = Integer.parseInt(singleton.dtm.getValueAt(row, 0).toString());
            String nombre = singleton.dtm.getValueAt(row, 1).toString();
            float precio = Float.parseFloat(singleton.dtm.getValueAt(row, 3).toString());
            
            singleton.articuloSelected = new Articulo(id,nombre,precio);
            
        }catch(Exception ex){
            error = true;
            System.err.println("Error: "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"No has seleccionado ningun producto de la tabla! por favor selecciona uno para poder añadirlo a el carro", "Atención", JOptionPane.WARNING_MESSAGE);
        }
        return error;
    }
    
    public static boolean selectLinea(){
       boolean error = false;
       
        try{
            int row = carritoView.carritoTable.getSelectedRow();
            singleton.selectedRow = Integer.parseInt(singleton.dtm.getValueAt(row, 0).toString());
            
        }catch(Exception ex){
            error = true;
            System.err.println("Error: "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"No has seleccionado ninguna linea de la tabla! por favor selecciona uno", "Atención", JOptionPane.WARNING_MESSAGE);
        }
        return error;
    }
    
    public static void deleteLinea(){
        try {
            Statement stmt = singleton.conn.createStatement();
            Statement stmt2 = singleton.conn.createStatement();

            stmt.executeUpdate("DELETE FROM lincesta WHERE linea = "+singleton.selectedRow+" AND cesta = "+singleton.carritoActivo.getId());

            
            ResultSet rs1 = stmt2.executeQuery("SELECT * FROM lincesta WHERE cesta = "+singleton.carritoActivo.getId());

            while(rs1.next()){
                if(rs1.getInt("linea")>singleton.selectedRow){
                    stmt.executeUpdate("UPDATE lincesta SET linea = ("+rs1.getInt("linea")+"-1)"
                            + "WHERE linea = "+rs1.getInt("linea")+" AND cesta = "+singleton.carritoActivo.getId());
                }
            }
            
            carritoView.comboCarritos.setSelectedItem(singleton.carritoActivo);
            seeCarts(singleton.carritoActivo);
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    public static void changeLinea(){
        try {
            if(carritoView.panelCantidad.isVisible()){
                if(!carritoView.cantidadField.getText().equals("")){
                    ResultSet rs = null;
                    singleton.dtm = new DefaultTableModel();
                    Statement stmt = singleton.conn.createStatement();
                    int cantidad = Integer.parseInt(carritoView.cantidadField.getText());
                    stmt.executeUpdate("UPDATE lincesta SET cantidad = '"+cantidad+"',importe = (precio *"+cantidad+")"
                            + "WHERE linea = "+singleton.selectedRow+" AND cesta = "+singleton.carritoActivo.getId());

                    carritoView.comboCarritos.setSelectedItem(singleton.carritoActivo);
                    carritoView.panelCantidad.setVisible(false);
                    seeCarts(singleton.carritoActivo);
                }
            }else{
                carritoView.panelCantidad.setVisible(true);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(NumberFormatException ex){
            System.err.println("La cantidad debe ser un numero entero");
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    public static void printPedidoData(){
        try {
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM lincesta WHERE cesta = "+singleton.carritoActivo.getId());
            
            float importe_total = 0;
            while(rs1.next()){
                importe_total = importe_total + rs1.getFloat("importe");
            }
            newPedido.totalField.setText(String.valueOf(importe_total));
            
        } catch (SQLException ex) {
            Logger.getLogger(carritoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void createPedido(){
        if(singleton.carritoActivo != null){
            try {
                Statement stmt = singleton.conn.createStatement();
                Statement stmt2 = singleton.conn.createStatement();

                Date date = Calendar.getInstance().getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                ResultSet rs1 = stmt2.executeQuery("SELECT * FROM lincesta WHERE cesta = "+singleton.carritoActivo.getId());

                float importe_total = 0;
                while(rs1.next()){
                    importe_total = importe_total + rs1.getFloat("importe");
                }
                
                stmt.executeUpdate("INSERT INTO pedidos (cesta_id,fecha,divisa,fpago,fenvio,subtotal,iva,total) "
                        + "VALUES ('"+singleton.carritoActivo.getId()+"','"+String.valueOf(sdf.format(date))+"','EUR','"+newPedido.comboPago.getSelectedItem()+
                        "','"+newPedido.envioGrupo.getSelection().getActionCommand()+"',"+importe_total+",0,"+importe_total+")");

            } catch (SQLException ex) {
                System.err.println("SQL Error: "+ex);
            }catch(Exception ex){
                System.err.println("Error: "+ex);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay ningun carrito activo");
        }
    }
    
    public static void seePedidos(){
        try {
            ResultSet rs = null;
            singleton.dtm = new DefaultTableModel(); 
            String[] columnas = {"Num.Pedido","Cesta_id","Fecha","Divisa","F.Pago","F.Envio","Subtotal","IVA","Total"};
            singleton.dtm.setColumnIdentifiers(columnas);
            pedidosView.pedidoTable.setModel(singleton.dtm);
            Statement stmt = singleton.conn.createStatement();

            rs = stmt.executeQuery("SELECT p.* FROM pedidos p,cestas c WHERE p.cesta_id = c.cesta_id AND usuario_id = "+singleton.user.getId());

            while(rs.next()){
                singleton.dtm.addRow(getArrayDeObjectosPedidos(rs.getInt("nump"),rs.getInt("cesta_id"),rs.getString("fecha"),rs.getString("divisa"),rs.getString("fpago"),rs.getString("fenvio"),rs.getFloat("subtotal"),rs.getFloat("iva"),rs.getFloat("total")));
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    public static void carritoFinished(){
        try {
            ResultSet rs;
            Statement stmt = singleton.conn.createStatement();

            if(singleton.carritoActivo!=null){
                rs = stmt.executeQuery("SELECT * FROM pedidos WHERE cesta_id = "+singleton.carritoActivo.getId());

            }else{
                CarritoClass cart = (CarritoClass) carritoView.comboCarritos.getSelectedItem();
                rs = stmt.executeQuery("SELECT * FROM pedidos WHERE cesta_id = "+cart.getId());
            }
            
            if(rs.first()){
                carritoView.changeCarrito.setEnabled(false);
                carritoView.deleteCartBtn.setEnabled(false);
                carritoView.deleteLineaBtn.setEnabled(false);
                carritoView.editLineaBtn.setEnabled(false);
                carritoView.purchaseBtn.setEnabled(false);
            }else{
                carritoView.changeCarrito.setEnabled(true);
                carritoView.deleteCartBtn.setEnabled(true);
                carritoView.deleteLineaBtn.setEnabled(true);
                carritoView.editLineaBtn.setEnabled(true);
                carritoView.purchaseBtn.setEnabled(true);
            }
                    
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    public static Object[] getArrayDeObjectosPedidos(int pedido,int cesta, String fecha,String divisa,String fpago,String fenvio,float subtotal,float iva,float total) {
        Object[] v = new Object[9];
        
            v[0] = pedido;
            v[1] = cesta;
            v[2] = fecha;
            v[3] = divisa;
            v[4] = fpago;
            v[5] = fenvio;
            v[6] = subtotal;
            v[7] = iva;
            v[8] = total;

        return v;
    }
    
    public static boolean selectPedido(){
       boolean error = false;
       
        try{
            int row = pedidosView.pedidoTable.getSelectedRow();
            singleton.selectedRow = Integer.parseInt(singleton.dtm.getValueAt(row, 0).toString());
            
        }catch(Exception ex){
            error = true;
            System.err.println("Error: "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"No has seleccionado ningun pedido de la tabla! por favor selecciona uno", "Atención", JOptionPane.WARNING_MESSAGE);
        }
        return error;
    }
    
    public static void deletePedido(){
        try {
            Statement stmt = singleton.conn.createStatement();

            stmt.executeUpdate("DELETE FROM pedidos WHERE nump = "+singleton.selectedRow);
            
            carritoView.comboCarritos.setSelectedItem(singleton.carritoActivo);
               
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
}
