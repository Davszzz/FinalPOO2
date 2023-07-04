/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.Reserva;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ReservaRepository implements IReserva{

    @Override
    public List<Reserva> GetAllReservas() {
        
        try
        {
            /*Crear arreglo*/
            List<Reserva> lst = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement reserva = Conexion.ObtenerConexion().prepareCall("{CALL ListarReservas()}");
            ResultSet rsres = reserva.executeQuery();
            while(rsres.next())
            {
                Reserva objres = new Reserva();
                objres.setIdReserva(rsres.getInt("idReserva"));
                objres.setIdUsuario(rsres.getInt("idUsuario"));
                objres.setIdHabitacion(rsres.getInt("idHabitacion"));
                objres.setFechap(rsres.getString("Fecha_p"));
                objres.setFechai(rsres.getString("Fecha_i"));
                objres.setFechaf(rsres.getString("Fecha_f"));
                objres.setDiasr(rsres.getInt("Dias_r"));
                objres.setEstancia(rsres.getInt("Estancia"));
                objres.setCoste(rsres.getDouble("Costo"));
                objres.setEstadoR(rsres.getString("EstadoR"));
               
                lst.add(objres);
            }
            Conexion.ObtenerConexion().close();
            rsres.close();
            return lst;
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void addReserva(Reserva res) {
        try
        {
            PreparedStatement sR = Conexion.ObtenerConexion().prepareStatement("{CALL InsertarReserva(?,?,?,?,?,?,?,?,?,?)}");
            sR.setInt(1, res.getIdReserva());/*pasando los datos al parámetro del SP*/
            sR.setInt(2,res.getIdUsuario());
            sR.setInt(3,res.getIdHabitacion());
            sR.setString(4, res.getFechap());/*pasando los datos al parámetro del SP*/
            sR.setString(5, res.getFechai());
            sR.setString(6, res.getFechaf());
            sR.setInt(7,res.getDiasr());
            sR.setInt(8, res.getEstancia());/*pasando los datos al parámetro del SP*/
            sR.setDouble(9, res.getCoste());
            sR.setString(10, res.getEstadoR());
            sR.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public void UpdateReserva(Reserva res) {
        try
        {
            PreparedStatement sR = Conexion.ObtenerConexion().prepareStatement("{CALL UpdateReserva(?,?,?,?,?,?,?,?,?,?)}");
             sR.setInt(1, res.getIdReserva());/*pasando los datos al parámetro del SP*/
            sR.setInt(2,res.getIdUsuario());
            sR.setInt(3,res.getIdHabitacion());
            sR.setString(4, res.getFechap());/*pasando los datos al parámetro del SP*/
            sR.setString(5, res.getFechai());
            sR.setString(6, res.getFechaf());
            sR.setInt(7,res.getDiasr());
            sR.setInt(8, res.getEstancia());/*pasando los datos al parámetro del SP*/
            sR.setDouble(9, res.getCoste());
            sR.setString(10, res.getEstadoR());
            sR.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        } 
    }

    @Override
    public void RemoveReserva(Reserva res) {
         try
            {
                PreparedStatement sHB = Conexion.ObtenerConexion().prepareStatement("{CALL EliminarReserva(?)}");
                sHB.setInt(1, res.getIdReserva());/*pasando los datos al parámetro del SP*/
                sHB.executeUpdate();/*Actualizar la BD*/
            }
            catch(Exception e)
            {
                e.getMessage();
            }   
    }

    @Override
    public List<Reserva> GetEntreFechas(String fechai, String fechaf) {
        ResultSet rs=null;
        try
        {
            /*Crear arreglo*/
            List<Reserva> lstb = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement log = Conexion.ObtenerConexion().prepareCall("{CALL UspBuscarPorFecha(?,?)}");
            log.setString(1, fechai);
            log.setString(2, fechaf);
            rs= log.executeQuery();
            while(rs.next())
            {
                Reserva objres = new Reserva();
                objres.setIdReserva(rs.getInt("idReserva"));
                objres.setIdUsuario(rs.getInt("idUsuario"));
                objres.setIdHabitacion(rs.getInt("idHabitacion"));
                objres.setFechap(rs.getString("Fecha_p"));
                objres.setFechai(rs.getString("Fecha_i"));
                objres.setFechaf(rs.getString("Fecha_f"));
                objres.setDiasr(rs.getInt("Dias_r"));
                objres.setEstancia(rs.getInt("Estancia"));
                objres.setCoste(rs.getDouble("Costo"));
                objres.setEstadoR(rs.getString("EstadoR"));
               
                lstb.add(objres);
            }
            Conexion.ObtenerConexion().close();
            rs.close();
            return lstb;
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public void Completar(int cod) {
            try
            {
                PreparedStatement sHB = Conexion.ObtenerConexion().prepareStatement("{CALL uspCambiarEstadoC(?)}");
                sHB.setInt(1, cod);/*pasando los datos al parámetro del SP*/
                sHB.executeUpdate();/*Actualizar la BD*/
            }
            catch(Exception e)
            {
                e.getMessage();
            }  
    }

    @Override
    public void Cancelar(int cod) {
            try
            {
                PreparedStatement sHB = Conexion.ObtenerConexion().prepareStatement("{CALL uspCambiarEstadoCancelar(?)}");
                sHB.setInt(1, cod);/*pasando los datos al parámetro del SP*/
                sHB.executeUpdate();/*Actualizar la BD*/
            }
            catch(Exception e)
            {
                e.getMessage();
            } 
    }

    @Override
    public List<Reserva> BuscarporEstancia(int dias) {
        ResultSet rs=null;
        try
        {
            /*Crear arreglo*/
            List<Reserva> lstb = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement log = Conexion.ObtenerConexion().prepareCall("{CALL ReservasporDias(?)}");
            log.setInt(1, dias);
            rs= log.executeQuery();
            while(rs.next())
            {
                Reserva objres = new Reserva();
                objres.setIdReserva(rs.getInt("idReserva"));
                objres.setIdUsuario(rs.getInt("idUsuario"));
                objres.setIdHabitacion(rs.getInt("idHabitacion"));
                objres.setFechap(rs.getString("Fecha_p"));
                objres.setFechai(rs.getString("Fecha_i"));
                objres.setFechaf(rs.getString("Fecha_f"));
                objres.setDiasr(rs.getInt("Dias_r"));
                objres.setEstancia(rs.getInt("Estancia"));
                objres.setCoste(rs.getDouble("Costo"));
                objres.setEstadoR(rs.getString("EstadoR"));
               
                lstb.add(objres);
            }
            Conexion.ObtenerConexion().close();
            rs.close();
            return lstb;
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
