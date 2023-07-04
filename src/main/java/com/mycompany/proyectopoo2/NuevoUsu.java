/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.proyectopoo2;

import Controller.ReservaController;
import Controller.tipo_usuController;
import Controller.usuarioController;
import Model.SHA256;
import Model.tipo_usu;
import Model.usuario;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Davs
 */
public class NuevoUsu extends javax.swing.JInternalFrame {
    
    private usuarioController uController = new usuarioController();
    private tipo_usuController tController = new tipo_usuController();
    private DefaultTableModel dtm = new DefaultTableModel();

    /**
     * Creates new form Usuariomantenimientos
     */
    public NuevoUsu() {
        initComponents();
        llenaTabla();
        llenaLista();
        llenarCombo();
        ValidarLogin();
        Codigo();
        
        btneliminar.setEnabled(false);
        btneditar.setEnabled(false);
    }
    
    public void llenarCombo()
    {
        List<tipo_usu> lst = tController.getAllCategorias();
        
        for(tipo_usu item:lst)
        {
            this.ctipo1.addItem("[" + item.getId()+ "] " + item.getTipo());
            this.ctipo2.addItem("[" + item.getId()+ "] " + item.getTipo());
        }
    }
    
    public void Codigo()
    {
        List<usuario> lst = uController.getAllUsuController();
        
        this.txtid.setText(Integer.toString(lst.size()+1));
        txtid.setEnabled(false);
    
    }
    
    public void Borrar()
    {
        this.txtusuario.setText("");
        this.ctipo2.setSelectedIndex(0);
        this.txtcorreo.setText("");
        this.txtcontra.setText("");
        this.txttel.setText("");
        this.txtdni.setText("");
        this.cGenero.setSelectedIndex(0);
        btneliminar.setEnabled(false);
        btneditar.setEnabled(false);
    }
    
    public void ValidarLogin()
    {
       
       if(txtusuario.getText().isEmpty())
       {
            labelusu.setText("Campo obligatorio");
       }
       else
       {
           labelusu.setText("✔");
       }
       
       if(txtcorreo.getText().isEmpty())
       {
           labelcorreo.setText("Campo obligatorio");
       }
       else if(!txtcorreo.getText().contains("@") || !txtcorreo.getText().contains(".com"))
       {
           labelcorreo.setText("Correo invalido");
       }
       else
       {
            labelcorreo.setText("✔");   
       }
       if(txtcontra.getText().isEmpty())
       {
           labelcontra.setText("Campo obligatorio");
       }
       else
       {
           labelcontra.setText("✔");
       }
       if(txttel.getText().isEmpty())
       {
           labeltel.setText("Campo obligatorio");
       }
       else if(txttel.getText().length()<9)
       {
           labeltel.setText("Numero de telefono invalido");
       }
       else
       {
           labeltel.setText("✔");
       }
       if(txtdni.getText().isEmpty())
        {   
            labeldni.setText("Campo obligatorio");
        }
       else if(txtdni.getText().length()<8)
       {
           labeldni.setText("Dni Invalido");
       }
       else
       {
           labeldni.setText("✔");
       }
       if(ctipo2.getSelectedIndex()==0)
       {
           labeltipo.setText("Campo obligatorio");
       }
       else
       {
           labeltipo.setText("✔");
       }
       if(cGenero.getSelectedIndex()==0)
       {
           labelg.setText("Campo obligatorio");
       }
       else
       {
           labelg.setText("✔");
       }
       if(txtusuario.getText().isEmpty() || txtcorreo.getText().isEmpty() || txtcontra.getText().isEmpty() || !txtcorreo.getText().contains("@") || !txtcorreo.getText().contains(".com") || txttel.getText().isEmpty() || txtdni.getText().isEmpty() || cGenero.getSelectedIndex()==0 || txttel.getText().length()<9 || txtdni.getText().length()<8 || btneliminar.isEnabled()==true || ctipo2.getSelectedIndex()==0)
       {
           btncrear.setEnabled(false);
       }
       else
       {
           btncrear.setEnabled(true);
       }
     }
    
