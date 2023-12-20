package grupo1.FlySky.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaPorUsuarioDto {
    UsuarioDto datosUsuario;
    List<ReservaDto> reservasRealizadas;
}
