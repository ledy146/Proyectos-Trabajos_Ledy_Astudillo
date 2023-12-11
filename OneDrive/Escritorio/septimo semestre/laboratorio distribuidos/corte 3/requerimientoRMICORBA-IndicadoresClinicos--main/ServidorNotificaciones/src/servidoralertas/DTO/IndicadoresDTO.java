/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidoralertas.DTO;

import java.io.Serializable;

/**
 *
 * @author brayan
 */
public class IndicadoresDTO  implements Serializable{
     private int frecuenciaCardiaca;
    private int tensionArterialSistolica;
    private int tensionArterialDiastolica;
    private int frecuenciaRespiratoria;
    private int saturacionOxigeno;
    private double temperatura; 
    private PacienteDTO objPaciente;

    public IndicadoresDTO(int frecuenciaCardiaca, int tensionArterialSistolica, int tensionArterialDiastolica, int frecuenciaRespiratoria, int saturacionOxigeno, double temperatura,PacienteDTO objPaciente) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.tensionArterialSistolica = tensionArterialSistolica;
        this.tensionArterialDiastolica = tensionArterialDiastolica;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.saturacionOxigeno = saturacionOxigeno;
        this.temperatura = temperatura;
        this.objPaciente=objPaciente;
    }

    public IndicadoresDTO() {
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getTensionArterialSistolica() {
        return tensionArterialSistolica;
    }

    public void setTensionArterialSistolica(int tensionArterialSistolica) {
        this.tensionArterialSistolica = tensionArterialSistolica;
    }

    public int getTensionArterialDiastolica() {
        return tensionArterialDiastolica;
    }

    public void setTensionArterialDiastolica(int tensionArterialDiastolica) {
        this.tensionArterialDiastolica = tensionArterialDiastolica;
    }

    public int getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public int getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(int saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public PacienteDTO getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(PacienteDTO objPaciente) {
        this.objPaciente = objPaciente;
    }
}
