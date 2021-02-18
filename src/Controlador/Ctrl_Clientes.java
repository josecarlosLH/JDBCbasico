package Controlador;

import Modelo.Clientes;
import Vista.FramePrincipal;
import java.sql.*;
import java.util.ArrayList;

public class Ctrl_Clientes {
    public ArrayList<Clientes> listaClientes = new ArrayList<Clientes>();
    //private FramePrincipal fp = new FramePrincipal();
    
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private String sentencia;
    private int numReg;
    
    public ArrayList<Clientes> listarClientes() {
        conexion = FramePrincipal.co.getConexion();
        listaClientes.removeAll(listaClientes);
        
        if (conexion != null) {
            try {
                sentencia = "SELECT * FROM CLIENTES";
                                
                st = conexion.createStatement();
                rs = st.executeQuery(sentencia);
                                
                while (rs.next()) {     
                    String fechaFormateada = rs.getString("fechaNacimiento").substring(0, 10);
                    Clientes cliente = new Clientes (rs.getString("codCliente"), rs.getString("nombre"), rs.getString("apellidoUno"), rs.getString("apellidoDos"), rs.getString("telefono"), fechaFormateada, rs.getString("direccion"));
                    //rs.getDate("fechaNacimiento").getTime()
                    listaClientes.add(cliente);
                }
                st.close();
                rs.close();
            } catch (Exception ex) {
                listaClientes = null;
            }
        }
        return listaClientes;
    }
    
    public int anadirCliente(String codCliente, String nombre, String apellidoUno, String apellidoDos, String telefono, String fechaNacimiento, String direccion) {
        conexion = FramePrincipal.co.getConexion();
        Clientes cliente = new Clientes();
        FramePrincipal fp = new FramePrincipal();
        
        numReg = -1;
        
        if (conexion != null) {
            try {
//                sentencia = "INSERT INTO CLIENTES VALUES (" + c.getCodCliente() + ", \'" + c.getNombre() + "\', \'" + c.getApellidoUno() + "\', \'" + c.getApellidoDos() + "\'," + c.getTelefono() + ",\'" + new java.sql.Date(c.getFechaNacimiento()) + "\', \'" + c.getDireccion() + "\')";
//                ps = conexion.createStatement();
                sentencia = "INSERT INTO CLIENTES VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conexion.prepareStatement(sentencia);
                ps.setString(1, codCliente);
                ps.setString(2, nombre);
                ps.setString(3, apellidoUno);
                ps.setString(4, apellidoDos);
                ps.setString(5, telefono);
                ps.setString(6, fechaNacimiento);
                //ps.setDate(6, new java.sql.Date(c.getFechaNacimiento()));
                ps.setString(7, direccion);
                
                cliente.setFechaNacimiento(fechaNacimiento);
                if (cliente.getValido() == false) {
                    numReg = -1;
                } else {
                    cliente = new Clientes(codCliente,nombre,apellidoUno,apellidoDos,telefono,fechaNacimiento,direccion);
                    
                    if (cliente.getValido() == false) {
                        numReg = -1;
                    }
                        numReg = ps.executeUpdate();
                
                        fp.limpiarTxt();
                        ps.close();
                    
                }
                

            } catch (SQLException ex) {
                numReg = -1;
                fp.limpiarTxt();
            }
        }
        return numReg;
    }
    
    public int eliminarCliente(String codCliente) {
        FramePrincipal fp = new FramePrincipal();
        conexion = FramePrincipal.co.getConexion();
        numReg = -1;
        
        if (conexion != null) {
            try {
                sentencia = "DELETE FROM CLIENTES WHERE codCliente = ?";
                PreparedStatement ps = conexion.prepareStatement(sentencia);
                ps.setString(1, codCliente);
                
                numReg = ps.executeUpdate();
                
                fp.limpiarTxt();
                ps.close();
            } catch (SQLException ex) {
                numReg = -1;
            }
        }
        return numReg;
    }
    
    public int modificarCliente(String nombre, String apellidoUno, String apellidoDos, String telefono, String fechaNacimiento, String direccion, String parametro) {
        FramePrincipal fp = new FramePrincipal();
        conexion = FramePrincipal.co.getConexion();
        numReg = -1;
        
        if (conexion != null) {
            try {
                sentencia = "UPDATE CLIENTES SET nombre = ?, apellidoUno = ?, apellidoDos = ?, telefono =  ?, fechaNacimiento = ?, direccion = ? WHERE codCliente = ?";
                PreparedStatement ps = conexion.prepareStatement(sentencia);
                ps.setString(1, nombre);
                ps.setString(2, apellidoUno);
                ps.setString(3, apellidoDos);
                ps.setString(4, telefono);
                ps.setString(5, fechaNacimiento);
                ps.setString(6, direccion);
                ps.setString(7, parametro);
                
                numReg = ps.executeUpdate();
                
                fp.limpiarTxt();
                fp.txtCodClienteModificar.setText("");
                
                ps.close();
            } catch (SQLException ex) {
                numReg = -1;
                fp.limpiarTxt();
                fp.txtCodClienteModificar.setText("");
            }
        }
        return numReg;
    }
    
    public int buscarCliente(String opcionCmb, String parametro) {
        FramePrincipal fp = new FramePrincipal();
        conexion = FramePrincipal.co.getConexion();
        numReg = -1;
        
        if (conexion != null) {
            try {                
                sentencia = "SELECT * FROM CLIENTES WHERE " + opcionCmb + " = ?";
                PreparedStatement ps = conexion.prepareStatement(sentencia);
                ps.setString(1, parametro);
                
                numReg = ps.executeUpdate();
                
                //NUEVO
                rs = ps.executeQuery();
                listaClientes.removeAll(listaClientes);

                while (rs.next()) {     
                    Clientes cliente = new Clientes (rs.getString("codCliente"), rs.getString("nombre"), rs.getString("apellidoUno"), rs.getString("apellidoDos"), rs.getString("telefono"), rs.getString("fechaNacimiento"), rs.getString("direccion"));
                    listaClientes.add(cliente);
                }
                //FIN NUEVO
                
                fp.txtParametroABuscar.setText("");
                
                //el rs.close() es nuevo tmb
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                numReg = -1;
            }
        }
        return numReg;
    }
}