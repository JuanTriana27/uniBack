package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.EstadoPropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoPropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoPropiedad;
import java.util.List;

public interface EstadoPropiedadService {
    List<EstadoPropiedad> getAllEstadoPropiedad();

    // Otro metodo para consultar estado propiedad por su id
    EstadoPropiedadDTO getEstadoPropiedadPorId(Integer id);

    CreateEstadoPropiedadResponse createEstadoPropiedad(CreateEstadoPropiedadRequest createEstadoPropiedadRequest) throws Exception;
}