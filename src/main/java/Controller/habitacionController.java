/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.habitacion;
import Services.habitacionRepository;
import java.util.List;
/**
 *
 * @author Davs
 */
public class habitacionController {
       public List<habitacion> getAllRooms()
       {
           return new habitacionRepository().getAllRooms();
       }
         public void addHabitacion(habitacion hb)
        {
            new habitacionRepository().addHabitacion(hb);
        }
        public void Editar(habitacion hb)
        {
            new habitacionRepository().updateHabitacion(hb);
        }
        public void Borrar(habitacion hb)
        {
             new habitacionRepository().removeHabitacion(hb);
        }
        
        public habitacion BuscarCod(int cod)
        {
             return new habitacionRepository().Obtenerhab(cod);    
        }
        public void EditarEstadoT(int cod)
        {
            new habitacionRepository().updateEstadoT(cod);
        }
        public void EditarEstadoF(int cod)
        {
            new habitacionRepository().updateEstadoF(cod);
        }
    
}
