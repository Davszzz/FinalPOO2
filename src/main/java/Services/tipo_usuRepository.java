/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Model.tipo_usu;
import Util.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davs
 */
public class tipo_usuRepository implements Itipo_usu{

    @Override
    public List<tipo_usu> getAllTipos() {
                {
        try
        {
            /*Crear arreglo*/
            List<tipo_usu> lst = new ArrayList<>();
            /*Llamar funcion listar del sql*/
            CallableStatement tipo = Conexion.ObtenerConexion().prepareCall("{CALL uspListarTipo()}");
            ResultSet rstipo = tipo.executeQuery();
            while(rstipo.next())
            {
                tipo_usu objtipo = new tipo_usu();
                objtipo.setId(rstipo.getInt("idTipo_usu"));
                objtipo.setTipo(rstipo.getString("Tipo"));

                lst.add(objtipo);
            }
            Conexion.ObtenerConexion().close();
            rstipo.close();
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
    public tipo_usu Buscar(int codigo) {
        ResultSet rs=null;
        try
        {
            CallableStatement log = Conexion.ObtenerConexion().prepareCall("{CALL uspBuscarTipo(?)}");
            log.setInt(1, codigo);
            rs= log.executeQuery();
            if(rs.next())
            {
                tipo_usu objtipo = new tipo_usu();
                objtipo.setId(rs.getInt("idTipo_usu"));
                objtipo.setTipo(rs.getString("Tipo"));
                
                return objtipo;
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
    
