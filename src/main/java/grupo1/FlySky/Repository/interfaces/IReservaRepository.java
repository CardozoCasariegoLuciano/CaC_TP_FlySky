package grupo1.FlySky.Repository.interfaces;

import grupo1.FlySky.Entity.Reserva;

import java.util.ArrayList;
import java.util.List;

public interface IReservaRepository {
    List<Reserva> dataBase = new ArrayList<>();
    Reserva guardar(Reserva reserva);
    List<Reserva> devuelveTodos();
}
