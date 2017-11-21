/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.user_reg.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.login.controller.Controller;
import pccompov1_lluismataix.modules.login.views.login;
import pccompov1_lluismataix.modules.user_reg.model.Model_UserReg;
import pccompov1_lluismataix.modules.user_reg.views.home_RegisterUser;
import pccompov1_lluismataix.modules.user_reg.views.newOpinion;
import pccompov1_lluismataix.modules.user_reg.views.opinions;
import pccompov1_lluismataix.modules.user_reg.views.perfil;

/**
 *
 * @author lluis
 */
public class Controller_UserReg implements ActionListener{
    
    public static home_RegisterUser menuUserreg;
    public static perfil profile;
    public static opinions opinions;
    public static newOpinion newOpinion;
    /**
     * Constructor del controlador del Sign In
     * @param start 
     * @param o 
     */
    public Controller_UserReg(JFrame start, int o) {
        switch (o) {
            case 0:
                menuUserreg = (home_RegisterUser) start;
                break;
            case 1:
                profile = (perfil) start;
                break;
            case 2:
                opinions = (opinions) start;
                break;
            case 3:
                newOpinion = (newOpinion) start;
                break;
        }
    }
    
    public enum Action {

        //botones home usuario registrado
        profileBtn,
        logoutBtn,
        seeOpinionsBtn,
        //botones perfil
        backBtn,
        editBtn,
        saveBtn,
        cancelBtn,
        //botones opiniones
        OpinionBtn,
        volverBtn,
        //botones crear opinion
        cancelarBtn,
        createOpinionBtn,
        
    }
    
    public void start(int o) {
        
        switch (o) {
            case 0:
                menuUserreg.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, 555);//ancho x alto
                menuUserreg.setResizable(false);
                menuUserreg.setVisible(true);

                this.menuUserreg.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                this.menuUserreg.profileBtn.setActionCommand("profileBtn");
                this.menuUserreg.profileBtn.addActionListener(this);
                this.menuUserreg.seeOpinionsBtn.setActionCommand("seeOpinionsBtn");
                this.menuUserreg.seeOpinionsBtn.addActionListener(this);
                this.menuUserreg.logoutBtn.setActionCommand("logoutBtn");
                this.menuUserreg.logoutBtn.addActionListener(this);
        
                singleton.dtm = new DefaultTableModel();
                String[] columnas = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock", "Categoria"};
                singleton.dtm.setColumnIdentifiers(columnas);
                home_RegisterUser.table.setModel(singleton.dtm);
            
                Model_UserReg.searchProducts();
               
                break;
            case 1:
                profile.setSize(750, 555);//ancho x alto
                profile.setResizable(false);
                profile.setVisible(true);

                this.profile.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                this.profile.saveBtn.setActionCommand("saveBtn");
                this.profile.saveBtn.addActionListener(this);
                this.profile.cancelBtn.setActionCommand("cancelBtn");
                this.profile.cancelBtn.addActionListener(this);
                this.profile.editBtn.setActionCommand("editBtn");
                this.profile.editBtn.addActionListener(this);
                this.profile.backBtn.setActionCommand("backBtn");
                this.profile.backBtn.addActionListener(this);
                this.profile.editBtn.setActionCommand("editBtn");
                this.profile.editBtn.addActionListener(this);
                
                Model_UserReg.perfil_user();

                break;
            case 2:
                opinions.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, 555);//ancho x alto
                opinions.setResizable(false);
                opinions.setVisible(true);
                
                this.opinions.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                singleton.dtm = new DefaultTableModel();
                String[] columnasO = {"Pseudonimo", "Descripcion", "Fecha", "Ventajas", "incovenientes", "Recomienda", "Valoracion", "Calidad Precio"};
                singleton.dtm.setColumnIdentifiers(columnasO);
                opinions.table.setModel(singleton.dtm);
                Model_UserReg.seeOpinions();
                
                this.opinions.volverBtn.setActionCommand("volverBtn");
                this.opinions.volverBtn.addActionListener(this);
                this.opinions.OpinionBtn.setActionCommand("OpinionBtn");
                this.opinions.OpinionBtn.addActionListener(this);

                break;
            case 3:
                newOpinion.setSize(900, 600);//ancho x alto
                newOpinion.setResizable(false);
                newOpinion.setVisible(true);
                
                this.newOpinion.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                this.newOpinion.createOpinionBtn.setActionCommand("createOpinionBtn");
                this.newOpinion.createOpinionBtn.addActionListener(this);
                this.newOpinion.cancelarBtn.setActionCommand("cancelarBtn");
                this.newOpinion.cancelarBtn.addActionListener(this);
                
                break;    
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (Action.valueOf(ae.getActionCommand())) {
            //Acciones home usuario registrado
            case profileBtn:
                menuUserreg.dispose();
                new Controller_UserReg(new perfil(),1).start(1);
                break;
            case logoutBtn:
                singleton.user = null;
                menuUserreg.dispose();
                new Controller(new login()).start();
                break;
            case seeOpinionsBtn:
                boolean error = Model_UserReg.getProduct();
                if(!error){
                    menuUserreg.dispose();
                    new Controller_UserReg(new opinions(),2).start(2); 
                }
                break;
            //acciones perfil
            case backBtn:
                profile.dispose();
                new Controller_UserReg(new home_RegisterUser(),0).start(0);
                break;
            case editBtn:
                Model_UserReg.editProfile();
                break;
            case saveBtn:
                Model_UserReg.saveProfile();
                break;
            case cancelBtn:
                Model_UserReg.perfil_user();
                break;
                //acciones opiniones
            case OpinionBtn:
                boolean verify = Model_UserReg.verifyPurchase();
                if(verify){
                    opinions.dispose();
                    new Controller_UserReg(new newOpinion(),3).start(3);
                }else{
                    JOptionPane.showMessageDialog(null,"No puedes escribir una opinion de un articulo que no has comprado!","Atención",JOptionPane.WARNING_MESSAGE);
                }
                
                break;
            case volverBtn:
                singleton.selectedRow = 0;
                opinions.dispose();
                new Controller_UserReg(new home_RegisterUser(),0).start(0);
                break;
                //acciones crear opinion
            case createOpinionBtn:
                boolean insert = Model_UserReg.createOpinion();
                if(insert){
                    newOpinion.dispose();
                    new Controller_UserReg(new opinions(),2).start(2);
                }

                break;
            case cancelarBtn:
                newOpinion.dispose();
                new Controller_UserReg(new opinions(),2).start(2);
                break;
        }
    }
}
