package edu.sistema.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.sistema.dto.ProductoDto;
import edu.sistema.entity.Producto;
import edu.sistema.entity.Tipo;
import edu.sistema.mapper.ProductoMapper;
import edu.sistema.repository.ProductoRepository;
import edu.sistema.repository.TipoRepository;
import edu.sistema.service.ProductoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService{

	private ProductoRepository productoRepository;
	 private TipoRepository tipoRepository;
	
	//Construir res API para agregar un producto
		@Override
		public ProductoDto createProducto (ProductoDto productoDto) {
			Producto producto = ProductoMapper.mapToProducto(productoDto);

			// Guardar imagen si se adjunta
		    MultipartFile foto = productoDto.getFotoProducto();
		    if (foto != null && !foto.isEmpty()) {
		        try {
		            String nombreArchivo = org.springframework.util.StringUtils.cleanPath(foto.getOriginalFilename());
		            String rutaDirectorio = "/app/imagenes/productos";
		            
		            // Crear el objeto File para el directorio
		            java.io.File directorio = new java.io.File(rutaDirectorio);
		            
		            // Verificar si existe y, si no, crearlo
		            if (!directorio.exists()) {
		                directorio.mkdirs(); // Usa mkdirs() para crear directorios intermedios también
		            }

		            //Crear el objeto File para el destino final del archivo
		            java.io.File destino = new java.io.File(directorio, nombreArchivo);
		            
		            //Transferir el archivo
		            foto.transferTo(destino);

		            producto.setFotoProductoNombre(nombreArchivo);

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    Producto savedProducto = productoRepository.save(producto);
		    return ProductoMapper.mapToProductoDto(savedProducto);
		}
		
		
		 // Buscar producto por ID
	    @Override
	    public ProductoDto getProductoById(Integer productoId) {
	        Producto producto = productoRepository.findById(productoId).orElse(null);
	        return ProductoMapper.mapToProductoDto(producto);
	    }

	    // Obtener todos los productos
	    @Override
	    public List<ProductoDto> getAllProductos() {
	        List<Producto> productos = productoRepository.findAll();
	        return productos.stream()
	                .map(ProductoMapper::mapToProductoDto)
	                .collect(Collectors.toList());
	    }

	    // Actualizar producto
	    @Override
	    public ProductoDto updateProducto(Integer productoId, ProductoDto updateProducto) {
	        Producto producto = productoRepository.findById(productoId).orElse(null);

	        if (producto != null) {
	            // Actualizar campos básicos
	            producto.setNombreProducto(updateProducto.getNombreProducto());
	            producto.setDescripcionP(updateProducto.getDescripcionP());
	            producto.setPrecioP(updateProducto.getPrecioP());

	            // Actualizar el estatus
	            if (updateProducto.getEstatus() != null) {
	                producto.setEstatus(updateProducto.getEstatus());
	            }

	            // Actualizar tipo si se envía
	            if (updateProducto.getIdTipo() != null) {
	                Tipo tipo = tipoRepository.findById(updateProducto.getIdTipo()).orElse(null);
	                producto.setTipo(tipo);
	            }

	            // Manejar la nueva imagen (si se sube una)
	            MultipartFile nuevaFoto = updateProducto.getFotoProducto();
	            if (nuevaFoto != null && !nuevaFoto.isEmpty()) {
	                try {
	                    String nombreArchivo = org.springframework.util.StringUtils.cleanPath(nuevaFoto.getOriginalFilename());
	                    String rutaDirectorio = "/app/imagenes/productos";

	                    java.io.File directorio = new java.io.File(rutaDirectorio);
	                    if (!directorio.exists()) {
	                        directorio.mkdirs();
	                    }

	                    // (Opcional) eliminar imagen anterior si existe
	                    if (producto.getFotoProductoNombre() != null) {
	                        java.io.File anterior = new java.io.File(rutaDirectorio, producto.getFotoProductoNombre());
	                        if (anterior.exists()) anterior.delete();
	                    }

	                    // Guardar nueva imagen
	                    java.io.File destino = new java.io.File(directorio, nombreArchivo);
	                    nuevaFoto.transferTo(destino);

	                    // Actualizar nombre del archivo en BD
	                    producto.setFotoProductoNombre(nombreArchivo);

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }

	            Producto updatedProducto = productoRepository.save(producto);
	            return ProductoMapper.mapToProductoDto(updatedProducto);
	        }

	        return null;
	    
	    }

	    // Eliminar producto
	    @Override
	    public void deleteProducto(Integer productoId) {
	        productoRepository.deleteById(productoId);
	    }
		
		
	    @Override
	    public ProductoDto cambiarEstatus(Integer productoId, String estatus) {
	        Producto producto = productoRepository.findById(productoId).orElse(null);

	        if (producto == null) {
	            return null;
	        }

	        producto.setEstatus(estatus);
	        Producto productoActualizado = productoRepository.save(producto);

	        return ProductoMapper.mapToProductoDto(productoActualizado);
	    }
		
	
	    @Override
	    public List<ProductoDto> buscarPorNombre(String nombre) {
	        List<Producto> productos = productoRepository.findAll();
	        return productos.stream()
	                .filter(p -> nombre != null && p.getNombreProducto().toLowerCase().contains(nombre.toLowerCase()))
	                .map(ProductoMapper::mapToProductoDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<ProductoDto> buscarPorTipo(Integer idTipo) {
	        List<Producto> productos = productoRepository.findAll();
	        return productos.stream()
	                .filter(p -> p.getTipo() != null && p.getTipo().getIdTipo().equals(idTipo))
	                .map(ProductoMapper::mapToProductoDto)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public List<ProductoDto> buscarPorRangoPrecio(Double precioMin, Double precioMax) {
	        List<Producto> productos = productoRepository.findAll();
	        return productos.stream()
	                .filter(p -> {
	                    double precio = p.getPrecioP();
	                    boolean dentroRango = true;
	                    if (precioMin != null) dentroRango = precio >= precioMin;
	                    if (precioMax != null) dentroRango = dentroRango && precio <= precioMax;
	                    return dentroRango;
	                })
	                .map(ProductoMapper::mapToProductoDto)
	                .collect(Collectors.toList());
	    }
	    
}
