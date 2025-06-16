package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.MessageResponse;
import co.edu.usbcali.inmobiliaria.dto.PropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreatePropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreatePropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.Propiedad;
import co.edu.usbcali.inmobiliaria.service.PropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Peticiones desde front
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("propiedad")
@RequiredArgsConstructor
public class PropiedadController {

    private final PropiedadService propiedadService;

    // Obtener todas las propiedades datos (GET)
    @GetMapping("/todos")
    public List<Propiedad> obtenerTodasLasPropiedades(){
        return propiedadService.getAllPropiedad();
    }

    // Obtener Propiedad por su ID (GET)
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<PropiedadDTO> buscarPorId(@PathVariable Integer id){
        PropiedadDTO propiedadDTO = propiedadService.getPropiedadPorId(id);

        return new ResponseEntity<>(propiedadDTO, HttpStatus.OK);
    }

    // Endpoint para crear propiedad (POST)
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<CreatePropiedadResponse> guardarNuevo(@RequestBody CreatePropiedadRequest request) throws Exception {
        CreatePropiedadResponse response = propiedadService.createPropiedad(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Endpoint para actulizar propiedad existente
    @PutMapping("/update/{id}")
    public ResponseEntity<CreatePropiedadResponse> propiedad(
            @PathVariable Integer id,
            @RequestBody CreatePropiedadRequest request){

        // Bloque try
        try{
            CreatePropiedadResponse response = propiedadService.updatePropiedad(id, request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deletePropiedad(@PathVariable Integer id) {
        try {
            propiedadService.deletePropiedad(id);
            return new ResponseEntity<>(new MessageResponse("Propiedad eliminada correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}