package co.edu.usbcali.inmobiliaria.mapper;

import co.edu.usbcali.inmobiliaria.dto.ContratoArrendamientoDTO;
import co.edu.usbcali.inmobiliaria.model.ContratoArrendamiento;

public class ContratoArrendamientoMapper {

    public static ContratoArrendamientoDTO modelToDTO(ContratoArrendamiento contratoArrendamiento) {
        return ContratoArrendamientoDTO.builder()
                .fecha_inicio(contratoArrendamiento.getFechaInicio())
                .fechaFin(contratoArrendamiento.getFechaFin())
                .rentaMensual(contratoArrendamiento.getRentaMensual())
                .deposito(contratoArrendamiento.getDeposito())
                .build();
    }

    public static ContratoArrendamiento dtoToModel(ContratoArrendamientoDTO contratoArrendamientoDTO) {
        return ContratoArrendamiento.builder()
                .fechaInicio(contratoArrendamientoDTO.getFecha_inicio())
                .fechaFin(contratoArrendamientoDTO.getFechaFin())
                .rentaMensual(contratoArrendamientoDTO.getRentaMensual())
                .deposito(contratoArrendamientoDTO.getDeposito())
                .build();
    }
}
