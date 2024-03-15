package com.taller.DNA.repositories;

import org.springframework.data.repository.CrudRepository;

import com.taller.DNA.repositories.entities.Persona;

public interface RepositoryPersona extends CrudRepository<Persona, Long> {
}