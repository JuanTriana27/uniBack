package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.ContratoArrendamientoDTO;
import co.edu.usbcali.inmobiliaria.model.ContratoArrendamiento;
import co.edu.usbcali.inmobiliaria.service.ContratoArrendamientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador para la gestión de contratos de arrendamiento.
 * Este controlador expone endpoints relacionados con las operaciones de contratos
 * de arrendamiento, como obtener todos los contratos y buscar un contrato por su ID.
 */
@RestController
@RequestMapping("contrato-arrendamiento")
@RequiredArgsConstructor
public class ContratoArrendamientoController {
    // Inyeccion
    private final ContratoArrendamientoService contratoArrendamientoService;

    @GetMapping("/todos")
    public List<ContratoArrendamiento> obtenerTodosLosContratosArrendamiento() {
        return contratoArrendamientoService.getAllContratosArrendamiento();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<ContratoArrendamientoDTO> buscarPorId(@PathVariable Integer id){
        return new ResponseEntity<>(contratoArrendamientoService.getContratoArrendamientoPorId(id), HttpStatus.OK);
    }
}
