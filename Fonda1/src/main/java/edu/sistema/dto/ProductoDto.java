package edu.sistema.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
	
	
	private Integer idProducto;
	    private String nombreProducto;
	    private String descripcionP;
	    private Integer precioP;
	    private String estatus; 
	    private Integer idTipo; // Solo el id del tipo
	    private MultipartFile fotoProducto; //Campo para manejar el archivo desde el formulario
	    private String fotoProductoNombre; // Nombre del archivo (guardado en BD)
	
}
