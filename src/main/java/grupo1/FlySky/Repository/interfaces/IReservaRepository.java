package grupo1.FlySky.Repository.interfaces;

import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReservaRepository extends JpaRepository <Reserva, Long> {

    Optional<Reserva> findReservaById(Long id);

    List<Reserva> findByClienteID(Usuario usuario);

    List<Reserva> findByFecha(LocalDate fecha);
}
