package edu.sistema.service;

import java.util.List;

import edu.sistema.dto.DetallePedidoDto;

public interface DetallePedidoService {

	 List<DetallePedidoDto> getAllDetalles();
	    DetallePedidoDto getDetalleById(Integer id);
	
}
