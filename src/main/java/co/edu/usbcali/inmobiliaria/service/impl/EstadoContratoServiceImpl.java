package co.edu.usbcali.inmobiliaria.service.impl;

import co.edu.usbcali.inmobiliaria.dto.EstadoContratoDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoContratoRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoContratoResponse;
import co.edu.usbcali.inmobiliaria.mapper.EstadoContratoMapper;
import co.edu.usbcali.inmobiliaria.model.EstadoContrato;
import co.edu.usbcali.inmobiliaria.repository.EstadoContratoRepository;
import co.edu.usbcali.inmobiliaria.service.EstadoContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoContratoServiceImpl implements EstadoContratoService {

    private final EstadoContratoRepository estadoContratoRepository;

    // Metodo para obtener todos los estados contrato
    @Override
    public List<EstadoContrato> getAllEstadosContrato() {
        return estadoContratoRepository.findAll();
    }

    // Metodo para consultar estado contrato por su id
    @Override
    public EstadoContratoDTO getEstadoContratoPorId(Integer id){

        // Consultar en db el estado contrato por ID
        EstadoContrato estadoContrato = estadoContratoRepository.getReferenceById(id);

        // Mapear hacia DTO el resultado que trae el modelo
        EstadoContratoDTO estadoContratoDTO = EstadoContratoMapper.modelToDTO(estadoContrato);

        // Retornar el objeto mapeado a DTO
        return estadoContratoDTO;
    }

    // Metodo para crear un nuevo estado contrato
    @Override
    public CreateEstadoContratoResponse createEstadoContrato(CreateEstadoContratoRequest createEstadoContratoRequest) throws Exception {

        // Validar que no sea nuelo el estado contrato
        if(createEstadoContratoRequest == null){
            throw new Exception("El estado contrato no puede ser nulo");
        }

        // Validar que el nombre no sea nulo
        if (createEstadoContratoRequest.getNombre() == null ||
        createEstadoContratoRequest.getNombre().isBlank() == true) {
            throw new Exception("El nombre del estado contrato no puede ser nulo o vacio");
        }

        // Validar que la descripcion no sea nula ni vacia
        if (createEstadoContratoRequest.getDescripcion() == null ||
        createEstadoContratoRequest.getDescripcion().isBlank() == true) {
            throw new Exception("La descripcion del estado contrato no puede ser nula o vacia");
        }

        // Convertir de Request a Model
        EstadoContrato estadoContrato = EstadoContratoMapper.createRequestToModel(createEstadoContratoRequest);

        // Persistir el modelo en base de datos
        estadoContrato = estadoContratoRepository.save(estadoContrato);

        // Convertir a Response para retornar
        CreateEstadoContratoResponse createEstadoContratoResponse = EstadoContratoMapper.modelToCreateResponse(estadoContrato);

        // Retornar el response persistido como lo solicita el metodo
        return createEstadoContratoResponse;
    }

    // Metodo para actualizar estado contrato
    @Override
    public CreateEstadoContratoResponse updateEstadoContrato(Integer id, CreateEstadoContratoRequest createEstadoContratoRequest) throws Exception{
        // Vetificamos que exista estado contrato
        EstadoContrato estadoContrato = estadoContratoRepository.findById(id)
                .orElseThrow(() -> new Exception("Estado contrato no encontrado"));

        // Validar que el estado contrato no sea nulo
        if (createEstadoContratoRequest == null) {
            throw new Exception("El estado contrato no puede ser nulo");
        }

        // validar nombre
        if (createEstadoContratoRequest.getNombre() == null ||
        createEstadoContratoRequest.getNombre().isBlank() == true){
            throw new Exception("El nombre del estado contrato no puede ser nulo o vacio");
        }

        // Validar descripcion
        if (createEstadoContratoRequest.getDescripcion() == null ||
        createEstadoContratoRequest.getDescripcion().isBlank() == true){
            throw new Exception("La descripcion del estado contrato no puede ser nulo o vacio");
        }

        // Actualizar los datos de la propiedad con los nuevos valores
        estadoContrato.setNombre(createEstadoContratoRequest.getNombre());
        estadoContrato.setDescripcion(createEstadoContratoRequest.getDescripcion());

        // Guardar el estado contrato actualizado
        estadoContrato = estadoContratoRepository.save(estadoContrato);

        // Mapear y retornar el response
        return EstadoContratoMapper.modelToCreateResponse(estadoContrato);
    }

    // Metodo para eliminar un estado contrato
    @Override
    public void deleteEstadoContrato(Integer id) throws Exception {
        // Validamos que exista
        if(!estadoContratoRepository.existsById(id)){
            throw new Exception("Estado contrato no encontrado");
        }

        // Eliminamos
        estadoContratoRepository.deleteById(id);
    }
}
