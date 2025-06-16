package co.edu.usbcali.inmobiliaria.controller;

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

    @GetMapping("/todos")
    public List<TipoPropiedad> buscarTodos(){
        return tipoPropiedadService.getAllTiposPropiedad();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<TipoPropiedadDTO> buscarPorId(@PathVariable Integer id) {
        return new ResponseEntity<>(
                tipoPropiedadService.getTipoPropiedadPorId(id),
                HttpStatus.OK
        );
    }

    //metodo para guardar un nuevo tipo de propiedad y sera de tipo POST
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<CreateTipoPropiedadResponse> guardarNuevo(@RequestBody CreateTipoPropiedadRequest createTipoPropiedadRequest) throws Exception {
        CreateTipoPropiedadResponse createPersonaResponse = tipoPropiedadService.createTipoPropiedad(createTipoPropiedadRequest);
        return new ResponseEntity<>(createPersonaResponse, HttpStatus.CREATED);
    }
}