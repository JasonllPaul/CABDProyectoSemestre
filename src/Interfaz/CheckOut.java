package Interfaz;

import Datos.Conexion;
import Datos.Datos_Cliente;
import Datos.Datos_Comprende;
import Datos.Datos_Consultas;
import Datos.Datos_Habitacion;
import Datos.Datos_Reserva;
import Interfaz.Submenu.CalculoReserva;
import Modelo.Modelo_Cliente;
import Modelo.Modelo_Habitacion;
import Modelo.Modelo_Reserva;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utilidades.Validador;

/**
 *
 * @author JasonllPaul
 */
public final class CheckOut extends javax.swing.JDialog {

    Modelo.Modelo_Reserva reserva;
    Modelo.Modelo_Hotel hotel;
    Modelo.Modelo_Comprende comprende;
    Datos_Habitacion habitacionControlador;
    Datos_Comprende comprendeControlador;
    Datos_Reserva reservaControlador;
    Datos_Cliente clienteControlador;
    Datos_Consultas consultasControlador;
    Conexion conexion;
    Validador validador;
    boolean encontrado;

    /**
     * Creates new form MenuReserva
     *
     * @param parent
     * @param modal
     * @param hotel
     * @param conexion
     */
    public CheckOut(java.awt.Frame parent, boolean modal, Modelo.Modelo_Hotel hotel, Conexion conexion) {
        super(parent, modal);
        initComponents();
        this.validador = new Validador();
        this.encontrado = false;
        this.hotel = hotel;
        this.conexion = conexion;
        consultasControlador = new Datos_Consultas(this.conexion);
        habitacionControlador = new Datos_Habitacion(conexion);
        reservaControlador = new Datos_Reserva(conexion);
        comprendeControlador = new Datos_Comprende(conexion);
        reserva = new Modelo_Reserva();
    }

    public void ejecutarCheckOut(int pos) throws SQLException {
        if (JOptionPane.showConfirmDialog(this, "¿Está seguro del Check-Out?") == 0) {
            try {
                cargarReserva(pos);
            } catch (SQLException ex) {
                Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
            }

            int nHabitaciones = 0;
            ResultSet res = consultasControlador.joinReservaComprende(reserva.getResId());
            while (res.next()) {
                nHabitaciones = Integer.parseInt(res.getObject("Número de habitaciones").toString());
            }

            CalculoReserva cr = new CalculoReserva(null, true, reserva, nHabitaciones);
            cr.setVisible(encontrado);
            reserva.setResestado("Finalizado");
            if (reservaControlador.checkout(reserva) == 1) {

                res = consultasControlador.joinReservaComprendeHabId(reserva.getResId());
                while (res.next()) {
                    
                    Modelo_Habitacion habitacion = new Modelo_Habitacion();
                    habitacion.setHabid(Integer.parseInt(res.getObject("HABID").toString()));
                    habitacion.setHab("Libre");
                    if (habitacionControlador.actualizarEstadoHabitacion(habitacion) == 1) {

                        JOptionPane.showMessageDialog(this, "Check-Out exitoso");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error del sistema para Check-Out");
                    }
                }

                llenarTablaReserva();
            } else {
                JOptionPane.showMessageDialog(this, "Error del sistema para Check-Out");
            }
        }
    }

    public boolean prepararCheckout() {
        int pos = tblReserva.getSelectedRow();
        if (tblReserva.getValueAt(pos, 3) != null) {
            String fechaFin = tblReserva.getValueAt(pos, 3).toString();
            System.out.println("fechaFin: " + fechaFin);
            return fechaFin.length() > 0;
        } else {
            return false;
        }
    }

