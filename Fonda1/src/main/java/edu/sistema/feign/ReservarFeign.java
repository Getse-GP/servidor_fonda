package edu.sistema.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import edu.sistema.dto.ReservarDto;

@FeignClient(name = "Reservas", contextId = "reservarFeign")
public interface ReservarFeign {

	 @GetMapping("/api/reservar/{id}")
	    ReservarDto obtenerPorId(@PathVariable("id") Integer id);
	

	    //Nuevo metodo para actualizar el estatus de la reserva
	    @PutMapping("/api/reservar/{id}/estatus/{nuevoEstatus}")
	    ReservarDto actualizarEstatus(@PathVariable("id") Integer id,
	                                  @PathVariable("nuevoEstatus") String nuevoEstatus);
	 
}
