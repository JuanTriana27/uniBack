package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.PropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreatePropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreatePropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.Propiedad;
import java.util.List;

public interface PropiedadService {
    List<Propiedad> getAllPropiedad();
    PropiedadDTO getPropiedadPorId(Integer id);

    CreatePropiedadResponse createPropiedad(CreatePropiedadRequest createPropiedadRequest) throws Exception;
    CreatePropiedadResponse updatePropiedad(Integer id, CreatePropiedadRequest createPropiedadRequest) throws Exception;
    void deletePropiedad(Integer id) throws Exception;
}