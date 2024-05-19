/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionbiblioteca;

import Formularios.VentanaInicial;
import Clases.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author Geydi
 */
public class GestionBiblioteca {

    public static void main(String[] args) throws SQLException {
        
         JFrame.setDefaultLookAndFeelDecorated(true);
         VentanaInicial vi = new VentanaInicial();
    
         vi.setResizable(false);
         vi.setVisible(true);
    }
 
 
}
