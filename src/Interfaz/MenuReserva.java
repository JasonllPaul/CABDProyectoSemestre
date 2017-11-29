/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.Conexion;
import Datos.Datos_Cliente;
import Datos.Datos_Comprende;
import Datos.Datos_Habitacion;
import Datos.Datos_Reserva;
import Interfaz.Submenu.CalculoReserva;
import Modelo.Modelo_Cliente;
import Modelo.Modelo_Comprende;
import Modelo.Modelo_Habitacion;
import Modelo.Modelo_Reserva;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import utilidades.Validador;

/**
 *
 * @author JasonllPaul
 */
public final class MenuReserva extends javax.swing.JDialog {

    Modelo.Modelo_Reserva reserva;
    Modelo.Modelo_Hotel hotel;
    Modelo.Modelo_Comprende comprende;
    Datos_Habitacion habitacionControlador;
    Datos_Comprende comprendeControlador;
    Datos_Reserva reservaControlador;
    Datos_Cliente clienteControlador;
    Conexion conexion;
    Validador validador;
    boolean calculado;

    /**
     * Creates new form MenuReserva
     *
     * @param parent
     * @param modal
     * @param hotel
     * @param conexion
     */
    public MenuReserva(java.awt.Frame parent, boolean modal, Modelo.Modelo_Hotel hotel, Conexion conexion) {
        super(parent, modal);
        initComponents();
        this.validador = new Validador();
        this.calculado = false;
        this.hotel = hotel;
        this.conexion = conexion;
        habitacionControlador = new Datos_Habitacion(conexion);
        reservaControlador = new Datos_Reserva(conexion);
        comprendeControlador = new Datos_Comprende(conexion);
        reserva = new Modelo_Reserva();
        lblTitulo2.setText(hotel.getHotId() + ". " + hotel.getHotNombre().trim());
        cargarFechas();
        cargarCombo(cmbHabitaciones);
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
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return array.size();
    }

    public void cargarCombo(JComboBox<String> cmb) {
        cmb.removeAllItems();
        ArrayList habitaciones = getValoresTabla("HABID");

        for (int i = 0; i < habitaciones.size(); i++) {
            cmb.addItem(habitaciones.get(i).toString());
        }
    }

    public int asignarHabitaciones() {
        int respuesta = 0;
        for (int i = 0; i < cmbHabitaciones2.getItemCount(); i++) {
            comprende = new Modelo_Comprende();
            comprende.setResid(reserva.getResId());
            comprende.setHabid(Integer.parseInt(cmbHabitaciones2.getItemAt(i)));
            respuesta = comprendeControlador.insertar(comprende);
            if (respuesta == 0) {
                break;
            }
        }

        if (respuesta == 1) { // La respuesta es positiva, se procede a reflejarlo en la base de datos
            System.out.println("ENTRA");
            for (int i = 0; i < cmbHabitaciones2.getItemCount(); i++) {
                Modelo_Habitacion habitacion = new Modelo_Habitacion();
                habitacion.setHabid(Integer.parseInt(cmbHabitaciones2.getItemAt(i)));
                habitacion.setHab("Ocupado");
                respuesta = habitacionControlador.actualizarEstadoHabitacion(habitacion);
                System.out.println("respuesta : " + respuesta);
                if (respuesta == 0) {
                    System.out.println("paila con la act");
                    break;
                }
            }
        }

        return respuesta;
    }

