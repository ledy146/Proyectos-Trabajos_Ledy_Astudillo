/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidorusuarios.impl;
import java.util.HashMap;
import servidorusuarios_sop_corba.GestionPacientesPOA;
import servidorusuarios_sop_corba.GestionPacientesPackage.pacienteDTO;

/**
 *
 * @author ledya
 */
public class GestionPacientesImpl extends GestionPacientesPOA {
    private HashMap<Integer, pacienteDTO> pacientes;

    public GestionPacientesImpl() {
        this.pacientes = new HashMap();
    }

    
    @Override
    public boolean registrarPaciente(pacienteDTO objPaciente) {
        System.out.println("Invocando a registrar paciente");
        boolean bandera = false;
       
        if (!pacientes.containsKey(objPaciente.identificacion)&&objPaciente!=null) {

            pacientes.put(objPaciente.identificacion, objPaciente);
            bandera = true; 
            System.out.println("registrado");
        }
        return bandera;
    }
    @Override
    public pacienteDTO buscarPaciente(int noIdentificacion) {
        pacienteDTO objPaciente = new pacienteDTO(-1, "", "",-1);
        if (this.pacientes.get(noIdentificacion) != null) {
            objPaciente = this.pacientes.get(noIdentificacion);
        }
        return objPaciente;
    }
    

   

    
}
