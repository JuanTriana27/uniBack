package co.edu.usbcali.inmobiliaria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContratoArrendamientoDTO {
    private LocalDateTime fecha_inicio;
    private LocalDateTime fechaFin;
    private Double rentaMensual;
    private Double deposito;
}
