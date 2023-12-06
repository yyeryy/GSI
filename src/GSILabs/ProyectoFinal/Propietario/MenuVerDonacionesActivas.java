package GSILabs.ProyectoFinal.Propietario;

import GSILabs.BModel.Usuario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author javie
 */
public class MenuVerDonacionesActivas extends javax.swing.JFrame {
    
    /**
     * Almacena datos del usuario que está utilizando la aplicación.
     */
    private Usuario usuario = null;

    /**
     * Constructor MenuVerDonacionesActivas
     * @param usuario usuario con la que se está utilizando la aplicación.
     */
    public MenuVerDonacionesActivas(Usuario usuario) {
        initComponents();
        List<String> listaComidas = new ArrayList<>(Arrays.asList("Pan", "Aceite", "Huevo", "Ensalada", "Macarrones"));
        DefaultListModel modelo = new DefaultListModel();
        this.listaComidas.setModel(modelo);
        for (String comida: listaComidas){
            modelo.addElement(comida);
        }
        
        this.usuario = usuario;
    }
    
    public String devuelveComida(String comida){
        return comida;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelDonacionesDisponibles = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaComidas = new javax.swing.JList<>();
        botonVolver = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelDonacionesDisponibles.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N
        labelDonacionesDisponibles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDonacionesDisponibles.setText("Donaciones Disponibles");

        jScrollPane1.setViewportView(listaComidas);

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        BotonEliminar.setBackground(new java.awt.Color(255, 0, 51));
        BotonEliminar.setText("Eliminar");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(botonVolver)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotonEliminar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(labelDonacionesDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelDonacionesDisponibles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonVolver)
                    .addComponent(BotonEliminar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        int[] indices = this.listaComidas.getSelectedIndices();
        List<Integer> indicesAEliminar = new ArrayList<>();
        for (Integer indice: indices){
            System.out.println(indice);
            indicesAEliminar.add(indice);
        }
        Collections.sort(indicesAEliminar, Collections.reverseOrder());
        DefaultListModel modelo = (DefaultListModel) this.listaComidas.getModel();
        for (int indice: indicesAEliminar){
            System.out.println(modelo.get(indice)+" eliminado ("+indice+")");
            modelo.remove(indice);
        }
    }//GEN-LAST:event_BotonEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDonacionesDisponibles;
    private javax.swing.JList<String> listaComidas;
    // End of variables declaration//GEN-END:variables
}
