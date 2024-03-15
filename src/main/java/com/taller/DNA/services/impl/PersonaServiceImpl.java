package com.taller.DNA.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.DNA.repositories.RepositoryPersona;
import com.taller.DNA.repositories.entities.Persona;
import com.taller.DNA.services.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    private RepositoryPersona repositoryPersona;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return (List<Persona>) repositoryPersona.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById(Long id) throws Exception {
        return repositoryPersona.findById(id).orElseThrow(() -> new Exception("Persona no encontrada"));
    }

    @Override
    @Transactional
    public Persona save(Persona persona) {
        return repositoryPersona.save(persona);
    }

    @Override
    @Transactional
    public Persona update(Long id, Persona persona) throws Exception {
        findById(id);
        persona.setId(id);
        return repositoryPersona.save(persona);
    }

    @Override
    public void delete(Long id) throws Exception {
        repositoryPersona.delete(findById(id));
    }



}