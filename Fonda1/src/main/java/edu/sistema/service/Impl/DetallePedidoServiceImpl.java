package edu.sistema.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.sistema.dto.DetallePedidoDto;
import edu.sistema.entity.DetallePedido;
import edu.sistema.mapper.DetallePedidoMapper;
import edu.sistema.repository.DetallePedidoRepository;
import edu.sistema.service.DetallePedidoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService{
	
	 private final DetallePedidoRepository detallePedidoRepository;

	    @Override
	    public List<DetallePedidoDto> getAllDetalles() {
	        return detallePedidoRepository.findAll()
	                .stream()
	                .map(DetallePedidoMapper::mapToDetallePedidoDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public DetallePedidoDto getDetalleById(Integer id) {
	        DetallePedido detalle = detallePedidoRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("DetallePedido no encontrado"));
	        return DetallePedidoMapper.mapToDetallePedidoDto(detalle);
	    }

}
