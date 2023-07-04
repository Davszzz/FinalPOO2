/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;
import java.util.List;
import Model.boleta;

/**
 *
 * @author Davs
 */
public interface Iboleta {
    List<boleta> getAllBoletas();
    List<boleta> GetEntreFechas(String fechai, String fechaf);
    void addBoleta(boleta bol);
    void updateBoleta(boleta bol);
    
    
}
