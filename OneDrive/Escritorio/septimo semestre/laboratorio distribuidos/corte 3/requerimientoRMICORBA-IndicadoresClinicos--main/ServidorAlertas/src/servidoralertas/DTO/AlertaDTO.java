
package servidoralertas.DTO;

import java.io.Serializable;

/**
 *
 * @author brayan
 */
public class AlertaDTO implements Serializable {
    public FechaHoraDTO objFechaHora;
    private int puntuacion;
    
    public AlertaDTO(FechaHoraDTO objFechaHora, int puntuacion) {
        this.objFechaHora = objFechaHora;
        this.puntuacion = puntuacion;
    }

    public AlertaDTO() {
        this.objFechaHora=new FechaHoraDTO();
    }

    public FechaHoraDTO getObjFechaHora() {
        return objFechaHora;
    }

    public void setObjFechaHora(FechaHoraDTO objFechaHora) {
        this.objFechaHora = objFechaHora;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
