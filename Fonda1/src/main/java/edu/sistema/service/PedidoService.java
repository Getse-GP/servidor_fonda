package edu.sistema.service;

import java.time.LocalDate;
import java.util.List;

import edu.sistema.dto.PedidoDto;

public interface PedidoService {

	PedidoDto registrarPedido(PedidoDto pedidoDto);

	//Buscar por id
	PedidoDto obtenerPedidoPorId(Integer idPedido);
	
	//Todos los pedidos
	List<PedidoDto> obtenerTodosLosPedidos();
	
	//Buscar por fecha
	List<PedidoDto> buscarPorFecha(LocalDate fecha);

	
}
