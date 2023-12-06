package grupo1.FlySky.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import grupo1.FlySky.Service.interfaces.IReservaService;

public class ReservaServiceImp implements IReservaService {
    private IReservaRepository repository;
    private final ObjectMapper mapper;

    public ReservaServiceImp(reservaRepositoryImp repository){
        this.repository = repository;
        mapper = new ObjectMapper();
    }
}
