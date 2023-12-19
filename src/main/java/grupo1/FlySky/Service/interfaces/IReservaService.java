package grupo1.FlySky.Service.interfaces;

import grupo1.FlySky.Dto.Requests.ReservaSaveDto;
import grupo1.FlySky.Dto.Responses.ReservaDto;

import java.util.List;

public interface IReservaService {
    ReservaDto nuevaReserva(ReservaSaveDto reserva);

    List<ReservaDto> devolverTodos();

    List<ReservaDto> reservasPorUsuario(Long id_usuario);
}
