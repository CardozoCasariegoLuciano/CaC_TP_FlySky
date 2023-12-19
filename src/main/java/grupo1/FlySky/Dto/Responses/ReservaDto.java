package grupo1.FlySky.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReservaDto {

    private Long id;
    private Long vueloID;
    private Long clienteID;
    private LocalDate fecha;
    private int cantAsientos;
    private String metodoPago;
    private Float precioFinal;
}
