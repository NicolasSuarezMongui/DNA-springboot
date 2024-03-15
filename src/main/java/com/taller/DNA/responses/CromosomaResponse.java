package com.taller.DNA.responses;

import java.util.List;

import com.taller.DNA.repositories.entities.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CromosomaResponse {
    
    private Persona persona;
    private double porcentaje;

    public void CromosomaCoincidence(String dna, List<Persona> personas){
        int coincidencias, max=0;
        Persona aux = null;
        for (Persona p : personas){
            coincidencias = 0;
            for (int i=0; i < dna.length(); i++){
                if (dna.charAt(i) == p.getCromosoma().charAt(i)) {
                    coincidencias++;
                }
            }
            if (coincidencias > max){
                max = coincidencias;
                aux = p;
            }
        }
        this.porcentaje = (double) max / dna.length() * 100;
        this.persona = aux;
    }

}
