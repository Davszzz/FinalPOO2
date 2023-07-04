/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Gabriel
 */
public class Reserva {
    
    private int idReserva;
    private int idUsuario;
    private int idHabitacion;
    private String fechap;
    private String fechai;
    private String fechaf;
    private int Diasr;
    private int Estancia;
    private double Coste;
    private String EstadoR;

    public Reserva() {
    }

    public Reserva(int idReserva, int idUsuario, int idHabitacion, String fechap, String fechai, String fechaf, int Diasr, int Estancia, double Coste, String EstadoR) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idHabitacion = idHabitacion;
        this.fechap = fechap;
        this.fechai = fechai;
        this.fechaf = fechaf;
        this.Diasr = Diasr;
        this.Estancia = Estancia;
        this.Coste = Coste;
        this.EstadoR = EstadoR;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getFechap() {
        return fechap;
    }

    public void setFechap(String fechap) {
        this.fechap = fechap;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public int getDiasr() {
        return Diasr;
    }

    public void setDiasr(int Diasr) {
        this.Diasr = Diasr;
    }

    public int getEstancia() {
        return Estancia;
    }

    public void setEstancia(int Estancia) {
        this.Estancia = Estancia;
    }

    public double getCoste() {
        return Coste;
    }

    public void setCoste(double Coste) {
        this.Coste = Coste;
    }

    public String getEstadoR() {
        return EstadoR;
    }

    public void setEstadoR(String EstadoR) {
        this.EstadoR = EstadoR;
    }
    
    
}
