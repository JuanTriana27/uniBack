package co.edu.usbcali.inmobiliaria.service;
import co.edu.usbcali.inmobiliaria.dto.VentaPropiedadDTO;
import co.edu.usbcali.inmobiliaria.model.VentaPropiedad;
import java.util.List;

public interface VentaPropiedadService {
    List<VentaPropiedad> getAllVentasPropiedad();
    VentaPropiedadDTO getVentaPropiedadPorId(Integer id);
    VentaPropiedadDTO saveVentaPropiedad(VentaPropiedadDTO ventaPropiedadDTO) throws Exception;
}
