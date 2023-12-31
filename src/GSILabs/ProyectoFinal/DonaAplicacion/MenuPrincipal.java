package GSILabs.ProyectoFinal.DonaAplicacion;

import GSILabs.ProyectoFinal.Login.Login;
import GSILabs.ProyectoFinal.Login.RegistrarUsuario;

/**
 * Clase MenuPrincipal
 * Interfaz gráfica con la que se inicia la ejecución del programa y que nos da
 * la opción de iniciar sesión si tenemos ya un perfil de usuario creado (login)
 * o crear un nuevo perfil de usuario.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.12.2023
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Constructor MenuPrincipal.
     */
    public MenuPrincipal() {
        initComponents();
        
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

        labelTitulo = new javax.swing.JLabel();
        botonLogin = new javax.swing.JButton();
        botonRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        labelTitulo.setText("DonaAplicación");

        botonLogin.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        botonLogin.setText("Login");
        botonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLoginActionPerformed(evt);
            }
        });

        botonRegistrarse.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        botonRegistrarse.setText("Registrarse");
        botonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(labelTitulo)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addGap(47, 47, 47)
                .addComponent(botonLogin)
                .addGap(45, 45, 45)
                .addComponent(botonRegistrarse)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función ActionPerformed para el boton botonLogin mediante el que
     * accedemos al JFrame Login con el que podremos logearnos en el sistema
     * con nuestro usuario.
     * @param evt Evento recibido por la función
     */
    private void botonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLoginActionPerformed
        Login abrirLogin = new Login();
        abrirLogin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonLoginActionPerformed

    /**
     * Función ActionPerformed para el boton botonRegistrarse mediante el que
     * accedemos al JFrame RegistrarUsuario con el que podremos crear un nuevo
     * usuario.
     * @param evt Evento recibido por la función
     */
    private void botonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarseActionPerformed
        RegistrarUsuario abrirRegistrarUsuario = new RegistrarUsuario();
        abrirRegistrarUsuario.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonRegistrarseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonLogin;
    private javax.swing.JButton botonRegistrarse;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
