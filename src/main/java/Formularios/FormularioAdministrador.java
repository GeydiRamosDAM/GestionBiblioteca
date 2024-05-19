/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import Clases.ConnectionDB;
import Clases.Encriptacion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Geydi
 */
public class FormularioAdministrador extends javax.swing.JFrame {

    /**
     * Creates new form FormularioCliente
     */
    JFrame frame1;
    public ConnectionDB myConnDB;
    String FechaReserva;
    String FechaEntrega; 
    String EstadoReserva;
    String NombreLibro;
    String AutorLibro; 
    String EdicionLibro;
    String AnnoLibro;
    String NombreBiblioteca;
    String DireccionBiblioteca;
    String ExistenciaLibro;
    int idUsuario;
    String UsuarioCorreo;
    int rolUsuario;
    long diasRestantes;
    String idReservaLibro;
    String usuarioReserva;
    
    String idLibroPrestamo;
    String idPrestamo;
    String estadoPrestamo;
    String stockLibro;
    
    
    
    
    public FormularioAdministrador(int p_idUsuario, int p_Rol, String p_UsuarioCorreo) {
        initComponents();
        
        idUsuario = p_idUsuario;
        UsuarioCorreo = p_UsuarioCorreo;       
        rolUsuario = p_Rol;
        
        if(rolUsuario ==1) //habilitamos la opción de gestion usuario si el usuario logueado es Administrador
            jMenuItem4.setEnabled(true);
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setTitle("GESTION DE LIBROS - ADMINISTRADOR");
        jl_usuario.setText(UsuarioCorreo);
        
        myConnDB = new ConnectionDB ("usuario_libreria","Libreria2024","gestion_libros"); //usuario, contraseña, BD
       
        try {
            showTableData();
        } catch (SQLException ex) {
            Logger.getLogger(FormularioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         //bloquear la edición de las filas
        jTable1.setDefaultEditor(Object.class, null);
        jTable2.setDefaultEditor(Object.class, null);
        
        jOptionPane1.setVisible(false);
        
        //esconder la columna
        jTable1.getColumnModel().getColumn(0).setWidth(0);
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        
        jTable1.getColumnModel().getColumn(1).setWidth(0);
        jTable1.getColumnModel().getColumn(1).setMinWidth(0);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(0);

        jTable1.getColumnModel().getColumn(2).setWidth(0);
        jTable1.getColumnModel().getColumn(2).setMinWidth(0);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(0);
        
        //esconder la columna
        jTable2.getColumnModel().getColumn(0).setWidth(0);
        jTable2.getColumnModel().getColumn(0).setMinWidth(0);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(0);
        
    }
    
        public void showTableData() throws SQLException
    {
       //limpiar las filas de las tablas
        DefaultTableModel tblModel1 = (DefaultTableModel)jTable1.getModel();
        tblModel1.setRowCount(0);
       
        DefaultTableModel tblModel2 = (DefaultTableModel)jTable2.getModel();
        tblModel2.setRowCount(0);
        
        
        frame1 = new JFrame("Database Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
       
         //---------------------MOSTRAR DATOS DE RESERVAS PENDIENTES DE APROBAR------------------
        
        jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable2.setFillsViewportHeight(true);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate DiaDeHoy = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
                
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date dateDiaDeHoy = Date.from(DiaDeHoy.atStartOfDay(defaultZoneId).toInstant());
               
         myConnDB.connect();
         ResultSet listaReservas = myConnDB.query("SELECT * FROM v_misreservas where EstadoReserva = 'En curso'");
         while(listaReservas.next())
            {
                idReservaLibro = String.valueOf(listaReservas.getInt("idReservaLibro"));  
                 
                java.util.Date fechaReservaBD = new java.util.Date(listaReservas.getDate("FechaReserva").getTime());
                long  time_difference = dateDiaDeHoy.getTime() - fechaReservaBD.getTime();  
                diasRestantes = (time_difference / (1000*60*60*24)) % 365;   
                                                
                usuarioReserva = listaReservas.getString("UsuarioCorreoElectronico");
                
                FechaReserva = String.valueOf(listaReservas.getDate("FechaReserva"));
                EstadoReserva = listaReservas.getString("EstadoReserva");
                NombreLibro = listaReservas.getString("NombreLibro");
                AutorLibro = listaReservas.getString("AutorLibro");
                EdicionLibro = String.valueOf(listaReservas.getInt("EdicionLibro"));
                AnnoLibro = String.valueOf(listaReservas.getInt("AnnoLibro"));
                DireccionBiblioteca = listaReservas.getString("DireccionBiblioteca");
                NombreBiblioteca = listaReservas.getString("NombreBiblioteca");
                ExistenciaLibro = String.valueOf(listaReservas.getInt("Existencia"));
                
                     
                String tbData [] = {idReservaLibro, String.valueOf(diasRestantes), usuarioReserva ,FechaReserva, EstadoReserva, NombreLibro, AutorLibro, EdicionLibro, AnnoLibro, ExistenciaLibro, NombreBiblioteca, DireccionBiblioteca};
                
                DefaultTableModel tblModel = (DefaultTableModel)jTable2.getModel();
                
                tblModel.addRow(tbData);
                
                //ordenar filas por el valor de una columna
                
            }

         myConnDB.disconnect();
         
         
         //---------------------MOSTRAR DATOS DE TODOS LOS PRESTAMOS REALIZADOS------------------
         
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setFillsViewportHeight(true);
               
         myConnDB.connect();
         ResultSet listaPrestamos = myConnDB.query("SELECT * FROM v_misprestamos where EstadoPrestamo = 'En curso'");
         while(listaPrestamos.next())
            {
                idLibroPrestamo = String.valueOf(listaPrestamos.getInt("idLibroPrestamo"));
                idPrestamo = String.valueOf(listaPrestamos.getInt("idPrestamo"));
                stockLibro = String.valueOf(listaPrestamos.getInt("StockLibro"));
                estadoPrestamo = listaPrestamos.getString("EstadoPrestamo");
                NombreLibro = listaPrestamos.getString("NombreLibro");
                FechaReserva = String.valueOf(listaPrestamos.getDate("FechaReserva"));
                FechaEntrega = String.valueOf(listaPrestamos.getDate("FechaEntrega"));
                NombreBiblioteca = listaPrestamos.getString("NombreBiblioteca");
                DireccionBiblioteca = listaPrestamos.getString("DireccionBiblioteca");
                                
                     
                String tbData [] = {idLibroPrestamo, idPrestamo, stockLibro, NombreLibro, estadoPrestamo, FechaReserva, FechaEntrega, NombreBiblioteca, DireccionBiblioteca};
                
                DefaultTableModel tblModel = (DefaultTableModel)jTable1.getModel();
                
                tblModel.addRow(tbData);
                //tblModel.isCellEditable(false);
            }

         myConnDB.disconnect();
         
         
         
         
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jl_usuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jOptionPane1 = new javax.swing.JOptionPane();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabel2.setText("Bienvenido:");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 5));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 51));
        jLabel1.setText("RESERVAS PENDIENTES POR APROBAR");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idReserva", "Días Restantes", "Usuario", "Fecha de Reserva", "Estado de Reserva", "Nombre del Libro", "Autor del Libro", "Edición del LIbro", "Año del Libro", "Existencia", "Nombre Biblioteca", "Dirección Biblioteca"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51), 5));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 51));
        jLabel4.setText("PRESTAMOS REALIZADOS");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idLibro", "idPrestamo", "stockLibro", "Nombre de Libro", "Estado", "Fecha de Préstamo", "Fecha de Entrega", "Biblioteca", "Dirección Biblioteca"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(432, 432, 432))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jButton1.setText("Cancelar Reserva");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aprobar Reserva");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Realizar Devolución");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 1204, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(435, 435, 435)
                .addComponent(jOptionPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jOptionPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jMenu1.setText("Inicio");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Configuración");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu5.setText("Operaciones");

        jMenuItem3.setText("Realizar Préstamo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Gestionar Usuarios");
        jMenuItem4.setEnabled(false);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem5.setText("Gestionar Libros");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem5);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // cerrar sesión
        this.setVisible(false);
        InicioSesion is = new InicioSesion();
        is.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // Inicio de Pantalla de Administrador
        
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // abrir formulario Reserva libro
        RealizarPrestamo realizarPrestamo;
        try {
             realizarPrestamo = new RealizarPrestamo(idUsuario,rolUsuario,UsuarioCorreo);
             this.setVisible(false);
             realizarPrestamo.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(FormularioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        try {
            // TODO add your handling code here:
            //this.setEnabled(true);
            showTableData();
        } catch (SQLException ex) {
            Logger.getLogger(FormularioAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        switch (rolUsuario) {
            case 1://rol administrador
            {
                ConfiguracionUsuario configUsuario = new ConfiguracionUsuario(idUsuario, "Administrador");
                configUsuario.setVisible(true);
            }
                break;
            case 3://rol gestor
            {
                ConfiguracionUsuario configUsuario = new ConfiguracionUsuario(idUsuario, "Gestor");
                configUsuario.setVisible(true);
            }
                break;
            default:
                throw new AssertionError();
        }
//        ConfiguracionUsuario configUsuario = new ConfiguracionUsuario(idUsuario);
//        configUsuario.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // cancelar reserva
         CambiarEstadoReserva("Cancelada", "Se ha cancelado la reserva!");
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public void CambiarEstadoReserva (String estado, String mensaje)
    {
        int filaSeleccionada = jTable2.getSelectedRow();
        if(filaSeleccionada != -1){
            int idReservaSeleccionada = Integer.valueOf(jTable2.getModel().getValueAt(filaSeleccionada, 0).toString()); //columna 0
        
            try {
                    //conecto con BD
                    myConnDB.connect();

                    //actualiza en BD --- updateQuery() devuelve 1 si modificó algún registro en BD, 0 sino de devuelve o modifica un registro
                    String query =               
                        "UPDATE reservalibro SET" +
                        " Estado = '" + estado + "'" +
                        " WHERE idReserva = " + idReservaSeleccionada;        

                     int actualizacionReserva = myConnDB.updateQuery(query);

                    //desconecto con BD
                    myConnDB.disconnect();
                    
                    if(actualizacionReserva == 1)
                        jOptionPane1.showMessageDialog(null, mensaje);

                    } catch (SQLException e) {
                       // throw new Exception("");
                    }
//        
        }
        else
        {
             jOptionPane1.showMessageDialog(null, "Debe seleccionar antes una reserva!");
        }
    
    }       
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // aprobar reserva
         CambiarEstadoReserva("Aprobada", "Se ha aprobado la reserva!");   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // realizar devolución
        
        int filaSeleccionada = jTable1.getSelectedRow();
        if(filaSeleccionada != -1){
            int idPrestamoSeleccionado = Integer.valueOf(jTable1.getModel().getValueAt(filaSeleccionada, 1).toString()); //columna 1, idPrestamo
            int idLibroPrestamo = Integer.valueOf(jTable1.getModel().getValueAt(filaSeleccionada, 0).toString()); //columna 0, idLibroPrestamo
            int stockLibro = Integer.valueOf(jTable1.getModel().getValueAt(filaSeleccionada, 2).toString()); //columna 2, stockLIbro
            
            try {
                    //conecto con BD
                    myConnDB.connect();

                    //actualiza en BD --- updateQuery() devuelve 1 si modificó algún registro en BD, 0 sino de devuelve o modifica un registro
                    String query =               
                        "UPDATE prestamos SET" +
                        " Estado = 'Devuelto'" +
                        " WHERE idPrestamoLibro = " + idPrestamoSeleccionado;        

                     int actualizacionPrestamo = myConnDB.updateQuery(query);
                    
                    //if(actualizacionPrestamo == 1)
                      //  jOptionPane1.showMessageDialog(null, "Se ha realizado la devolución!");
                    
                    String query1 =               
                        "UPDATE libros SET" +
                        " Stock = " + (stockLibro+1) +
                        " WHERE idLibros = " + idLibroPrestamo;        

                     int actualizacionStockLibro = myConnDB.updateQuery(query1);
                     
                     
                     //desconecto con BD
                    myConnDB.disconnect(); 
                     
                    if(actualizacionPrestamo == 1 && actualizacionPrestamo == 1)
                        jOptionPane1.showMessageDialog(null, "Se ha realizado la devolución!");
                            
                    } catch (SQLException e) {
                       // throw new Exception("");
                    }
//        
        }
        else
        {
             jOptionPane1.showMessageDialog(null, "Debe seleccionar antes un préstamo!");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        GestionUsuarios gu = new GestionUsuarios(idUsuario,rolUsuario,UsuarioCorreo);
        this.setVisible(false);
        gu.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        GestionLibros gl = new GestionLibros(idUsuario, rolUsuario, UsuarioCorreo);
        gl.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FormularioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FormularioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FormularioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FormularioCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FormularioCliente().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jl_usuario;
    // End of variables declaration//GEN-END:variables
}
