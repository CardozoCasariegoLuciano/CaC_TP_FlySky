package grupo1.FlySky.Dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaSaveDto {
    private Long id;
    private Long vueloID;
    private Long clienteID;
    //private LocalDate fecha;
    private int cantAsientos;
    private String metodoPago;
    private double precioFinal;

}
