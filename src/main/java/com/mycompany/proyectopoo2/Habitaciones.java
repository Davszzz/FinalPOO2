/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.proyectopoo2;

import Controller.habitacionController;
import Controller.hcatController;
import Model.habitacion;
import Model.hcat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Davs
 */
public class Habitaciones extends javax.swing.JInternalFrame {
 
    private DefaultTableModel dtm = new DefaultTableModel();
    private habitacionController  hController = new habitacionController();
    private hcatController cController = new hcatController();
    /**
     * Creates new form Habitaciones
     */
    public Habitaciones() {
        initComponents();
        LlenarCombo();
        llenaTabla();
        llenaLista();
        Codigo();
        ValidarLogin();
        btneliminar.setEnabled(false);
        btneditar.setEnabled(false);
        txtprecio.setEnabled(false);
    }
    public void Setteo()
    {
        this.txtnum.setText("");
        this.comboC2.setSelectedIndex(0);
        this.txtprecio.setText("");
        this.comboE.setSelectedIndex(0);
        this.txtDescripcion.setText("");
        btneliminar.setEnabled(false);
        btneditar.setEnabled(false);
        Codigo();
    }
    
    public void Codigo()
    {
        List<habitacion> lst = hController.getAllRooms();
        
        this.txtid.setText(Integer.toString(lst.size()+1));
        txtid.setEnabled(false);
    }
    
    public void ValidarLogin()
    {
       
       if(txtnum.getText().isEmpty())
       {
            labelnum.setText("Campo obligatorio");
       }
       else if(!txtnum.getText().contains("0"))
       {
           labelnum.setText("Numero invalido");
       }
       else
       {
           labelnum.setText("✔");
       }
       
       if(this.comboC2.getSelectedIndex()==0)
       {
           labelcat.setText("Campo obligatorio");
       }
       else
       {
           labelcat.setText("✔");
       }
       if(this.txtDescripcion.getText().length()>45)
       {
           labeldes.setText("Superior a 45 caracteres");
       }
       else
       {
           labeldes.setText("✔");
       }
       if(txtnum.getText().isEmpty() || !txtnum.getText().contains("0") || this.comboC2.getSelectedIndex()==0  || this.txtDescripcion.getText().length()>45 || btneliminar.isEnabled()==true)
       {
           btnregistrar.setEnabled(false);
       }
       else
       {
           btnregistrar.setEnabled(true);
       }
     }
    
    
    private void LlenarCombo()
    {
        List<hcat> lst = cController.getAllCategorias();
        for(hcat item:lst)
        {
            this.comboC1.addItem("[" + item.getCod()+ "] " + item.getDescripcion());
            this.comboC2.addItem("[" + item.getCod()+ "] " + item.getDescripcion());
        }
    }
    
    public void llenaTabla()
    {
        dtm.addColumn("CODIGO");
        dtm.addColumn("NUMERO");
        dtm.addColumn("PRECIO");
        dtm.addColumn("CATEGORIA");
        dtm.addColumn("ESTADO");
        dtm.addColumn("DESCRIPCION");
        this.dtmhab.setModel(dtm);
    }

