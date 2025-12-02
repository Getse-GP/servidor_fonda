package edu.sistema.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sistema.dto.TipoDto;
import edu.sistema.entity.Tipo;
import edu.sistema.mapper.TipoMapper;
import edu.sistema.repository.TipoRepository;
import edu.sistema.service.TipoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoServiceImpl implements TipoService{
	
	private TipoRepository tipoRepository;
	
	
	//Construir res API para agregar un tipo
		@Override
		public TipoDto createTipo (TipoDto tipoDto) {
			Tipo tipo=TipoMapper.mapToTipo(tipoDto); // mapea DTO a entidad
			Tipo savedTipo= tipoRepository.save(tipo); // guarda en base de datos
			
			return TipoMapper.mapToTipoDto(savedTipo); // devuelve DTO
		}
	
		
	    // Buscar tipo por ID
	    @Override
	    public TipoDto getTipoById(Integer tipoId) {
	        Tipo tipo = tipoRepository.findById(tipoId).orElse(null);
	        return TipoMapper.mapToTipoDto(tipo);
	    }

	    // Obtener todos los tipos
	    @Override
	    public List<TipoDto> getAllTipos() {
	        List<Tipo> tipos = tipoRepository.findAll();
	        return tipos.stream()
	                .map(TipoMapper::mapToTipoDto)
	                .collect(Collectors.toList());
	    }

	    // Actualizar tipo
	    @Override
	    public TipoDto updateTipo(Integer tipoId, TipoDto updateTipo) {
	        Tipo tipo = tipoRepository.findById(tipoId).orElse(null);

	            tipo.setTipo(updateTipo.getTipo());
	            tipo.setDescripcion(updateTipo.getDescripcion());

	            //Guardar cambios
	            Tipo updatedTipoObj = tipoRepository.save(tipo);
	            
	            return TipoMapper.mapToTipoDto(updatedTipoObj);
	        
	    }

	    
	    // Eliminar tipo
	    @Override
	    public void deleteTipo(Integer tipoId) {
	    	//Buscar registro que se desea eliminar
	    	 Tipo tipo = tipoRepository.findById(tipoId).orElse(null);
	    	
	        tipoRepository.deleteById(tipoId);
	    }
		
		
		
		

}
