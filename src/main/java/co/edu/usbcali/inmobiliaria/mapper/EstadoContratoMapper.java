package co.edu.usbcali.inmobiliaria.mapper;

import co.edu.usbcali.inmobiliaria.dto.EstadoContratoDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoContratoRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoContratoResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoContrato;

public class EstadoContratoMapper {

    // Model to DTO
    public static EstadoContratoDTO modelToDTO(EstadoContrato estadoContrato) {
        return EstadoContratoDTO.builder()
                .nombre(estadoContrato.getNombre())
                .descripcion(estadoContrato.getDescripcion())
                .build();
    }

    // DTO to Model
    public static EstadoContrato dtoToModel(EstadoContratoDTO estadoContratoDTO) {
        return EstadoContrato.builder()
                .nombre(estadoContratoDTO.getNombre())
                .descripcion(estadoContratoDTO.getDescripcion())
                .build();
    }

    // Create Request To Model
    public static EstadoContrato createRequestToModel(CreateEstadoContratoRequest createEstadoContratoRequest) {
        return EstadoContrato.builder()
                .nombre(createEstadoContratoRequest.getNombre())
                .descripcion(createEstadoContratoRequest.getDescripcion())
                .build();
    }

    // Model To Create Response
    public static CreateEstadoContratoResponse modelToCreateResponse(EstadoContrato estadoContrato){
        return CreateEstadoContratoResponse.builder()
                .id(estadoContrato.getIdEstadoContrato())
                .nombre(estadoContrato.getNombre())
                .descripcion(estadoContrato.getDescripcion())
                .build();
    }
}