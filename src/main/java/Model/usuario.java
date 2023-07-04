/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Davs
 */
public class usuario {
    
    int cod;
    int tipo;
    String usuario;
    String correo;
    String contraseña;
    String Telefono;
    String Dni;
    String Genero;

    public usuario() 
    {
        
    }
    
    public usuario(int cod, int tipo, String usuario, String correo, String contraseña, String Telefono, String Dni, String Genero) {
        this.cod = cod;
        this.tipo = tipo;
        this.usuario = usuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.Telefono = Telefono;
        this.Dni = Dni;
        this.Genero = Genero;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }
    
}
