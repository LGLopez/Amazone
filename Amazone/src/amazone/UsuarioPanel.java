/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazone;

import static amazone.EmpresaPanel.encoder64;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

public class UsuarioPanel extends javax.swing.JFrame {
    ArrayList <Producto> paraCosas = new ArrayList<Producto>();
    ArrayList <Producto> carrito = new ArrayList<Producto>();
    ArrayList<imagenYTexto> paraList = new ArrayList<imagenYTexto>();
    
    imagenYTexto imgPrueba;
    DefaultListModel dm = new DefaultListModel();
    
    int precioT = 0;

    String file = "Empresa\\Producto.ser";
    
    Producto product1;
    
    String patronCantidad = "^([0-9]+)$";
    
    imagenYTexto imgLista;
    
    public void pasarAImagen() throws IOException{
        paraList.clear();
        for(int i=0; i<paraCosas.size(); i++){
            //Conseguir el icono
            String nombrePath = "Img\\" + Integer.toString(i) +".jpg";
            decoder(paraCosas.get(i).getImagen(), nombrePath);
            
            BufferedImage img = ImageIO.read(new File(nombrePath));
            ImageIcon icon = new ImageIcon(img);
            
            //Agregar a la lista para despues mandar a la lista visual
            paraList.add(new imagenYTexto(paraCosas.get(i).getNombre(), icon, paraCosas.get(i).getTipo()));

        }
        
    }
    
    public void filtrar(String catego) throws IOException{
        pasarAImagen();
        
        for(int i=0; i<paraList.size(); i++){
            if(paraList.get(i).getCategoria().equals(catego)){
                agregar(paraList.get(i));
            }
        }
    }
    
    public UsuarioPanel() {
        initComponents();
    }
    
    public UsuarioPanel(ArrayList<Producto> temp){
        initComponents();
        carrito = (ArrayList<Producto>)temp.clone();
        precioT = 0;
        if(carrito.size()!=0){
            for(int i =0; i<carrito.size(); i++){
                int toMulti = Integer.parseInt(carrito.get(i).getCantidad());
                toMulti *= Integer.parseInt(carrito.get(i).getPrecio());

                precioT += toMulti;
            }

            txtTotal.setText(Integer.toString(precioT));
        }
        else{
            txtTotal.setText("0");
        }
    }
    
    public void agregar(imagenYTexto aImagen){
        dm.addElement(aImagen);
        
        listProductos.setCellRenderer((ListCellRenderer<? super String>) new Renderer());
        listProductos.setModel(dm);
    }
    
    public void llenarArray() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream salida = new FileInputStream("Empresa\\Producto.ser");
        
        ObjectInputStream in = new ObjectInputStream(salida);
        
