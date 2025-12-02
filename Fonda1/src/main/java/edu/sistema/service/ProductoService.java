package edu.sistema.service;

import java.util.List;

import edu.sistema.dto.ProductoDto;

public interface ProductoService {


    // Agregar un producto
    ProductoDto createProducto(ProductoDto productoDto);

    // Buscar producto por ID
    ProductoDto getProductoById(Integer productoId);

    // Obtener todos los productos
    List<ProductoDto> getAllProductos();

    // Actualizar producto
    ProductoDto updateProducto(Integer productoId, ProductoDto updateProducto);

    // Eliminar producto
    void deleteProducto(Integer productoId);
    
    // Cambiar solo el estatus
    ProductoDto cambiarEstatus(Integer productoId, String estatus);
	
    //busquedas
    List<ProductoDto> buscarPorNombre(String nombre);
    List<ProductoDto> buscarPorTipo(Integer idTipo);
    List<ProductoDto> buscarPorRangoPrecio(Double precioMin, Double precioMax);


}
