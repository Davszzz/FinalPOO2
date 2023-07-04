/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Model.habitacion;
import java.util.List;

/**
 *
 * @author Davs
 */
public interface Ihabitacion {
    List<habitacion> getAllRooms();
    void addHabitacion(habitacion hab);
    void removeHabitacion(habitacion hab);
    void updateHabitacion(habitacion hab);
    habitacion Obtenerhab(int cod);
    void updateEstadoT(int cod);
    void updateEstadoF(int cod);

    
}
