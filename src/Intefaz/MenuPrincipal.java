/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intefaz;

import Modelo.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JasonllPaul
 */
public class MenuPrincipal extends javax.swing.JFrame {

    Hotel hotel;

    public MenuPrincipal() {
        initComponents();
        inicializarEntidades();
    }

    public final void inicializarEntidades() {
        hotel = new Hotel();
        
        consultarTodosHoteles();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInformacion = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblInformacion.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tblInformacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIT", "NOMBRE", "DIRECCIÓN", "AÑO FUNDACIÓN"
            }
        ));
        tblInformacion.setEnabled(false);
        tblInformacion.setGridColor(new java.awt.Color(102, 153, 255));
        tblInformacion.setShowHorizontalLines(false);
        tblInformacion.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblInformacion);

        escritorio.add(jScrollPane1);
        jScrollPane1.setBounds(30, 390, 830, 150);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CADENA HOTELERA");
        escritorio.add(jLabel2);
        jLabel2.setBounds(120, 40, 660, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private static DefaultTableModel ConstruirModeloDeDatos(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

    // ------------------------------------------- ***** INICIO ASIGNACIONES A TABLAS ******* ------------------------------------------
    
    public void consultarTodosHoteles() {
        ResultSet resTodos = hotel.consultarTodos();
        try {
            tblInformacion.setModel(ConstruirModeloDeDatos(resTodos));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    
    // ------------------------------------------- ***** FIN ASIGNACIONES A TABLAS ******* ------------------------------------------
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane escritorio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInformacion;
    // End of variables declaration//GEN-END:variables
}
