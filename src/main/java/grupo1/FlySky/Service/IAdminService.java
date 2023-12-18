package grupo1.FlySky.Service;

import grupo1.FlySky.Dto.EstadisticasDTO;

import java.time.LocalDate;

public interface IAdminService {
    EstadisticasDTO dailyStatus(LocalDate date);
}
