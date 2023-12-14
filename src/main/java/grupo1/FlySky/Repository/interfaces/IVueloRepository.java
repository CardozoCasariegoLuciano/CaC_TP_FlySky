package grupo1.FlySky.Repository.interfaces;

import grupo1.FlySky.Entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("IVueloRepository2")
public interface IVueloRepository extends JpaRepository<Vuelo,Long> {
    Optional<Vuelo> findVueloById(Long id);
}
