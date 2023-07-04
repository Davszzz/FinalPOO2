/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.boleta;
import Services.boletaRepository;
import java.util.List;

/**
 *
 * @author Davs
 */
public class boletaController {
    
    public List<boleta> getAllBoletas()
       {
           return new boletaRepository().getAllBoletas();
       }
         public void addBoleta(boleta bol)
        {
            new boletaRepository().addBoleta(bol);
        }
        public void Editar(boleta bol)
        {
            new boletaRepository().updateBoleta(bol);
        }
    public List<boleta>BuscarEntreFechas(String fecha1, String fecha2)
    {
        return new boletaRepository().GetEntreFechas(fecha1, fecha2);
    }
    
}
