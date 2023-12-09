package GSILabs.ProyectoFinal.Login;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Restaurante;
import GSILabs.BSystem.BusinessSystem;
import GSILabs.MongoDB.ConexionBBDD;
import static GSILabs.MongoDB.ConexionBBDD.CargarDatos;
import static GSILabs.MongoDB.ConexionBBDD.DescargarDatos;
import static GSILabs.MongoDB.ConexionBBDD.actualizarLocal;
import static GSILabs.MongoDB.ConexionBBDD.cargarLocal;
import GSILabs.ProyectoFinal.DonaAplicacion.DonaAplicacion;
import GSILabs.ProyectoFinal.Propietario.MenuPropietario;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 * Clase EspecificacionesLocal
 * Interfaz gráfica con la que un propietario que se está registrando introduce
 * los datos de su local.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 05.12.2023
 */
public class EspecificacionesLocal extends javax.swing.JFrame {
    
    /**
     * Almacena datos del usuario que está utilizando la aplicación.
     */
    private Propietario propietario = (Propietario) DonaAplicacion.usuario;

    /**
     * Constructor EspecificacionesLocal.
     * @param propietario Propietario del local del cual se introducen los datos.
     */
    public EspecificacionesLocal(Propietario propietario) {
        initComponents();
        this.propietario = propietario;
        
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
        fieldNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fieldLocalidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fieldDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cBoxTipoLocal = new javax.swing.JComboBox<>();
        botonVolver = new javax.swing.JButton();
        botonConfirmar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldProvincia = new javax.swing.JTextField();
        fieldCalle = new javax.swing.JTextField();
        fieldNumero = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel1.setText("Especificaciones Local");

        jLabel2.setText("Nombre del local");

        jLabel3.setText("Dirección del local");

        jLabel4.setText("Descripción");

        fieldDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldDescripcionActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo local");

        cBoxTipoLocal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Restaurante", "Bar", "Pub" }));
        cBoxTipoLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxTipoLocalActionPerformed(evt);
            }
        });

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        botonConfirmar.setText("Confirmar");
        botonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonConfirmarActionPerformed(evt);
            }
        });

        jLabel6.setText("Localidad:");

        jLabel7.setText("Provincia:");

        jLabel8.setText("Calle:");

        jLabel9.setText("Número:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cBoxTipoLocal, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(30, 30, 30)
                                    .addComponent(fieldNumero))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(46, 46, 46)
                                    .addComponent(fieldCalle))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(22, 22, 22)
                                    .addComponent(fieldProvincia))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(fieldLocalidad))
                                .addComponent(fieldNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fieldDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(fieldLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cBoxTipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Creo que no es necesario hacerlo aquí, habría que hacerlo al pulsar el botón confirmar.
    private void cBoxTipoLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxTipoLocalActionPerformed
        //Hacer insert a la Base de Datos
        //Pasar vista a menu Propiertario
    }//GEN-LAST:event_cBoxTipoLocalActionPerformed

    /**
     * Función ActionPerformed para el JButton botonVolver, mediante la que se
     * vuelve a la ventana de introducción de datos del propietario.
     * @param evt Evento recibido por la función
     */
    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        RegistrarUsuario abrirRegistrarUsuario = new RegistrarUsuario();
        abrirRegistrarUsuario.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_botonVolverActionPerformed

    /**
     * Función ActionPerformed para el boton botonConfirmar en la que en primer
     * lugar comprobamos si todos los campos están rellenados;
     * En caso contrario se muestra un JOptionPane de error;
     * Si se han introducido correctamente los datos entonces se crea el nuevo
     * perfil de propietario con las especificaciones de local y se le redirige
     * a la ventana MenuPropietario.
     * @param evt Evento recibido por la función
     */
    private void botonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonConfirmarActionPerformed
        if(fieldNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca un nombre al local.");
        } else if(fieldLocalidad.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca una localidad al local.");
        }else if(fieldProvincia.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca una provincia al local.");
        }else if(fieldCalle.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca una calle al local.");
        }else if(fieldNumero.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca una numero al local.");
        } else if(fieldDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Porfavor, introduzca una descripcion al local.");
        } else {
            //Rellenamos atributos para clase Local
            String nombreLocal = this.fieldNombre.getText();
            //Cambiar esto para que coja de manera correcta la direccion
            Direccion direccion = new Direccion(this.fieldLocalidad.getText(), this.fieldProvincia.getText(), this.fieldCalle.getText(), Integer.parseInt(this.fieldNumero.getText()));
            String descripcion = this.fieldDescripcion.getText();
            
            BusinessSystem bs = DescargarDatos();
            
            //Local local = null;
            if((cBoxTipoLocal.getItemAt(cBoxTipoLocal.getSelectedIndex())).equals("Bar")) {
                Bar local = new Bar(nombreLocal, direccion, descripcion, this.propietario);
                this.propietario.setLocal(local);
                if (bs.obtenerLocal(local.getDireccion()) != null) {
                    System.out.println("Este local ya existe");
                    if(bs.asociarLocal(local, propietario)){
                        System.out.println("Bar asociado correctamente");
                    }
                    //CargarDatos(bs);
                    if(actualizarLocal(local)){
                        System.out.println("Bar actualizado correctamente");
                    }
                } else {
                    bs.nuevoLocal(local);
                    System.out.println("Este local no existe");
                    cargarLocal(local);
                }
            } else if((cBoxTipoLocal.getItemAt(cBoxTipoLocal.getSelectedIndex())).equals("Restaurante")) {
                Restaurante local = new Restaurante(nombreLocal, direccion, descripcion, this.propietario, 12.0, null, null);
                this.propietario.setLocal(local);
                if (bs.obtenerLocal(local.getDireccion()) != null) {
                    System.out.println("Este local ya existe");
                    bs.asociarLocal(local, propietario);
                    CargarDatos(bs);
                } else {
                    bs.nuevoLocal(local);
                    System.out.println("Este local no existe");
                    cargarLocal(local);
                }
            } else if((cBoxTipoLocal.getItemAt(cBoxTipoLocal.getSelectedIndex())).equals("Pub")) {
                Pub local = new Pub(null, null, nombreLocal, direccion, descripcion, this.propietario);
                this.propietario.setLocal(local);
                if (bs.obtenerLocal(local.getDireccion()) != null) {
                    System.out.println("Este local ya existe");
                    bs.asociarLocal(bs.obtenerLocal(local.getDireccion()), propietario);
                    //bs.actualizarLocal(local, local)
                    CargarDatos(bs);
                } else {
                    bs.nuevoLocal(local);
                    System.out.println("Este local no existe");
                    cargarLocal(local);
                }
            }
            
            MenuPropietario abrirMenuPropietario = new MenuPropietario(propietario);
            this.setVisible(false);
        }
            
        System.out.println("Nombre" + fieldNombre.getText());
        System.out.println("Direccion" + fieldLocalidad.getText());
        System.out.println("Descripcion" + fieldDescripcion.getText());
        System.out.println("Tipo" + cBoxTipoLocal.getItemAt(cBoxTipoLocal.getSelectedIndex()));
    }//GEN-LAST:event_botonConfirmarActionPerformed

    private void fieldDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldDescripcionActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonConfirmar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JComboBox<String> cBoxTipoLocal;
    private javax.swing.JTextField fieldCalle;
    private javax.swing.JTextField fieldDescripcion;
    private javax.swing.JTextField fieldLocalidad;
    private javax.swing.JTextField fieldNombre;
    private javax.swing.JTextField fieldNumero;
    private javax.swing.JTextField fieldProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
