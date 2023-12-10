package GSILabs.ProyectoFinal.Login;

import GSILabs.BModel.Usuario;
import static GSILabs.MongoDB.ConexionBBDD.descargarUsuario;
import GSILabs.ProyectoFinal.Cliente.MenuCliente;
import GSILabs.ProyectoFinal.Propietario.MenuPropietario;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

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
     * Usuario que está intentando hacer login.
     */
    private Usuario usuario = null;

    /**
     * Constructor Login.
     */
    public Login() {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        UsuarioLogin = new javax.swing.JTextField();
        botonLogin = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();
        ContrasenaLogin = new javax.swing.JPasswordField();
        mostrarContraseña = new javax.swing.JCheckBox();
        botonRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("Contraseña:");

        botonLogin.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        botonLogin.setText("Login");
        botonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLoginActionPerformed(evt);
            }
        });

        botonSalir.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        mostrarContraseña.setText("Mostrar Contraseña");
        mostrarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarContraseñaActionPerformed(evt);
            }
        });

        botonRegistrarse.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
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
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(botonSalir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(botonRegistrarse)
                                .addGap(52, 52, 52)
                                .addComponent(botonLogin))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(mostrarContraseña))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(UsuarioLogin)
                                    .addComponent(ContrasenaLogin)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1)))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(UsuarioLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ContrasenaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(mostrarContraseña)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonSalir)
                    .addComponent(botonRegistrarse)
                    .addComponent(botonLogin))
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función ActionPerformed para el boton botonLogin en la que en primer
     * lugar comprobamos si todos los campos están rellenados;
     * En segundo lugar, si el login es correcto comprobamos si el usuario tiene 
     * condición de cliente o de administrador, en función de ello se le redigirá 
     * a una ventana u otra. En caso contrario se muestra un JOptionPane de error.
     * @param evt Evento recibido por la función
     */
    private void botonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLoginActionPerformed
        System.out.println("Boton login pulsado");
        //Si ambos campos están sin rellenar
        if(UsuarioLogin.getText().equals("") && ContrasenaLogin.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca un nombre de usuario y una contraseña.");
        } 
        //Si el campo nombre de usuario está sin rellenar
        else if(UsuarioLogin.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca un nombre de usuario.");
        } 
        //Si el campo contraseña está sin rellenar
        else if(ContrasenaLogin.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca una contraseña.");
        } 
        //Si el login se ha realizado correctamente
        else if(esLoginCorrecto()) {
            //Si el usuario que intenta hacer el login es Cliente
            if(usuario.getStringTipo().equals("Cliente")) {
                MenuCliente abrirMenuCliente = new MenuCliente(this.usuario);
                this.setVisible(false);
            }
            //Si el usuario que intenta hacer el login es Cliente
            else if(usuario.getStringTipo().equals("Propietario")) {
                MenuPropietario abrirMenuPropietario = new MenuPropietario(this.usuario);
                this.setVisible(false);
            }
            //Si no es ninguna de las anteriores
            else {
                System.out.println("Error. El usuario no es ni cliente ni propietario");
            }
            
        //El login no se ha realizado correctamente
        } else {
            System.out.println("El login es incorrecto.");
        }
    }//GEN-LAST:event_botonLoginActionPerformed

    /**
     * Función ActionPerformed para el JCheckBox mostrarContraseña, que al 
     * pulsarlo podemos visualizar la contraseña, y al volverlo a pulsar
     * se vuelve a ocultar la contraseña mediante puntos.
     * @param evt Evento recibido por la función
     */
    private void mostrarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarContraseñaActionPerformed
        if(mostrarContraseña.isSelected()){
            ContrasenaLogin.setEchoChar((char)0);
        } else {
            ContrasenaLogin.setEchoChar('*');
        }
    }//GEN-LAST:event_mostrarContraseñaActionPerformed

    /**
     * Función ActionPerformed para el JButton botonSalir, mediante el que se
     * sale del programa al pulsarlo.
     * @param evt Evento recibido por la función
     */
    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_botonSalirActionPerformed

    /**
     * Función ActionPerformed para el JButton botonRegistrarse, mediante el que
     * se accede al JFrame RegistrarUsuario donde podremos crear un nuevo usuario.
     * @param evt Evento recibido por la función
     */
    private void botonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarseActionPerformed
        RegistrarUsuario abrirRegistrarUsuario = new RegistrarUsuario();
        abrirRegistrarUsuario.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonRegistrarseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField ContrasenaLogin;
    private javax.swing.JTextField UsuarioLogin;
    private javax.swing.JButton botonLogin;
    private javax.swing.JButton botonRegistrarse;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JCheckBox mostrarContraseña;
    // End of variables declaration//GEN-END:variables

    
    /** PROBABLEMENTE NO HAGA FALTA
     * Comprobación de si los datos introducidos en la ventana de login 
     * corresponden con los datos de un usuario previamente registrado y si la 
     * contraseña introducida corresponde con el usuario introducido.
     * @return Booleano. True si los datos introducidos se corresponden con
     * los de un usuario previamente registrado y si la contraseña corresponde
     * con el usuario. False en caso contrario.
     */
    private boolean esLoginCorrecto() {
        // Obtener datos usuario
        String nick = UsuarioLogin.getText();
        String contrasena = getMd5(ContrasenaLogin.getText());

        //Descargamos el usuario que buscamos
        Usuario usuarioLogin = descargarUsuario(nick);
        
        //Comprobar contraseña
        //Si no se ha descargado correctamente el usuario
        if(usuarioLogin == null) {
            JOptionPane.showMessageDialog(null, "Los datos introducidos no son correctos.");
            return false;
        }
        //Si la contraseña introducida en el login no coincide con la contraeña real
        if(!usuarioLogin.getContraseña().equals(contrasena)) {
            JOptionPane.showMessageDialog(null, "Los datos introducidos no son correctos.");
            return false;
        }
        //Si el login es correcto
        this.usuario = usuarioLogin;
        if(this.usuario.getTipo().toString().equals("PROPIETARIO")){
            System.out.println("Tipo de usuario: PROPIETARIO");
        } else if(this.usuario.getTipo().toString().equals("CLIENTE")){
            System.out.println("Tipo de usuario: CLIENTE");
        }
        return true;
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
