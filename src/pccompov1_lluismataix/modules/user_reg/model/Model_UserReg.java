/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.user_reg.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import pccompov1_lluismataix.classes.CarritoClass;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.user_reg.views.home_RegisterUser;
import pccompov1_lluismataix.modules.user_reg.views.newOpinion;
import pccompov1_lluismataix.modules.user_reg.views.perfil;

/**
 *
 * @author lluis
 */
public class Model_UserReg {
    
    //funcion para mostrar todos los datos del usuario en el perfil
    public static void perfil_user(){
        //pongo en invisible el boton de guardar y cancelar
        
        perfil.saveBtn.setEnabled(false);
        perfil.saveBtn.setVisible(false);
        perfil.cancelBtn.setEnabled(false);
        perfil.cancelBtn.setVisible(false);
        
        //meto todos los datos del usuario en los campos de la vista
        
        perfil.nameField.setText(singleton.user.getName());
        perfil.surnameField.setText(singleton.user.getSurname());
        perfil.emailField.setText(singleton.user.getEmail());
        perfil.dniField.setText(singleton.user.getDni());
        perfil.passField.setText("");
        perfil.repeatPassField.setText("");
        perfil.homeField.setText(singleton.user.getHome());
        perfil.cityField.setText(singleton.user.getCity());
        perfil.provinceField.setText(singleton.user.getProvince());
        perfil.mobileField.setText(singleton.user.getMobile());
        perfil.creditCardField.setText(singleton.user.getCreditCard());
        
        //desactivo los campos
        
        perfil.nameField.setEditable(false);
        perfil.surnameField.setEditable(false);
        perfil.emailField.setEditable(false);
        perfil.dniField.setEditable(false);
        perfil.passField.setEditable(false);
        perfil.repeatPassField.setEditable(false);
        perfil.homeField.setEditable(false);
        perfil.cityField.setEditable(false);
        perfil.provinceField.setEditable(false);
        perfil.mobileField.setEditable(false);
        perfil.creditCardField.setEditable(false);
        
    }
    
    //funcion editar perfil para activar los campos y el boton de guardar
    public static void editProfile(){
        
        //Activo los campos
        
        perfil.nameField.setEditable(true);
        perfil.surnameField.setEditable(true);
        perfil.emailField.setEditable(true);
        perfil.dniField.setEditable(true);
        perfil.passField.setEditable(true);
        perfil.repeatPassField.setEditable(true);
        perfil.homeField.setEditable(true);
        perfil.cityField.setEditable(true);
        perfil.provinceField.setEditable(true);
        perfil.mobileField.setEditable(true);
        perfil.creditCardField.setEditable(true);
        
        //Activo el boton de guardar y cancelar
        
        perfil.saveBtn.setEnabled(true);
        perfil.saveBtn.setVisible(true);
        perfil.cancelBtn.setEnabled(true);
        perfil.cancelBtn.setVisible(true);
        
    }
    
