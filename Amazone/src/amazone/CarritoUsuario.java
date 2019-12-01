package amazone;

import static amazone.UsuarioPanel.decoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ListCellRenderer;

public class CarritoUsuario extends javax.swing.JFrame {
    ArrayList<imagenYTexto> paraList = new ArrayList<imagenYTexto>();
    ArrayList<Producto> paraCosas;
    ArrayList<Producto> emptyList = new ArrayList<Producto>();
    DefaultListModel dm = new DefaultListModel();
    
    public void paraImagen() throws IOException{
        paraList.clear();
        for(int i=0; i<paraCosas.size(); i++){
            //Conseguir el icono
            String nombrePath = "Img\\" + Integer.toString(i)+ "Carrito" +".jpg";
            decoder(paraCosas.get(i).getImagen(), nombrePath);
            
            BufferedImage img = ImageIO.read(new File(nombrePath));
            ImageIcon icon = new ImageIcon(img);
            
            //Agregar a la lista para despues mandar a la lista visual
            paraList.add(new imagenYTexto(paraCosas.get(i).getNombre(), icon, paraCosas.get(i).getTipo()));

        }
    }
    
    public void llenarArray() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream salida = new FileInputStream("temp\\carrito.ser");
        
        ObjectInputStream in = new ObjectInputStream(salida);
        
        paraCosas = (ArrayList<Producto>) in.readObject();
        
        FileOutputStream salidaEmpty;
        
        salidaEmpty = new FileOutputStream("temp\\carrito.ser");
        ObjectOutputStream out = new ObjectOutputStream (salidaEmpty);
        out.writeObject(emptyList);
        out.close();
        
    }
    
    public void agregarLista(){
        for(int i =0; i<paraList.size(); i++){
            dm.addElement(paraList.get(i));
        }
    
        jListCarrito.setCellRenderer((ListCellRenderer<? super String>) new Renderer());
        jListCarrito.setModel(dm);    
    }
    
    public CarritoUsuario(ArrayList<Producto> temp) throws IOException {
        initComponents();  
        
        paraCosas = new ArrayList<Producto>(temp);
        
        paraImagen();
        agregarLista();
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCarrito = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSalir.setText("Volver a la tienda");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jListCarrito);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnSalir)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        UsuarioPanel n = new UsuarioPanel();
        n.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JList<String> jListCarrito;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
