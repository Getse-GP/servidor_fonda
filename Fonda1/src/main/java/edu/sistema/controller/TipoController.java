package edu.sistema.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sistema.dto.TipoDto;
import edu.sistema.service.TipoService;
import lombok.AllArgsConstructor;

@CrossOrigin("*")

@AllArgsConstructor
@RestController
@RequestMapping("/api/tipo")
public class TipoController {
	
	//Inyeccion de dependencia
		private TipoService tipoService;
		
		//Construir el REST API para agregar un Tipo
		@PostMapping
		public ResponseEntity<TipoDto> crearTipo(@RequestBody TipoDto tipoDto){
			
			TipoDto guardarTipo=tipoService.createTipo(tipoDto);
					return new ResponseEntity<>(guardarTipo, HttpStatus.CREATED);
		}
			
		
		 // Obtener tipo por id
	    @GetMapping("{id}")
	    public ResponseEntity<TipoDto> getTipoById(@PathVariable("id") Integer tipoId){
	        TipoDto tipoDto = tipoService.getTipoById(tipoId);
	        return ResponseEntity.ok(tipoDto);
	    }

	    // Obtener todos los tipos
	    @GetMapping
	    public ResponseEntity<List<TipoDto>> getAllTipos(){
	        List<TipoDto> tipos = tipoService.getAllTipos();
	        return ResponseEntity.ok(tipos);
	    }

	    // Actualizar tipo
	    @PutMapping("{id}")
	    public ResponseEntity<TipoDto> updateTipo(@PathVariable("id") Integer tipoId,
	                                               @RequestBody TipoDto updateTipo){
	        TipoDto tipoDto = tipoService.updateTipo(tipoId, updateTipo);
	        return ResponseEntity.ok(tipoDto);
	    }

	    // Eliminar tipo
	    @DeleteMapping("{id}")
	    public ResponseEntity<String> deleteTipo(@PathVariable("id") Integer tipoId){
	        tipoService.deleteTipo(tipoId);
	        return ResponseEntity.ok("Registro eliminado");
	    }
		
		
		
		

}