    public ArrayList getValoresTabla(String nombreColumna) {
        ResultSet resTodos = habitacionControlador.consultarHabitacion("Libre", hotel.getHotId());
        ArrayList<String> array = new ArrayList<>();
        try {
            while (resTodos.next()) {
                array.add(resTodos.getObject(nombreColumna).toString());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return array;
    }

    public void cargarFechas() {
        Date fecha = new Date();
        dateInicio.setDate(fecha);
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
        lblTitulo2 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblDni = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cmbPersonas = new javax.swing.JComboBox<>();
        lblNumeroPersonas = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblFechaInicio = new javax.swing.JLabel();
        dateInicio = new com.toedter.calendar.JDateChooser();
        lblFechaFin = new javax.swing.JLabel();
        dateFin = new com.toedter.calendar.JDateChooser();
        txtCalculo = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblCalculo = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cmbHabitaciones2 = new javax.swing.JComboBox<>();
        btnAsignar = new javax.swing.JButton();
        btnDesasignar = new javax.swing.JButton();
        cmbHabitaciones = new javax.swing.JComboBox<>();
        lblDisponibles = new javax.swing.JLabel();
        lblAsignadas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblEstadoDni = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelEscritorio.setBackground(new java.awt.Color(255, 255, 255));

        panelTitulo.setBackground(new java.awt.Color(102, 51, 0));
        panelTitulo.setForeground(new java.awt.Color(102, 51, 0));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("RESERVAS");

        lblTitulo2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblTitulo2.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelTituloLayout = new javax.swing.GroupLayout(panelTitulo);
        panelTitulo.setLayout(panelTituloLayout);
        panelTituloLayout.setHorizontalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelTituloLayout.setVerticalGroup(
            panelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTituloLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtDni.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
        });

        lblDni.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDni.setText("DNI Cliente:");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CREAR NUEVA RESERVA");

        cmbPersonas.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cmbPersonas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        lblNumeroPersonas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblNumeroPersonas.setText("N° Personas:");

        btnGuardar.setBackground(new java.awt.Color(0, 102, 204));
        btnGuardar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("+ Crear");
        btnGuardar.setFocusable(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.setFocusable(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblFechaInicio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblFechaInicio.setText("Fecha Inicio:");

        lblFechaFin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblFechaFin.setText("Fecha Fin:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(lblFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(dateFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(dateFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        txtCalculo.setEditable(false);
        txtCalculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCalculo.setText("$ 0.00");
        txtCalculo.setFocusable(false);
        txtCalculo.setRequestFocusEnabled(false);

        lblCalculo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCalculo.setText("TOTAL:");

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

        cmbHabitaciones2.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cmbHabitaciones2.setFocusable(false);

        btnAsignar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnAsignar.setForeground(new java.awt.Color(0, 102, 204));
        btnAsignar.setText("→");
        btnAsignar.setFocusable(false);
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        btnDesasignar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnDesasignar.setForeground(new java.awt.Color(255, 102, 102));
        btnDesasignar.setText("←");
        btnDesasignar.setFocusable(false);
        btnDesasignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesasignarActionPerformed(evt);
            }
        });

        cmbHabitaciones.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        cmbHabitaciones.setFocusable(false);

        lblDisponibles.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblDisponibles.setText("Disponibles");

        lblAsignadas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblAsignadas.setText("Asignadas");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Asignar Habitación");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDisponibles)
                                .addGap(18, 18, 18)
                                .addComponent(btnAsignar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDesasignar)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbHabitaciones2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(lblAsignadas)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAsignadas, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDisponibles, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbHabitaciones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cmbHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAsignar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDesasignar)
                        .addContainerGap())))
        );

        lblEstadoDni.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblEstadoDni.setForeground(new java.awt.Color(0, 153, 51));
        lblEstadoDni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelEscritorioLayout = new javax.swing.GroupLayout(panelEscritorio);
        panelEscritorio.setLayout(panelEscritorioLayout);
        panelEscritorioLayout.setHorizontalGroup(
            panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEscritorioLayout.createSequentialGroup()
                .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(btnCancelar)
                        .addGap(42, 42, 42)
                        .addComponent(btnGuardar))
                    .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEscritorioLayout.createSequentialGroup()
                                .addComponent(txtCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCalcular))
                            .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelEscritorioLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblEstadoDni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDni, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))))))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(lblCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEscritorioLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(lblNumeroPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(cmbPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(4, 4, 4)
                .addComponent(lblEstadoDni, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroPersonas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCalculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCalculo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalcular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap())
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
            .addComponent(panelEscritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (calculado) {
            try {
                inicialiarReserva();

                if (reservaControlador.insertarReservas(reserva) == 1) {

                    if (asignarHabitaciones() == 1) {
                        JOptionPane.showMessageDialog(this, "Reserva registrada con éxito");
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Reserva no registrada, Cliente " + txtDni.getText() + " no ha sido registrado");
                }
            } catch (java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese el DNI del cliente correctamente");
            } catch (SQLException ex) {
                Logger.getLogger(MenuReserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor calcule el total");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        if (validar()) {
            if (cmbHabitaciones2.getItemCount() > 0) {
                try {
                    inicialiarReserva();
                    CalculoReserva cr = new CalculoReserva(null, true, reserva, cmbHabitaciones2.getItemCount());
                    txtCalculo.setText("$" + cr.getCalculo());
                    calculado = true;
                    cr.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(MenuReserva.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay habitaciones asignadas para calcular el total");
            }
        }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed

        if (cmbHabitaciones.getItemCount() > 0) {
            cmbHabitaciones2.addItem(cmbHabitaciones.getSelectedItem().toString());
            cmbHabitaciones.removeItem(cmbHabitaciones.getSelectedItem());
        } else {
            JOptionPane.showMessageDialog(this, "No hay habitaciones disponibles en este Hotel");
        }
    }//GEN-LAST:event_btnAsignarActionPerformed

    private void btnDesasignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesasignarActionPerformed
        if (cmbHabitaciones2.getItemCount() > 0) {
            cmbHabitaciones.addItem(cmbHabitaciones2.getSelectedItem().toString());
            cmbHabitaciones2.removeItem(cmbHabitaciones2.getSelectedItem());
        } else {
            JOptionPane.showMessageDialog(this, "No hay habitaciones asignadas");
        }
    }//GEN-LAST:event_btnDesasignarActionPerformed

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased

        validador.validarTamanio(txtDni);

        if (buscarCliente() == 1) {
            lblEstadoDni.setForeground(new Color(0, 153, 51));
            lblEstadoDni.setText("Cliente registrado");
        } else {
            lblEstadoDni.setForeground(Color.red);
            lblEstadoDni.setText("DNI no encontrado");
        }

        if (!validador.validarVacio(txtDni)) {
            lblEstadoDni.setText("");
        }

    }//GEN-LAST:event_txtDniKeyReleased

    public void inicialiarReserva() throws SQLException {
        ResultSet res = reservaControlador.selectSequence(); // Obtiene el valor del autoincremental para la llave primaria
        ArrayList<String> array = new ArrayList<>();
        while (res.next()) {
            reserva.setResId(Integer.parseInt(res.getObject("NEXTVAL").toString()));
        }
        reserva.setCliDni(Integer.parseInt(txtDni.getText()));
        reserva.setResPersonas(Integer.parseInt(cmbPersonas.getSelectedItem().toString()));
        reserva.setResFechaInicio(dateInicio.getDate());
        reserva.setResFechaFin(dateFin.getDate());
        reserva.setHotId(hotel.getHotId());
        reserva.setRestotal(Float.parseFloat(txtCalculo.getText().replace("$", "")));
        reservaControlador = new Datos_Reserva(this.conexion);
    }

    public boolean validar() {
        if (validador.validarVacio(txtDni)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this,"El campo de texto DNI está vacío");
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
            java.util.logging.Logger.getLogger(MenuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuReserva dialog = new MenuReserva(new javax.swing.JFrame(), true, null, null);
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
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesasignar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbHabitaciones;
    private javax.swing.JComboBox<String> cmbHabitaciones2;
    private javax.swing.JComboBox<String> cmbPersonas;
    private com.toedter.calendar.JDateChooser dateFin;
    private com.toedter.calendar.JDateChooser dateInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAsignadas;
    private javax.swing.JLabel lblCalculo;
    private javax.swing.JLabel lblDisponibles;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblEstadoDni;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblNumeroPersonas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JPanel panelEscritorio;
    private javax.swing.JPanel panelTitulo;
    private javax.swing.JTextField txtCalculo;
    private javax.swing.JTextField txtDni;
    // End of variables declaration//GEN-END:variables
}
