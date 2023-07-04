/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.hcat;
import Services.hcatRepository;
import java.util.List;

/**
 *
 * @author Davs
 */
public class hcatController {
        public List<hcat> getAllCategorias()
    {
        return new hcatRepository().getAllcat();
    }
        public hcat Buscar(int codigo)
     {
         return new hcatRepository().Buscar(codigo);
            
     }
      
    
}