    public void llenaLista()
    {
        String Estado;
        List<habitacion> lst = hController.getAllRooms();
        List<hcat> lstc = cController.getAllCategorias();
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
            if(lst.get(i).getCodc()==this.comboC1.getSelectedIndex())
            {
                Object[] vhb=new Object[6];
                vhb[0] = lst.get(i).getCod();
                vhb[1] = lst.get(i).getNumero();
                vhb[2] = cController.Buscar(lst.get(i).getCodc()).getPrecio();
                vhb[3] = cController.Buscar(lst.get(i).getCodc()).getDescripcion(); 
                vhb[4] = Estado;
                vhb[5] = lst.get(i).getDescripcion();
                dtm.addRow(vhb);
            }
            else if(this.comboC1.getSelectedIndex()==0)
            {
                Object[] vhb=new Object[6];
                vhb[0] = lst.get(i).getCod();
                vhb[1] = lst.get(i).getNumero();
                vhb[2] = cController.Buscar(lst.get(i).getCodc()).getPrecio();
                vhb[3] = cController.Buscar(lst.get(i).getCodc()).getDescripcion(); 
                vhb[4] = Estado;
                vhb[5] = lst.get(i).getDescripcion();
                dtm.addRow(vhb);
            }
        this.dtmhab.setModel(dtm);
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

        comboC1 = new javax.swing.JComboBox<>();
        txtprecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        dtmhab = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboC2 = new javax.swing.JComboBox<>();
        txtid = new javax.swing.JTextField();
        txtnum = new javax.swing.JTextField();
        comboE = new javax.swing.JComboBox<>();
        txtDescripcion = new javax.swing.JTextField();
        btnregistrar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        labelnum = new javax.swing.JLabel();
        labelcat = new javax.swing.JLabel();
        labeldes = new javax.swing.JLabel();

        setClosable(true);
        setForeground(new java.awt.Color(153, 255, 204));
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        comboC1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboC1ActionPerformed(evt);
            }
        });

        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecioKeyReleased(evt);
            }
        });

        dtmhab.setModel(new javax.swing.table.DefaultTableModel(
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
        dtmhab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtmhabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dtmhab);

        jLabel1.setText("Lista de habitaciones:");

        jLabel2.setText("Filtrar por categoria:");

        jLabel3.setText("idHabitacion:");

        jLabel4.setText("N° Habitacion:");

        jLabel5.setText("Categoria:");

        jLabel6.setText("Precio:");

        jLabel7.setText("Estado:");

        jLabel8.setText("Descripcion:");

        comboC2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboC2ActionPerformed(evt);
            }
        });

        txtnum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnumKeyReleased(evt);
            }
        });

        comboE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OCUPADO", "DISPONIBLE", " " }));

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        btnregistrar.setText("CREAR HABITACION");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        btneliminar.setText("ELIMINAR HABITACION");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btneditar.setText("EDITAR");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btncancelar.setText("CANCELAR REGISTRO");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        labelnum.setText("*");

        labelcat.setText("*");

        labeldes.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(comboC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labeldes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(comboE, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtprecio, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboC2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtnum, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 49, Short.MAX_VALUE)
                                                .addComponent(btncancelar))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(labelnum, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                                    .addComponent(labelcat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnregistrar)
                                .addGap(26, 26, 26)
                                .addComponent(btneliminar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(btncancelar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(labelnum))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelcat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labeldes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnregistrar)
                            .addComponent(btneliminar))
                        .addGap(18, 18, 18)
                        .addComponent(btneditar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboC1ActionPerformed
        // TODO add your handling code here:
        llenaLista();
        
    }//GEN-LAST:event_comboC1ActionPerformed

    private void comboC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboC2ActionPerformed
        // TODO add your handling code here:
        hcat objc = cController.Buscar(this.comboC2.getSelectedIndex());
        if(this.comboC2.getSelectedIndex()==0)
        {
            this.txtprecio.setText(" ");
        }
        else
        {
           this.txtprecio.setText("" + objc.getPrecio());
        }
        ValidarLogin();
    }//GEN-LAST:event_comboC2ActionPerformed

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        // TODO add your handling code here:
        boolean estado;
        if(comboE.getSelectedIndex()==0)
        {
                estado=false;
        }
        else
        {
                estado=true;
        }
        
        habitacion objhab = new habitacion();
        
        objhab.setCod(Integer.parseInt(this.txtid.getText()));
        objhab.setCodc(this.comboC2.getSelectedIndex());
        objhab.setNumero(this.txtnum.getText());
        objhab.setEstado(estado);
        objhab.setDescripcion(this.txtDescripcion.getText());
        
        hController.addHabitacion(objhab);
        JOptionPane.showMessageDialog(this, "Registro grabado satisfactoriamente!");
        llenaLista();
        Setteo();
        Codigo();
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void dtmhabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtmhabMouseClicked
        // TODO add your handling code here:
        this.txtid.setText(dtm.getValueAt(this.dtmhab.getSelectedRow(), 0).toString());
        this.txtnum.setText(dtm.getValueAt(this.dtmhab.getSelectedRow(), 1).toString());
        this.txtprecio.setText(dtm.getValueAt(this.dtmhab.getSelectedRow(), 2).toString());
        this.txtDescripcion.setText(dtm.getValueAt(this.dtmhab.getSelectedRow(), 5).toString());
        btneliminar.setEnabled(true);
        btneditar.setEnabled(true);
    }//GEN-LAST:event_dtmhabMouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        boolean estado;
        if(comboE.getSelectedIndex()==0)
        {
                estado=false;
        }
        else
        {
                estado=true;
        }
        
        habitacion objhab = new habitacion();
        objhab.setCod(Integer.parseInt(this.txtid.getText()));
        objhab.setCodc(this.comboC2.getSelectedIndex());
        objhab.setNumero(this.txtnum.getText());
        objhab.setEstado(estado);
        objhab.setDescripcion(this.txtDescripcion.getText());
        
        hController.Editar(objhab);
        JOptionPane.showMessageDialog(this, "Registro EDITADO satisfactoriamente!");
        llenaLista();
        Setteo();
        Codigo();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
        habitacion objhab = new habitacion();
        objhab.setCod(Integer.parseInt(this.txtid.getText()));
        hController.Borrar(objhab);
        JOptionPane.showMessageDialog(this, "Registro ELIMINADO satisfactoriamente!");
        llenaLista();
        Codigo();
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:
        Setteo();

    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtnumKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txtnumKeyReleased

    private void txtprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txtprecioKeyReleased

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txtDescripcionKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JComboBox<String> comboC1;
    private javax.swing.JComboBox<String> comboC2;
    private javax.swing.JComboBox<String> comboE;
    private javax.swing.JTable dtmhab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelcat;
    private javax.swing.JLabel labeldes;
    private javax.swing.JLabel labelnum;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnum;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
