package grupo1.FlySky.Service.interfaces;

import grupo1.FlySky.Dto.EstadisticasDTO;
import grupo1.FlySky.Dto.Responses.UsuarioDto;

import java.time.LocalDate;

public interface IAdminService {
    EstadisticasDTO dailyStatus(UsuarioDto body, LocalDate date);
}
