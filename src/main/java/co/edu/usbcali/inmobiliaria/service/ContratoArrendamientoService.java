package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.ContratoArrendamientoDTO;
import co.edu.usbcali.inmobiliaria.model.ContratoArrendamiento;
import java.util.List;

public interface ContratoArrendamientoService {
    List<ContratoArrendamiento> getAllContratosArrendamiento();
    ContratoArrendamientoDTO getContratoArrendamientoPorId(Integer id);
    ContratoArrendamientoDTO saveContratoArrendamientoPorId(ContratoArrendamientoDTO contratoArrendamientoDTO) throws Exception;
}