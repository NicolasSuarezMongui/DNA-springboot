package com.taller.DNA.services;

import java.util.List;

import com.taller.DNA.repositories.entities.Persona;

public interface PersonaService {

    List<Persona> findAll();

    Persona findById(Long id) throws Exception;

    Persona save(Persona persona);

    Persona update(Long id, Persona persona) throws Exception;

    void delete(Long id) throws Exception;

    

}