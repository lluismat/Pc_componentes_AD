/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.employee.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.employee.model.Model_employee;
import pccompov1_lluismataix.modules.employee.views.home_employee;
import pccompov1_lluismataix.modules.employee.views.products;
import pccompov1_lluismataix.modules.employee.views.users;
import pccompov1_lluismataix.modules.login.controller.Controller;
import pccompov1_lluismataix.modules.login.views.login;

/**
 *
 * @author lluis
 */
public class Controller_employee implements ActionListener{
    public static home_employee menu;
    public static users usersView;
    public static products productView;
    
    /**
     * Constructor del controlador del Sign In
     * @param start 
     * @param o 
     */
    public Controller_employee(JFrame start, int o) {
        switch (o) {
            case 0:
                menu = (home_employee) start;
                break;
            case 1:
                usersView = (users) start;
                break;
            case 2:
                productView = (products) start;
                break;
        }
    }
    
    public enum Action {

        //botones home usuario registrado
        newBtn,
        logoutBtn,
        editBtn,
        deleteBtn,
        choiceCombo,
        //botones vista campos usuario
        createBtn,
        saveBtn,
        cancelBtn,
        //botones vista campos articulo
        createArtBtn,
        saveArtBtn,
        cancelArtBtn,
        
    }
    
    public void start(int o) {
        
        switch (o) {
            case 0:
                menu.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, 555);//ancho x alto
                menu.setResizable(false);
                menu.setVisible(true);

                this.menu.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                this.menu.choiceCombo.setActionCommand("choiceCombo");
                this.menu.choiceCombo.addActionListener(this);
                this.menu.newBtn.setActionCommand("newBtn");
                this.menu.newBtn.addActionListener(this);
                this.menu.editBtn.setActionCommand("editBtn");
                this.menu.editBtn.addActionListener(this);
                this.menu.deleteBtn.setActionCommand("deleteBtn");
                this.menu.deleteBtn.addActionListener(this);
                this.menu.logoutBtn.setActionCommand("logoutBtn");
                this.menu.logoutBtn.addActionListener(this);
        
                singleton.dtm = new DefaultTableModel();
                String[] columnas = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock", "Categoria"};
                singleton.dtm.setColumnIdentifiers(columnas);
                menu.table.setModel(singleton.dtm);
                
                Model_employee.searchProducts();
            
               
                break;
            case 1:
                usersView.setSize(650, 555);//ancho x alto
                usersView.setResizable(false);
                usersView.setVisible(true);
                
                this.usersView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                this.usersView.createBtn.setActionCommand("createBtn");
                this.usersView.createBtn.addActionListener(this);
                this.usersView.saveBtn.setActionCommand("saveBtn");
                this.usersView.saveBtn.addActionListener(this);
                this.usersView.cancelBtn.setActionCommand("cancelBtn");
                this.usersView.cancelBtn.addActionListener(this);
                
                break;
            case 2:
                productView.setSize(800, 550);//ancho x alto
                productView.setResizable(false);
                productView.setVisible(true);
                
                this.productView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                Model_employee.getCategories();
                
                this.productView.createArtBtn.setActionCommand("createArtBtn");
                this.productView.createArtBtn.addActionListener(this);                
                this.productView.saveArtBtn.setActionCommand("saveArtBtn");
                this.productView.saveArtBtn.addActionListener(this);
                this.productView.cancelArtBtn.setActionCommand("cancelArtBtn");
                this.productView.cancelArtBtn.addActionListener(this);

                
                break;  
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String choice;
        boolean error;
        switch (Action.valueOf(ae.getActionCommand())) {
            //Acciones home empleado
            case choiceCombo:
                Model_employee.changeTable();
                break;
            case newBtn:
                
                choice = menu.choiceCombo.getSelectedItem().toString();
                
                switch (choice) {
                    case "Articulos":
                        menu.dispose();
                        new Controller_employee(new products(),2).start(2);
                        productView.saveArtBtn.setVisible(false);
                        productView.createArtBtn.setVisible(true);
                        break;
                    case "Usuarios":
                        menu.dispose();
                        new Controller_employee(new users(),1).start(1);
                        usersView.saveBtn.setVisible(false);
                        usersView.createBtn.setVisible(true);
                        break;
                }
                
                break;
            case editBtn:
                
                 error = Model_employee.getRow();
                if(!error){
                    choice = menu.choiceCombo.getSelectedItem().toString();
                    switch (choice) {
                        case "Articulos":
                            menu.dispose();
                            new Controller_employee(new products(),2).start(2);
                            productView.saveArtBtn.setVisible(true);
                            productView.createArtBtn.setVisible(false);
                            Model_employee.getDataProduct();
                            break;
                        case "Usuarios":
                            menu.dispose();
                            new Controller_employee(new users(),1).start(1);
                            usersView.saveBtn.setVisible(true);
                            usersView.createBtn.setVisible(false);
                            Model_employee.getDataUser();
                            break;
                    }
                }
                break;
            case deleteBtn:
                error = Model_employee.getRow();
                if(!error){
                    Model_employee.deleteRow();
                }
                break;
            case logoutBtn:
                singleton.user = null;
                menu.dispose();
                new Controller(new login()).start();
                break;
            //vista campos usuario
            case createBtn:
                error = Model_employee.newUser();
                if(!error){
                    usersView.dispose();
                    new Controller_employee(new home_employee(),0).start(0);
                }
                break;
            case saveBtn:
                error = Model_employee.editUser();
                if(!error){
                    usersView.dispose();
                    new Controller_employee(new home_employee(),0).start(0);
                }
                break;
            case cancelBtn:
                usersView.dispose();
                new Controller_employee(new home_employee(),0).start(0);
                break;
            //vista campos articulo
            case createArtBtn:
                error = Model_employee.newProduct();
                if(!error){
                    productView.dispose();
                    new Controller_employee(new home_employee(),0).start(0);
                }
                break;
            case saveArtBtn:
                error = Model_employee.editProduct();
                if(!error){
                    productView.dispose();
                    new Controller_employee(new home_employee(),0).start(0);
                }
                break;
            case cancelArtBtn:
                productView.dispose();
                new Controller_employee(new home_employee(),0).start(0);
                break;
        }
    }
}
