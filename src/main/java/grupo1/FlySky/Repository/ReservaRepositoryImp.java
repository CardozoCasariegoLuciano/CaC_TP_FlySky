package grupo1.FlySky.Repository;

import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Repository.interfaces.IReservaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservaRepositoryImp implements IReservaRepository {

    private List<Reserva> dataBase = new ArrayList<>();

    @Override
    public Reserva guardar(Reserva reserva) {
        dataBase.add(reserva);
        return reserva;
    }

    @Override
    public List<Reserva> devuelveTodos() {
        return dataBase;
    }

}
