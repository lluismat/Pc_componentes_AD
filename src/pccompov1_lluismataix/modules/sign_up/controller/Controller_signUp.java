/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.sign_up.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pccompov1_lluismataix.modules.login.controller.Controller;
import pccompov1_lluismataix.modules.login.views.login;
import pccompov1_lluismataix.modules.sign_up.model.Model_signUp;
import pccompov1_lluismataix.modules.sign_up.view.sign_up;
import pccompov1_lluismataix.modules.user_reg.controller.Controller_UserReg;
import pccompov1_lluismataix.modules.user_reg.views.home_RegisterUser;

/**
 *
 * @author lluis
 */
public class Controller_signUp implements ActionListener{
    
        public static sign_up sign_up;
    /**
     * Constructor del controlador del SignUp
     * @param start 
     */
    public Controller_signUp(JFrame start) {
        sign_up = (sign_up) start;
    }
    
    public enum Action {

        //botones SignUp
        loginBtn,
        registerBtn,
        
    }
    
    public void start() {
        
        sign_up.setSize(750, 700);//ancho x alto
        sign_up.setResizable(false);
        sign_up.setVisible(true);

        this.sign_up.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                System.exit(0);
            }
        });

        this.sign_up.loginBtn.setActionCommand("loginBtn");
        this.sign_up.loginBtn.addActionListener(this);
        this.sign_up.registerBtn.setActionCommand("registerBtn");
        this.sign_up.registerBtn.addActionListener(this);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (Action.valueOf(ae.getActionCommand())) {
            
            case loginBtn:
                
                sign_up.dispose();
                new Controller(new login()).start();
                
            break;
            
            case registerBtn:
                boolean login_user = Model_signUp.registerUser();
                if(login_user){
                    sign_up.dispose();
                    new Controller_UserReg(new home_RegisterUser(),0).start(0);
                }
                break;
        }
    }
    
}
