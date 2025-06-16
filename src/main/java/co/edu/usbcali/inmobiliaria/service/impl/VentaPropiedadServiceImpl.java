package co.edu.usbcali.inmobiliaria.service.impl;

import co.edu.usbcali.inmobiliaria.dto.VentaPropiedadDTO;
import co.edu.usbcali.inmobiliaria.mapper.VentaPropiedadMapper;
import co.edu.usbcali.inmobiliaria.model.VentaPropiedad;
import co.edu.usbcali.inmobiliaria.repository.VentaPropiedadRepository;
import co.edu.usbcali.inmobiliaria.service.VentaPropiedadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaPropiedadServiceImpl implements VentaPropiedadService {

    private final VentaPropiedadRepository ventaPropiedadRepository;

    @Override
    public List<VentaPropiedad> getAllVentasPropiedad() {
        // Implementacion de este

        // se inicializa la lista de tipos de propiedades
        // se usa el metodo findAll del repository
        // para ir a la base de datos y traer todos los tipos de propiedad
        List<VentaPropiedad> ventasPropiedad = ventaPropiedadRepository.findAll();
        return ventasPropiedad;
    }

    @Override
    public VentaPropiedadDTO getVentaPropiedadPorId(Integer id) {
        return VentaPropiedadMapper.modelToDto(
                ventaPropiedadRepository.getReferenceById(id)
        );
    }

    @Override
    public VentaPropiedadDTO saveVentaPropiedad(VentaPropiedadDTO ventaPropiedadDTO) throws Exception {
        // Validaciones logicas respecto al DTO de venta propiedad

        // Validar que venta no sea nulo
        if (ventaPropiedadDTO == null) {
            throw new Exception("El tipo de venta a guardar no puede ser nulo");
        }

        // Validar fecha
        if (ventaPropiedadDTO.getFecha_venta() == null) {
            throw new Exception("El tipo de fecha a guardar no puede ser nulo");
        }

        // Precio Venta
        if (ventaPropiedadDTO.getPrecio_venta() == null) {
            throw new Exception("El tipo de precio a guardar no puede ser nulo");
        }

        // Comision asesor
        if (ventaPropiedadDTO.getComision_asesor() == null) {
            throw new Exception("El tipo de comision a guardar no puede ser nulo");
        }

        // Convertir DTO a Model
        VentaPropiedad ventaPropiedad = VentaPropiedadMapper.dtoToModel(ventaPropiedadDTO);

        // Persistir el modelo en db
        ventaPropiedad = ventaPropiedadRepository.save(ventaPropiedad);

        // Convertir a DTO para retornar
        VentaPropiedadDTO ventaPropiedadDTOPersistido = VentaPropiedadMapper.modelToDto(ventaPropiedad);

        // Retornar el DTO persistido como lo solicita el metodo
        return ventaPropiedadDTOPersistido;
    }
}