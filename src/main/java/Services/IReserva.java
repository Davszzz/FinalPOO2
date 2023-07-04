/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Model.Reserva;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public interface IReserva {
    List<Reserva> GetAllReservas();
    List<Reserva> GetEntreFechas(String fechai, String fechaf);
    void addReserva(Reserva res);
    void UpdateReserva(Reserva res);
    void RemoveReserva(Reserva res);
    void Completar(int cod);
    void Cancelar(int cod);
    List<Reserva> BuscarporEstancia(int dias);
    
    
}
