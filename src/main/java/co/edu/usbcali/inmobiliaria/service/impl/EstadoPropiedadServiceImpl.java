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

    @Override
    public List<EstadoPropiedad> getAllEstadoPropiedad() {
        return estadoPropiedadRepository.findAll();
    }

    @Override
    public EstadoPropiedadDTO getEstadoPropiedadPorId(Integer id) {

        // Consultar en db el estado propiedad por ID
        EstadoPropiedad estadoPropiedad = estadoPropiedadRepository.getReferenceById(id);

        // Mapear hacia dto el resultado que trae el modelo
        EstadoPropiedadDTO estadoPropiedadDTO = EstadoPropiedadMapper.modelToDTO(estadoPropiedad);

        // retornar el objeto mapeado a DTO
        return estadoPropiedadDTO;
    }

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

}
