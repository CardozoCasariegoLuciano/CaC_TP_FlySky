package grupo1.FlySky.Repository;

import grupo1.FlySky.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<Usuario, Long> {
}
