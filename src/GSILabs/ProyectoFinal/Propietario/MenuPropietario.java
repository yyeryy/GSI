package GSILabs.ProyectoFinal.Propietario;

import GSILabs.BModel.Usuario;

/**
 *
 * @author javie
 */
public class MenuPropietario extends javax.swing.JFrame {
    
    /**
     * Almacena datos del usuario que está utilizando la aplicación.
     */
    private Usuario usuario = null;

    /**
     * Constructor MenuAdministrador
     * @param usuario Usuario con el que se está utilizando la aplicación.
     */
    public MenuPropietario(Usuario usuario) {
        initComponents();
        this.usuario = usuario;
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonDonar = new javax.swing.JButton();
        botonCrearEliminarDonaciones = new javax.swing.JButton();
        botonHistorialDonaciones = new javax.swing.JButton();
        botonPerfil = new javax.swing.JButton();
        botonCerrarSesion = new javax.swing.JButton();
        labelMenuAdministrador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonDonar.setText("Donar comida");
        botonDonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDonarActionPerformed(evt);
            }
        });

        botonCrearEliminarDonaciones.setText("Visualizar y Eliminar Donaciones");
        botonCrearEliminarDonaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearEliminarDonacionesActionPerformed(evt);
            }
        });

        botonHistorialDonaciones.setText("Historial de Donaciones");
        botonHistorialDonaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonHistorialDonacionesActionPerformed(evt);
            }
        });

        botonPerfil.setText("Ver Perfil");
        botonPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPerfilActionPerformed(evt);
            }
        });

        botonCerrarSesion.setText("Cerrar Sesión");
        botonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarSesionActionPerformed(evt);
            }
        });

        labelMenuAdministrador.setFont(new java.awt.Font("Monotype Corsiva", 2, 24)); // NOI18N
        labelMenuAdministrador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMenuAdministrador.setText("Menú Administrador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botonCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonHistorialDonaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonCrearEliminarDonaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(botonDonar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(labelMenuAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuAdministrador, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(botonDonar)
                .addGap(23, 23, 23)
                .addComponent(botonCrearEliminarDonaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(botonHistorialDonaciones)
                .addGap(23, 23, 23)
                .addComponent(botonPerfil)
                .addGap(23, 23, 23)
                .addComponent(botonCerrarSesion)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarSesionActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_botonCerrarSesionActionPerformed

    private void botonPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonPerfilActionPerformed

    private void botonDonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDonarActionPerformed
       MenuDonacion menuDonacion = new MenuDonacion(usuario); //Habrá que meterle usuario también por parámetro? 
       menuDonacion.setVisible(true);
    }//GEN-LAST:event_botonDonarActionPerformed

    private void botonCrearEliminarDonacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearEliminarDonacionesActionPerformed
        MenuVerDonacionesActivas donacionesActivas = new MenuVerDonacionesActivas(this.usuario);
        donacionesActivas.setVisible(true);
    }//GEN-LAST:event_botonCrearEliminarDonacionesActionPerformed

    private void botonHistorialDonacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonHistorialDonacionesActionPerformed
        MenuHistorialDonaciones historialDonaciones = new MenuHistorialDonaciones(this.usuario);
        //this.setVisible(false);
        historialDonaciones.setVisible(true);
    }//GEN-LAST:event_botonHistorialDonacionesActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCerrarSesion;
    private javax.swing.JButton botonCrearEliminarDonaciones;
    private javax.swing.JButton botonDonar;
    private javax.swing.JButton botonHistorialDonaciones;
    private javax.swing.JButton botonPerfil;
    private javax.swing.JLabel labelMenuAdministrador;
    // End of variables declaration//GEN-END:variables
}
