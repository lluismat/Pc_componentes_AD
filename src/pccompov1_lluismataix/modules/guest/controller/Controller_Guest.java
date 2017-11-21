/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.guest.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.guest.model.Model_Guest;
import pccompov1_lluismataix.modules.guest.view.home_guest;
import pccompov1_lluismataix.modules.guest.view.opinions_guest;
import pccompov1_lluismataix.modules.login.controller.Controller;
import pccompov1_lluismataix.modules.login.views.login;

/**
 *
 * @author lluis
 */
public class Controller_Guest implements ActionListener{
    
    public static home_guest menuGuest;
    public static opinions_guest opinions;
    /**
     * Constructor del controlador del Invitado
     * @param start 
     * @param o 
     */
    public Controller_Guest(JFrame start, int o) {
        switch (o) {
            case 0:
                menuGuest = (home_guest) start;
                break;
            case 1:
                opinions = (opinions_guest) start;
                break;
        }
    }
    
    public enum Action {

        //botones home_guest
        returnBtn,
        opinionsBtn,
        //botones opiniones
        volverBtn,
        
    }
    
    public void start(int o) {
        
        switch (o) {
            case 0:
                menuGuest.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, 555);//ancho x alto
                menuGuest.setResizable(false);
                menuGuest.setVisible(true);

                this.menuGuest.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                                
                this.menuGuest.returnBtn.setActionCommand("returnBtn");
                this.menuGuest.returnBtn.addActionListener(this);
                this.menuGuest.opinionsBtn.setActionCommand("opinionsBtn");
                this.menuGuest.opinionsBtn.addActionListener(this);
                
                singleton.dtm = new DefaultTableModel();
                String[] columnas = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock", "Categoria"};
                singleton.dtm.setColumnIdentifiers(columnas);
                home_guest.table.setModel(singleton.dtm);
            
                Model_Guest.searchProducts();
                
                
               
                break;
            case 1:
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
                Model_Guest.seeOpinions();
                
                this.opinions.volverBtn.setActionCommand("volverBtn");
                this.opinions.volverBtn.addActionListener(this);

                break;
               
        }
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        switch (Action.valueOf(ae.getActionCommand())) {
            case returnBtn:
                    menuGuest.dispose();
                    new Controller(new login()).start();
            break;
            case opinionsBtn:
                boolean error = Model_Guest.getProduct();
                if(!error){
                    menuGuest.dispose();
                    new Controller_Guest(new opinions_guest(),1).start(1);
                }
                break;
            case volverBtn:
                singleton.selectedRow = 0;
                opinions.dispose();
                new Controller_Guest(new home_guest(),0).start(0);
                break;
        }
    }
    
}
