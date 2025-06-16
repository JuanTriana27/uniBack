package co.edu.usbcali.inmobiliaria.service.impl;

import co.edu.usbcali.inmobiliaria.dto.EstadoContratoDTO;
import co.edu.usbcali.inmobiliaria.mapper.EstadoContratoMapper;
import co.edu.usbcali.inmobiliaria.model.EstadoContrato;
import co.edu.usbcali.inmobiliaria.repository.EstadoContratoRepository;
import co.edu.usbcali.inmobiliaria.service.EstadoContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoContratoServiceImpl implements EstadoContratoService {

    private final EstadoContratoRepository estadoContratoRepository;

    @Override
    public List<EstadoContrato> getAllEstadosContrato() {
        return estadoContratoRepository.findAll();
    }

    @Override
    public EstadoContratoDTO getEstadoContratoPorId(Integer id){

        // Consultar en db el estado contrato por ID
        EstadoContrato estadoContrato = estadoContratoRepository.getReferenceById(id);

        // Mapear hacia DTO el resultado que trae el modelo
        EstadoContratoDTO estadoContratoDTO = EstadoContratoMapper.modelToDTO(estadoContrato);

        // Retornar el objeto mapeado a DTO
        return estadoContratoDTO;
    }
}
