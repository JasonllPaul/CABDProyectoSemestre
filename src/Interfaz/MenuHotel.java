/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Datos.*;
import Modelo.Modelo_Empleado;
import Modelo.Modelo_Hotel;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JasonllPaul-PC
 */
public class MenuHotel extends javax.swing.JDialog {

    private Datos_Departamento departamento;
    private Datos_Ciudad ciudad;
    private Datos_Categoria categoria;
    private Datos_Empleado empleado;
    private Datos_Hotel hotel;

    /**
     * Creates new form MenuHotel
     *
     * @param parent Ventana padre de donde es cargado
     * @param modal ventana modal o no
     */
    public MenuHotel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        inicializarAtributos();
    }

    private void inicializarAtributos() {
        departamento = new Datos_Departamento();
        ciudad = new Datos_Ciudad();
        categoria = new Datos_Categoria();
        empleado = new Datos_Empleado();
        hotel = new Datos_Hotel();
        llenarTablaTodosHoteles();
        llenarDni(cmbDni);
        llenarDni(cmbDni1);
        llenarEmpleadoNombre(lblEmpleadoNombre, cmbDni);
        llenarEmpleadoNombre(lblEmpleadoNombre1, cmbDni1);
        llenarDepartamento(cmbDepartamento);
        llenarDepartamento(cmbDepartamento1);
        llenarCategoria(cmbCategoria);
        llenarCategoria(cmbCategoria1);
    }

    public void llenarTablaTodosHoteles() {
        ResultSet resTodos = hotel.consultarTodosHoteles();
        try {
            tblInformacion.setModel(ConstruirModeloDeDatos(resTodos));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Llena el combobox con los departamentos que estan registrados en la base
     * de datos
     *
     * @param cmb JComboBox que se va a inicializar
     */
    public void llenarDepartamento(javax.swing.JComboBox cmb) {
        ArrayList departamentos = getValoresTabla(departamento, "DEPNOMBRE");

        for (int i = 0; i < departamentos.size(); i++) {
            cmb.addItem(departamentos.get(i).toString());
        }
        llenarCiudad(cmbCiudad, cmbDepartamento);
        llenarCiudad(cmbCiudad1, cmbDepartamento1);
    }

    public void llenarDni(javax.swing.JComboBox cmb) {
        ArrayList empleados = getValoresTabla(empleado, "EMPDNI");

        for (int i = 0; i < empleados.size(); i++) {
            cmb.addItem(empleados.get(i).toString());
        }
    }

    public void llenarEmpleadoNombre(javax.swing.JLabel label, javax.swing.JComboBox cmb) {
        String nombre = getValoresTablaEmpleado(cmb.getSelectedIndex() + 1, "EMPNOMBRE").get(0).toString();
        label.setText(nombre);
    }

    public void llenarCiudad(javax.swing.JComboBox cmb, javax.swing.JComboBox cmbDepto) {
        cmb.removeAllItems();
        int departamentoSeleccionado = cmbDepto.getSelectedIndex() + 1;
        ArrayList ciudades = getValoresTablaDepartamentos("CIUNOMBRE", departamentoSeleccionado);

        for (int i = 0; i < ciudades.size(); i++) {
            cmb.addItem(ciudades.get(i).toString());
        }

    }

    public void llenarCategoria(javax.swing.JComboBox cmb) {
        ArrayList categorias = getValoresTabla(categoria, "CATESTRELLA");

        for (int i = 0; i < categorias.size(); i++) {
            cmb.addItem(categorias.get(i).toString());
        }
    }

    public ArrayList getValoresTabla(Datos tabla, String nombreColumna) {
        ResultSet resTodos = tabla.consultarTodo();
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

    public ArrayList getValoresTablaEmpleado(int id, String nombreColumna) {
        Modelo_Empleado e = new Modelo_Empleado(id, id, nombreColumna, nombreColumna, null, null);
        ResultSet resTodos = empleado.buscarRegistroEmpleados(e);
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

    public ArrayList getValoresTablaDepartamentos(String nombreColumna, int id) {
        ResultSet resTodos = ciudad.consultarCiudadesDepartamento(id);
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

    public int registrarHotel() {
        int idCiudad = Integer.parseInt(getValoresTablaDepartamentos("CIUID", cmbDepartamento.getSelectedIndex() + 1).get(0).toString());
        System.out.println("CIUID: " + idCiudad);
        Modelo_Hotel mh = new Modelo_Hotel(Integer.parseInt(txtIdentificacion.getText()), idCiudad, Integer.parseInt(cmbDni.getSelectedItem().toString()), Integer.parseInt(cmbCategoria.getSelectedItem().toString()), txtNombre.getText(), txtDireccion.getText());
        return hotel.insertarHoteles(mh);
    }
    
     public int actualizarHotel() {
        int idCiudad = Integer.parseInt(getValoresTablaDepartamentos("CIUID", cmbDepartamento1.getSelectedIndex() + 1).get(0).toString());
        Modelo_Hotel mh = new Modelo_Hotel(Integer.parseInt(txtIdentificacion1.getText()), idCiudad, Integer.parseInt(cmbDni1.getSelectedItem().toString()), Integer.parseInt(cmbCategoria1.getSelectedItem().toString()), txtNombre1.getText(), txtDireccion1.getText());
        return hotel.actualizarRegistroHoteles(mh);
    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        txtIdentificacion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        cmbDni = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbCategoria = new javax.swing.JComboBox<>();
        cmbCiudad = new javax.swing.JComboBox<>();
        cmbDepartamento = new javax.swing.JComboBox<>();
        txtCategoria = new javax.swing.JLabel();
        txtEmpleadoDni = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JLabel();
        txtDepartamento = new javax.swing.JLabel();
        lblEmpleadoNombre = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbDni1 = new javax.swing.JComboBox<>();
        cmbCategoria1 = new javax.swing.JComboBox<>();
        cmbDepartamento1 = new javax.swing.JComboBox<>();
        btnAceptar1 = new javax.swing.JButton();
        cmbCiudad1 = new javax.swing.JComboBox<>();
        txtDireccion1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtIdentificacion1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCiudad1 = new javax.swing.JLabel();
        txtDepartamento1 = new javax.swing.JLabel();
        txtCategoria1 = new javax.swing.JLabel();
        txtEmpleadoDni1 = new javax.swing.JLabel();
        lblEmpleadoNombre1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        txtIdentificacion2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblInformacion = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane4MouseClicked(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        txtIdentificacion.setToolTipText("Ingrese una identificación");

        jLabel2.setText("Identificación:");

        jLabel3.setText("Nombre:");

        cmbDni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDniActionPerformed(evt);
            }
        });

        jLabel4.setText("Dirección");

        cmbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriaActionPerformed(evt);
            }
        });

        cmbDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDepartamentoActionPerformed(evt);
            }
        });

        txtCategoria.setText("Categoría (en estrellas)");

        txtEmpleadoDni.setText("DNI Empleado a cargo:");

        txtCiudad.setText("Ciudad:");

        txtDepartamento.setText("Departamento:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIdentificacion)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(197, 197, 197)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpleadoDni, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmbDni, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbCiudad, javax.swing.GroupLayout.Alignment.LEADING, 0, 235, Short.MAX_VALUE)
                            .addComponent(cmbDepartamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(lblEmpleadoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtEmpleadoDni))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblEmpleadoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCategoria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtDepartamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCiudad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane4.addTab("CREAR", jPanel1);

        cmbDepartamento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDepartamento1ActionPerformed(evt);
            }
        });

        btnAceptar1.setText("Aceptar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Dirección");

        jLabel6.setText("Nombre:");

        txtIdentificacion1.setToolTipText("Ingrese una identificación");
        txtIdentificacion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdentificacion1KeyPressed(evt);
            }
        });

        jLabel7.setText("Identificación:");

        txtCiudad1.setText("Ciudad:");

        txtDepartamento1.setText("Departamento:");

        txtCategoria1.setText("Categoría (en estrellas)");

        txtEmpleadoDni1.setText("DNI Empleado a cargo:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtIdentificacion1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 182, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpleadoDni1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartamento1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCiudad1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbCiudad1, javax.swing.GroupLayout.Alignment.LEADING, 0, 230, Short.MAX_VALUE)
                                    .addComponent(cmbDepartamento1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCategoria1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbDni1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmpleadoNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)))))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmpleadoDni1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdentificacion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbDni1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblEmpleadoNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCategoria1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDepartamento1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDepartamento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtCiudad1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCiudad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );

        jTabbedPane4.addTab("ACTUALIZAR", jPanel2);

        jLabel8.setText("Identificación:");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtIdentificacion2.setToolTipText("Ingrese una identificación");
        txtIdentificacion2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdentificacion2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                        .addGap(454, 454, 454))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtIdentificacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdentificacion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("ELIMINAR", jPanel3);

        tblInformacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblInformacion);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MENÚ HOTEL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1059, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(475, 475, 475)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDepartamentoActionPerformed
        llenarCiudad(cmbCiudad, cmbDepartamento);
    }//GEN-LAST:event_cmbDepartamentoActionPerformed

    private void cmbDepartamento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDepartamento1ActionPerformed
        llenarCiudad(cmbCiudad1, cmbDepartamento1);
    }//GEN-LAST:event_cmbDepartamento1ActionPerformed

    private void cmbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriaActionPerformed
        llenarTablaTodosHoteles();
    }//GEN-LAST:event_cmbCategoriaActionPerformed

    private void cmbDniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDniActionPerformed
        llenarEmpleadoNombre(lblEmpleadoNombre, cmbDni);
    }//GEN-LAST:event_cmbDniActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        switch (registrarHotel()) {
            case 0:
                JOptionPane.showMessageDialog(rootPane, "Hotel NO registrado");
                break;
            case 1:
                JOptionPane.showMessageDialog(rootPane, "Hotel registrado con éxito");
                llenarTablaTodosHoteles();
                break;
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtIdentificacion1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacion1KeyPressed

        String txt = txtIdentificacion1.getText();

        try {
            if (txt.length() > 0) {
                Modelo_Hotel hot = new Modelo_Hotel(Integer.parseInt(txtIdentificacion1.getText()));
                ResultSet resTodos = hotel.buscarRegistroHoteles(hot);
                tblInformacion.setModel(ConstruirModeloDeDatos(resTodos));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Modelo_Hotel hot = new Modelo_Hotel(Integer.parseInt(txtIdentificacion1.getText()));
                ResultSet resTodos = hotel.buscarRegistroHoteles(hot);
                while (resTodos.next()) {
                    txtNombre1.setText(resTodos.getObject("HOTNOMBRE").toString());
                    txtDireccion1.setText(resTodos.getObject("HOTDIRECCION").toString());
                    cmbCategoria1.removeAllItems();
                    cmbDni1.removeAllItems();
                    cmbCiudad1.removeAllItems();
                    cmbCategoria1.addItem(resTodos.getObject("CATID").toString());
                    cmbDni1.addItem(resTodos.getObject("EMPDNI").toString());
                    cmbCiudad1.addItem(resTodos.getObject("CIUID").toString());
                    txtDireccion1.setText(resTodos.getObject("HOTDIRECCION").toString());

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }//GEN-LAST:event_txtIdentificacion1KeyPressed

    private void jTabbedPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane4MouseClicked
        llenarTablaTodosHoteles();
    }//GEN-LAST:event_jTabbedPane4MouseClicked

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
         switch (actualizarHotel()) {
            case 0:
                JOptionPane.showMessageDialog(rootPane, "Hotel NO actualizado");
                break;
            case 1:
                JOptionPane.showMessageDialog(rootPane, "Hotel actualizado con éxito");
                llenarTablaTodosHoteles();
                break;
        }
    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void txtIdentificacion2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificacion2KeyPressed
    String txt = txtIdentificacion2.getText();

        try {
            if (txt.length() > 0) {
                Modelo_Hotel hot = new Modelo_Hotel(Integer.parseInt(txtIdentificacion2.getText()));
                ResultSet resTodos = hotel.buscarRegistroHoteles(hot);
                tblInformacion.setModel(ConstruirModeloDeDatos(resTodos));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_txtIdentificacion2KeyPressed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int decision = JOptionPane.showConfirmDialog(rootPane,"¿Está seguro?");
        switch(decision){
            case 0:
                hotel.borrarHotel(Integer.parseInt(txtIdentificacion2.getText()));
                JOptionPane.showMessageDialog(rootPane,"Hotel eliminado");
                break;
        }
        llenarTablaTodosHoteles();
    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(MenuHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuHotel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuHotel dialog = new MenuHotel(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbCategoria;
    private javax.swing.JComboBox<String> cmbCategoria1;
    private javax.swing.JComboBox<String> cmbCiudad;
    private javax.swing.JComboBox<String> cmbCiudad1;
    private javax.swing.JComboBox<String> cmbDepartamento;
    private javax.swing.JComboBox<String> cmbDepartamento1;
    private javax.swing.JComboBox<String> cmbDni;
    private javax.swing.JComboBox<String> cmbDni1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblEmpleadoNombre;
    private javax.swing.JLabel lblEmpleadoNombre1;
    private javax.swing.JTable tblInformacion;
    private javax.swing.JLabel txtCategoria;
    private javax.swing.JLabel txtCategoria1;
    private javax.swing.JLabel txtCiudad;
    private javax.swing.JLabel txtCiudad1;
    private javax.swing.JLabel txtDepartamento;
    private javax.swing.JLabel txtDepartamento1;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccion1;
    private javax.swing.JLabel txtEmpleadoDni;
    private javax.swing.JLabel txtEmpleadoDni1;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtIdentificacion1;
    private javax.swing.JTextField txtIdentificacion2;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombre1;
    // End of variables declaration//GEN-END:variables
}
