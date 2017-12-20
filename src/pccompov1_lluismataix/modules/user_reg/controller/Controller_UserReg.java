/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.user_reg.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.login.controller.Controller;
import pccompov1_lluismataix.modules.login.views.login;
import pccompov1_lluismataix.modules.user_reg.model.Filtro;
import pccompov1_lluismataix.modules.user_reg.model.Model_UserReg;
import pccompov1_lluismataix.modules.user_reg.model.carritoModel;
import pccompov1_lluismataix.modules.user_reg.views.carritoView;
import pccompov1_lluismataix.modules.user_reg.views.home_RegisterUser;
import pccompov1_lluismataix.modules.user_reg.views.newOpinion;
import pccompov1_lluismataix.modules.user_reg.views.opinions;
import pccompov1_lluismataix.modules.user_reg.views.perfil;

/**
 *
 * @author lluis
 */
public class Controller_UserReg implements KeyListener,ActionListener{
    
    public static home_RegisterUser menuUserreg;
    public static perfil profile;
    public static opinions opinions;
    public static newOpinion newOpinion;
    public static carritoView carrito;
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
            case 4:
                carrito = (carritoView) start;
                break;
        }
    }
    
    public enum Action {

        //botones home usuario registrado
        profileBtn,
        logoutBtn,
        seeOpinionsBtn,
        filterField,
        comboFilter,
        btnRam,
        motherboardBtn,
        displayBtn,
        hddBtn,
        processorBtn,
        printerBtn,
        btnSearchAll,
        cartBtn,
        addArtBtn,
        //options RAM
        ramType1,
        ramType2,
        ramType3,
        ramCap1,
        ramCap2,
        ramCap3,
        ramVel1,
        ramVel2,
        ramVel3,
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
        //botones carritoView
        AtrasCarritoBtn,
        changeCarrito,
        comboCarritos,
        createCarrito,
        deleteLineaBtn,
        deleteCartBtn,
        editLineaBtn,
        purchaseBtn,
    }
    
    public void start(int o) {
        
        switch (o) {
            case 0:
                menuUserreg.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width, 650);//ancho x alto
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
                //filtro
                this.menuUserreg.filterField.setActionCommand("filterField");
                this.menuUserreg.filterField.addActionListener(this);
                this.menuUserreg.filterField.setName("filterField");        
                this.menuUserreg.filterField.addKeyListener(this);
                this.menuUserreg.comboFilter.setActionCommand("comboFilter");
                this.menuUserreg.comboFilter.addActionListener(this);
                //botones categorias
                this.menuUserreg.btnRam.setActionCommand("btnRam");
                this.menuUserreg.btnRam.addActionListener(this);
                this.menuUserreg.motherboardBtn.setActionCommand("motherboardBtn");
                this.menuUserreg.motherboardBtn.addActionListener(this);
                this.menuUserreg.displayBtn.setActionCommand("displayBtn");
                this.menuUserreg.displayBtn.addActionListener(this);
                this.menuUserreg.processorBtn.setActionCommand("processorBtn");
                this.menuUserreg.processorBtn.addActionListener(this);
                this.menuUserreg.printerBtn.setActionCommand("printerBtn");
                this.menuUserreg.printerBtn.addActionListener(this);
                this.menuUserreg.hddBtn.setActionCommand("hddBtn");
                this.menuUserreg.hddBtn.addActionListener(this);
                this.menuUserreg.btnSearchAll.setActionCommand("btnSearchAll");
                this.menuUserreg.btnSearchAll.addActionListener(this);
                
                //botones carrito
                this.menuUserreg.cartBtn.setActionCommand("cartBtn");
                this.menuUserreg.cartBtn.addActionListener(this);
                this.menuUserreg.addArtBtn.setActionCommand("addArtBtn");
                this.menuUserreg.addArtBtn.addActionListener(this);
                //OPTIONS RAM
                this.menuUserreg.ramType1.setActionCommand("ramType1");
                this.menuUserreg.ramType1.addActionListener(this);
                this.menuUserreg.ramType2.setActionCommand("ramType2");
                this.menuUserreg.ramType2.addActionListener(this);
                this.menuUserreg.ramType3.setActionCommand("ramType3");
                this.menuUserreg.ramType3.addActionListener(this);
                
                this.menuUserreg.ramCap1.setActionCommand("ramCap1");
                this.menuUserreg.ramCap1.addActionListener(this);
                this.menuUserreg.ramCap2.setActionCommand("ramCap2");
                this.menuUserreg.ramCap2.addActionListener(this);
                this.menuUserreg.ramCap3.setActionCommand("ramCap3");
                this.menuUserreg.ramCap3.addActionListener(this);
                
                this.menuUserreg.ramVel1.setActionCommand("ramVel1");
                this.menuUserreg.ramVel1.addActionListener(this);
                this.menuUserreg.ramVel2.setActionCommand("ramVel2");
                this.menuUserreg.ramVel2.addActionListener(this);
                this.menuUserreg.ramVel3.setActionCommand("ramVel3");
                this.menuUserreg.ramVel3.addActionListener(this);
        
                home_RegisterUser.panelRam.setVisible(false);
                Filtro.searchAll();
                
                DefaultComboBoxModel model = new DefaultComboBoxModel(new String[]{"Nombre", "Precio mayor que", "Precio menor que", "Fabricante"});
                home_RegisterUser.comboFilter.setModel(model);
               
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
            case 4:
                carrito.setSize(1000, 500);//ancho x alto
                carrito.setResizable(false);
                carrito.setVisible(true);
                
                carritoModel.comprobaIfCarritoExist();
                
                this.carrito.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        JOptionPane.showMessageDialog(null,"Saliendo de la Aplicación");
                        System.exit(0);
                    }
                });
                
                this.carrito.createCarrito.setActionCommand("createCarrito");
                this.carrito.createCarrito.addActionListener(this);
                this.carrito.AtrasCarritoBtn.setActionCommand("AtrasCarritoBtn");
                this.carrito.AtrasCarritoBtn.addActionListener(this);
                
                break;
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        DefaultComboBoxModel model;
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
            case addArtBtn:
                
                break;
            case cartBtn:
                menuUserreg.dispose();
                new Controller_UserReg(new carritoView(),4).start(4); 
                break;
            //ACCIONES CARRITO  
            case createCarrito:
                carritoModel.createCarrito();
                break;
            case deleteCartBtn:
                break;
            case deleteLineaBtn:
                break;
            case changeCarrito:
                break;
            case editLineaBtn:
                break;
            case purchaseBtn:
                break;
            case comboCarritos:
                break;
            case AtrasCarritoBtn:
                break;
            //FILTRO
            case btnSearchAll:
                model = new DefaultComboBoxModel(new String[]{"Nombre", "Precio mayor que", "Precio menor que", "Fabricante"});
                home_RegisterUser.comboFilter.setModel(model);
                singleton.categorias="general";
                Filtro.searchAll();
                home_RegisterUser.panelRam.setVisible(false);
                break;
            case btnRam:
                model = new DefaultComboBoxModel(new String[]{"Nombre", "Precio mayor que", "Precio menor que", "Fabricante","Tipo","Capacidad","Velocidad"});
                home_RegisterUser.comboFilter.setModel(model);
                singleton.categorias="ram";
                Filtro.searchRam();
                home_RegisterUser.panelRam.setVisible(true);
                break;
            case processorBtn:
                model = new DefaultComboBoxModel(new String[]{"Nombre","Precio mayor que","Precio menor que","Fabricante","Nucleos","Tipo Socket","Velocidad"});
                home_RegisterUser.comboFilter.setModel(model);
                singleton.categorias="proc";
                Filtro.searchProcessors();
                home_RegisterUser.panelRam.setVisible(false);
                break;
            case displayBtn:
                model = new DefaultComboBoxModel(new String[]{"Nombre", "Precio mayor que", "Precio menor que", "Fabricante","Resolución","Tamaño"});
                home_RegisterUser.comboFilter.setModel(model);
                singleton.categorias="mon";
                Filtro.searchDisplays();
                home_RegisterUser.panelRam.setVisible(false);
                break;
            case hddBtn:
                model = new DefaultComboBoxModel(new String[]{"Nombre", "Precio mayor que", "Precio menor que", "Fabricante","Capacidad","RPM","Tipo"});
                home_RegisterUser.comboFilter.setModel(model);
                singleton.categorias="hdd";
                Filtro.searchHdd();
                home_RegisterUser.panelRam.setVisible(false);
                break;
            case printerBtn:
                model = new DefaultComboBoxModel(new String[]{"Nombre", "Precio mayor que", "Precio menor que", "Fabricante","Tipo","Color","PPM"});
                home_RegisterUser.comboFilter.setModel(model);
                singleton.categorias="imp";
                Filtro.searchPrinter();
                home_RegisterUser.panelRam.setVisible(false);
                break;
            case motherboardBtn:
                model = new DefaultComboBoxModel(new String[]{"Nombre", "Precio mayor que", "Precio menor que", "Fabricante","Tipo Procesador"});
                home_RegisterUser.comboFilter.setModel(model);
                singleton.categorias="pb";
                Filtro.searchMotherboard();
                home_RegisterUser.panelRam.setVisible(false);
                break;
             //opciones RAM
            case ramType1:
                if(home_RegisterUser.ramType1.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("ddR2");
                }
                break;
            case ramType2:
                if(home_RegisterUser.ramType2.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("ddR3");
                }
                break;
            case ramType3:
                if(home_RegisterUser.ramType3.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("ddR4");
                }
                break;
            case ramCap1:
                if(home_RegisterUser.ramCap1.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("1");
                }
                break;
            case ramCap2:
                if(home_RegisterUser.ramCap2.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("2");
                }
                break;
            case ramCap3:
                if(home_RegisterUser.ramCap3.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("4");
                }
                break;   
            case ramVel1:
                if(home_RegisterUser.ramVel1.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("400");
                }
                break;
            case ramVel2:
                if(home_RegisterUser.ramVel2.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("800");
                }
                break;
            case ramVel3:
                if(home_RegisterUser.ramVel3.isSelected()){
                    Filtro.searchRamBy();
                }else{
                    Filtro.deleteRam("1000");
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
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Action.valueOf(e.getComponent().getName())) {
            case filterField:
                Filtro.searchArtByFilter();
                break;
        }
    }
}
