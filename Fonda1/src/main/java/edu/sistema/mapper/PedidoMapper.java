package edu.sistema.mapper;

import java.util.stream.Collectors;

import edu.sistema.dto.PedidoDto;
import edu.sistema.entity.Pedido;

public class PedidoMapper {
	  // De Entidad a DTO
	 public static PedidoDto mapToPedidoDto(Pedido pedido) {
	        return new PedidoDto(
	            pedido.getIdPedido(),
	            pedido.getCliente(),
	            pedido.getIdReserva(), //nuevo campo agregado
	            pedido.getTotal(),
	            pedido.getDetalles() != null
	                ? pedido.getDetalles().stream()
	                    .map(DetallePedidoMapper::mapToDetallePedidoDto)
	                    .collect(Collectors.toList())
	                : null
	        );
	    }

	    //De DTO a Entidad
	    public static Pedido mapToPedido(PedidoDto pedidoDto) {
	        Pedido pedido = new Pedido();
	        pedido.setIdPedido(pedidoDto.getIdPedido());
	        pedido.setCliente(pedidoDto.getIdCliente());
	        pedido.setIdReserva(pedidoDto.getIdReserva()); //nuevo campo agregado
	        pedido.setTotal(pedidoDto.getTotal());

	        if (pedidoDto.getDetalles() != null) {
	            pedido.setDetalles(
	                pedidoDto.getDetalles().stream()
	                    .map(detalleDto -> DetallePedidoMapper.mapToDetallePedido(detalleDto))
	                    .peek(detalle -> detalle.setPedido(pedido))
	                    .collect(Collectors.toList())
	            );
	        }

	        return pedido;
	    }
}
