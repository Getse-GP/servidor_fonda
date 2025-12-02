package edu.sistema.service;

import java.util.List;

import edu.sistema.dto.TipoDto;

public interface TipoService {

	//Agregar
		TipoDto createTipo (TipoDto tipoDto);
		
	// Buscar tipo por ID
	TipoDto getTipoById(Integer tipoId);

  // Obtener todos los tipos
   List<TipoDto> getAllTipos();

	  // Actualizar tipo
	   TipoDto updateTipo(Integer tipoId, TipoDto updateTipo);

	  // Eliminar tipo
	   void deleteTipo(Integer tipoId);
		
}
