package co.edu.usbcali.inmobiliaria.service.impl;

import co.edu.usbcali.inmobiliaria.dto.PropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreatePropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreatePropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.*;
import co.edu.usbcali.inmobiliaria.mapper.PropiedadMapper;
import co.edu.usbcali.inmobiliaria.repository.*;
import co.edu.usbcali.inmobiliaria.service.PropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PropiedadServiceImpl implements PropiedadService {

    private final PropiedadRepository propiedadRepository;
    private final PersonaRepository personaRepository;
    private final TipoPropiedadRepository tipoPropiedadRepository;
    private final EstadoPropiedadRepository estadoPropiedadRepository;

    @Override
    public List<Propiedad> getAllPropiedad() {
        return propiedadRepository.findAll();
    }

    @Override
    public PropiedadDTO getPropiedadPorId(Integer id) {
        Propiedad propiedad = propiedadRepository.getReferenceById(id);
        return PropiedadMapper.modelToDTO(propiedad);
    }

    @Override
    public CreatePropiedadResponse createPropiedad(CreatePropiedadRequest request) throws Exception {
        // Validar request no nulo
        if (request == null) {
            throw new Exception("La propiedad no puede ser nula");
        }

        // Validar campos básicos
        if (request.getDireccion() == null || request.getDireccion().isBlank()) {
            throw new Exception("La dirección no puede ser nula o vacía");
        }

        if (request.getCiudad() == null || request.getCiudad().isBlank()) {
            throw new Exception("La ciudad no puede ser nula o vacía");
        }

        if (request.getCodigoPostal() == null || request.getCodigoPostal().isBlank()) {
            throw new Exception("El código postal no puede ser nulo o vacío");
        }

        if (request.getMetrosCuadrados() == null || request.getMetrosCuadrados() <= 0) {
            throw new Exception("Los metros cuadrados deben ser mayores a cero");
        }

        if (request.getHabitaciones() == null || request.getHabitaciones() < 1) {
            throw new Exception("El número de habitaciones debe ser al menos 1");
        }

        if (request.getBanos() == null || request.getBanos() < 1) {
            throw new Exception("El número de baños debe ser al menos 1");
        }

        if (request.getPrecio() == null || request.getPrecio() <= 0.0) {
            throw new Exception("El precio debe ser mayor a cero");
        }

        // Validar IDs de entidades relacionadas
        if (request.getIdPropietario() == null || request.getIdPropietario() <= 0) {
            throw new Exception("El ID del propietario es inválido");
        }

        if (request.getIdAsesor() == null || request.getIdAsesor() <= 0) {
            throw new Exception("El ID del asesor es inválido");
        }

        if (request.getIdTipoPropiedad() == null || request.getIdTipoPropiedad() <= 0) {
            throw new Exception("El ID del tipo de propiedad es inválido");
        }

        if (request.getIdEstadoPropiedad() == null || request.getIdEstadoPropiedad() <= 0) {
            throw new Exception("El ID del estado de la propiedad es inválido");
        }

        // Obtener entidades relacionadas
        Persona propietario = personaRepository.findById(request.getIdPropietario())
                .orElseThrow(() -> new Exception("Propietario no encontrado con ID: " + request.getIdPropietario()));

        Persona asesor = personaRepository.findById(request.getIdAsesor())
                .orElseThrow(() -> new Exception("Asesor no encontrado con ID: " + request.getIdAsesor()));

        TipoPropiedad tipoPropiedad = tipoPropiedadRepository.findById(request.getIdTipoPropiedad())
                .orElseThrow(() -> new Exception("Tipo de propiedad no encontrado con ID: " + request.getIdTipoPropiedad()));

        EstadoPropiedad estadoPropiedad = estadoPropiedadRepository.findById(request.getIdEstadoPropiedad())
                .orElseThrow(() -> new Exception("Estado de propiedad no encontrado con ID: " + request.getIdEstadoPropiedad()));

        // Mapear request a modelo y establecer relaciones
        Propiedad propiedad = PropiedadMapper.createRequestToModel(request);
        propiedad.setPropietario(propietario);
        propiedad.setAsesor(asesor);
        propiedad.setTipoPropiedad(tipoPropiedad);
        propiedad.setEstadoPropiedad(estadoPropiedad);
        propiedad.setFechaCreacion(LocalDateTime.now());

        // Guardar en base de datos
        propiedad = propiedadRepository.save(propiedad);

        // Convertir a Response y retornar
        return PropiedadMapper.modelToCreateResponse(propiedad);
    }

    @Override
    public CreatePropiedadResponse updatePropiedad (Integer id, CreatePropiedadRequest request) throws Exception {

        // Verificamos que exista la propiedad
        Propiedad propiedad = propiedadRepository.findById(id)
                .orElseThrow(() -> new Exception("Propiedad no encontrada"));

        // Validaciones basicas (Iguales que en create)
        if (request == null) {
            throw new Exception("La propiedad no puede ser nula");
        }

        if (request.getDireccion() == null || request.getDireccion().isBlank()) {
            throw new Exception("La dirección no puede ser nula o vacía");
        }

        if (request.getCiudad() == null || request.getCiudad().isBlank()) {
            throw new Exception("La ciudad no puede ser nula o vacía");
        }

        if (request.getCodigoPostal() == null || request.getCodigoPostal().isBlank()) {
            throw new Exception("El código postal no puede ser nulo o vacío");
        }

        if (request.getMetrosCuadrados() == null || request.getMetrosCuadrados() <= 0) {
            throw new Exception("Los metros cuadrados deben ser mayores a cero");
        }

        if (request.getHabitaciones() == null || request.getHabitaciones() < 1) {
            throw new Exception("El número de habitaciones debe ser al menos 1");
        }

        if (request.getBanos() == null || request.getBanos() < 1) {
            throw new Exception("El número de baños debe ser al menos 1");
        }

        if (request.getPrecio() == null || request.getPrecio() <= 0.0) {
            throw new Exception("El precio debe ser mayor a cero");
        }

        if (request.getIdPropietario() == null || request.getIdPropietario() <= 0) {
            throw new Exception("El ID del propietario es inválido");
        }

        if (request.getIdAsesor() == null || request.getIdAsesor() <= 0) {
            throw new Exception("El ID del asesor es inválido");
        }

        if (request.getIdTipoPropiedad() == null || request.getIdTipoPropiedad() <= 0) {
            throw new Exception("El ID del tipo de propiedad es inválido");
        }

        if (request.getIdEstadoPropiedad() == null || request.getIdEstadoPropiedad() <= 0) {
            throw new Exception("El ID del estado de la propiedad es inválido");
        }

        // Obtener entidades relacionadas (Propietario, asesor, tipo y estado)
        Persona propietario = personaRepository.findById(request.getIdPropietario())
                .orElseThrow(() -> new Exception("Propietario no encontrado con ID: " + request.getIdPropietario()));
        Persona asesor = personaRepository.findById(request.getIdAsesor())
                .orElseThrow(() -> new Exception("Asesor no encontrado con ID: " + request.getIdAsesor()));
        TipoPropiedad tipoPropiedad = tipoPropiedadRepository.findById(request.getIdTipoPropiedad())
                .orElseThrow(() -> new Exception("Tipo de propiedad no encontrado con ID: " + request.getIdTipoPropiedad()));
        EstadoPropiedad estadoPropiedad = estadoPropiedadRepository.findById(request.getIdEstadoPropiedad())
                .orElseThrow(() -> new Exception("Estado de propiedad no encontrado con ID: " + request.getIdEstadoPropiedad()));

        // Actualizar los campos de la entidad existente
        propiedad.setDireccion(request.getDireccion());
        propiedad.setCiudad(request.getCiudad());
        propiedad.setCodigoPostal(request.getCodigoPostal());
        propiedad.setMetrosCuadrados(request.getMetrosCuadrados());
        propiedad.setHabitaciones(request.getHabitaciones());
        propiedad.setBanos(request.getBanos());
        propiedad.setPrecio(request.getPrecio());

        propiedad.setPropietario(propietario);
        propiedad.setAsesor(asesor);
        propiedad.setTipoPropiedad(tipoPropiedad);
        propiedad.setEstadoPropiedad(estadoPropiedad);

        // Guardar los cambios en BD
        Propiedad propiedadUpdated = propiedadRepository.save(propiedad);

        // Mapear a CreatePropiedadResponse
        return PropiedadMapper.modelToCreateResponse(propiedadUpdated);
    }

    @Override
    public void deletePropiedad(Integer id) throws Exception {

        // Verificamos que exista la propiedad
        if (!propiedadRepository.existsById(id)) {
            throw new Exception("No existe propiedad con ID: " + id);
        }

        // Eliminar Propiedad
        propiedadRepository.deleteById(id);
    }
}