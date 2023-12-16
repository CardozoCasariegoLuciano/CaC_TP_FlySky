package grupo1.FlySky.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Reserva")
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boletoSeq")
    @SequenceGenerator(sequenceName = "boletoSeq", allocationSize = 1, name = "boletoSeq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VUELO_ID")
    private Vuelo vueloID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID")
    private Usuario clienteID;

    @Column(name = "FECHA")
    private LocalDate fecha;

    @Column(name = "PRECIO_FINAL")
    private float precioFinal;

    @Column(name = "CANT_ASIENTOS")
    private int cantAsientos;

    @Column(name = "METODO_PAGO")
    private String metodoPago;


}
