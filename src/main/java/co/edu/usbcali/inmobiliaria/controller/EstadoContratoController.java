package co.edu.usbcali.inmobiliaria.controller;

import co.edu.usbcali.inmobiliaria.dto.EstadoContratoDTO;
import co.edu.usbcali.inmobiliaria.model.EstadoContrato;
import co.edu.usbcali.inmobiliaria.service.EstadoContratoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estado-contrato")
@RequiredArgsConstructor
public class EstadoContratoController {
    // Inyeccion
    private final EstadoContratoService estadoContratoService;

    @GetMapping("/todos")
    public List<EstadoContrato> obtenerTodosLosEstadosContratos(){
        return estadoContratoService.getAllEstadosContrato();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<EstadoContratoDTO> buscarPorId(@PathVariable Integer id){
        EstadoContratoDTO estadoContratoDTO = estadoContratoService.getEstadoContratoPorId(id);

        return new ResponseEntity<>(estadoContratoDTO, HttpStatus.OK);
    }
}