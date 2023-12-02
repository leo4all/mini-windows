package com.unitec.mini.windows.apps;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author leonel
 */
public class PaintApp extends javax.swing.JInternalFrame  implements AppInterface {
    int cont= 1; 
    private ImageIcon[] Imagen;
    private ArrayList<String> ImageName= new ArrayList() ;
    /**
     * Creates new form PaintApp
     */
    public PaintApp(String pathUserLoging,boolean Tipodevista,Image ImageSpecific) {
        initComponents();
        ControladorBotones.setVisible(true);
        this.setTitle("Visor de imágenes");
        // Count images in the folder
        if(Tipodevista){ 
        int imageCount = countImagesInFolder(pathUserLoging);
        Imagen = new ImageIcon[imageCount];
        
        //setComponents();
        this.setTitle("Visor de imagenes");
        
        // Load images into the array
        for (int i = 0; i < imageCount; i++) {
            Imagen[i] = new ImageIcon(pathUserLoging +"/"+ImageName.get(i));
        }
        PhotoName_Jlabel.setText(ImageName.get(cont));
        actualizarImagen(Imagen_Principal, 0, 390, 610);
        actualizarImagen(Imagen_Siguiente, cont + 1, 150, 124);
        actualizarImagen(Imagen_Siguiente_siguiente, cont + 2, 150, 124);
        }else{
        ControladorBotones.setVisible(false);
        Imagen_Principal.setIcon(new ImageIcon(ImageSpecific));
        }
    }
    public void setComponents(){
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Imagen_Principal = new javax.swing.JLabel();
        ControladorBotones = new javax.swing.JPanel();
        Imagen_Siguiente = new javax.swing.JLabel();
        Siguiente = new javax.swing.JButton();
        Atras = new javax.swing.JButton();
        Imagen_Anterio_anterior = new javax.swing.JLabel();
        Imagen_Siguiente_siguiente = new javax.swing.JLabel();
        Imagen_Anterior = new javax.swing.JLabel();
        PhotoName_Jlabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(140, 136, 136));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Media Player");

