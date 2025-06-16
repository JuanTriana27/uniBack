package co.edu.usbcali.inmobiliaria.mapper;

import co.edu.usbcali.inmobiliaria.dto.VentaPropiedadDTO;
import co.edu.usbcali.inmobiliaria.model.VentaPropiedad;

public class VentaPropiedadMapper {

    public static VentaPropiedadDTO modelToDto(VentaPropiedad ventaPropiedad) {
        return VentaPropiedadDTO.builder()
                .fecha_venta(ventaPropiedad.getFechaVenta())
                .precio_venta(ventaPropiedad.getPrecioVenta())
                .comision_asesor(ventaPropiedad.getComisionAsesor())
                .build();
    }

    public static VentaPropiedad dtoToModel(VentaPropiedadDTO ventaPropiedadDTO) {
        return VentaPropiedad.builder()
                .fechaVenta(ventaPropiedadDTO.getFecha_venta())
                .precioVenta(ventaPropiedadDTO.getPrecio_venta())
                .comisionAsesor(ventaPropiedadDTO.getComision_asesor())
                .build();
    }
}
