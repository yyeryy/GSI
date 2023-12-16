/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GSILabs.ProyectoFinal.Cliente;

import GSILabs.BModel.Donacion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import GSILabs.BModel.Usuario;
import static GSILabs.MongoDB.ConexionBBDD.descargarDonacionesDisponibles;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan1
 */
public class HistorialDonaciones extends javax.swing.JFrame {


    private Usuario usuario = null;

    private ArrayList<Donacion> donaciones = new ArrayList<>();
    /**
     * Creates new form HistorialDonaciones
     */
    public HistorialDonaciones(Usuario usuario) {
        initComponents();
        this.usuario = usuario;

        // Descargar donaciones reservadas por el usuario(donaciones con usuario = usuario)
        this.donaciones = descargarDonacionesDisponibles(this.usuario);
        if(this.donaciones.size() < 1){
            System.out.println("Fallo en Donaciones descargadas o esta vacía. Tamaño = "+ this.donaciones.size());
            JOptionPane.showMessageDialog(null, "No has reservado ninguna Donación.");  
            // Ventana anterior
            MenuCliente abrirMenuCliente = new MenuCliente(this.usuario);
            this.setVisible(false);
        }else{
            System.out.println("Donaciones descargadas. Tamaño = "+ this.donaciones.size());       
        
            DefaultListModel modelo = new DefaultListModel();
            for (Donacion dona : this.donaciones){

                String local = "Local: ";
                if(dona.getLocal().getTipo() == tipoLocal.BAR){
                    local = "Bar: ";
                }else if(dona.getLocal().getTipo() == tipoLocal.PUB){
                    local = "Pub: ";
                }else if(dona.getLocal().getTipo() == tipoLocal.RESTAURANTE){
                    local = "Restaurante: ";
                }

                modelo.addElement(
                    "Comida: " + dona.getNombreProducto() + "    \n"
                    + "Cantidad: " + dona.getCantidadProducto() + "    \n"
                    + local + dona.getLocal().getNombre());
            }
            jList1.setModel(modelo);
            
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Ver Detalles");
        jButton1.setActionCommand("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Volver");
        jButton2.setActionCommand("jButton1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.setDragEnabled(true);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jList1.getSelectedIndex() != -1){
            Donacion dona = this.donaciones.get(jList1.getSelectedIndex());

            VerDonacionCliente abrirVerDonacionCliente = new VerDonacionCliente(dona);
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null, "Donación no selecionada.");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Ventana anterior
        MenuCliente abrirMenuCliente = new MenuCliente(this.usuario);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
