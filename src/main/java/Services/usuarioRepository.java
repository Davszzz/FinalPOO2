/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.usuario;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davs
 */
public class usuarioRepository implements Iusuario {

    @Override
    public List<usuario> getAllUsers() {
        
        try
        {
            /*Crear arreglo*/
            List<usuario> lst = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement usu = Conexion.ObtenerConexion().prepareCall("{CALL uspListarUsuario()}");
            ResultSet rsusu = usu.executeQuery();
            while(rsusu.next())
            {
                usuario objusu = new usuario();
                objusu.setCod(rsusu.getInt("idUsuario"));
                objusu.setTipo(rsusu.getInt("Tipo_usu_idTipo_usu"));
                objusu.setUsuario(rsusu.getString("Usuario"));
                objusu.setCorreo(rsusu.getString("Correo"));
                objusu.setContraseña(rsusu.getString("contra"));
                objusu.setTelefono(rsusu.getString("Telofono"));
                objusu.setDni(rsusu.getString("Dni"));
                objusu.setGenero(rsusu.getString("Genero"));
                
                lst.add(objusu);
            }
            Conexion.ObtenerConexion().close();
            rsusu.close();
            return lst;
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void addUser(usuario user) {
        
        try
        {
            PreparedStatement usu = Conexion.ObtenerConexion().prepareStatement("{CALL UspInsertarUsuario(?,?,?,?,?,?,?,?)}");/*pasando los datos al parámetro del SP*/
            usu.setInt(1, user.getCod());
            usu.setInt(2, user.getTipo());
            usu.setString(3,user.getUsuario());
            usu.setString(4,user.getCorreo());
            usu.setString(5,user.getContraseña());
            usu.setString(6, user.getTelefono());
            usu.setString(7, user.getDni());
            usu.setString(8, user.getGenero());
            usu.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    } 

    @Override
    public void removeUser(usuario user) {
        try
        {
            PreparedStatement usu = Conexion.ObtenerConexion().prepareStatement("{CALL UspEliminarUsuario(?)}");
            usu.setInt(1, user.getCod());/*pasando los datos al parámetro del SP*/
            usu.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public void updateUser(usuario user) {
        try
        {
            PreparedStatement sH = Conexion.ObtenerConexion().prepareStatement("{CALL uspUpdateUsuario(?,?,?,?,?,?,?,?)}");
            sH.setInt(1, user.getCod());/*pasando los datos al parámetro del SP*/
            sH.setInt(2,user.getTipo());
            sH.setString(3, user.getUsuario());/*pasando los datos al parámetro del SP*/
            sH.setString(4,user.getCorreo());
            sH.setString(5,user.getContraseña());
            sH.setString(6,user.getTelefono());
            sH.setString(7,user.getDni());
            sH.setString(8,user.getGenero()); /*pasando los datos al parámetro del SP*/
            
            sH.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        
    }

    @Override
    public boolean EstadoLogin(String usu, String pas) {
        ResultSet rs=null;
        try
        {
            CallableStatement log2 = Conexion.ObtenerConexion().prepareCall("{CALL uspLogin(?,?)}");
            log2.setString(1, usu);
            log2.setString(2, pas);
            rs= log2.executeQuery();
            if(rs.next())
            {
                return true;
            }
            Conexion.ObtenerConexion().close();
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return false;
    }

    @Override
    public int ObtenerCodigo(String usu) {
       ResultSet rs=null;
        try
        {
            CallableStatement cod = Conexion.ObtenerConexion().prepareCall("{CALL Uspbuscarpornombre(?)}");
            cod.setString(1, usu);
            rs= cod.executeQuery();
            if(rs.next())
            {
                usuario objcat = new usuario();
                objcat.setCod(rs.getInt("idUsuario"));
                
                return objcat.getCod();
            }
            Conexion.ObtenerConexion().close();
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return -1;
    }

    @Override
    public usuario ObjetoLogin(String usu, String pas) {
        try
        {
            CallableStatement log1 = Conexion.ObtenerConexion().prepareCall("{CALL uspLogin(?,?)}");
            log1.setString(1,usu);
            log1.setString(2,pas); /*pasando los datos al parámetro del SP*/
            ResultSet rs = log1.executeQuery();
            if(rs.next())
            {
                usuario objusu = new usuario();
                objusu.setCod(rs.getInt("idUsuario"));
                objusu.setTipo(rs.getInt("Tipo_usu_idTipo_usu"));
                objusu.setUsuario(rs.getString("Usuario"));
                objusu.setCorreo(rs.getString("Correo"));
                objusu.setContraseña(rs.getString("Contra"));
                objusu.setContraseña(rs.getString("Telofono"));
                objusu.setDni(rs.getString("Dni"));
                objusu.setGenero(rs.getString("Genero"));
                
                return objusu;
            }
            Conexion.ObtenerConexion().close();
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return null;
    }  
}
