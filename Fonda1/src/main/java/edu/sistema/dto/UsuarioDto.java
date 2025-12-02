package edu.sistema.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

	private Integer id;
    private Integer estatus;
    private LocalDateTime fechaRegistro;
    private String username;
 // Campo solo de entrada
    private String password;
    // Se utiliza el DTO de Perfil en lugar de la entidad completa
    private PerfilDto perfil;
	
}
