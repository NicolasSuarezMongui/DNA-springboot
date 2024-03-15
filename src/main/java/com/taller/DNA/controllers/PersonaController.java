package com.taller.DNA.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.DNA.repositories.entities.Persona;
import com.taller.DNA.responses.CromosomaResponse;
import com.taller.DNA.services.PersonaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/personas")
@AllArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @GetMapping("/")
    public ResponseEntity<List<Persona>> findAll() {
        return ResponseEntity.ok(personaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(personaService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestBody Persona persona) {
        if (persona.getCromosoma().length() != 20){
            return ResponseEntity.badRequest().body("El cromosoma debe tener al menos 20 caracteres");
        } else {
            return ResponseEntity.ok(personaService.save(persona));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Persona persona) {
        try {
            if (persona.getCromosoma().length() != 20){
                return ResponseEntity.badRequest().body("El cromosoma debe tener al menos 20 caracteres");
            }else{
                return ResponseEntity.ok(personaService.update(id, persona));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            personaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    } 

    @GetMapping("/busqueda/{cromosoma}")
    public ResponseEntity<Object> findByCromosoma(@PathVariable String cromosoma) {
        try {
            if (cromosoma.length() != 20){
                return ResponseEntity.badRequest().body("El cromosoma debe tener al menos 20 caracteres");
            } else {
                CromosomaResponse cromosomaResponse = new CromosomaResponse();
                cromosomaResponse.CromosomaCoincidence(cromosoma, personaService.findAll());
                return ResponseEntity.ok(cromosomaResponse);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Algo salió mal. Intente nuevamente.");
        }
    }


}