/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.habitacion;
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
public class habitacionRepository implements Ihabitacion{

    @Override
    public List<habitacion> getAllRooms() {
                {
        try
        {
            /*Crear arreglo*/
            List<habitacion> lst = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement hab = Conexion.ObtenerConexion().prepareCall("{CALL uspListarHabitacion()}");
            ResultSet rshab = hab.executeQuery();
            while(rshab.next())
            {
                habitacion objhab = new habitacion();
                objhab.setCod(rshab.getInt("idHabitacion"));
                objhab.setCodc(rshab.getInt("Hcat_idHcat"));
                objhab.setNumero(rshab.getString("NumeroH"));
                objhab.setEstado(rshab.getBoolean("EstadoH"));
                objhab.setDescripcion(rshab.getString("Descripcion"));
             
                lst.add(objhab);
            }
            Conexion.ObtenerConexion().close();
            rshab.close();
            return lst;
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return null;
    }
    }

    @Override
    public void addHabitacion(habitacion hab) {
         try
        {
            PreparedStatement sH = Conexion.ObtenerConexion().prepareStatement("{CALL AddHabitacion(?,?,?,?,?)}");
            sH.setInt(1, hab.getCod());/*pasando los datos al parámetro del SP*/
            sH.setInt(2,hab.getCodc());
            sH.setString(3, hab.getNumero());/*pasando los datos al parámetro del SP*/
            sH.setBoolean(4, hab.isEstado());
            sH.setString(5, hab.getDescripcion());/*pasando los datos al parámetro del SP*/
            sH.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        } 
    }

    @Override
    public void removeHabitacion(habitacion hab) {
            try
            {
                PreparedStatement sHB = Conexion.ObtenerConexion().prepareStatement("{CALL EliminarHabitacion(?)}");
                sHB.setInt(1, hab.getCod());/*pasando los datos al parámetro del SP*/
                sHB.executeUpdate();/*Actualizar la BD*/
            }
            catch(Exception e)
            {
                e.getMessage();
            }   
    }

    @Override
    public void updateHabitacion(habitacion hab) {
        try
        {
            PreparedStatement sH = Conexion.ObtenerConexion().prepareStatement("{CALL UspUpdateHabitacion(?,?,?,?,?)}");
            sH.setInt(1, hab.getCod());/*pasando los datos al parámetro del SP*/
            sH.setInt(2,hab.getCodc());
            sH.setString(3, hab.getNumero());/*pasando los datos al parámetro del SP*/
            sH.setBoolean(4, hab.isEstado());/*pasando los datos al parámetro del SP*/
            sH.setString(5, hab.getDescripcion());
            sH.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public habitacion Obtenerhab(int cod) {
        ResultSet rs=null;
        try
        {
            CallableStatement log = Conexion.ObtenerConexion().prepareCall("{CALL uspBuscarHabitacion(?)}");
            log.setInt(1, cod);
            rs= log.executeQuery();
            if(rs.next())
            {
                habitacion objh = new habitacion();
                objh.setCod(rs.getInt("idHabitacion"));
                objh.setCodc(rs.getInt("Hcat_idHcat"));
                objh.setNumero(rs.getString("NumeroH"));
                objh.setEstado(rs.getBoolean("EstadoH"));
                objh.setDescripcion(rs.getString("Descripcion"));
                
                return objh;
            }
            Conexion.ObtenerConexion().close();
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return null;
    }   

    @Override
    public void updateEstadoT(int cod) {
             try
            {
                PreparedStatement sHB = Conexion.ObtenerConexion().prepareStatement("{CALL uspCambiarEstadoT(?)}");
                sHB.setInt(1, cod);/*pasando los datos al parámetro del SP*/
                sHB.executeUpdate();/*Actualizar la BD*/
            }
            catch(Exception e)
            {
                e.getMessage();
            }  
    }

    @Override
    public void updateEstadoF(int cod) {
             try
            {
                PreparedStatement sHB = Conexion.ObtenerConexion().prepareStatement("{CALL uspCambiarEstadoF(?)}");
                sHB.setInt(1, cod);/*pasando los datos al parámetro del SP*/
                sHB.executeUpdate();/*Actualizar la BD*/
            }
            catch(Exception e)
            {
                e.getMessage();
            }  
    }
}
