package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.MessageResponse;
import co.edu.usbcali.inmobiliaria.dto.PersonaDTO;
import co.edu.usbcali.inmobiliaria.dto.request.CreatePersonaRequest;
import co.edu.usbcali.inmobiliaria.dto.response.CreatePersonaResponse;
import co.edu.usbcali.inmobiliaria.model.Persona;
import co.edu.usbcali.inmobiliaria.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Peticiones desde front
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("persona")
@RequiredArgsConstructor
public class PersonaController {
    // Inyeccion de dependencias del servicio en el controlador
    private final PersonaService personaService;

    @GetMapping("/todos")
    public List<Persona> obtenerTodasLasPersonas() {
        return personaService.getAllPersonas();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<PersonaDTO> buscarPorId(@PathVariable Integer id){
        PersonaDTO personaDTO = personaService.getPersonaPorId(id);

        return new ResponseEntity<>(personaDTO, HttpStatus.OK);
    }

    //metodo para guardar un nuevo tipo de propiedad y sera de tipo POST
    @PostMapping("/guardar-nuevo")
    public ResponseEntity<CreatePersonaResponse> guardarNuevo(@RequestBody CreatePersonaRequest createPersonaRequest) throws Exception {
        CreatePersonaResponse createPersonaResponse = personaService.createPersona(createPersonaRequest);
        return new ResponseEntity<>(createPersonaResponse, HttpStatus.CREATED);
    }

    // Endpoint para actualizar por id
    @PutMapping("/update/{id}")
    public ResponseEntity<CreatePersonaResponse> persona(
            @PathVariable Integer id,
            @RequestBody CreatePersonaRequest createPersonaRequest){

        // Bloque try
        try{
            CreatePersonaResponse response = personaService.updatePersona(id, createPersonaRequest);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deletePersona(@PathVariable Integer id) {
        try {
            personaService.deletePersona(id);
            return new ResponseEntity<>(new MessageResponse("Eliminado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
