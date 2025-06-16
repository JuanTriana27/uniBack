package co.edu.usbcali.inmobiliaria.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePropiedadResponse {
    private Integer idPropiedad;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private Integer metrosCuadrados;
    private Integer habitaciones;
    private Integer banos;
    private Double precio;
    private LocalDateTime fechaCreacion;

    private String nombrePropietario;
    private String nombreAsesor;
    private String tipoPropiedad;
    private String estadoPropiedad;
}
