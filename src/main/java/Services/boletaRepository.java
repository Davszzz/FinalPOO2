/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.boleta;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davs
 */
public class boletaRepository implements Iboleta{

    @Override
    public List<boleta> getAllBoletas() {
               {
        try
        {
            /*Crear arreglo*/
            List<boleta> lst = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement bol = Conexion.ObtenerConexion().prepareCall("{CALL uspListarPagos()}");
            ResultSet rsbol = bol.executeQuery();
            while(rsbol.next())
            {
                boleta objbol = new boleta();
                objbol.setIdPago(rsbol.getInt("idPago"));
                objbol.setIdReserva(rsbol.getInt("Reserva_idReserva"));
                objbol.setIdUsuario(rsbol.getInt("Reserva_idUsuario"));
                objbol.setIdHabitacion(rsbol.getInt("Reserva_idHabitacion"));
                objbol.setTipo_comprobante(rsbol.getString("tipo_comprobante"));
                objbol.setNum_comprobante(rsbol.getString("num_comprobante"));
                objbol.setIgv(rsbol.getDouble("igv"));
                objbol.setTotal(rsbol.getDouble("total"));
                objbol.setFecha(rsbol.getString("fecha"));
             
                lst.add(objbol);
            }
            Conexion.ObtenerConexion().close();
            rsbol.close();
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
    public void addBoleta(boleta bol) {
        try
        {
            PreparedStatement sB = Conexion.ObtenerConexion().prepareStatement("{CALL AddPago(?,?,?,?,?,?,?,?,?)}");
            sB.setInt(1, bol.getIdPago());
            sB.setInt(2, bol.getIdReserva());
            sB.setInt(3, bol.getIdUsuario());
            sB.setInt(4, bol.getIdHabitacion());/*pasando los datos al parámetro del SP*/
            sB.setString(5,bol.getTipo_comprobante());
            sB.setString(6,bol.getNum_comprobante());
            sB.setDouble(7,bol.getIgv());
            sB.setDouble(8,bol.getTotal());
            sB.setString(9, bol.getFecha());/*pasando los datos al parámetro del SP*/
            sB.executeUpdate();/*Actualizar la BD*/
        }
        catch(Exception e)
        {
            e.getMessage();
        } 
    }

    @Override
    public List<boleta> GetEntreFechas(String fecha1, String fecha2) {
    ResultSet rs=null;
        try
        {
            /*Crear arreglo*/
            List<boleta> lstb = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement log = Conexion.ObtenerConexion().prepareCall("{CALL UspBuscarPorFechaBoleta(?,?)}");
            log.setString(1, fecha1);
            log.setString(2, fecha2);
            rs= log.executeQuery();
            while(rs.next())
            {
                boleta objbol = new boleta();
                objbol.setIdPago(rs.getInt("idPago"));
                objbol.setIdReserva(rs.getInt("Reserva_idReserva"));
                objbol.setIdUsuario(rs.getInt("Reserva_idUsuario"));
                objbol.setIdHabitacion(rs.getInt("Reserva_idHabitacion"));
                objbol.setTipo_comprobante(rs.getString("tipo_comprobante"));
                objbol.setNum_comprobante(rs.getString("tipo_comprobante"));
                objbol.setNum_comprobante(rs.getString("num_comprobante"));
                objbol.setIgv(rs.getDouble("igv"));
                objbol.setTotal(rs.getDouble("total"));
                objbol.setFecha(rs.getString("fecha"));
                
                lstb.add(objbol);
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
    public void updateBoleta(boleta bol) {

    }
    
}
