package grupo1.FlySky.Service.interfaces;

import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ResponseDTO;

public interface IReservaService {
    ResponseDTO nuevaReserva(ReservaSaveDto reserva);
}
