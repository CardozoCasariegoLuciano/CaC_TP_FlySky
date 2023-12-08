package grupo1.FlySky.Service.interfaces;

import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ResponseDTO;

import java.util.List;

public interface IReservaService {
    ResponseDTO nuevaReserva(ReservaSaveDto reserva);
    List<ReservaSaveDto> devolverTodos();
}
