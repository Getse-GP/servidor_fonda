package edu.sistema.mapper;

import edu.sistema.dto.DetallePedidoDto;
import edu.sistema.entity.DetallePedido;
import edu.sistema.entity.Pedido;
import edu.sistema.entity.Producto;

public class DetallePedidoMapper {
	 // De Entidad a DTO
    public static DetallePedidoDto mapToDetallePedidoDto(DetallePedido detalle) {
        if (detalle == null) return null;

        return new DetallePedidoDto(
                detalle.getIdDetalle(),
                detalle.getIdCliente(),
                detalle.getPedido() != null ? detalle.getPedido().getIdPedido() : null,
                detalle.getFechaHora(),
                detalle.getProducto() != null ? detalle.getProducto().getIdProducto() : null, // solo id
                detalle.getCantidad(),
                detalle.getSubtotal()
        );
    }

    // De DTO a Entidad
    public static DetallePedido mapToDetallePedido(DetallePedidoDto dto) {
        if (dto == null) return null;

        Pedido pedido = null;
        if (dto.getIdPedido() != null) {
            pedido = new Pedido();
            pedido.setIdPedido(dto.getIdPedido());
        }

        // Solo se crea un Producto con id para referencia
        Producto producto = null;
        if (dto.getIdProducto() != null) {
            producto = new Producto();
            producto.setIdProducto(dto.getIdProducto());
        }

        DetallePedido detalle = new DetallePedido();
        detalle.setIdDetalle(dto.getIdDetalle());
        detalle.setIdCliente(dto.getIdCliente());
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setSubtotal(dto.getSubtotal());
        detalle.setFechaHora(dto.getFechaHora());

        return detalle;
    }

}
