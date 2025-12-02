package edu.sistema.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.sistema.dto.DetallePedidoDto;
import edu.sistema.service.DetallePedidoService;
import lombok.AllArgsConstructor;

@CrossOrigin("*")

@AllArgsConstructor
@RestController
@RequestMapping("/api/Detalle")
public class DetallePedidoController {

	  private final DetallePedidoService detallePedidoService;

	    // Obtener todos los detalles de pedidos
	    @GetMapping
	    public ResponseEntity<List<DetallePedidoDto>> getAllDetalles() {
	        return ResponseEntity.ok(detallePedidoService.getAllDetalles());
	    }

	    // Obtener un detalle por su ID
	    @GetMapping("/{id}")
	    public ResponseEntity<DetallePedidoDto> getDetalleById(@PathVariable Integer id) {
	        DetallePedidoDto detalle = detallePedidoService.getDetalleById(id);
	        return ResponseEntity.ok(detalle);
	    }

	
	
}
