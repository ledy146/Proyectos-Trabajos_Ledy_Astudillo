
package servidoralertas.repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import servidoralertas.DTO.AlertaDTO;
import servidoralertas.DTO.IndicadoresDTO;
import servidoralertas.DTO.NotificacionDTO;


/**
 *
 * @author brayan
 */
public class PacienteRepositorioImpl implements PacienteRepositorioInt {
    int contador=0;
    private NotificacionDTO objNotificacion;
    private ArrayList<IndicadoresDTO> Indicadores;
    
    public PacienteRepositorioImpl() {
        this.Indicadores=new ArrayList<>();
        this.objNotificacion=new NotificacionDTO();
    }
    
    @Override
    public IndicadoresDTO GuardarIndicadores(IndicadoresDTO objIndicadores) {
     this.Indicadores.add(objIndicadores);
      mostrar(objIndicadores);
     // determinar();
        return objIndicadores;
    }

    @Override
    public void guardarInformacionArchivo(IndicadoresDTO objIndicadores, NotificacionDTO objNotificacion, int puntuacion) {
      
        System.out.println(":"+objIndicadores.getObjPaciente().getIdentificacion());
        System.out.println("archivo"+objNotificacion.getObjFechaHora().getHoraActual()); 
         System.out.println("archivo"+objNotificacion.getObjFechaHora().getFechaActual()); 
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("historialDeAlertas.txt", true))){
            writer.write(objIndicadores.getObjPaciente().getIdentificacion()+",");
            writer.write(objIndicadores.getObjPaciente().getNombres()+ ",");
            writer.write(objIndicadores.getObjPaciente().getApellidos()+ ",");
            writer.write(objNotificacion.getObjFechaHora().getHoraActual()+ ",");
            writer.write(objNotificacion.getObjFechaHora().getFechaActual()+ ",");
            writer.write(puntuacion + "\n");
        }catch(IOException e){
            System.out.println("Error al abrir el archivo para escritura.");
            e.printStackTrace();
        }    
    }

    @Override
    public ArrayList<AlertaDTO> leerInformacionArchivo(int noIdentificacion) {
        
        int cantidad=0;
        ArrayList<AlertaDTO> listaAlertas = new ArrayList<>();
         ArrayList<AlertaDTO> alertas = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("historialDeAlertas.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int identificacion = Integer.parseInt(parts[0]);
                System.out.println("--------");
                System.out.println("identificacion: "+identificacion);
                System.out.println("--------");
                if (identificacion == noIdentificacion) {
                    AlertaDTO alerta = new AlertaDTO();

                   // alerta.getObjPaciente().setNoHabitacion(identificacion);
                    alerta.getObjFechaHora().setHoraActual(LocalDateTime.parse(parts[3]));
                    alerta.getObjFechaHora().setFechaActual(LocalDate.parse(parts[4]));
                    alerta.setPuntuacion(Integer.parseInt(parts[5])); // Ajusta el índice si es necesario

                    System.out.println("fecha: "+alerta.getObjFechaHora().fechaActual);
                     System.out.println("hora: "+alerta.getObjFechaHora().horaActual);
                      System.out.println("fecha: "+alerta.getPuntuacion());
                    listaAlertas.add(alerta);

                    
                    System.out.println("--------");
                    System.out.println("Cantidad"+listaAlertas.size());
                    System.out.println("--------");
                    cantidad++;
                   
                }
            }
            if(cantidad>5){
                cantidad=5;
            }
            int pos=0;
            this.contador=cantidad;
            this.objNotificacion.setCantidadAlertas(cantidad);
            
            for (int i = listaAlertas.size() - 1; i >= 0; i--) {
                if(pos<5){
                    alertas.add(listaAlertas.get(i));
                    pos++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return alertas;
    
    }

    @Override
    public void guardarNotificacion(NotificacionDTO objNotificacion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int cantida() {
        return this.contador;
    }
    
    
    
    public void mostrar(IndicadoresDTO objIndicadores){
            IndicadoresDTO indicadores=new IndicadoresDTO();
           //indicadores=objIndicadores;
        for(int i=0;i<this.Indicadores.size();i++){
            System.out.println("No.identificacion: "+objIndicadores.getObjPaciente().getIdentificacion());
            System.out.println("nombre: "+objIndicadores.getObjPaciente().getNombres());
            System.out.println("nombre: "+objIndicadores.getObjPaciente().getApellidos());
            System.out.println("edad: "+objIndicadores.getObjPaciente().getEdad());
            System.out.println("frecuenciaCardica: "+objIndicadores.getFrecuenciaCardiaca());
            System.out.println("tension arterial:  "+objIndicadores.getTensionArterialDiastolica());
            System.out.println("tension arterial sistolica: "+objIndicadores.getTensionArterialSistolica());
            System.out.println("frecuencia respiratoria: "+objIndicadores.getFrecuenciaRespiratoria());
            System.out.println("Saturacion de oxigeno: "+objIndicadores.getSaturacionOxigeno());
            System.out.println("temperatura: "+objIndicadores.getTemperatura());
              
        }
            
        
    }
    
}
