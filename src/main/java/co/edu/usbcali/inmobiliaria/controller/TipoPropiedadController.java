package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.MessageResponse;
import co.edu.usbcali.inmobiliaria.dto.TipoPropiedadDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreateTipoPropiedadRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreateTipoPropiedadResponse;
import co.edu.usbcali.inmobiliaria.model.TipoPropiedad;
import co.edu.usbcali.inmobiliaria.service.TipoPropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tipo-propiedad")
@RequiredArgsConstructor
public class TipoPropiedadController {
    // Inyección de dependencias del Servicio en el Controlador
    private final TipoPropiedadService tipoPropiedadService;

    // Metodo para obtener todos los tipos de propiedad
    @GetMapping("/todos")
    public List<TipoPropiedad> buscarTodos(){
        return tipoPropiedadService.getAllTiposPropiedad();
    }

    // Metodo para obtener por ID
    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<TipoPropiedadDTO> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(
                tipoPropiedadService.getTipoPropiedadPorId(id),
                HttpStatus.OK
        );
    }

    // Metodo para guardar un nuevo tipo de propiedad
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<CreateTipoPropiedadResponse> guardarNuevo(@RequestBody CreateTipoPropiedadRequest createTipoPropiedadRequest) throws Exception {
        CreateTipoPropiedadResponse createPersonaResponse = tipoPropiedadService.createTipoPropiedad(createTipoPropiedadRequest);
        return new ResponseEntity<>(createPersonaResponse, HttpStatus.CREATED);
    }

    // Metodo para actualizar tipo de propiedad
    @PutMapping("/update/{id}")
    public ResponseEntity<CreateTipoPropiedadResponse> tipoPropiedad(
            @PathVariable Integer id,
            @RequestBody CreateTipoPropiedadRequest createTipoPropiedadRequest){

        try{
            CreateTipoPropiedadResponse response = tipoPropiedadService.updateTipoPropiedad(id, createTipoPropiedadRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Metodo para eliminar tipo de propiedad
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteTipoPropiedad(@PathVariable Integer id) {
        try {
            tipoPropiedadService.deleteTipoPropiedad(id);
            return new ResponseEntity<>(new MessageResponse("Tipo Propiedad eliminado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}