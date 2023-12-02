package GSILabs.Login;

import GSILabs.Admin.MenuAdministrador;
import GSILabs.BModel.SesionUsuario;
import java.awt.Image;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * Clase Login
 * Interfaz gráfica con la que iniciamos sesión (siempre y cuando tengamos un
 * usuario) y que nos da acceso al programa.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.12.2023
 */
public class Login extends javax.swing.JFrame {
    
    /**
     * Almacena datos de la sesión de un usuario en la aplicación.
     */
    private SesionUsuario sesionUsuario = null;
    
    //Mensajes de error para los jdialog.
    
    /**
     * Mensaje de error advirtiendo de que hay campos pendientes de rellenar.
     */
    static final String CAMPOS_SIN_RELLENAR = "Hay campos sin rellenar. Por favor, introduzca nombre y contraseña.";
    
    /**
     * Mensaje de error advirtiendo de que no existe ningún usuario registrado con ese nombre.
     */
    static final String NOMBRE_USUARIO_NO_ESTA_REGISTRADO = "No existe usuario con este nombre. Por favor, introduzca el nombre de un usuario ya registrado.";
    
    /**
     * Mensaje de error advirtiendo de que la contraseña del usuario no es correcta.
     */
    static final String CONTRASENA_NO_ES_CORRECTA = "La contraseña del usuario no es válida. Por favor, introduzca la contraseña correcta del usuario.";

