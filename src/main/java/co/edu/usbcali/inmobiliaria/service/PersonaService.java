package co.edu.usbcali.inmobiliaria.service;

import co.edu.usbcali.inmobiliaria.dto.PersonaDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreatePersonaRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreatePersonaResponse;
import co.edu.usbcali.inmobiliaria.model.Persona;
import java.util.List;

public interface PersonaService {
    List<Persona> getAllPersonas();

    // Metodo para consultar personas por su ID
    PersonaDTO getPersonaPorId(Integer id);

    CreatePersonaResponse createPersona(CreatePersonaRequest createPersonaRequest) throws Exception;
    CreatePersonaResponse updatePersona(Integer id, CreatePersonaRequest createPersonaRequest) throws Exception;
    void deletePersona(Integer id) throws Exception;
}