    public void llenaTabla()
    {
        dtm.addColumn("CODIGO");
        dtm.addColumn("TIPO");
        dtm.addColumn("USUARIO");
        dtm.addColumn("CORREO");
        dtm.addColumn("CONTRASEÑA");
        dtm.addColumn("TELEFONO");
        dtm.addColumn("DNI");
        dtm.addColumn("GENERO");
        this.dtmusu.setModel(dtm);
    }
    public void llenaLista()
    {
        List<usuario> lst = uController.getAllUsuController();
        dtm.setRowCount(0);
        for(int i=0;i<lst.size();i++)
        {
            if(lst.get(i).getTipo()==this.ctipo1.getSelectedIndex())
            {
                Object[] vec=new Object[8];
                vec[0] = lst.get(i).getCod();
                vec[1]= tController.Buscar(lst.get(i).getTipo()).getTipo();
                vec[2] = lst.get(i).getUsuario();
                vec[3] = lst.get(i).getCorreo();
                vec[4] = lst.get(i).getContraseña();
                vec[5] = lst.get(i).getTelefono();
                vec[6] = lst.get(i).getDni();
                vec[7] = lst.get(i).getGenero();
                dtm.addRow(vec);
            }
            else if(this.ctipo1.getSelectedIndex()==0 )
            {
                Object[] vec=new Object[8];
                vec[0] = lst.get(i).getCod();
                vec[1]= tController.Buscar(lst.get(i).getTipo()).getTipo();
                vec[2] = lst.get(i).getUsuario();
                vec[3] = lst.get(i).getCorreo();
                vec[4] = lst.get(i).getContraseña();
                vec[5] = lst.get(i).getTelefono();
                vec[6] = lst.get(i).getDni();
                vec[7] = lst.get(i).getGenero();
                dtm.addRow(vec);
            }
        }
        this.dtmusu.setModel(dtm);
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
        jLabel2 = new javax.swing.JLabel();
        ctipo1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        dtmusu = new javax.swing.JTable();
        ctipo2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cGenero = new javax.swing.JComboBox<>();
        txtid = new javax.swing.JTextField();
        txtusuario = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txtcontra = new javax.swing.JTextField();
        txttel = new javax.swing.JTextField();
        btncrear = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        labelusu = new javax.swing.JLabel();
        labeltipo = new javax.swing.JLabel();
        labelcorreo = new javax.swing.JLabel();
        labelcontra = new javax.swing.JLabel();
        labeltel = new javax.swing.JLabel();
        labeldni = new javax.swing.JLabel();
        labelg = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Nuevo Usuario");

        jLabel1.setText("Lista de usuarios:");

        jLabel2.setText("Filtrar por rol:");

        ctipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        ctipo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctipo1ActionPerformed(evt);
            }
        });

        dtmusu.setModel(new javax.swing.table.DefaultTableModel(
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
        dtmusu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtmusuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dtmusu);

        ctipo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        ctipo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctipo2ActionPerformed(evt);
            }
        });

        jLabel3.setText("idUsuario:");

        jLabel4.setText("Usuario:");

        jLabel5.setText("Correo:");

        jLabel6.setText("Contraseña:");

        jLabel7.setText("Telefono:");

        jLabel8.setText("Genero:");

        jLabel9.setText("El usuario es:");

        cGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "M", "F" }));
        cGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cGeneroActionPerformed(evt);
            }
        });

        txtusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtusuarioKeyReleased(evt);
            }
        });

        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });
        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcorreoKeyReleased(evt);
            }
        });

        txtcontra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraActionPerformed(evt);
            }
        });
        txtcontra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontraKeyReleased(evt);
            }
        });

        txttel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelKeyReleased(evt);
            }
        });

        btncrear.setText("CREAR USUARIO");
        btncrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearActionPerformed(evt);
            }
        });

        btneliminar.setText("ELIMINAR USUARIO");

        btneditar.setText("EDITAR");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        jButton4.setText("CANCELAR REGISTRO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setText("Dni:");

        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdniKeyReleased(evt);
            }
        });

        labelusu.setText("*");

        labeltipo.setText("*");

        labelcorreo.setText("*");

        labelcontra.setText("*");

        labeltel.setText("*");

        labeldni.setText("*");

        labelg.setText("*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(182, 182, 182)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ctipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btncrear)
                                        .addGap(18, 18, 18)
                                        .addComponent(btneliminar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel10))
                                                .addGap(36, 36, 36)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtcontra, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtusuario, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtcorreo, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(ctipo2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txttel)
                                                    .addComponent(txtdni))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addComponent(jButton4))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(labelg, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                                    .addComponent(labeldni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labeltel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelcontra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelcorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labeltipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(labelusu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(111, 111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(ctipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelusu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(ctipo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labeltipo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelcorreo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtcontra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelcontra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labeltel))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labeldni))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelg)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btncrear)
                            .addComponent(btneliminar))
                        .addGap(18, 18, 18)
                        .addComponent(btneditar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ctipo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctipo1ActionPerformed
        // TODO add your handling code here:
        llenaLista();
    }//GEN-LAST:event_ctipo1ActionPerformed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void txtcontraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontraActionPerformed

    private void btncrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearActionPerformed
        // TODO add your handling code here:
        SHA256 SHA = new SHA256();
        usuario objusu = new usuario();
        
       objusu.setCod(Integer.parseInt(this.txtid.getText()));
       objusu.setTipo(this.ctipo2.getSelectedIndex());
       objusu.setUsuario(this.txtusuario.getText());
       objusu.setCorreo(this.txtcorreo.getText());
       objusu.setContraseña(SHA.Char25(this.txtcontra.getText()));
       objusu.setTelefono(this.txttel.getText());
       objusu.setDni(this.txtdni.getText());
       objusu.setGenero(this.cGenero.getSelectedItem().toString());
        
       uController.Añadir(objusu); //Pasa el objeto con los datos escritos en el formulario/
       JOptionPane.showMessageDialog(this, "Registro grabado satisfactoriamente!");
       llenaLista();
       Borrar();
       Codigo();
    }//GEN-LAST:event_btncrearActionPerformed

    private void txtusuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txtusuarioKeyReleased

    private void ctipo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ctipo2ActionPerformed
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_ctipo2ActionPerformed

    private void txtcorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txtcorreoKeyReleased

    private void txtcontraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txtcontraKeyReleased

    private void txttelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txttelKeyReleased

    private void txtdniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyReleased
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_txtdniKeyReleased

    private void cGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cGeneroActionPerformed
        // TODO add your handling code here:
        ValidarLogin();
    }//GEN-LAST:event_cGeneroActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Borrar();
        Codigo();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void dtmusuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtmusuMouseClicked
        // TODO add your handling code here:
        this.txtid.setText(dtm.getValueAt(this.dtmusu.getSelectedRow(), 0).toString());
        this.txtusuario.setText(dtm.getValueAt(this.dtmusu.getSelectedRow(), 2).toString());
        this.txtcorreo.setText(dtm.getValueAt(this.dtmusu.getSelectedRow(), 3).toString());
        this.txttel.setText(dtm.getValueAt(this.dtmusu.getSelectedRow(), 5).toString());
        this.txtdni.setText(dtm.getValueAt(this.dtmusu.getSelectedRow(), 6).toString());
        
        btneliminar.setEnabled(true);
        btneditar.setEnabled(true);
        ValidarLogin();
    }//GEN-LAST:event_dtmusuMouseClicked

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        SHA256 SHA = new SHA256();
        usuario objusu = new usuario();
        objusu.setCod(Integer.parseInt(this.txtid.getText()));
        objusu.setTipo(this.ctipo2.getSelectedIndex());
        objusu.setUsuario(this.txtusuario.getText());
        objusu.setCorreo(this.txtcorreo.getText());
        objusu.setContraseña(SHA.Char25(this.txtcontra.getText()));
        objusu.setTelefono(this.txttel.getText());
        objusu.setDni(this.txtdni.getText());
        objusu.setGenero(this.cGenero.getSelectedItem().toString());
        
        uController.Editar(objusu);
        JOptionPane.showMessageDialog(this, "Registro editado satisfactoriamente!");
        llenaLista();
        Borrar();
        Codigo();
    }//GEN-LAST:event_btneditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncrear;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JComboBox<String> cGenero;
    private javax.swing.JComboBox<String> ctipo1;
    private javax.swing.JComboBox<String> ctipo2;
    private javax.swing.JTable dtmusu;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelcontra;
    private javax.swing.JLabel labelcorreo;
    private javax.swing.JLabel labeldni;
    private javax.swing.JLabel labelg;
    private javax.swing.JLabel labeltel;
    private javax.swing.JLabel labeltipo;
    private javax.swing.JLabel labelusu;
    private javax.swing.JTextField txtcontra;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txttel;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
