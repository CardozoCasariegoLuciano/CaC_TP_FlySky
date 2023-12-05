package grupo1.FlySky.Repository;



import grupo1.FlySky.Entity.Vuelo;

import java.util.List;

public interface IVueloRepository {

    List<Vuelo> fullVuelos();

    Vuelo guardarVuelo(Vuelo vuelo);
}
