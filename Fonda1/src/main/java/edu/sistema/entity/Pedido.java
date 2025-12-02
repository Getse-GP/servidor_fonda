package edu.sistema.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "Pedido")
public class Pedido {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer idPedido;

	    @Column(name = "idCliente")
	    private Integer cliente;     // solo guardamos idCliente, no la entidad completa
	

	    //total del pedido
	    @Column(name = "total")
	    private int total;
	    
	    // Guardamos solo el ID de la reserva (opcional)
	    @Column(name = "idReserva", nullable = true)
	    private Integer idReserva;

	    //Relacion uno a muchos con DetallePedido
	    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<DetallePedido> detalles;
}
