package co.edu.usbcali.inmobiliaria.mapper;

import co.edu.usbcali.inmobiliaria.dto.PropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreatePropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreatePropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoPropiedad;
import co.edu.usbcali.inmobiliaria.model.Persona;
import co.edu.usbcali.inmobiliaria.model.Propiedad;
import co.edu.usbcali.inmobiliaria.model.TipoPropiedad;
import java.time.LocalDateTime;

public class PropiedadMapper {

    public static PropiedadDTO modelToDTO(Propiedad propiedad) {
        return PropiedadDTO.builder()
                .direccion(propiedad.getDireccion())
                .ciudad(propiedad.getCiudad())
                .codigo_postal(propiedad.getCodigoPostal())
                .metros_cuadrados(propiedad.getMetrosCuadrados())
                .habitaciones(propiedad.getHabitaciones())
                .banos(propiedad.getBanos())
                .precio(propiedad.getPrecio())
                .fecha_creacion(propiedad.getFechaCreacion())
                .idPropietario(propiedad.getPropietario() != null ? propiedad.getPropietario().getIdPersona() : null)
                .idAsesor(propiedad.getAsesor() != null ? propiedad.getAsesor().getIdPersona() : null)
                .idTipoPropiedad(propiedad.getTipoPropiedad() != null ? propiedad.getTipoPropiedad().getIdTipoPropiedad() : null)
                .idEstadoPropiedad(propiedad.getEstadoPropiedad() != null ? propiedad.getEstadoPropiedad().getIdEstadoPropiedad() : null)
                .build();
    }

    public static Propiedad dtoToModel(PropiedadDTO propiedadDTO) {
        return Propiedad.builder()
                .direccion(propiedadDTO.getDireccion())
                .ciudad(propiedadDTO.getCiudad())
                .codigoPostal(propiedadDTO.getCodigo_postal())
                .metrosCuadrados(propiedadDTO.getMetros_cuadrados())
                .habitaciones(propiedadDTO.getHabitaciones())
                .banos(propiedadDTO.getBanos())
                .precio(propiedadDTO.getPrecio())
                .fechaCreacion(propiedadDTO.getFecha_creacion())
                // Para las relaciones, solo seteamos el ID, los demás campos deben buscarse y asignarse aparte en el servicio
                .propietario(propiedadDTO.getIdPropietario() != null ? Persona.builder().idPersona(propiedadDTO.getIdPropietario()).build() : null)
                .asesor(propiedadDTO.getIdAsesor() != null ? Persona.builder().idPersona(propiedadDTO.getIdAsesor()).build() : null)
                .tipoPropiedad(propiedadDTO.getIdTipoPropiedad() != null ? TipoPropiedad.builder().idTipoPropiedad(propiedadDTO.getIdTipoPropiedad()).build() : null)
                .estadoPropiedad(propiedadDTO.getIdEstadoPropiedad() != null ? EstadoPropiedad.builder().idEstadoPropiedad(propiedadDTO.getIdEstadoPropiedad()).build() : null)
                .build();
    }

    public static Propiedad createRequestToModel(CreatePropiedadRequest createPropiedadRequest) {
        return Propiedad.builder()
                .direccion(createPropiedadRequest.getDireccion())
                .ciudad(createPropiedadRequest.getCiudad())
                .codigoPostal(createPropiedadRequest.getCodigoPostal())
                .metrosCuadrados(createPropiedadRequest.getMetrosCuadrados())
                .habitaciones(createPropiedadRequest.getHabitaciones())
                .banos(createPropiedadRequest.getBanos())
                .precio(createPropiedadRequest.getPrecio())
                .fechaCreacion(LocalDateTime.now())
                .build();
    }

    public static CreatePropiedadResponse modelToCreateResponse(Propiedad propiedad) {
        return CreatePropiedadResponse.builder()
                .idPropiedad(propiedad.getIdPropiedad())
                .direccion(propiedad.getDireccion())
                .ciudad(propiedad.getCiudad())
                .codigoPostal(propiedad.getCodigoPostal())
                .metrosCuadrados(propiedad.getMetrosCuadrados())
                .habitaciones(propiedad.getHabitaciones())
                .banos(propiedad.getBanos())
                .precio(propiedad.getPrecio())
                .fechaCreacion(propiedad.getFechaCreacion())
                .nombrePropietario(
                        propiedad.getPropietario() == null ? null :
                                propiedad.getPropietario().getNombre() + " " + propiedad.getPropietario().getApellido())
                .nombreAsesor(
                        propiedad.getAsesor() == null ? null :
                                propiedad.getAsesor().getNombre() + " " + propiedad.getAsesor().getApellido())
                .tipoPropiedad(
                        propiedad.getTipoPropiedad() == null ? null :
                                propiedad.getTipoPropiedad().getNombre())
                .estadoPropiedad(
                        propiedad.getEstadoPropiedad() == null ? null :
                                propiedad.getEstadoPropiedad().getNombre())
                .build();
    }

}