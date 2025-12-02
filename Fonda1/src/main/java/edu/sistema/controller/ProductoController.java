package edu.sistema.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sistema.dto.ProductoDto;
import edu.sistema.service.ProductoService;
import lombok.AllArgsConstructor;

@CrossOrigin("*")

@AllArgsConstructor
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

	//Inyeccion de dependencia
			private ProductoService productoService;
			
			//Construir el REST API para agregar un producto
			@PostMapping(consumes = "multipart/form-data")
			public ResponseEntity<ProductoDto> crearProducto(@ModelAttribute ProductoDto productoDto) {
			    ProductoDto guardarProducto = productoService.createProducto(productoDto);
			    return new ResponseEntity<>(guardarProducto, HttpStatus.CREATED);
			}
	
			 // Construir el GET para buscar producto por ID
		    @GetMapping("{id}")
		    public ResponseEntity<ProductoDto> getProductoById(@PathVariable("id") Integer productoId) {
		        ProductoDto productoDto = productoService.getProductoById(productoId);
		        return ResponseEntity.ok(productoDto);
		    }

		    // Construir el GET para obtener todos los productos
		    @GetMapping
		    public ResponseEntity<List<ProductoDto>> getAllProductos() {
		        List<ProductoDto> productos = productoService.getAllProductos();
		        return ResponseEntity.ok(productos);
		    }

		    // Construir el PUT para actualizar producto
		    @PutMapping(value = "{id}", consumes = "multipart/form-data")
		    public ResponseEntity<ProductoDto> updateProducto(
		            @PathVariable("id") Integer productoId,
		            @ModelAttribute ProductoDto updateProducto) {
		        ProductoDto productoDto = productoService.updateProducto(productoId, updateProducto);
		        return ResponseEntity.ok(productoDto);
		    }

		    // Construir el DELETE para eliminar producto
		    @DeleteMapping("{id}")
		    public ResponseEntity<String> deleteProducto(@PathVariable("id") Integer productoId) {
		        productoService.deleteProducto(productoId);
		        return ResponseEntity.ok("Producto eliminado");
		    }
			
			
		    //cambiar solo el estatus
		    @PatchMapping("/{id}/estatus")
		    public ResponseEntity<ProductoDto> cambiarEstatusProducto(
		            @PathVariable("id") Integer productoId,
		            @RequestParam String estatus) {

		        ProductoDto actualizado = productoService.cambiarEstatus(productoId, estatus);
		        
		        if (actualizado == null) {
		            return ResponseEntity.notFound().build();
		        }

		        return ResponseEntity.ok(actualizado);
		    }
		        
		    
		 // Buscar por nombre
		    @GetMapping("/buscar/nombre")
		    public ResponseEntity<List<ProductoDto>> buscarPorNombre(@RequestParam String nombre) {
		        List<ProductoDto> productos = productoService.buscarPorNombre(nombre);
		        return ResponseEntity.ok(productos);
		    }

		    // Buscar por tipo
		    @GetMapping("/buscar/tipo/{idTipo}")
		    public ResponseEntity<List<ProductoDto>> buscarPorTipo(@PathVariable Integer idTipo) {
		        List<ProductoDto> productos = productoService.buscarPorTipo(idTipo);
		        return ResponseEntity.ok(productos);
		    }

		    // Buscar por rango de precio
		    @GetMapping("/buscar/precio")
		    public ResponseEntity<List<ProductoDto>> buscarPorRangoPrecio(
		            @RequestParam(required = false) Double precioMin,
		            @RequestParam(required = false) Double precioMax) {
		        List<ProductoDto> productos = productoService.buscarPorRangoPrecio(precioMin, precioMax);
		        return ResponseEntity.ok(productos);
		    }
}
