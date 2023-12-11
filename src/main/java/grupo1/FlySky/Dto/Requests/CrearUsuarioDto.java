package grupo1.FlySky.Dto.Requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearUsuarioDto {

    @NotNull(message = "Nombre es requerido")
    private String nombre;

    @NotNull(message = "Apellido es requerido")
    private String apellido;

    @NotNull(message = "Ingrese una fecha de nacimiento")
    private LocalDate fechaNac;

    @NotNull(message = "Ingrese un email")
    @Email(message = "El email tiene que ser valido")
    private String email;

    @NotNull(message = "Numero de telefono es requerido")
    private String telefono;
}