    //funcion para guardar los datos modificados por el usuario
    public static void saveProfile(){
        boolean error =false;
        try {
            Statement stmt;
            stmt = singleton.conn.createStatement();

            if(perfil.passField.getText().equals(perfil.repeatPassField.getText()) && !perfil.emailField.getText().isEmpty() && !perfil.dniField.getText().isEmpty()){
                ResultSet rs = stmt.executeQuery("SELECT email,dnicif FROM usuarios WHERE usuario_id != "+singleton.selectedRow);
                while(rs.next()){
                    if(perfil.emailField.getText().equals(rs.getString("email"))){
                        error = true;
                        JOptionPane.showMessageDialog(null,"El email introducido ya esta siendo usado por otro usuario","Atención",JOptionPane.WARNING_MESSAGE);
            
                    }else if(perfil.dniField.getText().equals(rs.getString("dnicif"))){
                        error = true;
                        JOptionPane.showMessageDialog(null,"El dni introducido ya esta siendo usado por otro usuario","Atención",JOptionPane.WARNING_MESSAGE);
                    }
                }
                if(!error){
                    if(!perfil.passField.getText().isEmpty()){
                    stmt.executeUpdate("UPDATE usuarios SET contrasenya =md5('"+perfil.passField.getText()+"') WHERE usuario_id = '"+singleton.user.getId()+"'");   
                    }
                            
                    stmt.executeUpdate("UPDATE usuarios SET email ='"+perfil.emailField.getText()+
                        "', nombre = '"+perfil.nameField.getText()+
                        "', apellidos = '"+perfil.surnameField.getText()+
                        "', dnicif = '"+perfil.dniField.getText()+
                        "', domicilio = '"+perfil.homeField.getText()+
                        "', poblacion = '"+perfil.cityField.getText()+
                        "', provincia = '"+perfil.provinceField.getText()+
                        "', tarjeta = '"+perfil.creditCardField.getText()+
                        "', telefono = '"+perfil.mobileField.getText()+
                        "' WHERE usuario_id = '"+singleton.user.getId()+"'");  
                
                    rs = stmt.executeQuery("SELECT * FROM usuarios WHERE usuario_id = '"+singleton.user.getId()+"'");

                    rs.first();
                    //meto el usuario que ha iniciado sesion en el singleton user
                    singleton.user.setEmail(rs.getString("email"));
                    singleton.user.setPass(rs.getString("contrasenya"));
                    singleton.user.setName(rs.getString("nombre"));
                    singleton.user.setSurname(rs.getString("apellidos"));
                    singleton.user.setDni(rs.getString("dnicif"));
                    singleton.user.setHome(rs.getString("domicilio"));
                    singleton.user.setCity(rs.getString("poblacion"));
                    singleton.user.setProvince(rs.getString("provincia"));
                    singleton.user.setMobile(rs.getString("telefono"));
                    singleton.user.setCreditCard(rs.getString("tarjeta"));

                    perfil_user();
                }
                
            }else{
                error = true;
                JOptionPane.showMessageDialog(null,"Las contraseña deben coincidir y los campos email y dni no pueden estar vacios","Atención",JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException ex) {
            error = true;
            JOptionPane.showMessageDialog(null,"Error al guardar los datos","Error",JOptionPane.ERROR_MESSAGE);
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
    //funcion para comprobar si el usuario ha comprado el producto
    public static boolean verifyPurchase(){
        boolean purchase=false;
        
        try {
            Statement stmt = singleton.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT l.articulo FROM cestas c,lincesta l,pedidos p,facturas f WHERE c.cesta_id = l.cesta AND c.cesta_id = p.cesta_id AND p.nump = f.pedido AND c.usuario_id = '"+singleton.user.getId()+"'");
            
            while(rs.next()){
                if(rs.getInt("articulo") == singleton.selectedRow){
                    purchase=true;
                }
            }      
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        
        return purchase;
    }
    
    public static boolean getProduct(){
        boolean error = false;
        try{
            int row = home_RegisterUser.table.getSelectedRow();
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
    
    public static String getCurrentDate() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calobj = Calendar.getInstance();
        String strDate = df.format(calobj.getTime());
        return strDate;
    }   
    
    //funcion para crear una opinion
    public static boolean createOpinion(){
        boolean insert = false;
        try {
            Statement stmt = singleton.conn.createStatement();
            
            if(!newOpinion.descriptionArea.getText().isEmpty() && !newOpinion.ventajasArea.getText().isEmpty() && !newOpinion.inconvenientesArea.getText().isEmpty()){
                insert = true;
                int rb = 0;
                if(newOpinion.rbSi.isSelected()){
                    rb = 1;
                }else{
                    rb = 0;
                }
                
                stmt.executeUpdate("INSERT INTO opiniones (articulo,pseudonimo,fecha,descripcion,ventajas,inconvenientes,recomienda,valoracion,calidadprecio) VALUES("
                        + singleton.selectedRow+",'anonimo','"+getCurrentDate()+"','"+newOpinion.descriptionArea.getText()+
                        "','"+newOpinion.ventajasArea.getText()+"','"+newOpinion.inconvenientesArea.getText()+"',"+rb+
                        ","+newOpinion.valorationCombo.getSelectedItem().toString()+","+newOpinion.calidadprecioCombo.getSelectedItem().toString()+")");  
            }else{
                insert = false;
                JOptionPane.showMessageDialog(null, "Los campos descripcion,ventajas e inconvenientes no pueden estar vacion","Atención",JOptionPane.WARNING_MESSAGE);
            }
            
            
        } catch (SQLException ex) {
            insert = false;
            JOptionPane.showMessageDialog(null, "Algo ha ido mal al crear la nueva opinión, intentalo de nuevo","Error",JOptionPane.ERROR_MESSAGE);
            System.err.println("Error: "+ex.getMessage());
        }
        return insert;
    }
    
//        public static void createComboCarritoHome(){
//        
//        try {
//            DefaultComboBoxModel model = new DefaultComboBoxModel();
//            Statement stmt = singleton.conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM cestas WHERE usuario_id = "+singleton.user.getId());
//            
//            //lleno el combobox con los nombres del carrito
//            
//            home_RegisterUser.cestaActCombo.setModel(model);
//            int count=0;
//            while(rs.next()){
//                model.addElement(new CarritoClass(rs.getInt("cesta_id"),rs.getString("nombre")));
//                if(singleton.carritoActivo!=null){
//                    if(rs.getInt("cesta_id")==singleton.carritoActivo.getId()){
//                        //home_RegisterUser.cestaActCombo.setSelectedIndex(count);
//                    }
//                }
//                count++;
//            }
//            
//        } catch (SQLException ex) {
//            System.err.println("SQL Error: "+ex);
//        }catch(Exception ex){
//            System.err.println("Error: "+ex);
//        }
//    }
    
}
