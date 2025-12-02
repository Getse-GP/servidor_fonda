package edu.sistema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

	 private Integer idcliente;
	    private String nombreCliente;
	    private String telefono;
	    private String correo;
	    private Integer idUsuario;   // CLAVE
	    private UsuarioDto usuario;  // Igual que EmpleadoDto
}
