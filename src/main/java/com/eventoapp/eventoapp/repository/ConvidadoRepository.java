package com.eventoapp.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;
import com.eventoapp.eventoapp.models.Convidado;


public interface ConvidadoRepository extends CrudRepository<Convidado, String>{
	//LISTA DE CONVIDADOS
	Iterable<Convidado> findByEventoCodigo(String eventoCodigo);
	//Convidado findByRg(String rg);
}
