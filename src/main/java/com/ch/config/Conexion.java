package com.ch.config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
    
    
     Connection conectar = null;
    String user = "root";
    String password = "admin";
    String bd = "carrito";
    String url ="jdbc:mysql://localhost:3306";
    String cadenaConexion = url+"/"+bd;
    
    public Connection establecerConexion() {
        
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection(cadenaConexion, user, password);
            JOptionPane.showMessageDialog(null, "Se conecto a la BD");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se conecto a la BD" + ex.toString());
        }
        return conectar;
    }
    
}
