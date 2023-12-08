package GSILabs.ProyectoFinal.Login;

import GSILabs.BModel.Cliente;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import GSILabs.BSystem.BusinessSystem;
import static GSILabs.MongoDB.ConexionBBDD.CargarDatos;
import static GSILabs.MongoDB.ConexionBBDD.DescargarDatos;
import static GSILabs.MongoDB.ConexionBBDD.cargarUsuario;
import javax.swing.JOptionPane;
import GSILabs.ProyectoFinal.Cliente.MenuCliente;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Clase RegistrarUsuario
 * Interfaz gráfica con la que creamos un nuevo perfil de usuario para la aplicación.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.12.2023
 */
public class RegistrarUsuario extends javax.swing.JFrame {
    
    /**
     * Almacena datos del usuario que se está registrando.
     */
    private Usuario usuario = null;

    /**
     * Constructor RegistrarUsuario.
     */
    public RegistrarUsuario() {
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

        jLabel1 = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        labelContrasena = new javax.swing.JLabel();
        labConfirmacionContrasena = new javax.swing.JLabel();
        tfContrasena = new javax.swing.JPasswordField();
        tfConfirmacionContrasena = new javax.swing.JPasswordField();
        tfNombre = new javax.swing.JTextField();
        botonCrearCuenta = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        mostrarContrasena = new javax.swing.JCheckBox();
        botonLogin = new javax.swing.JButton();
        cBoxTipoUsuario = new javax.swing.JComboBox<>();
        labConfirmacionContrasena1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel1.setText("Crear Cuenta de Usuario");

        labelNombre.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        labelNombre.setText("Nombre:");

        labelContrasena.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        labelContrasena.setText("Contraseña:");

        labConfirmacionContrasena.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        labConfirmacionContrasena.setText("Confirmación contraseña:");

        tfConfirmacionContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfConfirmacionContrasenaActionPerformed(evt);
            }
        });

        tfNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNombreActionPerformed(evt);
            }
        });

        botonCrearCuenta.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        botonCrearCuenta.setText("Crear Cuenta");
        botonCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearCuentaActionPerformed(evt);
            }
        });

        botonSalir.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        mostrarContrasena.setText("Mostrar Contraseña");
        mostrarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarContrasenaActionPerformed(evt);
            }
        });

        botonLogin.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        botonLogin.setText("Login");
        botonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLoginActionPerformed(evt);
            }
        });

        cBoxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente", "Propietario" }));

        labConfirmacionContrasena1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        labConfirmacionContrasena1.setText("Tipo de usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(mostrarContrasena))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botonSalir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonLogin)
                                .addGap(47, 47, 47)
                                .addComponent(botonCrearCuenta))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelContrasena)
                                    .addComponent(labConfirmacionContrasena)
                                    .addComponent(labelNombre)
                                    .addComponent(labConfirmacionContrasena1))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tfContrasena)
                                    .addComponent(tfConfirmacionContrasena)
                                    .addComponent(cBoxTipoUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContrasena)
                    .addComponent(tfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labConfirmacionContrasena)
                    .addComponent(tfConfirmacionContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mostrarContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cBoxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonCrearCuenta)
                            .addComponent(botonSalir)
                            .addComponent(botonLogin)))
                    .addComponent(labConfirmacionContrasena1))
                .addGap(24, 24, 24))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función ActionPerformed para el JCheckBox mostrarContraseña, que al 
     * pulsarlo podemos visualizar la contraseña, y al volverlo a pulsar
     * se vuelve a ocultar la contraseña mediante puntos.
     * @param evt Evento recibido por la función
     */
    private void mostrarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarContrasenaActionPerformed
        if(mostrarContrasena.isSelected()){
            tfContrasena.setEchoChar((char)0);
            tfConfirmacionContrasena.setEchoChar((char)0);
        } else {
            tfContrasena.setEchoChar('*');
            tfConfirmacionContrasena.setEchoChar('*');
        }
    }//GEN-LAST:event_mostrarContrasenaActionPerformed

    /**
     * Función ActionPerformed para el boton botonCrearCuenta en la que en primer
     * lugar comprobamos si todos los campos están rellenados;
     * En segundo lugar, si el login es correcto comprobamos si el usuario tiene 
     * condición de cliente o de administrador, en función de ello si quiere
     * registrarse como administrador se le redigirá a EspecificacionesLocal, si
     * en cambio quiere registrase como cliente se le dará acceso a la aplicación
     * y se le redigirá a MenuCliente. En caso contrario se muestra un 
     * JOptionPane de error.
     * @param evt Evento recibido por la función
     */
    private void botonCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearCuentaActionPerformed
        System.out.println("Boton login pulsado");
        if(tfNombre.getText().equals("") && tfContrasena.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca un nombre de usuario y una contraseña.");
        } else if(tfNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca un nombre de usuario.");
        } else if(tfContrasena.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca una contraseña.");
        } else if(!tfContrasena.getText().equals(tfConfirmacionContrasena.getText())){
            JOptionPane.showMessageDialog(null, "Las contraseñas son diferentes, ponga 2 iguales.");
        } else {
            // Obtener datos usuario
            String nick = tfNombre.getText();
            String contrasena = tfContrasena.getText();
            String tipoStr = (String) cBoxTipoUsuario.getSelectedItem();
            // Añadir usuario
            Usuario nuevoUsuario;
            if(tipoStr.equals("Cliente"))
                nuevoUsuario = new Usuario(nick,contrasena, LocalDate.of(2000,1,1), CLIENTE);
            else
                nuevoUsuario = new Usuario(nick,contrasena, LocalDate.of(2000,1,1), PROPIETARIO);
            // Descargar BS
            BusinessSystem bs = DescargarDatos();
            // Comprobar si existe el usuario
            if(bs.obtenerUsuario(nick) != null){
                JOptionPane.showMessageDialog(null, "Este usuario ya esta en uso.");
                return;
            }
            // Añado el nuevo usuario
            bs.nuevoUsuario(nuevoUsuario);
            
            usuario = nuevoUsuario;
            
            // Añadir a la base de datos
            cargarUsuario(nuevoUsuario);
            
            //Si no hay ningún error al crear
            if((cBoxTipoUsuario.getItemAt(cBoxTipoUsuario.getSelectedIndex())).equals("Cliente")) {
                //Rellenamos campos para la creación de cliente
                String nombreCliente = this.tfNombre.getText();
                String contrasenaCliente = Arrays.toString(this.tfContrasena.getPassword()); //Comprobar que lo devuelve bien
                Cliente cliente = new Cliente(nombreCliente, contrasenaCliente, LocalDate.of(2001, 6, 12));
                
                MenuCliente abrirMenuCliente = new MenuCliente(nuevoUsuario);
                this.setVisible(false);
                
            } else if((cBoxTipoUsuario.getItemAt(cBoxTipoUsuario.getSelectedIndex())).equals("Propietario")) {
                //Rellenamos campos para la creación de propietario
                String nombrePropietario = this.tfNombre.getText();
                String contrasenaPropietario = Arrays.toString(this.tfContrasena.getPassword()); //Comprobar que lo devuelve bien
                Propietario propietario = new Propietario(nombrePropietario, contrasenaPropietario, LocalDate.of(2001, 6, 12));
                
                //MenuPropietario abrirMenuPropietario = new MenuPropietario(propietario);
                EspecificacionesLocal abrirEspecificacionesLocal = new EspecificacionesLocal(propietario);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_botonCrearCuentaActionPerformed

    /**
     * Función ActionPerformed para el JButton botonSalir, mediante el que se
     * sale del programa al pulsarlo.
     * @param evt Evento recibido por la función
     */
    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botonSalirActionPerformed

    /**
     * Función ActionPerformed para el JButton botonLogin, mediante el que
     * se accede al JFrame Login donde podremos registrarnos con nuestro perfil
     * de usuario creado anteriormente.
     * @param evt Evento recibido por la función
     */
    private void botonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLoginActionPerformed
        Login abrirLogin = new Login();
        abrirLogin.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonLoginActionPerformed

    private void tfNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNombreActionPerformed

    private void tfConfirmacionContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfConfirmacionContrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfConfirmacionContrasenaActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrearCuenta;
    private javax.swing.JButton botonLogin;
    private javax.swing.JButton botonSalir;
    private javax.swing.JComboBox<String> cBoxTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labConfirmacionContrasena;
    private javax.swing.JLabel labConfirmacionContrasena1;
    private javax.swing.JLabel labelContrasena;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JCheckBox mostrarContrasena;
    private javax.swing.JPasswordField tfConfirmacionContrasena;
    private javax.swing.JPasswordField tfContrasena;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables
}