        paraCosas = (ArrayList<Producto>) in.readObject();
        
    }
    
    public UsuarioPanel(String username) throws IOException{
        initComponents();
        try {
            llenarArray();
        } catch (IOException ex) {} catch (ClassNotFoundException ex) {}
        usernameShow.setText(username);
    }
    
    public static String encoder64(File fileImage) throws FileNotFoundException, IOException{
        String base64Image = "";
        
        FileInputStream imageInFile = new FileInputStream(fileImage);
        
        byte imageData[] = new byte[(int) fileImage.length()];
        
        imageInFile.read(imageData);
        
        base64Image = Base64.getEncoder().encodeToString(imageData);

        return base64Image;
    }
    
    public static String decoder(String base64Image, String pathFile) throws FileNotFoundException, IOException {
        FileOutputStream imageOutFile = new FileOutputStream(pathFile);
        
        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
        
        imageOutFile.write(imageByteArray);
        
        return pathFile;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameShow = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBuscar = new javax.swing.JComboBox<>();
        btnBuscarUs = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listProductos = new javax.swing.JList<>();
        btnAgregarC = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnVerC = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnComprar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel1.setText("Amazone");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Bienvenido");

        usernameShow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        usernameShow.setText("Username");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Categorias productos: ");

        jComboBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronicos", "Hogar", "Deportes", "Musica", "Videojuegos" }));

        btnBuscarUs.setText("Buscar");
        btnBuscarUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listProductos);

        btnAgregarC.setText("Agregar");
        btnAgregarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad:");

        jLabel5.setText("Total:");

        btnVerC.setText("Ver carrito");
        btnVerC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerCActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver al Inicio");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnComprar.setText("Comprar");
        btnComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usernameShow))
                            .addComponent(jLabel1))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btnVerC)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnBuscarUs)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnAgregarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCantidad)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(btnVolver)
                                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(btnComprar))))
                                .addGap(0, 52, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameShow))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarUs)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnAgregarC)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnComprar)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerC)
                    .addComponent(btnVolver))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarCarrito()throws FileNotFoundException, IOException {
        String patronCantidad = "^([0-9]+)$";
        Pattern c = Pattern.compile(patronCantidad);
        
        if(txtCantidad.equals("")){
            JOptionPane.showMessageDialog(this, "No se lleno el campo cantidad.");
            return;
        }
        
        String cantidad = txtCantidad.getText();
        if(!c.matcher(cantidad).matches()){
           JOptionPane.showMessageDialog(this, "Cantidad invalida");
           return;
       }
        
        imagenYTexto paraVer =(imagenYTexto) dm.getElementAt(listProductos.getSelectedIndex());
        
        for(int i=0; i<paraCosas.size(); i++){
            if(paraVer.getTexto().equals(paraCosas.get(i).getNombre())){
                Producto toAdd = paraCosas.get(i);
                toAdd.setCantidad(cantidad);
                carrito.add(toAdd);
                
            }
        }
        
    }

    private void buscarProducto()throws FileNotFoundException, IOException {
        dm.removeAllElements();
        filtrar(jComboBuscar.getSelectedItem().toString());
    }

    private void btnAgregarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCActionPerformed
        try {
            agregarCarrito();
        } catch (IOException ex) {} 
        precioT = 0;
        for(int i =0; i<carrito.size(); i++){
            int toMulti = Integer.parseInt(carrito.get(i).getCantidad());
            toMulti *= Integer.parseInt(carrito.get(i).getPrecio());
            
            precioT += toMulti;
        }
        
        txtTotal.setText(Integer.toString(precioT));
        txtCantidad.setText("");
    }//GEN-LAST:event_btnAgregarCActionPerformed

    private void btnBuscarUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsActionPerformed
        try {
            buscarProducto();
        } catch (IOException ex) {} 
    }//GEN-LAST:event_btnBuscarUsActionPerformed

    private void btnVerCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerCActionPerformed
//        String mostrar = "";
//        for(int i=0; i<carrito.size(); i++){
//            mostrar += carrito.get(i).getNombre();
//            mostrar += "\r\n";
//        }
//        JOptionPane.showMessageDialog(this, mostrar);

    CarritoUsuario n;
        try {
            n = new CarritoUsuario(carrito);
            n.setVisible(true);
            dispose();
        } catch (IOException ex) {}
    
    }//GEN-LAST:event_btnVerCActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        Login n = new Login();
        n.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarActionPerformed
        Producto toBuy;
        
        int toRest, toSave, result;
        String modified;
        
        for(int i=0; i<carrito.size(); i++){
            toBuy = carrito.get(i);
            
            for(int j = 0; j<paraCosas.size(); j++){
                if(toBuy.getNombre().equals(paraCosas.get(j).getNombre())){
                    
                    toRest = Integer.parseInt(toBuy.getCantidad());
                    
                    toSave = Integer.parseInt(paraCosas.get(j).getCantidad());
                    
                    result = toSave-toRest;
                    
                    modified = Integer.toString(result);
                    
                    paraCosas.get(j).setCantidad(modified);
                    
                }
            }
            
            FileOutputStream salida;
            try {
                salida = new FileOutputStream(file);
                ObjectOutputStream out = new ObjectOutputStream (salida);
                out.writeObject(paraCosas);
                out.close();
                JOptionPane.showMessageDialog(this, "Pagado!");
            } catch (FileNotFoundException ex) {} catch (IOException ex) {}
            
        }
        
    }//GEN-LAST:event_btnComprarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarC;
    private javax.swing.JButton btnBuscarUs;
    private javax.swing.JButton btnComprar;
    private javax.swing.JButton btnVerC;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> jComboBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JList<String> listProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JLabel usernameShow;
    // End of variables declaration//GEN-END:variables

}
