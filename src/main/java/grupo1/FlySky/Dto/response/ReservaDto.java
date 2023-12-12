package grupo1.FlySky.Dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReservaDto {

    private Long id;
    private Long vueloID;
    private Long clienteID;
    //private LocalDate fecha;
    private int cantAsientos;
    private String metodoPago;
    private Double precioFinal;
}
