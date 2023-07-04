/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.tipo_usu;
import Services.tipo_usuRepository;
import java.util.List;

/**
 *
 * @author Davs
 */
public class tipo_usuController {
    public List<tipo_usu> getAllCategorias()
    {
        return new tipo_usuRepository().getAllTipos();
    }
    public tipo_usu Buscar(int codigo)
    {
        return new tipo_usuRepository().Buscar(codigo);
            
    }
}
