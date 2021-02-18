package Modelo;

import Vista.FramePrincipal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Clientes {
    private String nombre, apellidoUno, apellidoDos, direccion, fechaNacimiento,codCliente, telefono;
    private boolean valido = true;
    private static FramePrincipal fp;
    
    public Clientes() {}
    
    public Clientes (String codCliente, String nombre, String apellidoUno, String apellidoDos, String telefono, String fechaNacimiento, String direccion) {
//        this.codCliente = codCliente;
//        this.nombre = nombre;
//        this.apellidoUno = apellidoUno;
//        this.apellidoDos = apellidoDos;
//        this.telefono = telefono;
//        this.fechaNacimiento = fechaNacimiento;
//        this.direccion = direccion;
        setCodCliente(codCliente);
        setNombre(nombre);
        setApellidoUno(apellidoUno);
        setApellidoDos(apellidoDos);
        setTelefono(telefono);
        this.fechaNacimiento = fechaNacimiento;
        setDireccion(direccion);
    }
    
    public String getCodCliente() {return codCliente;}
    public String getNombre() {return nombre;}
    public String getApellidoUno() {return apellidoUno;}
    public String getApellidoDos() {return apellidoDos;}
    public String getTelefono() {return telefono;}
    public String getFechaNacimiento() {return fechaNacimiento;}
    public String getDireccion() {return direccion;}
    
    public boolean getValido() {return valido;}

    
    public void setValido(boolean valido) {
        this.valido = valido;
    } 
    
    public void setCodCliente(String codCliente){
        FramePrincipal fp = new FramePrincipal();
        
        if (codCliente.length() > 20) {
            JOptionPane.showMessageDialog(null, "El código de cliente tiene que tener menos de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtCodigo.setText("");
            setValido(false);
        } else if (!(codCliente.matches("\\d+"))) {
            JOptionPane.showMessageDialog(null, "El código de cliente sólo puede contener dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtCodigo.setText("");
            setValido(false);
        } else {
            this.codCliente = codCliente;
        }
    }
    
    public void setNombre(String nombre){
        FramePrincipal fp = new FramePrincipal();
        
        if (nombre.length() > 20) {
            JOptionPane.showMessageDialog(null, "El nombre tiene que tener menos de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtNombre.setText("");
            setValido(false);
        } else  if (!(nombre.matches("[a-zA-ZñáéíóúÁÉÍÓÚ\\s]+"))) {
            JOptionPane.showMessageDialog(null, "El nombre sólo puede contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtNombre.setText("");
            setValido(false);
        } else {
            this.nombre = nombre;
        }
    }
    
    public void setApellidoUno(String apellidoUno){
        FramePrincipal fp = new FramePrincipal();
        
        if (apellidoUno.length() > 20) {
            JOptionPane.showMessageDialog(null, "El primer apellido tiene que tener menos de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtPrimerApellido.setText("");
            setValido(false);
        } else  if (!(nombre.matches("[a-zA-ZñáéíóúÁÉÍÓÚ\\s]+"))) {
            JOptionPane.showMessageDialog(null, "El primer apellido sólo puede contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtPrimerApellido.setText("");
            setValido(false);
        } else {
            this.apellidoUno = apellidoUno;
        }
    }
    
    public void setApellidoDos(String apellidoDos){
        FramePrincipal fp = new FramePrincipal();
        
        if (apellidoDos.length() > 20) {
            JOptionPane.showMessageDialog(null, "El segundo apellido tiene que tener menos de 20 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtSegundoApellido.setText("");
            setValido(false);
        } else  if (!(nombre.matches("[a-zA-ZñáéíóúÁÉÍÓÚ\\s]+"))) {
            JOptionPane.showMessageDialog(null, "El segundo apellido sólo puede contener letras.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtSegundoApellido.setText("");
            setValido(false);
        } else {
            this.apellidoDos = apellidoDos;
        }
    }
    
    public void setTelefono(String telefono){
        FramePrincipal fp = new FramePrincipal();
        
        if (!(telefono.matches("\\d+"))) {
            JOptionPane.showMessageDialog(null, "El número de teléfono sólo puede contener números.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtTelefono.setText("");
            setValido(false);
        } else if (!(telefono.length() == 9)) {
            JOptionPane.showMessageDialog(null, "El número de teléfono tiene que tener 9 números.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtTelefono.setText("");
            setValido(false);
        } else {
            this.telefono = telefono;
        }
    }
    
    public void setFechaNacimiento(String fechaNacimiento){
        FramePrincipal fp = new FramePrincipal();
        
        if (fechaNacimiento.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {          
            String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String [] vectorFechaActual = fechaActual.split("/");
            String stringAnioActual = vectorFechaActual[2];
            int anioActual = Integer.parseInt(stringAnioActual);

            String [] vectorFechaNacimiento = fechaNacimiento.split("/");
            String stringAnioNacimiento = vectorFechaNacimiento[2];
            int anioNacimiento = Integer.parseInt(stringAnioNacimiento);
            
            if (anioNacimiento >= anioActual) {
                JOptionPane.showMessageDialog(null, "El cliente no ha nacido aún.", "Error", JOptionPane.ERROR_MESSAGE);
                fp.txtFechaNacimiento.setText("");
                setValido(false);
            } else if (anioNacimiento < 1900) {
                JOptionPane.showMessageDialog(null, "El cliente tiene que haber nacido a partir del año 1900.", "Error", JOptionPane.ERROR_MESSAGE);
                fp.txtFechaNacimiento.setText("");
                setValido(false);
            }            
            this.fechaNacimiento = fechaNacimiento;
        } else {
            JOptionPane.showMessageDialog(null, "La fecha introducida no es válida. El formato es dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtFechaNacimiento.setText("");
            setValido(false);
        }        
    }
    
    public void setDireccion(String direccion){
        FramePrincipal fp = new FramePrincipal();
        
        if (direccion.length() > 120) {
            JOptionPane.showMessageDialog(null, "La dirección tiene que tener menos de 120 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            fp.txtDireccion.setText("");
            setValido(false);
        } else {
            this.direccion = direccion;
        }
    }
}