        Imagen_Principal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Imagen_Principal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        Imagen_Siguiente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Siguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Imagen_Siguiente_Imagen32x32.png"))); // NOI18N
        Siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SiguienteActionPerformed(evt);
            }
        });

        Atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Imagen_Anterior_Imagen_32x32.png"))); // NOI18N
        Atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AtrasMouseClicked(evt);
            }
        });
        Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AtrasActionPerformed(evt);
            }
        });

        Imagen_Anterio_anterior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Imagen_Siguiente_siguiente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Imagen_Anterior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout ControladorBotonesLayout = new javax.swing.GroupLayout(ControladorBotones);
        ControladorBotones.setLayout(ControladorBotonesLayout);
        ControladorBotonesLayout.setHorizontalGroup(
            ControladorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControladorBotonesLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(Imagen_Anterio_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Imagen_Anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(Imagen_Siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Imagen_Siguiente_siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ControladorBotonesLayout.setVerticalGroup(
            ControladorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControladorBotonesLayout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(ControladorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControladorBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Imagen_Siguiente_siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Imagen_Siguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Imagen_Anterio_anterior, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Imagen_Anterior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ControladorBotonesLayout.createSequentialGroup()
                        .addComponent(Atras, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addComponent(Siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(Imagen_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ControladorBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(PhotoName_Jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(PhotoName_Jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Imagen_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ControladorBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SiguienteActionPerformed
        // TODO add your handling code here:
        if (cont == Imagen.length-1) {
            cont = 0;  // Reinicia el contador al llegar a 5
        }
        cont++;
        PhotoName_Jlabel.setText(ImageName.get(cont));
        actualizarImagen(Imagen_Principal,cont,390,610);

        int indiceImagen1 = (cont - 2 + Imagen.length) % Imagen.length;
        if (indiceImagen1 >= 0 && indiceImagen1 < Imagen.length && Imagen[indiceImagen1] != null) {
            Image imagenRedimensionada1 = redimensionarImagen(Imagen[indiceImagen1].getImage(), 90, 90);
            Imagen_Anterio_anterior.setIcon(new ImageIcon(imagenRedimensionada1));
        } else {
            // Manejar el caso de índice fuera de límites o imagen nula
            Imagen_Anterio_anterior.setIcon(null);
        }

        // Redimensiona la imagen antes de establecerla como icono para Imagen2
        int indiceImagen2 = (cont - 1 + Imagen.length) % Imagen.length; // Manejar el límite inferior
        if (indiceImagen2 >= 0 && indiceImagen2 < Imagen.length && Imagen[indiceImagen2] != null) {
            Image imagenRedimensionada2 = redimensionarImagen(Imagen[indiceImagen2].getImage(), 150, 124);
            Imagen_Anterior.setIcon(new ImageIcon(imagenRedimensionada2));
        } else {
            // Manejar el caso de índice fuera de límites o imagen nula
            Imagen_Anterior.setIcon(null);
        }

        //imagen central
        // Redimensiona la imagen antes de establecerla como icono para Imagen3
        int indiceImagen3 = (cont + 1) % Imagen.length; // Manejar el límite superior
        if (indiceImagen3 >= 0 && indiceImagen3 < Imagen.length && Imagen[indiceImagen3] != null) {
            Image imagenRedimensionada3 = redimensionarImagen(Imagen[indiceImagen3].getImage(), 150, 124);
            Imagen_Siguiente.setIcon(new ImageIcon(imagenRedimensionada3));
        } else {
            // Manejar el caso de índice fuera de límites o imagen nula
            Imagen_Siguiente.setIcon(null);
        }

        // Redimensiona la imagen antes de establecerla como icono para Imagen4
        int indiceImagen4 = (cont + 2) % Imagen.length; // Manejar el límite superior
        if (indiceImagen4 >= 0 && indiceImagen4 < Imagen.length && Imagen[indiceImagen4] != null) {
            Image imagenRedimensionada4 = redimensionarImagen(Imagen[indiceImagen4].getImage(), 90, 90);
            Imagen_Siguiente_siguiente.setIcon(new ImageIcon(imagenRedimensionada4));
        } else {
            // Manejar el caso de índice fuera de límites o imagen nula
            Imagen_Siguiente_siguiente.setIcon(null);
        }
    }//GEN-LAST:event_SiguienteActionPerformed

    private void AtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AtrasMouseClicked

    }//GEN-LAST:event_AtrasMouseClicked

    private void AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AtrasActionPerformed
        // TODO add your handling code here:
        cont--;

        if (cont < 0) {
            cont = Imagen.length - 1;  // Reinicia el contador al llegar a -1
        }
        PhotoName_Jlabel.setText(ImageName.get(cont));
        actualizarImagen(Imagen_Principal,cont,390,610);

        // Actualizar Imagen1
        int indiceImagen1 = (cont - 2 + Imagen.length) % Imagen.length;
        actualizarImagen(Imagen_Anterio_anterior, indiceImagen1, 90, 90);

        // Actualizar Imagen2 con dimensiones específicas (largo: 124, ancho: 150)
        int indiceImagen2 = (cont - 1 + Imagen.length) % Imagen.length;
        actualizarImagen(Imagen_Anterior, indiceImagen2, 150, 124);

        // Actualizar Imagen3 con dimensiones específicas (largo: 124, ancho: 150)
        int indiceImagen3 = cont+1 % Imagen.length;
        actualizarImagen(Imagen_Siguiente, indiceImagen3, 150, 124);

        // Actualizar Imagen4
        int indiceImagen4 = (cont +2) % Imagen.length;
        actualizarImagen(Imagen_Siguiente_siguiente, indiceImagen4, 90, 90);
    }//GEN-LAST:event_AtrasActionPerformed
    
    private void actualizarImagen(JLabel label, int indice, int ancho, int alto) {
    if (indice >= 0 && indice < Imagen.length && Imagen[indice] != null) {
        Image imagenOriginal = Imagen[indice].getImage();
        Image imagenRedimensionada = redimensionarImagen(imagenOriginal, ancho, alto);
        label.setIcon(new ImageIcon(imagenRedimensionada));
    } else {
        label.setIcon(null);
    }
    }

// Función para redimensionar una imagen
    private Image redimensionarImagen(Image img, int ancho, int alto) {
        BufferedImage nuevaImagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = nuevaImagen.createGraphics();
        g.drawImage(img, 0, 0, ancho, alto, null);
        g.dispose();
        return nuevaImagen;
    }

//funcion para encontrar size de la funcion 
        public  int countImagesInFolder(String folderPath) {
            File folder = new File(folderPath);

            if (!folder.isDirectory()) {
                System.out.println("La ruta no es un directorio.");
                return 0;
            }

            int imageCount = 0;

            File[] files = folder.listFiles();
            System.out.println("Files size: "+files.length);
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isImageFile(file.getName())) {
                        ImageName.add(file.getName());
                        imageCount++;
                    }
                }
            }
            return imageCount;
        }

        private static boolean isImageFile(String fileName) {
            // Puedes ajustar esta condición según los tipos de archivos de imágenes que estás buscando
            return fileName.toLowerCase().endsWith(".png");
        }
        
    @Override
    public void closeFrame() {
        try {
            this.setClosed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Atras;
    private javax.swing.JPanel ControladorBotones;
    private javax.swing.JLabel Imagen_Anterio_anterior;
    private javax.swing.JLabel Imagen_Anterior;
    private javax.swing.JLabel Imagen_Principal;
    private javax.swing.JLabel Imagen_Siguiente;
    private javax.swing.JLabel Imagen_Siguiente_siguiente;
    private javax.swing.JLabel PhotoName_Jlabel;
    private javax.swing.JButton Siguiente;
    // End of variables declaration//GEN-END:variables
}
