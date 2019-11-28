/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazone;

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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Usuario
 */
public class EmpresaPanel extends javax.swing.JFrame {
    ArrayList<Producto> paraCosas = new ArrayList<Producto>();
    
    Producto product;
    
    File toImage = null;
    
    String patronNombre = "^([A-Z]{1}[a-zA-Z\\s0-9]+)$";
    String patronPrecio = "^([0-9]+)$";
    
    
    public File selectFile()throws FileNotFoundException, IOException {
        JFileChooser chooser = new JFileChooser();
        
        FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        
        chooser.setFileFilter(imageFilter);
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            toImage = f;
            decoder(encoder64(f), "img\\" + f.getName());
            BufferedImage img = ImageIO.read(new File("img\\" + f.getName()));
            ImageIcon icon = new ImageIcon(img);
            
            lblParaImagen.setText("");
            lblParaImagen.setIcon(icon);
            //imgPrueba = new imagenYTexto("Prueba", icon);
            
            //agregar(imgPrueba);
            return f;
        } else {
            System.err.println("Chales no quiso nah.");
            return null;
        }
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
    
    public void llenarArray() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream salida = new FileInputStream("Empresa\\Producto.ser");
        
        ObjectInputStream in = new ObjectInputStream(salida);
        
        Producto obj = null; 
        obj = (Producto) in.readObject();
        paraCosas.add(obj);
        while(obj != null){
            obj = (Producto) in.readObject();
            paraCosas.add(obj);
        }
    }
    
    public EmpresaPanel() {
        initComponents();
        
        try {
            llenarArray();
        } catch (IOException ex) {} catch (ClassNotFoundException ex) {}
    }

     private void agregarProducto()throws FileNotFoundException, IOException, ClassNotFoundException  {
       if(txtNombreProducto.getText().isEmpty() || jTextAreaDescripcion.getText().isEmpty()|| txtPrecio.getText().isEmpty()  ){
           JOptionPane.showMessageDialog(this, "Faltan campos por llenar");
           return;
       }

       String nombre;
       String descripcion;
       String precio;
       String tipo;

       nombre = txtNombreProducto.getText();
       descripcion = jTextAreaDescripcion.getText();
       precio = txtPrecio.getText();
       tipo = comboTipo.getSelectedItem().toString();
       
       Pattern a = Pattern.compile(patronNombre);
       Pattern b = Pattern.compile(precio);

        
       if(!a.matcher(nombre).matches()){
           JOptionPane.showMessageDialog(this, "Nombre invalido");
           return;
       }
       else if(!b.matcher(precio).matches()){
           JOptionPane.showMessageDialog(this, "Precio invalido");
           return;
       }
       
       

       String file = "Empresa\\Producto.ser";
       product = new Producto(nombre,descripcion,precio,tipo, encoder64(toImage));

           try {
               FileOutputStream salida = new FileOutputStream(file);
               ObjectOutputStream out = new ObjectOutputStream (salida);
               out.writeObject(product);
               out.close();
               JOptionPane.showMessageDialog(this, "Producto Agregado Exitosamente!");
           } catch (FileNotFoundException ex) {
               JOptionPane.showMessageDialog(this, "Error al agregar Producto");
           }
           txtNombreProducto.setText("");
           jTextAreaDescripcion.setText("");
           txtPrecio.setText("");
           lblParaImagen.setIcon(null);
           lblParaImagen.setText("Foto");
           /*
           Producto product2 = null;
          
           try{
                FileInputStream salida = new FileInputStream (file);
                ObjectInputStream in = new ObjectInputStream(salida);
                product2 = (Producto)in.readObject();
                in.close();
                JOptionPane.showMessageDialog(this, "Se ha desserializado el producto");
           }catch (FileNotFoundException ex) {
               JOptionPane.showMessageDialog(this, "Error al agregar Producto");
           }
           */
     }

    private void eliminarProducto()throws FileNotFoundException, IOException {
    
    }

    private void editarProducto()throws FileNotFoundException, IOException, ClassNotFoundException  {
        Producto toCheck = null;
        String toSearch = txtNombreProducto.getText();
        
        for(int i=0; i<paraCosas.size(); i++){
            if(paraCosas.get(i).getNombre().equals(toSearch)){
                txtNombreProducto.setText(paraCosas.get(i).getNombre());
                jTextAreaDescripcion.setText(paraCosas.get(i).getDescripcion());
                txtPrecio.setText(paraCosas.get(i).getTipo());
                decoder(paraCosas.get(i).getImagen(), "Img\\imgProvisional.jpg");
                BufferedImage img = ImageIO.read(new File("Img\\imgProvisional.jpg"));
                ImageIcon icon = new ImageIcon(img);
                lblParaImagen.setText("");
                lblParaImagen.setIcon(icon);
                
                return;
            }
        }
        
        /*
        while(true){
            toCheck = (Producto) in.readObject();
            
            if(toCheck.getNombre().equals(toSearch)){
               txtNombreProducto.setText(toCheck.getNombre());
                jTextAreaDescripcion.setText(toCheck.getDescripcion());
                txtPrecio.setText(toCheck.getTipo());
                decoder(toCheck.getImagen(), "Img\\imgProvisional.jpg");
                BufferedImage img = ImageIO.read(new File("Img\\imgProvisional.jpg"));
                ImageIcon icon = new ImageIcon(img);
                lblParaImagen.setText("");
                lblParaImagen.setIcon(icon);
                
                return;
            }
        }
        */
    }

    private void guardarProducto()throws FileNotFoundException, IOException  {
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescripcion = new javax.swing.JTextArea();
        txtPrecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblParaImagen = new javax.swing.JLabel();
        btnAbrirImagen = new javax.swing.JButton();
        btnSalirEmpresa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel1.setText("Empresa");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jLabel2.setText("Agregar productos");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Descripcion:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Precio:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Foto:");

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescripcion);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Categoria:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electronicos", "Hogar", "Deportes", "Musica", "Videojuegos" }));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblParaImagen.setText("foto");

        btnAbrirImagen.setText("Abrir Imagen");
        btnAbrirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirImagenActionPerformed(evt);
            }
        });

        btnSalirEmpresa.setText("Volver al Inicio");
        btnSalirEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblParaImagen)
                                .addGap(69, 69, 69)
                                .addComponent(btnAbrirImagen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalirEmpresa))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreProducto))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnAgregar))
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblParaImagen)
                    .addComponent(btnAbrirImagen)
                    .addComponent(btnSalirEmpresa))
                .addGap(346, 346, 346))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirImagenActionPerformed
    try {
            selectFile();
        } catch (IOException ex) {} 
    }//GEN-LAST:event_btnAbrirImagenActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
     try {
            agregarProducto();
        } catch (IOException ex) {} catch (ClassNotFoundException ex) {
            System.err.println("Ya valio :c pero en agregar producto.");
        } 
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       try {
            eliminarProducto();
        } catch (IOException ex) {} 
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try {
            editarProducto();
        } catch (IOException ex) {} catch (ClassNotFoundException ex) {} 
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardarProducto();
        } catch (IOException ex) {} 
        lblParaImagen.setText("Foto");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirEmpresaActionPerformed
        Login n = new Login();
        n.setVisible(true);
        dispose();

    }//GEN-LAST:event_btnSalirEmpresaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirImagen;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalirEmpresa;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JLabel lblParaImagen;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

}
