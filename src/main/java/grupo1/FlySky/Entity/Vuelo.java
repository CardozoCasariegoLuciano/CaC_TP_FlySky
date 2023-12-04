package grupo1.FlySky.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Vuelo {
    private Long idVuelo;
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
