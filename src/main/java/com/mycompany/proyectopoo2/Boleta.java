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
import Model.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Controller.boletaController;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author Davs
 */
public class Boleta extends javax.swing.JFrame {
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private ReservaController rController = new ReservaController();
    private usuarioController uController = new usuarioController();
    private tipo_usuController tController = new tipo_usuController();
    private habitacionController hController = new habitacionController();
    private hcatController cController = new hcatController();
    private boletaController bController = new boletaController();
    

    /**
     * Creates new form Boleta
     */
    public Boleta() {
        initComponents();
                
        llenaTabla();
        llenaLista();
        ObtenerUsuario();
        llenaLista2();
    }
    public void generatePDF(String filename) { 
            try {
                // Crear un nuevo documento PDF
                PdfWriter writer = new PdfWriter(filename);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf) {};

                // Agregar contenido al documento
                boleta bol = new boleta();
                bol.setIdPago(Integer.parseInt(this.txtid.getText()));
                bol.setIdReserva(Integer.parseInt(this.txtidres.getText()));
                bol.setIdUsuario(Integer.parseInt(this.txtidusu.getText()));
                bol.setIdHabitacion(Integer.parseInt(this.txtidHab.getText()));
                bol.setTipo_comprobante(this.combopago.getSelectedItem().toString());
                bol.setNum_comprobante(this.txtcomprobante.getText());
                bol.setIgv(Double.parseDouble(this.igv.getText()));
                bol.setTotal(Double.parseDouble(this.txttotal.getText()));
                bol.setFecha(this.txtfecha.getText());

                bController.addBoleta(bol);
        
                rController.Completar(Integer.parseInt(this.txtidres.getText()));
                
                //contenido dentro del PDF
                document.add(new Paragraph("Numero de Boleta : " +bol.getIdPago()
                                          +"Numero de Reserva : " +bol.getIdReserva()
                                          +"Numero de Usuario : " +bol.getIdUsuario()
                                          +"Numero de Habitacion : " +bol.getIdHabitacion()
                                          +"Tipo de comprobante : " +bol.getTipo_comprobante()
                                          +"Numero de comprobante : " +bol.getNum_comprobante()
                                          +"IGV : " +bol.getIgv()
                                          +"Monto de total : " +bol.getTotal()
                                          +"Fecha de emision : " +bol.getFecha()));

                // Cerrar el documento
                document.close();
                System.out.println("Se ha generado el archivo PDF correctamente: " + filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public void generarPDF2(String filename)
    {
        try {
            // Crear un nuevo archivo PDF
            File file = new File("boleta.pdf");
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A6);

            // Crear fuente personalizada
            PdfFont font = PdfFontFactory.createFont("arial.ttf", true);

            // Encabezado
            Paragraph header = new Paragraph("Boleta de reserva")
                    .setFont(font)
                    .setBold()
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(header);
            
                        // Ruta de la imagen
            String imagePath = "logo.png";

            // Cargar la imagen
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image image = new Image(imageData);

            float logoWidth = 100;  // Ancho del logo en puntos
            float logoHeight = 38; // Altura del logo en puntos
            image.setWidth(logoWidth);
            image.setHeight(logoHeight);

            // Obtener el ancho del documento
            float docWidth = pdf.getDefaultPageSize().getWidth();

            // Colocar la imagen en la parte superior derecha
            float xPosition = docWidth - logoWidth - 20; // 20 puntos de margen desde el borde derecho
            float yPosition = pdf.getDefaultPageSize().getTop() - logoHeight - 10; // 20 puntos de margen desde el borde superior
            image.setFixedPosition(xPosition, yPosition);

            // Agregar la imagen al documento
            document.add(image);

            // Agregar la imagen al documento
            document.add(image);


            // Información del cliente
            Paragraph customerInfo = new Paragraph()
                    .add(new Text("Cliente: Juan Pérez\n"))
                    .add(new Text("Dirección: Calle 123, Ciudad\n"))
                    .add(new Text("Fecha: 4 de Julio, 2023\n"))
                    .setFont(font)
                    .setFontSize(12);
            document.add(customerInfo);

            // Detalles de los productos
            Paragraph productsHeader = new Paragraph("Detalles de la reserva")
                    .setFont(font)
                    .setBold()
                    .setFontSize(12);
            document.add(productsHeader);

            // Productos
            Paragraph product1 = new Paragraph("Producto 1: $10")
                    .setFont(font)
                    .setFontSize(12);
            Paragraph product2 = new Paragraph("Producto 2: $20")
                    .setFont(font)
                    .setFontSize(12);
            document.add(product1);
            document.add(product2);

            // Total
            Paragraph total = new Paragraph("Total: $30")
                    .setFont(font)
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(total);
            
            // Cerrar el documento
            document.close();

            System.out.println("Boleta generada correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ObtenerUsuario()
    {   
        InicioSesion is = new InicioSesion();
        String usuario = is.texto.toString();
        String contraseña = is.contraseña.toString();
        usu.setText(usuario);
    }
    
    
    public void llenaTabla()
    {
        dtm.addColumn("CODIGO");
        dtm.addColumn("IDUSUARIO");
        dtm.addColumn("IDHABITACION");
        dtm.addColumn("FECHA RESERVACION");
        dtm.addColumn("CHECK-IN");
        dtm.addColumn("CHECK-OUT");
        dtm.addColumn("DIAS DISPONIBLES");
        dtm.addColumn("ESTANCIA");
        dtm.addColumn("COSTO");
        dtm.addColumn("ESTADO");
        
        dtm2.addColumn("CODIGO");
        dtm2.addColumn("IDUSUARIO");
        dtm2.addColumn("IDHABITACION");
        dtm2.addColumn("FECHA RESERVACION");
        dtm2.addColumn("CHECK-IN");
        dtm2.addColumn("CHECK-OUT");
        dtm2.addColumn("DIAS DISPONIBLES");
        dtm2.addColumn("ESTANCIA");
        dtm2.addColumn("COSTO");
        dtm2.addColumn("ESTADO");
        
        this.dtmpendientes.setModel(dtm);
        this.dtmreservas.setModel(dtm2);
    }
    
    public void llenaLista()
    {
        SHA256 SHA = new SHA256();
        InicioSesion is = new InicioSesion();
        
        String usuario = is.texto;
        String contraseña = SHA.Char25(is.contraseña);
        
        usuario usuobj = uController.ObjetoLogin(usuario, contraseña);
        
        List<Reserva> lst = rController.ReservaController();
        for(int i=0;i<lst.size();i++)
        {
            if(usuobj.getCod() == lst.get(i).getIdUsuario() && lst.get(i).getEstadoR().equals("EN PROCESO"))
            {
                Object[] vhb=new Object[10];
                vhb[0] = lst.get(i).getIdReserva();
                vhb[1] = lst.get(i).getIdUsuario();
                vhb[2] = lst.get(i).getIdHabitacion();
                vhb[3] = lst.get(i).getFechap();
                vhb[4] = lst.get(i).getFechai(); 
                vhb[5] = lst.get(i).getFechaf();
                vhb[6] = lst.get(i).getDiasr();
                vhb[7] = lst.get(i).getEstancia();
                vhb[8] = lst.get(i).getCoste();
                vhb[9] = lst.get(i).getEstadoR();
                
                dtm.addRow(vhb);
            }
        }            
        this.dtmpendientes.setModel(dtm);
 
    }
    
    public void llenaLista2()
    {
        
        InicioSesion is = new InicioSesion();
        
        SHA256 SHA = new SHA256();
        
        String usuario = is.texto.toString();
        String contraseña = is.contraseña.toString();
        
        String PasCHAR25 = SHA.Char25(contraseña);
        
        usuario usuobj = uController.ObjetoLogin(usuario, PasCHAR25);
        
        List<Reserva> lst = rController.ReservaController();
        dtm2.setRowCount(0);
        for(int i=0;i<lst.size();i++)
        {
             if(usuobj.getCod() == lst.get(i).getIdUsuario() && lst.get(i).getEstadoR().equals(comboEstado.getSelectedItem().toString()))
            {
                Object[] vhb1=new Object[10];
                vhb1[0] = lst.get(i).getIdReserva();
                vhb1[1] = lst.get(i).getIdUsuario();
                vhb1[2] = lst.get(i).getIdHabitacion();
                vhb1[3] = lst.get(i).getFechap();
                vhb1[4] = lst.get(i).getFechai(); 
                vhb1[5] = lst.get(i).getFechaf();
                vhb1[6] = lst.get(i).getDiasr();
                vhb1[7] = lst.get(i).getEstancia();
                vhb1[8] = lst.get(i).getCoste();
                vhb1[9] = lst.get(i).getEstadoR();
                
                dtm2.addRow(vhb1);
            }
            else if(usuobj.getCod() == lst.get(i).getIdUsuario() && comboEstado.getSelectedIndex()==0)
            {
                Object[] vhb1=new Object[10];
                vhb1[0] = lst.get(i).getIdReserva();
                vhb1[1] = lst.get(i).getIdUsuario();
                vhb1[2] = lst.get(i).getIdHabitacion();
                vhb1[3] = lst.get(i).getFechap();
                vhb1[4] = lst.get(i).getFechai(); 
                vhb1[5] = lst.get(i).getFechaf();
                vhb1[6] = lst.get(i).getDiasr();
                vhb1[7] = lst.get(i).getEstancia();
                vhb1[8] = lst.get(i).getCoste();
                vhb1[9] = lst.get(i).getEstadoR();
                
                dtm2.addRow(vhb1);
            }
        }
        this.dtmreservas.setModel(dtm2);   
    }
    
    StringBuilder camposIncorrectos = new StringBuilder();
    private void ValidarBoleta ()
    {
        camposIncorrectos.setLength(0);
        
        if(txtid.getText().isEmpty())
        {
            camposIncorrectos.append("- Codigo\n");
            labelcodigo.setText("Campo obligatorio");
        }
        else
        {
            labelcodigo.setText("✔");
        }
        
        
        if(txtidusu.getText().isEmpty())
        {
            camposIncorrectos.append("- idUsuario\n");
            labelidusu.setText("Campo obligatorio");
        }
        else
        {
            labelidusu.setText("✔");
        }
        
        if(txtidres.getText().isEmpty())
        {
            camposIncorrectos.append("- idReserva\n");
            labelidres.setText("Campo obligatorio");
        }
        else
        {
            labelidres.setText("✔");
        }
        
        if(txtidHab.getText().isEmpty())
        {
            camposIncorrectos.append("- idHab\n");
            labelidHab.setText("Campo obligatorio");
        }
        else
        {
            labelidHab.setText("✔");
        }
        
        if(combopago.getSelectedIndex()==0)
        {
            camposIncorrectos.append("- Tipo de Comprobante\n");
            labelcom.setText("Campo obligatorio");
        }
        else
        {
            labelcom.setText("✔");
        }
        
        if(txtcomprobante.getText().isEmpty())
        {
            camposIncorrectos.append("- Nº de comprobante\n");
            labelncom.setText("Campo obligatorio");
        }
        else
        {
            labelncom.setText("✔");
        }
        
        if(igv.getText().isEmpty())
        {
            camposIncorrectos.append("- IGV\n");
            labeligv.setText("Campo obligatorio");
        }
        else
        {
            labeligv.setText("✔");
        }
        
        if(txttotal.getText().isEmpty())
        {
            camposIncorrectos.append("- Total\n");
            labeltotal.setText("Campo obligatorio");
        }
        else
        {
            labeltotal.setText("✔");
        }
        
        if(txtfecha.getText().isEmpty())
        {
            camposIncorrectos.append("- Fecha\n");
            labelfecha.setText("Campo obligatorio");
        }
        else
        {
            labelfecha.setText("✔");
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
        usu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dtmpendientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtidusu = new javax.swing.JTextField();
        txtidres = new javax.swing.JTextField();
        txtidHab = new javax.swing.JTextField();
        combopago = new javax.swing.JComboBox<>();
        txtcomprobante = new javax.swing.JTextField();
        igv = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dtmreservas = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        labelcodigo = new javax.swing.JLabel();
        labelidusu = new javax.swing.JLabel();
        labelcom = new javax.swing.JLabel();
        labelidres = new javax.swing.JLabel();
        labelidHab = new javax.swing.JLabel();
        labelncom = new javax.swing.JLabel();
        labeligv = new javax.swing.JLabel();
        labeltotal = new javax.swing.JLabel();
        labelfecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bienvenido de vuelta: ");

        usu.setText("*");

        dtmpendientes.setModel(new javax.swing.table.DefaultTableModel(
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
        dtmpendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtmpendientesMouseClicked(evt);
            }
        });
        dtmpendientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtmpendientesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(dtmpendientes);

        jLabel2.setText("Reservas pendientes:");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "COMPLETADA", "EN PROCESO", "CANCELADA" }));
        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
            }
        });

        jLabel3.setText("Estado:");

        jLabel4.setText("PAGO");

        jLabel5.setText("idUsuario:");

        jLabel6.setText("Codigo:");

        jLabel7.setText("idReserva:");

        jLabel8.setText("idHabitacion:");

        jLabel9.setText("Tipo de comprobante:");

        jLabel10.setText("N° Comprobante:");

        jLabel11.setText("IGV:");

        jLabel12.setText("Total:");

        jLabel13.setText("Fecha actual:");

        combopago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOLETA", "FACTURA" }));

        jButton1.setText("NUEVO PAGO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REGISTRAR PAGO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("CANCELAR PAGO");

        jLabel14.setText("Historial de reservas:");

        dtmreservas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(dtmreservas);

        btnCancelar.setText("CANCELAR RESERVA");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        labelcodigo.setText("*");

        labelidusu.setText("*");

        labelcom.setText("*");

        labelidres.setText("*");

        labelidHab.setText("*");

        labelncom.setText("*");

        labeligv.setText("*");

        labeltotal.setText("*");

        labelfecha.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txttotal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(igv, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcomprobante, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63))
                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidusu, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidres, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidHab, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combopago, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelidres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelidHab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelcom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelncom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labeligv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(246, 246, 246))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelidusu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(81, 81, 81))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labeltotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelfecha, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(17, 17, 17)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel2)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(combopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelcom))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(labelncom))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(igv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(labeligv))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(labeltotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(labelfecha)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jLabel6)
                            .addComponent(labelcodigo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidusu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(labelidusu))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(labelidres))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(labelidHab))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(btnCancelar))
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoActionPerformed
        // TODO add your handling code here:
        llenaLista2();
    }//GEN-LAST:event_comboEstadoActionPerformed

    private void dtmpendientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtmpendientesKeyPressed
        // TODO add your handling code here:
            
    }//GEN-LAST:event_dtmpendientesKeyPressed

    private void dtmpendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtmpendientesMouseClicked
        // TODO add your handling code here:
         if(dtm.getValueAt(this.dtmpendientes.getSelectedRow(), 9) == "EN PROCESO")
        {
            JOptionPane.showMessageDialog(this, "Este registro no esta en proceso, selecione otro");
        }
        else
        {
            ValidarBoleta();
            this.txtidusu.setText(dtm.getValueAt(this.dtmpendientes.getSelectedRow(), 1).toString());
            this.txtidHab.setText(dtm.getValueAt(this.dtmpendientes.getSelectedRow(), 2).toString());
            this.txtidres.setText(dtm.getValueAt(this.dtmpendientes.getSelectedRow(), 0).toString());
            
            Double total = Double.parseDouble(dtm.getValueAt(this.dtmpendientes.getSelectedRow(), 8).toString());
            
            this.igv.setText("" + total * 0.18);
            this.txttotal.setText(String.valueOf(Double.parseDouble(this.igv.getText()) + total));
        }
    }//GEN-LAST:event_dtmpendientesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ValidarBoleta();
        Date fecha = new Date();
        
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        
        List<boleta> lst = bController.getAllBoletas();
        txtid.setText(String.valueOf(lst.size()+1));
        this.txtcomprobante.setText("B-0000" + txtid.getText());
        this.txtfecha.setText("" + format1.format(fecha)); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// Dentro del botom 
        ValidarBoleta();
        
        if(txtid.getText().isEmpty() || txtidusu.getText().isEmpty() || txtidres.getText().isEmpty() || txtidHab.getText().isEmpty() || combopago.getSelectedIndex()==0 || txtcomprobante.getText().isEmpty() || igv.getText().isEmpty() || txttotal.getText().isEmpty() || txtfecha.getText().isEmpty() )
        {
            String mensaje = "Debe completar los siguientes campos correctamente:\n" + camposIncorrectos.toString();
            JOptionPane.showMessageDialog(null, mensaje, "Campos incorrectos", JOptionPane.WARNING_MESSAGE);
        }
        
        else
        {
        boleta bol = new boleta();
        
        bol.setIdPago(Integer.parseInt(this.txtid.getText()));
        bol.setIdReserva(Integer.parseInt(this.txtidres.getText()));
        bol.setIdUsuario(Integer.parseInt(this.txtidusu.getText()));
        bol.setIdHabitacion(Integer.parseInt(this.txtidHab.getText()));
        bol.setTipo_comprobante(this.combopago.getSelectedItem().toString());
        bol.setNum_comprobante(this.txtcomprobante.getText());
        bol.setIgv(Double.parseDouble(this.igv.getText()));
        bol.setTotal(Double.parseDouble(this.txttotal.getText()));
        bol.setFecha(this.txtfecha.getText());
        
        bController.addBoleta(bol);
        
        rController.Completar(Integer.parseInt(this.txtidres.getText()));
        
        String filename = bol.getNum_comprobante()+".pdf";
        generatePDF(filename);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        rController.Cancelar(Integer.parseInt(this.txtidres.getText()));
        hController.EditarEstadoT(Integer.parseInt(this.txtidHab.getText()));
        llenaLista();
        llenaLista2();
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Boleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Boleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Boleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Boleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Boleta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JComboBox<String> combopago;
    private javax.swing.JTable dtmpendientes;
    private javax.swing.JTable dtmreservas;
    private javax.swing.JTextField igv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelcodigo;
    private javax.swing.JLabel labelcom;
    private javax.swing.JLabel labelfecha;
    private javax.swing.JLabel labelidHab;
    private javax.swing.JLabel labelidres;
    private javax.swing.JLabel labelidusu;
    private javax.swing.JLabel labeligv;
    private javax.swing.JLabel labelncom;
    private javax.swing.JLabel labeltotal;
    private javax.swing.JTextField txtcomprobante;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtidHab;
    private javax.swing.JTextField txtidres;
    private javax.swing.JTextField txtidusu;
    private javax.swing.JTextField txttotal;
    private javax.swing.JLabel usu;
    // End of variables declaration//GEN-END:variables
}
