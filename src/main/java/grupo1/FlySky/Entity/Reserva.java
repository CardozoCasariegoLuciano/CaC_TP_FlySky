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

@Entity
@Data
@Table(name = "Reserva")
public class Reserva {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boletoSeq")
    @SequenceGenerator(sequenceName = "boletoSeq", allocationSize = 1, name = "boletoSeq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RESERVA")
    private Reserva reservaId;

    private C

    @Column(name = "PRECIO_FINAL")
    private Double precioFinal;

    @Column(name = "CANT_ASIENTOS")
    private int cantAsientos;

    @Column(name = "METODO_PAGO")
    private String metodoPago;

}
