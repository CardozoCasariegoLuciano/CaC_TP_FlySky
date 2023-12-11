package grupo1.FlySky.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clienteSeq")
    @SequenceGenerator(sequenceName = "clienteSeq", allocationSize = 1, name = "clienteSeq")
    private Long id;

    @Column(name = "NOMBRE_CLIENTE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "FECHA")
    private LocalDate fechaNac;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "ROL")
    @Enumerated(EnumType.STRING)
    private Roles.Rol rol;
}
