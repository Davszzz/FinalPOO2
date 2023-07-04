/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Model.tipo_usu;
import java.util.List;

/**
 *
 * @author Davs
 */
public interface Itipo_usu {
    List<tipo_usu> getAllTipos();
    tipo_usu Buscar(int codigo);
}
