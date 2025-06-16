package co.edu.usbcali.inmobiliaria.service.impl;

import co.edu.usbcali.inmobiliaria.dto.ContratoArrendamientoDTO;
import co.edu.usbcali.inmobiliaria.mapper.ContratoArrendamientoMapper;
import co.edu.usbcali.inmobiliaria.model.ContratoArrendamiento;
import co.edu.usbcali.inmobiliaria.repository.ContratoArrendamientoRepository;
import co.edu.usbcali.inmobiliaria.service.ContratoArrendamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratoArrendamientoServiceImpl implements ContratoArrendamientoService {

    private final ContratoArrendamientoRepository contratoArrendamientoRepository;

    @Override
    public List<ContratoArrendamiento> getAllContratosArrendamiento() {
        // Aquí vamos a hacer la implementación de este

        // Declaro e inicializo una lista de Tipos de Propiedades
        // En la inicialización, utilizo el método findAll del Repository
        // Para ir a la base de datos y traer todos los tipos de propiedad :D
        List<ContratoArrendamiento> contratoArrendamientos = contratoArrendamientoRepository.findAll();

        // Retorno los tipos de contrato consultados en la linea anterior
        return contratoArrendamientos;
    }

    @Override
    public ContratoArrendamientoDTO getContratoArrendamientoPorId(Integer id) {
        return ContratoArrendamientoMapper.modelToDTO(
                contratoArrendamientoRepository.getReferenceById(id)
        );
    }

    @Override
    public ContratoArrendamientoDTO saveContratoArrendamientoPorId(ContratoArrendamientoDTO contratoArrendamientoDTO) throws Exception {
        // Poner validaciones logicas respecto al DTO del contrato arrendamiento

        // Validar que el contrato no sea nulo
        if (contratoArrendamientoDTO == null) {
            throw new Exception("El tipo de contrato a guardar no puede ser nulo");
        }

        // Validar que la fecha no sea nulo
        if (contratoArrendamientoDTO.getFecha_inicio() == null) {
            throw new Exception("La fecha de inicio no puede ser nula");
        }

        // Validar que la fecha no sea nulo
        if (contratoArrendamientoDTO.getFechaFin() == null) {
            throw new Exception("La fecha de fin no puede ser nula");
        }

        // Validar que la renta Mensual no sea nulo
        if (contratoArrendamientoDTO.getRentaMensual() == null) {
            throw new Exception("La renta mensual no puede ser nula");
        }

        // Validar que el deposito no sea nulo
        if (contratoArrendamientoDTO.getDeposito() == null) {
            throw new Exception("El depósito no puede ser nulo");
        }

        // Convertir DTO a Model
        ContratoArrendamiento contratoArrendamiento = ContratoArrendamientoMapper.dtoToModel(contratoArrendamientoDTO);

        // Persistir el modelo en db
        contratoArrendamiento = contratoArrendamientoRepository.save(contratoArrendamiento);

        // Convertir a DTO para retornar
        ContratoArrendamientoDTO contratoArrendamientoDTOPersistido = ContratoArrendamientoMapper.modelToDTO(contratoArrendamiento);

        // Retornar el DTO persistido como lo solicita el metodo
        return contratoArrendamientoDTOPersistido;
    }
}
