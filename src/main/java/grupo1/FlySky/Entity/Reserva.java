package grupo1.FlySky.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "RESERVA")
public class Reserva {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservaSeq")
    @SequenceGenerator(sequenceName = "reservaSeq", allocationSize = 1, name = "reservaSeq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VUELO")
    private Vuelo vueloId;

    @Column(name = "CANTIDAD_ASIENTOS")
    private Integer cantidadAsientos;

    @Column(name = "PRECIO_FINAL")
    private Double precioFinal;

    @Column(name = "METODO_PAGO")
    private String metodoPago;

    @Column(name = "FECHA")
    private LocalDate fecha;
}
