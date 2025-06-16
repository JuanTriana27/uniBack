package co.edu.usbcali.inmobiliaria.mapper;

import co.edu.usbcali.inmobiliaria.dto.PersonaDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreatePersonaRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreatePersonaResponse;
import co.edu.usbcali.inmobiliaria.model.Persona;

public class PersonaMapper {

    public static PersonaDTO modelToDTO(Persona persona) {
        return PersonaDTO.builder()
                .nombre(persona.getNombre())
                .apellido(persona.getApellido())
                .telefono(persona.getTelefono())
                .email(persona.getEmail())
                .direccion(persona.getDireccion())
                .ciudad(persona.getCiudad())
                .build();
    }

    public static Persona dtoToModel(PersonaDTO personaDTO) {
        return Persona.builder()
                .nombre(personaDTO.getNombre())
                .apellido(personaDTO.getApellido())
                .telefono(personaDTO.getTelefono())
                .email(personaDTO.getEmail())
                .direccion(personaDTO.getDireccion())
                .ciudad(personaDTO.getCiudad())
                .codigo_postal(personaDTO.getCodigo_postal())
                .build();
    }

    public static Persona createRequestToModel(CreatePersonaRequest createPersonaRequest) {
        return Persona.builder()
                .nombre(createPersonaRequest.getNombre())
                .apellido(createPersonaRequest.getApellido())
                .telefono(createPersonaRequest.getTelefono())
                .email(createPersonaRequest.getEmail())
                .direccion(createPersonaRequest.getDireccion())
                .ciudad(createPersonaRequest.getCiudad())
                .codigo_postal(createPersonaRequest.getCodigo_postal())
                .build();
    }

    public static CreatePersonaResponse modelToCreateResponse(Persona persona) {
        return CreatePersonaResponse.builder()
                .nombre(persona.getNombre())
                .apellido(persona.getApellido())
                .telefono(persona.getTelefono())
                .email(persona.getEmail())
                .direccion(persona.getDireccion())
                .ciudad(persona.getCiudad())
                .codigo_postal(persona.getCodigo_postal())
                .build();
    }

}