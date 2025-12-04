package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.EstadoPropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.MessageResponse;
import co.edu.usbcali.inmobiliaria.dto.request.CreateEstadoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateEstadoPropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.EstadoPropiedad;
import co.edu.usbcali.inmobiliaria.service.EstadoPropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estado-propiedad")
@RequiredArgsConstructor
public class EstadoPropiedadController {
    // Inyeccion
    private final EstadoPropiedadService estadoPropiedadService;

    @GetMapping("/todos")
    public List<EstadoPropiedad> obtenerTodosLosEstadosPropiedad(){
        return estadoPropiedadService.getAllEstadoPropiedad();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<EstadoPropiedadDTO> buscarPorId(@PathVariable Integer id){
        EstadoPropiedadDTO estadoPropiedadDTO = estadoPropiedadService.getEstadoPropiedadPorId(id);

        return new ResponseEntity<>(estadoPropiedadDTO, HttpStatus.OK);
    }

    //metodo para guardar un nuevo tipo de propiedad y sera de tipo POST
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<CreateEstadoPropiedadResponse> guardarNuevo(
            @RequestBody CreateEstadoPropiedadRequest createTipoPropiedadRequest) throws Exception {
        CreateEstadoPropiedadResponse createTipoPropiedadResponse = estadoPropiedadService.createEstadoPropiedad(createTipoPropiedadRequest);
        return new ResponseEntity<>(createTipoPropiedadResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CreateEstadoPropiedadResponse> estadoPropiedad(
            @PathVariable Integer id,
            @RequestBody CreateEstadoPropiedadRequest createEstadoPropiedadRequest) {

        try {
            CreateEstadoPropiedadResponse response =
                    estadoPropiedadService.updateEstadoPropiedad(id, createEstadoPropiedadRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo para eliminar
    @DeleteMapping( "/delete/{id}")
    public ResponseEntity<MessageResponse> deleteEstadoPropiedad(@PathVariable Integer id) {
        try{
            estadoPropiedadService.deleteEstadoPropiedad(id);
            return new ResponseEntity<>(new MessageResponse("Estado Propiedad eliminado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}