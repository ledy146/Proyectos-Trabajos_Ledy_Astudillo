/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidoralertas.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author brayan
 */
public class FechaHoraDTO implements Serializable{
     public  LocalDate fechaActual;
  public LocalDateTime horaActual;

    public FechaHoraDTO(LocalDate fechaActual, LocalDateTime horaActual) {
        this.fechaActual = fechaActual;
        this.horaActual = horaActual;
    }
    public FechaHoraDTO() {
        
    }
    public LocalDate getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }

    public LocalDateTime getHoraActual() {
        return horaActual;
    }

    public void setHoraActual(LocalDateTime horaActual) {
        this.horaActual = horaActual;
    }
}
