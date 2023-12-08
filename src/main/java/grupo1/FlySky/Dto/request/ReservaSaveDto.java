package grupo1.FlySky.Dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservaSaveDto {
    private int vueloID;
    private int clienteID;
    private LocalDate fecha;
    private int cantAsientos;
    private String metodoPago;
    private float precioFinal;

}
