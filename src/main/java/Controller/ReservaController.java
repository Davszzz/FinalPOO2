/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Reserva;
import Services.ReservaRepository;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class ReservaController {
    
    public List<Reserva>ReservaController()
    {
        return new ReservaRepository().GetAllReservas();
    }
    
    public void addReserva(Reserva rs)
        {
            new ReservaRepository().addReserva(rs);
        }
        public void Editar(Reserva rs)
        {
            new ReservaRepository().UpdateReserva(rs);
        }
        public void Borrar(Reserva rs)
        {
             new ReservaRepository().RemoveReserva(rs);
        }
    
    public List<Reserva>BuscarEntreFechas(String fechai, String fechaf)
    {
        return new ReservaRepository().GetEntreFechas(fechai, fechaf);
    }
    
    public void Completar (int cod)
    {
        new ReservaRepository().Completar(cod);
    }
    
    public void Cancelar (int cod)
    {
        new ReservaRepository().Completar(cod);
    }
    public List<Reserva>Buscarporestancia(int dias)
    {
        return new ReservaRepository().BuscarporEstancia(dias);
    }
    
}
