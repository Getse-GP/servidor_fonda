package edu.sistema.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sistema.dto.PedidoDto;
import edu.sistema.entity.Pedido;
import edu.sistema.repository.PedidoRepository;
import edu.sistema.service.PedidoService;
import edu.sistema.service.Impl.PedidoPdfService;
import lombok.AllArgsConstructor;

@CrossOrigin("*")

//@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")


//@CrossOrigin(origins = "http://localhost:5173")

@AllArgsConstructor
@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

	 private final PedidoService pedidoService;
	 private final PedidoPdfService pedidoPdfService;
	 private final PedidoRepository pedidoRepository;
	
	 //Crear un pedido
    @PostMapping
    public ResponseEntity<PedidoDto> crearPedido(@RequestBody PedidoDto pedidoDto) {
        PedidoDto guardarPedido = pedidoService.registrarPedido(pedidoDto);
        return new ResponseEntity<>(guardarPedido, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> obtenerPedidoPorId(@PathVariable Integer id) {
        PedidoDto pedidoDto = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedidoDto);
    }
    
    
    @GetMapping
    public ResponseEntity<List<PedidoDto>> listarPedidos() {
        List<PedidoDto> pedidos = pedidoService.obtenerTodosLosPedidos();
        return ResponseEntity.ok(pedidos);
    }
    
    // Generar ticket en PDF
    @GetMapping("/{id}/ticket")
    public ResponseEntity<byte[]> generarTicketPdf(@PathVariable Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        ByteArrayInputStream pdfStream = pedidoPdfService.generarTicketPedido(pedido);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket_pedido_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }
    
    // Buscar pedidos por fecha de los detalles
    @GetMapping("/buscar/fecha")
    public ResponseEntity<List<PedidoDto>> buscarPorFecha(@RequestParam String fecha) {
        LocalDate fechaBuscada = LocalDate.parse(fecha); // formato: yyyy-MM-dd
        List<PedidoDto> pedidos = pedidoService.buscarPorFecha(fechaBuscada);
        return ResponseEntity.ok(pedidos);
    }
    
	
}
