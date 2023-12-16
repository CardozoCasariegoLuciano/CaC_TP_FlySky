package grupo1.FlySky.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EstadisticasDTO {
    int cantAsientos;
    int asientosReservaMax;
    Long asientosReservaMaxId;
    Long reservaMasCaraId;
    Float reservaMasCara;
    int cantReservas;
    Float sumaDiaria;
}

