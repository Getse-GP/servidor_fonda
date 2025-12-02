package edu.sistema.mapper;

import edu.sistema.dto.TipoDto;
import edu.sistema.entity.Tipo;

public class TipoMapper {

	public static TipoDto mapToTipoDto(Tipo tipo) {
       
        return new TipoDto(
                tipo.getIdTipo(),
                tipo.getTipo(),
                tipo.getDescripcion()
        );
    }

    public static Tipo mapToTipo(TipoDto tipoDto) {
       
        return new Tipo(
                tipoDto.getIdTipo(),
                tipoDto.getTipo(),
                tipoDto.getDescripcion()
        );
    }
	
}
