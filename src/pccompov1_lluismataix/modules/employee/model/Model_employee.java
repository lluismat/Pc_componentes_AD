/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.employee.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.employee.views.home_employee;
import pccompov1_lluismataix.modules.employee.views.products;
import pccompov1_lluismataix.modules.employee.views.users;

/**
 *
 * @author lluis
 */
public class Model_employee {
    //funcion para buscar todos los articulos y mostrarlos en la tabla
    public static void searchProducts(){
        try {
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM articulos");
            
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
            

            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            
            Statement stmt = singleton.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT c.nombre FROM articulos a,categorias c,prodcategoria p "
                    + "WHERE a.codigo = p.articulo AND c.categoria_id = p.categoria AND a.codigo = "+codigo);
            
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
    
    //funcion para cambiar la tabla
    public static void changeTable(){
        singleton.dtm = new DefaultTableModel();
        String choice = home_employee.choiceCombo.getSelectedItem().toString();
        switch (choice) {
            case "Articulos":
                String[] colArt = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock", "Categoria"};
                singleton.dtm.setColumnIdentifiers(colArt);
                home_employee.table.setModel(singleton.dtm);
                searchProducts();
                break;
            case "Usuarios":
                String[] colUser = {"Usuario_id", "Email", "Contrasenya", "Nombre", "Apellidos","Dnicif","Domicilio","Poblacion","Provincia","Telefono","Tarjeta"};
                singleton.dtm.setColumnIdentifiers(colUser);
                home_employee.table.setModel(singleton.dtm);
                searchUsers();
                break;
        }
    }
    
    //funcion para buscar los usuarios
    public static void searchUsers(){
        try {
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            
            while(rs.next()){
                singleton.dtm.addRow(getArrayObjetosUser(rs.getInt("usuario_id"),rs.getString("email"),rs.getString("contrasenya"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("dnicif"),rs.getString("domicilio"),rs.getString("poblacion"),rs.getString("provincia"),rs.getString("telefono"),rs.getString("tarjeta")));     
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    //funcion para montar la tabla
    public static Object[] getArrayObjetosUser(int usuario_id,String email,String contrasenya,String nombre,String apellidos,String dnicif,String domicilio,String poblacion,String provincia,String telefono,String tarjeta) {
        Object[] v = new Object[11];
            v[0] = usuario_id;
            v[1] = email;
            v[2] = contrasenya;
            v[3] = nombre;
            v[4] = apellidos;
            v[5] = dnicif;
            v[6] = domicilio;
            v[7] = poblacion;
            v[8] = provincia;
            v[9] = telefono;
            v[10] = tarjeta;
            
        return v;
    }
    
    public static boolean getRow(){
        boolean error = false;
        try{
            int row = home_employee.table.getSelectedRow();
            singleton.selectedRow = Integer.parseInt(singleton.dtm.getValueAt(row, 0).toString());
        }catch(Exception ex){
            error = true;
            System.err.println("Error: "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"No has seleccionado ninguna fila de la tabla! por favor selecciona uno fila", "Atención", JOptionPane.WARNING_MESSAGE);
        }
        return error;
    }
        
    //funcion para eliminar un usuario,articulo o categoria
    public static void deleteRow(){
        try {
            if (JOptionPane.showConfirmDialog(null, "Estas seguro que quieres eliminar este registro?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Statement stmt = singleton.conn.createStatement();
                    String choice = home_employee.choiceCombo.getSelectedItem().toString();
                    String sql = null;

                    switch (choice) {

                        case "Articulos":
                            sql = "DELETE FROM articulos WHERE codigo = "+singleton.selectedRow;
                            break;
                        case "Usuarios":
                           sql = "DELETE FROM usuarios WHERE usuario_id = "+singleton.selectedRow;
                            break;
                    }

                    stmt.executeUpdate(sql);
                    //vuelvo a pintar la tabla para que se vean los cambios
                    changeTable();
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
    //funciona para crear un nuevo usuario
    public static boolean newUser(){
        boolean error = false;
        try {
            if(!users.emailField.getText().isEmpty() && !users.passField.getText().isEmpty() && !users.dniField.getText().isEmpty()){
                
                Statement stmt = singleton.conn.createStatement();
           
                stmt.executeUpdate("INSERT INTO usuarios(email,contrasenya,nombre,apellidos,dnicif,domicilio,poblacion,provincia,telefono,tarjeta) "
                        + "VALUES ('"+users.emailField.getText()+
                "',md5('"+users.passField.getText()+"'),'"+users.nameField.getText()+"','"+users.surnameField.getText()+"',"
                        + "'"+users.dniField.getText()+"','"+users.homeField.getText()+"','"+users.cityField.getText()+"',"
                                + "'"+users.provinceField.getText()+"','"+users.mobileField.getText()+"','"+users.creditCardField.getText()+"')");   
            }else{
                error = true;
                JOptionPane.showMessageDialog(null,"Los campos de email,contraseña y dni no pueden estar vacios","Atención",JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException e) {
            error = true;
            JOptionPane.showMessageDialog(null,"Ha habido un error al crear un nuevo usuario","Error",JOptionPane.ERROR_MESSAGE);
            System.err.println("Error... " + e.getMessage());
        }
        return error;
    }
    
    //funcion para editar un usuario
    public static boolean editUser(){
        boolean error = false;
        try {
            Statement stmt = singleton.conn.createStatement();

            if(!users.emailField.getText().isEmpty() && !users.dniField.getText().isEmpty()){
                
                ResultSet rs = stmt.executeQuery("SELECT email,dnicif FROM usuarios WHERE usuario_id != "+singleton.selectedRow);
                while(rs.next()){
                    if(users.emailField.getText().equals(rs.getString("email"))){
                        error = true;
                        JOptionPane.showMessageDialog(null,"El email introducido ya esta siendo usado por otro usuario","Atención",JOptionPane.WARNING_MESSAGE);
            
                    }else if(users.dniField.getText().equals(rs.getString("dnicif"))){
                        error = true;
                        JOptionPane.showMessageDialog(null,"El dni introducido ya esta siendo usado por otro usuario","Atención",JOptionPane.WARNING_MESSAGE);
                    }
                }
                if(!error){
                    if(!users.passField.getText().isEmpty()){
                    stmt.executeUpdate("UPDATE usuarios SET contrasenya =md5('"+users.passField.getText()+"') WHERE usuario_id = '"+singleton.selectedRow+"'");   
                    }
                        
                    stmt.executeUpdate("UPDATE usuarios SET email ='"+users.emailField.getText()+
                        "', nombre = '"+users.nameField.getText()+
                        "', apellidos = '"+users.surnameField.getText()+
                        "', dnicif = '"+users.dniField.getText()+
                        "', domicilio = '"+users.homeField.getText()+
                        "', poblacion = '"+users.cityField.getText()+
                        "', provincia = '"+users.provinceField.getText()+
                        "', tarjeta = '"+users.creditCardField.getText()+
                        "', telefono = '"+users.mobileField.getText()+
                        "' WHERE usuario_id = '"+singleton.selectedRow+"'"); 
                }
                
            }else{
                error = true;
                JOptionPane.showMessageDialog(null,"Los campos email y dni no pueden estar vacios","Atención",JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            error = true;
            JOptionPane.showMessageDialog(null,"Ha habido un error al editar el usuario","Error",JOptionPane.ERROR_MESSAGE);
            System.err.println("Error... " + e.getMessage());
        }
        return error;
    }
    
    //funcion para crear un nuevo articulo
    public static boolean newProduct(){
        boolean error = false;
        try {
            if(!products.nameField.getText().isEmpty() && !products.fabField.getText().isEmpty() && !products.priceField.getText().isEmpty() && !products.stockField.getText().isEmpty()){
                Statement stmt = singleton.conn.createStatement();
           
                stmt.executeUpdate("INSERT INTO articulos(nombre,fabricante,precio,stock) "
                        + "VALUES ('"+products.nameField.getText()+
                        "','"+products.fabField.getText()+"',"+products.priceField.getText()+","+products.stockField.getText()+")");  
               
            }else{
                error = true;
                JOptionPane.showMessageDialog(null,"Debes rellenar todos los campos","Atención",JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException e) {
            error = true;
            JOptionPane.showMessageDialog(null,"Ha habido un error al crear un nuevo articulo","Error",JOptionPane.ERROR_MESSAGE);
            System.err.println("Error... " + e.getMessage());
        }
        return error;
        
    }
    
    //funcion para editar un articulo
    public static boolean editProduct(){
        boolean error = false;
        try {
            if(!products.nameField.getText().isEmpty() && !products.fabField.getText().isEmpty() && !products.priceField.getText().isEmpty() && !products.stockField.getText().isEmpty()){
                Statement stmt = singleton.conn.createStatement();
           
                stmt.executeUpdate("UPDATE articulos SET nombre ='"+products.nameField.getText()+
                        "', fabricante = '"+products.fabField.getText()+
                        "', precio = '"+products.priceField.getText()+
                        "', stock = '"+products.stockField.getText()+
                        "' WHERE codigo = '"+singleton.selectedRow+"'");  
               
            }else{
                error = true;
                JOptionPane.showMessageDialog(null,"Debes rellenar todos los campos","Atención",JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException e) {
            error = true;
            JOptionPane.showMessageDialog(null,"Ha habido un error al editar el articulo","Error",JOptionPane.ERROR_MESSAGE);
            System.err.println("Error... " + e.getMessage());
        }
        return error;
    }
    
    //funcion para pintar los datos del usuario
    public static void getDataUser(){
        try {
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE usuario_id = "+singleton.selectedRow);
            
            rs.first();
            
            users.emailField.setText(rs.getString("email"));
            users.nameField.setText(rs.getString("nombre"));
            users.surnameField.setText(rs.getString("apellidos"));
            users.dniField.setText(rs.getString("dnicif"));
            users.homeField.setText(rs.getString("domicilio"));
            users.cityField.setText(rs.getString("poblacion"));
            users.provinceField.setText(rs.getString("provincia"));
            users.mobileField.setText(rs.getString("telefono"));
            users.creditCardField.setText(rs.getString("tarjeta"));

        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    //funcion para pintar la lista de categorias
    public static void getCategories(){
        try {
            Statement stmt = singleton.conn.createStatement();
                    
            ResultSet rs = stmt.executeQuery("SELECT * FROM categorias");
            
            DefaultListModel modelo = new DefaultListModel();
            
            while(rs.next()){
                modelo.addElement(rs.getString("nombre"));
            }
            
            products.listCat.setModel(modelo);
            
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    //funcion para pintar los datos del articulo
    public static void getDataProduct(){
            try {
                Statement stmt = singleton.conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM articulos WHERE codigo = "+singleton.selectedRow);

                rs.first();

                products.nameField.setText(rs.getString("nombre"));
                products.fabField.setText(rs.getString("fabricante"));
                products.priceField.setText(rs.getString("precio"));
                products.stockField.setText(rs.getString("stock"));
                        
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
}
