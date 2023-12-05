package grupo1.FlySky.Repository;

import grupo1.FlySky.Entity.Vuelo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VueloRepository implements IVueloRepository{

    @PersistenceContext
    EntityManager entityManager;

    private List<Vuelo> dataBaseCategory = new ArrayList<>();
    @Override
    public List<Vuelo> fullVuelos() {
        String query = "FROM Vuelo";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Vuelo guardarVuelo(Vuelo vuelo) {
        dataBaseCategory.add(vuelo);
        return vuelo;
    }
}
