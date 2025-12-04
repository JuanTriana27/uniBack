package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.EstadoContratoDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoContratoRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoContratoResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoContrato;
import java.util.List;

public interface EstadoContratoService {

    // Metodo para obtener todos los estados de contrato
    List<EstadoContrato> getAllEstadosContrato();

    // Metodo nuevo para consultar estado contrato por su id
    EstadoContratoDTO getEstadoContratoPorId(Integer id);

    // Metodo para crear un nuevo estado de contrato
    CreateEstadoContratoResponse createEstadoContrato(CreateEstadoContratoRequest createEstadoContratoRequest) throws Exception;

    // Metodo para actualizar estado de contrato
    CreateEstadoContratoResponse updateEstadoContrato(Integer id, CreateEstadoContratoRequest createEstadoContratoRequest) throws Exception;

    // Metodo para eliminar estado de contrato
    void deleteEstadoContrato(Integer id) throws Exception;
}
