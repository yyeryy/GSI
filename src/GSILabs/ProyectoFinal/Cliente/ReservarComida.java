/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GSILabs.ProyectoFinal.Cliente;

import GSILabs.BModel.Donacion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Usuario;
import static GSILabs.MongoDB.ConexionBBDD.actualizarDonacionUsuario;
import static GSILabs.MongoDB.ConexionBBDD.descargarDonacionesDisponibles;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan1
 */
public class ReservarComida extends javax.swing.JFrame {


    private Usuario usuario = null;

    private ArrayList<Donacion> donaciones = new ArrayList<>();

    private ArrayList<Local> locales = new ArrayList<>();

    private ArrayList<Donacion> donacionesDeUnLocal = new ArrayList<>();
    /**
     * Creates new form ReservarComida
     */
    public ReservarComida(Usuario usuario) {
        initComponents();
        this.usuario = usuario;

        // Descargar donaciones disponibles (donaciones con usuario = null)
        this.donaciones = descargarDonacionesDisponibles(null);
        this.locales.clear();
        if(this.donaciones.size() < 1){
            System.out.println("Fallo en Donaciones descargadas. Tamaño = "+ this.donaciones.size());
            JOptionPane.showMessageDialog(null, "No hay Donaciones disponibles.");  
            // Ventana anterior
            MenuCliente abrirMenuCliente = new MenuCliente(this.usuario);
            this.setVisible(false);
        }else{
            System.out.println("Donaciones descargadas. Tamaño = "+ this.donaciones.size());       
        
            for (Donacion dona : this.donaciones){
                if(!this.locales.contains(dona.getLocal())){
                    this.locales.add(dona.getLocal());
                    jComboBox1.addItem(dona.getLocal().getNombre());
                }
            }
            

            super.setVisible(true);
            super.setLocationRelativeTo(null);
        }
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setText("Local:");
        jLabel1.setPreferredSize(new java.awt.Dimension(500, 32));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel2.setText("Comida:");
        jLabel2.setPreferredSize(new java.awt.Dimension(500, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel3.setText("Dirección");
        jLabel3.setPreferredSize(new java.awt.Dimension(500, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel4.setText("Cantidad:");
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(250, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel5.setText("Tipo de Donación");
        jLabel5.setOpaque(true);
        jLabel5.setPreferredSize(new java.awt.Dimension(250, 32));

        jButton1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton1.setPreferredSize(new java.awt.Dimension(150, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jButton2.setText("Reservar");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton2.setPreferredSize(new java.awt.Dimension(150, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("1");

        jComboBox1.setToolTipText("");
        jComboBox1.setPreferredSize(new java.awt.Dimension(500, 32));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setPreferredSize(new java.awt.Dimension(500, 32));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel7.setText("jLabel7");
        jLabel7.setPreferredSize(new java.awt.Dimension(500, 32));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Completa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)))
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 112, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                
        // Ventana anterior
        MenuCliente abrirMenuCliente = new MenuCliente(this.usuario);
        this.setVisible(false);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int i = (int) jComboBox2.getSelectedIndex();
        if(!this.donacionesDeUnLocal.isEmpty()){
            Donacion dona = this.donacionesDeUnLocal.get(i);

            dona.setUsuario(this.usuario);
            // Actualizar
            if(actualizarDonacionUsuario(dona)){
                JOptionPane.showMessageDialog(null, "Reserva de la Donación realizada.");

                MenuCliente abrirMenuCliente = new MenuCliente(this.usuario);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Se ha producido un error al hacer la Reserva de la Donación.");
            }
         

        } else{
            JOptionPane.showMessageDialog(null, "Se ha producido un error al hacer la Reserva de la Donación.");
        }




    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        int i = (int) jComboBox1.getSelectedIndex();
        Local loca = this.locales.get(i);

        // Introducir Direccion
        jLabel7.setText(loca.getDireccion().getCalle() + ", " 
                + loca.getDireccion().getLocalidad() + ","
                + loca.getDireccion().getProvincia());

        // Buscamos las donaciones con el mismo Local loca
        this.donacionesDeUnLocal.clear();

        int n = jComboBox2.getItemCount();
        for (i = n-1; i > 0; i--){
            jComboBox2.removeItemAt(i);
        }
        for (Donacion dona : this.donaciones){
            if(dona.getLocal().equals(loca) && !this.donacionesDeUnLocal.contains(dona)){
                this.donacionesDeUnLocal.add(dona);
                jComboBox2.addItem(dona.getNombreProducto());
            }
        }
        if(n > 0){
            jComboBox2.removeItemAt(0);
        }

        int i2 = (int) jComboBox2.getSelectedIndex();
        if(!this.donacionesDeUnLocal.isEmpty()){
            Donacion dona = this.donacionesDeUnLocal.get(i2);

            // Introducir cantidad
            jLabel6.setText(Integer.toString(dona.getCantidadProducto()));
        }



        if(this.donacionesDeUnLocal.size() < 1){
            System.out.println("Fallo en Selección de Donacion. Tamaño = "+ this.donacionesDeUnLocal.size());
        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

        int i = (int) jComboBox2.getSelectedIndex();
        if(!this.donacionesDeUnLocal.isEmpty()){
            Donacion dona = this.donacionesDeUnLocal.get(i);

            // Introducir cantidad
            jLabel6.setText(Integer.toString(dona.getCantidadProducto()));
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
