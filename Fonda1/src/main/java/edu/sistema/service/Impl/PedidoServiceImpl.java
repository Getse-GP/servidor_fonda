package edu.sistema.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sistema.dto.ClienteDto;
import edu.sistema.dto.DetallePedidoDto;
import edu.sistema.dto.PedidoDto;
import edu.sistema.dto.ProductoDto;
import edu.sistema.entity.DetallePedido;
import edu.sistema.entity.Pedido;
import edu.sistema.entity.Producto;
import edu.sistema.feign.ClienteFeign;
import edu.sistema.feign.ReservarFeign;
import edu.sistema.mapper.PedidoMapper;
import edu.sistema.repository.DetallePedidoRepository;
import edu.sistema.repository.PedidoRepository;
import edu.sistema.service.PedidoService;
import edu.sistema.service.ProductoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService{
	  
	 private final PedidoRepository pedidoRepository;
	    private final ProductoService productoService;   // Producto nativo
	    private final ClienteFeign clienteFeign;         // Cliente externo
	    private final DetallePedidoRepository detallePedidoRepository;
	    private final ReservarFeign reservarFeign;         // reserva externo
	    
	    @Override
	    public PedidoDto registrarPedido(PedidoDto pedidoDto) {
	        // Verificar cliente
	        ClienteDto clienteDto = clienteFeign.obtenerPorId(pedidoDto.getIdCliente());
	        if (clienteDto == null) {
	            throw new RuntimeException("Cliente no encontrado");
	        }

	        // Crear pedido
	        Pedido pedido = new Pedido();
	        pedido.setCliente(pedidoDto.getIdCliente());
	        pedido.setIdReserva(pedidoDto.getIdReserva()); // Puede ser null

	        int totalPedido = 0;
	        List<DetallePedido> detalles = new ArrayList<>();

	        // Crear detalles
	        for (DetallePedidoDto detalleDto : pedidoDto.getDetalles()) {
	            ProductoDto productoDto = productoService.getProductoById(detalleDto.getIdProducto());
	            if (productoDto == null) {
	                throw new RuntimeException("Producto con ID " + detalleDto.getIdProducto() + " no existe");
	            }

	            DetallePedido detalle = new DetallePedido();
	            detalle.setPedido(pedido);
	            detalle.setIdCliente(pedido.getCliente());
	            detalle.setProducto(new Producto());
	            detalle.getProducto().setIdProducto(productoDto.getIdProducto());
	            detalle.setCantidad(detalleDto.getCantidad());

	            int subtotal = detalleDto.getCantidad() * productoDto.getPrecioP();
	            detalle.setSubtotal(subtotal);

	            totalPedido += subtotal;
	            detalles.add(detalle);
	        }

	        pedido.setTotal(totalPedido);
	        pedido.setDetalles(detalles);

	        // Guardar pedido
	        Pedido savedPedido = pedidoRepository.save(pedido);

	        // 🔹 Si hay reserva asociada, actualizar estatus a "Confirmado"
	        if (pedidoDto.getIdReserva() != null) {
	            try {
	                reservarFeign.actualizarEstatus(pedidoDto.getIdReserva(), "Confirmado");
	            } catch (Exception e) {
	                System.err.println("No se pudo actualizar el estatus de la reserva: " + e.getMessage());
	            }
	        }

	        return PedidoMapper.mapToPedidoDto(savedPedido);
	    }

	    @Override
	    public PedidoDto obtenerPedidoPorId(Integer idPedido) {
	        Pedido pedido = pedidoRepository.findById(idPedido)
	                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + idPedido));
	        return PedidoMapper.mapToPedidoDto(pedido);
	    }

	    @Override
	    public List<PedidoDto> obtenerTodosLosPedidos() {
	        List<Pedido> pedidos = pedidoRepository.findAll();
	        return pedidos.stream()
	                .map(PedidoMapper::mapToPedidoDto)
	                .collect(Collectors.toList());
	    }
	    
	    
	    @Override
	    public List<PedidoDto> buscarPorFecha(LocalDate fecha) {
	        // Traemos todos los detalles de pedido
	        List<DetallePedido> detalles = detallePedidoRepository.findAll();

	        // Obtenemos los pedidos únicos cuya fechaHora coincida con la fecha indicada
	        Set<Pedido> pedidosFiltrados = detalles.stream()
	                .filter(d -> d.getFechaHora() != null && d.getFechaHora().toLocalDate().isEqual(fecha))
	                .map(DetallePedido::getPedido)
	                .collect(Collectors.toSet()); // usamos Set para evitar duplicados

	        return pedidosFiltrados.stream()
	                .map(PedidoMapper::mapToPedidoDto)
	                .collect(Collectors.toList());
	    }
	    
	    
}
