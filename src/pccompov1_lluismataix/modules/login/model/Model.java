/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.login.model;

import java.awt.Color;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pccompov1_lluismataix.classes.User;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.login.views.login;

/**
 *
 * @author lluis
 */
public class Model {
    

   
    public static void connectDB() {
        
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver).newInstance();
            System.out.println("Driver " + driver + " Registrado correctamente");

            //Abrir la conexion con la Base de Datos
            System.out.println("Conectando con la Base de datos...");
            String jdbcUrl = "jdbc:mysql://localhost:3306/pccompo";

            singleton.conn = DriverManager.getConnection(jdbcUrl, "batoi", "1234");
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static boolean searchUser(){
        int id;
        ResultSet rs;
        boolean login_user = false;
        
        PreparedStatement pstmt_loginUser=null;
        PreparedStatement pstmt_loginPass=null;
        
        try {
            if(login.usernameField.getText().isEmpty() || login.passField.getText().isEmpty()){
                login.errorEti.setText("Debes rellenar los 2 campos para poder iniciar sesión");
                login.errorEti.setForeground(Color.red);
            }else{
                pstmt_loginUser = singleton.conn.prepareStatement("select usuario_id, email, nombre from usuarios where usuario_id = ? OR email = ?");

                try{
                    pstmt_loginUser.setLong(1, Integer.parseInt(login.usernameField.getText()));
                    pstmt_loginUser.setString(2, login.usernameField.getText());
                }catch(NumberFormatException e){
                    pstmt_loginUser.setLong(1,0);
                    pstmt_loginUser.setString(2, login.usernameField.getText());
                }
                rs = pstmt_loginUser.executeQuery();
                
                if(rs.first()){
                    id = rs.getInt(1);
                    pstmt_loginPass = singleton.conn.prepareStatement("select * from usuarios where usuario_id = ? AND contrasenya = md5(?)");
                    pstmt_loginPass.setLong(1, id);
                    pstmt_loginPass.setString(2, login.passField.getText());
                    rs = pstmt_loginPass.executeQuery();
                    if(rs.first()){
                        login_user = true;
                        //System.out.println("id: "+rs.getInt(1)+ ", email: "+rs.getString(2)+", nombre: "+rs.getString(3));
                        login.errorEti.setText("Login Correcto");
                        login.errorEti.setForeground(Color.green);
                        
                        //meto el usuario que ha iniciado sesion en el singleton user
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
                        
                        //System.out.println(singleton.user.toString());

                    }else{
                        login.errorEti.setText("El usuario o la contraseña son incorrectos");
                        login.errorEti.setForeground(Color.red);
                    }
                }else{
                    login.errorEti.setText("El usuario o la contraseña son incorrectos");
                    login.errorEti.setForeground(Color.red);
                }
                rs.close();
            }
            
        } catch (SQLException e) {
            System.err.println("Error... " + e.getMessage());
        }
        return login_user;
    }
}
