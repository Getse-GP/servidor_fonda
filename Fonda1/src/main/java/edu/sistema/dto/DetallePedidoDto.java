package edu.sistema.dto;

import java.time.LocalDateTime;

import edu.sistema.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedidoDto {

	 private Integer idDetalle;
	    private Integer idCliente;   // solo el id del cliente, ya que vive en otro microservicio
	    private Integer idPedido;    // id del pedido
	    private LocalDateTime fechaHora;
	
	    private Integer idProducto;      // producto asociado a este detalle
	    private Integer cantidad;        // cantidad de ese producto
	    private int subtotal;  
}
