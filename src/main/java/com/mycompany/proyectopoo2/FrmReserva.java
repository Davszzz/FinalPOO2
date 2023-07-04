/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectopoo2;

import Controller.ReservaController;
import Controller.habitacionController;
import Controller.hcatController;
import Controller.tipo_usuController;
import Controller.usuarioController;
import Model.Reserva;
import Model.habitacion;
import Model.hcat;
import Model.tipo_usu;
import Model.usuario;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class FrmReserva extends javax.swing.JFrame {
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private ReservaController rController = new ReservaController();
    private usuarioController uController = new usuarioController();
    private tipo_usuController tController = new tipo_usuController();
    private habitacionController hController = new habitacionController();
    private hcatController cController = new hcatController();

    
    /**
     * Creates new form FrmReserva
     */
    public FrmReserva() {
        initComponents();
        llenaTabla();
        llenarCombo();
        LlenarCombo2();
        llenaLista();
        llenaLista2();
        Codigo();
        cerrar();
                
    }
    
    public void cerrar()
    {
        try
        {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter()
            {
                public void windowClosing(WindowEvent e)
                {
                    confirmarsalida();
                }
            });
            this.setVisible(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void confirmarsalida()
    {
        int valor = JOptionPane.showConfirmDialog(this, "¿Esta seguro de regresar al inicio de sesion? ", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(valor==JOptionPane.YES_OPTION)
        {
            JOptionPane.showMessageDialog(null, "Sera redireccionado al Inicio de sesion", "Redireccionamiento",JOptionPane.INFORMATION_MESSAGE);
            InicioSesion is = new InicioSesion();
            is.setVisible(true);
            this.dispose();
        }
    }

    
    public void llenaTabla()
    {
        dtm.addColumn("CODIGO");
        dtm.addColumn("CATEGORIA");
        dtm.addColumn("NUMERO");
        dtm.addColumn("ESTADO");
        dtm.addColumn("DESCRIPCION");
        this.tbHabitaciones.setModel(dtm);
        
        
        dtm2.addColumn("CODIGO");
        dtm2.addColumn("TIPO");
        dtm2.addColumn("USUARIO");
        dtm2.addColumn("CORREO");
        this.tbUsuarios.setModel(dtm2);
        
        
    }
    
    public void Codigo()
    {
        List<Reserva> lst = rController.ReservaController();
        
        this.txtidReserva.setText(Integer.toString(lst.size()+1));
        txtidReserva.setEnabled(false);
    
    }
    
    public void llenaLista()
    {
        String Estado;
        List<habitacion> lst = hController.getAllRooms();
        dtm.setRowCount(0);
        for(int i=0;i<lst.size();i++)
        {
            if(lst.get(i).isEstado()==true)
            {
                Estado="DISPONIBLE";
            }
            else
            {
                Estado="OCUPADO";
            }
            if(lst.get(i).isEstado()==true && lst.get(i).getCodc()==this.cmbEstado.getSelectedIndex())
            {
                Object[] vhb=new Object[5];
                vhb[0] = lst.get(i).getCod();
                vhb[1] = lst.get(i).getCodc();
                vhb[2] = lst.get(i).getNumero();
                vhb[3] = Estado;
                vhb[4] = lst.get(i).getDescripcion(); 
                
                dtm.addRow(vhb);
            }
            else if(lst.get(i).isEstado()==true && this.cmbEstado.getSelectedIndex()==0)
            {
                Object[] vhb=new Object[5];
                vhb[0] = lst.get(i).getCod();
                vhb[1] = lst.get(i).getCodc();
                vhb[2] = lst.get(i).getNumero();
                vhb[3] = Estado;
                vhb[4] = lst.get(i).getDescripcion(); 
                
                dtm.addRow(vhb);
                       
            }

            }
            
        this.tbHabitaciones.setModel(dtm);
    }
    
    public void llenaLista2()
    {
        List<usuario> lst = uController.getAllUsuController();
        dtm2.setRowCount(0);
        for(int i=0;i<lst.size();i++)
        {
            if(lst.get(i).getTipo()==this.ctipo1.getSelectedIndex())
            {
                Object[] vec=new Object[4];
                vec[0] = lst.get(i).getCod();
                vec[1]= tController.Buscar(lst.get(i).getTipo()).getTipo();
                vec[2] = lst.get(i).getUsuario();
                vec[3] = lst.get(i).getCorreo();
                
                dtm2.addRow(vec);
            }
            else if(this.ctipo1.getSelectedIndex()==0)
            {
                Object[] vec=new Object[4];
                vec[0] = lst.get(i).getCod();
                vec[1]= tController.Buscar(lst.get(i).getTipo()).getTipo();
                vec[2] = lst.get(i).getUsuario();
                vec[3] = lst.get(i).getCorreo();
                
                dtm2.addRow(vec);
            }
        }
        this.tbUsuarios.setModel(dtm2);
    }
    public void Setteo()
    {
        this.txtidReserva.setText("");
        this.txtidUsuario.setText("");
        this.txtidHabitacion.setText("");
        this.txtfechaInicial.setText("");
        this.txtFechaFinal.setText("");
        this.txtfechaActual.setText("");
        this.txtDiasparaPago.setText("");
        this.txtdiasEstancia.setText("");
        this.txtCoste.setText("");
        this.txtnombreUsuario.setText("");
        this.txtNumHabitacion.setText("");
        
    }
    
    public void llenarCombo()
    {
        List<tipo_usu> lst = tController.getAllCategorias();
        
        for(tipo_usu item:lst)
        {
            this.ctipo1.addItem("[" + item.getId()+ "] " + item.getTipo());
        }
    }
     private void LlenarCombo2()
    {
        List<hcat> lst = cController.getAllCategorias();
        for(hcat item:lst)
        {
            this.cmbEstado.addItem("[" + item.getCod()+ "] " + item.getDescripcion());
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

        jPanel1 = new javax.swing.JPanel();
        txtidReserva = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtfechaActual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFechaFinal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmbEstadoReserva = new javax.swing.JComboBox<>();
        labelnumero = new javax.swing.JLabel();
        labelprecio = new javax.swing.JLabel();
        txtidHabitacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtidUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNumHabitacion = new javax.swing.JTextField();
        txtnombreUsuario = new javax.swing.JTextField();
        txtdiasEstancia = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtfechaInicial = new javax.swing.JTextField();
        labelnumero1 = new javax.swing.JLabel();
        labelnumero2 = new javax.swing.JLabel();
        labelnumero3 = new javax.swing.JLabel();
        txtDiasparaPago = new javax.swing.JTextField();
        btncalcular = new javax.swing.JButton();
        txtCoste = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbHabitaciones = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        cmbEstado = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        btnsalir1 = new javax.swing.JButton();
        ctipo1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de Reservas"));

        txtidReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidReservaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Fecha Check-Out:");

        txtfechaActual.setText("00-00-00");
        txtfechaActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActualActionPerformed(evt);
            }
        });
        txtfechaActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfechaActualKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Fecha Check-In:");

        txtFechaFinal.setText("00-00-00");
        txtFechaFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFinalActionPerformed(evt);
            }
        });
        txtFechaFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaFinalKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Estancia:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Coste:");

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Id:");

        cmbEstadoReserva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMPLETADA", "EN PROCESO", "CANCELADA" }));

        labelnumero.setText("*");

        labelprecio.setText("*");

        txtidHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidHabitacionActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Id Usuario:");

        txtidUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidUsuarioActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Id Habitacion:");

        txtNumHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumHabitacionActionPerformed(evt);
            }
        });

        txtnombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreUsuarioActionPerformed(evt);
            }
        });

        txtdiasEstancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiasEstanciaActionPerformed(evt);
            }
        });
        txtdiasEstancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdiasEstanciaKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Estado de Reserva:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Fecha Actual:");

        txtfechaInicial.setText("00-00-00");
        txtfechaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaInicialActionPerformed(evt);
            }
        });
        txtfechaInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtfechaInicialKeyReleased(evt);
            }
        });

        labelnumero1.setText("*");

        labelnumero2.setText("*");

        labelnumero3.setText("*");

        txtDiasparaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasparaPagoActionPerformed(evt);
            }
        });
        txtDiasparaPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasparaPagoKeyReleased(evt);
            }
        });

        btncalcular.setText("Calcular");
        btncalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcularActionPerformed(evt);
            }
        });

        txtCoste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCosteActionPerformed(evt);
            }
        });
        txtCoste.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCosteKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Tiempo de Pago:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtidReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtidHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumHabitacion))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtidUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnombreUsuario))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtfechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtdiasEstancia, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfechaInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btncalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbEstadoReserva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDiasparaPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCoste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelnumero1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelnumero2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(labelnumero3)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtidReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtidHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNumHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtfechaActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelnumero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtfechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelprecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(labelnumero1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtdiasEstancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelnumero2))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEstadoReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelnumero3)
                    .addComponent(txtDiasparaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCoste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Reservas");

        jPanel2.setBackground(new java.awt.Color(204, 255, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Habitaciones"));

        tbHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHabitacionesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbHabitaciones);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Usuarios"));

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbUsuarios);

        btnsalir1.setText("Salir");
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });

        ctipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        ctipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctipo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ctipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnsalir1))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir1)
                    .addComponent(ctipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtidReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidReservaActionPerformed

    private void txtfechaActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaActualActionPerformed

    private void txtfechaActualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaActualKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtfechaActualKeyReleased

    private void txtFechaFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFinalActionPerformed

    private void txtFechaFinalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFinalKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtFechaFinalKeyReleased

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
         Reserva objR = new Reserva();/*Crear el objeto*/
       objR.setIdReserva(Integer.parseInt(this.txtidReserva.getText()));
       objR.setIdUsuario(Integer.parseInt(this.txtidUsuario.getText()));
       objR.setIdHabitacion(Integer.parseInt(this.txtidHabitacion.getText()));
       objR.setFechai(this.txtfechaInicial.getText()); 
       objR.setFechaf(this.txtFechaFinal.getText());
       objR.setFechap(this.txtfechaActual.getText()); 
       objR.setDiasr(Integer.parseInt(this.txtDiasparaPago.getText()));
       objR.setEstancia(Integer.parseInt(this.txtdiasEstancia.getText())); 
       objR.setCoste(Double.parseDouble(this.txtCoste.getText()));
       objR.setEstadoR(this.cmbEstadoReserva.getSelectedItem().toString()); 
       rController.addReserva(objR);/*Pasa el objeto con los datos escritos en el formulario*/
       JOptionPane.showMessageDialog(this, "Registro grabado satisfactoriamente!");
       llenaLista();
       llenaLista2();
       
       hController.EditarEstadoF(Integer.parseInt(this.txtidHabitacion.getText())); 
       Setteo();
        
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
       Setteo();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void tbHabitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHabitacionesMouseClicked
        // TODO add your handling code here:
        this.txtidHabitacion.setText(dtm.getValueAt(this.tbHabitaciones.getSelectedRow(), 0).toString());
        this.txtNumHabitacion.setText(dtm.getValueAt(this.tbHabitaciones.getSelectedRow(), 2).toString());
        
        
    }//GEN-LAST:event_tbHabitacionesMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtidHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidHabitacionActionPerformed

    private void txtidUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidUsuarioActionPerformed

    private void txtNumHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumHabitacionActionPerformed

    private void txtnombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreUsuarioActionPerformed

    private void txtdiasEstanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiasEstanciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiasEstanciaActionPerformed

    private void txtdiasEstanciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiasEstanciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiasEstanciaKeyReleased

    private void txtfechaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaInicialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaInicialActionPerformed

    private void txtfechaInicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechaInicialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechaInicialKeyReleased

    private void tbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseClicked

        this.txtidUsuario.setText(dtm2.getValueAt(this.tbUsuarios.getSelectedRow(), 0).toString());
        this.txtnombreUsuario.setText(dtm2.getValueAt(this.tbUsuarios.getSelectedRow(),2).toString());
    }//GEN-LAST:event_tbUsuariosMouseClicked

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void txtDiasparaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasparaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasparaPagoActionPerformed

    private void txtDiasparaPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasparaPagoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasparaPagoKeyReleased

    private void btncalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcularActionPerformed
        // TODO add your handling code here:
        String fecha1 = txtfechaInicial.getText();
        String fecha2 = txtFechaFinal.getText();
        String fecha3 = txtfechaActual.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha11 = LocalDate.parse(fecha1, formatter);
        LocalDate fecha22 = LocalDate.parse(fecha2, formatter);
        LocalDate fecha33 = LocalDate.parse(fecha3, formatter);
        
        long diasEntreFechas = ChronoUnit.DAYS.between(fecha11, fecha22);
       String diasEntreFechasString = String.valueOf(diasEntreFechas);
       long DiasparaPago = ChronoUnit.DAYS.between(fecha33, fecha11);
       String DiasparaPagoString = String.valueOf(DiasparaPago);
       
        
       
        txtdiasEstancia.setText(diasEntreFechasString);
        txtDiasparaPago.setText(DiasparaPagoString);
        
        habitacion habtemp = hController.BuscarCod(Integer.parseInt(txtidHabitacion.getText().toString()));
        
        hcat cat = cController.Buscar(habtemp.getCodc());
        
        this.txtCoste.setText("" + Integer.parseInt(diasEntreFechasString) * cat.getPrecio());

    }//GEN-LAST:event_btncalcularActionPerformed

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        // TODO add your handling code here:
        llenaLista();
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void ctipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctipo1ActionPerformed
        // TODO add your handling code here:
        llenaLista2();
    }//GEN-LAST:event_ctipo1ActionPerformed

    private void txtCosteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCosteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCosteActionPerformed

    private void txtCosteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCosteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCosteKeyReleased

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
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btncalcular;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbEstadoReserva;
    private javax.swing.JComboBox<String> ctipo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelnumero;
    private javax.swing.JLabel labelnumero1;
    private javax.swing.JLabel labelnumero2;
    private javax.swing.JLabel labelnumero3;
    private javax.swing.JLabel labelprecio;
    private javax.swing.JTable tbHabitaciones;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtCoste;
    private javax.swing.JTextField txtDiasparaPago;
    private javax.swing.JTextField txtFechaFinal;
    private javax.swing.JTextField txtNumHabitacion;
    private javax.swing.JTextField txtdiasEstancia;
    private javax.swing.JTextField txtfechaActual;
    private javax.swing.JTextField txtfechaInicial;
    private javax.swing.JTextField txtidHabitacion;
    private javax.swing.JTextField txtidReserva;
    private javax.swing.JTextField txtidUsuario;
    private javax.swing.JTextField txtnombreUsuario;
    // End of variables declaration//GEN-END:variables
}
