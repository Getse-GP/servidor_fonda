package edu.sistema.mapper;

import edu.sistema.dto.ProductoDto;
import edu.sistema.entity.Producto;
import edu.sistema.entity.Tipo;

public class ProductoMapper {

	public static ProductoDto mapToProductoDto(Producto producto) {
        // Si el producto tiene un tipo asignado, toma su id. Si no, deja el id como null.
        Integer idTipo = producto.getTipo() != null ? producto.getTipo().getIdTipo() : null;

        return new ProductoDto(
                producto.getIdProducto(),
                producto.getNombreProducto(),
                producto.getDescripcionP(),
                producto.getPrecioP(),
                producto.getEstatus(),
                idTipo,
                null, // fotoProducto (no se usa al convertir desde entidad)
                producto.getFotoProductoNombre() // nombre de la imagen
        );
    }
		    public static Producto mapToProducto(ProductoDto productoDto) {
		        // Si el DTO tiene un idTipo asignado, crea una instancia de Tipo con ese id
		        Tipo tipo = productoDto.getIdTipo() != null ? new Tipo(productoDto.getIdTipo(), null, null) : null;

		        return new Producto(
		                productoDto.getIdProducto(),
		                productoDto.getNombreProducto(),
		                productoDto.getDescripcionP(),
		                productoDto.getPrecioP(),
		                productoDto.getEstatus(), 
		                tipo,
		                productoDto.getFotoProducto(), // archivo subido
		                productoDto.getFotoProductoNombre() // nombre del archivo
		        );
		    }
	
}
