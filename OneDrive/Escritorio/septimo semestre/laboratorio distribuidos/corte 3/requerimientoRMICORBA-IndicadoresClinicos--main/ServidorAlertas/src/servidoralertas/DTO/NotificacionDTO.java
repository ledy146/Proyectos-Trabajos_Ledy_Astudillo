/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidoralertas.DTO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author brayan
 */
public class NotificacionDTO implements Serializable {
   private FechaHoraDTO objFechaHora;
   private PacienteDTO objPaciente ;
   private IndicadoresDTO objIndicador;
   private ArrayList<AlertaDTO> alertas;
   private String mensaje;
   private int cantidadAlertas;

    public NotificacionDTO(FechaHoraDTO objFechaHora, PacienteDTO objPaciente, IndicadoresDTO objIndicador, int cantidadAlertas) {
        this.objFechaHora = objFechaHora;
        this.objPaciente = objPaciente;
        this.objIndicador = objIndicador;
        this.cantidadAlertas = cantidadAlertas;
        this.alertas=new ArrayList<>();
    }

   
    public NotificacionDTO(){
        this.objIndicador=new IndicadoresDTO();
        this.objFechaHora=new FechaHoraDTO();
        this.objPaciente=new PacienteDTO();  
    }

    public ArrayList<AlertaDTO> getAlertas() {
        return alertas;
    }

    public void setAlertas(ArrayList<AlertaDTO> alertas) {
        this.alertas = alertas;
    }
    
  
   
    public FechaHoraDTO getObjFechaHora() {
        return objFechaHora;
    }

    public void setObjFechaHora(FechaHoraDTO objFechaHora) {
        this.objFechaHora = objFechaHora;
    }

    public PacienteDTO getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(PacienteDTO objPaciente) {
        this.objPaciente = objPaciente;
    }

    public IndicadoresDTO getObjIndicador() {
        return objIndicador;
    }

    public void setObjIndicador(IndicadoresDTO objIndicador) {
        this.objIndicador = objIndicador;
    }

   
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCantidadAlertas() {
        return cantidadAlertas;
    }

    public void setCantidadAlertas(int cantidadAlertas) {
        this.cantidadAlertas = cantidadAlertas;
    }
    
}
