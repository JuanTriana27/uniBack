package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.EstadoContratoDTO;
import co.edu.usbcali.inmobiliaria.model.EstadoContrato;
import java.util.List;

public interface EstadoContratoService {
    List<EstadoContrato> getAllEstadosContrato();

    // Metodo nuevo para consultar estado contrato por su id
    EstadoContratoDTO getEstadoContratoPorId(Integer id);
}
