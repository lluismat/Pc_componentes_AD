/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.sign_up.model;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import pccompov1_lluismataix.classes.User;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.sign_up.view.sign_up;

/**
 *
 * @author lluis
 */
public class Model_signUp {
    
    public static boolean registerUser(){
        
        boolean signUp = true;

        try {
            if(sign_up.emailField.getText().isEmpty() || sign_up.passField.getText().isEmpty() || sign_up.repeatPassField.getText().isEmpty() || sign_up.dniField.getText().isEmpty()){
                signUp = false;
                sign_up.errorEti.setText("Debes rellenar todos los campos para poder darse de alta");
                sign_up.errorEti.setForeground(Color.red);
            }else if(!sign_up.passField.getText().equals(sign_up.repeatPassField.getText())){
                signUp = false;
                sign_up.errorEti.setText("La contraseña no coincide");
                sign_up.errorEti.setForeground(Color.red);
            }else{
                Statement stmt = singleton.conn.createStatement();
           
                ResultSet rs = stmt.executeQuery("SELECT email,dnicif FROM usuarios");
                while(rs.next()){
                    if(sign_up.emailField.getText().equals(rs.getString("email"))){
                        signUp = false;
                        sign_up.errorEti.setText("Este email ya ha sido usado por otro usuario por favor pon otro");
                        sign_up.errorEti.setForeground(Color.red);
                    }else if(sign_up.dniField.getText().equals(rs.getString("dnicif"))){
                        signUp = false;
                        sign_up.errorEti.setText("Este dni ya ha sido usado por otro usuario por favor pon otro");
                        sign_up.errorEti.setForeground(Color.red);
                    }
                }
                if(signUp){
                    stmt.executeUpdate("INSERT INTO usuarios(email,contrasenya,dnicif,nombre,apellidos,domicilio,poblacion,provincia,telefono,tarjeta) "
                    + "VALUES ('"+sign_up.emailField.getText()+
                    "',md5('"+sign_up.passField.getText()+"'),'"+sign_up.dniField.getText()+"','','','','','','','')");
                    
                    JOptionPane.showMessageDialog(null,"Te has registrado con exito");
                    
                    rs = stmt.executeQuery("SELECT * FROM usuarios WHERE email = '"+sign_up.emailField.getText()+"'");
                    //meto el usuario que ha iniciado sesion en el singleton user
                    rs.first();
                    singleton.user = new User();
                    singleton.user.setId(rs.getInt("usuario_id"));
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
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error... " + e.getMessage());
        }
        
        return signUp;
    }
    
}
