package co.edu.usbcali.inmobiliaria.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personas")

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_persona", nullable = false, length =30)
    private Integer idPersona;

    @Column (name = "nombre", length =30)
    private String nombre;

    @Column (name = "apellido", nullable = false, length =30)
    private String apellido;

    @Column (name = "telefono", nullable = false, length =30)
    private String telefono;

    @Column (name = "email", length =30)
    private String email;

    @Column (name = "direccion", length =30)
    private String direccion;

    @Column (name = "ciudad", length =30)
    private String ciudad;

    @Column (name = "codigo_postal", length =30)
    private Integer codigo_postal;
}


