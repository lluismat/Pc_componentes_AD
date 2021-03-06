/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pccompov1_lluismataix.modules.user_reg.views;

/**
 *
 * @author lluis
 */
public class carritoView extends javax.swing.JFrame {

    /**
     * Creates new form carrito
     */
    public carritoView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        comboCarritos = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        carritoTable = new javax.swing.JTable();
        editLineaBtn = new javax.swing.JButton();
        deleteLineaBtn = new javax.swing.JButton();
        purchaseBtn = new javax.swing.JButton();
        deleteCartBtn = new javax.swing.JButton();
        createCarrito = new javax.swing.JButton();
        AtrasCarritoBtn = new javax.swing.JButton();
        etiCarrito = new javax.swing.JLabel();
        panelCarrito = new javax.swing.JPanel();
        privateRB = new javax.swing.JRadioButton();
        publicRB = new javax.swing.JRadioButton();
        etiTipo = new javax.swing.JLabel();
        nameCarritoField = new javax.swing.JTextField();
        etiNombreCarrito = new javax.swing.JLabel();
        btnCancelAddCart = new javax.swing.JButton();
        changeCarrito = new javax.swing.JButton();
        panelCantidad = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cantidadField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        carritoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(carritoTable);

        editLineaBtn.setText("Modificar Cantidad Articulo");

        deleteLineaBtn.setText("Eliminar Articulo");

        purchaseBtn.setText("Finalizar Compra");

        deleteCartBtn.setText("Eliminar Cesta");

        createCarrito.setText("Crear Cesta");

        AtrasCarritoBtn.setText("Atras");

        etiCarrito.setText("CARRITO");

        grupo.add(privateRB);
        privateRB.setText("Privada");
        privateRB.setActionCommand("privada");

        grupo.add(publicRB);
        publicRB.setSelected(true);
        publicRB.setText("Publica");
        publicRB.setActionCommand("publica");

        etiTipo.setText("Tipo:");

        etiNombreCarrito.setText("Nombre Carrito:");

        btnCancelAddCart.setText("Cancelar");

        javax.swing.GroupLayout panelCarritoLayout = new javax.swing.GroupLayout(panelCarrito);
        panelCarrito.setLayout(panelCarritoLayout);
        panelCarritoLayout.setHorizontalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarritoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiNombreCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameCarritoField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelCarritoLayout.createSequentialGroup()
                        .addComponent(publicRB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(privateRB))
                    .addComponent(etiTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnCancelAddCart, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCarritoLayout.setVerticalGroup(
            panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarritoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiNombreCarrito)
                    .addComponent(etiTipo))
                .addGap(1, 1, 1)
                .addGroup(panelCarritoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameCarritoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publicRB)
                    .addComponent(privateRB)
                    .addComponent(btnCancelAddCart))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        changeCarrito.setText("Modificar Cesta");

        jLabel1.setText("Nueva Cantidad:");

        javax.swing.GroupLayout panelCantidadLayout = new javax.swing.GroupLayout(panelCantidad);
        panelCantidad.setLayout(panelCantidadLayout);
        panelCantidadLayout.setHorizontalGroup(
            panelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCantidadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cantidadField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        panelCantidadLayout.setVerticalGroup(
            panelCantidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCantidadLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(cantidadField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCarrito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboCarritos, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(createCarrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeCarrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteCartBtn)
                        .addGap(18, 63, Short.MAX_VALUE)
                        .addComponent(purchaseBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(editLineaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteLineaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AtrasCarritoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboCarritos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(createCarrito)
                        .addComponent(deleteCartBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(changeCarrito))
                    .addComponent(purchaseBtn))
                .addGap(15, 15, 15)
                .addComponent(panelCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editLineaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AtrasCarritoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(deleteLineaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton AtrasCarritoBtn;
    public static javax.swing.JButton btnCancelAddCart;
    public static javax.swing.JTextField cantidadField;
    public static javax.swing.JTable carritoTable;
    public static javax.swing.JButton changeCarrito;
    public static javax.swing.JComboBox<String> comboCarritos;
    public static javax.swing.JButton createCarrito;
    public static javax.swing.JButton deleteCartBtn;
    public static javax.swing.JButton deleteLineaBtn;
    public static javax.swing.JButton editLineaBtn;
    private javax.swing.JLabel etiCarrito;
    public static javax.swing.JLabel etiNombreCarrito;
    public static javax.swing.JLabel etiTipo;
    public static javax.swing.ButtonGroup grupo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField nameCarritoField;
    public static javax.swing.JPanel panelCantidad;
    public static javax.swing.JPanel panelCarrito;
    public static javax.swing.JRadioButton privateRB;
    private javax.swing.JRadioButton publicRB;
    public static javax.swing.JButton purchaseBtn;
    // End of variables declaration//GEN-END:variables
}
