package grupo1.FlySky.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import grupo1.FlySky.Dto.request.ReservaSaveDto;
import grupo1.FlySky.Dto.response.ResponseDTO;
import grupo1.FlySky.Entity.Reserva;
import grupo1.FlySky.Repository.ReservaRepositoryImp;
import grupo1.FlySky.Service.interfaces.IReservaService;

import java.util.LinkedList;
import java.util.List;

public class ReservaServiceImp implements IReservaService {
    private ReservaRepositoryImp repository;
    private final ObjectMapper mapper;

    public ReservaServiceImp(ReservaRepositoryImp repository){
        this.repository = repository;
        mapper = new ObjectMapper();
    }

    @Override
    public ResponseDTO nuevaReserva(ReservaSaveDto reserva) {
        Reserva reservaEntity = mapper.convertValue(reserva, Reserva.class);
        if(verificarExistencia(reservaEntity))
            throw new CloneException("La reserva ya existe");
        Reserva respuestaRepo = repository.save(reservaEntity);
        if(respuestaRepo == null)
            throw new InsertDBException("Falla durante la insercion");
        return new ResponseDTO("La reserva "+respuestaRepo.getReservaId()+" se guardo correctamente.");
    }

    private boolean verificarExistencia(Reserva r){
        List<Reserva> listaEntidad = respository.findAll();
        if(listaEntidad.isEmpty())
            return false;
        List<Reserva> listaBusqueda =listaEntidad.stream()
                .filter(reserva -> reserva.equals(r))
                .toList();

        return !listaBusqueda.isEmpty();

    }
}
