/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.classes;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lluis
 */
public class singleton {
    
   public static Connection conn;
   public static User user;
   public static DefaultTableModel dtm;
   public static int selectedRow;
   public static int ram;
   public static String categorias = "general";
   public static int carritoActivo;
   
}
