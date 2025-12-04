package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.EstadoContratoDTO;
import co.edu.usbcali.inmobiliaria.dto.MessageResponse;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoContratoRequest;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoContratoResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoContrato;
import co.edu.usbcali.inmobiliaria.service.EstadoContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estado-contrato")
@RequiredArgsConstructor
public class EstadoContratoController {
    // Inyeccion
    private final EstadoContratoService estadoContratoService;

    // Metodo para obtener todos los estados de contrato
    @GetMapping("/todos")
    public List<EstadoContrato> obtenerTodosLosEstadosContratos() {
        return estadoContratoService.getAllEstadosContrato();
    }

    // Metodo para obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<EstadoContratoDTO> buscarPorId(@PathVariable Integer id) {
        EstadoContratoDTO estadoContratoDTO = estadoContratoService.getEstadoContratoPorId(id);

        return new ResponseEntity<>(estadoContratoDTO, HttpStatus.OK);
    }

    // Metodo para guardar un nuevo estado de contrato y sera de tipo POST
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<CreateEstadoContratoResponse> guardarNuevo(
            @RequestBody CreateEstadoContratoRequest createEstadoContratoRequest) throws Exception {
        CreateEstadoContratoResponse createEstadoContratoResponse = estadoContratoService.createEstadoContrato(createEstadoContratoRequest);
        return new ResponseEntity<>(createEstadoContratoResponse, HttpStatus.CREATED);
    }

    // Metodo para actualizar estado de contrato
    @PutMapping("/update/{id}")
    public ResponseEntity<CreateEstadoContratoResponse> estadoContrato(
            @PathVariable Integer id,
            @RequestBody CreateEstadoContratoRequest createEstadoContratoRequest){
        try{
            CreateEstadoContratoResponse response = estadoContratoService.updateEstadoContrato(id, createEstadoContratoRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo para eliminar
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteEstadoContrato(@PathVariable Integer id) {
        try{
            estadoContratoService.deleteEstadoContrato(id);
            return new ResponseEntity<>(new MessageResponse("Estado Contrato eliminado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}