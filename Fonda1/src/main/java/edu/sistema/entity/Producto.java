package edu.sistema.entity;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Producto")
public class Producto {
	

	@Id
	// Notacion para que la PK sea autoincrementable
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@Column(name="nombreProducto")
	private String nombreProducto;
	
	@Column(name="descripcionP")
	private String descripcionP;
	
	@Column(name="precioP")
	private Integer precioP;
	
	@Column(name = "estatus", nullable = false, length = 10)
	private String estatus = "Activo";
	
	
	  // Relacion muchos a uno con Tipo
    @ManyToOne
    @JoinColumn(name = "idTipo", referencedColumnName = "idTipo")
    private Tipo tipo;
	
    
    // Campo transitorio para recibir el archivo desde el formulario
    @Transient
    private MultipartFile fotoProducto;

    // Nombre del archivo almacenado (se guarda en BD)
    @Column(name = "foto_producto_nombre")
    private String fotoProductoNombre;

    // Asigna automaticamente el nombre del archivo al subirlo
    public void setFotoProducto(MultipartFile fotoProducto) {
        if (fotoProducto != null && !fotoProducto.isEmpty()) {
            this.fotoProducto = fotoProducto;
            this.fotoProductoNombre = fotoProducto.getOriginalFilename();
        }
    }
    
    
    

}
