package co.edu.usbcali.inmobiliaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "propiedades")

public class Propiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Integer idPropiedad;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(name = "codigo_postal", nullable = false, length = 10)
    private String codigoPostal;

    @Column(name = "metros_cuadrados")
    private Integer metrosCuadrados;

    @Column(name = "habitaciones")
    private Integer habitaciones;

    @Column(name = "banos")
    private Integer banos;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    //Llaves Foraneas
    @ManyToOne
    @JoinColumn(name = "id_propietario", referencedColumnName = "id_persona", nullable = false)
    private Persona propietario;

    @ManyToOne
    @JoinColumn(name = "id_asesor", referencedColumnName = "id_persona", nullable = false)
    private Persona asesor;

    @ManyToOne
    @JoinColumn(name = "id_tipo_propiedad", referencedColumnName = "id_tipo_propiedad", nullable = false)
    private TipoPropiedad tipoPropiedad;

    @ManyToOne
    @JoinColumn(name = "id_estado_propiedad", referencedColumnName = "id_estado_propiedad", nullable = false)
    private EstadoPropiedad estadoPropiedad;
}
