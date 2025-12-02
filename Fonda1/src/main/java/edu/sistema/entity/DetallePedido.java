package edu.sistema.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "DetallePedido")
public class DetallePedido {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idDetalle;

	    // Cliente solo se referencia, porque viene de otro microservicio
	    @Column(name = "idCliente")
	    private Integer idCliente;

	    // Relación con Pedido (de esta misma BD)
	    @ManyToOne
	    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
	    private Pedido pedido;
	    
	    // Relación con Producto (nativo en este microservicio)
	    @ManyToOne
	    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
	    private Producto producto;

	    // Cantidad del producto en el pedido
	    @Column(name = "cantidad")
	    private Integer cantidad;

	    // Subtotal = precio * cantidad
	    @Column(name = "subtotal")
	    private int subtotal;

	    @Column(name = "fechaHora")
	    private LocalDateTime fechaHora = LocalDateTime.now();
	
}
