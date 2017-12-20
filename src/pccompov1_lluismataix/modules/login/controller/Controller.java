/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.employee.controller.Controller_employee;
import pccompov1_lluismataix.modules.employee.views.home_employee;
import pccompov1_lluismataix.modules.guest.controller.Controller_Guest;
import pccompov1_lluismataix.modules.guest.view.home_guest;
import pccompov1_lluismataix.modules.login.model.Model;
import pccompov1_lluismataix.modules.user_reg.views.home_RegisterUser;
import pccompov1_lluismataix.modules.login.views.login;
import pccompov1_lluismataix.modules.sign_up.controller.Controller_signUp;
import pccompov1_lluismataix.modules.sign_up.view.sign_up;
import pccompov1_lluismataix.modules.user_reg.controller.Controller_UserReg;

/**
 *
 * @author lluis
 */
public class Controller implements ActionListener{
    
    public static login signIn;
    /**
     * Constructor del controlador del Sign In
     * @param start 
     */
    public Controller(JFrame start) {
        signIn = (login) start;
    }
    
    public enum Action {

        //botones SignIn
        loginBtn,
        guestBtn,
        registerBtn,
        
    }
    
    public void start() {
        
        signIn.setSize(750, 555);//ancho x alto
        signIn.setResizable(false);
        signIn.setVisible(true);

        this.signIn.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                System.exit(0);
            }
        });

        this.signIn.loginBtn.setActionCommand("loginBtn");
        this.signIn.loginBtn.addActionListener(this);
        this.signIn.guestBtn.setActionCommand("guestBtn");
        this.signIn.guestBtn.addActionListener(this);
        this.signIn.registerBtn.setActionCommand("registerBtn");
        this.signIn.registerBtn.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (Action.valueOf(ae.getActionCommand())) {
            
            case loginBtn:
                //boolean disp = false;
                boolean login_user = Model.searchUser();
                if(login_user){
                    signIn.dispose();
                    if(singleton.user.getId() == 1){
                        new Controller_employee(new home_employee(),0).start(0);   
                    }else{
                        new Controller_UserReg(new home_RegisterUser(),0).start(0);   
                    }
                }
                break;
            case guestBtn:
                    signIn.dispose();
                    new Controller_Guest(new home_guest(),0).start(0);
                break;
            case registerBtn:
                signIn.dispose();
                new Controller_signUp(new sign_up()).start();
                break;
        }
    }
}