    public Login() {
        initComponents();
        creacionDocumentoLogin();
        iniciarIcono();
        pfContrasena.setEchoChar('*');

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

        jDialogInformacion = new javax.swing.JDialog();
        scrollPane1 = new java.awt.ScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        ePaneInformacion = new javax.swing.JEditorPane();
        btnAceptar = new javax.swing.JButton();
        labTitulo = new javax.swing.JLabel();
        labNombre = new javax.swing.JLabel();
        labContrasena = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        btnSiguiente = new javax.swing.JButton();
        pfContrasena = new javax.swing.JPasswordField();
        labVisibilidadContrasena = new javax.swing.JLabel();

        ePaneInformacion.setEditable(false);
        jScrollPane1.setViewportView(ePaneInformacion);

        scrollPane1.add(jScrollPane1);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogInformacionLayout = new javax.swing.GroupLayout(jDialogInformacion.getContentPane());
        jDialogInformacion.getContentPane().setLayout(jDialogInformacionLayout);
        jDialogInformacionLayout.setHorizontalGroup(
            jDialogInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogInformacionLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogInformacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(241, 241, 241))
        );
        jDialogInformacionLayout.setVerticalGroup(
            jDialogInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogInformacionLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(btnAceptar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        labTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labTitulo.setText("Login");

        labNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labNombre.setText("Nombre de Usuario:");

        labContrasena.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labContrasena.setText("Contraseña:");

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        labVisibilidadContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labVisibilidadContrasenaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addComponent(labContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pfContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labVisibilidadContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(labTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(btnSiguiente)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(labTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(labContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labVisibilidadContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pfContrasena))
                .addGap(39, 39, 39)
                .addComponent(btnSiguiente)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función ActionPerformed para el boton btnSiguiente en la que en primer
     * lugar comprobamos si todos los campos están rellenados;
     * En segundo lugar si el login es correcto comprobamos si el usuario tiene 
     * condición de cliente o de administrador, en función de ello se le redigirá 
     * a una ventana u otra. En caso contrario se muestra un jDialog de error.
     * @param evt Evento recibido por la función
     */
    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        if(!estanTodosLosCamposRellenados()) {
            mostrarVentana(CAMPOS_SIN_RELLENAR);
        } else if(esLoginCorrecto()) {
            //Si el usuario que intenta hacer el login es Cliente
            if(sesionUsuario.getTipoUsuario().equals("Cliente")) {
                new MenuCliente(sesionUsuario);
                this.setVisible(false);
            }
            //Si el usuario que intenta hacer el login es Cliente
            else if(sesionUsuario.getTipoUsuario().equals("Administrador")) {
                new MenuAdministrador(sesionUsuario);
                this.setVisible(false);
            }
            //Si no es ninguna de las anteriores
            else {
                System.out.println("Error. El usuario no es ni cliente ni administrador");
            }
        } else {
            mostrarVentana(obtenerMensajeDeTipoDeError());
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    /**
     * Función MouseClicked para el JLabel labVisibilidadContrasena en la que
     * al pulsar el jLabel cambiamos la condición actual tanto del propio JLabel
     * como del PasswordTextField;
     * Si las letras del passwordText se veían ocultas tras puntos al pulsar se
     * podrán observar las letras ocultas, además de cambiar la imagen del JLabel
     * En caso contrario se ocultarán las letras con puntos y también cambiará
     * la imagen del JLabel.
     * @param evt Evento recibido por la función.
     */
    private void labVisibilidadContrasenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labVisibilidadContrasenaMouseClicked
        if(pfContrasena.getEchoChar() == '*'){
            pfContrasena.setEchoChar((char)0);
            Image imgIcon = new ImageIcon("Imagenes\\Ojo.png").getImage();
            ImageIcon imgEscalada = new ImageIcon(imgIcon.getScaledInstance(labVisibilidadContrasena.getWidth(), labVisibilidadContrasena.getHeight(), Image.SCALE_SMOOTH));
            labVisibilidadContrasena.setIcon(imgEscalada);
        }else{
            pfContrasena.setEchoChar('*');
            Image imgIcon = new ImageIcon("Imagenes\\OjoTachado.png").getImage();
            ImageIcon imgEscalada = new ImageIcon(imgIcon.getScaledInstance(labVisibilidadContrasena.getWidth(), labVisibilidadContrasena.getHeight(), Image.SCALE_SMOOTH));
            labVisibilidadContrasena.setIcon(imgEscalada);
        }
    }//GEN-LAST:event_labVisibilidadContrasenaMouseClicked

    /**
     * Función ActionPerformed para el boton btnAceptar del JDialog en la que
     * se cierra la ventana JDialogInformacion al pulsar el boton.
     * @param evt Evento recibido por la función
     */
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        jDialogInformacion.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JEditorPane ePaneInformacion;
    private javax.swing.JDialog jDialogInformacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labContrasena;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labTitulo;
    private javax.swing.JLabel labVisibilidadContrasena;
    private javax.swing.JPasswordField pfContrasena;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTextField tfNombre;
    // End of variables declaration//GEN-END:variables

    /**
     * Creación inicial del documento claves.txt, en el que ya tiene creado un 
     * usuario con condición de administrador con las siguientes credenciales:
     * Nombre: admin
     * Contrasena: admin.
     */
    private void creacionDocumentoLogin() {
        try {
            File archivoClaves = new File(System.getProperty("user.dir") + "\\claves.txt");
            if(!archivoClaves.exists()){
                System.out.println("Creamos fichero de claves.");
                archivoClaves.createNewFile();             
                insertarCuentaAdministradorPorDefecto(archivoClaves);
            } else {
            }
        } catch(IOException ex) {
            System.out.println("Error en la creación del documento de claves.");
        }
    }

    /**
     * Establecimiento de la imagen del ojo tachado en el JLabel labVisibilidadContrasena.
     */
    private void iniciarIcono(){
        //Inicializamos el icono de visibilidad para contraseña y confirmar contraseña
        Image imgIcon = new ImageIcon("Imagenes\\OjoTachado.png").getImage();
        ImageIcon imgEscalada = new ImageIcon(imgIcon.getScaledInstance(labVisibilidadContrasena.getWidth(), labVisibilidadContrasena.getHeight(), Image.SCALE_SMOOTH));
        labVisibilidadContrasena.setIcon(imgEscalada);

    }
    
    /**
     * Inserción de cuenta inicial con las siguientes credenciales:
     * Nombre: admin
     * Contrasena: admin
     * TipoUsuario: Administrador
     * en el archivo claves.txt que almacena todos los usuarios del sistema.
     * @param archivoLogin Fichero que almacena las los usuarios de la aplicación
     * con sus claves y su tipo de usuario
     * @throws IOException Exception lanzada al encontrar un error en el manejo
     * de ficheros
     */
    private void insertarCuentaAdministradorPorDefecto(File archivoLogin) throws IOException {
        String textoAEscribir = "";
        try (FileWriter miEscritor = new FileWriter(archivoLogin)) {
            textoAEscribir = textoAEscribir + "admin" + ":" + getMd5("admin") + ":" + "Administrador";
            miEscritor.write(textoAEscribir);
        } catch(Exception e) {
            System.out.println("Error con FileWriter.");
        }
    }

    /**
     * Muestra de un JDialog en el que se muestra el texto del error ocurrido.
     * @param texto Texto del error ocurrido en el transcurso de la aplicación.
     */
    public void mostrarVentana(String texto) {
        ePaneInformacion.setText(texto);
        jDialogInformacion.setSize(500, 250);
        jDialogInformacion.setLocationRelativeTo(null);
        jDialogInformacion.setVisible(true);
    }

    /**
     * Comprobación de si todos los campos de la ventana de Login están cumplimentadas.
     * @return Booleano. True si están todos los campos rellenados y False en caso contrario.
     */
    private boolean estanTodosLosCamposRellenados() {
        if(this.tfNombre.getText().isEmpty() || this.pfContrasena.getText().isEmpty())
            return false;
        return true;
    }

    /**
     * Comprobación de si los datos introducidos en la ventana de login 
     * corresponden con los datos de un usuario previamente registrado y si la 
     * contraseña introducida corresponde con el usuario introducido.
     * @return Booleano. True si los datos introducidos se corresponden con
     * los de un usuario previamente registrado y si la contraseña corresponde
     * con el usuario. False en caso contrario.
     */
    private boolean esLoginCorrecto() {
        List<String> contenidoFicheroClaves;
        try {
            contenidoFicheroClaves = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\claves.txt"));
            for(String linea : contenidoFicheroClaves){
                String[] trozos = linea.split(":");
                String nombreUsuario = trozos[0];
                String contrasena = trozos[1];
                String rolUsuario = trozos[2].split("\n")[0];
                if(nombreUsuario.equals(tfNombre.getText()) && contrasena.equals(getMd5(this.pfContrasena.getText()))) {
                    sesionUsuario = new SesionUsuario(nombreUsuario, contrasena, rolUsuario);
                    return true;
                } else {
                    mostrarVentana(CONTRASENA_NO_ES_CORRECTA);
                    return false;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Obtención del mensaje de error ocurrido.
     * @return Texto del mensaje de error ocurrido.
     */
    private String obtenerMensajeDeTipoDeError() {
        List<String> contenidoFicheroClaves;
        try {
            contenidoFicheroClaves = Files.readAllLines(Paths.get(System.getProperty("user.dir") + "\\claves.txt"));
            for(String linea : contenidoFicheroClaves){
                String[] trozos = linea.split(":");
                String nombreUsuario = trozos[0];
                if(!nombreUsuario.equals(tfNombre.getText()))
                    return NOMBRE_USUARIO_NO_ESTA_REGISTRADO;
                else
                    return CONTRASENA_NO_ES_CORRECTA;
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Cifrado de la cadena introducida mediante el algoritmo de hash MD5.
     * @param input Cadena que se va a cifrar.
     * @return Cadena de texto cifrada con algoritmo de hash MD5.
     */
    public static String getMd5(String input) {
        try {
            //Instancia de MessageDigest estableciendo el algoritmo de hash MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            //Calculamos el hash de la cadena de entrada.
            byte[] messageDigest = md.digest(input.getBytes());
 
            //Creación del objeto BigInteger a partir del hash (array de bytes)
            BigInteger no = new BigInteger(1, messageDigest);
 
            //Conversión de message digest a un valor hexadecimal.
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
