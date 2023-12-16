package grupo1.FlySky.Repository.interfaces;

import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IReservaRepository extends JpaRepository <Reserva, Long> {
    Optional<Reserva> findReservaById(Long id);

    //@Query("SELECT r FROM Reserva r WHERE r.CLIENTE_ID = :clienteid")
    //List<Reserva> findReservaByClienteID(@Param("cliente_id") Long cliente_id);

    List<Reserva> findByClienteID(Usuario usuario);


}
