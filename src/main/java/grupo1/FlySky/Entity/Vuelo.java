package grupo1.FlySky.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "VUELO")
public class Vuelo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vueloSeq")
    @SequenceGenerator(sequenceName = "vueloSeq", allocationSize = 1, name = "vueloSeq")
    private Long id;

    @Column(name = "AEROLINEA")
    private String aerolinea;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "PAIS_ORIGEN")
    private String paisOrigen;

    @Column(name = "PAIS_DESTINO")
    private String paisDestino;

    @Column(name = "AEROPUERTO_ORIGEN")
    private String aeropuertoOrigen;

    @Column(name = "AEROPUERTO_DESTINO")
    private String aeropuertoDestino;

    @Column(name = "CUPOS_LIBRES")
    private int cuposLibres;

    @Column(name = "FECHA_SALIDA")
    private LocalDateTime fechaSalida;

    @Column(name = "FECHA_LLEGADA")
    private LocalDateTime fechaLlegada;
}
