package edu.sistema.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

	 private Integer idPedido;
	    private Integer idCliente;                       // solo el id del cliente
	    private Integer idReserva;
	    private int total;                          // total del pedido
	    private List<DetallePedidoDto> detalles;         // lista de productos y subtotales
	    
	    
}
