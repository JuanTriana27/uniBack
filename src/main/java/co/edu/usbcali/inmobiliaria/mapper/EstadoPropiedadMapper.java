package co.edu.usbcali.inmobiliaria.mapper;

import co.edu.usbcali.inmobiliaria.dto.EstadoPropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoPropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoPropiedad;

public class EstadoPropiedadMapper {

    public static EstadoPropiedadDTO modelToDTO(EstadoPropiedad estadoPropiedad){
        // Forma tradicional
        /*EstadoPropiedadDTO estadoPropiedadDTO = new EstadoPropiedadDTO();

        estadoPropiedadDTO.setNombre(estadoPropiedad.getNombre());
        estadoPropiedadDTO.setDescripcion(estadoPropiedad.getDescripcion());

        return estadoPropiedadDTO;*/

        return EstadoPropiedadDTO.builder()
                .nombre(estadoPropiedad.getNombre())
                .descripcion(estadoPropiedad.getDescripcion())
                .build();
    }

    public static EstadoPropiedad dtoToModel(EstadoPropiedadDTO estadoPropiedadDTO) {
        return EstadoPropiedad.builder()
                .nombre(estadoPropiedadDTO.getNombre())
                .descripcion(estadoPropiedadDTO.getDescripcion())
                .build();
    }

    public static EstadoPropiedad createRequestToModel(CreateEstadoPropiedadRequest createEstadoPropiedadRequest) {
        return EstadoPropiedad.builder()
                .nombre(createEstadoPropiedadRequest.getNombre())
                .descripcion(createEstadoPropiedadRequest.getDescripcion())
                .build();
    }

    public static CreateEstadoPropiedadResponse modelToCreateResponse(EstadoPropiedad estadoPropiedad) {
        return CreateEstadoPropiedadResponse.builder()
                .id(estadoPropiedad.getIdEstadoPropiedad())
                .nombre(estadoPropiedad.getNombre())
                .descripcion(estadoPropiedad.getDescripcion())
                .build();
    }
}