    public int buscarCliente() {
        ArrayList<String> array = new ArrayList<>();
        if (txtDni.getText().length() > 0) {
            Modelo_Cliente cliente = new Modelo_Cliente();
            try {
                cliente.setCliDni(Integer.parseInt(txtDni.getText()));
            } catch (java.lang.NumberFormatException e) {
                System.out.println("Excepción: " + e.getMessage());
            }
            clienteControlador = new Datos_Cliente(this.conexion);
            ResultSet resTodos = clienteControlador.buscarRegistroClientes(cliente);

            try {
                while (resTodos.next()) {
                    array.add(resTodos.getObject("CLIDNI").toString());
                    lblListaReservas.setText("Lista de reservas " + resTodos.getObject("CLINOMBRE").toString().trim() + resTodos.getObject("CLIAPELLIDO").toString().trim());
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return array.size();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEscritorio = new javax.swing.JPanel();
        panelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblDni = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnCalcular = new javax.swing.JButton();
        lblEstadoDni = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReserva = new javax.swing.JTable();
        lblListaReservas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelEscritorio.setBackground(new java.awt.Color(255, 255, 255));

        panelTitulo.setBackground(new java.awt.Color(102, 51, 0));
        panelTitulo.setForeground(new java.awt.Color(102, 51, 0));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CHECK-OUT");

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        txtDni.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
        });

        lblDni.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDni.setText("DNI Cliente:");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHECK-OUT");

        btnGuardar.setBackground(new java.awt.Color(0, 102, 204));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Check-Out");
        btnGuardar.setFocusable(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cerrar");
        btnCancelar.setFocusable(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCalcular.setBackground(new java.awt.Color(0, 153, 153));
        btnCalcular.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCalcular.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcular.setText("Calcular");
        btnCalcular.setFocusable(false);
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        lblEstadoDni.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEstadoDni.setForeground(new java.awt.Color(0, 153, 51));
        lblEstadoDni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        tblReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Número de Reserva", "Hotel", "Fecha de Inicio", "Fecha Fin", "Total"
            }
        ));
        jScrollPane1.setViewportView(tblReserva);

        lblListaReservas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblListaReservas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblListaReservas.setText("Lista de reservas");

        javax.swing.GroupLayout panelEscritorioLayout = new javax.swing.GroupLayout(panelEscritorio);
        panelEscritorio.setLayout(panelEscritorioLayout);
        panelEscritorioLayout.setHorizontalGroup(
            panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEscritorioLayout.createSequentialGroup()
                .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblEstadoDni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDni, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                            .addGroup(panelEscritorioLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelEscritorioLayout.createSequentialGroup()
                                .addGap(247, 247, 247)
                                .addComponent(btnCancelar)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardar))
                            .addComponent(lblListaReservas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(340, 340, 340)
                        .addComponent(btnCalcular))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEscritorioLayout.setVerticalGroup(
            panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEscritorioLayout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoDni, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCalcular)
                        .addGap(120, 120, 120))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblListaReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEscritorio, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEscritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int pos = tblReserva.getSelectedRow();
        if (pos != -1 && encontrado) {
            try {
                int resid = Integer.parseInt(tblReserva.getValueAt(pos, 0).toString());
                reserva.setResId(resid);
                if (prepararCheckout()) {
                    ejecutarCheckOut(pos);
                } else {
                    reserva.setResFechaFin(new Date());
                    if (reservaControlador.insertFechaFin(reserva) == 1) {
                        llenarTablaReserva();
                        ejecutarCheckOut(pos);
                    } else {
                        JOptionPane.showMessageDialog(this, "Problema con la fecha fin");
                    }

                }

            } catch (java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese el DNI del cliente correctamente");
            } catch (SQLException ex) {
                Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una reserva");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        if (validar()) {

        }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased

        validador.validarTamanio(txtDni);
        validador.validarNumeros(txtDni, lblEstadoDni);

        if (buscarCliente() == 1) {
            lblEstadoDni.setForeground(new Color(0, 153, 51));
            lblEstadoDni.setText("Cliente registrado");
            llenarTablaReserva();
            encontrado = true;
        } else {
            lblEstadoDni.setForeground(Color.red);
            lblEstadoDni.setText("DNI no encontrado");
            lblListaReservas.setText("");
            tblReserva.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{},
                    new String[]{
                        "Número de Reserva", "Hotel", "Fecha de Inicio", "Fecha Fin", "Total"
                    }
            ));
            encontrado = false;
        }

        if (!validador.validarVacio(txtDni)) {
            lblEstadoDni.setText("");
            lblListaReservas.setText("");
            encontrado = false;
        }

    }//GEN-LAST:event_txtDniKeyReleased

    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (encontrado) {
                llenarTablaReserva();
            }
        }

    }//GEN-LAST:event_txtDniKeyPressed

    public void cargarReserva(int pos) throws SQLException {

        if (pos != -1) {
            int resid = Integer.parseInt(tblReserva.getValueAt(pos, 0).toString());
            reserva.setResId(resid);
            ResultSet res = reservaControlador.buscarRegistroReservas(reserva);
            while (res.next()) {
                reserva.setResId(Integer.parseInt(res.getObject("RESID").toString()));
                reserva.setHotId(Integer.parseInt(res.getObject("HOTID").toString()));
                reserva.setCliDni(Integer.parseInt(res.getObject("CLIDNI").toString()));
                reserva.setResPersonas(Integer.parseInt(res.getObject("RESPERSONAS").toString()));
                reserva.setResestado(res.getObject("RESESTADO").toString());

                String fechaInicio = new SimpleDateFormat("MM/dd/yy").format(new Date(res.getObject("RESFECHAINICIO").toString().substring(0, 10).replace("-", "/")));
                String fechaFin = new SimpleDateFormat("MM/dd/yy").format(new Date(res.getObject("RESFECHAFIN").toString().substring(0, 10).replace("-", "/")));

                reserva.setResFechaFin(new Date(fechaFin));
                reserva.setResFechaInicio(new Date(fechaInicio));
                reserva.setRestotal(Float.parseFloat(res.getObject("RESTOTAL").toString()));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva");
        }
    }

    public void llenarTablaReserva() {
        ResultSet resTodos = consultasControlador.joinReservaHotel(Integer.parseInt(txtDni.getText()));
        try {
            tblReserva.setModel(MenuPrincipal.ConstruirModeloDeDatos(resTodos));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean validar() {
        if (validador.validarVacio(txtDni)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, "El campo de texto DNI está vacío");
            return false;
        }
    }

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
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CheckOut dialog = new CheckOut(new javax.swing.JFrame(), true, null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblEstadoDni;
    private javax.swing.JLabel lblListaReservas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelEscritorio;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTable tblReserva;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
