package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.EstadoPropiedadDTO;
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
    public ResponseEntity<CreateEstadoPropiedadResponse> guardarNuevo(@RequestBody CreateEstadoPropiedadRequest createTipoPropiedadRequest) throws Exception {
        CreateEstadoPropiedadResponse createTipoPropiedadResponse = estadoPropiedadService.createEstadoPropiedad(createTipoPropiedadRequest);
        return new ResponseEntity<>(createTipoPropiedadResponse, HttpStatus.CREATED);
    }
}