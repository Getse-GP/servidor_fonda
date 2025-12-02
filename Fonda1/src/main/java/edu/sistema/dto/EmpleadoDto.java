package edu.sistema.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {

	 private Integer idEmpleado;
	    private String nombre;
	    private String puesto;
	    private Integer idUsuario;
	    private UsuarioDto usuario;
	    private List<AtenderDto> atenciones;

}
