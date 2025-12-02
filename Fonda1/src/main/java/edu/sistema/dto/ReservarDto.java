package edu.sistema.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservarDto {

	private Integer idReserva;
    private LocalDate fecha;
    private LocalTime hora;
    private Integer idCliente; // referencia a Cliente (otro microservicio)
    private Integer idMesa;    // referencia a Mesa
    private String estatus;
}
