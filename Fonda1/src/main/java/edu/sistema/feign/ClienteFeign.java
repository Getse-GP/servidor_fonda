package edu.sistema.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.sistema.dto.ClienteDto;

@FeignClient(name = "Restaurante-1")
public interface ClienteFeign {

	 @GetMapping("/api/cliente/{id}")
	    ClienteDto obtenerPorId(@PathVariable("id") Integer id);
	
}
