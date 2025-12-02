package edu.sistema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtenderDto {

	 private Integer idAtender;
	    private Integer idEmpleado; // referencia a Empleado
	    private Integer idPedido;   // referencia a Pedido (otro microservicio)
}
