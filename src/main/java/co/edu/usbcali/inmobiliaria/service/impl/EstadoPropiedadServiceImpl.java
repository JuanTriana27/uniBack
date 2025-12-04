package co.edu.usbcali.inmobiliaria.service.impl;

import co.edu.usbcali.inmobiliaria.dto.EstadoPropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoPropiedadResponse;
import co.edu.usbcali.inmobiliaria.mapper.EstadoPropiedadMapper;
import co.edu.usbcali.inmobiliaria.model.EstadoPropiedad;
import co.edu.usbcali.inmobiliaria.repository.EstadoPropiedadRepository;
import co.edu.usbcali.inmobiliaria.service.EstadoPropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoPropiedadServiceImpl implements EstadoPropiedadService {

    private final EstadoPropiedadRepository estadoPropiedadRepository;

    // Metodo para obtener todos los estados propiedad
    @Override
    public List<EstadoPropiedad> getAllEstadoPropiedad() {
        return estadoPropiedadRepository.findAll();
    }

    // Metodo para obtener por ID
    @Override
    public EstadoPropiedadDTO getEstadoPropiedadPorId(Integer id) {

        // Consultar en db el estado propiedad por ID
        EstadoPropiedad estadoPropiedad = estadoPropiedadRepository.getReferenceById(id);

        // Mapear hacia dto el resultado que trae el modelo
        EstadoPropiedadDTO estadoPropiedadDTO = EstadoPropiedadMapper.modelToDTO(estadoPropiedad);

        // retornar el objeto mapeado a DTO
        return estadoPropiedadDTO;
    }

    // Metodo para crear un nuevo estado propiedad
    @Override
    public CreateEstadoPropiedadResponse createEstadoPropiedad(CreateEstadoPropiedadRequest createEstadoPropiedadRequest) throws Exception {
        // Poner validaciones lógicas respecto al DTO del Tipo de Propiedad

        // Validar que el estado de propiedad no sea nulo
        if (createEstadoPropiedadRequest == null) {
            throw new Exception("El estado de la propiedad no puede ser nulo");
        }

        // Validar que el nombre no sea nulo
        if (createEstadoPropiedadRequest.getNombre() == null ||
                createEstadoPropiedadRequest.getNombre().isBlank() == true){
            throw new Exception("El nombre del estado propiedad no puede ser nulo o vacio");
        }

        // Validar que la descripción del tipo de propiedad a agregar no sea nula ni vacía
        if (createEstadoPropiedadRequest.getDescripcion() == null ||
                createEstadoPropiedadRequest.getDescripcion().isBlank() == true){
            throw new Exception("La descripcion del estado propiedad no puede ser nulo o vacio");
        }

        // Convertir de Request a Model
        EstadoPropiedad estadoPropiedad = EstadoPropiedadMapper.createRequestToModel(createEstadoPropiedadRequest);

        // Persistir el modelo en base de datos
        estadoPropiedad = estadoPropiedadRepository.save(estadoPropiedad);

        // Convertir a Response para retornar
        CreateEstadoPropiedadResponse createEstadoPropiedadResponse = EstadoPropiedadMapper.modelToCreateResponse(estadoPropiedad);

        // Retornar el Response persistido como lo solicita el métdodo
        return createEstadoPropiedadResponse;
    }

    // Metodo para actualizar un estado propiedad
    @Override
    public CreateEstadoPropiedadResponse updateEstadoPropiedad(Integer id, CreateEstadoPropiedadRequest createEstadoPropiedadRequest) throws Exception {
        // Verificamos que exista el estado de propiedad
        EstadoPropiedad estadoPropiedad = estadoPropiedadRepository.findById(id)
                .orElseThrow(() -> new Exception("Estado propiedad no encontrado"));

        // Validar que el estado de propiedad no sea nulo
        if (createEstadoPropiedadRequest == null) {
            throw new Exception("El estado de la propiedad no puede ser nulo");
        }

        // Validar que el nombre no sea nulo
        if (createEstadoPropiedadRequest.getNombre() == null ||
                createEstadoPropiedadRequest.getNombre().isBlank() == true){
            throw new Exception("El nombre del estado propiedad no puede ser nulo o vacio");
        }

        // Validar que la descripción del tipo de propiedad a agregar no sea nula ni vacía
        if (createEstadoPropiedadRequest.getDescripcion() == null ||
                createEstadoPropiedadRequest.getDescripcion().isBlank() == true){
            throw new Exception("La descripcion del estado propiedad no puede ser nulo o vacio");
        }

        // Actualizar los datos de la propiedad con los nuevos valores
        estadoPropiedad.setNombre(createEstadoPropiedadRequest.getNombre());
        estadoPropiedad.setDescripcion(createEstadoPropiedadRequest.getDescripcion());

        // Guardar el estado propiedad actualizado
        estadoPropiedad = estadoPropiedadRepository.save(estadoPropiedad);

        // Mapear y retornar el response
        return EstadoPropiedadMapper.modelToCreateResponse(estadoPropiedad);
    }

    // Metodo para eliminar un estado propiedad
    @Override
    public void deleteEstadoPropiedad(Integer id) throws Exception {

        // Verificamos que exista
        if(!estadoPropiedadRepository.existsById(id)){
            throw new Exception("Estado propiedad no encontrado");
        }

        // Eliminar estado propiedad
        estadoPropiedadRepository.deleteById(id);
    }
}
