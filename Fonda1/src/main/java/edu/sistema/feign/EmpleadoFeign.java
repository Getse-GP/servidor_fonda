package edu.sistema.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.sistema.dto.EmpleadoDto;

@FeignClient(name = "Reservas", contextId = "empleadoFeign")
public interface EmpleadoFeign {

	 @GetMapping("/api/empleado/{id}")
	    EmpleadoDto obtenerPorId(@PathVariable("id") Integer id);
	
}
