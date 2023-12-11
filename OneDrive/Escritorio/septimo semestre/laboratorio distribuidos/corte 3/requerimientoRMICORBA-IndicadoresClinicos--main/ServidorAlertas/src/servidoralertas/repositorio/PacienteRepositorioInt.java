/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servidoralertas.repositorio;

import java.util.ArrayList;
import servidoralertas.DTO.AlertaDTO;
import servidoralertas.DTO.IndicadoresDTO;
import servidoralertas.DTO.NotificacionDTO;

/**
 *
 * @author brayan
 */
public interface PacienteRepositorioInt {
    IndicadoresDTO GuardarIndicadores(IndicadoresDTO objIndicadores);
    void guardarInformacionArchivo(IndicadoresDTO objIndicadores,NotificacionDTO objNotificacionint,int puntuacion); 
    ArrayList<AlertaDTO> leerInformacionArchivo(int numHabitacion);
    void guardarNotificacion(NotificacionDTO objNotificacion);
    int cantida();
}
