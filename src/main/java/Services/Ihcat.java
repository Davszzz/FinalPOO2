/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Model.hcat;
import java.util.List;

/**
 *
 * @author Davs
 */
public interface Ihcat {
    List<hcat> getAllcat();
    hcat Buscar(int codigo);
    
}
