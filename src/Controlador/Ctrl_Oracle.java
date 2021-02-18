/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.*;

/**
 *
 * @author Gunze
 */
public class Ctrl_Oracle {
    
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private String sentencia;
    private int numReg;
    private boolean resultado;
    
    public boolean conectarServidor() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            resultado = true;
        } catch (ClassNotFoundException ex) {
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean conectarBD() {
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
            resultado = true;
        } catch (SQLException ex) {
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean conectarBD(String servidor, String puerto, String usuario, String contrasena) {
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:" + servidor + ":" + puerto + ":xe", usuario, contrasena);
            resultado = true;
        } catch (SQLException ex) {
            resultado = false;
        }
        
        return resultado;
    }
    
    public Connection getConexion() {
        return conexion;
    }
    
    public boolean existeTabla(String tabla) {
        resultado = false;
        if (conexion != null) {
            try {
                sentencia = "SELECT tabla FROM tabla WHERE tabla=\'"+tabla+"\'";
                st = conexion.createStatement();
                rs = st.executeQuery(sentencia);
                if (rs.next()) {
                    resultado = true;
                } else {
                    resultado = false;
                }
                st.close();
                rs.close();
            } catch (SQLException ex) {}
        }
        return resultado;
    }
    
    public boolean createTable(String sql) {
        resultado = false;
        
        if (conexion != null) {
            try {
                st = conexion.createStatement();
                numReg = st.executeUpdate(sql);
                if (numReg == 0) {
                    resultado = true;
                }
                st.close();
            } catch (SQLException ex) {
                resultado = false;
            }
        }
        return resultado;
    }
    
    public boolean dropTable(String tabla) {
        resultado = false;
        
        if (conexion != null) {
            try {
                sentencia = "DROP TABLE " + tabla;
                st = conexion.createStatement();
                numReg = st.executeUpdate(sentencia);
                st.close();
                
                if (numReg == 0) {
                    resultado = true;
                }
            } catch (SQLException ex) {
                resultado = false;
            }
        }
        return resultado;
    }
    
    public void cerrarConexion() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException ex) {
            
        }
    }
}
