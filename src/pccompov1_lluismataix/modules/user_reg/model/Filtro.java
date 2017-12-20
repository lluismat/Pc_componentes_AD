/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.user_reg.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import pccompov1_lluismataix.classes.singleton;
import pccompov1_lluismataix.modules.user_reg.views.home_RegisterUser;

/**
 *
 * @author lluis
 */

public class Filtro {
    
    private static String[] general = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock", "Categoria"};
    private static String[] ram = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock","Tipo","Capacidad","Velocidad"};
    private static String[] proc = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock","Velocidad","Tipo Socket","Nucleos","Caracteristicas"};
    private static String[] displays = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock","Tamaño","Resolución"};
    private static String[] hdd={"Codigo", "Nombre", "Fabricante", "Precio", "Stock","Capacidad","RPM","Tipo Disco Duro"};
    private static String[] printers={"Codigo", "Nombre", "Fabricante", "Precio", "Stock","Tipo","Color","Ppm"};
    private static String[] mb = {"Codigo", "Nombre", "Fabricante", "Precio", "Stock","Tipo Procesador","Caracteristicas"};
    
    public static void searchArtByFilter(){
        switch(singleton.categorias){
            case "general":
                generalFilter();
                break;
            case "ram":
                ramFilter();
                break;
            case "hdd":
                hddFilter();
                break;
            case "mon":
                displayFilter();
                break;
            case "proc":
                procFilter();
                break;
            case "imp":
                printerFilter();
                break;
            case "pb":
                mbFilter();
                break;
        }
    }
    //funcion para filtrar todos los articulos segun criterio
    public static void generalFilter(){
        try {
            String choice = home_RegisterUser.comboFilter.getSelectedItem().toString();
            ResultSet rs = null;
            singleton.dtm = new DefaultTableModel();
            
            singleton.dtm.setColumnIdentifiers(general);
            home_RegisterUser.table.setModel(singleton.dtm);
            Statement stmt = singleton.conn.createStatement();
                        
            switch (choice) {
                case "Nombre":
                    //BUSCA POR NOMBRE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT DISTINCT * FROM articulos  WHERE nombre LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
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
                    }else{
                        searchAll();
                    }
                    break;
                case "Precio mayor que":
                    //BUSCA POR PRECIO MAYOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT DISTINCT * FROM articulos  WHERE precio > '"+home_RegisterUser.filterField.getText()+"'");
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
                    }else{
                        searchAll();
                    }
                    break;
                case "Precio menor que":
                    //BUSCA POR PRECIO MENOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT DISTINCT * FROM articulos  WHERE precio < '"+home_RegisterUser.filterField.getText()+"'");
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
                    }else{
                        searchAll();
                    }
                    break;                    
                case "Fabricante":
                    //BUSCA POR FABRICANTE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT DISTINCT * FROM articulos  WHERE fabricante LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
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
                    }else{
                        searchAll();
                    }
                    break;
                default:
                    
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }   
    //funcion para filtrar ram segun criterio
    public static void ramFilter(){
        try {
            String choice = home_RegisterUser.comboFilter.getSelectedItem().toString();
            ResultSet rs = null;
            singleton.dtm = new DefaultTableModel();
            singleton.dtm.setColumnIdentifiers(ram);
            home_RegisterUser.table.setModel(singleton.dtm);
            Statement stmt = singleton.conn.createStatement();
                        
            switch (choice) {
                case "Nombre":
                    //BUSCA POR NOMBRE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND  nombre LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }else{
                        searchRam();
                    }
                    break;
                case "Precio mayor que":
                    //BUSCA POR PRECIO MAYOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND precio > '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }else{
                        searchRam();
                    }
                    break;
                case "Precio menor que":
                    //BUSCA POR PRECIO MENOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND precio < '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }else{
                        searchRam();
                    }
                    break;                    
                case "Fabricante":
                    //BUSCA POR FABRICANTE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND fabricante LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }else{
                        searchRam();
                    }
                    break;
                case "Tipo":
                    //BUSCA POR TIPO
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND tipo LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }else{
                        searchRam();
                    }
                    break;
                case "Capacidad":
                    //BUSCA POR CAPACIDAD
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND capacidad = '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }else{
                        searchRam();
                    }
                    break;
                case "Velocidad":
                    //BUSCA POR VELOCIDAD   
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND velocidad = '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                        singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }else{
                        searchRam();
                    }
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    //funcion para filtrar monitores segun criterio
    public static void displayFilter(){
        try {
            String choice = home_RegisterUser.comboFilter.getSelectedItem().toString();
            ResultSet rs = null;
            singleton.dtm = new DefaultTableModel();
            
            singleton.dtm.setColumnIdentifiers(displays);
            home_RegisterUser.table.setModel(singleton.dtm);
            Statement stmt = singleton.conn.createStatement();
                        
            switch (choice) {
                case "Nombre":
                    //BUSCA POR NOMBRE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tam,r.resolucion FROM articulos a,mon r WHERE a.codigo = r.producto_id AND nombre LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosMon(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("tam"),rs.getString("resolucion")));
                        }
                    }else{
                        searchDisplays();
                    }
                    break;
                case "Precio mayor que":
                    //BUSCA POR PRECIO MAYOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tam,r.resolucion FROM articulos a,mon r WHERE a.codigo = r.producto_id AND precio > '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosMon(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("tam"),rs.getString("resolucion")));
                        }
                    }else{
                        searchDisplays();
                    }
                    break;
                case "Precio menor que":
                    //BUSCA POR PRECIO MENOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tam,r.resolucion FROM articulos a,mon r WHERE a.codigo = r.producto_id AND precio < '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosMon(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("tam"),rs.getString("resolucion")));
                        }
                    }else{
                        searchDisplays();
                    }
                    break;                    
                case "Fabricante":
                    //BUSCA POR FABRICANTE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tam,r.resolucion FROM articulos a,mon r WHERE a.codigo = r.producto_id AND fabricante LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosMon(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("tam"),rs.getString("resolucion")));
                        }
                    }else{
                        searchDisplays();
                    }
                    break;
                case "Resolución":
                    //BUSCA POR RESOLUCION
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tam,r.resolucion FROM articulos a,mon r WHERE a.codigo = r.producto_id AND resolucion LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosMon(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("tam"),rs.getString("resolucion")));
                        }
                    }else{
                        searchDisplays();
                    }
                    break;
                case "Tamaño":
                    //BUSCA POR TAMAÑO
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT *,r.tam,r.resolucion FROM articulos a,mon r WHERE a.codigo = r.producto_id AND tam LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosMon(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("tam"),rs.getString("resolucion")));
                        }
                    }else{
                        searchDisplays();
                    }
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    //funcion para filtrar placas base segun criterio
    public static void mbFilter(){
        try {
            String choice = home_RegisterUser.comboFilter.getSelectedItem().toString();
            ResultSet rs = null;
            singleton.dtm = new DefaultTableModel();
            
            singleton.dtm.setColumnIdentifiers(mb);
            home_RegisterUser.table.setModel(singleton.dtm);
            Statement stmt = singleton.conn.createStatement();
                        
            switch (choice) {
                case "Nombre":
                    //BUSCA POR NOMBRE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,pb r WHERE a.codigo = r.codart AND nombre LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosPB(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipoprocesador"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchMotherboard();
                    }
                    break;
                case "Precio mayor que":
                    //BUSCA POR PRECIO MAYOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,pb r WHERE a.codigo = r.codart AND precio > '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosPB(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipoprocesador"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchMotherboard();
                    }
                    break;
                case "Precio menor que":
                    //BUSCA POR PRECIO MENOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,pb r WHERE a.codigo = r.codart AND precio < '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosPB(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipoprocesador"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchMotherboard();
                    }
                    break;                    
                case "Fabricante":
                    //BUSCA POR FABRICANTE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,pb r WHERE a.codigo = r.codart AND fabricante LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosPB(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipoprocesador"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchMotherboard();
                    }
                    break;
                case "Tipo Procesador":
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,pb r WHERE a.codigo = r.producto_id AND tipoprocesador LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosPB(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipoprocesador"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchMotherboard();
                    }
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    //funcion para filtrar discos duros segun criterio
    public static void hddFilter(){
        try {
            String choice = home_RegisterUser.comboFilter.getSelectedItem().toString();
            ResultSet rs = null;
            singleton.dtm = new DefaultTableModel();
            
            singleton.dtm.setColumnIdentifiers(hdd);
            home_RegisterUser.table.setModel(singleton.dtm);
            Statement stmt = singleton.conn.createStatement();
                        
            switch (choice) {
                case "Nombre":
                    //BUSCA POR NOMBRE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart AND nombre LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));
                        }
                    }else{
                        searchHdd();
                    }
                    break;
                case "Precio mayor que":
                    //BUSCA POR PRECIO MAYOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart AND precio > '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));
                        }
                    }else{
                        searchHdd();
                    }
                    break;
                case "Precio menor que":
                    //BUSCA POR PRECIO MENOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart AND precio < '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));
                        }
                    }else{
                        searchHdd();
                    }
                    break;                    
                case "Fabricante":
                    //BUSCA POR FABRICANTE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart AND fabricante LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));
                        }
                    }else{
                        searchHdd();
                    }
                    break;
                case "Capacidad":
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart AND capacidad LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));
                        }
                    }else{
                        searchHdd();
                    }
                    break;
                case "RPM":
                    //BUSCA POR RESOLUCION
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart AND rpm LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));
                        }
                    }else{
                        searchHdd();
                    }
                    break;
               case "Tipo":
                    //BUSCA POR RESOLUCION
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart AND tipo LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));
                        }
                    }else{
                        searchHdd();
                    }
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    //funcion para filtrar procesadores segun criterio
    public static void procFilter(){
       try {
            String choice = home_RegisterUser.comboFilter.getSelectedItem().toString();
            ResultSet rs = null;
            singleton.dtm = new DefaultTableModel();
            
            singleton.dtm.setColumnIdentifiers(proc);
            home_RegisterUser.table.setModel(singleton.dtm);
            Statement stmt = singleton.conn.createStatement();
                        
            switch (choice) {
                case "Nombre":
                    //BUSCA POR NOMBRE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart AND nombre LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchProcessors();
                    }
                    break;
                case "Precio mayor que":
                    //BUSCA POR PRECIO MAYOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart AND precio > '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchProcessors();
                    }
                    break;
                case "Precio menor que":
                    //BUSCA POR PRECIO MENOR
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart AND precio < '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchProcessors();
                    }
                    break;                    
                case "Fabricante":
                    //BUSCA POR FABRICANTE
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart AND fabricante LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchProcessors();
                    }
                    break;
                case "Nucleos":
                    //BUSCA POR RESOLUCION
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart AND nucleos = '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchProcessors();
                    }
                    break;
                case "Tipo Socket":
                    //BUSCA POR RESOLUCION
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart AND tiposocket LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchProcessors();
                    }
                    break;
               case "Velocidad":
                    if(!home_RegisterUser.filterField.getText().equals("")){
                        rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart AND velocidad = '"+home_RegisterUser.filterField.getText()+"'");
                        while(rs.next()){
                            int stock = rs.getInt("stock");
                            String stock2;
                            if(stock>0){
                                stock2 = "Esta en Stock";
                            }else{
                                stock2 = "No esta en Stock";
                            }
                            singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));
                        }
                    }else{
                        searchProcessors();
                    }
                    break;
            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        } 
    }
    
    //funcion para filtrar impresoras segun criterio
    public static void printerFilter(){
               try {
                String choice = home_RegisterUser.comboFilter.getSelectedItem().toString();
                ResultSet rs = null;
                singleton.dtm = new DefaultTableModel();

                singleton.dtm.setColumnIdentifiers(printers);
                home_RegisterUser.table.setModel(singleton.dtm);
                Statement stmt = singleton.conn.createStatement();

                switch (choice) {
                    case "Nombre":
                        //BUSCA POR NOMBRE
                        if(!home_RegisterUser.filterField.getText().equals("")){
                            rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart AND nombre LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                            while(rs.next()){
                                int stock = rs.getInt("stock");
                                String stock2;
                                if(stock>0){
                                    stock2 = "Esta en Stock";
                                }else{
                                    stock2 = "No esta en Stock";
                                }
                                int color = rs.getInt("color");
                                String color2;
                                if(color!=0){
                                    color2 = "Si";
                                }else{
                                    color2 = "No";
                                }
                
                                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));
                            }
                        }else{
                            searchPrinter();
                        }
                        break;
                    case "Precio mayor que":
                        //BUSCA POR PRECIO MAYOR
                        if(!home_RegisterUser.filterField.getText().equals("")){
                            rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart AND precio > '"+home_RegisterUser.filterField.getText()+"'");
                            while(rs.next()){
                                int stock = rs.getInt("stock");
                                String stock2;
                                if(stock>0){
                                    stock2 = "Esta en Stock";
                                }else{
                                    stock2 = "No esta en Stock";
                                }
                                int color = rs.getInt("color");
                                String color2;
                                if(color!=0){
                                    color2 = "Si";
                                }else{
                                    color2 = "No";
                                }
                
                                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));
                            }
                        }else{
                            searchPrinter();
                        }
                        break;
                    case "Precio menor que":
                        //BUSCA POR PRECIO MENOR
                        if(!home_RegisterUser.filterField.getText().equals("")){
                            rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart AND precio < '"+home_RegisterUser.filterField.getText()+"'");
                            while(rs.next()){
                                int stock = rs.getInt("stock");
                                String stock2;
                                if(stock>0){
                                    stock2 = "Esta en Stock";
                                }else{
                                    stock2 = "No esta en Stock";
                                }
                                int color = rs.getInt("color");
                                String color2;
                                if(color!=0){
                                    color2 = "Si";
                                }else{
                                    color2 = "No";
                                }
                
                                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));
                            }
                        }else{
                            searchPrinter();
                        }
                        break;                    
                    case "Fabricante":
                        //BUSCA POR FABRICANTE
                        if(!home_RegisterUser.filterField.getText().equals("")){
                            rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart AND fabricante LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                            while(rs.next()){
                                int stock = rs.getInt("stock");
                                String stock2;
                                if(stock>0){
                                    stock2 = "Esta en Stock";
                                }else{
                                    stock2 = "No esta en Stock";
                                }
                                int color = rs.getInt("color");
                                String color2;
                                if(color!=0){
                                    color2 = "Si";
                                }else{
                                    color2 = "No";
                                }
                
                                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));
                            }
                        }else{
                            searchPrinter();
                        }
                        break;
                    case "Tipo":
                        
                        if(!home_RegisterUser.filterField.getText().equals("")){
                            rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart AND tipo LIKE '%"+home_RegisterUser.filterField.getText()+"%'");
                            while(rs.next()){
                                int stock = rs.getInt("stock");
                                String stock2;
                                if(stock>0){
                                    stock2 = "Esta en Stock";
                                }else{
                                    stock2 = "No esta en Stock";
                                }
                                int color = rs.getInt("color");
                                String color2;
                                if(color!=0){
                                    color2 = "Si";
                                }else{
                                    color2 = "No";
                                }
                
                                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));
                            }
                        }else{
                            searchPrinter();
                        }
                        break;
                    case "Color":
                        String filterString = home_RegisterUser.filterField.getText();
                        filterString = filterString.toLowerCase();
                        
                        if(!filterString.equals("") || filterString.equals("si") || filterString.equals("no")){
                            int ifcolor=-1;
                            if(filterString.equals("si")){
                                ifcolor=1;
                            }else if(filterString.equals("no")){
                                ifcolor=0;
                            }
                            
                            rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart AND color = '"+ifcolor+"'");
                            while(rs.next()){
                                int stock = rs.getInt("stock");
                                String stock2;
                                if(stock>0){
                                    stock2 = "Esta en Stock";
                                }else{
                                    stock2 = "No esta en Stock";
                                }
                                int color = rs.getInt("color");
                                String color2;
                                if(color!=0){
                                    color2 = "Si";
                                }else{
                                    color2 = "No";
                                }
                
                                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));
                            }
                        }else{
                            searchPrinter();
                        }
                        break;
                   case "PPM":
                        if(!home_RegisterUser.filterField.getText().equals("")){
                            rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart AND ppm = '"+home_RegisterUser.filterField.getText()+"'");
                            while(rs.next()){
                                int stock = rs.getInt("stock");
                                String stock2;
                                if(stock>0){
                                    stock2 = "Esta en Stock";
                                }else{
                                    stock2 = "No esta en Stock";
                                }
                                int color = rs.getInt("color");
                                String color2;
                                if(color!=0){
                                    color2 = "Si";
                                }else{
                                    color2 = "No";
                                }
                
                                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));
                            }
                        }else{
                            searchPrinter();
                        }
                        break;
                }
            } catch (SQLException ex) {
                System.err.println("SQL Error: "+ex);
            }catch(Exception ex){
                System.err.println("Error: "+ex);
            } 
    }
    
    //funcion para montar la tabla RAM
    public static Object[] getArrayDeObjectosRam(int codigo,String nombre,String fabricante,float precio, String stock, String tipo, int capacidad, int velocidad) {
        Object[] v = new Object[8];
        
            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            v[5] = tipo;
            v[6] = capacidad;
            v[7] = velocidad;

        return v;
    }
    
    //buscar todos los articulos
    public static void searchAll(){
        try {
            singleton.dtm = new DefaultTableModel(); 
            singleton.dtm.setColumnIdentifiers(general);
            home_RegisterUser.table.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM articulos");
            //SELECT a.*,c.nombre FROM articulos a,categorias c,prodcategoria p WHERE a.codigo = p.articulo AND c.categoria_id = p.categoria
            
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
            Statement stmt = singleton.conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT c.nombre FROM articulos a,categorias c,prodcategoria p "
                    + "WHERE a.codigo = p.articulo AND c.categoria_id = p.categoria AND a.codigo = "+codigo);

            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            
            if(rs.first()){
                String categories = rs.getString("nombre");
                while(rs.next()){
                    categories = categories + ", "+rs.getString("nombre");
                }
                v[5] = categories;
            }
            
        } catch (SQLException ex) {
            
        }
        return v;
    }
    
    //buscar ram
    public static void searchRam(){
        try {
            singleton.ram = 0;
            for( int i=0; i<home_RegisterUser.panelRam.getComponentCount(); i++ ) {
              if( home_RegisterUser.panelRam.getComponent(i) instanceof JCheckBox){
                JCheckBox checkBox = (JCheckBox)home_RegisterUser.panelRam.getComponent(i);
                checkBox.setSelected(false);
              }
            }
                        
            singleton.dtm = new DefaultTableModel();
            singleton.dtm.setColumnIdentifiers(ram);
            home_RegisterUser.table.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart");
         
            while(rs.next()){
                int stock = rs.getInt("stock");
                String stock2;
                if(stock>0){
                    stock2 = "Esta en Stock";
                }else{
                    stock2 = "No esta en Stock";
                }
                
                singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));

            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    //resetear la tabla de ram
    public static void resetTableRam(){
        singleton.dtm = new DefaultTableModel();
        singleton.dtm.setColumnIdentifiers(ram);
        home_RegisterUser.table.setModel(singleton.dtm);
        
    }
    
    //buscar ram segun los checkbox activados
    public static void searchRamBy(){
        try {
            
            if(singleton.ram == 0){
                resetTableRam();
            }

            ResultSet rs = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            
            Statement stmt = singleton.conn.createStatement();
            Statement stmt1 = singleton.conn.createStatement();
            Statement stmt2 = singleton.conn.createStatement();
            
            for( int i=0; i<home_RegisterUser.panelRam.getComponentCount(); i++ ) {
              if( home_RegisterUser.panelRam.getComponent(i) instanceof JCheckBox){
                JCheckBox checkBox = (JCheckBox)home_RegisterUser.panelRam.getComponent(i);
                if(checkBox.isSelected()) {
                    String value = checkBox.getToolTipText();
                    if(checkBox.getActionCommand().contains("ramType")){
                        singleton.ram = 1;
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND tipo = '"+value+"'");
                    }else if(checkBox.getActionCommand().contains("ramCap")){
                        singleton.ram = 1;
                        rs1 = stmt1.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND capacidad = "+Integer.parseInt(value)+"");
                    }else if(checkBox.getActionCommand().contains("ramVel")){
                        singleton.ram = 1;
                        rs2 = stmt2.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND velocidad = "+Integer.parseInt(value)+"");
                    }
                }
              }
            }
            
            if(rs == null && rs1 == null && rs2 == null){
                singleton.ram = 0;
                resetTableRam();
                rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart");
                while(rs.next()){
                    int stock = rs.getInt("stock");
                    String stock2;
                    if(stock>0){
                        stock2 = "Esta en Stock";
                    }else{
                        stock2 = "No esta en Stock";
                    }
                    singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));    
                }
            }else{
                if(rs != null){
                    while(rs.next()){
                        int stock = rs.getInt("stock");
                        String stock2;
                        if(stock>0){
                            stock2 = "Esta en Stock";
                        }else{
                            stock2 = "No esta en Stock";
                        }
                        boolean repeat = false;
                        for(int i=0; i<singleton.dtm.getRowCount();i++){
                            if(Integer.parseInt(singleton.dtm.getValueAt(i, 0).toString())==rs.getInt("codigo")){
                                repeat = true;
                            }
                        }
                        if(!repeat){
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                        }
                    }
                }
                if(rs1!=null){
                    while(rs1.next()){
                        int stock = rs1.getInt("stock");
                        String stock2;
                        if(stock>0){
                            stock2 = "Esta en Stock";
                        }else{
                            stock2 = "No esta en Stock";
                        }
                        boolean repeat = false;
                        for(int i=0; i<singleton.dtm.getRowCount();i++){
                            if(Integer.parseInt(singleton.dtm.getValueAt(i, 0).toString())==rs1.getInt("codigo")){
                                repeat = true;
                            }
                        }
                        if(!repeat){
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs1.getInt("codigo"),rs1.getString("nombre"),rs1.getString("fabricante"),rs1.getFloat("precio"),stock2,rs1.getString("tipo"),rs1.getInt("capacidad"),rs1.getInt("velocidad")));
                        }
                    }
                }
                if(rs2 != null){
                    while(rs2.next()){
                        int stock = rs2.getInt("stock");
                        String stock2;
                        if(stock>0){
                            stock2 = "Esta en Stock";
                        }else{
                            stock2 = "No esta en Stock";
                        }
                        boolean repeat = false;
                        for(int i=0; i<singleton.dtm.getRowCount();i++){
                            if(Integer.parseInt(singleton.dtm.getValueAt(i, 0).toString())==rs2.getInt("codigo")){
                                repeat = true;
                            }
                        }
                        if(!repeat){
                            singleton.dtm.addRow(getArrayDeObjectosRam(rs2.getInt("codigo"),rs2.getString("nombre"),rs2.getString("fabricante"),rs2.getFloat("precio"),stock2,rs2.getString("tipo"),rs2.getInt("capacidad"),rs2.getInt("velocidad")));
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    //eliminar la ram cuando se deselecciona un checkbox
    public static void deleteRam(String value){
        try {
            
            if(singleton.ram == 0){
                resetTableRam();
            }

            ResultSet rs = null;
            Statement stmt = singleton.conn.createStatement();
            
            for( int i=0; i<home_RegisterUser.panelRam.getComponentCount();i++) {
              if( home_RegisterUser.panelRam.getComponent(i) instanceof JCheckBox){
                JCheckBox checkBox = (JCheckBox)home_RegisterUser.panelRam.getComponent(i);
                if(checkBox.getToolTipText().equals(value)) {
                    if(checkBox.getActionCommand().contains("ramType")){
                        singleton.ram = 1;
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND tipo = '"+value+"'");
                    }else if(checkBox.getActionCommand().contains("ramCap")){
                        singleton.ram = 1;
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND capacidad = "+Integer.parseInt(value)+"");
                    }else if(checkBox.getActionCommand().contains("ramVel")){
                        singleton.ram = 1;
                        rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart AND velocidad = "+Integer.parseInt(value)+"");
                    }
                }
              }
            }
            if(rs!=null){
                while(rs.next()){
                    for(int i=0; i<singleton.dtm.getRowCount();i++){
                        if(Integer.parseInt(singleton.dtm.getValueAt(i, 0).toString())==rs.getInt("codigo")){
                            singleton.dtm.removeRow(i);
                        }
                    }
                }
            }
            
            if(singleton.dtm.getRowCount() == 0){
                singleton.ram = 0;
                resetTableRam();
                rs = stmt.executeQuery("SELECT *,r.tipo,r.capacidad,r.velocidad FROM articulos a,ram r WHERE a.codigo = r.codart");
                while(rs.next()){
                    int stock = rs.getInt("stock");
                    String stock2;
                    if(stock>0){
                        stock2 = "Esta en Stock";
                    }else{
                        stock2 = "No esta en Stock";
                    }
                    singleton.dtm.addRow(getArrayDeObjectosRam(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),rs.getInt("capacidad"),rs.getInt("velocidad")));
                }
            }
                
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    //funcion para montar la tabla de monitores
    public static Object[] getArrayDeObjectosMon(int codigo,String nombre,String fabricante,float precio, String stock, int tam, String resolucion){
        Object[] v = new Object[7];
        
            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            v[5] = tam;
            v[6] = resolucion;

        return v;
    }
    
    //funcion para buscar monitores
    public static void searchDisplays(){
        try {
                        
            singleton.dtm = new DefaultTableModel(); 
            singleton.dtm.setColumnIdentifiers(displays);
            home_RegisterUser.table.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *,r.tam,r.resolucion FROM articulos a,mon r WHERE a.codigo = r.producto_id");
         
            while(rs.next()){
                int stock = rs.getInt("stock");
                String stock2;
                if(stock>0){
                    stock2 = "Esta en Stock";
                }else{
                    stock2 = "No esta en Stock";
                }
                
                singleton.dtm.addRow(getArrayDeObjectosMon(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("tam"),rs.getString("resolucion")));

            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    //funcion para montar tabla procesadores
    public static Object[] getArrayDeObjectosProc(int codigo,String nombre,String fabricante,float precio, String stock, int vel, String tipo, int nucleos,String caract){
        Object[] v = new Object[9];
        
            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            v[5] = vel;
            v[6] = tipo;
            v[7] = nucleos;
            v[8] = caract;

        return v;
    }
    
    //buscar procesadores
    public static void searchProcessors(){
        try {
                        
            singleton.dtm = new DefaultTableModel();
            
            singleton.dtm.setColumnIdentifiers(proc);
            home_RegisterUser.table.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM articulos a,proc r WHERE a.codigo = r.codart");
         
            while(rs.next()){
                int stock = rs.getInt("stock");
                String stock2;
                if(stock>0){
                    stock2 = "Esta en Stock";
                }else{
                    stock2 = "No esta en Stock";
                }
                
                singleton.dtm.addRow(getArrayDeObjectosProc(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("velocidad"),rs.getString("tiposocket"),rs.getInt("nucleos"),rs.getString("caracteristicas")));

            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    //funcion para montar la tabla de placas base
    public static Object[] getArrayDeObjectosPB(int codigo,String nombre,String fabricante,float precio, String stock, String tipo, String caract){
        Object[] v = new Object[7];
        
            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            v[5] = tipo;
            v[6] = caract;

        return v;
    }
    
    //buscar placas base
    public static void searchMotherboard(){
        try {           
            singleton.dtm = new DefaultTableModel();
            singleton.dtm.setColumnIdentifiers(mb);
            home_RegisterUser.table.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM articulos a,pb r WHERE a.codigo = r.codart");
         
            while(rs.next()){
                int stock = rs.getInt("stock");
                String stock2;
                if(stock>0){
                    stock2 = "Esta en Stock";
                }else{
                    stock2 = "No esta en Stock";
                }
                
                singleton.dtm.addRow(getArrayDeObjectosPB(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipoprocesador"),rs.getString("caracteristicas")));

            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
    
    //funcion para montar tabla discos duros
    public static Object[] getArrayDeObjectosHDD(int codigo,String nombre,String fabricante,float precio, String stock, int capacidad,int rpm,String tipo){
        Object[] v = new Object[8];
        
            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            v[5] = capacidad;
            v[6] = rpm;
            v[7] = tipo;

        return v;
    }
    
    //funcion para buscar discos duros
    public static void searchHdd(){
        try {           
            singleton.dtm = new DefaultTableModel();
            singleton.dtm.setColumnIdentifiers(hdd);
            home_RegisterUser.table.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM articulos a,dd r WHERE a.codigo = r.codart");
         
            while(rs.next()){
                int stock = rs.getInt("stock");
                String stock2;
                if(stock>0){
                    stock2 = "Esta en Stock";
                }else{
                    stock2 = "No esta en Stock";
                }
                
                singleton.dtm.addRow(getArrayDeObjectosHDD(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getInt("capacidad"),rs.getInt("rpm"),rs.getString("tipo")));

            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }

    //funcion para montar la tabla de impresoras
    public static Object[] getArrayDeObjectosPrinter(int codigo,String nombre,String fabricante,float precio, String stock, String tipo, String color,int ppm){
        Object[] v = new Object[8];
        
            v[0] = codigo;
            v[1] = nombre;
            v[2] = fabricante;
            v[3] = precio;
            v[4] = stock;
            v[5] = tipo;
            v[6] = color;
            v[7] = ppm;

        return v;
    }
    
    //funcion para buscar impresoras
    public static void searchPrinter(){
        try {           
            singleton.dtm = new DefaultTableModel();
            singleton.dtm.setColumnIdentifiers(printers);
            home_RegisterUser.table.setModel(singleton.dtm);
            
            Statement stmt = singleton.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM articulos a,impresora r WHERE a.codigo = r.codart");
         
            while(rs.next()){
                int stock = rs.getInt("stock");
                String stock2;
                if(stock>0){
                    stock2 = "Esta en Stock";
                }else{
                    stock2 = "No esta en Stock";
                }
                
                int color = rs.getInt("color");
                String color2;
                if(color!=0){
                    color2 = "Si";
                }else{
                    color2 = "No";
                }
                
                singleton.dtm.addRow(getArrayDeObjectosPrinter(rs.getInt("codigo"),rs.getString("nombre"),rs.getString("fabricante"),rs.getFloat("precio"),stock2,rs.getString("tipo"),color2,rs.getInt("ppm")));

            }
        } catch (SQLException ex) {
            System.err.println("SQL Error: "+ex);
        }catch(Exception ex){
            System.err.println("Error: "+ex);
        }
    }
}
