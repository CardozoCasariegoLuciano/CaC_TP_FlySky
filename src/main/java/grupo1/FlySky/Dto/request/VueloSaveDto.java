package grupo1.FlySky.Dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VueloSaveDto {

    private Long id;
    private String aerolinea;
    private Double precio;
    private String paisOrigen;
    private String paisDestino;
    private String aeropuertoOrigen;
    private String aeropuertoDestino;
    private int cuposLibres;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
}
