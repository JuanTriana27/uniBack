package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.TipoPropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateTipoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateTipoPropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.TipoPropiedad;
import java.util.List;

public interface TipoPropiedadService {

    // Metodo para obtener todos los tipos de propiedad
    List<TipoPropiedad> getAllTiposPropiedad();

    // Metodo para obtener un tipo de propiedad por su id
    TipoPropiedadDTO getTipoPropiedadPorId(Integer id);

    // Metodo para crear un nuevo tipo de propiedad
    CreateTipoPropiedadResponse createTipoPropiedad(CreateTipoPropiedadRequest createTipoPropiedadRequest) throws Exception;

    // Metodo para actualizar tipo de propiedad
    CreateTipoPropiedadResponse updateTipoPropiedad(Integer id, CreateTipoPropiedadRequest createTipoPropiedadRequest) throws Exception;

    // Metodo para eliminar tipo de propiedad
    void deleteTipoPropiedad(Integer id) throws Exception;
}