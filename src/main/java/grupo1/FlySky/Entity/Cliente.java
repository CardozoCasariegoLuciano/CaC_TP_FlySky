package grupo1.FlySky.Entity;

import jakarta.persistence.Entity;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Cliente {
    private String nombre;
    private String apellido;
    private LocalDate fechaNac;
    private String email;
    private String telefono;
}
