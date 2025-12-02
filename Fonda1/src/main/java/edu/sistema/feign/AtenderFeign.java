package edu.sistema.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.sistema.dto.AtenderDto;

@FeignClient(name = "Reservas", contextId = "atenderFeign")
public interface AtenderFeign {

	 @GetMapping("/api/atender/{id}")
	    AtenderDto obtenerPorId(@PathVariable("id") Integer id);
	 
	 @GetMapping("/api/atender/pedido/{idPedido}")
	    AtenderDto obtenerPorPedido(@PathVariable("idPedido") Integer idPedido);
	
}
