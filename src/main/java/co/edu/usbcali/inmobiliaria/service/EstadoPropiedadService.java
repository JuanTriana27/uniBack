package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.EstadoPropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoPropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoPropiedad;
import java.util.List;

public interface EstadoPropiedadService {

    // Metodo para listar todos los estados de propiedad
    List<EstadoPropiedad> getAllEstadoPropiedad();

    // Metodo para consultar por id
    EstadoPropiedadDTO getEstadoPropiedadPorId(Integer id);

    // Metodo para crear un nuevo estado de propiedad
    CreateEstadoPropiedadResponse createEstadoPropiedad(CreateEstadoPropiedadRequest createEstadoPropiedadRequest) throws Exception;

    // Metodo para actualizar estado de propiedad
    CreateEstadoPropiedadResponse updateEstadoPropiedad(Integer id, CreateEstadoPropiedadRequest createEstadoPropiedadRequest) throws Exception;

    // Metodo para eliminar estado de propiedad
    void deleteEstadoPropiedad(Integer id) throws Exception;
}