/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.hcat;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davs
 */
public class hcatRepository implements Ihcat {

    @Override
    public List<hcat> getAllcat() {
        {
        try
        {
            /*Crear arreglo*/
            List<hcat> lst = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement cat = Conexion.ObtenerConexion().prepareCall("{CALL uspListarCategoria()}");
            ResultSet rscat = cat.executeQuery();
            while(rscat.next())
            {
                hcat objcat = new hcat();
                objcat.setCod(rscat.getInt("idHcat"));
                objcat.setDescripcion(rscat.getString("Categoria"));
                objcat.setPrecio(rscat.getDouble("Precio"));

                lst.add(objcat);
            }
            Conexion.ObtenerConexion().close();
            rscat.close();
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
    public hcat Buscar(int codigo) {
           ResultSet rs=null;
        try
        {
            CallableStatement log = Conexion.ObtenerConexion().prepareCall("{CALL uspBuscarCategoria(?)}");
            log.setInt(1, codigo);
            rs= log.executeQuery();
            if(rs.next())
            {
                hcat objcat = new hcat();
                objcat.setCod(rs.getInt("idHcat"));
                objcat.setDescripcion(rs.getString("Categoria"));
                objcat.setPrecio(rs.getDouble("Precio"));
                
                return objcat;
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

