package com.eventoapp.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.eventoapp.models.Evento;

//AQUI FAREMOS A POPULACAO DOS DADOS
public interface EventoRepository extends CrudRepository<Evento, String> {
	
}
