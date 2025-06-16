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
public class VentaPropiedadDTO {
    private Integer id;
    private LocalDateTime fecha_venta;
    private Double precio_venta;
    private Double comision_asesor;
}
