package GSILabs.ProyectoFinal.Propietario;

import GSILabs.BModel.Donacion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Usuario;
import static GSILabs.MongoDB.ConexionBBDD.descargarDonacionesDisponibles;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 * Clase MenuHistorialDonaciones
 * Interfaz Gráfica mediante la que se muestra el historial de donaciones
 * realizadas por el usuario, dando la opción de visualizar los detalles
 * de una de las donaciones de la lista que se seleccione.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.12.2023
 */
public class MenuHistorialDonaciones extends javax.swing.JFrame {
    
    /**
     * Almacena datos del usuario que está utilizando la aplicación.
     */
    private Usuario usuario = null;
    private ArrayList<Donacion> donaciones = new ArrayList<>();
    private ArrayList<Donacion> donacionesUsuario = new ArrayList<>();

    /**
     * Constructor MenuHistorialDonaciones
     * @param usuario Usuario con la que se está utilizando la aplicación.
     */
    public MenuHistorialDonaciones(Usuario usuario) {
        initComponents();
        this.donaciones = descargarDonacionesDisponibles(this.usuario);
        
        System.out.println("Donaciones descargadas. Tamaño = "+ this.donaciones.size()); 
        DefaultListModel modelo = new DefaultListModel();
        this.listaDonaciones.setModel(modelo);
        
        for (Donacion donacion : this.donaciones){ 
            for (Propietario propietario : donacion.getLocal().getPropietarios()) {
                if((propietario.getNick()).equals(usuario.getNick())) {
                    modelo.addElement(donacion.getNombreProducto());
                    donacionesUsuario.add(donacion);
                }
            }
        }
        
        this.listaDonaciones.setModel(modelo);
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

        labelHistorialDonaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaDonaciones = new javax.swing.JList<>();
        botonDetalles = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelHistorialDonaciones.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N
        labelHistorialDonaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelHistorialDonaciones.setText("Historial Donaciones");

        listaDonaciones.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaDonaciones);

        botonDetalles.setText("Ver Detalles");
        botonDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDetallesActionPerformed(evt);
            }
        });

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelHistorialDonaciones)
                .addGap(110, 110, 110))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonDetalles))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelHistorialDonaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonDetalles)
                    .addComponent(botonVolver))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDetallesActionPerformed
        int indiceLocal = this.listaDonaciones.getSelectedIndex();
        Donacion donacionSeleccionada = this.donacionesUsuario.get(this.listaDonaciones.getSelectedIndex());
        int indiceGeneral = -1;
        for (int i = 0; i < this.donaciones.size(); i++) {
            if(this.donaciones.get(i).equals(donacionSeleccionada)) {
                indiceGeneral = i;
            }
        }
        
        System.out.println("IndiceGeneral = " + indiceGeneral);
        
        if(indiceGeneral == -1) {
            System.out.println("Se ha producido un error al seleccionar donacion.");
        }
        DefaultListModel modelo = (DefaultListModel) this.listaDonaciones.getModel();
        String comida = (String) modelo.get(indiceLocal); //indice
        
        int cantidad = this.donaciones.get(indiceGeneral).getCantidadProducto();
        String cantidadComoString = String.valueOf(cantidad);
        
        DetallesDonacion detallesDonacion = new DetallesDonacion(comida, cantidadComoString, this.donaciones.get(indiceGeneral).getLocal().getNombre()/*, "1/10/1999"*/);
        detallesDonacion.setVisible(true);
    }//GEN-LAST:event_botonDetallesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonDetalles;
    private javax.swing.JButton botonVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelHistorialDonaciones;
    private javax.swing.JList<String> listaDonaciones;
    // End of variables declaration//GEN-END:variables
}
