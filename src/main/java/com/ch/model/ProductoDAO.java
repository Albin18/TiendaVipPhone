package com.ch.model;

import java.sql.Connection;
import com.ch.config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class ProductoDAO {

Connection con;
Conexion conexion = new Conexion();
PreparedStatement ps;
ResultSet rs;

public List Listar(){
    List<Producto>productos = new ArrayList();
    String sql = "SELECT * FROM producto";
            try {
                con = conexion.establecerConexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setId(rs.getInt(1));
                    p.setNombre(rs.getString(2));
                    p.setDescripcion(rs.getString(3));
                    p.setPrecio(rs.getDouble(4));
                    p.setStock(rs.getInt(5));
                    p.setFoto(rs.getBinaryStream(6));
                    productos.add(p);
                }
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, "No se agrego producto"+ ex.toString());
        }
            return productos;
    }

public void listarImagen(int id, HttpServletResponse response){
    String sql = "SELECT * FROM producto WHERE idProducto =" +id;
    InputStream inputStream = null;
     OutputStream outputStream = null;
     BufferedInputStream bufferedInputStream = null;
     BufferedOutputStream bufferedOutputStream = null;
    try{
        outputStream = response.getOutputStream();
                con = conexion.establecerConexion();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    inputStream = rs.getBinaryStream("Foto");
                } 
                bufferedInputStream = new BufferedInputStream(inputStream);
                bufferedOutputStream = new BufferedOutputStream(outputStream);
                int i= 0;
                while((i=bufferedInputStream.read()) != -1){
                    bufferedOutputStream.write(i);
                }
    
                        }catch(Exception ex) {
        
    }
    
}

    
}
