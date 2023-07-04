/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.proyectopoo2;

import Controller.ReservaController;
import Controller.boletaController;
import Controller.habitacionController;
import Controller.hcatController;
import Controller.tipo_usuController;
import Controller.usuarioController;
import Model.Reserva;
import Model.boleta;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Davs
 */
public class Pagos extends javax.swing.JInternalFrame {
    
    private DefaultTableModel dtm = new DefaultTableModel();
    private ReservaController rController = new ReservaController();
    private usuarioController uController = new usuarioController();
    private tipo_usuController tController = new tipo_usuController();
    private habitacionController hController = new habitacionController();
    private hcatController cController = new hcatController();
    private boletaController bController = new boletaController();

    /**
     * Creates new form Pagos
     */
    public Pagos() {
        initComponents();
        llenaTabla();
        llenaLista();
        this.txtboleta.setEnabled(false);
    }
    public void abrir(String nombre) throws IOException
    {
        try
        {
            File path = new File(nombre + ".pdf");
            Desktop.getDesktop().open(path);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"El archivo " + nombre + ".pdf no existe","Atencion", 2);
        }
    }
    
        public void llenaTabla()
    {
        dtm.addColumn("CODIGO");
        dtm.addColumn("IDRESERVA");
        dtm.addColumn("IDUSUARIO");
        dtm.addColumn("IDHABITACION");
        dtm.addColumn("TIPO DE C°");
        dtm.addColumn("COMPROBANTE");
        dtm.addColumn("IGV");
        dtm.addColumn("TOTAL");
        dtm.addColumn("FECHA");
        
        this.tbboleta.setModel(dtm);
    }
    
    public void llenaLista()
    {
        List<boleta> lst = bController.getAllBoletas();
        dtm.setRowCount(0);
        for(int i=0;i<lst.size();i++)
        {
                Object[] vhb=new Object[9];
                vhb[0] = lst.get(i).getIdPago();
                vhb[1] = lst.get(i).getIdReserva();
                vhb[2] = lst.get(i).getIdUsuario();
                vhb[3] = lst.get(i).getIdHabitacion();
                vhb[4] = lst.get(i).getTipo_comprobante();
                vhb[5] = lst.get(i).getNum_comprobante(); 
                vhb[6] = lst.get(i).getIgv();
                vhb[7] = lst.get(i).getTotal();
                vhb[8] = lst.get(i).getFecha();
               
                dtm.addRow(vhb);
        }
          
        this.tbboleta.setModel(dtm);
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
        tbboleta = new javax.swing.JTable();
        fechai = new javax.swing.JTextField();
        fechaf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtboleta = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        tbboleta.setModel(new javax.swing.table.DefaultTableModel(
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
        tbboleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbboletaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbboleta);

        fechai.setText("00-00-0000");
        fechai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaiActionPerformed(evt);
            }
        });

        fechaf.setText("00-00-0000");

        jLabel1.setText("FILTRAR ENTRE FECHAS:");

        jLabel2.setText("Fecha inicial:");

        jLabel3.setText("Fecha final:");

        jLabel4.setText("REGISTRO DE BOLETAS:");

        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("REINICIAR TABLA");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("CARGAR BOLETA");

        jLabel6.setText("Numero de boleta:");

        jButton3.setText("CARGAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(fechai, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtboleta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(59, 59, 59)))))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jButton1)
                    .addComponent(jLabel6)
                    .addComponent(txtboleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fechaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        List<boleta> lst = bController.BuscarEntreFechas(fechai.getText(), fechaf.getText());
        dtm.setRowCount(0);
        for(int i=0;i<lst.size();i++)
        {

                Object[] vhb=new Object[9];
                vhb[0] = lst.get(i).getIdPago();
                vhb[1] = lst.get(i).getIdReserva();
                vhb[2] = lst.get(i).getIdUsuario();
                vhb[3] = lst.get(i).getIdHabitacion();
                vhb[4] = lst.get(i).getTipo_comprobante();
                vhb[5] = lst.get(i).getNum_comprobante(); 
                vhb[6] = lst.get(i).getIgv();
                vhb[7] = lst.get(i).getTotal();
                vhb[8] = lst.get(i).getFecha();
               
                dtm.addRow(vhb);
            }
            this.tbboleta.setModel(dtm);   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        llenaLista();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(!txtboleta.getText().isEmpty())
        {
            try {
                abrir(txtboleta.getText());
            } catch (IOException ex) {
                Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Campo vacio");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tbboletaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbboletaMouseClicked
        // TODO add your handling code here:
        this.txtboleta.setText(dtm.getValueAt(this.tbboleta.getSelectedRow(), 5).toString());
    }//GEN-LAST:event_tbboletaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField fechaf;
    private javax.swing.JTextField fechai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbboleta;
    private javax.swing.JTextField txtboleta;
    // End of variables declaration//GEN-END:variables
}